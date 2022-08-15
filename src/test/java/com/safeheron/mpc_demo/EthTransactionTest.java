package com.safeheron.mpc_demo;

import okhttp3.OkHttpClient;
import org.junit.BeforeClass;
import org.junit.Test;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

/**
 * @author safeheron
 * @date 2022/7/12 12:12
 */
public class EthTransactionTest {

    static Web3j web3;
    static Credentials credentials;

    // Replace with your private key
    private static String privetKey = "6b493b4*****ad01d357";
    // Replace with your address
    private static String toAddress = "0x53B11df*****7321789";
    private final static String ERC20_CONTRACT_ADDRESS = "0x7fab42998149d35C03376b09D042220b6c7c778B";
    private static final String READ_ONLY_FROM_ADDRESS = "0x0000000000000000000000000000000000000000";

    @BeforeClass
    public static void beforeClass() {
        web3 = Web3j.build(new HttpService("https://ropsten.infura.io/v3/802679315d0441069fec1ee3d2bc2fcb",
                new OkHttpClient.Builder().build()));
        credentials = Credentials.create(privetKey);
    }

    @Test
    public void testTransaction() throws IOException {
        System.out.println(String.format("Attempting to send transaction from %s to %s, EIP-1559: %s",
                credentials.getAddress(), toAddress, true));
        // Create transaction object
        RawTransaction rawTransaction = createTransaction("0.001", toAddress, null);

        // Encode the transaction and compute the hash value
        byte[] encodedRawTransaction = TransactionEncoder.encode(rawTransaction);
        String hash = Numeric.toHexString(Hash.sha3(encodedRawTransaction));

        // Sign with private key
        String sig  = getLocalSigWithPrivateKey(hash.substring(2));

        // Sign with safeheron mpc
        // String sig = getMpcSigFromSafeheron(hash.substring(2))
        System.out.println("sig: " + sig);

        // Encode with sig and send transaction
        encodeWithSigAndSendTransaction(rawTransaction, sig);
    }

    @Test
    public void testTransactionERC20() throws IOException {
        System.out.println(String.format("Attempting to send contract transaction from %s to %s, EIP-1559: %s",
                credentials.getAddress(), toAddress, true));
        // Create function
        Function function = createERC20Function("6", toAddress);

        // Create transaction object
        RawTransaction rawTransaction = createTransaction("0", ERC20_CONTRACT_ADDRESS, function);

        // Encode the transaction and compute the hash value
        byte[] encodedRawTransaction = TransactionEncoder.encode(rawTransaction);
        String hash = Numeric.toHexString(Hash.sha3(encodedRawTransaction));

        // Sign with private key
        String sig  = getLocalSigWithPrivateKey(hash.substring(2));

        // Sign with safeheron mpc
        // String sig = getMpcSigFromSafeheron(hash.substring(2))
        System.out.println("sig: " + sig);

        // Encode with sig and send transaction
        encodeWithSigAndSendTransaction(rawTransaction, sig);
    }

    private RawTransaction createTransaction(String value, String toAddress, Function contractFunction) throws IOException {
        // Get data from block chain: nonce, chainId, gasLimit and latestBlock's baseFeePerGas
        EthGetTransactionCount ethGetTransactionCount = web3
                .ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).send();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        EthChainId ethChainId = web3.ethChainId().send();

        String data = contractFunction == null ? "" : FunctionEncoder.encode(contractFunction);

        System.out.println("function data: " + data);
        // Estimate gas
        Transaction transaction = Transaction.createEthCallTransaction(credentials.getAddress(), toAddress, data);
        EthEstimateGas gasLimit = web3.ethEstimateGas(transaction).send();

        // Estimate maxFeePerGas, we assume maxPriorityFeePerGas's value is 2(gwei)
        BigInteger maxPriorityFeePerGas = Convert.toWei("2", Convert.Unit.GWEI).toBigInteger();
        EthBlock.Block latestBlock = web3.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send().getBlock();

        // The baseFeePerGas is recommended to be 1.25 times the latest block's baseFeePerGas value
        // maxFeePerGas must not less than baseFeePerGas + maxPriorityFeePerGas
        BigDecimal maxFeePerGas = new BigDecimal(latestBlock.getBaseFeePerGas())
                .multiply(new BigDecimal("1.25"))
                .add(new BigDecimal(maxPriorityFeePerGas));

        // Create raw transaction
        RawTransaction rawTransaction = RawTransaction.createTransaction(
                ethChainId.getChainId().longValue(),
                nonce,
                gasLimit.getAmountUsed(),
                toAddress,
                Convert.toWei(value, Convert.Unit.ETHER).toBigInteger(),
                data,
                maxPriorityFeePerGas,
                maxFeePerGas.toBigInteger());

        return rawTransaction;
    }

    private Function createERC20Function(String tokenAmount, String toAddress) throws IOException {
        BigInteger decimals = getERC20Decimals(ERC20_CONTRACT_ADDRESS);
        BigDecimal tokenValue = new BigDecimal(tokenAmount).multiply(new BigDecimal(Math.pow(10, decimals.longValue())));
        Function function = new Function(
                "transfer",
                Arrays.asList(new Address(toAddress), new Uint256(tokenValue.toBigInteger())),
                Arrays.asList(new TypeReference<Type>() {
                }));

        return function;
    }

    private void encodeWithSigAndSendTransaction(RawTransaction rawTransaction, String sig) throws IOException {
        // Encode transaction with signature
        byte[] signedMessage = TransactionEncoder.encode(rawTransaction, convertSig(sig));
        String hexValue = Numeric.toHexString(signedMessage);
        System.out.println("signed transaction: " + hexValue);

        // Send transaction
        EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue).send();
        if(ethSendTransaction.getError() != null){
            System.out.println("send transaction error, code: " + ethSendTransaction.getError().getCode()
                    + ", message: " + ethSendTransaction.getError().getMessage());
            return;
        }

        String transactionHash = ethSendTransaction.getTransactionHash();
        System.out.println("transaction hash: " + transactionHash);
    }

    private String getLocalSigWithPrivateKey(String hash){
        // Sign the transaction
        Sign.SignatureData signatureData = Sign.signMessage(Numeric.hexStringToByteArray(hash),
                credentials.getEcKeyPair(), false);

        // Let's put Sign.SignatureData's R, S and V into an array and return hex value instead of Sign.SignatureData
        // The purpose of doing this is simply to be consistent with the MPC and explain it better
        byte[] rsv = new byte[signatureData.getR().length + signatureData.getS().length + signatureData.getV().length];
        System.arraycopy(signatureData.getR(), 0, rsv,
                0, signatureData.getR().length);
        System.arraycopy(signatureData.getS(), 0, rsv,
                signatureData.getR().length, signatureData.getS().length);
        System.arraycopy(signatureData.getV(), 0, rsv,
                signatureData.getR().length + signatureData.getS().length, signatureData.getV().length);

        return Numeric.toHexString(rsv).substring(2);
    }

    private String getMpcSigFromSafeheron(String hash){
        // Get sig's value from safeheron with hash(32-byte hex string without '0x' prefix).
        // 1. Request "v1/transactions/mpcsign/create" api to sign then waiting for completed.
        // 2. Get sig from webhook or "v1/transactions/mpcsign/one" api

        // This is an example.
        // The value of sig consists of 32 bytes r + 32 bytes s + 1 byte v
        String sig = "f7fdd1c4d0e2d90a0c2dc1672d37dd0f143f308d4983ccb5a189dd2698780edf224e0add37e131e92781a197c5f2fd28aa5296e15bee4566d9578daa0e532d571b";
        return sig;
    }

    private Sign.SignatureData convertSig(String sig){
        // Split sig into R, S, V
        String sigR = sig.substring(0, 64);
        String sigS = sig.substring(64, 128);
        String sigV = sig.substring(128);

        // Create and return the Sign.SignatureData Object
        return new Sign.SignatureData(Numeric.hexStringToByteArray(sigV),
                Numeric.hexStringToByteArray(sigR),
                Numeric.hexStringToByteArray(sigS));
    }

    private BigInteger getERC20Decimals(String contractAddress) throws IOException {
        final Function function = new Function("decimals",
                Arrays.asList(),
                Arrays.asList(new TypeReference<Uint256>() {}));

        String callResult = web3.ethCall(
                Transaction.createEthCallTransaction(READ_ONLY_FROM_ADDRESS, contractAddress, FunctionEncoder.encode(function)),
                DefaultBlockParameterName.LATEST)
                .send()
                .getValue();

        List<Type> result = FunctionReturnDecoder.decode(callResult, function.getOutputParameters());
        return (BigInteger)result.get(0).getValue();
    }

}
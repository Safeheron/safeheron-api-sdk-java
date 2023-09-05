package com.safeheron.demo.api.web3;

import com.safeheron.client.api.Web3ApiService;
import com.safeheron.client.config.SafeheronConfig;
import com.safeheron.client.request.CreateWeb3EthSignRequest;
import com.safeheron.client.request.OneWeb3SignRequest;
import com.safeheron.client.response.TxKeyResult;
import com.safeheron.client.response.Web3SignResponse;
import com.safeheron.client.utils.ServiceCreator;
import com.safeheron.client.utils.ServiceExecutor;
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
import org.web3j.crypto.Hash;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.Sign;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * @author safeheron
 * @date 2023/8/23
 */
public class Web3EthSignTest {

    static Web3j web3;
    private static final String READ_ONLY_FROM_ADDRESS = "0x0000000000000000000000000000000000000000";

    static Web3ApiService web3ApiService;
    static Map<String, String> config;

    @BeforeClass
    public static void beforeClass() throws FileNotFoundException {

        Yaml yaml = new Yaml();
        File file = new File("src/test/resources/demo/api/web3/config.yaml");
        InputStream inputStream = new FileInputStream(file);
        config = yaml.load(inputStream);

        web3ApiService = ServiceCreator.create(Web3ApiService.class, SafeheronConfig.builder()
                .baseUrl(config.get("baseUrl"))
                .apiKey(config.get("apiKey"))
                .safeheronRsaPublicKey(config.get("safeheronPublicKey"))
                .rsaPrivateKey(config.get("privateKey"))
                .build());

        web3 = Web3j.build(new HttpService(config.get("ethereumRpcApi"),
                new OkHttpClient.Builder().build()));

    }

    @Test
    public void testSendToken() throws Exception {
        String accountKey = config.get("accountKey");
        String fromAddress = config.get("accountTokenAddress");
        String contractAddress = config.get("erc20ContractAddress");
        String toAddress = config.get("toAddress");
        System.out.println(String.format("Attempting to send contract transaction from %s to %s, EIP-1559: %s",
                fromAddress, toAddress, true));
        // Create function
        Function function = createERC20Function(contractAddress, "1", toAddress);

        // Create transaction object
        RawTransaction rawTransaction = createRawTransaction(fromAddress, "0", contractAddress, function);


        // Encode the transaction and compute the hash value
        byte[] encodedRawTransaction = TransactionEncoder.encode(rawTransaction);
        String hash = Numeric.toHexString(Hash.sha3(encodedRawTransaction));
        List<String> hashList = new ArrayList<>();
        hashList.add(hash);
        CreateWeb3EthSignRequest.MessageHash messageHash = new CreateWeb3EthSignRequest.MessageHash();
        messageHash.setChainId(web3.ethChainId().send().getChainId().longValue());
        messageHash.setHash(hashList);

        // Sign with safeheron ethSign
        String customerRefId = UUID.randomUUID().toString();
        String txKey = requestWeb3EthSign(customerRefId, accountKey, messageHash);
        System.out.println(String.format("transaction created, txKey: %s", txKey));

        // Get sig
        String sig = retrieveSig(customerRefId);
        System.out.println(String.format("got ethSign result, sig: %s", sig));

        // Encode with sig and send transaction
        encodeWithSigAndSendTransaction(rawTransaction, sig);
    }

    private RawTransaction createRawTransaction(String fromAddress, String value, String contractAddress, Function contractFunction) throws Exception {
        // Get data from block chain: nonce, chainId, gasLimit and latestBlock's baseFeePerGas
        EthGetTransactionCount ethGetTransactionCount = web3
                .ethGetTransactionCount(fromAddress, DefaultBlockParameterName.LATEST).send();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        EthChainId ethChainId = web3.ethChainId().send();

        String data = contractFunction == null ? "" : FunctionEncoder.encode(contractFunction);

        System.out.println("function data: " + data);
        // Estimate gas
        Transaction transaction = Transaction.createEthCallTransaction(fromAddress, contractAddress, data);
        EthEstimateGas gasLimit = web3.ethEstimateGas(transaction).send();
        if (gasLimit.hasError()) {
            throw new Exception(String.format("error estimate gas:%s-%s", gasLimit.getError().getCode(), gasLimit.getError().getMessage()));
        }
        // Estimate maxFeePerGas, we assume maxPriorityFeePerGas's value is 2(gwei)
        BigInteger maxPriorityFeePerGas = Convert.toWei("2", Convert.Unit.GWEI).toBigInteger();
        EthBlock.Block latestBlock = web3.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send().getBlock();

        // The baseFeePerGas is recommended to be 2 times the latest block's baseFeePerGas value
        // maxFeePerGas must not less than baseFeePerGas + maxPriorityFeePerGas
        BigDecimal maxFeePerGas = new BigDecimal(latestBlock.getBaseFeePerGas())
                .multiply(new BigDecimal("2"))
                .add(new BigDecimal(maxPriorityFeePerGas));

        // Create raw transaction
        RawTransaction rawTransaction = RawTransaction.createTransaction(
                ethChainId.getChainId().longValue(),
                nonce,
                gasLimit.getAmountUsed(),
                contractAddress,
                Convert.toWei(value, Convert.Unit.ETHER).toBigInteger(),
                data,
                maxPriorityFeePerGas,
                maxFeePerGas.toBigInteger());

        return rawTransaction;
    }

    private Function createERC20Function(String contractAddress, String tokenAmount, String toAddress) throws IOException {
        BigInteger decimals = getERC20Decimals(contractAddress);
        BigDecimal tokenValue = new BigDecimal(tokenAmount).multiply(new BigDecimal(Math.pow(10, decimals.longValue())));
        Function function = new Function(
                "transfer",
                Arrays.asList(new Address(toAddress), new Uint256(tokenValue.toBigInteger())),
                Arrays.asList(new TypeReference<Type>() {
                }));

        return function;
    }

    private String requestWeb3EthSign(String customerRefId, String accountKey, CreateWeb3EthSignRequest.MessageHash messageHash) {
        CreateWeb3EthSignRequest request = new CreateWeb3EthSignRequest();
        request.setCustomerRefId(customerRefId);
        request.setAccountKey(accountKey);
        request.setMessageHash(messageHash);
        TxKeyResult response = ServiceExecutor.execute(web3ApiService.createWeb3EthSign(request));
        return response.getTxKey();
    }


    private String retrieveSig(String customerRefId) throws Exception {
        OneWeb3SignRequest request = new OneWeb3SignRequest();
        request.setCustomerRefId(customerRefId);

        for (int i = 0; i < 100; i++) {
            Web3SignResponse response = ServiceExecutor.execute(web3ApiService.oneWeb3Sign(request));
            System.out.println(String.format("ethSign status: %s, sub status: %s", response.getTransactionStatus(), response.getTransactionSubStatus()));

            if ("FAILED".equalsIgnoreCase(response.getTransactionStatus()) || "REJECTED".equalsIgnoreCase(response.getTransactionStatus())) {
                System.out.println("ethSign transaction was FAILED or REJECTED");
                System.exit(1);
            }

            if ("SIGN_COMPLETED".equalsIgnoreCase(response.getTransactionStatus())) {
                return response.getMessageHash().getSigList().get(0).getSig();
            }

            System.out.println("wait 5000ms");
            Thread.sleep(5000);
        }

        throw new Exception("can't get sig.");
    }

    private void encodeWithSigAndSendTransaction(RawTransaction rawTransaction, String sig) throws IOException {
        // Encode transaction with signature
        byte[] signedMessage = TransactionEncoder.encode(rawTransaction, convertSig(sig));
        String hexValue = Numeric.toHexString(signedMessage);
        System.out.println("signed transaction: " + hexValue);

        // Send transaction
        EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue).send();
        if (ethSendTransaction.getError() != null) {
            System.out.println("send transaction error, code: " + ethSendTransaction.getError().getCode()
                    + ", message: " + ethSendTransaction.getError().getMessage());
            return;
        }

        String transactionHash = ethSendTransaction.getTransactionHash();
        System.out.println(String.format("Transaction successful with hash: %s", transactionHash));

    }

    private Sign.SignatureData convertSig(String sig) {
        // Split sig into R, S, V
        String sigR = sig.substring(0, 64);
        String sigS = sig.substring(64, 128);
        String sigV = sig.substring(128);

        Integer v = Integer.parseInt(sigV, 16) + 27;

        // Create and return the Sign.SignatureData Object
        return new Sign.SignatureData(v.byteValue(),
                Numeric.hexStringToByteArray(sigR),
                Numeric.hexStringToByteArray(sigS));
    }

    private BigInteger getERC20Decimals(String contractAddress) throws IOException {
        final Function function = new Function("decimals",
                Arrays.asList(),
                Arrays.asList(new TypeReference<Uint256>() {
                }));

        String callResult = web3.ethCall(
                        Transaction.createEthCallTransaction(READ_ONLY_FROM_ADDRESS, contractAddress, FunctionEncoder.encode(function)),
                        DefaultBlockParameterName.LATEST)
                .send()
                .getValue();

        List<Type> result = FunctionReturnDecoder.decode(callResult, function.getOutputParameters());
        return (BigInteger) result.get(0).getValue();
    }

}
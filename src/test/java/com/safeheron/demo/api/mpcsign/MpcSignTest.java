package com.safeheron.demo.api.mpcsign;

import com.safeheron.client.config.SafeheronConfig;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author safeheron
 * @date 2022/7/12 12:12
 */
public class MpcSignTest {

    static Web3j web3;
    private static final String READ_ONLY_FROM_ADDRESS = "0x0000000000000000000000000000000000000000";

    static MpcSignApi mpcSignApi;
    static Map<String, String> config;

    @BeforeClass
    public static void beforeClass() throws FileNotFoundException {

        Yaml yaml = new Yaml();
        File file = new File("src/test/resources/demo/api/mpcsign/config.yaml");
        InputStream inputStream = new FileInputStream(file);
        config = yaml.load(inputStream);

        mpcSignApi = ServiceCreator.create(MpcSignApi.class, SafeheronConfig.builder()
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
        Function function = createERC20Function(contractAddress,"1", toAddress);

        // Create transaction object
        RawTransaction rawTransaction = createRawTransaction(fromAddress,"0", contractAddress, function);

        // Encode the transaction and compute the hash value
        byte[] encodedRawTransaction = TransactionEncoder.encode(rawTransaction);
        String hash = Numeric.toHexString(Hash.sha3(encodedRawTransaction));

        // Sign with safeheron mpc
        String customerRefId = UUID.randomUUID().toString();
        String txKey = requestMpcSig(customerRefId, accountKey, hash.substring(2));
        System.out.println(String.format("transaction created, txKey: %s", txKey));

        // Get sig
        String mpcSig = retrieveSig(customerRefId);
        System.out.println(String.format("got mpc sign result, sig: %s", mpcSig));

        // Encode with sig and send transaction
        encodeWithSigAndSendTransaction(rawTransaction, mpcSig);
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
        if(gasLimit.hasError()){
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

    private String requestMpcSig(String customerRefId, String accountKey, String hash){
        CreateMpcSignRequest request = new CreateMpcSignRequest();
        request.setCustomerRefId(customerRefId);
        request.setSourceAccountKey(accountKey);
        request.setSignAlg("Secp256k1");

        CreateMpcSignRequest.Entry hashItem = new CreateMpcSignRequest.Entry();
        hashItem.setData(hash);
        request.setDataList(Arrays.asList(hashItem));

        CreateMpcSignResponse response = ServiceExecutor.execute(mpcSignApi.createMpcSign(request));

        return response.getTxKey();
    }

    private String retrieveSig(String customerRefId) throws Exception {
        RetrieveMpcSignRequest request = new RetrieveMpcSignRequest();
        request.setCustomerRefId(customerRefId);

        for(int i = 0; i < 100 ; i++){
            RetrieveMpcSignResponse response = ServiceExecutor.execute(mpcSignApi.retrieveSig(request));
            System.out.println(String.format("mpc sign transaction status: %s, sub status: %s", response.getTransactionStatus(), response.getTransactionSubStatus()));

            if ("FAILED".equalsIgnoreCase(response.getTransactionStatus()) || "REJECTED".equalsIgnoreCase(response.getTransactionStatus())){
                System.out.println("mpc sign transaction was FAILED or REJECTED");
                System.exit(1);
            }

            if ("COMPLETED".equalsIgnoreCase(response.getTransactionStatus()) && "CONFIRMED".equalsIgnoreCase(response.getTransactionSubStatus())){
                return response.getHashs().get(0).getSig();
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
        if(ethSendTransaction.getError() != null){
            System.out.println("send transaction error, code: " + ethSendTransaction.getError().getCode()
                    + ", message: " + ethSendTransaction.getError().getMessage());
            return;
        }

        String transactionHash = ethSendTransaction.getTransactionHash();
        System.out.println(String.format("Transaction successful with hash: %s", transactionHash));

    }

    private Sign.SignatureData convertSig(String sig){
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
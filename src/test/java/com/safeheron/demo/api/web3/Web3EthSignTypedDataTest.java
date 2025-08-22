package com.safeheron.demo.api.web3;

import com.safeheron.client.api.Web3ApiService;
import com.safeheron.client.config.SafeheronConfig;
import com.safeheron.client.request.CreateWeb3EthSignTypedDataRequest;
import com.safeheron.client.request.OneWeb3SignRequest;
import com.safeheron.client.response.TxKeyResult;
import com.safeheron.client.response.Web3SignResponse;
import com.safeheron.client.utils.ServiceCreator;
import com.safeheron.client.utils.ServiceExecutor;
import okhttp3.OkHttpClient;
import org.junit.BeforeClass;
import org.junit.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

/**
 * @author safeheron
 */
public class Web3EthSignTypedDataTest {

    static Web3j web3;

    static Web3ApiService web3ApiService;
    static Map<String, Object> config;

    @BeforeClass
    public static void beforeClass() throws FileNotFoundException {

        Yaml yaml = new Yaml();
        File file = new File("src/test/resources/demo/api/web3/config.yaml");
        InputStream inputStream = new FileInputStream(file);
        config = yaml.load(inputStream);

        web3ApiService = ServiceCreator.create(Web3ApiService.class, SafeheronConfig.builder()
                .baseUrl(config.get("baseUrl").toString())
                .apiKey(config.get("apiKey").toString())
                .safeheronRsaPublicKey(config.get("safeheronPublicKey").toString())
                .rsaPrivateKey(config.get("privateKey").toString())
                .requestTimeout(Long.valueOf(config.get("requestTimeout").toString()))
                .build());

        web3 = Web3j.build(new HttpService(config.get("ethereumRpcApi").toString(),
                new OkHttpClient.Builder().build()));

    }

    @Test
    public void testWeb3ethSignTypedData() throws Exception {
        String accountKey = config.get("accountKey").toString();

        CreateWeb3EthSignTypedDataRequest.Message message = new CreateWeb3EthSignTypedDataRequest.Message();
        message.setChainId(web3.ethChainId().send().getChainId().longValue());
        message.setData("{\"types\":{\"EIP712Domain\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"version\",\"type\":\"string\"},{\"name\":\"chainId\",\"type\":\"uint256\"},{\"name\":\"verifyingContract\",\"type\":\"address\"}],\"Person\":[{\"name\":\"name\",\"type\":\"string\"},{\"name\":\"wallet\",\"type\":\"address\"}],\"Mail\":[{\"name\":\"from\",\"type\":\"Person\"},{\"name\":\"to\",\"type\":\"Person\"},{\"name\":\"contents\",\"type\":\"string\"}]},\"primaryType\":\"Mail\",\"domain\":{\"name\":\"Ether Mail\",\"version\":\"1\",\"chainId\":1,\"verifyingContract\":\"0xCcCCccccCCCCcCCCCCCcCcCccCcCCCcCcccccccC\"},\"message\":{\"from\":{\"name\":\"Cow\",\"wallet\":\"0xCD2a3d9F938E13CD947Ec05AbC7FE734Df8DD826\"},\"to\":{\"name\":\"Bob\",\"wallet\":\"0xbBbBBBBbbBBBbbbBbbBbbbbBBbBbbbbBbBbbBBbB\"},\"contents\":\"Hello, Bob!\"}}");
        message.setVersion("ETH_SIGNTYPEDDATA_V4");

        // Sign with safeheron ethSignTypedData
        String customerRefId = UUID.randomUUID().toString();
        String txKey = requestWeb3ethSignTypedData(customerRefId, accountKey, message);
        System.out.println(String.format("transaction created, txKey: %s", txKey));

        // Get sig
        String sig = retrieveSig(customerRefId);
        System.out.println(String.format("got ethSignTypedData result, sig: %s", sig));
    }

    private String requestWeb3ethSignTypedData(String customerRefId, String accountKey, CreateWeb3EthSignTypedDataRequest.Message message) {
        CreateWeb3EthSignTypedDataRequest request = new CreateWeb3EthSignTypedDataRequest();
        request.setCustomerRefId(customerRefId);
        request.setAccountKey(accountKey);
        request.setMessage(message);
        TxKeyResult response = ServiceExecutor.execute(web3ApiService.createWeb3EthSignTypedData(request));
        return response.getTxKey();
    }


    private String retrieveSig(String customerRefId) throws Exception {
        OneWeb3SignRequest request = new OneWeb3SignRequest();
        request.setCustomerRefId(customerRefId);

        for (int i = 0; i < 100; i++) {
            Web3SignResponse response = ServiceExecutor.execute(web3ApiService.oneWeb3Sign(request));
            System.out.println(String.format("ethSignTypedData status: %s, sub status: %s", response.getTransactionStatus(), response.getTransactionSubStatus()));

            if ("FAILED".equalsIgnoreCase(response.getTransactionStatus()) || "REJECTED".equalsIgnoreCase(response.getTransactionStatus())) {
                System.out.println("ethSignTypedData transaction was FAILED or REJECTED");
                System.exit(1);
            }

            if ("SIGN_COMPLETED".equalsIgnoreCase(response.getTransactionStatus())) {
                return response.getMessage().getSig().getSig();
            }

            System.out.println("wait 5000ms");
            Thread.sleep(5000);
        }

        throw new Exception("can't get sig.");
    }
}
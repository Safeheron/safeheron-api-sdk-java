package com.safeheron.demo.api.web3;

import com.safeheron.client.api.Web3ApiService;
import com.safeheron.client.config.SafeheronConfig;
import com.safeheron.client.request.CreateWeb3PersonalSignRequest;
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

import java.io.*;
import java.util.*;

/**
 * @author safeheron
 * @date 2023/8/23
 */
public class Web3PersonalSignTest {

    static Web3j web3;

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

        CreateWeb3PersonalSignRequest.Message message = new CreateWeb3PersonalSignRequest.Message();
        message.setChainId(web3.ethChainId().send().getChainId().longValue());
        message.setData("demo text");

        // Sign with safeheron personalSign
        String customerRefId = UUID.randomUUID().toString();
        String txKey = requestWeb3PersonalSign(customerRefId, accountKey, message);
        System.out.println(String.format("transaction created, txKey: %s", txKey));

        // Get sig
        String sig = retrieveSig(customerRefId);
        System.out.println(String.format("got personalSign result, sig: %s", sig));
    }

    private String requestWeb3PersonalSign(String customerRefId, String accountKey, CreateWeb3PersonalSignRequest.Message message) {
        CreateWeb3PersonalSignRequest request = new CreateWeb3PersonalSignRequest();
        request.setCustomerRefId(customerRefId);
        request.setAccountKey(accountKey);
        request.setMessage(message);
        TxKeyResult response = ServiceExecutor.execute(web3ApiService.createWeb3PersonalSign(request));
        return response.getTxKey();
    }


    private String retrieveSig(String customerRefId) throws Exception {
        OneWeb3SignRequest request = new OneWeb3SignRequest();
        request.setCustomerRefId(customerRefId);

        for (int i = 0; i < 100; i++) {
            Web3SignResponse response = ServiceExecutor.execute(web3ApiService.oneWeb3Sign(request));
            System.out.println(String.format("personalSign status: %s, sub status: %s", response.getTransactionStatus(), response.getTransactionSubStatus()));

            if ("FAILED".equalsIgnoreCase(response.getTransactionStatus()) || "REJECTED".equalsIgnoreCase(response.getTransactionStatus())) {
                System.out.println("personalSign transaction was FAILED or REJECTED");
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
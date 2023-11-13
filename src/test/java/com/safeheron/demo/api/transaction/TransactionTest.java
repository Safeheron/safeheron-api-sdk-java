package com.safeheron.demo.api.transaction;

import com.safeheron.client.api.TransactionApiService;
import com.safeheron.client.config.SafeheronConfig;
import com.safeheron.client.request.CreateTransactionRequest;
import com.safeheron.client.response.TxKeyResult;
import com.safeheron.client.utils.ServiceCreator;
import com.safeheron.client.utils.ServiceExecutor;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;


/**
 * @author xigaoku
 * @date 2022/10/12 19:27
 */
@Slf4j
public class TransactionTest {

    static TransactionApiService transactionApi;

    static Map<String, String> config;

    @BeforeClass
    public static void beforeClass() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        File file = new File("src/test/resources/demo/api/transaction/config.yaml");
        InputStream inputStream = new FileInputStream(file);
        config = yaml.load(inputStream);

        transactionApi = ServiceCreator.create(TransactionApiService.class, SafeheronConfig.builder()
                .baseUrl(config.get("baseUrl"))
                .apiKey(config.get("apiKey"))
                .safeheronRsaPublicKey(config.get("safeheronPublicKey"))
                .rsaPrivateKey(config.get("privateKey"))
                .build());
    }

    @Test
    public void testSendTransaction(){
        CreateTransactionRequest createTransactionRequest = new com.safeheron.client.request.CreateTransactionRequest();
        createTransactionRequest.setSourceAccountKey(config.get("accountKey"));
        createTransactionRequest.setSourceAccountType("VAULT_ACCOUNT");
        createTransactionRequest.setDestinationAccountType("ONE_TIME_ADDRESS");
        createTransactionRequest.setDestinationAddress(config.get("destinationAddress"));
        createTransactionRequest.setCoinKey("ETH_GOERLI");
        createTransactionRequest.setTxAmount("0.001");
        createTransactionRequest.setTxFeeLevel("MIDDLE");
        createTransactionRequest.setCustomerRefId(UUID.randomUUID().toString());
        TxKeyResult createTransactionResponse = ServiceExecutor.execute(transactionApi.createTransactions(createTransactionRequest));
        System.out.println(String.format("transaction has been created, txKey: %s", createTransactionResponse.getTxKey()));
    }

}

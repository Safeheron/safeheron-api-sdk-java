package com.safeheron.demo.api.coin;

import com.safeheron.client.api.GasApiService;
import com.safeheron.client.config.SafeheronConfig;
import com.safeheron.client.request.GasTransactionsGetByTxKeyRequest;
import com.safeheron.client.response.GasTransactionsGetByTxKeyResponse;
import com.safeheron.client.utils.ServiceCreator;
import com.safeheron.client.utils.ServiceExecutor;
import org.junit.BeforeClass;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class CoinTest {


    static GasApiService gasApiService;

    static Map<String, Object> config;

//    @BeforeClass
//    public static void beforeClass() throws FileNotFoundException {
//        Yaml yaml = new Yaml();
//        File file = new File("src/test/resources/demo/api/coin/config.yaml");
//        InputStream inputStream = new FileInputStream(file);
//        config = yaml.load(inputStream);
//
//        coinApiService = ServiceCreator.create(CoinApiService.class, SafeheronConfig.builder()
//                .baseUrl(config.get("baseUrl").toString())
//                .apiKey(config.get("apiKey").toString())
//                .safeheronRsaPublicKey(config.get("safeheronPublicKey").toString())
//                .rsaPrivateKey(config.get("privateKey").toString())
//                .requestTimeout(Long.valueOf(config.get("requestTimeout").toString()))
//                .build());
//    }

    @BeforeClass
    public static void beforeClass() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        File file = new File("src/test/resources/demo/api/coin/config.yaml");
        InputStream inputStream = new FileInputStream(file);
        config = yaml.load(inputStream);

        gasApiService = ServiceCreator.create(GasApiService.class, SafeheronConfig.builder()
                .baseUrl(config.get("baseUrl").toString())
                .apiKey(config.get("apiKey").toString())
                .safeheronRsaPublicKey(config.get("safeheronPublicKey").toString())
                .rsaPrivateKey(config.get("privateKey").toString())
                .requestTimeout(Long.valueOf(config.get("requestTimeout").toString()))
                .build());
    }

    @Test
    public void testCreateAccountAndAddCoin(){
//        GasStatusResponse execute = ServiceExecutor.execute(gasApiService.gasStatus());
//        System.out.println(execute);
        GasTransactionsGetByTxKeyRequest gasTransactionsGetByTxKeyRequest = new GasTransactionsGetByTxKeyRequest();
        gasTransactionsGetByTxKeyRequest.setTxKey("xx");
        GasTransactionsGetByTxKeyResponse execute1 = ServiceExecutor.execute(gasApiService.gasTransactionsGetByTxKey(gasTransactionsGetByTxKeyRequest));
        System.out.println(execute1);
    }


}

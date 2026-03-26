package com.safeheron.demo.api.transaction;

import com.safeheron.client.KeyProvider;
import com.safeheron.client.api.TransactionApiService;
import com.safeheron.client.config.RSATypeEnum;
import com.safeheron.client.config.SafeheronConfig;
import com.safeheron.client.request.CreateTransactionRequest;
import com.safeheron.client.response.TxKeyResult;
import com.safeheron.client.utils.RsaUtil;
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
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;


/**
 * @author xigaoku
 */
@Slf4j
public class TransactionTest {

    static TransactionApiService transactionApi;

    static Map<String, Object> config;

    @BeforeClass
    public static void beforeClass() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        File file = new File("src/test/resources/demo/api/transaction/config.yaml");
        InputStream inputStream = new FileInputStream(file);
        config = yaml.load(inputStream);

        transactionApi = ServiceCreator.create(TransactionApiService.class, SafeheronConfig.builder()
                .baseUrl(config.get("baseUrl").toString())
                .apiKey(config.get("apiKey").toString())
                .safeheronRsaPublicKey(config.get("safeheronPublicKey").toString())
                .rsaPrivateKey(config.get("privateKey").toString())
                .requestTimeout(Long.valueOf(config.get("requestTimeout").toString()))
                .build());
    }

    @Test
    public void testSendTransaction(){
        CreateTransactionRequest createTransactionRequest = new com.safeheron.client.request.CreateTransactionRequest();
        createTransactionRequest.setSourceAccountKey(config.get("accountKey").toString());
        createTransactionRequest.setSourceAccountType("VAULT_ACCOUNT");
        createTransactionRequest.setDestinationAccountType("ONE_TIME_ADDRESS");
        createTransactionRequest.setDestinationAddress(config.get("destinationAddress").toString());
        createTransactionRequest.setCoinKey("ETH_GOERLI");
        createTransactionRequest.setTxAmount("0.001");
        createTransactionRequest.setTxFeeLevel("MIDDLE");
        createTransactionRequest.setCustomerRefId(UUID.randomUUID().toString());
        TxKeyResult createTransactionResponse = ServiceExecutor.execute(transactionApi.createTransactions(createTransactionRequest));
        System.out.println(String.format("transaction has been created, txKey: %s", createTransactionResponse.getTxKey()));
    }

    /**
     * Test case for sending a transaction using a custom KeyProvider.
     *
     * <p>This test demonstrates how to integrate a custom {@link KeyProvider}
     * to perform RSA signing through an external system (e.g. Self-Managed Key Management).
     *
     * <p>In this scenario, we demonstrate that the "privateKey" field in config.yaml
     * can actually be a "keyId" or "keyName" used by your KMS, rather than the actual private key.</p>
     */
    @Test
    public void testSendTransactionWithKeyProvider() {
        // 1. Assume we got a keyId from our configuration (e.g., config.yaml)
        // Although the key in YAML is named 'privateKey', its content could be a 'keyId'
        String keyIdFromConfig = config.get("privateKey").toString();

        /**
         * 2. Define a Self-Managed KeyProvider.
         * In a real project, this class would be part of your application code.
         */
        class MySelfManagedKeyProvider implements KeyProvider {
            private final String keyId;

            public MySelfManagedKeyProvider(String keyId) {
                this.keyId = keyId;
            }

            @Override
            public String sign(String content) {
                // In a real scenario, you would call your signing service:
                // return mySigningService.sign(content, this.keyId);

                // For this test to pass, we simulate the signing service by signing locally
                // using the keyId (which we know is actually the private key in this demo)
                try {
                    PrivateKey priKey = getPrivateKey("RSA", this.keyId);
                    Signature privateSignature = Signature.getInstance("SHA256WithRSA");
                    privateSignature.initSign(priKey);
                    privateSignature.update(content.getBytes(StandardCharsets.UTF_8));
                    byte[] signature = privateSignature.sign();
                    return Base64.getEncoder().encodeToString(signature);
                } catch (Exception e) {
                    throw new RuntimeException("Signing failed", e);
                }
            }

            @Override
            public String signPSS(String content) {
                return "";
            }

            @Override
            public byte[] decrypt(String content, RSATypeEnum rsaType) {
                return new byte[0];
            }

            private PrivateKey getPrivateKey(String algorithm, String privateKey) throws Exception {
                KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
                byte[] privateKeyData = Base64.getDecoder().decode(privateKey.getBytes(StandardCharsets.UTF_8));
                return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyData));
            }
        }

        // 3. Instantiate your provider with the keyId
        KeyProvider mySelfManagedProvider = new MySelfManagedKeyProvider(keyIdFromConfig);

        /**
         * 4. Build TransactionApiService with the custom KeyProvider.
         * Notice we don't call .rsaPrivateKey() here, as the provider handles everything.
         */
        TransactionApiService transactionApiWithKeyProvider = ServiceCreator.create(TransactionApiService.class, SafeheronConfig.builder()
                .baseUrl(config.get("baseUrl").toString())
                .apiKey(config.get("apiKey").toString())
                .safeheronRsaPublicKey(config.get("safeheronPublicKey").toString())
                .keyProvider(mySelfManagedProvider)
                .requestTimeout(Long.valueOf(config.get("requestTimeout").toString()))
                .build());

        /**
         * 5. Execute transaction creation as usual.
         */
        CreateTransactionRequest createTransactionRequest = new CreateTransactionRequest();
        createTransactionRequest.setSourceAccountKey(config.get("accountKey").toString());
        createTransactionRequest.setSourceAccountType("VAULT_ACCOUNT");
        createTransactionRequest.setDestinationAccountType("ONE_TIME_ADDRESS");
        createTransactionRequest.setDestinationAddress(config.get("destinationAddress").toString());
        createTransactionRequest.setCoinKey("USDT_METACOMP_ERC20_ETHEREUM_SEPOLIA");
        createTransactionRequest.setTxAmount("0.001");
        createTransactionRequest.setTxFeeLevel("MIDDLE");
        createTransactionRequest.setCustomerRefId(UUID.randomUUID().toString());
        TxKeyResult createTransactionResponse = ServiceExecutor.execute(transactionApiWithKeyProvider.createTransactions(createTransactionRequest));
        System.out.println(String.format("transaction has been created, txKey: %s", createTransactionResponse.getTxKey()));
    }

}

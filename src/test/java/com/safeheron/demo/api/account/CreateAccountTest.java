package com.safeheron.demo.api.account;

import com.safeheron.client.api.AccountApiService;
import com.safeheron.client.config.SafeheronConfig;
import com.safeheron.client.request.CreateAccountCoinRequest;
import com.safeheron.client.request.CreateAccountRequest;
import com.safeheron.client.request.ListAccountRequest;
import com.safeheron.client.response.AccountResponse;
import com.safeheron.client.response.CreateAccountCoinResponse;
import com.safeheron.client.response.CreateAccountResponse;
import com.safeheron.client.response.PageResult;
import com.safeheron.client.utils.ServiceCreator;
import com.safeheron.client.utils.ServiceExecutor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;


/**
 * @author xigaoku
 * @date 2022/10/12 19:27
 */
@Slf4j
public class CreateAccountTest {

    static AccountApiService accountApi;

    static Map<String, Object> config;

    @BeforeClass
    public static void beforeClass() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        File file = new File("src/test/resources/demo/api/account/config.yaml");
        InputStream inputStream = new FileInputStream(file);
        config = yaml.load(inputStream);

        accountApi = ServiceCreator.create(AccountApiService.class, SafeheronConfig.builder()
                .baseUrl(config.get("baseUrl").toString())
                .apiKey(config.get("apiKey").toString())
                .safeheronRsaPublicKey(config.get("safeheronPublicKey").toString())
                .rsaPrivateKey(config.get("privateKey").toString())
                .requestTimeout(Long.valueOf(config.get("requestTimeout").toString()))
                .build());
    }

    @Test
    public void testCreateAccountAndAddCoin(){
        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setAccountName("first-wallet-account4");
        createAccountRequest.setHiddenOnUI(false);
        CreateAccountResponse createAccountResponse = ServiceExecutor.execute(accountApi.createAccount(createAccountRequest));
        System.out.println(String.format("wallet account created, account key: %s", createAccountResponse.getAccountKey()));

        CreateAccountCoinRequest addCoinRequest = new CreateAccountCoinRequest();
        addCoinRequest.setAccountKey(createAccountResponse.getAccountKey());
        addCoinRequest.setCoinKey("ETH_GOERLI");
        List<CreateAccountCoinResponse> addCoinResponseList = ServiceExecutor.execute(accountApi.createAccountCoin(addCoinRequest));

        System.out.println(String.format("Token[ETH_GOERLI] address: %s", addCoinResponseList.get(0).getAddress()));


    }

    @Test
    public void testListAccount(){
        ListAccountRequest request = new ListAccountRequest();
        request.setPageSize(10L);
        request.setPageNumber(1L);
        PageResult<AccountResponse> response = ServiceExecutor.execute(accountApi.listAccounts(request));
        Assert.assertNotNull(response);
    }
}

package com.safeheron.demo.api.account;

import com.safeheron.client.config.SafeheronConfig;
import com.safeheron.client.request.CreateAccountRequest;
import com.safeheron.client.request.ListAccountRequest;
import com.safeheron.client.response.AccountResponse;
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
import java.util.*;


/**
 * @author xigaoku
 * @date 2022/10/12 19:27
 */
@Slf4j
public class CreateAccountTest {

    static AccountApi accountApi;

    static Map<String, String> config;

    @BeforeClass
    public static void beforeClass() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        File file = new File("src/test/resources/demo/api/account/config.yaml");
        InputStream inputStream = new FileInputStream(file);
        config = yaml.load(inputStream);

        accountApi = ServiceCreator.create(AccountApi.class, SafeheronConfig.builder()
                .baseUrl(config.get("baseUrl"))
                .apiKey(config.get("apiKey"))
                .safeheronRsaPublicKey(config.get("safeheronPublicKey"))
                .rsaPrivateKey(config.get("privateKey"))
                .build());
    }

    @Test
    public void testCreateAccountAndAddCoin(){
        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setAccountName("first-wallet-account4");
        createAccountRequest.setHiddenOnUI(false);
        CreateAccountResponse createAccountResponse = ServiceExecutor.execute(accountApi.createAccount(createAccountRequest));
        System.out.println(String.format("wallet account created, account key: %s", createAccountResponse.getAccountKey()));

        AddCoinRequest addCoinRequest = new AddCoinRequest();
        addCoinRequest.setAccountKey(createAccountResponse.getAccountKey());
        addCoinRequest.setCoinKey("ETH_GOERLI");
        List<AddCoinResponse> addCoinResponseList = ServiceExecutor.execute(accountApi.addCoin(addCoinRequest));

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

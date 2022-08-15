package com.safeheron.client.api;

import com.safeheron.client.config.SafeheronConfig;
import com.safeheron.client.exception.SafeheronException;
import com.safeheron.client.request.CreateAccountRequest;
import com.safeheron.client.request.ListAccountRequest;
import com.safeheron.client.response.AccountResponse;
import com.safeheron.client.response.CreateAccountResponse;
import com.safeheron.client.response.PageResult;
import com.safeheron.client.utils.ServiceCreator;
import com.safeheron.client.utils.ServiceExecutor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;


@Slf4j
public class AccountApiServiceTest {

    static AccountApiService apiService;

    @BeforeClass
    public static void beforeClass(){
        SafeheronConfig config = SafeheronConfig.builder()
                .baseUrl("https://api.91aql.com")
                .apiKey("d1ad6a9*******1ba572e7")
                .safeheronRsaPublicKey("MIICIjANBgkqhkiG********+yvFR3Eh7OjkCAwEAAQ==")
                .rsaPrivateKey("MIIJQwIBADANBgkqhk**********8Hgkhc7T5yQ2Texc+3t6qNfhT6qvSw41k=")
                .build();
        apiService = ServiceCreator.create(AccountApiService.class, config);
    }

    @Test
    public void testCreateAccount(){
        CreateAccountRequest request = new CreateAccountRequest();
        request.setHiddenOnUI(true);
        Call<CreateAccountResponse> call = apiService.createAccount(request);
        CreateAccountResponse response = ServiceExecutor.execute(call);
        Assert.assertNotNull(response.getAccountKey());

    }

    @Test
    public void testListAccount(){
        ListAccountRequest request = new ListAccountRequest();
        request.setPageSize(10L);
        request.setPageNumber(1L);
        Call<PageResult<AccountResponse>> call = apiService.listAccounts(request);
        PageResult<AccountResponse> response = ServiceExecutor.execute(call);
        Assert.assertNotNull(response);
    }

    @Test(expected = SafeheronException.class)
    public void testMockListAccount() throws IOException {
        ListAccountRequest request = new ListAccountRequest();
        request.setPageSize(10L);
        request.setPageNumber(1L);
        Call<PageResult<AccountResponse>> call = Mockito.mock(Call.class);
        Response<PageResult<AccountResponse>> mockResponse = Response.error(404,
                ResponseBody.create("not found", MediaType.parse("application/json")));
        Mockito.when(call.execute()).thenReturn(mockResponse);
        ServiceExecutor.execute(call);
    }
}

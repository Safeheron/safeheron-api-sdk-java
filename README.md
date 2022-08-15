# Java SDK for Safeheron API

[![Maven](https://img.shields.io/badge/maven-3.8.4-blue)](http://www.safeheron.com)
![GitHub last commit](https://img.shields.io/github/last-commit/Safeheron/safeheron-api-sdk-java)
![GitHub top language](https://img.shields.io/github/languages/top/Safeheron/safeheron-api-sdk-java?color=red)

# API Documentation
- [Official documentation](https://docs.safeheron.com/api/index.html)

# Usage
## Download and build
```shell
$ git clone https://github.com/Safeheron/safeheron-api-sdk-java.git
$ cd safeheron-api-sdk-java
$ mvn install -Dmaven.test.skip=true
```
  
## Develop
> Take `/v1/account/create` as an example to explain
* Define request parameter data object
    ```java
    @Data
    public class CreateAccountRequest {
        private String accountName;
        private Boolean autoFuel;
        private Boolean hiddenOnUI;
    }
    ```
* Define response data object
    ```java
    @Data
    public class CreateAccountResponse {
        private String accountKey;
        private List<PubKey> pubKeys;
        @Data
        @NoArgsConstructor
        static class PubKey{
            private String signAlg;
            private String pubKey;
        }
    }
    ```
* Define the interface to use
    ```java
    public interface AccountApiService {
        @POST("/v1/account/create")
        Call<CreateAccountResponse> createAccount(@Body CreateAccountRequest createAccountRequest);
  
        // Other interfaces
        ...
        ...
    }
    ```

* Call `createAccount` api which defined above
    ```java
    SafeheronConfig config = SafeheronConfig.builder()
            .baseUrl("https://api.safeheron.vip")
            .apiKey("d1ad6******72e7")
            .safeheronRsaPublicKey("MIICIjANBgkqhk******h7OjkCAwEAAQ==")
            .rsaPrivateKey("MIIJQwIBADANBgkqhki********NUt6qNfhT6qvSw41k=")
            .build();
    AccountApiService apiService = ServiceCreator.create(AccountApiService.class, config)
    CreateAccountRequest request = new CreateAccountRequest();
    request.setHiddenOnUI(true);
    Call<CreateAccountResponse> call = apiService.createAccount(request);
    CreateAccountResponse response = ServiceExecutor.execute(call);
    // Your code to process response
    ...
    ...
    ```

# MPC Demo

## How to run `EthTransactionTest`
* Replace with your private key and address
```java
// Replace with your private key
private static String privetKey = "6b493b4*****ad01d357";
// Replace with your address
private static String toAddress = "0x53B11df*****7321789";
```

* Execute
```
testTransaction
OR
testTransactionERC20
```

## How to run `TronTransactionTest`
* Replace with your private key and address
```java
// Replace with your private key
private static String privateKey = "b5b2581c3*******d05ee775";
// Replace with your address
private static String toAddress = "TDBrZQN******8Ld2FV";
```

* Download Tron wallet-cli lib，and add to your classpath
  * [Tron Wallet-cli](https://github.com/tronprotocol/wallet-cli)
  
* Execute
```
testTransaction
OR
testTRC20Transaction
```



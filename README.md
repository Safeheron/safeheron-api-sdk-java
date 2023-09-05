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
        public static class PubKey{
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
    // You can get `ApiKey` and `SafeheronRsaPublicKey` from Safeheron Web Console: https://www.safeheron.com/console.
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

# Test

## Test Create Wallet Account
* Before run the test code, modify `src/test/resources/demo/api/account/config.yaml.example` according to the comments
    ```yaml
    # Your api key, you can get it from Safeheron Web Console: https://www.safeheron.com/console.
    apiKey: 080d****e06e60
    # Your private key
    privateKey: MIIJRQIBA*******DtGRBdennqu8g95jcrMxCUhsifVgzP6vUyg==
    # Safeheron API public key, you can get it from Safeheron Web Console: https://www.safeheron.com/console.
    safeheronPublicKey: MIICI****QuTOTECAwEAAQ==
    # Safeheron API url
    baseUrl: https://api.safeheron.vip
    ```
* Copy config to `config.yaml` file.
    ```bash
    $ cd src/test/resources/demo/api/account
    $ cp config.yaml.example config.yaml
    ```
* JUnit 
  
  Execute `testCreateAccountAndAddCoin` unit in `/src/test/java/com/safeheron/demo/api/account/CreateAccountTest.java` Java file.

## Test Send A Transaction
* Before run the test code, modify `src/test/resources/demo/api/transaction/config.yaml.example` according to the comments
    ```yaml
    # Your api key, you can get it from Safeheron Web Console: https://www.safeheron.com/console.
    apiKey: 080d****e06e60
    # Your private key
    privateKey: MIIJRQIBA*******DtGRBdennqu8g95jcrMxCUhsifVgzP6vUyg==
    # Safeheron API public key, you can get it from Safeheron Web Console: https://www.safeheron.com/console.
    safeheronPublicKey: MIICI****QuTOTECAwEAAQ==
    # Safeheron API url
    baseUrl: https://api.safeheron.vip
    # Wallet Account key
    accountKey: account****5ecad40
    # To address
    destinationAddress: "0x943****0BF95f5"
    ```
* Copy config to `config.yaml` file.
    ```bash
    $ cd src/test/resources/demo/api/transaction
    $ cp config.yaml.example config.yaml
    ```
* JUnit

  Execute `testSendTransaction` unit in `/src/test/java/com/safeheron/demo/api/transaction/TransactionTest.java` Java file.


## Test MPC Sign
* Before run the test code, modify `src/test/resources/demo/api/mpcsign/config.yaml.example` according to the comments
    ```yaml
    # Your api key, you can get it from Safeheron Web Console: https://www.safeheron.com/console.
    apiKey: 080d****e06e60
    # Your private key
    privateKey: MIIJRQIBA*******DtGRBdennqu8g95jcrMxCUhsifVgzP6vUyg==
    # Safeheron API public key, you can get it from Safeheron Web Console: https://www.safeheron.com/console.
    safeheronPublicKey: MIICI****QuTOTECAwEAAQ==
    # Safeheron API url
    baseUrl: https://api.safeheron.vip
    # Wallet Account key
    accountKey: account****5ecad40
    # Goerli testnet token address in wallet account
    accountTokenAddress: "0x970****4ffD59"
    # erc20 token contract address
    erc20ContractAddress: "0x078****Eaa37F"
    # address to receive token
    toAddress: "0x53B****321789"
    # Ethereum RPC API
    ethereumRpcApi: https://goerli.infura.io/v3/802******bc2fcb
    ```
  
* Copy config to `config.yaml` file.
    ```bash
    $ cd src/test/resources/demo/api/mpcsign
    $ cp config.yaml.example config.yaml
    ```
* JUnit

  Execute `testSendToken` unit in `/src/test/java/com/safeheron/demo/api/mpcsign/MpcSignTest.java` Java file.

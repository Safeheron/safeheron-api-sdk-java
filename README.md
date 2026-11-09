# Java SDK for Safeheron API

[![Maven](https://img.shields.io/badge/maven-3.8.4-blue)](http://www.safeheron.com)
![GitHub last commit](https://img.shields.io/github/last-commit/Safeheron/safeheron-api-sdk-java)
![GitHub top language](https://img.shields.io/github/languages/top/Safeheron/safeheron-api-sdk-java?color=red)

# API Documentation
- [Official documentation](https://docs.safeheron.com/api/index.html)

# AI-Powered Development

Use the **Safeheron Skill** with Claude Code or Cursor to generate, debug, and troubleshoot Safeheron API integrations using natural language.

**Example prompts:**
- "Help me set up an API Co-Signer approval callback service"
- "Write a Webhook handler to process incoming transaction events"
- "What causes error 1012 and how do I fix it?"

**Get started:**

View the Skill source code on GitHub: [safeheron/safeheron-skill](https://github.com/safeheron/safeheron-skill)

# Usage

Add this dependency to your project's POM:

```xml

<dependency>
    <groupId>com.safeheron</groupId>
    <artifactId>api-sdk-java</artifactId>
    <version>1.0.16</version>
</dependency>
```

## Download and build
```shell
$ git clone https://github.com/Safeheron/safeheron-api-sdk-java.git
$ cd safeheron-api-sdk-java
$ mvn install -Dmaven.test.skip=true
```

## Security Notes
* **Replay protection for Webhook / Co-Signer callbacks**: `WebhookConverter` and `CoSignerConverter` can reject callbacks whose `timestamp` deviates from the local clock by more than a given tolerance. The check is disabled by default for backward compatibility; enable it with the constructor that accepts `timestampToleranceMillis`:
    ```java
    WebhookConverter webhookConverter = new WebhookConverter(safeheronWebHookRsaPublicKey, webHookRsaPrivateKey, 5 * 60 * 1000L);
    CoSignerConverter coSignerConverter = new CoSignerConverter(coSignerPubKey, approvalCallbackServicePrivateKey, 5 * 60 * 1000L);
    ```
  Keep your server clock synchronized (NTP). Regardless of this setting, always process callbacks idempotently (e.g. by `txKey`), since a callback may be delivered more than once.
* **Error responses are not signature-verified**: when the API returns a non-200 `code`, the SDK throws `SafeheronException` with the returned `code` / `message` without verifying the response signature; only successful responses go through signature verification and decryption. Treat the error message as informational and do not use it as the basis for security decisions.

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
    # RequestTimeout (Millisecond), Default: 20000
    requestTimeout: 20000
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
    # RequestTimeout (Millisecond), Default: 20000
    requestTimeout: 20000
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
    # RequestTimeout (Millisecond), Default: 20000
    requestTimeout: 20000
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

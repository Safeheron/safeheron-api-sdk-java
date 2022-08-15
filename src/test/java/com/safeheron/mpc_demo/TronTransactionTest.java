//package com.safeheron.mpc_demo;
//
//import com.google.protobuf.Any;
//import com.google.protobuf.ByteString;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Assert;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.tron.api.GrpcAPI;
//import org.tron.common.crypto.ECKey;
//import org.tron.common.crypto.Sha256Sm3Hash;
//import org.tron.common.utils.ByteArray;
//import org.tron.protos.Protocol;
//import org.tron.protos.contract.BalanceContract;
//import org.tron.protos.contract.SmartContractOuterClass;
//import org.tron.walletserver.GrpcClient;
//import org.tron.walletserver.WalletApi;
//import org.web3j.abi.FunctionEncoder;
//import org.web3j.abi.TypeReference;
//import org.web3j.abi.datatypes.Function;
//import org.web3j.abi.datatypes.Type;
//import org.web3j.abi.datatypes.generated.Uint256;
//
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.util.Arrays;
//import java.util.Collections;
//
///**
// * To run this ut, you need to add wallet-cli-version.jar to your classpath,
// * you can find it on github(https://github.com/tronprotocol/wallet-cli)
// *
// * @author safeheron
// * @date 2022/7/29 12:45
// */
//@Slf4j
//public class TronTransactionTest {
//
//    private static byte addressPreFixByte = 65;
//    // Tron Nile Testnet USDT_TRC20
//    static ByteString BS_CONTRACT = ByteString.copyFrom(WalletApi.decodeFromBase58Check("TXLAQ63Xg1NAzckPwKHvzw7CSEmLMEqcdj"));
//    static GrpcClient grpcClient;
//    static ECKey ecKey;
//
//    // Replace with your private key
//    private static String privateKey = "b5b2581c3*******d05ee775";
//    // Replace with your address
//    private static String toAddress = "TDBrZQN******8Ld2FV";
//
//    @BeforeClass
//    public static void beforeClass() {
//        WalletApi.setAddressPreFixByte(addressPreFixByte);
//        // Replace with your private key
//        byte[] privateKeyByte = ByteArray.fromHexString(privateKey);
//        ecKey = ECKey.fromPrivate(privateKeyByte);
//        grpcClient = WalletApi.init();
//    }
//
//    @Test
//    public void testTransaction() {
//        System.out.println(String.format("Attempting to send trx transaction from %s to %s",
//                WalletApi.encode58Check(ecKey.getAddress()), toAddress));
//
//        long currentTimeMillis = System.currentTimeMillis();
//        // Recommended to be 10 Hours
//        long expiration = currentTimeMillis + 1000 * 60 * 60 * 10;
//        // 10TRX
//        long amount = 10_000_000L;
//
//        Protocol.Block newestBlock = WalletApi.getBlock(-1);
//
//        // Create transaction object
//        Protocol.Transaction transaction = createTransaction(WalletApi.encode58Check(ecKey.getAddress()), toAddress,
//                amount, currentTimeMillis, expiration, newestBlock);
//
//        // Encode the transaction and compute the hash value
//        byte[] rawData = transaction.getRawData().toByteArray();
//        String hash  = ByteArray.toHexString(Sha256Sm3Hash.hash(rawData));
//        System.out.println("transaction hash: " + hash);
//
//        // Sign with private key
//        String signature = getLocalSignWithPrivateKey(hash, ecKey);
//
//        // Or sign with safeheron mpc
//        // String signature = getMpcSigFromSafeheron(hash)
//        System.out.println("signature: " + signature);
//
//        // Create transaction object again
//        // The purpose of doing this is simply to be consistent with the MPC and explain it better
//        Protocol.Transaction transaction2 = createTransaction(WalletApi.encode58Check(ecKey.getAddress()), toAddress,
//                amount, currentTimeMillis, expiration, newestBlock);
//        byte[] sign = ByteArray.fromHexString(signature);
//
//        // Encode with signature
//        Protocol.Transaction signedTransaction = transaction2.toBuilder().addSignature(ByteString.copyFrom(sign)).build();
//
//        // Send transaction
//        boolean result = WalletApi.broadcastTransaction(signedTransaction);
//        System.out.println("send result:" + result);
//        Assert.assertTrue(result);
//    }
//
//    @Test
//    public void testTRC20Transaction() {
//        System.out.println(String.format("Attempting to send trc20 transaction from %s to %s",
//                WalletApi.encode58Check(ecKey.getAddress()), toAddress));
//
//        long currentTimeMillis = System.currentTimeMillis();
//        // Recommended to be 10 Hours
//        long expiration = currentTimeMillis + 1000 * 60 * 60 * 10;
//        // token amount, 10Tokens
//        Integer decimals = getTokenDecimals(BS_CONTRACT);
//        long amount = new BigDecimal("10").multiply(new BigDecimal(Math.pow(10, decimals))).longValue();
//        // Recommended to be 40TRX
//        long feeLimit = 40_000_000L;
//
//        Protocol.Block newestBlock = WalletApi.getBlock(-1);
//
//        // Create transaction object
//        Protocol.Transaction transaction = createTRC20Transaction(WalletApi.encode58Check(ecKey.getAddress()), toAddress,
//                amount, currentTimeMillis, expiration, feeLimit, newestBlock);
//
//        // Encode the transaction and compute the hash value
//        byte[] rawData = transaction.getRawData().toByteArray();
//        String hash  = ByteArray.toHexString(Sha256Sm3Hash.hash(rawData));
//        System.out.println("transaction hash: " + hash);
//
//        // Sign with private key
//        String signature = getLocalSignWithPrivateKey(hash, ecKey);
//
//        // Or sign with safeheron mpc
//        // String signature = getMpcSigFromSafeheron(hash)
//        System.out.println("signature: " + signature);
//
//        // Create transaction object again
//        // The purpose of doing this is simply to be consistent with the MPC and explain it better
//        Protocol.Transaction transaction2 = createTRC20Transaction(WalletApi.encode58Check(ecKey.getAddress()), toAddress,
//                amount, currentTimeMillis, expiration, feeLimit, newestBlock);
//        byte[] sign = ByteArray.fromHexString(signature);
//
//        // Encode with signature
//        Protocol.Transaction signedTransaction = transaction2.toBuilder().addSignature(ByteString.copyFrom(sign)).build();
//
//        // Send transaction
//        boolean result = WalletApi.broadcastTransaction(signedTransaction);
//        System.out.println("send result:" + result);
//        Assert.assertTrue(result);
//    }
//
//    private Protocol.Transaction createTransaction(String from, String to, Long amount,
//                                                   Long timestamp, Long expiration,
//                                                   Protocol.Block newestBlock) {
//        BalanceContract.TransferContract.Builder transferContractBuilder = BalanceContract.TransferContract.newBuilder();
//
//        // Set amount
//        transferContractBuilder.setAmount(amount);
//
//        // Set from and to address
//        final byte[] fromAddress = WalletApi.decodeFromBase58Check(from);
//        final byte[] toAddress = WalletApi.decodeFromBase58Check(to);
//        ByteString bsOwner = ByteString.copyFrom(fromAddress);
//        ByteString bsTo = ByteString.copyFrom(toAddress);
//        transferContractBuilder.setToAddress(bsTo);
//        transferContractBuilder.setOwnerAddress(bsOwner);
//
//        // Create transaction contract
//        Protocol.Transaction.Contract.Builder contractBuilder = Protocol.Transaction.Contract.newBuilder();
//        Any any = Any.pack(transferContractBuilder.build());
//        contractBuilder.setParameter(any);
//        contractBuilder.setPermissionId(0);
//        contractBuilder.setType(Protocol.Transaction.Contract.ContractType.TransferContract);
//
//        // Create Transaction object, set timestamp and expiration time
//        Protocol.Transaction.Builder transactionBuilder = Protocol.Transaction.newBuilder();
//        transactionBuilder.getRawDataBuilder().addContract(contractBuilder)
//                .setTimestamp(timestamp)
//                .setExpiration(expiration);
//        Protocol.Transaction transaction = transactionBuilder.build();
//        return setReference(transaction, newestBlock);
//    }
//
//    private Protocol.Transaction createTRC20Transaction(String from, String to, Long amount,
//                                                        Long timestamp, Long expiration, long feeLimit,
//                                                        Protocol.Block newestBlock) {
//        SmartContractOuterClass.TriggerSmartContract.Builder smartContract = SmartContractOuterClass.TriggerSmartContract.newBuilder();
//        ByteString bsOwner = ByteString.copyFrom(WalletApi.decodeFromBase58Check(from));
//        smartContract.setOwnerAddress(bsOwner);
//        smartContract.setContractAddress(BS_CONTRACT);
//
//        byte[] toBytes = WalletApi.decodeFromBase58Check(to);
//        byte[] to2 = Arrays.copyOfRange(toBytes, 1, toBytes.length);
//        String toAddress = ByteArray.toHexString(to2);
//
//        Function function = new Function(
//                "transfer",
//                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(toAddress),
//                        new org.web3j.abi.datatypes.generated.Uint256(amount)),
//                Collections.emptyList()
//        );
//
//        String functionData = FunctionEncoder.encode(function);
//        byte[] dataByte = ByteArray.fromHexString(functionData);
//        ByteString dataByteString = ByteString.copyFrom(dataByte);
//        smartContract.setData(dataByteString);
//
//        // Create transaction contract
//        Protocol.Transaction.Contract.Builder contractBuilder = Protocol.Transaction.Contract.newBuilder();
//        Any any = Any.pack(smartContract.build());
//        contractBuilder.setParameter(any);
//        contractBuilder.setType(Protocol.Transaction.Contract.ContractType.TriggerSmartContract);
//
//        // Create Transaction object, set timestamp and expiration time(example: 10 hours)
//        Protocol.Transaction.Builder transactionBuilder = Protocol.Transaction.newBuilder();
//        transactionBuilder.getRawDataBuilder().addContract(contractBuilder)
//                .setTimestamp(timestamp)
//                .setExpiration(expiration)
//                .setFeeLimit(feeLimit);
//        Protocol.Transaction transaction = transactionBuilder.build();
//        return setReference(transaction, newestBlock);
//    }
//
//    private Protocol.Transaction setReference(Protocol.Transaction transaction, Protocol.Block newestBlock) {
//        long blockHeight = newestBlock.getBlockHeader().getRawData().getNumber();
//        byte[] blockHash = getBlockHash(newestBlock).getBytes();
//        byte[] refBlockNum = ByteArray.fromLong(blockHeight);
//        Protocol.Transaction.raw rawData = transaction.getRawData().toBuilder()
//                .setRefBlockHash(ByteString.copyFrom(ByteArray.subArray(blockHash, 8, 16)))
//                .setRefBlockBytes(ByteString.copyFrom(ByteArray.subArray(refBlockNum, 6, 8)))
//                .build();
//        return transaction.toBuilder().setRawData(rawData).build();
//    }
//
//    private Integer getTokenDecimals(ByteString tokenAddress) {
//        SmartContractOuterClass.TriggerSmartContract.Builder triggerSmartContract = SmartContractOuterClass.TriggerSmartContract.newBuilder();
//        triggerSmartContract.setContractAddress(tokenAddress);
//
//        final Function function = new Function("decimals",
//                Collections.emptyList(),
//                Collections.singletonList(new TypeReference<Uint256>() {
//                }));
//        String data = FunctionEncoder.encode(function);
//        byte[] dataByte = ByteArray.fromHexString(data);
//        ByteString dataByteString = ByteString.copyFrom(dataByte);
//        triggerSmartContract.setData(dataByteString);
//
//        GrpcAPI.TransactionExtention extention = grpcClient.triggerContract(triggerSmartContract.build());
//
//        if (extention.getConstantResultCount() < 1) {
//            System.out.println("query token's decimals failed.");
//            return null;
//        }
//
//        byte[] res = extention.getConstantResult(0).toByteArray();
//        return new BigInteger(res).intValue();
//    }
//
//    private Sha256Sm3Hash getBlockHash(Protocol.Block block) {
//        return Sha256Sm3Hash.of(block.getBlockHeader().getRawData().toByteArray());
//    }
//
//    private String getLocalSignWithPrivateKey(String hash, ECKey ecKey){
//        byte[] hashByte = ByteArray.fromHexString(hash);
//        ECKey.ECDSASignature ecdsaSignature = ecKey.sign(hashByte);
//        byte[] sign = ecdsaSignature.toByteArray();
//        return ByteArray.toHexString(sign);
//    }
//
//    private String getMpcSigFromSafeheron(String hash){
//        // Get sig's value from safeheron with hash(32-byte hex string without '0x' prefix).
//        // 1. Request "v1/transactions/mpcsign/create" api to sign then waiting for completed.
//        // 2. Get sig from webhook or "v1/transactions/mpcsign/one" api
//
//        // This is an example.
//        // The value of sig consists of 32 bytes r + 32 bytes s + 1 byte v
//        String sig = "f7fdd1c4d0e2d90a0c2dc1672d37dd0f143f308d4983ccb5a189dd2698780edf224e0add37e131e92781a197c5f2fd28aa5296e15bee4566d9578daa0e532d571b";
//        return sig;
//    }
//}

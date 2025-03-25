package com.safeheron.client.cosigner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safeheron.client.config.AESTypeEnum;
import com.safeheron.client.config.RSATypeEnum;
import com.safeheron.client.exception.SafeheronException;
import com.safeheron.client.utils.AesUtil;
import com.safeheron.client.utils.JsonUtil;
import com.safeheron.client.utils.RsaUtil;
import org.apache.commons.lang.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * CoSigner decryption and data conversion converter
 *
 * @author safeheron
 */
public class CoSignerConverter {
    /**
     * coSignerPubKey
     * 1.The API Co-Signer, acting as the client of the Approval Callback Service, sends a POST request and signs the request data using its private key.
     * 2.Upon receiving the request, the Approval Callback Service uses the API Co-Signer's public key to authenticate the request data, ensuring that the request originates from the API Co-Signer.
     * 3.Use the export-public-key command of the API Co-Signer CLI to export the API Co-Signer's public key.
     */
    private final String coSignerPubKey;

    /**
     * approvalCallbackServicePrivateKey
     * 1.The Approval Callback Service, acting as the server, signs the response data using its private key.
     * 2.Upon receiving the response, the API Co-Signer uses the Approval Callback Service's public key to authenticate the response data, ensuring that the response originates from the Approval Callback Service.
     * 3.The API Co-Signer automatically recieves and applies the Approval Callback Service's public key and callback address. (When creating the API Key, it is necessary to fill in the Approval Callback Service's public key and callback address.)
     */
    private final String approvalCallbackServicePrivateKey;

    /**
     * CoSignerConverter
     *
     * @param coSignerPubKey
     * @param approvalCallbackServicePrivateKey
     */
    public CoSignerConverter(String coSignerPubKey, String approvalCallbackServicePrivateKey) {
        this.coSignerPubKey = coSignerPubKey;
        this.approvalCallbackServicePrivateKey = approvalCallbackServicePrivateKey;
    }

    /**
     * requestConvert
     *
     * @param coSignerCallBack
     * @return CoSignerBizContent
     * @see CoSignerCallBack
     * @see CoSignerBizContent
     */
    public CoSignerBizContent requestConvert(CoSignerCallBack coSignerCallBack) throws Exception {
        // Verify sign
        Map<String, String> sigMap = new TreeMap<>();
        sigMap.put("key", coSignerCallBack.getKey());
        sigMap.put("timestamp", coSignerCallBack.getTimestamp().toString());
        sigMap.put("bizContent", coSignerCallBack.getBizContent());

        String signContent = sigMap.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
        boolean checkResult = RsaUtil.verifySign(signContent, coSignerCallBack.getSig(), coSignerPubKey);
        if (!checkResult) {
            throw new SafeheronException("signature verification failed");
        }

        // Use your RSA private key to decrypt request's aesKey and aesIv
        RSATypeEnum rsaType = StringUtils.isNotEmpty(coSignerCallBack.getRsaType()) && RSATypeEnum.valueByCode(coSignerCallBack.getRsaType()) != null ? RSATypeEnum.valueByCode(coSignerCallBack.getRsaType()) : RSATypeEnum.RSA;
        byte[] aesSaltDecrypt = RsaUtil.decrypt(coSignerCallBack.getKey(), approvalCallbackServicePrivateKey,rsaType);
        byte[] aesKey = Arrays.copyOfRange(aesSaltDecrypt, 0, 32);
        byte[] iv = Arrays.copyOfRange(aesSaltDecrypt, 32, aesSaltDecrypt.length);

        // Use AES to decrypt bizContent
        AESTypeEnum aesType = StringUtils.isNotEmpty(coSignerCallBack.getAesType()) && AESTypeEnum.valueByCode(coSignerCallBack.getAesType()) != null ? AESTypeEnum.valueByCode(coSignerCallBack.getAesType()) : AESTypeEnum.CBC;
        String decrypt = AesUtil.decrypt(coSignerCallBack.getBizContent(), aesKey, iv,aesType);
        ObjectMapper mapper = JsonUtil.getObjectMapper();

        //Data conversion
        CoSignerBizContent coSignerBizContent = mapper.readValue(decrypt, CoSignerBizContent.class);
        String coSignerBizContentJsonString = JsonUtil.toJson(coSignerBizContent.getCustomerContent());
        if (CoSignerTypeEnum.TRANSACTION.getTypeList().contains(coSignerBizContent.getType())) {
            TransactionApproval transactionApproval = mapper.readValue(coSignerBizContentJsonString, TransactionApproval.class);
            coSignerBizContent.setCustomerContent(transactionApproval);
        } else if (CoSignerTypeEnum.MPC_SIGN.getTypeList().contains(coSignerBizContent.getType())) {
            MPCSignApproval mpcSignApproval = mapper.readValue(coSignerBizContentJsonString, MPCSignApproval.class);
            coSignerBizContent.setCustomerContent(mpcSignApproval);
        } else if (CoSignerTypeEnum.WEB3_SIGN.getTypeList().contains(coSignerBizContent.getType())) {
            Web3SignApproval web3SignApproval = mapper.readValue(coSignerBizContentJsonString, Web3SignApproval.class);
            coSignerBizContent.setCustomerContent(web3SignApproval);
        }
        return coSignerBizContent;
    }

    /**
     * requestV3Convert
     *
     * @param coSignerCallBackV3
     * @return CoSignerBizContentV3
     * @see CoSignerCallBackV3
     * @see CoSignerBizContentV3
     */
    public CoSignerBizContentV3 requestV3Convert(CoSignerCallBackV3 coSignerCallBackV3) throws Exception {
        // Verify sign
        Map<String, String> sigMap = new TreeMap<>();
        sigMap.put("version", "v3");
        sigMap.put("timestamp", coSignerCallBackV3.getTimestamp().toString());
        sigMap.put("bizContent", coSignerCallBackV3.getBizContent());

        String signContent = sigMap.entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue()).collect(Collectors.joining("&"));
        boolean checkResult = RsaUtil.verifySignPSS(signContent, coSignerCallBackV3.getSig(), coSignerPubKey);
        if (!checkResult) {
            throw new SafeheronException("signature verification failed");
        }
        ObjectMapper mapper = JsonUtil.getObjectMapper();
        String bizContent = coSignerCallBackV3.getBizContent();
        String decrypt = new String(Base64.getDecoder().decode(bizContent), StandardCharsets.UTF_8);
        //Data conversion
        CoSignerBizContentV3 coSignerBizContent = mapper.readValue(decrypt, CoSignerBizContentV3.class);
        String coSignerBizContentJsonString = JsonUtil.toJson(coSignerBizContent.getDetail());
        if (CoSignerTypeEnum.TRANSACTION.getTypeList().contains(coSignerBizContent.getType())) {
            TransactionApproval transactionApproval = mapper.readValue(coSignerBizContentJsonString, TransactionApproval.class);
            coSignerBizContent.setDetail(transactionApproval);
        } else if (CoSignerTypeEnum.MPC_SIGN.getTypeList().contains(coSignerBizContent.getType())) {
            MPCSignApproval mpcSignApproval = mapper.readValue(coSignerBizContentJsonString, MPCSignApproval.class);
            coSignerBizContent.setDetail(mpcSignApproval);
        } else if (CoSignerTypeEnum.WEB3_SIGN.getTypeList().contains(coSignerBizContent.getType())) {
            Web3SignApproval web3SignApproval = mapper.readValue(coSignerBizContentJsonString, Web3SignApproval.class);
            coSignerBizContent.setDetail(web3SignApproval);
        }
        return coSignerBizContent;
    }

    /**
     * responseConverter
     * It has been Deprecated,Please use {@link #responseConverterWithNewCryptoType}
     *
     * @param coSignerResponse
     * @return Map<String, Object>
     * @see CoSignerResponse
     * @see Map<String, Object>
     */
    @Deprecated
    public Map<String, String> responseConverter(CoSignerResponse coSignerResponse) throws Exception {


        final String responseJson = JsonUtil.toJson(coSignerResponse);
        byte[] aesKey = AesUtil.generateAESKey();
        byte[] ivKey = AesUtil.generateIvKey();
        String aesEncryptResult = "";
        if (StringUtils.isNotBlank(responseJson)) {
            aesEncryptResult = AesUtil.encrypt(responseJson, aesKey, ivKey, AESTypeEnum.CBC);
        }

        // Use Safeheron apiPubKey to encrypt response's aesKey and aesIv
        byte[] sourceKey = Arrays.copyOf(aesKey, aesKey.length + ivKey.length);
        System.arraycopy(ivKey, 0, sourceKey, aesKey.length, ivKey.length);
        String rsaEncryptResult = RsaUtil.encrypt(sourceKey, coSignerPubKey, RSATypeEnum.RSA);

        // Create params map
        long timestamp = System.currentTimeMillis();
        Map<String, String> responseData = new TreeMap<>();
        responseData.put("code", "200");
        responseData.put("message", "SUCCESS");
        responseData.put("timestamp", timestamp + "");
        if (StringUtils.isNotBlank(responseJson)) {
            responseData.put("bizContent", aesEncryptResult);
        }
        responseData.put("key", rsaEncryptResult);

        // Sign the response data with your bizPrivKey
        String signContent = responseData.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
        String rsaSig = RsaUtil.sign(signContent, approvalCallbackServicePrivateKey);
        responseData.put("sig", rsaSig);
        return responseData;
    }

    /**
     * responseConverterWithNewCryptoType
     *
     * @param coSignerResponse
     * @return Map<String, Object>
     * @see CoSignerResponse
     * @see Map<String, Object>
     */
    public Map<String, String> responseConverterWithNewCryptoType(CoSignerResponse coSignerResponse) throws Exception {
        final String responseJson = JsonUtil.toJson(coSignerResponse);
        byte[] aesKey = AesUtil.generateAESKey();
        byte[] ivKey = AesUtil.generateIvKey();
        String aesEncryptResult = "";
        if (StringUtils.isNotBlank(responseJson)) {
            aesEncryptResult = AesUtil.encrypt(responseJson, aesKey, ivKey, AESTypeEnum.GCM);
        }

        // Use Safeheron apiPubKey to encrypt response's aesKey and aesIv
        byte[] sourceKey = Arrays.copyOf(aesKey, aesKey.length + ivKey.length);
        System.arraycopy(ivKey, 0, sourceKey, aesKey.length, ivKey.length);
        String rsaEncryptResult = RsaUtil.encrypt(sourceKey, coSignerPubKey, RSATypeEnum.ECB_OAEP);
        // Create params map
        long timestamp = System.currentTimeMillis();
        Map<String, String> responseData = new TreeMap<>();
        responseData.put("code", "200");
        responseData.put("message", "SUCCESS");
        responseData.put("timestamp", timestamp + "");
        if (StringUtils.isNotBlank(responseJson)) {
            responseData.put("bizContent", aesEncryptResult);
        }
        responseData.put("key", rsaEncryptResult);
        // Sign the response data with your bizPrivKey
        String signContent = responseData.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
        String rsaSig = RsaUtil.sign(signContent, approvalCallbackServicePrivateKey);
        responseData.put("sig", rsaSig);
        responseData.put("rsaType", RSATypeEnum.ECB_OAEP.getCode());
        responseData.put("aesType", AESTypeEnum.GCM.getCode());
        return responseData;
    }


    /**
     * responseConverterV3
     *
     * @param coSignerResponseV3
     * @return Map<String, Object>
     * @see CoSignerResponseV3
     * @see Map<String, Object>
     */
    public Map<String, String> responseV3Converter(CoSignerResponseV3 coSignerResponseV3) throws Exception {
        final String responseJson = JsonUtil.toJson(coSignerResponseV3);
        // Create params map
        long timestamp = System.currentTimeMillis();
        Map<String, String> responseData = new TreeMap<>();
        responseData.put("code", "200");
        responseData.put("message", "SUCCESS");
        responseData.put("timestamp", timestamp + "");
        responseData.put("version", "v3");
        if (StringUtils.isNotBlank(responseJson)) {
            responseData.put("bizContent", Base64.getEncoder().encodeToString(responseJson.getBytes()));
        }
        // Sign the response data with your Approval Callback Service's private key
        String signContent = responseData.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
        String rsaSig = RsaUtil.signPSS(signContent, approvalCallbackServicePrivateKey);
        responseData.put("sig", rsaSig);
        return responseData;
    }
}

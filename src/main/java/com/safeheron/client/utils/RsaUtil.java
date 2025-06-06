package com.safeheron.client.utils;

import com.safeheron.client.config.RSATypeEnum;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author safeheron
 * @date 2022/7/25 16:27
 */
public class RsaUtil {

    private static final String SIGN_TYPE_RSA = "RSA";

    public static final String SIGN_TYPE_RSA_OAEP = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";
    private static final String SIGN_ALGORITHMS_SHA256RSA = "SHA256WithRSA";
    public static final String SIGN_ALGORITHMS_SHA256RSA_PSS = "SHA256withRSA/PSS";
    private static final int MAX_ENCRYPT_BLOCK = 501;
    private static final int MAX_DECRYPT_BLOCK = 512;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static String encrypt(byte[] plainText, String publicKey, RSATypeEnum RSAType) throws Exception {
        ByteArrayOutputStream out = null;
        try {
            PublicKey pubKey = getPublicKey(SIGN_TYPE_RSA, publicKey);
            Cipher cipher;
            if (RSATypeEnum.ECB_OAEP.equals(RSAType)) {
                cipher = Cipher.getInstance(SIGN_TYPE_RSA_OAEP);
                OAEPParameterSpec oaepParams = new OAEPParameterSpec("SHA-256", "MGF1", new MGF1ParameterSpec("SHA-256"), PSource.PSpecified.DEFAULT);
                cipher.init(Cipher.ENCRYPT_MODE, pubKey, oaepParams);
            } else {
                cipher = Cipher.getInstance(SIGN_TYPE_RSA);
                cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            }
            int inputLen = plainText.length;
            out = new ByteArrayOutputStream();
            int i = 0, offSet = 0;
            byte[] cache;
            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(plainText, offSet, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(plainText, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }

            return Base64.getEncoder().encodeToString(out.toByteArray());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static byte[] decrypt(String content, String privateKey, RSATypeEnum RSAType) throws Exception {
        ByteArrayOutputStream out = null;
        try {
            PrivateKey priKey = getPrivateKey(SIGN_TYPE_RSA, privateKey);
            Cipher cipher;
            if (RSATypeEnum.ECB_OAEP.equals(RSAType)) {
                cipher = Cipher.getInstance(SIGN_TYPE_RSA_OAEP);
                OAEPParameterSpec oaepParams = new OAEPParameterSpec("SHA-256", "MGF1", new MGF1ParameterSpec("SHA-256"), PSource.PSpecified.DEFAULT);
                cipher.init(Cipher.DECRYPT_MODE, priKey, oaepParams);
            } else {
                cipher = Cipher.getInstance(SIGN_TYPE_RSA);
                cipher.init(Cipher.DECRYPT_MODE, priKey);
            }
            byte[] encryptedData = Base64.getDecoder().decode(content);
            int inputLen = encryptedData.length;
            out = new ByteArrayOutputStream();
            int i = 0, offSet = 0;
            byte[] cache;

            while (inputLen - offSet > 0) {
                if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_DECRYPT_BLOCK;
            }
            byte[] decryptedData = out.toByteArray();
            return decryptedData;
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static String sign(String content, String privateKey) throws Exception {
        PrivateKey priKey = getPrivateKey(SIGN_TYPE_RSA, privateKey);
        Signature privateSignature = Signature.getInstance(SIGN_ALGORITHMS_SHA256RSA);
        privateSignature.initSign(priKey);
        privateSignature.update(content.getBytes(StandardCharsets.UTF_8));
        byte[] signature = privateSignature.sign();
        return Base64.getEncoder().encodeToString(signature);
    }

    public static String signPSS(String content, String privateKey) throws Exception {
        PrivateKey priKey = getPrivateKey(SIGN_TYPE_RSA, privateKey);
        Signature privateSignature = Signature.getInstance(SIGN_ALGORITHMS_SHA256RSA_PSS);
        privateSignature.initSign(priKey);
        privateSignature.update(content.getBytes(StandardCharsets.UTF_8));
        byte[] signature = privateSignature.sign();
        return Base64.getEncoder().encodeToString(signature);
    }

    public static boolean verifySign(String content, String sign, String publicKey) throws Exception {
        PublicKey pubKey = getPublicKey(SIGN_TYPE_RSA, publicKey);
        Signature signature = Signature.getInstance(SIGN_ALGORITHMS_SHA256RSA);
        signature.initVerify(pubKey);
        signature.update(content.getBytes(StandardCharsets.UTF_8));
        return signature.verify(Base64.getDecoder().decode(sign.getBytes()));
    }

    public static boolean verifySignPSS(String content, String sign, String publicKey) throws Exception {
        PublicKey pubKey = getPublicKey(SIGN_TYPE_RSA, publicKey);
        Signature signature = Signature.getInstance(SIGN_ALGORITHMS_SHA256RSA_PSS);
        signature.initVerify(pubKey);
        signature.update(content.getBytes(StandardCharsets.UTF_8));
        return signature.verify(Base64.getDecoder().decode(sign.getBytes()));
    }

    private static PublicKey getPublicKey(String algorithm, String publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        byte[] publicKeyData = Base64.getDecoder().decode(publicKey.getBytes(StandardCharsets.UTF_8));
        return keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyData));
    }

    private static PrivateKey getPrivateKey(String algorithm, String privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        byte[] privateKeyData = Base64.getDecoder().decode(privateKey.getBytes(StandardCharsets.UTF_8));
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyData));
    }

}

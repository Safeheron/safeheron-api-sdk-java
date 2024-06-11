package com.safeheron.client.utils;

import com.safeheron.client.config.AESTypeEnum;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;

/**
 * @author safeheron
 * @date 2022/7/25 16:27
 */
public class AesUtil {

    private static final int AES_KEY_SIZE_256 = 256;
    private static final String AES_ALG = "AES";
    private static final String AES_CBC_PKC_ALG = "AES/CBC/PKCS7Padding";
    private static final String AES_GCM_PKC_ALG = "AES/GCM/NoPadding";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static byte[] generateAESKey() throws NoSuchAlgorithmException {
        KeyGenerator kg = KeyGenerator.getInstance(AES_ALG);
        kg.init(AES_KEY_SIZE_256);
        SecretKey secretKey = kg.generateKey();
        return secretKey.getEncoded();
    }

    public static byte[] generateIvKey() {
        SecureRandom random = new SecureRandom();
        byte[] iv = new byte[16];
        random.nextBytes(iv);
        return iv;
    }


    public static String encrypt(String content, byte[] aesKey, byte[] iv, AESTypeEnum AESType) throws Exception {
        Cipher cipher;
        SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey, AES_ALG);
        if (AESTypeEnum.GCM.equals(AESType)) {
            cipher = Cipher.getInstance(AES_GCM_PKC_ALG, "BC");
            GCMParameterSpec parameterSpec = new GCMParameterSpec(128, iv);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, parameterSpec);
        } else {
            cipher = Cipher.getInstance(AES_CBC_PKC_ALG, "BC");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        }

        byte[] encryptBytes = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptBytes);
    }

    public static String decrypt(String content, byte[] aesKey, byte[] iv, AESTypeEnum AESType) throws Exception {
        Cipher cipher;
        SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey, AES_ALG);
        if (AESTypeEnum.GCM.equals(AESType)) {
            cipher = Cipher.getInstance(AES_GCM_PKC_ALG, "BC");
            GCMParameterSpec parameterSpec = new GCMParameterSpec(128, iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, parameterSpec);
        } else {
            cipher = Cipher.getInstance(AES_CBC_PKC_ALG, "BC");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        }
        byte[] decryptBytes = cipher.doFinal(Base64.getDecoder().decode(content));
        return new String(decryptBytes, StandardCharsets.UTF_8);
    }
}

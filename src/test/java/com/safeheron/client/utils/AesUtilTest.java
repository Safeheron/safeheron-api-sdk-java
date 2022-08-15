package com.safeheron.client.utils;

import org.junit.*;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

/**
 * @author safeheron
 * @date 2022/7/25 17:49
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AesUtilTest {

    static byte[] aesKey;
    static byte[] aesIv;
    String plaintext = "plain text";
    static String encryptedData;

    @BeforeClass
    public static void setUp() throws Exception {
        aesKey = AesUtil.generateAESKey();
        aesIv = AesUtil.generateIvKey();
    }

    @Test
    public void a_encrypt() throws Exception {
        encryptedData = AesUtil.encrypt(plaintext, aesKey, aesIv);
        Assert.assertNotNull(encryptedData);
    }

    @Test
    public void b_decrypt() throws Exception {
        String decryptedData = AesUtil.decrypt(encryptedData, aesKey, aesIv);
        Assert.assertEquals(plaintext, decryptedData);
    }
}
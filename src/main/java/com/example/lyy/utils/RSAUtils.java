package com.example.lyy.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtils {

    /**
     * RSA最大加密明文大小
     */
    public static final int DEFAULT_MAX_ENCRYPT_BLOCK = 117;

    public static String encrypt(String plainText) throws Exception {
        String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDFK8/0WUrI+n4aCaP4DQpF0lW5h28iNrGJwTkf/eJAYFhJ3lzWb7WlYJvz/3+kPq0/SHbTP0DtAvFQwb5PfrZqaJF5aBt8XGHbe7DNp6PRDrpTbcuKOg+xpZn9nBoNEB8MSL/eWprKPK3QXD1KjZzxeWB3VhmTWg8vKA4cMM+cVwIDAQAB";
        BASE64Decoder b64d = new BASE64Decoder();
        byte[] keyByte = b64d.decodeBuffer(publicKeyStr);
        X509EncodedKeySpec x509ek = new X509EncodedKeySpec(keyByte);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509ek);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] byteArray = RSAUtils.segmentedProcess(cipher, plainText.getBytes(), DEFAULT_MAX_ENCRYPT_BLOCK);
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String password=base64Encoder.encode(byteArray);
        password=password.replaceAll("\r\n","");
        return password ;
    }

    /**
     * @description
     */
    private static byte[] segmentedProcess(Cipher cipher, byte[] textArray, int maxBlock)
            throws BadPaddingException, IllegalBlockSizeException, IOException, BadPaddingException, IllegalBlockSizeException {
        int textLength = textArray.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int loopIndex = 0;

        // 对数据分段解密
        while (textLength - offSet > 0) {
            if (textLength - offSet > maxBlock) {
                cache = cipher.doFinal(textArray, offSet, maxBlock);
            } else {
                cache = cipher.doFinal(textArray, offSet, textLength - offSet);
            }

            out.write(cache, 0, cache.length);
            loopIndex++;
            offSet = loopIndex * maxBlock;
        }

        byte[] byteArray = out.toByteArray();
        out.close();
        return byteArray;
    }

    public static void main(String[] args) throws Exception {
        String dd=RSAUtils.encrypt("Zl3838438+-");
        System.out.println(dd.replaceAll("\r\n",""));
    }
}

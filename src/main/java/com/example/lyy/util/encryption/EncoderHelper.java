package com.example.lyy.util.encryption;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 开发工具: Intellij IDEA
 * 标题:
 * 内容:
 * 作者:     Jinzy
 * 创建日期: 2017/3/10
 */
public class EncoderHelper {

    /**
     * 生成密文工具
     * 使用 密码 + 用户主键生成 MD5的密码，防止穷举法破解密码
     */
    public static String encode(String password, Object primaryKey) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            BASE64Encoder base64Encoder = new BASE64Encoder();
            String reciphertext = password + String.valueOf(primaryKey);
            String ciphertext = base64Encoder.encode(messageDigest.digest(reciphertext.getBytes("UTF-8")));

            return ciphertext;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}

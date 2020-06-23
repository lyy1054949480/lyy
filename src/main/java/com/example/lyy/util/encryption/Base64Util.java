package com.example.lyy.util.encryption;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @Description: Base64编码、解码工具
 */
public class Base64Util {
    static Base64.Encoder encoder = Base64.getEncoder();
    static Base64.Decoder decoder = Base64.getDecoder();
    
    /**
     * 解码
     */
    public static String decode(String encodedContent){
        String result = null;
        try {
            result = new String(decoder.decode(encodedContent), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 编码
     */
    public static String encode(String content){
        byte[] textByte = new byte[0];
        try {
            textByte = content.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String encodedContent = encoder.encodeToString(textByte);
        return encodedContent;
    }
}

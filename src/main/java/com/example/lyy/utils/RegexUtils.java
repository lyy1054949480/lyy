package com.example.lyy.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title: 正则表达式工具类
 * @Description: 正则表达式工具类
 * @Date: 2019/3/26 20:59
 * @Auther: jinzhy
 */
public class RegexUtils {

    public static String getMatcher(String regex, String source) {
        String result = "";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            result = matcher.group(0);
        }
        return result;
    }
}

package com.example.lyy.util;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpStringFormat {
    /**
     * 方法说明 :将首字母和带 _ 后第一个字母 转换成大写
     *
     * @return :String
     * @author :HFanss
     * @date :2018年5月31日下午9:52:19
     */
    public static String upperTable(String str) {
        // 字符串缓冲区
        StringBuffer sbf = new StringBuffer();
        // 如果字符串包含 下划线
        if (str.contains("_")) {
            // 按下划线来切割字符串为数组
            String[] split = str.split("_");
            // 循环数组操作其中的字符串
            for (int i = 0, index = split.length; i < index; i++) {
                // 递归调用本方法
                String upperTable = upperTable(split[i]);
                // 添加到字符串缓冲区
                sbf.append(upperTable);
            }
        } else {// 字符串不包含下划线
            // 转换成字符数组
            char[] ch = str.toCharArray();
            // 判断首字母是否是字母
            if (ch[0] >= 'a' && ch[0] <= 'z') {
                // 利用ASCII码实现大写
                ch[0] = (char) (ch[0] - 32);
            }
            // 添加进字符串缓存区
            sbf.append(ch);
        }
        // 返回
        return sbf.toString();
    }

    public static String lineToHump(String str) {
        Pattern linePattern = Pattern.compile("_(\\w)");
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static void main(String[] args) {
        String a_s_a = "";
        Charset utf8 = StandardCharsets.UTF_8;
        assert(StringUtils.isEmpty(a_s_a));
        System.out.println(a_s_a);
    }

}

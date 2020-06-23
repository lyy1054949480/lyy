package com.example.lyy.utils;

import com.google.common.base.Preconditions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title: 正则校验工具
 * @Description: 正则校验工具
 * @Date: 2019/5/24 17:44
 * @Auther: zhaoxuezhao
 */
public class RegexUtil {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");


    /**
     * 根据身份证号获取年龄
     *
     * @param idNumber
     * @return Integer
     */
    public static Integer getAge(String idNumber) {
        Calendar curr = Calendar.getInstance();
        Calendar born = Calendar.getInstance();
        born.setTime(getBirthDay(idNumber));
        if (curr.before(born)) {
            throw new RuntimeException("出生日期不能大于当前日期！");
        }
        /*取出当前年月日*/
        int yearNow = curr.get(Calendar.YEAR);
        int monthNow = curr.get(Calendar.MONTH);
        int dayNow = curr.get(Calendar.DAY_OF_MONTH);
        /*取出出生年月日*/
        int yearBirth = born.get(Calendar.YEAR);
        int monthBirth = born.get(Calendar.MONTH);
        int dayBirth = born.get(Calendar.DAY_OF_MONTH);
        /*大概年龄是当前年减去出生年*/
        int age = yearNow - yearBirth;
        /*如果出当前月小与出生月，或者当前月等于出生月但是当前日小于出生日，那么年龄age就减一岁*/
        if (monthNow < monthBirth || (monthNow == monthBirth && dayNow < dayBirth)) {
            age--;
        }
        return age < 0 ? 0 : age;
    }


    /**
     * 身份证验证
     *
     * @param idNumber 号码内容
     * @return 是否有效
     */
    public static boolean isValid(String idNumber) {
        Preconditions.checkNotNull(idNumber, "身份号码为空");
        // 定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
        //^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$  15位
        //^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$  18位
        String regularExpression =
                "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
        boolean matches = idNumber.matches(regularExpression);

        //十八位身份证校验
        if (matches && idNumber.length() == 18) {
            try {
                char[] charArray = idNumber.toCharArray();
                //前十七位加权因子
                int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                //这是除以11后，可能产生的11位余数对应的验证码
                String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
                int sum = 0;
                for (int i = 0; i < idCardWi.length; i++) {
                    int current = Integer.parseInt(String.valueOf(charArray[i]));
                    int count = current * idCardWi[i];
                    sum += count;
                }
                char idCardLast = charArray[17];
                int idCardMod = sum % 11;
                if (idCardY[idCardMod].toUpperCase().equals(String.valueOf(idCardLast).toUpperCase())) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return matches;

    }


    /**
     * 获取出生日期
     *
     * @param idNumber
     * @return Date
     */
    public static Date getBirthDay(String idNumber) {
        // 准备验证规则
        String regex1 = "(\\d{6})(\\d{8})(.*)";
        String regex2 = "(\\d{4})(\\d{2})(\\d{2})";
        Pattern birthDayRegular = Pattern.compile(regex1);
        // .*连在一起就意味着任意数量的不包含换行的字符
        Pattern yearMonthDayRegular = Pattern.compile(regex2);

        Matcher matcher = birthDayRegular.matcher(idNumber);
        Date date = null;
        if (matcher.matches()) {
            //matcher.group(2)匹配BirthDayRegular里的第二个表达式\d{8}的8个字符即出生年月加日期
            Matcher matcher2 = yearMonthDayRegular.matcher(matcher.group(2));
            //若不为空,依次输出年月日
            if (matcher2.matches()) {
                try {
                    date = format.parse(matcher2.group(1) + "-" + matcher2.group(2) + "-" + matcher2.group(3));
                } catch (ParseException e) {
                    throw new RuntimeException("身份证不正确，无法获取出生日期！");
                }
            }
        }
        return date;

    }

    /**
     * 校验手机号是否位11位数字
     *
     * @param phoneNum
     * @return boolean
     */
    public static boolean checkPhone(String phoneNum) {
        String regex = "^1\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNum);
        return matcher.matches();
    }

    /**
     * 校验时间串格式是否为 %Y-%m
     *
     * @param date
     * @return
     */
    public static boolean checkDate(String date) {
        String regex = "[1-9]\\d{3}-(0[1-9]|1[0-2])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }


    /**
     * 校验字符串是否为数字字母下划线组成
     *
     * @param date
     * @return
     */
    public static boolean checkAccount(String date) {
        String regex = "^\\w+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }


    /**
     * 校验字符串是否手机号
     *
     * @param date
     * @return
     */
    public static boolean checkPhoneNum(String date) {
        String regex = "^1\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    /**
     * 校验字符串是否
     *
     * @param date
     * @return
     */
    public static boolean checkHeath(String date) {
        String regexHeath = "^['是'|'否']$";
        Pattern pattern = Pattern.compile(regexHeath);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }
    /**
     * 校验字符串性别
     *
     * @param date
     * @return
     */
    public static boolean checkGender(String date) {
        String regexGender = "^['男'|'女']$";
        Pattern pattern = Pattern.compile(regexGender);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    /**
     * 校验字符串星期
     *
     * @param workDay
     * @return
     */
    public static boolean checkWorkDay(String workDay){
        String reg = "^([1-7]+[,])*([1-7]+)$";
        Pattern pattern=Pattern.compile(reg);
        Matcher isNum=pattern.matcher(workDay);
        boolean matches = isNum.matches();
        if (matches){
            String[] split = workDay.split(",");
            Map<String,String> map =new HashMap<>();
            for (String week:split) {
                if (!map.containsKey(week)){
                    map.put(week,week);
                }else{
                    return false;
                }
            }
            return true;
        }else{
            return false;
        }
    }
}

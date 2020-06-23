package com.example.lyy.domain;

/**
 * @Author: cjw
 * @Description: 缴费频率枚举
 * @Date: 19:49 2018/8/17
 */
public enum PaymentFreqEnum {

    FULL_PAY("0","趸交"),
    YEAR_PAY("12","年交"),
    MONTH_PAY("1","月交"),
    SEASON_PAY("3","季交"),
    HALF_YEAR_PAY("6","半年交"),
    INDEFINITE_PAY("-1","不定期交");

    private String code;

    private String message;

    PaymentFreqEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static String getCodeByMessage(String message){
        PaymentFreqEnum[] enums = values();
        for (PaymentFreqEnum e : enums) {
            if (e.getMessage().equals(message)) {
                return e.getCode();
            }
        }
        return null;
    }

    public static String getMessageByCode(String code){
        PaymentFreqEnum[] enums = values();
        for (PaymentFreqEnum e : enums) {
            if (e.getCode().equals(code)) {
                return e.getMessage();
            }
        }
        return null;
    }
}

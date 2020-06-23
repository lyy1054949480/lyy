package com.example.lyy.domain;

/**
 * @Author: cjw
 * @Description: 状态码枚举
 * @Date: 11:04 2018/6/27
 */
public enum ResponseCode {

    //成功
    Success("0000"),
    SuccessMsg("success"),
    SystemErrorMsg("failed"),

    //失败
    SystemError("9999");

    private String code;

    private ResponseCode(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

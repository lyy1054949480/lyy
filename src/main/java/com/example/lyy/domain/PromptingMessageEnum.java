package com.example.lyy.domain;

/**
 * @Author: cjw
 * @Description: 提示信息枚举
 * @Date: 10:21 2018/7/19
 */
public enum PromptingMessageEnum {

    MESSAGE_0("成功"),
    MESSAGE_1("参数有误"),
    MESSAGE_2("产品信息调用失败"),
    MESSAGE_3("拆单失败"),
    MESSAGE_4("保费计算失败"),
    MESSAGE_5("无法识别该订单"),
    MESSAGE_6("返回失败"),
    MESSAGE_7("参数有误"),
    MESSAGE_8("无查询数据"),
    MESSAGE_9("用户信息调用失败"),
    MESSAGE_10("支付失败"),
    MESSAGE_11("系统错误"),
    MESSAGE_12("核保失败"),
    MESSAGE_13("入库失败"),
    MESSAGE_14("附件入库失败"),
    MESSAGE_15("参数有误"),
    MESSAGE_16("回执入库失败"),
    MESSAGE_17("投保单不存在"),
    MESSAGE_18("userCode不能为空"),
    MESSAGE_19("查询数据为空"),
    MESSAGE_20("该用户无查询权限"),
    MESSAGE_21("报文推送异常"),
    MESSAGE_22("投保单导出异常"),
    MESSAGE_23("查询异常"),
    MESSAGE_24("投保单资源绑定失败"),
    MESSAGE_25("订单不存在"),
    MESSAGE_26("订单保费修改失败"),
    MESSAGE_27("投保单保费修改失败"),
    MESSAGE_28("订单信息入库失败");

    private String message;

    PromptingMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}

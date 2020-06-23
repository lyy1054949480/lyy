package com.example.lyy.util.auxiliary;

import com.alibaba.fastjson.JSONObject;
import com.example.lyy.domain.PromptingMessageEnum;
import com.example.lyy.domain.ResponseCode;
import com.example.lyy.domain.ResponseData;


import java.util.List;

/**
 * @Author: cjw
 * @Description: 返回信息工具类
 * @Date: 9:42 2019/3/1
 */
public class ResponseUtil {
    /**
     * 提供系统错误的默认错误提示.
     * @return
     */
    public static String packCommonErrorResponseData(){
        return packErrorResponseData(ResponseCode.SystemError.getCode(), PromptingMessageEnum.MESSAGE_11.getMessage());
    }

    /**
     *  error类型的返回值处理
     * @param responseCode 错误码
     * @param msg 错误信息
     * @return
     */
    public static String packErrorResponseData(String responseCode, String msg){
        return packResponseData(responseCode, msg, null, null, null);
    }

    /**
     * 封装成功类型返回多个对象的返回值
     * @param list
     * @param total
     * @return
     */
    public static String packSuccessMultiResponseData(List list, Integer total){
        return packSuccessResponseData(null, list, total);
    }

    /**
     * 封装成功类型无返回
     * @return
     */
    public static String packSuccessNoneResponseData(){
        return packSuccessResponseData(null, null, null);
    }

    /**
     *  封装成功类型返回一个对象的返回值
     * @param data
     * @return
     */
    public static String packSuccessSingleResponseData(Object data){
        return packSuccessResponseData(data, null, null);
    }

    /**
     * 封装成功类型的返回值
     * @param data 单个查询的返回值
     * @param list 列表查询的返回值
     * @param total 分页中的总条数
     * @return
     */
    public static String packSuccessResponseData(Object data, List list, Integer total){
        return packResponseData(ResponseCode.Success.getCode(), PromptingMessageEnum.MESSAGE_0.getMessage(), data, list, total);
    }


    /**
     * 封装返回数据
     * @param responseCode
     * @param msg
     * @param data
     * @return
     */
    public static String packResponseData(String responseCode, String msg, Object data, List list, Integer total){
        try {
            return JSONObject.toJSONString(packResponseDataObject(responseCode, msg, data, list, total));
        }catch (Exception e){
            ResponseData responseData = new ResponseData();
            responseData.setCode(ResponseCode.SystemError.getCode());
            responseData.setMsg(PromptingMessageEnum.MESSAGE_11.getMessage());
            return JSONObject.toJSONString(responseData);
        }
    }

    /**
     * 初始化
     * @param responseCode
     * @param msg
     * @param data
     * @param list
     * @param total
     * @return
     */
    public static ResponseData packResponseDataObject(String responseCode, String msg, Object data, List list, Integer total){
        ResponseData responseData = new ResponseData();
        responseData.setCode(responseCode);
        responseData.setList(list);
        responseData.setMsg(msg);
        responseData.setData(data);
        responseData.setTotal(total);
        return responseData;
    }

    /**
     * 成功返回多个数据, 带总数.
     * @param list
     * @param total
     * @return
     */
    public static ResponseData packSuccessMultiResponseDataObject(List list, Integer total){
        return packResponseDataObject(ResponseCode.Success.getCode(), PromptingMessageEnum.MESSAGE_0.getMessage(), null, list, total);
    }

    /**
     * 成功返回单个数据
     * @return
     */
    public static ResponseData packSuccessSingleResponseDataObject(Object data){
        return packResponseDataObject(ResponseCode.Success.getCode(), PromptingMessageEnum.MESSAGE_0.getMessage(), data, null, null);
    }

    /**
     * 无查询结果, 实体返回值
     * @return
     */
    public static ResponseData packSuccessNoneDataObject(){
        return packResponseDataObject(ResponseCode.Success.getCode(), PromptingMessageEnum.MESSAGE_8.getMessage(), null, null, null);
    }


    /**
     * 无查询结果, String返回值
     * @return
     */
    public static String packSuccessNoResultResponseData(){
        return packResponseData(ResponseCode.Success.getCode(), PromptingMessageEnum.MESSAGE_8.getMessage(), null, null, null);
    }

    /**
     * 错误提示.
     * @param code
     * @param message
     * @return
     */
    public static ResponseData packErrorResponseDataObject(String code, String message) {
        return packResponseDataObject(code, message, null, null, null);
    }
}

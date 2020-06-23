package com.example.lyy.domain;

import java.util.List;

/**
 * @Author: cjw
 * @Description:
 * @Date: 10:19 2018/6/27
 */
public class ResponseData {

    /**
     * 状态码
     */
    private String code = "";

    /**
     * 错误信息
     */
    private String msg = "";

    /**
     * 数据对象
     */
    private Object data;

    /**
     * 总条数
     */
    private Integer total;

    /**
     * 数据列表
     */
    private List list;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", total=" + total +
                ", list=" + list +
                '}';
    }
}

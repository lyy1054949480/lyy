package com.example.lyy.entity;

import java.util.List;

public class UpdateData {


    private String account;
    private String appCode;
    private String name;
    private String userCode;
    private List<UpdateDetailQoListBean> updateDetailQoList;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public List<UpdateDetailQoListBean> getUpdateDetailQoList() {
        return updateDetailQoList;
    }

    public void setUpdateDetailQoList(List<UpdateDetailQoListBean> updateDetailQoList) {
        this.updateDetailQoList = updateDetailQoList;
    }

}

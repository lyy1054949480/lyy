package com.example.lyy.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

public class Data1 implements Serializable {
    @Column(name = "app_code")
    private String appCode;

    @Column(name = "policy_no")
    private String policyNo;

    private String rid;

    private BigDecimal prem;

    private static final long serialVersionUID = 1L;

    /**
     * @return app_code
     */
    public String getAppCode() {
        return appCode;
    }

    /**
     * @param appCode
     */
    public void setAppCode(String appCode) {
        this.appCode = appCode == null ? null : appCode.trim();
    }

    /**
     * @return policy_no
     */
    public String getPolicyNo() {
        return policyNo;
    }

    /**
     * @param policyNo
     */
    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo == null ? null : policyNo.trim();
    }

    /**
     * @return rid
     */
    public String getRid() {
        return rid;
    }

    /**
     * @param rid
     */
    public void setRid(String rid) {
        this.rid = rid == null ? null : rid.trim();
    }

    /**
     * @return prem
     */
    public BigDecimal getPrem() {
        return prem;
    }

    /**
     * @param prem
     */
    public void setPrem(BigDecimal prem) {
        this.prem = prem;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Data1 other = (Data1) that;
        return (this.getAppCode() == null ? other.getAppCode() == null : this.getAppCode().equals(other.getAppCode()))
            && (this.getPolicyNo() == null ? other.getPolicyNo() == null : this.getPolicyNo().equals(other.getPolicyNo()))
            && (this.getRid() == null ? other.getRid() == null : this.getRid().equals(other.getRid()))
            && (this.getPrem() == null ? other.getPrem() == null : this.getPrem().equals(other.getPrem()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAppCode() == null) ? 0 : getAppCode().hashCode());
        result = prime * result + ((getPolicyNo() == null) ? 0 : getPolicyNo().hashCode());
        result = prime * result + ((getRid() == null) ? 0 : getRid().hashCode());
        result = prime * result + ((getPrem() == null) ? 0 : getPrem().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", appCode=").append(appCode);
        sb.append(", policyNo=").append(policyNo);
        sb.append(", rid=").append(rid);
        sb.append(", prem=").append(prem);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
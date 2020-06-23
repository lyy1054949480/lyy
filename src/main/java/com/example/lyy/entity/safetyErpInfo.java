package com.example.lyy.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "safety_erp_info")
public class safetyErpInfo implements Serializable {
    /**
     * 企业信心编号
     */
    @Column(name = "erp_id")
    private String erpId;

    /**
     * 信用编码
     */
    @Column(name = "credit code")
    private String creditCode;

    /**
     * 企业性质  01国有  02个体/民营   03三资企业
     */
    @Column(name = "erp_nature")
    private String erpNature;

    /**
     * 企业类型  01旅行社  02景区 03旅游住宿企业  04文化娱乐场所 05客运企业  
     */
    @Column(name = "erp_type")
    private String erpType;

    /**
     * 景区等级
     */
    @Column(name = "erp_rank")
    private String erpRank;

    /**
     * 景区类型
     */
    @Column(name = "scenic_spot_type")
    private String scenicSpotType;

    /**
     * 全年累计人数
     */
    @Column(name = "annual_amount")
    private String annualAmount;

    /**
     * 高峰时间段
     */
    @Column(name = "peak_period")
    private String peakPeriod;

    /**
     * 高峰期流量
     */
    @Column(name = "peak_traffic")
    private String peakTraffic;

    /**
     * 每天最大承载
     */
    @Column(name = "erp_capacity_day")
    private String erpCapacityDay;

    /**
     * 瞬时承载量
     */
    @Column(name = "erp_capacity_hour")
    private String erpCapacityHour;

    /**
     * 客户类型  01团   02散客
     */
    @Column(name = "client_type")
    private String clientType;

    /**
     * 客源类型比例
     */
    @Column(name = "client_type_ratio")
    private String clientTypeRatio;

    /**
     * 特殊人群  01老年人   02未成年
     */
    @Column(name = "special_crowd")
    private String specialCrowd;

    /**
     * 特殊人群比例
     */
    @Column(name = "special_crowd_ratio")
    private String specialCrowdRatio;

    private static final long serialVersionUID = 1L;

    /**
     * 获取企业信心编号
     *
     * @return erp_id - 企业信心编号
     */
    public String getErpId() {
        return erpId;
    }

    /**
     * 设置企业信心编号
     *
     * @param erpId 企业信心编号
     */
    public void setErpId(String erpId) {
        this.erpId = erpId == null ? null : erpId.trim();
    }

    /**
     * 获取信用编码
     *
     * @return credit code - 信用编码
     */
    public String getCreditCode() {
        return creditCode;
    }

    /**
     * 设置信用编码
     *
     * @param creditCode 信用编码
     */
    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode == null ? null : creditCode.trim();
    }

    /**
     * 获取企业性质  01国有  02个体/民营   03三资企业
     *
     * @return erp_nature - 企业性质  01国有  02个体/民营   03三资企业
     */
    public String getErpNature() {
        return erpNature;
    }

    /**
     * 设置企业性质  01国有  02个体/民营   03三资企业
     *
     * @param erpNature 企业性质  01国有  02个体/民营   03三资企业
     */
    public void setErpNature(String erpNature) {
        this.erpNature = erpNature == null ? null : erpNature.trim();
    }

    /**
     * 获取企业类型  01旅行社  02景区 03旅游住宿企业  04文化娱乐场所 05客运企业  
     *
     * @return erp_type - 企业类型  01旅行社  02景区 03旅游住宿企业  04文化娱乐场所 05客运企业  
     */
    public String getErpType() {
        return erpType;
    }

    /**
     * 设置企业类型  01旅行社  02景区 03旅游住宿企业  04文化娱乐场所 05客运企业  
     *
     * @param erpType 企业类型  01旅行社  02景区 03旅游住宿企业  04文化娱乐场所 05客运企业  
     */
    public void setErpType(String erpType) {
        this.erpType = erpType == null ? null : erpType.trim();
    }

    /**
     * 获取景区等级
     *
     * @return erp_rank - 景区等级
     */
    public String getErpRank() {
        return erpRank;
    }

    /**
     * 设置景区等级
     *
     * @param erpRank 景区等级
     */
    public void setErpRank(String erpRank) {
        this.erpRank = erpRank == null ? null : erpRank.trim();
    }

    /**
     * 获取景区类型
     *
     * @return scenic_spot_type - 景区类型
     */
    public String getScenicSpotType() {
        return scenicSpotType;
    }

    /**
     * 设置景区类型
     *
     * @param scenicSpotType 景区类型
     */
    public void setScenicSpotType(String scenicSpotType) {
        this.scenicSpotType = scenicSpotType == null ? null : scenicSpotType.trim();
    }

    /**
     * 获取全年累计人数
     *
     * @return annual_amount - 全年累计人数
     */
    public String getAnnualAmount() {
        return annualAmount;
    }

    /**
     * 设置全年累计人数
     *
     * @param annualAmount 全年累计人数
     */
    public void setAnnualAmount(String annualAmount) {
        this.annualAmount = annualAmount == null ? null : annualAmount.trim();
    }

    /**
     * 获取高峰时间段
     *
     * @return peak_period - 高峰时间段
     */
    public String getPeakPeriod() {
        return peakPeriod;
    }

    /**
     * 设置高峰时间段
     *
     * @param peakPeriod 高峰时间段
     */
    public void setPeakPeriod(String peakPeriod) {
        this.peakPeriod = peakPeriod == null ? null : peakPeriod.trim();
    }

    /**
     * 获取高峰期流量
     *
     * @return peak_traffic - 高峰期流量
     */
    public String getPeakTraffic() {
        return peakTraffic;
    }

    /**
     * 设置高峰期流量
     *
     * @param peakTraffic 高峰期流量
     */
    public void setPeakTraffic(String peakTraffic) {
        this.peakTraffic = peakTraffic == null ? null : peakTraffic.trim();
    }

    /**
     * 获取每天最大承载
     *
     * @return erp_capacity_day - 每天最大承载
     */
    public String getErpCapacityDay() {
        return erpCapacityDay;
    }

    /**
     * 设置每天最大承载
     *
     * @param erpCapacityDay 每天最大承载
     */
    public void setErpCapacityDay(String erpCapacityDay) {
        this.erpCapacityDay = erpCapacityDay == null ? null : erpCapacityDay.trim();
    }

    /**
     * 获取瞬时承载量
     *
     * @return erp_capacity_hour - 瞬时承载量
     */
    public String getErpCapacityHour() {
        return erpCapacityHour;
    }

    /**
     * 设置瞬时承载量
     *
     * @param erpCapacityHour 瞬时承载量
     */
    public void setErpCapacityHour(String erpCapacityHour) {
        this.erpCapacityHour = erpCapacityHour == null ? null : erpCapacityHour.trim();
    }

    /**
     * 获取客户类型  01团   02散客
     *
     * @return client_type - 客户类型  01团   02散客
     */
    public String getClientType() {
        return clientType;
    }

    /**
     * 设置客户类型  01团   02散客
     *
     * @param clientType 客户类型  01团   02散客
     */
    public void setClientType(String clientType) {
        this.clientType = clientType == null ? null : clientType.trim();
    }

    /**
     * 获取客源类型比例
     *
     * @return client_type_ratio - 客源类型比例
     */
    public String getClientTypeRatio() {
        return clientTypeRatio;
    }

    /**
     * 设置客源类型比例
     *
     * @param clientTypeRatio 客源类型比例
     */
    public void setClientTypeRatio(String clientTypeRatio) {
        this.clientTypeRatio = clientTypeRatio == null ? null : clientTypeRatio.trim();
    }

    /**
     * 获取特殊人群  01老年人   02未成年
     *
     * @return special_crowd - 特殊人群  01老年人   02未成年
     */
    public String getSpecialCrowd() {
        return specialCrowd;
    }

    /**
     * 设置特殊人群  01老年人   02未成年
     *
     * @param specialCrowd 特殊人群  01老年人   02未成年
     */
    public void setSpecialCrowd(String specialCrowd) {
        this.specialCrowd = specialCrowd == null ? null : specialCrowd.trim();
    }

    /**
     * 获取特殊人群比例
     *
     * @return special_crowd_ratio - 特殊人群比例
     */
    public String getSpecialCrowdRatio() {
        return specialCrowdRatio;
    }

    /**
     * 设置特殊人群比例
     *
     * @param specialCrowdRatio 特殊人群比例
     */
    public void setSpecialCrowdRatio(String specialCrowdRatio) {
        this.specialCrowdRatio = specialCrowdRatio == null ? null : specialCrowdRatio.trim();
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
        safetyErpInfo other = (safetyErpInfo) that;
        return (this.getErpId() == null ? other.getErpId() == null : this.getErpId().equals(other.getErpId()))
            && (this.getCreditCode() == null ? other.getCreditCode() == null : this.getCreditCode().equals(other.getCreditCode()))
            && (this.getErpNature() == null ? other.getErpNature() == null : this.getErpNature().equals(other.getErpNature()))
            && (this.getErpType() == null ? other.getErpType() == null : this.getErpType().equals(other.getErpType()))
            && (this.getErpRank() == null ? other.getErpRank() == null : this.getErpRank().equals(other.getErpRank()))
            && (this.getScenicSpotType() == null ? other.getScenicSpotType() == null : this.getScenicSpotType().equals(other.getScenicSpotType()))
            && (this.getAnnualAmount() == null ? other.getAnnualAmount() == null : this.getAnnualAmount().equals(other.getAnnualAmount()))
            && (this.getPeakPeriod() == null ? other.getPeakPeriod() == null : this.getPeakPeriod().equals(other.getPeakPeriod()))
            && (this.getPeakTraffic() == null ? other.getPeakTraffic() == null : this.getPeakTraffic().equals(other.getPeakTraffic()))
            && (this.getErpCapacityDay() == null ? other.getErpCapacityDay() == null : this.getErpCapacityDay().equals(other.getErpCapacityDay()))
            && (this.getErpCapacityHour() == null ? other.getErpCapacityHour() == null : this.getErpCapacityHour().equals(other.getErpCapacityHour()))
            && (this.getClientType() == null ? other.getClientType() == null : this.getClientType().equals(other.getClientType()))
            && (this.getClientTypeRatio() == null ? other.getClientTypeRatio() == null : this.getClientTypeRatio().equals(other.getClientTypeRatio()))
            && (this.getSpecialCrowd() == null ? other.getSpecialCrowd() == null : this.getSpecialCrowd().equals(other.getSpecialCrowd()))
            && (this.getSpecialCrowdRatio() == null ? other.getSpecialCrowdRatio() == null : this.getSpecialCrowdRatio().equals(other.getSpecialCrowdRatio()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getErpId() == null) ? 0 : getErpId().hashCode());
        result = prime * result + ((getCreditCode() == null) ? 0 : getCreditCode().hashCode());
        result = prime * result + ((getErpNature() == null) ? 0 : getErpNature().hashCode());
        result = prime * result + ((getErpType() == null) ? 0 : getErpType().hashCode());
        result = prime * result + ((getErpRank() == null) ? 0 : getErpRank().hashCode());
        result = prime * result + ((getScenicSpotType() == null) ? 0 : getScenicSpotType().hashCode());
        result = prime * result + ((getAnnualAmount() == null) ? 0 : getAnnualAmount().hashCode());
        result = prime * result + ((getPeakPeriod() == null) ? 0 : getPeakPeriod().hashCode());
        result = prime * result + ((getPeakTraffic() == null) ? 0 : getPeakTraffic().hashCode());
        result = prime * result + ((getErpCapacityDay() == null) ? 0 : getErpCapacityDay().hashCode());
        result = prime * result + ((getErpCapacityHour() == null) ? 0 : getErpCapacityHour().hashCode());
        result = prime * result + ((getClientType() == null) ? 0 : getClientType().hashCode());
        result = prime * result + ((getClientTypeRatio() == null) ? 0 : getClientTypeRatio().hashCode());
        result = prime * result + ((getSpecialCrowd() == null) ? 0 : getSpecialCrowd().hashCode());
        result = prime * result + ((getSpecialCrowdRatio() == null) ? 0 : getSpecialCrowdRatio().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", erpId=").append(erpId);
        sb.append(", creditCode=").append(creditCode);
        sb.append(", erpNature=").append(erpNature);
        sb.append(", erpType=").append(erpType);
        sb.append(", erpRank=").append(erpRank);
        sb.append(", scenicSpotType=").append(scenicSpotType);
        sb.append(", annualAmount=").append(annualAmount);
        sb.append(", peakPeriod=").append(peakPeriod);
        sb.append(", peakTraffic=").append(peakTraffic);
        sb.append(", erpCapacityDay=").append(erpCapacityDay);
        sb.append(", erpCapacityHour=").append(erpCapacityHour);
        sb.append(", clientType=").append(clientType);
        sb.append(", clientTypeRatio=").append(clientTypeRatio);
        sb.append(", specialCrowd=").append(specialCrowd);
        sb.append(", specialCrowdRatio=").append(specialCrowdRatio);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
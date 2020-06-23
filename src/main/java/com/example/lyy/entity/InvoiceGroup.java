package com.example.lyy.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "invoice_group")
public class InvoiceGroup implements Serializable {
    @Id
    private String id;

    @Column(name = "user_code")
    private String userCode;

    /**
     * 用户名称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 保险公司名称
     */
    @Column(name = "insurance_company_name")
    private String insuranceCompanyName;

    /**
     * 保险公司编码
     */
    @Column(name = "insurance_company_code")
    private String insuranceCompanyCode;

    /**
     * 投保时间
     */
    @Column(name = "app_start_date")
    private Date appStartDate;

    /**
     * 起保时间
     */
    @Column(name = "pol_start_date")
    private Date polStartDate;

    /**
     * 终保时间
     */
    @Column(name = "pol_end_date")
    private Date polEndDate;

    /**
     * 产品编码
     */
    @Column(name = "product_code")
    private String productCode;

    /**
     * 产品名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 一级分类
     */
    @Column(name = "product_category1")
    private String productCategory1;

    /**
     * 二级分类
     */
    @Column(name = "product_category2")
    private String productCategory2;

    /**
     * 三级分类
     */
    @Column(name = "product_category3")
    private String productCategory3;

    /**
     * 保单编号
     */
    @Column(name = "policy_no")
    private String policyNo;

    /**
     * 投保单号
     */
    @Column(name = "app_code")
    private String appCode;

    /**
     * 订单号
     */
    @Column(name = "order_number")
    private String orderNumber;

    /**
     * 保单保费
     */
    private BigDecimal premium;

    /**
     * 保单状态00在保 01退保  02过期
     */
    @Column(name = "policy_status")
    private String policyStatus;

    /**
     * 发票状态发票状态（00未开票 / 01已开票 /03开票中）
     */
    @Column(name = "invoice_status")
    private String invoiceStatus;

    /**
     * 纳税人编号
     */
    @Column(name = "taxpayer_no")
    private String taxpayerNo;

    /**
     * 发票抬头
     */
    @Column(name = "taxpayer_name")
    private String taxpayerName;

    /**
     * 联系人名称
     */
    @Column(name = "contact_name")
    private String contactName;

    /**
     * 联系人电话
     */
    @Column(name = "contact_phone")
    private String contactPhone;

    /**
     * 开户银行
     */
    @Column(name = "vat_bank")
    private String vatBank;

    /**
     * 开户账号
     */
    @Column(name = "vat_account")
    private String vatAccount;

    /**
     * 注册地址
     */
    @Column(name = "vat_address")
    private String vatAddress;

    /**
     * 注册电话
     */
    @Column(name = "vat_phone")
    private String vatPhone;

    /**
     * 联系人邮箱
     */
    @Column(name = "contact_email")
    private String contactEmail;

    /**
     * 接口编码
     */
    @Column(name = "if_code")
    private String ifCode;

    /**
     * 是否可以合并(1可以 ,0不可以 )
     */
    @Column(name = "if_merge")
    private String ifMerge;

    /**
     * 分公司编码
     */
    @Column(name = "branch_code")
    private String branchCode;

    /**
     * 分公司名称
     */
    @Column(name = "branch_name")
    private String branchName;

    /**
     * 旅游目的地
     */
    @Column(name = "travel_team")
    private String travelTeam;

    /**
     * 旅游编号
     */
    @Column(name = "travel_no")
    private Integer travelNo;

    /**
     * 渠道
     */
    private String channel;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * @return user_code
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * @param userCode
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * 获取用户名称
     *
     * @return user_name - 用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名称
     *
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取保险公司名称
     *
     * @return insurance_company_name - 保险公司名称
     */
    public String getInsuranceCompanyName() {
        return insuranceCompanyName;
    }

    /**
     * 设置保险公司名称
     *
     * @param insuranceCompanyName 保险公司名称
     */
    public void setInsuranceCompanyName(String insuranceCompanyName) {
        this.insuranceCompanyName = insuranceCompanyName == null ? null : insuranceCompanyName.trim();
    }

    /**
     * 获取保险公司编码
     *
     * @return insurance_company_code - 保险公司编码
     */
    public String getInsuranceCompanyCode() {
        return insuranceCompanyCode;
    }

    /**
     * 设置保险公司编码
     *
     * @param insuranceCompanyCode 保险公司编码
     */
    public void setInsuranceCompanyCode(String insuranceCompanyCode) {
        this.insuranceCompanyCode = insuranceCompanyCode == null ? null : insuranceCompanyCode.trim();
    }

    /**
     * 获取投保时间
     *
     * @return app_start_date - 投保时间
     */
    public Date getAppStartDate() {
        return appStartDate;
    }

    /**
     * 设置投保时间
     *
     * @param appStartDate 投保时间
     */
    public void setAppStartDate(Date appStartDate) {
        this.appStartDate = appStartDate;
    }

    /**
     * 获取起保时间
     *
     * @return pol_start_date - 起保时间
     */
    public Date getPolStartDate() {
        return polStartDate;
    }

    /**
     * 设置起保时间
     *
     * @param polStartDate 起保时间
     */
    public void setPolStartDate(Date polStartDate) {
        this.polStartDate = polStartDate;
    }

    /**
     * 获取终保时间
     *
     * @return pol_end_date - 终保时间
     */
    public Date getPolEndDate() {
        return polEndDate;
    }

    /**
     * 设置终保时间
     *
     * @param polEndDate 终保时间
     */
    public void setPolEndDate(Date polEndDate) {
        this.polEndDate = polEndDate;
    }

    /**
     * 获取产品编码
     *
     * @return product_code - 产品编码
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 设置产品编码
     *
     * @param productCode 产品编码
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    /**
     * 获取产品名称
     *
     * @return product_name - 产品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置产品名称
     *
     * @param productName 产品名称
     */
    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    /**
     * 获取一级分类
     *
     * @return product_category1 - 一级分类
     */
    public String getProductCategory1() {
        return productCategory1;
    }

    /**
     * 设置一级分类
     *
     * @param productCategory1 一级分类
     */
    public void setProductCategory1(String productCategory1) {
        this.productCategory1 = productCategory1 == null ? null : productCategory1.trim();
    }

    /**
     * 获取二级分类
     *
     * @return product_category2 - 二级分类
     */
    public String getProductCategory2() {
        return productCategory2;
    }

    /**
     * 设置二级分类
     *
     * @param productCategory2 二级分类
     */
    public void setProductCategory2(String productCategory2) {
        this.productCategory2 = productCategory2 == null ? null : productCategory2.trim();
    }

    /**
     * 获取三级分类
     *
     * @return product_category3 - 三级分类
     */
    public String getProductCategory3() {
        return productCategory3;
    }

    /**
     * 设置三级分类
     *
     * @param productCategory3 三级分类
     */
    public void setProductCategory3(String productCategory3) {
        this.productCategory3 = productCategory3 == null ? null : productCategory3.trim();
    }

    /**
     * 获取保单编号
     *
     * @return policy_no - 保单编号
     */
    public String getPolicyNo() {
        return policyNo;
    }

    /**
     * 设置保单编号
     *
     * @param policyNo 保单编号
     */
    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo == null ? null : policyNo.trim();
    }

    /**
     * 获取投保单号
     *
     * @return app_code - 投保单号
     */
    public String getAppCode() {
        return appCode;
    }

    /**
     * 设置投保单号
     *
     * @param appCode 投保单号
     */
    public void setAppCode(String appCode) {
        this.appCode = appCode == null ? null : appCode.trim();
    }

    /**
     * 获取订单号
     *
     * @return order_number - 订单号
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * 设置订单号
     *
     * @param orderNumber 订单号
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    /**
     * 获取保单保费
     *
     * @return premium - 保单保费
     */
    public BigDecimal getPremium() {
        return premium;
    }

    /**
     * 设置保单保费
     *
     * @param premium 保单保费
     */
    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    /**
     * 获取保单状态00在保 01退保  02过期
     *
     * @return policy_status - 保单状态00在保 01退保  02过期
     */
    public String getPolicyStatus() {
        return policyStatus;
    }

    /**
     * 设置保单状态00在保 01退保  02过期
     *
     * @param policyStatus 保单状态00在保 01退保  02过期
     */
    public void setPolicyStatus(String policyStatus) {
        this.policyStatus = policyStatus == null ? null : policyStatus.trim();
    }

    /**
     * 获取发票状态发票状态（00未开票 / 01已开票 /03开票中）
     *
     * @return invoice_status - 发票状态发票状态（00未开票 / 01已开票 /03开票中）
     */
    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    /**
     * 设置发票状态发票状态（00未开票 / 01已开票 /03开票中）
     *
     * @param invoiceStatus 发票状态发票状态（00未开票 / 01已开票 /03开票中）
     */
    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus == null ? null : invoiceStatus.trim();
    }

    /**
     * 获取纳税人编号
     *
     * @return taxpayer_no - 纳税人编号
     */
    public String getTaxpayerNo() {
        return taxpayerNo;
    }

    /**
     * 设置纳税人编号
     *
     * @param taxpayerNo 纳税人编号
     */
    public void setTaxpayerNo(String taxpayerNo) {
        this.taxpayerNo = taxpayerNo == null ? null : taxpayerNo.trim();
    }

    /**
     * 获取发票抬头
     *
     * @return taxpayer_name - 发票抬头
     */
    public String getTaxpayerName() {
        return taxpayerName;
    }

    /**
     * 设置发票抬头
     *
     * @param taxpayerName 发票抬头
     */
    public void setTaxpayerName(String taxpayerName) {
        this.taxpayerName = taxpayerName == null ? null : taxpayerName.trim();
    }

    /**
     * 获取联系人名称
     *
     * @return contact_name - 联系人名称
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * 设置联系人名称
     *
     * @param contactName 联系人名称
     */
    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    /**
     * 获取联系人电话
     *
     * @return contact_phone - 联系人电话
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * 设置联系人电话
     *
     * @param contactPhone 联系人电话
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    /**
     * 获取开户银行
     *
     * @return vat_bank - 开户银行
     */
    public String getVatBank() {
        return vatBank;
    }

    /**
     * 设置开户银行
     *
     * @param vatBank 开户银行
     */
    public void setVatBank(String vatBank) {
        this.vatBank = vatBank == null ? null : vatBank.trim();
    }

    /**
     * 获取开户账号
     *
     * @return vat_account - 开户账号
     */
    public String getVatAccount() {
        return vatAccount;
    }

    /**
     * 设置开户账号
     *
     * @param vatAccount 开户账号
     */
    public void setVatAccount(String vatAccount) {
        this.vatAccount = vatAccount == null ? null : vatAccount.trim();
    }

    /**
     * 获取注册地址
     *
     * @return vat_address - 注册地址
     */
    public String getVatAddress() {
        return vatAddress;
    }

    /**
     * 设置注册地址
     *
     * @param vatAddress 注册地址
     */
    public void setVatAddress(String vatAddress) {
        this.vatAddress = vatAddress == null ? null : vatAddress.trim();
    }

    /**
     * 获取注册电话
     *
     * @return vat_phone - 注册电话
     */
    public String getVatPhone() {
        return vatPhone;
    }

    /**
     * 设置注册电话
     *
     * @param vatPhone 注册电话
     */
    public void setVatPhone(String vatPhone) {
        this.vatPhone = vatPhone == null ? null : vatPhone.trim();
    }

    /**
     * 获取联系人邮箱
     *
     * @return contact_email - 联系人邮箱
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * 设置联系人邮箱
     *
     * @param contactEmail 联系人邮箱
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail == null ? null : contactEmail.trim();
    }

    /**
     * 获取接口编码
     *
     * @return if_code - 接口编码
     */
    public String getIfCode() {
        return ifCode;
    }

    /**
     * 设置接口编码
     *
     * @param ifCode 接口编码
     */
    public void setIfCode(String ifCode) {
        this.ifCode = ifCode == null ? null : ifCode.trim();
    }

    /**
     * 获取是否可以合并(1可以 ,0不可以 )
     *
     * @return if_merge - 是否可以合并(1可以 ,0不可以 )
     */
    public String getIfMerge() {
        return ifMerge;
    }

    /**
     * 设置是否可以合并(1可以 ,0不可以 )
     *
     * @param ifMerge 是否可以合并(1可以 ,0不可以 )
     */
    public void setIfMerge(String ifMerge) {
        this.ifMerge = ifMerge == null ? null : ifMerge.trim();
    }

    /**
     * 获取分公司编码
     *
     * @return branch_code - 分公司编码
     */
    public String getBranchCode() {
        return branchCode;
    }

    /**
     * 设置分公司编码
     *
     * @param branchCode 分公司编码
     */
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode == null ? null : branchCode.trim();
    }

    /**
     * 获取分公司名称
     *
     * @return branch_name - 分公司名称
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * 设置分公司名称
     *
     * @param branchName 分公司名称
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName == null ? null : branchName.trim();
    }

    /**
     * 获取旅游目的地
     *
     * @return travel_team - 旅游目的地
     */
    public String getTravelTeam() {
        return travelTeam;
    }

    /**
     * 设置旅游目的地
     *
     * @param travelTeam 旅游目的地
     */
    public void setTravelTeam(String travelTeam) {
        this.travelTeam = travelTeam == null ? null : travelTeam.trim();
    }

    /**
     * 获取旅游编号
     *
     * @return travel_no - 旅游编号
     */
    public Integer getTravelNo() {
        return travelNo;
    }

    /**
     * 设置旅游编号
     *
     * @param travelNo 旅游编号
     */
    public void setTravelNo(Integer travelNo) {
        this.travelNo = travelNo;
    }

    /**
     * 获取渠道
     *
     * @return channel - 渠道
     */
    public String getChannel() {
        return channel;
    }

    /**
     * 设置渠道
     *
     * @param channel 渠道
     */
    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
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
        InvoiceGroup other = (InvoiceGroup) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserCode() == null ? other.getUserCode() == null : this.getUserCode().equals(other.getUserCode()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getInsuranceCompanyName() == null ? other.getInsuranceCompanyName() == null : this.getInsuranceCompanyName().equals(other.getInsuranceCompanyName()))
            && (this.getInsuranceCompanyCode() == null ? other.getInsuranceCompanyCode() == null : this.getInsuranceCompanyCode().equals(other.getInsuranceCompanyCode()))
            && (this.getAppStartDate() == null ? other.getAppStartDate() == null : this.getAppStartDate().equals(other.getAppStartDate()))
            && (this.getPolStartDate() == null ? other.getPolStartDate() == null : this.getPolStartDate().equals(other.getPolStartDate()))
            && (this.getPolEndDate() == null ? other.getPolEndDate() == null : this.getPolEndDate().equals(other.getPolEndDate()))
            && (this.getProductCode() == null ? other.getProductCode() == null : this.getProductCode().equals(other.getProductCode()))
            && (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName()))
            && (this.getProductCategory1() == null ? other.getProductCategory1() == null : this.getProductCategory1().equals(other.getProductCategory1()))
            && (this.getProductCategory2() == null ? other.getProductCategory2() == null : this.getProductCategory2().equals(other.getProductCategory2()))
            && (this.getProductCategory3() == null ? other.getProductCategory3() == null : this.getProductCategory3().equals(other.getProductCategory3()))
            && (this.getPolicyNo() == null ? other.getPolicyNo() == null : this.getPolicyNo().equals(other.getPolicyNo()))
            && (this.getAppCode() == null ? other.getAppCode() == null : this.getAppCode().equals(other.getAppCode()))
            && (this.getOrderNumber() == null ? other.getOrderNumber() == null : this.getOrderNumber().equals(other.getOrderNumber()))
            && (this.getPremium() == null ? other.getPremium() == null : this.getPremium().equals(other.getPremium()))
            && (this.getPolicyStatus() == null ? other.getPolicyStatus() == null : this.getPolicyStatus().equals(other.getPolicyStatus()))
            && (this.getInvoiceStatus() == null ? other.getInvoiceStatus() == null : this.getInvoiceStatus().equals(other.getInvoiceStatus()))
            && (this.getTaxpayerNo() == null ? other.getTaxpayerNo() == null : this.getTaxpayerNo().equals(other.getTaxpayerNo()))
            && (this.getTaxpayerName() == null ? other.getTaxpayerName() == null : this.getTaxpayerName().equals(other.getTaxpayerName()))
            && (this.getContactName() == null ? other.getContactName() == null : this.getContactName().equals(other.getContactName()))
            && (this.getContactPhone() == null ? other.getContactPhone() == null : this.getContactPhone().equals(other.getContactPhone()))
            && (this.getVatBank() == null ? other.getVatBank() == null : this.getVatBank().equals(other.getVatBank()))
            && (this.getVatAccount() == null ? other.getVatAccount() == null : this.getVatAccount().equals(other.getVatAccount()))
            && (this.getVatAddress() == null ? other.getVatAddress() == null : this.getVatAddress().equals(other.getVatAddress()))
            && (this.getVatPhone() == null ? other.getVatPhone() == null : this.getVatPhone().equals(other.getVatPhone()))
            && (this.getContactEmail() == null ? other.getContactEmail() == null : this.getContactEmail().equals(other.getContactEmail()))
            && (this.getIfCode() == null ? other.getIfCode() == null : this.getIfCode().equals(other.getIfCode()))
            && (this.getIfMerge() == null ? other.getIfMerge() == null : this.getIfMerge().equals(other.getIfMerge()))
            && (this.getBranchCode() == null ? other.getBranchCode() == null : this.getBranchCode().equals(other.getBranchCode()))
            && (this.getBranchName() == null ? other.getBranchName() == null : this.getBranchName().equals(other.getBranchName()))
            && (this.getTravelTeam() == null ? other.getTravelTeam() == null : this.getTravelTeam().equals(other.getTravelTeam()))
            && (this.getTravelNo() == null ? other.getTravelNo() == null : this.getTravelNo().equals(other.getTravelNo()))
            && (this.getChannel() == null ? other.getChannel() == null : this.getChannel().equals(other.getChannel()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserCode() == null) ? 0 : getUserCode().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getInsuranceCompanyName() == null) ? 0 : getInsuranceCompanyName().hashCode());
        result = prime * result + ((getInsuranceCompanyCode() == null) ? 0 : getInsuranceCompanyCode().hashCode());
        result = prime * result + ((getAppStartDate() == null) ? 0 : getAppStartDate().hashCode());
        result = prime * result + ((getPolStartDate() == null) ? 0 : getPolStartDate().hashCode());
        result = prime * result + ((getPolEndDate() == null) ? 0 : getPolEndDate().hashCode());
        result = prime * result + ((getProductCode() == null) ? 0 : getProductCode().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getProductCategory1() == null) ? 0 : getProductCategory1().hashCode());
        result = prime * result + ((getProductCategory2() == null) ? 0 : getProductCategory2().hashCode());
        result = prime * result + ((getProductCategory3() == null) ? 0 : getProductCategory3().hashCode());
        result = prime * result + ((getPolicyNo() == null) ? 0 : getPolicyNo().hashCode());
        result = prime * result + ((getAppCode() == null) ? 0 : getAppCode().hashCode());
        result = prime * result + ((getOrderNumber() == null) ? 0 : getOrderNumber().hashCode());
        result = prime * result + ((getPremium() == null) ? 0 : getPremium().hashCode());
        result = prime * result + ((getPolicyStatus() == null) ? 0 : getPolicyStatus().hashCode());
        result = prime * result + ((getInvoiceStatus() == null) ? 0 : getInvoiceStatus().hashCode());
        result = prime * result + ((getTaxpayerNo() == null) ? 0 : getTaxpayerNo().hashCode());
        result = prime * result + ((getTaxpayerName() == null) ? 0 : getTaxpayerName().hashCode());
        result = prime * result + ((getContactName() == null) ? 0 : getContactName().hashCode());
        result = prime * result + ((getContactPhone() == null) ? 0 : getContactPhone().hashCode());
        result = prime * result + ((getVatBank() == null) ? 0 : getVatBank().hashCode());
        result = prime * result + ((getVatAccount() == null) ? 0 : getVatAccount().hashCode());
        result = prime * result + ((getVatAddress() == null) ? 0 : getVatAddress().hashCode());
        result = prime * result + ((getVatPhone() == null) ? 0 : getVatPhone().hashCode());
        result = prime * result + ((getContactEmail() == null) ? 0 : getContactEmail().hashCode());
        result = prime * result + ((getIfCode() == null) ? 0 : getIfCode().hashCode());
        result = prime * result + ((getIfMerge() == null) ? 0 : getIfMerge().hashCode());
        result = prime * result + ((getBranchCode() == null) ? 0 : getBranchCode().hashCode());
        result = prime * result + ((getBranchName() == null) ? 0 : getBranchName().hashCode());
        result = prime * result + ((getTravelTeam() == null) ? 0 : getTravelTeam().hashCode());
        result = prime * result + ((getTravelNo() == null) ? 0 : getTravelNo().hashCode());
        result = prime * result + ((getChannel() == null) ? 0 : getChannel().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userCode=").append(userCode);
        sb.append(", userName=").append(userName);
        sb.append(", insuranceCompanyName=").append(insuranceCompanyName);
        sb.append(", insuranceCompanyCode=").append(insuranceCompanyCode);
        sb.append(", appStartDate=").append(appStartDate);
        sb.append(", polStartDate=").append(polStartDate);
        sb.append(", polEndDate=").append(polEndDate);
        sb.append(", productCode=").append(productCode);
        sb.append(", productName=").append(productName);
        sb.append(", productCategory1=").append(productCategory1);
        sb.append(", productCategory2=").append(productCategory2);
        sb.append(", productCategory3=").append(productCategory3);
        sb.append(", policyNo=").append(policyNo);
        sb.append(", appCode=").append(appCode);
        sb.append(", orderNumber=").append(orderNumber);
        sb.append(", premium=").append(premium);
        sb.append(", policyStatus=").append(policyStatus);
        sb.append(", invoiceStatus=").append(invoiceStatus);
        sb.append(", taxpayerNo=").append(taxpayerNo);
        sb.append(", taxpayerName=").append(taxpayerName);
        sb.append(", contactName=").append(contactName);
        sb.append(", contactPhone=").append(contactPhone);
        sb.append(", vatBank=").append(vatBank);
        sb.append(", vatAccount=").append(vatAccount);
        sb.append(", vatAddress=").append(vatAddress);
        sb.append(", vatPhone=").append(vatPhone);
        sb.append(", contactEmail=").append(contactEmail);
        sb.append(", ifCode=").append(ifCode);
        sb.append(", ifMerge=").append(ifMerge);
        sb.append(", branchCode=").append(branchCode);
        sb.append(", branchName=").append(branchName);
        sb.append(", travelTeam=").append(travelTeam);
        sb.append(", travelNo=").append(travelNo);
        sb.append(", channel=").append(channel);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
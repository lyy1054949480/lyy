package com.example.lyy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "旅游渠道, 投保单列表, 查询条件, 封装实体")
public class TourApplicationFormQo {
    @ApiModelProperty(value = "投保单状态, 支持多个以逗号隔开.（待初审（00）/初审不通过（01）/待复审（02）/复审不通过（03）/延期凭证待上传（04）/延期凭证待审核（05）/延期拼装审核不通过（06）/待关联（07）/待出单（08）/已出单（10）/已取消(11)/已删除(12)/投保单盖章审核不通过(13)）")
    private String appStatus;
    @ApiModelProperty(value = "订单状态, 支持多个以逗号隔开.待支付（01）、 部分支付（02）、 支付完成（03）、 已完成（04）、 已过期（05）、 待核保（06）、 核保失败（07）、 支付失败（08）已取消(09) 已删除(10) 支付中(11)")
    private String orderStatus;
    @ApiModelProperty(value = "财务状态, 支持多个以逗号隔开.（00未到账/01已到账/02部分到账）")
    private String financeStatus;
    @ApiModelProperty(value = "出单状态  末出单（n） 已出单（y） ")
    private String issueStatus;
    @ApiModelProperty(value = "发票状态（00未开票 / 01已开票）")
    private String invoiceStatus;
    @ApiModelProperty(value = "保单状态, 支持多个以逗号隔开.（00生效/01终止/02失效）")
    private String policyStatus;

    @ApiModelProperty(value = "投保开始时间, 查询开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date appStartDateFrom;
    @ApiModelProperty(value = "投保开始时间, 查询结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date appStartDateEnd;
    @ApiModelProperty(value = "保单开始时间, 查询开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date polStartDateFrom;
    @ApiModelProperty(value = "保单开始时间, 查询结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date polStartDateEnd;
    @ApiModelProperty(value = "出单时间, 查询开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date issueDateFrom;
    @ApiModelProperty(value = "出单时间, 查询结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date issueDateEnd;

    @ApiModelProperty(value = "投保单号")
    private String appCode;
    @ApiModelProperty(value = "订单号")
    private String orderCode;
    @ApiModelProperty(value = "保单号")
    private String policyNo;

    @ApiModelProperty(value = "被保人姓名")
    private String insuredName;
    @ApiModelProperty(value = "被保人身份证号")
    private String insuredLicenseNo;
    @ApiModelProperty(value = "被保人联系电话")
    private String insuredLinkTel;
    @ApiModelProperty(value = "投保人姓名")
    private String holderName;
    @ApiModelProperty(value = "投保人身份证号")
    private String holderLicenseNo;
    @ApiModelProperty(value = "投保人联系电话")
    private String holderLinkTel;

    @ApiModelProperty(value = "投保年份")
    private String insureYear;
    @ApiModelProperty(value = "机构编码")
    private String branchCode;
    @ApiModelProperty(value = "出单公司")
    private String insuranceCompany;
    @ApiModelProperty(value = "支付类型, 在线支付（00）、线下转账支付（01）、支付凭证（02）")
    private String payType;
    @ApiModelProperty(value = "产品编码, 支持多个, 用逗号连接")
    private String productCode;
    @ApiModelProperty(value = "产品名称")
    private String productName;
    @ApiModelProperty(value = "服务人员编码")
    private String agentCode;
    @ApiModelProperty(value = "服务人员姓名")
    private String agentName;
    @ApiModelProperty(value = "保单类型 电子保单（00）/纸质保单（01）")
    private String policyKind;
    @ApiModelProperty(value = "发票类型 电子发票（10）/纸质发票（11）")
    private String invoiceKind;

    @ApiModelProperty(value = "机构编码, 支持多个, 以逗号隔开")
    private String branchScope;
    @ApiModelProperty(value = "渠道编码, 支持多个, 以逗号隔开")
    private String channelScope;
    @ApiModelProperty(value = "用户编码")
    private String userCode;
    @ApiModelProperty(value = "页码")
    private Integer pn;
    @ApiModelProperty(value = "页幅")
    private Integer ps;
}

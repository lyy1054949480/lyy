package com.example.lyy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "旅游渠道, 投保单列表返回字段, 封装实体")
public class TourApplicationFormVO {
    @ApiModelProperty(value = "订单号")
    private String orderCode;
    @ApiModelProperty(value = "投保单号")
    private String appCode;
    @ApiModelProperty(value = "保单号")
    private String policyNo;
    @ApiModelProperty(value = "产品名称")
    private String productName;
    @ApiModelProperty(value = "产品编码")
    private String productCode;
    @ApiModelProperty(value = "机构名称")
    private String branchName;
    @ApiModelProperty(value = "机构编码")
    private String branchCode;
    @ApiModelProperty(value = "公司名称")
    private String companyName;
    @ApiModelProperty(value = "投保人姓名")
    private String holderName;
    @ApiModelProperty(value = "投保人编码")
    private String holderCode;
    @ApiModelProperty(value = "投保时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date applicationStartDate;
    @ApiModelProperty(value = "出单时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date issueDate;
    @ApiModelProperty(value = "保险起期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date polStartDate;
    @ApiModelProperty(value = "保险止期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date polEndDate;
    @ApiModelProperty(value = "支付时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date payDate;
    @ApiModelProperty(value = "省编码")
    private String provinceCode;
    @ApiModelProperty(value = "省名称")
    private String provinceName;
    @ApiModelProperty(value = "城市编码")
    private String cityCode;
    @ApiModelProperty(value = "城市名称")
    private String cityName;
    @ApiModelProperty(value = "区县编码")
    private String countyCode;
    @ApiModelProperty(value = "区县名称")
    private String countyName;
    @ApiModelProperty(value = "服务人员姓名")
    private String agentName;
    @ApiModelProperty(value = "服务人员编码")
    private String agentCode;
    @ApiModelProperty(value = "投保单金额")
    private BigDecimal prem;
    @ApiModelProperty(value = "优惠后金额")
    private BigDecimal afterPrem;
    @ApiModelProperty(value = "实际支付金额")
    private BigDecimal acturalPrem;
    @ApiModelProperty(value = "支付类型")
    private String payType;
    @ApiModelProperty(value = "投保单状态")
    private String applicationStatus;
    @ApiModelProperty(value = "订单状态")
    private String orderStatus;
    @ApiModelProperty(value = "保单状态")
    private String policyStatus;
    @ApiModelProperty(value = "财务状态")
    private String financeStatus;
    @ApiModelProperty(value = "出单状态")
    private String issueStatus;
    @ApiModelProperty(value = "发票状态")
    private String invoiceStatus;
    @ApiModelProperty(value = "被保人数量")
    private Integer insuredCount;
    @ApiModelProperty(value = "旅行社数量")
    private Integer agencyCount;
    @ApiModelProperty(value = "旅游路线")
    private String travelRoute;
    @ApiModelProperty(value = "旅行团编号")
    private String travelTeamNo;

}

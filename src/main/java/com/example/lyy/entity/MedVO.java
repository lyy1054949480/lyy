package com.example.lyy.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "医疗渠道, 用户端, 查询订单列表对应的查询返回结果封装实体.")
public class MedVO {
    @ApiModelProperty(value = "医院名称")
    private String medName;
    @ApiModelProperty(value = "省编码")
    private String provinceCode;
    @ApiModelProperty(value = "保费")
    private String afterPrem;
    @ApiModelProperty(value = "市编码")
    private String cityCode;
    @ApiModelProperty(value = "县编码")
    private String countyCode;
    @ApiModelProperty(value = "签单日期")
    private String issueDate;
    @ApiModelProperty(value = "医疗机构等级")
    private String medLevel;
    @ApiModelProperty(value = "医疗机构类型")
    private String medType;
    @ApiModelProperty(value = "医生数")
    private String yount;
    @ApiModelProperty(value = "护士数")
    private String hcount;
    @ApiModelProperty(value = "床位数")
    private String ccount;
    @ApiModelProperty(value = "诊疗人次数")
    private String zl;
    @ApiModelProperty(value = "年住院人次")
    private String zy;
    @ApiModelProperty(value = "年手术人次")
    private String ss;
}

package com.example.lyy.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * @Author: cjw
 * @Description: 公共更新入参
 * @Date: 9:26 2019/10/21
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@ApiModel(description = "公共更新入参")
public class CommonUpdateQo {

    @ApiModelProperty(value = "投保单号")
    private String appCode;

    @ApiModelProperty(value = "操作人")
    private String userCode = "19UC1732892602427261721";

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "类型")
    private List<CommonUpdateDetailQo> updateDetailQoList;

}

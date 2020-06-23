package com.example.lyy.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @Author: cjw
 * @Description: 公共更新明细
 * @Date: 10:28 2019/10/22
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@ApiModel(description = "公共更新明细入参")
public class CommonUpdateDetailQo {

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "新值")
    private String newValue;

}

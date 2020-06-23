package com.example.lyy.domain;

import com.example.lyy.entity.Json;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "日期")
public class ProByDateVO {

    @ApiModelProperty(value = "集合")
    private List<Json> dataList;
}

package com.example.lyy.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.example.lyy.util.ecxel.ExcelVOAttribute;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class User implements Serializable {


    @ApiModelProperty("用户名")
    @ExcelVOAttribute(name = "用户", column = "A")
    private String username;

    @ApiModelProperty("密码")
    @ExcelVOAttribute(name = "批复状态", column = "B", map = {"1111:提现审核", "2222:提现成功", "3333:提现失败"})
    private String password;

    @ApiModelProperty("地址集合")
    private List<Address> addressList;

    @ApiModelProperty("地址")
    private Address address;

    @ApiModelProperty("手机号集合")
    private List<Phone> phoneList;


    @ApiModelProperty("日期")
    @JSONField(format="yyyy-MM-dd")
    private String date;

}

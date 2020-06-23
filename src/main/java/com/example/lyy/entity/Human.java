package com.example.lyy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Human {

    private String sex;

    private String age;

    private String weight;

    private BigDecimal salary;

    public Boolean isMale(){
        return "00".equals(this.sex);
    }


}

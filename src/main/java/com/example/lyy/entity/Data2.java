package com.example.lyy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Data2 implements Serializable {
    @Column(name = "app_code2")
    @Id
    private String appCode2;

    @Column(name = "policy_no2")
    private String policyNo2;

    private BigDecimal prem2;

}
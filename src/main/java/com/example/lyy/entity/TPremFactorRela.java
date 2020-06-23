package com.example.lyy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_prem_factor_context_rela_def")
public class TPremFactorRela implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "channel_code")
    private String channelCode;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "factor_code")
    private String factorCode;

    @Column(name = "fullpath_element")
    private String fullpathElement;

    @Column(name = "risk_code")
    private String riskCode;



}
package com.example.lyy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "t_biz_factor_def")
public class TBizEntryRela implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "channel_code")
    private String channelCode;

    private String entry;

    @Column(name = "product_code")
    private String productCode;

    private String kind;

    private String source;



}
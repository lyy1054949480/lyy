package com.example.lyy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "t_underwriting_attr_def")
public class TUnderWriting implements Serializable {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "channel_code")
    private String channelCode;

    private String attribute;

    @Column(name = "attribute_value")
    private String attributeValue;

    private String note;

}
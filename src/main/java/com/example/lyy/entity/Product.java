package com.example.lyy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class Product implements Serializable {
    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_id")
    private String productId;


}
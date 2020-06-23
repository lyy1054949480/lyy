package com.example.lyy.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "t_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TOrder implements Serializable {
    @Id
    @Column(name = "order_id")
    @ApiModelProperty(value = "ssssss")
    private String orderId;

    @Column(name = "context_msg")
    private String contextMsg;

    private String operator;


}
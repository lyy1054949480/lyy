package com.example.lyy.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AreaCode {

    private String id;

    private String code;

    private String name;

    private String pid;

    private Integer count;
}

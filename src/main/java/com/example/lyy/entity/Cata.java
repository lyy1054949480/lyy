package com.example.lyy.entity;


import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class Cata {
    private String name;
    private String info;
    private String cata;
    private Boolean bool;


    private List<Detail> details;
}

package com.example.lyy.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Detail {

    private String info;

    private List<Option> list;
}

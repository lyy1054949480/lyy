package com.example.lyy.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Option {

    List<String> options;
}

package com.example.lyy.demo.jsonpath;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateContextEntity {

    private String appCode;

    private String uniqueId;

    private String type;

    private String  value;

    private String updatePathl;
}

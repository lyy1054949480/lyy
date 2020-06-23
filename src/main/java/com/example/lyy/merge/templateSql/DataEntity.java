package com.example.lyy.merge.templateSql;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataEntity {

    private LinkedList<String> columnName;

    private LinkedList<Object> columnValue;
}

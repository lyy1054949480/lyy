package com.example.lyy.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UpdateDetailQoListBean {
        /**
         * newValue : 2021-06-11 23:59:59
         * type : 01
         */

        private Object newValue;
        private String type;


}
package com.example.lyy.demo;


import com.example.lyy.util.auxiliary.AesUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class Image {
    private String name;
    private String data;
    private String size;


    public void setName(String name) {
        this.name = AesUtil.encrypt(name);
    }
}

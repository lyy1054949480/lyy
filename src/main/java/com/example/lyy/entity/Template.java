package com.example.lyy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "my_template")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Template implements Serializable {
    @Id
    private String id;

    @Column(name = "test_content")
    private byte[] testContent;

}
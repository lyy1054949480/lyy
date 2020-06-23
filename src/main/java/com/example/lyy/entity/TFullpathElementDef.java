package com.example.lyy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "t_fullpath_element_def")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TFullpathElementDef implements Serializable {
    @Id
    private Integer id;

    @Column(name = "json_item_path")
    private String jsonItemPath;

    @Column(name = "parent_path")
    private String parentPath;

    /**
     * 字段类型
主字段（00）/扩展字段（01）
     */
    @Column(name = "col_type")
    private String colType;

    /**
     * 值类型
字符串（00）、数组（01）
     */
    @Column(name = "value_type")
    private String valueType;

    /**
     * 对应上下文属性
     */
    @Column(name = "fullpath_element")
    private String fullpathElement;

    private String introduce;

    @Column(name = "isMust")
    private String ismust;


}
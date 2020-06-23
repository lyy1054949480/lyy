package com.example.lyy.util.tree;

/**
 * @ProjectName: JavaUtilsProject
 * @Package: com.sl.utils
 * @ClassName: entity
 * @Author: zc1997
 * @Description:
 * @Date: 2019/12/31 9:30
 * @Version: 1.0
 */
//import com.baomidou.mybatisplus.annotation.TableField;
//import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

//import lombok.EqualsAndHashCode;

/**
 * 标题模板 TitleTemplateEntity
 *
 * @author hcbf
 * @version 1.0
 * @date 2019-12-25 11:04:20
 * @modifiedBy
 */
@Data
//@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
//@TableName("title_template")
@Builder
public class TitleTemplateEntity  {


    /**
     * 标题
     */
//    @TableField("TITLE")
    private String title;

    /**
     * 标题级别：1-1级表头，2-2级标题以此类推
     */
//    @TableField("TITLE_LEVEL")
    private Integer titleLevel;

    /**
     * 父标题id
     */
//    @TableField("PID")
    private Long pid;
    /**
     * 模板排序，排序规则：增序
     */
//    @TableField("ORDER_BY")
    protected Integer orderBy;
    /**
     * 报告类型：1-常规报告，2-住宿业接待专项报告，3-客流量分析专项报告
     */
//    @TableField("REPORT_TYPE")
    private Integer reportType;

    /**
     * 报告时间类型：1-年度，2-季度，3-月份，4-节假日
     */
//    @TableField("REPORT_DATE_TYPE")
    private Integer reportDateType;

    /**
     * 地区等级(3-区县，4-地市，5-省)
     */
//    @TableField("AREA_LEVEL")
    private Integer areaLevel;

    /**
     * 启用状态：0-未启用，1-启用
     */
//    @TableField("ENABLE")
    private Integer enable;

//    @TableId(type = IdType.AUTO)
    protected Long id;

//    @TableCreateTimeField
//    @TableField("create_date")
    protected LocalDateTime createDate;

//    @TableUpdateTimeField
//    @TableField("update_date")
    protected LocalDateTime updateDate;

//    @TableCreateUserField
//    @TableField("creator")
    protected String creator;

//    @TableUpdateUserField
//    @TableField("updator")
    protected String updator;

//    @TableActiveField
//    @TableField("valid")
    protected Integer valid;
}

package com.example.lyy.util.tree;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @ProjectName: travel-report
 * @Package: com.zjhcsoft.travel.report.entity.dto
 * @ClassName: ReportAddVO
 * @Author: zhangchao
 * @Description:
 * @Date: 2019/12/30 10:43
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode
@ToString
public class ReportAddVO {

    /**
     * 标题ID
     */
    private Long id;
    /**
     * 标题
     */
    private String title;

    /**
     * 标题级别：1-1级表头，2-2级标题以此类推
     */
    private Integer titleLevel;

    /**
     * 父标题id
     */
    private Long pid;

    /**
     * 报告类型：1-常规报告，2-住宿业接待专项报告，3-客流量分析专项报告
     */
    private Integer reportType;

    /**
     * 报告时间类型：1-年度，2-季度，3-月份，4-节假日
     */
    private Integer reportDateType;

    /**
     * 地区等级(3-区县，4-地市，5-省)
     */
    private Integer areaLevel;

    /**
     * 启用状态：0-未启用，1-启用
     */
    private Integer enable;

    /**
     * 数据来源
     */
    private String dataSource;
//    /**
//     * 文本模板
//     */
//    private List<TextModelEntity> textModelList;
//    /**
//     * 图表模板
//     */
//    private List<ChartModelEntity> chartModelList;
//    /**
//     * 表格模板
//     */
//    private List<TableModelEntity> tableModelList;
    /**
     * 子标题模板
     */
    private List<ReportAddVO> reportAddVOList;
}

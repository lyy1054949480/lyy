package com.example.lyy.util.tree;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName: TitleTreeUtil
 * @Author: zhangchao
 * @Description:标题树形工具类  需要表结构中有parent_id这种字段（父id）
 * 表结构
 * CREATE TABLE `title_template` (
 *   `ID` bigint(20) NOT NULL AUTO_INCREMENT,
 *   `TITLE` varchar(255) DEFAULT NULL COMMENT '标题',
 *   `TITLE_LEVEL` tinyint(4) NOT NULL COMMENT '标题级别：1-1级表头，2-2级标题以此类推',
 *   `PID` bigint(20) DEFAULT NULL COMMENT '父标题id',
 *   `REPORT_TYPE` tinyint(4) NOT NULL COMMENT '报告类型：1-常规报告，2-住宿业接待专项报告，3-客流量分析专项报告',
 *   `REPORT_DATE_TYPE` tinyint(4) DEFAULT NULL COMMENT '报告时间类型：1-年度，2-季度，3-月份，4-节假日',
 *   `AREA_LEVEL` tinyint(4) NOT NULL COMMENT '地区等级(3-区县，4-地市，5-省)',
 *   `ENABLE` tinyint(4) NOT NULL DEFAULT '0' COMMENT '启用状态：0-未启用，1-启用',
 *   `CREATOR` bigint(20) NOT NULL COMMENT '创建者',
 *   `CREATE_DATE` datetime NOT NULL COMMENT '创建时间',
 *   `UPDATOR` bigint(20) DEFAULT NULL COMMENT '修改人',
 *   `UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改时间',
 *   `VALID` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否有效',
 *   PRIMARY KEY (`ID`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='标题模板'
 * @Date: 2019/12/30 15:52
 * @Version: 1.0
 */
public class TitleTreeUtil {
    public static List<ReportAddVO> list2Tree(List<TitleTemplateEntity> titleTemplateList, Long root){
        List<ReportAddVO> reportAddVOList = buildTree(titleTemplateList);
        reportAddVOList.forEach(resource ->{

            reportAddVOList.forEach(current->{
                if (resource.getId().equals(current.getPid())){
                    if (null==resource.getReportAddVOList()){
                        resource.setReportAddVOList(new ArrayList<ReportAddVO>());
                    }
                    resource.getReportAddVOList().add(current);
                }
            });
        });
        return reportAddVOList;
    }
    public static List<ReportAddVO> buildTree(List<TitleTemplateEntity> titleTemplateList){
        List<ReportAddVO> reportAddVOList = new ArrayList<>(16);
        titleTemplateList.forEach(resource ->{
            ReportAddVO reportAddVO = new ReportAddVO();
            BeanUtils.copyProperties(resource,reportAddVO);
            reportAddVOList.add(reportAddVO);
        });
        return reportAddVOList;

    }
    /**
     * 集合排序
     * @param list 排序的集合
     * TitleTemplateEntity: 带有排序字段的对象orderBy
     */
    public static void sortList(List list){
        list.sort(new Comparator<TitleTemplateEntity>() {
            @Override
            public int compare(TitleTemplateEntity o1, TitleTemplateEntity o2) {
                return o1.getOrderBy() - o2.getOrderBy();
            }
        });
    }

    public static void main(String[] args) {
        List<TitleTemplateEntity> titleTemplateList = new ArrayList<>();
        titleTemplateList.add(TitleTemplateEntity.builder().id(1l).title("111111111").titleLevel(1).build());
        titleTemplateList.add(TitleTemplateEntity.builder().id(2l).title("2222222222").pid(1l).titleLevel(2).build());
        titleTemplateList.add(TitleTemplateEntity.builder().id(3l).title("3333333333").pid(1l).titleLevel(2).build());
        titleTemplateList.add(TitleTemplateEntity.builder().id(4l).title("4444444444").pid(2l).titleLevel(3).build());

        System.out.println(JSON.toJSON(titleTemplateList));
        List<ReportAddVO> reportAddVOS = TitleTreeUtil.list2Tree(titleTemplateList, 1l);
        System.out.println(JSON.toJSON(reportAddVOS));

    }
}

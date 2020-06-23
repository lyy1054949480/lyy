package com.example.lyy.merge;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.util.Iterator;
import java.util.Set;

public class SpecialProviderExtend extends MapperTemplate{
    public SpecialProviderExtend(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 批量插入包括id，去除创建时间和更新时间
     * @param ms
     * @return
     */
    public String insertListExtend(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append("<bind name=\"listNotEmptyCheck\" value=\"@tk.mybatis.mapper.util.OGNL@notEmptyCollectionCheck(list, '" + ms.getId() + " 方法参数为空')\"/>");
        sql.append(SqlHelper.insertIntoTable(entityClass, this.tableName(entityClass), "list[0]"));
        // 获取插入列
        String insertColumns = SqlHelper.insertColumns(entityClass, false, false, false);
        // 排除创建时间和更新时间
        String realColumns = insertColumns.replaceAll(",create_date", "").replaceAll(",update_date", "");
        sql.append(realColumns);
        sql.append(" VALUES ");
        sql.append("<foreach collection=\"list\" item=\"record\" separator=\",\" >");
        sql.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        Iterator var5 = columnList.iterator();

        while(var5.hasNext()) {
            EntityColumn column = (EntityColumn)var5.next();
            if(!"create_date".equals(column.getColumn()) && !"update_date".equals(column.getColumn()) && column.isInsertable()) {
                sql.append(column.getColumnHolder("record") + ",");
            }
        }

        sql.append("</trim>");
        sql.append("</foreach>");
        EntityHelper.setKeyProperties(EntityHelper.getPKColumns(entityClass), ms);
        return sql.toString();
    }

}

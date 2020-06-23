package com.example.lyy.merge.templateSql;

import com.alibaba.fastjson.JSON;
import com.example.lyy.mapper.TLogMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class GenerateSqlUtil{

	@Resource
	public TLogMapper tLogMapper;

	public static GenerateSqlUtil generateSqlUtil;

	@PostConstruct
	public void init(){
		generateSqlUtil = this;
		generateSqlUtil.tLogMapper = this.tLogMapper;
	}
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());


	public static String lineToHump(String str) {
		Pattern linePattern = Pattern.compile("_(\\w)");
		str = str.toLowerCase();
		Matcher matcher = linePattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 提取出用于生成SQL语句所需要的参数数据
	 * @param tableName
	 * @return
	 */
	public static String getParams(String tableName,List dataSource) throws Exception {
		LinkedHashMap<String,Object> params = new LinkedHashMap<>();
		LinkedList<String> list = generateSqlUtil.tLogMapper.selectFullColumnsByTableName(tableName);
		List<DataEntity> dataList = new ArrayList<>();
		if (!CollectionUtils.isEmpty(dataSource)){
			for (Object o : dataSource) {
				DataEntity dataEntity = new DataEntity();
				dataEntity.setColumnName(list);
				LinkedList<Object> objects = new LinkedList<>();
				for (String columnName : list) {
					objects.add(BeanUtils.getProperty(o,lineToHump(columnName)));
				}
				dataEntity.setColumnValue(objects);
				dataList.add(dataEntity);
			}
		}
		String result = null;
		if (!CollectionUtils.isEmpty(dataList)){
			params.put("dataSource",dataList);
			params.put("tableName",tableName);
			//获取模板内容
			InputStream templateIS = Thread.currentThread().getContextClassLoader().getResourceAsStream("template/mysql/insert/addSome.ftl");
			if(templateIS==null){
				throw new RuntimeException("获取SQL模板文件内容异常");
			}
			//初始化freemarker
			InputStreamReader isrContent = new InputStreamReader(templateIS);
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
			StringWriter stringWriter = new StringWriter();
			Template temp = null;
			try {
				temp = new Template("mysql", isrContent,cfg);
				temp.process(params, stringWriter);
				result = stringWriter.toString();
			} catch (IOException | TemplateException e) {
				throw new RuntimeException("合成SQL语句异常", e);
			}
			System.out.println(result);
		}
		return result;
	}
}

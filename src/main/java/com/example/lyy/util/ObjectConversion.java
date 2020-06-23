package com.example.lyy.util;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * 两个对象或集合同名属性赋值

 */
public class ObjectConversion {
	
	/**
	 * 从List<A> copy到List<B>
	 * @param list List<B>
	 * @param clazz B
	 * @return List<B>
	 */
	public static <T> List<T> copy(List<?> list,Class<T> clazz){
		String oldOb = JSON.toJSONString(list);
		return JSON.parseArray(oldOb, clazz);
	}
	
	/**
	 * 从对象A copy到 对象B
	 * @param ob A
	 * @param clazz B.class
	 * @return B
	 */
	public static <T> T copy(Object ob,Class<T> clazz){
		String oldOb = JSON.toJSONString(ob);
		return JSON.parseObject(oldOb, clazz);
	}
}


package com.example.lyy.util;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class MapUtil {
	@SuppressWarnings("unchecked")
	public static final <KT, VT> Map<KT, VT> buildMapKVGen(Object[][] arr, Class<KT> kclass, Class<VT> vclass) {
		if (arr == null || arr.length == 0) {
			return null;
		}
		Map<KT, VT> res = new HashMap<KT, VT>();
		for (Object[] row : arr) {
			res.put((KT)row[0], (VT)row[1]);
		}
		return res;
	}
	
	public static void main(String[] args) {
		String[][] arr = new String[][] {{"a", "a1"}, {"b", "b1"}};
		Map<String, String> res = MapUtil.buildMapKVGen(arr, String.class, String.class);
		System.out.println(JSON.toJSON(res).toString());
		
		Object[][] arr2 = new Object[][]{{1, "a1"},{2, 3}};
		Map<Integer, Object> res2 = MapUtil.buildMapKVGen(arr2, Integer.class, Object.class);
		System.out.println(JSON.toJSON(res2).toString());
	}
}

package com.zmst.Tools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class Json2Map {
	public static Map<String, String> JSON2Map(String jsonObjectData) {  
		System.out.println(jsonObjectData);
		Map<String,String> map1 = (Map<String,String>)JSON.parse(jsonObjectData); 
		
		return map1; 
	}  
}

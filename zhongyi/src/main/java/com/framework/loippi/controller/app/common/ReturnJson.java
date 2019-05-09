package com.framework.loippi.controller.app.common;



import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class ReturnJson {
	
	public static String jsonString(String message, Object data, int status)

	{
		Map returnMap = new HashMap();
		returnMap.put("message", message);
		returnMap.put("data", data);
		returnMap.put("status", status);

		JSONObject json = JSONObject.fromObject(returnMap);
		return json.toString();
	}
	
    public static String jsonString(String message, Object data, String status)
    
    {
    	 Map returnMap=new HashMap();
		 returnMap.put("message",message);
		 returnMap.put("data", data);
		 returnMap.put("status",status);
		 JSONObject json = JSONObject.fromObject(returnMap);
		 return 		json.toString();
    }
    
    public static String jsonString(String message,  String status)
    
    {
    	 Map returnMap=new HashMap();
		 returnMap.put("message",message);
		 returnMap.put("status",status);
		 JSONObject json = JSONObject.fromObject(returnMap);
		 return 		json.toString();
    }
    
    
    public static String jsonStringError(String message,  String status)
    
    {
    	 Map returnMap=new HashMap();
		 returnMap.put("message",message);
		 returnMap.put("status",status);
		 JSONObject json = JSONObject.fromObject(returnMap);
		 return 		json.toString();
    }
    
    
    public static String jsonString(String message, int status)

	{
		Map returnMap = new HashMap();
		returnMap.put("message", message);
		returnMap.put("status", status);
		JSONObject json = JSONObject.fromObject(returnMap);
		return json.toString();
	}

	public static String jsonStringError(String message, int status)

	{
		Map returnMap = new HashMap();
		returnMap.put("message", message);
		returnMap.put("status", status);
		JSONObject json = JSONObject.fromObject(returnMap);
		return json.toString();
	}
}

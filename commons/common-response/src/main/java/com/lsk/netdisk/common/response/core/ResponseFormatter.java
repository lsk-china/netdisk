package com.lsk.netdisk.common.response.core;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

public class ResponseFormatter {

	private static Gson gson = new Gson();

	public static String format(Object resp){
		if(resp == null){
			resp = new Object();
		}
		Response response = new Response();
		response.setCode(0);
		response.setMessage("");
		response.setData(resp);
		return gson.toJson(response);
	}
	public static String format(int code,String message){
		Response response = new Response();
		response.setCode(code);
		response.setMessage(message);
		return gson.toJson(response);
	}
}

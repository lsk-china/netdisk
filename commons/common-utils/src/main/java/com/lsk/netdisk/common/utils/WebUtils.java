package com.lsk.netdisk.common.utils;

import javax.servlet.http.Cookie;

public final class WebUtils {
	public static boolean hasCookie(Cookie[] cookies, String key){
		for (Cookie cookie : cookies){
			if (cookie.getName().equals(key)){
				return true;
			}
		}
		return false;
	}
	public static Cookie getCookie(Cookie[] cookies,String key){
		for (Cookie cookie : cookies){
			if (cookie.getName().equals(key)){
				return cookie;
			}
		}
		return null;
	}
}


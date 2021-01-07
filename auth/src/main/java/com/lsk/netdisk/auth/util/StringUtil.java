package com.lsk.netdisk.auth.util;

public final class StringUtil {
	public static boolean isEmpty(String... s){
		boolean result = false;
		for (String str : s){
			result |= privateIsEmpty(str);
		}
		return result;
	}
	private static boolean privateIsEmpty(String s){
		if (s == null || s.trim().equals("")){
			return true;
		}
		return false;
	}
}

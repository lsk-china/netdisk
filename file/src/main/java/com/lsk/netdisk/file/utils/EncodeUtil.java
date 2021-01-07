package com.lsk.netdisk.file.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;

public final class EncodeUtil {
	public static String makeFileName(String srcName) throws UnsupportedEncodingException {
		String nameNotEncoded = "SNAME="+srcName+",DATE="+new Date().getTime();
		Base64.Encoder encoder = Base64.getEncoder();
		byte[] bytes = nameNotEncoded.getBytes("UTF-8");
		return encoder.encodeToString(bytes);
	}
}

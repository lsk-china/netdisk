package com.lsk.netdisk.auth.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public final class ArrayUtil {
	public static <T> boolean contains(T[] array,T element){
		if(array == null){
			return false;
		}
		ArrayList<T> list = new ArrayList<T>(Arrays.asList(array));
		return list.contains(element);
	}
}

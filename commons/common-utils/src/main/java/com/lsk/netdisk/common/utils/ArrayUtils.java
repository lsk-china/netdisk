package com.lsk.netdisk.common.utils;

import java.util.ArrayList;
import java.util.Arrays;

public final class ArrayUtils {
	public static <T> boolean contains(T[] array,T element){
		if(array == null){
			return false;
		}
		ArrayList<T> list = new ArrayList<T>(Arrays.asList(array));
		return list.contains(element);
	}
}

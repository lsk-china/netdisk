package com.lsk.netdisk.common.response.utils;

import com.lsk.netdisk.common.response.aspect.annotation.WithCors;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public final class ReflectUtils {
	public static <T> T getObject(int objLocation, ProceedingJoinPoint pjp, Class<T> type) throws Exception{
		if(objLocation > pjp.getArgs().length){
			throw new IllegalArgumentException("Illegal object location.");
		}
		return (T) pjp.getArgs()[objLocation];
	}
	public static int getReq(Method target){
		WithCors annotationClass = target.getAnnotation(WithCors.class);
		return annotationClass.req();
	}
	public static int getResp(Method target){
		WithCors annotationClass = target.getAnnotation(WithCors.class);
		return annotationClass.resp();
	}
}

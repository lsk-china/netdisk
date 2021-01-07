package com.lsk.netdisk.common.response.aspect;

import com.lsk.netdisk.common.response.utils.ReflectUtils;
import com.lsk.netdisk.common.response.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
@Aspect
public class WithCORSAspect {
	@Pointcut("@annotation(com.lsk.netdisk.common.response.aspect.annotation.WithCors)")
	public void pointcut(){}

	@Around("pointcut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		Method target = ((MethodSignature)(pjp.getSignature())).getMethod();
		int req = ReflectUtils.getReq(target);
		int resp = ReflectUtils.getResp(target);
		HttpServletRequest request = ReflectUtils.getObject(req,pjp, HttpServletRequest.class);
		HttpServletResponse response = ReflectUtils.getObject(resp,pjp,HttpServletResponse.class);
		try{
			Object result = pjp.proceed();
			String origin = request.getHeader("Origin");
			if(!StringUtils.isEmpty(origin)){
				response.addHeader("Access-Control-Allow-Origin",origin);
			}
			response.addHeader("Access-Control-Allow-Credentials","true");
			response.addHeader("Access-Control-Allow-Methods","*");
			String headers = request.getHeader("Access-Control-Request-Headers");
			if(!StringUtils.isEmpty(headers)){
				response.addHeader("Access-Control-Allow-Headers",headers);
			}
			response.addHeader("Access-Control-Max-Age","3600");
			return result;
		}catch(Throwable t){
			throw t;
		}
	}
}

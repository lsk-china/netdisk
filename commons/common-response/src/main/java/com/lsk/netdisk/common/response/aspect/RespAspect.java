package com.lsk.netdisk.common.response.aspect;

import com.lsk.netdisk.common.response.core.ResponseFormatter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class RespAspect {
	@Pointcut("@annotation(com.lsk.netdisk.common.response.aspect.annotation.JsonReturn)")
	public void pointcut(){}

	@Around("pointcut()")
	public Object around(ProceedingJoinPoint pjp){
		Object result = null;
		try{
			result = pjp.proceed();
			return ResponseFormatter.format(result);
		}catch(Throwable t){
			log.error("Error: ",t);
			return ResponseFormatter.format(1,t.getMessage());
		}
	}
}

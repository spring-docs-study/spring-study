package org.exmplae.spring.chpater;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggingAspect {
	private Logger log = Logger.getLogger(LoggingAspect.class.getName());


	@Around(value = "@annotation(ToLog)")
	public Object log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		log.info("Calling the intercepted method");
		Object returnValue = proceedingJoinPoint.proceed();
		log.info("[AOP Method executed] : " + returnValue );
		return returnValue;
	}
}

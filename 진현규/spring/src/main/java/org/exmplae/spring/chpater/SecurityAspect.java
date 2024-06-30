package org.exmplae.spring.chpater;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Order(1)
@Aspect
public class SecurityAspect {
	private Logger log = Logger.getLogger(SecurityAspect.class.getName());

	@Around(value="@annotation(ToLog)")
	public Object secure(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("SecurityAspect Calling");
		Object result = joinPoint.proceed();
		log.info("SecurityAspect Returning" + result);
		return result;
	}
}

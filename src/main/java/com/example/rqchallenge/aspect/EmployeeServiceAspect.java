package com.example.rqchallenge.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// add a log before and after every method call in EmployeeService class
@Aspect
@Component
public class EmployeeServiceAspect {

	private final Logger logger = LoggerFactory.getLogger(EmployeeServiceAspect.class);
	
	@Before("execution(* com.example.rqchallenge.service.employee.*.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		logger.debug("{} has started execution.", joinPoint.getSignature().getName());
	}
	
	@After("execution(* com.example.rqchallenge.service.employee.*.*(..))")
	public void logAfter(JoinPoint joinPoint) {
		logger.debug("{} finished execution", joinPoint.getSignature().getName());	}
}

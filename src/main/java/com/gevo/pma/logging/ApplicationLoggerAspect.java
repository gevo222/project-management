package com.gevo.pma.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationLoggerAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("within(com.gevo.pma.controllers..*)")
	public void definePackagePointcuts()
	{
		//naming point cut
	}
	
	@After("definePackagePointcuts()")
	public void logAfter(JoinPoint jp)
	{
		log.debug("\n {}.{}() with argument[s] = {}", jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName(), jp.getArgs());
	}
	
}

package com.javalec.ex;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAop {
	
	@Pointcut("within(com.javalec.ex.*)") //pointcut에는 범주를 줘야함
	private void pointcutMethod() {
	}
	
	
	@Around("pointcutMethod()")	//Around 실행될때 오류가 날때 모두 사용됨
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable {
		String signatureStr = joinpoint.getSignature().toShortString();
		System.out.println( signatureStr + " is start.");
		long st = System.currentTimeMillis();
		
		try {
			Object obj = joinpoint.proceed();
			return obj;
		} finally {
			long et = System.currentTimeMillis();
			System.out.println( signatureStr + " is finished.");
			System.out.println( signatureStr + " 경과시간 : " + (et - st));
		}
		
	}
	
	@Before("within(com.javalec.ex.*)") //핵심Mothod를 실행한후 실행됨
	public void beforAdvice() {
		System.out.println("beforAdvice()");
	}
	
}

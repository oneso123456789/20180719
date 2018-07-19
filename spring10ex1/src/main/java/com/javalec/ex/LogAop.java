package com.javalec.ex;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAop {
	
									//within(com.javalec.ex.? 표현식
	@Pointcut("within(com.javalec.ex.*)")   //? = * : 모든 클래스에 적용
	private void pointcutMethod() {			//? = . :현재 클래스에 적용
	}										//?  = .. :0개이상에 클래스에	
	
	@Around("pointcutMethod()")
	public Object loggerAop(ProceedingJoinPoint joinPoint) throws Throwable{
		String signatureStr = joinPoint.getSignature().toShortString();
		System.out.println(signatureStr+ " is start.");
		long st = System.currentTimeMillis();
		
		try {
			Object obj = joinPoint.proceed();
			return obj;
		}finally {
			long et = System.currentTimeMillis();
			System.out.println(signatureStr + " is finished.");
			System.out.println(signatureStr + " 경과시간 : " + (et - st)) ;
		}
	}
	
	@Before("within(com.javalec.ex.*)")
	public void beforAdvice() {
		System.out.println("beforAdvice()");
	}
}

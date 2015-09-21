package com.dspl.malkey.aspect;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.dspl.malkey.dao.FarfdtfDAOImpl;
import com.dspl.malkey.util.MailMail;

@Aspect
public class LoggingIntercepter {
	private final Log log = LogFactory.getLog(this.getClass());

	@Resource(name="mailMail")
	MailMail mailMail;
	
	@Before("execution(* com.dspl.malkey..*.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		log.info("-----------------------------------------------------");
		log.info("Class:"+joinPoint.getTarget().getClass().getName() +" Method Begin: " + joinPoint.getSignature().getName());
		Object[] methodArguments = joinPoint.getArgs();
		for (Object value : methodArguments) {
			if(value!=null)
			log.info("Method Arguments : " + value.toString()+" @"+System.currentTimeMillis());
			else
			log.info("Method Arguments : " + value+" @"+System.currentTimeMillis());
		}
		log.info("-----------------------------------------------------");
		
/*		System.out.println("-----------------------------------------------------");
		System.out.println("Method Begin: " + joinPoint.getSignature().getName()+" @"+System.currentTimeMillis());
		for (Object value : methodArguments) {
			System.out.println("Method Arguments : " + value.toString());
		}
		System.out.println("-----------------------------------------------------");*/
		
		

	}

	@After("execution(* com.dspl.malkey..*.*(..))")
	public void logAfter(JoinPoint joinPoint) {
		log.info("-----------------------------------------------------");
		log.info("Class:"+joinPoint.getTarget().getClass().getName() +" Method After : " + joinPoint.getSignature().getName()+" @"+System.currentTimeMillis());
		log.info("-----------------------------------------------------");
		
/*		System.out.println("-----------------------------------------------------");
		System.out.println("Method After : " + joinPoint.getSignature().getName()+" @"+System.currentTimeMillis());
		System.out.println("-----------------------------------------------------");*/
		
		
	}

	@AfterThrowing(pointcut = "execution(* com.dspl.malkey..*.*(..))", throwing = "error")
	public void afterThrowing(JoinPoint joinPoint, Throwable error) {
		log.info("#####################################################");
		log.info("Exception in Class    : " + joinPoint.getTarget().getClass().getName());
		log.info("Exception in Method    : " + joinPoint.getSignature().getName());
		log.info("Custome Error Message  : " + error.getLocalizedMessage());
		log.error("StackTrace : ", error);
		log.info("#####################################################");
		
		System.out.println("#####################################################");
		System.out.println("Exception in Class    : " + joinPoint.getTarget().getClass().getName());
		System.out.println("Exception in Method    : " + joinPoint.getSignature().getName());
		System.out.println("Custome Error Message  : " + error.getLocalizedMessage());
		System.out.println("StackTrace : "+ error);
		System.out.println("#####################################################");

if(joinPoint.getTarget().getClass().equals(FarfdtfDAOImpl.class)){
			try{
			String emailMsg= "\nException in Method    : " + joinPoint.getTarget().getClass().getName() +"."+joinPoint.getSignature().getName()+"\n"+"Custome Error Message  : " 
			+ error.getLocalizedMessage()+"\nStackTrace : "+ error;
			mailMail.sendPlainMail("Dear Sanka",emailMsg);
			}catch(Exception e){log.info("Unable to sen email");}
}


	}
}

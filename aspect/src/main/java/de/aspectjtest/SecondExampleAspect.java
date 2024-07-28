package de.aspectjtest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SecondExampleAspect {

	@Pointcut("!within(de.aspectjtest..*)")
	public void notWithinAspect() {
	}

	@Pointcut("!set(private * *)")
	public void noSet() {
		
	}
	
	@Before("notWithinAspect() && noSet()")
	public void aroundStuff(final JoinPoint thisJoinPoint)
			throws Throwable {
		System.out.println("=== Call2: " + thisJoinPoint.getSignature() + " " + thisJoinPoint.getKind());
	}
}

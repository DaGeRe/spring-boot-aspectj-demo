package de.aspectjtest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ExampleAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExampleAspect.class);

	@Pointcut("!within(de.aspectjtest..*)")
	public void notWithinAspect() {}

	@Pointcut("!set(private * *)")
	public void noSet() {

	}

	@Before("notWithinAspect() && noSet()")
	public void aroundStuff(final JoinPoint thisJoinPoint)
			throws Throwable {
		System.out.println("=== Call1: " + thisJoinPoint.getSignature() + " " + thisJoinPoint.getKind());
		LOGGER.info("Method was called");
	}
}

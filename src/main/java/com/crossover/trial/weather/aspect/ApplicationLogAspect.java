package com.crossover.trial.weather.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.crossover.trial.weather.endpoints.RestWeatherQueryEndpoint;

import java.util.logging.Logger;

/**
 * Method execution time logging aspect
 */
@Component
@Aspect
public class ApplicationLogAspect {

    private final static Logger LOGGER = Logger.getLogger(RestWeatherQueryEndpoint.class.getName());

    @Around("execution(* com.crossover.trial.weather.endpoints.*.*(..))")
    public Object logTimeMethod(ProceedingJoinPoint joinPoint) throws Throwable {
    	try{
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();

        StringBuilder logMessage = new StringBuilder();
        logMessage.append(joinPoint.getTarget().getClass().getName());
        logMessage.append(".");
        logMessage.append(joinPoint.getSignature().getName());
        logMessage.append("(");

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            logMessage.append("'").append(arg).append("', ");
        }
        if (args.length > 0) {
            logMessage.delete(logMessage.length() - 2, logMessage.length());
        }

        logMessage.append(")");
        logMessage.append(" execution time: ");
        logMessage.append(stopWatch.getTotalTimeMillis());
        logMessage.append(" ms");

        LOGGER.info(logMessage.toString());

        return result;
        }
    	catch (Exception ex)
    	{
    		System.out.println("Exception in aspect " + ex.getMessage());
    		return joinPoint.proceed();
    	}
    }

}

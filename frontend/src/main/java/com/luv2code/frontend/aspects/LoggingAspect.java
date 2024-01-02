package com.luv2code.frontend.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Aspect per il logging centralizzato
 */
@Aspect
@Component
public class LoggingAspect {

    /**
     * Logger
     */
    private Logger logger = Logger.getLogger(getClass().getName());

    /**
     * Pointcut per intercettare qualsiasi metodo di qualsiasi classe nel package com.luv2code.frontend.controllers
     */
    @Pointcut("execution(* com.luv2code.frontend.controllers.*.*(..))")
    private void forControllersPackage() {}

    /**
     * Pointcut per intercettare qualsiasi metodo di qualsiasi classe nel package com.luv2code.frontend.services
     */
    @Pointcut("execution(* com.luv2code.frontend.services.*.*(..))")
    private void forServicesPackage() {}

    /**
     * Pointcut per intercettare qualsiasi metodo di qualsiasi classe nel package com.luv2code.frontend.dao
     */
    @Pointcut("execution(* com.luv2code.frontend.dao.*.*(..))")
    private void forDaoPackage() {}

    /**
     * Pointcut per intercettare qualsiasi metodo di un service, dao o controller
     */
    @Pointcut("forControllersPackage() || forServicesPackage() || forDaoPackage()")
    private void forAppFlow() {}

    /**
     * Before advice
     * @param joinPoint join point (method execution)
     */
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {

        String method = joinPoint.getSignature().toShortString();
        logger.info("=====>> in @before. calling method: " + method);

        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            logger.info("=====>> argument. type: " + arg.getClass().getName() + ", value: " + arg);
        }
    }

    /**
     * AfterReturning advice
     * @param joinPoint join point (method execution)
     * @param result Oggetto ritornato dal metodo intercettato
     */
    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "result"
    )
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("=====>> in @AfterReturning. calling method: " + method);

        if (result != null) {
            logger.info("=====>> Return. type: " + result.getClass().getName() + ", value: " + result);
        } else {
            logger.info("=====>> Return null");
        }
    }
}

package org.acme.logintercept;

import org.jboss.logging.Logger;
import javax.inject.Inject;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Logged
@Priority(2020)
@Interceptor
public class LoggingInterceptor {

    @Inject
    Logger logger;

    @AroundInvoke
    Object logInvocation(InvocationContext context) throws Exception {

        logger.info("object before");

        Object ret = context.proceed();

        logger.info("object after");
        return ret;
    }

}


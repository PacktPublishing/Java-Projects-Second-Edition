package packt.java11.bulkorder;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import packt.java11.bulkorder.dtos.ProductInformation;

@Configuration
@Aspect
public class SpringConfigurationAspect {
    private static final Logger log = LoggerFactory.getLogger("AUDIT_LOG");

    @Around("execution(* byId(..))")
    public ProductInformation byIdQueryLogging(ProceedingJoinPoint jp) throws Throwable {
        log.info("byId query is about to run");
        ProductInformation pi = (ProductInformation) jp.proceed(jp.getArgs());
        log.info("byId query was executed");
        return pi;
    }

    @Around("execution(* url(..))")
    public String urlCreationLogging(ProceedingJoinPoint jp) throws Throwable {
        log.info("url is to be created");
        String url = (String) jp.proceed(jp.getArgs());
        log.info("url created was "+url);
        return url;
    }
}

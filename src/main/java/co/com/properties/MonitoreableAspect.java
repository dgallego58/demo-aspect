package co.com.properties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

@Aspect
public class MonitoreableAspect {

    private static final Logger log = LoggerFactory.getLogger(MonitoreableAspect.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Pointcut(value = "execution(* (@co.com.properties.Monitoreable *).*(..))")
    public void executionOnEveryClassAnnotatedWithMonitoreable() {
        //on aspect relieve
    }

    @Pointcut(value = "execution(* (@co.com.properties.Monitoreable *).*())")
    public void executionOnEveryClassAnnotatedWithMonitoreable1() {
        //on aspect relieve
    }

    //@Async
    @Before("executionOnEveryClassAnnotatedWithMonitoreable()")
    public void adviceExecutionBefore(JoinPoint joinPoint) throws JsonProcessingException {
        String targetObject = objectMapper.writeValueAsString(joinPoint.getSignature().getDeclaringType());
        String req = objectMapper.writeValueAsString(joinPoint.getArgs());
        log.info("On Before - {}, {}", targetObject, req);
        //here you call kinesis before
    }

    //@Async
    @AfterReturning(pointcut = "executionOnEveryClassAnnotatedWithMonitoreable()", returning = "response")
    public void adviceExecutionAfter(JoinPoint joinPoint, Object response) throws JsonProcessingException {
        String requestJson = objectMapper.writeValueAsString(joinPoint.getArgs());
        String responseJson = objectMapper.writeValueAsString(response);
        log.info("On AfterReturning - Request: {}, Response: {}", requestJson, responseJson);
        //here you call kinesis after
    }

    //@Async
    @After("executionOnEveryClassAnnotatedWithMonitoreable()")
    public void adviceFinally(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        log.info("On After Method: {}", method);
    }

    //@Async
    @AfterThrowing(pointcut = "executionOnEveryClassAnnotatedWithMonitoreable()", throwing = "ex")
    public void adviceAfterThrowing(JoinPoint joinPoint, Throwable ex) {

        String methodName = joinPoint.getSignature().getName();
        log.info("On AfterThrowing: {}, {}", methodName, ex.getMessage());
    }

}

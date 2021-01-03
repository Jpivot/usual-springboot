package com.jpivot.springbootdemo;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class TestAspect {
    @Around("execution(* com.jpivot.springbootdemo.controller.HelloWorldController.getUser(..))")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        log.info("@Around：执行目标方法之前...");
        long start = System.currentTimeMillis();
        //访问目标方法的参数：
        Object[] args = point.getArgs();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        assert sra != null;
        HttpServletRequest request = sra.getRequest();
        if (args != null && args.length > 0 && args[0].getClass() == String.class) {
            args[0] = "改变后的参数1";
        }
        //用改变后的参数执行目标方法
        Object returnValue = point.proceed(args);
        log.info("@Around：执行目标方法之后...");
        log.info("@Around：被织入的目标对象为：" + point.getTarget());
        long end = System.currentTimeMillis();
        log.info("目标对象方法执行时间为：{}",(end-start));
        return returnValue;
    }

}

package com.beiyou.util;

import com.beiyou.auth.TYPE;
import com.beiyou.business.MYSQL.SystemLogService;
import com.beiyou.pojo.systemlogmessage;
import com.beiyou.util.data.DateSourceUtils;
import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;
@Component
@Aspect
public class LogAsp  {
    @Autowired
    SystemLogService systemLogService;
    public String getbefore(ProceedingJoinPoint joinPoint){
        String str = "";
        Class clazz = joinPoint.getTarget().getClass();
        Object[] objects = joinPoint.getArgs();
        String name = joinPoint.getSignature().getName();
        Method[] method = clazz.getMethods();
        for (Method m : method){
            if(m.getName().equals(name)){
                if (m.getParameterTypes().length==objects.length){
                    str=m.getAnnotation(Systemrizhi.class).desc();
                }
            }
        }
        return  str;
    }
    public String getprams(ProceedingJoinPoint joinPoint){
        String argus = "";
        Class clazz = joinPoint.getTarget().getClass();
        Object[] objects = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        Method[] methods = clazz.getMethods();
        Gson gson = new Gson();
        for (Method m:methods) {
            if(m.getName().equals(methodName)){
                if(m.getParameterTypes().length==objects.length){
                    if(m.getAnnotation(Systemrizhi.class).isWrite()){
                        argus = gson.toJson(objects);
                    }else{
                        if(objects.length!=0){
                            for (int i = 0; i < objects.length; i++) {
                                argus += (objects[i].getClass().getName() + ",");
                            }
                        }
                    }
                }
            }
        }

        return argus;
    }
    @Around("@annotation(com.beiyou.util.Systemrizhi)")
    @Order(20)
    public Object before(ProceedingJoinPoint joinPoint){
        systemlogmessage systemlogMessage = new systemlogmessage();
        systemlogMessage.setSystemlogmessageName("晁鑫");
        systemlogMessage.setSystemlogmessageRoles("admin");
        Object object = null;
       Object[] objects = joinPoint.getArgs();
       Gson gson = new Gson();
       systemlogMessage.setSystemlogmessageParams(getprams(joinPoint));
       String name = joinPoint.getSignature().getName();
       systemlogMessage.setSystemlogmessagMethod(name);
      String str=getbefore(joinPoint);
      systemlogMessage.setSystemlogmessageDescription(str);
        Long time = System.currentTimeMillis();
        systemlogMessage.setSystemlogmessageStarttime(new Date());
        try {
            object = joinPoint.proceed(objects);
            systemlogMessage.setSystemlogmessageSuccessful("成功");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            systemlogMessage.setSystemlogmessageSuccessful("失败");
            systemlogMessage.setSystemlogmessageException(throwable.toString());
        }
        Long endtime = System.currentTimeMillis();
systemlogMessage.setSystemlogmessageTime(endtime-time+"ms");

        systemLogService.insert(systemlogMessage);
        return object;
    }


    @Pointcut("execution(public * com.beiyou.business.oracle..*(..))")
    public void oracleCut(){
        // 系统中需要配置oracle数据源的切面
    }
    @Before("oracleCut()")
    @Order(10)
    public void dataAround(){
        DateSourceUtils.setDataSourceKey(TYPE.ORACLE);
    }






}

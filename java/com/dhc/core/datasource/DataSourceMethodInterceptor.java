/**
 *  Copyright (c) 2012-2015 dhc
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.dhc.core.datasource;

import com.dhc.common.utils.reflection.ReflectionUtils;
import com.dhc.core.datasource.annotation.DataSource;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.Proxy;

/**
 * 多数据源动态配置拦截器
 *
 *  
 *   
 */
public class DataSourceMethodInterceptor implements MethodInterceptor, InitializingBean {

    private Logger logger = LoggerFactory.getLogger(DataSourceMethodInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Class<?> clazz = invocation.getThis().getClass();
        String className = clazz.getName();
        if (ClassUtils.isAssignable(clazz, Proxy.class)) {
            className = invocation.getMethod().getDeclaringClass().getName();
        }
        String methodName = invocation.getMethod().getName();
        Object[] arguments = invocation.getArguments();
        logger.trace("execute {}.{}({})", className, methodName, arguments);

        invocation.getMethod();
        DataSource classDataSource = ReflectionUtils.getAnnotation(invocation.getThis(), DataSource.class);
        DataSource methodDataSource = ReflectionUtils.getAnnotation(invocation.getMethod(), DataSource.class);
        if(methodDataSource != null){
            DataSourceContextHolder.setDataSourceType(methodDataSource.value());
        }else if(classDataSource != null){
            DataSourceContextHolder.setDataSourceType(classDataSource.value());
        }else {
            DataSourceContextHolder.clearDataSourceType();
        }

        Object result = invocation.proceed();
        return result;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }
}
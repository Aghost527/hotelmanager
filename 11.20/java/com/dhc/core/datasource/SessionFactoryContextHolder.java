/**
 *  Copyright (c) 2012-2015 dhc
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.dhc.core.datasource;

/**
 *  
 *   
 */
public abstract class SessionFactoryContextHolder {

    public final static String DATA_SOURCE_DEFAULT = "dataSource";
    public final static String DATA_SOURCE_MYSQL = "dataSource2";

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setSessionFactoryType(String sessionFactory) {
        contextHolder.set(sessionFactory);
    }

    public static String getSessionFactory() {
        return contextHolder.get();
    }

    public static void clearSessionFactory() {
        contextHolder.remove();
    }
}
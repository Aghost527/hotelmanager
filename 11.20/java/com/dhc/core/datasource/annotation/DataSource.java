/**
 * Copyright (c) 2012-2014 http://www.eryansky.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.dhc.core.datasource.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  
 *   
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {

    String value();
}

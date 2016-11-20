/**
 *  Copyright (c) 2012-2015 dhc
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); 
 */
package com.dhc.core.security.annotation;


import com.dhc.core.security._enum.Logical;

import java.lang.annotation.*;

import static com.dhc.core.security._enum.Logical.AND;

/**
 * 需要的角色
 *  
 *  
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiresRoles {

    String[] value() default {};

    public Logical logical() default AND;
}
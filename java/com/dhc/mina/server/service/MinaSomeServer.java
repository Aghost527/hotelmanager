/**
 *  Copyright (c) 2012-2015 dhc
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.dhc.mina.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @Description: Mina Socket服务
 * 
 * @author 尔演&Eryan eryanwcp@163.com
 * 
 *   
 * 
 */
public class MinaSomeServer {

    private static final Logger logger = LoggerFactory.getLogger(MinaSomeServer.class);

    public String doSome(String msg) {
        logger.info("msg = " + msg);
		return msg;
    }

}

/**
 *  Copyright (c) 2012-2015 dhc
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.dhc.listener;

import com.dhc.common.web.listener.DefaultSystemInitListener;
import com.dhc.core.security.SecurityType;
import com.dhc.core.security.SecurityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpSessionEvent;

/**
 * 系统初始化监听 继承默认系统启动监听器.
 * 
 *  
 *   
 */
public class SystemInitListener extends DefaultSystemInitListener{

	private static final Logger logger = LoggerFactory
			.getLogger(SystemInitListener.class);

	public SystemInitListener() {
	}

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        super.contextInitialized(sce);
        //WebService发布
//        if(StringUtils.isNotBlank(AppConstants.getWebServiceUrl())){
//            logger.info("WebService发布...");
//            Endpoint.publish(AppConstants.getWebServiceUrl(), new UserWebServiceImpl());
//            logger.info("WebService[{}]发布成功。",AppConstants.getWebServiceUrl());
//        }
    }

    /**
	 * session销毁
	 */
	public void sessionDestroyed(HttpSessionEvent evt) {
		super.sessionDestroyed(evt);
		String sessionId = evt.getSession().getId();
		SecurityUtils.removeUserFromSession(sessionId,true, SecurityType.logout_abnormal);
	}

}

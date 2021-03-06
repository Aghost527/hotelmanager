/**
 *  Copyright (c) 2012-2015 dhc
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.dhc.webservice.soap.server.impl;


import com.dhc.common.spring.SpringContextHolder;
import com.dhc.core.security.SessionInfo;
import com.dhc.modules.sys.service.UserManager;
import com.dhc.webservice.soap.server.UserWebService;
import com.dhc.webservice.soap.server.WsConstants;
import com.dhc.webservice.soap.server.result.GetUserResult;
import com.dhc.webservice.soap.server.result.WSResult;

import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.jws.HandlerChain;
import javax.jws.WebService;

/**
 * UserWebService服务端实现类.
 *  
 *   
 *
 */
//serviceName与portName属性指明WSDL中的名称, endpointInterface属性指向Interface定义类.
@WebService(serviceName = "UserService", portName = "UserServicePort", endpointInterface = "com.dhc.webservice.soap.server.UserWebService", targetNamespace = WsConstants.NS)
@HandlerChain(file = "ws-handler-chain.xml")
public class UserWebServiceImpl implements UserWebService {

	private static Logger logger = LoggerFactory.getLogger(UserWebServiceImpl.class);

	private static UserManager userManager = SpringContextHolder.getBean(UserManager.class);

	/**
     */
	public GetUserResult getUser(String loginName) {
		//校验请求参数
		try {
			Assert.notNull(loginName, "loginName参数为空");
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage());
			return WSResult.buildResult(GetUserResult.class, WSResult.PARAMETER_ERROR, e.getMessage());
		}

		//获取用户
		try {

			SessionInfo sessionInfo = userManager.getUser(loginName);

			GetUserResult result = new GetUserResult();
			result.setUser(sessionInfo);

			return result;
		} catch (ObjectNotFoundException e) {
			String message = "用户不存在(loginName:" + loginName + ")";
			logger.error(message, e);
			return WSResult.buildResult(GetUserResult.class, WSResult.PARAMETER_ERROR, message);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return WSResult.buildDefaultErrorResult(GetUserResult.class);
		}
	}
}

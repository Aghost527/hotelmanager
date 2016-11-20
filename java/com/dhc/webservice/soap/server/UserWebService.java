package com.dhc.webservice.soap.server;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.dhc.webservice.soap.server.result.GetUserResult;



/**
 * JAX-WS2.0的WebService接口定义类.
 *  
 *   
 *
 */
@WebService(name = "UserService", targetNamespace = WsConstants.NS)
public interface UserWebService {

	/**
	 * 获取用户, 受SpringSecurity保护.
	 */
	GetUserResult getUser(@WebParam(name = "name") String name);
}

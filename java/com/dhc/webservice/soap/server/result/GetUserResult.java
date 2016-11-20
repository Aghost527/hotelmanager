/**
 *  Copyright (c) 2012-2015 dhc
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.dhc.webservice.soap.server.result;

import com.dhc.core.security.SessionInfo;
import com.dhc.webservice.soap.server.WsConstants;

import javax.xml.bind.annotation.XmlType;


/**
 * GetUser方法的返回结果.
 *  
 *   
 *
 */
@XmlType(name = "GetUserResult", namespace = WsConstants.NS)
public class GetUserResult extends WSResult {

	private SessionInfo user;

	public SessionInfo getUser() {
		return user;
	}

	public void setUser(SessionInfo user) {
		this.user = user;
	}
}

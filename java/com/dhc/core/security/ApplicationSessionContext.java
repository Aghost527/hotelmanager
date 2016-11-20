package com.dhc.core.security;

import com.google.common.collect.Maps;

import javax.servlet.http.HttpSession;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 应用Session上下文
 */
public class ApplicationSessionContext {

	private static ApplicationSessionContext instance;

	/**
	 * 用于存储HttpSession的Map对象
	 */
	private Map<String,HttpSession> sessionData;

	private ApplicationSessionContext() {
		sessionData = Maps.newHashMap();
	}

	public static ApplicationSessionContext getInstance() {
		if (instance == null) {
			instance = new ApplicationSessionContext();
		}
		return instance;
	}

	public synchronized void addSession(HttpSession session) {
		if (session != null) {
			sessionData.put(session.getId(), session);
		}
	}

	public synchronized void removeSession(HttpSession session) {
		if (session != null) {
			sessionData.remove(session.getId());
		}
	}

	public synchronized HttpSession getSession(String sessionId) {
		if (sessionId == null) {System.out.println("sessionid is null");return null;};
		System.out.println("session is here,随后遍历键值对看看");
		HttpSession sb= sessionData.get(sessionId);
		System.out.println("通过Map.entrySet使用iterator遍历key和value：");
		  Iterator<Entry<String, HttpSession>> it = sessionData.entrySet().iterator();
		  while (it.hasNext()) {
		   Entry<String, HttpSession> entry = it.next();
		   System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
		  }
		if(sb==null){System.out.println("but session is null");}
		return sb;
	//	return (HttpSession) sessionData.get(sessionId);
		
	}

	public Map<String,HttpSession> getSessionData() {
		return sessionData;
	}
}
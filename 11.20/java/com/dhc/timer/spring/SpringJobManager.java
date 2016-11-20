/**
 *  Copyright (c) 2012-2015 dhc
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.dhc.timer.spring;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;





import com.dhc.common.orm.hibernate.EntityManager;
import com.dhc.common.orm.hibernate.HibernateDao;
import com.dhc.common.orm.hibernate.Parameter;
import com.dhc.modules.sys.entity.Log;
import com.dhc.utils.AppConstants;
import com.dhc.utils.SSHClient;

/**
 * Spring3中使用注解(@Scheduled)创建计划任务. <br>
 * 使用注解方式.
 * 
 * 
 */
@Component
public class SpringJobManager {
	@Autowired




	private static final Logger logger = LoggerFactory
			.getLogger(SpringJobManager.class);

		@Scheduled(cron = "0 0 2 * * ?")
	// 每天2点触发执行
	public void clearExpiredOfficeIP() {
		logger.info("清除失效办公设备IP");
		//ipaddressManager.clearExpiredOfficeIP();
		
	}


}
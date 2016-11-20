/**
 *  Copyright (c) 2012-2015 dhc
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.dhc.modules.sys.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.dhc.common.exception.DaoException;
import com.dhc.common.exception.ServiceException;
import com.dhc.common.exception.SystemException;
import com.dhc.common.model.TreeNode;
import com.dhc.common.orm.entity.StatusState;
import com.dhc.common.orm.hibernate.EntityManager;
import com.dhc.common.orm.hibernate.HibernateDao;
import com.dhc.common.orm.hibernate.Parameter;
import com.dhc.common.utils.InetAddressUtils;
import com.dhc.common.utils.StringUtils;
import com.dhc.common.utils.collections.Collections3;
import com.dhc.modules.sys._enum.SexType;
import com.dhc.modules.sys.entity.Maintains;
import com.dhc.modules.sys.entity.User;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Iprange管理Service层.
 * 
 * 
 * 
 */
@Service
public class MaintainsManager extends EntityManager<Maintains, Long> {

	private HibernateDao<Maintains, Long> MaintainsDao;

	/**
	 * 通过注入的sessionFactory初始化默认的泛型DAO成员变量.
	 */
	@Autowired
	public void setSessionFactory(final SessionFactory sessionFactory) {
		MaintainsDao = new HibernateDao<Maintains, Long>(sessionFactory,
				Maintains.class);
	}

	@Override
	protected HibernateDao<Maintains, Long> getEntityDao() {
		return MaintainsDao;
	}
}

/**
 *  Copyright (c) 2012-2015 dhc
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.dhc.modules.sys.service;

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
import com.dhc.common.utils.collections.Collections3;
import com.dhc.modules.sys.entity.ComboDict;
import com.dhc.modules.sys.entity.equipname;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ComboDict管理Service层.
 * 
 * 
 * 
 */
@Service
public class ComboDictManager extends EntityManager<ComboDict, Long> {

	private HibernateDao<ComboDict, Long> ComboDictDao;
	
	public String[] hostList= { "设备类型", "type", "设备品牌", "brand", "型号",
			"hostEquipmentVersion", "设备功率", "power", "CPU", "cpu", "内存",
			"memory", "硬盘", "disk", "网卡", "networkCard", "HBA卡", "hbacard",
			"操作系统", "operatingSystemName", "操作系统版本", "operatingSystemVersion",
			"使用人", "administrator", "承载系统", "system", "城市", "city", "地址",
			"location", "楼层", "floor" };
	public String[] officeList = { "mac地址", "mac", "类型", "type", "设备品牌", "brand", "设备型号",
			"equipmentversion", "设备功率", "power", "CPU", "cpu", "内存",
			"memory", "硬盘", "disk", "网卡", "networkCard", "操作系统",
			"operatingSystemName", "操作系统版本", "operatingSystemVersion", "使用人",
			"administrator" ,"设备用途","utilization", "城市", "city", "地址", "location" };
	public String[] netList = { "设备类型", "type", "设备品牌", "brand", "设备型号",
			"equipmentversion", "设备功率", "power", "CPU", "cpu", "内存",
			"memory", "板卡类型", "boardtype", "板卡配置", "boardsetting", "使用人",
			"administrator" ,"设备用途","utilization", "城市", "city", "地址", "location" };
	/**
	 * 通过注入的sessionFactory初始化默认的泛型DAO成员变量.
	 */
	@Autowired
	public void setSessionFactory(final SessionFactory sessionFactory) {
		ComboDictDao = new HibernateDao<ComboDict, Long>(sessionFactory,
				ComboDict.class);
	}

	@Override
	protected HibernateDao<ComboDict, Long> getEntityDao() {
		return ComboDictDao;
	}

	/**
	 * 获取所有选项
	 * 
	 * 
	 * @return
	 * @throws DaoException
	 * @throws SystemException
	 * @throws ServiceException
	 */
	public List<ComboDict> getComboDictList(String page, String type)
			throws DaoException, SystemException, ServiceException {
		// List<ComboDict> hostEquipmentNodes = Lists.newArrayList();
		// 顶级机构

		// List<ComboDict> Dicts = Lists.newArrayList();
		// Dicts = getById(null, StatusState.normal.getValue());

		StringBuilder sb = new StringBuilder();
		Parameter parameter = new Parameter();
		sb.append("select distint type from `t_sys_combodictionary` o where page=  ");
		sb.append("'" + page + "'");

		sb.append(" order by o.id desc");

		List<ComboDict> list = ComboDictDao.find(sb.toString(), parameter);
		// for (ComboDict rs : Dicts) {
		// if (rs.getPage().equals(page))
		// if (rs.getType().equals(type))
		// hostEquipmentNodes.add(rs);
		// }
		return list;

	}

	/*
	 * 显示所选page和type下的选项
	 */
	public List<ComboDict> getComboDictTypeList(String page, String type)
			throws DaoException, SystemException, ServiceException {

		StringBuilder sb = new StringBuilder();
		Parameter parameter = new Parameter();
		sb.append("from ComboDict o where o.page= :page and o.type=:type");
		// sb.append("page" );
		parameter.put("page", page);
		parameter.put("type", type);
		sb.append(" order by o.id desc");

		List<ComboDict> list = ComboDictDao.find(sb.toString(), parameter);
		// for (ComboDict rs : Dicts) {
		// if (rs.getPage().equals(page))
		// if (rs.getType().equals(type))
		// hostEquipmentNodes.add(rs);
		// }
		return list;

	}

	public List<ComboDict> getById(Long parentId, Integer status)
			throws DaoException, SystemException, ServiceException {
		// 默认值 正常
		if (status == null) {
			status = StatusState.normal.getValue();
		}
		StringBuilder sb = new StringBuilder();
		Parameter parameter = new Parameter();
		sb.append("from ComboDict o where o.status = :status  ");
		parameter.put("status", status);

		sb.append(" order by o.id desc");

		List<ComboDict> list = ComboDictDao.find(sb.toString(), parameter);
		return list;
	}



	public List<equipname> getPageList()// pageList
			throws DaoException, SystemException, ServiceException {
		List<equipname> names = Lists.newArrayList();
		String a[] = { "主机设备", "host", "网络设备", "net", "办公设备", "office" };
		for (int i = 0; i < a.length; i += 2) {
			names.add(new equipname(a[i], a[i + 1]));
		}
		return names;
	}

	/**
	 * 获取所有导航机构.
	 * 
	 * @param parentId
	 *            父级ID 为null，则可查询所有节点；不为null，则查询该级以及以下
	 * @param excludeIprangeId
	 *            需要排除的机构ID 子级也会被排除
	 * @param isCascade
	 *            是否级联
	 * @param adduser
	 *            是否带用户
	 * @return
	 * @throws DaoException
	 * @throws SystemException
	 * @throws ServiceException
	 */
	public List<TreeNode> getComboDictTree() throws DaoException,
			SystemException, ServiceException {
		List<TreeNode> treeNodes = Lists.newArrayList();
		// 顶级机构

		List<equipname> names = Lists.newArrayList();

		treeNodes.add(createHostTreeNode());
		treeNodes.add(createNetTreeNode());
		treeNodes.add(createOfficeTreeNode());

		return treeNodes;

	}

	/**
	 * host TreeNode
	 * 
	 */
	private TreeNode createHostTreeNode() throws DaoException, SystemException,
			ServiceException {
		// 自定义属性 url
		TreeNode treeNode = new TreeNode(1+"", "主机设备", "图标");
		// 自定义属性 url
		Map<String, Object> attributes = Maps.newHashMap();
		attributes.put("page","host" );
		attributes.put("type", "null");
		treeNode.setAttributes(attributes);
	
		
		List<TreeNode> childrenTreeNodes = Lists.newArrayList();

		
		for (int i = 0; i < hostList.length; i += 2) {
			TreeNode node = new TreeNode(""+(100+i+1), hostList[i], "图标");
			// 自定义属性 url
			Map<String, Object> attributes2 = Maps.newHashMap();
			attributes2.put("page", "host");
			attributes2.put("type", hostList[i+1]);
			node.setAttributes(attributes2);
			childrenTreeNodes.add(node);
		}
		treeNode.setChildren(childrenTreeNodes);
		return treeNode;
	}

	/**
	 * host TreeNode
	 * 
	 */
	private TreeNode createOfficeTreeNode() throws DaoException,
			SystemException, ServiceException {
		// 自定义属性 url
		TreeNode treeNode = new TreeNode(2+"", "办公设备", "图标");
		// 自定义属性 url
		Map<String, Object> attributes = Maps.newHashMap();
		attributes.put("page","office" );
		attributes.put("type", "null");
		treeNode.setAttributes(attributes);
		
	
		List<TreeNode> childrenTreeNodes = Lists.newArrayList();
		for (int i = 0; i < officeList.length; i += 2) {
			TreeNode node = new TreeNode((200+i+1)+"", officeList[i], "图标");
			// 自定义属性 url
			Map<String, Object> attributes2 = Maps.newHashMap();
			attributes2.put("page", "office");
			attributes2.put("type", officeList[i+1]);
			node.setAttributes(attributes2);
			childrenTreeNodes.add(node);
		}
		treeNode.setChildren(childrenTreeNodes);
		return treeNode;
	}

	/**
	 * host TreeNode
	 * 
	 */
	private TreeNode createNetTreeNode() throws DaoException, SystemException,
			ServiceException {
		// 自定义属性 url
		TreeNode treeNode = new TreeNode(3+"", "网络设备", "图标");
		// 自定义属性 url
		Map<String, Object> attributes = Maps.newHashMap();
		attributes.put("page","net" );
		attributes.put("type", "null");
		treeNode.setAttributes(attributes);

		List<TreeNode> childrenTreeNodes = Lists.newArrayList();
		for (int i = 0; i < netList.length; i += 2) {
			TreeNode node = new TreeNode((300+i+1)+"", netList[i], "图标");
			// 自定义属性 url
			Map<String, Object> attributes2 = Maps.newHashMap();
			attributes2.put("page", "net");
			attributes2.put("type", netList[i+1]);
			node.setAttributes(attributes2);
			childrenTreeNodes.add(node);
		}
		treeNode.setChildren(childrenTreeNodes);
		return treeNode;
	}
}

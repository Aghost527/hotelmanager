/**
 *  Copyright (c) 2012-2015 dhc
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.dhc.modules.sys.entity;

import com.dhc.common.excel.annotation.Excel;
import com.dhc.common.orm.entity.BaseEntity;
import com.dhc.common.utils.io.ClobUtil;
import com.dhc.common.utils.jackson.ClobSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.Lists;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.sql.Clob;
import java.util.List;

/**
 * 下拉列表内容 
 */
@Entity
@Table(name = "t_sys_combodictionary")
// jackson标记不生成json对象的属性
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" , "handler","fieldHandler"})
@SuppressWarnings("serial")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ComboDict extends BaseEntity implements Serializable{
	/**
	 * 下拉内容
	 */
	@Excel(exportName = "选项名称", exportFieldWidth = 20)
	private String name;
	

	/**
	 * 下拉页面
	 */
	@Excel(exportName = "所属页面", exportFieldWidth = 20)
	private String page;
	/**
	 * 下拉类型
	 */
	@Excel(exportName = "所属类型", exportFieldWidth = 20)
	private String type;
	/**
	 * 下拉类型中文
	 */
	private String typename;

	
	@Column(name = "PAGE",length = 36)	
    public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	@Column(name = "TYPE",length = 36)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    @Column(name = "NAME",length = 36)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
    @Column(name = "TYPENAME",length = 36)
    	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
   
	
	
}

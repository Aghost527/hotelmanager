/**
 *  Copyright (c) 2012-2015 dhc
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.dhc.modules.sys.entity;

import com.dhc.common.orm.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Equipment的名字 转化json用
 */

public class equipname {

	private String title;// 分别指代标识和名字
	private String name;
	private long id;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 *name title id
	 **/
	public equipname(String name, String title,long id) {
		this.name = name;
		this.title = title;
		this.id =id;
	}
	public equipname(String name, String title) {
		this.name = name;
		this.title = title;
		
	}

}

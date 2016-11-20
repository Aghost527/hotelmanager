/**
 *  Copyright (c) 2012-2015 dhc
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.dhc.modules.sys.entity;

import com.dhc.common.excel.annotation.Excel;
import com.dhc.common.orm.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;

/**
 * 留言板 
 */
@Entity
@Table(name = "t_sys_bulletin")
// jackson标记不生成json对象的属性
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" , "handler","fieldHandler" })
@SuppressWarnings("serial")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Bulletin extends BaseEntity implements Serializable{

	/**
	 *留言板 标题 
	 */
	@Excel(exportName="标题", exportFieldWidth = 20)
	private String title;
    
	/**
	 * 留言类型
	 */
    @Excel(exportName="留言类型", exportFieldWidth = 20)
	private String type;
  


	/**
	 * 留言内容  @Transient
	 */
    @Excel(exportName="留言内容", exportFieldWidth = 100)
	private String content;


	public Bulletin() {
		super();
	}

    @Column(name = "TITLE",length = 255)
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

   
	
	 @Column(name = "TYPE",length = 255)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 留言内容
	 */
//	@Lob
	//自定义Clob
//	@JsonSerialize(using = ClobSerializer.class)
    @Column(name = "CONTENT",columnDefinition = "text(4000)")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 用于view显示
	 * @return
	 */


	
     
 
    
	
	
}

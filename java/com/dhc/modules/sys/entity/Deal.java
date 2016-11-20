/**
 *  Copyright (c) 2012-2013 http://www.eryansky.com
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); 
 */
package com.dhc.modules.sys.entity;

import com.dhc.common.orm.entity.BaseEntity;
import com.dhc.modules.sys._enum.LogType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统操作日志Entity
 *  
 * @date: 13-11-27 下午9:18
 */
@Entity
@Table(name = "T_SYS_LOG")
// jackson标记不生成json对象的属性
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Deal extends BaseEntity implements Serializable {

    /**
     * Deal Table
     */
    private String activity;
    /**
     * Activity
     */
    private String joinLevel;
    /**
     * Join Level
     */
    private Integer capacity;
    /**
     * Capacity
     */
    private Integer beginDate;
    /**
     * Begin Date
     */
    private Integer endDate;
    /**
     * End Date
     */
    private Integer dealNo;
    /**
     * Deal Number
     */

    public Deal() {
    }
    @Column(name = ”activity”,length = 255)
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
    @Column(name = ”joinLevel”,length = 255)

	public String getJoinLevel() {
		return joinLevel;
	}
	public void setJoinLevel(String joinLevel) {
		this.joinLevel = joinLevel;
	}
    @Column(name = ”capacity”,length = 255)

	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
    @Column(name = ”beginDate”,length = 255)

	public Integer getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Integer beginDate) {
		this.beginDate = beginDate;
	}
    @Column(name = ”endDate”,length = 255)

	public Integer getEndDate() {
		return endDate;
	}
	public void setEndDate(Integer endDate) {
		this.endDate = endDate;
	}
    @Column(name = ”dealNo”,length = 255)

	public Integer getDealNo() {
		return dealNo;
	}
	public void setDealNo(Integer dealNo) {
		this.dealNo = dealNo;
	}
}
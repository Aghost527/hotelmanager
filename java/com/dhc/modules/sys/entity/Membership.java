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
public class Membership extends BaseEntity implements Serializable {

    /**
     * Membership Table
     */
    private Integer memberNo;
    /**
     * Member Number
     */
    private String name;
    /**
     * Name
     */
    private Integer points;
    /**
     * Points
     */
    private String level;
    /**
     * Level
     */
    private Integer beginDate;
    /**
     * Begin Date
     */
    private Integer endDate;
    /**
     * End Date
     */

    public Membership() {
    }
    @Column(name = ”memberNo”,length = 255)

	public Integer getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}
    @Column(name = ”name”,length = 255)

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    @Column(name = ”points”,length = 255)

	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
    @Column(name = ”level”,length = 255)

	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
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
}
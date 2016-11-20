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
public class Maintains extends BaseEntity implements Serializable {

    /**
     * Maintains Table
     */
    private String record;
    /**
     * Record
     */
    private Integer staffNo;
    /**
     * Staff Number
     */
    private Integer roomNo;
    /**
     * Room Number
     */
    private String linens;
    /**
     * Linens
     */
    private Integer lQty;
    /**
     * L-qty
     */
    private String lType;
    /**
     * L-type
     */
    private String reusable;
    /**
     * Reusable
     */
    private Double cost;
    /**
     * Cost
     */
    private Integer date;
    /**
     * Date
     */

    public Maintains() {
    }
    @Column(name = ”record”,length = 255)
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
    @Column(name = ”staffNo”,length = 255)

	public Integer getStaffNo() {
		return staffNo;
	}
	public void setStaffNo(Integer staffNo) {
		this.staffNo = staffNo;
	}
    @Column(name = ”roomNo”,length = 255)

	public Integer getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(Integer roomNo) {
		this.roomNo = roomNo;
	}
    @Column(name = ”linens”,length = 255)

	public String getLinens() {
		return linens;
	}
	public void setLinens(String linens) {
		this.linens = linens;
	}
    @Column(name = ”lQty”,length = 255)

	public Integer getlQty() {
		return lQty;
	}
	public void setlQty(Integer lQty) {
		this.lQty = lQty;
	}
    @Column(name = ”lType”,length = 255)

	public String getlType() {
		return lType;
	}
	public void setlType(String lType) {
		this.lType = lType;
	}
    @Column(name = ”reusable”,length = 255)

	public String getReusable() {
		return reusable;
	}
	public void setReusable(String reusable) {
		this.reusable = reusable;
	}
    @Column(name = ”cost”,length = 255)

	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
    @Column(name = ”date”,length = 255)

	public Integer getDate() {
		return date;
	}
	public void setDate(Integer date) {
		this.date = date;
	}
	
}
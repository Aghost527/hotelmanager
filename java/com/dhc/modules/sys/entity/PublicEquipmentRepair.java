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
@Table(name = "T_SYS_PUBLICEQUIPMENTREPAIR")
// jackson标记不生成json对象的属性
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class PublicEquipmentRepair extends BaseEntity implements Serializable {

    /**
     * Public Equipment Repair Table
     */
    private String record;
    /**
     * Record
     */
    private Integer staffNo;
    /**
     * Staff Number
     */
    private String equipName;
    /**
     * Equipment Name
     */
    private Integer equipID;
    /**
     * Equipment ID
     */
    private Double cost;
    /**
     * Cost
     */
    private Integer date;
    /**
     * Date
     */

    public PublicEquipmentRepair() {
    }
    
    @Column(name = "record",length = 255)
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	
	@Column(name = "staffNo",length = 255)
	public Integer getStaffNo() {
		return staffNo;
	}
	public void setStaffNo(Integer staffNo) {
		this.staffNo = staffNo;
	}
	
	@Column(name = "equipName",length = 255)
	public String getEquipName() {
		return equipName;
	}
	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}
	
	@Column(name = "equipID",length = 255)
	public Integer getEquipID() {
		return equipID;
	}
	public void setEquipID(Integer equipID) {
		this.equipID = equipID;
	}
	
	@Column(name = "cost",length = 255)
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	@Column(name = "date",length = 255)
	public Integer getDate() {
		return date;
	}
	public void setDate(Integer date) {
		this.date = date;
	}
	
}
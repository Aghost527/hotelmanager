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
@Table(name = "T_SYS_ROOMS")
// jackson标记不生成json对象的属性
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Rooms extends BaseEntity implements Serializable {

    /**
     * Rooms Table
     */
    private Integer roomNo;
    /**
     * Room Number
     */
    private Double inRoomBill;
    /**
     * In-room Bill
     */
    private Integer capacity;
    /**
     * Capacity
     */
    private Integer bedsNo;
    /**
     * Beds Number
     */
    private String type;
    /**
     * Type
     */
//    private Integer status;
//    /**
//     * Status
//     */

    public Rooms() {
    }
    
    @Column(name = "roomNo",length = 255)
	public Integer getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(Integer roomNo) {
		this.roomNo = roomNo;
	}
	
	@Column(name = "inRoomBill",length = 255)
	public Double getInRoomBill() {
		return inRoomBill;
	}
	public void setInRoomBill(Double inRoomBill) {
		this.inRoomBill = inRoomBill;
	}
	
	@Column(name = "capacity",length = 255)
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	
	@Column(name = "bedsNo",length = 255)
	public Integer getBedsNo() {
		return bedsNo;
	}
	public void setBedsNo(Integer bedsNo) {
		this.bedsNo = bedsNo;
	}
	
	@Column(name = "type",length = 255)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
//	@Column(name = "TITLE",length = 255)
//	public Integer getStatus() {
//		return status;
//	}
//	public void setStatus(Integer status) {
//		this.status = status;
//	}

}
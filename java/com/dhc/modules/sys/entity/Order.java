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
@Table(name = "T_SYS_ORDER")
// jackson标记不生成json对象的属性
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Order extends BaseEntity implements Serializable {

    /**
     * Order Table
     */
    private Integer orderNo;
    /**
     * Order Number
     */
    private Integer roomNo;
    /**
     * Room Number
     */
    private String customer;
    /**
     * Customer
     */
    private Integer checkInDate;
    /**
     * Check-in Date
     */
    private Integer checkOutDate;
    /**
     * Check-out Date
     */
    private Double price;
    /**
     * Price
     */
    private Integer pointCharge;
    /**
     * Point Charge
     */
    private Integer cashierNo;
    /**
     * Cashier Number
     */

    public Order() {
    }

    @Column(name = "orderNo",length = 255)
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	@Column(name = "roomNo",length = 255)
	public Integer getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(Integer roomNo) {
		this.roomNo = roomNo;
	}
	
	@Column(name = "customer",length = 255)
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	@Column(name = "checkInDate",length = 255)
	public Integer getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(Integer checkInDate) {
		this.checkInDate = checkInDate;
	}
	
	@Column(name = "checkOutDate",length = 255)
	public Integer getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(Integer checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	
	@Column(name = "price",length = 255)
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Column(name = "pointCharge",length = 255)
	public Integer getPointCharge() {
		return pointCharge;
	}
	public void setPointCharge(Integer pointCharge) {
		this.pointCharge = pointCharge;
	}
	
	@Column(name = "cashierNo",length = 255)
	public Integer getCashierNo() {
		return cashierNo;
	}
	public void setCashierNo(Integer cashierNo) {
		this.cashierNo = cashierNo;
	}

}
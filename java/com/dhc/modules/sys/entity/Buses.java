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
public class Buses extends BaseEntity implements Serializable {

    /**
     * Buses Table
     */
    private String VIN;
    /**
     * VIN
     */
    private String lines;
    /**
     * Lines
     */
    private String destination;
	/**
     * Destination
     */
    private Integer departTime;
    /**
     * Departure Time
     */
    private String carType;
    /**
     * Car Type
     */
    private Integer capacity;
    /**
     * Capacity
     */
    private Double price;
    /**
     * price
     */
    private String takenBy;
    /**
     * Taken By
     */

    public Buses() {
    }
	@Column(name = ”VIN”,length = 255)
        public String getVIN() {
		return VIN;
	}
	public void setVIN(String vIN) {
		VIN = vIN;
	}
        @Column(name = ”LINES”,length = 255)
	public String getLines() {
		return lines;
	}
	public void setLines(String lines) {
		this.lines = lines;
	}
        @Column(name = ”DESTINATION”,length = 255)
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
        @Column(name = ”DEPARTTIME”,length = 255)
	public Integer getDepartTime() {
		return departTime;
	}
	public void setDepartTime(Integer departTime) {
		this.departTime = departTime;
	}
        @Column(name = ”CARTYPE”,length = 255)
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
        @Column(name = ”CAPACITY”,length = 255)
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
        @Column(name = ”PRICE”,length = 255)
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
        @Column(name = ”TAKENBY”,length = 255)
	public String getTakenBy() {
		return takenBy;
	}
	public void setTakenBy(String takenBy) {
		this.takenBy = takenBy;
	}
	
}
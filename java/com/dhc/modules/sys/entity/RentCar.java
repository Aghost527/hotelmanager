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
@Table(name = "T_SYS_RENTCAR")
// jackson标记不生成json对象的属性
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class RentCar extends BaseEntity implements Serializable {

    /**
     * Rent Car Table
     */
    private String VIN;
    /**
     * VIN
     */
    private String company;
	/**
     * Company
     */
    private String carType;
    /**
     * Car Type
     */
    private Integer capacity;
    /**
     * Capacity
     */
    private Double priceTotal;
    /**
     * Total Price
     */
    private Integer rentDate;
    /**
     * Rent Date
     */
    private Integer returnDate;
    /**
     * Return Date
     */
    private String rentPlace;
    /**
     * Rent place
     */
    private String returnPlace;
    /**
     * Return Place
     */
    private String rentBy;
    /**
     * Rent By
     */

    public RentCar() {
    }
    
    @Column(name = "VIN",length = 255)
    public String getVIN() {
		return VIN;
	}
	public void setVIN(String vIN) {
		VIN = vIN;
	}
	
	@Column(name = "company",length = 255)
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	@Column(name = "carType",length = 255)
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	
	@Column(name = "capacity",length = 255)
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	
	@Column(name = "priceTotal",length = 255)
	public Double getPriceTotal() {
		return priceTotal;
	}
	public void setPriceTotal(Double priceTotal) {
		this.priceTotal = priceTotal;
	}
	
	@Column(name = "rentDate",length = 255)
	public Integer getRentDate() {
		return rentDate;
	}
	public void setRentDate(Integer rentDate) {
		this.rentDate = rentDate;
	}
	
	@Column(name = "returnDate",length = 255)
	public Integer getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Integer returnDate) {
		this.returnDate = returnDate;
	}
	
	@Column(name = "rentPlace",length = 255)
	public String getRentPlace() {
		return rentPlace;
	}
	public void setRentPlace(String rentPlace) {
		this.rentPlace = rentPlace;
	}
	
	@Column(name = "returnPlace",length = 255)
	public String getReturnPlace() {
		return returnPlace;
	}
	public void setReturnPlace(String returnPlace) {
		this.returnPlace = returnPlace;
	}
	
	@Column(name = "rentBy",length = 255)
	public String getRentBy() {
		return rentBy;
	}
	public void setRentBy(String rentBy) {
		this.rentBy = rentBy;
	}
}
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
@Table(name = "T_SYS_STAFF")
// jackson标记不生成json对象的属性
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Staff extends BaseEntity implements Serializable {

    /**
     * Staff Table
     */
    private Integer ssn;
    /**
     * SSN
     */
    private Integer staffNo;
    /**
     * Staff Number
     */
    private String position;
    /**
     * Position
     */
    private Integer workYear;
    /**
     * Work-year
     */
    private String name;
    /**
     * name
     */
    private String address;
    /**
     * Address
     */
    private Integer phone;
    /**
     * Phone
     */
    private String gender;
    /**
     * Gender
     */
    private Double salary;
    /**
     * Salary
     */

    public Staff() {
    }
    
    @Column(name = "ssn",length = 255)
	public Integer getSsn() {
		return ssn;
	}
	public void setSsn(Integer ssn) {
		this.ssn = ssn;
	}
	
	@Column(name = "staffNo",length = 255)
	public Integer getStaffNo() {
		return staffNo;
	}
	public void setStaffNo(Integer staffNo) {
		this.staffNo = staffNo;
	}
	
	@Column(name = "position",length = 255)
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	@Column(name = "workYear",length = 255)
	public Integer getWorkYear() {
		return workYear;
	}
	public void setWorkYear(Integer workYear) {
		this.workYear = workYear;
	}
	
	@Column(name = "name",length = 255)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "address",length = 255)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "phone",length = 255)
	public Integer getPhone() {
		return phone;
	}
	public void setPhone(Integer phone) {
		this.phone = phone;
	}
	
	@Column(name = "gender",length = 255)
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Column(name = "salary",length = 255)
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
}
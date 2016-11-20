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
public class Customers extends BaseEntity implements Serializable {

    /**
     * Customers Table
     */
    private Integer ssn;
    /**
     * SSN
     */
    private String name;
    /**
     * Name
     */
    private String address;
    /**
     * Address
     */
    private String gender;
    /**
     * Gender
     */
    private Integer phone;
    /**
     * Phone
     */
    private Integer dob;
    /**
     * Date of Birth
     */
    private Integer cardNo;
    /**
     * Card Number
     */
    private Integer memberNo;
    /**
     * Member Number
     */

    public Customers() {
    }
        @Column(name = ”SSN”,length = 255)
	public Integer getSsn() {
		return ssn;
	}
	public void setSsn(Integer ssn) {
		this.ssn = ssn;
	}
        @Column(name = ”NAME”,length = 255)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
        @Column(name = ”ADDRESS”,length = 255)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
        @Column(name = ”GENDER”,length = 255)
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
        @Column(name = ”PHONE”,length = 255)
	public Integer getPhone() {
		return phone;
	}
	public void setPhone(Integer phone) {
		this.phone = phone;
	}
        @Column(name = ”DOB”,length = 255)
	public Integer getDob() {
		return dob;
	}
	public void setDob(Integer dob) {
		this.dob = dob;
	}
        @Column(name = ”CARDNO”,length = 255)
	public Integer getCardNo() {
		return cardNo;
	}
	public void setCardNo(Integer cardNo) {
		this.cardNo = cardNo;
	}
        @Column(name = ”MEMBERNO”,length = 255)
	public Integer getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}

}
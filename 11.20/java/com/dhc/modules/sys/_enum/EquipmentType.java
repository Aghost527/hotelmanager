/**
 *  Copyright (c) 2012-2015 dhc
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.dhc.modules.sys._enum;

/**
 * 机构类型
 *  
 *   
 * @version 1.0
 */
public enum EquipmentType {
    /** 办公设备(0) */
    office(0, "办公设备"),
    /** 网络设备(1) */
    network(1, "网络设备"),
    /** 生产设备(2) */
    product(2, "生产设备");

	/**
	 * 值 Integer型
	 */
	private final Integer value;
	/**
	 * 描述 String型
	 */
	private final String description;

	EquipmentType(Integer value, String description) {
		this.value = value;
		this.description = description;
	}

	/**
	 * 获取值
	 * @return value
	 */
	public Integer getValue() {
		return value;
	}

	/**
     * 获取描述信息
     * @return description
     */
	public String getDescription() {
		return description;
	}

	public static EquipmentType getEquipmentType(Integer value) {
		if (null == value)
			return null;
		for (EquipmentType _enum : EquipmentType.values()) {
			if (value.equals(_enum.getValue()))
				return _enum;
		}
		return null;
	}
	
	public static EquipmentType getEquipmentType(String description) {
		if (null == description)
			return null;
		for (EquipmentType _enum : EquipmentType.values()) {
			if (description.equals(_enum.getDescription()))
				return _enum;
		}
		return null;
	}

}
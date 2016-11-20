package com.dhc.modules.sys.entity;

import com.dhc.common.orm.entity.AutoEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 系统配置参数
 *   
 */
@Entity
@Table(name = "T_SYS_CONFIG")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Config extends AutoEntity {
    /**
     * 属性标识
     */
    private String code;
    /**
     * 属性值
     */
    private String value;
    /**
     * 备注
     */
    private String remark;

    public Config() {
    }

    public Config(String code, String value, String remark) {
        this.code = code;
        this.value = value;
        this.remark = remark;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Column(length = 4096)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

/**
 *  Copyright (c) 2012-2015 dhc
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.dhc.modules.sys.utils;

import com.dhc.common.spring.SpringContextHolder;
import com.dhc.common.utils.ConvertUtils;
import com.dhc.common.utils.collections.Collections3;
import com.dhc.modules.sys.entity.Organ;
import com.dhc.modules.sys.service.OrganManager;

import java.util.List;

/**
 *  
 *   
 */
public class OrganUtils {

    private static OrganManager organManager = SpringContextHolder.getBean(OrganManager.class);

    /**
     * 根据机构ID查找机构名称
     * @param organId 机构ID
     * @return
     */
    public static String getOrganName(Long organId){
        if(organId != null){
            Organ organ = organManager.loadById(organId);
            if(organ != null){
                return organ.getName();
            }
        }
        return null;
    }

    /**
     * 根据机构ID集合转换成机构名称
     * @param organIds 机构ID集合
     * @return
     */
    public static String getOrganNames(List<Long> organIds){
        if(Collections3.isNotEmpty(organIds)){
            List<Organ> list = organManager.findOrgansByIds(organIds);
            return ConvertUtils.convertElementPropertyToString(list, "name", ", ");
        }
        return null;
    }
}

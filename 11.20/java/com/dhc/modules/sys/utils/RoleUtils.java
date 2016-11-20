/**
 *  Copyright (c) 2012-2015 dhc
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.dhc.modules.sys.utils;

import com.dhc.common.spring.SpringContextHolder;
import com.dhc.common.utils.ConvertUtils;
import com.dhc.common.utils.collections.Collections3;
import com.dhc.modules.sys.entity.Role;
import com.dhc.modules.sys.service.RoleManager;

import java.util.List;

/**
 *  
 *   
 */
public class RoleUtils {

    private static RoleManager roleManager = SpringContextHolder.getBean(RoleManager.class);

    /**
     * 根据角色ID查找角色名称
     * @param roleId 角色ID
     * @return
     */
    public static String getRoleName(Long roleId){
        if(roleId != null){
            Role Role = roleManager.loadById(roleId);
            if(Role != null){
                return Role.getName();
            }
        }
        return null;
    }

    /**
     * 根据角色ID查找角色名称集合
     * @param roleIds 角色ID集合
     * @return
     */
    public static String getRoleNames(List<Long> roleIds){
        if(Collections3.isNotEmpty(roleIds)){
            List<Role> list = roleManager.findRolesByIds(roleIds);
            return ConvertUtils.convertElementPropertyToString(list, "name", ", ");
        }
        return null;
    }
}

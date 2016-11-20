/**
 *  Copyright (c) 2012-2015 dhc
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.dhc.modules.sys.utils;

import com.dhc.common.spring.SpringContextHolder;
import com.dhc.common.utils.ConvertUtils;
import com.dhc.common.utils.collections.Collections3;
import com.dhc.modules.sys.entity.User;
import com.dhc.modules.sys.service.UserManager;

import java.util.List;

/**
 *  
 *   
 */
public class UserUtils {

    private static UserManager userManager = SpringContextHolder.getBean(UserManager.class);

    /**
     * 根据userId查找用户姓名
     * @param userId 用户ID
     * @return
     */
    public static String getUserName(Long userId){
        if(userId != null){
            User user = userManager.loadById(userId);
            if(user != null){
                return user.getName();
            }
        }
        return null;
    }

    /**
     * 根据userId查找用户姓名
     * @param userIds 用户ID集合
     * @return
     */
    public static String getUserNames(List<Long> userIds){
        if(Collections3.isNotEmpty(userIds)){
            List<User> list = userManager.findUsersByIds(userIds);
            return ConvertUtils.convertElementPropertyToString(list, "name", ", ");
        }
        return null;
    }
}

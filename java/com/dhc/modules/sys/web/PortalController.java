/**
 *  Copyright (c) 2012-2015 dhc
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.dhc.modules.sys.web;

import com.dhc.common.model.Result;
import com.dhc.common.web.springmvc.SimpleController;
import com.dhc.common.web.utils.WebUtils;
import com.dhc.core.security.SecurityUtils;
import com.dhc.core.security.SessionInfo;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Portal主页门户管理
 *
 *  
 * 
 */
@Controller
@RequestMapping(value = "${adminPath}/portal")
public class PortalController extends SimpleController {

    @RequestMapping("")
    public ModelAndView portal() {
        ModelAndView modelAnView = new ModelAndView("layout/portal");
        return modelAnView;
    }


    /**
     * 我的通知
     *
     * @return
     */
    @RequestMapping("notice")
    public ModelAndView notice() {
        ModelAndView modelAnView = new ModelAndView("layout/portal-notice");       

        return modelAnView;
    }





}

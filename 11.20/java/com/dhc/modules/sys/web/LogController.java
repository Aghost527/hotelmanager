/**
 *  Copyright (c) 2012-2015 dhc
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.dhc.modules.sys.web;

import com.dhc.common.exception.ActionException;
import com.dhc.common.model.Combobox;
import com.dhc.common.model.Result;
import com.dhc.common.orm.hibernate.EntityManager;
import com.dhc.common.utils.StringUtils;
import com.dhc.common.utils.io.PropertiesLoader;
import com.dhc.common.web.springmvc.BaseController;
import com.dhc.modules.sys._enum.LogType;
import com.dhc.modules.sys.entity.Log;
import com.dhc.modules.sys.service.LogManager;
import com.dhc.utils.AppConstants;
import com.dhc.utils.SelectType;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 日志
 *  
 *   
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/log")
public class LogController
        extends BaseController<Log,Long> {

    @Autowired
    private LogManager logManager;

    @Override
    public EntityManager<Log, Long> getEntityManager() {
        return logManager;
    }

    @RequestMapping(value = {""})
    public String list() {
        return "modules/sys/log";
    }

    /**
     * 清空所有日志
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"removeAll"})
    @ResponseBody
    public Result removeAll() throws Exception {
        logManager.removeAll();
        Result result = Result.successResult();
        return result;
    }

    /**
     * 日志类型下拉列表.
     */
    @RequestMapping(value = {"logTypeCombobox"})
    @ResponseBody
    public List<Combobox> logTypeCombobox(String selectType) throws Exception {
        List<Combobox> cList = Lists.newArrayList();
        //为combobox添加  "---全部---"、"---请选择---"
        if (!StringUtils.isBlank(selectType)) {
            SelectType s = SelectType.getSelectTypeValue(selectType);
            if (s != null) {
                Combobox selectCombobox = new Combobox("", s.getDescription());
                cList.add(selectCombobox);
            }
        }

        LogType[] lts = LogType.values();
        for (int i = 0; i < lts.length; i++) {
            Combobox combobox = new Combobox();
            combobox.setValue(lts[i].getValue().toString());
            combobox.setText(lts[i].getDescription());
            cList.add(combobox);
        }
        return cList;
    }
    /**
     * 清理过期日志
     */
    //@Scheduled(cron="*/5 * * * * *")
    @Scheduled(cron="0 0 1 * * ?")  //每天1点触发执行
    public void clearInvalidLog(){
        logger.info("定时任务...开始：清理过期日志");
        int logKeepTime = AppConstants.getLogKeepTime();
        logManager.clearInvalidLog(logKeepTime);
        logger.info("定时任务...结束：清理过期日志");
    }

}

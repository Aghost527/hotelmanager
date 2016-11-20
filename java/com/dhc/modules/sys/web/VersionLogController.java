/**
 *  Copyright (c) 2014 
 *
 */
package com.dhc.modules.sys.web;

import com.dhc.common.model.Datagrid;
import com.dhc.common.model.Result;
import com.dhc.common.orm.Page;
import com.dhc.common.orm.PropertyFilter;
import com.dhc.common.orm.hibernate.EntityManager;
import com.dhc.common.orm.hibernate.HibernateWebUtils;
import com.dhc.common.web.springmvc.BaseController;
import com.dhc.modules.sys.entity.VersionLog;
import com.dhc.modules.sys.service.VersionLogManager;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(value = "${adminPath}/sys/versionLog")
public class VersionLogController extends BaseController<VersionLog, Long> {

    @Autowired
    private VersionLogManager versionLogManager;

    @Override
    public EntityManager<VersionLog, Long> getEntityManager() {
        return versionLogManager;
    }


    @RequestMapping(value = {""})
    public String list() {
        return "modules/sys/versionLog";
    }

    @RequestMapping(value = {"input"})
    public ModelAndView input(@ModelAttribute("model") VersionLog model,ModelAndView modelAndView) {
        modelAndView = new ModelAndView("modules/sys/versionLog-input");
//        if(model.getId() == null){
//            modelAndView.addObject("nowTime", DateUtils.getDate(DateUtils.DATE_TIME_FORMAT));
//        }
        return modelAndView;
    }


    /**
     * 数据列表
     * @param request
     * @param startTIme 更新时间 - 起始时间
     * @param endTime 更新时间 - 截止时间
     * @return
     */
    @RequestMapping(value = {"_datagrid"})
    @ResponseBody
    public Datagrid<VersionLog> datagrid(HttpServletRequest request,Date startTIme,Date endTime) {
        // 自动构造属性过滤器
        List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(request);
        Page<VersionLog> p = new Page<VersionLog>(request);

        Criterion[] criterions = getEntityManager().getCriterionsByFilter(filters);
        if (startTIme != null && endTime != null) {
            Criterion criterion = Restrictions.between("updateTime", startTIme, endTime);
            criterions = ArrayUtils.add(criterions, 0, criterion);
        }

        p = getEntityManager().findPageByCriteria(p, criterions);
        Datagrid<VersionLog> datagrid = new Datagrid<VersionLog>(p.getTotalCount(), p.getResult());
        return datagrid;
    }

    /**
     * 清空所有数据
     * @return
     */
    @RequestMapping(value = {"removeAll"})
    @ResponseBody
    public Result removeAll(){
        versionLogManager.removeAll();
        Result result = Result.successResult();
        return result;
    }
}
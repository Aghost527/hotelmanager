/**
 *  Copyright (c) 2012-2015 dhc
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.dhc.modules.sys.web;

import com.dhc.common.excel.ExcelUtil;
import com.dhc.common.excel.ExportExcel;
import com.dhc.common.model.Datagrid;
import com.dhc.common.model.Result;
import com.dhc.common.orm.Page;
import com.dhc.common.orm.PropertyFilter;
import com.dhc.common.orm.hibernate.EntityManager;
import com.dhc.common.orm.hibernate.HibernateWebUtils;
import com.dhc.common.utils.DateUtils;
import com.dhc.common.web.springmvc.BaseController;
import com.dhc.common.web.springmvc.SpringMVCHolder;
import com.dhc.common.web.utils.WebUtils;
import com.dhc.modules.sys.entity.Maintains;
import com.dhc.modules.sys.service.MaintainsManager;
import com.dhc.modules.sys.service.UserManager;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * Maintains管理Controller层.
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping(value = "${adminPath}/sys/maintains")
public class MaintainsController extends BaseController<Maintains, Long> {

	@Autowired
	private UserManager userManager;

	public final static String SSSION_SEARCH = "Maintains_SEARCH";

	@Autowired
	private MaintainsManager maintainsManager;
 

	@Override
	public EntityManager<Maintains, Long> getEntityManager() {
		return maintainsManager;
	}

	@RequestMapping(value = { "" })
	public String list() {
		return "modules/sys/maintains";
	}

	@RequestMapping(value = { "input" })
	public String input() {
		return "modules/sys/maintains-input";
	}


	/**
	 * 初始化数据绑定
	 * 
	 * @param binder
	 */
	@Override
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		Object annotationValue = AnnotationUtils.getValue(AnnotationUtils
				.findAnnotation(entityClass, JsonIgnoreProperties.class));
		if (annotationValue != null) {
			String[] jsonIgnoreProperties = (String[]) annotationValue;
			binder.setDisallowedFields(jsonIgnoreProperties);
		}

		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(DateUtils.parseDate(text));
			}
		});
	}

 

	@RequestMapping(value = { "_save" })
	@ResponseBody
	public Result save(@ModelAttribute("model") Maintains model) {
		Result result;
		// 名称重复校验
//		Maintains checkMaintains = maintainsManager.findUniqueBy("title",
//				model.getTitle());
//		if (checkMaintains != null
//				&& !checkMaintains.getId().equals(model.getId())) {
//			result = new Result(Result.WARN, "标题 [" + model.getTitle()
//					+ "]已存在,请写一个不同的标题", "title");
//			return result;
//		}
		maintainsManager.saveEntity(model);
		result = Result.successResult();
		return result;
	}

	@Override
	@RequestMapping(value = { "datagrid" })
	@ResponseBody
	public Datagrid datagrid() {
		// 自动构造属性过滤器
		List<PropertyFilter> filters = HibernateWebUtils
				.buildPropertyFilters(SpringMVCHolder.getRequest());
		Page<Maintains> p = new Page<Maintains>(SpringMVCHolder.getRequest());
		p = getEntityManager().findPage(p, filters);

		Datagrid<Maintains> dg = new Datagrid<Maintains>(p.getTotalCount(),
				p.getResult());
		return dg;
	}
}

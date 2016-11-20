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
import com.dhc.modules.sys.entity.Bulletin;
import com.dhc.modules.sys.service.BulletinManager;
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
 * Bulletin管理Controller层.
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping(value = "${adminPath}/sys/bulletin")
public class BulletinController extends BaseController<Bulletin, Long> {

	@Autowired
	private UserManager userManager;

	public final static String SSSION_SEARCH = "Bulletin_SEARCH";

	@Autowired
	private BulletinManager bulletinManager;
 

	@Override
	public EntityManager<Bulletin, Long> getEntityManager() {
		return bulletinManager;
	}

	@RequestMapping(value = { "" })
	public String list() {
		return "modules/sys/bulletin";
	}

	@RequestMapping(value = { "input" })
	public String input() {
		return "modules/sys/bulletin-input";
	}

	@RequestMapping(value = { "view" })
	public String view(@ModelAttribute("model") Bulletin Bulletin) {
		return "modules/sys/bulletin-view";
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
	public Result save(@ModelAttribute("model") Bulletin model) {
		Result result;
		// 名称重复校验
		Bulletin checkBulletin = bulletinManager.findUniqueBy("title",
				model.getTitle());
		if (checkBulletin != null
				&& !checkBulletin.getId().equals(model.getId())) {
			result = new Result(Result.WARN, "标题 [" + model.getTitle()
					+ "]已存在,请写一个不同的标题", "title");
			return result;
		}
		bulletinManager.saveEntity(model);
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
		Page<Bulletin> p = new Page<Bulletin>(SpringMVCHolder.getRequest());
		p = getEntityManager().findPage(p, filters);

		Datagrid<Bulletin> dg = new Datagrid<Bulletin>(p.getTotalCount(),
				p.getResult());
		return dg;
	}

	@RequestMapping(value = { "import" })
	public String _import() {
		return "modules/sys/bulletin-import";
	}

	/**
	 * Excel导入
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "importExcel" })
	@ResponseBody
	public Result importExcel(
			@RequestParam(value = "filedata") MultipartFile file) {
		Result result = null;
		List<Bulletin> bulletins = null;
		List<Bulletin> bulletins_new = Lists.newArrayList();
		try {
			if (file != null) {
				bulletins = (List<Bulletin>) ExcelUtil
						.importExcelByIs(file.getInputStream(),
								Bulletin.class);
				if (bulletins != null && bulletins.size() > 0) {
					for (Bulletin bulletin : bulletins) {
						// 缺省信息检验
				  if (bulletinManager.findUniqueBy("title",
								bulletin.getTitle()) != null) {

							logger.warn("[{}]已存在.", bulletin.getTitle());

						} else if (bulletinManager.findUniqueBy("title",
								bulletin.getTitle()) != null) {

							logger.warn("[{}]已存在.", bulletin.getTitle());

						}

						else {
							bulletin.setVersion(0);
							bulletins_new.add(bulletin);
						}
					}
				}
				bulletinManager.saveOrUpdate(bulletins_new);
				result = new Result(Result.SUCCESS, "已导入"
						+ bulletins_new.size() + "条数据.", null);

			} else {
				result = new Result(Result.WARN, "未上传任何文件.", null);
			}

		} catch (IOException e) {
			logger.error("文件导入失败 " + e.getMessage(), e);
			result = new Result(Result.ERROR, "文件导入失败", null);
		} catch (Exception e) {
			logger.error("文件格式不正确，导入失败 " + e.getMessage(), e);
			result = new Result(Result.ERROR, "文件格式不正确，导入失败", null);
		} finally {
			return result;
		}
	}

	/**
	 * Excel导出
	 */
	// @SuppressWarnings("unchecked")
	@RequestMapping(value = { "exportExcel" })
	public void exportExcel(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		// 生成提示信息，
		final String fileName = "留言版信息.xls";
		OutputStream outStream = null;
		try {
			// 设置文件类型
			response.setContentType(WebUtils.EXCEL_TYPE);
			// 设置下载弹出对话框
			WebUtils.setDownloadableHeader(request, response, fileName);
			// 从session中获取查询参数
			List<PropertyFilter> sessionFilters = (List<PropertyFilter>) session
					.getAttribute(SSSION_SEARCH);
			List<Bulletin> Bulletins = null;
			if (sessionFilters != null) {
				Bulletins = bulletinManager.find(sessionFilters, "id", Page.ASC);
			} else {
				Bulletins = bulletinManager.getAll("id", Page.ASC);
			}

			HSSFWorkbook workbook = new ExportExcel<Bulletin>().exportExcel(
					"导出信息", Bulletin.class, Bulletins);
			outStream = response.getOutputStream();
			workbook.write(outStream);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				outStream.flush();
				outStream.close();
			} catch (IOException e) {

			}
		}
	}

 

	 

}

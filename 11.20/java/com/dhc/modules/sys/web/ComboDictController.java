/**
 *  Copyright (c) 2012-2015 dhc
 *
 */
package com.dhc.modules.sys.web;

import com.dhc.common.excel.ExcelUtil;
import com.dhc.common.excel.ExportExcel;
import com.dhc.common.exception.DaoException;
import com.dhc.common.exception.ServiceException;
import com.dhc.common.exception.SystemException;
import com.dhc.common.model.Datagrid;
import com.dhc.common.model.Result;
import com.dhc.common.model.TreeNode;
import com.dhc.common.orm.Page;
import com.dhc.common.orm.PropertyFilter;
import com.dhc.common.orm.entity.StatusState;
import com.dhc.common.orm.hibernate.EntityManager;
import com.dhc.common.orm.hibernate.HibernateWebUtils;
import com.dhc.common.orm.hibernate.Parameter;
import com.dhc.common.utils.DateUtils;
import com.dhc.common.utils.StringUtils;
import com.dhc.common.web.springmvc.BaseController;
import com.dhc.common.web.springmvc.SpringMVCHolder;
import com.dhc.common.web.utils.WebUtils;
import com.dhc.modules.sys.entity.ComboDict;
import com.dhc.modules.sys.entity.equipname;
import com.dhc.modules.sys.service.ComboDictManager;
import com.dhc.utils.SelectType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.apache.commons.collections.ListUtils;
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
import java.util.Map;

/**
 * comboDict管理Controller层.
 * 
 * 
 * 
 */
@SuppressWarnings("serial")
@Controller
@RequestMapping(value = "${adminPath}/sys/comboDict")
public class ComboDictController extends BaseController<ComboDict, Long> {

	public final static String SSSION_SEARCH = "ComboDict_SEARCH";

	@Autowired
	private ComboDictManager comboDictManager;

	@Override
	public EntityManager<ComboDict, Long> getEntityManager() {
		return comboDictManager;
	}

	@RequestMapping(value = { "" })
	public String list() {
		return "modules/sys/pic/2DegreeRelationship";
	}

	@RequestMapping(value = { "input" })
	public String input(@ModelAttribute("model") ComboDict comboDict) {
		return "modules/sys/comboDict-input";
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
	public Result save(@ModelAttribute("model") ComboDict comboDict) {
		Result result;
		// 名称重复校验
		List<ComboDict> Nodes = null;
		Nodes = comboDictManager.getComboDictTypeList(comboDict.getPage(),
				comboDict.getType());
		for (ComboDict rs : Nodes) {
			if (rs.getName().equals(comboDict.getName())) {
				if (comboDict != null)

				{
					result = new Result(Result.WARN, "选项["
							+ comboDict.getName() + "]已存在,请修正!", "title");
					return result;
				}
			}

		}

		comboDictManager.saveEntity(comboDict);
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
		Page<ComboDict> p = new Page<ComboDict>(SpringMVCHolder.getRequest());
		p = getEntityManager().findPage(p, filters);

		Datagrid<ComboDict> dg = new Datagrid<ComboDict>(p.getTotalCount(),
				p.getResult());
		return dg;
	}

	@RequestMapping(value = { "import" })
	public String _import() {
		return "modules/sys/comboDict-import";
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
		List<ComboDict> comboDicts = null;
		List<ComboDict> comboDicts_new = Lists.newArrayList();
		try {
			if (file != null) {
				comboDicts = (List<ComboDict>) ExcelUtil.importExcelByIs(
						file.getInputStream(), ComboDict.class);
				if (comboDicts != null && comboDicts.size() > 0) {
					for (ComboDict comboDict : comboDicts) {
						// 名称重复校验
						List<ComboDict> Nodes = null;
						Nodes = comboDictManager.getComboDictTypeList(
								comboDict.getPage(), comboDict.getType());
						for (ComboDict rs : Nodes) {
							boolean repeated = false;
							
							
							if (rs.getName().equals(comboDict.getName())) {
								if (comboDict != null)

								{

									repeated = true;
								}
							}
							if (repeated == false) {
								comboDict.setVersion(0);
								// comboDict.setContent(ClobUtil.getClob(comboDict.getContentView()));

								comboDicts_new.add(comboDict);
							} else {
								logger.warn("[{}]已存在.", comboDict.getName());
							}

						}

					}
				}
				comboDictManager.saveOrUpdate(comboDicts_new);
				result = new Result(Result.SUCCESS, "已导入"
						+ comboDicts_new.size() + "条数据.", null);

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
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "exportExcel" })
	public void exportExcel(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		// 生成提示信息，
		final String fileName = "下拉选项信息.xls";
		OutputStream outStream = null;
		try {
			// 设置文件类型
			response.setContentType(WebUtils.EXCEL_TYPE);
			// 设置下载弹出对话框
			WebUtils.setDownloadableHeader(request, response, fileName);
			// 从session中获取查询参数
			List<PropertyFilter> sessionFilters = (List<PropertyFilter>) session
					.getAttribute(SSSION_SEARCH);
			List<ComboDict> comboDicts = null;
			if (sessionFilters != null) {
				comboDicts = comboDictManager.find(sessionFilters, "id",
						Page.ASC);
			} else {
				comboDicts = comboDictManager.getAll("id", Page.ASC);
			}
			HSSFWorkbook workbook = new ExportExcel<ComboDict>().exportExcel(
					"导出信息", ComboDict.class, comboDicts);
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

	@RequestMapping(value = { "hostList" })
	@ResponseBody
	public List<equipname> HostTypeList() throws Exception {
		List<equipname> names = Lists.newArrayList();
		;
		String[] a = { "设备类型", "type", "设备品牌", "brand", "型号",
				"hostEquipmentVersion", "设备功率", "power", "CPU", "cpu", "内存",
				"memory", "硬盘", "disk", "网卡", "networkCard", "HBA卡", "hbacard",
				"操作系统", "operatingSystemName", "操作系统版本",
				"operatingSystemVersion", "使用人", "administrator", "承载系统",
				"system", "城市", "city", "地址", "location" };
		for (int i = 0; i < a.length - 1; i += 2) {
			names.add(new equipname(a[i], a[i + 1]));
		}
		return names;

	}

	@RequestMapping(value = { "netList" })
	@ResponseBody
	public List<equipname> NetTypeList() throws Exception {
		List<equipname> names = Lists.newArrayList();
		;
		String[] a = { "设备类型", "type", "设备品牌", "brand", "设备型号",
				"equipmentversion", "设备功率", "power", "CPU", "cpu", "内存",
				"memory", "板卡类型", "boardtype", "板卡配置", "boardsetting", "使用人",
				"administrator", "设备用途", "utilization", "城市", "city", "地址",
				"location" };
		for (int i = 0; i < a.length; i += 2) {
			names.add(new equipname(a[i], a[i + 1]));
		}
		return names;

	}

	@RequestMapping(value = { "officeList" })
	@ResponseBody
	public List<equipname> TypeList() throws Exception {
		List<equipname> names = Lists.newArrayList();
		;
		String[] a = { "类型", "type", "设备品牌", "brand", "设备型号",
				"equipmentversion", "设备功率", "power", "CPU", "cpu", "内存",
				"memory", "硬盘", "disk", "网卡", "networkCard", "操作系统",
				"operatingSystemName", "操作系统版本", "operatingSystemVersion",
				"使用人", "administrator", "设备用途", "utilization", "城市", "city",
				"地址", "location" };
		for (int i = 0; i < a.length - 1; i += 2) {
			names.add(new equipname(a[i], a[i + 1]));
		}
		return names;

	}

	@RequestMapping(value = { "allList" })
	@ResponseBody
	public List<ComboDict> AllNameList(String selectType,
			@ModelAttribute("model") ComboDict comboDict) throws Exception {
		List<ComboDict> netEquipmentNodes = null;
		netEquipmentNodes = comboDictManager.getComboDictTypeList(
				comboDict.getPage(), comboDict.getType());
		return netEquipmentNodes;

	}

	/*
	 * /显示页面列表 三种设备
	 */
	@RequestMapping(value = { "pageList" })
	@ResponseBody
	public List<equipname> EquipmentPageList() throws Exception {
		List<equipname> EquipmentPageLists = null;
		EquipmentPageLists = comboDictManager.getPageList();
		return EquipmentPageLists;

	}

	/**
	 * 
	 * @param selectType
	 * @param grade
	 *            是否需要分级授权 默认值：1(需要) 0 (不需要)
	 * @return
	 * @throws Exception
	 */
	/* 加树 */
	@RequestMapping(value = { "tree" })
	@ResponseBody
	public List<TreeNode> tree(String selectType) throws Exception {
		List<TreeNode> treeNodes = null;
		List<TreeNode> titleList = Lists.newArrayList();
		TreeNode treeNode = SelectType.treeNode(selectType);
		if (treeNode != null) {
			titleList.add(treeNode);
		}

		treeNodes = comboDictManager.getComboDictTree();
		// treeNodes = iprangeManager.getIprangeTree(iprangeId,null, true);
		List<TreeNode> unionList = ListUtils.union(titleList, treeNodes);
		return unionList;
	}

}

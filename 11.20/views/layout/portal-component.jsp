<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%> 
<h3 style="margin-left: 12px;">
	系统简介
</h3>
<ul>
	<li>本数据分析系统主要提供大数据内容前端展示，系统基于Web技术实现，
	后台管理用Java语言实现.</li>
</ul> 

<h3 style="margin-left: 12px;">
	系统功能
</h3>
<ul>
	<li>
        数据分析:通过Thrift接口读取Hbase中数据内容，并通过Echarts的形式前台展现.<br/>
    </li>
    
    
	<br />	

		
	<li>
	用户管理:对用户的基本信息、权限、角色进行管理。包含以下模块：模块管理、角色管理、用户管理、组织机构管理等.<br/>
	</li>
	<br />
	
	<li>
	日志管理:对每个用户在系统中的各种操作进行日志记录和管理.
	</li>
	<br />

	<li>
		兼容IE 8+、Firefox以及Chrome等浏览器.
	</li>
</ul>
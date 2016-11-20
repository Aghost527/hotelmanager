<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>

<script type="text/javascript"  for="window" event="onload">  
 </script>
<div>
 


	<div align="center">
	    <h3 >${model.title} </h3>
    <%-- 中间部分 列表 --%>
 
	<div align="right">
	   [ 本记录由${model.createUser} 发布于<fmt:formatDate value="${model.createTime}" type="both" /> ]
	</div>

	<br>
	<br>
	<strong>留言内容</strong>
	<hr>
 
	<div>${model.content}</div>
</div>
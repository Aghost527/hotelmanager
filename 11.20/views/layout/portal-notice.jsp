<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="portal-div">
   	您好,${sessionInfo.loginName}<br>
   	所属角色：[${sessionInfo.roleNames}] <br>
	最后登录IP： ${sessionInfo.ip}<br>
          最后登录时间：<fmt:formatDate value="${sessionInfo.loginTime}" pattern="yyyy/MM/dd HH:mm:ss"/> <br>
          
    <%--<div style="float: right;margin-right: 4px;">--%>
    <%--<button class="btn btn-primary" onclick="eu.addTab(window.parent.$layout_center_tabs,'我的通知','${ctxAdmin}/notice/notice?mode=view',true)" type="button">更 多</button>--%>
    <%--</div>--%>
</div>
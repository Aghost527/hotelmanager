<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<style type="text/css">        
	.loginTitle{
	font-size: 28px;
	text-align: center;
	color: #FFFFFF;
	letter-spacing: 20px;
	font-weight: bold;
	/*text-shadow: 0px 0px 4px rgb(255, 255, 255), 0px 0px 20px #999, 0px 0px 40px rgb(255, 255, 255);*/
	text-shadow: 2px 2px 2px #0D0D0D, 0px 0px 5px #aaa;
	}
	</style>
<div style="height: 100%; width:100%; background-image: url(${ctxStatic}/img/top_bg.jpg); background-repeat: no-repeat; background-size:100% 100%;">
<!--     <div style="float: left;">
	    <img  title="微博大数据分析系统" class="easyui-tooltip" data-options="position:'right'" src="${ctxStatic}/img/logo.png" width= 200px height= 51px>
	</div> -->
	<div class="loginTitle" style="text-align: center; height: 55px; line-height: 55px;  ">微博大数据分析展示平台</div>
	 <div style="float: right; position: absolute; bottom: 20px; right: 10px">
	    <%--<div style="text-align: right;">您好,<span style="color: red;">${sessionInfo.loginName}</span>[${sessionInfo.roleNames}] 欢迎您！</div>--%>
		<%--<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_pfMenu" iconCls="eu-icon-user_red">更换皮肤</a>--%>
		<%--<div id="layout_north_pfMenu" style="width: 120px; display: none;">--%>
			<%--<div onclick="eu.changeTheme('bootstrap');">bootstrap</div>--%>
			<%--<div onclick="eu.changeTheme('default');">蓝色</div>--%>
			<%--<div onclick="eu.changeTheme('gray');">灰色</div>--%>
			<%--<div onclick="eu.changeTheme('black');">黑色</div>--%>
			<%--<div onclick="eu.changeTheme('metro');">metro</div>--%>
		<%--</div>--%>

	    <%--<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_kzmbMenu" iconCls="easyui-icon-help">控制面板</a>--%>
		<%--<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">--%>
			<%--<div onclick="editLoginUserPassword();" iconCls="easyui-icon-edit">修改密码</div>--%>
			<%--<div class="menu-sep"></div>--%>
            <%--<div onclick="toApp();">切换到桌面版</div>--%>
            <%--<div class="menu-sep"></div>--%>
			<%--<div onclick="" data-options="iconCls:'easyui-icon-help'">帮助</div>--%>
			<%--<div onclick="showAbout();" data-options="iconCls:'easyui-icon-essh'">关于</div>--%>
		<%--</div>--%>

		<%--<a href="javascript:void(0);" class="easyui-menubutton" menu="#layout_north_logoutMenu" iconCls="easyui-icon-back">安全退出</a>--%>
		<%--<div id="layout_north_logoutMenu" style="width: 100px; display: none;">--%>
			<%--<div onclick="logout(true);"  data-options="iconCls:'eu-icon-lock'">切换账号</div>--%>
			<%--<div onclick="logout();"  data-options="iconCls:'eu-icon-lock'">注销</div>--%>
		<%--</div>--%>
	</div>
</div>

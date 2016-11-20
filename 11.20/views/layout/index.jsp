<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%-- 引入jQuery --%>
<script src="${ctxStatic}/js/easyui-1.4.2/jquery.min.js"></script>
<%-- jQuery Cookie插件 --%>
<script type="text/javascript" src="${ctxStatic}/js/jquery/jquery.cookie-min.js" charset="utf-8"></script>
<link href="${ctxStatic}/js/bootstrap/2.3.2/custom/bootstrap-img.css" rel="stylesheet" type="text/css"/>
<!-- <script type="text/javascript" src="${ctxStatic}/app/modules/sys/autoCombo.js?_=${sysInitTime}" charset="utf-8"></script> -->

<script type="text/javascript">
	function jump(destination){
		var iframe =document.getElementById("chartArea");
		url="http://localhost:8080/admin/sys/"+destination;
		iframe.src=url;
		//document.getElementById("data-menu").className='start open';
	}
</script>

<style type="text/css"> 

.autocomplete{
border: 1px solid #9ACCFB;
background-color: white;
text-align: left;
}
.autocomplete li{
list-style-type: none;
}
.clickable {
cursor: default;
}
.highlight {
background-color: #9ACCFB;
}
</style>

<html class="expanded">
<head>
    <meta charset="utf-8"/>
    <title>微博大数据分析展示平台</title>
    <link rel="shortcut icon" href="${ctxStatic}/img/favicon16x16.ico">
 	<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/ad/default.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/ad/style.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/ad/entypo.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/ad/custom.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/ad/font-awesome.css"/>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/ad/bootstrap.min.css"/>
    <script src="${ctxStatic}/js/ad/metisMenu.min.js"></script> 
    <script src="${ctxStatic}/js/ad/custom.js"></script> 
    <script src="${ctxStatic}/js/ad/bootstrap.min.js"></script> 
</head>


<body>
<div class="header">
    <div class="h-logo">
        <a href="index" title="数据展示中心">
            <img id="logo" src="${ctxStatic}/img/logo.png"  alt="logo"/>
        </a>
    </div>
     
    <div class="clear"></div>
</div>
 
    <div class="layout_leftnav" class="navbar-default sidebar">
  
        <div class="page-sidebar-wrapper">
            <div class="sidebar-nav navbar-collapse  ">
                                  <ul class="nav" id="side-menu">
                        <li>
                            <a href="index"><i class="fa fa-dashboard fa-fw nav_icon"></i>主页</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-laptop nav_icon"></i>数据分析<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li onclick="jump('echarts')">
                                    <a href="javascript:void(0)">·&nbsp;二度人脉图</a>
                                </li>
                                <li onclick="jump('echarts/WeiboTagRecomm')">
                                    <a href="javascript:void(0)">·&nbsp;网络用户分析</a>
                                </li>
                                <li   onclick="jump('echarts/agent_bar')">
                                    <a href="javascript:void(0)">·&nbsp;基站覆盖强度</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-indent nav_icon"></i>用户管理<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li onclick="jump('organ')">
                                <a href="javascript:void(0)">·&nbsp;机构管理</a>
                            </li>
                            <li   onclick="jump('post')">
                                <a href="javascript:void(0)">·&nbsp;岗位管理</a>
                            </li>
                            <li onclick="jump('role')">
                                <a href="javascript:void(0)">·&nbsp;角色管理</a>
                            </li>
                            <li onclick="jump('user')">
                                <a href="javascript:void(0)">·&nbsp;用户管理</a>
                            </li>

                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-envelope nav_icon"></i>系统管理<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                 <li onclick="jump('log')">
                                <a href="javascript:void(0)">·&nbsp;日志管理</a>
                            </li>
                            <li onclick="jump('config')">
                                <a href="javascript:void(0)">·&nbsp;配置管理</a>
                            </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                           <li onclick="jump('bulletin')">
                            <a href="javascript:void(0)"><i class="fa fa-dashboard fa-fw nav_icon"></i>留言板</a>
                        </li>
                          
                        
                        
                    </ul>
            </div>
            </div>
                
        </div>
    </div>
    <div class="layout_rightmain">
        <div class="r-top">
        
            <div class="logoff" style="float:right;">
                <a href='${ctxAdmin}/login/logout'>${user.name}退出系统</a>
             </div>
            <div class="logoff" style="float:right;">
             <a href='${ctxAdmin}/indexOld/'>切换版本&nbsp&nbsp&nbsp&nbsp  </a>   
            </div>
        </div>
<!--         <div id="tipBox" style="height:0;">
            <span class="title">温馨提示：</span>
            <span class="message"></span>
            <span class="close_icon"></span>
        </div> -->
        <div class="chartArea"  >
		 <iframe id="chartArea" src="http://localhost:8080/admin/portal " width="1050px" height="520px" scrolling="no" frameborder="0"> </iframe>
    </div>
	


</body>
 
</html>


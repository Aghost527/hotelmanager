<%@ page import="com.dhc.common.utils.browser.BrowserType" %>
<%@ page import="com.dhc.common.utils.browser.BrowserUtils" %>
<%@ page import="com.dhc.common.web.utils.WebUtils" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<%
    String ctx = request.getContextPath();
    BrowserType browserType = BrowserUtils.getBrowserType(request);
%>
<%-- 引入jQuery --%>
<%
    if (browserType != null
            && (browserType.equals(BrowserType.IE11) || browserType.equals(BrowserType.IE10) || browserType.equals(BrowserType.IE9)
            || browserType.equals(BrowserType.Chrome) || browserType.equals(BrowserType.Firefox)
            || browserType.equals(BrowserType.Safari))) {
        out.println("<script type='text/javascript' src='" + ctx + "/static/js/jquery/jquery-2.1.0.min.js' charset='utf-8'></script>");
    } else {
        out.println("<script type='text/javascript' src='" + ctx + "/static/js/jquery/jquery-1.10.2.min.js' charset='utf-8'></script>");
        out.println("<script type='text/javascript' src='" + ctx + "/static/js/jquery/jquery-migrate-1.2.1.min.js' charset='utf-8'></script>");
    }
%>
<%-- jQuery Cookie插件 --%>
<script type="text/javascript" src="${ctxStatic}/js/jquery/jquery.cookie-min.js" charset="utf-8"></script>

<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/formstyle.css?_=${sysInitTime}" />
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/checkboxstyle.css" />

<meta http-equiv="X-UA-Compatible" content="IE=EDGE;chrome=1" />
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<link rel="shortcut icon" href="${ctxStatic}/img/favicon.ico" />

<script type="text/javascript" charset="utf-8">
    var ctx = "${ctx}";
    var ctxAdmin = "${ctxAdmin}";
    var ctxFront = "${ctxFront}";
    var ctxStatic = "${ctxStatic}";
    isSuperUser = function() {
        var isSuperUser = "${sessionInfo.superUser}";
        if(isSuperUser == "true"){
            return true;
        }
        return false;
    }
</script>

<style type="text/css">
.wrap{margin:25px 0}
.wrap{padding:10px 0}
.login_label{width:560px; margin:60px auto 10px auto}
body{font-size:14px}
</style>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>用户登录</title>

    

    <%@ include file="/common/autocomplete.jsp" %>
    <script type="text/javascript" src="${ctxStatic}/js/easyform.js?_=${sysInitTime}" charset="utf-8"></script>
    
<!--     <%
        Cookie cookie = WebUtils.getCookie(request, "loginName");
        if(cookie!=null){
            String loginNameOrName = WebUtils.getCookie(request, "loginName").getValue();
            request.setAttribute("loginNameOrName",loginNameOrName);
        }
    %> -->
    <script type="text/javascript">
        var loginForm;
        var login_linkbutton;
        var $loginName, $password, $rememberPassword, $autoLogin;
        $(function () {
            $loginName = $("#loginName");
            $password = $("#password");
            // $rememberPassword = $("#rememberPassword");
            // $autoLogin = $("#autoLogin");
            loginForm = $('#loginForm').easyform();
            //refreshCheckCode();

            // $rememberPassword.prop("checked", "${cookie.rememberPassword.value}" == "" ? false : true);
            // $autoLogin.prop("checked", "${cookie.autoLogin.value}" == "" ? false : true);

            // if ("${cookie.autoLogin.value}" != "") {
            //     login();
            // } else {
            //     $loginName.focus();
            // }

            // $rememberPassword.click(function () {
            //     var checked = $(this).prop('checked');
            //     if (checked) {
            //         $.cookie('password', $password.val(), {
            //             expires: 7
            //         });
            //         $.cookie('rememberPassword', checked, {
            //             expires: 7
            //         });
            //     } else {
            //         $.cookie('password', "", {
            //             expires: 7
            //         });
            //         $.cookie('rememberPassword', "", {
            //             expires: 7
            //         });
            //     }
            // });
            // $autoLogin.click(function () {
            //     var checked = $(this).prop('checked');
            //     if (checked) {
            //         $.cookie('autoLogin', checked, {
            //             expires: 7
            //         });
            //         $rememberPassword.prop('checked', checked);
            //         $.cookie('rememberPassword', checked, {
            //             expires: 7
            //         });
            //     } else {
            //         $.cookie('autoLogin', "", {
            //             expires: 7
            //         });
            //     }
            // });

            // $loginNameAutocompleter = $("#loginName").next().children(".textbox-text").autocomplete('${ctxAdmin}/sys/user/autoComplete', {
            //     remoteDataType:'json',
            //     minChars: 0,
            //     maxItemsToShow: 10
            // });
            // var ac = $loginNameAutocompleter.data('autocompleter');
            // //添加查询属性
            // ac.setExtraParam("rows",ac.options.maxItemsToShow);
        });
        // function cover(){
        //     var win_width=$(window).width();
        //     var win_height=$(window).height();
        //     $("#bigpic").attr({width:win_width,height:win_height});
        // }
                $.ajaxSetup({async:false});//同步传参
       
               function login() {
               
                // var cookieThemeType = "${cookie.themeType.value}"; //cookie初访的登录管理界面类型
                // var loginList =document.getElementById("loginForm");
               
                var destination;
                $.post('${ctxAdmin}/login/login',$('#loginForm').serialize(),  function (data) {
                     
                    if (data.code == 1) {
                        destination =data.obj;
                       
                        return false;
                        // alert(data.obj);
                        
                    } else {
                       
                       alert("用户名或者密码错误");
                        destination="/";
                    }
                }, 'json');
                 window.location  = destination;//跳转
            }
        
    </script>
</head>

<body>
    <div class="form-div" id="login_div" style="margin-top: 26px;" >
               <h1 style="text-align: center;font-size: 42px;font-weight: bolder;color: #006699;">用户登录</h1>
            <!-- <form id="loginForm" method="post" action="${ctxAdmin}/login/login/"  > -->
            <form id="loginForm" name="loginForm" style="margin-top: 0px" >
            <table>
                <tr>
                <td>
                    <label size=8 class="login_label">用户名</label>
                </td>
                <td>
                    <input  name="loginName" type="text" id="loginName" easyform="length:5-16;char-normal;real-time;"
                            value="${fns:urlDecode(loginNameOrName)}"
                            message="帐号必须为6-16位英文、数字和下划线" easytip="disappear:lost-focus;theme:blue;" >
                </td>
            </tr>
            <tr>
                <td>
                    <label class="login_label">密&nbsp;&nbsp;&nbsp;&nbsp;码   </label>
                </td>
                <td>
                     <input name="password" type="password" id="password" easyform="length:5-16;" 
                    onkeydown="javascript:if(event.keyCode==13){login();}"
                    message="密码必须为6—16位"
                           easytip="disappear:lost-focus;theme:blue;">
                </td>
                
            </table> 

                <!-- <div class="wrap" style="margin-left: 16px;">
            
            <input type="checkbox" id="rememberPassword" class="chk_1" checked /><label for="rememberPassword" style="margin-left: 101px;"></label><td>记住密码</td>
            <input type="checkbox" id="autoLogin" class="chk_1" /><label for="autoLogin"></label><font  >自动登录</font>
        </div>
                 -->
            </tr>
            </form>     

                <div class='bottons'>
                    <input  value="登 录" id="login_linkbutton"  type="submit" onclick="login();" href="#"  
                       style="margin-left: 116px;width: 210px;">


                </div>
            
        </div>
    </div>
</body>
</html>


<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>

<script type="text/javascript" src="${ctxStatic}/app/modules/sys/rentCar.js?_=${sysInitTime}" charset="utf-8"></script>
<%-- 隐藏iframe --%>
<iframe id="rentCar_temp_iframe" style="display: none;"></iframe>
<%-- 列表右键 --%>
<div id="rentCar_datagrid_menu" class="easyui-menu" style="width:120px; ">
    <div onclick="showDialog();" iconCls="easyui-icon-add">后增</div>
    <div onclick="edit();" data-options="iconCls:'easyui-icon-edit'">编辑</div>
    <div onclick="del();" data-options="iconCls:'easyui-icon-remove'">删除</div>
    <div onclick="exportExcel();" data-options="iconCls:'easyui-icon-edit'">Excel导出</div>
    <div onclick="importExcel();" data-options="iconCls:'easyui-icon-edit'">Excel导入</div>
</div>

<%-- easyui-layout布局 --%>
<div class="easyui-layout" fit="true" style="margin: 0px;border: 0px;overflow: hidden;width:100%;height:100%;">

 

    <div data-options="region:'north',title:'过滤条件',collapsed:false,split:false,border:false"
         style="padding: 0px; height: 70px;width:100%; overflow-y: hidden;">
        <form id="rentCar_search_form" style="padding: 5px;">
            &nbsp;筛选类型:
			<select name="filter_LIKES_type"   class="easyui-combobox " style="width: 130px;height: 28px;">  
				<option value="">所有</option>
        <option value="留言">留言</option>  
        <option value="通知">通知</option>  
        <option value="意见">意见</option>  
			</select>
            &nbsp;搜索标题:<input type="text" name="filter_LIKES_title" maxLength="25"
                      class="easyui-validatebox textbox eu-input" placeholder="请输入要搜索的日志标题.."
                      onkeydown="if(event.keyCode==13)search()" style="width: 160px" />
            &nbsp;<a class="easyui-linkbutton" href="#" data-options="iconCls:'easyui-icon-search',width:100,height:28,onClick:search">查询</a>
            <a class="easyui-linkbutton" href="#" data-options="iconCls:'easyui-icon-no',width:100,height:28" onclick="javascript:$rentCar_search_form.form('reset');">重置查询</a>
        </form>
    </div>
    <%-- 中间部分 列表 --%>
    <div data-options="region:'center',split:false,border:false"
         style="padding: 0px; height: 100%;width:100%; overflow-y: hidden;">
        <table id="rentCar_datagrid"></table>
    </div>
</div>

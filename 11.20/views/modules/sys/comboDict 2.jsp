<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<script type="text/javascript" src="${ctxStatic}/app/modules/sys/comboDict.js?_=${sysInitTime}" charset="utf-8"></script>
<%-- 隐藏iframe --%>
<iframe id="comboDict_temp_iframe" style="display: none;"></iframe>
<%-- 列表右键 --%>
<div id="comboDict_datagrid_menu" class="easyui-menu" style="width:120px;display: none;">
    <div onclick="showDialog();" data-options="iconCls:'easyui-icon-add'">新增</div>
    <div onclick="edit();" data-options="iconCls:'easyui-icon-edit'">编辑</div>
    <div onclick="del();" data-options="iconCls:'easyui-icon-remove'">删除</div>

</div>
<%-- easyui-layout布局 --%>
<div class="easyui-layout" fit="true" style="margin: 0px;border: 0px;overflow: hidden;width:100%;height:100%;">

    <%-- 左边部分 菜单树形 --%>
    <div data-options="region:'west',title:'类别列表',split:false,collapsed:false,border:false"
         style="width: 180px; text-align: left;padding:5px;">
        <ul id="comboDict_tree"></ul>
    </div>

    <!-- 中间部分 列表 -->
    <div data-options="region:'center',split:true" style="overflow: hidden;">
        <div class="easyui-layout" fit="true" style="margin: 0px;border: 0px;overflow: hidden;width:100%;height:100%;">
            <div data-options="region:'center',split:true" style="overflow: hidden;">
                <table id="comboDict_datagrid" ></table>
            </div>

            <div data-options="region:'north',title:'过滤条件',split:false,collapsed:false,border:false"
                 style="width: 100%;height:70px; overflow-y: hidden;">
                <form id="comboDict_search_form" style="padding: 5px;">
                    &nbsp;搜索选项名称: &nbsp;<input type="text" id="filter_LIKES_name" name="filter_LIKES_name"
                                      class="easyui-validatebox textbox eu-input" placeholder="查询具体选项"
                                       onkeydown="if(event.keyCode==13)search()"  style="width: 160px"/>
                    &nbsp;<a class="easyui-linkbutton" href="#" data-options="iconCls:'easyui-icon-search',width:100,height:28,onClick:search">查询</a>
                    <a class="easyui-linkbutton" href="#" data-options="iconCls:'easyui-icon-no',width:100,height:28" onclick="javascript:$comboDict_search_form.form('reset');">重置查询</a>
                </form>
            </div>
        </div>
    </div>
</div>
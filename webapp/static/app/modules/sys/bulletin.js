var $bulletin_datagrid;
var $bulletin_form;
var $bulletin_search_form;
var $bulletin_dialog;
 

var $bulletin_import_dialog;//bulletin导入表单弹出对话框
var $bulletin_import_form;
$(function () {
    $bulletin_form = $('#bulletin_form').form();
    $bulletin_search_form = $('#bulletin_search_form').form();
	
	 
	
	
    //数据列表
    $bulletin_datagrid = $('#bulletin_datagrid').datagrid({
        url: ctxAdmin + '/sys/bulletin/datagrid',
        fit: true,
        pagination: true,//底部分页
        rownumbers: true,//显示行数
        fitColumns: false,//自适应列宽
        striped: true,//显示条纹
        nowrap: true,
        pageSize: 20,//每页记录数
        remoteSort: false,//是否通过远程服务器对数据排序
        sortName: 'id',//默认排序字段
        sortOrder: 'desc',//默认排序方式 'desc' 'asc'
        idField: 'id',
        frozenColumns: [
            [
                {field: 'ck', checkbox: true},
                {field: 'title', title: '留言标题', width: 200, formatter: function (value, rowData, rowIndex) {
                    var html = $.formatString("<span style='color:{0}'>{1}</span>", rowData.color, value);
                    return html;
                }},
				{field: 'type', title: '留言类型', width: 120 },
				{field: 'content', title: '留言内容', width: 250 },
                {field: 'createUser', title: '创建用户', width: 120 },
				{field: 'operater', title: '操作', width: 260, formatter: function (value, rowData, rowIndex) {
                    var url = $.formatString(ctxAdmin + '/sys/bulletin/view?id={0}', rowData.id);
                    var operaterHtml = "<a class='easyui-linkbutton' iconCls='easyui-icon-add'  " +
                        "onclick='view(\"" + rowData.title + "\",\"" + url + "\")' >查看</a>"
                        + "&nbsp;<a class='easyui-linkbutton' iconCls='easyui-icon-edit'  href='#' " +
                        "onclick='edit(" + rowIndex + ");' >编辑</a>"
                        + "&nbsp;<a class='easyui-linkbutton' iconCls='easyui-icon-remove'  href='#' " +
                        "onclick='del(" + rowIndex + ");' >删除</a>";
                    return operaterHtml;
                }}
            ]
        ],
        columns: [
            [
				
            ]
        ],
        toolbar: [
            {
                text: '新增留言',
                iconCls: 'easyui-icon-add',
                handler: function () {
                    showDialog()
                }
            },
            '-',
            {
                text: '编辑',
                iconCls: 'easyui-icon-edit',
                handler: function () {
                    edit()
                }
            },
            '-',
            {
                text: '删除',
                iconCls: 'easyui-icon-remove',
                handler: function () {
                    del()
                }
            },
            '-',
            {
                text: 'Excel导出',
                iconCls: 'easyui-icon-edit',
                handler: function () {
                    exportExcel()
                }
            },
            '-',
            {
                text: 'Excel导入',
                iconCls: 'easyui-icon-edit',
                handler: function () {
                    importExcel()
                }
            }
        ],
        onLoadSuccess: function () {
            $(this).datagrid('clearSelections');//取消所有的已选择项
            $(this).datagrid('unselectAll');//取消全选按钮为全选状态
        },
        onRowContextMenu: function (e, rowIndex, rowData) {
            e.preventDefault();
            $(this).datagrid('unselectAll');
            $(this).datagrid('selectRow', rowIndex);
            $('#bulletin_datagrid_menu').menu('show', {
                left: e.pageX,
                top: e.pageY
            });
        },
        onDblClickRow: function (rowIndex, rowData) {
            edit(rowIndex, rowData);
        }
    }).datagrid('showTooltip');
});
//查看
function view(title, url) {
    if (window.parent.$layout_center_tabs) {
        $bulletin_datagrid.datagrid('unselectAll');
        eu.addTab(window.parent.$layout_center_tabs, title, url, true);
    }
}

function formInit() {
    $bulletin_form = $('#bulletin_form').form({
        url: ctxAdmin + '/sys/bulletin/_save',
        onSubmit: function (param) {
            $.messager.progress({
                title: '提示信息！',
                text: '数据处理中，请稍后....'
            });

            var isValid = $(this).form('validate');
            if (!isValid) {
                $.messager.progress('close');
            }
            return isValid;
        },
        success: function (data) {
            $.messager.progress('close');
            var json = $.parseJSON(data);
            if (json.code == 1) {
                $bulletin_dialog.dialog('destroy');//销毁对话框
                $bulletin_datagrid.datagrid('reload');//重新加载列表数据
				$bulletin_tree.tree("reload");
                eu.showMsg(json.msg);//操作结果提示
            } else if (json.code == 2) {
                $.messager.alert('提示信息！', json.msg, 'warning', function () {
                    if (json.obj) {
                        $('#bulletin_form input[name="' + json.obj + '"]').focus();
                    }
                });
            } else {
                eu.showAlertMsg(json.msg, 'error');
            }
        }
    });
}
//显示弹出窗口 新增：row为空 编辑:row有值
function showDialog(row) {
    var inputUrl = ctxAdmin + "/sys/bulletin/input";
    if (row != undefined && row.id) {
        inputUrl = inputUrl + "?id=" + row.id;
    }

    //弹出对话窗口
    $bulletin_dialog = $('<div/>').dialog({
        title: '留言版',
        top: 20,
        width: 500,
        height: 360,
        modal: true,
        maximizable: true,
        href: inputUrl,
        buttons: [
            {
                text: '保存',
                iconCls: 'easyui-icon-save',
                handler: function () {
                    $bulletin_form.submit();
                }
            },
            {
                text: '关闭',
                iconCls: 'easyui-icon-cancel',
                handler: function () {
                    $bulletin_dialog.dialog('destroy');
                }
            }
        ],
        onClose: function () {
            $bulletin_dialog.dialog('destroy');
        },
        onLoad: function () {
            formInit();
            if (row) {
                $bulletin_form.form('load', row);
            }

        }
    });

}

//编辑
function edit(rowIndex, rowData) {
    //响应双击事件
    if (rowIndex != undefined) {
        $bulletin_datagrid.datagrid('unselectAll');
        $bulletin_datagrid.datagrid('selectRow', rowIndex);
        var rowData = $bulletin_datagrid.datagrid('getSelected');
        $bulletin_datagrid.datagrid('unselectRow', rowIndex);
        showDialog(rowData);
        return;
    }
    //选中的所有行
    var rows = $bulletin_datagrid.datagrid('getSelections');
    //选中的行（第一次选择的行）
    var row = $bulletin_datagrid.datagrid('getSelected');
    if (row) {
        if (rows.length > 1) {
            row = rows[rows.length - 1];
            eu.showMsg("您选择了多个操作对象，默认操作第一次被选中的记录！");
        }
        showDialog(row);
    } else {
        eu.showMsg("您未选择任何操作对象，请选择一行数据！");
    }
}

//删除
function del(rowIndex) {
    var rows = new Array();
    var tipMsg = "您确定要删除选中的所有行？";
    if (rowIndex != undefined) {
        $bulletin_datagrid.datagrid('unselectAll');
        $bulletin_datagrid.datagrid('selectRow', rowIndex);
        var rowData = $bulletin_datagrid.datagrid('getSelected');
        rows[0] = rowData;
        $bulletin_datagrid.datagrid('unselectRow', rowIndex);
        tipMsg = "您确定要删除？";
    } else {
        rows = $bulletin_datagrid.datagrid('getSelections');
    }

    if (rows.length > 0) {
        $.messager.confirm('确认提示！', tipMsg, function (r) {
            if (r) {
                var ids = new Array();
                $.each(rows, function (i, row) {
                    ids[i] = row.id;
                });
                $.ajax({
                    url: ctxAdmin + '/sys/bulletin/remove',
                    type: 'post',
                    data: {ids: ids},
                    traditional: true,
                    dataType: 'json',
                    success: function (data) {
                        if (data.code == 1) {
                            $bulletin_datagrid.datagrid('load');	// reload the user data
							 
                            eu.showMsg(data.msg);//操作结果提示
                        } else {
                            eu.showAlertMsg(data.msg, 'error');
                        }
                    }
                });
            }
        });
    } else {
        eu.showMsg("您未选择任何操作对象，请选择一行数据！");
    }
}

//搜索
function search() {
    $bulletin_datagrid.datagrid('load', $.serializeObject($bulletin_search_form));
}

//导出Excel
function exportExcel() {
    $('#bulletin_temp_iframe').attr('src', ctxAdmin + '/sys/bulletin/exportExcel');
}

function importFormInit() {
    $bulletin_import_form = $('#bulletin_import_form').form({
        url: ctxAdmin + '/sys/bulletin/importExcel',
        onSubmit: function (param) {
            $.messager.progress({
                title: '提示信息！',
                text: '数据处理中，请稍后....'
            });
            return $(this).form('validate');
        },
        success: function (data) {
            $.messager.progress('close');
            var json = $.parseJSON(data);
            if (json.code == 1) {
                $bulletin_import_dialog.dialog('destroy');//销毁对话框
                $bulletin_datagrid.datagrid('reload');//重新加载列表数据
				$bulletin_tree.tree("reload");
                eu.showMsg(json.msg);//操作结果提示
            } else {
               // eu.showAlertMsg(json.msg, 'error');
            }
        }
    });
}
//导入
function importExcel() {
    $bulletin_import_dialog = $('<div/>').dialog({//基于中心面板
        title: 'Excel导入',
        top: 55,
        height: 200,
        width: 500,
        modal: true,
        maximizable: true,
        href: ctxAdmin + '/sys/bulletin/import',
        buttons: [
            {
                text: '导入',
                iconCls: 'easyui-icon-save',
                handler: function () {
                    $bulletin_import_form.submit();
                }
            },
            {
                text: '关闭',
                iconCls: 'easyui-icon-cancel',
                handler: function () {
                    $bulletin_import_dialog.dialog('destroy');
                }
            }
        ],
        onClose: function () {
            $bulletin_import_dialog.dialog('destroy');
        },
        onLoad: function () {
            importFormInit();
        }
    });
}
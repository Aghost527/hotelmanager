var $comboDict_tree;//树(左边)
var $comboDict_datagrid;
var $comboDict_form;
var $comboDict_form;
var $comboDict_search_form;
var $comboDict_dialog
var $comboDict_dialog
var $comboDict_import_dialog;//comboDict导入表单弹出对话框
var $comboDict_import_form;


$(function() {
    $comboDict_search_form = $('#comboDict_search_form').form();


    //树
    var selectedNode = null;//存放被选中的节点对象 临时变量
    $comboDict_tree = $("#comboDict_tree").tree({
        url : ctxAdmin+"/sys/comboDict/tree",
        onClick:function(node){
            search();
        },
        onBeforeSelect:function(node){
            var selected = $(this).tree('getSelected');
            if(selected){
                if(selected.id == node.id){
                    $(".tree-node-selected", $(this).tree()).removeClass("tree-node-selected");//移除样式
                    selectedNode = null;
                    return false;
                }
            }
            selectedNode = node;
            return true;
        },
        onLoadSuccess:function(node, data){
            if(selectedNode !=null){
                selectedNode = $(this).tree('find', selectedNode.title);
                if(selectedNode !=null){//刷新树后 如果临时变量中存在被选中的节点 则重新将该节点置为被选状态
                    $(this).tree('select', selectedNode.target);
                }
            }
            $(this).tree("expandAll");
        }
    });

    //数据列表
    $comboDict_datagrid = $('#comboDict_datagrid').datagrid({
        url:ctxAdmin+'/sys/comboDict/datagrid',
        fit:true,
        pagination:true,//底部分页
        rownumbers:true,//显示行数
        fitColumns:false,//自适应列宽
        striped:true,//显示条纹
        pageSize:20,//每页记录数
        remoteSort:false,//是否通过远程服务器对数据排序
        sortName:'page',//默认排序字段
        sortOrder:'desc',//默认排序方式 'desc' 'asc'
        idField : 'id',
        frozen:true, 
        collapsible: true,
        frozenColumns:[[
            {field:'ck',checkbox:true},
            
        ]],
        columns:[[
		    {field:'id',title:'主键',hidden:true,sortable:true,align:'right',width:80} ,
            {field:'type',title:'选项类别',width:120,hidden:true} ,
			{field:'page',title:'所属页面',width:120,hidden:true},
			{field:'name',title:'选项内容',width:200,sortable:true}
        ]],
        toolbar:[{
            text:'新建选项',
            iconCls:'easyui-icon-add',
            handler:function(){showDialog()}
        },'-',{
            text:'修改选项',
            iconCls:'easyui-icon-edit',
            handler:function(){edit()}
        },'-',{
            text:'删除选项',
            iconCls:'easyui-icon-remove',
            handler:function(){del()}
        }
		],
        onLoadSuccess:function(){
            $(this).datagrid('clearSelections');//取消所有的已选择项
            $(this).datagrid('unselectAll');//取消全选按钮为全选状态
        },
        onRowContextMenu : function(e, rowIndex, rowData) {
            e.preventDefault();
            $(this).datagrid('unselectAll');
            $(this).datagrid('selectRow', rowIndex);
            $('#comboDict_datagrid_menu').menu('show', {
                left : e.pageX,
                top : e.pageY
            });
        },
        onDblClickRow:function(rowIndex, rowData){
            edit(rowIndex, rowData);
        }
    }).datagrid('showTooltip');
});

function formInit(){
    $comboDict_form = $('#comboDict_form').form({
        url: ctxAdmin+'/sys/comboDict/_save',
        onSubmit: function(param){
            $.messager.progress({
                title : '提示信息！',
                text : '数据处理中，请稍后....'
            });
            var isValid = $(this).form('validate');
            if (!isValid) {
                $.messager.progress('close');
            }
            return isValid;
        },
        success: function(data){
            $.messager.progress('close');
            var json = $.parseJSON(data);
            if (json.code ==1){
                $comboDict_dialog.dialog('destroy');//销毁对话框
                $comboDict_datagrid.datagrid('reload');//重新加载列表数据
                $comboDict_tree.tree("reload");
                eu.showMsg(json.msg);//操作结果提示
            }else if(json.code == 2){
                $.messager.alert('提示信息！', json.msg, 'warning',function(){
                    if(json.obj){
                        $('#comboDict_form input[name="'+json.obj+'"]').focus();
                    }
                });
            }else {
                eu.showAlertMsg(json.msg,'error');
            }
        }
    });
}
//显示弹出窗口 新增comboDict：row为空 编辑:row有值
function showDialog(row){
    var inputUrl = ctxAdmin+"/sys/comboDict/input";
    if(row != undefined && row.id){
        inputUrl = inputUrl+"?id="+row.id;
    }

    //弹出对话窗口
    $comboDict_dialog = $('<div/>').dialog({
        title:'新建修改选项',
        top:20,
        width : 490,
        height:210,
        modal : true,
        maximizable:true,
        href : inputUrl,
        buttons : [ {
            text : '保存',
            iconCls : 'easyui-icon-save',
            handler : function() {
                $comboDict_form.submit();
            }
        },{
            text : '关闭',
            iconCls : 'easyui-icon-cancel',
            handler : function() {
                $comboDict_dialog.dialog('destroy');
            }
        }],
        onClose : function() {
            $comboDict_dialog.dialog('destroy');
        },
        onLoad:function(){
            formInit();
            if(row){
                $comboDict_form.form('load', row);
            }else{
                var node = $comboDict_tree.tree('getSelected'); //组织机构选中节点
                if(node != undefined && node.attributes.page != undefined){
                    //设置新增内容默认值
                    $('#page').combobox("setValue",node.attributes.page);
					if(node.attributes.type!=null)
					{
					$('#type').combobox("setValue",node.attributes.type);
					}
                }
            }

        }
    });

}


//编辑
function edit(rowIndex, rowData){
    //响应双击事件
    if(rowIndex != undefined) {
        showDialog(rowData);
        return;
    }
    //选中的所有行
    var rows = $comboDict_datagrid.datagrid('getSelections');
    //选中的行（第一次选择的行）
    var row = $comboDict_datagrid.datagrid('getSelected');
    if (row){
        if(rows.length>1){
            row = rows[rows.length-1];
            eu.showMsg("您选择了多个操作对象，默认操作第一次被选中的记录！");
        }

        showDialog(row);
    }else{
        eu.showMsg("您未选择任何操作对象，请选择一行数据！");
    }
}

//删除
function del(rowIndex) {
    var rows = new Array();
    var tipMsg = "您确定要删除选中的所有记录？";
    if (rowIndex != undefined) {
        $comboDict_datagrid.datagrid('unselectAll');
        $comboDict_datagrid.datagrid('selectRow', rowIndex);
        var rowData = $comboDict_datagrid.datagrid('getSelected');
        rows[0] = rowData;
        $comboDict_datagrid.datagrid('unselectRow', rowIndex);
        tipMsg = "您确定要删除？";
    } else {
        rows = $comboDict_datagrid.datagrid('getSelections');
    }

    if (rows.length > 0) {
        $.messager.confirm('确认提示！', tipMsg, function (r) {
            if (r) {
                var ids = new Array();
                $.each(rows, function (i, row) {
                    ids[i] = row.id;
                });
                $.ajax({
                    url: ctxAdmin + '/sys/comboDict/remove',
                    type: 'post',
                    data: {ids: ids},
                    traditional: true,
                    dataType: 'json',
                    success: function (data) {
                        if (data.code == 1) {
                            $comboDict_datagrid.datagrid('load');	// reload the user data
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

//导出Excel

function exportExcel() {
    $('#comboDict_temp_iframe').attr('src', ctxAdmin + '/sys/comboDict/exportExcel');
}


//搜索

function search(){
    var node = $comboDict_tree.tree('getSelected');//
    var comboDictType = '';
	var comboDictPage = '';
    if(node != null){
        comboDictType = node.attributes.type; //搜索 id:主键 即是通过左边组织机构树点击得到搜索结果
		comboDictPage = node.attributes.page;
    }

    $comboDict_datagrid.datagrid('load',{filter_LIKES_type:comboDictType,filter_LIKES_page:comboDictPage,filter_LIKES_name:$("#filter_LIKES_name").val()});
}




function importFormInit() {
    $comboDict_import_form = $('#comboDict_import_form').form({
        url: ctxAdmin + '/sys/comboDict/importExcel',
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
                $comboDict_import_dialog.dialog('destroy');//销毁对话框
                $comboDict_datagrid.datagrid('reload');//重新加载列表数据
                eu.showMsg(json.msg);//操作结果提示
            } else {
                eu.showAlertMsg(json.msg, 'error');
            }
        }
    });
}

//导入
function importExcel() {
    $comboDict_import_dialog = $('<div/>').dialog({//基于中心面板
        title: 'Excel导入',
        top: 20,
        height: 200,
        width: 500,
        modal: true,
        maximizable: true,
        href: ctxAdmin + '/sys/comboDict/import',
        buttons: [
            {
                text: '导入',
                iconCls: 'easyui-icon-save',
                handler: function () {
                    $comboDict_import_form.submit();
                }
            },
            {
                text: '关闭',
                iconCls: 'easyui-icon-cancel',
                handler: function () {
                    $comboDict_import_dialog.dialog('destroy');
                }
            }
        ],
        onClose: function () {
            $comboDict_import_dialog.dialog('destroy');
        },
        onLoad: function () {
            importFormInit();
        }
    });
}




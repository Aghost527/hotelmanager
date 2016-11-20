var $stuff_datagrid;
var $stuff_search_form;
$(function () {
    $stuff_search_form = $('#stuff_search_form').form();
    //数据列表
    $stuff_datagrid = $('#stuff_datagrid').datagrid({
        url: ctxAdmin+'/sys/stuff/datagrid',
        fit:true,
        pagination: true,//底部分页
        rownumbers: true,//显示行数
        fitColumns: false,//自适应列宽
        striped: true,//显示条纹
        pageSize: 20,//每页记录数
        singleSelect: false,//单选模式
        checkbox: true,
        nowrap: false,
        border: false,
        remoteSort: true,//是否通过远程服务器对数据排序
        sortName: 'id',//默认排序字段
        sortOrder: 'desc',//默认排序方式 'desc' 'asc'
        idField: 'id',
       
        columns: [ [
            {field: 'ssn',title: 'ssn', width: 80 } ,
            {field: 'staffNo', title: 'staffNo', width: 100} ,
            {field: 'position', title: 'position', width: 100} ,
            {field: 'workYear',title: 'workYear', width: 160},
            {field: 'name',title: 'name',width: 200},
            {field: 'address',title: 'address',width: 136,} ,
            {field: 'phone',title: 'phone',width: 100},
            {field: 'gender',title: 'gender',width: 200 },
            {field: 'salary',title: 'salary',width: 200 }
        ]],
        toolbar:[{
            text:'删除',
            iconCls:'easyui-icon-remove',
            handler:function(){del()}
        },'-',{
            text:'清空所有',
            iconCls:'easyui-icon-no',
            handler:function(){delAll()}
        }]
    }).datagrid("showTooltip");

    //日志类型 搜索选项
    $('#filter_EQI_type').combobox({
        url:ctxAdmin+'/sys/stuff/stuffTypeCombobox?selectType=all',
        editable:false,//是否可编辑
        height:28,
        width:120
    });
});

//删除
function del() {
    var rows = $stuff_datagrid.datagrid('getSelections');
    if (rows.length > 0) {
        $.messager.confirm('确认提示！', '您确定要删除当前选中的所有行？', function (r) {
            if (r) {
                var ids = new Array();
                $.each(rows,function(i,row){
                    ids[i] = row.id;
                });
                $.ajax({
                    url:ctxAdmin+'/sys/stuff/remove',
                    type:'post',
                    data: {ids:ids},
                    traditional:true,
                    dataType:'json',
                    success:function(data) {
                        if (data.code==1){
                            $stuff_datagrid.datagrid('clearSelections');//取消所有的已选择项
                            $stuff_datagrid.datagrid('load');//重新加载列表数据
                            eu.showMsg(data.msg);//操作结果提示
                        } else {
                            eu.showAlertMsg(data.msg,'error');
                        }
                    }
                });
            }
        });
    } else {
        eu.showMsg("您未选择任何操作对象，请选择一行数据！");
    }
}

/**
 * 清空所有日志
 */
function delAll(){
    $.post(ctxAdmin+'/sys/stuff/removeAll',
        function (data) {
            if (data.code == 1) {
                $stuff_datagrid.datagrid('clearSelections');//取消所有的已选择项
                $stuff_datagrid.datagrid('load');//重新加载列表数据
                eu.showMsg(data.msg);//操作结果提示
            } else {
                eu.showAlertMsg(data.msg, 'error');
            }
        },
        'json');
}

//搜索
function search() {
    $stuff_datagrid.datagrid('load', $.serializeObject($stuff_search_form));
}
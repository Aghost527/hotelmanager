var $customers_datagrid;
var $customers_search_form;
$(function () {
    $customers_search_form = $('#customers_search_form').form();
    //数据列表
    $customers_datagrid = $('#customers_datagrid').datagrid({
        url: ctxAdmin+'/sys/customers/datagrid',
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
            {field: 'ssn',title: 'ssn',width: 80 } ,
            {field: 'name', title: 'name', width: 100} ,
            {field: 'address', title: 'address', width: 100} ,
            {field: 'gender',title: 'gender', width: 160},
            {field: 'phone',title: 'phone',width: 200},
            {field: 'dob',title: 'dob',width: 136} ,
            {field: 'cardNo',title: 'cardNo',width: 100},
            {field: 'memberNo',title: 'memberNo',width: 200 }
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
        url:ctxAdmin+'/sys/customers/customersTypeCombobox?selectType=all',
        editable:false,//是否可编辑
        height:28,
        width:120
    });
});

//删除
function del() {
    var rows = $customers_datagrid.datagrid('getSelections');
    if (rows.length > 0) {
        $.messager.confirm('确认提示！', '您确定要删除当前选中的所有行？', function (r) {
            if (r) {
                var ids = new Array();
                $.each(rows,function(i,row){
                    ids[i] = row.id;
                });
                $.ajax({
                    url:ctxAdmin+'/sys/customers/remove',
                    type:'post',
                    data: {ids:ids},
                    traditional:true,
                    dataType:'json',
                    success:function(data) {
                        if (data.code==1){
                            $customers_datagrid.datagrid('clearSelections');//取消所有的已选择项
                            $customers_datagrid.datagrid('load');//重新加载列表数据
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
    $.post(ctxAdmin+'/sys/customers/removeAll',
        function (data) {
            if (data.code == 1) {
                $customers_datagrid.datagrid('clearSelections');//取消所有的已选择项
                $customers_datagrid.datagrid('load');//重新加载列表数据
                eu.showMsg(data.msg);//操作结果提示
            } else {
                eu.showAlertMsg(data.msg, 'error');
            }
        },
        'json');
}

//搜索
function search() {
    $customers_datagrid.datagrid('load', $.serializeObject($customers_search_form));
}
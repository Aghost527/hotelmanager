var $membership_datagrid;
var $membership_search_form;
$(function () {
    $membership_search_form = $('#membership_search_form').form();
    //数据列表
    $membership_datagrid = $('#membership_datagrid').datagrid({
        url: ctxAdmin+'/sys/membership/datagrid',
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
            {field: 'memberNo',title: 'memberNo',width: 80 } ,
            {field: 'name', title: 'name', width: 100} ,
            {field: 'points', title: 'points', width: 100} ,
            {field: 'level',title: 'level', width: 160},
            {field: 'beginDate',title: 'beginDate',width: 200},
            {field: 'endDate',title: 'endDate',width: 136} 
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
        url:ctxAdmin+'/sys/membership/membershipTypeCombobox?selectType=all',
        editable:false,//是否可编辑
        height:28,
        width:120
    });
});

//删除
function del() {
    var rows = $membership_datagrid.datagrid('getSelections');
    if (rows.length > 0) {
        $.messager.confirm('确认提示！', '您确定要删除当前选中的所有行？', function (r) {
            if (r) {
                var ids = new Array();
                $.each(rows,function(i,row){
                    ids[i] = row.id;
                });
                $.ajax({
                    url:ctxAdmin+'/sys/membership/remove',
                    type:'post',
                    data: {ids:ids},
                    traditional:true,
                    dataType:'json',
                    success:function(data) {
                        if (data.code==1){
                            $membership_datagrid.datagrid('clearSelections');//取消所有的已选择项
                            $membership_datagrid.datagrid('load');//重新加载列表数据
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
    $.post(ctxAdmin+'/sys/membership/removeAll',
        function (data) {
            if (data.code == 1) {
                $membership_datagrid.datagrid('clearSelections');//取消所有的已选择项
                $membership_datagrid.datagrid('load');//重新加载列表数据
                eu.showMsg(data.msg);//操作结果提示
            } else {
                eu.showAlertMsg(data.msg, 'error');
            }
        },
        'json');
}

//搜索
function search() {
    $membership_datagrid.datagrid('load', $.serializeObject($membership_search_form));
}
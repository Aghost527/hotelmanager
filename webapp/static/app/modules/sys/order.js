var $order_datagrid;
var $order_search_form;
$(function () {
    $order_search_form = $('#order_search_form').form();
    //数据列表
    $order_datagrid = $('#order_datagrid').datagrid({
        url: ctxAdmin+'/sys/order/datagrid',
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
            {field: 'orderNo',title: 'orderNo', width: 80 } ,
            {field: 'roomNo', title: 'roomNo', width: 100} ,
            {field: 'customer', title: 'customer', width: 100} ,
            {field: 'checkInDate',title: 'checkInDate', width: 160},
            {field: 'checkOutDate',title: 'checkOutDate',width: 200},
            {field: 'price',title: 'price',width: 136} ,
            {field: 'pointCharge',title: 'pointCharge',width: 100},
            {field: 'cashierNo',title: 'cashierNo',width: 200 }
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
        url:ctxAdmin+'/sys/order/orderTypeCombobox?selectType=all',
        editable:false,//是否可编辑
        height:28,
        width:120
    });
});

//删除
function del() {
    var rows = $order_datagrid.datagrid('getSelections');
    if (rows.length > 0) {
        $.messager.confirm('确认提示！', '您确定要删除当前选中的所有行？', function (r) {
            if (r) {
                var ids = new Array();
                $.each(rows,function(i,row){
                    ids[i] = row.id;
                });
                $.ajax({
                    url:ctxAdmin+'/sys/order/remove',
                    type:'post',
                    data: {ids:ids},
                    traditional:true,
                    dataType:'json',
                    success:function(data) {
                        if (data.code==1){
                            $order_datagrid.datagrid('clearSelections');//取消所有的已选择项
                            $order_datagrid.datagrid('load');//重新加载列表数据
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
    $.post(ctxAdmin+'/sys/order/removeAll',
        function (data) {
            if (data.code == 1) {
                $order_datagrid.datagrid('clearSelections');//取消所有的已选择项
                $order_datagrid.datagrid('load');//重新加载列表数据
                eu.showMsg(data.msg);//操作结果提示
            } else {
                eu.showAlertMsg(data.msg, 'error');
            }
        },
        'json');
}

//搜索
function search() {
    $order_datagrid.datagrid('load', $.serializeObject($order_search_form));
}
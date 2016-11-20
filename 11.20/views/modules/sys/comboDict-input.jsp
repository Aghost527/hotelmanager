
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<script type="text/javascript">
	var page='host';
	var	urls='${ctxAdmin}/sys/comboDict/hostList';
	
	$('#page').combobox({ //重新加载次级下拉列表
		url:'${ctxAdmin}/sys/comboDict/pageList',
		valueField:'title',
		textField:'name',
		multiple:false,//是否可多选
		editable:false,
				onChange:function(n,o){
			var a=$('#page').combobox('getValue');
			showtype(a);}});
	

	

		$('#type').combobox({ //加载次级下拉列表
		url:urls,
		valueField:'title',
		textField:'name',
		multiple:false,//是否可多选
		editable:false,
		
		
	});

	

	
	
	function showtype(v){   
		page=v;
		$('#type').combobox({
			//重新加载次级下拉列表
		url:'${ctxAdmin}/sys/comboDict/'+v+'List',
		valueField:'title',
		textField:'name',
		
	});
    }   

	
	

    </script>

    <form id="comboDict_form" method="post" class="dialog-form" >
	    <input type="hidden"  name="id"/>
	    <!-- 用户版本控制字段 version -->
        <input type="hidden" id="version" name="version"/>
		<div>
			<label>选项所属页面:</label>
			<input id="page" name="page"  style="width: 260px;height: 28px;">

        </div>
		<div>
			<label>选项类别:</label>
		    <input id="type" name="type"  style="width: 260px;height: 28px;"  data-options="required:true,missingMessage:'请选择选项类别.'">
        </div>
		<div>
			<label>选项内容:</label>
		     <input id="name" name="name" type="text" class="easyui-validatebox textbox"
				maxLength="100" data-options="required:true,missingMessage:'请输入选项内容.'">
        </div>

		
	</form>
</div>
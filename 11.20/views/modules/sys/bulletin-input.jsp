
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
       
 
// $("#parentRange").combobox({
//             url:'${ctxAdmin}/sys/bulletin/nameList',
// 			valueField:'name',
// 			textField:'title',
//             multiple:false,//是否可多选
//             editable:false
//         });
  
    </script>
<div>
    <form id="bulletin_form" method="post" class="dialog-form" >
	    <input type="hidden"  name="id"/>
	    <!-- 用户版本控制字段 version -->
        <input type="hidden" id="version" name="version"/>
 		
		 
		<br>
 		<div>
			<label>留言标题:</label>
		
		    <input id="title" name="title" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'请输入留言标题.',validType:''">
           		
			 
        </div>
        <br>
        <div>
			<label>留言类型:</label>
            <select name="type" id="type" class="easyui-combobox"   style="width: 213px;height: 28px;">  
				<option value="留言">留言</option>  
				<option value="通知">通知</option>  
				<option value="意见">意见</option>  
			</select>
		</div>
		<br>

	 
	  	<br>
        <table style="border: 0px;width: 100%;">
        <div>    
			<label style="vertical-align: top;">留言内容	:</label>
               <!--  <td style="display: inline-block; width: 96px; vertical-align: top;">备注:</td>
               <td><textarea id="content_kindeditor" name="content" ></textarea></td>-->
				
			<input name="content" maxLength="255" class="easyui-textbox" maxLength="2000"  data-options="multiline:true" style="width:260px;height:155px;">
        </div>    
        </table>
		
	</form>
</div>

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
       
 

  
    </script>
<div>
    <form id="maintains_form" method="post" class="dialog-form" >
	    <input type="hidden"  name="id"/>
	    <!-- 用户版本控制字段 version -->
        <input type="hidden" id="version" name="version"/>
 		
		 
		<br>
 		<div>
			<label>record:</label>
		
		    <input id="record" name="record" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input record.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>staffNo:</label>
		
		    <input id="staffNo" name="staffNo" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input staffNo.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>roomNo:</label>
		
		    <input id="roomNo" name="roomNo" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input roomNo.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>linens:</label>
		
		    <input id="linens" name="linens" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input linens.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>lQty:</label>
		
		    <input id="lQty" name="lQty" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input lQty.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>lType:</label>
		
		    <input id="lType" name="lType" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input lType.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>reusable:</label>
		
		    <input id="reusable" name="reusable" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input reusable.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>cost:</label>
		
		    <input id="cost" name="cost" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input cost.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>date:</label>
		
		    <input id="date" name="date" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input date.',validType:''">
           		
			 
        </div>
      
        </table>
		
	</form>
</div>
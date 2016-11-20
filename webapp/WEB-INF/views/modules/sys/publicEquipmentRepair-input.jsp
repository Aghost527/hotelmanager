
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
       
 

  
    </script>
<div>
    <form id="publicEquipmentRepair_form" method="post" class="dialog-form" >
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
			<label>equipName:</label>
		
		    <input id="equipName" name="equipName" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input equipName.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>equipID:</label>
		
		    <input id="equipID" name="equipID" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input equipID.',validType:''">
           		
			 
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
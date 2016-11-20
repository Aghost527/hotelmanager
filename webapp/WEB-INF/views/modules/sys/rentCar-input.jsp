
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
       
 

  
    </script>
<div>
    <form id="rentCar_form" method="post" class="dialog-form" >
	    <input type="hidden"  name="id"/>
	    <!-- 用户版本控制字段 version -->
        <input type="hidden" id="version" name="version"/>
 		
		 
		<br>
 		<div>
			<label>VIN:</label>
		
		    <input id="VIN" name="VIN" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input VIN.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>company:</label>
		
		    <input id="company" name="company" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input company.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>carType:</label>
		
		    <input id="carType" name="carType" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input carType.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>capacity:</label>
		
		    <input id="capacity" name="capacity" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input capacity.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>priceTotal:</label>
		
		    <input id="priceTotal" name="priceTotal" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input priceTotal.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>rentDate:</label>
		
		    <input id="rentDate" name="rentDate" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input rentDate.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>returnDate:</label>
		
		    <input id="returnDate" name="returnDate" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input returnDate.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>rentPlace:</label>
		
		    <input id="rentPlace" name="rentPlace" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input rentPlace.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>returnPlace:</label>
		
		    <input id="returnPlace" name="returnPlace" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input returnPlace.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>rentBy:</label>
		
		    <input id="rentBy" name="rentBy" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input rentBy.',validType:''">
           		
			 
        </div>
      
        </table>
		
	</form>
</div>
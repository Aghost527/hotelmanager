
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
       
 

  
    </script>
<div>
    <form id="buses_form" method="post" class="dialog-form" >
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
			<label>lines:</label>
		
		    <input id="lines" name="lines" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input lines.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>destination:</label>
		
		    <input id="destination" name="destination" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input destination.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>departTime:</label>
		
		    <input id="departTime" name="departTime" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input departTime.',validType:''">
           		
			 
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
			<label>price:</label>
		
		    <input id="price" name="price" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input price.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>takenBy:</label>
		
		    <input id="takenBy" name="takenBy" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input takenBy.',validType:''">
           		
			 
        </div>
      
        </table>
		
	</form>
</div>
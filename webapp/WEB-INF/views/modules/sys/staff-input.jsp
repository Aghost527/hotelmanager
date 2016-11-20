
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
       
 

  
    </script>
<div>
    <form id="staff_form" method="post" class="dialog-form" >
	    <input type="hidden"  name="id"/>
	    <!-- 用户版本控制字段 version -->
        <input type="hidden" id="version" name="version"/>
 		
		 
		<br>
 		<div>
			<label>ssn:</label>
		
		    <input id="ssn" name="ssn" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input ssn.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>staffNo:</label>
		
		    <input id="staffNo" name="staffNo" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input staffNo.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>position:</label>
		
		    <input id="position" name="position" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input position.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>workYear:</label>
		
		    <input id="workYear" name="workYear" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input workYear.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>name:</label>
		
		    <input id="name" name="name" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input name.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>address:</label>
		
		    <input id="address" name="address" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input address.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>phone:</label>
		
		    <input id="phone" name="phone" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input phone.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>gender:</label>
		
		    <input id="gender" name="gender" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input gender.',validType:''">
           		
			 
        </div>
      
	  <br>
 		<div>
			<label>salary:</label>
		
		    <input id="salary" name="salary" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input salary.',validType:''">
           		
			 
        </div>
        </table>
		
	</form>
</div>
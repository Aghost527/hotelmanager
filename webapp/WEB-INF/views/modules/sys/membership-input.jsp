
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
       
 

  
    </script>
<div>
    <form id="membership_form" method="post" class="dialog-form" >
	    <input type="hidden"  name="id"/>
	    <!-- 用户版本控制字段 version -->
        <input type="hidden" id="version" name="version"/>
 		
		 
		<br>
 		<div>
			<label>memberNo:</label>
		
		    <input id="memberNo" name="memberNo" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input memberNo.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>name:</label>
		
		    <input id="name" name="name" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input name.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>points:</label>
		
		    <input id="points" name="points" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input points.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>level:</label>
		
		    <input id="level" name="level" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input level.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>beginDate:</label>
		
		    <input id="beginDate" name="beginDate" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input beginDate.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>endDate:</label>
		
		    <input id="endDate" name="endDate" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input endDate.',validType:''">
           		
			 
        </div>
		
      
        </table>
		
	</form>
</div>
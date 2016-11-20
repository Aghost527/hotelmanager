
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
       
 

  
    </script>
<div>
    <form id="deal_form" method="post" class="dialog-form" >
	    <input type="hidden"  name="id"/>
	    <!-- 用户版本控制字段 version -->
        <input type="hidden" id="version" name="version"/>
 		
		 
		<br>
 		<div>
			<label>activity:</label>
		
		    <input id="activity" name="activity" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input activity.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>joinLevel:</label>
		
		    <input id="joinLevel" name="joinLevel" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input joinLevel.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>capacity:</label>
		
		    <input id="capacity" name="capacity" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input capacity.',validType:''">
           		
			 
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
		<br>
 		<div>
			<label>dealNo:</label>
		
		    <input id="dealNo" name="dealNo" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input dealNo.',validType:''">
           		
			 
        </div>
		
      
        </table>
		
	</form>
</div>
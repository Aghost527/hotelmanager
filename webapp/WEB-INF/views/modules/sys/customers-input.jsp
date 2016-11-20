
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
       
 

  
    </script>
<div>
    <form id="customers_form" method="post" class="dialog-form" >
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
			<label>name:</label>
		
		    <input id="name" name="name" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input name.',validType:''">
           		
			 
        </div>
        <br>
		<div>
			<label>address:</label>
		
		    <input id="address" name="address" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="350" data-options="required:true,missingMessage:'input address.',validType:''">
           		
			 
        </div>
        <br>
		 <div>
			<label>gender:</label>
            <select name="type" id="type" class="easyui-combobox"   style="width: 213px;height: 28px;">  
				<option value="man">man</option>  
				<option value="woman">woman</option>  
			</select>
		</div>
		<br>
		<div>
			<label>phone:</label>
		
		    <input id="phone" name="phone" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input phone.',validType:''">
           		
			 
        </div>
        <br>
		<div>
			<label>dob:</label>
		
		    <input id="dob" name="dob" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input dob.',validType:''">
           		
			 
        </div>
        <br>
		<div>
			<label>cardNo:</label>
		
		    <input id="cardNo" name="cardNo" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input cardNo.',validType:''">
           		
			 
        </div>
        <br>
		<div>
			<label>memberNo:</label>
		
		    <input id="memberNo" name="memberNo" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input memberNo.',validType:''">
           		
			 
        </div>
        <br>
		
    

	 
	  	
        </table>
		
	</form>
</div>
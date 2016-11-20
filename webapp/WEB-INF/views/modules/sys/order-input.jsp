
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
       
 

  
    </script>
<div>
    <form id="order_form" method="post" class="dialog-form" >
	    <input type="hidden"  name="id"/>
	    <!-- 用户版本控制字段 version -->
        <input type="hidden" id="version" name="version"/>
 		
		 
		<br>
 		<div>
			<label>orderNo:</label>
		
		    <input id="orderNo" name="orderNo" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input orderNo.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>roomNo:</label>
		
		    <input id="roomNo" name="roomNo" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input roomNo.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>customer:</label>
		
		    <input id="customer" name="customer" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input customer.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>checkInDate:</label>
		
		    <input id="checkInDate" name="checkInDate" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input checkInDate.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>checkOutDate:</label>
		
		    <input id="checkOutDate" name="checkOutDate" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input checkOutDate.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>price:</label>
		
		    <input id="price" name="price" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input price.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>pointCharge:</label>
		
		    <input id="pointCharge" name="pointCharge" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input pointCharge.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>cashierNo:</label>
		
		    <input id="cashierNo" name="cashierNo" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input cashierNo.',validType:''">
           		
			 
        </div>
      
        </table>
		
	</form>
</div>
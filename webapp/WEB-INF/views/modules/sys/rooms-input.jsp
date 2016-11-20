
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<script type="text/javascript">
       
 

  
    </script>
<div>
    <form id="rooms_form" method="post" class="dialog-form" >
	    <input type="hidden"  name="id"/>
	    <!-- 用户版本控制字段 version -->
        <input type="hidden" id="version" name="version"/>
 		
		 
		
		<br>
 		<div>
			<label>roomNo:</label>
		
		    <input id="roomNo" name="roomNo" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input roomNo.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>inRoomBill:</label>
		
		    <input id="inRoomBill" name="inRoomBill" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input inRoomBill.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>capacity:</label>
		
		    <input id="capacity" name="capacity" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input capacity.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>bedsNo:</label>
		
		    <input id="bedsNo" name="bedsNo" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input bedsNo.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>type:</label>
		
		    <input id="type" name="type" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input type.',validType:''">
           		
			 
        </div>
		<br>
 		<div>
			<label>status:</label>
		
		    <input id="status" name="status" type="text" class="easyui-validatebox textbox" style="width: 213px;height: 28px;"
				maxLength="50" data-options="required:true,missingMessage:'input status.',validType:''">
           		
			 
        </div>
		
      
        </table>
		
	</form>
</div>
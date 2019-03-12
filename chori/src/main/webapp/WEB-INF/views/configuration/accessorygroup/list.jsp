<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="resources/userJs/accessorygroup/list.js"></script>
<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">Accessory Group List</div>
		</div>
		
		<button class="btn btn-info" name="btnAdd" style="margin-left:20px;margin-top:10px" id="btnAdd" ><spring:message code="lbl.btnAdd_ag"/></button>
		
		<div class="block-content collapse in">		
			<div class="span12">
				<table  cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered display responsive" id="listAccessoryGroup">
					<thead>
						<tr>
							<th><spring:message code="lbl.lblNo"/></th>
							<th><spring:message code="lbl.lblAGCode"/></th>							
							<th><spring:message code="lbl.lblDescription"/></th>
							<th><spring:message code="lbl.lblAction"/></th>
						</tr>
					</thead>
					
				</table>
			</div>
		</div>
		
	</div>
	<!-- /block -->
	
</div>
<!-- dialog when add AccessoryGroup-->
	<div id="dialogAddAccessoryGroup" class="" style="display: none;" name="addAccessoryGroupForm">
		<div class="form-group" >
	       <label for="txtAccessoryGroupCode" class="col-sm-3 control-label"><spring:message code="lbl.lblAGCode"/><span style="color: red">*</span></label>
	       <div class="col-sm-3">
	           <input type="text" class="form-control" placeholder="Accessory group code" id="txtAccessoryGroupCode" value="" name="txtAccessoryGroupCode" >
	           
	             <span style="font-size: 11px; color: red" id="errorTxtAccessoryGroupCode" ></span>
	       </div>
	    </div>
	    <br/>
		<div class="form-group" >
	       <label for="txtDescription" class="col-sm-3 control-label"><spring:message code="lbl.lblDescription"/></label>
	       <div class="col-sm-4">
	           <textarea rows="5" cols="5"  class="form-control" id="txtDescription" value="" name="txtDescription" ></textarea>
	           <p align="center"> 
	             <span style="font-size: 11px; color: red" id="errorTxtDescription" ></span></p>
	       </div>
	    </div>
	   
	</div>
<!-- dialog when edit AccessoryGroup-->
	<div id="dialogEditAccessoryGroup" class="" style="display: none;" name="editAccessoryGroupForm">
		<div class="form-group">
	       <label for="txtAccessoryGroupCode" class="col-sm-3 control-label"><spring:message code="lbl.lblAGCode"/><span style="color: red">*</span></label>
	       <div class="col-sm-9">
	           <input type="text" class="form-control" id="txtAccessoryGroupCode" value="" name="txtAccessoryGroupCode" disabled>
	       </div>
	    </div>
	    <br/>
		<div class="form-group">
	       <label for="txtDescription" class="col-sm-3 control-label"><spring:message code="lbl.lblDescription"/></label>
	       <div class="col-sm-9">
	         <textarea rows="5" cols="5"  class="form-control" id="txtDescription" value="" name="txtDescription" ></textarea>
	         <p align="center"> 
	             <span style="font-size: 11px; color: red" id="errorTxtDescription" ></span></p>
	       </div>
	    </div>
	</div>
<!-- This is dialog confirm delete -->
	<div id="deleteAccessoryGroupDialog" style="display: none;">
		<span id="messageContent"></span>
	</div>
	
	<!-- This div is used to show message to user -->
	<div id="messageDialog" style="display: none;"><span id="messageContent"></span></div>
	<!-- 
	<script type="text/javascript">
	  $(function() {
		    $( "#dtpCreateDate" ).datepicker();
		    $("#dtpCreateDate").datepicker('setDate', new Date());
		    $( "#dtpCreateDate").datepicker( "option", "disabled", true );
		  });
   	</script>
   	 -->


	
	

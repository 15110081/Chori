<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="resources/userJs/garmentstyle/dataTables.rowsGroup.js"></script>

<script src="resources/userJs/fabricassignment/list.js"></script>

<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left"></div>
		</div>
		<input type="button" class="form-control btn btn-info" id="btnAssignFabricForPi"
		 name="btnAssignFabricForPi" value="<spring:message code="lbl.assignfabric.btnAssignFabric" />">	
	</div>
	<!-- /block -->
</div>

<!-- This is dialog to show detail of fabric assignment -->
<div id="fabricAssignmentListDialog" style="display: none;" name="fabricAssignmentListDialog">
	<div class="form-group">
		<label class="control-label col-sm-2" for="txtLotNumber"><spring:message
				code="lbl.assignfabric.fabricAssignmentListDialog.lotnumber" /></label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="txtLotNumber" value="" name="txtLotNumber" disabled>	
			</div>				
	</div>		
	<div class="form-group">
		<label for="txtCustomerName" class="control-label col-sm-2"><spring:message
				code="lbl.assignfabric.fabricAssignmentListDialog.customername" /></label>
		<div class="col-sm-10">
		<input type="text" class="form-control" id="txtCustomerName" value="" name="txtCustomerName" disabled>
		<input type="text" class="form-control" id="txtCustomerCode" value="" name="txtCustomerCode" style="display: none;">	
		</div>				
	</div>	
	<div class="form-group">
		<label for="txtFactoryName" class="control-label col-sm-2"><spring:message
				code="lbl.assignfabric.fabricAssignmentListDialog.factoryname" /></label>
		<div class="col-sm-10">
		<input type="text" class="form-control" id="txtFactoryName" value="" name="txtFactoryName" disabled>
		<input type="text" class="form-control" id="txtFactoryCode" value="" name="txtFactoryCode" style="display: none;">	
		</div>				
	</div>	 
	
	<div class="block-content collapse in">
		<div class="span10">
			<table class="table table-striped table-bordered display responsive"
				id="listFabricAssignment">
				<thead>
					<tr>
						<th></th>
						<th><spring:message code="lbl.assignfabric.listFabricAssignment.row1" /></th>
						<th><spring:message code="lbl.assignfabric.listFabricAssignment.row2" /></th>
						<th><spring:message code="lbl.assignfabric.listFabricAssignment.row3" /></th>
						<th><spring:message code="lbl.assignfabric.listFabricAssignment.row4" /></th>
						<th><spring:message code="lbl.assignfabric.listFabricAssignment.row5" /></th>
						<th><spring:message code="lbl.assignfabric.listFabricAssignment.row6" /></th>
						<th></th>
					</tr>
				</thead>
			</table>
		</div>			
	</div>
	<div class="form-group">
     		<div class="col-sm-offset-2 col-sm-10" align="right">
       		<button type="submit" class="btn btn-primary" id="btnSubmit"><spring:message code="lbl.assignfabric.listFabricAssignment.btnAssign" /></button>
       		<button type="button" class="btn btn-warning" id="btnCancel"><spring:message code="lbl.assignfabric.listFabricAssignment.btnCancel" /></button>
     		</div>
   	</div>
</div>

<!-- This is dialog to show detail of fabric assignment -->
	<div id="fabricAssignmentDialog" style="display: none;" name="fabricAssignmentDialog">
		<div class="form-group">
			<label class="control-label col-sm-2" for="txtFabricNo"><spring:message code="lbl.assignfabric.fabricAssignmentDialog.fabricindent" /></label>				
			<input type="text" class="form-control" id="txtFabricNo" value="" name="txtFabricNo" disabled>				
		</div>		
		
		<div class="form-group" style="margin-left:20px;margin-top:20px">
	      	<div class="col-sm-9">
		<button class="btn btn-info" id="btnAddNewAssignment"><spring:message code="lbl.assignfabric.fabricAssignmentDialog.btnAddNewAssignment" /></button>
			</div>
		 </div>
				
		<div class="form-group" >
    	<div class="block-content collapse in">
			<div class="span6">
			<div id="listFabricAssignmentDetailCover">
				<table class="table table-striped table-bordered display responsive"
					id="listFabricAssignmentDetail">
					<thead>
						<tr>
							<th><spring:message code="lbl.assignfabric.listFabricAssignmentDetail.row1" /></th>
							<th><spring:message code="lbl.assignfabric.listFabricAssignmentDetail.row2" /></th>
							<th><spring:message code="lbl.assignfabric.listFabricAssignmentDetail.row3" /></th>
							<th><spring:message code="lbl.assignfabric.listFabricAssignmentDetail.row4" /><th>
						</tr>
					</thead>
				</table>
				</div>
			</div>
		</div>
	</div>
</div>
	
	
<!-- This is dialog to add new fabric assignment -->

	<div id="addNewFabricAssignmentDialog" style="display: none;"
		name="addNewFabricAssignmentDialog">
		<div class="form-group">
			<label class="control-label col-sm-2" for="txtFabricNo"><spring:message
				code="lbl.assignfabric.addNewFabricAssignmentDialog.fabricindent" /></lable>				
					<input type="text" class="form-control" id="txtFabricNo" value="" name="txtFabricNo" disabled>				
		</div>		
		
		<div class="form-group">
			<label class="control-label col-sm-2" for="txtLotNo"><spring:message
				code="lbl.assignfabric.addNewFabricAssignmentDialog.forlotno" /></lable>				
					<input type="text" class="form-control" id="txtLotNo" value="" name="txtLotNo" disabled>				
		</div>	
				
		<div class="form-group" style="margin-left: 20px; margin-top: 20px">
			<div class="col-sm-9">
				<button id="btn100PercentAssign" class="btn btn-info"
					style="margin-left: 20px; margin-top: 10px">
					<spring:message code="lbl.assignfabric.btn100PercentAssign" />
				</button>
				
				<button id="btnReset100Percent" class="btn btn-info"
					style="margin-left: 20px; margin-top: 10px">
					<spring:message code="lbl.assignfabric.btnReset100Percent" />
				</button>
				
			</div>
			
			
		</div>	
				
				
		<div class="form-group" >
    	<div class="block-content collapse in">
			<div class="span8">
			<div id="listAddNewFabricAssignmentCover">
				<table class="table table-striped table-bordered display responsive"
					id="listAddNewFabricAssignment">
					<thead>
						<tr>
							<th><spring:message code="lbl.assignfabric.listAddNewFabricAssignment.row1" /></th>
							<th><spring:message code="lbl.assignfabric.listAddNewFabricAssignment.row2" /></th>
							<th><spring:message code="lbl.assignfabric.listAddNewFabricAssignment.row3" /></th>
							<th><spring:message code="lbl.assignfabric.listAddNewFabricAssignment.row8" /></th>
							<th><spring:message code="lbl.assignfabric.listAddNewFabricAssignment.row4" /></th>
							<th><spring:message code="lbl.assignfabric.listAddNewFabricAssignment.row5" /></th>
							<th><spring:message code="lbl.assignfabric.listAddNewFabricAssignment.row6" /></th>	
							<th><spring:message code="lbl.assignfabric.listAddNewFabricAssignment.row7" /></th>													
						</tr>
					</thead>
				</table>
				</div>
			</div>
		</div>
	</div>
	</div>
	
<!-- This div is used to show message to user -->
<div id="messageDialog" style="display: none;">
	<span id="messageContent"></span>
</div>

	
	<!-- This div is used to show message to user -->
	<div id="messageDialog" style="display: none;"><span id="messageContent"></span></div>	
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="resources/userJs/garmentstyle/dataTables.rowsGroup.js"></script>
<script src="resources/userJs/operation/fabricassignment/list.js"></script>
<script src="resources/userJs/operation/rfpi/rfpiGridDetail.js"></script>

<script
	src="${pageContext.request.contextPath}/resources/userJs/pi/list.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fancybox/jquery.mousewheel-3.0.6.pack.js"></script>
<!-- 
<script
	src="${pageContext.request.contextPath}/resources/userJs/operation/assigninternalaccessories/assigninternalaccessories.js"></script> -->
	

<head>
<title>Pi</title>
</head>
<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">Pi List</div>
		</div>
		<div class="block-content collapse in">
			<div class="span12">
				<button class="btn btn-primary" name="btnAddPi" id="btnAddPi"><spring:message code="listPi.btnAddNewPi"/>
					</button>
					
		
	 		<div class="col-sm-9">
	 		<spring:message code="listPi.ddlStatus"/>
				<select id="ddlShipmentStatus">
					<option value="None" selected disabled><spring:message code="listPi.ddlStatus.Select"/></option>
					<option value="Yes"><spring:message code="listPi.ddlStatus.Yes"/></option>
					<option value="No"><spring:message code="listPi.ddlStatus.No"/></option>
					<option value="All"><spring:message code="listPi.ddlStatus.All"/></option> 
				</select>
			</div>
			
					
				<table cellpadding="0" cellspacing="0" border="0"
					class="table table-striped table-bordered display responsive"
					id="listPi">
					<thead>
						<tr>
							<th><spring:message code="listPi.row1"/></th>
							<th><spring:message code="listPi.row2"/></th>
							<th><spring:message code="listPi.row3"/></th>
							<th><spring:message code="listPi.row4"/></th>
							<th><spring:message code="listPi.row5"/></th>
							<th><spring:message code="listPi.row6"/></th>
							<th><spring:message code="listPi.row7"/></th>
							<th><spring:message code="listPi.row8"/></th>
						</tr>
					</thead>

				</table>
			</div>
		</div>
	</div>
	<!-- /block -->
</div>

<div id="dialogAddPi" style="display: none;" class="" name="dialogAddPi">
	<form:form method="POST" modelAttribute="piModel" action="pi/add"
		enctype="multipart/form-data" class="">
		<div  style="width: 70%; float: left">
			<div class="form-group">
				<label for="noneorderaccessories" class="col-sm-6 control-label"><spring:message code="dialogAddPi.txtNoneorderaccessories"/></label>
				<div class="col-sm-6">
					<form:checkbox class="form-control" id="noneorderaccessories" path="noneorderaccessories"
						name="noneorderaccessories" value="false"/>
				</div>
			</div>
			
			<div class="form-group">
				<label for="customer1" class="col-sm-3 control-label"><spring:message code="dialogAddPi.ddlCustomername1"/><span style="color: red;">*</span></label>
				<div class="col-sm-9">
					<form:select id="customer1" name="customer1" path="customer1" >
						<form:option value="none"><spring:message code="dialogAddPi.ddlCustomername1.optionSelect"/></form:option>
					</form:select>
					<span style="font-size: 11px; color: red" id="errorCustomer1"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="consignee" class="col-sm-3 control-label"><spring:message code="dialogAddPi.ddlConsignee.optionSelect"/></label>
				<div class="col-sm-9">
					<form:select id="consignee" name="consignee" path="consignee" >
						<form:option value="none"><spring:message code="dialogAddPi.ddlConsignee.optionSelect"/></form:option>
					</form:select>
					<span style="font-size: 11px; color: red" id="errorConsignee"></span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="txtLotNumber" class="col-sm-3 control-label"><spring:message code="dialogAddPi.txtLotNumber"/><span style="color: red;">*</span>
				</label>
				<div class="col-sm-9">
					<form:input type="text" class="form-control" id="lotnumber"
						path="lotnumber" value=""
						name="lotnumber" />
					<span style="font-size: 11px; color: red" id="errorLotnumber"></span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="factorycode" class="col-sm-3 control-label"><spring:message code="dialogAddPi.ddlFactory"/><span style="color: red;">*</span></label>
				<div class="col-sm-9">
					<form:select id="factorycode" name="factorycode" path="factorycode" >
						<form:option value="none"><spring:message code="dialogAddPi.ddlFactory.optionSelect"/></form:option>
					</form:select>
					<span style="font-size: 11px; color: red" id="errorFactorycode"></span>

				</div>
			</div>
			
			<div class="form-group">
				<label for="country" class="col-sm-3 control-label"><spring:message code="dialogAddPi.ddlCountry"/>
				<span style="color: red;">*</span></label>
				<div class="col-sm-9">
					<select id="country">
					<option value="none"><spring:message code="dialogAddPi.ddlCountry.optionSelect"/></option>
					</select>
					
					<span style="font-size: 11px; color: red" id="errorCountry"></span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="destinationcode" class="col-sm-3 control-label"><spring:message code="dialogAddPi.ddlDestination"/>
				<span style="color: red;">*</span></label>
				<div class="col-sm-9">
					<form:select id="destinationcode" name="destinationcode"
						path="destinationcode" >
						<form:option value="none"><spring:message code="dialogAddPi.ddlDestination.optionSelect"/></form:option>
					</form:select>
					<span style="font-size: 11px; color: red" id="errorDestinationcode"></span>
				</div>
			</div>

			
	<div class="form-group">
		<div class="col-sm-9">
			<input class="btn btn-primary" type="button" class="form-control" id="btnGridPi"
				 value="Import" name="btnGridPi" >
			
		</div>
	</div>
	
	<div class="form-group">
		<div class="col-sm-9">
			<input class="btn btn-primary" type="button" class="form-control" id="btnFabricRemainAdd"
				 value="Show" name="btnFabricRemainAdd" >
		</div>
	</div>
	
	<div class="form-group" id="tableViewFabricRemain" hidden="true">
    	<div class="block-content collapse in">
			<div class="span8">		
				<table class="table table-striped table-bordered display responsive"
					id="listViewFabricRemain">
					<thead>
						<tr>
							<th>Fabric No</th>
							<th>Customer</th>
							<th>Color Code</th>
							<th>Inventory remained</th>	
																		
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	
<!-- 
	<div class="form-group">
		<div class="col-sm-9">
			<input type="button" class="form-control" id="btnAssignFabric"
				 value="Assign Fabric" name="btnAssignFabric" >
		</div>
	</div>
	 -->
		</div>

		<div class="right" style="width: 30%; float: right">
		
			<div class="form-group">
				<label for="customer1" class="col-sm-3 control-label"><spring:message code="dialogAddPi.ddlBrandname"/>
					<span style="color: red;">*</span></label>
				<div class="col-sm-9">
					<form:select id="brandcode" name="brandcode" path="brandcode" >
						<form:option value="0"><spring:message code="dialogAddPi.ddlBrandname.optionSelect"/></form:option>
					</form:select>
					<span style="font-size: 11px; color: red" id="errorBrandcode"></span>
				</div>
			</div>
		    
			<div class="form-group">
				<label for="pireceiveddate" class="col-sm-3 control-label"><spring:message code="dialogAddPi.txtPIReceivedDate"/></label>
				<div class="col-sm-9">
					<form:input type="text" class="form-control" id="pireceiveddate"
						 value=""
						name="pireceiveddate" path="pireceiveddate" />
					<span style="font-size: 11px; color: red" id="errorPireceiveddate"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="piestshipdate" class="col-sm-3 control-label"><spring:message code="dialogAddPi.txtPIEstShipDate"/><span style="color: red;">*</span></label>
				<div class="col-sm-9">
					<form:input type="text" class="form-control" id="piestshipdate"
						 value="" name="piestshipdate" 
						path="piestshipdate" />
					<span style="font-size: 11px; color: red" id="errorPiestshipdate"></span>
				</div>
			</div>
			<div class="form-group">
				<label for="mfgstarteddate" class="col-sm-3 control-label"><spring:message code="dialogAddPi.txtMFGStartedDate"/></label>
				<div class="col-sm-9">
					<form:input type="text" class="form-control" id="mfgstarteddate"
						value="" path="mfgstarteddate"  />
					<span style="font-size: 11px; color: red" id="errorMfgstarteddate"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="mfgfinisheddate" class="col-sm-3 control-label"><spring:message code="dialogAddPi.txtMFGFinishedDate"/></label>
				<div class="col-sm-9">
					<form:input type="text" class="form-control" id="mfgfinisheddate"
						 value="" path="mfgfinisheddate" />
					<span style="font-size: 11px; color: red" id="errorMfgfinisheddate"></span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="piattachedFile" class="col-sm-3 control-label"><spring:message code="dialogAddPi.filePiAttached"/></label>
				<div class="col-sm-9">
					<form:input type='file' class="form-control" path="piattached.file"
						name='piattached' id='piattached' />
				</div>
			</div>

			<div class="form-group">
				<label for=shipmentstatus class="col-sm-3 control-label"><spring:message code="dialogAddPi.ddlShipmentStatus"/><span style="color: red;">*</span></label>
				<div class="col-sm-9">
					<form:select id="shipmentstatus" name="shipmentstatus" path="shipmentstatus" />
					<span style="font-size: 11px; color: red" id="errorShipmentstatus"></span>

				</div>
			</div>

			

			<div class="form-group">
				<label for="status" class="col-sm-3 control-label"><spring:message code="dialogAddPi.ddlStatus"/></label>
				<div class="col-sm-9">
					<form:select id="status" name="status" path="status">
						<form:option value="Normal"><spring:message code="dialogAddPi.ddlStatus.normal"/></form:option>
						<form:option value="Urgent"><spring:message code="dialogAddPi.ddlStatus.urgent"/></form:option>
						<form:option value="Pending"><spring:message code="dialogAddPi.ddlStatus.pending"/></form:option>
					</form:select>
					<span style="font-size: 11px; color: red" id="errorStatus"></span>
				</div>
			</div>
			
			 
			 <div class="form-group">
				<label for="packingguidecode" class="col-sm-3 control-label"><spring:message code="dialogAddPi.ddlPackingGuide"/></label>
				<div class="col-sm-9">
					<form:select id="packingguidecode" name="packingguidecode" path="packingguidecode" />
					<span style="font-size: 11px; color: red" id="errorPackingguidecode"></span>

				</div>
			</div>
			 
			 
			<div class="form-group">
				<label for="remark" class="col-sm-3 control-label"><spring:message code="dialogAddPi.txareaRemak"/></label>
				<div class="col-sm-9">
					<form:textarea id="remark" name="remark" path="remark" rows="5"
						cols="5" />
				</div>
			</div>


			<input type="submit" value="Save" class="btn btn-primary btn-sm"
				id="btnAdd">

		</div>
	</form:form>
</div>

<!-- Edit dialog   action="pi/edit" action="#"-->
<div id="editPIdialog" style="display: none;" class="" name="editPIdialog">
	<form:form method="POST" modelAttribute="piModel" action="pi/edit"
		enctype="multipart/form-data" class="" id="editPiForm" >
		<div style="width: 70%; float: left">
			<div class="form-group">
				<label for="noneorderaccessories" class="col-sm-6 control-label"><spring:message code="editPiDialog.txtNoneorderaccessories"/></label>
				<div class="col-sm-6">
					<form:checkbox class="form-control" id="noneorderaccessories" path="noneorderaccessories"
						name="noneorderaccessories"/>
				</div>
			</div>
			
			<div class="form-group">
				<label for="customer1" class="col-sm-3 control-label"><spring:message code="editPiDialog.ddlCustomername1"/><span style="color: red;">*</span></label>
				<div class="col-sm-9">
					<form:select id="customer1" name="customer1" path="customer1" >
						
					</form:select>
					<span style="font-size: 11px; color: red" id="errorCustomer1"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="consignee" class="col-sm-3 control-label"><spring:message code="editPiDialog.ddlConsignee"/></label>
				<div class="col-sm-9">
					<form:select id="consignee" name="consignee" path="consignee" />
					<span style="font-size: 11px; color: red" id="errorConsignee"></span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="txtLotNumber" class="col-sm-3 control-label"><spring:message code="editPiDialog.txtLotNumber"/><span style="color: red;">*</span>
				</label>
				<div class="col-sm-9">
					<form:input readonly="true" type="text" class="form-control" id="lotnumber"
						path="lotnumber"  value=""
						name="lotnumber" />
				</div>
			</div>
			
			<div class="form-group">
				<label for="factorycode" class="col-sm-3 control-label"><spring:message code="editPiDialog.ddlFactory"/><span style="color: red;">*</span></label>
				<div class="col-sm-9">
					<form:select id="factorycode" name="factorycode" path="factorycode" />
					<span style="font-size: 11px; color: red" id="errorFactorycode"></span>

				</div>
			</div>
			
			<div class="form-group">
				<label for="country" class="col-sm-3 control-label"><spring:message code="editPiDialog.ddlCountry"/><span style="color: red;">*</span></label>
				<div class="col-sm-9">
					<select id="country" name="country">
					</select>
					<span style="font-size: 11px; color: red" id="errorCountry"></span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="destinationcode" class="col-sm-3 control-label"><spring:message code="editPiDialog.ddlDestination"/><span style="color: red;">*</span></label>
				<div class="col-sm-9">
					<form:select id="destinationcode" name="destinationcode"
						path="destinationcode" />
					<span style="font-size: 11px; color: red" id="errorDestinationcode"></span>
				</div>
			</div>
			
	
			<!-- 
	<div class="form-group">
		<div class="col-sm-9">
			<input type="button" class="form-control" id="btnGridPi"
				 value="Import" name="btnGridPi" >
			
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-9">
			<input type="button" class="form-control" id="btnAssignFabric"
				 value="Assign Fabric" name="btnAssignFabric" >
		</div>
	</div>
	 -->
	 
	 		<div id="PiGridDetailTableCover">
					<table
						class="table table-striped table-bordered display responsive"
						id="listPiGridDetail">
						<thead>
							<tr>
								<th rowspan="2">Total Pcs</th>
								<th rowspan="2">Color</th>
								<th colspan="5">Garment Style</th>
								<th rowspan="2">Size</th>
								<th rowspan="2">Pcs</th>
							</tr>
							<tr>
								<th>Name</th>
								<th>Image</th>
								<th>Type</th>
								<th>Sewing guide</th>
								<th>Packing guide</th>
							</tr>
						</thead>
					</table>
				</div>
	 <div class="form-group">
		<div class="col-sm-9">
			<input class="btn btn-primary" type="button" class="form-control" id="btnFabricRemainEdit"
				 value="Show" name="btnFabricRemainEdit" >
			
		</div>
	</div>
	<div class="form-group" id="tableViewFabricRemainEdit" hidden="true">
    	<div class="block-content collapse in">
			<div class="span8">		
				<table class="table table-striped table-bordered display responsive"
					id="listViewFabricRemainEdit">
					<thead>
						<tr>
							<th>Fabric No</th>
							<th>Customer</th>
							<th>Color Code</th>
							<th>Inventory remained</th>	
																		
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	
		</div>

		<div class="right" style="width: 30%; float: right">
			
			<div class="form-group">
				<label for="brandcode" class="col-sm-3 control-label"><spring:message code="editPiDialog.ddlBrandname"/><span style="color: red;">*</span></label>
				<div class="col-sm-9">
					<form:select id="brandcode" name="brandcode" path="brandcode" >
					</form:select>
					<span style="font-size: 11px; color: red" id="errorBrandcode"></span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="pireceiveddateEdit" class="col-sm-3 control-label"><spring:message code="editPiDialog.txtPIReceivedDate"/></label>
				<div class="col-sm-9">
				<!-- 
					<input type="text" class="form-control" id="pireceiveddate2"
						value="" name="pireceiveddate2" />
					<span style="font-size: 11px; color: red" id="errorPireceiveddate2"></span>
				 -->		
				 <form:input type="text" class="form-control" id="pireceiveddateEdit"
						value=""
						name="pireceiveddateEdit" path="pireceiveddate" />
					
				</div>
			</div>

			<div class="form-group">
				<label for="piestshipdateEdit" class="col-sm-3 control-label"><spring:message code="editPiDialog.txtPIEstShipDate"/><span style="color: red;">*</span></label>
				<div class="col-sm-9">
					<form:input type="text" class="form-control" id="piestshipdateEdit"
						value="" name="piestshipdateEdit"
						path="piestshipdate" />
					<span style="font-size: 11px; color: red" id="errorPiestshipdateEdit"></span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="mfgstarteddateEdit" class="col-sm-3 control-label"><spring:message code="editPiDialog.txtMFGStartedDate"/></label>
				<div class="col-sm-9">
					<form:input type="text" class="form-control" id="mfgstarteddateEdit" name="mfgstarteddateEdit"
						value="" path="mfgstarteddate" />
					<span style="font-size: 11px; color: red" id="errorMfgstarteddateEdit"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="mfgfinisheddateEdit" class="col-sm-3 control-label"><spring:message code="editPiDialog.txtMFGFinishedDate"/></label>
				<div class="col-sm-9">
					<form:input type="text" class="form-control" id="mfgfinisheddateEdit" name=""
						 value="" path="mfgfinisheddate" />
					<span style="font-size: 11px; color: red" id="errorMfgfinisheddateEdit"></span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="piattachedFile" class="col-sm-3 control-label"><spring:message code="editPiDialog.filePiAttached"/></label>
				<div class="col-sm-9">
					<form:input type='file' class="form-control" path="piattached.file"
						name='piattached' id='piattached' /><br/>
						<a href="#" id="linkpiattached" download></a>
			
					
				</div>
			</div>

			<div class="form-group">
				<label for=shipmentstatus class="col-sm-3 control-label"><spring:message code="editPiDialog.ddlShipmentStatus"/><span style="color: red;">*</span></label>
				<div class="col-sm-9">
					<form:select id="shipmentstatus" name="shipmentstatus" path="shipmentstatus" />
					<span style="font-size: 11px; color: red" id="errorShipmentstatus"></span>

				</div>
			</div>
			
			<div class="form-group">
				<label for="status" class="col-sm-3 control-label"><spring:message code="editPiDialog.ddlStatus"/> </label>
				<div class="col-sm-9">
					<form:select id="status" name="status" path="status">
						<form:option value="Normal"><spring:message code="editPiDialog.ddlStatus.normal"/></form:option>
						<form:option value="Urgent"><spring:message code="editPiDialog.ddlStatus.pending"/></form:option>
						<form:option value="Pending"><spring:message code="editPiDialog.ddlStatus.urgent"/></form:option>
					</form:select>
					<span style="font-size: 11px; color: red" id="errorStatus"></span>
				</div>
			</div>
			
			
			 <div class="form-group">
				<label for="packingguidecode" class="col-sm-3 control-label"><spring:message code="editPiDialog.ddlPackingGuide"/></label>
				<div class="col-sm-9">
					<form:select id="packingguidecode" name="packingguidecode" path="packingguidecode" />
					<span style="font-size: 11px; color: red" id="errorPackingguidecode"></span>

				</div>
			</div>
			 
			<div class="form-group">
				<label for="remark" class="col-sm-3 control-label"><spring:message code="editPiDialog.txareaRemak"/></label>
				<div class="col-sm-9">
					<form:textarea id="remark" name="remark" path="remark" rows="5"
						cols="5" />
				</div>
			</div>
			
			

		</div>
		<div style="clear: both;"></div>
		<br>
		<br>
		
		<div class="row-fluid">
	<!-- block -->
	
		<input type="button" class="form-control btn btn-info" id="btnAssignFabricForPi" style="margin-bottom:20px" 
		 name="btnAssignFabricForPi" value="<spring:message code="lbl.assignfabric.btnAssignFabric" />">
	
	
	<!-- /block -->
</div>
		
		<!-- Test -->
		<button id="btnShowAssignExAccDialog" class="btn btn-info"
			><spring:message code="lbl.assignExtAccForPi.btnShowAssignExAccDialog"/></button>
		<br>
		<br>
				<div id="AssignExternalAccessoryDetailTableCover">
					<table
						class="table table-striped table-bordered display responsive"
						id="listAssignExternalAccessoryDetail">
						<thead>
							<tr>
								<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row1"/></th>
								<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row2"/></th>
								<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row3"/></th>
								<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row4"/></th>
								<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row5"/></th>
								<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row6"/></th>
								<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row7"/></th>
								<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row8"/></th>
								<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row9"/></th>
								<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row10"/></th>
								<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row11"/></th>
								<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row12"/></th>
								<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row13"/></th>
								<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row14"/></th>
								<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row15"/></th>
								<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row16"/></th>
								<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row17"/></th>
								<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row18"/></th>
								<th></th>
							</tr>
						</thead>
					</table>
				</div>
			
		<!-- Test -->
		 <jsp:include page="assigninternalaccessories.jsp" />
		<div style="clear: both;"></div>
		
		<div class="form-group" style="margin-bottom:20px" >
				<div class="col-sm-9">
					<input type="button" id="btnLogofChange" name="btnLogofChange" class="btn btn-info"
						value="Log Of Change">
				</div>
			</div>
			
			<div class="col-sm-offset-2 col-sm-10" align="right">
			<input type="submit" value="Save" class="btn btn-primary btn-sm"
				id="btnAdd">
       		<button type="button" class="btn btn-warning" id="btnCancelEditPIDialog">Cancel</button>
     		</div>

	</form:form>
</div>
<!-- End edit dialog -->

<!-- This is dialog confirm delete -->
<div id="deletePiDialog" style="display: none;">
	<span id="messageContent"></span>
</div>
<!-- dialog show when edit status success-->
<div id="messageDialog" style="display: none;">
	<span id="messageContent"></span>
</div>

<!--  This is dialog log of change -->
<div id="dialogLogOfChange" style="display: none;">
<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">Log Of Change</div>
		</div>
		<div class="block-content collapse in">
			<div class="span12">
					<button class="btn btn-primary" name="btnAddLogOfChange" id="btnAddLogOfChange">Create
						New Log Of Change</button>
					<table cellpadding="0" cellspacing="0" border="0"
						class="table table-striped table-bordered display responsive"
						id="listLogOfChange">
						<thead>
							<tr>
								<th>Date</th>
								<th>Subject</th>
								<th>To</th>
							</tr>
						</thead>
	
					</table>
				
			</div>
		</div>
	</div>
</div>

<div id="sendmailDialog" style="display: none;"
	name="sendmailDialog">
	<form method="post" action="sendmailForLogOfChange" enctype="multipart/form-data">
		<table style="border: none;">
		 
			<tr>
				<td></td>
				<td><input id="txtLotNumberSendMail" name="txtLotNumberSendMail" value="" hidden="true"/></td>
			</tr>
		 
			<tr>
				<td>Email To:</td>
				<td><input type="text" name="mailTo" placeholder="To" /></td>
			</tr>
			<tr>
				<td>CC:</td>
				<td><input type="text" name="CC" placeholder="CC" /></td>
			</tr>
			<tr>
				<td>Subject:</td>
				<td><input type="text" name="subject" placeholder="subject" /></td>
			</tr>
			<tr>
				<td>Message:</td>
				<td><textarea cols="50" rows="10" name="message"
						placeholder="message"></textarea></td>
			</tr>
			<tr>
				<td>Attach file:</td>
				<td><input type="file" name="attachFile1" id="attachFile1"/>
				<input type="file" name="attachFile2" id="attachFile2"  />
				<input type="file" name="attachFile3" id="attachFile3"  />
				<input type="file" name="attachFile4" id="attachFile4"  />
				<input type="file" name="attachFile5" id="attachFile5"  />
				<input type="file" name="attachFile6" id="attachFile6"  />
				<input type="file" name="attachFile7" id="attachFile7"  />
				<input type="file" name="attachFile8" id="attachFile8"  />
				<input type="file" name="attachFile9" id="attachFile9"  />
				<input type="file" name="attachFile10" id="attachFile10" /></td>
				
			</tr>
			<tr>
				<td><input type="submit" class="btn btn-info" value="Send" /></td>
				<td><input type="button" class="btn btn-info" value="Cancel" id="btnCancelSendMail" /></td>
			</tr>
		</table>
	</form>
	</div>

<div id="assignExternalAccessoryForPiDialog" style="display: none;"
	name="assignExternalAccessoryForPiDialog">
	<div class="inputarea">
		<div class="left" style="width: 50%; float: left">
			<div class="form-group">
				<label for="txtLOTNo" class="col-sm-3 control-label"><spring:message code="lbl.assignExtAccForPi.assignExternalAccessoryForPiDialog.txtLOTNo"/></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtLOTNo"
						value="Lot1" name="txtLOTNo" required>
					<p style="font-size: 11px; color: red" id="errorTxtLOTNo"></p>
				</div>
			</div>
			<!-- 
			<!-- 
			<div class="form-group">
				<label for="txtShortName" class="col-sm-3 control-label">Garment Style
				</label>
				<div class="col-sm-9">
					<select id="txtStatus">
						<option value="Active">Active</option>
						<option value="In-Active">In-Active</option>
					</select>
					<p style="font-size: 11px; color: red" id="errorTxtShortName"></p>
				</div>
			</div> -->
		</div>
		<div class="right" style="width: 50%; float: right">
			<!-- 
			<div class="form-group">
				<label for="txtTel" class="col-sm-3 control-label">Search</label>
				<div class="col-sm-9">
					<select id="txtStatus">
						<option value="Active">Active</option>
						<option value="In-Active">In-Active</option>
					</select>
					<p style="font-size: 11px; color: red" id="errorTxtTel"></p>
				</div>
			</div>

			<div class="form-group">
				<label for="txtFax" class="col-sm-3 control-label">Type</label>
				<div class="col-sm-9">
					<select id="txtStatus">
						<option value="Active">Active</option>
						<option value="In-Active">In-Active</option>
					</select>
					<p style="font-size: 11px; color: red" id="errorTxtFax"></p>
				</div>
			</div> -->
			 
		</div>
		<div style="clear: both;"></div>
	</div>
	<div id="AssignExternalAccessoryTableCover">
		<table class="table table-striped table-bordered display responsive"
					id="listAssignExternalAccessory">
					<thead>
						<tr>
							<th><spring:message code="lbl.assignExtAccForPi.assignExternalAccessoryForPiDialog.listAssignExternalAccessory.row1"/></th>
							<th><spring:message code="lbl.assignExtAccForPi.assignExternalAccessoryForPiDialog.listAssignExternalAccessory.row2"/></th>
							<th><spring:message code="lbl.assignExtAccForPi.assignExternalAccessoryForPiDialog.listAssignExternalAccessory.row3"/></th>
							<th><spring:message code="lbl.assignExtAccForPi.assignExternalAccessoryForPiDialog.listAssignExternalAccessory.row4"/></th>
							<th><spring:message code="lbl.assignExtAccForPi.assignExternalAccessoryForPiDialog.listAssignExternalAccessory.row5"/></th>
							<th></th>
							<input type="hidden" id="txt"/>
							<!-- <th></th> -->
						</tr>
					</thead>
				</table>
	</div>
</div>

<!-- This is dialog to edit wasted percentage -->
<div id="editWastedPercentageDialog" style="display: none;"
	name="editWastedPercentageDialog">
	<div class="form-group">
		LOT No: <span id="lblLotNo"></span>
	</div>

	<div class="form-group">
		Factory: <span id="lblFactory"></span> <input type="text"
			class="form-control" id="txtFactory" name="txtFactory"
			style="display: none;">
	</div>

	<div class="form-group">
		Wasted Percentage:
		<div class="col-sm-9">
			<input type="number" class="form-control" id="txtWastedPercentage"
				placeholder='Wasted Percentage' value="" name="txtWastedPercentage"
				step="any" min="0">
		</div>
	</div>

	<div id="ExternalAccessoryDetailByLotNoAndAccCodeTableCover">
		<table class="table table-striped table-bordered display responsive"
			id="listExternalAccessoryDetailByLotNoAndAccCode">
			<thead>
			</thead>
		</table>
	</div>
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
	<!-- FPI -->
	<div id="viewFPIdialog"  style="display: none;"
		name="viewFPIdialog">

		<div style="width: 70%; float: left">
			<div class="form-group" style ="clear:both">
				<label for="noneorderaccessories" class="col-sm-6 control-label"><spring:message
						code="lbl.fpi.dialogfpi.lblnoneorderaccessories" /></label>
				<div class="col-sm-6" style ="clear:both">
					<input type="checkbox" class="form-control"
						id="noneorderaccessories" name="noneorderaccessories"
						value="false" />
				</div>
				
			</div>

			<div class="form-group" style ="clear:both">
				<label for="txtCustomerName" class="col-sm-3 control-label"><spring:message
						code="lbl.fpi.dialogfpi.lblcustomername" /></label>
				<div class="col-sm-9" style ="clear:both">
					<input type="text" class="form-control" id="txtCustomerName"
						value="" name="txtCustomerName" disabled>
				</div>
			</div>
			<div class="form-group">
				<label for="txtConsigneeName" class="control-label col-sm-2"><spring:message
						code="lbl.fpi.dialogfpi.lblconsigneename" /></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="txtConsigneeName"
						value="" name="txtConsigneeName" disabled>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="txtLotNumber"><spring:message
						code="lbl.fpi.dialogfpi.lbllotnumber" /></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="txtLotNumber" value=""
						name="txtLotNumber" disabled>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="txtCountry"><spring:message
						code="lbl.fpi.dialogfpi.lblcountry" /></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="txtCountry" value=""
						name="txtCountry" disabled>
				</div>
			</div>
			
			

			<div class="form-group">
				<label class="control-label col-sm-2" for="txtDestination"><spring:message
						code="lbl.fpi.dialogfpi.lbldestination" /></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="txtDestination"
						value="" name="txtDestination" disabled>
				</div>
			</div>
			</div>
			<br/>

		<div class="right" style="width: 30%; float: right">

			<div class="form-group">
				<label class="control-label col-sm-2" for="txtBrandName"><spring:message
						code="lbl.fpi.dialogfpi.lblbrandname" /></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="txtBrandName" value=""
						name="txtBrandName" readonly="true">
				</div>
			</div>
			<div class="form-group">
				<label for="txtPIReceivedDate" class="control-label col-sm-2"><spring:message
						code="lbl.fpi.dialogfpi.lblpireceiveddate" /></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="txtPIReceivedDate"
						value="" name="txtPIReceivedDate" disabled>
				</div>
			</div>
			<div class="form-group">
				<label for="txtPIEstShipDate" class="control-label col-sm-2"><spring:message
						code="lbl.fpi.dialogfpi.lblpiestshipdate" /></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="txtPIEstShipDate"
						value="" name="txtPIEstShipDate" disabled>
				</div>
			</div>

			<div class="form-group">
				<label for="txtMFGStartedDate" class="control-label col-sm-2"><spring:message
						code="lbl.fpi.dialogfpi.lblmfgstarteddate" /></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="txtMFGStartedDate"
						value="" name="txtMFGStartedDate" disabled>
				</div>
			</div>

			<div class="form-group">
				<label for="txtMFGFinishedDate" class="control-label col-sm-2"><spring:message
						code="lbl.fpi.dialogfpi.lblmfgfinisheddate" /></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="txtMFGFinishedDate"
						value="" name="txtMFGFinishedDate" disabled>
				</div>
			</div>


			<div class="form-group">
				<label for="piattachedFile" class="col-sm-3 control-label"><spring:message
						code="lbl.fpi.dialogfpi.lblpiattached" /></label>
				<div class="col-sm-9">
					<input type='file' class="form-control" name='piattached'
						id='piattached' /><br />
						<a href="#" id="linkpiattached" download></a>


				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="txtFactory"><spring:message
						code="lbl.fpi.dialogfpi.lblfactoryname" /></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="txtFactory" value=""
						name="txtFactory" disabled>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="txtStatus"><spring:message
						code="lbl.fpi.dialogfpi.lblstatus" /></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="txtStatus" value=""
						name="txtStatus" disabled>
				</div>
			</div>

			<div class="form-group">
				<label for="remark" class="col-sm-3 control-label"><spring:message
						code="lbl.fpi.dialogfpi.lblremark" /></label>
				<div class="col-sm-9">
					<textarea id="remark" name="remark" rows="5" cols="5" disabled></textarea>
				</div>
			</div>


			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10" align="right">
					<button type="submit" class="btn btn-primary" id="btnSubmit">
						Save
					</button>
					<button type="button" class="btn btn-warning" id="btnCancel">
						Cancel
					</button>
				</div>
			</div>

		</div>
		<div style="clear: both;"></div>

		<div class="form-group" style="margin-bottom: 20px">
			<div class="col-sm-9">
				<button id="btnLogofChange" class="btn btn-info">
					Log Of Change
				</button>
			</div>
		</div>
		
		<div id="fabricAssignedDetailTableCoverToPiInFpi">
					<table
						class="table table-striped table-bordered display responsive"
						id="listFabricAssignedDetailToPiInFpi">
						<thead>
							<tr>
								<th>Fabric Indent</th>
								<th>Color</th>
								<th>Garment Style</th>
								<th>Assign Quantity</th>
							</tr>
						</thead>
					</table>
				</div>
		
		<div class="form-group">
				<label for="importFPI" class="col-sm-3 control-label">Import FPI
					</label>
				<div class="col-sm-9">
					<input  type='file' class="form-control" 
						name='importFPI' id='importFPI' />
				</div>
			</div>
		
		<select id="fpiVersion" name="fpiVersion">
		</select>
		
		<!-- bảng FPI detail -->
		<div id="FpiGridDetailTableCover">
			<table class="table table-striped table-bordered display responsive"
				id="listFpiGridDetail">
				<thead>
					<tr>
						<th rowspan="2">Total Pcs</th>
						<th rowspan="2">Color</th>
						<th colspan="3">Garment Style</th>
						<th rowspan="2">Size</th>
						<th rowspan="2">FA</th>
						<th rowspan="2">FPI</th>
					</tr>
					<tr>
						<th>Name</th>
						<th>Image</th>
						<th>Type</th>
					</tr>
				</thead>
			</table>
		</div>
		<!-- end bảng FPI detail -->
		
		<!-- bảng assign external ver fpi -->
		<div id="AssignExternalAccessoryDetailTableCoverFpiVer">
			<table
				class="table table-striped table-bordered display responsive"
				id="listAssignExternalAccessoryDetailFpiVer">
				<thead>
					<tr>
						<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row1"/></th>
						<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row2"/></th>
						<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row3"/></th>
						<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row4"/></th>
						<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row5"/></th>
						<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row6"/></th>
						<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row7"/></th>
						<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row8"/></th>
						<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row9"/></th>
						<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row10"/></th>
						<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row11"/></th>
						<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row12"/></th>
						<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row13"/></th>
						<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row14"/></th>
						<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row15"/></th>
						<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row16"/></th>
						<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row17"/></th>
						<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row18"/></th>
						<th></th>
					</tr>
				</thead>
			</table>
		</div>
		<!-- end bảng assign external ver fpi -->
		
	</div>

	
	<!-- FPI -->
	
	
	<!-- RFPI -->
	
	<div id="viewRFPIdialog" style="display: none;" class="" name="viewRFPIdialog">
	<form:form method="POST" modelAttribute="piModel" action="pi/edit"
		enctype="multipart/form-data" class="" id="editPiForm" >
		<div style="width: 70%; float: left">
			<div class="form-group">
				<label for="noneorderaccessories" class="col-sm-6 control-label"><spring:message
						code="lbl.rfpi.viewRFPIdialog.noneorderaccessories" /> </label>
				<div class="col-sm-6">
					<form:checkbox class="form-control" id="noneorderaccessories" path="noneorderaccessories"
						name="noneorderaccessories"/>
				</div>
			</div>
			
			<div class="form-group">
				<label for="customer1" class="col-sm-3 control-label"><spring:message
						code="lbl.rfpi.viewRFPIdialog.customername" /><span style="color: red;">*</span></label>
				<div class="col-sm-9">
					<form:select id="customer1" name="customer1" path="customer1" >
						
					</form:select>
					<span style="font-size: 11px; color: red" id="errorCustomer1"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="consignee" class="col-sm-3 control-label"><spring:message
						code="lbl.rfpi.viewRFPIdialog.consigneename" /></label>
				<div class="col-sm-9">
					<form:select id="consignee" name="consignee" path="consignee" />
					<span style="font-size: 11px; color: red" id="errorConsignee"></span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="txtLotNumber" class="col-sm-3 control-label"><spring:message
						code="lbl.rfpi.viewRFPIdialog.lotnumber" /><span style="color: red;">*</span>
				</label>
				<div class="col-sm-9">
					<form:input readonly="true" type="text" class="form-control" id="lotnumber"
						path="lotnumber"  value=""
						name="lotnumber" />
				</div>
			</div>
			
			<div class="form-group">
				<label for="country" class="col-sm-3 control-label"><spring:message
						code="lbl.rfpi.viewRFPIdialog.country" />
				<span style="color: red;">*</span></label>
				<div class="col-sm-9">
					<select id="country" name="country">
					</select>
					<span style="font-size: 11px; color: red" id="errorCountry"></span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="destinationcode" class="col-sm-3 control-label"><spring:message
						code="lbl.rfpi.viewRFPIdialog.destination" />
				<span style="color: red;">*</span></label>
				<div class="col-sm-9">
					<form:select id="destinationcode" name="destinationcode"
						path="destinationcode" />
					<span style="font-size: 11px; color: red" id="errorDestinationcode"></span>
				</div>
			</div>
						 	 			 	
	
		</div>

		<div class="right" style="width: 30%; float: right">
			
			<div class="form-group">
				<label for="brandcode" class="col-sm-3 control-label"><spring:message
						code="lbl.rfpi.viewRFPIdialog.brand" /> <span style="color: red;">*</span></label>
				<div class="col-sm-9">
					<form:select id="brandcode" name="brandcode" path="brandcode" >
					</form:select>
					<span style="font-size: 11px; color: red" id="errorBrandcode"></span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="pireceiveddate" class="col-sm-3 control-label"><spring:message
						code="lbl.rfpi.viewRFPIdialog.pireceiveddate" /></label>
				<div class="col-sm-9">
					<form:input type="text" class="form-control" id="pireceiveddate2"
						value=""
						name="pireceiveddate2" path="pireceiveddate" />
					<span style="font-size: 11px; color: red" id="errorPireceiveddate"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="piestshipdate" class="col-sm-3 control-label"><spring:message
						code="lbl.rfpi.viewRFPIdialog.piestshipdate" /></label>
				<div class="col-sm-9">
					<form:input type="text" class="form-control" id="piestshipdate2"
						value="" name="piestshipdate2"
						path="piestshipdate" />
					<span style="font-size: 11px; color: red" id="errorPiestshipdate"></span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="mfgstarteddate" class="col-sm-3 control-label"><spring:message
						code="lbl.rfpi.viewRFPIdialog.mfgstarteddate" /></label>
				<div class="col-sm-9">
					<form:input type="text" class="form-control" id="mfgstarteddate2" name="mfgstarteddate2"
						value="" path="mfgstarteddate" />
					<span style="font-size: 11px; color: red" id="errorMfgstarteddate"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="mfgfinisheddate" class="col-sm-3 control-label"><spring:message
						code="lbl.rfpi.viewRFPIdialog.mfgfinisheddate1" /></label>
				<div class="col-sm-9">
					<form:input type="text" class="form-control" id="mfgfinisheddate2" name=""
						 value="" path="mfgfinisheddate" />
					<span style="font-size: 11px; color: red" id="errorMfgfinisheddate"></span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="piattachedFile" class="col-sm-3 control-label"><spring:message
						code="lbl.rfpi.viewRFPIdialog.piattachedfile" /></label>
				<div class="col-sm-9">
					<form:input type='file' class="form-control" path="piattached.file"
						name='piattached' id='piattached' /><br/>
						<a href="#" id="linkpiattached" download></a>
			
					
				</div>
			</div>

			<div class="form-group">
				<label for="factorycode" class="col-sm-3 control-label"><spring:message
						code="lbl.rfpi.viewRFPIdialog.factory" /></label>
				<div class="col-sm-9">
					<form:select id="factorycode" name="factorycode" path="factorycode" />
					<span style="font-size: 11px; color: red" id="errorFactorycode"></span>

				</div>
			</div>
			
			<div class="form-group">
				<label for="status" class="col-sm-3 control-label"><spring:message
						code="lbl.rfpi.viewRFPIdialog.status" /></label>
				<div class="col-sm-9">
					<form:select id="status" name="status" path="status">
						<form:option value="Normal">Normal</form:option>
						<form:option value="Urgent">Urgent</form:option>
						<form:option value="Pending">Pending</form:option>
					</form:select>
					<span style="font-size: 11px; color: red" id="errorStatus"></span>
				</div>
			</div>
			
			
			 
			<div class="form-group">
				<label for="remark" class="col-sm-3 control-label"><spring:message
						code="lbl.rfpi.viewRFPIdialog.remark" /></label>
				<div class="col-sm-9">
					<form:textarea id="remark" name="remark" path="remark" rows="5"
						cols="5" />
				</div>
			</div>
			
			

		</div>
		<div style="clear: both;"></div>
		<br>
		<br>		
				
		<div style="clear: both;"></div>
		
		<div class="form-group" style="margin-bottom:20px" >
				<div class="col-sm-9">
					<input type="button" id="btnLogofChange" name="btnLogofChange" class="btn btn-info"
						value="Log Of Change">
				</div>
			</div>
			
			<div id="fabricAssignedDetailTableCover">
					<table
						class="table table-striped table-bordered display responsive"
						id="listFabricAssignedDetail">
						<thead>
							<tr>
								<th><spring:message code="lbl.rfpi.viewRFPIdialog.fabricAssignedDetailTableCover.row1" /></th>
								<th><spring:message code="lbl.rfpi.viewRFPIdialog.fabricAssignedDetailTableCover.row2" /></th>
								<th><spring:message code="lbl.rfpi.viewRFPIdialog.fabricAssignedDetailTableCover.row3" /></th>
								<th><spring:message code="lbl.rfpi.viewRFPIdialog.fabricAssignedDetailTableCover.row4" /></th>
							</tr>
						</thead>
					</table>
				</div>


			<div class="form-group">
				<label for="importRFPI" class="col-sm-3 control-label">Import RFPI</label>
				<div class="col-sm-9">
					<input  type='file' class="form-control" 
						name='importRFPI' id='importRFPI' />
				</div>
			</div>
		
		<select id="rfpiVersion" name="rfpiVersion">
		</select>
		
				<div id="RfpiGridDetailTableCover">
					<table
						class="table table-striped table-bordered display responsive"
						id="listRfpiGridDetail">
						<thead>
							<tr>
								<th rowspan="2"><spring:message code="lbl.rfpi.viewRFPIdialog.RfpiGridDetailTableCover.row1" /></th>
								<th rowspan="2"><spring:message code="lbl.rfpi.viewRFPIdialog.RfpiGridDetailTableCover.row2" /></th>
								<th colspan="3"><spring:message code="lbl.rfpi.viewRFPIdialog.RfpiGridDetailTableCover.row3" /></th>
								<th rowspan="2"><spring:message code="lbl.rfpi.viewRFPIdialog.RfpiGridDetailTableCover.row4" /></th>
								<th rowspan="2"><spring:message code="lbl.rfpi.viewRFPIdialog.RfpiGridDetailTableCover.row5" /></th>
								<th rowspan="2"><spring:message code="lbl.rfpi.viewRFPIdialog.RfpiGridDetailTableCover.row6" /></th>
							</tr>
							<tr>
								<th><spring:message code="lbl.rfpi.viewRFPIdialog.RfpiGridDetailTableCover.row3.row1" /></th>
								<th><spring:message code="lbl.rfpi.viewRFPIdialog.RfpiGridDetailTableCover.row3.row2" /></th>
								<th><spring:message code="lbl.rfpi.viewRFPIdialog.RfpiGridDetailTableCover.row3.row3" /></th>
							</tr>
						</thead>
					</table>
				</div>
				
				<br><br>								


			
			
			<div class="col-sm-offset-2 col-sm-10" align="right">
			<input type="submit" value="Save" class="btn btn-primary btn-sm"
				id="btnAdd">
       		<button type="button" class="btn btn-warning" id="btnCancel"><spring:message code="lbl.rfpi.viewRFPIdialog.btnCancel" /></button>
     		</div>

	</form:form>
	
	<!-- bảng assign external ver rfpi -->
	<div id="AssignExternalAccessoryDetailTableCoverRfpiVer">
		<table
			class="table table-striped table-bordered display responsive"
			id="listAssignExternalAccessoryDetailRfpiVer">
			<thead>
				<tr>
					<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row1"/></th>
					<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row2"/></th>
					<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row3"/></th>
					<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row4"/></th>
					<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row5"/></th>
					<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row6"/></th>
					<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row7"/></th>
					<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row8"/></th>
					<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row9"/></th>
					<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row10"/></th>
					<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row11"/></th>
					<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row12"/></th>
					<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row13"/></th>
					<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row14"/></th>
					<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row15"/></th>
					<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row16"/></th>
					<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row17"/></th>
					<th><spring:message code="lbl.assignExtAccForPi.listAssignExternalAccessoryDetail.row18"/></th>
					<th></th>
				</tr>
			</thead>
		</table>
	</div>
	<!-- end bảng assign external ver rfpi -->
	
</div>



<!--  order accessories -->
	<div id="dialogShowStock2" class="form-horizontal"
		style="display: none;" name="dialogShowStock2">
		<div class="form-group" style="text-align: center;margin:20px auto 20px auto;">
			<div class="col-sm-9">
				<button  class="form-control btn btn-info" id="btnAssign"
					 name="btnAssign"><spring:message code="lbl.dialogShowStock2.row1" /></button>

			</div>
		</div>
		<input type="hidden" id="txtLotNumber" name="txtLotNumber" /> <input
			type="hidden" id="txtAccessoryCode" name="txtAccessoryCode" />
		<div class="form-group" style="text-align: center;margin:20px auto 20px auto;">
			<div class="col-sm-9">
				<button class="form-control btn btn-info" id="btnNew"
				 name="btnNew"><spring:message code="lbl.dialogShowStock2.row2" /></button>
			</div>
		</div>
		<!-- <div class="form-group" style="margin-bottom:20px">
			<div class="col-sm-9">
				<input type="button" class="form-control btn btn-info" id="btnCancel"
					value="CANCEL" name="btnCancel">
			</div>
			 -->
	</div>

	<div id="dialogShowStock1" class="form-horizontal"
		style="display: none;" name="dialogShowStock1">

		<div class="form-group" style="text-align: center;margin:20px auto 20px auto;">
			<button class="form-control btn btn-info" id="btnNew"
				 name="btnNew"><spring:message code="lbl.dialogShowStock1.row1" /></button>
		</div>
		<input type="hidden" id="txtLotNumber" name="txtLotNumber" /> <input
			type="hidden" id="txtAccessoryCode" name="txtAccessoryCode" />
		<!--<div class="form-group" style="text-align: center;margin:20px auto 20px auto;">
			<input type="button" class="form-control btn btn-info" id="btnCancel"
				value="CANCEL" name="btnCancel">
		</div>-->
	</div>

	<div id="dialogOrderExtAccc" class="form-horizontal" style="display: none;" >
				<div class="left" style="width: 60%; float: left">
					<div class="form-group form-inline"
						style="margin: 10px 0px 0px 20px" align="left">
						<label class="col-sm-2 control-label "><spring:message code="lbl.dialogOrderExtAccc.suplier" />
						</label> <select id="txtAccessorysuplier">
						</select>
					</div>
					<br />
					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px" align="left">
						<label class="col-sm-2 control-label "><spring:message code="lbl.dialogOrderExtAccc.ordersheetno" /></label> <input
							type="text" id="txtOrdersheetno" disabled="disabled" border="0px" />

					</div>
					<br />
					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px" align="left">

						<label class="col-sm-2 control-label "><spring:message code="lbl.dialogOrderExtAccc.factory" /></label> <select
							id="txtFactory"></select>

					</div>

					<input type="hidden" id="txtSheetNo" /> <br />
					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px" align="left">
						<label class="col-sm-2 control-label "><spring:message code="lbl.dialogOrderExtAccc.accessory" /></label> <input
							type="text" class="form-control" id="txtAccessoryName"> <span
							style="font-size: 11px; color: red" id="errorTxtAccessoryName"
							class="help-block"></span>
					</div>
					<br />
					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px" align="left">
						
							<table
								class="table table-striped table-bordered display responsive"
								id="listAccessory">
								<thead>
									<tr>
										<th><spring:message code="lbl.listAccessory.row1" /></th>
										<th><spring:message code="lbl.listAccessory.row2" /></th>
										<th><spring:message code="lbl.listAccessory.row3" /></th>
										<th><spring:message code="lbl.listAccessory.row4" /></th>
										<th><spring:message code="lbl.listAccessory.row5" /></th>
										<th><spring:message code="lbl.listAccessory.row6" /></th>
										<th><spring:message code="lbl.listAccessory.row7" /></th>
										<th><spring:message code="lbl.listAccessory.row8" /></th>
										<th><spring:message code="lbl.listAccessory.row9" /></th>
										
										<!-- <th></th> -->
									</tr>
								</thead>
							</table>
					
					</div>
					<br />
					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px" align="left">

						<label class="col-sm-2 control-label "><spring:message code="lbl.dialogOrderExtAccc.estquantity" />
						</label> <input type="text" class="form-control" id="txtEstimateQuantity" name="txtEstimateQuantity"   maxlength="11" >
						<span style="font-size: 11px; color: red"
							id="errorTxtEstimateQuantity" class="help-block"></span>
					</div>
					<br />
					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px" align="left">

						<label class="col-sm-2 control-label "><spring:message code="lbl.dialogOrderExtAccc.actassignquantity" />
						</label> <input type="text" class="form-control" id="txtActualAssignQuantity" name="txtActualAssignQuantity"   maxlength="11" >
						<span style="font-size: 11px; color: red"
							id="errorTxtActualAssignQuantity" class="help-block"></span>
					</div>
					<br />
					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px" align="left">

						<label class="col-sm-2 control-label "><spring:message code="lbl.dialogOrderExtAccc.orderquantity" />
						</label> <input type="text" class="form-control" id="txtOrderQuantity" name="txtOrderQuantity"   maxlength="11" >
						<span style="font-size: 11px; color: red"
							id="errorTxtOrderQuantity" class="help-block"></span>
					</div>
					<br />

				</div>

				<div class="right" style="width: 40%; float: right;margin-bottom: 50px;" >
					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px" align="left">
						<label class="col-sm-2 control-label "><spring:message code="lbl.dialogOrderExtAccc.estgrossquantity" /> </label> <input type="text" class="form-control"
							id="txtEstimateGrossQuantity" name="txtEstimateGrossQuantity"> <span
							style="font-size: 11px; color: red"
							id="errorTxtEstimateGrossQuantity" class="help-block"></span>

					</div>
					<br/>
					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px" align="left">
						<div>
						<label class="col-sm-2 control-label "><spring:message code="lbl.dialogOrderExtAccc.price" /></label>
						 <input	type="text" id="txtPrice" name="txtPrice" /> </div>
						 <label	class="col-sm-2 control-label "><spring:message code="lbl.dialogOrderExtAccc.currency" /></label> 
							<label class="col-sm-2 control-label " id="lblCurrency"> </label>


					</div>
					<br/>
					<div class="form-group form-inline"
						style="margin: 10px 0px 0px 20px" align="left">
						<label class="col-sm-2 control-label "><spring:message code="lbl.dialogOrderExtAccc.actdelivergrossquantity" /> </label> <input type="text" class="form-control"
							id="txtActualDeliveriedGrossQuantity" name="txtActualDeliveriedGrossQuantity"> <span
							style="font-size: 11px; color: red"
							id="errorTxtActualDeliveriedGrossQuantity" class="help-block"></span>

					</div>
					<br />
					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px" align="left">
						<label class="col-sm-2 control-label "><spring:message code="lbl.dialogOrderExtAccc.orderdate" /></label> <input
							type="text" class="form-control" id="txtOrderDate"
							name="txtOrderDate"> <span
							style="font-size: 11px; color: red" id="errorTxtOrderDate"
							class="help-block"></span>

					</div>
					<br />
					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px" align="left">
						<label class="col-sm-2 control-label "><spring:message code="lbl.dialogOrderExtAccc.status" /></label> <select
							id="txtStatus">
							<option selected="selected"><spring:message code="lbl.dialogOrderExtAccc.status.row1" /></option>
							<option><spring:message code="lbl.dialogOrderExtAccc.status.row2" /></option>
							<option><spring:message code="lbl.dialogOrderExtAccc.status.row3" /></option>

						</select>

					</div>
					<br />
					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px" align="left">

						<label class="col-sm-2 control-label "><spring:message code="lbl.dialogOrderExtAccc.estdeliverdate" />
						</label> <input type="text" class="form-control" id="txtEstDeliveryDate">
						<span style="font-size: 11px; color: red"
							id="errorTxtEstDeliveryDate" class="help-block"></span>
					</div>
					<br />
					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px" align="left">
						<label class="col-sm-2 control-label "><spring:message code="lbl.dialogOrderExtAccc.actdelverdate" /></label> <input type="text" class="form-control"
							id="txtActualDeliveryDate"> <span
							style="font-size: 11px; color: red"
							id="errorTxtActualDeliveryDate" class="help-block"></span>
					</div>
					<br />
					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px" align="left">

						<label class="col-sm-2 control-label "><spring:message code="lbl.dialogOrderExtAccc.remark" /> </label>

						<textarea rows="4" cols="5" class="form-control" id="txtRemark"></textarea>
						<span style="font-size: 11px; color: red" id="errorTxtRemark"
							class="help-block"></span>

					</div>
				

					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px">
						<label class="col-sm-2 control-label "><spring:message code="lbl.dialogOrderExtAccc.paymentstatus" /></label>
						 <select id="txtPaymentStatus">
							<option selected="selected"><spring:message code="lbl.dialogOrderExtAccc.paymentstatus.row1" /></option>
							<option><spring:message code="lbl.dialogOrderExtAccc.paymentstatus.row2" /></option>
						</select>

					</div>
   	</div>
   	<br/>
   	
						<div class="col-sm-offset-2 col-sm-10" align="right">

						<button  class="btn btn-info glyphicon glyphicon-ok"
							id="btnSendMail"><spring:message code="lbl.dialogOrderExtAccc.sendmail" /></button>
						<button  class="btn btn-info glyphicon glyphicon-ok"
							id="btnSave"><spring:message code="lbl.dialogOrderExtAccc.save" /></button>
						<!-- Standard button -->
						<button  class="btn btn-info  glyphicon glyphicon-remove"
							id="btnCancel"><spring:message code="lbl.dialogOrderExtAccc.cancel" /></button>
					</div>
				</div>


	<div id="assignForm" name="assignForm" style="display: none;">
		<div class="form-group form-inline" style="margin: 10px 0px 0px 20px" align="left">
						<label class="col-sm-2 control-label "><spring:message code="lbl.assignForm.name" /></label> 
						<label  id="lblAccessoryName"></label>
		</div>
		<br/>
		<div class="form-group form-inline" style="margin: 0px 0px 0px 20px" align="left">
						<label class="col-sm-2 control-label "><spring:message code="lbl.assignForm.unit" /></label> 
						<label  id="lblUnit"></label>
		</div>
		<br/>
		<div class="form-group form-inline" style="margin: 0px 0px 0px 20px" align="left">
						<label class="col-sm-2 control-label "><spring:message code="lbl.assignForm.inventoryquantity" /></label> 
						<label  id="lblInventoryquantity"></label>
		</div>
		<br/>
		<div class="form-group form-inline" style="margin: 0px 0px 0px 20px" align="left">
						<label class="col-sm-2 control-label "><spring:message code="lbl.assignForm.availablequantity" /></label> 
						<label  id="lblAvailablequantity"></label>
		</div>
		<br/>
		<div class="form-group form-inline" style="margin: 0px 0px 0px 20px" align="left">
						<label class="col-sm-2 control-label "><spring:message code="lbl.assignForm.assignquantity" /></label> 
						<input type="text" id="txtAssignQuantity" name="txtAssignQuantity"/>
						<button id="btnDetail" ><spring:message code="lbl.assignForm.detail" /></button>
		</div>
		<br/>
		<div class="form-group form-inline" style="margin: 0px 0px 0px 20px" align="left">
			<button id="btnSave" name="btnSave"><spring:message code="lbl.assignForm.save" /></button>
		</div>
		
		
	</div>


		
		<!-- dialog show when edit  success-->
		<div id="messageDialog" style="display: none;">
			<span id="messageContent"></span>
		</div>
		
		
		
	<div id="dialogAssignFromStock" style="display: none;" class="form-horizontal">
		<div class="form-group" style="clear:both">
			<label class="col-sm-1 control-label"><spring:message code="lbl.dialogAssignFromStock.accessoryname" /></label>				
			<label class="col-sm-1 control-label" id="lblAssignFromStockLotNumber"></label>			
		</div>
		<br/>
		<!--  <div class="form-group" style="clear:both">
			<label class="col-sm-1 control-label">Unit: &nbsp;</label>		
			<label class="col-sm-1 control-label" id="lblAssignFromStockUnit"></label>		
		</div>
		<br/>
		<div class="form-group" style="clear:both">
			<label class="col-sm-1 control-label">Inventory Quantity: &nbsp;</label>		
			<label class="col-sm-1 control-label" id="lblAssignFromStockInventoryQty"></label>		
		</div>
		-->
		<br/>
		<div class="form-group" style="clear:both">
			<label class="col-sm-1 control-label"><spring:message code="lbl.dialogAssignFromStock.actassignquantity" /></label>		
			<label class="col-sm-1 control-label" id="lblAssignFromStockActualAssignQty"></label>		
		</div>
		<br/>
		<div class="form-group" style="clear:both">
			<label class="col-sm-1 control-label"><spring:message code="lbl.dialogAssignFromStock.shortagequantity" /></label>		
			<label class="col-sm-1 control-label" id="lblAssignFromStockShortageQty"></label>		
		</div>
		<br/>
		<div class="form-group" style="clear:both">
			<label class="col-sm-1 control-label"><spring:message code="lbl.dialogAssignFromStock.availablestockquantity" /></label>		
			<label class="col-sm-1 control-label" id="lblAssignFromStockAvailableStockQty"></label>		
		</div>
		<br/>
		<div class="form-group" style="clear:both">
			<label class="col-sm-1 control-label"><spring:message code="lbl.dialogAssignFromStock.assignquantity" /></label>		
			<input type="number" step="any" class="form-control col-sm-1 " id="txtAssignFromStockAssignQty" value="" name="txtAssignFromStockAssignQty">		
		</div>
	</div>
	
	<div id="dialogMessageNotEnoughQtyFromStock" style="display: none;" class="form-horizontal">
		<div class="form-group" style="clear:both">
			<label class="col-sm-1 control-label"><spring:message code="lbl.dialogAssignFromStock.shortagequantityis" /></label>		
			<label class="col-sm-1 control-label" id="lblAssignFromStockShortageQty"></label>		
		</div>
		<br/>
		<div class="form-group" style="clear:both">
			<label class="control-label"><spring:message code="lbl.dialogAssignFromStock.message" /></label>		
		</div>
	</div>
	
	
	<div id="sendMailDialog" style="display: none;">
	<form method="post" action="sendmailForOrderExternalAccessoryInStock" enctype="multipart/form-data">
		<table style="border: none;">
			<tr>
				<td><spring:message code="lbl.sendMailDialog.emailto" /></td>
				<td><input type="text" name="mailTo" placeholder="To" /></td>
			</tr>
			<tr>
				<td><spring:message code="lbl.sendMailDialog.cc" /></td>
				<td><input type="text" name="CC" placeholder="CC" /></td>
			</tr>
			<tr>
				<td><spring:message code="lbl.sendMailDialog.subject" /></td>
				<td><input type="text" name="subject" placeholder="subject" /></td>
			</tr>
			<tr>
				<td><spring:message code="lbl.sendMailDialog.message" /></td>
				<td><textarea cols="50" rows="10" name="message"
						placeholder="message"></textarea></td>
			</tr>
			<tr>
				<td><spring:message code="lbl.sendMailDialog.attachfile" /></td>
				<td><input type="file" name="attachFile" /></td>
			</tr>
			<tr>
				<td><input type="submit" class="btn btn-info" value="Send" /></td>
				<td><input type="button" class="btn btn-info" value="Cancel" id="btnCancelSendMail" /></td>
			</tr>
		</table>
	</form>
</div>
	</div>
	

	
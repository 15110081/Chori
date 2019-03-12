<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  
<script src="resources/userJs/garmentstyle/dataTables.rowsGroup.js"></script>
<script src="resources/userJs/operation/assignExternalAccessoryForPi/assignExternalAccessoryForPi.js"></script>
    
<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left"><spring:message code="lbl.assignExtAccForPi.lblAssignExtAccForPiTitle"/></div>
		</div>

		<spring:message code="lbl.assignExtAccForPi.lblTestLotNo"/> <input type="text" class="form-control"
			id="txtLotNumber" value="Lot1" name="txtLotNumber" required>
			<br>

		<button id="btnShowAssignExAccDialog" class="btn btn-info"
			style="margin-left: 20px; margin-top: 10px"><spring:message code="lbl.assignExtAccForPi.btnShowAssignExAccDialog"/></button>

		<div class="block-content collapse in">
			<div class="span12">
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
			</div>
		</div>
	</div>
	<!-- /block -->
</div>

<!-- This div is used to show message to user -->
<div id="messageDialog" style="display: none;">
	<span id="messageContent"></span>
</div>

<!-- This is dialog to add Factory -->
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
			 -->
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
							<!-- <th></th> -->
						</tr>
					</thead>
				</table>
	</div>
</div>
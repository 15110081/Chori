<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="resources/userJs/agent/agent.js"></script>

<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">
				<spring:message code="lbl.agent.agentForm" />
			</div>
		</div>

		<button id="btnAddNewAgent" class="btn btn-info"
			style="margin-left: 20px; margin-top: 10px">
			<spring:message code="lbl.agent.btnAddNewAgent" />
		</button>
		<div class="form-group">
			<span style="margin-left: 20px; margin-top: 10px;"><spring:message
					code="lbl.agent.lblForDdlStatus" /></span><select id="ddlStatus"
				style="margin-left: 20px; margin-top: 10px">
				<option value="selectRequest" selected disabled><spring:message
						code="lbl.agent.ddlStatus.selectRequest" /></option>
				<option value="All"><spring:message
						code="lbl.agent.ddlStatus.All" /></option>
				<option value="Active"><spring:message
						code="lbl.agent.ddlStatus.Active" /></option>
				<option value="In-Active"><spring:message
						code="lbl.agent.ddlStatus.In-Active" /></option>
			</select>
		</div>
		<div class="block-content collapse in">
			<div class="span12">
				<table class="table table-striped table-bordered display responsive"
					id="listAgent">
					<thead>
						<tr>
							<th><spring:message code="lbl.agent.listAgent.row1" /></th>
							<th><spring:message code="lbl.agent.listAgent.row2" /></th>
							<th><spring:message code="lbl.agent.listAgent.row3" /></th>
							<th><spring:message code="lbl.agent.listAgent.row4" /></th>
							<th><spring:message code="lbl.agent.listAgent.row5" /></th>
							<th><spring:message code="lbl.agent.listAgent.row6" /></th>
							<th><spring:message code="lbl.agent.listAgent.row7" /></th>
							<th><spring:message code="lbl.agent.listAgent.row8" /></th>
							<th><spring:message code="lbl.agent.listAgent.row9" /></th>
							<th></th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- /block -->
</div>
<script>
	
</script>

<!-- This div is used to show message to user -->
<div id="messageDialog" style="display: none;">
	<span id="messageContent"></span>
</div>

<!-- This is dialog to add Agent -->
<div id="addAgentDialog" style="display: none;">
 <div class="inputarea">
	<div class="left" style="width: 50%; float: left">		
		<div class="form-group">
			<label for="shortname" class="col-sm-3 control-label"><spring:message
					code="lbl.agent.addAgentDialog.shortname" /><span
				style="color: red;">*</span> </label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="txtShortName"
					placeholder='Short name' value="" name="txtShortName" required>
				<span style="font-size: 11px; color: red" id="errorTxtShortName"></span>
			</div>
		</div>

		<div class="form-group">
			<label for="longname" class="col-sm-3 control-label"><spring:message
					code="lbl.agent.addAgentDialog.longname" /></label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="txtLongName"
					placeholder='Long name' value="" name="txtLongName" required>
				<span style="font-size: 11px; color: red" id="errorTxtLongName"></span>
			</div>
		</div>

		<div class="form-group">
			<label for="address" class="col-sm-3 control-label"><spring:message
					code="lbl.agent.addAgentDialog.address" /> </label>
			<div class="col-sm-9">
				<textarea type="text" class="form-control" id="txtAddress"
					placeholder='Address' value="" name="txtAddress" required></textarea>
				<span style="font-size: 11px; color: red" id="errorTxtAddress"></span>
			</div>
		</div>

		<div class="form-group">
			<label for="tel" class="col-sm-3 control-label"><spring:message
					code="lbl.agent.addAgentDialog.tel" /></label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="txtTel"
					placeholder='Telephone number' value="" name="txtTel" required>
				<span style="font-size: 11px; color: red" id="errorTxtTel"></span>
			</div>
		</div>
		</div>
		<div class="right" style="width: 50%; float: right">
		<div class="form-group">
			<label for="fax" class="col-sm-3 control-label"><spring:message
					code="lbl.agent.addAgentDialog.fax" /></label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="txtFax"
					placeholder='Fax' value="" name="txtFax" required> <span
					style="font-size: 11px; color: red" id="errorTxtFax"></span>
			</div>
		</div>

		<div class="form-group">
			<label for="taxno" class="col-sm-3 control-label"><spring:message
					code="lbl.agent.addAgentDialog.taxno" /></label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="txtTaxNo"
					placeholder='Tax number' value="" name="txtTaxNo" required>
				<span style="font-size: 11px; color: red" id="errorTxtTaxNo"></span>
			</div>
		</div>
		
		<div class="form-group">
				<label for="remark" class="col-sm-3 control-label"><spring:message
						code="lbl.agent.addAgentDialog.remark" /></label>
				<div class="col-sm-9">
					<textarea type="text" class="form-control" id="txtRemark"
						placeholder='Remark' value="" name="txtRemark" required></textarea>
					<span style="font-size: 11px; color: red" id="errorTxtRemark"></span>
				</div>
			</div>

		<div class="form-group">
			<label for="txtStatus" class="col-sm-3 control-label"><spring:message
					code="lbl.agent.lblForDdlStatus" /> </label>
			<div class="col-sm-9">
				<select id="txtStatus">
					<option value="Active"><spring:message
							code="lbl.agent.txtStatus.Active" /></option>
					<option value="In-Active"><spring:message
							code="lbl.agent.txtStatus.In-Active" /></option>
				</select>
			</div>
		</div>
		</div>
		<div style="clear: both;"></div>
	</div>
	<div class="contactlist">
		<div class="form-group">
				<button id="btnAddNewAgentContact" class="btn btn-info">
					<spring:message code="lbl.agent.btnAddNewAgentContact" />
				</button>
				<h3>Contact List</h3>
				<table id="tblAgentContactList"
					class="table table-hover table-bordered responsive"
					style="margin-top: 15px">
					<thead>
						<tr>
							<th class="th"><spring:message
									code="lbl.agent.listAgentContact.row1" /></th>
							<th class="th"><spring:message
									code="lbl.agent.listAgentContact.row2" /></th>
							<th class="th"><spring:message
									code="lbl.agent.listAgentContact.row3" /></th>
							<th class="th"></th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
	</div>
</div>


<!-- This is dialog to edit Agent -->
<div id="editAgentDialog" style="display: none;"editAgentDialog">
 <div class="inputarea">
	<div class="left" style="width: 50%; float: left">
		<div class="form-group" style="display: none;">
			<label for="agentcode" class="col-sm-3 control-label"><spring:message
					code="lbl.agent.addAgentDialog.agentcode" /><span
				style="color: red;">*</span> </label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="txtAgentCode"
					placeholder='Short name' value="" name="txtAgentCode" required>
				<p style="font-size: 11px; color: red" id="errortxtAgentCode"></p>
			</div>
		</div>
		<div class="form-group">
			<label for="shortname" class="col-sm-3 control-label"><spring:message
					code="lbl.agent.addAgentDialog.shortname" /><span
				style="color: red;">*</span> </label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="txtShortName"
					placeholder='Short name' value="" name="txtShortName" required>
				<span style="font-size: 11px; color: red" id="errorTxtShortName"></span>
			</div>
		</div>

		<div class="form-group">
			<label for="longname" class="col-sm-3 control-label"><spring:message
					code="lbl.agent.addAgentDialog.longname" /></label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="txtLongName"
					placeholder='Long name' value="" name="txtLongName" required>
				<span style="font-size: 11px; color: red" id="errorTxtLongName"></span>
			</div>
		</div>

		<div class="form-group">
			<label for="address" class="col-sm-3 control-label"><spring:message
					code="lbl.agent.addAgentDialog.address" /> </label>
			<div class="col-sm-9">
				<textarea type="text" class="form-control" id="txtAddress"
					placeholder='Address' value="" name="txtAddress" required></textarea>
				<span style="font-size: 11px; color: red" id="errorTxtAddress"></span>
			</div>
		</div>

		<div class="form-group">
			<label for="tel" class="col-sm-3 control-label"><spring:message
					code="lbl.agent.addAgentDialog.tel" /></label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="txtTel"
					placeholder='Telephone number' value="" name="txtTel" required>
				<span style="font-size: 11px; color: red" id="errorTxtTel"></span>
			</div>
		</div>
		</div>
		<div class="right" style="width: 50%; float: right">
		<div class="form-group">
			<label for="fax" class="col-sm-3 control-label"><spring:message
					code="lbl.agent.addAgentDialog.fax" /></label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="txtFax"
					placeholder='Fax' value="" name="txtFax" required> <span
					style="font-size: 11px; color: red" id="errorTxtFax"></span>
			</div>
		</div>

		<div class="form-group">
			<label for="taxno" class="col-sm-3 control-label"><spring:message
					code="lbl.agent.addAgentDialog.taxno" /></label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="txtTaxNo"
					placeholder='Tax number' value="" name="txtTaxNo" required>
				<span style="font-size: 11px; color: red" id="errorTxtTaxNo"></span>
			</div>
		</div>
		
		<div class="form-group">
				<label for="remark" class="col-sm-3 control-label"><spring:message
						code="lbl.agent.addAgentDialog.remark" /></label>
				<div class="col-sm-9">
					<textarea type="text" class="form-control" id="txtRemark"
						placeholder='Remark' value="" name="txtRemark" required></textarea>
					<span style="font-size: 11px; color: red" id="errorTxtRemark"></span>
				</div>
			</div>

		<div class="form-group">
			<label for="txtStatus" class="col-sm-3 control-label"><spring:message
					code="lbl.agent.lblForDdlStatus" /> </label>
			<div class="col-sm-9">
				<select id="txtStatus">
					<option value="Active"><spring:message
							code="lbl.agent.txtStatus.Active" /></option>
					<option value="In-Active"><spring:message
							code="lbl.agent.txtStatus.In-Active" /></option>
				</select>
			</div>
		</div>
		</div>
		<div style="clear: both;"></div>
	</div>
	<div class="contactlist">
		<div class="form-group">
				<button id="btnAddNewAgentContact" class="btn btn-info">
					<spring:message code="lbl.agent.btnAddNewAgentContact" />
				</button>
				<h3>Contact List</h3>
				<table id="tblAgentContactList"
					class="table table-hover table-bordered responsive"
					style="margin-top: 15px">
					<thead>
						<tr>
							<th class="th"><spring:message
									code="lbl.agent.listAgentContact.row1" /></th>
							<th class="th"><spring:message
									code="lbl.agent.listAgentContact.row2" /></th>
							<th class="th"><spring:message
									code="lbl.agent.listAgentContact.row3" /></th>
							<th class="th"></th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
	</div>
</div>

<!-- This is dialog confirm delete -->
<div id="deleteAgentDialog" style="display: none;">
	<span id="messageContent"></span>
</div>

<!-- This div is used to show message to user -->
<div id="messageDialog" style="display: none;">
	<span id="messageContent"></span>
</div>

<style>
.ui-dialog-buttonset {
	
}
</style>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="resources/userJs/factory/factory.js"></script>

<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left"><spring:message code="lbl.factory.lblFactoryTitle"/></div>
		</div>
		
		<button id="btnAddNewFactory" class="btn btn-info"
			style="margin-left: 20px; margin-top: 10px"><spring:message code="lbl.factory.btnAddNewFactory"/></button>

		<div class="form-group">
			<span style="margin-left: 20px; margin-top: 10px;"><spring:message code="lbl.factory.ddlStatus"/></span> <select
				id="ddlStatus" style="margin-left: 20px; margin-top: 10px">
				<option value="selectRequest" selected disabled><spring:message code="lbl.factory.ddlStatus.selectRequest"/></option>
				<option value="All"><spring:message code="lbl.factory.ddlStatus.All"/></option>
				<option value="Active"><spring:message code="lbl.factory.ddlStatus.Active"/></option>
				<option value="In-Active"><spring:message code="lbl.factory.ddlStatus.In-Active"/></option>
			</select>
		</div>
		<div class="block-content collapse in">
			<div class="span12">
				<table class="table table-striped table-bordered display responsive"
					id="listFactory">
					<thead>
						<tr>
							<th><spring:message code="lbl.factory.listFactory.row1"/></th>
							<th><spring:message code="lbl.factory.listFactory.row2"/></th>
							<th><spring:message code="lbl.factory.listFactory.row3"/></th>
							<th><spring:message code="lbl.factory.listFactory.row4"/></th>
							<th><spring:message code="lbl.factory.listFactory.row5"/></th>
							<th><spring:message code="lbl.factory.listFactory.row6"/></th>
							<th><spring:message code="lbl.factory.listFactory.row7"/></th>
							<th><spring:message code="lbl.factory.listFactory.row8"/></th>
							<th><spring:message code="lbl.factory.listFactory.row9"/></th>
							<th></th>
							<!-- <th></th> -->
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

<!-- This is dialog to add Factory -->
<div id="addFactoryDialog" style="display: none;"
	name="addFactoryDialog">
	<div class="inputarea">
		<div class="left" style="width: 50%; float: left">
			<div class="form-group">
				<label for="txtFactoryCode" class="col-sm-3 control-label"><spring:message code="lbl.factory.addFactoryDialog.txtFactoryCode"/><span
					style="color: red;">*</span></label>
				<div class="col-sm-9">
					<input maxlength="50" type="text" class="form-control" id="txtFactoryCode"
						placeholder='Factory code' value="" name="txtFactoryCode" required>
					<p style="font-size: 11px; color: red" id="errorTxtFactoryCode"></p>
				</div>
			</div>

			<div class="form-group">
				<label for="txtShortName" class="col-sm-3 control-label"><spring:message code="lbl.factory.addFactoryDialog.txtShortName"/><span style="color: red;">*</span>
				</label>
				<div class="col-sm-9">
					<input maxlength="50" type="text" class="form-control" id="txtShortName"
						placeholder='Short name' value="" name="txtShortName" required>
					<p style="font-size: 11px; color: red" id="errorTxtShortName"></p>
				</div>
			</div>

			<div class="form-group">
				<label for="txtLongName" class="col-sm-3 control-label"><spring:message code="lbl.factory.addFactoryDialog.txtLongName"/></label>
				<div class="col-sm-9">
					<input maxlength="100" type="text" class="form-control" id="txtLongName"
						placeholder='Long name' value="" name="txtLongName" required>
					<p style="font-size: 11px; color: red" id="errorTxtLongName"></p>
				</div>
			</div>

			<div class="form-group">
				<label for="txtAddress" class="col-sm-3 control-label"><spring:message code="lbl.factory.addFactoryDialog.txtAddress"/>
				</label>
				<div class="col-sm-9">
					<textarea maxlength="200" type="text" class="form-control" id="txtAddress"
						placeholder='Address' value="" name="txtAddress" required></textarea>
					<p style="font-size: 11px; color: red" id="errorTxtAddress"></p>
				</div>
			</div>
		</div>
		<div class="right" style="width: 50%; float: right">
			<div class="form-group">
				<label for="txtTel" class="col-sm-3 control-label"><spring:message code="lbl.factory.addFactoryDialog.txtTel"/></label>
				<div class="col-sm-9">
					<input maxlength="50" type="text" class="form-control" id="txtTel"
						placeholder='Telephone number' value="" name="txtTel" required>
					<p style="font-size: 11px; color: red" id="errorTxtTel"></p>
				</div>
			</div>

			<div class="form-group">
				<label for="txtFax" class="col-sm-3 control-label"><spring:message code="lbl.factory.addFactoryDialog.txtFax"/></label>
				<div class="col-sm-9">
					<input maxlength="50" type="text" class="form-control" id="txtFax"
						placeholder='Fax' value="" name="txtFax" required>
					<p style="font-size: 11px; color: red" id="errorTxtFax"></p>
				</div>
			</div>

			<div class="form-group">
				<label for="txtTaxNo" class="col-sm-3 control-label"><spring:message code="lbl.factory.addFactoryDialog.txtTaxNo"/></label>
				<div class="col-sm-9">
					<input maxlength="50" type="text" class="form-control" id="txtTaxNo"
						placeholder='Tax number' value="" name="txtTaxNo" required>
					<p style="font-size: 11px; color: red" id="errorTxtTaxNo"></p>
				</div>
			</div>

			<div class="form-group">
				<label for="txtStatus" class="col-sm-3 control-label"><spring:message code="lbl.factory.addFactoryDialog.txtStatus"/>
				</label>
				<div class="col-sm-9">
					<select id="txtStatus">
						<option value="Active"><spring:message code="lbl.factory.addFactoryDialog.txtStatus.Active"/></option>
						<option value="In-Active"><spring:message code="lbl.factory.addFactoryDialog.txtStatus.In-Active"/></option>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label for="txtRemark" class="col-sm-3 control-label"><spring:message code="lbl.factory.addFactoryDialog.txtRemark"/>
				</label>
				<div class="col-sm-9">
					<textarea maxlength="500" class="form-control" id="txtRemark"
						placeholder='Remark' name="txtRemark" maxlength="500"></textarea>
					<p style="font-size: 11px; color: red" id="errorTxtRemark"></p>
				</div>
			</div>
		</div>
		<div style="clear: both;"></div>
	</div>
	
	<div class="contactlist">
		<div class="form-group">
			<button id="btnAddNewFactoryContact" class="btn btn-info"><spring:message code="lbl.factory.addFactoryDialog.btnAddNewFactoryContact"/></button>
			<h3>Contact List</h3>
			<table id="tblFactoryContactList"
				class="table table-hover table-bordered responsive"
				style="margin-top: 15px">
				<thead>
					<tr>
						<th class="th"><spring:message code="lbl.factory.addFactoryDialog.tblFactoryContactList.name"/></th>
						<th class="th"><spring:message code="lbl.factory.addFactoryDialog.tblFactoryContactList.email"/></th>
						<th class="th"><spring:message code="lbl.factory.addFactoryDialog.tblFactoryContactList.tel"/></th>
						<th class="th"></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
	<hr>
	<div class="accountinformation">
		<div class="form-group">
			<button id="btnAddNewFactoryaccountinformation" class="btn btn-info"><spring:message code="lbl.factory.addFactoryDialog.btnAddNewFactoryaccountinformation"/></button>
			<h3>Account Infomation List</h3>
			<table id="tblFactoryaccountinformationList"
				class="table table-hover table-bordered responsive"
				style="margin-top: 15px">
				<thead>
					<tr>
						<th class="th"><spring:message code="lbl.factory.addFactoryDialog.tblFactoryaccountinformationList.row1"/></th>
						<th class="th"><spring:message code="lbl.factory.addFactoryDialog.tblFactoryaccountinformationList.row2"/></th>
						<th class="th"><spring:message code="lbl.factory.addFactoryDialog.tblFactoryaccountinformationList.row3"/></th>
						<th class="th"><spring:message code="lbl.factory.addFactoryDialog.tblFactoryaccountinformationList.row4"/></th>
						<th class="th"><spring:message code="lbl.factory.addFactoryDialog.tblFactoryaccountinformationList.row5"/></th>
						<th class="th"></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
</div>


<!-- This is dialog to edit Factory -->
<div id="editFactoryDialog" style="display: none;"
	name="addFactoryDialog">
	<div class="inputarea">
		<div class="left" style="width: 50%; float: left">
			<div class="form-group">
				<label for="txtFactoryCode" class="col-sm-3 control-label"><spring:message code="lbl.factory.addFactoryDialog.txtFactoryCode"/><span
					style="color: red;">*</span></label>
				<div class="col-sm-9">
					<input maxlength="50" type="text" class="form-control" id="txtFactoryCode"
						placeholder='Factory code' value="" name="txtFactoryCode" required
						readonly> <span style="font-size: 11px; color: red"
						id="errorTxtFactoryCode"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="txtShortName" class="col-sm-3 control-label"><spring:message code="lbl.factory.addFactoryDialog.txtShortName"/><span style="color: red;">*</span>
				</label>
				<div class="col-sm-9">
					<input maxlength="50" type="text" class="form-control" id="txtShortName"
						placeholder='Short name' value="" name="txtShortName" required>
					<span style="font-size: 11px; color: red" id="errorTxtShortName"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="txtLongName" class="col-sm-3 control-label"><spring:message code="lbl.factory.addFactoryDialog.txtLongName"/></label>
				<div class="col-sm-9">
					<input maxlength="100" type="text" class="form-control" id="txtLongName"
						placeholder='Long name' value="" name="txtLongName" required>
					<span style="font-size: 11px; color: red" id="errorTxtLongName"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="txtAddress" class="col-sm-3 control-label"><spring:message code="lbl.factory.addFactoryDialog.txtAddress"/>
				</label>
				<div class="col-sm-9">
					<textarea maxlength="200" type="text" class="form-control" id="txtAddress"
						placeholder='Address' value="" name="txtAddress" required></textarea>
					<span style="font-size: 11px; color: red" id="errorTxtAddress"></span>
				</div>
			</div>
		</div>
		<div class="right" style="width: 50%; float: right">
			<div class="form-group">
				<label for="txtTel" class="col-sm-3 control-label"><spring:message code="lbl.factory.addFactoryDialog.txtTel"/></label>
				<div class="col-sm-9">
					<input maxlength="50" type="text" class="form-control" id="txtTel"
						placeholder='Telephone number' value="" name="txtTel" required>
					<span style="font-size: 11px; color: red" id="errorTxtTel"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="txtFax" class="col-sm-3 control-label"><spring:message code="lbl.factory.addFactoryDialog.txtFax"/></label>
				<div class="col-sm-9">
					<input maxlength="50" type="text" class="form-control" id="txtFax"
						placeholder='Fax' value="" name="txtFax" required> <span
						style="font-size: 11px; color: red" id="errorTxtFax"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="txtTaxNo" class="col-sm-3 control-label"><spring:message code="lbl.factory.addFactoryDialog.txtTaxNo"/></label>
				<div class="col-sm-9">
					<input maxlength="50" type="text" class="form-control" id="txtTaxNo"
						placeholder='Tax number' value="" name="txtTaxNo" required>
					<span style="font-size: 11px; color: red" id="errorTxtTaxNo"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="txtStatus" class="col-sm-3 control-label"><spring:message code="lbl.factory.addFactoryDialog.txtStatus"/>
				</label>
				<div class="col-sm-9">
					<select id="txtStatus">
						<option value="Active"><spring:message code="lbl.factory.addFactoryDialog.txtStatus.Active"/></option>
						<option value="In-Active"><spring:message code="lbl.factory.addFactoryDialog.txtStatus.In-Active"/></option>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label for="txtRemark" class="col-sm-3 control-label"><spring:message code="lbl.factory.addFactoryDialog.txtRemark"/>
				</label>
				<div class="col-sm-9">
					<textarea maxlength="500" class="form-control" id="txtRemark"
						placeholder='Remark' name="txtRemark" maxlength="500"></textarea>
					<p style="font-size: 11px; color: red" id="errorTxtRemark"></p>
				</div>
			</div>
		</div>
		<div style="clear: both;"></div>
	</div>
	<div class="contactlist">
		<div class="form-group">
			<button id="btnAddNewFactoryContact" class="btn btn-info"><spring:message code="lbl.factory.addFactoryDialog.btnAddNewFactoryContact"/></button>
			<table id="tblFactoryContactList"
				class="table table-hover table-bordered responsive" style="margin-top: 15px">
				<thead>
					<tr>
						<th class="th"><spring:message code="lbl.factory.addFactoryDialog.tblFactoryContactList.name"/></th>
						<th class="th"><spring:message code="lbl.factory.addFactoryDialog.tblFactoryContactList.email"/></th>
						<th class="th"><spring:message code="lbl.factory.addFactoryDialog.tblFactoryContactList.tel"/></th>
						<th class="th"></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
	
	<hr>
	<div class="accountinformation">
		<div class="form-group">
			<button id="btnAddNewFactoryaccountinformation" class="btn btn-info"><spring:message code="lbl.factory.addFactoryDialog.btnAddNewFactoryaccountinformation"/></button>
			<h3>Account Infomation List</h3>
			<table id="tblFactoryaccountinformationList"
				class="table table-hover table-bordered responsive"
				style="margin-top: 15px">
				<thead>
					<tr>
						<th class="th"><spring:message code="lbl.factory.addFactoryDialog.tblFactoryaccountinformationList.row1"/></th>
						<th class="th"><spring:message code="lbl.factory.addFactoryDialog.tblFactoryaccountinformationList.row2"/></th>
						<th class="th"><spring:message code="lbl.factory.addFactoryDialog.tblFactoryaccountinformationList.row3"/></th>
						<th class="th"><spring:message code="lbl.factory.addFactoryDialog.tblFactoryaccountinformationList.row4"/></th>
						<th class="th"><spring:message code="lbl.factory.addFactoryDialog.tblFactoryaccountinformationList.row5"/></th>
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
<div id="deleteFactoryDialog" style="display: none;">
	<span id="messageContent"></span>
</div>

<style>
.ui-dialog-buttonset {
	
}
</style>
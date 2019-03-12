<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="resources/userJs/shippingline/list.js"></script>

<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left"><spring:message code="lbl.shippingLineList"/></div>
		</div>

		<button id="btnAddNewShippingline" class="btn btn-info"
			style="margin-left: 20px; margin-top: 10px"><spring:message code="lbl.addNewShippingLine"/></button>
		<div class="form-group">
			<span style="margin-left: 20px; margin-top: 10px;"><spring:message code="lbl.status"/></span> 
			<select
				id="ddlStatus" style="margin-left: 20px; margin-top: 10px">
				<option value="All"><spring:message code="lbl.status.All"/></option>
				<option value="Active"><spring:message code="lbl.status.Active"/></option>
				<option value="InActive"><spring:message code="lbl.status.InActive"/></option>
			</select>
		</div>
		<div class="block-content collapse in">
			<div class="span12">
				<table class="table table-striped table-bordered display responsive"
					id="listShippingline">
					<thead>
						<tr>
							<th><spring:message code="lbl.tbl.no"/></th>
							<th><spring:message code="lbl.tbl.code"/></th>
							<th><spring:message code="lbl.tbl.shortName"/></th>
							<th><spring:message code="lbl.tbl.longName"/>e</th>
							<th><spring:message code="lbl.tbl.address"/></th>
							<th><spring:message code="lbl.tbl.tel"/></th>
							<th><spring:message code="lbl.tbl.fax"/></th>
							<th><spring:message code="lbl.tbl.taxNo"/></th>
							<th><spring:message code="lbl.tbl.NameEmail"/></th>
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

<!-- This is dialog to add Shippingline -->
<div id="addShippinglineDialog" style="display: none;"
	name="addShippinglineDialog">
	<div class="inputarea">
		<div class="left" style="width: 50%; float: left">
			<div class="form-group">
				<label for="txtShippinglineCode" class="col-sm-3 control-label"><spring:message code="lbl.code"/><span
					style="color: red;">*</span></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtShippinglineCode"
						placeholder='Shipping line code' value=""
						name="txtShippinglineCode" required>
					<p style="font-size: 11px; color: red"
						id="errorTxtShippinglineCode"></p>
				</div>
			</div>

			<div class="form-group">
				<label for="txtShortName" class="col-sm-3 control-label"><spring:message code="lbl.shortName"/><span style="color: red;">*</span>
				</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtShortName"
						placeholder='Short name' value="" name="txtShortName" required>
					<p style="font-size: 11px; color: red" id="errorTxtShortName"></p>
				</div>
			</div>

			<div class="form-group">
				<label for="txtLongName" class="col-sm-3 control-label"><spring:message code="lbl.longName"/></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtLongName"
						placeholder='Long name' value="" name="txtLongName">
					<p style="font-size: 11px; color: red" id="errorTxtLongName"></p>
				</div>
			</div>

			<div class="form-group">
				<label for="txtAddress" class="col-sm-3 control-label"><spring:message code="lbl.address"/></label>
				<div class="col-sm-9">
					<textarea rows="3" cols="50" id="txtAddress" name="txtAddress"></textarea>
					<p style="font-size: 11px; color: red" id="errorTxtAddress"></p>
				</div>
			</div>

		</div>
		<div class="right" style="width: 50%; float: right">
			<div class="form-group">
				<label for="txtTel" class="col-sm-3 control-label"><spring:message code="lbl.tel"/></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtTel"
						placeholder='Telephone number' value="" name="txtTel">
					<p style="font-size: 11px; color: red" id="errorTxtTel"></p>
				</div>
			</div>

			<div class="form-group">
				<label for="txtFax" class="col-sm-3 control-label"><spring:message code="lbl.fax"/></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtFax"
						placeholder='Fax' value="" name="txtFax">
					<p style="font-size: 11px; color: red" id="errorTxtFax"></p>
				</div>
			</div>

			<div class="form-group">
				<label for="txtTaxNo" class="col-sm-3 control-label"><spring:message code="lbl.taxNo"/></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtTaxNo"
						placeholder='Tax number' value="" name="txtTaxNo">
					<p style="font-size: 11px; color: red" id="errorTxtTaxNo"></p>
				</div>
			</div>

			<div class="form-group">
				<label for="txtStatus" class="col-sm-3 control-label"><spring:message code="lbl.status"/></label>
				<div class="col-sm-9">
					<select id="txtStatus">
						<option value="Active"><spring:message code="lbl.status.Active"/></option>
						<option value="InActive"><spring:message code="lbl.status.InActive"/></option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="txtRemark" class="col-sm-3 control-label"><spring:message code="lbl.remark"/></label>
				<div class="col-sm-9">
					<textarea rows="3" cols="50" id="txtRemark"></textarea>
				</div>
			</div>
		</div>
		<div style="clear: both;"></div>
	</div>
	<div class="contactlist">
		<div class="form-group">
			<button id="btnAddNewShippinglineContact" class="btn btn-info"><spring:message code="lbl.newContact"/></button>
			<h3>Contact List</h3>
			<table id="tblShippinglineContactList"
				class="table table-hover table-bordered responsive"
				style="margin-top: 15px">
				<thead>
					<tr>
						<th class="th"><spring:message code="lbl.tblContact.name"/></th>
						<th class="th"><spring:message code="lbl.tblContact.email"/></th>
						<th class="th"><spring:message code="lbl.tblContact.tel"/></th>
						<th class="th"></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
</div>


<!-- This is dialog to edit Shippingline -->
<div id="editShippinglineDialog" style="display: none;"editShippinglineDialog">
	<div class="left" style="width: 50%; float: left">
		<div class="form-group">
			<label for="txtShippinglineCode" class="col-sm-3 control-label"><spring:message code="lbl.code"/><span
				style="color: red;">*</span></label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="txtShippinglineCode"
					placeholder='Shipping line code' value=""
					name="txtShippinglineCode" required disabled>
				<p style="font-size: 11px; color: red" id="errorTxtShippinglineCode"></p>
			</div>
		</div>

		<div class="form-group">
			<label for="txtShortName" class="col-sm-3 control-label"><spring:message code="lbl.shortName"/><span style="color: red;">*</span>
			</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="txtShortName"
					placeholder='Short name' value="" name="txtShortName" required>
				<p style="font-size: 11px; color: red" id="errorTxtShortName"></p>
			</div>
		</div>

		<div class="form-group">
			<label for="txtLongName" class="col-sm-3 control-label"><spring:message code="lbl.longName"/></label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="txtLongName"
					placeholder='Long name' value="" name="txtLongName">
				<p style="font-size: 11px; color: red" id="errorTxtLongName"></p>
			</div>
		</div>

		<div class="form-group">
			<label for="txtAddress" class="col-sm-3 control-label"><spring:message code="lbl.address"/></label>
			<div class="col-sm-9">
				<textarea rows="3" cols="50" id="txtAddress" name="txtAddress"></textarea>
				<p style="font-size: 11px; color: red" id="errorTxtAddress"></p>
			</div>
		</div>
	</div>
	<div class="right" style="width: 50%; float: right">

		<div class="form-group">
			<label for="txtTel" class="col-sm-3 control-label"><spring:message code="lbl.tel"/></label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="txtTel"
					placeholder='Telephone number' value="" name="txtTel">
				<p style="font-size: 11px; color: red" id="errorTxtTel"></p>
			</div>
		</div>

		<div class="form-group">
			<label for="txtFax" class="col-sm-3 control-label"><spring:message code="lbl.fax"/></label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="txtFax"
					placeholder='Fax' value="" name="txtFax">
				<p style="font-size: 11px; color: red" id="errorTxtFax"></p>
			</div>
		</div>

		<div class="form-group">
			<label for="txtTaxNo" class="col-sm-3 control-label"><spring:message code="lbl.taxNo"/></label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="txtTaxNo"
					placeholder='Tax number' value="" name="txtTaxNo">
				<p style="font-size: 11px; color: red" id="errorTxtTaxNo"></p>
			</div>
		</div>

		<div class="form-group">
			<label for="txtStatus" class="col-sm-3 control-label"><spring:message code="lbl.status"/></label>
			<div class="col-sm-9">
				<select id="txtStatus">
					<option value="Active"><spring:message code="lbl.status.Active"/></option>
					<option value="InActive"><spring:message code="lbl.status.InActive"/></option>
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<label for="txtRemark" class="col-sm-3 control-label"><spring:message code="lbl.remark"/></label>
			<div class="col-sm-9">
				<textarea rows="3" cols="50" id="txtRemark"></textarea>
			</div>
		</div>
	</div>
	<div style="clear: both;"></div>
	<div class="form-group">
		<button id="btnAddNewShippinglineContact" class="btn btn-info"><spring:message code="lbl.newContact"/></button>
		<h3>Contact List</h3>
		<table id="tblShippinglineContactList"
			class="table table-hover table-bordered responsive"
			style="margin-top: 15px">
			<thead>
				<tr>
					<th class="th"><spring:message code="lbl.tblContact.name"/></th>
					<th class="th"><spring:message code="lbl.tblContact.email"/></th>
					<th class="th"><spring:message code="lbl.tblContact.tel"/></th>
					<th class="th"></th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</div>

<!-- This is dialog confirm delete -->
<div id="deleteShippinglineDialog" style="display: none;">
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
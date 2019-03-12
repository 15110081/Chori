<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="resources/userJs/fabricSupplier/fabricSupplier.js"></script>

<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left"><spring:message code="lbl.fabricSupplier.lblFabricSupplierTitle"/></div>
		</div>

		<button id="btnAddNewFabricSupplier" class="btn btn-info"
			style="margin-left: 20px; margin-top: 10px"><spring:message code="lbl.fabricSupplier.btnAddNewFabricSupplier"/></button>
		<div class="form-group">
			<span style="margin-left: 20px; margin-top: 10px;"><spring:message code="lbl.fabricSupplier.ddlStatus"/></span><select
				id="ddlStatus" style="margin-left: 20px; margin-top: 10px">
				<option value="selectRequest" selected disabled><spring:message code="lbl.fabricSupplier.ddlStatus.selectRequest"/></option>
				<option value="All"><spring:message code="lbl.fabricSupplier.ddlStatus.All"/></option>
				<option value="Active"><spring:message code="lbl.fabricSupplier.ddlStatus.Active"/></option>
				<option value="In-Active"><spring:message code="lbl.fabricSupplier.ddlStatus.In-Active"/></option>
			</select>
		</div>
		<div class="block-content collapse in">
			<div class="span12">
				<table class="table table-striped table-bordered display responsive"
					id="listFabricSupplier">
					<thead>
						<tr>
							<th><spring:message code="lbl.fabricSupplier.listFabricSupplier.row1"/></th>
							<th><spring:message code="lbl.fabricSupplier.listFabricSupplier.row2"/></th>
							<th><spring:message code="lbl.fabricSupplier.listFabricSupplier.row3"/></th>
							<th><spring:message code="lbl.fabricSupplier.listFabricSupplier.row4"/></th>
							<th><spring:message code="lbl.fabricSupplier.listFabricSupplier.row5"/></th>
							<th><spring:message code="lbl.fabricSupplier.listFabricSupplier.row6"/></th>
							<th><spring:message code="lbl.fabricSupplier.listFabricSupplier.row7"/></th>
							<th><spring:message code="lbl.fabricSupplier.listFabricSupplier.row8"/></th>
							<th><spring:message code="lbl.fabricSupplier.listFabricSupplier.row9"/></th>
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

<!-- This is dialog to add FabricSupplier -->
<div id="addFabricSupplierDialog" style="display: none;"
	name="addFabricSupplierDialog">

	<!-- 
	<div class="form-group">
		<label for="txtFabricSupplierCode" class="col-sm-3 control-label">Is
			Chori</label>
		<div class="col-sm-9">
			<input type="checkbox" id="cbIsChori" name="cbIsChori"
				class="uniform_on">
		</div>
	</div>
	 -->
	<div class="inputarea">
		<div class="left" style="width: 50%; float: left">
			<div class="form-group">
				<label for="txtFabricSupplierCode" class="col-sm-3 control-label"><spring:message code="lbl.fabricSupplier.addFabricSupplierDialog.txtFabricSupplierCode"/><span
					style="color: red;">*</span></label>
				<div class="col-sm-9">
					<input maxlength="50" type="text" class="form-control" id="txtFabricSupplierCode"
						placeholder='Fabric Supplier Code' value=""
						name="txtFabricSupplierCode" required> <p
						style="font-size: 11px; color: red"
						id="errorTxtFabricSupplierCode"></p>
				</div>
			</div>

			<div class="form-group">
				<label for="txtShortName" class="col-sm-3 control-label"><spring:message code="lbl.fabricSupplier.addFabricSupplierDialog.txtShortName"/>
					<span style="color: red;">*</span>
				</label>
				<div class="col-sm-9">
					<input maxlength="50" type="text" class="form-control" id="txtShortName"
						placeholder='Short name' value="" name="txtShortName" required>
					<p style="font-size: 11px; color: red" id="errorTxtShortName"></p>
				</div>
			</div>

			<div class="form-group">
				<label for="txtLongName" class="col-sm-3 control-label"><spring:message code="lbl.fabricSupplier.addFabricSupplierDialog.txtLongName"/></label>
				<div class="col-sm-9">
					<input maxlength="100" type="text" class="form-control" id="txtLongName"
						placeholder='Long name' value="" name="txtLongName" required>
					<p style="font-size: 11px; color: red" id="errorTxtLongName"></p>
				</div>
			</div>

			<div class="form-group">
				<label for="txtAddress" class="col-sm-3 control-label"><spring:message code="lbl.fabricSupplier.addFabricSupplierDialog.txtAddress"/>
				</label>
				<div class="col-sm-9">
					<textarea maxlength="200" class="form-control" id="txtAddress"
						placeholder='Address' name="txtAddress" required></textarea>
					<p style="font-size: 11px; color: red" id="errorTxtAddress"></p>
				</div>
			</div>
		</div>

		<div class="right" style="width: 50%; float: right">
			<div class="form-group">
				<label for="txtTel" class="col-sm-3 control-label"><spring:message code="lbl.fabricSupplier.addFabricSupplierDialog.txtTel"/></label>
				<div class="col-sm-9">
					<input maxlength="50" type="text" class="form-control" id="txtTel"
						placeholder='Telephone number' value="" name="txtTel" required>
					<p style="font-size: 11px; color: red" id="errorTxtTel"></p>
				</div>
			</div>

			<div class="form-group">
				<label for="txtFax" class="col-sm-3 control-label"><spring:message code="lbl.fabricSupplier.addFabricSupplierDialog.txtFax"/></label>
				<div class="col-sm-9">
					<input maxlength="50" type="text" class="form-control" id="txtFax"
						placeholder='Fax' value="" name="txtFax" required> <p
						style="font-size: 11px; color: red" id="errorTxtFax"></p>
				</div>
			</div>

			<div class="form-group">
				<label for="txtTaxNo" class="col-sm-3 control-label"><spring:message code="lbl.fabricSupplier.addFabricSupplierDialog.txtTaxNo"/></label>
				<div class="col-sm-9">
					<input maxlength="50" type="text" class="form-control" id="txtTaxNo"
						placeholder='Tax number' value="" name="txtTaxNo" required>
					<p style="font-size: 11px; color: red" id="errorTxtTaxNo"></p>
				</div>
			</div>

			<div class="form-group">
				<label for="txtStatus" class="col-sm-3 control-label"><spring:message code="lbl.fabricSupplier.addFabricSupplierDialog.txtStatus"/>
				</label>
				<div class="col-sm-9">
					<select id="txtStatus">
						<option value="Active"><spring:message code="lbl.fabricSupplier.addFabricSupplierDialog.txtStatus.Active"/></option>
						<option value="In-Active"><spring:message code="lbl.fabricSupplier.addFabricSupplierDialog.txtStatus.In-Active"/></option>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label for="txtRemark" class="col-sm-3 control-label"><spring:message
						code="lbl.fabricSupplier.addFabricSupplierDialog.txtRemark" /> </label>
				<div class="col-sm-9">
					<textarea maxlength="500" class="form-control" id="txtRemark" placeholder='Remark'
						name="txtRemark" maxlength="500"></textarea>
					<p style="font-size: 11px; color: red" id="errorTxtRemark"></p>
				</div>
			</div>
		</div>
		<div style="clear: both;"></div>
	</div>
	<div class="contactlist">
		<div class="form-group">
			<button id="btnAddNewFabricSupplierContact" class="btn btn-info"><spring:message code="lbl.fabricSupplier.addFabricSupplierDialog.btnAddNewFabricSupplierContact"/></button>
			<h3>Contact List</h3>
			<table id="tblFabricSupplierContactList"
				class="table table-hover table-bordered responsive"
				style="margin-top: 15px">
				<thead>
					<tr>
						<th class="th"><spring:message code="lbl.fabricSupplier.addFabricSupplierDialog.tblFabricSupplierContactList.name"/></th>
						<th class="th"><spring:message code="lbl.fabricSupplier.addFabricSupplierDialog.tblFabricSupplierContactList.email"/></th>
						<th class="th"><spring:message code="lbl.fabricSupplier.addFabricSupplierDialog.tblFabricSupplierContactList.telephone"/></th>
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
<div id="editFabricSupplierDialog" style="display: none;"
	name="editFabricSupplierDialog">
	<!-- 
	<div class="form-group">
		<label for="txtFabricSupplierCode" class="col-sm-3 control-label">Is
			Chori</label>
		<div class="col-sm-9">
			<input type="checkbox" id="cbIsChori" name="cbIsChori">
		</div>
	</div>
	 -->
	 <div class="inputarea">
	 <div class="left" style="width: 50%; float: left">
	<div class="form-group">
		<label for="txtFabricSupplierCode" class="col-sm-3 control-label"><spring:message code="lbl.fabricSupplier.addFabricSupplierDialog.txtFabricSupplierCode"/><span
			style="color: red;">*</span></label>
		<div class="col-sm-9">
			<input maxlength="50" type="text" class="form-control" id="txtFabricSupplierCode"
				placeholder='Fabric Supplier Code' value=""
				name="txtFabricSupplierCode" required readonly> <p
				style="font-size: 11px; color: red" id="errorTxtFabricSupplierCode"></p>
		</div>
	</div>

	<div class="form-group">
		<label for="txtShortName" class="col-sm-3 control-label"><spring:message code="lbl.fabricSupplier.addFabricSupplierDialog.txtShortName"/><span style="color: red;">*</span>
		</label>
		<div class="col-sm-9">
			<input maxlength="50" type="text" class="form-control" id="txtShortName"
				placeholder='Short name' value="" name="txtShortName" required>
			<p style="font-size: 11px; color: red" id="errorTxtShortName"></p>
		</div>
	</div>

	<div class="form-group">
		<label for="txtLongName" class="col-sm-3 control-label"><spring:message code="lbl.fabricSupplier.addFabricSupplierDialog.txtLongName"/></label>
		<div class="col-sm-9">
			<input maxlength="100" type="text" class="form-control" id="txtLongName"
				placeholder='Long name' value="" name="txtLongName" required>
			<p style="font-size: 11px; color: red" id="errorTxtLongName"></p>
		</div>
	</div>

	<div class="form-group">
		<label for="txtAddress" class="col-sm-3 control-label"><spring:message code="lbl.fabricSupplier.addFabricSupplierDialog.txtAddress"/>
		</label>
		<div class="col-sm-9">
			<textarea maxlength="200" class="form-control" id="txtAddress"
				placeholder='Address' name="txtAddress" required></textarea> <span
				style="font-size: 11px; color: red" id="errorTxtAddress"></span>
		</div>
	</div>
	</div>
	<div class="right" style="width: 50%; float: right">
	<div class="form-group">
		<label for="txtTel" class="col-sm-3 control-label"><spring:message code="lbl.fabricSupplier.addFabricSupplierDialog.txtTel"/></label>
		<div class="col-sm-9">
			<input maxlength="50" type="text" class="form-control" id="txtTel"
				placeholder='Telephone number' value="" name="txtTel" required>
			<p style="font-size: 11px; color: red" id="errorTxtTel"></p>
		</div>
	</div>

	<div class="form-group">
		<label for="txtFax" class="col-sm-3 control-label"><spring:message code="lbl.fabricSupplier.addFabricSupplierDialog.txtFax"/></label>
		<div class="col-sm-9">
			<input maxlength="50" type="text" class="form-control" id="txtFax" placeholder='Fax'
				value="" name="txtFax" required> <p
				style="font-size: 11px; color: red" id="errorTxtFax"></p>
		</div>
	</div>

	<div class="form-group">
		<label for="txtTaxNo" class="col-sm-3 control-label"><spring:message code="lbl.fabricSupplier.addFabricSupplierDialog.txtTaxNo"/></label>
		<div class="col-sm-9">
			<input maxlength="50" type="text" class="form-control" id="txtTaxNo"
				placeholder='Tax number' value="" name="txtTaxNo" required>
			<p style="font-size: 11px; color: red" id="errorTxtTaxNo"></p>
		</div>
	</div>

	<div class="form-group">
		<label for="txtStatus" class="col-sm-3 control-label"><spring:message code="lbl.fabricSupplier.addFabricSupplierDialog.txtStatus"/></label>
		<div class="col-sm-9">
			<select id="txtStatus">
				<option value="Active"><spring:message code="lbl.fabricSupplier.addFabricSupplierDialog.txtStatus.Active"/></option>
				<option value="In-Active"><spring:message code="lbl.fabricSupplier.addFabricSupplierDialog.txtStatus.In-Active"/></option>
			</select>
		</div>
	</div>

			<div class="form-group">
				<label for="txtRemark" class="col-sm-3 control-label"><spring:message
						code="lbl.fabricSupplier.addFabricSupplierDialog.txtRemark" /> </label>
				<div class="col-sm-9">
					<textarea maxlength="500" class="form-control" id="txtRemark" placeholder='Remark'
						name="txtRemark" maxlength="500"></textarea>
					<p style="font-size: 11px; color: red" id="errorTxtRemark"></p>
				</div>
			</div>
		</div>
	<div style="clear: both;"></div>
</div>
<div class="contactlist">
	<div class="form-group">
		<button id="btnAddNewFabricSupplierContact" class="btn btn-info"><spring:message code="lbl.fabricSupplier.addFabricSupplierDialog.btnAddNewFabricSupplierContact"/></button>
		<h3>Contact List</h3>
		<table id="tblFabricSupplierContactList"
			class="table table-hover table-bordered responsive" style="margin-top: 15px">
			<thead>
				<tr>
					<th class="th"><spring:message code="lbl.fabricSupplier.addFabricSupplierDialog.tblFabricSupplierContactList.name"/></th>
					<th class="th"><spring:message code="lbl.fabricSupplier.addFabricSupplierDialog.tblFabricSupplierContactList.email"/></th>
					<th class="th"><spring:message code="lbl.fabricSupplier.addFabricSupplierDialog.tblFabricSupplierContactList.telephone"/></th>
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
<div id="deleteFabricSupplierDialog" style="display: none;">
	<span id="messageContent"></span>
</div>

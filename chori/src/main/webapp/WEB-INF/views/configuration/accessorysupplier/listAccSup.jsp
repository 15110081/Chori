<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="resources/userJs/accessorysupplier/accessorysupplier.js"></script>

<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left"><spring:message code="lbl.accessorysupplier.lblTitle"/></div>
		</div>
		
		<button class="btn btn-info" id="btnAddNewAccSup"
		style="margin-left: 20px; margin-top: 10px"><spring:message code="lbl.accessorysupplier.btnAddNew"/></button>
		
		<div class="form-group">
			<span style="margin-left: 20px; margin-top: 10px;"><spring:message code="lbl.accessorysupplier.ddlStatus"/></span> 
			<select id="ddlStatus" style="margin-left: 20px; margin-top: 10px">
				<option value="All"><spring:message code="lbl.accessorysupplier.ddlAll"/></option>
				<option value="Active"><spring:message code="lbl.accessorysupplier.ddlActive"/></option>
				<option value="In-Active"><spring:message code="lbl.accessorysupplier.ddlInActive"/></option>
			</select>
		</div>
		<div class="block-content collapse in">
			<div class="span12">
				<table class="table table-striped table-bordered display responsive"
					id="listAccSup">
					<thead>
						<tr>
							<th><spring:message code="lbl.accessorysupplier.lblNo"/></th>
							<th><spring:message code="lbl.accessorysupplier.lblCode"/></th>
							<th><spring:message code="lbl.accessorysupplier.lblShortName"/></th>
							<th><spring:message code="lbl.accessorysupplier.lblLongName"/></th>
							<th><spring:message code="lbl.accessorysupplier.lblAddress"/></th>
							<th><spring:message code="lbl.accessorysupplier.lblTelephone"/></th>
							<th><spring:message code="lbl.accessorysupplier.lblFax"/></th>
							<th><spring:message code="lbl.accessorysupplier.lblTaxNo"/></th>
							<th><spring:message code="lbl.accessorysupplier.lblRemark"/></th>
							<th><spring:message code="lbl.accessorysupplier.lblNameEmail"/></th>
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

<!-- This is dialog to add Role -->
<div id="addAccSupDialog" style="display: none;"
	 name="addFactoryDialog">
 	<div class="inputarea">
	<div class="left" style="width: 50%; float: left">
	<div class="form-group">
		<label for="txtAccSupCode" class="col-sm-3 control-label"><spring:message code="lbl.accessorysupplier.lblCode"/><span
			style="color: red;">*</span></label>
		<div class="col-sm-9">
			<input placeholder="Accessory Supplier Code" type="text" class="form-control" id="txtAccSupCode"
				value="" name="txtAccSupCode" required>
			<span style="font-size: 11px; color: red" id="errorTxtAccSupCode"></span>
		</div>
	</div>

	<div class="form-group">
		<label for="txtShortName" class="col-sm-3 control-label"><spring:message code="lbl.accessorysupplier.lblShortName"/>
			<span style="color: red;">*</span>
		</label>
		<div class="col-sm-9">
			<input type="text" class="form-control" id="txtShortName"
				placeholder='Short name' value="" name="txtShortName" required>
			<span style="font-size: 11px; color: red" id="errorTxtShortName"></span>
		</div>
	</div>

	<div class="form-group">
		<label for="txtLongName" class="col-sm-3 control-label"><spring:message code="lbl.accessorysupplier.lblLongName"/>
			 </label>
		<div class="col-sm-9">
			<input type="text" class="form-control" id="txtLongName"
				placeholder='Long name' value="" name="txtLongName" required>
			<span style="font-size: 11px; color: red" id="errorTxtLongName"></span>
		</div>
	</div>

	<div class="form-group">
		<label for="txtAddress" class="col-sm-3 control-label"><spring:message code="lbl.accessorysupplier.lblAddress"/>
		</label>
		<div class="col-sm-9">
			<textarea class="form-control" id="txtAddress"
				placeholder='Address' value="" name="txtAddress" required></textarea> <span
				style="font-size: 11px; color: red" id="errorTxtAddress"></span>
		</div>
	</div>
	
	<div class="form-group">
		<label for="txtTel" class="col-sm-3 control-label"><spring:message code="lbl.accessorysupplier.lblTelephone"/></label>
		<div class="col-sm-9">
			<input placeholder="Telephone" type="text" class="form-control" id="txtTel"
				 value="" name="txtTel" required>
			<span style="font-size: 11px; color: red" id="errorTxtTel"></span>
		</div>
	</div>
	
	</div>
	<div class="right" style="width: 50%; float: right">
	<div class="form-group">
		<label for="txtFax" class="col-sm-3 control-label"><spring:message code="lbl.accessorysupplier.lblFax"/></label>
		<div class="col-sm-9">
			<input placeholder="Fax" type="text" class="form-control" id="txtFax" 
				value="" name="txtFax" required> <span
				style="font-size: 11px; color: red" id="errorTxtFax"></span>
		</div>
	</div>

	<div class="form-group">
		<label for="txtTaxNo" class="col-sm-3 control-label"><spring:message code="lbl.accessorysupplier.lblTaxNo"/></label>
		<div class="col-sm-9">
			<input placeholder="Tax No." type="text" class="form-control" id="txtTaxNo"
				 value="" name="txtTaxNo" required>
			<span style="font-size: 11px; color: red" id="errorTxtTaxNo"></span>
		</div>
	</div>

	<div class="form-group">
		<label for="txtEmail" class="col-sm-3 control-label"><spring:message
				code="lbl.user.addUserDialog.txtEmail" /></label>
		<div class="col-sm-9">
			<input type="email" class="form-control" id="txtEmail"
				placeholder='Email' value="" name="txtEmail" required> <span
				style="font-size: 11px; color: red" id="errorTxtEmail"></span>
		</div>
	</div>

	<div class="form-group">
		<label for="txtRemark" class="col-sm-3 control-label"><spring:message code="lbl.accessorysupplier.lblRemark"/></label>
		<div class="col-sm-9">
			<textarea placeholder="Remark" type="text" class="form-control" id="txtRemark"
				 value="" name="txtRemark" required></textarea>
			<span style="font-size: 11px; color: red" id="errorTxtRemark"></span>
		</div>
	</div>

	<div class="form-group">
		<label for="txtStatus" class="col-sm-3 control-label"><spring:message code="lbl.accessorysupplier.ddlStatus"/> </label>
		<div class="col-sm-9">
			<select id="txtStatus">
				<option value="Active"><spring:message code="lbl.accessorysupplier.ddlActive"/></option>
				<option value="In-Active"><spring:message code="lbl.accessorysupplier.ddlInActive"/></option>
			</select>
		</div>
	</div>
	</div>
	</div>
	<div style="clear: both;"></div>
	<div class="contactlist">
	<div class="form-group">
		<button id="btnAddNewAccSupContact" class="btn btn-info"><spring:message code="lbl.accessorysupplier.btnNewContact"/></button>
		<h3>Contact List</h3>
		<table id="tblAccSupContactList"
			class="table table-hover table-bordered responsive" style="margin-top: 15px">
			<thead>
				<tr>
					<th class="th"><spring:message code="lbl.accessorysupplier.lblName"/></th>
					<th class="th"><spring:message code="lbl.accessorysupplier.lblEmail"/></th>
					<th class="th"><spring:message code="lbl.accessorysupplier.lblTel"/></th>
					<th class="th"></th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	</div>
</div>


<!-- This is dialog to edit Acc Sup -->
<div id="editAccSupDialog" style="display: none;"
	 name="editAccSupDialog">
	<div class="left" style="width: 50%; float: left">
	<div class="form-group">
		<label for="txtAccSupCode" class="col-sm-3 control-label" ><spring:message code="lbl.accessorysupplier.lblCode"/><span
			style="color: red;">*</span></label>
		<div class="col-sm-9">
			<input placeholder="Accessory Supplier Code" type="text" class="form-control" id="txtAccSupCode"
				 value="" name="txtAccSupCode" disabled>
			<span style="font-size: 11px; color: red" id="errorTxtAccSupCode"></span>
		</div>
	</div>

	<div class="form-group">
		<label for="txtShortName" class="col-sm-3 control-label"><spring:message code="lbl.accessorysupplier.lblShortName"/>
			<span style="color: red;">*</span>
		</label>
		<div class="col-sm-9">
			<input placeholder="Short name" type="text" class="form-control" id="txtShortName"
				 value="" name="txtShortName" required>
			<span style="font-size: 11px; color: red" id="errorTxtShortName"></span>
		</div>
	</div>

	<div class="form-group">
		<label for="txtLongName" class="col-sm-3 control-label"><spring:message code="lbl.accessorysupplier.lblLongName"/>
			 </label>
		<div class="col-sm-9">
			<input placeholde="Long name" type="text" class="form-control" id="txtLongName"
				value="" name="txtLongName" required>
			<span style="font-size: 11px; color: red" id="errorTxtLongName"></span>
		</div>
	</div>

	<div class="form-group">
		<label placeholder="Address" for="txtAddress" class="col-sm-3 control-label"><spring:message code="lbl.accessorysupplier.lblAddress"/>
		</label>
		<div class="col-sm-9">
			<textarea class="form-control" id="txtAddress"
				value="" name="txtAddress" required></textarea> <span
				style="font-size: 11px; color: red" id="errorTxtAddress"></span>
		</div>
	</div>
	<div class="form-group">
		<label for="txtTel" class="col-sm-3 control-label"><spring:message code="lbl.accessorysupplier.lblTelephone"/></label>
		<div class="col-sm-9">
			<input placeholder="Telephone" type="text" class="form-control" id="txtTel"
				value="" name="txtTel" required>
			<span style="font-size: 11px; color: red" id="errorTxtTel"></span>
		</div>
	</div>
	</div>
	<div class="right" style="width: 50%; float: right">
	<div class="form-group">
		<label for="txtFax" class="col-sm-3 control-label"><spring:message code="lbl.accessorysupplier.lblFax"/></label>
		<div class="col-sm-9">
			<input placeholder="Fax" type="text" class="form-control" id="txtFax" 
				value="" name="txtFax" required> <span
				style="font-size: 11px; color: red" id="errorTxtFax"></span>
		</div>
	</div>

	<div class="form-group">
		<label for="txtTaxNo" class="col-sm-3 control-label"><spring:message code="lbl.accessorysupplier.lblTaxNo"/></label>
		<div class="col-sm-9">
			<input placeholder="Tax No." type="text" class="form-control" id="txtTaxNo"
				 value="" name="txtTaxNo" required>
			<span style="font-size: 11px; color: red" id="errorTxtTaxNo"></span>
		</div>
	</div>
	
	<div class="form-group">
		<label for="txtEmail" class="col-sm-3 control-label"><spring:message
				code="lbl.user.addUserDialog.txtEmail" /></label>
		<div class="col-sm-9">
			<input type="email" class="form-control" id="txtEmail"
				placeholder='Email' value="" name="txtEmail" required> <span
				style="font-size: 11px; color: red" id="errorTxtEmail"></span>
		</div>
	</div>
	
	<div class="form-group">
		<label for="txtRemark" class="col-sm-3 control-label"><spring:message code="lbl.accessorysupplier.lblRemark"/></label>
		<div class="col-sm-9">
			<textarea placeholder="Remark" type="text" class="form-control" id="txtRemark"
				 value="" name="txtRemark" required></textarea>
			<span style="font-size: 11px; color: red" id="errorTxtRemark"></span>
		</div>
	</div>

	<div class="form-group">
		<label for="txtStatus" class="col-sm-3 control-label"><spring:message code="lbl.accessorysupplier.ddlStatus"/> </label>
		<div class="col-sm-9">
			<select id="txtStatus">
				<option value="Active"><spring:message code="lbl.accessorysupplier.ddlActive"/></option>
				<option value="In-Active"><spring:message code="lbl.accessorysupplier.ddlInActive"/></option>
			</select>
		</div>
	</div>
	</div>
	<div style="clear: both;"></div>
	<div class="form-group">
		<button id="btnAddNewAccSupContact" class="btn btn-info"><spring:message code="lbl.accessorysupplier.btnNewContact"/></button>
		<h3>Contact List</h3>
		<table id="tblAccSupContactList"
			class="table table-hover table-bordered responsive" style="margin-top: 15px">
			<thead>
				<tr>
					<th class="th"><spring:message code="lbl.accessorysupplier.lblName"/></th>
					<th class="th"><spring:message code="lbl.accessorysupplier.lblEmail"/></th>
					<th class="th"><spring:message code="lbl.accessorysupplier.lblTel"/></th>
					<th class="th"></th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</div>
<!-- This is dialog confirm delete -->
<div id="deleteAccSupDialog" style="display: none;">
	<span id="messageContent"></span>
</div>

<!-- This div is used to show message to user -->
<div id="messageDialog" style="display: none;">
	<span id="messageContent"></span>
</div>
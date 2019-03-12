<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="resources/userJs/customer/customer.js"></script>

<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">
				<spring:message code="lbl.customer.customerForm" />
			</div>
		</div>
		
		<button id="btnAddNewCustomer" class="btn btn-info"
			style="margin-left: 20px; margin-top: 10px">
			<spring:message code="lbl.customer.btnAddNewCustomer" />
		</button>

		<div class="form-group">
			<span style="margin-left: 20px; margin-top: 10px;"><spring:message
					code="lbl.customer.lblForDdlStatus" /></span><select id="ddlStatus"
				style="margin-left: 20px; margin-top: 10px">
				<option value="selectRequest" selected disabled><spring:message
						code="lbl.customer.ddlStatus.selectRequest" /></option>
				<option value="All"><spring:message
						code="lbl.customer.ddlStatus.All" /></option>
				<option value="Active"><spring:message
						code="lbl.customer.ddlStatus.Active" /></option>
				<option value="In-Active"><spring:message
						code="lbl.customer.ddlStatus.In-Active" /></option>
			</select>
		</div>


		<div class="block-content collapse in">
			<div class="span12">
				<table class="table table-striped table-bordered display responsive"
					id="listCustomer">
					<thead>
						<tr>
							<th><spring:message code="lbl.customer.listCustomer.row1" /></th>
							<th><spring:message code="lbl.customer.listCustomer.row2" /></th>
							<th><spring:message code="lbl.customer.listCustomer.row3" /></th>
					<!--		<th><spring:message code="lbl.customer.listCustomer.row4" /></th>    -->
							<th><spring:message code="lbl.customer.listCustomer.row5" /></th>
							<th><spring:message code="lbl.customer.listCustomer.row6" /></th>
							<th><spring:message code="lbl.customer.listCustomer.row7" /></th>
							<th><spring:message code="lbl.customer.listCustomer.row8" /></th>
							<th><spring:message code="lbl.customer.listCustomer.row9" /></th>
							<th><spring:message code="lbl.customer.listCustomer.row10" /></th>
							<th><spring:message code="lbl.customer.listCustomer.row11" /></th>
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

<!-- This is dialog to add Customer -->
<div id="addCustomerDialog" style="display: none;"
	 name="addCustomerDialog">
	<div class="inputarea">
		<div class="left" style="width: 50%; float: left">
			<div class="form-group">
				<label for="customercode" class="col-sm-3 control-label"><spring:message
						code="lbl.customer.addCustomerDialog.customercode" /><span
					style="color: red;">*</span></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtCustomerCode"
						placeholder='Customer' value="" name="txtCustomerCode"
						required> <span style="font-size: 11px; color: red"
						id="errorTxtCustomerCode"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="shortname" class="col-sm-3 control-label"><spring:message
						code="lbl.customer.addCustomerDialog.shortname" /><span
					style="color: red;">*</span> </label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtShortName"
						placeholder='Full name' value="" name="txtShortName" required>
					<span style="font-size: 11px; color: red" id="errorTxtShortName"></span>
				</div>
			</div>

	<!--		<div class="form-group">
				<label for="longname" class="col-sm-3 control-label"><spring:message
						code="lbl.customer.addCustomerDialog.longname" /></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtLongName"
						placeholder='Long name' value="" name="txtLongName" required>
					<span style="font-size: 11px; color: red" id="errorTxtLongName"></span>
				</div>
			</div>			-->

			<div class="form-group">
				<label for="tel" class="col-sm-3 control-label"><spring:message
						code="lbl.customer.addCustomerDialog.tel" /></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtTel"
						placeholder='Telephone number' value="" name="txtTel" required>
					<span style="font-size: 11px; color: red" id="errorTxtTel"></span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="address" class="col-sm-3 control-label"><spring:message
						code="lbl.customer.addCustomerDialog.address" /> </label>
				<div class="col-sm-9">
					<textarea type="text" class="form-control" id="txtAddress"
						placeholder='Address' value="" name="txtAddress" required></textarea>
					<span style="font-size: 11px; color: red" id="errorTxtAddress"></span>
				</div>
			</div>
		</div>

		<div class="right" style="width: 50%; float: right">
			<div class="form-group">
				<label for="fax" class="col-sm-3 control-label"><spring:message
						code="lbl.customer.addCustomerDialog.fax" /></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtFax"
						placeholder='Fax' value="" name="txtFax" required> <span
						style="font-size: 11px; color: red" id="errorTxtFax"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="taxno" class="col-sm-3 control-label"><spring:message
						code="lbl.customer.addCustomerDialog.taxno" /></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtTaxNo"
						placeholder='Tax number' value="" name="txtTaxNo" required>
					<span style="font-size: 11px; color: red" id="errorTxtTaxNo"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="txtStatus" class="col-sm-3 control-label"><spring:message
						code="lbl.customer.lblForDdlStatus" /> </label>
				<div class="col-sm-9">
					<select id="txtStatus">
						<option value="Active"><spring:message
								code="lbl.customer.txtStatus.Active" /></option>
						<option value="In-Active"><spring:message
								code="lbl.customer.txtStatus.In-Active" /></option>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label for="remark" class="col-sm-3 control-label"><spring:message
						code="lbl.customer.addCustomerDialog.remark" /> </label>
				<div class="col-sm-9">
					<textarea type="text" class="form-control" id="txtRemark"
						placeholder='Remark' value="" name="txtRemark" required></textarea>
					<span style="font-size: 11px; color: red" id="errorTxtRemark"></span>
				</div>
			</div>
		</div>
		<div style="clear: both;"></div>
	</div>

	<div class="contactlist">
		<div class="form-group">
			<button id="btnAddNewCustomerContact" class="btn btn-info">
				<spring:message code="lbl.customer.btnAddNewCustomerContact" />
			</button>
			<h3>Contact List</h3>
			<table id="tblCustomerContactList"
				class="table table-hover table-bordered responsive"
				style="margin-top: 15px">
				<thead>
					<tr>
						<th class="th"><spring:message
								code="lbl.customer.listCustomerContact.row1" /></th>
						<th class="th"><spring:message
								code="lbl.customer.listCustomerContact.row2" /></th>
						<th class="th"><spring:message
								code="lbl.customer.listCustomerContact.row3" /></th>
						<th class="th"></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>

	<hr>
	<div class="brand">
		<div class="form-group">
			<button id="btnAddNewBrand" class="btn btn-info">
				<spring:message code="lbl.customer.btnAddNewBrand" />
			</button>
			<h3>Brand List</h3>
			<table id="tblBrandList"
				class="table table-hover table-bordered responsive"
				style="margin-top: 15px">
				<thead>
					<tr>
						<th class="th"><spring:message
								code="lbl.customer.listBrand.row1" /></th>
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
			<button id="btnAddNewCustomeraccountinformation" class="btn btn-info">
				<spring:message
					code="lbl.customer.addCustomerDialog.btnAddNewCustomeraccountinformation" />
			</button>
			<h3>Account Information List</h3>
			<table id="tblCustomeraccountinformationList"
				class="table table-hover table-bordered responsive"
				style="margin-top: 15px">
				<thead>
					<tr>
						<th class="th"><spring:message
								code="lbl.customer.addCustomerDialog.tblCustomeraccountinformationList.row1" /></th>
						<th class="th"><spring:message
								code="lbl.customer.addCustomerDialog.tblCustomeraccountinformationList.row2" /></th>
						<th class="th"><spring:message
								code="lbl.customer.addCustomerDialog.tblCustomeraccountinformationList.row3" /></th>
						<th class="th"><spring:message
								code="lbl.customer.addCustomerDialog.tblCustomeraccountinformationList.row4" /></th>
						<th class="th"><spring:message
								code="lbl.customer.addCustomerDialog.tblCustomeraccountinformationList.row5" /></th>
						<th class="th"></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
</div>

<!-- This is dialog to edit Customer -->
<div id="editCustomerDialog" style="display: none;"
	 name="editCustomerDialog">
	<div class="inputarea">
		<div class="left" style="width: 50%; float: left">
			<div class="form-group">
				<label for="customercode" class="col-sm-3 control-label"><spring:message
						code="lbl.customer.addCustomerDialog.customercode" /><span
					style="color: red;">*</span></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtCustomerCode"
						placeholder='Customer' value="" name="txtCustomerCode"
						required readonly> <span style="font-size: 11px; color: red"
						id="errorTxtCustomerCode"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="shortname" class="col-sm-3 control-label"><spring:message
						code="lbl.customer.addCustomerDialog.shortname" /><span
					style="color: red;">*</span> </label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtShortName"
						placeholder='Full name' value="" name="txtShortName" required>
					<span style="font-size: 11px; color: red" id="errorTxtShortName"></span>
				</div>
			</div>

	<!--		<div class="form-group">
				<label for="longname" class="col-sm-3 control-label"><spring:message
						code="lbl.customer.addCustomerDialog.longname" /></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtLongName"
						placeholder='Long name' value="" name="txtLongName" required>
					<span style="font-size: 11px; color: red" id="errorTxtLongName"></span>
				</div>
			</div>			-->
			<div class="form-group">
				<label for="tel" class="col-sm-3 control-label"><spring:message
						code="lbl.customer.addCustomerDialog.tel" /></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtTel"
						placeholder='Telephone number' value="" name="txtTel" required>
					<span style="font-size: 11px; color: red" id="errorTxtTel"></span>
				</div>
			</div>
			
			<div class="form-group">
				<label for="address" class="col-sm-3 control-label"><spring:message
						code="lbl.customer.addCustomerDialog.address" /> </label>
				<div class="col-sm-9">
					<textarea type="text" class="form-control" id="txtAddress"
						placeholder='Address' value="" name="txtAddress" required></textarea>
					<span style="font-size: 11px; color: red" id="errorTxtAddress"></span>
				</div>
			</div>
		</div>
		<div class="right" style="width: 50%; float: right">
			<div class="form-group">
				<label for="fax" class="col-sm-3 control-label"><spring:message
						code="lbl.customer.addCustomerDialog.fax" /></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtFax"
						placeholder='Fax' value="" name="txtFax" required> <span
						style="font-size: 11px; color: red" id="errorTxtFax"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="taxno" class="col-sm-3 control-label"><spring:message
						code="lbl.customer.addCustomerDialog.taxno" /></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtTaxNo"
						placeholder='Tax number' value="" name="txtTaxNo" required>
					<span style="font-size: 11px; color: red" id="errorTxtTaxNo"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="txtStatus" class="col-sm-3 control-label"><spring:message
						code="lbl.customer.lblForDdlStatus" /> </label>
				<div class="col-sm-9">
					<select id="txtStatus">
						<option value="Active"><spring:message
								code="lbl.customer.txtStatus.Active" /></option>
						<option value="In-Active"><spring:message
								code="lbl.customer.txtStatus.In-Active" /></option>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label for="remark" class="col-sm-3 control-label"><spring:message
						code="lbl.customer.addCustomerDialog.remark" /> </label>
				<div class="col-sm-9">
					<textarea type="text" class="form-control" id="txtRemark"
						placeholder='Remark' value="" name="txtRemark" required></textarea>
					<span style="font-size: 11px; color: red" id="errorTxtRemark"></span>
				</div>
			</div>
		</div>
		<div style="clear: both;"></div>
	</div>

	<div class="contactlist">
		<div class="form-group">
			<button id="btnAddNewCustomerContact" class="btn btn-info">
				<spring:message code="lbl.customer.btnAddNewCustomerContact" />
			</button>
			<h3>Contact List</h3>
			<table id="tblCustomerContactList"
				class="table table-hover table-bordered responsive"
				style="margin-top: 15px">
				<thead>
					<tr>
						<th class="th"><spring:message
								code="lbl.customer.listCustomerContact.row1" /></th>
						<th class="th"><spring:message
								code="lbl.customer.listCustomerContact.row2" /></th>
						<th class="th"><spring:message
								code="lbl.customer.listCustomerContact.row3" /></th>
						<th class="th"></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>

	<hr>
	<div class="brand">
		<div class="form-group">
			<button id="btnAddNewBrand" class="btn btn-info">
				<spring:message code="lbl.customer.btnAddNewBrand" />
			</button>
			<h3>Brand List</h3>
			<table id="tblBrandList"
				class="table table-hover table-bordered responsive"
				style="margin-top: 15px">
				<thead>
					<tr>
						<th class="th"><spring:message
								code="lbl.customer.listBrand.row1" /></th>
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
			<button id="btnAddNewCustomeraccountinformation" class="btn btn-info">
				<spring:message
					code="lbl.customer.addCustomerDialog.btnAddNewCustomeraccountinformation" />
			</button>
			<h3>Account Information List</h3>
			<table id="tblCustomeraccountinformationList"
				class="table table-hover table-bordered responsive"
				style="margin-top: 15px">
				<thead>
					<tr>
						<th class="th"><spring:message
								code="lbl.customer.addCustomerDialog.tblCustomeraccountinformationList.row1" /></th>
						<th class="th"><spring:message
								code="lbl.customer.addCustomerDialog.tblCustomeraccountinformationList.row2" /></th>
						<th class="th"><spring:message
								code="lbl.customer.addCustomerDialog.tblCustomeraccountinformationList.row3" /></th>
						<th class="th"><spring:message
								code="lbl.customer.addCustomerDialog.tblCustomeraccountinformationList.row4" /></th>
						<th class="th"><spring:message
								code="lbl.customer.addCustomerDialog.tblCustomeraccountinformationList.row5" /></th>
						<th class="th"></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
</div>
<!-- This is dialog confirm delete 
	<div id="deleteCustomerDialog" style="display: none;">
		<span id="messageContent"></span>
	</div>
	-->

<!-- This div is used to show message to user -->
<div id="messageDialog" style="display: none;">
	<span id="messageContent"></span>
</div>

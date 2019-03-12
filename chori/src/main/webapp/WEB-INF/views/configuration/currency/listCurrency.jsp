<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="resources/userJs/currency/currency.js"></script>

<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left"><spring:message code="lbl.currency.lblCurrencyTitle"/></div>
		</div>

		<div class="form-group" style="margin-left:20px;margin-top:20px">
	      	<div class="col-sm-9">
	      		<button class="btn btn-info" id="btnAddNewCurrency"><spring:message code="lbl.currency.btnAddNewCurrency"/></button>
     		</div>
		 </div>

		<div class="block-content collapse in">
			<div class="span12">
				<table class="table table-striped table-bordered display responsive"
					id="listCurrency">
					<thead>
						<tr>
							<th><spring:message code="lbl.currency.listCurrency.row1"/></th>
							<th><spring:message code="lbl.currency.listCurrency.row2"/></th>
							<th><spring:message code="lbl.currency.listCurrency.row3"/></th>
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

<!-- This div is used to show message to user -->
<div id="messageDialog" style="display: none;">
	<span id="messageContent"></span>
</div>

<!-- This is dialog confirm delete -->
<div id="deleteCurrencyDialog" style="display: none;">
	<span id="messageContent"></span>
</div>

<!-- This is dialog to add FabricSupplier -->
<div id="addCurrencyDialog" style="display: none;"
	class="" name="addCurrencyDialog">
	
	<div class="form-group">
		<label for="txtCurrencyCode" class="col-sm-3 control-label"><spring:message code="lbl.currency.addCurrencyDialog.txtCurrencyCode"/><span
			style="color: red;">*</span></label>
		<div class="col-sm-9">
			<input maxlength="20" type="text" class="form-control" id="txtCurrencyCode"
				placeholder='Currency Code' value=""
				name="txtCurrencyCode" required> <p
				style="font-size: 11px; color: red" id="errorTxtCurrencyCode"></p>
		</div>
	</div>

	<div class="form-group">
		<label for="txtDescription" class="col-sm-3 control-label"><spring:message code="lbl.currency.addCurrencyDialog.txtDescription"/>
		</label>
		<div class="col-sm-9">
			<input maxlength="50" type="text" class="form-control" id="txtDescription"
				placeholder='Description' value="" name="txtDescription" required>
			<p style="font-size: 11px; color: red" id="errorTxtDescription"></p>
		</div>
	</div>
</div>

<!-- This is dialog to add FabricSupplier -->
<div id="editCurrencyDialog" style="display: none;"
	class="" name="editCurrencyDialog">
	
	<div class="form-group">
		<label for="txtCurrencyCode" class="col-sm-3 control-label"><spring:message code="lbl.currency.addCurrencyDialog.txtCurrencyCode"/></label>
		<div class="col-sm-9">
			<input maxlength="20" type="text" class="form-control" id="txtCurrencyCode"
				placeholder='Currency Code' value=""
				name="txtCurrencyCode" required readonly> <p
				style="font-size: 11px; color: red" id="errorTxtCurrencyCode"></p>
		</div>
	</div>

	<div class="form-group">
		<label for="txtDescription" class="col-sm-3 control-label"><spring:message code="lbl.currency.addCurrencyDialog.txtDescription"/>
		</label>
		<div class="col-sm-9">
			<input maxlength="50" type="text" class="form-control" id="txtDescription"
				placeholder='Description' value="" name="txtDescription" required>
			<p style="font-size: 11px; color: red" id="errorTxtDescription"></p>
		</div>
	</div>
</div>
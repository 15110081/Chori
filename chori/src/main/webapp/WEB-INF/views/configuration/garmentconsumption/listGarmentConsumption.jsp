<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="resources/userJs/garmentstyle/dataTables.rowsGroup.js"></script>
<script src="resources/userJs/garmentconsumption/garmentconsumption.js"></script>

<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left"><spring:message code="lblGarmentConsumption.garmentConsumptionList"/></div>
		</div>

		<button id="btnAddNewGarmentConsumption" class="btn btn-info"
			style="margin-left: 20px; margin-top: 10px"><spring:message code="lblGarmentConsumption.addNewGarmentConsumption"/></button>
			
		<button id="btnAddNewGarmentConsumptionForCopy" class="btn btn-info"
			style="margin-left: 20px; margin-top: 10px"><spring:message code="lblGarmentConsumption.addNewGarmentConsumptionByGarmentStyle"/></button>
			
		<div class="block-content collapse in">
			<div class="span12">
			<div id="listGarmentConsumptionCover">

			</div>
			</div>
		</div>
	</div>
	<!-- /block -->
</div>
<div id="listGarmentConsumptionForMultiLanguage" style="display:none">
	<table class="table table-striped table-bordered display responsive"
		id="listGarmentConsumption">
		<thead>
			<tr>
				<!-- <th><spring:message code="lblGarmentConsumption.tbl.no"/></th> -->
				<th><spring:message code="lblGarmentConsumption.tbl.customer"/></th>
				<th><spring:message code="lblGarmentConsumption.tbl.garmentStyle"/></th>	
				<th><spring:message code="lblGarmentConsumption.tbl.kind"/></th>
				<th><spring:message code="lblGarmentConsumption.tbl.type"/></th>
				<th><spring:message code="lblGarmentConsumption.tbl.size"/></th>		
				<th><spring:message code="lblGarmentConsumption.tbl.description"/></th>
				<th><spring:message code="lblGarmentConsumption.tbl.widthConValue"/></th>
				<th></th>
				<!-- <th></th> -->
			</tr>
		</thead>
	</table>
</div>
<!-- This div is used to show message to user -->
<div id="messageDialog" style="display: none;">
	<span id="messageContent"></span>
</div>

<!-- This is dialog to add GarmentConsumption -->
<div id="addGarmentConsumptionDialog" style="display: none;" name="addGarmentConsumptionDialog">
	<div class="inputarea">
			<div class="form-group" style="display: none;">
				<label for="txtGarmentConsumptionCode" class="col-sm-3 control-label"><spring:message code="lblGarmentConsumption.code"/><span
					style="color: red;">*</span></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtGarmentConsumptionCode"
						placeholder='txtGarmentConsumptionCode' value=""
						name="txtGarmentConsumptionCode" required disabled>
				</div>
			</div>
			<div class="form-group">
				<label for="sltCustomer" class="col-sm-3 control-label"><spring:message code="lblGarmentConsumption.customer"/><span
					style="color: red;">*</span></label>
				<div class="col-sm-9">
					<select id="sltCustomer">
						<option value="-1" disabled selected><spring:message code="lblGarmentConsumption.pleaseChooseCustomer"/></option>
					</select>
					<p style="font-size: 11px; color: red" id="errorAddSltGarmentConsumption"></p>
				</div>
			</div>
			<div class="form-group">
				<label for="sltGarmentStyle" class="col-sm-3 control-label"><spring:message code="lblGarmentConsumption.garmentStyle"/><span
					style="color: red;">*</span></label>
				<div class="col-sm-9">
					<select id="sltGarmentStyle">
						<option value="-1" disabled selected><spring:message code="lblGarmentConsumption.pleaseChooseGarmentStyle"/></option>
					</select>				
				</div>
				<p style="font-size: 11px; color: red" id="errorAddSltSize"></p>
			</div>
			<div class="form-group">
				<label for="sltSize" class="col-sm-3 control-label"><spring:message code="lblGarmentConsumption.size"/><span
					style="color: red;">*</span></label>
				<div class="col-sm-9">
					<select id="sltSize">
						<option value="-1" disabled selected><spring:message code="lblGarmentConsumption.pleaseChooseSize"/></option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="txtDescription" class="col-sm-3 control-label"><spring:message code="lblGarmentConsumption.description"/></label>
				<div class="col-sm-9">
					<textarea class="form-control" id="txtDescription"
						placeholder='Description' name="txtDescription" rows="3"></textarea>
					<p style="font-size: 11px; color: red"
						id="errorTxtDescription"></p>
				</div>
			</div>
			<p style="font-weight: bold"><spring:message code="lblGarmentConsumption.widthConsumptionValue"/></p>
	</div>
	
	<div id="widthValueContainer">

	</div>
	<!-- 
	<div class="detaillist">
		<div class="form-group">
			<button id="btnAddNewGarmentConsumptionDetail" class="btn btn-info">New Detail</button>
			<table id="tblGarmentConsumptionDetailList"
				class="table table-hover table-bordered responsive"
				style="margin-top: 15px">
				<thead>
					<tr>
						<th class="th">Width</th>
						<th class="th">Con Value</th>
						<th class="th"></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
	 -->
</div>

<!-- This is dialog to edit GarmentConsumption -->
<div id="editGarmentConsumptionDialog" style="display: none;" name="editGarmentConsumptionDialog">
	<div class="inputarea">
			<div class="form-group" style="display: none;">
				<label for="txtGarmentConsumptionCode" class="col-sm-3 control-label"><spring:message code="lblGarmentConsumption.code"/><span
					style="color: red;">*</span></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtGarmentConsumptionCode"
						placeholder='txtGarmentConsumptionCode' value=""
						name="txtGarmentConsumptionCode" required disabled>
					<p style="font-size: 11px; color: red" id="errorTxtGarmentConsumptionCode"></p>
				</div>
			</div>
			<div class="form-group">
				<label for="sltCustomer" class="col-sm-3 control-label"><spring:message code="lblGarmentConsumption.customer"/><span
					style="color: red;">*</span></label>
				<div class="col-sm-9">
					<select id="sltCustomer">
						<option value=-1 disabled selected><spring:message code="lblGarmentConsumption.pleaseChooseCustomer"/></option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="sltGarmentStyle" class="col-sm-3 control-label"><spring:message code="lblGarmentConsumption.garmentStyle"/>
				<span
					style="color: red;">*</span></label>
				<div class="col-sm-9">
					<select id="sltGarmentStyle">
						<option value=-1 disabled selected><spring:message code="lblGarmentConsumption.pleaseChooseGarmentStyle"/></option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="sltSize" class="col-sm-3 control-label"><spring:message code="lblGarmentConsumption.size"/><span
					style="color: red;">*</span></label>
				<div class="col-sm-9">
					<select id="sltSize">
						<option value=-1 disabled selected><spring:message code="lblGarmentConsumption.pleaseChooseSize"/></option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="txtDescription" class="col-sm-3 control-label"><spring:message code="lblGarmentConsumption.description"/></label>
				<div class="col-sm-9">
					<textarea class="form-control" id="txtDescription"
						placeholder='Description' name="txtDescription" rows="3"></textarea>
					<p style="font-size: 11px; color: red"
						id="errorTxtDescription"></p>
				</div>
			</div>
			<p style="font-weight: bold"><spring:message code="lblGarmentConsumption.widthConsumptionValue"/></p>
	</div>
	
	<div id="widthValueEditContainer">	
	</div>
	<!-- 
	<div class="form-group">
		<button id="btnAddNewGarmentConsumptionDetail" class="btn btn-info">New Detail</button>
		<table id="tblGarmentConsumptionDetailList"
			class="table table-hover table-bordered responsive"
			style="margin-top: 15px">
			<thead>
				<tr>
					<th class="th">Width</th>
					<th class="th">Con Value</th>
					<th class="th"></th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
	-->
</div>

<!-- This is dialog to add GarmentConsumption -->
<div id="addGarmentConsumptionDialogForCopy" style="display: none;" name="addGarmentConsumptionDialogForCopy">
	<div class="inputarea">
			<div class="form-group" style="display: none;">
				<label for="txtGarmentConsumptionCodeForCopy" class="col-sm-3 control-label"><spring:message code="lblGarmentConsumption.code"/><span
					style="color: red;">*</span></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtGarmentConsumptionCodeForCopy"
						placeholder='txtGarmentConsumptionCode' value=""
						name="txtGarmentConsumptionCode" required disabled>
					<p style="font-size: 11px; color: red" id="errorTxtGarmentConsumptionCodeForCopy"></p>
				</div>
			</div>
			<div class="form-group">
				<label for="sltCustomerForCopy" class="col-sm-3 control-label"><spring:message code="lblGarmentConsumption.customer"/><span
					style="color: red;">*</span></label>
				<div class="col-sm-9">
					<select id="sltCustomerForCopy">
						<option value="-1" disabled selected><spring:message code="lblGarmentConsumption.pleaseChooseCustomer"/></option>
					</select>
					<p style="font-size: 11px; color: red" id="errorCopySltGarmentConsumption"></p>
				</div>
			</div>
			<div class="form-group">
				<label for="sltGarmentStyleForCopy" class="col-sm-3 control-label"><spring:message code="lblGarmentConsumption.garmentStyle"/><span
					style="color: red;">*</span></label>
				<div class="col-sm-9">
					<select id="sltGarmentStyleForCopy">
						<option value="-1" disabled selected><spring:message code="lblGarmentConsumption.pleaseChooseGarmentStyle"/></option>
					</select>
				</div>
			</div>
			<p style="font-weight: bold"><spring:message code="lblGarmentConsumption.widthConsumptionValue"/></p>
	</div>
	
	<div id="widthValueContainerForCopy">

	</div>
</div>

<!-- This is dialog confirm delete -->
<div id="deleteGarmentConsumptionDialog" style="display: none;">
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

<!-- MESSAGE DIALOG -->
<span id="SuccessTitle" style="display: none;"><spring:message code="DialogMessage.TitleSuccess"/></span>
<span id="ErrorTitle" style="display: none;"><spring:message code="DialogMessage.TitleError"/></span>

<div id="EditSuccessDialog" style="display: none;">
	<span id="EditSuccessContent"><spring:message code="DialogMessage.EditSuccess"/></span>
</div>
<div id="AddSuccessDialog" style="display: none;">
	<span id="AddSuccessContent"><spring:message code="DialogMessage.AddSuccess"/></span>
</div>
<div id="DeleteSuccessDialog" style="display: none;">
	<span id="DeleteSuccessContent"><spring:message code="DialogMessage.DeleteSuccess"/></span>
</div>
<div id="EditFailedDialog" style="display: none;">
	<span id="EditSuccessContent"><spring:message code="DialogMessage.EditFailed"/></span>
</div>
<div id="AddFailedDialog" style="display: none;">
	<span id="AddSuccessContent"><spring:message code="DialogMessage.AddFailed"/></span>
</div>
<div id="DeleteFailedDialog" style="display: none;">
	<span id="DeleteSuccessContent"><spring:message code="DialogMessage.DeleteFailed"/></span>
</div>
<div id="IsExistedDialog" style="display: none;">
	<span id="IsExistedContent"><spring:message code="DialogMessage.IsExisted"/></span>
</div>
<div id="DeleteQuestionDialog" style="display: none;">
	<span id="DeleteQuestionContent"><spring:message code="DialogMessage.DeleteQuestion"/></span>
</div>
<div id="CanNotGetDataDialog" style="display: none;">
	<span id="CanNotGetDatanContent"><spring:message code="DialogMessage.CanNotGetData"/></span>
</div>
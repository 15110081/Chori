<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="resources/userJs/accessory/accessory.js"></script>

<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">
				<spring:message code="label.accessory.accessoryForm" />
			</div>
		</div>

		<button id="btnAddNewAccessory" class="btn btn-info"
			style="margin-left: 20px; margin-top: 10px">
			<spring:message code="lbl.accessory.btnAddNewAccessory" />
		</button>

		<div class="form-group">
			<span style="margin-left: 20px; margin-top: 10px;"><spring:message
					code="lbl.accessory.lblForDdlStatus" /></span><select id="ddlStatus"
				style="margin-left: 20px; margin-top: 10px">
				<option value="selectRequest" selected disabled><spring:message
						code="lbl.accessory.ddlStatus.selectRequest" /></option>
				<option value="All"><spring:message
						code="lbl.accessory.ddlStatus.All" /></option>
				<option value="Active"><spring:message
						code="lbl.accessory.ddlStatus.Active" /></option>
				<option value="In-Active"><spring:message
						code="lbl.accessory.ddlStatus.In-Active" /></option>
			</select>
		</div>

		<div class="block-content collapse in">
			<div class="span12">
				<table class="table table-striped table-bordered display responsive"
					id="listAccessory">
					<thead>
						<tr>
							<th><spring:message code="lbl.accessory.listAccessory.row1" /></th>
							<th><spring:message code="lbl.accessory.listAccessory.row2" /></th>
							<th><spring:message code="lbl.accessory.listAccessory.row3" /></th>
							<th><spring:message code="lbl.accessory.listAccessory.row4" /></th>
							<th><spring:message code="lbl.accessory.listAccessory.row5" /></th>
							<th><spring:message code="lbl.accessory.listAccessory.row6" /></th>
							<th><spring:message code="lbl.accessory.listAccessory.row7" /></th>
							<th><spring:message code="lbl.accessory.listAccessory.row8" /></th>
							<th><spring:message code="lbl.accessory.listAccessory.row9" /></th>
							<th><spring:message code="lbl.accessory.listAccessory.row10" /></th>
							<th><spring:message code="lbl.accessory.listAccessory.row11" /></th>
							<th><spring:message code="lbl.accessory.listAccessory.row12" /></th>
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
<div id="deleteAccessoryDialog" style="display: none;">
	<span id="messageContent"></span>
</div>

<!-- This is dialog to add new Accessory -->
<div id="addAccessoryDialog" style="display: none;">
	<form:form method="POST" modelAttribute="accessoryAddModel"
		enctype="multipart/form-data" action="accessory/addNewAccessory">
		<div class="inputarea">
			<div class="left" style="width: 50%; float: left">
				<div class="form-group">
					<label for="accessorycode" class="col-sm-2 control-label"><spring:message
							code="lbl.accessory.addAccessoryDialog.accessorycode" /><span
						style="color: red;">*</span></label>
					<div class="col-sm-10">
						<form:input maxlength="100" type="text" class="form-control" id="accessorycode"
							path="accessorycode" placeholder='Accessory Code' value=""
							name="accessorycode" />
						<p style="font-size: 11px; color: red" id="errorAccessorycode"></p>
					</div>
				</div>
				
				<div class="form-group">
					<label for="accessorychoricode" class="col-sm-2 control-label"><spring:message
							code="lbl.accessory.addAccessoryDialog.accessorychoricode" /></label>
					<div class="col-sm-10">
						<form:input maxlength="50" type="text" class="form-control" id="accessorychoricode"
							path="accessorychoricode" placeholder='Chori Code' value=""
							name="accessorychoricode" />
						<p style="font-size: 11px; color: red" id="errorAccessoryChoriCode"></p>
					</div>
				</div>

				<div class="form-group">
					<label for="accessorygroupcode" class="col-sm-2 control-label"><spring:message
							code="lbl.accessory.addAccessoryDialog.accessorygroupcode" /></label>
					<div class="col-sm-10">
						<form:select class="form-control" id="accessorygroupcode"
							path="accessorygroupcode" name="accessorygroupcode" />
					</div>
				</div>

				<div class="form-group">
					<label for="name" class="col-sm-3 control-label"><spring:message
							code="lbl.accessory.addAccessoryDialog.name" /><span
						style="color: red;">*</span></label>
					<div class="col-sm-9">
						<form:input maxlength="50" type="text" class="form-control" id="name" path="name"
							placeholder='Name' value="" name="name" />
						<p style="font-size: 11px; color: red" id="errorName"></p>
					</div>
				</div>

				<div class="form-group">
					<label for="kind" class="col-sm-3 control-label"><spring:message
							code="lbl.accessory.addAccessoryDialog.kind" /></label>
					<div class="col-sm-9">
						<form:select class="form-control" id="kind" path="kind"
							name="kind" />
						<p style="font-size: 11px; color: red" id="errorKind"></p>
					</div>
				</div>

				<div class="form-group">
					<label for="dimension" class="col-sm-3 control-label"><spring:message
							code="lbl.accessory.addAccessoryDialog.dimension" /></label>
					<div class="col-sm-9">
						<form:input maxlength="100" type="text" class="form-control" id="dimension"
							path="dimension" placeholder='Dimension' value=""
							name="dimension" />
						<p style="font-size: 11px; color: red" id="errorDimension"></p>
					</div>
				</div>
			</div>
			<div class="right" style="width: 50%; float: right">
				<div class="form-group">
					<label for="mode" class="col-sm-3 control-label"><spring:message
							code="lbl.accessory.addAccessoryDialog.mode" /></label>
					<div class="col-sm-9">
						<form:select class="form-control" id="mode" path="mode"
							name="mode" />
						<p style="font-size: 11px; color: red" id="errorMode"></p>
					</div>
				</div>

				<div class="form-group">
					<label for="unitcode" class="col-sm-3 control-label"><spring:message
							code="lbl.accessory.addAccessoryDialog.unitcode" /></label>
					<div class="col-sm-9">
						<form:select class="form-control" id="unitcode" path="unitcode"
							name="unitcode" />
						<p style="font-size: 11px; color: red" id="errorUnit"></p>
					</div>
				</div>
<!-- Tuy là 11 nhưng cho dài 9 -->
				<div class="form-group">
					<label for="percontainer" class="col-sm-3 control-label"><spring:message
							code="lbl.accessory.addAccessoryDialog.percontainer" /></label>
					<div class="col-sm-9">
						<form:input maxlength="9" type="number" class="form-control quantity" id="percontainer"
							path="percontainer" placeholder='Per Group' value=""
							name="percontainer" min="0" max="999999999" />
						<p style="font-size: 11px; color: red" id="errorPercontainer"></p>
					</div>
				</div>

				<div class="form-group">
					<label for="containerUnitCode" class="col-sm-3 control-label"><spring:message
							code="lbl.accessory.addAccessoryDialog.containerUnitCode" /></label>
					<div class="col-sm-9">
						<form:select class="form-control" id="containerUnitCode"
							path="containerUnitCode" name="containerUnitCode" />
					</div>
				</div>

				<div class="form-group">
					<label for="colorcode" class="col-sm-3 control-label"><spring:message
							code="lbl.accessory.addAccessoryDialog.colorcode" /></label>
					<div class="col-sm-9">
						<form:select class="form-control" id="colorcode" path="colorcode"
							name="colorcode" />
						<p style="font-size: 11px; color: red" id="errorColor"></p>
					</div>
				</div>

				<div class="form-group">
					<label for="status" class="col-sm-3 control-label"><spring:message
							code="lbl.accessory.addAccessoryDialog.status" /></label>
					<div class="col-sm-9">
						<form:select class="form-control" id="status" path="status"
							name="status" />
						<p style="font-size: 11px; color: red" id="errorColor"></p>
					</div>
				</div>
			</div>
			<div style="clear: both;"></div>
		</div>
		<div class="form-group">
			<div class="col-sm-6">
				<form:input type="file" path="files[0].file" id=""
					class="form-control input-sm" accept="image/*" />
			</div>
			<div class="col-sm-6">
				<form:input type="file" path="files[1].file" id=""
					class="form-control input-sm" accept="image/*" />
			</div>

			<div class="col-sm-6">
				<form:input type="file" path="files[2].file" id=""
					class="form-control input-sm" accept="image/*" />
			</div>
			<div class="col-sm-6">
				<form:input type="file" path="files[3].file" id=""
					class="form-control input-sm" accept="image/*" />
			</div>
		</div>

		<br />
		<input type="submit"
			value="<spring:message code="lbl.accessory.addAccessoryDialog.btnSave"/>"
			class="btn btn-primary btn-sm" id="btnAdd">
	</form:form>
</div>

<!-- This is dialog to edit Accessory -->
<div id="editAccessoryDialog" style="display: none;">
	<form:form method="POST" modelAttribute="accessoryAddModel2"
		enctype="multipart/form-data" action="#" id="editAccessoryForm">
		<div class="inputarea">
			<div class="left" style="width: 50%; float: left">
				<div class="form-group">
					<label for="accessorycode" class="col-sm-2 control-label"><spring:message
							code="lbl.accessory.addAccessoryDialog.accessorycode" /><span
						style="color: red;">*</span></label>
					<div class="col-sm-10">
						<form:input type="text" class="form-control" id="accessorycode"
							path="accessorycode" placeholder='Accessory Code' value=""
							name="accessorycode" readonly="true" />
						<p style="font-size: 11px; color: red" id="errorAccessorycode"></p>
					</div>
				</div>
				
				<div class="form-group">
					<label for="accessorychoricode" class="col-sm-2 control-label"><spring:message
							code="lbl.accessory.addAccessoryDialog.accessorychoricode" /></label>
					<div class="col-sm-10">
						<form:input maxlength="50" type="text" class="form-control" id="accessorychoricode"
							path="accessorychoricode" placeholder='Chori Code' value=""
							name="accessorychoricode" />
						<p style="font-size: 11px; color: red" id="errorAccessoryChoriCode"></p>
					</div>
				</div>

				<div class="form-group">
					<label for="accessorygroupcode" class="col-sm-2 control-label"><spring:message
							code="lbl.accessory.addAccessoryDialog.accessorygroupcode" /></label>
					<div class="col-sm-10">
						<form:select class="form-control" id="accessorygroupcode"
							path="accessorygroupcode" name="accessorygroupcode" />
					</div>
				</div>

				<div class="form-group">
					<label for="name" class="col-sm-3 control-label"><spring:message
							code="lbl.accessory.addAccessoryDialog.name" /><span
						style="color: red;">*</span></label>
					<div class="col-sm-9">
						<form:input maxlength="50" type="text" class="form-control" id="name" path="name"
							placeholder='Name' value="" name="name" />
						<p style="font-size: 11px; color: red" id="errorName"></p>
					</div>
				</div>

				<div class="form-group">
					<label for="kind" class="col-sm-3 control-label"><spring:message
							code="lbl.accessory.addAccessoryDialog.kind" /></label>
					<div class="col-sm-9">
						<form:select class="form-control" id="kind" path="kind"
							name="kind" />
						<p style="font-size: 11px; color: red" id="errorKind"></p>
					</div>
				</div>

				<div class="form-group">
					<label for="dimension" class="col-sm-3 control-label"><spring:message
							code="lbl.accessory.addAccessoryDialog.dimension" /></label>
					<div class="col-sm-9">
						<form:input maxlength="100" type="text" class="form-control" id="dimension"
							path="dimension" placeholder='Dimension' value=""
							name="dimension" />
						<p style="font-size: 11px; color: red" id="errorDimension"></p>
					</div>
				</div>
			</div>
			<div class="right" style="width: 50%; float: right">
				<div class="form-group">
					<label for="mode" class="col-sm-3 control-label"><spring:message
							code="lbl.accessory.addAccessoryDialog.mode" /></label>
					<div class="col-sm-9">
						<form:select class="form-control" id="mode" path="mode"
							name="mode" />
						<p style="font-size: 11px; color: red" id="errorMode"></p>
					</div>
				</div>

				<div class="form-group">
					<label for="unitcode" class="col-sm-3 control-label"><spring:message
							code="lbl.accessory.addAccessoryDialog.unitcode" /></label>
					<div class="col-sm-9">
						<form:select class="form-control" id="unitcode" path="unitcode"
							name="unitcode" />
						<p style="font-size: 11px; color: red" id="errorUnit"></p>
					</div>
				</div>

				<div class="form-group">
					<label for="percontainer" class="col-sm-3 control-label"><spring:message
							code="lbl.accessory.addAccessoryDialog.percontainer" /></label>
					<div class="col-sm-9">
						<form:input maxlength="9" type="number" class="form-control quantity" id="percontainer"
							path="percontainer" placeholder='Per Group' value=""
							name="percontainer" min="0" max="999999999" />
						<p style="font-size: 11px; color: red" id="errorPercontainer"></p>
					</div>
				</div>

				<div class="form-group">
					<label for="containerUnitCode" class="col-sm-3 control-label"><spring:message
							code="lbl.accessory.addAccessoryDialog.containerUnitCode" /></label>
					<div class="col-sm-9">
						<form:select class="form-control" id="containerUnitCode"
							path="containerUnitCode" name="containerUnitCode" />
					</div>
				</div>

				<div class="form-group">
					<label for="colorcode" class="col-sm-3 control-label"><spring:message
							code="lbl.accessory.addAccessoryDialog.colorcode" /></label>
					<div class="col-sm-9">
						<form:select class="form-control" id="colorcode" path="colorcode"
							name="colorcode" />
						<p style="font-size: 11px; color: red" id="errorColor"></p>
					</div>
				</div>

				<div class="form-group">
					<label for="status" class="col-sm-3 control-label"><spring:message
							code="lbl.accessory.addAccessoryDialog.status" /></label>
					<div class="col-sm-9">
						<form:select class="form-control" id="status" path="status"
							name="status" />
						<p style="font-size: 11px; color: red" id="errorColor"></p>
					</div>
				</div>
			</div>
			<div style="clear: both;"></div>
		</div>

		<!-- This div to display 2 images -->
		<div class="form-group" id="2images"></div>

		<div class="form-group">
			<div class="col-sm-6">
				<form:input type="file" path="files[0].file" id=""
					class="form-control input-sm" accept="image/*" />
			</div>
			<div class="col-sm-6">
				<form:input type="file" path="files[1].file" id=""
					class="form-control input-sm" accept="image/*" />
			</div>

			<div class="col-sm-6">
				<form:input type="file" path="files[2].file" id=""
					class="form-control input-sm" accept="image/*" />
			</div>
			<div class="col-sm-6">
				<form:input type="file" path="files[3].file" id=""
					class="form-control input-sm" accept="image/*" />
			</div>
		</div>
		<br />
		<input type="submit"
			value="<spring:message code="lbl.accessory.addAccessoryDialog.btnSave"/>"
			class="btn btn-primary btn-sm" id="btnEditAcc">
	</form:form>
</div>

<!-- This div is used to show image fullsize to user -->
<div id="deleteImageDialog" style="display: none;">
	<span id="messageContent"></span>
</div>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="resources/userJs/garmentstyle/garmentstyle.js"></script>
<script src="resources/userJs/garmentstyle/dataTables.rowsGroup.js"></script>

<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left"><spring:message code="lbl.garmentStyle.lblGarmentStyleTitle"/></div>
		</div>

		<button id="btnAddNewGarmentstyle" class="btn btn-info"
			style="margin-left: 20px; margin-top: 10px"><spring:message code="lbl.garmentStyle.btnAddNewGarmentstyle"/></button>
			
		<div class="form-group">
			<span style="margin-left: 20px; margin-top: 10px;"><spring:message code="lbl.garmentStyle.ddlGlobalCustomer"/></span> <select
				id="ddlGlobalCustomer" style="margin-left: 20px; margin-top: 10px">
				<!-- <option value="All">All</option>
				<option value="Active">Active</option>
				<option value="In-Active">In-Active</option> -->
			</select>
		</div>
		
		<div class="form-group">
			<span style="margin-left: 20px; margin-top: 10px;"><spring:message code="lbl.garmentStyle.ddlGlobalFactory"/></span> <select
				id="ddlGlobalFactory" style="margin-left: 33px; margin-top: 10px">
				<!-- <option value="All">All</option>
				<option value="Active">Active</option>
				<option value="In-Active">In-Active</option> -->
			</select>
		</div>

		<div class="block-content collapse in">
			<div class="span12">
				<table class="table table-striped table-bordered display responsive"
					id="listGarmentstyle">
					<thead>
						<tr>
							<th><spring:message code="lbl.garmentStyle.listGarmentstyle.row1"/></th>
							<th><spring:message code="lbl.garmentStyle.listGarmentstyle.row2"/></th>
							<th><spring:message code="lbl.garmentStyle.listGarmentstyle.row3"/></th>
							<th><spring:message code="lbl.garmentStyle.listGarmentstyle.row4"/></th>
							<th><spring:message code="lbl.garmentStyle.listGarmentstyle.row5"/></th>
							<th><spring:message code="lbl.garmentStyle.listGarmentstyle.row6"/></th>
							<th><spring:message code="lbl.garmentStyle.listGarmentstyle.row7"/></th>
							<th></th>
							<!-- <th></th>
							<th></th>
							<th></th> -->
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
<div id="deleteGarmentstyleDialog" style="display: none;">
	<span id="messageContent"></span>
</div>

<!-- This is dialog to add new Garmentstyle -->
<div id="addGarmentstyleDialog" style="display: none;"
	name="addGarmentstyleForm">
	<form:form method="POST" modelAttribute="garmentstyleModel1"
		enctype="multipart/form-data" action="garmentstyle/add" id="addGarmentstyleForm">

		<div class="left" style="width: 50%; float: left">

			<div class="form-group">
				<label for="ddlCustomer" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleDialog.ddlCustomer"/></label>
				<div class="col-sm-9">
					<form:select class="form-control" id="customercode"
						path="customercode" name="customercode" />
				</div>
			</div>

			<div class="form-group">
				<label for="ddlFactory" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleDialog.ddlFactory"/></label>
				<div class="col-sm-9">
					<form:select class="form-control" id="factorycode"
						path="factorycode" name="factorycode" />
				</div>
			</div>

			<div class="form-group">
				<label for="garmentstylecode" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleDialog.garmentstylecode"/><span style="color: red;">*</span>
				</label>
				<div class="col-sm-9">
					<form:input type="text" class="form-control" id="garmentstylecode"
						path="garmentstylecode" placeholder='Garment Name' value=""
						name="garmentstylecode" maxlength="50" />
					<p style="font-size: 11px; color: red" id="errorGarmentstylecode"></p>
				</div>
			</div>

			<div class="form-group">
				<label for="description" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleDialog.description"/></label>
				<div class="col-sm-9">
					<form:textarea type="text" class="form-control" id="description"
						path="description" placeholder='Description' value=""
						name="description" maxlength="500" />
					<p style="font-size: 11px; color: red" id="errorDescription"></p>
				</div>
			</div>

			<div class="form-group">
				<label for="kind" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleDialog.kind"/></label>
				<div class="col-sm-9">
					<form:select class="form-control" id="garmentkindcode"
						path="garmentkindcode" name="garmentkindcode" />
				</div>
			</div>

			<div class="form-group">
				<label for="sewingguide" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleDialog.sewingguide"/></label>
				<div class="col-sm-9">
					<form:textarea type="text" class="form-control" id="sewingguide"
						path="sewingguide" placeholder='Sewing Guide' value=""
						name="sewingguide" maxlength="500" />
					<p style="font-size: 11px; color: red" id="errorSewingguide"></p>
				</div>
			</div>

		</div>
		<div class="right" style="width: 50%; float: right">
			<div class="form-group">
				<label for="picture1" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleDialog.picture1"/></label>
				<div class="col-sm-9">
					<form:input type="file" path="files[0].file" id=""
						class="form-control input-sm" accept="image/*" />
				</div>
			</div>
			<div class="form-group">
				<label for="picture2" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleDialog.picture2"/></label>
				<div class="col-sm-9">
					<form:input type="file" path="files[1].file" id=""
						class="form-control input-sm" accept="image/*" />
				</div>
			</div>
			<div class="form-group">
				<label for="picture3" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleDialog.picture3"/></label>
				<div class="col-sm-9">
					<form:input type="file" path="files[2].file" id=""
						class="form-control input-sm" accept="image/*" />
				</div>
			</div>
			<div class="form-group">
				<label for="picture4" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleDialog.picture4"/></label>
				<div class="col-sm-9">
					<form:input type="file" path="files[3].file" id=""
						class="form-control input-sm" accept="image/*" />
				</div>
			</div>
			<div class="form-group">
				<label for="picture5" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleDialog.picture5"/></label>
				<div class="col-sm-9">
					<form:input type="file" path="files[4].file" id=""
						class="form-control input-sm" accept="image/*" />
				</div>
			</div>
			<div class="form-group">
				<label for="referPrice" class="col-sm-3 control-label"><input type="radio" name="pcsOrDozen" value="pcs" id="radPcs"> <spring:message code="lbl.garmentStyle.addGarmentstyleDialog.referPrice"/></label>
				<div class="col-sm-9">
					<form:input maxlength="12" type="number" class="form-control quantity" id="referprice"
						path="referprice" placeholder='Ref. Price' value=""
						name="referprice" min="0" max="999999999999" step="any" />
					<p style="font-size: 11px; color: red" id="errorReferPrice"></p>
				</div>
			</div>
			<div class="form-group">
				<label for="ddlPriceUnit" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleDialog.ddlPriceUnit"/></label>
				<div class="col-sm-9">
					<form:select path="currencycode" id="ddlPriceUnit"/>
				</div>
			</div>
<!--  -->	
			<input type="radio" name="pcsOrDozen" value="dozen" id="radDozen"> Price by dozen <br>		
			<div id="priceBySizeGroupCover"></div>

			<div class="form-group">
				<label for="packingguide" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleDialog.packingguide"/></label>
				<div class="col-sm-9">
					<form:textarea type="text" class="form-control" id="packingguide"
						path="packingguide" placeholder='Packing Guide' value=""
						name="packingguide" maxlength="500" />
					<p style="font-size: 11px; color: red" id="errorPackingguide"></p>
				</div>
			</div>

		</div>
		<div style="clear: both;"></div>
		<input type="submit" value="Save" class="btn btn-primary btn-sm"
				id="btnAdd">
	</form:form>
</div>

<!-- This is dialog to edit Garmentstyle -->
<div id="editGarmentstyleDialog" style="display: none;"
	name="editGarmentstyleDialog">
	<form:form method="POST" modelAttribute="garmentstyleModel2"
		enctype="multipart/form-data" action="garmentstyle/edit" id="editGarmentstyleForm">

		<div class="left" style="width: 50%; float: left">

			<div class="form-group">
				<label for="ddlCustomer" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleDialog.ddlCustomer"/></label>
				<div class="col-sm-9">
					<form:select class="form-control" id="customercode"
						path="customercode" name="customercode" />
				</div>
			</div>

			<div class="form-group">
				<label for="ddlFactory" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleDialog.ddlFactory"/></label>
				<div class="col-sm-9">
					<form:select class="form-control" id="factorycode"
						path="factorycode" name="factorycode" />
				</div>
			</div>

			<div class="form-group">
				<label for="garmentstylecode" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleDialog.garmentstylecode"/><span style="color: red;">*</span>
				</label>
				<div class="col-sm-9">
					<form:input style="display: none;" type="text" class="form-control" id="garmentstylecode"
						path="garmentstylecode" placeholder='Garment Name' value=""
						name="garmentstylecode" readonly="true" />
						<!--  -->
					<form:input type="text" class="form-control" id="displayName"
						path="displayName" placeholder='Garment Name' value=""
						name="displayName" readonly="true" />
					<p style="font-size: 11px; color: red" id="errorGarmentstylecode"></p>
				</div>
			</div>

			<div class="form-group">
				<label for="description" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleDialog.description"/></label>
				<div class="col-sm-9">
					<form:textarea type="text" class="form-control" id="description"
						path="description" placeholder='Description' value=""
						name="description" maxlength="500"/>
					<p style="font-size: 11px; color: red" id="errorDescription"></p>
				</div>
			</div>

			<div class="form-group">
				<label for="kind" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleDialog.kind"/></label>
				<div class="col-sm-9">
					<form:select class="form-control" id="garmentkindcode"
						path="garmentkindcode" name="garmentkindcode" />
				</div>
			</div>

			<div class="form-group">
				<label for="sewingguide" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleDialog.sewingguide"/></label>
				<div class="col-sm-9">
					<form:textarea type="text" class="form-control" id="sewingguide"
						path="sewingguide" placeholder='Sewing Guide' value=""
						name="sewingguide" maxlength="500" />
					<p style="font-size: 11px; color: red" id="errorSewingguide"></p>
				</div>
			</div>

		</div>
		<div class="right" style="width: 50%; float: right">
			<div class="form-group">
				<label for="picture1" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleDialog.picture1"/></label>
				<div class="col-sm-9">
					<form:input type="file" path="files[0].file" id=""
						class="form-control input-sm" accept="image/*" />
				</div>
			</div>
			<div class="form-group">
				<label for="picture2" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleDialog.picture2"/></label>
				<div class="col-sm-9">
					<form:input type="file" path="files[1].file" id=""
						class="form-control input-sm" accept="image/*" />
				</div>
			</div>
			<div class="form-group">
				<label for="picture3" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleDialog.picture3"/></label>
				<div class="col-sm-9">
					<form:input type="file" path="files[2].file" id=""
						class="form-control input-sm" accept="image/*" />
				</div>
			</div>
			<div class="form-group">
				<label for="picture4" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleDialog.picture4"/></label>
				<div class="col-sm-9">
					<form:input type="file" path="files[3].file" id=""
						class="form-control input-sm" accept="image/*" />
				</div>
			</div>
			<div class="form-group">
				<label for="picture5" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleDialog.picture5"/></label>
				<div class="col-sm-9">
					<form:input type="file" path="files[4].file" id=""
						class="form-control input-sm" accept="image/*" />
				</div>
			</div>
			<div class="form-group">
				<label for="referPrice" class="col-sm-3 control-label"><input type="radio" name="pcsOrDozen" value="pcs" id="radPcs"> <spring:message code="lbl.garmentStyle.addGarmentstyleDialog.referPrice"/></label>
				<div class="col-sm-9">
					<form:input maxlength="12" type="number" class="form-control quantity" id="referprice"
						path="referprice" placeholder='Ref. Price' value=""
						name="referprice" min="0" max="999999999999" step="any" />
					<p style="font-size: 11px; color: red" id="errorReferPrice"></p>
				</div>
			</div>
			<div class="form-group">
				<label for="ddlPriceUnit" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleDialog.ddlPriceUnit"/></label>
				<div class="col-sm-9">
					<form:select path="currencycode" id="ddlPriceUnit"/>
				</div>
			</div>
<!--  -->	
			<input type="radio" name="pcsOrDozen" value="dozen" id="radDozen"> Price by dozen <br>		
			<div id="priceBySizeGroupCover"></div>

			<div class="form-group">
				<label for="packingguide" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleDialog.packingguide"/></label>
				<div class="col-sm-9">
					<form:textarea type="text" class="form-control" id="packingguide"
						path="packingguide" placeholder='Packing Guide' value=""
						name="packingguide" maxlength="500" />
					<p style="font-size: 11px; color: red" id="errorPackingguide"></p>
				</div>
			</div>
		</div>
		<div style="clear: both;"></div>
		<!-- This div to display 2 images -->
			<div class="form-group" id="5images"></div>

			<input type="submit" value="Save" class="btn btn-primary btn-sm"
				id="btnEdit">
	</form:form>
</div>

<!-- This is copy garment style dialog -->
<div id="copyGarmentstyleDialog" style="display: none;">
	<div class="form-group">
		<label for="txtOldGarmentstyleCode" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.copyGarmentstyleDialog.txtOldGarmentstyleCode"/></label>
		<div class="col-sm-9">
			<input style="display: none;" type="text" class="form-control" id="txtOldGarmentstyleCode"
				value="" name="txtOldGarmentstyleCode" disabled="disabled">
			
			<input style="display: none;" type="text" class="form-control" id="customercode"
				value="" name="customercode" >
			
			<input type="text" class="form-control" id="displayName"
				value="" name="displayName" disabled="disabled">
		</div>
	</div>

	<div class="form-group">
		<label for="txtNewGarmentstyleCode" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.copyGarmentstyleDialog.txtNewGarmentstyleCode"/><span
			style="color: red;">*</span></label>
		<div class="col-sm-9">
			<input type="text" class="form-control" id="txtNewGarmentstyleCode"
				placeholder='Enter New Garment Style' value=""
				name="txtNewGarmentstyleCode" maxlength="50" required>
			<p style="font-size: 11px; color: red"
				id="errorTxtNewGarmentstyleCode"></p>
		</div>
	</div>
</div>


<!-- This is dialog to config accessory list for Garment style -->
<div id="configAccessoryDialog" style="display: none;"
	name="configAccessoryDialog">
	<div style="display: none;">
		<div class="left" style="width: 50%; float: left">
			<div class="form-group">
				<label for="ddlCustomer" class="col-sm-3 control-label"><spring:message
						code="lbl.garmentStyle.addGarmentstyleDialog.ddlCustomer" /></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="customercode"
						name="customercode" disabled />
				</div>
			</div>

			<div class="form-group">
				<label for="ddlFactory" class="col-sm-3 control-label"><spring:message
						code="lbl.garmentStyle.addGarmentstyleDialog.ddlFactory" /></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="factorycode"
						name="factorycode" disabled />
				</div>
			</div>

			<div class="form-group">
				<label for="garmentstylecode" class="col-sm-3 control-label"><spring:message
						code="lbl.garmentStyle.addGarmentstyleDialog.garmentstylecode" /></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="garmentstylecode"
						placeholder='Garment Name' value="" name="garmentstylecode"
						disabled />
				</div>
			</div>

			<div class="form-group">
				<label for="description" class="col-sm-3 control-label"><spring:message
						code="lbl.garmentStyle.addGarmentstyleDialog.description" /></label>
				<div class="col-sm-9">
					<textarea class="form-control" id="description"
						placeholder='Description' value="" name="description" disabled></textarea>
				</div>
			</div>

			<div class="form-group">
				<label for="kind" class="col-sm-3 control-label"><spring:message
						code="lbl.garmentStyle.addGarmentstyleDialog.kind" /></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="garmentkindcode"
						name="garmentkindcode" disabled />
				</div>
			</div>

			<div class="form-group">
				<label for="sewingguide" class="col-sm-3 control-label"><spring:message
						code="lbl.garmentStyle.addGarmentstyleDialog.sewingguide" /></label>
				<div class="col-sm-9">
					<textarea class="form-control" id="sewingguide" name="sewingguide"
						disabled></textarea>
				</div>
			</div>

		</div>
		<div class="right" style="width: 50%; float: right">
			<div class="form-group">
				<label for="picture1" class="col-sm-3 control-label"><spring:message
						code="lbl.garmentStyle.addGarmentstyleDialog.picture1" /></label>
				<div class="col-sm-9">
					<input type="file" id="" class="form-control input-sm"
						accept="image/*" disabled />
				</div>
			</div>
			<div class="form-group">
				<label for="picture2" class="col-sm-3 control-label"><spring:message
						code="lbl.garmentStyle.addGarmentstyleDialog.picture2" /></label>
				<div class="col-sm-9">
					<input type="file" id="" class="form-control input-sm"
						accept="image/*" disabled />
				</div>
			</div>
			<div class="form-group">
				<label for="picture3" class="col-sm-3 control-label"><spring:message
						code="lbl.garmentStyle.addGarmentstyleDialog.picture3" /></label>
				<div class="col-sm-9">
					<input type="file" id="" class="form-control input-sm"
						accept="image/*" disabled />
				</div>
			</div>
			<div class="form-group">
				<label for="picture4" class="col-sm-3 control-label"><spring:message
						code="lbl.garmentStyle.addGarmentstyleDialog.picture4" /></label>
				<div class="col-sm-9">
					<input type="file" id="" class="form-control input-sm"
						accept="image/*" disabled />
				</div>
			</div>
			<div class="form-group">
				<label for="picture5" class="col-sm-3 control-label"><spring:message
						code="lbl.garmentStyle.addGarmentstyleDialog.picture5" /></label>
				<div class="col-sm-9">
					<input type="file" id="" class="form-control input-sm"
						accept="image/*" disabled />
				</div>
			</div>
			<div class="form-group">
				<label for="referPrice" class="col-sm-3 control-label"><spring:message
						code="lbl.garmentStyle.addGarmentstyleDialog.referPrice" /></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="referprice"
						placeholder='Ref. Price' value="" name="referprice" disabled />
					<p style="font-size: 11px; color: red" id="errorReferPrice"></p>
				</div>
			</div>
			<div class="form-group">
				<label for="ddlPriceUnit" class="col-sm-3 control-label"><spring:message
						code="lbl.garmentStyle.addGarmentstyleDialog.ddlPriceUnit" /></label>
				<div class="col-sm-9">
					<select id="ddlPriceUnit" disabled></select>
				</div>
			</div>

			<div class="form-group">
				<label for="packingguide" class="col-sm-3 control-label"><spring:message
						code="lbl.garmentStyle.addGarmentstyleDialog.packingguide" /></label>
				<div class="col-sm-9">
					<textarea class="form-control" id="packingguide"
						name="packingguide" disabled></textarea>
				</div>
			</div>


		</div>
		<div style="clear: both;"></div>
		<!-- This div to display 2 images -->
		<div class="form-group" id="5images"></div>
	</div>

	<div style="width: 100%">
		<div class="form-group">
			<label for="accComeWithThisGarment" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.configAccessoryDialog.accComeWithThisGarment"/></label>
			<div class="col-sm-9">
			
			<input style="display: none;" type="text" class="form-control" id="garmentstylecode2"
						name="garmentstylecode" disabled />
			<input type="text" class="form-control" id="displayName"
						name="displayName" disabled />
			
			<!-- 
				<table class="table table-striped table-bordered display responsive"
					id="listAccessoryForGarment">
					<thead>
						<tr>
							<th><spring:message code="lbl.garmentStyle.configAccessoryDialog.listAccessoryForGarment.row1"/></th>
							<th><spring:message code="lbl.garmentStyle.configAccessoryDialog.listAccessoryForGarment.row2"/></th>
							<th><spring:message code="lbl.garmentStyle.configAccessoryDialog.listAccessoryForGarment.row3"/></th>
							<th></th>
						</tr>
					</thead>
				</table>
				 -->
			</div>
		</div>
	</div>

	<table class="table table-striped table-bordered display responsive"
		id="listAccessoryForGarment">
		<thead>
			<tr>
				<th><spring:message
						code="lbl.garmentStyle.configAccessoryDialog.listAccessoryForGarment.row1" /></th>
				<th><spring:message
						code="lbl.garmentStyle.configAccessoryDialog.listAccessoryForGarment.row2" /></th>
				<th><spring:message
						code="lbl.garmentStyle.configAccessoryDialog.listAccessoryForGarment.row3" /></th>
				<th></th>
			</tr>
		</thead>
	</table>

	<div style="width: 100%">
		<div class="form-group">
			<label for="accUsed4ThisGarment"><spring:message code="lbl.garmentStyle.configAccessoryDialog.accUsed4ThisGarment"/></label><br/>
			<spring:message code="lbl.garmentStyle.configAccessoryDialog.ddlAccessoryGroup"/><select id="ddlAccessoryGroup">
				</select>
				<div id="lastTable">
				<table class="table table-striped table-bordered display responsive"
					id="listGarmentStyleAccessoryDetailMain">
					<thead>
						<tr>
							<th><spring:message code="lbl.garmentStyle.configAccessoryDialog.listGarmentStyleAccessoryDetailMain.row1"/></th>
							<th><spring:message code="lbl.garmentStyle.configAccessoryDialog.listGarmentStyleAccessoryDetailMain.row2"/></th>
							<th><spring:message code="lbl.garmentStyle.configAccessoryDialog.listGarmentStyleAccessoryDetailMain.row3"/></th>
							<th><spring:message code="lbl.garmentStyle.configAccessoryDialog.listGarmentStyleAccessoryDetailMain.row4"/></th>
						</tr>
					</thead>
				</table>
				</div>
		</div>
	</div>
</div>


<div id="addGarmentstyleaccessorydetailDialog" style="display: none;"
	name="addGarmentstyleaccessorydetailDialog">
	<div class="form-group">
		<label for="garmentstylecode" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleaccessorydetailDialog.garmentstylecode"/></label>
		<div class="col-sm-9">
			<input style="display: none;" type="text" class="form-control" id="garmentstylecode"
				placeholder='garmentstyle code' value="" name="garmentstylecode"
				disabled />
			<input type="text" class="form-control" id="displayName"
				placeholder='Display Name' value="" name="displayName"
				disabled />
		</div>
	</div>

	<div class="form-group">
		<label for="accessoryCode" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleaccessorydetailDialog.accessoryCode"/></label>
		<div class="col-sm-9">
			<input type="text" class="form-control" id="accessoryName"
				placeholder='accessory Code' value="" name="accessoryName" disabled />
			<input type="hidden" class="form-control" id="accessoryCode"
				placeholder='accessory Code' value="" name="accessoryCode" />
			<input type="hidden" class="form-control" id="customerCode"
				placeholder='accessory Code' value="" name="customerCode" />
		</div>
	</div>

	<div class="form-group">
		<label for="ddlSize" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleaccessorydetailDialog.ddlSize"/></label>
		<div class="col-sm-9">
			<select id="ddlSize" name="ddlSize"></select>
		</div>
	</div>

	<div class="form-group">
		<label for="usedValue" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.addGarmentstyleaccessorydetailDialog.usedValue"/></label>
		<div class="col-sm-9">
			<input type="number" step="any" class="form-control" id="usedValue"
				placeholder='Used Value' value="" name="usedValue" />
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-9">
			<button id="btnAddNewGarmentstyleaccessorydetail"
				class="btn btn-info"><spring:message code="lbl.garmentStyle.addGarmentstyleaccessorydetailDialog.btnAddNewGarmentstyleaccessorydetail"/></button>
		</div>
	</div>

	<table class="table table-striped table-bordered display responsive"
		id="listGarmentstyleaccessorydetailAdd">
		<thead>
			<tr>
				<th><spring:message code="lbl.garmentStyle.addGarmentstyleaccessorydetailDialog.listGarmentstyleaccessorydetailAdd.row1"/></th>
				<th><spring:message code="lbl.garmentStyle.addGarmentstyleaccessorydetailDialog.listGarmentstyleaccessorydetailAdd.row2"/></th>
				<th><spring:message code="lbl.garmentStyle.addGarmentstyleaccessorydetailDialog.listGarmentstyleaccessorydetailAdd.row3"/></th>
				<th><spring:message code="lbl.garmentStyle.addGarmentstyleaccessorydetailDialog.listGarmentstyleaccessorydetailAdd.row4"/></th>
				<th></th>
				<!-- <th></th> -->
			</tr>
		</thead>
	</table>
</div>

<div id="editGarmentstyleaccessorydetailDialog" style="display: none;"
	name="editGarmentstyleaccessorydetailDialog">
	<input type="hidden" class="form-control" id="garmentstyleaccessorydetailcode"
				value="" name="garmentstyleaccessorydetailcode"/>
	<div class="form-group">
		<label for="newUsedValue" class="col-sm-3 control-label"><spring:message code="lbl.garmentStyle.editGarmentstyleaccessorydetailDialog.newUsedValue"/></label>
		<div class="col-sm-9">
			<input type="number" step="any" class="form-control" id="newUsedValue"
				placeholder='Used Value' value="" name="newUsedValue" />
		</div>
	</div>
</div>

<!-- This is dialog confirm delete -->
<div id="deleteGarmentstyleaccessorydetailDialog" style="display: none;">
	<span id="messageContent"></span>
</div>

<!-- This is dialog confirm delete image -->
<div id="deleteImageDialog" style="display: none;">
	<span id="messageContent"></span>
</div>
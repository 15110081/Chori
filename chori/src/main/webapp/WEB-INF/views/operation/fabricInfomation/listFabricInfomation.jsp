<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  
<script src="resources/userJs/garmentstyle/dataTables.rowsGroup.js"></script>

<script src="resources/userJs/operation/fabricInfomation/jquery.fileupload.js"></script>

<script src="resources/userJs/operation/fabricInfomation/fabricInfomation.js"></script>
    
<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">FABRIC INFOMATION</div>
		</div>

		<button id="btnCreateNewFabricInformation" class="btn btn-info"
			style="margin-left: 20px; margin-top: 10px">Create New Fabric Information</button>

		<div class="block-content collapse in">
			<div class="span12">
				<div id="FabricInformationTableCover">
					<table
						class="table table-striped table-bordered display responsive"
						id="listFabricInformation">
						<thead>
							<tr>
								<th>No</th>
								<th>Fabric Indent</th>
								<th>Customer</th>
								<th>Fabric Supplier</th>
								<th>Factory Name</th>
								<th>Est Delv Date</th>
								<th>Act Delv Date</th>
								<th>Fabric Invoice No</th>
								<th></th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- /block -->
</div>

<!-- This div is used to show message to user -->
<div id="messageDialog" style="display: none;">
	<span id="messageContent"></span>
</div>

<!-- This is dialog confirm delete Fabric Information -->
<div id="deleteFabricInformationDialog" style="display: none;">
	<span id="messageContent"></span>
</div>

<!-- This is dialog to add new fabric information -->
<div id="addFabricInformationDialog" style="display: none;">
	<form:form method="POST" modelAttribute="fabricinformationModel1"
		enctype="multipart/form-data" action="fabricinformation/addNew" class="form-horizontal" id="addFabricInformationForm">
		<div class="inputarea">
			<div class="left" style="width: 50%; float: left">
				<div class="form-group">
					<label for="ddlCustomer" class="col-sm-2 control-label">Customer</label>
					<div class="col-sm-10">
						<select id="ddlCustomer">
						</select>
					</div>
				</div>

				<div class="form-group">
					<label for="accessorygroupcode" class="col-sm-3 control-label">Fabric
						Supplier</label>
					<div class="col-sm-3">
						<select id="ddlFabricSupplier">
						</select>
					</div>
					<label for="accessorygroupcode" class="col-sm-3 control-label"><input type="checkbox" id="isChori"> is Chori</label>
					<div class="col-sm-3">
						<select id="ddlChoriAgent">
						</select>
					</div>
				</div>

				<div class="form-group">
					<label for="name" class="col-sm-3 control-label">Fabric Item</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="fabricItem"
							placeholder='Fabric Item' value="" name="fabricItem" maxlength="50" />
					</div>
				</div>

				<div class="form-group">
					<label for="kind" class="col-sm-3 control-label">Fabric Indent<span
					style="color: red;">*</span></label>
					<div class="col-sm-9">
						<form:input class="form-control" id="fabricno" path="fabricno"
							name="fabricno" placeholder='Fabric Indent' maxlength="50" />
						<span style="font-size: 11px; color: red" id="errorFabricno"></span>
					</div>
				</div>

				<div class="form-group">
					<label for="dimension" class="col-sm-3 control-label">Composition</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="component"
							placeholder='Composition' value=""
							name="component" maxlength="50" />
						<span style="font-size: 11px; color: red" id="errorComponent"></span>
					</div>
				</div>
				
				<div class="form-group">
					<label for="ddlFactory" class="col-sm-2 control-label">Factory Name</label>
					<div class="col-sm-10">
						<select id="ddlFactory">
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label for="ddlWidth" class="col-sm-2 control-label">Width</label>
					<div class="col-sm-10">
						<select id="ddlWidth">
						</select>
					</div>
				</div>
			</div>
			<div class="right" style="width: 50%; float: right">
				<div class="form-group">
					<label for="fabricInvoiceNo" class="col-sm-3 control-label">Fabric Invoice No</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="fabricInvoiceNo"
							placeholder='Fabric Invoice No' value=""
							name="fabricInvoiceNo" maxlength="50" />
						<span style="font-size: 11px; color: red" id="errorFabricInvoiceNo"></span>
					</div>
				</div>

				<div class="form-group">
					<label for="currency" class="col-sm-3 control-label">Currency</label>
					<div class="col-sm-9">
						<select id="ddlCurrency">
						</select>
					</div>
				</div>

				<div class="form-group">
					<label for="fabricImage" class="col-sm-3 control-label">Document File</label>
					<div class="col-sm-9">
						<input type="file" name="fabricImage" id="fabricImage"  /> 
						<span style="font-size: 11px; color: red" id="errorFabricImage"></span>
					</div>
				</div>

				<div class="form-group">
					<label for="remark" class="col-sm-3 control-label">Remark
						</label>
					<div class="col-sm-9">
						<textarea class="form-control" id="remark"
							name="remark" maxlength="500"></textarea>
					</div>
				</div>
			</div>
			<div style="clear: both;"></div>
			<br />
			<button id="btnCreateNewFabricInformationDetail" class="btn btn-info">Create New Fabric Color</button>
			<br />
			<br />
			
			<div id="FabricInformationDetailTableCover">
			<!-- 
				<table class="table table-striped table-bordered display responsive"
					id="listFabricInformationDetail">
					<thead>
						<tr>
							<th>Color</th>
							<th>Yard in B/L</th>
							<th>Unit Price</th>
							<th>Image</th>
							<th></th>
						</tr>
					</thead>
				</table> -->
			</div>

		</div>

		<div class="form-group">
			<label for="estDeliveredDate" class="col-sm-3 control-label">Est Delivered
				Date</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="estDeliveredDate"
							placeholder='Estimate Delivered Date' value=""
							name="estDeliveredDate" /> <span
					style="font-size: 11px; color: red" id="errorEstDeliveredDate"></span>
			</div>
		</div>
		
		<div class="form-group">
			<label for="actDeliveredDate" class="col-sm-3 control-label">Act Delivered
				Date</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="actDeliveredDate"
							placeholder='Actual Delivered Date' value=""
							name="actDeliveredDate" /> <span
					style="font-size: 11px; color: red" id="errorActDeliveredDate"></span>
			</div>
		</div>
		
		<div class="form-group">
			<label for="voucherReceivedDate" class="col-sm-3 control-label">Voucher Received Date</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="voucherReceivedDate"
							placeholder='Voucher Received Date' value=""
							name="voucherReceivedDate" /> <span
					style="font-size: 11px; color: red" id="errorVoucherReceivedDate"></span>
			</div>
		</div>
		
		<div class="form-group">
			<label for="voucherSentDate" class="col-sm-3 control-label">Voucher Sent Date</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="voucherSentDate"
							placeholder='Voucher Sent Date' value=""
							name="voucherSentDate" /> <span
					style="font-size: 11px; color: red" id="errorVoucherSentDate"></span>
			</div>
		</div>

		<br />
		<input type="submit" value="Save" class="btn btn-primary btn-sm"
			id="btnSaveFabricinformation">
	</form:form>
</div>

<!-- This is dialog to add new fabric information detail -->
<div id="addFabricInformationDetailDialog" style="display: none;">
	<form:form method="POST" modelAttribute="fabricinformationdetailModel1"
		enctype="multipart/form-data" action="fabricinformationDetail/addNew" id="addFabricInformationDetailForm">
				<div class="form-group">
					<label for="ddlColor" class="col-sm-2 control-label">Color</label>
					<div class="col-sm-10">
						<form:select id="ddlColor" path="colorcode">
						</form:select>
					</div>
				</div>

				<div class="form-group">
					<label for="yardInBL" class="col-sm-3 control-label">Yard in B/L</label>
					<div class="col-sm-9">
						<input type="number" class="form-control" id="yardInBL"
							placeholder='Yard in B/L' name="yardInBL" min="0" />
					</div>
				</div>

				<div class="form-group">
					<label for="unitPrice" class="col-sm-3 control-label">Unit Price</label>
					<div class="col-sm-9">
						<input type="number" class="form-control" id="unitPrice"
							placeholder='Unit Price' name="unitPrice" min="0" />
					</div>
				</div>

				<div class="form-group">
					<label for="fabricImageDetail" class="col-sm-3 control-label">Photo
						Upload</label>
					<div class="col-sm-9">
						<input type="file" name="fabricImageDetail" id="fabricImageDetail"  /> 
						<span style="font-size: 11px; color: red" id="errorFabricImageDetail"></span>
					</div>
				</div>			

		<input type="submit" value="Save" class="btn btn-primary btn-sm"
			id="btnSaveFabricinformationDetail">
	</form:form>
</div>

<!-- This is dialog confirm delete Fabric Information Detail add -->
<div id="deleteFabricInformationDetailDialog" style="display: none;">
	<span id="messageContent"></span>
</div>

<!-- end add part -->

<!-- This is dialog to edit fabric information -->
<div id="editFabricInformationDialog" style="display: none;">
	<form:form method="POST" modelAttribute="fabricinformationModel2"
		enctype="multipart/form-data" action="fabricinformation/editPost" class="form-horizontal" id="editFabricInformationForm">
		<div class="inputarea">
			<div class="left" style="width: 50%; float: left">
				<div class="form-group">
					<label for="ddlCustomer" class="col-sm-2 control-label">Customer</label>
					<div class="col-sm-10">
						<select id="ddlCustomer">
						</select>
					</div>
				</div>

				<div class="form-group">
					<label for="accessorygroupcode" class="col-sm-3 control-label">Fabric
						Supplier</label>
					<div class="col-sm-3">
						<select id="ddlFabricSupplier">
						</select>
					</div>
					<label for="accessorygroupcode" class="col-sm-3 control-label"><input type="checkbox" id="isChori"> is Chori</label>
					<div class="col-sm-3">
						<select id="ddlChoriAgent">
						</select>
					</div>
				</div>

				<div class="form-group">
					<label for="name" class="col-sm-3 control-label">Fabric Item</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="fabricItem"
							placeholder='Fabric Item' value="" name="fabricItem" maxlength="50" />
					</div>
				</div>

				<div class="form-group">
					<label for="kind" class="col-sm-3 control-label">Fabric Indent<span
					style="color: red;">*</span></label>
					<div class="col-sm-9">
						<form:input class="form-control" id="fabricno" path="fabricno"
							name="fabricno" placeholder='Fabric Indent' maxlength="50" readonly="true"/>
						<span style="font-size: 11px; color: red" id="errorFabricno"></span>
					</div>
				</div>

				<div class="form-group">
					<label for="dimension" class="col-sm-3 control-label">Composition</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="component"
							placeholder='Composition' value=""
							name="component" maxlength="50" />
						<span style="font-size: 11px; color: red" id="errorComponent"></span>
					</div>
				</div>
				
				<div class="form-group">
					<label for="ddlFactory" class="col-sm-2 control-label">Factory Name</label>
					<div class="col-sm-10">
						<select id="ddlFactory">
						</select>
					</div>
				</div>
				
				<div class="form-group">
					<label for="ddlWidth" class="col-sm-2 control-label">Width</label>
					<div class="col-sm-10">
						<select id="ddlWidth">
						</select>
					</div>
				</div>
			</div>
			<div class="right" style="width: 50%; float: right">
				<div class="form-group">
					<label for="fabricInvoiceNo" class="col-sm-3 control-label">Fabric Invoice No</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="fabricInvoiceNo"
							placeholder='Fabric Invoice No' value=""
							name="fabricInvoiceNo" maxlength="50" />
						<span style="font-size: 11px; color: red" id="errorFabricInvoiceNo"></span>
					</div>
				</div>

				<div class="form-group">
					<label for="currency" class="col-sm-3 control-label">Currency</label>
					<div class="col-sm-9">
						<select id="ddlCurrency">
						</select>
					</div>
				</div>

				<div class="form-group">
					<label for="fabricImage" class="col-sm-3 control-label">Document File</label>
					<div class="col-sm-9">
						<input type="file" name="fabricImageEdit" id="fabricImageEdit"  /> 
						<span style="font-size: 11px; color: red" id="errorFabricImageEdit"></span>
					</div>
				</div>

				<div class="form-group">
					<label for="remark" class="col-sm-3 control-label">Remark
						</label>
					<div class="col-sm-9">
						<textarea class="form-control" id="remark"
							name="remark" maxlength="500"></textarea>
					</div>
				</div>
				
				<div class="form-group" id="showFabricImageDiv">
				<!-- 
					<label for="remark" class="col-sm-3 control-label">Fabric Image
						</label>
					<div class="col-sm-9">
						
					</div>
					 -->
				</div>
			</div>
			<div style="clear: both;"></div>
			<br />
			<button id="btnCreateNewFabricInformationDetailEditVer" class="btn btn-info">Create New Fabric Color</button>
			<br />
			<br />
			
			<div id="FabricInformationDetailTableCoverEditVer">
			<!-- 
				<table class="table table-striped table-bordered display responsive"
					id="listFabricInformationDetailEditVer">
					<thead>
						<tr>
							<th>Color</th>
							<th>Yard in B/L</th>
							<th>Unit Price</th>
							<th>Image</th>
							<th></th>
						</tr>
					</thead>
				</table> -->
			</div>

		</div>

		<div class="form-group">
			<label for="estDeliveredDate" class="col-sm-3 control-label">Est Delivered
				Date</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="estDeliveredDateEditVer"
							placeholder='Estimate Delivered Date' value=""
							name="estDeliveredDate" /> <span
					style="font-size: 11px; color: red" id="errorEstDeliveredDate"></span>
			</div>
		</div>
		
		<div class="form-group">
			<label for="actDeliveredDate" class="col-sm-3 control-label">Act Delivered
				Date</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="actDeliveredDateEditVer"
							placeholder='Actual Delivered Date' value=""
							name="actDeliveredDate" /> <span
					style="font-size: 11px; color: red" id="errorActDeliveredDate"></span>
			</div>
		</div>
		
		<div class="form-group">
			<label for="voucherReceivedDate" class="col-sm-3 control-label">Voucher Received Date</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="voucherReceivedDateEditVer"
							placeholder='Voucher Received Date' value=""
							name="voucherReceivedDateEditVer" /> <span
					style="font-size: 11px; color: red" id="errorVoucherReceivedDate"></span>
			</div>
		</div>
		
		<div class="form-group">
			<label for="voucherSentDate" class="col-sm-3 control-label">Voucher Sent Date</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="voucherSentDateEditVer"
							placeholder='Voucher Sent Date' value=""
							name="voucherSentDateEditVer" /> <span
					style="font-size: 11px; color: red" id="errorVoucherSentDate"></span>
			</div>
		</div>

		<br />
		<input type="submit" value="Save" class="btn btn-primary btn-sm"
			id="btnSaveFabricinformationEditVer">
	</form:form>
</div>

<!-- This is dialog to add new fabric information detail -->
<div id="addFabricInformationDetailEditVerDialog" style="display: none;">
	<form:form method="POST" modelAttribute="fabricinformationdetailModel1"
		enctype="multipart/form-data" action="fabricinformationDetail/addNewEditVer" id="addFabricInformationDetailEditVerForm">
				<div class="form-group">
					<label for="ddlColor" class="col-sm-2 control-label">Color</label>
					<div class="col-sm-10">
						<form:select id="ddlColor" path="colorcode">
						</form:select>
					</div>
				</div>

				<div class="form-group">
					<label for="yardInBL" class="col-sm-3 control-label">Yard in B/L</label>
					<div class="col-sm-9">
						<input type="number" class="form-control" id="yardInBL"
							placeholder='Yard in B/L' name="yardInBL" min="0" />
					</div>
				</div>

				<div class="form-group">
					<label for="unitPrice" class="col-sm-3 control-label">Unit Price</label>
					<div class="col-sm-9">
						<input type="number" class="form-control" id="unitPrice"
							placeholder='Unit Price' name="unitPrice" min="0" />
					</div>
				</div>

				<div class="form-group">
					<label for="fabricImageDetailEditVer" class="col-sm-3 control-label">Photo
						Upload</label>
					<div class="col-sm-9">
						<input type="file" name="fabricImageDetailEditVer" id="fabricImageDetailEditVer"  /> 
						<span style="font-size: 11px; color: red" id="errorFabricImageDetail"></span>
					</div>
				</div>			

		<input type="submit" value="Save" class="btn btn-primary btn-sm"
			id="btnSaveFabricinformationDetailEditVer">
	</form:form>
</div>
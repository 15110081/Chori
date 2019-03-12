<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script
	src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/userJs/operation/orderinternalaccessory/listorderinternalaccessory.js"></script>
<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left"><spring:message code="lbl.orderinternalaccessory.lblTitle" /></div>
		</div>
		<button class="btn btn-primary" name="btnCreateOrInternalAcc"
					id="btnCreateOrInternalAcc" style="margin-left: 20px; margin-top: 10px" ><spring:message code="lbl.orderinternalaccessory.btnCreateNew" /></button>
		<div class="block-content collapse in">
			<div class="span12">
				<table class="table table-striped table-bordered display responsive"
					id="listOrderInternalAccessory">
					<thead>
						<tr>
							<th><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblNo" /></th>
							
							<th><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblOrderSheetNo" /></th>
							<th><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblAccessorySupplier" /></th>
							<th><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblFactoryName" /></th>
							<th><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblStatus" /></th>
							<th><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblAction" /></th>
						</tr>
					</thead>
				</table>
			</div>
		</div>

	</div>
	<!-- /block -->
</div>

<div id="dialogEditOrderInt" class="row-fluid" style="display: none;">
	<!-- block -->
	<div class="left" style="width: 50%; float: left">
		<div class="form-group form-inline" style="margin: 10px 0px 0px 20px">
			<label for="ddlAccSupplierCode" class="col-sm-3 control-label"><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblAccessorySupplier" /> : </label> <select id="ddlAccSupplierCode" disabled>
				<option value="none">None</option>
			</select>
		</div>
		</br>

		<div class="form-group form-inline" style="margin: 0px 0px 0px 20px">
			<label for="ddlFactoryCode" class="col-sm-3 control-label"><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblFactoryName" /> : </label> <select id="ddlFactoryCode" disabled>
				<option value="none">None</option>
			</select>
		</div>
		</br>

		<div class="form-group form-inline" style="margin: 0px 0px 0px 20px">
			<label for="lblInvoiceNumber" class="col-sm-3 control-label"><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblInvoiceNumber" /> : </label> <input type="text" class="form-control"
				id="txtInvoiceNumber" placeholder='' value=""
				name="txtInvoiceNumber">

		</div>
		</br>

		<div class="form-group form-inline" style="margin: 0px 0px 0px 20px">
			<label for="lblOrderSheetNo" class="col-sm-3 control-label"><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblOrderSheetNo" /> : </label> <input type="text" class="form-control"
				id="txtOrderSheetNo" placeholder='Order Sheet No' value=""
				name="txtOrderSheetNo" disabled>
		</div>
		</br>

		<div class="form-group form-inline" style="margin: 0px 0px 0px 20px">
			<label for="lblOrderAccessories" class="col-sm-3 control-label"><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblOrderAccessories" /> : </label> <input type="button" class="btn btn-primary"
				id="btnAdd" value="Add" name="btnAdd">
		</div>
		</br>
	</div>
	<div class="right" style="width: 50%; float: left">
		<div class="form-group form-inline" style="margin: 10px 0px 0px 20px">
			<label for="ddlStatus" class="col-sm-3 control-label"><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblStatus" />:
			</label> <select id="ddlStatus">
				<option value="Not-Ordered"><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblStatus.lblNotOrder" /></option>
				<option value="Ordered"><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblStatus.lblOrder" /></option>
				<option value="Delivered"><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblStatus.lblDeliver" /></option>
				<option value="Cancel"><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblStatus.lblCancel" /></option>
			</select>

		</div>
		</br>

		<div class="form-group form-inline" style="margin: 0px 0px 0px 20px">
			<label for="lblOrderDate" class="col-sm-3 control-label"><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblOrderDate" /> : </label> <input type="text" class="form-control" id="txtOrderDate"
				placeholder='' value="" name="txtOrderDate">

		</div>
		</br>

		<div class="form-group form-inline" style="margin: 0px 0px 0px 20px">
			<label for="lblEstDeliveryDate" class="col-sm-3 control-label"><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblEstDeliveryDate" /> : </label> <input type="text" class="form-control"
				id="txtEstDeliveryDate" placeholder='' value=""
				name="txtEstDeliveryDate">

		</div>
		</br>

		<div class="form-group form-inline" style="margin: 0px 0px 0px 20px">
			<label for="lblActualDeliveryDate" class="col-sm-3 control-label"><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblActualDeliveryDate" /> : </label> <input type="text" class="form-control"
				id="txtActualDeliveryDate" placeholder='' value=""
				name="txtActualDeliveryDate">

		</div>
		</br>

		<div class="form-group form-inline" style="margin: 0px 0px 0px 20px">
			<label for="areaRemark" class="col-sm-3 control-label"><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblRemark" />
				: </label>

			<textarea id="areaRemak" rows="5" cols="5"></textarea>

		</div>
		</br>
	</div>
	<div style="clear: both;"></div>
	<div class="block-content collapse in">
		<div id="OrderInternalAccessoryDetailTableCoverEditVer">
			
		</div>
	</div>
</div>

<!-- This div is used to show message to user -->
<div id="messageDialog" style="display: none;">
	<span id="messageContent"></span>
</div>

<!-- This div is used to show accessory list to user -->
<div id="dialogOrderInternalAccessoryDetailEditVer" style="display: none;"
	class="form-horizontal" name="">
	<div class="block-content collapse in">
		<div class="span12">
			<table class="table table-striped table-bordered display responsive"
				id="listAccessory">
				<thead>
					<tr>
						<th></th>
						
						<th><spring:message code="lbl.orderinternalaccessory.listaccessory.lblNo" /></th>
						<th><spring:message code="lbl.orderinternalaccessory.listaccessory.lblAccessoryName" /></th>
						<th><spring:message code="lbl.orderinternalaccessory.listaccessory.lblCode" /></th>
						<!-- <th></th> -->
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>

<div id="sendMailDialog" style="display: none;">
	<form method="post" action="sendmailForOrderInternalAccessory" enctype="multipart/form-data">
		<table style="border: none;">
			<tr>
				<td><spring:message code="lbl.orderinternalaccessory.dialogsendmail.lblEmailTo" />:</td>
				<td><input type="text" name="mailTo" placeholder="To" /></td>
			</tr>
			<tr>
				<td><spring:message code="lbl.orderinternalaccessory.dialogsendmail.lblCC" />:</td>
				<td><input type="text" name="CC" placeholder="CC" /></td>
			</tr>
			<tr>
				<td><spring:message code="lbl.orderinternalaccessory.dialogsendmail.lblSubject" />:</td>
				<td><input type="text" name="subject" placeholder="subject" /></td>
			</tr>
			<tr>
				<td><spring:message code="lbl.orderinternalaccessory.dialogsendmail.lblMessage" />:</td>
				<td><textarea cols="50" rows="10" name="message"
						placeholder="message"></textarea></td>
			</tr>
			<tr>
				<td><spring:message code="lbl.orderinternalaccessory.dialogsendmail.lblAttachFile" />:</td>
				<td><input type="file" name="attachFile" /></td>
			</tr>
			<tr>
				<td><input type="submit" class="btn btn-info" value="<spring:message code='lbl.orderinternalaccessory.dialogsendmail.btnSend' />" /></td>
			</tr>
		</table>
	</form>
</div>
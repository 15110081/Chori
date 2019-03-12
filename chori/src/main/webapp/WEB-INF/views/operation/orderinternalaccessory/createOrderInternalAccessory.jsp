<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--  
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.4/jquery.datetimepicker.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.4/build/jquery.datetimepicker.full.js"></script>
-->

<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>

<script
	src="${pageContext.request.contextPath}/resources/userJs/operation/orderinternalaccessory/createorderinternalaccessory.js"></script>




<h1 align="center"><spring:message code="lbl.orderinternalaccessory.lblTitleCreate" /></h1>
<div id="AddOrderInt" class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left"><spring:message code="lbl.orderinternalaccessory.lblTitleCreate" /></div>
		</div>

		<div class="left" style="width: 50%; float: left">
			<div class="form-group form-inline" style="margin: 10px 0px 0px 20px">
				<label for="ddlAccSupplierCode" class="col-sm-3 control-label"><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblAccessorySupplier" /> : </label>
			</div>
			<div class="col-sm-9">
				 <select id="ddlAccSupplierCode">
					
				</select>
			</div>
			

			<div class="form-group form-inline" style="margin: 0px 0px 0px 20px">
				<label for="ddlFactoryCode" class="col-sm-3 control-label"><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblFactoryName" /> : </label> 
			</div>
			<div class="col-sm-9">
				 <select id="ddlFactoryCode">
					
				</select>
			</div>
		

			<div class="form-group form-inline" style="margin: 0px 0px 0px 20px">
				<label for="lblInvoiceNumber" class="col-sm-3 control-label"><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblInvoiceNumber" /> : </label> 
			</div>
			<div class="col-sm-9">
				<input type="text" class="form-control"
					id="txtInvoiceNumber" placeholder='' value=""
					name="txtInvoiceNumber">
			</div>
		

			<div class="form-group form-inline" style="margin: 0px 0px 0px 20px">
				<label for="lblOrderSheetNo" class="col-sm-3 control-label"><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblOrderSheetNo" /> : </label> 
			</div>
			<div class="col-sm-9">
				<input type="text" class="form-control"
					id="txtOrderSheetNo" placeholder='Order Sheet No' value=""
					name="txtOrderSheetNo" readonly>
			</div>
			<div class="form-group form-inline" style="margin: 0px 0px 0px 20px">
				<label for="lblOrderAccessories" class="col-sm-3 control-label"><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblOrderAccessories" />  : </label> <input type="button" class="form-control"
					id="btnAdd" value="Add" name="btnAdd">
			</div>
		
			



		</div>

		<div class="right">
			<div class="form-group form-inline" style="margin: 10px 0px 0px 20px">
				<label for="ddlFactoryCode" class="col-sm-3 control-label"><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblStatus" />:
				</label> 
			</div>
			<div class="col-sm-9">
				 <select id="ddlStatus">
					<option value="Not-Ordered"><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblStatus.lblNotOrder" /></option>
					<option value="Ordered"><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblStatus.lblOrder" /></option>
					<option value="Delivered"><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblStatus.lblDeliver" /></option>
				</select>
			</div>
			<div class="form-group form-inline" style="margin: 0px 0px 0px 20px">
				<label for="lblOrderDate" class="col-sm-3 control-label"><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblOrderDate" /> : </label>
			</div>
			<div class="col-sm-9">
				  <input type="text" class="form-control" id="txtOrderDate"
					placeholder='' value="" name="txtOrderDate">
			</div>
			<div class="form-group form-inline" style="margin: 0px 0px 0px 20px">
				<label for="lblEstDeliveryDate" class="col-sm-3 control-label"><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblEstDeliveryDate" /> : </label> 
			</div>
			<div class="col-sm-9">
				 <input type="text" class="form-control"
					id="txtEstDeliveryDate" placeholder='' value=""
					name="txtEstDeliveryDate">
			</div>
			<div class="form-group form-inline" style="margin: 0px 0px 0px 20px">
				<label for="lblActualDeliveryDate" class="col-sm-3 control-label"><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblActualDeliveryDate" /> : </label> 
			</div>
			<div class="col-sm-9">
				 <input type="text" class="form-control"
					id="txtActualDeliveryDate" placeholder='' value=""
					name="txtActualDeliveryDate">
			</div>

			<div class="form-group form-inline" style="margin: 0px 0px 0px 20px">
				<label for="areaRemark" class="col-sm-3 control-label"><spring:message code="lbl.orderinternalaccessory.listorderinternalaccessory.lblRemark" />
					: <div>
				 		<textarea id="areaRemak" rows="5" cols="5"></textarea>
					</div>
				</label>
					
			</div>
			
		
		</div>
		<div class="block-content collapse in">
			<div class="span12">
				<div id="OrderInternalAccessoryDetailTableCover">
					
				</div>
			</div>
		</div>

	</div>
	<div class="form-group text-center ">
		<button type="button" class="btn btn-info glyphicon glyphicon-ok"
			id="btnSave">Save</button>
		<!-- Standard button -->
		<button type="button" class="btn glyphicon glyphicon-remove"
			id="btnCancel">Cancel</button>

	</div>
</div>
<!-- dialog show when edit  success-->
<div id="messageDialog" style="display: none;">
	<span id="messageContent"></span>
</div>
<!-- MESSAGE DIALOG -->
<span id="SuccessTitle" style="display: none;"><spring:message
		code="DialogMessage.TitleSuccess" /></span>
<span id="ErrorTitle" style="display: none;"><spring:message
		code="DialogMessage.TitleError" /></span>


<div id="AddSuccessDialog" style="display: none;">
	<span id="AddSuccessContent"><spring:message
			code="DialogMessage.AddSuccess" /></span>
</div>


<div id="AddFailedDialog" style="display: none;">
	<span id="AddSuccessContent"><spring:message
			code="DialogMessage.AddFailed" /></span>
</div>
</body>
</html>
<div id="dialogOrderInternalAccessoryDetail" style="display: none;"
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
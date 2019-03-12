<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script src="resources/userJs/estimatetime/list.js"></script>
<title>Order External Accessory</title>
</head>
<body>
<div class="col-sm-10" id="frmEstimatetime" align="center">
	<h1>Order External Accessory</h1>
		<form class="">
		
		<div class="form-group" >
				<label class="col-sm-2 control-label ">AccessorySuplier</label>
				<div class="col-sm-8">
					<select id=accessorysuplier></select>
				</div>
			</div>
			<div class="form-group" >
				<label class="col-sm-2 control-label ">Factory</label>
				<div class="col-sm-8">
					<select id=factory></select>
				</div>		
			</div>
			<div class="form-group" >
				<label class="col-sm-2 control-label ">Accessory Name</label>
				<div class="col-sm-8">
	<input type="text" class="form-control" id="txtAccessoryName">
					 <span style="font-size: 11px;color: red" id="errorTxtAccessoryName" class="help-block"></span>				
					 </div>
			</div>
			<div class="form-group" >
				<label class="col-sm-2 control-label ">Estimate Quantity</label>
				<div class="col-sm-8">
	<input type="text" class="form-control" id="txtAccessoryName">
					 <span style="font-size: 11px;color: red" id="errorTxtEstimateQuantity" class="help-block"></span>				
					 </div>
			</div>
			<div class="form-group" >
				<label class="col-sm-2 control-label ">Estimate Gross Quantity</label>
				<div class="col-sm-8">
	<input type="text" class="form-control" id="txtAccessoryName">
					 <span style="font-size: 11px;color: red" id="errorTxtEstimateGrossQuantity" class="help-block"></span>				
					 </div>
			</div>
			<br/>
			<div class="form-group" >
				<label class="col-sm-2 control-label ">Actual Deliveried Gross Quantity</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="txtPackingaccdelv">
					 <span style="font-size: 11px;color: red" id="errorTxtActualDeliveriedGrossQuantity" class="help-block"></span>
				</div>
			</div>
			<br/>
			<div class="form-group" >
				<label class="col-sm-2 control-label " >Manufacturing accessories delivered before</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="txtManuaccdelv">
					 <span style="font-size: 11px;color: red" id="errorTxtManuaccdelv" class="help-block"></span>
				</div>
			</div>
		<input type="hidden" id="txtEstimatetimeCode"/>
			
			
			
			<div class="form-group text-center ">
				<button type="button" class="btn btn-info glyphicon glyphicon-ok" id="btnSave">Save</button>
				<!-- Standard button -->
				<button type="button" class="btn glyphicon glyphicon-remove" 
				id="btnCancel" >Cacel</button>
				
			</div>
		</form>
	</div>
<!-- dialog show when edit  success-->
<div id="messageDialog" style="display: none;"><span id="messageContent"></span></div>	

</body>
</html>
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
<script src="${pageContext.request.contextPath}/resources/userJs/orderexternalaccessory/list.js"></script>
<title>Order External Accessory</title>
</head>
<body>
<div class="col-sm-10" id="frmOrderextacc" align="center" name="frmOrderextacc">
	<h1>Order Accessories</h1>
	<div class="block">
	<div class="navbar navbar-inner block-header">
		
<div class="left" style="float:left" >
	<div class="form-group form-inline" style="margin:10px 0px 0px 20px" align="left" >
				<label class="col-sm-2 control-label ">AccessorySuplier : </label>			
					<select id="txtAccessorysuplier">					
					</select>
	</div>
			<br/>
			<div class="form-group form-inline" style="margin:0px 0px 0px 20px" align="left">
				<label class="col-sm-2 control-label ">Order Sheet No : </label>
				
				<input type="text" id="txtOrdersheetno" disabled="disabled" border="0px"/>
					 
				</div> 
			<br/>
				<div class="form-group form-inline" style="margin:0px 0px 0px 20px" align="left">

				<label class="col-sm-2 control-label ">Factory:</label>
				
					<select id="txtFactory"></select>
						
			</div>
			
			<input type="hidden" id="txtSheetNo"/>		 
				<br/>
			<div class="form-group form-inline" style="margin:0px 0px 0px 20px" align="left">
				<label class="col-sm-2 control-label ">Accessory Name : </label>
				
	<input type="text" class="form-control" id="txtAccessoryName">
					 <span style="font-size: 11px;color: red" id="errorTxtAccessoryName" class="help-block"></span>				
					 </div>
			<br/>
			<div class="block-content collapse in">
			<div class="span12">
				<table class="table table-striped table-bordered display responsive"
					id="listAccessory">
					<thead>
						<tr>
							<th>No</th>
							<th>Code</th>
							<th>Name</th>
							<th>Kind</th>
							<th>Dimension</th>
							<th>Mode</th>
							<th>Unit</th>
							<th>Color</th>
							<th>Photo</th>
							<th></th>
							<!-- <th></th> -->
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<br/>
		<div class="form-group form-inline" style="margin:0px 0px 0px 20px" align="left">
			
				<label class="col-sm-2 control-label ">Estimate Quantity : </label>
	<input type="text" class="form-control" id="txtEstimateQuantity">
					 <span style="font-size: 11px;color: red" id="errorTxtEstimateQuantity" class="help-block"></span>				
					 </div>
					 <br/>
			
			<div class="form-group form-inline" style="margin:0px 0px 0px 20px" align="left">
				<label class="col-sm-2 control-label ">Estimate Gross Quantity : </label>
				
	<input type="text" class="form-control" id="txtEstimateGrossQuantity">
					 <span style="font-size: 11px;color: red" id="errorTxtEstimateGrossQuantity" class="help-block"></span>				
					
			</div>
			<br/>
			<div class="form-group form-inline" style="margin:0px 0px 0px 20px" align="left">
				<label class="col-sm-2 control-label ">Price : </label>
				<input type="text" id="txtPrice"/>
		
					 <label class="col-sm-2 control-label ">Currency : </label>
				 <label class="col-sm-2 control-label " id="lblCurrency"> </label>
				
				
			</div>
			<br/>
			
			
	</div>
		
	<div class="right" style="float:right">
	<div class="form-group form-inline" style="margin:10px 0px 0px 20px" align="left">
				<label class="col-sm-2 control-label ">Actual Deliveried Gross Quantity : </label>
				
					<input type="text" class="form-control" id="txtActualDeliveriedGrossQuantity">
					 <span style="font-size: 11px;color: red" id="errorTxtActualDeliveriedGrossQuantity" class="help-block"></span>
				
			</div>
	<br/>
			<div class="form-group form-inline" style="margin:0px 0px 0px 20px" align="left">
				<label class="col-sm-2 control-label " >Order Date</label>
					<input type="text" class="form-control" id="txtOrderDate" name="txtOrderDate">
					 <span style="font-size: 11px;color: red" id="errorTxtOrderDate" class="help-block"></span>
				
			</div>
			<br/>
		<div class="form-group form-inline" style="margin:0px 0px 0px 20px" align="left">
				<label class="col-sm-2 control-label " >Status</label>
				
					<select id="txtStatus">
					<option  selected="selected">Not Ordered</option>
					<option>Ordered</option>
					<option >Delivered</option>
					
					</select>
				
			</div>
			<br/>
		<div class="form-group form-inline" style="margin:0px 0px 0px 20px" align="left">

				<label class="col-sm-2 control-label " >Est Delivery Date : </label>
				
					<input type="text" class="form-control" id="txtEstDeliveryDate">
					 <span style="font-size: 11px;color: red" id="errorTxtEstDeliveryDate" class="help-block"></span>
		</div>
				<br/>
			<div class="form-group form-inline" style="margin:0px 0px 0px 20px" align="left">
				<label class="col-sm-2 control-label " >Actual Delivery Date : </label>
				
					<input type="text" class="form-control" id="txtActualDeliveryDate">
					 <span style="font-size: 11px;color: red" id="errorTxtActualDeliveryDate" class="help-block"></span>
				</div>
					<br/>
		<div class="form-group form-inline" style="margin:0px 0px 0px 20px" align="left">

				<label class="col-sm-2 control-label " >Remark : </label>
				
					<textarea rows="4" cols="5" class="form-control" id="txtRemark" ></textarea> 
					 <span style="font-size: 11px;color: red" id="errorTxtRemark" class="help-block"></span>
				
		</div>		
		<div class="form-group form-inline" style="margin:0px 0px 0px 20px" >
				<label class="col-sm-2 control-label " >Payment Status</label>
				
					<select id="txtPaymentStatus">
					<option  selected="selected">No</option>
					<option>Yes</option>
					
					
					</select>
				
			</div>
			<div class="form-group text-center " align="center">
				<button type="button" class="btn btn-info glyphicon glyphicon-ok" id="btnSave">Save</button>
				<!-- Standard button -->
				<button type="button" class="btn glyphicon glyphicon-remove" 
				id="btnCancel" >Cancel</button>
				
			</div>	
		
		
	</div>
	
	
	</div>		
			
			
	
			
				
	</div>
<!-- dialog show when edit  success-->
<div id="messageDialog" style="display: none;"><span id="messageContent"></span></div>	

</body>
</html>
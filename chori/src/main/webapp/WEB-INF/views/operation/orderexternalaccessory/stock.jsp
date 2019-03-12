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
<script
	src="${pageContext.request.contextPath}/resources/userJs/orderexternalaccessory/stock.js"></script>

</head>
<body>
	<div id="dialogShowStock2" class="form-horizontal"
		style="display: none;" name="dialogShowStock2">
		<div class="form-group" style="text-align: center;margin:20px auto 20px auto;">
			<div class="col-sm-9">
				<input type="button" class="form-control btn btn-info" id="btnAssign"
					value="ASSIGN FROM STOCK" name="btnAssign">

			</div>
		</div>
		<input type="hidden" id="txtLotNumber" name="txtLotNumber" /> <input
			type="hidden" id="txtAccessoryCode" name="txtAccessoryCode" />
		<div class="form-group" style="text-align: center;margin:20px auto 20px auto;">
			<div class="col-sm-9">
				<input type="button" class="form-control btn btn-info" id="btnNew"
					value="MAKE NEW ORDER" name="btnNew">
			</div>
		</div>
		<!-- <div class="form-group" style="margin-bottom:20px">
			<div class="col-sm-9">
				<input type="button" class="form-control btn btn-info" id="btnCancel"
					value="CANCEL" name="btnCancel">
			</div>
			 -->
	</div>

	<div id="dialogShowStock1" class="form-horizontal"
		style="display: none;" name="dialogShowStock1">

		<div class="form-group" style="text-align: center;margin:20px auto 20px auto;">
			<input type="button" class="form-control btn btn-info" id="btnNew"
				value="MAKE NEW ORDER" name="btnNew">
		</div>
		<input type="hidden" id="txtLotNumber" name="txtLotNumber" /> <input
			type="hidden" id="txtAccessoryCode" name="txtAccessoryCode" />
		<!--<div class="form-group" style="text-align: center;margin:20px auto 20px auto;">
			<input type="button" class="form-control btn btn-info" id="btnCancel"
				value="CANCEL" name="btnCancel">
		</div>-->
	</div>

	<div id="dialogOrderExtAccc" class="form-horizontal" style="display: none;" name="dialogOrderExtAccc">
				<div class="left" style="width: 60%; float: left">
					<div class="form-group form-inline"
						style="margin: 10px 0px 0px 20px" align="left">
						<label class="col-sm-2 control-label ">AccessorySuplier :
						</label> <select id="txtAccessorysuplier">
						</select>
					</div>
					<br />
					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px" align="left">
						<label class="col-sm-2 control-label ">Order Sheet No : </label> <input
							type="text" id="txtOrdersheetno" disabled="disabled" border="0px" />

					</div>
					<br />
					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px" align="left">

						<label class="col-sm-2 control-label ">Factory:</label> <select
							id="txtFactory"></select>

					</div>

					<input type="hidden" id="txtSheetNo" /> <br />
					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px" align="left">
						<label class="col-sm-2 control-label ">Accessory Name : </label> <input
							type="text" class="form-control" id="txtAccessoryName"> <span
							style="font-size: 11px; color: red" id="errorTxtAccessoryName"
							class="help-block"></span>
					</div>
					<br />
					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px" align="left">
						
							<table
								class="table table-striped table-bordered display responsive"
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
										
										<!-- <th></th> -->
									</tr>
								</thead>
							</table>
					
					</div>
					<br />
					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px" align="left">

						<label class="col-sm-2 control-label ">Estimate Quantity :
						</label> <input type="text" class="form-control" id="txtEstimateQuantity" name="txtEstimateQuantity"   maxlength="11" >
						<span style="font-size: 11px; color: red"
							id="errorTxtEstimateQuantity" class="help-block"></span>
					</div>
					<br />
					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px" align="left">

						<label class="col-sm-2 control-label ">Actual Assign Quantity :
						</label> <input type="text" class="form-control" id="txtActualAssignQuantity" name="txtActualAssignQuantity"   maxlength="11" >
						<span style="font-size: 11px; color: red"
							id="errorTxtActualAssignQuantity" class="help-block"></span>
					</div>
					<br />
					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px" align="left">

						<label class="col-sm-2 control-label ">Order Quantity :
						</label> <input type="text" class="form-control" id="txtOrderQuantity" name="txtOrderQuantity"   maxlength="11" >
						<span style="font-size: 11px; color: red"
							id="errorTxtOrderQuantity" class="help-block"></span>
					</div>
					<br />
					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px" align="left">
						<label class="col-sm-2 control-label ">Estimate Gross
							Quantity : </label> <input type="text" class="form-control"
							id="txtEstimateGrossQuantity" name="txtEstimateGrossQuantity"> <span
							style="font-size: 11px; color: red"
							id="errorTxtEstimateGrossQuantity" class="help-block"></span>

					</div>
					<br/>
					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px" align="left">
						<div>
						<label class="col-sm-2 control-label ">Price : </label>
						 <input	type="text" id="txtPrice" name="txtPrice" /> </div>
						 <label	class="col-sm-2 control-label ">Currency : </label> 
							<label class="col-sm-2 control-label " id="lblCurrency"> </label>


					</div>
					<br/>


				</div>

				<div class="right" style="width: 40%; float: right" >
					<div class="form-group form-inline"
						style="margin: 10px 0px 0px 20px" align="left">
						<label class="col-sm-2 control-label ">Actual Deliveried
							Gross Quantity : </label> <input type="text" class="form-control"
							id="txtActualDeliveriedGrossQuantity" name="txtActualDeliveriedGrossQuantity"> <span
							style="font-size: 11px; color: red"
							id="errorTxtActualDeliveriedGrossQuantity" class="help-block"></span>

					</div>
					<br />
					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px" align="left">
						<label class="col-sm-2 control-label ">Order Date</label> <input
							type="text" class="form-control" id="txtOrderDate"
							name="txtOrderDate"> <span
							style="font-size: 11px; color: red" id="errorTxtOrderDate"
							class="help-block"></span>

					</div>
					<br />
					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px" align="left">
						<label class="col-sm-2 control-label ">Status</label> <select
							id="txtStatus">
							<option selected="selected">Not Ordered</option>
							<option>Ordered</option>
							<option>Delivered</option>

						</select>

					</div>
					<br />
					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px" align="left">

						<label class="col-sm-2 control-label ">Est Delivery Date :
						</label> <input type="text" class="form-control" id="txtEstDeliveryDate">
						<span style="font-size: 11px; color: red"
							id="errorTxtEstDeliveryDate" class="help-block"></span>
					</div>
					<br />
					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px" align="left">
						<label class="col-sm-2 control-label ">Actual Delivery
							Date : </label> <input type="text" class="form-control"
							id="txtActualDeliveryDate"> <span
							style="font-size: 11px; color: red"
							id="errorTxtActualDeliveryDate" class="help-block"></span>
					</div>
					<br />
					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px" align="left">

						<label class="col-sm-2 control-label ">Remark : </label>

						<textarea rows="4" cols="5" class="form-control" id="txtRemark"></textarea>
						<span style="font-size: 11px; color: red" id="errorTxtRemark"
							class="help-block"></span>

					</div>
					<div class="form-group form-inline"
						style="margin: 0px 0px 0px 20px">
						<label class="col-sm-2 control-label ">Payment Status</label> <select
							id="txtPaymentStatus">
							<option selected="selected">No</option>
							<option>Yes</option>


						</select>

					</div>
   	
					<div class="form-group text-center " align="center">
						<button type="button" class="btn btn-info glyphicon glyphicon-ok"
							id="btnSendMail">Send Mail</button>
						<button type="button" class="btn btn-info glyphicon glyphicon-ok"
							id="btnSave">Save</button>
						<!-- Standard button -->
						<button type="button" class="btn glyphicon glyphicon-remove"
							id="btnCancelOrderExtAcc">Cancel</button>
					</div>
				</div>


	<div id="assignForm" name="assignForm" style="display: none;">
		<div class="form-group form-inline" style="margin: 10px 0px 0px 20px" align="left">
						<label class="col-sm-2 control-label ">Name :</label> 
						<label  id="lblAccessoryName"></label>
		</div>
		<br/>
		<div class="form-group form-inline" style="margin: 0px 0px 0px 20px" align="left">
						<label class="col-sm-2 control-label ">Unit :</label> 
						<label  id="lblUnit"></label>
		</div>
		<br/>
		<div class="form-group form-inline" style="margin: 0px 0px 0px 20px" align="left">
						<label class="col-sm-2 control-label ">Inventory quantity :</label> 
						<label  id="lblInventoryquantity"></label>
		</div>
		<br/>
		<div class="form-group form-inline" style="margin: 0px 0px 0px 20px" align="left">
						<label class="col-sm-2 control-label ">Available quantity :</label> 
						<label  id="lblAvailablequantity"></label>
		</div>
		<br/>
		<div class="form-group form-inline" style="margin: 0px 0px 0px 20px" align="left">
						<label class="col-sm-2 control-label ">Assign quantity :</label> 
						<input type="text" id="txtAssignQuantity" name="txtAssignQuantity"/>
						<button id="btnDetail" >Detail</button>
		</div>
		<br/>
		<div class="form-group form-inline" style="margin: 0px 0px 0px 20px" align="left">
			<button id="btnSave" name="btnSave">Save</button>
		</div>
		
		
	</div>


		</div>
		<!-- dialog show when edit  success-->
		<div id="messageDialog" style="display: none;">
			<span id="messageContent"></span>
		</div>
		
		
		
	<div id="dialogAssignFromStock" style="display: none;" class="form-horizontal">
		<div class="form-group" style="clear:both">
			<label class="col-sm-1 control-label">Accessory Name: &nbsp;</label>				
			<label class="col-sm-1 control-label" id="lblAssignFromStockLotNumber"></label>			
		</div>
		<br/>
		<!--  <div class="form-group" style="clear:both">
			<label class="col-sm-1 control-label">Unit: &nbsp;</label>		
			<label class="col-sm-1 control-label" id="lblAssignFromStockUnit"></label>		
		</div>
		<br/>
		<div class="form-group" style="clear:both">
			<label class="col-sm-1 control-label">Inventory Quantity: &nbsp;</label>		
			<label class="col-sm-1 control-label" id="lblAssignFromStockInventoryQty"></label>		
		</div>
		-->
		<br/>
		<div class="form-group" style="clear:both">
			<label class="col-sm-1 control-label">Actual Assign Qty: &nbsp;</label>		
			<label class="col-sm-1 control-label" id="lblAssignFromStockActualAssignQty"></label>		
		</div>
		<br/>
		<div class="form-group" style="clear:both">
			<label class="col-sm-1 control-label">Shortage Qty: &nbsp;</label>		
			<label class="col-sm-1 control-label" id="lblAssignFromStockShortageQty"></label>		
		</div>
		<br/>
		<div class="form-group" style="clear:both">
			<label class="col-sm-1 control-label">Available Stock Qty: &nbsp;</label>		
			<label class="col-sm-1 control-label" id="lblAssignFromStockAvailableStockQty"></label>		
		</div>
		<br/>
		<div class="form-group" style="clear:both">
			<label class="col-sm-1 control-label">Assign Quantity: &nbsp;</label>		
			<input type="number" step="any" class="form-control col-sm-1 " id="txtAssignFromStockAssignQty" value="" name="txtAssignFromStockAssignQty">		
		</div>
	</div>
	
	<div id="dialogMessageNotEnoughQtyFromStock" style="display: none;" class="form-horizontal">
		<div class="form-group" style="clear:both">
			<label class="col-sm-1 control-label">Shortage Qty is: &nbsp;</label>		
			<label class="col-sm-1 control-label" id="lblAssignFromStockShortageQty"></label>		
		</div>
		<br/>
		<div class="form-group" style="clear:both">
			<label class="control-label">Do you want to make new order?: &nbsp;</label>		
		</div>
	</div>
	
	
	<div id="sendMailDialog" style="display: none;">
	<form method="post" action="sendmailForOrderExternalAccessoryInStock" enctype="multipart/form-data">
		<table style="border: none;">
			<tr>
				<td>Email To:</td>
				<td><input type="text" name="mailTo" placeholder="To" /></td>
			</tr>
			<tr>
				<td>CC:</td>
				<td><input type="text" name="CC" placeholder="CC" /></td>
			</tr>
			<tr>
				<td>Subject:</td>
				<td><input type="text" name="subject" placeholder="subject" /></td>
			</tr>
			<tr>
				<td>Message:</td>
				<td><textarea cols="50" rows="10" name="message"
						placeholder="message"></textarea></td>
			</tr>
			<tr>
				<td>Attach file:</td>
				<td><input type="file" name="attachFile" /></td>
			</tr>
			<tr>
				<td><input type="submit" class="btn btn-info" value="Send" /></td>
				<td><input type="button" class="btn btn-info" value="Cancel" id="btnCancelSendMail" /></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>
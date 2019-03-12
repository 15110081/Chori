<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/userJs/orderexternalaccessory/list.js"></script>

<!-- 
  <script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
<input type="button" id="btnTest2">
<div id="page_content_dialog_form" title="Page Content" >
  <textarea class="tinymce" cols="80" id="content_text_area" name="[]" rows="40">some content here</textarea>
  <script type="text/javascript">
	tinyMCE.init({"selector":"textarea.tinymce","width": 450, "height": 125, "plugins":"link contextmenu","toolbar1":"link"});
  </script>
</div>
 -->
<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left" >Ordered Accessories List </div>
		</div>
		<div class="block-content collapse in">
			<div class="span12">
			
				<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered display responsive" id="listOrderExtAcc">
					<thead>
						<tr>
							<th>NO.</th>
							<th>OrderSheetNo</th>
							<th>Accessory Supplier</th>
							<th>Factory</th>
							<th>Accessory</th>
							<th>Status</th>
							<th>Action</th>
						</tr>
					</thead>
					
				</table>
			</div>
		</div>
	</div>
	<div id="dialogEditOrderExtAccc" class="form-horizontal" style="display: none;" name="dialogEditOrderExtAccc">
	<div class="block">
	<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">Order Accessories</div>
		</div>
<div class="left" style="float:left" >
	<div class="form-group form-inline" style="margin:10px 0px 0px 20px">
				<label class="col-sm-2 control-label ">AccessorySuplier : </label>
				
					<select id="txtAccessorysuplier" disabled="disabled">
					
					</select>
				
			</div>
			<br/>
			<div class="form-group form-inline" style="margin:0px 0px 0px 20px">
				<label class="col-sm-2 control-label ">Order Sheet No : </label>
				
				<input type="text" id="txtOrdersheetno" disabled="disabled" border="0px"/>
					 
				</div> 
			<br/>
				<div class="form-group form-inline" style="margin:0px 0px 0px 20px">

				<label class="col-sm-2 control-label ">Factory : </label>
				
					<select id="txtFactory" disabled="disabled"></select>
						
			</div>
			
			<input type="hidden" id="txtSheetNo"/>		 
				<br/>
			<div class="form-group form-inline" style="margin:0px 0px 0px 20px">
				<label class="col-sm-2 control-label ">Accessory Name : </label>
				
	<input type="text" class="form-control" id="txtAccessoryName" disabled="disabled">
					 <span style="font-size: 11px;color: red" id="errorTxtAccessoryName" disabled="disabled" class="help-block"></span>				
					 </div>
					 <input type="hidden" id="txtAccessoryCode" />
			<br/>
		<div class="form-group form-inline" style="margin:0px 0px 0px 20px">
			
				<label class="col-sm-2 control-label ">Estimate Quantity : </label>
	<input type="text" class="form-control" id="txtEstimateQuantity" disabled="disabled">
					 <span style="font-size: 11px;color: red" id="errorTxtEstimateQuantity" class="help-block"></span>				
					 </div>
					 <br/>
			
			<div class="form-group form-inline" style="margin:0px 0px 0px 20px">
				<label class="col-sm-2 control-label ">Estimate Gross Quantity : </label>
				
	<input type="text" class="form-control" id="txtEstimateGrossQuantity" disabled="disabled">
					 <span style="font-size: 11px;color: red" id="errorTxtEstimateGrossQuantity"  class="help-block"></span>				
					
			</div>
			<br/>
			<div class="form-group form-inline" style="margin:0px 0px 0px 20px">
				<label class="col-sm-2 control-label ">Price : </label>
				<label class="col-sm-2 control-label " id="lblPrice"> </label>
		
					 <label class="col-sm-2 control-label ">Currency : </label>
				 <label class="col-sm-2 control-label " id="lblCurrency"> </label>
				
				
			</div>
			<br/>
			
			<div class="form-group form-inline" style="margin:0px 0px 0px 20px">
			
				<label class="col-sm-2 control-label ">Order Quantity : </label>
	<input type="text" class="form-control" id="txtOrderQuantity" >
					 <span style="font-size: 11px;color: red" id="errorTxtOrderQuantity" class="help-block"></span>				
					 </div>
					 <br/>
					 
					 <div class="form-group form-inline" style="margin:0px 0px 0px 20px">
			
				<label class="col-sm-2 control-label ">Actual Assign Quantity : </label>
	<input type="text" class="form-control" id="txtActAssignQuantity" disabled="disabled">
					 <span style="font-size: 11px;color: red" id="errorTxtActAssignQuantity" class="help-block"></span>				
					 </div>
					 <br/>
			
			
	</div>
		
	<div class="right" style="float:right">
	<div class="form-group form-inline" style="margin:10px 0px 0px 20px">
				<label class="col-sm-2 control-label ">Actual Deliveried  Quantity : </label>
				
					<input type="text" class="form-control" id="txtActualDeliveriedQuantity" >
					 <span style="font-size: 11px;color: red" id="errorTxtActualDeliveriedQuantity" class="help-block"></span>
				
			</div>
	<br/>
			<div class="form-group form-inline" style="margin:0px 0px 0px 20px">
				<label class="col-sm-2 control-label " >Order Date : </label>
					<input type="text" class="form-control" id="txtOrderDate" name="txtOrderDate" >
					 <span style="font-size: 11px;color: red" id="errorTxtOrderDate" class="help-block"></span>
				
			</div>
			<br/>
		<div class="form-group form-inline" style="margin:0px 0px 0px 20px">
				<label class="col-sm-2 control-label " >Status : </label>
				
					<select id="txtStatus">
					<option  selected="selected">Not Ordered</option>
					<option>Ordered</option>
					<option >Delivered</option>
					<option>Cancel</option>
					
					</select>
				
			</div>
			<br/>
		<div class="form-group form-inline" style="margin:0px 0px 0px 20px">

				<label class="col-sm-2 control-label " >Est Delivery Date : </label>
				
					<input type="text" class="form-control" id="txtEstDeliveryDate" >
					 <span style="font-size: 11px;color: red" id="errorTxtEstDeliveryDate" class="help-block"></span>
		</div>
				<br/>
			<div class="form-group form-inline" style="margin:0px 0px 0px 20px">
				<label class="col-sm-2 control-label " >Actual Delivery Date : </label>
				
					<input type="text" class="form-control" id="txtActualDeliveryDate" >
					 <span style="font-size: 11px;color: red" id="errorTxtActualDeliveryDate" class="help-block"></span>
				</div>
					<br/>
		<div class="form-group form-inline" style="margin:0px 0px 0px 20px" >

				<label class="col-sm-2 control-label " >Remark : </label>
				
					<textarea rows="4" cols="5" class="form-control" id="txtRemark" ></textarea> 
					 <span style="font-size: 11px;color: red" id="errorTxtRemark" class="help-block"></span>
				
		</div>		
		
		
	</div>	
	</div>		
	    
	</div>
	
	<div id="sendMailDialog" style="display: none;">
	<form method="post" action="sendmailForOrderExternalAccessory" enctype="multipart/form-data">
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
				<td>
				<!-- 	<textarea id="id1" class="mceEditor" rows="10" cols="50" name="name1"></textarea>
					<input type="button" id="btnTest" value="Test">
				</td> -->
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
		
		<a href="<c:url value='/sendemail'/>" target="_blank">Go to Send Email Management Page</a>
	</form>
</div>
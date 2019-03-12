<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script
	src="resources/userJs/operation/pigriddetail/list.js"></script>
<title>PI Griddetail</title>
</head>
<body>
<div id="dialogShowPigrid" class="form-horizontal"
		 name="dialogShowPigrid">
		<div class="form-group" style="text-align: center;margin:20px auto 20px auto;">
			<div class="col-sm-9">
				<input type="button" class="form-control btn btn-info" id="btnEdit"
					value="Edit" name="btnEdit">

			</div>
		</div>
		<input type="hidden" id="txtPigrid" name="txtPigrid" /> 
		
		<!-- <div class="form-group" style="margin-bottom:20px">
			<div class="col-sm-9">
				<input type="button" class="form-control btn btn-info" id="btnCancel"
					value="CANCEL" name="btnCancel">
			</div>
			 -->
	</div>
	
	<div id="dialogShowPigrid2" class="form-horizontal"
		style="display: none;" name="dialogShowPigrid2">
		<button style="margin-left:20px;margin-top:10px" class="btn btn-info" name="btnAdd" id="btnAdd" >Add</button>

		<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered display responsive" id="listPigriddetail">
					<thead>
						<tr>
							<th>NO.</th>
							<th>Pigrid </th>
							<th>Size</th>
							<th>Garment Style</th>
							<th>Color</th>
							<th>PCS</th>
							<th>Barcode</th>
							<th>Action</th>
						</tr>
					</thead>
					
				</table>
	</div>


<div class="col-sm-10" id="dialogAddPigriddetail" style="display: none;" align="center" name="dialogAddPigriddetail">



<div class="form-group form-inline" style="margin:10px 0px 0px 20px" align="left" >
				<label class="col-sm-2 control-label ">Garment Style : </label>			
					<select id="txtGarmentstyle">					
					</select>
	</div>
			<br/>
			
			<div class="form-group form-inline" style="margin:10px 0px 0px 20px" align="left" >
				<label class="col-sm-2 control-label ">Color : </label>			
					<select id="txtColor">					
					</select>
	</div>
			<br/>
			
			<div class="form-group form-inline" style="margin:10px 0px 0px 20px" align="left" >
				<label class="col-sm-2 control-label ">Size : </label>			
					<select id="txtSize">					
					</select>
	</div>
			<br/>
			
			<div class="form-group form-inline" style="margin:0px 0px 0px 20px" align="left">
				<label class="col-sm-2 control-label ">PCS : </label>
				
				<input type="text" id="txtPcs" border="0px" maxlength="11" class="integer"/>
				</div> 
			<br/>
			
			<div class="form-group form-inline" style="margin:0px 0px 0px 20px" align="left">
				<label class="col-sm-2 control-label ">Barcode : </label>
				
				<input type="text" id="txtBarcode"  border="0px"/>
					 <p style="font-size: 11px; color: red" id="errorTxtBarcode"></p>
				</div> 
			<br/>
</div>


<div class="col-sm-10" id="dialogEditPigriddetail" align="center"  style="display: none;"name="dialogEditPigriddetail">



<div class="form-group form-inline" style="margin:10px 0px 0px 20px" align="left" >
				<label class="col-sm-2 control-label ">Garment Style : </label>			
					<select id="txtGarmentstyle">					
					</select>
	</div>
			<br/>
			
			<div class="form-group form-inline" style="margin:10px 0px 0px 20px" align="left" >
				<label class="col-sm-2 control-label ">Color : </label>			
					<select id="txtColor">					
					</select>
	</div>
			<br/>
			
			<div class="form-group form-inline" style="margin:10px 0px 0px 20px" align="left" >
				<label class="col-sm-2 control-label ">Size : </label>			
					<select id="txtSize">					
					</select>
	</div>
			<br/>
			
			<div class="form-group form-inline" style="margin:0px 0px 0px 20px" align="left">
				<label class="col-sm-2 control-label ">PCS : </label>
				
				<input type="text" id="txtPcs"  border="0px" maxlength="11" class="integer"/>
					 
				</div> 
			<br/>
			
			<div class="form-group form-inline" style="margin:0px 0px 0px 20px" align="left">
				<label class="col-sm-2 control-label ">Barcode : </label>
				
				<input type="text" id="txtBarcode" border="0px"/>
					 					 <p style="font-size: 11px; color: red" id="errorTxtBarcode"></p>
					 
				</div> 
			<br/>
		
			
</div>
<div id="messageDialog" style="display: none;"><span id="messageContent"></span></div>
<div id="dialogDeletePigriddetail" class="" style="display: none;" name="dialogDeletePigriddetail">
		<div class="form-group">
	      <span id="messageContent"></span>
	    </div>
	</div>
</body>
</html>
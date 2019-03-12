<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="resources/userJs/currencyexchange/list.js"></script>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>



<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left"><spring:message code="lbl.currencyexchange.lblTitle"/></div>
		</div>
		
		<button class="btn btn-info" style="margin-left:20px;margin-top:10px" name="btnAdd" id="btnAdd" ><spring:message code="lbl.currencyexchange.btnAddNew"/></button>
		
		<div class="form-group" style="margin-left: 20px;  margin-top: 15px;">
			<span style="margin-top: 10px;"><spring:message code="lbl.status"/></span> 
				<spring:message code="lbl.currencyexchange.lblFrom"/>
				<input placeholder="Date From" type="text" class="form-control" name="searchFrom" id="searchFrom">
				<spring:message code="lbl.currencyexchange.lblTo"/>
				<input placeholder="Date To" type="text" class="form-control" name="searchTo" id="searchTo">
				<button class="btn btn-info" name="btnSearch" style="margin-top: -15px;" id="btnSearch" value="Search"><spring:message code="lbl.currencyexchange.btnSearch"/></button>
		</div>		
						
		<div class="block-content collapse in">
			<div class="span12">			
				<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered display responsive" id="listCurrencyExchange">
					<thead>
						<tr>
							<th><spring:message code="lbl.currencyexchange.lblNo"/></th>
							<th><spring:message code="lbl.currencyexchange.lblFrom"/></th>
							<th><spring:message code="lbl.currencyexchange.lblTo"/></th>
							<th><spring:message code="lbl.currencyexchange.lblAmount"/></th>
							<th><spring:message code="lbl.currencyexchange.lblExchangeDate"/></th>
							<th><spring:message code="lbl.currencyexchange.lblAction"/></th>
						</tr>
					</thead>
					
				</table>
			</div>
		</div>
	</div>
	<!-- /block -->
</div>
	<!-- dialog when edit status-->
	<div id="dialogEditCurrencyExchange" class="form-horizontal" style="display: none;" name="editCurrencyExchangeForm">
		<input class="form-control" id="txtCurrencyExchangeCode" name="txtCurrencyExchangeCode" type="hidden">
		<div class="form-group">
	       <label for="txtCurrencyExchangeDate" class="col-sm-3 control-label"><spring:message code="lbl.currencyexchange.lblExchangeDate"/>
	       <span style="color: red;">*</span><span>&nbsp;</span></label>
	       <div class="col-sm-9">
	           <input class="form-control" id="txtCurrencyExchangeDate" name="txtCurrencyExchangeDate" disabled="disabled">
	           <span style="font-size: 11px; color: red" id="errorTxtCurrencyExchangeDate"></span>
	       </div>
	    </div></br>
	    
	     <div class="form-group">
	       <label for="txtFrom" class="col-sm-3 control-label"><spring:message code="lbl.currencyexchange.lblFrom"/>
	       <span style="color: red;">*</span></label>
	       
	       <div class="col-sm-9">
	           <select id="txtFrom"></select>
	       </div>
	    </div></br>
	    
	    <div class="form-group">
	       <label for="txtTo" class="col-sm-3 control-label"><spring:message code="lbl.currencyexchange.lblTo"/>
	       <span style="color: red;">*</span></label>
	       <div class="col-sm-9">
	           <select id="txtTo"></select>
	       </div></br>
	    </div>
	    <div class="form-group">
	       <label for=txtAmount class="col-sm-3 control-label"><spring:message code="lbl.currencyexchange.lblAmount"/><span style="color: red;">*</span><span>&nbsp;</span></label>
	       <div class="col-sm-9">
	            <input type="number" step="any" min="0"  class="form-control" id="txtAmount" value="" name="txtAmount" >
	            <span style="font-size: 11px; color: red" id="errorTxtAmount"></span>
	       </div>
	    </div>
	</div>
	
	<div id="dialogAddCurrencyExchange" class="form-horizontal" style="display: none;" name="addCurrencyExchangeForm">
		<div class="form-group">
	       <label for="txtCurrencyExchangeDate" class="col-sm-3 control-label"><spring:message code="lbl.currencyexchange.lblExchangeDate"/><span style="color: red;">*</span><span>&nbsp;</span></label>
	       <div class="col-sm-9">
	           <input class="form-control" id="txtCurrencyExchangeDateAdd" name="txtCurrencyExchangeDateAdd">
	           <span style="font-size: 11px; color: red" id="errorTxtCurrencyExchangeDate"></span>
	       </div>
	    </div></br>
	    <div class="form-group">
	       <label for="txtFrom" class="col-sm-3 control-label"><spring:message code="lbl.currencyexchange.lblFrom"/>
	       <span style="color: red;">*</span></label>
	      
	       <div class="col-sm-9">
	           <select id="txtFrom" style="width: 206px;">
	           	<option value="noneFrom"><spring:message code="lbl.currencyexchange.ddlStatus"/></option>
	           </select>
	       </div>
	    </div></br>
	    <div class="form-group">
	       <label for="txtTo" class="col-sm-3 control-label"><spring:message code="lbl.currencyexchange.lblTo"/><span style="color: red;">*</span></label>
	       <div class="col-sm-9">
	           <select id="txtTo" disabled="disabled" style="width: 206px;">
	           <option value="noneTo"><spring:message code="lbl.currencyexchange.ddlStatus"/></option>
	           </select>
	       </div></br>
	       <p id="demo"></p>
	    </div>
	    
	    
	    <div class="form-group">
	       <label for=txtAmount class="col-sm-3 control-label"><spring:message code="lbl.currencyexchange.lblAmount"/><span style="color: red;">*</span><span>&nbsp;</span></label>
	       <div class="col-sm-9">
	            <input type="number" step="any" min="0" class="form-control" id="txtAmount" value="" name="txtAmount"  >
	            <span style="font-size: 11px; color: red" id="errorTxtAmount"></span>
	       </div>
	    </div>
	</div>
	
	
	<div id="dialogDeleteCurrencyExchange" class="form-horizontal" style="display: none;" name="deleteCurrencyExchangeForm">
		<div class="form-group">
	       <span id="messageContent"></span>
	    </div>
	</div>
	<!-- dialog show when edit status success-->
	<div id="messageDialog" style="display: none;"><span id="messageContent"></span></div>

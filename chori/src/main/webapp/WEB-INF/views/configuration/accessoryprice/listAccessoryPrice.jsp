<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--  
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.4/jquery.datetimepicker.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.4/build/jquery.datetimepicker.full.js"></script>
-->
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="resources/userJs/accessoryprice/listAccessoryPrice.js"></script>
<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left"><spring:message code="lbl.accessoryprice.lblTitle"/></div>
		</div>
		
		<div class="form-group" style="margin-left:20px;margin-top:10px">
	      	<div class="col-sm-9">
				<button class="btn btn-info" id="btnAdd"><spring:message code="lbl.accessoryprice.btnCreateNewAccessoryPrice"/></button>
		<!-- Status -->

			</div>
		 </div>
		 
		 
	 	<div class="form-group" style="margin-left:20px;margin-top:15px">
	 		<div class="col-sm-9">
	 		<spring:message code="lbl.accessoryprice.ddlStatus"/>
				<select id="ddlStatus">
					<option value="Non-Expired"><spring:message code="lbl.accessoryprice.ddlStatus.Non-Expired"/></option>
					<option value="Expired"><spring:message code="lbl.accessoryprice.ddlStatus.Expired"/></option>
					<option value="All"><spring:message code="lbl.accessoryprice.ddlStatus.All"/></option> 
				</select>
			</div>
		</div>
		
		<div class="form-group" style="margin-left:20px;margin-top:15px">
	      	<div class="col-sm-9">
	      		<spring:message code="lbl.accessoryprice.ddlAccessoryGroup"/>
	      		<select class="form-control" id="sltAccessoryGroup"> 
	      		<option value="All"><spring:message code="lbl.accessoryprice.ddlAccessoryGroup.All"/> </option>    	
	      		</select>
	      		 
     		</div>
		 </div>		
				
		<div class="form-group" style="margin-left:20px;margin-top:15px">
	 		<div class="col-sm-9">
				 <spring:message code="lbl.accessoryprice.lblFrom"/> : <input type="text" class="form-control" placeholder="" id="txtSearchFrom" placeholder="Click to Select" value="" name="txtSearchFrom">
				 <spring:message code="lbl.accessoryprice.lblTo"/>   : <input type="text" class="form-control" placeholder="" id="txtSearchTo" value="" placeholder="Click to Select" name="txtSearchTo">
				 <button style="margin-top:-15px" name="btnSearch" class="btn btn-info" id="btnSearch" ><spring:message code="lbl.accessoryprice.btnSearch"/></button>
		 	</div>
		</div>
		 
		<div class="block-content collapse in">
			<div class="span12">
				<table  cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered display responsive" id="listAccessoryPrice">
					<thead>
						<tr>
							<th><spring:message code="lbl.accessoryprice.listaccessoryprice.lblNo"/></th>
							<th><spring:message code="lbl.accessoryprice.listaccessoryprice.lblAccessoryName"/></th>							
							<th><spring:message code="lbl.accessoryprice.listaccessoryprice.lblMode"/></th>
							<th><spring:message code="lbl.accessoryprice.listaccessoryprice.lblSupplier"/></th>							
							<th><spring:message code="lbl.accessoryprice.listaccessoryprice.lblFromDate"/></th>
							<th><spring:message code="lbl.accessoryprice.listaccessoryprice.lblToDate"/></th>
							<th><spring:message code="lbl.accessoryprice.listaccessoryprice.lblUnit"/></th>
							<th><spring:message code="lbl.accessoryprice.listaccessoryprice.lblUnitPricePerContainerUnit"/></th>							
							<th><spring:message code="lbl.accessoryprice.listaccessoryprice.lblCurrency"/></th>
							<th><spring:message code="lbl.accessoryprice.listaccessoryprice.lblExpiredorNonExpired"/></th>
							<th><spring:message code="lbl.accessoryprice.listaccessoryprice.lblRemark"/></th>
							
							<th align="center"><spring:message code="lbl.accessoryprice.listaccessoryprice.lblAction"/></th>
						</tr>
					</thead>
					
				</table>
			</div>
		</div>
	</div>
	<!-- /block -->
</div>

<!-- dialog when add AccessoryPrice-->
	<div id="dialogAddAccessoryPrice" class="form-horizontal" size="600px" style="display: none" name="addAccessoryPriceForm">
		<div class="form-group">
	       <label for="txtAccessoriesCode" class="col-sm-3 control-label"><spring:message code="lbl.accessoryprice.listaccessoryprice.lblAccessoryName"/> : </label>
	       <div class="col-sm-9">
	           <select id="txtAccessoriesCode"></select>
	       </div>
	    </div>
		<div class="form-group">
	       <label for="txtAccSupplierCode" class="col-sm-3 control-label"><spring:message code="lbl.accessoryprice.listaccessoryprice.lblSupplier"/> : </label>
	       <div class="col-sm-9">
	           <select id="txtAccSupplierCode"></select>
	       </div>
	    </div>
	    <div class="form-group">
	    	<div>
		       <label for="txtFromDate" class="col-sm-3 control-label"><spring:message code="lbl.accessoryprice.listaccessoryprice.lblFromDate"/> <span style="color: red;">*</span> : </label>
		           <input type="text"  placeholder="" id="txtFromDate" value="" name="txtFromDate">
	        </div>
	        <div>
		       <label for="txtToDate" class="col-sm-3 control-label"><spring:message code="lbl.accessoryprice.listaccessoryprice.lblToDate"/> : </label>
		           <input type="text" width=20px placeholder="" id="txtToDate" value="" name="txtToDate">
	        </div>
	       
	    </div>
	    <div class="form-group">
	       <label for="txtUnitPrice" class="col-sm-3 control-label"><spring:message code="lbl.accessoryprice.listaccessoryprice.lblUnitPricePerContainerUnit"/> : </label>
	       <div class="col-sm-9">
	           <input type="text" class="form-control" placeholder="" id="txtUnitPrice" value="" name="txtUnitPrice">
	           <p align="center"> 
	             <span style="font-size: 11px; color: red" id="errorUnitPrice" ></span></p>
	       </div>
	    </div>
	    <div class="form-group">
	       <label for="txtCurrencyCode" class="col-sm-3 control-label"><spring:message code="lbl.accessoryprice.listaccessoryprice.lblCurrency"/> : </label>
	       <div class="col-sm-9">
	           <select id="txtCurrencyCode"></select>
	       </div>
	    </div>
	    <div class="form-group">
	       <label for="areaRemark" class="col-sm-3 control-label"><spring:message code="lbl.accessoryprice.listaccessoryprice.lblRemark"/> : </label>
	       <div class="col-sm-9">
	          <textarea id="areaRemark" rows="5" cols="5"></textarea>
	           <p align="center"> 
	             <span style="font-size: 11px; color: red" id="errorAreaRemark" ></span></p>
	       </div>
	    </div>
	</div>
	
	<!-- dialog when edit AccessoryPrice-->
	<div id="dialogEditAccessoryPrice" class="form-horizontal" size="600px" style="display: none" name="dialogEditAccessoryPrice">
		<div class="form-group">
	       <label for="txtAccessoriesCode" class="col-sm-3 control-label"><spring:message code="lbl.accessoryprice.listaccessoryprice.lblAccessoryName"/> : </label>
	       <div class="col-sm-9">
	           <select id="txtAccessoriesCode"></select>
	       </div>
	    </div>
		<div class="form-group">
	       <label for="txtAccSupplierCode" class="col-sm-3 control-label"><spring:message code="lbl.accessoryprice.listaccessoryprice.lblSupplier"/> : </label>
	       <div class="col-sm-9">
	           <select id="txtAccSupplierCode"></select>
	       </div>
	    </div>
	    <div class="form-group">
	    	<div>
		       <label for="txtFromDate" class="col-sm-3 control-label"><spring:message code="lbl.accessoryprice.listaccessoryprice.lblFromDate"/> : </label>
		           <input type="text"  placeholder="" id="txtFromDateEdit" value="" name="txtFromDateEdit">
	        </div>
	        <div>
		       <label for="txtToDate" class="col-sm-3 control-label"><spring:message code="lbl.accessoryprice.listaccessoryprice.lblToDate"/> : </label>
		           <input type="text" width=20px placeholder="" id="txtToDateEdit" value="" name="txtToDateEdit">
	        </div>
	    </div>
	    <div class="form-group">
	       <label for="txtUnitPrice" class="col-sm-3 control-label"><spring:message code="lbl.accessoryprice.listaccessoryprice.lblUnitPricePerContainerUnit"/> : </label>
	       <div class="col-sm-9">
	           <input type="text" class="form-control" placeholder="" id="txtUnitPrice" value="" name="txtUnitPrice">
	            <p align="center"> 
	             <span style="font-size: 11px; color: red" id="errorUnitPrice" ></span></p>
	       </div>
	    </div>
	    <div class="form-group">
	       <label for="txtCurrencyCode" class="col-sm-3 control-label"><spring:message code="lbl.accessoryprice.listaccessoryprice.lblCurrency"/> : </label>
	       <div class="col-sm-9">
	           <select id="txtCurrencyCode"></select>
	       </div>
	    </div>
	    <div class="form-group">
	       <label for="areaRemark" class="col-sm-3 control-label"><spring:message code="lbl.accessoryprice.listaccessoryprice.lblRemark"/> : </label>
	       <div class="col-sm-9">
	          <textarea id="areaRemark" rows="5" cols="5"></textarea>
	          <p align="center"> 
	             <span style="font-size: 11px; color: red" id="errorAreaRemark" ></span></p>
	       </div>
	    </div>
	</div>
	
	<!-- delete-->
	<div id="dialogDeleteAccessoryPrice" class="form-horizontal" style="display: none;" name="deleteAccessoryPriceForm">
		<div class="form-group">
	       <span id="messageContent"></span>
	      
	    </div>
	 </div>
	<!-- dialog show when edit status success-->
	<div id="messageDialog" style="display: none;"><span id="messageContent"></span></div>
	<!-- MESSAGE DIALOG -->
<span id="SuccessTitle" style="display: none;"><spring:message code="DialogMessage.TitleSuccess"/></span>
<span id="ErrorTitle" style="display: none;"><spring:message code="DialogMessage.TitleError"/></span>

<div id="EditSuccessDialog" style="display: none;">
	<span id="EditSuccessContent"><spring:message code="DialogMessage.EditSuccess"/></span>
</div>
<div id="AddSuccessDialog" style="display: none;">
	<span id="AddSuccessContent"><spring:message code="DialogMessage.AddSuccess"/></span>
</div>
<div id="DeleteSuccessDialog" style="display: none;">
	<span id="DeleteSuccessContent"><spring:message code="DialogMessage.DeleteSuccess"/></span>
</div>
<div id="EditFailedDialog" style="display: none;">
	<span id="EditSuccessContent"><spring:message code="DialogMessage.EditFailed"/></span>
</div>
<div id="AddFailedDialog" style="display: none;">
	<span id="AddSuccessContent"><spring:message code="DialogMessage.AddFailed"/></span>
</div>
<div id="DeleteFailedDialog" style="display: none;">
	<span id="DeleteSuccessContent"><spring:message code="DialogMessage.DeleteFailed"/></span>
</div>
<div id="IsExistedDialog" style="display: none;">
	<span id="IsExistedContent"><spring:message code="DialogMessage.IsExisted"/></span>
</div>
<div id="DeleteQuestionDialog" style="display: none;">
	<span id="DeleteQuestionContent"><spring:message code="DialogMessage.DeleteQuestion"/></span>
</div>
<div id="CanNotGetDataDialog" style="display: none;">
	<span id="CanNotGetDatanContent"><spring:message code="DialogMessage.CanNotGetData"/></span>
</div>
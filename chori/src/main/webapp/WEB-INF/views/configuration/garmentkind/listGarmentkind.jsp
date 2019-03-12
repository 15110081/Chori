<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script
		src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="resources/userJs/garmentkind/listGarmentkind.js"></script>
<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left"><spring:message code="lbl.garmentkind.lblTitle"/></div>
		</div>
		<button class="btn btn-info" style="margin-left: 20px; margin-top: 10px" name="btnAdd" id="btnAdd" ><spring:message code="lbl.garmentkind.btnCreateNewGarmentkind"/></button>
		
		<div class="block-content collapse in">	
			<div class="span12">
				<table  cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered display responsive" id="listGarmentkind">
					<thead>
						<tr>
							<th><spring:message code="lbl.garmentkind.listgarmentkind.lblNo"/></th>
							<th><spring:message code="lbl.garmentkind.listgarmentkind.lblKind"/></th>							
							<th><spring:message code="lbl.garmentkind.listgarmentkind.lblDescription"/></th>
							
							<th><spring:message code="lbl.garmentkind.listgarmentkind.lblAction"/></th>
						</tr>
					</thead>
					
				</table>
			</div>
		</div>
		
	</div>
	<!-- /block -->
</div>
<!-- dialog when add Garment Kind-->
	<div id="dialogAddGarmentKind" class="form-horizontal" style="display: none;" name="addKindForm">
		<div class="form-group">
	       <label for="txtKind" class="col-sm-3 control-label"><spring:message code="lbl.garmentkind.listgarmentkind.lblKind"/> <span style="color: red;">*</span></label>
	       <div class="col-sm-4">
	           <input type="text" class="form-control" placeholder="Kind" id="txtKind" value="" name="txtKind" >
	            <p> 
	             <span style="font-size: 11px; color: red" id="errorTxtKind"></span></p>
	       </div>
	    </div>
		<div class="form-group">
	       <label for="txtDescription" class="col-sm-3 control-label"><spring:message code="lbl.garmentkind.listgarmentkind.lblDescription"/> </label>
	       <div class="col-sm-4">
	           <textarea rows="5" cols="5" placeholder="Desciption"  class="form-control" id="txtDescription" value="" name="txtDescription" ></textarea>
	             <p align="center"> 
	             <span style="font-size: 11px; color: red" id="errorTxtDescription" ></span></p>
	       </div>
	    </div>
	   
	</div>
	
	<!-- dialog when edit Garment Kind-->
	<div id="dialogEditGarmentkind" class="form-horizontal" style="display: none;" name="editKindForm">
		<div class="form-group">
	       <label for="txtKind" class="col-sm-3 control-label"><spring:message code="lbl.garmentkind.listgarmentkind.lblKind"/><span style="color: red;">*</span></label>
	       <div class="col-sm-9">
	           <input type="text" class="form-control" id="txtKind" value="" name="txtKind" disabled="">
	       </div>
	    </div>
		<div class="form-group">
	       <label for="txtDescription" class="col-sm-3 control-label"><spring:message code="lbl.garmentkind.listgarmentkind.lblDescription"/></label>
	       <div class="col-sm-9">
	           <textarea rows="5" cols="5"  class="form-control" id="txtDescription" value="" name="txtDescription" ></textarea>
	             <p align="center"> 
	             <span style="font-size: 11px; color: red" id="errorTxtDescription" ></span></p>
	       </div>
	    </div>
	   
	</div>
	<!-- This is dialog confirm delete -->
	<div id="deleteGarmentkindDialog" style="display: none;">
		<span id="messageContent"></span>
	</div>
	<!-- This div is used to show message to user -->
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
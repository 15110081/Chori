<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script
		src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="resources/userJs/packingguide/listPackingguide.js"></script>
<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left"><spring:message code="lbl.packingguide.lblTitle"/></div>
		</div>
		<button class="btn btn-info" name="btnAdd" id="btnAdd" style="margin-left: 20px; margin-top: 10px" ><spring:message code="lbl.packingguide.btnCreateNewPackingguide"/></button>
		<div class="block-content collapse in">		
			<div class="span12">		
				<table  cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered display responsive" id="listPackingguide">
					<thead>
						<tr>
							<th><spring:message code="lbl.packingguide.listpackingguide.lblNo"/></th>
							<th><spring:message code="lbl.packingguide.listpackingguide.lblCode"/></th>							
							<th><spring:message code="lbl.packingguide.listpackingguide.lblDescription"/></th>
							
							<th><spring:message code="lbl.garmentkind.listgarmentkind.lblAction"/></th>
						</tr>
					</thead>
					
				</table>
			</div>
		</div>
		
	</div>
	<!-- /block -->
</div>
<!-- dialog when add Packing Guide-->
	<div id="dialogAddPackingguide" class="form-horizontal" style="display: none;" name="addPackingGuideForm">
		<div class="form-group">
	       <label for="txtCode" class="col-sm-3 control-label"><spring:message code="lbl.packingguide.listpackingguide.lblCode"/> <span style="color: red;">*</span></label>
	       <div class="col-sm-4">
	           <input type="text" class="form-control" placeholder="Paking Guide Code" id="txtCode" value="" name="txtCode" >
	            <p> 
	             <span style="font-size: 11px; color: red" id="errorTxtCode"></span></p>
	       </div>
	    </div>
		<div class="form-group">
	       <label for="txtDescription" class="col-sm-3 control-label"><spring:message code="lbl.packingguide.listpackingguide.lblDescription"/> </label>
	       <div class="col-sm-4">
	           <textarea rows="5" cols="5"  placeholder="Description" class="form-control" id="txtDescription" value="" name="txtDescription" ></textarea>
	             <p align="center"> 
	             <span style="font-size: 11px; color: red" id="errorTxtDescription" ></span></p>
	       </div>
	    </div>
	   
	</div>
	
	<!-- dialog when edit Packing Guide-->
	<div id="dialogEditPackingguide" class="form-horizontal" style="display: none;" name="editPackingGuideForm">
		<div class="form-group">
	       <label for="txtCode" class="col-sm-3 control-label"><spring:message code="lbl.packingguide.listpackingguide.lblCode"/><span style="color: red;">*</span></label>
	       <div class="col-sm-9">
	           <input type="text" class="form-control" id="txtCode" value="" name="txtCode" disabled="">
	       </div>
	    </div>
		<div class="form-group">
	       <label for="txtDescription" class="col-sm-3 control-label"><spring:message code="lbl.packingguide.listpackingguide.lblDescription"/></label>
	       <div class="col-sm-9">
	           <textarea rows="5" cols="5"  class="form-control" id="txtDescription" value="" name="txtDescription" ></textarea>
	             <p align="center"> 
	             <span style="font-size: 11px; color: red" id="errorTxtDescription" ></span></p>
	       </div>
	    </div>
	   
	</div>
	<!-- This is dialog confirm delete -->
	<div id="deletePackingguideDialog" style="display: none;">
		<span id="messageContent"></span>
	</div>
	<!-- This div is used to show message to user -->
	<div id="messageDialog" style="display: none;"><span id="messageContent"></span></div>
	

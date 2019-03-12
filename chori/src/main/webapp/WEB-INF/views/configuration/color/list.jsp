<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="resources/userJs/color/list.js"></script>
<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left"><spring:message code="lbl.color.lblTitle"/></div>
		</div>
		
		<button style="margin-left:20px;margin-top:10px" class="btn btn-info" name="btnAdd" id="btnAdd" ><spring:message code="lbl.color.btnCreateNewColor"/></button>
		
		<div class="block-content collapse in">
			<div class="span12">
				<table  cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered display responsive" id="listColor">
					<thead>
						<tr>
							<th><spring:message code="lbl.color.listcolor.lblNo"/></th>
							<th><spring:message code="lbl.color.listcolor.lblCode"/></th>							
							<th><spring:message code="lbl.color.listcolor.lblDescription"/></th>
							<th><spring:message code="lbl.color.listcolor.lblAction"/></th>
						</tr>
					</thead>
					
				</table>
			</div>
		</div>
		
	</div>
	<!-- /block -->
	
</div>
<!-- dialog when add Color-->
	<div id="dialogAddColor" class="form-horizontal" style="display: none;" name="addColorForm">
		<div class="form-group">
	       <label for="txtColorCode" class="col-sm-3 control-label"><spring:message code="lbl.color.listcolor.lblCode"/> <span style="color: red;">*</span></label>
	       <div class="col-sm-4">
	           <input type="text" placeholder="Color Code" class="form-control" id="txtColorCode" value="" name="txtColorCode" >
	            <p align="center"> 
	             <span style="font-size: 11px; color: red" id="errorTxtColorCode" ></span></p>
	       </div>
	    </div>
		<div class="form-group">
	       <label for="txtDescription" class="col-sm-3 control-label"><spring:message code="lbl.color.listcolor.lblDescription"/> </label>
	       <div class="col-sm-4">
	           <textarea rows="5" cols="5" placeholder="Description"  class="form-control" id="txtDescription" value="" name="txtDescription" ></textarea>
	           <p align="center"> 
	             <span style="font-size: 11px; color: red" id="errorTxtDescription" ></span></p>
	       </div>
	    </div>
	   
	</div>
<!-- dialog when edit Color-->
	<div id="dialogEditColor" class="form-horizontal" style="display: none;" name="editColorForm">
		<div class="form-group">
	       <label for="txtColorCode" class="col-sm-3 control-label"><spring:message code="lbl.color.listcolor.lblCode"/><span style="color: red;">*</span></label>
	       <div class="col-sm-9">
	           <input type="text" class="form-control" id="txtColorCode" value="" name="txtColorCode" disabled>
	       </div>
	    </div>
		<div class="form-group">
	       <label for="txtDescription" class="col-sm-3 control-label"><spring:message code="lbl.color.listcolor.lblDescription"/></label>
	       <div class="col-sm-9">
	         <textarea rows="5" cols="5"  class="form-control" id="txtDescription" value="" name="txtDescription" ></textarea>
	         <p align="center"> 
	             <span style="font-size: 11px; color: red" id="errorTxtDescription" ></span></p>
	       </div>
	    </div>
	</div>
<!-- This is dialog confirm delete -->
	<div id="deleteColorDialog" style="display: none;">
		<span id="messageContent"></span>
	</div>
	
	<!-- This div is used to show message to user -->
	<div id="messageDialog" style="display: none;"><span id="messageContent"></span></div>
	


	
	

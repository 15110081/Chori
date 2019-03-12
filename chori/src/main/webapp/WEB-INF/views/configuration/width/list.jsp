<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="resources/userJs/width/list.js"></script>
<head>

</head>
<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left"><spring:message code="lbl.width.lblTitle"/></div>
		</div>
		
		<button class="btn btn-info" style="margin-left:20px;margin-top:10px" name="btnAdd" id="btnAdd" ><spring:message code="lbl.width.btnAddNew"/></button>
		
		<div class="block-content collapse in">
			<div class="span12">
				<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered display responsive" id="listWidth">
					<thead>
						<tr>
							<th><spring:message code="lbl.width.lblWidthCode"/></th>
							<th><spring:message code="lbl.width.lblUnitName"/></th>
							<th><spring:message code="lbl.width.lblWidthValue"/></th>
							<th><spring:message code="lbl.width.lblAction"/></th>
						</tr>
					</thead>
					
				</table>
			</div>
		</div>
	</div>
	<!-- /block -->
</div>
	<!-- dialog when edit status-->
	<div id="dialogEditWidth" class="form-horizontal" style="display: none;" name="editWidthForm">
		<div class="form-group">
	       <label for="txtWidthCode" class="col-sm-3 control-label"><spring:message code="lbl.width.lblWidthCode"/><span style="color: red;">*</span></label>
	       <div class="col-sm-9">
	           <input type="text" class="form-control" id="txtWidthCode" value="" name="txtWidthCode" disabled>
	           <span style="font-size: 11px; color: red" id="errorTxtWidthCode"></span>
	       </div>
	    </div>
		<div class="form-group">
	       <label for="txtUnitName" class="col-sm-3 control-label"><spring:message code="lbl.width.lblUnitName"/></label>
	       <div class="col-sm-9">
	           <select id="txtUnitId"></select>
	       </div>
	    </div>
	    <div class="form-group">
	       <label for="txtWidthValue" class="col-sm-3 control-label"><spring:message code="lbl.width.lblWidthValue"/><span style="color: red;">*</span></label>
	       <div class="col-sm-9">
	            <input type="number" class="form-control" placeholder="Width Value" id="txtWidthValue" value="" name="txtWidthValue" min="0" step="any">
	            <span style="font-size: 11px; color: red" id="errorTxtWidthValue"></span>
	       </div>
	    </div>
	</div>
	
	<div id="dialogAddWidth" class="form-horizontal" style="display: none;" name="addWidthForm">
		<div class="form-group">
	       <label for="txtWidthCode" class="col-sm-3 control-label"><spring:message code="lbl.width.lblWidthCode"/><span style="color: red;">*</span></label>
	       <div class="col-sm-9">
	            <input type="text" class="form-control" placeholder="Width Code" id="txtWidthCode" value="" name="txtWidthCode">
	           <span style="font-size: 11px; color: red" id="errorTxtWidthCode"></span>
	       </div>
	    </div>
		<div class="form-group">
	       <label for="txtUnitName" class="col-sm-3 control-label"><spring:message code="lbl.width.lblUnitName"/></label>
	       <div class="col-sm-9">
	           <select id="txtUnitId"></select>
	       </div>
	    </div>
	    <div class="form-group">
	       <label for="txtWidthValue" class="col-sm-3 control-label"><spring:message code="lbl.width.lblWidthValue"/><span style="color: red;">*</span></label>
	       <div class="col-sm-9">
	            <input type="number" class="form-control" placeholder="Width Value" id="txtWidthValue" value="" name="txtWidthValue" min="0" step="any">
	            <span style="font-size: 11px; color: red" id="errorTxtWidthValue"></span>
	       </div>
	    </div>
	</div>
	
	<div id="dialogDeleteWidth" class="form-horizontal" style="display: none;">
		<div class="form-group">
	       <span id="messageContent"></span>
	    </div>
	</div>

	<!-- dialog show when edit status success-->
	<div id="messageDialog" style="display: none;"><span id="messageContent"></span></div>

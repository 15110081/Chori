<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>

<script src="resources/userJs/unit/list.js"></script>
<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left"><spring:message code="lbl.lblTitleList"/></div>
		</div>
		
		<button style="margin-left:20px;margin-top:10px" class="btn btn-info" name="btnAdd" id="btnAdd" ><spring:message code="lbl.btnAddUnit"/></button>
		<div class="block-content collapse in">
			<div class="span12">			
				<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered display responsive" id="listUnit">
					<thead>
						<tr>	
							<th><spring:message code="lbl.lblNo"/></th>
							<th><spring:message code="lbl.lblUnitCode"/></th>
							<th><spring:message code="lbl.lblUnitName"/></th>
							<th><spring:message code="lbl.lblAction"/></th>
						</tr>
					</thead>
					
				</table>
			</div>
		</div>
	</div>
	<!-- /block -->
</div>
	<!-- dialog when edit status-->
	<div id="dialogEditUnit" class="" style="display: none;" name="editUnitForm">
		<div class="form-group">
	       <label for="txtUnitID" class="col-sm-3 control-label"><spring:message code="lbl.lblUnitCode"/><span style="color: red">*</span></label>
	       <div class="col-sm-9">
	           <input type="text" class="form-control" id="txtUnitID" value="" name="txtUnitID" disabled="disabled">
	       </div>
	    </div>
		<div class="form-group">
	       <label for="txtUnitName" class="col-sm-3 control-label"><spring:message code="lbl.lblUnitName"/><span style="color: red">*</span></label>
	       <div class="col-sm-9">
	           <input type="text" class="form-control" id="txtUnitName" value="" name="txtUnitName" placeholder="Unit Name">
	           			<p style="font-size: 11px; color: red" id="errorTxtUnitName"></p>
	           
	       </div>
	    </div>
	   
	</div>
	
	<!-- dialog when add status-->
	<div id="dialogAddUnit" class="" style="display: none;" name="addUnitForm">
		<div class="form-group">
	       <label for="txtUnitID" class="col-sm-3 control-label"><spring:message code="lbl.lblUnitCode"/><span style="color: red">*</span></label>
	       <div class="col-sm-9">
	           <input type="text" class="form-control" id="txtUnitID" value="" name="txtUnitID" placeholder="<spring:message code="lbl.lblUnitCode"/>" >
	           	      	<p style="font-size: 11px; color: red" id="errorTxtUnitCode"></p>
	           
	       </div>
	    </div>
		<div class="form-group">
	       <label for="txtUnitName" class="col-sm-3 control-label"><spring:message code="lbl.lblUnitName"/><span style="color: red">*</span></label>
	       <div class="col-sm-9">
	           <input type="text" class="form-control" id="txtUnitName" value="" name="txtUnitName" placeholder="<spring:message code="lbl.lblUnitName"/>">
	           	   	<p style="font-size: 11px; color: red" id="errorTxtUnitName"></p>
	           
	       </div>
	    </div>
	   
	</div>
	<!-- dialog when delete Unit-->
	<div id="dialogDeleteUnit" class="" style="display: none;" name="deleteUnitForm">
		<div class="form-group">
	      <span id="messageContent"></span>
	    </div>
		
	   
	</div>
	<!-- dialog show when edit status success-->
<div id="messageDialog" style="display: none;"><span id="messageContent"></span></div>

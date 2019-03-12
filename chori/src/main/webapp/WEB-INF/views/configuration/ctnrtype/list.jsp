<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="resources/userJs/ctnrtype/list.js"></script>
<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left"><spring:message code="lbl.ctnrtype.lblTitle"/></div>
		</div>
		
		<button class="btn btn-info" style="margin-left: 20px; margin-top: 10px" name="btnAdd" id="btnAdd" ><spring:message code="lbl.ctnrtype.btnAddNew"/></button>
		
		<div class="block-content collapse in">
			<div class="span12">			
				<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered display responsive" id="listCtnr">
					<thead>
						<tr>
							<th><spring:message code="lbl.ctnrtype.lblContainerTypeCode"/></th>
							<th><spring:message code="lbl.ctnrtype.lblDescription"/></th>
							<th><spring:message code="lbl.ctnrtype.lblAction"/></th>
						</tr>
					</thead>
					
				</table>
			</div>
		</div>
	</div>
	<!-- /block -->
</div>
	<!-- dialog when edit status-->
	<div id="dialogEditCtnr" class="form-horizontal" style="display: none;" name="editCtnrForm">
		<div class="form-group">
	       <label for="txtCtnrtypeCode" style="width:inherit" class="col-sm-4 control-label"><spring:message code="lbl.ctnrtype.lblContainerTypeCode"/><span style="color: red;">*</span></label>
	       <div class="col-sm-8">
	           <input type="text" class="form-control" id="txtCtnrtypeCode" value="" name="txtCtnrtypeCode" disabled>
	           <span style="font-size: 11px; color: red" id="errorTxtCtnrtypeCode"></span>
	       </div>
	    </div>
	  <div class="form-group" style="margin-top:15px">
	       <label for="txtDescription" class="col-sm-4 control-label"><spring:message code="lbl.ctnrtype.lblDescription"/> </label>
	       <div class="col-sm-8">
	           <textarea placeholder="Description" rows="5" cols="5" class="form-control" id="txtDescription" value="" name="txtDescription" ></textarea>
	           <span style="font-size: 11px; color: red" id="errorTxtDescription"></span>
	       </div>
	    </div>	    		
	</div>
	
	<div id="dialogAddCtnr" class="form-horizontal" style="display: none;" name="addCtnrForm">
		<div class="form-group">
	       <label for="txtCtnrtypeCode" style="width:inherit" class="col-sm-4 control-label"><spring:message code="lbl.ctnrtype.lblContainerTypeCode"/><span style="color: red;">*</span></label>
	       <div class="col-sm-9">
	            <input type="text" class="form-control" placeholder="Container Type Code" id="txtCtnrtypeCode" value="" name="txtCtnrtypeCode">
	           <span style="font-size: 11px; color: red" id="errorTxtCtnrtypeCode"></span>
	       </div>
	    </div>
	   <div class="form-group" style="margin-top:15px" >
	       <label for="txtDescription" class="col-sm-4 control-label"><spring:message code="lbl.ctnrtype.lblDescription"/> </label>
	       <div class="col-sm-9">
	           <textarea placeholder="Description" rows="5" cols="5" class="form-control" id="txtDescription" value="" name="txtDescription" ></textarea>
	            <span style="font-size: 11px; color: red" id="errorTxtDescription"></span>
	       </div>
	    </div>
	</div>
	
	<div id="dialogDeleteCtnr" class="form-horizontal" style="display: none;" name="dialogDeleteCtnr">
		<div class="form-group">
	       <span id="messageContent"></span>
	    </div>
	</div>
	<!-- dialog show when edit status success-->
	<div id="messageDialog" style="display: none;"><span id="messageContent"></span></div>

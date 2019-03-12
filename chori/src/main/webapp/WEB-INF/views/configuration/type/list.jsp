<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>

<script src="resources/userJs/type/list.js"></script>
<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left"><spring:message code="lbl.lblTilteList"/></div>
		</div>
		<button style="margin-left:20px;margin-top:10px" class="btn btn-info" name="btnAdd" id="btnAdd" ><spring:message code="lbl.btnAddSizeGroup"/></button>
		<div class="block-content collapse in">
			<div class="span12">			
				<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered display responsive" id="listType">
					<thead>
						<tr>
							<th><spring:message code="lbl.lblNo"/></th>
							<th><spring:message code="lbl.lblSizeGroup"/></th>
							<th><spring:message code="lbl.lblDescription"/></th>
							<th><spring:message code="lbl.lblAction"/></th>
						</tr>
					</thead>
					
				</table>
			</div>
		</div>
	</div>
	<!-- /block -->
</div>
	<!-- dialog when edit Type-->
	<div id="dialogEditType" class="" style="display: none;" name="dialogEditType">
		<div class="form-group">
	       <label for="txtUnitID" class="col-sm-3 control-label"><spring:message code="lbl.lblSizeGroupCode"/><span style="color: red">*</span></label>
	       <div class="col-sm-4">
	           <input type="text" class="form-control" id="txtTypeCode" value="" name="txtTypeCode" disabled="">
	       </div>
	    </div>
		<div class="form-group">
	       <label for="txaDescription" class="col-sm-3 control-label"><spring:message code="lbl.lblDescription"/></label>
	       <div class="col-sm-4">
	           <textarea rows="5" cols="5" class="form-control" id="txaDescription" value="" name="txaDescription"></textarea>
	           			<p style="font-size: 11px; color: red" id="errorTxtDescription"></p>
	           
	       </div>
	    </div>	   
	</div>
	
	<!-- dialog when add Type-->
	<div id="dialogAddType" class="" style="display: none;" name="">
		<div class="form-group">
	       <label for="txtTypeCode" class="col-sm-3 control-label"><spring:message code="lbl.lblSizeGroupCode"/><span style="color: red">*</span></label>
	       <div class="col-sm-4">
	           <input type="text" class="form-control" id="txtTypeCode" value="" name="txtTypeCode" placeholder="<spring:message code="lbl.lblSizeGroupCode"/>" >
	           	<span style="font-size: 11px; color: red" id="errorTxtTypeCode" ></span>       
	       </div>
	    </div>
		<div class="form-group">
	       <label for="txaDescription" class="col-sm-3 control-label"><spring:message code="lbl.lblDescription"/></label>
	       <div class="col-sm-4">
	           <textarea rows="5" cols="5"  class="form-control" id="txaDescription" value="" name="txaDescription"></textarea>
	           <p align="center"> 
	           	<span style="font-size: 11px; color: red" id="errorTxtDescription" ></span></p>      
	       </div>
	    </div>
	   
	</div>
	<!-- dialog when delete Type-->
	<div id="dialogDeleteType" class="" style="display: none;" name="dialogDeleteType">
	
	      <span id="messageContent"></span>
	      
	   
		
	   
	</div>
	<!-- dialog show when edit status success-->
<div id="messageDialog" style="display: none;"><span id="messageContent"></span></div>

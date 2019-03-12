<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="resources/userJs/garmentstyle/dataTables.rowsGroup.js"></script>
<script src="resources/userJs/size/list.js"></script>
<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left"><spring:message code="lblSize.sizeList"/></div>
		</div>
		
		<div class="form-group" style="margin-left:20px;margin-top:10px">
	      	<div class="col-sm-9">
	      		<button class="btn btn-info" id="btnCreate"><spring:message code="lblSize.createNewSize"/></button>
	      		<button class="btn btn-info" id="btnDuplicate"><spring:message code="lblSize.duplicateSize"/></button>
     		</div>
     		
		 </div>
		 
		 <!-- 
		<div class="form-group" style="margin-left:20px;margin-top:20px">
	      	<div class="col-sm-9">
	      		Customer
	      		<select class="form-control" id="sltCustomer">   
	      			<option value=-1 disabled selected>-- Please choose customer --</option>   	
	      		</select>
     		</div>
		 </div>
		 
		 <div class="form-group" style="margin-left:20px;margin-top:20px">
	      	<div class="col-sm-9">
	      		Garment Style
	      		<select class="form-control" id="sltGarmentKind">   
	      			<option value="All" disabled selected>-- All--</option>   	
	      		</select>
     		</div>
		 </div>
		 -->
		  <div class="form-group form-horizontal" style="margin-left:20px;margin-top:10px;">
			   <label class="control-label col-sm-1" style="text-align: left;"><spring:message code="lblSize.customer"/></label>
			   <div class="col-sm-7">
				    <select class="form-control" id="sltCustomer" style="margin-left:-40px;">   
		      			<option value=-1 disabled selected><spring:message code="lblSize.pleaseChooseCustomer"/></option>   	
		      		</select>
			   </div>
		 </div>
		 <div class="form-group form-horizontal" style="margin-left:20px;margin-top:10px;">
			   <label class="control-label col-sm-1" style="text-align: left;"><spring:message code="lblSize.garmentKind"/></label>
			   <div class="col-sm-7"> 
			   		<select class="form-control" id="sltGarmentKind" style="margin-left:-40px;">   
	      				<option value="All" selected><spring:message code="lblSize.all"/></option>   	
	      			</select>
			   </div>
		 </div>
		 <div class="block-content collapse in">
			<div class="span12">
			<div id="listSizeCover">
				<table class="table table-striped table-bordered display responsive"
					id="listSize">
					<thead>
						<tr>
							<!--<th><spring:message code="lblSize.tbl.no"/></th> -->
							<th><spring:message code="lblSize.tbl.garmentKind"/></th>
							<th><spring:message code="lblSize.tbl.type"/></th>
							<th><spring:message code="lblSize.tbl.size"/></th>
							<th><spring:message code="lblSize.tbl.action"/></th>
						</tr>
					</thead>
				</table>
				</div>
			</div>
		</div>
		
	</div>
	<!-- /block -->
</div>

<!-- dialog when create size-->
<div id="dialogCreateSize" style="display: none;" name="dialogCreateSize" >
	<div class="form-group">
       <label for="CustomerCode" class="col-sm-3 control-label"><spring:message code="lblSize.customer"/><span
					style="color: red;">*</span></label>
       <div class="col-sm-9">
       	      	<select class="form-control" id="sltAddCustomer"> 
       	      		<option value=-1 disabled selected><spring:message code="lblSize.pleaseChooseCustomer"/></option>        	
	      		</select>
       </div>
    </div>
    <div class="form-group">
       <label for="GarmentKind" class="col-sm-3 control-label"><spring:message code="lblSize.garmentKind"/><span
					style="color: red;">*</span></label>
       <div class="col-sm-9">
       	      	<select class="form-control" id="sltAddGarmentKind">
       	      		<option value=-1 disabled selected><spring:message code="lblSize.pleaseChooseGarmentKind"/></option>         	
	      		</select>
       </div>
    </div>
    <div class="form-group">
       <label for="Type" class="col-sm-3 control-label"><spring:message code="lblSize.type"/><span
					style="color: red;">*</span></label>
       <div class="col-sm-9">
       	      	<select class="form-control" id="sltAddType">     
       	      		<option value=-1 disabled selected><spring:message code="lblSize.pleaseChooseType"/></option>    	
	      		</select>
       </div>
    </div>
    <div class="form-group">
       <label for="txtSize" class="col-sm-3 control-label"><spring:message code="lblSize.size"/><span
					style="color: red;">*</span></label>
       <div class="col-sm-9">
           <input type="text" class="form-control" id="txtAddSize" value="" name="txtSize">
           <p style="font-size: 11px; color: red" id="errorTxtAddSize"></p>
       </div>
    </div>
    <div class="form-group" style="display: none;">
		<button class="btn btn-info" id="btnFormAdd"><spring:message code="lblSize.add"/></button>
    </div>
     <div class="form-group" style="display: none;">
    	<div class="block-content collapse in">
			<div class="span12">
			<div id="listSizeCover">
				<table class="table table-striped table-bordered display responsive"
					id="listSize">
					<thead>
						<tr>
							<!-- <th><spring:message code="lblSize.tbl.no"/></th> -->
							<th><spring:message code="lblSize.tbl.garmentKind"/></th>
							<th><spring:message code="lblSize.tbl.type"/></th>
							<th><spring:message code="lblSize.tbl.size"/></th>
							<th><spring:message code="lblSize.tbl.action"/></th>
						</tr>
					</thead>
				</table>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- dialog when edit size-->
<div id="dialogEditSize" style="display: none;" name="dialogEditSize">
	<div class="form-group">
       <label for="CustomerCode" class="col-sm-3 control-label"><spring:message code="lblSize.customer"/></label>
       <div class="col-sm-9">
       	      	<select class="form-control" id="sltEditCustomer">   
       	      		<option value=-1 disabled selected><spring:message code="lblSize.pleaseChooseCustomer"/></option>    	
	      		</select>
       </div>
    </div>
    <div class="form-group">
       <label for="GarmentKind" class="col-sm-3 control-label"><spring:message code="lblSize.garmentKind"/></label>
       <div class="col-sm-9">
       	      	<select class="form-control" id="sltEditGarmentKind" > 
       	      		<option value=-1 disabled selected><spring:message code="lblSize.pleaseChooseGarmentKind"/></option>      	
	      		</select>
       </div>
    </div>
    <div class="form-group">
       <label for="Type" class="col-sm-3 control-label"><spring:message code="lblSize.type"/></label>
       <div class="col-sm-9">
       	      	<select class="form-control" id="sltEditType">  
       	      		<option value=-1 disabled selected><spring:message code="lblSize.pleaseChooseType"/></option>     	
	      		</select>
       </div>
    </div>
    <div class="form-group">
       <label for="txtSize" class="col-sm-3 control-label"><spring:message code="lblSize.size"/></label>
       <div class="col-sm-9">
           <input type="text" class="form-control" id="txtEditSize" value="" name="txtSize">
           <p style="font-size: 11px; color: red" id="errorTxtEditSize"></p>
       </div>
    </div>
    <div class="form-group" style="display: none;">
		<button class="btn btn-info" id="btnFormEdit"><spring:message code="lblSize.add"/></button>
    </div>
     <div class="form-group" style="display: none;">
    	<div class="block-content collapse in">
			<div class="span12">
			<div id="listSizeCover">
				<table class="table table-striped table-bordered display responsive"
					id="listSize">
					<thead>
						<tr>
							<!--<th><spring:message code="lblSize.tbl.no"/></th> -->
							<th><spring:message code="lblSize.tbl.garmentKind"/></th>
							<th><spring:message code="lblSize.tbl.type"/></th>
							<th><spring:message code="lblSize.tbl.size"/></th>
							<th><spring:message code="lblSize.tbl.action"/></th>
						</tr>
					</thead>
				</table>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- dialog when delete Size-->
<div id="dialogDeleteSize" class="form-horizontal" style="display: none;" name="dialogDeleteSize">
	<div class="form-group">
       <label for="txtSizeCode" class="col-sm-3 control-label"><spring:message code="lblSize.areYouSureToDelete"/></label>
    </div>   
</div>

<!-- dialog when duplicate size-->
<div id="dialogDuplicateSize" style="display: none;" name="dialogDuplicateSize" >
	<div class="form-group">
       <label for="CustomerCode" class="col-sm-3 control-label"><spring:message code="lblSize.from"/></label>
       <div class="col-sm-9">
      	    <select class="form-control" id="sltDuplicateFrom"> 
      	    	<option value=-1 disabled selected><spring:message code="lblSize.pleaseChooseCustomer"/></option>        	
      		</select>
       </div>
    </div>
    <div class="form-group">
       <label for="CustomerCode" class="col-sm-3 control-label"><spring:message code="lblSize.to"/></label>
       <div class="col-sm-9">
      	    <select class="form-control" id="sltDuplicateTo"> 
      	    	<option value=-1 disabled selected><spring:message code="lblSize.pleaseChooseCustomer"/></option>        	
      		</select>
       </div>
    </div>
    <div class="block-content collapse in">
		<div class="span6">
		<label class="col-sm-3 control-label" style="font-weight: bold"><spring:message code="lblSize.sizeOfCustomer"/></label>
		<br/>
		<div id="listDuplicateSizeCover">
			<table class="table table-striped table-bordered display responsive"
				id="listDuplicateSize">
				<thead>
					<tr>
						<!-- <th><spring:message code="lblSize.tbl.no"/></th>  -->
						<th><spring:message code="lblSize.tbl.garmentKind"/></th>
						<th><spring:message code="lblSize.tbl.type"/></th>
						<th><spring:message code="lblSize.tbl.size"/></th>			
					</tr>
				</thead>
			</table>
			</div>
		</div>
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
							
<label id="lblno" style="display: none;"><spring:message code="lblSize.tbl.no"/></label>
<label id="lblgarmentKind" style="display: none;"><spring:message code="lblSize.tbl.garmentKind"/></label>
<label id="lbltype" style="display: none;"><spring:message code="lblSize.tbl.type"/></label>
<label id="lblsize" style="display: none;"><spring:message code="lblSize.tbl.size"/></label>
<label id="lblaction" style="display: none;"><spring:message code="lblSize.tbl.action"/></label>
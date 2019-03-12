<%--
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="resources/userJs/accessoryconsumption/list.js"></script>
<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">Accessory Consumption List</div>
		</div>
		
		<div class="form-group" style="margin-left:20px;margin-top:20px">
	      	<div class="col-sm-9">
	      		<button class="btn btn-info" id="btnCreate">Create New Accessory Consumption</button>
     		</div>
		 </div>

		<div class="form-group" style="margin-left:20px;margin-top:20px">
	      	<div class="col-sm-9">
	      		Factory
	      		<select class="form-control" id="sltFactory">      	
	      		</select>
     		</div>
		 </div>
		 
		<div class="block-content collapse in">
			<div class="span12">
				<table class="table table-striped table-bordered display responsive"
					id="listAccessoryConsumption">
					<thead>
						<tr>
							<th>Factory Code</th>
							<th>Factory Short Name</th>
							<th>Accessory Code</th>
							<th>Accessory Short Name</th>
							<th>Consumption</th>
							<th>Action</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- /block -->
</div>

<!-- dialog when edit accesory consumption-->
<div id="dialogEditAccessoryConsumption" style="display: none;" name="dialogEditAccessoryConsumption">
	<div class="form-group">
       <label for="txtFactoryCode" class="col-sm-3 control-label">Factory Code</label>
       <div class="col-sm-9">
           <input type="text" class="form-control" id="txtFactoryCode" value="" name="txtFactoryCode" disabled>
       </div>
    </div>
	<div class="form-group">
       <label for="txtAccessoryCode" class="col-sm-3 control-label">Accessory Code</label>
       <div class="col-sm-9">
           <input type="text" class="form-control" id="txtAccessoryCode" value="" name="txtAccessoryCode" disabled>
       </div>
    </div>
    <div class="form-group">
       <label for="txtConsumption" class="col-sm-3 control-label">Consumption</label>
       <div class="col-sm-9">
           <input type="text" class="form-control" id="txtConsumption" value="" name="txtConsumption">
           <p style="font-size: 11px; color: red" id="errorTxtConsumption"></p>
       </div>
    </div>
</div>

<!-- dialog when create accesory consumption-->
<div id="dialogCreateAccessoryConsumption" style="display: none;" name="dialogCreateAccessoryConsumption">
	<div class="form-group">
       <label for="txtFactoryCode" class="col-sm-3 control-label">Factory Code</label>
       <div class="col-sm-9">
       	      	<select class="form-control" id="sltAddFactory">      	
	      		</select>
       </div>
    </div>
	<div class="form-group">
       <label for="txtAccessoryCode" class="col-sm-3 control-label">Accessory Code</label>
       <div class="col-sm-9">
       	  	<select class="form-control" id="sltAddAccessoryCode"></select>
       </div>
    </div>
    <div class="form-group">
       <label for="txtConsumption" class="col-sm-3 control-label">Consumption</label>
       <div class="col-sm-9">
           <input type="text" class="form-control" id="txtConsumption" value="" name="txtConsumption">
           <p style="font-size: 11px; color: red" id="errorTxtConsumption"></p>
       </div>
    </div>
</div>

<!-- This div is used to show message to user -->
<div id="messageDialog" style="display: none;">
	<span id="messageContent"></span>
</div>
--%>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="resources/userJs/accessoryconsumption/list.js"></script>
<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left"><spring:message code="lbl.accessoryconsumptionlist"/></div>
		</div>
		
		<div class="form-group" style="margin-left:20px;margin-top:10px">
	      	<div class="col-sm-9">
	      		<button class="btn btn-info" id="btnCreate"><spring:message code="lbl.createnewaccessoryconsumption"/></button>
     		</div>
		 </div>

		<div class="form-group" style="margin-left:20px;margin-top:20px">
	      	<div class="col-sm-9">
	      	<spring:message code="lbl.factory"/>   		
	      		<select class="form-control" id="sltFactory"> 
	      			<option value="All">All</option>     	
	      		</select>
     		</div>
		 </div>
		 
		<div class="block-content collapse in">
			<div class="span12">
				<table class="table table-striped table-bordered display responsive"
					id="listAccessoryConsumption">
					<thead>
						<tr>
							<th><spring:message code="lbl.listAccessoryConsumption.no"/></th>
							<th><spring:message code="lbl.listAccessoryConsumption.factoryCode"/></th>
							<th><spring:message code="lbl.listAccessoryConsumption.factoryShortName"/></th>
							<th><spring:message code="lbl.listAccessoryConsumption.action"/></th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- /block -->
</div>

<!-- dialog when view detail accesory consumption-->
<div id="dialogViewDetailAccessoryConsumption" style="display: none;" name="dialogViewDetailAccessoryConsumption">
	<div class="form-group">
       <label for="txtViewDetailFactory" class="col-sm-3 control-label"><spring:message code="lbl.viewDetail.factoryCode"/></label>
		<div class="col-sm-9">
		    <input type="text" class="form-control" id="txtViewDetailFactory" value="" name="txtViewDetailFactory">
		</div>
    </div>
    <div class="form-group">
       <label for="txtViewDetailFactoryName" class="col-sm-3 control-label"><spring:message code="lbl.viewDetail.factoryName"/></label>
		<div class="col-sm-9">
		    <input type="text" class="form-control" id="txtViewDetailFactoryName" value="" name="txtViewDetailFactoryName">
		</div>
    </div>  
    <div class="form-group">
		<p class="col-sm-3 control-label" style="font-weight: bold"><spring:message code="lbl.viewDetail.accessoryConsumptionDetails"/></p>
				<div class="block-content collapse in">
			<div class="span7">
				<table class="table table-striped table-bordered display responsive"
					id="listViewDetailAccessoryConsumptionDetail">
					<thead>
						<tr>
							<th><spring:message code="lbl.viewDetail.accessoryName"/></th>
							<th><spring:message code="lbl.viewDetail.accessoryGroupName"/></th>						
							<th><spring:message code="lbl.viewDetail.wastedPercentage"/></th>
						</tr>
					</thead>
				</table>
			</div>
		</div>	
    </div> 
</div>

<!-- dialog when edit accesory consumption-->
<div id="dialogEditAccessoryConsumption" style="display: none;" name="dialogEditAccessoryConsumption">
	<div class="form-group">
       <label for="txtFactoryCode" class="col-sm-3 control-label"><spring:message code="lbl.viewDetail.factoryCode"/></label>
		<div class="col-sm-9">
		    <input type="text" class="form-control" id="txtEditFactory" value="" name="txtEditFactory">
		</div>
    </div>
    <div class="form-group">
       <label for="txtEditFactoryName" class="col-sm-3 control-label"><spring:message code="lbl.viewDetail.factoryName"/></label>
		<div class="col-sm-9">
		    <input type="text" class="form-control" id="txtEditFactoryName" value="" name="txtEditFactoryName">
		</div>
    </div>
	<div class="form-group">
		<p class="col-sm-3 control-label" style="font-weight: bold"><spring:message code="lbl.accessoryConsumptionByGroupName"/></p>
				<div class="block-content collapse in">
			<div class="span7">
				<table class="table table-striped table-bordered display responsive"
					id="listEditAccessoryGroup">
					<thead>
						<tr>
							<th><spring:message code="lbl.viewDetail.accessoryGroupName"/></th>
							<th><spring:message code="lbl.viewDetail.wastedPercentage"/></th>
						</tr>
					</thead>
				</table>
			</div>
		</div>	
    </div>    
    <div class="form-group">
		<p class="col-sm-3 control-label" style="font-weight: bold"><spring:message code="lbl.viewDetail.accessoryConsumptionDetails"/></p>
				<div class="block-content collapse in">
			<div class="span7">
				<table class="table table-striped table-bordered display responsive"
					id="listEditAccessoryConsumptionDetail">
					<thead>
						<tr>
							<th><spring:message code="lbl.viewDetail.accessoryName"/></th>
							<th><spring:message code="lbl.viewDetail.accessoryGroupName"/></th>						
							<th><spring:message code="lbl.viewDetail.wastedPercentage"/></th>
						</tr>
					</thead>
				</table>
			</div>
		</div>	
    </div> 
</div>

<!-- dialog when create accesory consumption-->
<div id="dialogCreateAccessoryConsumption" style="display: none;" name="dialogCreateAccessoryConsumption">
	<div class="form-group">
       <label for="txtFactoryCode" class="col-sm-3 control-label"><spring:message code="lbl.viewDetail.factoryCode"/><span
					style="color: red;">*</span></label>
       <div class="col-sm-9">
       	      	<select class="form-control" id="sltAddFactory">      	
	      		</select>
       </div>
    </div>
	<div class="form-group">
		<p class="col-sm-3 control-label"  style="font-weight: bold"><spring:message code="lbl.accessoryConsumptionByGroupName"/></p>
				<div class="block-content collapse in">
			<div class="span7">
				<table class="table table-striped table-bordered display responsive"
					id="listAddAccessoryGroup">
					<thead>
						<tr>
							<th><spring:message code="lbl.viewDetail.accessoryGroupName"/></th>
							<th><spring:message code="lbl.viewDetail.wastedPercentage"/></th>
						</tr>
					</thead>
				</table>
			</div>
		</div>	
    </div>    
    <div class="form-group">
		<p class="col-sm-3 control-label" style="font-weight: bold"><spring:message code="lbl.viewDetail.accessoryConsumptionDetails"/></p>
				<div class="block-content collapse in">
			<div class="span7">
				<table class="table table-striped table-bordered display responsive"
					id="listAddAccessoryConsumptionDetail">
					<thead>
						<tr>
							<th><spring:message code="lbl.viewDetail.accessoryName"/></th>
							<th><spring:message code="lbl.viewDetail.accessoryGroupName"/></th>						
							<th><spring:message code="lbl.viewDetail.wastedPercentage"/></th>
						</tr>
					</thead>
				</table>
			</div>
		</div>	
    </div>   
    <!--  
	<div class="form-group">
		<label for="txtAccessoryCode" class="col-sm-3 control-label">Accessory Code</label>
		<div class="col-sm-9">
			  	<select class="form-control" id="sltAddAccessoryCode"></select>
		</div>		
    </div>
    <div class="form-group">
       <label for="txtConsumption" class="col-sm-3 control-label">Consumption</label>
       <div class="col-sm-9">
           <input type="text" class="form-control" id="txtConsumption" value="" name="txtConsumption">
           <p style="font-size: 11px; color: red" id="errorTxtConsumption"></p>
       </div>
    </div>
    -->
</div>

<!-- This is dialog confirm delete -->
<div id="deleteAccessoryConsumptionDialog" style="display: none;">
	<span id="messageContent"></span>
</div>

<!-- This div is used to show message to user -->
<div id="messageDialog" style="display: none;">
	<span id="messageContent"></span>
</div>

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
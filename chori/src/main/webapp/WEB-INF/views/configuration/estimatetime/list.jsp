<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script src="resources/userJs/estimatetime/list.js"></script>
<title><spring:message code="lbl.lblTitle_est"/></title>
</head>
<body>
<div class="col-sm-10" id="frmEstimatetime" align="center">
	<h1><spring:message code="lbl.lblTitle_est"/></h1>
		<form class="">
		
		<div class="form-group" >
				<label class="col-sm-2 control-label "><spring:message code="lbl.lblPiCompl"/></label>
				<div class="col-sm-8">
					<input type="number" min="0" max="1000" class="form-control quantity" id="txtPiconpletion">
					<spring:message code="lbl.lblDays"/>
					 <span style="font-size: 11px;color: red" id="errorTxtCompletion" class="help-block"></span>
					 
				</div>
			</div>
			<br/>
			<div class="form-group" >
				<label class="col-sm-2 control-label "><spring:message code="lbl.lblPack"/></label>
				<div class="col-sm-8">
					<input type="number" min="0" max="1000" class="form-control quantity" id="txtPackingaccdelv">
					<spring:message code="lbl.lblDays"/>
					 <span style="font-size: 11px;color: red" id="errorTxtPackingaccdelv" class="help-block"></span>
				</div>
			</div>
			<br/>
			<div class="form-group" >
				<label class="col-sm-2 control-label " ><spring:message code="lbl.lblManu"/></label>
				<div class="col-sm-8">
					<input type="number" min="0" max="1000" class="form-control quantity" id="txtManuaccdelv">
					<spring:message code="lbl.lblDays"/>
					 <span style="font-size: 11px;color: red" id="errorTxtManuaccdelv" class="help-block"></span>
				</div>
			</div>
		<input type="hidden" id="txtEstimatetimeCode"/>
			
			
			
			<div class="form-group text-center ">
				<button type="button" class="btn btn-info glyphicon glyphicon-ok" id="btnSave"><spring:message code="lbl.btnSave"/></button>
				<!-- Standard button -->
				<!-- <button type="button" class="btn glyphicon glyphicon-remove" 
				id="btnCancel" ><spring:message code="lbl.btnCancel"/></button> -->
				
			</div>
		</form>
	</div>
<!-- dialog show when edit  success-->
<div id="messageDialog" style="display: none;"><span id="messageContent"></span></div>	

</body>
</html>
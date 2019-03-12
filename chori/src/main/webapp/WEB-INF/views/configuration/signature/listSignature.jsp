<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="resources/userJs/signature/signature.js"></script>

<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left"><spring:message code="lbl.signature.signatureForm" /></div>
		</div>

		<button style="margin-left:20px;margin-top:10px" class="btn btn-info" id="btnAddNewSignature"><spring:message code="lbl.signature.btnAddNewSignature" /></button>
		
		<div class="block-content collapse in">
			<div class="span12">
				<table class="table table-striped table-bordered display responsive"
					id="listSignature">
					<thead>
						<tr>
							<th><spring:message code="lbl.signature.listSignature.row1" /></th>
							<th><spring:message code="lbl.signature.listSignature.row2" /></th>							
							<th><spring:message code="lbl.signature.listSignature.row3" /></th>							
							<th><spring:message code="lbl.signature.listSignature.row4" /></th>
							<th><spring:message code="lbl.signature.listSignature.row5" /></th>
							<th></th>							
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- /block -->
</div>

<!-- This div is used to show message to user -->
<div id="messageDialog" style="display: none;">
	<span id="messageContent"></span>
</div>

<!-- This is dialog confirm delete -->
<div id="deleteSignatureDialog" style="display: none;">
	<span id="messageContent"></span>
</div>

<!-- This is dialog to add new Signature -->
<div id="addSignatureDialog" style="display: none;">
	<form:form method="POST" modelAttribute="signatureAddModel"
					enctype="multipart/form-data" action="signature/addNewSignature">
					
					
					<div class="form-group">
						<label for="name" class="col-sm-3 control-label"><spring:message
				code="lbl.signature.addSignatureDialog.name" /><span
							style="color: red;">*</span></label>
						<div class="col-sm-9">
							<form:input type="text" class="form-control" id="name"
								path="name" placeholder='Name' value=""
								name="name" />
							<span style="font-size: 11px; color: red" id="errorName"></span>
						</div>
					</div>
										

					<div class="form-group">
						<label for="text1" class="col-sm-3 control-label"><spring:message
				code="lbl.signature.addSignatureDialog.title" /></label>
						<div class="col-sm-9">
							<form:input type="text" class="form-control" id="text1"
								path="text1" placeholder='Title' value=""
								name="text1" />
							<span style="font-size: 11px; color: red" id="errorText1"></span>
						</div>
					</div>
					

					<div class="form-group">
						<div class="col-sm-6">
							<form:input type="file" path="files[0].file" id=""
								class="form-control input-sm" accept="image/*"/>
						</div>						
					</div>
					
					<div class="form-group">
						<label for="text2" class="col-sm-3 control-label"><spring:message
				code="lbl.signature.addSignatureDialog.fullname" /></label>
						<div class="col-sm-9">
							<form:input type="text" class="form-control" id="text2"
								path="text2" placeholder='Full Name' value=""
								name="text2" />
							<span style="font-size: 11px; color: red" id="errorText2"></span>
						</div>
					</div>
					
					
					<br />
					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit" value="Save"
								class="btn btn-primary btn-sm" id="btnAdd">
						</div>
					</div>
				</form:form>
</div>

<!-- This is dialog to edit Signature -->
<div id="editSignatureDialog" style="display: none;">
	<form:form method="POST" modelAttribute="signatureAddModel"
					enctype="multipart/form-data" action="signature/edit">

					<div class="form-group" style="display: none;">
						<label for="accordersigncode" class="col-sm-3 control-label"><spring:message
				code="lbl.signature.addSignatureDialog.code" /><span
							style="color: red;">*</span></label>
						<div class="col-sm-9">
							<form:input type="text" class="form-control" id="accordersigncode"
								path="accordersigncode" placeholder='Signature Code' value=""
								name="accordersigncode" />
							<span style="font-size: 11px; color: red" id="errorSignaturecode"></span>
						</div>
					</div>
					
					<div class="form-group">
						<label for="name" class="col-sm-3 control-label"><spring:message
				code="lbl.signature.addSignatureDialog.name" /><span
							style="color: red;">*</span></label>
						<div class="col-sm-9">
							<form:input type="text" class="form-control" id="name"
								path="name" placeholder='Name' value=""
								name="name" />
							<span style="font-size: 11px; color: red" id="errorName"></span>
						</div>
					</div>
										

					<div class="form-group">
						<label for="text1" class="col-sm-3 control-label"><spring:message
				code="lbl.signature.addSignatureDialog.title" /></label>
						<div class="col-sm-9">
							<form:input type="text" class="form-control" id="text1"
								path="text1" placeholder='Title' value=""
								name="text1" />
							<span style="font-size: 11px; color: red" id="errorText1"></span>
						</div>
					</div>
					

					<div class="form-group">
						<div class="col-sm-6">
							<form:input type="file" path="files[0].file" id=""
								class="form-control input-sm" accept="image/*"/>
						</div>						
					</div>
					
					<div class="form-group">
						<label for="text2" class="col-sm-3 control-label"><spring:message
				code="lbl.signature.addSignatureDialog.fullname" /></label>
						<div class="col-sm-9">
							<form:input type="text" class="form-control" id="text2"
								path="text2" placeholder='Full Name' value=""
								name="text2" />
							<span style="font-size: 11px; color: red" id="errorText2"></span>
						</div>
					</div>
					
					
					<br />
					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit" value="Save"
								class="btn btn-primary btn-sm" id="btnEditSig">
						</div>
					</div>
				</form:form>
</div>

<!-- This div is used to show image fullsize to user -->
<div id="imageDialog" style="display: none;">
	<img alt="" src="" id="imageToShow">
</div>
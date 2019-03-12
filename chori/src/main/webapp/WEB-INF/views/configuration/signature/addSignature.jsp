<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="${pageContext.request.contextPath}/resources/userJs/signature/addSignature.js"></script>

<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">Add New Signature</div>
		</div>

		<div class="block-content collapse in">
			<div class="span12">
				<form:form method="POST" modelAttribute="signatureAddModel"
					enctype="multipart/form-data" class="form-horizontal">

					<div class="form-group">
						<label for="accordersigncode" class="col-sm-3 control-label">Code<span
							style="color: red;">*</span></label>
						<div class="col-sm-9">
							<form:input type="text" class="form-control" id="accordersigncode"
								path="accordersigncode" placeholder='Signature Code' value=""
								name="accordersigncode" />
							<span style="font-size: 11px; color: red" id="errorSignaturecode"></span>
						</div>
					</div>
					
					<div class="form-group">
						<label for="name" class="col-sm-3 control-label">Name<span
							style="color: red;">*</span></label>
						<div class="col-sm-9">
							<form:input type="text" class="form-control" id="name"
								path="name" placeholder='Name' value=""
								name="name" />
							<span style="font-size: 11px; color: red" id="errorName"></span>
						</div>
					</div>
					
					<div class="form-group">
						<label for="text1" class="col-sm-3 control-label">Title<span
							style="color: red;">*</span></label>
						<div class="col-sm-9">
							<form:input type="text" class="form-control" id="text1"
								path="text1" placeholder='Text 1' value=""
								name="text1" />
							<span style="font-size: 11px; color: red" id="errorText1"></span>
						</div>
					</div>					

					<div class="form-group">
						<div class="col-sm-6">
							<form:input type="file" path="files[0].file" id=""
								class="form-control input-sm" />
						</div>						
					</div>
					
					<div class="form-group">
						<label for="text2" class="col-sm-3 control-label">Full Name<span
							style="color: red;">*</span></label>
						<div class="col-sm-9">
							<form:input type="text" class="form-control" id="text2"
								path="text2" placeholder='Text 2' value=""
								name="text2" />
							<span style="font-size: 11px; color: red" id="errorText2"></span>
						</div>
					</div>			
				
					<br />
					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit" value="Upload"
								class="btn btn-primary btn-sm">
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="${pageContext.request.contextPath}/resources/userJs/accessory/addAccessory.js"></script>

<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">Add New Accessory</div>
		</div>

		<div class="block-content collapse in">
			<div class="span12">
				<form:form method="POST" modelAttribute="accessoryAddModel"
					enctype="multipart/form-data" class="form-horizontal">

					<div class="form-group">
						<label for="accessorycode" class="col-sm-3 control-label">Code<span
							style="color: red;">*</span></label>
						<div class="col-sm-9">
							<form:input type="text" class="form-control" id="accessorycode"
								path="accessorycode" placeholder='Accessory Code' value=""
								name="accessorycode" />
							<span style="font-size: 11px; color: red" id="errorAccessorycode"></span>
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
						<label for="kind" class="col-sm-3 control-label">Kind</label>
						<div class="col-sm-9">
							<form:select class="form-control" id="kind"
								path="kind" name="kind" />
							<span style="font-size: 11px; color: red" id="errorKind"></span>
						</div>
					</div>

					<div class="form-group">
						<label for="dimension" class="col-sm-3 control-label">Dimension</label>
						<div class="col-sm-9">
							<form:input type="text" class="form-control" id="dimension"
								path="dimension" placeholder='Dimension' value=""
								name="dimension" />
							<span style="font-size: 11px; color: red" id="errorDimension"></span>
						</div>
					</div>

					<div class="form-group">
						<label for="mode" class="col-sm-3 control-label">Mode</label>
						<div class="col-sm-9">
							<form:select class="form-control" id="mode"
								path="mode" name="mode" />
							<span style="font-size: 11px; color: red" id="errorMode"></span>
						</div>
					</div>
					
					<div class="form-group">
						<label for="unitcode" class="col-sm-3 control-label">Unit</label>
						<div class="col-sm-9">
							<form:select class="form-control" id="unitcode"
								path="unitcode" name="unitcode" />
							<span style="font-size: 11px; color: red" id="errorUnit"></span>
						</div>
					</div>
					
					<div class="form-group">
						<label for="colorcode" class="col-sm-3 control-label">Color</label>
						<div class="col-sm-9">
							<form:select class="form-control" id="colorcode"
								path="colorcode" name="colorcode" />
							<span style="font-size: 11px; color: red" id="errorColor"></span>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-6">
							<form:input type="file" path="files[0].file" id=""
								class="form-control input-sm" />
						</div>
						<div class="col-sm-6">
							<form:input type="file" path="files[1].file" id=""
								class="form-control input-sm" />
						</div>
					</div>
					<!--
					<form:input type="file" path="files[0].file" id=""
						class="form-control input-sm" />

					<form:input type="file" path="files[1].file" id=""
						class="form-control input-sm" />

					 
					<c:forEach var="v" varStatus="vs"
						items="${accessoryAddModel.files}">
						<form:input type="file" path="files[${vs.index}].file"
							id="files[${vs.index}].file" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="files[${vs.index}].file" class="help-inline" />
						</div>
					</c:forEach>
					 -->
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
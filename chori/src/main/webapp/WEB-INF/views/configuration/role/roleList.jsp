<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="resources/userJs/role/role.js"></script>

<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">
				<spring:message code="lbl.role.roleTitle" />
			</div>
		</div>

		<button id="btnAddNewRole" class="btn btn-info"
			style="margin-left: 20px; margin-top: 10px">
			<spring:message code="lbl.role.btnAddNewRole" />
		</button>

		<div class="block-content collapse in">
			<div class="span12">
				<table class="table table-striped table-bordered display responsive"
					id="listRole">
					<thead>
						<tr>
							<th><spring:message code="lbl.role.listRole.row1" /></th>
							<th><spring:message code="lbl.role.listRole.row2" /></th>
							<th><spring:message code="lbl.role.listRole.row3" /></th>
							<th><spring:message code="lbl.role.listRole.row4" /></th>
							<th><spring:message code="lbl.role.listRole.row5" /></th>
							<th><spring:message code="lbl.role.listRole.row6" /></th>
							<th></th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- /block -->
</div>
<script>
	
</script>

<!-- This div is used to show message to user -->
<div id="messageDialog" style="display: none;">
	<span id="messageContent"></span>
</div>

<!-- This div is used to show delete message to user -->
<div id="deleteRoleDialog" style="display: none;">
	<span id="messageContent"></span>
</div>

<!-- This is dialog to Assign function for 1 Role -->
<div id="assignFunctionDialog" style="display: none;"
	class="" name="addRoleForm">
	<div class="form-group">
		<label for="txtRoleID" class="col-sm-3 control-label"><spring:message
				code="lbl.role.assignFunctionDialog.txtRoleID" /></label>
		<div class="col-sm-9">
			<input type="text" class="form-control" id="txtRoleID"
				placeholder="Role ID" value="" name="txtRoleID" disabled>
		</div>
	</div>

	<div class="form-group">
		<label for="txtRoleName" class="col-sm-3 control-label"><spring:message
				code="lbl.role.assignFunctionDialog.txtRoleName" /></label>
		<div class="col-sm-9">
			<input type="text" class="form-control" id="txtRoleName"
				placeholder="Role Name" value="" name="txtRoleName" disabled>
			<span style="font-size: 11px; color: red" id="errorTxtRoleName"></span>
		</div>
	</div>

	<div class="form-group">
		<label for="listOfFunctions" class="col-sm-3 control-label"><spring:message
				code="lbl.role.assignFunctionDialog.listOfFunctions" /></label>
		<!-- 		 
		<div class="col-sm-9" id="listOfFunctions"></div>
		 -->
		
		<table class="table table-striped table-bordered display responsive"
					id="listOfFunctions">
					<thead>
						<tr>
							<th>No</th>
							<th>Function Name</th>
							<th></th>
						</tr>
					</thead>
				</table>
		 
	</div>
</div>

<!-- This is dialog to add new Role -->
<div id="addRoleDialog" style="display: none;" name="addRoleDialog">
	<div class="form-group">
		<label for="txtRoleId" class="col-sm-3 control-label"><spring:message
				code="lbl.role.addRoleDialog.txtRoleId" /><span style="color: red;">*</span>
		</label>
		<div class="col-sm-9">
			<input type="text" class="form-control" id="txtRoleId" value=""
				name="txtRoleId" maxlength="20" required> <p
				style="font-size: 11px; color: red" id="errorTxtRoleId"></p>
		</div>
	</div>

	<div class="form-group">
		<label for="txtRoleName" class="col-sm-3 control-label"><spring:message
				code="lbl.role.addRoleDialog.txtRoleName" /></label>
		<div class="col-sm-9">
			<input type="text" class="form-control" id="txtRoleName" value=""
				name="txtRoleName" maxlength="100" required> <p
				style="font-size: 11px; color: red" id="errorTxtRoleName"></p>
		</div>
	</div>

	<div class="form-group">
		<label for="txtDescription" class="col-sm-3 control-label"><spring:message
				code="lbl.role.addRoleDialog.txtDescription" /></label>
		<div class="col-sm-9">
			<textarea class="form-control" id="txtDescription"
				name="txtDescription" maxlength="500" required></textarea> <p
				style="font-size: 11px; color: red" id="errorTxtDescription"></p>
		</div>
	</div>
</div>

<!-- This is dialog to edit Role -->
<div id="editRoleDialog" style="display: none;" name="addRoleDialog">
	<div class="form-group">
		<label for="txtRoleId" class="col-sm-3 control-label"><spring:message
				code="lbl.role.addRoleDialog.txtRoleId" />
		</label>
		<div class="col-sm-9">
			<input type="text" class="form-control" id="txtRoleId" value=""
				name="txtRoleId" disabled required> <p
				style="font-size: 11px; color: red" id="errorTxtRoleId"></p>
		</div>
	</div>

	<div class="form-group">
		<label for="txtRoleName" class="col-sm-3 control-label"><spring:message
				code="lbl.role.addRoleDialog.txtRoleName" /></label>
		<div class="col-sm-9">
			<input type="text" class="form-control" id="txtRoleName" value=""
				name="txtRoleName" maxlength="100" required> <p
				style="font-size: 11px; color: red" id="errorTxtRoleName"></p>
		</div>
	</div>

	<div class="form-group">
		<label for="txtDescription" class="col-sm-3 control-label"><spring:message
				code="lbl.role.addRoleDialog.txtDescription" /></label>
		<div class="col-sm-9">
			<textarea class="form-control" id="txtDescription"
				name="txtDescription" maxlength="500" required></textarea> <p
				style="font-size: 11px; color: red" id="errorTxtDescription"></p>
		</div>
	</div>
</div>
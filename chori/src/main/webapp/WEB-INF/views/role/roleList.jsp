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
			<div class="muted pull-left">User Group List</div>
		</div>
		<div class="block-content collapse in">
			<div class="span12">
				<table class="table table-striped table-bordered display responsive"
					id="listRole">
					<thead>
						<tr>
							<th>No</th>
							<th>Group ID</th>
							<th>Group Name</th>
							<th>Description</th>
							<th>Creator</th>
							<th>Created Date</th>
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

<!-- This is dialog to Assign function for 1 Role -->
<div id="assignFunctionDialog" style="display: none;"
	class="form-horizontal" name="addRoleForm">
	<div class="form-group">
		<label for="txtRoleID" class="col-sm-3 control-label">Role ID</label>
		<div class="col-sm-9">
			<input type="text" class="form-control" id="txtRoleID"
				placeholder="Role ID" value="" name="txtRoleID" disabled>
		</div>
	</div>

	<div class="form-group">
		<label for="txtRoleName" class="col-sm-3 control-label">Role Name</label>
		<div class="col-sm-9">
			<input type="text" class="form-control" id="txtRoleName"
				placeholder="Role Name" value="" name="txtRoleName" disabled>
			<span style="font-size: 11px; color: red" id="errorTxtRoleName"></span>
		</div>
	</div>

	<div class="form-group">
		<label for="txtDescription" class="col-sm-3 control-label">All Functions</label>
		<div class="col-sm-9" id="listOfFunctions"></div>
	</div>
</div>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="resources/userJs/user/user.js"></script>

<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">
				<spring:message code="lbl.user.userTitle" />
			</div>
		</div>

		<button id="btnAddNewUser" class="btn btn-info"
			style="margin-left: 20px; margin-top: 10px">
			<spring:message code="lbl.user.btnAddNewUser" />
		</button>

		<div class="form-group">
			<span style="margin-left: 20px; margin-top: 10px;"><spring:message
					code="lbl.user.ddlStatus" /></span> <select
				style="margin-left: 20px; margin-top: 10px" id="ddlStatus">
				<option value="All"><spring:message
						code="lbl.user.ddlStatus.All" /></option>
				<option value="Active"><spring:message
						code="lbl.user.ddlStatus.Active" /></option>
				<option value="In-Active"><spring:message
						code="lbl.user.ddlStatus.In-Active" /></option>
			</select>
		</div>
		<div class="block-content collapse in">
			<div class="span12">
				<table class="table table-striped table-bordered display responsive"
					id="listUser">
					<thead>
						<tr>
							<th><spring:message code="lbl.user.listUser.row1" /></th>
							<th><spring:message code="lbl.user.listUser.row2" /></th>
							<th><spring:message code="lbl.user.listUser.row3" /></th>
							<th><spring:message code="lbl.user.listUser.row4" /></th>
							<th><spring:message code="lbl.user.listUser.row5" /></th>
							<th><spring:message code="lbl.user.listUser.row6" /></th>
							<th><spring:message code="lbl.user.listUser.row7" /></th>
							<th><spring:message code="lbl.user.listUser.row8" /></th>
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
<div id="deleteUserDialog" style="display: none;">
	<span id="messageContent"></span>
</div>

<!-- This is dialog to add User -->
<div id="addUserDialog" style="display: none;" name="addUserDialog">
	<div class="inputarea">
		<div class="left" style="width: 50%; float: left">
			<div class="form-group">
				<label for="txtUserName" class="col-sm-3 control-label"><spring:message
						code="lbl.user.addUserDialog.txtUserName" /><span
					style="color: red;">*</span> </label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtUserName"
						placeholder='User name' value="" name="txtUserName" required>
					<span style="font-size: 11px; color: red" id="errorTxtUserName"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="txtPassword" class="col-sm-3 control-label"><spring:message
						code="lbl.user.addUserDialog.txtPassword" /><span
					style="color: red;">*</span> </label>
				<div class="col-sm-9">
					<input type="password" class="form-control" id="txtPassword"
						placeholder='Password' value="" name="txtPassword" required>
					<span style="font-size: 11px; color: red" id="errorTxtPassword"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="txtFirstName" class="col-sm-3 control-label"><spring:message
						code="lbl.user.addUserDialog.txtFirstName" /></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtFirstName"
						placeholder='First name' value="" name="txtFirstName" required>
					<span style="font-size: 11px; color: red" id="errorTxtFirstName"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="txtLastName" class="col-sm-3 control-label"><spring:message
						code="lbl.user.addUserDialog.txtLastName" /></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtLastName"
						placeholder='Last Name' value="" name="txtLastName" required>
					<span style="font-size: 11px; color: red" id="errorTxtLastName"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="txtEmail" class="col-sm-3 control-label"><spring:message
						code="lbl.user.addUserDialog.txtEmail" /></label>
				<div class="col-sm-9">
					<input type="email" class="form-control" id="txtEmail"
						placeholder='Email' value="" name="txtEmail" required> <span
						style="font-size: 11px; color: red" id="errorTxtEmail"></span>
				</div>
			</div>
		</div>
		<div class="right" style="width: 50%; float: right">
			<div class="form-group">
				<label for="txtPhone" class="col-sm-3 control-label"><spring:message
						code="lbl.user.addUserDialog.txtPhone" /></label>
				<div class="col-sm-9">
					<input maxlength="50" type="text" class="form-control" id="txtPhone"
						placeholder='Phone' value="" name="txtPhone" required> <span
						style="font-size: 11px; color: red" id="errorTxtPhone"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="txtAgent" class="col-sm-3 control-label"><spring:message
						code="lbl.user.addUserDialog.txtAgent" /></label>
				<div class="col-sm-9">
					<select id="ddlAgent">
					</select>
				</div>
			</div>

			<div class="form-group">
				<label for="txtRole" class="col-sm-3 control-label"><spring:message
						code="lbl.user.addUserDialog.txtRole" /></label>
				<div class="col-sm-9">
					<select id="ddlRole">
					</select>
				</div>
			</div>

			<div class="form-group">
				<label for="txtStatus" class="col-sm-3 control-label"><spring:message
						code="lbl.user.addUserDialog.txtStatus" /></label>
				<div class="col-sm-9">
					<select id="txtStatus">
						<option value="Active"><spring:message
								code="lbl.user.addUserDialog.txtStatus.Active" /></option>
						<option value="In-Active"><spring:message
								code="lbl.user.addUserDialog.txtStatus.In-Active" /></option>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label for="txtCCEmail" class="col-sm-3 control-label"><spring:message
						code="lbl.user.addUserDialog.txtCCEmail" /></label>
				<div class="col-sm-9">
					<input type="checkbox" id="chkCCmailstring">
				</div>
			</div>
		</div>
		<div style="clear: both;"></div>
	</div>
</div>

<!-- This is dialog to edit Role -->
<div id="editUserDialog" style="display: none;" name="editUserDialog">
	<div class="inputarea">
		<div class="left" style="width: 50%; float: left">
			<div class="form-group">
				<label for="txtUserName" class="col-sm-3 control-label"><spring:message
						code="lbl.user.addUserDialog.txtUserName" /><span
					style="color: red;">*</span> </label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtUserName"
						placeholder='User name' value="" name="txtUserName" required
						readonly> <span style="font-size: 11px; color: red"
						id="errorTxtUserName"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="txtPassword" class="col-sm-3 control-label"><spring:message
						code="lbl.user.editUserDialog.txtPassword" /><input
					type="checkbox" id="chkReset"> </label>
				<div class="col-sm-9">
					<input type="password" class="form-control" id="txtPassword"
						placeholder='Password' value="" name="txtPassword" required>
					<span style="font-size: 11px; color: red" id="errorTxtPassword"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="txtFirstName" class="col-sm-3 control-label"><spring:message
						code="lbl.user.addUserDialog.txtFirstName" /></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtFirstName"
						placeholder='First name' value="" name="txtFirstName" required>
					<span style="font-size: 11px; color: red" id="errorTxtFirstName"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="txtLastName" class="col-sm-3 control-label"><spring:message
						code="lbl.user.addUserDialog.txtLastName" /></label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="txtLastName"
						placeholder='Last Name' value="" name="txtLastName" required>
					<span style="font-size: 11px; color: red" id="errorTxtLastName"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="txtEmail" class="col-sm-3 control-label"><spring:message
						code="lbl.user.addUserDialog.txtEmail" /></label>
				<div class="col-sm-9">
					<input type="email" class="form-control" id="txtEmail"
						placeholder='Email' value="" name="txtEmail" required> <span
						style="font-size: 11px; color: red" id="errorTxtEmail"></span>
				</div>
			</div>
		</div>

		<div class="right" style="width: 50%; float: right">
			<div class="form-group">
				<label for="txtPhone" class="col-sm-3 control-label"><spring:message
						code="lbl.user.addUserDialog.txtPhone" /></label>
				<div class="col-sm-9">
					<input maxlength="50" type="text" class="form-control" id="txtPhone"
						placeholder='Phone' value="" name="txtPhone" required> <span
						style="font-size: 11px; color: red" id="errorTxtPhone"></span>
				</div>
			</div>

			<div class="form-group">
				<label for="txtAgent" class="col-sm-3 control-label"><spring:message
						code="lbl.user.addUserDialog.txtAgent" /></label>
				<div class="col-sm-9">
					<select id="ddlAgent">
					</select>
				</div>
			</div>

			<div class="form-group">
				<label for="txtRole" class="col-sm-3 control-label"><spring:message
						code="lbl.user.addUserDialog.txtRole" /></label>
				<div class="col-sm-9">
					<select id="ddlRole">
					</select>
				</div>
			</div>

			<div class="form-group">
				<label for="txtStatus" class="col-sm-3 control-label"><spring:message
						code="lbl.user.addUserDialog.txtStatus" /></label>
				<div class="col-sm-9">
					<select id="txtStatus">
						<option value="Active"><spring:message
								code="lbl.user.addUserDialog.txtStatus.Active" /></option>
						<option value="In-Active"><spring:message
								code="lbl.user.addUserDialog.txtStatus.In-Active" /></option>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label for="txtCCEmail" class="col-sm-3 control-label"><spring:message
						code="lbl.user.addUserDialog.txtCCEmail" /></label>
				<div class="col-sm-9">
					<input type="checkbox" id="chkCCmailstring">
				</div>
			</div>
		</div>
		<div style="clear: both;"></div>
	</div>
</div>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="${pageContext.request.contextPath}/resources/userJs/user/userProfile.js"></script>
<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left"><spring:message code="lbl.userProfile.userProfileTitle"/></div>
		</div>
		<div class="block-content collapse in">
			<div class="span9">
				<div class="" id="userProfile">
					<fieldset>
					<!-- 
						<legend><spring:message code="lbl.userProfile.userProfileTitle"/></legend> -->

						<div class="form-group">
							<label class="col-lg-2 control-label"><spring:message code="lbl.userProfile.userProfile.userName"/></label>
							<div class="col-lg-10">
								<input class="form-control" id="userName" type="text" disabled>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label"><spring:message code="lbl.userProfile.userProfile.firstName"/></label>
							<div class="col-lg-10">
								<input class="form-control" id="firstName" type="text" disabled>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label"><spring:message code="lbl.userProfile.userProfile.lastName"/></label>
							<div class="col-lg-10">
								<input class="form-control" id="lastName" type="text" disabled>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label"><spring:message code="lbl.userProfile.userProfile.email"/></label>
							<div class="col-lg-10">
								<input class="form-control" id="email" type="text" disabled>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label"><spring:message code="lbl.userProfile.userProfile.choriShortName"/></label>
							<div class="col-lg-10">
								<input class="form-control" id="choriShortName" type="text"
									disabled>
							</div>
						</div>

						<div class="form-group">
							<label class="col-lg-2 control-label"><spring:message code="lbl.userProfile.userProfile.phone"/></label>
							<div class="col-lg-10">
								<input class="form-control" id="phone" type="text" disabled>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-2 control-label"><spring:message code="lbl.userProfile.userProfile.chkCCmailstring"/></label>
							<div class="col-lg-10">
								<input type="checkbox" id="chkCCmailstring" disabled>
							</div>
						</div>
					</fieldset>
				</div>
				<br>
				<button id="btnEditProfile" class="btn btn-info"><spring:message code="lbl.userProfile.btnEditProfile"/></button>
				<button id="btnEditPassword" class="btn btn-info"><spring:message code="lbl.userProfile.btnEditPassword"/></button>
				
				<!-- dialog to edit password -->
				<div id="editPasswordDialog" style="display: none;" name="editPasswordDialog">
					<div class="form-group">
						<label for="currentPassword" class="col-sm-3 control-label"><spring:message code="lbl.userProfile.editPasswordDialog.currentPassword"/>
							<span style="color: red;">*</span>
						</label>
						<div class="col-sm-9">
							<input type="password" class="form-control" id="currentPassword"
								placeholder='Enter current password' value="" name="currentPassword" maxlength="100" required>
							<p style="font-size: 11px; color: red"
								id="errorCurrentPassword"></p>
						</div>
					</div>
					
					<div class="form-group">
						<label for="newPassword" class="col-sm-3 control-label"><spring:message code="lbl.userProfile.editPasswordDialog.newPassword"/>
							<span style="color: red;">*</span>
						</label>
						<div class="col-sm-9">
							<input type="password" class="form-control" id="newPassword"
								placeholder='Enter new password' value="" name="newPassword" maxlength="100" required>
							<p style="font-size: 11px; color: red"
								id="errorNewPassword"></p>
						</div>
					</div>
					
					<div class="form-group">
						<label for="retypePassword" class="col-sm-3 control-label"><spring:message code="lbl.userProfile.editPasswordDialog.retypePassword"/>
							<span style="color: red;">*</span>
						</label>
						<div class="col-sm-9">
							<input type="password" class="form-control" id="retypePassword"
								placeholder='Enter new password' value="" name="retypePassword" maxlength="100" required>
							<p style="font-size: 11px; color: red"
								id="errorRetypePassword"></p>
						</div>
					</div>
				</div>
				
				<!-- This is dialog to edit Role -->
				<div id="editUserDialog" style="display: none;"
					name="editUserDialog">
							<div class="form-group">
								<label for="txtFirstName" class="col-sm-3 control-label"><spring:message code="lbl.userProfile.editUserDialog.txtFirstName"/></label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="txtFirstName"
										placeholder='First name' value="" name="txtFirstName" maxlength="50" required>
									<p style="font-size: 11px; color: red"
										id="errorTxtFirstName"></p>
								</div>
							</div>

							<div class="form-group">
								<label for="txtLastName" class="col-sm-3 control-label"><spring:message code="lbl.userProfile.editUserDialog.txtLastName"/></label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="txtLastName"
										placeholder='Last Name' value="" name="txtLastName" maxlength="50" required>
									<p style="font-size: 11px; color: red" id="errorTxtLastName"></p>
								</div>
							</div>

							<div class="form-group">
								<label for="txtEmail" class="col-sm-3 control-label"><spring:message code="lbl.userProfile.editUserDialog.txtEmail"/></label>
								<div class="col-sm-9">
									<input type="email" class="form-control" id="txtEmail"
										placeholder='Email' value="" name="txtEmail" maxlength="50" required>
									<p style="font-size: 11px; color: red" id="errorTxtEmail"></p>
								</div>
							</div>

							<div class="form-group">
								<label for="txtEmailPassword" class="col-sm-3 control-label"><spring:message code="lbl.userProfile.editUserDialog.txtEmailPassword"/></label> <input type="checkbox" id="chkEmailPassword">
								<div class="col-sm-9">
									<input type="password" class="form-control" id="txtEmailPassword"
										placeholder="Email's Password" value="" name="txtEmailPassword" maxlength="100"
										required> <p style="font-size: 11px; color: red"
										id="errorTxtEmailPassword"></p>
								</div>
							</div>

							<div class="form-group">
								<label for="txtPhone" class="col-sm-3 control-label"><spring:message code="lbl.userProfile.editUserDialog.txtPhone"/></label>
								<div class="col-sm-9">
									<input maxlength="50" type="text" class="form-control" id="txtPhone"
										placeholder='Phone' value="" name="txtPhone" required>
									<p style="font-size: 11px; color: red" id="errorTxtPhone"></p>
								</div>
							</div>
							
							<!-- 
							<div class="form-group">
								<label for="txtCCEmail" class="col-sm-3 control-label"><spring:message code="lbl.userProfile.editUserDialog.txtCCEmail"/></label>
								<div class="col-sm-9">
									<textarea class="form-control" id="txtCCEmail"
										placeholder='Email' name="txtCCEmail" maxlength="500" required></textarea>
									<p style="font-size: 11px; color: red" id="errorTxtCCEmail"></p>
								</div>
							</div>
							 -->
				</div>
			</div>
		</div>
	</div>
	<!-- /block -->
</div>

<!-- This div is used to show message to user -->
<div id="messageDialog" style="display: none;">
	<span id="messageContent"></span>
</div>
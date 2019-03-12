<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- block -->
<div class="block">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">
			<spring:message code="partnerinfo.form.title.text">
			</spring:message>
		</div>
	</div>
	<div class="block-content collapse in">
		<div class="span12">
			<form:form id="" class="form-horizontal" method="POST" action="save"
				modelAttribute="partnerInfo">
				<fieldset>
					<div class="control-group">
						<label class="control-label" for="focusedInput"><spring:message
								code="partnerinfo.form.code.text">
							</spring:message></label>
						<div class="controls">
							<form:input class="input-small focused" id="focusedInput"
								path="code" type="text" />
							<form:errors path="code" class="alert alert-error" />

						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="focusedInput"><spring:message
								code="partnerinfo.form.name.text">
							</spring:message></label>
						<div class="controls">
							<form:input class="input-small focused" id="focusedInput"
								path="name" type="text" />
							<form:errors path="name" class="alert alert-error" />
						</div>
					</div>
					<div class="form-actions">
						<button type="submit" class="btn btn-primary">
							<spring:message code="common.form.save.button">
							</spring:message>
						</button>
						
						<button id="button-back" type="button" class="btn btn-info">
							<spring:message code="common.form.back.button">
							</spring:message>
							<script>
								$('#button-back').click(function() {
									$("form:first").attr('action', 'back');
									$("form:first").submit();
								});
							</script>
						</button>
						<button type="reset" class="btn">
							<spring:message code="common.form.reset.button">
							</spring:message>
						</button>
					</div>
				</fieldset>
			</form:form>

		</div>
	</div>

</div>

<!-- /block -->
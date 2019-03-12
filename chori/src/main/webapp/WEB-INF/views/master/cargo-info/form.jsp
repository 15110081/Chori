<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- block -->
<div class="block">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">
			<spring:message code="cargoinfo.form.title.text">
			</spring:message>
		</div>
	</div>
	<c:url var="addAction" value="/CargoInfo/save"></c:url>
	<div class="block-content collapse in">
		<div class="span12">
			<form:form id="" class="form-horizontal" method="POST"
				action="${addAction}" modelAttribute="cargoInfo">
				<fieldset>
					<form:hidden path="id" />
					<div class="control-group">
						<label class="control-label" for="focusedInput"> <spring:message
								code="cargoinfo.form.type.text">
							</spring:message>
						</label>
						<div class="controls">
							<!-- 
							<input class="input-small focused" id="focusedInput" name="code"
								type="text" value="">
								 -->
							<form:input class="input-small focused" id="focusedInput"
								path="type" type="text" />
							<form:errors path="type" class="alert alert-error" />
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
								$('#button-back')
										.click(
												function() {
													window.location.href = "/BargeOperation/CargoInfo/list";
												});
							</script>
						</button>
						<button id="button-reset" class="btn">
							<spring:message code="common.form.reset.button">
							</spring:message>
							<script>
								$('#button-reset').click(function(e) {
									e.preventDefault();
									$("#focusedInput").val('');
								});
							</script>
						</button>
					</div>
				</fieldset>
			</form:form>

		</div>
	</div>
</div>
<!-- /block -->
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- block -->
<div class="block">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">System Error:</div>
	</div>
	<div class="block-content collapse in">
		<div class="span12">
			<form:form class="form-horizontal" method="POST"
				action="save" modelAttribute="qcCrane">
				<fieldset>
					<legend>Quay Crane</legend>
					<div class="control-group">
						<label class="control-label" for="focusedInput">Hoist Mark</label>
						<div class="controls">
							<form:input path="code" class="input-small focused"
								id="focusedInput" type="text" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="focusedInput">Trolley
							Mark</label>
						<div class="controls">
							<form:input path="name" class="input-small focused"
								id="focusedInput" type="text" />
						</div>
					</div>
					<div class="form-actions">
						<button type="submit" class="btn btn-primary">Save
							changes</button>
						<button type="reset" class="btn">Cancel</button>
					</div>
				</fieldset>
			</form:form>

		</div>
	</div>
</div>
<!-- /block -->
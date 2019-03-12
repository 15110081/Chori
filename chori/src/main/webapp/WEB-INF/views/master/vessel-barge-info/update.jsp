<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- validation -->
<div class="row-fluid">
	<!-- block -->
	<!-- 
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">Vessel/Barge Information</div>
		</div>
		<c:url var="addAction" value="/VesselBargeInfo/save" ></c:url>
		<div class="block-content collapse in">
			<div class="span12">
			<form:form id="" class="form-horizontal" method="POST" action="${addAction}" modelAttribute="vbInfo">
				<fieldset>
					<div class="control-group">
						<label class="control-label" for="focusedInput">Vessel code (callID)</label>
						<div class="controls">
							<form:input class="input-small focused" id="focusedInput"
								path="vesselCallId" type="text"/>
							<form:errors path="vesselCallId" class="alert alert-error" />

						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="focusedInput">Name</label>
						<div class="controls">
							<form:input class="input-small focused" id="focusedInput"
								path="name" type="text" />
							<form:errors path="name" class="alert alert-error" />

						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="focusedInput">Voyage</label>
						<div class="controls">
							<form:input class="input-small focused" id="focusedInput"
								path="voyage" type="text" />
							<form:errors path="voyage" class="alert alert-error" />

						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="focusedInput">Vessel Code</label>
						<div class="controls">
							<form:input class="input-small focused" id="focusedInput"
								path="vesselCode" type="text" />
							<form:errors path="vesselCode" class="alert alert-error" />

						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label" for="focusedInput">ETB</label>
						<div class="controls">
							<form:input class="input-small focused" id="ETB"
								path="etb" type="text"/>
							<form:errors path="etb" class="alert alert-error" />

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
									window.location.href="/BargeOperation/VesselBargeInfo";
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
	 -->
	<!-- /block -->
	
	<div class="block">
			<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">Vessel/Barge Information</div>
		</div>
		<c:url var="addAction" value="/VesselBargeInfo/save" ></c:url>
		<div class="block-content collapse in">
		<form:form id="" class="form-horizontal" method="POST" action="${addAction}" modelAttribute="vbInfo">

					<fieldset>
						<div class="alert alert-error hide">
							<button class="close" data-dismiss="alert"></button>
							You have some form errors. Please check below.
						</div>
						<div class="alert alert-success hide">
							<button class="close" data-dismiss="alert"></button>
							Your form validation is successful!
						</div>

						<div class="row-fluid padd-bottom">
							<div class="span3 control-group">
								<label class="control-label">Vessel Code<span
									class="required">*</span></label>
								<div class="controls">
									<form:input class="input-small focused " id="vesselCallId"
									path="vesselCallId" type="text"/>
									<form:errors path="vesselCallId" class="alert alert-error" />
								</div>			
							</div>
							<div class="span3 control-group">
								<label class="control-label">Call Seq</label>
								<div class="controls">
									<form:input class="input-small disabled" id="callSeq" 
									path="callSeq" type="text" placeholder="CATOS" />
									<form:errors path="callSeq" class="alert alert-error" />
								</div>
							</div>
							<div class="span3 control-group">
								<label class="control-label">Call Year</label>
								<div class="controls">
									<form:input class="input-small disabled" id="callYear" 
									path="callYear" type="text" placeholder="CATOS"/>
									<form:errors path="callYear" class="alert alert-error" />
								</div>
							</div>
							<div class="span2 control-group">
								<div class="controls">
									<!-- <button type="submit" class="btn btn-primary">Inquire</button> -->
								</div>
							</div>
						</div>
						<div class="row-fluid padd-bottom">
							<div class="span6 control-group">
								<label class="control-label">Vessel Name</label>
								<div class="controls">
									<form:input class="input-small" id="vesselName" 
									path="name" type="text"/>
									<form:errors path="name" class="alert alert-error" />
								</div>
							</div>
							<div class="span6 control-group">
								<label class="control-label">Vessel Voyage</label>
								<div class="controls">						
									<form:input class="input-small" id="vesselYoyage" 
									path="voyage" type="text"/>
									<form:errors path="voyage" class="alert alert-error" />
								</div>
							</div>
						</div>
						<div class="row-fluid padd-bottom">
							<div class="span6 control-group">
								<label class="control-label">Vessel Code</label>
								<div class="controls">
									<form:input class="input-small focused" id="focusedInput"
										path="vesselCode" type="text" />
									<form:errors path="vesselCode" class="alert alert-error" />
								</div>
							</div>
						</div>

						<div class="row-fluid padd-bottom">
							<div class="span4 control-group">
								<label class="control-label">ETB</label>
								<div class="controls">
									<form:input class="input-medium focused" id="ETB" path="etb" type="text"/>
									<form:errors path="etb" class="alert alert-error" />
								</div>
							</div>
							<div class="span2 control-group">
								<label class="control-label">ETD</label>
								<div class="controls">
									<input class="input-small disabled" id="etd" type="text"
										placeholder="CATOS" disabled>
								</div>
							</div>
						</div>
						<div class="row-fluid padd-bottom">
							<div class="span3 control-group">
								<label class="control-label">ATB</label>
								<div class="controls">
									<input type="text" name="abb" class="span12 m-wrap" />
								</div>
							</div>
							<div class="span3 control-group">
								<label class="control-label">ATW</label>
								<div class="controls">
									<input type="text" name="atw" class="span12 m-wrap" />
								</div>
							</div>
							<div class="span3 control-group">
								<label class="control-label">ATC</label>
								<div class="controls">
									<input type="text" name="atc" class="span12 m-wrap" />
								</div>
							</div>
							<div class="span3 control-group">
								<label class="control-label">ATD</label>
								<div class="controls">
									<input type="text" name="atd" class="span12 m-wrap" />
								</div>
							</div>
						</div>

						<div class="row-fluid padd-bottom">
							<div class="span2 control-group">
								<label class="control-label">Gross Ton</label>
								<div class="controls">
									<input class="input-small disabled" id="grossTon" type="text"
										placeholder="CATOS" disabled>
								</div>
							</div>
							<div class="span2 control-group">
								<label class="control-label">LOA</label>
								<div class="controls">
									<input class="input-small disabled" id="loa" type="text"
										placeholder="CATOS" disabled>
								</div>
							</div>
							<div class="span2 control-group">
								<label class="control-label">Wharf Mark</label>
								<div class="controls">
									<input class="input-small disabled" id="wharfMark" type="text"
										placeholder="CATOS" disabled>
								</div>
							</div>
						</div>
						<div class="row-fluid padd-bottom">
							<div class="span4 control-group">
								<label class="control-label">Arrival Draft F/A</label>
								<div class="controls">
									<input type="text" name="arrivalDraftF" class="span6 m-wrap  input-small" />/<input
										type="text" name="arrivalDraftA" class="span6 m-wrap input-small" />
								</div>
							</div>
							<div class="span4 control-group">
								<label class="control-label">Departure Draft F/A</label>
								<div class="controls">
									<input type="text" name="departureDraftF" class="span6 m-wrap input-small" />/<input
										type="text" name="departureDraftA" class="span6 m-wrap input-small" />
								</div>
							</div>
						</div>
						<div class="row-fluid padd-bottom">
							<div class="span2 control-group">
								<label class="control-label">Barge/Vessel</label>
							</div>
							<div class="span3 control-group">
								<label class="control-label"><input type="radio"
									name="barge" class="span2" />Barge</label> <label
									class="control-label"><input type="radio" name="barge"
									class="span2" />Vessel</label>
							</div>
						</div>
						<div class="row-fluid padd-bottom">
							<div class="span2 control-group">
								<label class="control-label">Import/Export</label>
							</div>
							<div class="span3 control-group">
								<label class="control-label"><input type="radio"
									name="barge" class="span2" />Import</label> <label
									class="control-label"><input type="radio" name="barge"
									class="span2" />Export</label>
							</div>
						</div>
						<div class="row-fluid padd-bottom">
							<div class="span2 control-group">
								<label class="control-label">Use Port WB</label>
							</div>
							<div class="span3 control-group">
								<label class="control-label"><input type="radio"
									name="barge" class="span2" />Yes</label> <label
									class="control-label"><input type="radio" name="barge"
									class="span2" />No</label>
							</div>
						</div>
						<div class="row-fluid padd-bottom">
						<div class="control-group">
								<label class="control-label">Trolley Marked</label>
								<div class="controls">
									<form:input class="input-small disabled" id="TrolleyMark" 
									path="trolleyMark" type="text" placeholder="CATOS" />
									<form:errors path="trolleyMark" class="alert alert-error" />
								</div>
							</div>
						</div>
						<div class="row-fluid padd-bottom">
							<div class=" control-group">
								<label class="control-label">Hoist Marked</label>
								<div class="controls">
									<form:input class="input-small disabled" id="HoistMark"
									path="hoistMark" type="text" placeholder="CATOS" />
									<form:errors path="hoistMark" class="alert alert-error" />
								</div>
							</div>
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
									window.location.href="/BargeOperation/VesselBargeInfo";
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
<!-- /validation -->
<script>
$('#ETB').datetimepicker({
	controlType: 'select',
	dateFormat: 'dd/mm/yy',
	timeFormat: 'hh:mm:ss'
});
$("#TrolleyMark").numeric(",");
$("#HoistMark").numeric(",");
</script>


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- block -->
<div class="block">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">B/L Infomation</div>
	</div>
	<div class="block-content collapse in">
		<div class="span12">
			<form class="form-horizontal">
				<fieldset>
					<div class="control-group">
						<label class="control-label" for="focusedInput">B/L NO</label>
						<div class="controls">
							<input class="input-xlarge focused" id="focusedInput" type="text"
								value="">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="focusedInput">Partner</label>
						<div class="controls">
							<select id="selectError">
								<option>DELUS</option>
								<option>ABC</option>
							</select> <span class="help-inline">Partner Name: Delus Asia</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="focusedInput">Cargo Type</label>
						<div class="controls">
							<select id="selectError">
								<option>SMB</option>
								<option>ABM</option>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="focusedInput">Volume per B/L</label>
						<div class="controls">
							<input class="input-xlarge focused" id="focusedInput" type="text"
								value="">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="focusedInput">% Warehouse</label>
						<div class="controls">
							<input class="input-xlarge focused" id="focusedInput" type="text"
								value="">
								<span class="help-inline">%</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="focusedInput">Status</label>
						<div class="controls">
							<select id="selectError">
								<option>Allow</option>
								<option>DisAllow</option>
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="focusedInput">Remark</label>
						<div class="controls">
							<input class="input-xlarge focused" id="focusedInput" type="text"
								value="">
						</div>
					</div>
					 <div class="control-group">
                                          <label class="control-label" for="optionsCheckbox">Warehouse</label>
                                          <div class="controls">
                                            <label class="uniform">
                                              <input class="uniform_on" type="checkbox" id="optionsCheckbox" value="option1">
                                              Is in warehouse
                                            </label>
                                          </div>
                                        </div>
					<div class="form-actions">
						<button type="submit" class="btn btn-primary">Save
							changes</button>
						<button type="reset" class="btn">Cancel</button>
					</div>
				</fieldset>
			</form>

		</div>
	</div>
</div>
<!-- /block -->
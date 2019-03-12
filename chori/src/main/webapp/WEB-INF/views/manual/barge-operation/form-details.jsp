<!-- block -->
<div class="block">
	<div class="navbar navbar-inner block-header">
		<div class="muted pull-left">Hold Information</div>
	</div>
	<div class="block-content collapse in">
		<div class="span12">
			<form class="form-horizontal">
				<fieldset>
					<div class="control-group">
						<label class="control-label" for="focusedInput">Barge Name</label>
						<div class="controls">
							<input class="input-xlarge focused" id="focusedInput" type="text"
								value="">
						</div>
					</div>
					<div class="row-fluid padd-bottom">
						<div class="span3 control-group">
							<label class="control-label">Captain Name</label>
							<div class="controls">
								<input class="input-small" id="captainName" type="text">
							</div>
						</div>
						<div class="span4 control-group">
							<label class="control-label">ATB</label>
							<div class="controls">
								<input class="input-small" id="atb" type="text">
							</div>
						</div>
					</div>
					<div class="row-fluid padd-bottom">
						<div class="span3 control-group">
							<label class="control-label">B/L No</label>
							<div class="controls">
								<select id="selectError">
									<option>YC6;YC7</option>
									<option>fd</option>
								</select>
							</div>
						</div>
						<div class="span4 control-group">
							<label class="control-label">ATW</label>
							<div class="controls">
								<input class="input-small" id="atb" type="text"
									placeholder="Date Time of 1st of grab">
							</div>
						</div>
					</div>
					<div class="row-fluid padd-bottom">
						<div class="span3 control-group">
							<label class="control-label">Partner</label>
							<div class="controls">
								<span>Deluxe Asian</span>
							</div>
						</div>
						<div class="span4 control-group">
							<label class="control-label">ATC</label>
							<div class="controls">
								<input class="input-small" id="atc" type="text"
									placeholder="Date Time of last of grab">
							</div>
						</div>
					</div>
					<div class="row-fluid padd-bottom">
						<div class="span3 control-group">
							<label class="control-label">Cargo Type</label>
							<div class="controls">
								<span>SBM</span>
							</div>
						</div>
						<div class="span4 control-group">
							<label class="control-label">Received Cargo From</label>
							<div class="controls">
								<input class="input-small" id="cargoFrom" type="text"
									placeholder="SP-PSA" value="SP-PSA">
							</div>
						</div>
					</div>
					<div class="row-fluid padd-bottom">

						<div class="span6 control-group">
							<label class="control-label">Required Volume (Kg)</label>
							<div class="controls">
								<input type="radio" name="barge" class="span2" value="required" />
								<input class="input-xlarge focused" id="focusedInput"
									type="text" value=""> <input type="radio" name="barge"
									class="span2" value="notrequired" />MAX
							</div>

						</div>
						<div class="span3 control-group">
							<label class="control-label">Scale Type</label>
							<div class="controls">
								<input class="input-small" id="scaleType" type="text"
									value="CAN TREO">
							</div>
						</div>
					</div>
					<div class="row-fluid padd-bottom">
						<div class="span4 control-group">
							<label class="control-label">Delivered Volume (Kg)</label>
							<div class="controls">
								<input class="input-xlarge focused" id="focusedInput" type="text"
								value="">
							</div>
						</div>
						<div class="span4 control-group">
							<label class="control-label">Total of Grab</label>
							<div class="controls">
								<input class="input-xlarge focused" id="focusedInput" type="text"
								value="31">
							</div>
						</div>
						<div class="span4 control-group">
							<label class="control-label">Cargo Status</label>
							<div class="controls">
								<input class="input-small" id="cargoStatus" type="text"
									value="BINH THUONG">
							</div>
						</div>
					</div>
					
					
					<div class="row-fluid padd-bottom">
						<div class="span3 control-group">

							<div class="controls">
								<label class="control-label"><input type="radio"
									name="barge" class="span2" />Open</label>
							</div>
						</div>
						<div class="span3 control-group">
							<div class="controls">
								<label class="control-label"><input type="radio"
									name="barge" class="span2" />Under Ops</label>
							</div>
						</div>
						<div class="span3 control-group">
							<div class="controls">
								<label class="control-label"><input type="radio"
									name="barge" class="span2" />Finished</label>
							</div>
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
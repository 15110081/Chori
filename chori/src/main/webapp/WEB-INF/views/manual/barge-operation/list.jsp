
<div class="row-fluid">
	<!-- block -->
	<div class="row-fluid padd-bottom">
		<div class="span3 control-group success">
			<label class="control-label" for="selectError">Vessel Code</label>
			<div class="controls">
				<select id="selectError">
					<option>1</option>
					<option>2</option>
					<option>3</option>
					<option>4</option>
					<option>5</option>
				</select>
			</div>
		</div>
		<div class="span3 control-group success">
			<label class="control-label" for="selectError">Call Seq</label>
			<div class="controls">
				<select id="selectError">
					<option>1</option>
					<option>2</option>
					<option>3</option>
					<option>4</option>
					<option>5</option>
				</select>
			</div>
		</div>
		<div class="span3 control-group success">
			<label class="control-label" for="selectError">Call Year</label>
			<div class="controls">
				<select id="selectError">
					<option>1</option>
					<option>2</option>
					<option>3</option>
					<option>4</option>
					<option>5</option>
				</select>
			</div>
		</div>
		<div class="span3 control-group success">
			<div class="row-fluid">
				<div class="span12 control-group success">
					<label class="control-label" for="selectError">Vessel Name:</label>
					<div class="controls">
						<span class="help-inline">Golden Fortune</span>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span12 control-group success">
					<label class="control-label" for="selectError">ETB:</label>
					<div class="controls">
						<span class="help-inline">Golden Fortune</span>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">Voyage Cargo Information List</div>
		</div>

		<div class="block-content collapse in">
			<div class="span12">
				<div class="table-toolbar">
					<div class="btn-group">
						<a href="createVoyageBargeOperation"><button
								class="btn btn-success">
								Add New <i class="icon-plus icon-white"></i>
							</button></a>
					</div>
					<div class="btn-group pull-right">
						<button data-toggle="dropdown" class="btn dropdown-toggle">
							Tools <span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
							<li><a href="#">Print</a></li>
							<li><a href="#">Save as PDF</a></li>
							<li><a href="#">Export to Excel</a></li>
						</ul>
					</div>
				</div>

				<table cellpadding="0" cellspacing="0" border="0"
					class="table table-striped table-bordered" id="example2">
					<thead>
						<tr>
							<th>No</th>
							<th>Barge Name</th>
							<th>B/L No</th>
							<th>Partner</th>
							<th>Cargo Type</th>
							<th>Required Volume(Kg)</th>
							<th>Delivered Volume(Kg)</th>
							<th>Hold No</th>
							<th>ATB</th>
							<th>ATW</th>
							<th>ATC</th>
							<th>Status</th>
							<th>Remark</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<tr class="odd gradeX">
								<th>No</th>
							<th>Barge Name</th>
							<th>B/L No</th>
							<th>Partner</th>
							<th>Cargo Type</th>
							<th>Required Volume(Kg)</th>
							<th>Delivered Volume(Kg)</th>
							<th>Hold No</th>
							<th>ATB</th>
							<th>ATW</th>
							<th>ATC</th>
							<th>Status</th>
							<th>Remark</th>
							<th>
								<div class="btn-group pull-right">
									<button data-toggle="dropdown"
										class="btn btn-primary dropdown-toggle">
										Action <span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li><a href="editManualBargeOperation">Edit</a></li>
										<li><a href="listManualBargeOperationGrabDetails">Grab details</a></li>
									</ul>
								</div>
							</th>
						</tr>
						<tr class="even gradeC">
														<th>No</th>
							<th>Barge Name</th>
							<th>B/L No</th>
							<th>Partner</th>
							<th>Cargo Type</th>
							<th>Required Volume(Kg)</th>
							<th>Delivered Volume(Kg)</th>
							<th>Hold No</th>
							<th>ATB</th>
							<th>ATW</th>
							<th>ATC</th>
							<th>Status</th>
							<th>Remark</th>
							<th>
								<div class="btn-group pull-right">
									<button data-toggle="dropdown"
										class="btn btn-primary dropdown-toggle">
										Action <span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li><a href="editManualBargeOperation">Edit</a></li>
										<li><a href="#">Grab details</a></li>
									</ul>
								</div>
							</th>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- /block -->
</div>

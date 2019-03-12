
<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">Manual Barge Operation Grab
				Information</div>
		</div>
		<div class="block-content collapse in">
			<div class="form-horizontal">
				<fieldset>


					<div class="row-fluid padd-bottom padd-top">
						<div class="span3 control-group">
							<label class="control-label">Vessel</label>
							<div class="controls">
								<input class="input-small disabled" id="etb" type="text"
									placeholder="Golden Fortune" disabled>
							</div>
						</div>
						<div class="span3 control-group">
							<label class="control-label">Voyage</label>
							<div class="controls">
								<input class="input-small disabled" id="etb" type="text"
									placeholder="N12345" disabled>
							</div>
						</div>
					</div>

					<div class="row-fluid padd-bottom padd-top">
						<div class="span3 control-group">
							<label class="control-label">Barge Name</label>
							<div class="controls">
								<input class="input-small disabled" id="etb" type="text"
									placeholder="SG12345" disabled>
							</div>
						</div>
						
					</div>
					<div class="row-fluid padd-bottom padd-top">
						<div class="span3 control-group">
							<label class="control-label">Status</label>
							<div class="controls">
								<input class="input-small disabled" id="etb" type="text"
									placeholder="Finish" disabled>
							</div>
						</div>
						
					</div>
					<div class="row-fluid padd-bottom padd-top">
						<div class="span3 control-group">
							<div class="controls">
								<input class="input-small disabled" id="etb" type="checkbox"
									>Delivered
							</div>
						</div>
						<div class="span3 control-group">
							<div class="controls">
								<input class="input-small disabled" id="etb" type="checkbox"
									>Return
							</div>
						</div>
					</div>
				</fieldset>
			</div>
		</div>
	</div>

	<!-- /block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">Manual Barge Operation Grab List</div>
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
							<th>Grab No</th>
							<th>Date Time</th>
							<th>Weight</th>
							<th>Status</th>
							<th>Picture</th>
							<th>Created user</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<tr class="odd gradeX">
							<th>Grab No</th>
							<th>Date Time</th>
							<th>Weight</th>
							<th>Status</th>
							<th>Picture</th>
							<th>Created user</th>
							<th>
								<div class="btn-group pull-right">
									<button data-toggle="dropdown"
										class="btn btn-primary dropdown-toggle">
										Action <span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li><a href="editManualBargeOperationGrab">Edit...</a></li>
									</ul>
								</div>
							</th>
						</tr>
						<tr class="even gradeC">
							<th>Grab No</th>
							<th>Date Time</th>
							<th>Weight</th>
							<th>Status</th>
							<th>Picture</th>
							<th>Created user</th>
							<th>
								<div class="btn-group pull-right">
									<button data-toggle="dropdown"
										class="btn btn-primary dropdown-toggle">
										Action <span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li><a href="editManualBargeOperationGrab">Edit...</a></li>
									</ul>
								</div>
							</th>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</div>

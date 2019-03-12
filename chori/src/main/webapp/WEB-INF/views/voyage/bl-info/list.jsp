<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
			<div class="muted pull-left">Voyage Hold Information List</div>
		</div>

		<div class="block-content collapse in">
			<div class="span12">
				<div class="table-toolbar">
					<div class="btn-group">
						<a href="<c:url value='/BillOfLading/createBillOfLading' />"><button
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
							<th>Hold No</th>
							<th>Cargo Type</th>
							<th>Volume (kg)</th>
							<th>Remark</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<tr class="odd gradeX">
							<th>No</th>
							<th>Hold No</th>
							<th>Cargo Type</th>
							<th>Volume (kg)</th>
							<th>Remark</th>
							<th>
								<div class="btn-group pull-right">
									<button data-toggle="dropdown"
										class="btn btn-primary dropdown-toggle">
										Action <span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li><a href="#">Print</a></li>
										<li><a href="#">Save as PDF</a></li>
										<li><a href="#">Export to Excel</a></li>
									</ul>
								</div>
							</th>
						</tr>
						<tr class="even gradeC">
							<th>No</th>
							<th>Hold No</th>
							<th>Cargo Type</th>
							<th>Volume (kg)</th>
							<th>Remark</th>
							<th>
								<div class="btn-group pull-right">
									<button data-toggle="dropdown"
										class="btn btn-primary dropdown-toggle">
										Action <span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li><a href="#">Print</a></li>
										<li><a href="#">Save as PDF</a></li>
										<li><a href="#">Export to Excel</a></li>
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


	<!-- this is add BL dialog -->
	<div class="block" id="addBLDialog" style="display: none;">
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
								<input class="input-xlarge focused" id="focusedInput"
									type="text" value="">
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
							<label class="control-label" for="focusedInput">Cargo
								Type</label>
							<div class="controls">
								<select id="selectError">
									<option>SMB</option>
									<option>ABM</option>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="focusedInput">Volume
								per B/L</label>
							<div class="controls">
								<input class="input-xlarge focused" id="focusedInput"
									type="text" value="">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="focusedInput">%
								Warehouse</label>
							<div class="controls">
								<input class="input-xlarge focused" id="focusedInput"
									type="text" value=""> <span class="help-inline">%</span>
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
								<input class="input-xlarge focused" id="focusedInput"
									type="text" value="">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="optionsCheckbox">Warehouse</label>
							<div class="controls">
								<label class="uniform"> <input class="uniform_on"
									type="checkbox" id="optionsCheckbox" value="option1">
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
	<!-- end of add BL dialog -->

</div>

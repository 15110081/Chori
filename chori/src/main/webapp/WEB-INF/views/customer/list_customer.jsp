<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="resources/userJs/status/list.js"></script>
<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">Status List</div>
		</div>
		<div class="block-content collapse in">
			<div class="span12">
				<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered display responsive" id="listStatus">
					<thead>
						<tr>
							<th>Code</th>
							<th>Name</th>
							<th>Description</th>
							<th>Action</th>
						</tr>
					</thead>
					
				</table>
			</div>
		</div>
	</div>
	<!-- /block -->
</div>
	<!-- dialog when edit status-->
	<div id="dialogEditStatus" class="form-horizontal" style="display: none;" name="editStatusForm">
		<div class="form-group">
	       <label for="txtStatusID" class="col-sm-3 control-label">Status Code</label>
	       <div class="col-sm-9">
	           <input type="text" class="form-control" id="txtStatusID" value="" name="txtStatusID" disabled>
	       </div>
	    </div>
		<div class="form-group">
	       <label for="txtStatusName" class="col-sm-3 control-label">Status Name</label>
	       <div class="col-sm-9">
	           <input type="text" class="form-control" id="txtStatusName" value="" name="txtStatusName" disabled>
	       </div>
	    </div>
	    <div class="form-group">
	       <label for="txtStatusDescription" class="col-sm-3 control-label">Status Description</label>
	       <div class="col-sm-9">
	           <textarea type="text" class="form-control" id="txtStatusDescription" value="" name="txtStatusDescription">
	       </div>
	    </div>
	</div>
	
	<!-- dialog show when edit status success-->
	<div id="dialogStatusEditSuccess" style="display: none;" class="form-horizontal">
	   <div class="form-group">
		<h4 style="text-align: center; font-style: bold">Edit Status Successful!</h4>
		</div>
	</div>

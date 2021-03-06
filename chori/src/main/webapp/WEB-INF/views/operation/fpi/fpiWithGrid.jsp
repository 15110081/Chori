<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="resources/userJs/garmentstyle/dataTables.rowsGroup.js"></script>
<script src="resources/userJs/operation/fpi/fpiGridDetail.js"></script>

<div class="row-fluid">
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">Fpi</div>
		</div>
		<div class="block-content collapse in">
			<div class="span12">
				<div id="FpiGridDetailTableCover">
					<table
						class="table table-striped table-bordered display responsive"
						id="listFpiGridDetail">
						<thead>
							<tr>
								<th rowspan="2">Total Pcs</th>
								<th rowspan="2">Color</th>
								<th colspan="3">Garment Style</th>
								<th rowspan="2">Size</th>
								<th rowspan="2">FA</th>
								<th rowspan="2">FPI</th>
							</tr>
							<tr>
								<th>Name</th>
								<th>Image</th>
								<th>Type</th>
							</tr>
						</thead>
					</table>
				</div>
				
				<br><br>
				
				<div id="PiGridDetailTableCover">
					<table
						class="table table-striped table-bordered display responsive"
						id="listPiGridDetail">
						<thead>
							<tr>
								<th rowspan="2">Total Pcs</th>
								<th rowspan="2">Color</th>
								<th colspan="3">Garment Style</th>
								<th rowspan="2">Size</th>
								<th rowspan="2">Pcs</th>
								<th rowspan="2">FA</th>
							</tr>
							<tr>
								<th>Name</th>
								<th>Image</th>
								<th>Type</th>
							</tr>
						</thead>
					</table>
				</div>
				
			</div>
		</div>
	</div>
</div>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/fancybox/jquery.mousewheel-3.0.6.pack.js"></script>

<html>
<head>
<title><spring:message code="lbl.rfpi.rfpidetailForm" /></title>
</head>

<body>

<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left"></div>
		</div>
		<input type="button" class="form-control btn btn-info" id="btnViewRFPI"
		 name="btnViewRFPI" value="<spring:message code="lbl.rfpi.btnViewRFPI" />">	
	</div>
	<!-- /block -->
</div>

	<div id="viewRFPIdialog"  style="display: none;"
		name="viewRFPIdialog">

		<div style="width: 70%; float: left">
			<div class="form-group" style ="clear:both">
				<label for="noneorderaccessories" class="col-sm-6 control-label"><spring:message
						code="lbl.rfpi.viewRFPIdialog.noneorderaccessories" /> </label>
				<div class="col-sm-6" style ="clear:both">
					<input type="checkbox" class="form-control"
						id="noneorderaccessories" name="noneorderaccessories"
						value="false" />
				</div>
				
			</div>

			<div class="form-group" style ="clear:both">
				<label for="txtCustomerName" class="col-sm-3 control-label"><spring:message
						code="lbl.rfpi.viewRFPIdialog.customername" /></label>
				<div class="col-sm-9" style ="clear:both">
					<input type="text" class="form-control" id="txtCustomerName"
						value="" name="txtCustomerName" readonly="true">
				</div>
			</div>
			<div class="form-group">
				<label for="txtConsigneeName" class="control-label col-sm-2"><spring:message
						code="lbl.rfpi.viewRFPIdialog.consigneename" /></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="txtConsigneeName"
						value="" name="txtConsigneeName" readonly="true">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="txtLotNumber"><spring:message
						code="lbl.rfpi.viewRFPIdialog.lotnumber" /></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="txtLotNumber" value=""
						name="txtLotNumber" disabled>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="txtCountry"><spring:message
						code="lbl.rfpi.viewRFPIdialog.country" /></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="txtCountry" value=""
						name="txtCountry" disabled>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="txtDestination"><spring:message
						code="lbl.rfpi.viewRFPIdialog.destination" /></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="txtDestination"
						value="" name="txtDestination" readonly="true">
				</div>
			</div>
			<br/>
			<br/>
			<div id="RFPIGridDetailTableCover">
				<table class="table table-striped table-bordered display responsive"
					id="listRFPIGridDetail">
					<thead>
						<tr>
							<th rowspan="2"><spring:message
									code="lbl.rfpi.viewRFPIdialog.row1" /></th>
							<th rowspan="2"><spring:message
									code="lbl.rfpi.viewRFPIdialog.row2" /></th>
							<th colspan="5"><spring:message
									code="lbl.rfpi.viewRFPIdialog.row3" /></th>
							<th rowspan="2"><spring:message
									code="lbl.rfpi.viewRFPIdialog.row4" /></th>
							<th rowspan="2"><spring:message
									code="lbl.rfpi.viewRFPIdialog.row5" /></th>
						</tr>
						<tr>
							<th><spring:message code="lbl.rfpi.viewRFPIdialog.row6" /></th>
							<th><spring:message code="lbl.rfpi.viewRFPIdialog.row7" /></th>
							<th><spring:message code="lbl.rfpi.viewRFPIdialog.row8" /></th>
							<th><spring:message code="lbl.rfpi.viewRFPIdialog.row9" /></th>
							<th><spring:message code="lbl.rfpi.viewRFPIdialog.row10" /></th>
						</tr>
					</thead>
				</table>
			</div>
			<div class="form-group">
				<div class="col-sm-9">
					<input class="btn btn-primary" type="button" class="form-control"
						id="btnFabricRemainEdit" value="Show" name="btnFabricRemainEdit">

				</div>
			</div>
			<div class="form-group" id="tableListFabricDetail" hidden="true">
				<div class="block-content collapse in">
					<div class="span8">
						<table
							class="table table-striped table-bordered display responsive"
							id="listFabricDetail">
							<thead>
								<tr>
									<th><spring:message
											code="lbl.rfpi.viewRFPIdialog.listFabricDetail.row1" /></th>
									<th><spring:message
											code="lbl.rfpi.viewRFPIdialog.listFabricDetail.row2" /></th>
									<th><spring:message
											code="lbl.rfpi.viewRFPIdialog.listFabricDetail.row3" /></th>
									<th><spring:message
											code="lbl.rfpi.viewRFPIdialog.listFabricDetail.row4" /></th>
									<th><spring:message
											code="lbl.rfpi.viewRFPIdialog.listFabricDetail.row5" /></th>
									<th><spring:message
											code="lbl.rfpi.viewRFPIdialog.listFabricDetail.row6" /></th>
									<th><spring:message
											code="lbl.rfpi.viewRFPIdialog.listFabricDetail.row7" /></th>
									<th><spring:message
											code="lbl.rfpi.viewRFPIdialog.listFabricDetail.row8" /></th>
									<th><spring:message
											code="lbl.rfpi.viewRFPIdialog.listFabricDetail.row9" /></th>

								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>

		</div>

		<div class="right" style="width: 30%; float: right">

			<div class="form-group">
				<label class="control-label col-sm-2" for="txtBrandName"><spring:message
						code="lbl.rfpi.viewRFPIdialog.brand" /></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="txtBrandName" value=""
						name="txtBrandName" readonly="true">
				</div>
			</div>
			<div class="form-group">
				<label for="txtPIReceivedDate" class="control-label col-sm-2"><spring:message
						code="lbl.rfpi.viewRFPIdialog.pireceiveddate" /></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="txtPIReceivedDate"
						value="" name="txtPIReceivedDate" disabled>
				</div>
			</div>
			<div class="form-group">
				<label for="txtPIEstShipDate" class="control-label col-sm-2"><spring:message
						code="lbl.rfpi.viewRFPIdialog.piestshipdate" /></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="txtPIEstShipDate"
						value="" name="txtPIEstShipDate" disabled>
				</div>
			</div>

			<div class="form-group">
				<label for="txtMFGStartedDate" class="control-label col-sm-2"><spring:message
						code="lbl.rfpi.viewRFPIdialog.mfgstarteddate" /></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="txtMFGStartedDate"
						value="" name="txtMFGStartedDate" disabled>
				</div>
			</div>

			<div class="form-group">
				<label for="txtMFGFinishedDate" class="control-label col-sm-2"><spring:message
						code="lbl.rfpi.viewRFPIdialog.mfgfinisheddate1" /></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="txtMFGFinishedDate"
						value="" name="txtMFGFinishedDate" disabled>
				</div>
			</div>


			<div class="form-group">
				<label for="piattachedFile" class="col-sm-3 control-label"><spring:message
						code="lbl.rfpi.viewRFPIdialog.piattachedfile" /></label>
				<div class="col-sm-9">
					<input type='file' class="form-control" name='piattached'
						id='piattached' /><br />
						<a href="#" id="linkpiattached" download></a>


				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="txtFactory"><spring:message
						code="lbl.rfpi.viewRFPIdialog.factory" /></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="txtFactory" value=""
						name="txtFactory" disabled>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="txtStatus"><spring:message
						code="lbl.rfpi.viewRFPIdialog.status" /></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="txtStatus" value=""
						name="txtStatus" disabled>
				</div>
			</div>

			<div class="form-group">
				<label for="sewingguideFile" class="col-sm-3 control-label"><spring:message
						code="lbl.rfpi.viewRFPIdialog.sewingguide" /></label>
				<div class="col-sm-9">
					<input type='file' class="form-control" name='sewingguide'
						id='sewingguide' />

				</div>
			</div>
			<div class="form-group">
				<label for="packingguideFile" class="col-sm-3 control-label"><spring:message
						code="lbl.rfpi.viewRFPIdialog.packingguide" /></label>
				<div class="col-sm-9">
					<input type='file' class="form-control" name='packingguide'
						id='packingguide' /></br>

				</div>
			</div>
			<div class="form-group">
				<label for="remark" class="col-sm-3 control-label"><spring:message
						code="lbl.rfpi.viewRFPIdialog.remark" /></label>
				<div class="col-sm-9">
					<textarea id="remark" name="remark" rows="5" cols="5"></textarea>
				</div>
			</div>


			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10" align="right">
					<button type="submit" class="btn btn-primary" id="btnSubmit">
						<spring:message code="lbl.rfpi.viewRFPIdialog.btnSave" />
					</button>
					<button type="button" class="btn btn-warning" id="btnCancel">
						<spring:message code="lbl.rfpi.viewRFPIdialog.btnCancel" />
					</button>
				</div>
			</div>

		</div>

		<div class="form-group" style="margin-bottom: 20px">
			<div class="col-sm-9">
				<button id="btnLogofChange" class="btn btn-info">
					<spring:message code="lbl.rfpi.viewRFPIdialog.btnLogofChange" />
				</button>
			</div>
		</div>



	</div>
</body>
</html>
<!-- End view detail rfpi dialog -->

<!-- This div is used to show message to user -->
<div id="messageDialog" style="display: none;">
	<span id="messageContent"></span>
</div>


<!-- This div is used to show message to user -->
<div id="messageDialog" style="display: none;">
	<span id="messageContent"></span>
</div>

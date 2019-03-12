<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script
	src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
	
	<script src="resources/userJs/operation/orderaccsheetmanagement/listorderaccsheet.js"></script>
<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left"><spring:message code="lbl.orderaccsheetmanagement.lblTitle" /></div>
		</div>
		
		<div class="form-group">
			<span style="margin-left: 20px; margin-top: 10px;"><spring:message
					code="lbl.orderaccsheetmanagement.lblForDdlStatus" /></span><select id="ddlStatus"
				style="margin-left: 20px; margin-top: 10px">
				<option value="selectRequest" selected disabled><spring:message
						code="lbl.orderaccsheetmanagement.ddlStatus.selectRequest" /></option>
				<option value="All"><spring:message
						code="lbl.orderaccsheetmanagement.ddlStatus.All" /></option>
				<option value="No"><spring:message
						code="lbl.orderaccsheetmanagement.ddlStatus.No" /></option>
				<option value="Yes"><spring:message
						code="lbl.orderaccsheetmanagement.ddlStatus.Yes" /></option>
			</select>
		</div>
		
		<div class="block-content collapse in">
			<div class="span12">
				<table class="table table-striped table-bordered display responsive"
					id="listOrderAccSheetManagement">
					<thead>
						<tr>
							<th><spring:message code="lbl.orderaccsheetmanagement.list.lblNo" /></th>							
							<th><spring:message code="lbl.orderaccsheetmanagement.list.lblOrderSheetNo" /></th>
							<th><spring:message code="lbl.orderaccsheetmanagement.list.lblKind" /></th>
							<th><spring:message code="lbl.orderaccsheetmanagement.list.lblAccessorySupplier" /></th>
							<th><spring:message code="lbl.orderaccsheetmanagement.list.lblPrice" /></th>
							<th><spring:message code="lbl.orderaccsheetmanagement.list.lblPaymentStatus" /></th>
							<th><spring:message code="lbl.orderaccsheetmanagement.list.lblCreatedDate" /></th>
							<th></th>
						</tr>
					</thead>
				</table>
			</div>
		</div>

	</div>
	<!-- /block -->
</div>

<!-- This is dialog to show detail of Accessories Order Sheet Management -->
	<div id="orderAccSheetDetailDialog" style="display: none;" name="orderAccSheetDetailDialog">				
					
		<div class="form-group" >
    	<div class="block-content collapse in">
			<div class="span6">
			<div id="listOrderAccSheetDetailCover">
				<table class="table table-striped table-bordered display responsive"
					id="listOrderAccSheetDetail">
					<thead>
						<tr>
							<th><spring:message code="lbl.orderaccsheetmanagement.detail.row1" /></th>
							<th><spring:message code="lbl.orderaccsheetmanagement.detail.row2" /></th>
							<th><spring:message code="lbl.orderaccsheetmanagement.detail.row3" /></th>
							<th><spring:message code="lbl.orderaccsheetmanagement.detail.row4" /><th>
							<th><spring:message code="lbl.orderaccsheetmanagement.detail.row5" /><th>
							<th><spring:message code="lbl.orderaccsheetmanagement.detail.row6" /><th>
							<th><spring:message code="lbl.orderaccsheetmanagement.detail.row7" /><th>
						</tr>
					</thead>
				</table>
				</div>
			</div>
		</div>
	</div>
</div>
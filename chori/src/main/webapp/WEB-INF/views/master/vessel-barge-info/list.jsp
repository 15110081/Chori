<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row-fluid">
	<c:choose>
		<c:when test="${resultStatus eq 'SUCCESS'}">
			<div class="alert alert-success">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<h4>Success</h4>
				${message}
			</div>
		</c:when>
		<c:when test="${resultStatus eq 'ERROR'}">
			<div class="alert alert-error">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<h4>Fail</h4>
				${message}
			</div>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
	
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">Vessel Barge Information List</div>
		</div>
		<div class="block-content collapse in">
			<div class="span12">
				<div class="table-toolbar">
					<div class="btn-group">
						<a href="<c:url value='/VesselBargeInfo/create' />"><button class="btn btn-success">
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
							<th>Vessel Code</th>
							<th>Name</th>
							<th>ETB</th>
							<th>Action</th>
							<!-- 
							<th>Voyage</th>
							<th>Vessel Code</th>
							<th>ETBr</th>
							<th>Test</th>
							 -->
							<!-- 					
							<th>Name</th>
							<th>Voyage</th>
							<th>Code</th>
							<th>Call SEQ</th>
							<th>ETBr</th>
							<th>ETD</th>
							<th>ATB</th>
							<th>ATW</th>
							<th>ATC</th>
							<th>ATD</th>
							<th>Barge Check</th>
							<th>Action</th>
							-->
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${vbList}" var="row" varStatus="count">
							<tr
								class="${count.index % 2 == 0 ? 'odd gradeX' : 'even gradeC'}">
								<td><c:out value="${count.index+1}" /></td>
								<td>
								${row.vesselCallId}
								<span>-</span>
								${row.callSeq}
								<span>-</span>
								${row.callYear}
								</td>
								<td>${row.name}</td>		
								<td>${row.etb }</td>
								<!--  
								<td>${row.vesselCallId}</td>
								<td>${row.name}</td>
								<th>${row.voyage }</th>
								<th>${row.vesselCode }</th>			
								<th>${row.etb }</th>
								-->
								<th>
									<div class="btn-group pull-right">
										<button data-toggle="dropdown"
											class="btn btn-primary dropdown-toggle">
											Action <span class="caret"></span>
										</button>
										<ul class="dropdown-menu">
											<li><a href="<c:url value='/VesselBargeInfo/delete/${row.vesselCallId}' />">Delete</a></li>
											<li><a href="<c:url value='/VesselBargeInfo/edit/${row.vesselCallId}' />">Edit</a></li>
										</ul>
									</div>
								</th>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- /block -->
</div>

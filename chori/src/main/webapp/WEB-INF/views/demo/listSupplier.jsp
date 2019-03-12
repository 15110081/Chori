<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">Acc Supplier LIST</h1>
	<form id="frm" method="post" action="delete">
		<a href="create" type="button" class="btn btn-primary  btnAddNew"
			data-target="create"><i class="glyphicon glyphicon-plus"></i> Add
			New Supplier</a>
		<table width="100%" border="1"
			class="table table-striped table-bordered" id="example">
			<thead>
			<tr>
					<th>No.</th>
					<th>Code</th>
					<th>Short Name</th>
					<th>Long Name</th>
					<th>Phone</th>
					<th>Fax</th>
					<th>Tax No</th>
					<th>Action</th>
					
				</tr>
			</thead>
			<tbody>
			<%
				int i = 1;
			%>
			<c:forEach items="${listSuppliers}" var="each">
				<tr>
					<td><%=i++%></td>
					<td>${each.code}</td>
					<td>${each.shortname}</td>
					<td>${each.longname}</td>
					<td>${each.phone}</td>
					<td>${each.fax}</td>
					<td>${each.taxno}</td>
					<td><a  href="edit?id=${each.idSupplier}">Edit</a>
					&nbsp;
					<a href="delete/${each.idSupplier}"><i class="glyphicon glyphicon-remove"></i></a></td>
					
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</form>
	<script
		src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
	<script>
		$(function() {
			$(document).ready(function() {
				$('#example').DataTable();
			});
		});
	</script>
</body>
</html>
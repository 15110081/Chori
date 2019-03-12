<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="r" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">UPDATE Acc Supplier</h1>

	

	<form:form method="post"  modelAttribute="supplier" action="edit" id="frm">
		
			<table align="center" width="300" border="0">
			  <form:input type="hidden" path="idSupplier"></form:input>    
				<tr>
					<td><p>
						Code : <label for="txtCode"></label></td>
					<td><form:input path="code" type="text"  id="txtCode" /></td>
				</tr>
				<tr>
					<td>Short Name : <label for="txtShortName"></label></td>
					<td><form:input path="shortname" type="text"  id="txtShortName"/></td>
				</tr>
				<tr>
					<td>Long Name : <label for="txtLongName"></label></td>
					<td><form:input path="longname" type="text"  id="txtLongName"/></td>
				</tr>
				<tr>
					<td>Phone : <label for="txtPhone"></label></td>
					<td><form:input path="phone" type="text"  id="txtPhone"/></td>
				</tr>
				<tr>
					<td>Fax : <label for="txtFax"></label></td>
					<td><form:input path="fax" type="text"  id="txtFax"/></td>
				</tr>
				<tr>
					<td>Tax No : <label for="txtTaxNo"></label></td>
					<td><form:input path="taxno" type="text"  id="txtTaxNo"/></td>
				</tr>
				<tr>
				<td></td>
				  <td><button class="btn btn-primary  btnAddNew"  type="submit" name="btnSubmit" > Save</button>
				  &nbsp;
				  <a href="supplier" class="btn btn-primary  btnAddNew"> Cancel</a></td>
				  </tr>
			</table>
	</form:form>
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Registration Form</title>

<style>
.error {
	color: #ff0000;
}
</style>

</head>

<body>

	<h2>Registration Form</h2>

	<form method="POST" modelAttribute="employee">
		<input type="hidden" id="id" />
		<table>
			<tr>
				<td><label for="name">Name: </label></td>
				<td><input path="" id="name" /></td>
				<td><errors path="" cssClass="error" /></td>
			</tr>

			<tr>
				<td><label for="joiningDate">Joining Date: </label></td>
				<td><input path="" id="joiningDate" /></td>
				<td><errors path="" cssClass="error" /></td>
			</tr>

			<tr>
				<td><label for="salary">Salary: </label></td>
				<td><input path="" id="salary" /></td>
				<td><errors path="" cssClass="error" /></td>
			</tr>

			<tr>
				<td><label for="ssn">SSN: </label></td>
				<td><input path="" id="ssn" /></td>
				<td><errors path="" cssClass="error" /></td>
			</tr>

			<tr>
				<td colspan="3"><input type="submit" value="Register" /></td>
			</tr>
		</table>
	</form>
	<br />
	<br /> Go back to
	<a href="<c:url value='/list' />">List of All Employees</a>
</body>
</html>
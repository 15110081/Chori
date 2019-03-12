<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="lbl.login.Login"/></title>
</head>
<body>
	<div class="container">
		<div class="row">
			<c:url var="loginUrl" value="/login" />
			<form action="${loginUrl}" method="post">
				<h1><spring:message code="lbl.login.Login"/></h1>
				
				<c:if test="${param.error != null}">
                        <div class="alert alert-danger">
                            <p><spring:message code="lbl.login.invalidUandP"/></p>
                        </div>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <div class="alert alert-success">
                            <p><spring:message code="lbl.login.logoutSuccess"/></p>
                        </div>
                    </c:if>
				
				<div class="form-group">
					<input class="form-control" type="text" id="username" name="ssoId" placeholder="Enter Username" required>
				</div>
				<div class="form-group">
					<input class="form-control" type="password" id="password" name="password" placeholder="Enter Password" required>
				</div>
				<div class="form-group">
					<label> <input type="checkbox" name="remember_me">
						<spring:message code="lbl.login.remember"/>
					</label>
				</div>
				<button class="btn btn-lg btn-primary" type="submit"><spring:message code="lbl.login.Login"/></button>
			</form>
		</div>
	</div>

	<script type="text/javascript">
		
	</script>
</body>
</html>
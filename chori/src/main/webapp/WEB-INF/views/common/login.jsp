<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<c:url var="loginUrl" value="/login" />
			<form action="${loginUrl}" method="post">
				<h1>Login</h1>
				
				<c:if test="${param.error != null}">
                        <div class="alert alert-danger">
                            <p>Invalid username and password.</p>
                        </div>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <div class="alert alert-success">
                            <p>You have been logged out successfully.</p>
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
						Remember me
					</label>
				</div>
				<button class="btn btn-lg btn-primary" type="submit">Submit</button>
			</form>
		</div>
	</div>

	<script type="text/javascript">
		
	</script>
</body>
</html>
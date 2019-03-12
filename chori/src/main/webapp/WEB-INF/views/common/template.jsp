<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js">

<head>
<!-- Bootstrap -->
<link
	href="<c:url value='/admin-theme/bootstrap/css/bootstrap.min.css' />"
	rel="stylesheet" media="screen">
<link
	href="<c:url value='/admin-theme/bootstrap/css/bootstrap-responsive.min.css' />"
	rel="stylesheet" media="screen">
<link
	href="<c:url value='/admin-theme/vendors/easypiechart/jquery.easy-pie-chart.css' />"
	rel="stylesheet" media="screen">
<link href="<c:url value='/admin-theme/assets/styles.css' />"
	rel="stylesheet" media="screen">
<link href="<c:url value='/admin-theme/assets/site.css' />"
	rel="stylesheet" media="screen">
<link href="<c:url value='/admin-theme/assets/DT_bootstrap.css' />"
	rel="stylesheet" media="screen">
<link href="<c:url value='/css/jquery-ui.min.css' />" rel="stylesheet"
	media="screen">
<link href="<c:url value='/css/jquery-ui.theme.min.css' />"
	rel="stylesheet" media="screen">
<link href="<c:url value='/css/jquery-ui.structure.min.css' />"
	rel="stylesheet" media="screen">
<link href="<c:url value='/css/jquery-ui-timepicker-addon.css' />"
	rel="stylesheet" media="screen">
<!-- Include css dataTable -->
<link
	href="<c:url value='/admin-theme/vendors/datatables/css/jquery.dataTables.min.css' />"
	rel="stylesheet" media="screen">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

<script src="<c:url value='/js/jquery.js' />"></script>

<script src="<c:url value='/js/jquery.ui.datepicker.validation.js' />"></script>

<script src="<c:url value='/js/jquery-ui.min.js' />"></script>
<script src="<c:url value='/js/jquery.validate.js' />"></script>
<script src="<c:url value='/js/jquery-ui-timepicker-addon.js' />"></script>
<script src="<c:url value='/js/jquery.numeric.min.js' />"></script>
<script
	src="<c:url value='/admin-theme/bootstrap/js/bootstrap.min.js' />"></script>
<script
	src="<c:url value='/admin-theme/vendors/easypiechart/jquery.easy-pie-chart.js' />"></script>
<script src="<c:url value='/admin-theme/assets/scripts.js' />"></script>
<script src="<c:url value='/admin-theme/assets/modernizr-2.6.2.js' />"></script>
<script
	src="<c:url value='/admin-theme/vendors/datatables/js/jquery.dataTables.min.js' />"></script>
<script src="<c:url value='/admin-theme/assets/DT_bootstrap.js' />"></script>

<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>

<!-- link fancybox css -->
<link href="<c:url value='/js/fancybox/jquery.fancybox.css' />"
	rel="stylesheet" media="screen">

</head>
<body>
<!-- navbar-fixed-top -->
	<div class="navbar navbar-default">
		<jsp:include page="topmenu.jsp" />
	</div>
	<div class="container-fluid">
		<div class="row-fluid">
			<%-- <jsp:include page="sidebar-left.jsp" />             --%>

			<!--/span-->
			<div class="span12" id="content">
				<%-- 
				<div class="row-fluid">
					<jsp:include page="alerts.jsp" />
					<jsp:include page="nav.jsp" />
				</div>
				--%>
				<jsp:include page="${partial}" />
			</div>
		</div>
		<hr>
	</div>
	<div class="container-fluid bottom">
		<jsp:include page="footer.jsp" />
	</div>
	<!--/.fluid-container-->

	<!-- Include Message Dialog -->

	<script
		src="${pageContext.request.contextPath}/resources/userJs/checkLoggedIn.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/userJs/pageTitle.js"></script>

	<script>
		$(function() {
			// Easy pie charts
			$('.chart').easyPieChart({
				animate : 1000
			});
		});

		//to get path in controller when calling ajax by jquery
		function getAbsolutePath() {
		    var loc = window.location;
		    var pathName = loc.pathname.substring(0, loc.pathname.lastIndexOf('/') + 1);
		    return loc.href.substring(0, loc.href.length - ((loc.pathname + loc.search + loc.hash).length - pathName.length));
		}
	</script>

	<!-- fancyBox part -->
	<script
		src="${pageContext.request.contextPath}/resources/js/fancybox/jquery.fancybox.pack.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/fancybox/jquery.mousewheel-3.0.6.pack.js"></script>


	<%-- <jsp:include page="header.jsp" />
 <jsp:include page="${partial}" />
 <jsp:include page="footer.jsp" /> --%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row-fluid">
	<!-- block -->
	<div class="block">
		<div class="navbar navbar-inner block-header">
			<div class="muted pull-left">Excel Import Demo</div>
		</div>

		<div class="block-content collapse in">
			<div class="span12">
				<form:form method="POST" modelAttribute="excelImportDemo2"
					enctype="multipart/form-data" class="form-horizontal">

					<div class="form-group">
						<div class="col-sm-6">
							<form:input type="text" path="lotnumber" id="" value="Lot4"
								class="form-control input-sm" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-6">
							<form:input type="file" path="file" id=""
								class="form-control input-sm" />
						</div>
					</div>
					<br />
					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit" value="Import"
								class="btn btn-primary btn-sm">
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
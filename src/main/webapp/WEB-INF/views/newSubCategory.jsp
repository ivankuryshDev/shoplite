<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Search</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<title>Bootstrap Template</title>
	<!-- Bootstrap -->
	<link rel="stylesheet"
		href='<c:url value="/resources/css/bootstrap.css" />' />
	<link rel="stylesheet" href='<c:url value="/resources/css/app.css" />' />
	<link rel="stylesheet" type="text/css"
		href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
	
	<script type="text/javascript"
		src='<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" />'></script>
	<script src='<c:url value="/resources/js/bootstrap.js" />'></script>
	<script src='<c:url value="/resources/js/main.js" />'></script>
</head>
<body>
	<div class="generic-container">
		<div class="panel-heading">
			<span class="lead">SubCategory creation</span>
		</div>
		<hr>
		<form:form method="POST" modelAttribute="subCategory"
			class="form-horizontal">
			<div class="row">
				<div class="form-group col-md-8">
					<label class="col-md-3" for="name">Category:</label>
					<div class="col-md-6">
						<form:select type="text" path="parent" multiple="false"
							class="form-control">
							<form:option disabled="${hasCategories}" value="null"
								label="--- Category ---" />
							<form:options items="${category}" itemValue="id" itemLabel="name" />
						</form:select>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-8">
					<label class="col-md-3" for="name">New Sub Category:</label>
					<div class="col-md-6">
						<form:input type="text" path="name" id="name"
							class="form-control input-sm" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-actions floatRight">
					<input type="submit" value="Create!" class="btn btn-primary btn-sm" />
					or <a href="<c:url value='/adminProfile' />">Cancel</a>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>




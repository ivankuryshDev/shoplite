<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<div class="well lead">Product Registration Form</div>
		<form:form method="POST" modelAttribute="fileBucket"
			enctype="multipart/form-data" class="form-horizontal">
			<div class="row">
				<c:forEach var="list3" items="${product.pictures}">
					<div class="col-md-2"
						style="padding: 2px; border: 1px solid black;">
						<img alt="No Image"
							src="<c:url value='/image/getImg${list3.id}'/>" width="140" height="140" style="padding: 2px; border: 1px solid black;" /><br>
						<a href="<c:url value='/delete-picture-${list3.id}' />"
							class="btn btn-danger custom-width" id="delete">delete</a>
					</div>
				</c:forEach>
			</div>
			<br>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3" for="file">Add Picture:</label>
					<div class="col-md-7">
						<form:input type="file" path="files" id="files[0]"
							class="form-control input-sm" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3" for="file">Add picture:</label>
					<div class="col-md-7">
						<form:input type="file" path="files" id="files[1]"
							class="form-control input-sm" />
					</div>
				</div>
			</div>
			<div class="row" id="newAddPicture">
				<div class="form-group col-md-12">
					<label class="col-md-3" for="file"></label> <a id="addPicture">
						<button type="button" class="btn btn-success"
							aria-label="Left Align">
							<span>+ Add picture</span>
						</button>
					</a>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3" for="description">Product Name:</label>
					<div class="col-md-7">
						<form:input type="name" path="name" id="name" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="name" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3" for="description">Description:</label>
					<div class="col-md-7">
						<form:input type="description" path="description" id="description" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="description" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3" for="price">Product Price:</label>
					<div class="col-md-7">
						<form:input type="price" path="price" id="price" class="form-control input-sm" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3" for="subCategory">Sub Category:</label>
					<div class="col-md-7">
						<form:select type="subCategory" path="subCategory" multiple="false" class="form-control">
							<form:option disabled="${hasSubCategories}" value="null" label="--- Category ---" />
							<form:options items="${subCategory}" itemValue="id" itemLabel="name" />
						</form:select>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update!"
								class="btn btn-primary btn-sm" /> or <a
								href="<c:url value='/adminProfile' />">Cancel</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Create!" class="btn btn-primary btn-sm" /> 
								or 
							<a href="<c:url value='/adminProfile' />">Cancel</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>



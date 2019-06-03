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
		<script type="text/javascript">
				$(document).ready(function() {
					$('#placeholder').change(function(event) {
						var name = $('#placeholder').val();
						$("#buttonPlaceholder").attr("href", "${pageContext.request.contextPath}/filter/searchByName/" + name);
					});
					
					$('#buttonSearchByNameMinMax').click(function(event) {
						var name = $('#placeholder').val();
						var minPrice = $('#minPrice').val();
						var maxPrice = $('#maxPrice').val();
						$.get('searchByNameMinMax', {
							pName : name,
							pMinPrice : minPrice,
							pMaxPrice : maxPrice
						}, function(response) {
							$('#div').html("");
							$('#title').html('<p>Search by "' + name + '"</p>');
							for(i = 0; i < response.length; i++){
								$('#div').append("<tr>" +
										"<td>" + response[i].id + "</td>" +
										"<td>" + 
											"<img alt='No Image' src='<c:url value='/something/getImgX" + response[i].pictures[0].id + "'/>' width='50' height='50' />" +
										"</td>" +
										"<td>" + response[i].name + "</td>" +
										"<td>$" + response[i].price + "</td>" +
									"</tr>");
							}
						});
					});
					
					$('#buttonSearchByCategoryMinMax').click(function(event) {
						var name = $('#subcategory').val();
						var minPrice = $('#minPrice').val();
						var maxPrice = $('#maxPrice').val();
						$.get('searchByCategoryMinMax', {
							pName : name,
							pMinPrice : minPrice,
							pMaxPrice : maxPrice
						}, function(response) {
							$('#div').html("");
							for (i = 0; i < response.length; i++) {
								$('#div').append("<tr>" +
										"<td>" + response[i].id + "</td>" +
										"<td>" + 
											"<img alt='No Image' src='<c:url value='/image/getImg" + response[i].pictures[0].id + "'/>' width='50' height='50' />" +
										"</td>" +
										"<td>" + response[i].name + "</td>" +
										"<td>$" + response[i].price + "</td>" +
									"</tr>");
							}
						});
					});
				});
			</script>
</head>
<body>
	<header>
	<div class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header" id="brand">
				<a class="navbar-brand" href="<c:url value='/' />"
					style="color: #f00">Shop Lite</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="<c:url value='/' />">About<span
							class="sr-only">(current)</span></a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right ">
					<sec:authorize access="hasRole('USER')">
						<li><a href="<c:url value='/cart' />">Cart</a></li>
					</sec:authorize>

					<sec:authorize access="hasRole('ADMIN')">
						<li><a href="<c:url value='/allOrders' />">Orders</a></li>
					</sec:authorize>

					<sec:authorize access="hasRole('ADMIN')">
						<li><a href="<c:url value='/adminProfile' />">Admin
								Profile</a></li>
					</sec:authorize>

					<sec:authorize access="hasRole('USER') or hasRole('ADMIN')">
						<li><a href="<c:url value='/logout' />">Log out</a></li>
					</sec:authorize>

					<sec:authorize access="!isAuthenticated()">
						<li><a href="<c:url value='/login' />">Log in</a></li>
					</sec:authorize>

					<sec:authorize access="!isAuthenticated()">
						<li><a href="<c:url value='/newUser' />">Sign Up</a></li>
					</sec:authorize>

					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Language <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="<c:url value='/' />">English</a></li>
						</ul></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</div>
	<div class="navbar navbar-default">
		<div class="container">
			<div class="row">
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<c:forEach items="${categories}" var="categories">
							<li class="dropdown"><a href="<c:url value='/' />"
								class="dropdown-toggle" data-toggle="dropdown" role="button"
								aria-haspopup="true" aria-expanded="false"><c:out
										value="${categories}" /><span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><c:forEach items="${categories.subcategories}"
											var="subcategory">
											<a
												href="<c:url value='/filter/searchByCategory/${subcategory}' />"><c:out
													value="${subcategory}" /></a>
										</c:forEach></li>
								</ul></li>
						</c:forEach>
						<div class="col-md-3">
							<div class="input-group" style="margin-top: 5px;">
								<input type="text" class="form-control"
									placeholder="Search for..." name="placeholder" id="placeholder"
									value="${name}"> <span class="input-group-btn">
									<a href="<c:url value='/filter/searchByName/1' />"
									class="btn btn-default" id="buttonPlaceholder">Go!</a>
								</span>
							</div>
							<!-- /input-group -->
						</div>
						<!-- /.col-md-6 -->
					</ul>
				</div>
			</div>
		</div>
	</div>
	</header>

	<div class="container">
		<c:choose>
			<c:when test="${isCategory}">
				<div class="well lead" id="title">
					<p>Category: ${subCategory.name}</p>
				</div>
			</c:when>
			<c:otherwise>
				<div class="well lead" id="title">
					<p>Search by "${name}"</p>
				</div>
			</c:otherwise>
		</c:choose>
		<div class="row">
			<div class="col-md-10">
				<input type="hidden" class="form-control" placeholder="subcategory"
					name="subcategory" id="subcategory" value="${subCategory.name}">
				<div class="col-md-4">
					<div class="form-group">
						<label for="minPrice" class="col-md-5 control-label">Min
							Price:</label>
						<div class="col-md-7">
							<input type="text" class="form-control" placeholder="$"
								name="minPrice" id="minPrice" value="">
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label for="maxPrice" class="col-md-5 control-label">Max
							Price:</label>
						<div class="col-md-7">
							<input type="text" class="form-control" placeholder="$"
								name="maxPrice" id="maxPrice" value="">
						</div>
					</div>
				</div>
				<c:choose>
					<c:when test="${isCategory}">
						<button type="submit" class="btn btn-primary"
							id="buttonSearchByCategoryMinMax">Search!</button>
					</c:when>
					<c:otherwise>
						<button type="submit" class="btn btn-primary"
							id="buttonSearchByNameMinMax">Search!</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<br>
	</div>

	<div class="container">
		<div id="result">
			<c:if test="${not empty products}">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Id</th>
							<th>Picture</th>
							<th>Product Name</th>
							<th>Price:</th>
							<th></th>
						</tr>
					</thead>
					<tbody id="div">
						<c:forEach items="${products}" var="product">
							<tr>
								<td>${product.id}</td>
								<td><a href="<c:url value='/product-${product.name}' />"
									class="name" style="text-decoration: none"> <c:forEach
											items="${product.pictures}" var="list3">
											<c:choose>
												<c:when test="${list3.main}">
													<img alt="No Image"
														src="<c:url value='/image/getImg${list3.id}'/>" width="80"
														height="80" />
												</c:when>
											</c:choose>
										</c:forEach>
								</a></td>
								<td>${product.name}</td>
								<td>$${product.price}</td>
								<td><sec:authorize
										access="hasRole('ADMIN') or hasRole('DBA')">
										<td><a
											href="<c:url value='/edit-product-${product.name}' />"
											class="btn btn-success custom-width">edit</a></td>
									</sec:authorize> <sec:authorize access="hasRole('ADMIN')">
										<td><a
											href="<c:url value='/delete-product-${product.name}' />"
											class="btn btn-danger custom-width" id="delete">delete</a></td>
									</sec:authorize></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</div>
</body>
</html>
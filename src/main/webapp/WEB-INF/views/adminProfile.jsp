<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<script src='<c:url value="/resources/js/main.js" />'></script>

<body>
	<div class="generic-container">
		<%@include file="authHeader.jsp"%>

		<ul class="nav nav-pills">
			<li class="active"><a href="#users" data-toggle="tab">Users</a></li>
			<li class=""><a href="#products" data-toggle="tab">Products</a></li>
			<li class=""><a href="#categories" data-toggle="tab">Categories</a></li>
		</ul>

		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade active in" id="users">
				<br>
				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">
						<span class="lead">List of Users </span>
					</div>
					<c:if test="${not empty users}">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>Firstname</th>
									<th>Lastname</th>
									<th>Email</th>
									<th>SSO ID</th>
									<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
										<th width="100"></th>
									</sec:authorize>
									<sec:authorize access="hasRole('ADMIN')">
										<th width="100"></th>
									</sec:authorize>

								</tr>
							</thead>
							<tbody>
								<c:forEach items="${users}" var="user">
									<tr>
										<td>${user.firstName}</td>
										<td>${user.lastName}</td>
										<td>${user.email}</td>
										<td>${user.ssoId}</td>
										<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
											<td><a href="<c:url value='/edit-user-${user.ssoId}' />"
												class="btn btn-success custom-width">edit</a></td>
										</sec:authorize>
										<sec:authorize access="hasRole('ADMIN')">
											<td><a
												href="<c:url value='/delete-user-${user.ssoId}' />"
												class="btn btn-danger custom-width" id="delete">delete</a></td>
										</sec:authorize>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
				</div>
				<sec:authorize access="hasRole('ADMIN')">
					<div class="well">
						<a href="<c:url value='/newUser' />">Add New User</a><br>
					</div>
				</sec:authorize>
			</div>

			<div class="tab-pane fade" id="products">
				<br>
				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">
						<span class="lead">List of Products </span>
					</div>
					<c:if test="${not empty products}">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>Id</th>
									<th>Product Name</th>
									<th>Main picture:</th>
									<th>Description:</th>
									<th>Category:</th>
									<th>Price:</th>
									<th></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${products}" var="product">
									<tr>
										<td>${product.id}</td>
										<td>${product.name}</td>
										<td><c:forEach var="list3" items="${product.pictures}">
												<c:choose>
													<c:when test="${list3.main}">
														<img alt="No Image"
															src="<c:url value='/image/getImg${list3.id}'/>"
															width="50" height="50" />
													</c:when>
												</c:choose>
											</c:forEach></td>
										<td>${product.description}</td>
										<td>${product.subCategory}</td>
										<td>$${product.price}</td>
										<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
											<td><a
												href="<c:url value='/edit-product-${product.name}' />"
												class="btn btn-success custom-width">edit</a></td>
										</sec:authorize>
										<sec:authorize access="hasRole('ADMIN')">
											<td><a
												href="<c:url value='/delete-product-${product.name}' />"
												class="btn btn-danger custom-width" id="delete">delete</a></td>
										</sec:authorize>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
				</div>
				<sec:authorize access="hasRole('ADMIN')">
					<div class="well">
						<a href="<c:url value='/newProduct' />">Add New Product</a><br>
					</div>
				</sec:authorize>
			</div>

			<div class="tab-pane fade" id="categories">
				<br>
				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">
						<span class="lead">List of Categories </span>
					</div>
					<br>
					<div class="navbar navbar-default">
						<div class="container">
							<div class="row">
								<div class="collapse navbar-collapse"
									id="bs-example-navbar-collapse-1">
									<ul class="nav navbar-nav">
										<c:forEach items="${category}" var="categories">
											<li class="dropdown"><a href="<c:url value='/' />"
												class="dropdown-toggle" data-toggle="dropdown" role="button"
												aria-haspopup="true" aria-expanded="false"><c:out
														value="${categories}" /><span class="caret"></span></a>
												<ul class="dropdown-menu">
													<li><c:forEach items="${categories.subcategories}"
															var="subcategory">
															<a href="<c:url value='/' />"><c:out
																	value="${subcategory}" /></a>
															<a
																href="<c:url value='/delete-subCategory-${subcategory}' />"
																class="btn btn-danger custom-width">delete</a>
														</c:forEach> <a href="<c:url value='/newSubCategory' />"
														class="btn btn-success">Add SubCategory</a></li>
												</ul></li>
											<li><a
												href="<c:url value='/delete-category-${categories.name}' />"
												class="btn btn-danger custom-width">delete</a></li>
										</c:forEach>
										<li><a href="<c:url value='/newCategory' />"
											class="btn btn-success">Add Category</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
				<sec:authorize access="hasRole('ADMIN')">
					<div class="well">
						<a href="<c:url value='/newCategory' />">Add New Category</a><br>
						<a href="<c:url value='/newSubCategory' />">Add New
							SubCategory</a>
					</div>
				</sec:authorize>
			</div>
		</div>
	</div>
</body>

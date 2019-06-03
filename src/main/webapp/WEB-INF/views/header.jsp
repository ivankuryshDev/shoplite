<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<header>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#buttonPlaceholder').click(function(event) {
				var name = $('#placeholder').val();
				$.get('ajax', {
					pName : name
				}, function(response) {
					$('#result').html("Name: " + response.name + "<br>" + "ID: " + response.id);
				});
			});
			$('#placeholder').change(function(event) {
				var name = $('#placeholder').val();
				$("#buttonPlaceholder").attr("href", "${pageContext.request.contextPath}/filter/searchByName/" + name);
			});
		});
	</script>
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
					<li class="active"><a href="<c:url value='/' />">About<span class="sr-only">(current)</span></a></li>
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
						<c:forEach items="${category}" var="categories">
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
									value=""> <span class="input-group-btn"> <a
									href="<c:url value='/filter/searchByName/1' />"
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


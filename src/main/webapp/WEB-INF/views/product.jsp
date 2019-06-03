<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">
	<div class="row">
		<div class="col-md-3">
			<div id="carousel-example-generic" class="carousel slide"
				data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<c:forEach var="list3" items="${product.pictures}">
						<c:choose>
							<c:when test="${list3.main}">
								<li data-target="#carousel-example-generic"
									data-slide-to="${list3.id - 1}" class="active"></li>
							</c:when>
							<c:otherwise>
								<li data-target="#carousel-example-generic"
									data-slide-to="${list3.id - 1}"></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ol>

				<!-- Wrapper for slides -->
				<div class="carousel-inner" role="listbox">
					<c:forEach var="list3" items="${product.pictures}">

						<c:choose>
							<c:when test="${list3.main}">
								<div class="item active">
									<img alt="No Image"
										src="<c:url value='/image/getImg${list3.id}'/>" height="200" />
									<div class="carousel-caption"></div>
								</div>
							</c:when>
							<c:otherwise>
								<div class="item">
									<img alt="No Image"
										src="<c:url value='/image/getImg${list3.id}'/>" height="200" />
									<div class="carousel-caption"></div>
								</div>
							</c:otherwise>
						</c:choose>

					</c:forEach>
				</div>

				<!-- Controls -->
				<a class="left carousel-control" href="#carousel-example-generic"
					role="button" data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#carousel-example-generic"
					role="button" data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>
		</div>
		<div class="col-md-8">
			<h2>${product.name}</h2>
			<hr>
			<h4 style="color: red">Discount: 24% OFF</h4>
			<h4 style="color: green">Sale Price: $${product.price}</h4>
			<h4>Colors: DEEP Gray</h4>

			<!-- Single button -->
			<sec:authorize access="!isAuthenticated()">
				<span>For registered users only!</span>
				<br>
				<a class="btn btn-success custom-width"
					href="<c:url value='/login' />">Log in</a>
			</sec:authorize>
			<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
				<a href="<c:url value='/edit-product-${product.name}' />"
					class="btn btn-success custom-width">edit</a>
			</sec:authorize>
			<sec:authorize access="hasRole('USER')">
				<a href="<c:url value='/addToCart-${product.name}' />"
					class="btn btn-success">Add to Cart</a>
			</sec:authorize>
		</div>
	</div>
</div>
<br>
<br>
<ul class="nav nav-tabs">
	<li class="active"><a href="#description" data-toggle="tab">Description</a></li>
	<li class=""><a href="#reviews" data-toggle="tab">Customer
			Reviews</a></li>
</ul>
<div id="myTabContent" class="tab-content">
	<div class="tab-pane fade active in" id="description">
		<p>${product.description}</p>
	</div>
	<div class="tab-pane fade" id="reviews">
		<p>Some Revies Here!</p>
	</div>
</div>
<br>
<br>





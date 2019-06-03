<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="container">
	<c:if test="${not empty carts}">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Id</th>
					<th>Photo</th>
					<th>User</th>
					<th>Product</th>
					<th>Price</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${carts}" var="cart">
					<tr>
						<td>${cart.id}</td>
						<td><c:forEach items="${cart.product.pictures}" var="list3">
								<c:choose>
									<c:when test="${list3.main}">
										<div class="item active">
											<img alt="No Image"
												src="<c:url value='/image/getImg${list3.id}'/>" width="50"
												height="50" />
											<div class="carousel-caption"></div>
										</div>
									</c:when>
								</c:choose>
							</c:forEach></td>
						<td>${cart.user.ssoId}</td>
						<td>${cart.product.name}</td>
						<td>${cart.product.price}</td>
						<td><a href="<c:url value='/deleteFromCart-${cart.id}' />"
							class="btn btn-danger">delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="row" style="padding-bottom: 15px;">
			<a href="<c:url value='/order' />" class="btn btn-success">Submit!</a>
		</div>
	</c:if>
</div>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="container">
	<c:if test="${not empty orders}">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Id</th>
					<th>User</th>
					<th>Product</th>
					<th>Price</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${orders}" var="order">
					<tr>
						<td>${order.id}</td>
						<td>${order.user.ssoId}</td>
						<td>${order.product.name}</td>
						<td>${order.product.price}</td>
						<td><a href="<c:url value='/deleteFromOrder-${order.id}' />"
							class="btn btn-danger">delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</div>
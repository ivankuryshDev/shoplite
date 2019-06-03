<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">
	<div class="row">
		<c:forEach items="${products}" var="product">
			<div class="col-md-3">
				<div class="thumbnail">
					<a href="<c:url value='/product-${product.name}' />" class="name"
						style="text-decoration: none"> <c:forEach
							items="${product.pictures}" var="list3">
							<c:choose>
								<c:when test="${list3.main}">
									<img alt="No Image"
										src="<c:url value='/image/getImg${list3.id}'/>" />
								</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
						</c:forEach>
						<div class="caption">
							<h4>${product.name}</h4>
							<h4>Price: $${product.price}</h4>
							<p>Category: ${product.subCategory}</p>
						</div>
					</a>
				</div>
			</div>
		</c:forEach>
	</div>
	<ul class="pagination pagination-sm">
		<c:choose>
			<c:when test="${currentPage == 1}">
				<li class="disabled"><a href="">&laquo;</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="<c:url value='/page/${currentPage - 1}' />">&laquo;</a></li>
			</c:otherwise>
		</c:choose>
		<c:if test="${lastPage != 0}">
			<c:forEach var="i" begin="${1}" end="${lastPage}">
				<c:choose>
					<c:when test="${i == currentPage}">
						<li class="active"><a href="<c:url value='/page/${i}' />"><c:out
									value="${i}" /></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="<c:url value='/page/${i}' />"><c:out
									value="${i}" /></a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</c:if>
		<c:choose>
			<c:when test="${currentPage == lastPage}">
				<li class="disabled"><a href="">&laquo;</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="<c:url value='/page/${currentPage + 1}' />">&laquo;</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>


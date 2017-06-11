<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div class="container">
	<div class="row myTable">
		<table class="table table-striped">
			<tr>
				<th>Name</th>
				<th>Email</th>
				<th>Offers</th>
			</tr>
			<c:forEach var="offer" items="${offers}">
				<tr>
					<td><c:out value="${offer.user.name}"></c:out></td>
					<td><c:out value="${offer.user.email}"></c:out></td>
					<td><c:out value="${offer.text}"></c:out></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<div class="row">
		<div class="col-sm-5" style="padding-left: 5%;">
			<c:choose>
				<c:when test="${hasOffer}">
					<div class="container">
						<div class="row">
							<a href="${pageContext.request.contextPath}/createoffer"><button
									class="btn btn-danger" style="margin-right: 15px">Edit/Delete
									Offer</button></a>

							<sec:authorize access="hasAuthority('ROLE_ADMIN')">
								<a href="${pageContext.request.contextPath}/admin"><button
										class="btn btn-info">Admin</button></a>
							</sec:authorize>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="container">
						<div class="row">
							<a href="${pageContext.request.contextPath}/createoffer"><button
									class="btn btn-info">Add new offer</button></a>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>

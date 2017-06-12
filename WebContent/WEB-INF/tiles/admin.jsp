<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<div class="row">
		<h3 align="justify" >Authorized Users Only!</h3>
	</div>
	<div class="row" style="padding-top: 4%; margin-bottom: 100px;">
		<table class="table table-striped">
			<tr>
				<th>Name</th>
				<th>Username</th>
				<th>Email</th>
				<th>Authority</th>
				<th>Enabled</th>
				<th>Offers</th>
			</tr>
			<c:forEach var="offer" items="${offers}">
				<tr>
					<td><c:out value="${offer.user.name}"></c:out></td>
					<td><c:out value="${offer.user.username}"></c:out></td>
					<td><c:out value="${offer.user.email}"></c:out></td>
					<td><c:out value="${offer.user.authority}"></c:out></td>
					<td><c:out value="${offer.user.enabled}"></c:out></td>
					<td><c:out value="${offer.text}"></c:out></td>

				</tr>
			</c:forEach>
		</table>
	</div>
</div>



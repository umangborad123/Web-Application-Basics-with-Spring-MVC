<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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
					<%-- <p>
			<c:out value="${offer}"></c:out>
		</p> --%>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>


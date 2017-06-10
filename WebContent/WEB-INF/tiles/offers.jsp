<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<table class="offers">
	<tr><th align="center">Name</th><th>Email</th><th align="center">Offers</th></tr>
	<c:forEach var="offer" items="${offers}">
	<tr>
		<td width="100px" align="left"><c:out value="${offer.user.name}"></c:out></td>
		<td width="200px" align="center"><c:out value="${offer.user.email}"></c:out></td>
		<td width="200px" align="left" ><c:out value="${offer.text}"></c:out></td>
		<%-- <p>
			<c:out value="${offer}"></c:out>
		</p> --%>
	</tr>
	</c:forEach>
</table>

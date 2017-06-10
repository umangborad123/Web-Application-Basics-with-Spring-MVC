<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<table class="offers">
	<tr>
		<th align="center">Name</th>
		<th>Email</th>
		<th align="center">Offers</th>
	</tr>
	<c:forEach var="offer" items="${offers}">
		<tr>
			<td width="100px" align="left"><c:out value="${offer.user.name}"></c:out></td>
			<td width="200px" align="center"><c:out
					value="${offer.user.email}"></c:out></td>
			<td width="200px" align="left"><c:out value="${offer.text}"></c:out></td>
			<%-- <p>
			<c:out value="${offer}"></c:out>
		</p> --%>
		</tr>
	</c:forEach>
</table>

<%-- <p>
	<a href="${pageContext.request.contextPath}/offers">Show Offers</a></br>
</p> --%>

<c:choose>
	<c:when test="${hasOffer}">
		<p>
			<a href="${pageContext.request.contextPath}/createoffer">Edit or delete your current offer.</a>
		</p>
	</c:when>
	<c:otherwise>
		<p>
			<a href="${pageContext.request.contextPath}/createoffer">Add a
				new Offer</a>
		</p>
	</c:otherwise>
</c:choose>



<p>
	<sec:authorize access="hasAuthority('ROLE_ADMIN')">
		<a href="<c:url value = '/admin' />">Admin</a>
	</sec:authorize>
</p>



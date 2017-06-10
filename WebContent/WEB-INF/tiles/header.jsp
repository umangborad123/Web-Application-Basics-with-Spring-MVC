<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div>
	<a class="title" href="<c:url value = '/' />">Offers</a>
</div>
<div>
	<sec:authorize access="!isAuthenticated()">
		<a class="login" href="<c:url value = '/login' />">Log in</a>
	</sec:authorize>
</div>

<div>
	<sec:authorize access="isAuthenticated()">
		<form action="${pageContext.request.contextPath}/logout" method="post">
			<input type="submit" class="logout" value="Log out" /> <input type="hidden"
				name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</sec:authorize>
</div>



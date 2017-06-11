<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div class="container">
	<div class="row">

		<div style="text-align: center;" class="col-sm-8">
			<a class="title" href="<c:url value = '/' />"><h3>Offers</h3></a>
		</div>
		<div style="text-align: right; padding-top: 10px" class="col-sm-2">
			<sec:authorize access="!isAuthenticated()">
				<a href="<c:url value = '/login' />">Log in</a>
			</sec:authorize>
		</div>

		<div style="text-align: right;" class="col-sm-2">
			<sec:authorize access="isAuthenticated()">
				<form action="${pageContext.request.contextPath}/logout"
					method="post">
					<input type="submit"
						style="background-color: transparent; text-decoration: underline; border: none; color:blue; padding-top:10px"
						value="Log out" /> <input type="hidden"
						name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
			</sec:authorize>
		</div>
	</div>
</div>


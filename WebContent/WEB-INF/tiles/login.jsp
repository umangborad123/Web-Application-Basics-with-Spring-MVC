<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>



<script>
	$(document).ready(function() {
		document.f.username.focus();
	});
</script>

<h3>Login with Username and Password</h3>

<c:if test="${param.error != null }">
	<p class="error">Login failed. Check that username and password are
		correct.</p>
</c:if>

<form name='f' action='${pageContext.request.contextPath}/login'
	method='POST'>
	<table class="formtable">
		<tr>
			<td>Username:</td>
			<td><input type='text' name='username' /></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><input type='password' name='password' /></td>
		</tr>
		<tr>
			<td>Remember me:</td>
			<td><input type="checkbox" name='remember-me' checked="checked" /></td>
		</tr>
		<tr>
			<td colspan='2'><input name="submit" type="submit" value="Login" /></td>
		</tr>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<!-- <input name="_csrf" type="hidden"
				value="0f682301-dbde-43cb-ac16-7ffcc098098b" /> -->
	</table>
</form>
<p>
	Click <a href="<c:url value="/newaccount"/>">here</a> to register.
</p>

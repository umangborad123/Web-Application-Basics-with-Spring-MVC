<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>



<script>
	$(document).ready(function() {
		document.f.username.focus();
	});
</script>
<div class="container">
	<div class="container">
		<div class="row">
			<h3
				style="text-align: justify; padding-left: 85px; margin-bottom: 35px;">Login
				with Username and Password</h3>
		</div>
		<div class="row">
			<c:if test="${param.error != null }">
				<p class="error" style="text-align: justify; padding-left: 85px; margin-bottom: 35px;">Login failed. Check that username and password
					are correct.</p>
			</c:if>
		</div>
	</div>




	<%-- <form name='f' action='${pageContext.request.contextPath}/login'
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
</form> --%>
	<div class="container">
		<div class="row">
			<form name='f' class="form-horizontal"
				action='${pageContext.request.contextPath}/login' method='POST'>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label"
						style="color: grey;">Username</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="username"
							placeholder="Username">
					</div>
				</div>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label"
						style="color: grey;">Password</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" name="password"
							placeholder="Password">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<div class="checkbox">
							<label style="color: grey;"> <input type="checkbox"
								name="remember-me"> Remember me
							</label> <input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-success"
							style="margin-right: 10px">Sign in</button>
						<a href="<c:url value="/newaccount"/>">Register</a>
					</div>
				</div>

			</form>
		</div>
	</div>

</div>




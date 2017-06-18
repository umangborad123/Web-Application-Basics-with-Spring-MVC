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
		<div class="row" style="margin-top:4%;">
			<h3
				style="text-align: justify; padding-left: 85px; margin-bottom: 35px;">Login
				with Username and Password</h3>
		</div>
		<div class="row">
			<c:if test="${param.error != null }">
				<p class="error"
					style="text-align: justify; padding-left: 85px; margin-bottom: 35px;">Login
					failed. Check that username and password are correct.</p>
			</c:if>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<form name='f' class="form-horizontal"
				action="<c:url value='/login' />" method='POST'>
				<div class="form-group">
					<label for="inputEmail3" class="col-sm-2 control-label"
						style="color: grey;">Username</label>
					<div class="col-sm-10">
						<input type="text" Class="form-control" name="username"
							placeholder="Username" />
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
								name="remember-me"> <b>Remember me</b>
							</label> <input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-success"
							style="margin-right: 10px">Sign in</button>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<label class="control-label" style="color: grey;">Click <a
							href="<c:url value="/newaccount"/>">here</a> to register.
						</label>

					</div>
				</div>

			</form>
		</div>
	</div>

</div>




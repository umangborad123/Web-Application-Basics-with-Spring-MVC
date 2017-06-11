<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container">
	<div class="row">
		<h2>Create New Account</h2>
	</div>
	<div class="row">
		<sf:form id="details" class="form-horizontal"
			action="${pageContext.request.contextPath}/createaccount"
			method="post" commandName="user">
			<div class="form-group">
				<label class="col-sm-2 control-label" style="color: grey;">Username</label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" name="username"
						path="username" placeholder="Username" />
					<sf:errors path="username"></sf:errors>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label" style="color: grey;">Full
					Name</label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" name="name" path="name"
						placeholder="Full Name" />
					<sf:errors path="name"></sf:errors>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label" style="color: grey;">Email</label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" name="email"
						path="email" placeholder="Email" />
					<sf:errors path="email"></sf:errors>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label" style="color: grey;">Password</label>
				<div class="col-sm-10">
					<sf:input type="password" class="form-control" name="password"
						path="password" placeholder="Password" />
					<sf:errors path="password"></sf:errors>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label" style="color: grey;">Confirm
					Password</label>
				<div class="col-sm-10">
					<input type="password" id="confirmpass" class="form-control"
						name="confirmpass" path="password" placeholder="Password" />
					<div id="matchpass"></div>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-success"
						style="margin-right: 10px">Create</button>
				</div>
			</div>

<!-- 			<table class="formtable">



				<tr>
					<td class="label">Confirm Password:</td>
					<td><input type="password" id="confirmpass" name="confirmpass"
						class="control" />
						<div id="matchpass"></div>
				</tr>
				<tr>
					<td class="label"></td>
					<td><input type="submit" value="Create account"
						class="control" /></td>
				</tr>
			</table> -->
		</sf:form>
	</div>
</div>


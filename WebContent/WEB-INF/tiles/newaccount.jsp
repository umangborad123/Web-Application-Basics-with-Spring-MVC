<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


	<h2>Create New Account</h2>
	<sf:form id="details"
		action="${pageContext.request.contextPath}/createaccount"
		method="post" commandName="user">
		<table class="formtable">
			<tr>
				<td class="label">Username:</td>
				<td><sf:input type="text" path="username" name="username"
						class="control" /> <br />
					<div class="error">
						<sf:errors path="username"></sf:errors>
					</div></td>

			</tr>
			<tr>
				<td class="label">Name:</td>
				<td><sf:input type="text" path="name" name="name"
						class="control" /> <br />
					<div class="error">
						<sf:errors path="name"></sf:errors>
					</div></td>

			</tr>
			<tr>
				<td class="label">Email:</td>
				<td><sf:input type="text" path="email" name="email"
						class="control" /><br />
					<div class="error">
						<sf:errors path="email"></sf:errors>
					</div></td>
			</tr>
			<tr>
				<td class="label">Password:</td>
				<td><sf:input type="password" id="password" path="password"
						name="password" class="control" /><br />
					<div class="error">
						<sf:errors path="password"></sf:errors>
					</div></td>
			</tr>
			<tr>
				<td class="label">Confirm Password:</td>
				<td><input type="password" id="confirmpass" name="confirmpass"
					class="control" />
					<div id="matchpass"></div>
			</tr>
			<tr>
				<td class="label"></td>
				<td><input type="submit" value="Create account" class="control" /></td>
			</tr>
		</table>
	</sf:form>

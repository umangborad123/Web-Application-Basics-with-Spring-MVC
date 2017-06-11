<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="container">
	<div class="row" style="padding-top: 4%">
		<c:choose>
			<c:when test="${update}">
				<label class="col-sm-12 control-label" style="color: grey;">Your
					Successfully updated! </label>

			</c:when>
			<c:otherwise>
				<label class="col-sm-12 control-label" style="color: grey;">Your
					Successfully created! </label>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="row">
		<div class="col-sm-12" style="padding-top: 4%">
			Click <a href="${pageContext.request.contextPath}/offers">here</a> to
			view current offers.
		</div>
	</div>
</div>


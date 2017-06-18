<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<div class="row" style="margin-top: 4%;">
	<div class="col-sm-12" style="padding-left: 5%;">
		<c:choose>
			<c:when test="${hasOffer}">

				<a href="${pageContext.request.contextPath}/createoffer"><button
						class="btn btn-danger" style="margin-right: 15px">Edit/Delete
						Offer</button></a>

				<sec:authorize access="hasAuthority('ROLE_ADMIN')">
					<a href="${pageContext.request.contextPath}/admin"><button
							class="btn btn-info" style="margin-right: 15px">Admin</button></a>
				</sec:authorize>

			</c:when>
			<c:otherwise>

				<a href="${pageContext.request.contextPath}/createoffer"><button
						class="btn btn-info" style="margin-right: 15px">Add new
						offer</button></a>

			</c:otherwise>
		</c:choose>

		<sec:authorize access="isAuthenticated()">
			<a href="${pageContext.request.contextPath}/messages"><button
					class="btn btn-info" style="margin-right: 15px">
					Messages (<span id="numberMessages">0</span>)
				</button></a>
		</sec:authorize>

	</div>
</div>
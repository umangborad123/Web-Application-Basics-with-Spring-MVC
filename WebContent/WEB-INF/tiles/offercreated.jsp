<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<c:choose>
	<c:when test="${update}">
		Successfully updated! <br/>
	</c:when>
	<c:otherwise>
		Successfully created! <br/>
	</c:otherwise>
</c:choose>

Click <a href="${pageContext.request.contextPath}/offers">here</a> to view current offers.

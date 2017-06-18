<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>



<div class="row myTable" style="margin-top: 1%">
	<table class="table table-striped">
		<tr>
			<th>Name</th>
			<th>Send message</th>
			<th>Offers</th>
		</tr>
		<c:forEach var="offer" items="${offers}">
			<tr>
				<td><c:out value="${offer.user.name}"></c:out></td>
				<td><a href="<c:url value='/message?uid=${offer.username}' />"><c:out
							value="Contact" /></a></td>
				<td><c:out value="${offer.text}"></c:out></td>
			</tr>
		</c:forEach>
	</table>
</div>





<script type="text/javascript">
	function updateMessageLink(data) {
		$("#numberMessages").text(data.number);
		//alert(data.number);
	}

	function onLoad() {
		updatePage();
		window.setInterval(updatePage, 5000);
	}

	function updatePage() {

		$.getJSON("<c:url value='/getmessages' />", updateMessageLink);
	}

	$(document).ready(onLoad);
</script>
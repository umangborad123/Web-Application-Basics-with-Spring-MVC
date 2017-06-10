<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
	
	function onDeleteClick(event) {
		
		var doDelete = confirm("Are you sure you want to delete this offer?");
		
		if (doDelete == false) {
			event.preventDefault();
		}
	}
	
	function onReady() {
		$("#delete").click(onDeleteClick);
	}
	
	$(document).ready(onReady);
</script>


<sf:form action="${pageContext.request.contextPath}/docreate"
	method="post" commandName="offer">
	<sf:input type="hidden" name="id" path="id" />

	<table class="formtable">

		<tr>
			<td class="label">Your offer:</td>
			<td><sf:textarea path="text" name="text" class="control" /><br />
				<sf:errors path="text" cssClass="error"></sf:errors></td>
		</tr>
		<tr>
			<td class="label"></td>
			<td><input type="submit" value="Save advert" class="control" /></td>
		</tr>

		<c:if test="${offer.id != 0}">

			<tr>
				<td class="label"></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td class="label"></td>
				<td><input id="delete" type="submit" name="delete"
					value="Delete this offer" class="delete control" /></td>
			</tr>
		</c:if>
	</table>
</sf:form>

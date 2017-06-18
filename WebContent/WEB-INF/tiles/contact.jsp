<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>




<!-- <script type="text/javascript">
	function onLoad() {
		var formName = $("#nameText").val();
		var formEmail = $("#emailText").val();
		
		if (formName != "" && formEmail != "") {
			$("#nameText").prop("disabled", true);
			$("#emailText").prop("disabled", true);
		} else {
			$("#nameText").prop("disabled", false);
			$("#emailText").prop("disabled", false);
		}

	}

	$(document).ready(onLoad);
</script> -->


<div class="container">
	<div class="row">

		<label class="control-label" style="color: grey;"><h2>Send
				Message</h2></label>

	</div>
	<div class="row" style="padding-top: 4%; margin-bottom: 100px;">
		<sf:form class="form-horizontal" method="post" commandName="message">

			<input type="hidden" name="_flowExecutionKey"
				value="${flowExecutionKey}" />

			<input type="hidden" name="_eventId" value="send" />

			<!-- <input type="hidden" name="username" value="umangborad123" /> -->

			<div class="form-group">
				<label class="col-sm-2 control-label" style="color: grey;">Your
					name:</label>
				<div class="col-sm-10">
					<sf:input type="text" id="nameText" class="form-control"
						path="name" value="${fromName}" />
					<sf:errors path="name" cssClass="error"></sf:errors>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label" style="color: grey;">Your
					email:</label>
				<div class="col-sm-10">
					<sf:input type="text" id="emailText" class="form-control"
						path="email" value="${fromEmail}" />
					<sf:errors path="email" cssClass="error"></sf:errors>
				</div>
			</div>


			<div class="form-group">
				<label class="col-sm-2 control-label" style="color: grey;">Subject:</label>
				<div class="col-sm-10">
					<sf:input type="text" class="form-control" path="subject" />
					<sf:errors path="subject" cssClass="error"></sf:errors>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label" style="color: grey;">Your
					message:</label>
				<div class="col-sm-10">
					<sf:textarea class="form-control" path="content" />
					<sf:errors path="content" cssClass="error"></sf:errors>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-success"
						style="margin-right: 10px">Send message</button>
				</div>
			</div>
		</sf:form>
	</div>
</div>


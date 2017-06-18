<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div id="messages" class="row"></div>


<script type="text/javascript">
	var timer;

	function showReply(i) {
		stopTimer();
		$("#form" + i).toggle();
	}

	function success(data) {
		$("#form" + data.target).toggle();
		$("#alert" + data.target).text("   Message sent!");
		startTimer();
	}

	function error(data) {
		alert("Error!");
	}

	function sendMessage(i, name, email, subject) {

		var text = $("#textbox" + i).val();

		$.ajax({
			"type" : "POST",
			"url" : "<c:url value = '/sendmessage' />",
			"data" : JSON.stringify({
				"target" : i,
				"text" : text,
				"name" : name,
				"email" : email,
				"subject": subject
			}),
			"async": false,
			"success" : success,
			"error" : error,
			contentType : "application/json",
			dataType : "json"
		});

	}

	function showMessages(data) {
		$("div#messages").html("");

		for (var i = 0; i < data.messages.length; i++) {
			var message = data.messages[i];
			//$("div#messages").append(message.subject);

			var messageDiv = document.createElement("div");
			messageDiv.setAttribute("class", "message");

			var subjectSpan = document.createElement("span");
			subjectSpan.setAttribute("class", "subject");
			subjectSpan.appendChild(document.createTextNode(message.subject));

			var contentSpan = document.createElement("span");
			contentSpan.setAttribute("class", "messageBody");
			contentSpan.appendChild(document.createTextNode(message.content));

			var nameSpan = document.createElement("span");
			nameSpan.setAttribute("class", "messageName");
			nameSpan.appendChild(document.createTextNode(message.name + " ("));

			var link = document.createElement("a");
			link.setAttribute("class", "replyLink");
			link.setAttribute("href", "#");
			link.setAttribute("onclick", "showReply(" + i + ")");
			link.appendChild(document.createTextNode(message.email));
			nameSpan.appendChild(link);
			nameSpan.appendChild(document.createTextNode(")"));
			
			var alertSpan = document.createElement("span");
			alertSpan.setAttribute("class", "alert");
			alertSpan.setAttribute("id", "alert" + i);
			//alertSpan.appendChild(document.createTextNode("Message sent!"));
			
			var replyForm = document.createElement("form");
			replyForm.setAttribute("class", "replyForm");
			replyForm.setAttribute("id", "form" + i);
			/* replyForm.setAttribute("method", "POST") */

			var textarea = document.createElement("textarea");
			textarea.setAttribute("class", "replyArea");
			textarea.setAttribute("id", "textbox" + i);

			var replyButton = document.createElement("input");
			replyButton.setAttribute("type", "button");
			replyButton.setAttribute("value", "Reply");
			replyButton.setAttribute("class", "btn btn-success replyButton");
			replyButton.onclick = function(j, name, email, subject) {
				return function() {
					sendMessage(j, name, email, subject);
				}
			}(i, message.name, message.email, message.subject);

			replyForm.appendChild(textarea);
			replyForm.appendChild(replyButton);

			messageDiv.appendChild(subjectSpan);
			messageDiv.appendChild(contentSpan);
			messageDiv.appendChild(nameSpan);
			messageDiv.appendChild(alertSpan);
			messageDiv.appendChild(replyForm);

			$("div#messages").append(messageDiv);
		}
	}

	function onLoad() {
		updatePage();
		startTimer();
	}

	function startTimer() {
		timer = window.setInterval(updatePage, 10000);
	}

	function stopTimer() {
		window.clearInterval(timer);
	}

	function updatePage() {

		$.getJSON("<c:url value='/getmessages' />", showMessages);
	}

	$(document).ready(onLoad);
</script>






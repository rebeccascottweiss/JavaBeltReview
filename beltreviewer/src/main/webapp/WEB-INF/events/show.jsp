<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ page isErrorPage="true" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><c:out value="${ event.name }"/></title>
 <link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>
<body>
	<p class="logout"><a href="/events">Events</a> | <a href="/logout">logout</a></p>
	<h1><c:out value="${ event.name }"/></h1>
	<div class="container">
		<div class="wrapper">
			<div class="info">
				<p>Host: <c:out value="${ event.host.firstName } ${ event.host.lastName }"/></p>
				<p>Date: <fmt:formatDate type="date" value="${ event.eventDate }"/></p>
				<p>Location: <c:out value="${ event.city }"/></p>
				<p>People who are attending this event: <c:out value="${ event.attendees.size() }"/></p>
				<table>
					<thead>
						<tr>
							<th>Name</th>
							<th>Location</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ event.attendees }" var="user">
						<tr>
							<td>${ user.firstName } ${ user.lastName }</td>
							<td>${ user.city }</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
		</div>
		<div class="wrapper">
				<h2>Message Wall</h2>
				<div class="display">
					<c:forEach items="${ event.messages }" var="message">
						<p>${ message.user.firstName } ${ message.user.lastName } says: ${ message.text }</p>
						<p>------------------------------------------------------------</p>
					</c:forEach>
				</div>
				<div class="display">
					<h2 class="form-heading">Add Comment</h3>
					<form:form action="/createMessage" method="post" modelAttribute="message">
						<p>
							<form:textarea path="text"/>
							<form:hidden path="event" value="${ event.id }"/>
							<form:hidden path="user" value="${ user.id }"/>
						</p>
						<input type="submit" value="Submit" />
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
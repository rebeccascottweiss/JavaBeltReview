<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>   

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
 <link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>
<body>
	<h1>Welcome</h1>
	<div class="container">
		<div class="wrapper">
			<p><form:errors path="user.*"/></p>
			<h2 class="form-heading">Register</h2>
			<form:form action="/registerUser" method="post" modelAttribute="user">
				<p class="form-group">
					<form:label path="firstName">First Name: </form:label>
					<form:input path="firstName"/>
				</p>
				<p class="form-group">
					<form:label path="lastName">Last Name: </form:label>
					<form:input path="lastName"/>
				</p>
				<p class="form-group">
					<form:label path="email">Email: </form:label>
					<form:input type="email" path="email"/>
				</p>
				<p class="input location form-group">
					<span class="label" >Location:</span>	
					<form:input class="location" path="city"/>
					<form:select path="state">
						<option value="" disabled selected>State</option>
	                    <form:option value ="AL">AL</form:option>
	                    <form:option value ="AK">AK</form:option>
	                    <form:option value ="AZ">AZ</form:option>
	                    <form:option value ="AR">AR</form:option>
	                    <form:option value ="CA">CA</form:option>
	                    <form:option value ="CO">CO</form:option>
	                    <form:option value ="CT">CT</form:option>
	                    <form:option value ="DE">DE</form:option>
	                    <form:option value ="FL">FL</form:option>
	                    <form:option value ="GA">GA</form:option>
	                    <form:option value ="HI">HI</form:option>
	                    <form:option value ="ID">ID</form:option>
	                    <form:option value ="IL">IL</form:option>
	                    <form:option value ="IN">IN</form:option>
	                    <form:option value ="IA">IA</form:option>
	                    <form:option value ="KS">KS</form:option>
	                    <form:option value ="KY">KY</form:option>
						<form:option value ="LA">LA</form:option>
	                    <form:option value ="ME">ME</form:option>
	                    <form:option value ="MD">MD</form:option>
	                    <form:option value ="MA">MA</form:option>
	                    <form:option value ="MI">MI</form:option>
	                    <form:option value ="MN">MN</form:option>
	                    <form:option value ="MS">MS</form:option>
	                    <form:option value ="MO">MO</form:option>
	                    <form:option value ="MT">MT</form:option>
	                    <form:option value ="NE">NE</form:option>
	                    <form:option value ="NV">NV</form:option>
	                    <form:option value ="NH">NH</form:option>
	                    <form:option value ="NJ">NJ</form:option>
	                    <form:option value ="NM">NM</form:option>
	                    <form:option value ="NY">NY</form:option>
	                    <form:option value ="NC">NC</form:option>
	                    <form:option value ="ND">ND</form:option>
	                    <form:option value ="OH">OH</form:option>
	                    <form:option value ="OK">OK</form:option>
	                    <form:option value ="OR">OR</form:option>
						<form:option value ="PA">PA</form:option>
	                    <form:option value ="RI">RI</form:option>
	                    <form:option value ="SC">SC</form:option>
	                    <form:option value ="SD">SD</form:option>
	                    <form:option value ="TN">TN</form:option>
	                    <form:option value ="TX">TX</form:option>
	                    <form:option value ="UT">UT</form:option>
	                    <form:option value ="VT">VT</form:option>
	                    <form:option value ="VA">VA</form:option>
	                    <form:option value ="WA">WA</form:option>
	                    <form:option value ="WV">WV</form:option>
	                    <form:option value ="WI">WI</form:option>
	                    <form:option value ="WY">WY</form:option>
                	</form:select>
				</p>
				<p class="form-group">
					<form:label path="password">Password: </form:label>
					<form:input type="password" path="password"/>
				</p>
				<p class="form-group">
					<form:label path="passwordConfirmation">Confirm Password: </form:label>
					<form:input type="password" path="passwordConfirmation"/>
				</p>
				<p class="form-group">
					<input type="submit" value="Register" />
				</p>
			</form:form>
		</div>
		<div class="wrapper">
			<p><c:out value="${ error }"/></p>
			<h2 class="form-heading">Login</h2>
			<form action="/login" method="post">
				<p class="form-group">
					<label for="email">Email: </label>
					<input type="email" id="email" name="email" />
				</p>
				<p class="form-group">
					<label for="password">Password: </label>
					<input type="password" id="password" name="password" />
				</p>
				<p class="form-group">
					<input type="submit" value="login" />
				</p>
			</form>
		</div>
	</div>
</body>
</html>
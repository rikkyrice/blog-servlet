<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String message = (String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>blog</title>
</head>
<body>
	<div>
		<h1>ログイン</h1>
	</div>
	<div>
		<% if (message != null) { %>
			<span style="color: red;"><%= message %></span>
		<% } %>
		<form action="/blog/login" method="post">
			<div>
				<label>id</label>
				<input type="id" name="id">
			</div>
			<div>
				<label>password</label>
				<input type="password" name="password">
			</div>
			<input type="submit" value="ログイン">
		</form>
	</div>
</body>
</html>
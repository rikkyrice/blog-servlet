<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dto.Blog" %>
<%
Blog blog = (Blog) request.getAttribute("blog");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>blog</title>
</head>
<body>
	<h1><%= blog.getTitle() %></h1>
	<hr>
	<div>
			<p>本文</p>
			<p><%= blog.getBody() %></p>
			<p>投稿日時</p>
			<p><%= blog.getPostedAt() %></p>
			<form action="../blogUpdate" method="post">
				<input type="hidden" name="id" value="<%= blog.getId() %>">
				<input type="submit" value="編集">
			</form>
			<form action="../blogDelete" method="post">
				<input type="hidden" name="id" value="<%= blog.getId() %>">
				<input type="submit" value="削除">
			</form>
	</div>
</body>
</html>
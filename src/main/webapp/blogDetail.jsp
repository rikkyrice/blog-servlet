<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dto.Blog" %>
<%@ page import="dto.User" %>

<%
Blog blog = (Blog) request.getAttribute("blog");
User loginUser = (User) session.getAttribute("loginUser");
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
			<p><a href="/blog/userpage/<%= blog.getUserId() %>"><%= blog.getUserId() %></a></p>
			<% if (loginUser != null && loginUser.isLoginUser(blog.getUserId())) { %>
				<form action="/blog/blogUpdate" method="post">
					<input type="hidden" name="id" value="<%= blog.getId() %>">
					<input type="submit" value="編集">
				</form>
				<form action="/blog/blogDelete" method="post">
					<input type="hidden" name="id" value="<%= blog.getId() %>">
					<input type="submit" value="削除">
				</form>
			<% } %>
	</div>
</body>
</html>
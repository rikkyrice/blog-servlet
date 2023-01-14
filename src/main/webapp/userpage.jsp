<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dto.Blog" %>
<%@ page import="dto.User" %>
<%
String message = (String) request.getAttribute("message");
List<Blog> blogs = (List<Blog>) request.getAttribute("blogs");
User loginUser = (User) session.getAttribute("loginUser");
User user = (User) request.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>blog</title>
</head>
<body>
	<div>
		<h1><%= user.getId() %>さん</h1>
		<% if (loginUser != null && loginUser.isLoginUser(user.getId())) { %>
			<a href="post"><button>投稿する</button></a>
		<% } %>
	</div>
	<% for (Blog blog : blogs) { %>
		<hr>
		<div>
			<p><a href="/blog/post/<%= blog.getId() %>"><%= blog.getTitle() %></a></p>
			<p><%= blog.getBody().length() >= 5 
					? blog.getBody().substring(0, 5) + "..." 
					: blog.getBody() %></p>
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
	<% } %>
</html>
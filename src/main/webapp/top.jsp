<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dto.Blog" %>
<%@ page import="dto.User" %>
<%
String message = (String) request.getAttribute("message");
List<Blog> blogs = (List<Blog>) request.getAttribute("blogs");
User loginUser = (User) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>blog</title>
</head>
<body>
	<div>
		<span><%= message %></span>
		<% if (loginUser != null) { %>
		<a href="post"><button>投稿する</button></a>
		<% } else { %>
		<a href="login.jsp"><button>ログイン</button></a>
		<% } %>
	</div>
	<div>
		<form action="query" method="post">
			<input type="text" name="keyword">
			<input type="submit" value="検索">
		</form>
	</div>
	<% for (Blog blog : blogs) { %>
		<hr>
		<div>
			<p><a href="/blog/post/<%= blog.getId() %>"><%= blog.getTitle() %></a></p>
			<p><%= blog.getBody().length() >= 5 
					? blog.getBody().substring(0, 5) + "..." 
					: blog.getBody() %></p>
			<p>投稿者: <a href="userpage/<%= blog.getUserId() %>"><%= blog.getUserId() %></a></p>
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
</body>
</html>
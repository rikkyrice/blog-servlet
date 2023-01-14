<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<h1>ブログ編集</h1>
	<form action="updateComplete" method="post">
		<input type="hidden" name="id" value="<%= blog.getId() %>">
		<div>
			<label>タイトル</label>
			<input type="text" id="blog-title" name="title" value="<%= blog.getTitle() %>">
		</div>
		<div>
			<label>本文</label>
			<textarea id="blog-body" name="body"><%= blog.getBody() %></textarea>
		</div>
		<input type="hidden" name="postedAt" value="<%= blog.getPostedAt() %>">
		<input type="submit" value="再投稿">
	</form>
</body>
</html>
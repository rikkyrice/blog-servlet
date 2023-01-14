<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>blog</title>
</head>
<body>
	<h1>ブログ投稿</h1>
	<form action="/blog/postComplete" method="post">
		<div>
			<label>タイトル</label>
			<input type="text" id="blog-title" name="title">
		</div>
		<div>
			<label>本文</label>
			<textarea id="blog-body" name="body"></textarea>
		</div>
		<input type="submit" value="投稿">
	</form>
</body>
</html>
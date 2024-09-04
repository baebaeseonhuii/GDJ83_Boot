<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>QNA ADD</h1>
<form action="" method="post" enctype="multipart/form-data">
	<p>board title</p>
	<input type="text" name="boardTitle">
	<p>board writer</p>
	<input type="text" name="boardWriter">
	<p>board contents</p>
	<textarea type="text" name="boardContents"></textarea>
	<input type="file" name="attaches">
	<input type="file" name="attaches">
	<input type="file" name="attaches">
	
	<button>Add</button>
</form>
</body>
</html>
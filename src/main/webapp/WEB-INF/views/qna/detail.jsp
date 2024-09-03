<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List</title>
</head>
<body>
<h1>List</h1>
<table>
	<thead>
		<tr>
			<th>Num</th>
			<th>Title</th>
			<th>Writer</th>
			<th>Date</th>
		</tr>
	</thead>
	
	<tbody>
		
			<tr>
			<th>${qnaVO.boardNum}</th>
			<th>${qnaVO.boardTitle}</th>
			<th>${qnaVO.boardWriter}</th>
			<th>${qnaVO.createDate}</th>
			</tr>
		
	</tbody>
</table>


</body>
</html>
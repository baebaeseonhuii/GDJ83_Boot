<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>QNA ADD</h1>

<form:form modelAttribute="qnaVO">
	<!-- path에 VO의 멤버변수명이 들어감 -->
	<p>board writer</p>
	<form:input path="boardWriter"/>
	<form:errors path="boardWriter"></form:errors><br>
	<p>board title</p>
	<form:input path="boardTitle"/>
	<form:errors path="boardTitle"></form:errors><br>
	<p>board contents</p>
	<form:textarea path="boardContents"/>
	<p>Files</p>
	<input type="file" name="attaches">
	<input type="file" name="attaches">
	<input type="file" name="attaches">
	<button>Add</button>
</form:form>

</body>
</html>
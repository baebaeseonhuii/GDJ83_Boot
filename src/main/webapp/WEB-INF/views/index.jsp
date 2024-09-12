<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>hi</h1>
<img alt="" src="/images/picture.png">

<spring:message code="hello"></spring:message>



	<sec:authorize access="!isAuthenticated()">
		<h1>로그인 하기 전</h1>
		<a href="/member/login">Login</a>
		<a href="/oauth2/authorization/kakao">Kakao Login</a>
	</sec:authorize>
	
	
	<sec:authorize access="isAuthenticated()">
		<h1>로그인 성공</h1>
		<sec:authentication property="principal" var="member"/>
		<spring:message code="member.login.message" arguments="${member.username},${member.email}" argumentSeparator=","></spring:message>
		<c:forEach items="${member.vos}" var="v">
			<h3>당신의 권한: ${v.roleName}</h3>
		</c:forEach>
		
		<a href="/member/logout">logout</a>
	</sec:authorize>
	
	<sec:authorize access="hasRole('ADMIN')">
		<h1>관리자 전용</h1>
	</sec:authorize>
	
</body>
</html>
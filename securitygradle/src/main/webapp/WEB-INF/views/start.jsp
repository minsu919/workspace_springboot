<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.7.1.min.js"></script>
<script>
$(document).ready(function(){
	
});
</script>
</head>
<body>
<h1>나의 홈페이지</h1>
<h3>접속아이디</h3>
<p>${loginid }</p>
<h3>나의 역할</h3>
<p>${loginrole }</p>

<h3>역할에 따른 페이지 이동권한 확인</h3>
<button onclick="location.href='/adminpage'">관리자페이지로 이동</button>
<button onclick="location.href='/userpage'">사용자페이지로 이동</button>

<button onclick="location.href='/logout'">로그아웃</button>

</body>
</html>
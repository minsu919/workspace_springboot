<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.7.1.min.js"></script>
<script src="javascript/loginform.js"></script>
</head>
<body>
<h1>로그인테스트폼</h1>
<form action="mypage" method="post">
	아이디입력:<input type=text name="id" ><br>
	암호입력:<input type=password name="pw"><br>
	<input type=submit value="로그인">
</form>
</body>
</html>
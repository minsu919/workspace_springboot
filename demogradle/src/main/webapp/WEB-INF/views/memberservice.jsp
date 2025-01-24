<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% System.out.println("memberservice.jsp 뷰 실행중"); %>
<h1>회원가입폼</h1>
<form action="memberservice" method="post">
아이디<input type=text name="id" ><br>
암호<input type=password name="pw" ><br>
이름<input type=text name="name" ><br>
폰<input type=text name="phone" ><br>
이메일<input type=email name="email" ><br>
가입일<input type="datetime-local" name="regdate" ><br>
<!-- 캘린더+시계 : 2025-01-06Txx:xx-->
<input type=submit value="회원가입" >
</form>
</body>
</html>
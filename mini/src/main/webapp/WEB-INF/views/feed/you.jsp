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
	
	if (${member.isadmin} == 1){
		$("h1").append(" : 관리자 계정");
		$("#admin").on('click', function(){
			window.location.href = "/admin";
		});
	} else {
		$("#admin").remove();
	}
	
	$("#logout").on('click', function(){
		window.location.href = "/logout";
	});
	
	$("#modify").on('click', function(){
		window.location.href = "/modify";
	});
	
});
</script>
</head>
<body>
<div id="mypage">
<p></p>
<input type=button id="logout" value="로그아웃">
<input type=button id="modify" value="회원정보 수정">
<input type=button id="admin" value="관리자 페이지">>
</div>
</body>
</html>
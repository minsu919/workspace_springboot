<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
$(document).ready(function(){
	
	$("#register").on('click', function(){
		window.location.href = "/register";
	});
	
	$("#login").on('click', function(){
		window.location.href = "/login";
	});

	$("#mypage").on('click', function(){
		window.location.href = "/mypage";
	});
	
	$("#logout").on('click', function(){
		window.location.href = "/logout";
	});
	$("#studio").on('click', function(){
		window.location.href = "/studio";
	});
	
});
</script>
</head>
<body>
<h1>메인페이지</h1>
	<input type="button" id="register" value="회원가입">
	
	<c:if test="${empty sessionid}">
	    <input type="button" id="login" value="로그인">
	</c:if>
	
	<c:if test="${not empty sessionid}">
	    <input type="button" id="mypage" value="내 정보">
	    <input type="button" id="logout" value="로그아웃">
	    <input type="button" id="studio" value="스튜디오">
	</c:if>
	
	
<div id="contents">
	<h1>게시물리스트</h1>
	<table border="2">
		<tr><th>번호</th><th>제목</th><th>작성자</th><th>조회수</th></tr>
		<c:forEach items="${boardlist }" var="dto">
		<tr><td>${dto.seq }</td><td><a href='comments/${dto.seq }'>${dto.title }</a></td><td>${dto.writer }</td><td>${dto.viewcount }</td></tr>
		</c:forEach>
	</table>
	<h3>페이지번호를 선택하세요</h3>
	<%
	int total = (Integer)request.getAttribute("total");
	int totalpage=0;
	if (total % 2 == 0){
		totalpage = total / 2;
	} else {
		totalpage = total / 2 + 1;
	}
	for (int i=1; i<=totalpage; i++){
	%>
	<a href="/?pagenum=<%=i %>"><%=i %>페이지</a>&nbsp;
	<%}
	%>
</div>
</body>
</html>
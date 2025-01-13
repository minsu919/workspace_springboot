<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% if(session.getAttribute("sessionid") != null ){ %>
<%=session.getAttribute("sessionid") %> 회원님 로그인중입니다 <br>
<%}
else{
%>
	로그인한 적이 없습니다.
<%
}
%>
<h1>나의 게시판 프로젝트</h1>
<h3> 서비스는 다음과 같습니다. </h3>
<h4>
	<ul>
		<li><a href="boardlist">게시물리스트 보러가기</a>(로그인하지 않아도 됩니다)</li>
		<li><a href="boardwrite" id="boardwritelink">글쓰기</a>(로그인하셔야 합니다)</li>
		<li><a href="boardlogin">로그인</a></li>
		<li><a href="boardlogout">로그아웃</a></li>
	</ul>
</h4>
</body>
</html>
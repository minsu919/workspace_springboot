<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>관리자 페이지</title>
    <script src="js/jquery-3.7.1.min.js"></script>
	<script>
	$(document).ready(function(){
	    $("#mypage").on('click', function(){
	    	window.location.href = "/mypage";
	    });
	});
    	
    function deleteMember(memberId) {
        window.location.href = "/deleteMember?id=" + memberId;
    }
	</script>
</head>
<body>
    <h1>회원 관리</h1>
    <p>${allMembers[0].regdate}</p>
    <table>
        <tr>
            <th>이름</th>
            <th>아이디</th>
            <th>비밀번호</th>
            <th>전화번호</th>
            <th>관리자여부</th>
            <th>가입일</th>
        </tr>
        <c:forEach items="${allMembers}" var="member">
            <tr>
                <td>${member.name}</td>
                <td>${member.id}</td>
                <td>${member.pw}</td>
                <td>${member.phone}</td>
                <td>${member.isadmin == 1 ? '관리자' : '사용자'}</td>
                <td>${member.regdate}</td>
                <td>
    			<button type="button" onclick="deleteMember('${member.id}')">삭제</button>
				</td>
            </tr>
        </c:forEach>
	</table>
	<input type="button" id="mypage" value="돌아가기"></input>
</body>
</html>
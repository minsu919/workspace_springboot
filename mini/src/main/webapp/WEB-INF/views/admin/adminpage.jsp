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
	<!-- CSS 추가 -->
	<style>
		body {
	    font-family: Arial, sans-serif;
	    margin: 0;
	    padding: 0;
	    background-color: #f4f4f4;
	  }
	  
	    h1 {
	    text-align: center;
	    color: #333;
	    margin-top: 20px;
	  }
	  
	  form {
	  	text-align: center;
	  	
	  }
	  
	  form .password input {
	  	height: 40px;
	  	margin: 10px 0;
	  	font-size: 27px;
	  }
	  
	  .button {
	    padding: 10px 20px;
	    font-size: 16px;
	    margin: 10px;
	    border: none;
	    border-radius: 5px;
	    background-color: #ff0000;
	    color: white;
	    cursor: pointer;
	    transition: background-color 0.3s ease;
	  }
	  
	  .button:hover {
	    background-color: #cc0000;
	  }
	  
	  .button1 {
		text-align:center;	  
	  }
	  
	  table {
	  	text-align: center;
	  	justify-content: center;
	  	margin: 10px auto;
	  	background-color: white;
	  	padding: 30px;
	  	border-radius: 10px;
	  	font-size: 20px;
	  	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	  }
	  
	 th, td {
	 	border-bottom: 2px solid black;
	 }
	</style>
</head>
<body>
    <h1>회원 관리</h1>
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
    			<button type="button" class="button" onclick="deleteMember('${member.id}')">삭제</button>
				</td>
            </tr>
        </c:forEach>
	</table>
	<div class=button1>
		<input type="button" id="mypage" class="button" value="돌아가기"></input>
	</div>
</body>
</html>
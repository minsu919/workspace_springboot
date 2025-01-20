<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script src="js/jquery-3.7.1.min.js"></script>
<script src="javascript/loginform.js"></script>
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
  
  .login-box input {
  	height: 40px;
  	width: 250px;
  	margin: 10px 0;
  	font-size: 27px;
  	border-radius: 10px;
  	padding-left: 10px;
  }
  
  .button {
    text-align: center;
    margin-top: 30px;
  }

  .button {
    padding: 10px 20px;
    font-size: 16px;
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
  
  .login-box .id {
  	margin-right: 10px;
  }
</style>
</head>
<body>
<h1>로그인</h1>
<form action="login" method="post">
	<div class="login-box">
		아이디입력: <input type=text  class="id" name="id" ><br>
		암호 입력: <input type=password name="pw"><br>
	</div>
	<input class="button" type=submit value="로그인">
</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>비밀번호 재확인</title>
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
  	border-radius: 10px;
  }
  
  .button-container {
    text-align: center;
    margin-top: 30px;
  }

  .button-container a, button {
    padding: 10px 20px;
    font-size: 16px;
    margin: 0 10px;
    border: none;
    border-radius: 5px;
    background-color: #ff0000;
    color: white;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }
  
  .button-container input:hover {
    background-color: #cc0000;
  }
  
  .password #pw {
  	padding-left: 10px;  
  }
  </style>    
</head>
<body>
    <h1>비밀번호를 다시 입력해주세요</h1>
    
    <form action="/modify/pwcheck" method="POST">
        <div class="password">
            <label for="password">비밀번호:</label>
            <input type="password" name="pw" id="pw" required>
        </div>
        <div class="button-container">
            <button type="submit">확인</button>
            <a href="/mypage" class="button" style="text-decoration: none;">취소</a>
        </div>
    </form>
</body>
</html>
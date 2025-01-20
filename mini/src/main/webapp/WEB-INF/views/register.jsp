<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
    <script src="js/jquery-3.7.1.min.js"></script>
    <script>
        function checkId() {
            var id = document.getElementById('id').value;

            if (id.length === 0) {
                document.getElementById("idCheckResult").textContent = "";
                return;
            }
			
            $.ajax({
                url: '/checkId',
                type: 'GET',
                data: { id: id },
                success: function(response) {
                    document.getElementById("idCheckResult").textContent = response;
                    
                    if(response.includes("중복된 아이디입니다.")) {
                    	document.getElementById("registerBtn").disabled = true;
                    } else {
                    	document.getElementById("registerBtn").disabled = false;
                    }
                },
                error: function(error) {
                    console.error("아이디 중복 확인 오류 ", error);
                }
            });
        }
        
		$(document).ready(function(){
        	
            $("#main").on('click', function(){
                window.location.href = "/";
                return false; 
            });
        });
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
  
  .button-container {
    text-align: center;
    margin-top: 30px;
  }

  .button-container input,
  .button-container #registerBtn {
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
  
  .button-container input:hover,
  .button-container #registerBtn:hover {
    background-color: #cc0000;
  }
   
  .login-form {
  	text-align: center;
  	margin: 0 auto;
  	background-color: white;
  	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  	width: 388px;
  	padding: 30px;
  	border-radius: 10px;
  }
  
  .login-form .login-box input {
  	height: 40px;
  	width: 300px;
  	margin: 10px 0;
  	font-size: 27px;
  	border-radius: 10px;
  	padding-left: 10px;
  }
  
  .login-form .form input[name="phone1"] {
  	width : 58px;
  }
  
  .login-form .form input[name="phone2"],
  .login-form .form input[name="phone3"] {
  	width : 95px;
  }
  
  .login-form .name {
  	margin-left: 32px;
  }
  
  .login-form #id { 
	margin-left: 15px;
  }
  
  .login-form #name { 
	margin-left: 29px;
  }
  
  .login-form input[name="phone"] {
  	width : 300px;
  }
</style>
</head>
<body>
    <h1>회원가입</h1>
    <form action="register" class="login-form" method="post">
    	<div class="login-box">
	        <label for="id">아이디: </label>
	        <input type="text" name="id" id="id" required oninput="checkId()">
	        <span id="idCheckResult"></span><br>
	
	        <label for="pw">비밀번호: </label>
	        <input type="password" name="pw" required><br>
	
	        <label for="name">이름: </label>
	        <input type="text" name="name" id="name" required><br>
	
	        <label for="phone">전화번호: </label>
	        <input type="text" name="phone" pattern="010-\d{3,4}-\d{4}" placeholder="010-1234-5678"title="010-XXXX-XXXX로 입력." required>
		</div>
		
		<label for="isadmin">관리자 여부: </label>
        <input type="radio" name="isadmin" value="1"> 관리자
        <input type="radio" name="isadmin" value="0" checked> 일반<br>
		<div class="button-container">
        	<button type="submit" id="registerBtn">가입하기</button>
        	<input type=button id="main" value="메인">
        </div>
    </form>
</body>
</html>
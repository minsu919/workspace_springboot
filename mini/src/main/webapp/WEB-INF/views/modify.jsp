<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 수정</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$(document).ready(function(){
		
	$("#mypage").on('click', function(){
		window.location.href = "/mypage"; // /mypage로 리다이렉트
	});
});
</script>
<!-- CSS 스타일 추가 -->
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

  .button-container input {
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
  
  .button-container input:hover {
    background-color: #cc0000;
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
	  
	  
  .modify-form {
  	text-align: center;
  	margin: 0 auto;
  	background-color: white;
  	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  	width: 500px;
  	padding: 30px;
  	border-radius: 10px;
  }
  
  .modify-form .form input {
  	height: 40px;
  	width: 300px;
  	margin: 10px 0;
  	font-size: 27px;
  	border-radius: 10px;
  	padding-left: 10px;
  }
  
  .modify-form .form input[name="phone"] {
  	width : 300px;
  }
  
  .modify-form .name {
  	margin-left: 32px;
  }
  
  .modify-form .id {
	margin-left: 16px;  
  }

</style>
</head>
<body>
	<h1>회원 정보 수정</h1>
    <form action="/modify" method="POST" class="modify-form">
    	<div class = form>
    		<label for="name" class="id">아이디:</label>
        		<input type="text" name="id" value="${member.id}" readonly><br>
        
        	<label for="name" class="name">이름:</label>
        		<input type="text" id="name" name="name" value="${member.name}" readonly><br>	
        
        	<label for="pw">비밀번호:</label>
        		<input type="password" id="pw" name="pw" value="" required><br>
        
        	<label for="phone">전화번호:</label>
	        	<input type="text" name="phone" pattern="010-\d{3,4}-\d{4}" placeholder="010-1234-5678"title="010-XXXX-XXXX로 입력." required>
        </div>
        
		<div class="button-container">
        	<input type="submit" value="회원 수정">
        	<input type="button" id="mypage" value="돌아가기">
        </div> 
    </form>
    
    <form action="/deleteMember" method="POST">
    	<div class="button-container">
	    	<input type="hidden" name="id" value="${member.id}">
			<input type="submit" value="회원 삭제" onclick="return confirm('ID:${member.id} 계정을 삭제하려면 확인버튼을 눌러주세요.');">
			
		</div>    
    </form>
</body>
</html>
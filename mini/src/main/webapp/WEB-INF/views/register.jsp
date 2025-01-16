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
                window.location.href = "/main";
                return false; 
            });
        });
    </script>
</head>
<body>
    <h1>회원가입</h1>
    <form action="register" method="post">
        <label for="id">아이디:</label>
        <input type="text" name="id" id="id" required oninput="checkId()">
        <span id="idCheckResult"></span><br>

        <label for="pw">비밀번호:</label>
        <input type="password" name="pw" required><br>

        <label for="name">이름:</label>
        <input type="text" name="name" required><br>

        <label for="phone">전화번호:</label>
        <input type="text" name="phone" pattern="^010-\d{3,4}-\d{4}$" title="010-XXXX-XXXX 형식으로 입력" required><br>
		
		<label for="isadmin">관리자 여부:</label>
        <input type="radio" name="isadmin" value="1"> 관리자
        <input type="radio" name="isadmin" value="0" checked> 일반<br>
		
        <button type="submit" id="registerBtn">가입하기</button>
        <input type=button id="main" value="메인">
    </form>
</body>
</html>
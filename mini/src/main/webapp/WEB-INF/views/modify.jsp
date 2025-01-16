<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 수정</title>
</head>
<body>
	<h2>회원 정보 수정</h2>
    <form action="/modify" method="POST">
    	<label for="name">아이디:</label>
        <input type="text" name="id" value="${member.id}" readonly><br>
        
        <label for="name">이름:</label>
        <input type="text" id="name" name="name" value="${member.name}" readonly><br>
        
        <label for="pw">비밀번호:</label>
        <input type="password" id="pw" name="pw" value="" required><br>
        
        <label for="phone">전화번호:</label>
        <input type="text" name="phone" pattern="^010-\d{3,4}-\d{4}$" title="010-XXXX-XXXX 형식으로 입력" required><br>

        <input type="submit" value="회원 수정">
    </form>
    
    <form action="/deleteMember" method="POST">
    	<input type="hidden" name="id" value="${member.id}">
		<input type="submit" value="회원 삭제" onclick="return confirm('ID:${member.id} 계정을 삭제하려면 확인버튼을 눌러주세요.');">    
    </form>
</body>
</html>
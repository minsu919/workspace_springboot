<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>비밀번호 재확인</title>
</head>
<body>
    <h2>비밀번호를 입력해주세요</h2>
    
    <form action="/modify/pwcheck" method="POST">
        <label for="password">비밀번호:</label>
        <input type="password" name="pw" id="pw" required><br>

        <input type="submit" value="확인">
    </form>

    <form action="/mypage" method="GET">
        <input type="submit" value="취소" />
    </form>
</body>
</html>
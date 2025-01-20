<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.7.1.min.js"></script>
<script>
$(document).ready(function(){
	
});
</script>
<style>
/* 기본 바디 스타일 */
body {
    font-family: Arial, sans-serif;
    background-color: #f9f9f9;
    color: #333;
    margin: 0;
    padding: 0;
}

/* 헤더 스타일 */
h1 {
    font-size: 2rem;
    color: #000000;
    text-align: center;
    margin-top: 20px;
}

/* 폼 박스 스타일 */
.studio-box {
    background-color: #ffffff;
    border-radius: 8px;
    padding: 30px;
    max-width: 600px;
    margin: 20px auto;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

/* 레이블 스타일 */
label {
    font-size: 1rem;
    font-weight: bold;
    display: block;
    margin-bottom: 10px;
    color: #333;
}

/* 텍스트 입력 필드 스타일 */
input[type="text"], textarea {
    width: 100%;
    padding: 10px;
    margin-bottom: 15px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    font-size: 1rem;
    transition: border 0.3s ease;
}

input[type="text"]:focus, textarea:focus {
    border-color: #ff0000;
    outline: none;
}

/* 파일 선택 버튼 스타일 */
input[type="file"] {
    font-size: 1rem;
    padding: 10px;
    margin-bottom: 15px;
    background-color: #f1f1f1;
    border: 1px solid #ccc;
    border-radius: 4px;
    cursor: pointer;
}

/* 제출 버튼 스타일 */
input[type="submit"] {
    background-color: #ff0000;
    color: white;
    border: none;
    padding: 12px 20px;
    font-size: 1.1rem;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

input[type="submit"]:hover {
    background-color: #cc0000;
}

/* 레이아웃을 더 깔끔하게 하기 위한 작은 여백 */
textarea {
    resize: none;
}

/* 마진과 패딩 제거 */
body, html {
    margin: 0;
    padding: 0;
}
</style>

</head>
<body>
<h1>
<h1>스튜디오</h1>
<form action="boardwrite" method="post" enctype="multipart/form-data">
<div class="studio-box">
	<label for="title">제목</label><input type=text id="title" name="title"><br>
	<label for="boardtext">내용</label>
	<textarea rows="5" cols="50" id="boardtext" name="boardtext"></textarea><br>
	<label for="writer">작성자</label><input type=text id="writer" name="writer" 
	value=${sessionScope.sessionid } readonly="readonly"><br>
	<label for="photo">사진선택</label><input type="file" id="photo" name="photo"><br>
	<input type=submit value="게시물 작성" >
</div>

</form>
</h1>
</body>
</html>
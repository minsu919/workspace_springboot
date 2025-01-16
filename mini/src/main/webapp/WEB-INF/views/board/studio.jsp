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
</head>
<body>
<h1>
<h1>글쓰기폼</h1>
<form action="boardwrite" method="post" enctype="multipart/form-data">
<label for="title">제목</label><input type=text id="title" name="title"><br>
<label for="boardtext">내용</label>
<textarea rows="5" cols="50" id="boardtext" name="boardtext"></textarea>
<label for="writer">작성자</label><input type=text id="writer" name="writer" 
value=${sessionScope.sessionid } readonly="readonly"><br>
<label for="photo">사진선택</label><input type="file" id="photo" name="photo"><br>
<input type=submit value="게시물 작성" >
</form>
</h1>
</body>
</html>
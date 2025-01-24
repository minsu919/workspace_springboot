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
	$("#deletebtn").on('click', function(){
		//let userpw = prompt("암호입력하세요");
		$("#inputpw").html("<input type='password' id='userpw'><input type='button' id='checkpwbtn' value='암호확인'>");
		$("#checkpwbtn").on('click', function(){
			let userpw = $("#userpw").val();
			let dbpw = ${detailboard.pw};
			if (userpw == dbpw){
				$.ajax({
					url : "boarddelete",
					data : {'seq' : ${detailboard.seq }},
					type : 'get',
					dataType : 'json',
					
					success : function(res){
							$("#inputpw").append("<h3>"+res.result+"</h3>");
							}
				}); //ajax
			} else {
				alert("암호 불일치");
			}
		}); // checkpwbtn
		
		
	}); // deletebtn
	
	$("#updatebtn").on('click', function(){
		//let userpw = prompt("암호입력하세요");
		$("#inputpw").html("<input type='password' id='userpw'><input type='button' id='checkpwbtn' value='암호확인'>");
		$("#checkpwbtn").on('click', function(){
			let userpw = $("#userpw").val();
			let dbpw = ${detailboard.pw};
			if (userpw == dbpw){
				$.ajax({
					url : "boardupdate",
					data : $("form").serialize(), // jquery 함수 - $("#form").serialize() form 입력값 = json 객체변환
					type : 'get',
					dataType : 'json',
					
					success : function(res){
						alert(res.result);
							}
				}); //ajax
			} else {
				alert("암호 불일치");
			}
		}); // checkpwbtn
		
		
	}); // updatebtn
	
	$("#listbtn").on('click', function(){
		location.href="boardlist";
	});
});
</script>
</head>
<body>
<h1>상세게시물 화면입니다.</h1>
<form action="" method="">
	<table border=2>
	<tr><td>번호</td><td><input type=text value='${detailboard.seq }' readonly name='seq'></td></tr>
	<tr><td>제목</td><td><input type=text value='${detailboard.title }' name='title'></td></tr>
	<tr><td>내용</td><td><textarea cols=50 rows=5 name='contents'> ${detailboard.contents }</textarea></td></tr>
	<tr><td>작성자</td><td><input type=text value='${detailboard.writer }' name='writer' readonly></td></tr>
	<tr><td>조회수</td><td><input type=text value='${detailboard.viewcount }' name='viewcount' readonly></td></tr>
	<tr><td>작성시간</td><td><input type=text value='${detailboard.writingtime }' name='writingtime' readonly></td></tr>
	<tr><td>첨부파일명</td><td><a href='boarddownload?filename=${detailboard.file1 }'>${detailboard.file1 }</a></td></tr>
	<tr><td colspan="2" style="text-align:center">
	<input type=button value="수정버튼" id="updatebtn">
	<input type=button value="삭제버튼" id="deletebtn">
	</td></tr>
	</table>
</form>
<div id="inputpw"></div>

<input type=button value="게시물리스트" id="listbtn">
</body>
</html>
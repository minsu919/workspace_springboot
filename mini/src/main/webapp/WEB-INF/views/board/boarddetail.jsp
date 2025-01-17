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
    var adminid = "admin01"; // adminid 변수 선언

    // JSP 변수들을 JavaScript 변수로 전달
    var sessionid = "${sessionid}";
    var writer = "${detailboard.writer}";

    // sessionid가 detailboard.writer 또는 adminid와 일치하면 readonly 제거
    if (sessionid == writer || sessionid == adminid) {
        console.log("참이다");  // console.prompt() 대신 console.log() 사용
        $("textarea[name='boardtext']").removeAttr("readonly");
        $("input[name='title']").removeAttr("readonly");
    }
	
	$("#listbtn").on('click', function(){
		location.href = "/";
	});
	
	$("#deletebtn").on('click', function(){
				$.ajax({
					url : "boarddelete",
					data : {'seq' : ${detailboard.seq }},
					type : 'get',
					dataType : 'json',
					
					success : function(res){
						alert("삭제되었습니다!");
						location.href = "/";
					}
				});
	}); // deletebtn
	
	$("#updatebtn").on('click', function(){
		$.ajax({
			url : "boardupdate",
			data : $("form").serialize(),
			type : 'get',
			dataType : 'json',
			
			success : function(res){
				alert("수정되었습니다!");
			}
		}); //ajax
	}); // updatebtn
	
});
</script>
</head>
<body>
<h1>게시물 ${detailboard.seq }</h1><input type=button value="미니튜브" id="listbtn">
<form action="" method="">
	<input type="hidden" value="${detailboard.seq }" name='seq'>
	<c:if test="${sessionid == detailboard.writer or sessionid.equals('admin01') }">
		<input type=button value="수정" id="updatebtn">
		<input type=button value="삭제" id="deletebtn">
	</c:if>
	<div style="text-align:left">
		<div><h3><input type=text value='${detailboard.title }' name='title' readonly></h3></div>
		<div id="imgview"><img height="180" width="280" alt="이미지" src="imgdownload?filename=${detailboard.photofile }"></div>
		<div>
			<textarea cols=50 rows=5 name='boardtext' readonly> ${detailboard.boardtext }</textarea>
			<div>${detailboard.writer }</div>
			<div>조회수 : ${detailboard.viewcount }k</div>
			<div>${detailboard.writingtime }</div>
		</div>
	
	</div>
</form>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>아이디입력폼</h1>
아이디<input type=text name="id" id="id"> 
암호<input type=password  id="pw"> 
<input type=button id="listbtn" value="회원아이디리스트"> 

<div id="pwdiv"></div>
<div id="list"></div>

<!--  jquery3.7.1.... 서버 요청 mvc 실행 무관 파일 찾아서 그대로 전송 -->
<script src="resources/js/jquery-3.7.1.min.js"></script>
<script>
$("#listbtn").on('click', function(){
	$.ajax({
		url:"ajaxadminlist",
		data:{'id': $("#id").val(),  'pw':$("#pw").val() },
		type:"get",
		dataType:'json',
		success:function(server){
			$("#list").css("background-color", "silver");
			$("#list").html("");
			for(let i = 0; i < server.length; i++){
				$("#list").append
				("<h4><a href=''>" + server[i].id + "</a></h4>");
			}
		},
		error:function(){}
	});//ajax
});// listbtn on
// 동적 이벤트처리 생성 추가 태그의 이벤트 처리 
$("#list").on('click', 'a' , function(e){
	alert(" a클릭 ");
	e.preventDefault();
	$.ajax({
		url:"getpw/" + $(this).text(),
		type:"get",
		dataType:'json',
		success:function(server){
			$("#pwdiv").css("background-color", "silver");
			$("#pwdiv").html("암호=" + server.pw);
//
		},
		error:function(request, status, error){
			alert(request.responseText);
		}
	});//ajax
});// a on

</script>
 


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.7.1.min.js"></script>
<script>
$(document).ready(function(){
	
	if (${member.isadmin} == 1){
		$("h1").append(" : 관리자 계정");
		$("#admin").on('click', function(){
			window.location.href = "/admin";
		});
	} else {
		$("#admin").remove();
	}
	
	$("#logout").on('click', function(){
		window.location.href = "/logout";
	});
	
	$("#modify").on('click', function(){
		window.location.href = "/modify";
	});
	
	// 회원이 업로드한 게시물 리스트 가져오기

		$.ajax({
			url : "myboardlist",
			data : {'writer': '${sessionid}' },
			type : "get",
			dataType : 'json',
			
			success:function(res){
				$("#mylist").css("background-color", "silver");
				$("#mylist").html("");
				for(let i = 0; i < res.length; i++){
					$("#mylist").append
					('<div class="post-card">' +
					        '<a href="watch?seq=' + res[i].seq + '" autofocus="autofocus">' +
				            '<img alt="메인페이지이미지" src="imgdownload?filename=' + res[i].photofile + '">' +
				            '<div id="textcontents">' +
				                '<span>' + res[i].title + '</span>' +
				                '<span>조회수 ' + res[i].viewcount + 'k . ' + res[i].writingtime + '</span>' +
				            '</div>' +
				        '</a>' +
				    '</div>'	
					);
				} // for end
			}
			
		});//ajax
});
</script>
<style>
  .post-card {
    width: 30%;
    box-sizing: border-box;
    display: flex;
    flex-direction: column;
  }

  .post-card img {
    width: 100%;
    height: auto;
    object-fit: cover;
    border-radius: 8px;
  }

  .post-card div {
    margin-left: 20px;
    display: flex;
    flex-direction: column;
  }

  .post-card div span {
    font-size: 14px;
    color: #666;
    margin-top: 5px;
  }
</style>
</head>
<body>
<a href="/"><img height="60" width="80" src="images/minitube.png"></a>
<h1>${sessionid }</h1>
<div id="mypage">
  <p></p>
  <input type=button id="logout" value="로그아웃">
  <input type=button id="modify" value="회원정보 수정">
  <input type=button id="admin" value="관리자 페이지">

  <div id="mylist-container">
    <h3>내가 업로드한 게시물</h3>
    <div id='mylist'></div>
  </div>
</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>미니튜브</title>
<script src="js/jquery-3.7.1.min.js"></script>
<script>
$(document).ready(function(){
	
	$("#register").on('click', function(){
		window.location.href = "/register";
	});
	
	$("#login").on('click', function(){
		window.location.href = "/login";
	});

	$("#mypage").on('click', function(){
		window.location.href = "/mypage";
	});
	
	$("#logout").on('click', function(){
		window.location.href = "/logout";
	});
	$("#studio").on('click', function(){
		window.location.href = "/studio";
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
  
    .button-container input[name=searchquery] {
    background-color: white;
    border: 2px black solid;
    color : black;
  }

  .button-container input:hover {
    background-color: #cc0000;
  }

  #contents {
    width: 80%;
    margin: 40px auto;
  }

  #contents h1 {
    text-align: left;
    font-size: 28px;
    margin-bottom: 20px;
    color: #333;
  }

  .post-list {
  display: flex;
  flex-wrap: wrap;
  gap: 20px; /* 두 카드 사이의 간격 */
}
  .post-card {
  width: calc(50% - 10px);
  background-color: white;
  padding: 15px;
  border-radius: 8px;
  border: 2px solid black;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  cursor: pointer;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

  .post-card:hover {
    transform: scale(1.05);
    box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
  }

  .post-card img {
  width: 100%;
  height: 120px;
  object-fit: cover;
  border-radius: 8px;
}

.post-card div {
  margin-top: 10px;
}

.post-card div span {
  font-size: 14px;
  color: #666;
}

  /* 페이지 네비게이션 */
  .pagination {
    text-align: center;
    margin-top: 30px;
  }

  .pagination a {
    padding: 10px 15px;
    font-size: 16px;
    color: #ff0000;
    text-decoration: none;
    border-radius: 5px;
    margin: 0 5px;
    border: 1px solid #ff0000;
    transition: background-color 0.3s ease, color 0.3s ease;
  }

  .pagination a:hover {
    background-color: #ff0000;
    color: white;
  }

  a {
  	text-decoration: none;
  }
  
  #textcontents {
  font-size: 3;
  
  }
</style>

</head>
<body>

<h1>미니튜브</h1>
<div id="searchbar">

</div>
<div class="button-container">
	<form action="/results" method="get">
		<input type="text" name="searchquery" id="searchquery" required>
		<input type="submit" value="검색" id="searchbtn">
	</form>
    <input type="button" id="register" value="회원가입">
    
    <c:if test="${empty sessionid}">
        <input type="button" id="login" value="로그인">
    </c:if>
    
    <c:if test="${not empty sessionid}">
        <input type="button" id="mypage" value="내 정보">
        <input type="button" id="logout" value="로그아웃">
        <input type="button" id="studio" value="스튜디오">
    </c:if>
</div>

<div id="contents">
  <h1>게시물리스트</h1>

	<div class="post-list">
	  <c:forEach items="${boardlist}" var="dto">
	    <div class="post-card" id='watchseq${dto.seq}'>
	      <a href='watch?seq=${dto.seq}'>
	        <img alt="메인페이지이미지" src="imgdownload?filename=${dto.photofile}">
	        <div>
	          <span>${dto.title}</span>
	          <span>${dto.writer}</span>
	          <span>조회수 ${dto.viewcount}k . ${dto.writingtime}</span>
	        </div>
	      </a>
	    </div>
	  </c:forEach>
	</div>

  <div class="pagination">
    <h3>페이지번호를 선택하세요</h3>
    <%
    int total = (Integer)request.getAttribute("total");
    int totalpage = (total % 4 == 0) ? total / 4 : total / 4 + 1;
    for (int i = 1; i <= totalpage; i++) {
    %>
      <a href="/?pagenum=<%=i %>"><%=i %>페이지</a>
    <% } %>
  </div>
</div>

</body>
</html>

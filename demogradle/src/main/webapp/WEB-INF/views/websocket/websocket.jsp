<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
별명:<input type=text id="nickname" >
<input type=button id="enterbtn" value="입장버튼">
<input type=button id="exitbtn" value="퇴장버튼">

<h1>채팅영역</h1>
<div id="chatmessagearea" style="background-color:yellow;border:2px solid black">대화내용 출력<br></div>
<input type=text id="message"><!-- 내가 전송하려는 대화 입력-->
<input type=button id="sendbtn" value="대화전송">

<script src="/js/jquery-3.7.1.min.js"></script>
<script>
let websocket;
$(function(){
	$("#enterbtn").on('click', function(){
		websocket = new WebSocket("ws://localhost:8080/chatws");
		//서버 통신 시작. websocket.onopen 연결 함수 같이 호출실행
		
		websocket.onopen= onOpen;
		websocket.onclose= onClose;
		websocket.onmessage= onMessage;
	});//enterbtn on
	$("#exitbtn").on('click', function(){//서버 통신 해제
		websocket.close(); //websocket.onclose상태해당함수 같이 호출실행
	});//exitbtn on
	$("#sendbtn").on('click', function(){
		let nickname = $("#nickname").val();
		let msg = $("#message").val(); 
		websocket.send(nickname + ":" + msg);//서버로 내대화 보낸다-웹소켓서버
	});//sendbtn on
});

function onOpen(){
	console.log("웹소켓연결성공");
	let nickname = $("#nickname").val();
	websocket.send(nickname + " 님이 대화방 입장하셨습니다.");
	
}
function onClose(){
	console.log("웹소켓연결해제성공");
	$("#chatmessagearea").html("");
}

function onMessage(ev){//웹소켓서버로부터 메시지 받은 이벤트 객체-handleMessage메소드 실행
	console.log("서버로부터 대화 받기 성공");
	$("#chatmessagearea").append(ev.data + "<br>");
}
</script>
<!-- 
1.websocket = 
new WebSocket("ws://localhost:8081/chatws");

websocket.onopen= function(){서버와 웹소켓 연결시 실행 문장;}

websocket.onclose= function(){서버와 웹소켓 연결해제시 실행 문장;}

websocket.onmessage= function(){서버로부터웹소켓응답 있을시 실행 문장;}

websocket.send("메시지");//웹소켓연결중 서버로 메시지 전송 메소드
 -->
</body>
</html>
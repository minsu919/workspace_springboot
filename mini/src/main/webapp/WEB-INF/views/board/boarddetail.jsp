<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시물 상세</title>
    <script src="js/jquery-3.7.1.min.js"></script>
    <script>
    
    const commentData = []
    const updateComments = function (id) {
    	const idTag = id.replaceAll(/[^0-9]/g, '')
    	let data = commentData[idTag]
    	data.contents = $('#contents' + idTag).val() 
    	
    	$.ajax({
            url: '/comments/update',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function(response) {
                const res = JSON.parse(response);
                alert(res.message);
                if (res.status === 'success') {
                    location.reload();
                }
            },
            error: function() {
                alert('댓글 수정 실패');
            }
        });
    	
    }

    const deleteComments = function (id) {
    	const comment = commentData[id.replaceAll(/[^0-9]/g, '')]
    	
    	$.ajax({
            url: '/comments/delete',
            type: 'POST',
            contentType: 'application/x-www-form-urlencoded',
            data: {
            	seq: comment.seq,
                writer: comment.writer,
                boardseq: comment.boardseq 
            },
            success: function(response) {
                const res = JSON.parse(response);       
                alert(res.message);
                if (res.status === 'success') {
                    location.reload();
                }
            },
            error: function() {
                alert('댓글 삭제 실패');
            }
        });
    }
    
        $(document).ready(function(){
            // JSP 변수들을 JavaScript 변수로 전달
            var sessionid = "${sessionid}";
            var writer = "${detailboard.writer}";
            var memberisadmin = "${member.isadmin}"; 
        
            // sessionid가 detailboard.writer 또는 adminid와 일치하면 readonly 제거
            if (sessionid == writer || memberisadmin == 1) {
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
            
            //댓글리스트 요청
            $.ajax({
    			url : "mycommentlist",
    			data : {'boardseq': ${detailboard.seq} },
    			type : "get",
    			dataType : 'json',
    			
    			success:function(res){
    				$("#mylist").html("");
    				$("#comments-list").html("");
    				for(let i = 0; i < res.length; i++){
    			
    					commentData.push(res[i])
    				
    					if (res[i].writer === sessionid){
    						$("#comments-list").append
        					(
        					'<div>'+
        					'<p><strong>' + res[i].writer + '</strong>: ' + 
        					'<input type="text" value="' + res[i].contents + '" name="contents' + i + '" id="contents' + i + '"/>'+ 
        					'</p>' +
                            '<button id="update' + i + '" class="update">수정</button>' +
                            '<button id="delete' + i + '" class="delete">삭제</button>' +
                            '<p>' + res[i].regdate + '</p>' +
                        	'</div>'
        					);
    					} else {
    						$("#comments-list").append
        					(
        					'<div>'+
                            '<p><strong>' + res[i].writer + '</strong>: ' + res[i].contents + '</p>' +                    
                            '<p>' + res[i].regdate + '</p>' +
                        	'</div>'
        					);
    					}
    					
    				} // for end
    			}
    		}).always(function () {
    			$('.update').click(function(event){
    				updateComments($(this).attr('id'));
    			})
    			
    			$('.delete').click(function(event){
    				deleteComments($(this).attr('id'))
    			})
    		});//ajax
            
        });
        </script>

</head>
<body>
    <div class="container">
        <h1>게시물 ${detailboard.seq }</h1><input type=button value="미니튜브" id="listbtn">

        <!-- 게시물 정보 -->
        <div id="post-detail">
            <form action="" method="">
                <input type="hidden" value="${detailboard.seq }" name='seq'>
                <c:if test="${sessionid == detailboard.writer or member.isadmin == 1}">
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
        </div>

        <!-- 댓글 목록 -->
        <div id="comments-section">
            <h2>댓글</h2>
            <div id="comments-list">
            </div>
        </div>

        <!-- 댓글 작성 -->
        <div id="comment-form">
            <h3>댓글 작성</h3>
            <form id="add-comment-form">
                <input type="hidden" id="boardseq" name="boardseq" value="${detailboard.seq}">
                <textarea id="new-comment" placeholder="댓글 내용을 입력하세요" required></textarea>
                <button id="add-comment-btn" type="submit">댓글 작성</button>
            </form>
        </div>
    </div>

    <script>
        // 댓글 추가 ajax
        $('#add-comment-btn').click(function(event) {
            event.preventDefault();

            const detailboardseq = ${detailboard.seq}; // boardseq 값 가져오기
            const newComment = {
                boardseq: detailboardseq,
                contents: $('#new-comment').val(),
                writer: '${sessionid }'
            };

            $.ajax({
                url: '/comments/add',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(newComment),
                
                success: function(response) {
                    const res = JSON.parse(response);
                    alert(res.message);
                    if (res.status === 'success') {
                        location.reload();
                    }
                },
                error: function() {
                    alert('댓글 추가 실패');
                }
            });
        });
    </script>
</body>
</html>
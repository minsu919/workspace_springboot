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
            
            /* $("#listbtn").on('click', function(){
                location.href = "/";
            }); */
            
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

<!-- css -->
    <style>
   body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f4f4f4;
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
    }

    .container {
        width: 80%; 
        margin: auto;
        background: #fff;
        border: 1px solid #ccc;
        border-radius: 5px;
        padding: 30px; 
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    h1 {
        text-align: center;
        color: #333;
        margin-top: 20px;
    }

    h2 {
        text-align: center;
        color: #333;
        margin-top: 20px;
    }

    .button-container {
        text-align: center;
        margin-top: 30px;
    }

    #post-detail {
        border: 1px solid #ddd;
        border-radius: 5px;
        padding: 20px; 
        margin-bottom: 30px; 
        background-color: #fafafa;
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    #post-detail img {
        max-width: 100%; 
        width: 80%; 
        height: auto; 
        margin-top: 15px; 
        margin-bottom: 15px; 
        border-radius: 5px; 
    }

    #comments-section {
        border: 1px solid #ddd;
        border-radius: 5px;
        padding: 20px; 
        background-color: #fafafa;
    }

    #comments-list div {
        border-bottom: 1px solid #eee;
        padding: 15px 0;
    }

    #comments-list div:last-child {
        border-bottom: none;
    }

    textarea, input[type="text"] {
        width: 100%;
        box-sizing: border-box;
        padding: 12px; 
        margin-top: 12px; 
        margin-bottom: 12px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    button {
        padding: 12px 24px; 
        border: none;
        border-radius: 5px;
        background-color: #ff0000;
        color: white;
        cursor: pointer;
    }

    button:hover {
        background-color: #cc0000;
    }

    #comment-form {
        margin-top: 30px;
        padding-top: 15px;
        border-top: 1px solid #ddd;
    }

    #post-detail .writer-button {
        display: none;
    }
  
 
        }
        
    </style>

</head>
<body>
    <div class="container">
        <div class="start_image">
	<a href="/"><img height="30" width="45" src="images/minitube.png"></a>
		</div>
        <h1>게시물 ${detailboard.seq }</h1>

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
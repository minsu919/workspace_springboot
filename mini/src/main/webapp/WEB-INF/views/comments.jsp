<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>댓글관리테스트용</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .comment-container {
            width: 60%;
            margin: 20px auto;
        }
        .comment {
            border-bottom: 1px solid #ddd;
            padding: 10px 0;
        }
        .comment-form {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="comment-container">
        <h1>댓글 관리 시스템</h1>

        <!-- 댓글 입력 폼 -->
        <form action="/comments/add" method="post" class="comment-form">
            <input type="hidden" name="boardSeq" value="${boardSeq}">
            <label for="writer">작성자:</label>
            <input type="text" id="writer" name="writer" required >
            <br><br>
            <label for="contents">내용:</label>
            <textarea id="contents" name="contents" required></textarea>
            <br><br>
            <button type="submit">댓글 작성</button>
        </form>

        <!-- 댓글 목록 -->
        <h2>댓글 목록</h2>
        <div id="commentsSection">
            <c:forEach var="comment" items="${comments}">
                <div class="comment">
                    <p><strong>${comment.writer}</strong>: ${comment.contents}</p>
                    <p><small>${comment.regDate}</small></p>

                    <!-- 댓글 수정 폼 -->
                    <form action="/comments/update" method="post" style="display:inline;">
                        <input type="hidden" name="seq" value="${comment.seq}">
                        <input type="hidden" name="boardSeq" value="${boardSeq}">
                        <input type="hidden" name="writer" value="${comment.writer}">
                        <label for="updateContents-${comment.seq}">수정 내용:</label>
                        <input type="text" id="updateContents-${comment.seq}" name="contents" placeholder="수정할 내용" required>
                        <button type="submit">수정</button>
                    </form>

                    <!-- 댓글 삭제 폼 -->
                    <form action="/comments/delete" method="post" style="display:inline;">
                        <input type="hidden" name="seq" value="${comment.seq}">
                        <input type="hidden" name="boardSeq" value="${boardSeq}">
                        <input type="hidden" name="writer" value="${comment.writer}">
                        <button type="submit">삭제</button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>



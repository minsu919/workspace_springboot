<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>시청 기록</title>
</head>
<body>
    <h1>${member.name}님의 시청 기록</h1>

    <table>
    <tr>
        <th>ID</th>
        <th>Board ID</th>
        <th>View Time</th>
    </tr>
    <c:forEach items="${viewdList}" var="viewd">
        <tr>
            <td>${viewd.id}</td>
            <td>${viewd.boardid}</td>
            <td>${viewd.viewtime}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>로그인</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
     <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" >
</head>
<body>
<div class="container">
    <form class="form-signin" method="post" action="/login-process">
        <h2 class="form-signin-heading text-center mb-5">시큐리티로그인</h2>

        <p>
            <label for="username" class="sr-only">아이디</label>
            <input type="text" id="username" name="userid" class="form-control" placeholder="아이디" required="" autofocus="">
        </p>
        <p>
            <label for="password" class="sr-only">비밀번호</label>
            <input type="password" id="password" name="pw" class="form-control" placeholder="비밀번호" required="">
        </p>
        <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
    </form>
</div>
</body>
</html>
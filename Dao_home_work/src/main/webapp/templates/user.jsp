<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="/templates/header.jsp"/>
</head>
<body class="users">
    <div class="container">
        <h1>${Title}</h1>
        <a href="/">Главная</a> -> <a href="/users">Пользователи</a> -> <a href="">${Title}</a><br><br>

        <p><b>ИД: </b>${user.getId()}</p>
        <p><b>Имя: </b>${user.getName()}</p>
        <p><b>Возраст: </b>${user.getAge()}</p>
    </div>
</body>
</html>
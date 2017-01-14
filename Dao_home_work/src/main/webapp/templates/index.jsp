<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="/templates/header.jsp"/>
</head>
<body>
    <div class="container">
        <h1>Восславь солнце !</h1><%=request.getContextPath()%>
        <a href="users">Отобразить список пользователей</a><br>
        <a href="autos">Отобразить список автомобилей</a>
    </div>
</body>
</html>
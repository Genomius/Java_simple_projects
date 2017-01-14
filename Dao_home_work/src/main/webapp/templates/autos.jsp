<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${Title}</title>
    <jsp:include page="/templates/header.jsp"/>
</head>
<body>
<h1>Автомобили</h1>
<a href="/">Главная</a> -> <a href="">Автомобили</a><br><br>

<c:forEach var="auto" items="${autos}">
    ${auto.getId()} ${auto.getModel()} ${auto.getColor()} ${auto.getUserId()}
    <button data-id="${auto.getId()}">Удалить</button>
    <button data-id="${auto.getId()}">Изменить</button><br>
</c:forEach>
<button>Добавить автомобиль</button>
</body>
</html>
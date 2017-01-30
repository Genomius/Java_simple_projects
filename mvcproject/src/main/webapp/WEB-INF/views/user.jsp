<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp"/>
    <div class="content users">
        <h1>${title}</h1>
        <a href="/">Главная</a> -> <a href="/users">Пользователи</a> -> <a href="">${title}</a><br><br>

        <p><b>ИД: </b>${user.id}</p>
        <p><b>Имя: </b>${user.name}</p>
        <p><b>Возраст: </b>${user.age}</p>

        <a href="/users/${user.id}/autos">Показать автомобили пользователя</a>
    </div>
<jsp:include page="footer.jsp"/>

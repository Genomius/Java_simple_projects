<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="header.jsp"/>
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
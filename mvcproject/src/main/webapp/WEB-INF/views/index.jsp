<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="header.jsp"/>
</head>
<body>
    <div class="container">
        <h1>Восславь солнце !</h1><%=request.getContextPath()%>
        <a href="/users">Отобразить список пользователей</a><br>
        <a href="/autos">Отобразить список автомобилей</a><br>
    </div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp"/>
    <div class="content home">
        <h1>Восславь солнце !</h1><%=request.getContextPath()%>
        <a href="/users">Отобразить список пользователей</a><br>
        <a href="/autos">Отобразить список автомобилей</a><br>
    </div>
<jsp:include page="footer.jsp"/>
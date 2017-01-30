<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp"/>
    <div class="autos">
        <h1>${title}</h1>
        <c:if test="${empty user}">
            <a href="/">Главная</a> -> <a href="">Автомобили</a><br><br>
        </c:if>
        <c:if test="${not empty user}">
            <a href="/">Главная</a> -> <a href="/users">Пользователи</a> ->
            <a href="/users/${user.id}">${user.name}</a> -> <a href="">Автомобили пользователя</a><br><br>
        </c:if>

        <c:if test="${empty autos}">
            <h3>Список автомобилей пуст!</h3>
        </c:if>

        <c:if test="${not empty autos}">
            <table class="table table-hover table-bordered">
                <tr>
                    <td>ИД</td>
                    <td>Модель</td>
                    <td>Цвет</td>
                    <td>Пользователь</td>
                    <td style="width: 80px;">Удалить</td>
                    <td style="width: 80px;">Изменить</td>
                    <td style="width: 80px;">Показать пользователя</td>
                </tr>

                <c:forEach var="auto" items="${autos}">
                    <tr>
                        <td>${auto.id}</td>
                        <td>${auto.model}</td>
                        <td>${auto.color}</td>
                        <td>${auto.user.name}</td>
                        <td><i class="glyphicon glyphicon-trash delete-auto" data-id="${auto.id}"
                               data-toggle="modal" data-target="#deleteModal"></i></td>
                        <td><i class="glyphicon glyphicon-pencil edit-auto" data-id="${auto.id}"
                               data-toggle="modal" data-target="#editModal"></i></td>
                        <td><a href="/autos/get/user?id=${auto.id}"><i class="glyphicon glyphicon-user show-user"></i></a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <button type="button" class="btn btn-success btn-lg pull-right add-user"
            data-toggle="modal" data-target="#addModal">Добавить автомобиль</button>

        <!-- Modal -->
        <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="deleteLabel">Удалить автомобиль</h4>
                    </div>
                    <div class="modal-body">
                        Вы действительно хотите удалить автомобиль ?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                        <button type="button" class="btn btn-danger" id="delete-auto">Удалить</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="editLabel">Изменить автомобиль</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <input class="form-control" required name="model" placeholder="Модель">
                        </div>
                        <div class="form-group">
                            <input class="form-control" required name="color" placeholder="Цвет">
                        </div>
                        <div class="form-group">
                            <input class="form-control" required name="user_id" placeholder="ИД пользователя">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                        <button type="button" class="btn btn-info" id="edit-auto">Изменить</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="addLabel">Добавить пользователя</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <input class="form-control" required name="model" placeholder="Модель">
                        </div>
                        <div class="form-group">
                            <input class="form-control" required name="color" placeholder="Цвет">
                        </div>
                        <div class="form-group">
                            <input class="form-control" required name="user_id" placeholder="ИД пользователя">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                        <button type="button" class="btn btn-success" id="add-auto">Добавить</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
<jsp:include page="footer.jsp"/>
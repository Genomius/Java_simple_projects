<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="header.jsp"/>
</head>
<body class="users">
    <div class="container">
        <h1>${title}</h1>
        <a href="/">Главная</a> -> <a href="">Пользователи</a><br><br>

        <c:if test="${not empty users}">
            <table class="table table-hover table-bordered">
                <tr>
                    <td>ИД</td>
                    <td>Имя</td>
                    <td>Возраст</td>
                    <td style="width: 80px;">Удалить</td>
                    <td style="width: 80px;">Изменить</td>
                    <td style="width: 80px;">Показать авто</td>
                </tr>

                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.id}</td>
                        <td><a href="/users/${user.id}">${user.name}</a></td>
                        <td>${user.name}</td>
                        <td><i class="glyphicon glyphicon-trash delete-user" data-id="${user.id}"
                               data-toggle="modal" data-target="#deleteModal"></i></td>
                        <td><i class="glyphicon glyphicon-pencil edit-user" data-id="${user.id}"
                               data-toggle="modal" data-target="#editModal"></i></td>
                        <td><a href="/users/${user.id}/autos"><i class="glyphicon glyphicon-road show-auto"></i></a></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <button type="button" class="btn btn-success btn-lg pull-right add-user"
                data-toggle="modal" data-target="#addModal">Добавить пользователя</button>
    </div>


    <!-- Modal -->
    <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="deleteLabel">Удалить пользователя</h4>
                </div>
                <div class="modal-body">
                    Вы действительно хотите удалить пользователя ?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                    <button type="button" class="btn btn-danger" id="delete-user">Удалить</button>
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
                    <h4 class="modal-title" id="editLabel">Изменить пользователя</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <input class="form-control" required name="name" placeholder="Имя">
                    </div>
                    <div class="form-group">
                        <input class="form-control" required name="age" placeholder="Возраст">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                    <button type="button" class="btn btn-info" id="edit-user">Изменить</button>
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
                        <input class="form-control" required name="name" placeholder="Имя">
                    </div>
                    <div class="form-group">
                        <input class="form-control" required name="age" placeholder="Возраст">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                    <button type="button" class="btn btn-success" id="add-user">Добавить</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
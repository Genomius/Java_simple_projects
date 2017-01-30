<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp"/>
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-6">
                            <a href="#" class="active" id="login-form-link">Авторизация</a>
                        </div>
                        <div class="col-xs-6">
                            <a href="#" id="register-form-link">Регистрация</a>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <div id="login-form" role="form" style="display: block;">
                                <div class="form-group">
                                    <input type="text" required name="username" id="log-username" tabindex="1" class="form-control" placeholder="Имя" value="">
                                </div>
                                <div class="form-group">
                                    <input type="password" required name="password" id="log-password" tabindex="2" class="form-control" placeholder="Пароль">
                                </div>
                                <div class="form-group text-center">
                                    <input checked type="checkbox" tabindex="3" class="" name="remember" id="remember">
                                    <label for="remember"> Remember Me</label>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="Авторизоваться">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="text-center">
                                                <a href="   " tabindex="5" class="forgot-password">Forgot Password?</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="register-form" role="form" style="display: none;">
                                <div class="form-group">
                                    <input type="text" name="username" required id="reg-username" tabindex="1" class="form-control" placeholder="Имя" value="">
                                </div>
                                <div class="form-group">
                                    <input type="email" name="email" id="reg-email" tabindex="1" class="form-control" placeholder="Email" value="">
                                </div>
                                <div class="form-group">
                                    <input type="password" name="password" required id="reg-password" tabindex="2" class="form-control" placeholder="Пароль">
                                </div>
                                <div class="form-group">
                                    <input type="password" name="confirm-password" required id="reg-confirm-password" tabindex="2" class="form-control" placeholder="Снова пароль">
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Зарегистрироваться">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<jsp:include page="footer.jsp"/>
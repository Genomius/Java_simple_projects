toastr.options = {
  "closeButton": false,
  "debug": false,
  "newestOnTop": false,
  "progressBar": false,
  "positionClass": "toast-top-right",
  "preventDuplicates": false,
  "onclick": null,
  "showDuration": "300",
  "hideDuration": "1000",
  "timeOut": "5000",
  "extendedTimeOut": "1000",
  "showEasing": "swing",
  "hideEasing": "linear",
  "showMethod": "fadeIn",
  "hideMethod": "fadeOut"
};

var authToken = "NIotGxwxvjpTqF13RMry";
//var authToken = "NIotGxwxvjpTqF13RMry2";
var stompClient = null;

function getToken(data) {
    authToken = data.getAllResponseHeaders().split('Auth-Token: ')[1].split('\n')[0];
}

function connect(chatId) {
    var socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        stompClient.subscribe('/topic/' + chatId + '/messages', function(messageOutput) {
            var message = JSON.parse(messageOutput.body);
            console.log(message);
            $('#chat-area-messages').append(message.login + ": " + message.content + "\n");
        });
    });
}

function getUsers() {
    $.ajax({
        type: "GET",
        url: '/users',
        headers: {
            'Auth-Token': authToken
        },
        dataType: "json",
        success: function(users) {
            $("#jumbotron-main").hide();

            $(users).each(function() {
                $('#users-tab').append('<a href="#" class="list-group-item">' + this["login"] + "\n" + '</a>')
            });

            $('#users-tab').attr("rows", users.length + 1);
            $('.chat-area').show();
        },
        error: function(error){
            console.error(error);
        }
    });
}

function getChats() {
    $.ajax({
        type: "GET",
        url: '/chats',
        headers: {
            'Auth-Token': authToken
        },
        dataType: "json",
        success: function(chats) {
            $(chats).each(function() {
                $('#chats-tab').append('<a href="#" data-id="' + this["id"] + '" class="list-group-item chat-item">' + this["title"] + "\n" + '</a>')
            });

            $('#chats-tab').attr("rows", chats.length + 1);
        },
        error: function(error){
            console.error(error);
        }
    });
}

$( document ).ready(function() {
    $('#login-submit').on('click', function () {
        $.ajax({
            type: "POST",
            url: '/login',
            beforeSend: function(request) {
                request.setRequestHeader("login", $('#log-username').val());
                request.setRequestHeader("password", $('#log-password').val());
            },
            dataType: 'json',
            success: function(data) {
                getUsers();
                getChats();
            },
            error: function(error){
                if(error.status == 200){
                    getUsers();
                    getChats();
                }
            }
        });
    });

    $('#login-submit-test').on('click', function () {
        $.ajax({
            type: "POST",
            url: '/login',
            beforeSend: function(request) {
                request.setRequestHeader("login", "Genome");
                request.setRequestHeader("password", "123321");
            },
            dataType: 'json',
            success: function (data) {
                getUsers();
                getChats();
            },
            error: function(error){
                if(error.status == 200) {
                    getUsers();
                    getChats();
                }
            }
        });
    });

    $('#register-submit').on('click', function () {
        $.ajax({
            type: "POST",
            url: '/users',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify({"login": $('#reg-login').val(), "name": $('#reg-username').val(),
                "surname": $('#reg-surname').val(), "password": $('#reg-password').val()}),
            dataType: 'json',
            success: function(data) {
                // location.reload();
            },
            error: function(error){
                console.error(error);
                if(error.status == 200){
                    // location.reload();
                }
            }
        });
    });

    $('#addChat-submit').on('click', function(){
        $.ajax({
            type: "POST",
            url: '/chats',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Auth-Token': authToken
            },
            data: JSON.stringify({"title": $('#chat-name').val()}),
            dataType: 'json',
            success: function(data) {
                $('#chats-tab').append('<a href="#" data-id=' + data["id"] + ' class="list-group-item chat-item">' + $('#chat-name').val() + "\n" + '</a>');
            },
            error: function(error){
                console.error(error);
            }
        });
    });

    $('#chats-tab').on('click', '.chat-item', function(){
        chatId = $(this).data("id");

        $('#chats-tab .chat-item').each(function () {
           $(this).removeClass('active');
        });
        $(this).addClass('active');

        $.ajax({
            type: "GET",
            url: '/messages/' + chatId,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Auth-Token': authToken
            },
            dataType: 'json',
            success: function(messages) {
                $('#chat-area-messages').empty();

                $(messages).each(function() {
                    $('#chat-area-messages').append(this["login"] + ": " + this["content"] + "\n");
                });

                connect(chatId);
            },
            error: function(error){
                console.error(error);
            }
        });
    });

    $('#send-message-button').on('click', function(){
        $.ajax({
            type: "POST",
            url: '/messages/' +$('#chats-tab .chat-item.active').data('id'),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Auth-Token': authToken
            },
            data: $("#chat-area-new-message").val(),
            dataType: 'json',
            success: function(messages) {
                $("#chat-area-new-message").val('');

                // $(messages).each(function() {
                //     $('#chat-area-messages').append(this["login"] + ": " + this["content"] + "\n");
                // });
            },
            error: function(error){
                console.error(error);
            }
        });
    });

    // login - registration snippet
    $('#login-form-link').click(function(e) {
        $("#login-form").delay(100).fadeIn(100);
        $("#register-form").fadeOut(100);
        $('#register-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#register-form-link').click(function(e) {
        $("#register-form").delay(100).fadeIn(100);
        $("#login-form").fadeOut(100);
        $('#login-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });

});
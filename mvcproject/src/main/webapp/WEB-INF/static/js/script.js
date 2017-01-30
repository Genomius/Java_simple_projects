$( document ).ready(function() {
   var entity_for_delete_id;
   var entity_for_edit_id;

   $('.delete-user').on('click', function () {
      entity_for_delete_id = $(this).data('id');
   });

   $('#delete-user').on('click', function () {
       $.ajax({
           type: "POST",
           url: '/users/' + entity_for_delete_id + '?action=delete',
           dataType: 'json',
           success: function(data) {
                location.reload();
           },
           error: function(error){
                console.error(error);
                if(error.status == 200){
                    location.reload();
                }
           }
       });
   });

    $('#add-user').on('click', function () {
        $.ajax({
            type: "POST",
            url: '/users?name=' + $('#addModal input[name=name]').val() + '&age=' + $('#addModal input[name=age]').val(),
            dataType: 'json',
            success: function(data) {
                location.reload();
            },
            error: function(error){
                console.error(error);
                if(error.status == 200){
                    location.reload();
                }
            }
        });
    });

    $('.edit-user').on('click', function () {
        entity_for_edit_id = $(this).data('id');
    });

    $('#edit-user').on('click', function () {
        $.ajax({
            type: "POST",
            url: '/users/' + entity_for_edit_id + '?action=update&name=' + $('#editModal input[name=name]').val() + '&age=' + $('#editModal input[name=age]').val(),
            dataType: 'json',
            success: function(data) {
                location.reload();
            },
            error: function(error){
                console.error(error);
                if(error.status == 200){
                    location.reload();
                }
            }
        });
    });

    $('#login-submit').on('click', function () {
       $.ajax({
           type: "POST",
           url: '/auth/login?username=' + $('#log-username').val() + "&password=" + $('#log-password').val(),
           dataType: 'json',
           success: function(data) {
               location.reload();
           },
           error: function(error){
               console.error(error);
               if(error.status == 200){
                   location.reload();
               }
           }
       });
    });

    $('#register-submit').on('click', function () {
        $.ajax({
            type: "POST",
            url: '/auth/registration?username=' + $('#reg-username').val() + "&password=" + $('#reg-password').val(),
            dataType: 'json',
            success: function(data) {
                location.reload();
            },
            error: function(error){
                console.error(error);
                if(error.status == 200){
                    location.reload();
                }
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
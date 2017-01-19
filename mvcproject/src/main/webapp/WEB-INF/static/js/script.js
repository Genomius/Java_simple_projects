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
            url: '/users?action=add&name=' + $('#addModal input[name=name]').val() + '&age=' + $('#addModal input[name=age]').val(),
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
});
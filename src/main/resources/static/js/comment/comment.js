var comment = {
    init : function () {
        var _this = this;

        $('#btn-comment-save').on('click', function () {
            _this.save();
        });

        $('.dropdown-item-delete').on('click', function (e) {
            _this.delete(e);
        });

    },
    save : function () {
        var data = {
            ideaId: $('#ideaId').val(),
            comment: $('#comment').val(),
            author: 'testUser', // TODO
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/comment',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(data) {
            alert('コメントが登録されました。');
            window.location.href = '/idea/' + $('#ideaId').val();
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function (e) {
        var commentId = $(e.target).siblings('.comment-id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/comment/' + commentId,
            contentType: 'application/json; charset=utf-8',
        }).done(function(data) {
            alert('コメントが削除されました。');
            window.location.href = '/idea/' + $('#ideaId').val();
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },
};

comment.init();
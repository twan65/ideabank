var comment = {
    init : function () {
        var _this = this;

        $('#btn-comment-save').on('click', function () {
            _this.save();
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
};

comment.init();
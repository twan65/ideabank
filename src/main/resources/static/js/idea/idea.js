var idea = {
    init : function () {
        var _this = this;

        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-edit').on('click', function () {
            _this.edit();
        });
        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val(),
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/idea',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(data) {
            alert('アイデアが登録されました。');
            window.location.href = '/';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },
    edit : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val(),
        };

        var ideaId = $('#ideaId').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/idea/' + ideaId,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(data) {
            alert('アイデアが更新されました。');
            window.location.href = '/';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var ideaId = $('#ideaId').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/idea/' + ideaId,
            contentType: 'application/json; charset=utf-8',
        }).done(function(data) {
            alert('アイデアが削除されました。');
            window.location.href = '/';
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },
};

idea.init();
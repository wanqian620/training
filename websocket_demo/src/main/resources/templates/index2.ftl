<!DOCTYPE HTML>
<html>
<head>
    <title>webSocket</title>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col">
            <form>
                <div class="form-group">
                    <label for="comment">名称:</label><input type="text" class="form-control" id="name">
                    <label for="comment">请输入:</label> <textarea class="form-control" rows="5" id="text"></textarea>
                </div>
                <div class="form-group">
                    <button id="conn" onclick="connection()" type="button" class="btn btn-primary">连接</button>
                    <button id="close" onclick="closeConn()" type="button" class="btn btn-primary"
                            disabled="disabled">断开
                    </button>
                    <button onclick="send()" type="button" class="btn btn-primary">发送</button>
                </div>
                <div>
                    <ul class="list-group" id="message"></ul>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="/js/jquery-3.3.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script>
    var websocket = null;

    function connection() {
        var name = $("#name").val();

        // 判断当前浏览器是否支持WebSocket
        if ('WebSocket' in window) {
            websocket = new WebSocket("ws://127.0.0.1:8888/webSocket?user=" + name);

            // 连接成功建立的回调方法
            websocket.onopen = function (event) {
                setMessageInnerHTML("服务连接成功.");
                $("#conn").attr("disabled", 'disabled');
                $("#close").removeAttr("disabled");
            };

            // 连接发生错误的回调方法
            websocket.onerror = function () {
                setMessageInnerHTML("服务连接失败.")
            };

            // 接收到消息的回调方法
            websocket.onmessage = function (event) {
                setMessageInnerHTML(event.data)
            };

            // 连接关闭的回调方法
            websocket.onclose = function () {
                setMessageInnerHTML("已关闭与服务的连接")
            };

            // 监听窗口关闭事件，当窗口关闭时，主动关闭WebSocket连接(防止server端会抛异常)
            window.onbeforeunload = function () {
                websocket.close()
            }
        } else {
            alert("未支持WebSocket")
        }
    }

    /**
     * 关闭连接
     */
    function closeConn() {
        websocket.send($("#name").val() + "与服务器断开连接！");
        websocket.close();
        $("#close").attr("disabled", 'disabled');
        $("#conn").removeAttr("disabled");
    }

    /**
     * 将消息显示在网页上
     */
    function setMessageInnerHTML(innerHTML) {
        $("#message").append("<li class='list-group-item'>" + innerHTML + "</li>")
    }

    /**
     * 发送消息
     */
    function send() {
        websocket.send($("#name").val() + "：" + $("#text").val())
    }
</script>
</body>
</html>
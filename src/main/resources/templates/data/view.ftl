<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->

<head>
    <title>文章</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <meta name="MobileOptimized" content="320">
    <link rel="shortcut icon" href="favicon.ico"/>
    <link href="/static/pagurain/resources/css/public.css" rel="stylesheet" type="text/css"/>
    <link id="style_themes" href="/static/pagurain/resources/css/themes-green.css" rel="stylesheet" type="text/css"/>

    <link rel="stylesheet" href="/static/css/highlight.default.min.css">
    <!--[if lt IE 9]>
    <script src="/static/js/es5-shim.min.js"></script>
    <![endif]-->
    <!--[if !IE]><!-->
    <script src="/static/js/highlight.min.js"></script>
    <!--<![endif]-->


</head>
<body>

<#include "../header.ftl">
<div class="clearfix"></div>

<!-- BEGIN CONTAINER -->
<div class="page-container">
<#include "../left.ftl">


    <!-- BEGIN CONTENT -->
    <div class="page-content-wrapper">
        <div class="page-content">

            <div class="row">
                <div class="col-md-12" id="dataForm">
                    <h2>标题</h2>
                    <h4>{{article.title}}</h4>
                    <hr>
                    <h2>日期</h2>
                    <h4>{{article.date}}</h4>
                    <hr>
                    <h2>作者</h2>
                    <h4>{{article.author}}</h4>
                    <hr>
                    <h2>内容</h2>
                    <div>{{{article.content}}}</div>

                    <div class="clearfix"></div>
                </div>
            </div>
        </div>

    </div>
    <!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<div id="descrView"></div>

<script src="/static/pagurain/pagurian.js"></script>
<script src="/static/js/vue.js"></script>
<#--<script src="/static/js/vue.validator.js"></script>-->

<!--[if lt IE 9]>
<script src="/static/js/respond.min.js"></script>
<![endif]-->
<script type="text/javascript">
    //    window.CONFIG = {
    //        appId: "uploader"
    //    };
</script>
<script type="text/javascript">
    var href = window.location.href;
    var id = href.slice(href.lastIndexOf("/") + 1);

    pagurian.call(["modules/com/app",
    ], function (app) {
//        Vue.use(VueValidator);
        var api = "/api/template/";
        var formVm = new Vue({
            el: "#dataForm",
            data: {
                article:{}
            },
            ready: function () {
                var that = this;
                $.ajax({
                    url: "/api/data/" + id,
                    type: 'GET',
                    success: function (result) {
                        console.log(result)
                        that.article = result;
                    }
                });
            },
            methods: {
                submit: function (event) {
                    console.log(this.templateId)
                    console.log(this.urls)

                    var templateId = this.templateId;
                    var urls = this.urls;

                    $.ajax({
                        url: "/api/run/",
                        data: {urls: urls, templateId: templateId},
                        type: 'POST',
                        success: function (result) {
                            // Do something with the result
                            if (result.code == "200000") {
                                $p.alert("成功");
                            } else {
                                $p.alert("错误 : " + result.code, "error");
                            }
                        }
                    });
                }
            }
        });

        function validator(data) {
            function empty(data) {
                return data == null || data.length == 0;
            }

            function notEmpty(data) {
                return data && data.length != 0;
            }

            var result = true;
            if (result) {
                if (empty(data.name)) {
                    result = false;
                    $p.alert("用户名", "error");
                }
            }
            if (result) {
                if (empty(data.password)) {
                    result = false;
                    $p.alert("未设置密码", "error");
                }
            }
            if (result) {
                if (empty(data.role)) {

                    result = false;
                    $p.alert("未设置 角色", "error");
                }
                if (data.role == 0) {
                    result = false;
                    $p.alert("未设置 角色", "error");
                }
            }

            return result;
        }


    })
</script>

</body>
</html>

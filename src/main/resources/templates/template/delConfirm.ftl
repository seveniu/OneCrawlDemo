<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->

<head>
    <title>删除 确认</title>
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

    <style>
        .table-custom td, .table-custom th {
            height: 50px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
    </style>

</head>
<body>

<#include "/header.ftl">
<div class="clearfix"></div>

<!-- BEGIN CONTAINER -->
<div class="page-container">


    <!-- BEGIN CONTENT -->
    <div class="page-content-wrapper">
        <div class="page-content">

            <div class="row">
                <div id="main">
                    <h2>模板名称 : </h2>
                    <h2>{{name}}</h2>
                    <button id="confirm" type="button" class="btn btn-primary" @click="confirm"><i
                            class="fa fa-trash-o"></i> 确认删除
                    </button>
                </div>
            </div>
        </div>

    </div>
    <!-- END CONTENT -->
</div>
<!-- END CONTAINER -->


<script src="/static/js/vue.js"></script>
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/common.js"></script>
<script type="text/javascript">
    var id = window.location.hash.slice(1);
    var vm;
    $.get("/api/template/" + id, {}, function (data) {
        console.log(data);
        vm = new Vue({
            el: "#main",
            data: data,
            methods: {
                confirm: function () {
                    console.log(id);
                    $.ajax({
                        url: "/api/template/del/" + id,
                        method: "DELETE"
                    }).done(function (data) {
                        if(isSuccess(data)) {

                            if (window.confirm("删除成功")) {
//                                window.open("exit.html", "Thanks for Visiting!");
                                window.location.href = "/view/template";
                            }
                        }
                    })
                }
            }
        });

    });

</script>

</body>
</html>

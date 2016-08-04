<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->

<head>
    <title>编辑模板</title>
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

                    <div class="col-md-6">

                        <form id="fieldForm" action="">
                            <h4>ID:{{template.id}}</h4>
                            <div class="form-group">
                                <label class="control-label">名称</label>
                                <input v-model="template.name" type="text" class="form-control" maxlength="50" name="name"/>
                                <span for="name" class="help-block"></span>
                            </div>
                            <div class="form-group">
                                <label class="control-label">类型</label>
                                <select  v-model="template.type" name="template.type" class="form-control">
                                    <option value="1">多级内容页</option>
                                    <option value="2">单页</option>
                                </select>
                                <span for="type" class="help-block"></span>
                            </div>
                            <div class="form-group">
                                <label class="control-label">状态</label>
                                <select  v-model="template.status" name="template.status" class="form-control">
                                    <option value="1">正常</option>
                                    <option value="2">禁用</option>
                                </select>
                                <span for="name" class="help-block"></span>
                            </div>
                            <div class="form-group">
                                <label class="control-label">状态</label>
                                <textarea v-model="template.pages" class="form-control" rows="3"></textarea>
                                <span for="name" class="help-block"></span>
                            </div>
                        </form>
                    </div>
                    <div class="clearfix"></div>
                    <div class="form-actions">
                        <button v-on:click="submit" type="button" id="btn_submit" class="btn green">提交</button>
                    <#--<button type="button" class="btn default">Cancel</button>-->
                    </div>
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
                template:{}
            },
            ready:function () {
                $.ajax({
                    url: api + "/" + id ,
                    type: 'get',
                    success: function (result) {

                        formVm.template = result;
                        console.log(result);
                    }
                });
            },
            methods: {
                submit: function (event) {
                    if (validator(this.$data)) {
                        var data = JSON.parse(JSON.stringify(this.$data));

                        $.ajax({
                            url: api + "/edit",
                            type: 'put',
                            data: data,
                            success: function (result) {
                                console.log(result);
                                if (result.code == "200000") {
                                    $p.alert("编辑成功");
                                } else {
                                    $p.alert("错误 : " + result.code, "error");
                                }
                            }
                        });
                    }
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
                if(data.role == 0 ) {
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

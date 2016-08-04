<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->

<head>
    <title>列表</title>
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

<#include "/header.ftl">
<div class="clearfix"></div>

<!-- BEGIN CONTAINER -->
<div class="page-container">
<#include "/left.ftl">


    <!-- BEGIN CONTENT -->
    <div class="page-content-wrapper">
        <div class="page-content">

            <div class="row">
                <div class="col-md-12">
                    <div class="table-toolbar">
                        <button id="add" type="button" class="btn btn-primary"><i class="fa fa-plus"></i> 新建</button>
                        <button id="del" type="button" class="btn btn-danger"> 删除</button>
                        <button id="edit" type="button" class="btn btn-info"> 编辑</button>
                    <#--<span class="label ml10 mr10"> 状态 :</span>-->
                    <#--<div class="btn-group btn-dropdown btn-select" data-type='select'>-->
                    <#--<button data-toggle="dropdown" type="button" class="btn btn-default  dropdown-toggle w-unset">全部 <i class="fa fa-angle-down"></i></button>-->
                    <#--<ul role="menu" class="dropdown-menu">-->
                    <#--<li><a data-id="ALL" href="javascript:;">全部</a></li>-->
                    <#--<li><a data-id="ENABLE" href="javascript:;">启用</a></li>-->
                    <#--<li><a data-id="DISABLE" href="javascript:;">禁用</a></li>-->
                    <#--</ul>-->
                    <#--</div>-->
                    <#--<span class="label ml10 mr10"> 状态 :</span>-->
                    <#--<div class="btn-group btn-radio">-->
                    <#--<button  type="button" class="btn btn-default btn-active">全部</button>-->
                    <#--<button  type="button" class="btn btn-default ">启用</button>-->
                    <#--<button  type="button" class="btn btn-default ">禁用</button>-->
                    <#--</div>-->
                    <#--<span class="label">|</span>-->
                    <#--<button type="button" class="btn btn-default ">报告导出</button>-->

                    <#--<div class="input-icon input-search right">-->
                    <#--<i class="fa fa-search"></i>-->
                    <#--<input placeholder="查询关键字" id="txt_search" class="form-control" maxlength="1024" type="text">-->
                    <#--</div>-->

                    </div>
                    <table id="my_table"></table>
                </div>
            </div>
        </div>

    </div>
    <!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<div id="descrView"></div>

<script src="/static/pagurain/pagurian.js"></script>

<!--[if lt IE 9]>
<script src="/static/js/respond.min.js"></script>
<![endif]-->
<script type="text/javascript">
    //    window.CONFIG = {
    //        appId: "uploader"
    //    };
</script>
<script type="text/tpl" id="addTemplate">
            <div class="row">
                <div class="col-md-12 col-sm-12">
                    <div class="portlet-body">
                        <form id="fieldForm" action="">
                            <div class="form-group" style="display:none">
                                <label class="control-label">ID</label>
                                <input type="text" class="form-control" maxlength="50" name="id" />
                                <span for="id" class="help-block"></span>
                            </div>
                            <div class="form-group">
                                <label class="control-label">字段名</label>
                                <input type="text" class="form-control" maxlength="50" name="name" />
                                <span for="name" class="help-block"></span>
                            </div>
                            <div class="form-group">
                                <label class="control-label">类型</label>
                                  <select name="type" class="form-control">
                                        <option value="1">HTML源文本</option>
                                        <option value="2">跳转链接</option>
                                        <option value="3">下一页链接</option>
                                        <option value="4">文本链接（链接文本，以及链接）</option>
                                        <option value="5">纯文本</option>
                                    </select>
                                <span for="age" class="help-block"></span>
                            </div>
                        </form>
                    </div>
                </div>
            </div>



</script>
<script type="text/javascript">

    var dataTable ;
    pagurian.call("modules/datatable/app", function (app) {
        pagurian.path.api = "/api/field";
        pagurian.lib.apiPostfix = "";
        dataTable = $p.dataTable("#my_table", {
            "fnDataSource": function (params, callback) {
                pagurian.lib.service.get("/list", params, callback);
            },
            "sClass": "table-fixed",
            "aaSorting": [
                [0, "asc"]
            ],
            "fnParams": function () {
                return {};
            },
            "aoColumns": [{
                "sClass": "t-a-l w80",
                "sTitle": "ID",
                "mData": "id"
            }, {
                "bSortable": false,
                "sClass": "t-a-l",
                "sTitle": "字段名",
                "mData": "name"
            }, {
                "bSortable": false,
                "sClass": "t-a-l",
                "sTitle": "类型",
                "mData": "type",
                mRender: function (data, type, full) {
                    //1: HTML 源文本, 2：跳转链接,3：下一页链接, 4：文本链接（链接文本，以及链接），5： 纯文本;
                    if (data == 1) {
                        return '<span>HTML源文本</span>'
                    } else if (data == 2) {
                        return '<span>跳转链接</span>'
                    } else if (data == 3) {
                        return '<span>下一页链接</span>'
                    } else if (data == 4) {
                        return '<span>文本链接</span>'
                    } else if (data == 5) {
                        return '<span>纯文本</span>'
                    }
                    return data;
                }
            }
            ]
        });
        $p.dialog("#add", {
            title: "添加",
            body: $("#addTemplate").html(),
            submit: function (modal, data, params, callback) {
                console.log(data);
                $.post(pagurian.path.api + "/add", data, function (e) {
                    console.log(e);
                    if (e.code == "200000") {
                        $p.alert("添加成功");
                        window.location.reload()
//                        modal.hide();
                    } else {
                        $p.alert("错误 : " + e.code, "error");
                    }
                });
            }
        });
        var editDialog= $p.dialog("#edit", {
            title: "编辑",
            body: $("#addTemplate").html(),
            initForm: function(modal, form, params) {
                //获取用户信息
                var field = modal.getField();
                $p.form(form).val(field);
            },
            submit: function (modal, data, params, callback) {
                $.ajax({
                    url: pagurian.path.api + "/edit",
                    type: 'put',
                    data: data,
                    success: function (result) {
                        console.log(result);
                        if (result.code == "200000") {
                            $p.alert("编辑成功");
                            window.location.reload()
//                            modal.hide();
                        } else {
                            $p.alert("错误 : " + result.code, "error");
//                            $p.alert("错误 : xxx");
                        }
                    }
                });
            }
        });
        editDialog.getField = function () {
            var temp = $("table.dataTable tr.selected").first().find("td");
            if(temp.length == 0) {
                $p.alert("请选择指定行","error");
                modal.hide();
                return null;
            }

            var field = {};
            field.id = parseInt($(temp).first().html());
            field.name = $(temp).eq(1).html();
            field.type = $(temp).eq(2).html();

            return field;
        };
        $p.dialog("#del", {
            title: "提示",
            body: "你确定要删除吗？",
            submit: function(modal, data, params, callback) {
                var ids = [];
                var error = false;
                $("table.dataTable tr.selected").each(function (i, v) {
                    var id = $(v).find("td").first().html();
                    try {
                        ids.push(parseInt(id));
                    } catch (e) {
                        $p.alert("错误: id 不是数字");
                        error = true;
                    }
                });
                if(!error) {

                    $.ajax({
                        url: pagurian.path.api + "/del?ids=" + ids.join(","),
                        type: 'DELETE',
                        success: function (result) {
                            // Do something with the result
                            if (result.code == "200000") {
                                $p.alert("删除成功");
                                window.location.reload()
//                                modal.hide();
                            } else {
                                $p.alert("错误 : " + result.code, "error");
                            }
                        }
                    });
                }
            }
        });
        $('#my_table tbody').on( 'click', 'tr', function () {
            $(this).toggleClass('selected');
        } );
    })
</script>

</body>
</html>

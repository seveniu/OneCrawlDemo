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
<#include "/left.ftl">


    <!-- BEGIN CONTENT -->
    <div class="page-content-wrapper">
        <div class="page-content">

            <div class="row">
                <div class="col-md-12">
                    <div class="table-toolbar">
                        <button id="del" type="button" class="btn btn-danger"> 禁用</button>
                        <button id="edit" type="button" class="btn btn-info"> 编辑</button>
                    <span class="label ml10 mr10"> 状态 :</span>
                    <div class="btn-group btn-dropdown btn-select" data-type='select'>
                    <button data-toggle="dropdown" type="button" class="btn btn-default  dropdown-toggle w-unset">全部 <i class="fa fa-angle-down"></i></button>
                    <ul role="menu" class="dropdown-menu chooseStatus">
                    <li><a data-id="0" href="javascript:;">全部</a></li>
                    <li><a data-id="1" href="javascript:;">启用</a></li>
                    <li><a data-id="2" href="javascript:;">禁用</a></li>
                    </ul>
                    </div>
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
                                <label class="control-label">模板名</label>
                                <input type="text" class="form-control" maxlength="50" name="name" />
                                <span for="name" class="help-block"></span>
                            </div>
                            <div class="form-group">
                                <label class="control-label">类型</label>
                                <input type="text" class="form-control" maxlength="50" name="type" />
                                <span for="domain" class="help-block"></span>
                            </div>
                            <div class="form-group">
                                <label class="control-label">状态</label>
                                <input type="text" class="form-control" maxlength="50" name="status" />
                                <span for="domain" class="help-block"></span>
                            </div>
                            <div class="form-group">
                                <label class="control-label">模板</label>
                                <input type="text" class="form-control" maxlength="50" name="pages" />
                                <span for="domain" class="help-block"></span>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
</script>
<script type="text/javascript">

    pagurian.call(["modules/datatable/app",
        "/static/js/util"], function (app,util) {
        pagurian.path.api = "/api/template";
        pagurian.lib.apiPostfix = "";
        var dataTable = $p.dataTable("#my_table", {
            "fnDataSource": function (params, callback) {
                pagurian.lib.service.get("/list-filter", params, callback);
            },
            "sClass": "table-fixed",
            "aaSorting": [
                [0, "asc"]
            ],
//            "sScrollX": "100%",
            "bAutoWidth": false,
            "bScrollCollapse": true,
            "fnParams": function () {
                return {};
            },
            "aoColumns": [{
                "sClass": "t-a-l",
                "sTitle": "ID",
                "sWidth": "100px",
                "mData": "id"
            }, {
                "bSortable": false,
                "sClass": "t-a-l",
                "sTitle": "模板名",
                "sWidth": "300px",
                "mData": "name"
            }, {
                "bSortable": false,
                "sClass": "t-a-l",
                "sTitle": "网站",
                "sWidth": "100px",
                "mData": "websiteId"
            }, {
                "bSortable": false,
                "sClass": "t-a-l",
                "sTitle": "类型",
                "sWidth": "100px",
                "mData": "type",
                mRender: function (data, type, full) {
                    //1: HTML 源文本, 2：跳转链接,3：下一页链接, 4：文本链接（链接文本，以及链接），5： 纯文本;
                    if (data == 1) {
                        return '<span>多级单页</span>'
                    } else if (data == 2) {
                        return '<span>单页</span>'
                    }
                    return data;
                }
            }, {
                "bSortable": false,
                "sClass": "t-a-l",
                "sTitle": "状态",
                "sWidth": "100px",
                "mData": "status",
                mRender: function (data, type, full) {
                    //1: HTML 源文本, 2：跳转链接,3：下一页链接, 4：文本链接（链接文本，以及链接），5： 纯文本;
                    if (data == 1) {
                        return '<span>正常</span>'
                    } else if (data == 2) {
                        return '<span>禁用</span>'
                    }
                    return data;
                }
            }, {
                "bSortable": false,
                "sClass": "t-a-l",
                "sTitle": "模板",
                "sWidth": "180px",
                "mData": "pages"
            }
            ]
        });

        $("#add").click(function () {
            $p.alert("通过插件添加")
        });
        $("#edit").click(function () {
            var selectRows = $("table.dataTable tr.selected");
            if (selectRows.length == 0) {
                $p.alert("请选择指定行", "error");
                return null;
            }
            var rowIndex = dataTable.table.fnGetPosition(selectRows[0]);
            var field = dataTable.table.fnGetData()[rowIndex];
            util.goto("/view/template/"+field.id)
        });
        $p.dialog("#del", {
            title: "提示",
            body: "你确定要禁用吗？",
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
                if(ids.length == 0) {
                    $p.alert("请选中模板, 再禁用");
                    return;
                }
                if(!error) {

                    $.ajax({
                        url: pagurian.path.api + "/del?ids=" + ids.join(","),
                        type: 'DELETE',
                        success: function (result) {
                            // Do something with the result
                            if (result.code == "200000") {
                                $p.alert("禁用成功");
                                dataTable.updateCurPage();
                                modal.hide();
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
        $(".chooseStatus a").click(function (e) {
            var value = $(this).data("id");
            if (value == 0) {
                value = null;
            }
            dataTable.aApiParams["status"] = value;
            dataTable.update();
        });
    })
</script>

</body>
</html>

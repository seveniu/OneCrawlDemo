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
                        <div class="input-icon input-search right">
                            <span class="label">|</span>
                            <button id="exportToExcel" type="button" class="btn btn-default ">报告导出</button>
                        </div>

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
                                <label class="control-label">网站名</label>
                                <input type="text" class="form-control" maxlength="50" name="name" />
                                <span for="name" class="help-block"></span>
                            </div>
                            <div class="form-group">
                                <label class="control-label">域名</label>
                                <input type="text" class="form-control" maxlength="50" name="domain" />
                                <span for="domain" class="help-block"></span>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

</script>
<script type="text/javascript">

    pagurian.call(["modules/datatable/app",
        "/static/js/util",
        "/static/js/moment"
    ], function (app, util) {
        pagurian.path.api = "/api/data";
        pagurian.lib.apiPostfix = "";
        $p.dataTable("#my_table", {
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
                "sTitle": "标题",
                "mData": "title"
            }, {
                "bSortable": false,
                "sClass": "t-a-l",
                "sTitle": "创建时间",
                "mData": "createTime",
                mRender: function (data, type, full) {
                    return moment(data).format("YYYY-MM-DD HH:mm");
                }
            }, {
                "bSortable": false,
                "sClass": "t-a-l",
                "sTitle": "",
                "mRender": function (data, type, full) {
                    return '<button class="viewData btn btn-xs btn-primary" data-id="' + full.id + '">查看</button>'
                }
            }
            ]
        });
        $('#my_table tbody').on('click', 'tr', function () {
            $(this).toggleClass('selected');
        });

        $("body").on("click", ".viewData", function () {
            util.goto("/view/data/" + $(this).data("id"))
        })
    })
</script>

</body>
</html>

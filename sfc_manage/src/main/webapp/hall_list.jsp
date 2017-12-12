<%--
  Created by IntelliJ IDEA.
  User: 95217
  Date: 2017/12/1 0001
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="s" %>
<% String basepath = request.getContextPath();%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <!--[if lt IE 9]>
    <!--<script type="text/javascript" src="<%=basepath%>/lib/html5shiv.js"></script>-->
    <!--<script type="text/javascript" src="<%=basepath%>/lib/respond.min.js"></script>-->
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="<%=basepath%>/static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basepath%>/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basepath%>/lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basepath%>/static/h-ui.admin/skin/default/skin.css" id="skin"/>
    <link rel="stylesheet" type="text/css" href="<%=basepath%>/static/h-ui.admin/css/style.css"/>
    <!--[if IE 6]>
    <script type="text/javascript" src="<%=basepath%>/lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
    <script>
        function changess() {
            var a = $("#class1").val();
            var b = $("#class2").val();
            var c = $("#class3").val();
            var d = $("#class4").val();
            alert(a + b + c);
            $.ajax({
                url: "<%=basepath %>/hallchange?hallId=" + a + "&hallName=" + b + "&hallScreenType=" + c+"&hallDescribe="+d,
                context: document.body
            }).done(function () {
                $(this).addClass("done");
            });
        }
    </script>
    <title>资讯列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 资讯管理 <span class="c-gray en">&gt;</span> 资讯列表 <a class="btn btn-success radius r"
                                                                                                                                                     style="line-height:1.6em;margin-top:3px"
                                                                                                                                                     href="javascript:location.replace(location.href);"
                                                                                                                                                     title="刷新"><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>
<div class="page-container">
    <div class="text-c">
        <button onclick="removeIframe()" class="btn btn-primary radius">关闭选项卡</button>
        <span class="select-box inline">
		<select name="" class="select">
			<option value="0">全部分类</option>
			<option value="1">分类一</option>
			<option value="2">分类二</option>
		</select>
		</span> 日期范围：
        <input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })" id="logmin" class="input-text Wdate" style="width:120px;">
        -
        <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })" id="logmax" class="input-text Wdate" style="width:120px;">
        <input type="text" name="" id="" placeholder=" 资讯名称" style="width:250px" class="input-text">
        <button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜资讯</button>
    </div>
    <div class="cl pd-5 bg-1 bk-gray mt-20"><span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a
            class="btn btn-primary radius" data-title="添加资讯" data-href="article-add.html" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加资讯</a></span> <span
            class="r">共有数据：<strong>${hall.size()}</strong> 条</span>
    </div>
    <div class="mt-20">
        <%--<table class="table table-border table-bordered table-bg">--%>
        <table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">

            <thead>
            <tr>
                <th scope="col" colspan="9">影厅列表</th>
            </tr>
            <tr class="text-c">
                <th width="25"><input type="checkbox" name="" value=""></th>
                <th width="40">影厅名称</th>
                <th width="150">荧幕类型</th>
                <th width="90">影厅描述</th>
                <th width="150">操作</th>
                <th width="150"> 提交</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="hall" items="${hall}">
                <tr class="text-c" class="changes">
                    <td><input type="checkbox" value="1" name=""></td>
                    <form action="<%=basepath %>/hallchange">
                        <input id="class1" name="hallId" type="hidden" value="${hall.hallId}">
                        <td class="changessss"><input id="class2" class="input-text" name="hallName" type="text" value="${hall.hallName}"></td>
                        <td class="changessss"><input id="class3" class="input-text" name="hallScreenType" type="text" value="${hall.hallScreenType}"></td>
                        <td class="changessss"><input id="class4" class="input-text" name="hallDescribe" type="text" value="${hall.hallDescribe}"></td>
                        <td class="changessss"><a class="btn-link" class="btn btn-success radius" href="<%=basepath %>/seatchange?hallId=${hall.hallId}">座位修改</a></td>
                        <td><input type="submit" class="btn btn-success radius" value="提交修改"></td>
                    </form>

                    <span>${error_message}</span>
                    <span>${success_message}</span>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<!--_footer 作为公共模版分离出去-->
<%--<script type="text/javascript" src="<%=basepath%>/lib/jquery/1.9.1/jquery.min.js"></script>--%>
<%--<script type="text/javascript" src="<%=basepath%>/lib/layer/2.4/layer.js"></script>--%>
<%--<script type="text/javascript" src="<%=basepath%>/static/h-ui/js/H-ui.min.js"></script>--%>
<%--<script type="text/javascript" src="<%=basepath%>/static/h-ui.admin/js/H-ui.admin.js"></script>--%>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=basepath%>/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basepath%>/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=basepath%>/lib/laypage/1.2/laypage.js"></script>
</body>
</html>

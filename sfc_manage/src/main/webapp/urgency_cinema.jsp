<%--
  Created by IntelliJ IDEA.
  User: 95217
  Date: 2017/11/29 0029
  Time: 17:31
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
    <script type="text/javascript" src="lib/html5shiv.js"></script>
    <script type="text/javascript" src="lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="<%=basepath%>/static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basepath%>/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basepath%>/lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basepath%>/static/h-ui.admin/skin/default/skin.css" id="skin"/>
    <link rel="stylesheet" type="text/css" href="<%=basepath%>/static/h-ui.admin/css/style.css"/>
    <!--[if IE 6]>
    <script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>资讯列表</title>
</head>
<body>
<div class="mt-20">
    <table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
        <form action="<%=basepath%>/urgency/cinema">
            <tr>
                <td style="text-align: center">请输停业天数</td>
            </tr>
            <tr>
                <td><input class="input-text" style="text-align: center" name="timel" type="password" placeholder="不输入则代表永久"></td>
            </tr>
            <tr>
                <td style="text-align: center">请输入密码</td>
            </tr>
            <tr>
                <td><input class="input-text" style="text-align: center" name="mainpassword" type="password"></td>
            </tr>
            <tr>
                <td><input class="btn btn-success radius" type="submit"></td>
            </tr>
        </form>
    </table>
    <span>${error_message}</span>
    <span>${success_message}</span>
</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=basepath%>/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=basepath%>/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=basepath%>/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="<%=basepath%>/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=basepath%>/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basepath%>/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=basepath%>/lib/laypage/1.2/laypage.js"></script>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: 95217
  Date: 2017/12/6 0006
  Time: 10:57
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
    <title>我的桌面</title>
</head>

<body>
<div class="page-container">
    <p class="f-20 text-success">欢迎使用</p><br>
    <p class="f-20 text-success">SFC电影院后台管理系统</p><br>
    <p class="f-20 text-success">电影院管理系统是一个功能强大的工具 为用户提供对整体多厅影院的集中控制，借助电影院管理系统</p><br>
    <p class="f-20 text-success">用户可轻松地使得数字影院系统优化，影院的运作更加简便和自动化。</p>
</div>
<footer class="footer mt-20">
    <span>${error_message}</span>
    <span>${success_message}</span>
    <div class="container">
        <p>感谢jQuery、layer、laypage、Validform、UEditor、My97DatePicker、iconfont、Datatables、WebUploaded、icheck、highcharts、bootstrap-Switch<br>
            Copyright &copy;2015-2017 H-ui.admin v3.1 All Rights Reserved.<br>
            本后台系统由<a href="http://www.h-ui.net/" target="_blank" title="H-ui前端框架">H-ui前端框架</a>提供前端技术支持</p>
    </div>
</footer>
<script type="text/javascript" src="<%=basepath%>/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=basepath%>/static/h-ui/js/H-ui.min.js"></script>
</body>
</html>

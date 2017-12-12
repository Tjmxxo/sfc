<%--
  Created by IntelliJ IDEA.
  User: 95217
  Date: 2017/12/6 0006
  Time: 15:24
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
    <script type="text/javascript" src="<%=basepath%>/lib/html5shiv.js"></script>
    <script type="text/javascript" src="<%=basepath%>/lib/respond.min.js"></script>
    <![endif]-->
    <link href="<%=basepath%>/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basepath%>/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basepath%>/static/h-ui.admin/css/H-ui.admin.css" rel="stylesheet" type="text/css"/>
    <!--[if IE 6]>
    <script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>404页面</title>
</head>
<body>
<section class="container-fluid page-404 minWP text-c">
    <p class="error-title"><i class="Hui-iconfont va-m" style="font-size:80px">&#xe688;</i>
        <span class="va-m"> 404</span>
    </p>
    <p class="error-description">不好意思，您访问的页面不存在~</p>
    <p class="error-info">您可以：
        <a href="javascript:;" onclick="history.go(-1)" class="c-primary">&lt; 返回上一页</a>
        <span class="ml-20">|</span>
        <a href="<%=basepath%>/index.jsp" class="c-primary ml-20">去首页 &gt;</a>
    </p>
</section>
</body>
</html>

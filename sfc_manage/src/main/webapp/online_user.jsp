<%--
  Created by IntelliJ IDEA.
  User: 95217
  Date: 2017/12/5 0005
  Time: 14:34
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
    <link rel="stylesheet" type="text/css" href="<%=basepath%>/static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basepath%>/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basepath%>/lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basepath%>/static/h-ui.admin/skin/default/skin.css" id="skin"/>
    <link rel="stylesheet" type="text/css" href="<%=basepath%>/static/h-ui.admin/css/style.css"/>
    <title>权限管理</title>
</head>
<body>
<table class="table table-border table-bordered table-bg table-hover table-sort table-responsive">
    <tr>
        <td colspan="3">在线人数&nbsp;&nbsp;&nbsp;${user.size()}</td>
    </tr>
    <tr class="">
        <th>用户首次登录时间</th>
        <th>用户登录ip</th>
        <th>用户SessionId</th>
    </tr>
    <c:forEach items="${user}" var="user">
        <tr>
            <td>${user.firstTime.toString()}</td>
            <td>${user.ip}</td>
            <td>${user.sessionId}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

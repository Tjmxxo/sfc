<%--
  Created by IntelliJ IDEA.
  User: 95217
  Date: 2017/11/30 0030
  Time: 20:17
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
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="stylesheet" type="text/css" href="<%=basepath%>/static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basepath%>/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basepath%>/lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basepath%>/static/h-ui.admin/skin/default/skin.css" id="skin"/>
    <link rel="stylesheet" type="text/css" href="<%=basepath%>/static/h-ui.admin/css/style.css"/>
    <!--[if IE 7]>
    <link href="http://www.bootcss.com/p/font-awesome/assets/css/font-awesome-ie7.min.css" rel="stylesheet" type="text/css"/>
    <![endif]-->
    <title>添加用户</title>
</head>
<body>
<div class="pd-20">
    <div class="Huiform">
        <form action="<%=basepath%>/cinemachange" method="post">
            <table class="table table-bg">
                <tbody>
                <tr>
                    <th width="100" class="text-r"><span class="c-red">*</span> 影院名称：</th>
                    <td><input type="text" style="width:200px" class="input-text" value="${cinema.cinemaName}" placeholder="" id="user-name" name="cinemaName" datatype="*2-16" nullmsg="用户名不能为空"></td>
                </tr>
                <tr>
                    <th width="100" class="text-r"><span class="c-red">*</span> 影院描述：</th>
                    <td><input type="text" style="width:200px" class="input-text" value="${cinema.cinemaDescribe}" placeholder="" id="user-password" name="cinemaDescribe" datatype="*2-16"
                               nullmsg="用户名不能为空"></td>
                </tr>
                <tr>
                    <th class="text-r"><span class="c-red">*</span> 影院地址：</th>
                    <td><input type="text" style="width:300px" class="input-text" value="${cinema.cinemaAddress}" placeholder="" id="user-tel" name="cinemaAddress"></td>
                </tr>
                <tr>
                    <th class="text-r">联系电话：</th>
                    <td><input type="text" style="width:300px" class="input-text" value="${cinema.cinemaTel}" placeholder="" id="user-email" name="cinemaTel"></td>
                </tr>
                <tr>
                    <th class="text-r">工作时间：</th>
                    <td><input type="text" style="width:300px" class="input-text" value="${cinema.cinemaBusinessHours}" placeholder="" id="user-email" name="cinemaBusinessHours"></td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <button class="btn btn-success radius" type="submit"><i class="icon-ok"></i>提交</button>

                    </td>
                </tr>
                </tbody>
            </table>
        </form>
        <span>${error_message}</span>
        <span>${success_message}</span>
    </div>
</div>

<script type="text/javascript">
    $(".Huiform").Validform();
</script>
<script>
    var _hmt = _hmt || [];
    (function () {
        var hm = document.createElement("script");
        hm.src = "//hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
    var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
</script>
</body>
</html>

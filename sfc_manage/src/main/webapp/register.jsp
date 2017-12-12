<%--
  Created by IntelliJ IDEA.
  User: 95217
  Date: 2017/11/29 0029
  Time: 14:59
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
    <style type="text/css">
        .footer{
            padding-top: 500px;
            border-bottom: 1px solid #e8e8e8;
            border-top:0;
        }
        table tbody{
            position: absolute;
            left: 450px;
            top: 100px;
            width: 500px;
        }
        .Huiform select{
            font-size: 14px;
            width: 200px;
            height: 31px;
            border: solid 1px #ddd;
            line-height: 1.42857;
        }
        .Huiform select:hover{
            border: solid 1px #3bb4f2;
        }
    </style>
</head>
<body>
<div class="pd-20">
    <div class="header"></div>
    <div class="register">
        <div class="Huiform">
            <form action="<%=basepath%>/register" method="POST">
                <table class="table table-bg">
                    <tbody>
                    <tr>
                        <th width="100" class="text-r"><span class="c-red">*</span> 用户：</th>
                        <td><input type="text" style="width:200px" class="input-text" value="" placeholder="" id="user-name" name="userAccount" datatype="*2-16" nullmsg="用户名不能为空"></td>
                    </tr>
                    <tr>
                        <th width="100" class="text-r"><span class="c-red">*</span> 密码：</th>
                        <td><input type="password" style="width:200px" class="input-text" value="" placeholder="" id="user-password" name="userPassword" datatype="*2-16" nullmsg="用户名不能为空"></td>
                    </tr>
                    <tr>
                        <th class="text-r"><span class="c-red">*</span> 手机：</th>
                        <td><input type="text" style="width:300px" class="input-text" value="" placeholder="" id="user-tel" name="userTel"></td>
                    </tr>
                    <tr>
                        <th class="text-r">邮箱：</th>
                        <td><input type="text" style="width:300px" class="input-text" value="" placeholder="" id="user-email" name="userEmail"></td>
                    </tr>
                    <tr>
                        <th class="text-r"><span class="c-red">*</span> 电影院：</th>
                        <td><select name="cinemaId" id="cinamelist">
                            <c:forEach var="cinema" items="${cinames}"><option value="${cinema.cinemaId}">${cinema.cinemaName}</option></c:forEach>
                        </select></td>
                    </tr>
                    <tr>
                        <th></th>
                        <td>
                            <button class="btn btn-success radius" type="submit"><i class="icon-ok"></i> 确定</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
            <span>${error_message}</span>
            <span>${success_message}</span>
        </div>
    </div>
    <div class="footer">Copyright SFC电影院后台管理系统 by H-ui.admin v3.1</div>
</div>
<script type="text/javascript" src="<%=basepath%>/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
    $('#cinamelist').click(function(){
        $.ajax({
            timeout: 3000,
            async: false,
            type: "POST",
            url: "<%=basepath%>/cinames",
            success: function(data){
                var obj = eval("("+data+")");
                for(var i=0;i=obj.length;i++){
                    alert(obj[i]);
                }
            }
        });
    });
    //~~~~~~~~~~~~~~~~~~~~~
    var _hmt = _hmt || [];
    (function () {
        var hm = document.createElement("script");
        hm.src = "//hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
    var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
    document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F080836300300be57b7f34f4b3e97d911' type='text/javascript'%3E%3C/script%3E"));
</script>
</body>
</html>

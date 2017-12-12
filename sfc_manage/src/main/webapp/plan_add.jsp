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
    <title>添加用户</title>
    <!--[if IE 7]>
    <link href="http://www.bootcss.com/p/font-awesome/assets/css/font-awesome-ie7.min.css" rel="stylesheet" type="text/css"/>
    <![endif]-->
    <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>

</head>
<body>
<div class="pd-20">
    <div class="Huiform">
        <form action="<%=basepath%>/planset" method="post">
            <table class="table table-bg">
                <tbody>
                <tr>
                    <th width="100" class="text-r"><span class="c-red">*</span> 本场价格：</th>
                    <td><input type="text" style="width:200px" class="input-text" value="" placeholder="" id="user-name" name="planPrice" datatype="*2-16" nullmsg="用户名不能为空"></td>
                </tr>
                <tr>
                    <th width="100" class="text-r"><span class="c-red">*</span> 影片语言：</th>
                    <td><input type="text" style="width:200px" class="input-text" value="" placeholder="" id="user-password" name="planLanguage" datatype="*2-16" nullmsg="用户名不能为空"></td>
                </tr>
                <tr>
                    <th class="text-r"><span class="c-red">*</span> 荧幕类型：</th>
                    <td><input type="text" style="width:300px" class="input-text" value="" placeholder="" id="user-tel" name="planScreenType"></td>
                </tr>
                <tr>
                    <th class="text-r">本场折扣：</th>
                    <td><input type="text" style="width:300px" class="input-text" value="" placeholder="" id="user-email" name="planDiscount"></td>
                </tr>
                <tr>
                    <th class="text-r">播放电影：</th>
                    <td><select name="movieId" id="">
                        <c:forEach items="${movie}" var="movie">
                            <option class="select-option" value="${movie.movieId}">${movie.movieName}</option>
                        </c:forEach>
                    </select></td>
                </tr>
                <tr>
                    <th class="text-r">所属影厅：</th>
                    <td><select name="hallId" id="">
                        <c:forEach items="${hall}" var="hall">
                            <option class="select-option" value="${hall.hallId}">${hall.hallName}</option>
                        </c:forEach>
                    </select></td>
                </tr>
                <tr>
                    <th class="text-r">售票开始时间：</th>
                    <td><input id="sss" type="datetime-local" name=""></td>
                    <input type="hidden" class="datetimepicker" name="startTime" value="0" id="starts">
                </tr>
                <tr>
                    <th class="text-r">影片开播时间：</th>
                    <td><input type="datetime-local" id="ss" name=""></td>
                    <input type="hidden" name="time" value="0" id="tims">
                </tr>
                <script>
                    function cls() {
                        var start = $("#sss").val();
                        var play = $("#ss").val();
                        var starttime = new Date(start).getTime();
                        var playtime = new Date(play).getTime();
                        $("#starts").val(starttime);
                        $("#tims").val(playtime);
                        var jQuery = $("#starts").val();
                        alert($("#tims").val());
                    }
                </script>
                <tr>
                    <th></th>
                    <td>
                        <button class="btn btn-success radius" onclick="cls()" type="submit"><i class="icon-ok"></i> 提交</button>
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
    document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F080836300300be57b7f34f4b3e97d911' type='text/javascript'%3E%3C/script%3E"));
</script>
</body>
</html>

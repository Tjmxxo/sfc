<%--
  Created by IntelliJ IDEA.
  User: viphu
  Date: 2017-12-01
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="commons/path_init.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" http-equiv="X-UA-Compatible" content="IE=edge"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/sheet.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/other.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/js/dialog/easydialog.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/style.css"/>
    <script type="text/javascript" src="../../js/jquery-1.8.2.min.js"></script>
    <title>上影影城 - Accountupdate Member</title>
</head>

<body class="address_area">
<script type="text/javascript" src="${staticPath}/js/dialog/easydialog.min.js"></script>
<script type="text/javascript" src="${staticPath}/js/script.js"></script>
<script type="text/javascript" src="${staticPath}/js/member.js"></script>
<script type="text/javascript" src="${staticPath}/js/sheet.js"></script>
<script type="text/javascript" src="${staticPath}/js/komovie.js"></script>
<%@include file="/WEB-INF/jsp/commons/header.jsp" %>
<!--------- 顶部 -------------->
<div class="page_box">
    <div id="main">
        <div class="account_content clearfix">
            <%@include file="/WEB-INF/jsp/commons/member_left.jsp" %>
            <div class="account_right fr">
                <ul class="account_list clearfix">
                    <li class="account_current ml_30">修改登录密码</li>
                    <!--<li>修改支付密码</li>
                    <li>验证手机</li>
                    <li>验证邮箱</li>
                --></ul>
                <form action="">
                    <ul class="account_inter content_clear">
                        <li>
                            <div class="login_inter">
                                <span class="inter_left"></span>
                                <span class="inter_middle"><input type="password" name="old_pass"
                                                                  class="width_311"/></span>
                                <span class="inter_right"></span>
                                <span class="pass_tit">请输入旧密码</span>
                            </div>
                        </li>
                        <li>
                            <div class="login_inter">
                                <span class="inter_left"></span>
                                <span class="inter_middle"><input type="password" name="new_pass"
                                                                  class="width_311"/></span>
                                <span class="inter_right"></span>
                                <span class="pass_tit">请输入新密码</span>
                            </div>
                        </li>
                        <li>
                            <div class="login_inter">
                                <span class="inter_left"></span>
                                <span class="inter_middle"><input type="password" name="re_pass"
                                                                  class="width_311"/></span>
                                <span class="inter_right"></span>
                                <span class="pass_tit">请确认新密码</span>
                            </div>
                        </li>
                        <li class="login_button"><input id="mod_pass" type="button" value="完成"/></li>
                    </ul>
                </form>
            </div>
            <img class="ko_loading" src="${staticPath}/images/loading.gif"></div>
        <div class="poster_banner pd_30 mb_none"><a href="http://www.sfccinema.com" target="_blank"><img
                src="http://file.komovie.cn/yingguan/picture/201710/20171023/15087429473015.jpg" width="1200px"
                height="118px" alt=""/></a></div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/commons/footer.jsp" %>
</body>
</html>
<script>
    $(function () {
        $(".account_left ul a[tag=6]").css({"font-size": "14px", "font-weight": "bold"});
    })
</script>

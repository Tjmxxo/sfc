<%--
  Created by IntelliJ IDEA.
  User: 95217
  Date: 2017/11/29 0029
  Time: 13:49
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
    <link rel="Bookmark" href="/favicon.ico">
    <link rel="Shortcut Icon" href="/favicon.ico"/>
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
    <script type="text/javascript" src="<%=basepath%>/lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>H-ui.admin v3.1</title>
    <meta name="keywords" content="H-ui.admin v3.1,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
    <meta name="description" content="H-ui.admin v3.1，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
    <script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
<header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
        <div class="container-fluid cl">
            <a class="logo navbar-logo f-l mr-10 hidden-xs" href="<%=basepath%>/aboutHui.shtml">${cinimaname}</a>
            <a class="logo navbar-logo-m f-l mr-10 visible-xs" href="<%=basepath%>/aboutHui.shtml">H-u</a>
            <span class="logo navbar-slogan f-l mr-10 hidden-xs">SFC电影院后台管理系统</span>
            <a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
            <nav class="nav navbar-nav">
            </nav>
            <div id="times" style="height: 44px;color: white;margin:0px auto;width: 200px;line-height: 44px;">

            </div>

            <script>
                var d = new Date();
                var s = d.getFullYear() + "年" + (d.getMonth() + 1) + "月" + d.getDate() + "日";
                var s2 = ' 星期' + '日一二三四五六'.charAt(new Date().getDay());

                $(function () {
                    $("#times").text('日期 : '+s + s2);
                })
            </script>
            <nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
                <ul class="cl">
                    <li>当前用户 : ${user.userAccount}</li>
                    <li><a href="<%=basepath%>/toLogin">注销</a></li>
                </ul>
            </nav>
        </div>
    </div>
</header>
<s:hasAnyRoles name="staff">
    <span>${error_message}</span>
    <span>${success_message}</span>
    <aside class="Hui-aside">
        <div class="menu_dropdown bk_2">
            <dl id="menu-picture">
                <dt><i class="Hui-iconfont">&#xe613;</i> 影院信息<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
                <dd>
                    <ul>
                        <li><a data-href="<%=basepath%>/cinemashow" data-title="信息修改" href="javascript:void(0)">信息修改</a></li>
                        <li><a data-href="<%=basepath%>/hallview" data-title="影厅修改" href="javascript:void(0)">影厅修改</a></li>
                        <li><a data-href="<%=basepath%>/hall_add.jsp" data-title="影厅修改" href="javascript:void(0)">新增影厅</a></li>
                    </ul>
                </dd>
            </dl>
            <dl id="menu-article">
                <dt><i class="Hui-iconfont">&#xe616;</i> 场次管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
                <dd>
                    <ul>
                        <li><a data-href="<%=basepath%>/plan/plantodaylist" data-title="当前场次" href="javascript:void(0)">今日场次</a></li>
                        <li><a data-href="<%=basepath%>/plan/planalllist" data-title="历史场次" href="javascript:void(0)">历史场次</a></li>
                        <li><a data-href="<%=basepath%>/planadd" data-title="新增场次" href="javascript:void(0)">新增场次</a></li>
                    </ul>
                </dd>
            </dl>

            <dl id="menu-picture">
                <dt><i class="Hui-iconfont">&#xe613;</i> 会员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
                <dd>
                    <ul>
                        <li><a data-href="<%=basepath%>/orderlist" data-title="用户订单" href="javascript:void(0)">用户订单</a></li>
                        <li><a data-href="<%=basepath%>/vipview" data-title="用户订单" href="javascript:void(0)">vip卡信息</a></li>
                    </ul>
                </dd>
            </dl>
            <dl id="menu-member">
                <dt><i class="Hui-iconfont">&#xe60d;</i> 紧急事件<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
                <dd>
                    <ul>
                        <li><a data-href="<%=basepath%>/hallviewurgency" data-title="会员列表" href="javascript:;">影厅故障</a></li>
                        <li><a data-href="<%=basepath%>/urgency_cinema.jsp" data-title="会员列表" href="javascript:;">影院故障</a></li>
                    </ul>
                </dd>
            </dl>
            <dl id="menu-tongji">
                <dt><i class="Hui-iconfont">&#xe61a;</i> 信息统计<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
                <dd>
                    <ul>
                        <li><a data-href="<%=basepath%>/onlinecount" data-title="折线图" href="javascript:void(0)">在线人数</a></li>
                        <li><a data-href="<%=basepath%>/moviecount" data-title="时间轴折线图" href="javascript:void(0)">最热电影</a></li>
                    </ul>
                </dd>
            </dl>
        </div>
    </aside>
</s:hasAnyRoles>
<s:hasAnyRoles name="admin">
    <span>${error_message}</span>
    <span>${success_message}</span>
    <aside class="Hui-aside">
        <div class="menu_dropdown bk_2">
            <dl id="menu-product">
                <dt><i class="Hui-iconfont">&#xe620;</i> 影区管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
                <dd>
                    <ul>
                        <li><a data-href="<%=basepath%>/cinema/cinema_list" data-title="影院管理" href="javascript:void(0)">影院管理</a></li>
                        <li><a data-href="<%=basepath%>/movie/movie_list?pageNo=1" data-title="电影管理" href="javascript:void(0)">电影管理</a></li>
                    </ul>
                </dd>
            </dl>
            <dl id="menu-admin">
                <dt><i class="Hui-iconfont">&#xe62d;</i> 管理员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
                <dd>
                    <ul>
                        <%--<li><a data-href="admin-permission.html" data-title="权限管理" href="javascript:void(0)">权限管理</a></li>--%>
                        <li><a data-href="admin-list.html" data-title="影院管理员列表" href="javascript:void(0)">管理员列表</a></li>
                    </ul>
                </dd>
            </dl>
            <dl id="menu-system">
                <dt><i class="Hui-iconfont">&#xe62e;</i> 系统管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
                <dd>
                    <ul>
                        <li><a data-href="system-log.html" data-title="系统日志" href="javascript:void(0)">系统日志</a></li>
                    </ul>
                </dd>
            </dl>
        </div>
    </aside>
</s:hasAnyRoles>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
    <div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
        <div class="Hui-tabNav-wp">
            <ul id="min_title_list" class="acrossTab cl">
                <li class="active">
                    <span title="我的桌面" data-href="welcome.jsp">我的桌面</span>
                    <em></em></li>
            </ul>
        </div>
        <div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next"
                                                                                                                                                                           class="btn radius btn-default size-S"
                                                                                                                                                                           href="javascript:;"><i
                class="Hui-iconfont">&#xe6d7;</i></a></div>
    </div>
    <div id="iframe_box" class="Hui-article">
        <div class="show_iframe">
            <div style="display:none" class="loading"></div>
            <iframe scrolling="yes" frameborder="0" src="welcome.jsp"></iframe>
        </div>
    </div>
</section>

<div class="contextMenu" id="Huiadminmenu">
    <ul>
        <li id="closethis">关闭当前</li>
        <li id="closeall">关闭全部</li>
    </ul>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=basepath%>/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=basepath%>/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=basepath%>/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="<%=basepath%>/static/h-ui.admin/js/H-ui.admin.js"></script>
</body>
</html>

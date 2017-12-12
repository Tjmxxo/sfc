<%--
  Created by IntelliJ IDEA.
  User: viphu
  Date: 2017-12-07
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/commons/path_init.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" http-equiv="X-UA-Compatible" content="IE=edge"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/sheet.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/js/dialog/easydialog.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/photoShow.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/style.css"/>
    <script type="text/javascript" src="${staticPath}/js/jquery-1.8.2.min.js"></script>
    <title>影片详情-上影影城</title>
</head>
<body class="address_area">
<script type="text/javascript" src="${staticPath}/js/dialog/easydialog.min.js"></script>
<script type="text/javascript" src="${staticPath}/js/script.js"></script>
<script type="text/javascript" src="${staticPath}/js/movie.js"></script>
<script type="text/javascript" src="${staticPath}/js/sheet.js"></script>
<script type="text/javascript" src="${staticPath}/js/movie_gal.js"></script>
<script type="text/javascript" src="${staticPath}/js/photoShow.js"></script>
<%@include file="/WEB-INF/jsp/commons/header.jsp" %>
<div class="page_box">
    <div class="detail_banner"><a> <img src="${staticPath}/images/detail_banner.jpg" alt=""></a></div>
    <div class="detail_content clearfix mb_150">
        <div class="detail_name">
            <div class="detail_title clearfix">
                <div class="detail_key fl">${movie.movieName}</div>
            </div>
            <div class="detail_information clearfix">
                <div class="index_layer_like_detail hidden" id="index_layer_one_">喜欢+1</div>
                <a href="#" class="detail_picture"><img
                        src="${movie.moviePoster}" alt=""></a>
                <div class="detail_up">
                    <dl class="detail_story">
                        <dt>${movie.moviePublishTime} 上映</dt>
                        <dd>导演：${movie.movieDirector}</dd>
                        <dd>主演：${movie.movieActor}</dd>
                        <dd>类型：${movie.movieType}</dd>
                        <dd>地区：${movie.movieCountry}</dd>
                        <dd>片长：${movie.moviePlayTime}分钟</dd>
                        <dd>语言：${movie.movieLanguage}</dd>
                    </dl>
                    <ul class="detail_bar clearfix" id="detail_bar">
                        <li class="bar_current ml_30">剧照</li>
                        <!--<li>预告片</li>-->
                    </ul>
                    <input type="hidden" id="hid_movie_id" value="${movie.movieId}"/>
                    <input type="hidden" id="selectCinema" value=""/>
                    <input type="hidden" id="hid_cinema_id"/>
                    <input type="hidden" id="hid_date_id"/>
                    <input type="hidden" id="hid_photo_url"/>
                    <div class="detail_scroll clearfix">
                        <span class="scroll_left fl"></span>
                        <div class="scroll_middle fl">
                            <ul class="scroll_content clearfix">
                            </ul>
                        </div>
                        <script type="text/javascript">
                            jQuery('.scroll_content li').QQPhoto({
                                url: 'movie/GetGallerys?id=101310',
                                fix: 5,                           //表示相册列表左右间距的距离                 默认 5
                                minTextLen: 3,                    //发送评论时 表单验证，最少不能少于的字符    默认三个字符
                                maxTextLen: 140,                  //发送评论时 表单验证，最多不能少于的字符    默认140个字符
                                fadeIn: true,                     //图片默认是否开启淡入效果，默认为 false
                                lazyload: 'images/lazyload.gif',   //默认lazyload的图片，默认为1px.gif 透明图片
                                loadding: 'images/loading.gif'    //loading.gif 的文件路径
                            });
                        </script>
                        <span class="scroll_right fl"></span>
                    </div>
                    <div class="detail_scroll clearfix hidden">
                        <span style="border: 1px solid #CCC;padding: 10px;margin: 0 10px 10px 10px;float: left;">操作成功</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="detail_introduce clearfix">
            <div class="detail_left fl">
                <div class="detail_pay">
                    <div class="detail_menu clearfix">
                        <ul id="detail_list">
                            <li class="menu_current">排期购票</li>
                            <li>剧情介绍</li>
                        </ul>
                        <div class="detail_price">
                            <p>上海5家上影国际影城
                                <strong><fmt:formatNumber value="${movie.minPrice/100}" pattern="0.00"/> </strong>
                                元起</p>
                        </div>
                    </div>
                    <div class="detail_take">
                        <div class="detail_caption"><strong>片名：${movie.movieName}</strong>片长：${movie.moviePlayTime}分钟</div>
                        <div class="detail_date clearfix">
                            <p>选择影院：</p>
                            <ul class="fl" id="cinema_list"></ul>
                        </div>
                        <div class="detail_date clearfix">
                            <p>选择时间：</p>
                            <ul id="plan_date">
                            </ul>
                        </div>
                        <div id="cinema_detail"></div>
                        <ul class="detail_once clearfix" id="plan_info">
                        </ul>
                    </div>
                    <div class="detail_take hidden">
                        <div class="detail_caption"><strong>片名：${movie.movieName}</strong>片长：${movie.moviePlayTime}分钟</div>
                        <ul class="detail_notice">
                            <li class="detail_poster">${movie.movieName}</li>
                            </li>
                        </ul>
                    </div>
                    <div class="detail_take hidden">
                        <ul class="detail_list"></ul>
                    </div>
                    <div class="detail_take hidden">
                    </div>
                </div>
            </div>
            <div class="list_right fr pt_0">
                <div class="list_flack hidden">
                    <a  target="_blank"><img
                            src="http://file.komovie.cn/yingguan/picture/201608/20160801/14700451823933.jpg"
                            width="259px" height="115px" alt=""></a>
                    <a  target="_blank"><img
                            src="http://file.komovie.cn/yingguan/picture/201704/20170410/14918090728567.jpg"
                            width="259px" height="115px" alt=""></a>
                    <a  target="_blank"><img
                            src="http://file.komovie.cn/yingguan/picture/201602/20160226/14564529956707.jpg"
                            width="259px" height="115px" alt=""></a>
                    <a  target="_blank"><img
                            src="http://file.komovie.cn/yingguan/picture/201602/20160216/14556019546268.jpg"
                            width="259px" height="115px" alt=""></a>
                </div>
                <div class="list_hot">
                    <div class="list_point">一周购票排行</div>
                    <div class="list_offer clearfix">
                        <a  class="list_show"><img
                                src="http://audio.komovie.cn/sns/2017/10/24/96789839896102156148.jpg" alt=""
                                width="79px" height="119px;"></a>
                        <div class="list_one">
                            <span class="clearfix"><a >寻梦环游记</a>
                                <!-- <strong>8</strong><font>.0</font> --></span>
                            <div class="list_type">动画 / 音乐 / 冒险//</div>
                            <a  class="list_seat">选座购票</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="poster_banner"><a href="" target="_blank"><img
                src="http://file.komovie.cn/yingguan/picture/201710/20171023/15087429473015.jpg" width="1200px"
                height="118px" alt=""></a></div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/commons/footer.jsp" %>
<script type="text/javascript">
    getCinema();
</script>
</body>
</html>

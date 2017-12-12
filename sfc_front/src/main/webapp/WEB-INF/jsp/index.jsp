<%--
  Created by IntelliJ IDEA.
  User: viphu
  Date: 2017-11-30
  Time: 23:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/commons/path_init.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" http-equiv="X-UA-Compatible" content="IE=edge"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/sheet.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/other.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/js/dialog/easydialog.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/style.css"/>
    <script type="text/javascript" src="${staticPath}/js/jquery-1.8.2.min.js"></script>
    <title>上影影城 - 首页</title>
</head>

<body class="address_area">
<script type="text/javascript" src="${staticPath}/js/dialog/easydialog.min.js"></script>
<script type="text/javascript" src="${staticPath}/js/script.js"></script>
<script type="text/javascript" src="${staticPath}/js/index.js"></script>
<script type="text/javascript" src="${staticPath}/js/sheet.js"></script>
<script type="text/javascript" src="${staticPath}/js/superslide.2.1.js"></script>
<%@include file="/WEB-INF/jsp/commons/header.jsp" %>
<!--------- 顶部 -------------->
<script type="text/javascript">
    $(document).ready(function () {
        $(window).resize(function () {
            var width = $(window).width();
            var size = 1920 / 400;
            var height = width / size;		// 总体高度
            var top = height / 2 - ((height / 2) / 4 + 5);			// 按钮居顶高度
            jQuery('#banner_play').css('width', width);
            jQuery('.banner-box').css('height', height);
            jQuery('.banner-btn').css('top', top);
        });

        $(".prev,.next").hover(function () {
            $(this).stop(true, false).fadeTo("show", 0.9);
        }, function () {
            $(this).stop(true, false).fadeTo("show", 0.4);
        });

        $(".banner-box").slide({
            titCell: ".hd ul",
            mainCell: ".bd ul",
            effect: "fold",
            interTime: 3500,
            delayTime: 500,
            autoPlay: true,
            autoPage: true,
            trigger: "click"
        });

        var width = $(window).width();
        var size = 1920 / 400;
        var height = width / size;		// 总体高度
        var top = height / 2 - ((height / 2) / 4 + 5);			// 按钮居顶高度
        jQuery('#banner_play').css('width', width);
        jQuery('.banner-box').css('height', height);
        jQuery('.banner-btn').css('top', top);
    });
</script>
<div class="banner-box">
    <div class="bd">
        <ul id="banner_play" style="position: relative; width: 100%; height: 360px;">
            <li style=" position: absolute; width: 1920px; left: 0px; top: 0px; display: none;">
                <div class="m-width">
                    <a href="http://mp.weixin.qq.com/s/utbMeyO4VRfg8txmp9rF-A"><img
                            src="http://file.komovie.cn/yingguan/picture/201708/20170810/15023345609736.jpg"></a>
                </div>
            </li>
        </ul>
    </div>
    <div class="banner-btn">
        <a class="prev" href="javascript:void(0);"></a>
        <a style="opacity: 0.4;" class="next" href="javascript:void(0);"></a>
        <div class="hd">
            <ul>
                <li class="">0</li>

                <li class="">1</li>

                <li class="">2</li>

                <li class="">3</li>

                <li class="">4</li>
            </ul>
        </div>
    </div>
</div>
<div class="select_time">
    <div class="wrap">
        <div class="quick_buy clearfix">
            <div class="select_content_time">
                <div class="select_input clearfix">
                    <span></span>
                    <input type="text" readOnly="readOnly" id="movie_id_input" value="请选择影片">
                    <p id="movie_id"></p>
                </div>
                <div class="qui_search_drop" id="movie_div">
                    <div class="qui_search_drop_list">
                        <ul id="movie_list">
                        </ul>
                    </div>
                </div>
            </div>
            <div class="select_content_time">
                <div class="select_input clearfix">
                    <span></span>
                    <input type="text" readOnly="readOnly" id="cinema_id_input" value="请选择影院">
                    <p id="cinema_id"></p>
                </div>
                <div class="qui_search_drop" id="cinema_div">
                    <div class="qui_search_drop_list">
                        <ul id="cinema_ul">
                            <li><a>请先选择影片</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="select_content_time">
                <div class="select_input clearfix">
                    <span></span>
                    <input type="text" readOnly="readOnly" id="date_id_input" value="请选日期" class="width_125">
                    <p id="date_id"></p>
                </div>
                <div class="qui_search_drop width_180" id="date_div">
                    <div class="qui_search_drop_list">
                        <ul id="date_ul">
                            <li><a>请先选择影院</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="select_content_time">
                <div class="select_input clearfix">
                    <span></span>
                    <input type="text" readOnly="readOnly" id="plan_id_input" value="请选择时间" class="width_125">
                    <p id="plan_id"></p>
                </div>
                <div class="qui_search_drop width_180" id="plan_div">
                    <div class="qui_search_drop_list">
                        <ul id="plan_ul">
                            <li><a>请选日期</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <input type="hidden" id="hid_movie_id">
            <input type="hidden" id="hid_cinema_id">
            <input type="hidden" id="hid_plan_id">
            <input type="hidden" id="hid_date_id">
            <a class="qui_search_item_buttom" href="javascript:void(0)" id="buy_button">立即购票</a>
            <div class="clear"></div>
        </div>
        <div class="clear"></div>
    </div><!-- part one -->
</div>
<div class="movie_content_box">
    <div id="main" class="pt_30">
        <div class="wrap clearfix">
            <div class="movie_left fl">
                <div class="nav_key" id="nav_title">
                    <span class="title_line"></span>
                    <font class="title_hot_current">正在热映</font>
                    <span class="title_line_left">|</span>
                </div>
                <!-- 正在热映 -->
                <div class="movie_once_hot" id="tagContent0">
                    <div class="word_tip clearfix " id="movie_div_page_1">
                        <div class="word_icon fr"></div>
                        <span class="word_content">共有<font>0</font>部影片正在热映</span>
                    </div>
                    <div class="show_bg hot_movie_list clearfix " id="movie_div_1">
                        <div class="f_show_grid_bg left">
                            <ul >
                                <li>
                                    <div class="show_cell_list left ">
                                        <ul id="movie_show"></ul>
                                    </div>
                                    <div class="clear"></div>
                                </li>
                                <div class="clear"></div>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="movie_right fr">
                <div class="movie_download">
                    <div class="movie_number">
                        <img src="${staticPath}/images/download_number.png">
                        <span>扫一扫免费下载</span>
                    </div>
                </div>
                <div class="movie_pay_content">
                    <ul class="movie_pay_index">
                        <li class="movie_one">
                            <span>上影电影<br/>支付宝服务窗</span>
                            <img src="${staticPath}/images/login_service_alipay.png">
                        </li>
                        <li>
                            <span class="movie_out">上影电影<br/>微信服务窗</span>
                            <img src="${staticPath}/images/login_number.png">
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="poster_banner">
            <a href="" target="_blank"><img
                src="http://file.komovie.cn/yingguan/picture/201710/20171023/15087429473015.jpg" width="1200px"
                height="118px" alt=""></a></div>
        <div class="activity_area">
            <div class="nav_key width_1200">
                <span class="title_line"></span>
                <font>网站资讯</font>
            </div>
            <div class="activity_picture clearfix">
                <div class="movie_tip"
                     style="width: 550px;float: left;margin: 0 11px 10px 11px;border: 1px solid #e5e5e5;padding: 3px;">
                    <p class="station_title">
                        <span class="fr">更新时间：2016-02-16 13:15:11</span>
                        <a  target="_blank" style="width: 340px;">
                            来关注我们的微信吧 </a>
                    </p>
                    <div class="movie_layer clearfix">
                        <a  target="_blank" class="movie_pic fl"><img
                                src="http://file.komovie.cn/yingguan/picture/201602/20160216/14556019546268.jpg" alt=""></a>
                        <div class="movie_word fr" style="width: 300px;">
                            <p>扫二维码，关注我们的微信。了解影讯，购票，排片。还可以赢奖品哦！</p>
                            <div class="movie_detail clearfix">
                                <a  target="_blank" class="movie_see fr">查看全文</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function likes(obj, mid, type) {
        $.ajax({
            url: "movie/movielike",
            data: {film_id: mid},
            success: function (data) {
            }
        });
        var cs = 'layer_like_left_35';
        if (type == 1) {
            $("#index_layer_one_" + mid).addClass(cs).css("left", "102px").fadeIn().removeClass("hidden").html("喜欢+1");
            $(obj).addClass("background-position").attr({onclick: 'likeds(this,' + mid + ',' + type + ')'})

        } else if (type == 2) {
            $("#index_layer_one_" + mid).addClass(cs).css("left", "59px").fadeIn().removeClass("hidden").html("喜欢+1");
            $(obj).addClass("background-position").attr({onclick: 'likeds(this,' + mid + ',' + type + ')'})
        } else if (type == 3) {
            $("#index_layer_one_" + mid).addClass(cs).css("left", "47px").fadeIn().removeClass("hidden").html("喜欢+1");
            $(obj).addClass("background-position").attr({onclick: 'likeds(this,' + mid + ',' + type + ')'})
        }
        setCookie('xingmei', mid);
        $("#index_layer_one_" + mid).fadeOut({duration: 1000});
    }

    function likeds(obj, mid, type) {
        var cs = 'layer_like_left_35';
        if (type == 1) {
            $("#index_layer_one_" + mid).addClass(cs).css("left", "75px").fadeIn().removeClass("hidden").html("您已经喜欢过了");
        } else if (type == 2) {
            $("#index_layer_one_" + mid).addClass(cs).css("left", "32px").fadeIn().removeClass("hidden").html("您已经喜欢过了");
        } else if (type == 3) {
            $("#index_layer_one_" + mid).addClass(cs).css("left", "20px").fadeIn().removeClass("hidden").html("您已经喜欢过了");
        }
        $("#index_layer_one_" + mid).fadeOut({duration: 1000});
    }
    function func(data) {
        var movieList = jQuery('#movie_list');
        var movieShow = jQuery("#movie_show");
        var movieCount = jQuery(".word_content font");
        var list = '';
        var show ='';
        if (data == 0) {
            showEasyMsg("该城市没有电影放映")
        } else {
            data.forEach(function (movie) {
                list += "<li><a href=\"javascript:void(0)\" onclick=\"movieSelect(this)\" mid=\"" + movie['movieId'] + "\" title=\"" + movie['movieName'] + "\">" + movie['movieName'] + "</a></li>";
                show += "<li class=\"position_r\" >\n" +
                    "    <div class=\"index_layer_like hidden\" id=\"index_layer_one_"+movie['movieId']+"\">喜欢+1</div>\n" +
                    "    <div class=\"show_cell position_r\">\n" +
                    "        <a href=\"${localPath}/index/movie/"+movie['movieId']+".html\">\n" +
                    "            <img src=\""+movie['moviePoster']+"\"  id=\"movie_img_"+movie['moviePoster']+"\" width=\"220px\" height=\"300px\"/>\n" +
                    "        </a>\n" +
                    "        <div class=\"f_titl clearfix\" id=\"movie_end_100245\">\n" +
                    "            <a href=\"${localPath}/index/movie/"+movie['movieId']+".html\" class=\"left title_word\">"+movie['movieName']+"</a>\n" +
                    "            <div class=\"show_ticket right\"><span>￥40</span>&nbsp;元起</div>\n" +
                    "        </div>\n" +
                    "        <div class=\"movie_buy clearfix\">\n" +
                    "            <a href=\"${localPath}/index/movie/"+movie['movieId']+".html\" class=\"will_show fl\"></a>\n" +
                    "            <a href=\"${localPath}/index/movie/"+movie['movieId']+".html\" class=\"movie_button fr\">选座购票</a>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</li>";
            });
            movieShow.html(show);
            movieList.html(list);
            movieCount.html(data.length)
        }
    }
    $(document).ready(function () {
        getCurrentMovie(func)
    });
</script>
<%@include file="/WEB-INF/jsp/commons/footer.jsp" %>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: viphu
  Date: 2017-12-03
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/jsp/commons/path_init.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" http-equiv="X-UA-Compatible" content="IE=edge"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/sheet.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/js/dialog/easydialog.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/style.css"/>
    <script type="text/javascript" src="${staticPath}/js/jquery-1.8.2.min.js"></script>
    <title>在线选座-上影影城</title>
</head>

<body class="address_area">
<script type="text/javascript" src="${staticPath}/js/dialog/easydialog.min.js"></script>
<script type="text/javascript" src="${staticPath}/js/script.js"></script>
<script type="text/javascript" src="${staticPath}/js/sheet.js"></script>
<script type="text/javascript" src="${staticPath}/js/seat.js"></script>
<%@include file="/WEB-INF/jsp/commons/header.jsp" %>
<!--------- 顶部 -------------->
<div class="page_box pt_30_new pb_30">
    <div class="background_color">
        <div class="select_top_choose clearfix">
            <span>1.选择影片场次</span>
            <span class="select_current">2.在线选座</span>
            <span>3.支付订单</span>
            <span>4.影城取票观影</span>
        </div>
        <div class="select_content clearfix">
            <div class="select_left fl">
                <div class="select_tip clearfix">
                    <span class="free_seat"></span>
                    <font>可选座位</font>
                    <span class="choose_seat"></span>
                    <font>已选座位</font>
                    <span class="lock_seat"></span>
                    <font>不可选座位</font>
                </div>
                <div class="select_screen"><span>${plan.cinemaTbDTO.cinemaName}</span></div>
                <div class="select_seat clearfix" id="seat_map" planId=${plan.planId}>
                    <c:forEach var="row" items="${planSeat}">
                        <div style="width:710px;margin: 0 auto;"><p class="line_div">${row.key}排</p>
                            <dl>
                                <c:forEach var="seat" items="${row.value}">
                                    <dd id="s_${row.key}_${seat.seatTbDTO.seatCol}"
                                        r="${row.key}"
                                        c="${seat.seatTbDTO.seatCol}"
                                        class="<c:choose><c:when test="${seat.seatStatus==-1}">none_seat</c:when><c:when test="${seat.seatStatus==0}">free_seat</c:when><c:when test="${seat.seatStatus==1}">lock_seat</c:when></c:choose> seat"
                                        seattype="${seat.seatStatus}"
                                        seatstate="${seat.seatStatus}"
                                        seatcol="${seat.seatTbDTO.seatCol}"
                                        seatrow="${row.key}"
                                        seatno="${seat.seatTbDTO.seatId}"
                                        seatpieceno="undefined"
                                        title="${row.key}排${seat.seatTbDTO.seatCol}座"
                                        wen="${row.key}排${seat.seatTbDTO.seatCol}座">
                                        <span class="seat_one">${seat.seatTbDTO.seatCol}</span>
                                    </dd>
                                </c:forEach>
                            </dl>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="select_right fr">
                <div class="select_picture">
                    <a ><img
                            src="${plan.movieTbDTO.moviePoster}"
                            alt="" width="271px" height="405px"/></a>
                    <div class="select_layer"></div>
                    <ul class="layer_content">
                        <li class="layer_title">${plan.movieTbDTO.movieName}</li>
                        <li>语言制式： ${plan.movieTbDTO.movieType}</li>
                        <li>影片时长：${plan.movieTbDTO.moviePlayTime}分钟</li>
                        <li class="layer_key">主演：${plan.movieTbDTO.movieActor}</li>
                    </ul>
                </div>
                <input type="hidden" value="${plan.planPrice*(100-plan.planDiscount)/10000}" id="plan_price"/>
                <dl class="select_word">
                    <dt>影院：${plan.cinemaTbDTO.cinemaName}</dt>
                    <dd>影厅：${plan.hallTbDTO.hallName}</dd>
                    <dd class="select_session clearfix">
                        <p class="fr"><a>重新选择</a></p>
                        场次：${plan.planData} ${plan.planTime} <font>￥${plan.planPrice*(100-plan.planDiscount)/10000}</font>/张
                    </dd>
                    <dd class="seat_number">座位：<font><span class="seat_sell"></span></font></dd>
                    <dd class="seat_information">单击左侧座位图选择座位，再次单击取消</dd>
                    <dd class="select_total select_line"><span>总计：</span><strong id="total_price">￥0.00</strong></dd>
                </dl>
                <ul class="select_inter">
                    <li class="clearfix">
                        <span>手机号码：</span>
                        <input type="text" id="order_mobile" disabled="disabled" value="${userBean==null?'':userBean.userTel}">
                    </li>
                    <li><a href="javascript:void(0)" id="buy_next">下一步</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!--------- 底部 ---------------->
<%@include file="/WEB-INF/jsp/commons/footer.jsp" %>
</body>
</html>
<script type="text/javascript">
</script>

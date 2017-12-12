<%--
  Created by IntelliJ IDEA.
  User: viphu
  Date: 2017-12-05
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="cc.xpress.config.OrderStatus" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="commons/path_init.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" http-equiv="X-UA-Compatible" content="IE=edge"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/sheet.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/other.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/js/dialog/easydialog.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/style.css"/>
    <script type="text/javascript" src="${staticPath}/js/jquery-1.8.2.min.js"></script>
    <title>我的订单-上影影城</title>
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
                <ul id="order_type" class="account_list member_order_list clearfix">
                    <li class="${os==null?'account_current':''}"><a href="${localPath}/member/order"
                                                                    class="black">全部</a></li>
                    <li class="${os==OrderStatus.NON_PAYMENT_ORDER?'account_current':''}"><a
                            href="${localPath}/member/order?tag=${OrderStatus.NON_PAYMENT_ORDER}"
                            class="black">等待支付</a></li>
                    <li class="${os==OrderStatus.CANCEL_ORDER?'account_current':''}"><a
                            href="${localPath}/member/order?tag=${OrderStatus.CANCEL_ORDER}" class="black">已取消/过期</a>
                    </li>
                    <li class="${os==OrderStatus.PAID_ORDER?'account_current':''}"><a
                            href="${localPath}/member/order?tag=${OrderStatus.PAID_ORDER}" class="black">已完成</a></li>
                </ul>
                <div class="movie_list ">
                    <div class="movie_show clearfix" style="display:none">
                        <span>显示</span>
                        <select name="" id="monthSelect" onchange="monthSelect();">
                            <option value="0">全部</option>
                            <option value="3">3个月内</option>
                            <option value="2">2个月内</option>
                            <option value="1">1个月内</option>
                        </select>
                        <span>的订单记录</span>
                    </div>
                    <ul class="movie_title clearfix">
                        <li class="movie_order">电影票订单信息</li>
                        <li class="movie_time">购买时间</li>
                        <li class="movie_status">订单状态</li>
                        <li class="movie_money">金额</li>
                        <li class="movie_operate">操作</li>
                    </ul>
                    <div id="monthSelectData" style="height:80%;overflow-y:auto;overflow-x:hidden;">
                        <c:forEach var="order" items="${userOrder}">
                            <ul class="movie_content">
                                <li class="movie_order">
                                    <div class="movie_information clearfix">
                                        <a href="javascript:void(0)" onclick="showDetail(${order.orderId})"
                                           class="movie_picture"><img
                                                src="${order.movieTbDTO.moviePoster}"
                                                width="73px" height="110px" alt=""/></a>
                                        <dl>
                                            <dt><a href="javascript:void(0)" onclick="showDetail(${order.orderId})">${order.movieTbDTO.movieName}</a>
                                            </dt>
                                            <dd>订单编号：${order.orderId}</dd>
                                            <dd>观影时间：${order.planTbDTO.planData}&nbsp;&nbsp;${order.planTbDTO.planTime}</dd>
                                            <dd>影城：${order.cinemaTbDTO.cinemaName}</dd>
                                            <dd class="order_seat">
                                                座位
                                                <c:forEach items="${order.orderSeatTbDTOS}" var="seat">
                                                    <span><strong>${seat.seatTbDTO.seatRow}排${seat.seatTbDTO.seatCol}座</span></strong>;
                                                </c:forEach>
                                            </dd>
                                        </dl>
                                    </div>
                                </li>
                                <li class="movie_time">
                                    <span>${order.planTbDTO.planData}<br/>${order.planTbDTO.planTime}</span>
                                </li>
                                <li class="movie_status">
                                    <c:if test="${order.orderStatus==1||order.orderStatus==0}">
                                        <span class="movie_coin">生成订单</span>
                                    </c:if>
                                    <c:if test="${order.orderStatus==-1}">
                                        <span class="movie_current font_color">取消订单</span>
                                    </c:if>
                                </li>
                                <li class="movie_money">
                                    <span class="movie_coin clearfix"><span>
                                        ￥<fmt:formatNumber value="${order.orderSumPrice/100}" pattern="0.00"/>
                                    </span><font></font></span>
                                </li>
                                <li class="movie_operate">
                                    <c:if test="${order.orderStatus==0}">
                                        <span>
                                            <a href="javascript:void(0)" onclick="cancelOrder('${order.orderId}')">取消订单</a>
                                        </span>
                                        <a href="${localPath}/buy/order/${order.orderId}.html" class="movie_pay">立即支付</a>
                                    </c:if>
                                    <c:if test="${order.orderStatus==-1}">
                                        <a class="movie_exit">过期/已取消</a>
                                    </c:if>
                                    <c:if test="${order.orderStatus==1}">
                                        <span>
                                            <a href="javascript:void(0)" onclick="cancelOrder('${order.orderId}')">取消订单</a>
                                        </span>
                                        <a class="movie_pay" style="background:#8dcd5d ">支付完成</a>
                                    </c:if>
                                </li>
                            </ul>
                            <div class="order_content hidden" id="hid_detail_${order.orderId}">
                                <div class="order_title">
                                    <span onclick="hideDetail()"></span>
                                    编号为 ${order.orderId} 的订单详情
                                </div>
                                <div class="order_tip clearfix">
                                    <a href="#" class="order_picture"><img
                                            src="${order.movieTbDTO.moviePoster}"
                                            width="271px" height="405px" alt=""/></a>
                                    <div class="order_list">
                                        <p class="order_word">
                                            <strong>￥<fmt:formatNumber value="${order.orderSumPrice/100}" pattern="0.00"/></strong>
                                            <c:if test="${order.orderStatus==1||order.orderStatus==0}">
                                                生成订单
                                            </c:if>
                                            <c:if test="${order.orderStatus==1}">
                                                取消订单
                                            </c:if>
                                        </p>
                                        <a href="#" class="order_key">${order.movieTbDTO.movieName}</a>
                                        <ul>
                                            <li>订单编号：${order.orderId}</li>
                                            <li>影城：${order.cinemaTbDTO.cinemaName}</li>
                                            <li>场次：${order.planTbDTO.planData}&nbsp;&nbsp;${order.planTbDTO.planTime}</li>
                                            <li>
                                                座位
                                                <c:forEach items="${order.orderSeatTbDTOS}" var="seat">
                                                    <span><strong>${seat.seatTbDTO.seatRow}排${seat.seatTbDTO.seatCol}座</span></strong>;
                                                </c:forEach>
                                            </li>
                                            <li class="mt_25">下单时间：${order.orderCreateTime}</li>
                                            <li>支付时间：${order.orderPayTime}</li>
                                            <li>付款方式：${order.orderPayMethod}</li>
                                            <li>取票手机：${order.userTel}</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <script type="text/javascript">
                function showDetail(id) {
                    jQuery('#hid_detail_' + id).show();
                    jQuery('.page_color').show();
                }

                function hideDetail() {
                    jQuery('.order_content').hide();
                    jQuery('.page_color').hide();
                }

                //取消订单
                function cancelOrder(orderId) {
                    showConfirm("是否确定取消订单？", function () {
                        $.ajax({
                            url: '${localPath}/buy/cancel_order',
                            type: "POST",
                            data: {'orderId': orderId},
                            success: function (data) {
                                if (data.status == 0) {
                                    location.href = '${localPath}/member/order';
                                } else {
                                    showEasyMsg(data.error);
                                }
                            },
                            dataType: "json"
                        });
                    });
                }

                //按照月份查询订单
                var allData = $("#monthSelectData").html();

                function monthSelect() {
                    if ($("#monthSelect").val() > 0) {
                        var month = $("#monthSelect").val();
                        $.ajax({
                            url: '/member/monthSelect',
                            data: {month: month},
                            success: function (data) {
                                $("#monthSelectData").html(data);
                            }
                        });
                    } else {
                        $("#monthSelectData").html(allData);
                    }
                }
            </script>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/commons/footer.jsp" %>
</body>
</html>
<script>
    $(document).ready(function () {
        $(".account_left ul a[tag=1]").css({"font-size": "14px", "font-weight": "bold"});
    });
</script>
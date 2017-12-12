<%--
  Created by IntelliJ IDEA.
  User: viphu
  Date: 2017-12-05
  Time: 0:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/commons/path_init.jsp" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" http-equiv="X-UA-Compatible" content="IE=edge"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/sheet.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/other.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/js/dialog/easydialog.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/style.css"/>
    <script type="text/javascript" src="${staticPath}/js/jquery-1.8.2.min.js"></script>
    <title>支付订单 - 上影影城</title>
</head>

<body class="address_area">
<script type="text/javascript" src="${staticPath}/js/dialog/easydialog.min.js"></script>
<script type="text/javascript" src="${staticPath}/js/script.js"></script>
<script type="text/javascript" src="${staticPath}/js/sheet.js"></script>
<script type="text/javascript" src="${staticPath}/js/order.js"></script>
<script type="text/javascript" src="${staticPath}/js/global.js?rand=1512402618"></script>
<%@include file="/WEB-INF/jsp/commons/header.jsp" %>
<div class="page_box pb_30 pt_30_new">
    <div class="background_color">
        <div class="select_top clearfix pay_position">
            <span>1.选择影片场次</span>
            <span>2.在线选座</span>
            <span class="select_current">3.支付订单</span>
            <span>4.影城取票观影</span>
        </div>

        <div class="pay_top clearfix">
            <div class="pay_left fl">
                <a href="#" class="pay_picture"><img
                        src="${order.movieTbDTO.moviePoster}" width="190px"
                        height="286px" alt=""/></a>
                <dl>
                    <dt><a href="#">${order.movieTbDTO.movieName}</a></dt>
                    <dd>订单号：${order.orderId}</dd>
                    <dd>影院：${order.cinemaTbDTO.cinemaName}</dd>
                    <dd>场次：${order.planTbDTO.planData}${order.planTbDTO.planTime}</dd>
                    w
                    <dd>时长：${order.movieTbDTO.moviePlayTime}分钟</dd>
                    <dd>语言制式：${order.movieTbDTO.movieLanguage}</dd>
                    <dd>影厅：${order.hallTbDTO.hallName}</dd>
                    <dd>
                        <span>座位：共${order.orderSeatTbDTOS.size()}张</span>
                        <c:forEach items="${order.orderSeatTbDTOS}" var="seat">
                    <span>
                        <font>（${seat.seatTbDTO.seatCol}排${seat.seatTbDTO.seatCol}座/￥<fmt:formatNumber value="${seat.osPrice/100}" pattern="0.00"/> )</font>
                    </span>
                        </c:forEach>
                    </dd>
                    <dd class="pay_money"><span>应付金额：
                ￥<fmt:formatNumber value="${order.orderSumPrice/100}" pattern="0.00"/>
            </span><strong>
                        ￥<fmt:formatNumber value="${order.orderSumPrice/100}" pattern="0.00"/>
                    </strong></dd>
                </dl>
            </div>
            <div class="pay_right fr">
                <ul class="pay_tip">
                    <li class="pay_phone"><p class="fr"></p>取票手机：15068762959</li>
                    <li class="pay_word">请在15分钟内完成付款，超时后系统将自动释放您的已选座位。</li>
                    <li class="pay_time clearfix">
                        <img src="${staticPath}/images/bank/pay_icon.png"/>
                        <p>
                            <span>剩余支付时间：</span>
                            <span id="endtime">
                    505</span>
                        </p>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="pay_line"></div>
    <div class="pay_content">
        <div class="pay_special">

            <div class="pay_title">
                <strong>使用优惠</strong>（用卡券抵消订单金额）
                <a style="color: #FFA200;font-size: 14px;margin-left: 30px;text-decoration: underline;"
                   href="http://www.sfccinema.com/activity/detail/55" target="_blank">使用说明</a>
            </div>
            <div class="pay_nav clearfix">
                <span>+</span>
                <strong>卡券</strong>
            </div>
            <div class="pay_information">
                <div class="pay_inter clearfix">
                    <input type="text" placeholder="请输入优惠劵(多个用“,”隔开)" id="coupon_ids" class="inter_number"/>
                    <input type="button" value="使用兑换券" onclick="tcoupons()" id="use_coupon"
                           class="inter_button cursor"/>
                </div>
                <div class="pay_use">您可以使用的卡券：</div>
                <ul class="pay_list clearfix">
                </ul>
            </div>

            <div class="pay_line"></div>
            <div class="pay_content">
                <div class="pay_key clearfix">
                    <p>还需支付：<strong>￥<span id="sale_count">
                <fmt:formatNumber pattern="0.00" value="${order.orderSumPrice/100}"/>
            </span></strong><font></font></p>
                    <span class="pay_method">支付方式</span>
                    <ul class="pay_detail">
                        <li class="detail_current">会员卡支付</li>
                        <li>支付平台</li>
                        <li>网银支付</li>
                        <!--<li>扫码支付</li>-->
                    </ul>
                </div>
                <div class="pay_member clearfix pay_main">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您还没有绑定影管会员卡，<a
                        class="bind_card_button" target="_blank" href="/member/vip?c=is">立即绑定</a></div>
                <div class="pay_bank clearfix pay_main hidden">
                    <p>
                        <input name="pay_method" type="radio" value="4"/>
                        <span>
            <img src="${staticPath}/images/bank/zhifubao.jpg" alt=""/>支付宝
        </span>
                    </p>
                </div>
                <div class="pay_bank clearfix pay_main hidden">
                    <p>
                        <input name="pay_method" type="radio" value="CCB"/>
                        <span>
            <img src="${staticPath}/images/bank/jianhang.png" alt=""/>建设银行
        </span>
                    </p>
                    <p>
                        <input name="pay_method" type="radio" value="ICBCB2C"/>
                        <span>
            <img src="${staticPath}/images/bank/gongshang.png" alt=""/>工商银行
        </span>
                    </p>
                    <p>
                        <input name="pay_method" type="radio" value="CMB"/>
                        <span>
            <img src="${staticPath}/images/bank/zhaohang.png" alt=""/>招商银行
        </span>
                    </p>
                    <p>
                        <input name="pay_method" type="radio" value="ABC"/>
                        <span>
            <img src="${staticPath}/images/bank/nonghang.png" alt=""/>农业银行
        </span>
                    </p>
                    <p>
                        <input name="pay_method" type="radio" value="CIB"/>
                        <span>
            <img src="${staticPath}/images/bank/xingye.png" alt=""/>兴业银行
        </span>
                    </p>
                    <p>
                        <input name="pay_method" type="radio" value="POSTGC"/>
                        <span>
            <img src="${staticPath}/images/bank/youzheng.png" alt=""/>中国邮政储蓄
        </span>
                    </p>
                    <p>
                        <input name="pay_method" type="radio" value="COMM"/>
                        <span>
            <img src="${staticPath}/images/bank/jiaotong.png" alt=""/>交通银行
        </span>
                    </p>
                    <p>
                        <input name="pay_method" type="radio" value="BOCB2C"/>
                        <span>
            <img src="${staticPath}/images/bank/zhonghang.png" alt=""/>中国银行
        </span>
                    </p>
                    <p>
                        <input name="pay_method" type="radio" value="BJBANK"/>
                        <span>
            <img src="${staticPath}/images/bank/beijing.png" alt=""/>北京银行
        </span>
                    </p>
                    <p>
                        <input name="pay_method" type="radio" value="BJRCB"/>
                        <span>
            <img src="${staticPath}/images/bank/beijingnongshang.png" alt=""/>北京农商银行
        </span>
                    </p>
                    <p>
                        <input name="pay_method" type="radio" value="GDB"/>
                        <span>
            <img src="${staticPath}/images/bank/guangfa.png" alt=""/>广发银行
        </span>
                    </p>
                    <p>
                        <input name="pay_method" type="radio" value="CMBC"/>
                        <span>
            <img src="${staticPath}/images/bank/minsheng.png" alt=""/>民生银行
        </span>
                    </p>
                </div>
                <div class="pay_bank clearfix pay_main hidden">
                    <div class="pay_two">支持支付宝扫码支付，请打开支付宝钱包客户端进行扫码。</div>
                    <p>
                        <input name="" type="radio" value=""/>
                        <span>
            <img src="${staticPath}/images/bank/pay_bank.png" alt=""/>支付宝
        </span>
                    </p>
                </div>
                <div class="pay_button">
                    <form  name="pay_button_href" method="post" id="pay_button_href" target="_blank"
                           onsubmit="return payOrder()">
                        <input type="hidden" name="pay_url" id="pay_url"/>
                        <input type="button" onclick="payOrder()" value="支付订单">
                    </form>
                </div>
            </div>
        </div>

        <input type="hidden" id="plan_id" value="${order.orderId}"/>
        <input type="hidden" id="order_money" value="${order.orderId}"/>
        <input type="hidden" id="coupon_ids_pay"/>
        <input type="hidden" id="coupon_price" value="0"/>
        <input type="hidden" id="coupon_count" value="0"/>
        <div class="pay_pop" id="pay_dialog_div" style="display: none;">
            <p class="tit_hint">马上就能完成购票啦！</p>
            <p>请在新开的支付网关支付网页面进行付款，完成本次交易。</p>
            <div class="center pt_30">
                <a class="pay_btn_gray" href="/member/index">已完成付款</a>
                <a class="pay_btn_gray" href="javascript:void(0)" id="pay_dialog_close">取消</a>
            </div>
        </div>
    </div>
</div>

<!-----------支付弹出框----------------->
<script type="text/javascript">
    var payParams = {
        couponCost: 0,
        userCost: 0,
        orderCost: '55.00',
        remainCost: '55.00',
        balance: '0.00',
        order_no: 'a1512402223144522161',
        coupon_ids: ""
    };

    jQuery("#pay_dialog_close").click(function () {
        jQuery('.page_color').hide();
        jQuery("#pay_dialog").hide();
        jQuery("#pay_dialog_div").hide();
    });
    // 会员卡支付
    jQuery("#card_pass,#card_list").click(function () {
        if (jQuery('input[name=coupon_checkbox]:checked').length > 0) {
            jQuery('#vip_pay').attr('checked', false);
            showEasyMsg('优惠劵不能与会员卡一起使用');
            jQuery('#coupon_ids').focus();
            return false;
        } else {
            jQuery("#vip_pay").attr('checked', true);
        }
    });

    jQuery('#search_card_price,#card_list').click(function () {
        jQuery("#vip_pay").attr('checked', true);
        var card_no = jQuery('#card_list').val();
        if ("" == card_no) {
            showEasyMsg("请先输入会员卡号");
            return false;
        } else {
            var preg = /^[0-9a-zA-Z]{1,50}$/;
            if (!preg.test(card_no)) {
                showEasyMsg("会员卡号不能含有特殊字符！");
                return false;
            }
        }
        countSale();
    });


    jQuery("input[name=pay_method]").click(function () {
        countSale();
    });

    //会员卡折扣价计算
    function countSale() {
        var pay_method = $("input:checked[name=pay_method]").val();
        var order_money = '55.00';
        var is_fenxiang_card = jQuery('#is_fenxiang_card').val();

        if (jQuery('#coupon_count').val() > 0 && pay_method == 28) {
            showMsg('会员卡不能与卡券混合使用');
            jQuery('#coupon_count').val(0);
            jQuery('#coupon_price').val(0);
            jQuery('input[name=coupon_checkbox]').attr("checked", false);
            jQuery('.current_color').removeClass('current_color');
            jQuery('.pay_current').removeClass('pay_current');
            $("#sale_count").html(order_money);
        }

        // 支付方式会员卡,计算折扣
        if (28 == pay_method || (pay_method != 28 && is_fenxiang_card == 1)) {
            var card_no = "";
            if (pay_method != 28 && is_fenxiang_card == 1) {
                card_no = jQuery('#fenxiang_card_list').val();
            } else {
                jQuery('#member_price').val(28);
                card_no = jQuery('#card_list').val();
            }

            var order_id = 'a1512402223144522161';
            var order_count = '1';
            $.ajax({
                url: '/member/GetCardPrice',
                data: {order_id: order_id, membercard_no: card_no},
                success: function (data) {
                    if (data['status'] == 0) {
                        jQuery('#member_price').val(4);
                        if (0 == data['vipPrice'] || !data['vipPrice']) {
                            jQuery('#sale_count').text(order_money);
                            payParams.orderCost = order_money;
                            payParams.remainCost = order_money;
                        } else {
                            var total_price = data['vipPrice'];
                            jQuery('#sale_count').text(total_price);
                            payParams.orderCost = total_price;
                            payParams.remainCost = total_price;
                        }
                    } else {
                        showMsg(data['error']);
                        return false;
                    }
                }
            });
        } else {
            if (jQuery('#coupon_count').val() <= 0) {
                jQuery('#sale_count').text(order_money - coupon_total_price);
                payParams.orderCost = order_money;
                payParams.remainCost = order_money;
            }
        }
    }

    //兑换码使用 - 选择
    function coupons(obj) {
        var coupon_ids = "";
        var plan_id = jQuery('#plan_id').val();
        var order_money = jQuery('#order_money').val();
        var checkeds_ago = jQuery('#coupon_' + obj).attr('checked');
        var order_id = 'a1512402223144522161';
        if ('checked' == checkeds_ago) {
            jQuery('#coupon_' + obj).attr('checked', false);
        } else {
            jQuery('#coupon_' + obj).attr('checked', true);
        }
        var checkeds = jQuery('#coupon_' + obj).attr('checked');
        jQuery('#coupon_li_' + obj).addClass('current_color');
        jQuery('#coupon_span_' + obj).addClass('pay_current');

        jQuery('input[name=coupon_checkbox]:checked').each(function () {
            coupon_ids += ',' + $(this).attr("couponid");
        });
        coupon_ids = coupon_ids.substr(1);
        if (coupon_ids == "") {
            payParams.coupon_ids = coupon_ids;
            $("#total_money").html("￥" + order_money);
            jQuery('#coupon_count').val(0);
            jQuery('#coupon_price').text(0);
            jQuery('#sale_count').text(order_money);
            jQuery('#coupon_' + obj).attr('checked', false);
            jQuery('#coupon_li_' + obj).removeClass('current_color');
            jQuery('#coupon_span_' + obj).removeClass('pay_current');
            return false;
        }

        // 使用优惠劵前，移除会员卡支付的选定状态
        if (jQuery('input[name=coupon_checkbox]:checked').length >= 0) {
            jQuery('#vip_pay').attr('checked', false);
// 		jQuery('#sale_count').text(order_money);
        }
        jQuery.ajax({
            url: '/database/couponcheck',
            data: {order_id: order_id, coupon_ids: coupon_ids},
            success: function (data) {
                if (data['status'] == 0) {
                    var coupon_price = jQuery('#coupon_price').val();
                    var coupon_count = jQuery('#coupon_count').val();
                    if ('checked' == checkeds) {
                        if (0 == parseFloat(jQuery('#sale_count').text())) {
                            jQuery('#coupon_' + obj).attr('checked', false);
                            jQuery('#coupon_li_' + obj).removeClass('current_color');
                            jQuery('#coupon_span_' + obj).removeClass('pay_current');
                            showMsg('优惠劵总额已大于订单金额');
                            return false;
                        } else {
                            jQuery('#sale_count').text((parseFloat(data['orderValue']) * 10000) / 10000);
                            coupon_price = (order_money * 100 - parseFloat(data['orderValue']) * 100) / 100;
                            coupon_count++;
                        }
                    } else {
                        jQuery('#sale_count').text((parseFloat(data['orderValue']) * 10000) / 10000);
                        jQuery('#coupon_' + obj).attr('checked', false);
                        jQuery('#coupon_li_' + obj).removeClass('current_color');
                        jQuery('#coupon_span_' + obj).removeClass('pay_current');
                        coupon_price = parseInt(coupon_price) - data['value'];
                        coupon_count--;

                    }
                    jQuery('#coupon_price').val(coupon_price);
                    jQuery('#coupon_count').val(coupon_count);
                } else {
                    showEasyMsg(data['error']);
                    jQuery('#coupon_' + obj).attr('checked', false);
                    jQuery('#coupon_li_' + obj).removeClass('current_color');
                    jQuery('#coupon_span_' + obj).removeClass('pay_current');
                    return false;
                }
            }
        });
    }

    //兑换码使用 - 输入
    function tcoupons() {
        var coupon_ids = jQuery('#coupon_ids').val();						// 输入的内容
        var plan_id = jQuery('#plan_id').val();								// 场次ID
        var order_money = jQuery('#order_money').val();						// 订单金额
        var check_obj = jQuery('input[name=coupon_checkbox]:checked');		// 选中的checkbox优惠劵
        var cou_id = coupon_ids.split(',');									// 逗号拆分输入的优惠劵
        var count = '1';
        var order_id = 'a1512402223144522161';
        if ("" == coupon_ids) {
            showEasyMsg('请输入优惠劵');
            return false;
        }
// 使用优惠劵前，移除会员卡支付的选定状态
        if (check_obj.length == 0) {
            jQuery('#vip_pay').attr('checked', false);
            jQuery('#sale_count').text(order_money);
        }

// 如果有选中的优惠劵，验证重复使用
        if (0 < check_obj.length) {
            for (var i = 0; i < cou_id.length; i++) {
                for (var j = 0; j < check_obj.length; j++) {
                    var check_couponid = jQuery(check_obj[i]).attr('couponid');
                    if (cou_id[i] == check_couponid) {
                        showMsg('优惠券' + check_couponid + '已经使用');
                        return false;
                    }
                }
            }
        }

// 绑定优惠劵,绑定成功后使用
        var hid_check = "";
        for (var i = 0; i < cou_id.length; i++) {
            jQuery.ajax({
                url: '/database/couponbind',
                data: {plan_id: plan_id, coupon_id: cou_id[i], count: count, order_id: order_id},
                success: function (rs) {
                    if (0 == rs['status']) {
                        var start = rs.coupon.startDate;
                        var end = rs.coupon.endDate;
                        hid_check = '<li onclick="coupons(' + rs.coupon.couponId + ')" id="coupon_li_' + rs.coupon.couponId + '">' +
                            '<input type="checkbox" class="none" name="coupon_checkbox" couponId="' + rs.coupon.couponId + '" couponValue="' + rs.coupon.couponValue + '" id="coupon_' + rs.coupon.couponId + '" />' +
                            '<p class="clearfix cursor">' +
                            '<span id="coupon_span_' + rs.coupon.couponId + '"></span>' +
                            '<font>兑换码：' + rs.coupon.couponId + '<br/>有效期：' + end.substr(0, 10) + '</font>' +
                            '</p>' +
                            '</li>';
                        jQuery('.pay_list').append(hid_check);
                    } else {
                        showEasyMsg(rs['error']);
                        return false;
                    }
                }
            });
        }
    }

    // 15分钟倒计时
    var CID = "endtime";
    if (window.CID != null) {
        var iTime = jQuery("#endtime").text();
        var Account;
        RemainTime();
    }

    function RemainTime() {
        var iMinute, iSecond;
        var sMinute = "", sSecond = "", sTime = "";
        if (iTime >= 0) {
            iMinute = parseInt((iTime / 60) % 60);
            if (iMinute >= 10) {
                sMinute = iMinute + "<font>分</font>";
            }
            else {
                sMinute = "0" + iMinute + "<font>分</font> ";
            }
            iSecond = parseInt(iTime % 60);
            if (iSecond >= 10) {
                sSecond = iSecond + "";
            }
            else {
                sSecond = "0" + iSecond;
            }

            sTime = "<i>" + sMinute + sSecond + "<font>秒</font></i>";


            if (iTime == 0) {
                cancelOrder('a1512402223144522161', 1);
                clearTimeout(Account);
                sTime = "<i style='color:red'>00:00</i>";
            }
            else {
                Account = setTimeout("RemainTime()", 1000);
            }
            iTime = iTime - 1;
        }
        jQuery("#" + CID).html(sTime);
    }

    // 取消订单
    function cancelOrder(orderId, type) {
        if ("undefined" == typeof(type)) {
            type = 0;
        }
        $.ajax({
            url: '/database/cancelorder',
            data: {order_id: orderId},
            success: function (data) {
                if (data['status'] == 0) {
                    if (1 == type) {
                        showEasyMsg('成功取消订单');
                        location.href = '/member/index';
                    } else {
                        showEasyMsgThree("成功取消订单，等待系统处理");
                        setTimeout(function () {
                            initSeat();
                        }, 3000);
                    }
                } else {
                    showEasyMsg(data['error']);
                }
            }
        });
    }
</script>
</body>
<%@include file="/WEB-INF/jsp/commons/footer.jsp" %>
</html>


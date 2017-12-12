var localPath='/front';
function toggleCon(i) {
    if (i == 0) {
        $(".pay_info").fadeOut('fast', function () {
            $(".pay_method").fadeIn('fast');
        })
    } else {
        $(".pay_method").fadeOut('fast', function () {
            $(".pay_info").fadeIn('fast');
        })
    }
}
function payOrder(){
    var plan_id = $("#plan_id").val();
    var pay_method = $("input:checked[name=pay_method]").val();
    if (pay_method == undefined || pay_method == '') {
        showEasyMsg("请选择支付方式");
    }
    showConfirm("是否确定支付订单", function () {
        $.ajax({
            url: localPath+"/buy/pay_order",
            type: 'POST',
            data: {
                'orderId': plan_id,
                'payMethod': pay_method
            },
            success: function (result) {
                if (result.status == 0) {
                    showEasyMsg("支付完成，正在跳转订单页面");
                    setTimeout(function () {
                        location.href = localPath+'/member/order?tag=1';
                    }, 2000);
                }
                else {
                    showEasyMsg(result.error);
                }
            },
            dataType: "json"
        });
    });
}
function payOrders() {

    var payData = {
        order_id: payParams.order_no
    };
    var url = jQuery("#pay_url").val();
    if (payParams.userCost > 0) payData['balance'] = payParams.userCost;
    if (payParams.couponCost > 0) payData['coupon_ids'] = payParams.coupon_ids;
    var pay_method = $("input:checked[name=pay_method]").val();

    var check_obj = jQuery('input[name=coupon_checkbox]:checked');		// 选中的checkbox优惠劵
    var coupon_ids = "";
    var sale_count = parseFloat(jQuery('#sale_count').text());	// 剩余支付金额
    if (0 < check_obj.length) {
        for (var j = 0; j < check_obj.length; j++) {
            var check_couponid = jQuery(check_obj[j]).attr('couponid');
            if (j == (check_obj.length - 1)) {
                coupon_ids += check_couponid;
            } else {
                coupon_ids += check_couponid + ',';
            }
        }
        payData['coupon_ids'] = coupon_ids;
    }
    if ("undefined" == typeof(pay_method) && sale_count > 0) {
        showEasyMsg('请选择支付方式');
        return false;
    } else {

        var yue_money = jQuery("#yue_pay_money").val();
        if ("" != yue_money && 'undefined' != typeof(yue_money)) {
            payData['balance'] = yue_money;
        }
        if (payParams.remainCost > 0) {
            if (pay_method != 28 && pay_method != 4) {
                payData['bank'] = pay_method;
                payData['pay_method'] = 4;
            } else {
                payData['pay_method'] = pay_method;
            }
        }
        var async = false;
        if (pay_method == 28) {
            async = true;
            var card_pay_no = jQuery("#card_list").val();
            if (card_pay_no == "") {
                showEasyMsg('请输入会员卡号');
                return false;
            } else if ('undefined' == typeof(card_pay_no)) {
                showEasyMsg('没有可用的会员卡');
                return false;
            } else {
                payData['membercard_no'] = card_pay_no;
            }
            var card_pay_pass = jQuery("input[name=member_pas]").val();
            if (card_pay_pass == "") {
                showEasyMsg('请输入会员卡密码');
                return false;
            }
            payData['membercard_password'] = card_pay_pass;
            $.ajax({
                url: '/database/GetCardInfo',
                data: {membercard_no: card_pay_no},
                success: function (data) {
                    if (data['status'] == 0) {
                        if ("" != data['members']) {
                            showConfirm('订单金额：' + payParams.orderCost + '<br/>会员卡余额：' + parseInt(data['member'].balance), function () {
                                $.ajax({
                                    url: '/database/orderconfirm',
                                    data: payData,
                                    async: async,
                                    success: function (data) {
                                        checkConfirmOrder(data);
                                    }
                                });
                            });
                        } else {
                            showEasyMsg('会员卡查询失败，请检查是否正确。');
                            return false;
                        }
                    } else {
                        showEasyMsg(data['error']);
                        return false;
                    }
                }
            });

            jQuery(".payment_button").val('处理中');
        } else {


            if (0 == sale_count) {
                jQuery(".payment_button").val('处理中');
                payData['bank'] = '';
                payData['pay_method'] = '13';
                jQuery("#pay_url").val('');
                async = true;
            }
            $.ajax({
                url: '/database/orderconfirm',
                data: payData,
                async: async,
                success: function (data) {
                    checkConfirmOrder(data);
                }
            });
        }

        if (pay_method != 28) {
            if ("" == jQuery("#pay_url").val()) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

}

function udateCouponTotalCost() {
    payParams.couponCost = 0;
    payParams.userCost = 0;
    payParams.coupon_ids = "";
    $(".coupon_item").each(function () {
        payParams.couponCost += parseFloat($(this).find(".coupon_value").html()) * scale;
        payParams.coupon_ids += ',' + $(this).find(".coupon_id").html();
    });
    if (payParams.couponCost > 0) payParams.coupon_ids = payParams.coupon_ids.substring(1);
    payParams.couponCost /= scale;
    $(".coupon_list_value").html(float(payParams.couponCost));
    $("#user_balance").html(float(payParams.balance));
    if (payParams.couponCost == 0) {
        $(".coupon_method .left .method_info").html('您可以使用券码抵扣部分金额');
        $(".coupon_method .right .method_info").html('未使用');
    } else {
        $(".coupon_method .left .method_info").html('<span class="red">抵扣: ' + float(payParams.couponCost) + '元</span>');
        $(".coupon_method .right .method_info").html('');
    }
    if ($(".useBalance:checked").length > 0) {
        if (payParams.orderCost * scale > (payParams.balance * scale + payParams.couponCost * scale)) {
            payParams.userCost = payParams.balance;
        } else {
            payParams.userCost = (payParams.orderCost * scale - payParams.couponCost * scale) / scale;
        }
    }
    payParams.remainCost = (payParams.orderCost * scale - payParams.couponCost * scale - payParams.userCost * scale) / scale;
    $("#remain_cost").html(float(payParams.remainCost));
    if (payParams.remainCost == 0) {
        $(".other_method,.pay_cost_notice").hide();
    } else {
        $(".other_method,.pay_cost_notice").show();
    }
}

$(function () {
    $(".blue_use_btn").on("click", function () {
        var coupon_ids = "";
        var coupon_id = $(".coupon_input").val();
        $(".coupon_input").val("");
        if (coupon_id == "") {
            showEasyMsg("请输入礼券或团购码！");
            return false;
        }
        if (payParams.remainCost <= 0) {
            showEasyMsg("您已经满足支付的金额，可以去支付！");
            return false;
        }
        $(".coupon_item").each(function () {
            coupon_ids += $(this).find(".coupon_id").html() + ",";
        });
        coupon_ids += coupon_id;
        $.ajax({
            url: '/database/couponcheck',
            data: {order_id: payParams.order_no, coupon_ids: coupon_ids},
            success: function (data) {
                if (data['status'] == 0) {
                    var couponLast = {};
                    for (i in data['coupons']) {
                        var coupon = data['coupons'][i];
                        if (coupon['couponId'] == coupon_id) couponLast = coupon;
                    }
                    if (couponLast['value'] > payParams.remainCost) {
                        couponLast['value'] = payParams.remainCost;
                    }
                    $(".coupon_use_list").append('<div class="coupon_item">\r\
                        <div class="coupon_id">' + couponLast['couponId'] + '</div>\r\
                        <a class="right cancel_btn" href="javascript:void(0)">\r\
                            可抵扣<span class="coupon_value">' + couponLast['value'] + '</span>元\r\
                            <span class="cancel_coupon"></span>\r\
                        </a>\r\
                        <div class="clear"></div>\r\
                    </div>');
                    udateCouponTotalCost();
                } else {
                    showEasyMsg(data['error']);
                }
            }
        })
    });
    $(".coupon_use_list").on("click", ".cancel_btn", function () {
        $(this).parent().slideUp('fast', function () {
            $(this).remove();
            udateCouponTotalCost();
        })
    });
    $("#check_btn_1").click(function () {
        udateCouponTotalCost();
    });
//    $(".payment_button").click(function(){
//        payOrder();
//    })
})
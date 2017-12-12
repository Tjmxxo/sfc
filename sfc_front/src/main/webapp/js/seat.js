var seatQuery;
var seatParams = {
    rows: 0,
    cols: 0
};
var localPath = "/front";

function initSeat() {
    if (seatQuery) seatQuery.abort();
    $(".seat_btn_list").html("");
    seatQuery = $.ajax({
        url: '/movie/seatquery',
        data: {plan_id: planId},
        success: function (data) {
            console.log(data);
            if (data['status'] == 0) {
                var seatText = "";
                var seatBar = "";
                var seatBar1 = "";
                seatParams.rows = data['rows'];
                seatParams.cols = data['cols'];
                for (var x = 1; x <= data['cols']; x++) {
                    seatBar1 += "<span>" + x + "</span>";
                }
                var width = data['cols'] * 30 + 50;
                for (var i = 1; i <= data['rows']; i++) {
                    seatBar += "<span>" + i + "</span><br/>";
                    if (width > 737) {
                        seatText += "<div style='width:" + width + "px;margin: 0 auto;'><p class='line_div'>" + i + "排</p><dl >";
                    } else {
                        seatText += "<div style='width:" + width + "px;margin: 0 auto;'><p class='line_div'>" + i + "排</p><dl >";
                    }
                    for (var j = 1; j <= data['cols']; j++) {
                        var seat = data['seats'][i + "_" + j];
                        var title = "";
                        if ('none_seat' != seat['seatCss']) {
                            title = seat['seatRow'] + '排' + seat['seatCol'] + '座';
                        }
                        seatText += '<dd id="s_' + i + "_" + j + '" r="' + i + '" c="' + j + '" class="' + seat['seatCss'] + ' seat" seatType="' + seat['seatType'] + '" seatState="' + seat['seatStatus'] + '" seatCol="' + seat['seatCol'] + '" seatRow="' + seat['seatRow'] + '" seatNo="' + seat['seatNo'] + '" seatPieceNo="' + seat['seatAreaId'] + '" title="' + title + '" wen="' + seat['seatRow'] + '排' + seat['seatCol'] + '座' + '"><span class="seat_one">' + seat['seatCol'] + '</span></dd>';
                    }
                    seatText += "</dl></div>";
                }
                $(".select_seat").html(seatText);
                jQuery("#seat_title").remove();
                $(".seat_map").append('<p id="seat_title" class="font_12 blue pt_20 pl_20">*请选择同排连续座位，不要空出单个座位，最多可选择6个座位。</p>');
                $(".seat_sell").empty();
//                $(".seat_map").append('<div class="seat_bar">'+seatBar+'</div><p class="font_12 blue pt_20 pl_20">*请选择同排连续座位，不要空出单个座位，最多可选择6个座位。</p>');
//                $("#seat_map").prepend(line_div);
                //$(".seat_map p").width(data['cols']*48);
                //$(".seat_map").mCustomScrollbar({horizontalScroll:true});
            } else {

            }

        }
    })
}

function getseatstatus(row, col, rowNum, columnNum) {
    if (col < 1 || row < 1 || col > columnNum || row > rowNum) {
        return -1;
    } else {
        var id = '#s_' + row + '_' + col;
        if ($(id).attr("class").indexOf("none_seat") >= 0) {
            return -1;
        } else if ($(id).attr("class").indexOf("free_seat") >= 0 || $(id).attr("class").indexOf("red_seat") >= 0) {
            return 0;
        } else if ($(id).attr("class").indexOf("lock_seat") >= 0) {
            return 1;
        } else {
            return 2;
        }
    }
}

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
                    showMsg('成功取消订单', function () {
                        location.href = '/member/index/9';
                    });
                } else {
                    showEasyMsgThree("成功取消订单，等待系统处理");
                    setTimeout(function () {
                        initSeat();
                    }, 3000);
                }
            } else {
                showMsg(data['error']);
            }
        }
    });
}

function cancelActivity() {
    var isCancel = false;
    $.ajax({
        async: false,
        url: '/database/cancelactivity',
        data: {},
        success: function (data) {
            if (data['status'] == 0) {
                isCancel = true;
            } else {
                isCancel = false;
            }
        }
    });
    return isCancel;
}

function hasemptyseat() {
    var rowNum = seatParams.rows;
    var columnNum = seatParams.cols;
    for (var row = 1; row <= rowNum; row++) {
        for (var col = 1; col <= columnNum; col++) {
            var id = '#s_' + row + '_' + col;
            if ($(id).attr("class").indexOf("choose_seat") >= 0) {
                var originL = col;
                var originR = col;
                for (col; col <= columnNum; col++) {
                    var idd = '#s_' + row + '_' + col;
                    if ($(idd).attr("class").indexOf("choose_seat") >= 0) {
                        originR = col;
                    } else break;
                }
                /*
                 同一排的座位
                 1 左或右挨着已选座位或者边界，ok ！
                    左或右不可能挨着自选
                    左或右加1如果挨着自选，则中间隔的已选或者没座
                 2 左右挨着空座，左右隔一个不挨着自选，已选，边界
                */
                var l1State = getseatstatus(row, originL - 1, rowNum, columnNum);
                var l2State = getseatstatus(row, originL - 2, rowNum, columnNum);
                var r1State = getseatstatus(row, originR + 1, rowNum, columnNum);
                var r2State = getseatstatus(row, originR + 2, rowNum, columnNum);
                var SeatViewStateNone = -1;
                var SeatViewStateNormal = 0;//正常
                var SeatViewStateUnavailable = 1;//售出
                var SeatViewStateSelected = 2;//选中
                if (l1State == SeatViewStateUnavailable || l1State == SeatViewStateNone
                    || r1State == SeatViewStateUnavailable || r1State == SeatViewStateNone) {
                    if (l2State == SeatViewStateSelected
                        && l1State != SeatViewStateNone
                        && l1State != SeatViewStateUnavailable) {
                        return true;
                    }
                    if (r2State == SeatViewStateSelected
                        && r1State != SeatViewStateNone
                        && r1State != SeatViewStateUnavailable) {
                        return true;
                    }
                } else {
                    if (l2State != SeatViewStateNormal || r2State != SeatViewStateNormal) {
                        return true;
                    }
                }
            }
        }
    }
    return false;
}

function hasemptyseat1() {
    var rowNum = seatParams.rows;
    var columnNum = seatParams.cols;
    var hasempty = false;
    if ($(".seat[class^=choose_seat]").length > 1) {
        for (var row = 1; row <= rowNum; row++) {
            for (var col = 1; col <= columnNum; col++) {
                var id = '#s_' + row + '_' + col;
                if ($(id).attr("class").indexOf("choose_seat") >= 0 || $(id).attr("class").indexOf("choose_seat_l") >= 0 || $(id).attr("class").indexOf("choose_seat_r") >= 0) {
                    var up = 0, down = 0, left = 0, right = 0;
                    for (var x = row - 1; x > 0; x--) {
                        if ($('#s_' + x + '_' + col).hasClass("none_seat")) continue;
                        if ($('#s_' + x + '_' + col).hasClass("choose_seat") || $('#s_' + x + '_' + col).hasClass("choose_seat_l") || $('#s_' + x + '_' + col).hasClass("choose_seat_r")) {
                            up = 1;
                        }
                        break;
                    }
                    for (var x = row + 1; x <= rowNum; x++) {
                        if ($('#s_' + x + '_' + col).hasClass("none_seat")) continue;
                        if ($('#s_' + x + '_' + col).hasClass("choose_seat") || $('#s_' + x + '_' + col).hasClass("choose_seat_l") || $('#s_' + x + '_' + col).hasClass("choose_seat_r")) {
                            down = 1;
                        }
                        break;
                    }
                    for (var x = col - 1; x > 0; x--) {
                        alert('#s_' + row + '_' + x);
                        if ($('#s_' + row + '_' + x).hasClass("none_seat")) continue;
                        if ($('#s_' + row + '_' + x).hasClass("choose_seat") || $('#s_' + row + '_' + x).hasClass("choose_seat_l") || $('#s_' + row + '_' + x).hasClass("choose_seat_r")) {
                            left = 1;
                        }
                        break;
                    }
                    for (var x = col + 1; x <= columnNum; x++) {
                        alert('#s_' + row + '_' + x);
                        if ($('#s_' + row + '_' + x).hasClass("none_seat")) continue;
                        if ($('#s_' + row + '_' + x).hasClass("choose_seat") || $('#s_' + row + '_' + x).hasClass("choose_seat_l") || $('#s_' + row + '_' + x).hasClass("choose_seat_r")) {
                            right = 1;
                        }
                        break;
                    }

                    if (!up && !down && !left && !right) {
                        hasempty = true;
                        break;
                    }

                }
            }
        }
    }
    return hasempty;
}

$(function () {
    $("#buy_next").click(function () {
        var seatNo = "", seatInfo = "";
        var mobile = $("#order_mobile").val();
        var planId = $('#seat_map').attr("planId")
        if ($(".seat[class^=choose_seat]").length == 0) {
            showEasyMsg("请先选择观影座位！");
            return false;
        }
        if (hasemptyseat()) {
            showEasyMsg("请不要留下单个座位！");
            return false;
        }
        if ("" == mobile) {
            showEasyMsg("请先登陆后再购买！");
            return false;
        }
        if (!testMobile(mobile)) {
            showEasyMsg("请输入正确的手机号码！");
            return false;
        }
        $(".seat[class^=choose_seat]").each(function () {
            seatNo += ',' + $(this).attr("seatno");
            seatInfo += ',' + $(this).attr("seatpieceno") + '_' + $(this).attr("seatrow") + '_' + $(this).attr("seatcol");
        });
        seatNo = seatNo.substr(1);
        seatInfo = seatInfo.substr(1);
        var params = {userTel: mobile, seatId: seatNo, planId: planId};
        $.ajax({
            url: localPath + "/buy/check_order",
            success: function (data) {
                if (data == 0) {
                    var params = new Array();
                    params['seatId'] = seatNo;
                    params['userTel'] = mobile;
                    params['planId'] = planId;
                    post(localPath + "/buy/submit_order", params);
                }
                else if (data == -1) {
                    showEasyMsg("请先登陆再购买");
                } else if (data == -2) {
                    showEasyMsg("获取订单失败");
                } else if (data > 0) {
                    easyDialog.open({
                        container: {
                            content: "存在未完成订单，请先完成或取消订单",
                            yesFn: function () {
                                location.href = localPath + "/member/order?tag=0";
                            },
                            yesText: '去支付/取消订单',
                            /*noFn:function(){
                                cancelOrder(data['unpaid']['orderId']);
                                return true;
                            },*/
                        }
                    });
                } else {
                    location.href = localPath + '/user/login';
                }
            }
        });
        $.ajax({
            url: '/database/createorder',
            data: params,
            success: function (data) {
                if (data['status'] == 0) {
                    location.href = "/order/pay/order_id/" + data['order']['orderId'];
                } else if (data['status'] == -99) {
                    location.href = "/user/login";
                } else if (data['status'] == -8003) {
                    easyDialog.open({
                        container: {
                            content: data['error'],
                            yesFn: function () {
                                location.href = "/order/pay/order_id/" + data['unpaid']['orderId'] + "?mobile=" + mobile;
                            },
                            yesText: '去支付',
                            noFn: function () {
                                cancelOrder(data['unpaid']['orderId']);
                                return true;
                            },
                            noText: "取消订单"
                        }
                    });
                } else if (data['status'] == -8004) {
                    easyDialog.open({
                        container: {
                            content: data['error'],
                            yesFn: function () {
                                location.href = "/order/pay/order_id/" + data['unpaid']['orderId'] + "?mobile=" + mobile;
                            },
                            yesText: '继续支付'
                        }
                    });
                } else if (data['status'] == -8002) {
                    easyDialog.open({
                        container: {
                            content: data['error'],
                            yesFn: function () {
                                $("[name=seat_form]").submit();
                            },
                            yesText: '重新支付',
                            noFn: function () {
                                if (cancelActivity()) {
                                    $("[name=seat_form]").submit();
                                }
                                return true;
                            },
                            noText: "普通支付"
                        }
                    });
                } else {
                    showMsg(data['error']);
                }
                return false;
            }
        });
        return false;
    });

    // 动态价格计算
    function planPrice() {
        var plan_price = jQuery('#plan_price').val();
        var plan_count = jQuery(".seat[class^=choose_seat]").length;
        var total_movie_price = plan_count * parseFloat(plan_price);
        jQuery("#total_price").text('￥' + total_movie_price.toFixed(2));
    }

    $("#seat_map").on("click", '.seat[seatState!=-1]', function () {
        if ($(this).hasClass("free_seat")) {
            $(this).attr("class", $(this).attr("class").replace("free", "choose"));
        } else if ($(this).hasClass("choose_seat")) {
            $(this).attr("class", $(this).attr("class").replace("choose", "free"));
        } else if ($(this).hasClass("lock_seat")) {
//            showMsg("该座位已经被其他人抢先一步下单，请选择其它座位！");
            return false;
        }
        if ($(this).attr("seatType") > 0) {
            if ($(this).attr("class").indexOf("choose") >= 0) {
                var lover_id = "";
                if ($(this).attr("class").indexOf("seat_l") >= 0) {
                    var lover_id = '#s_' + $(this).attr("r") + "_" + (parseInt($(this).attr("c")) + 1);
                } else {
                    var lover_id = '#s_' + $(this).attr("r") + "_" + (parseInt($(this).attr("c")) - 1);
                }
                $(this).attr("class", $(this).attr("class").replace("choose", "free"));
                $(lover_id).attr("class", $(lover_id).attr("class").replace("choose", "free"));
            } else {
                var lover_id = "";
                if ($(this).attr("class").indexOf("seat_l") >= 0) {
                    var lover_id = '#s_' + $(this).attr("r") + "_" + (parseInt($(this).attr("c")) + 1);
                } else {
                    var lover_id = '#s_' + $(this).attr("r") + "_" + (parseInt($(this).attr("c")) - 1);
                }
                $(this).attr("class", $(this).attr("class").replace("free", "choose"));
                $(lover_id).attr("class", $(lover_id).attr("class").replace("free", "choose"));
            }
        }
        if ($(".seat[class^=choose_seat]").length > 6) {
            showEasyMsg("一次最多选6个座位！");
            $(this).click();
            return false;
        }
        planPrice();
        $(".seat_sell").html("");
        $(".seat[class^=choose_seat]").each(function () {
            var seat_str = '<span style="cursor:pointer;" onclick="$(\'#' + $(this).attr("id") + '\').click();">' + $(this).attr('wen') + ' </span>';
            $(".seat_sell").append(seat_str);
        })
    })
})
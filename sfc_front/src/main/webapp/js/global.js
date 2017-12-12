jQuery.ajaxSetup({
    beforeSend: function () {
        $(".ko_loading").show();
    },
    complete: function () {
        $(".ko_loading").hide();
    }
});
var scale = 100;
var orderStatusList = {
    1: '等待付款',
    3: '付款成功',
    4: '购买成功',
    5: '购买失败',
    6: '支付失败',
    7: '已经过期'
};

function showMsg(msg, fn) {
    var fun = fn || function () {
    };
    easyDialog.open({
        container: {
            content: msg,
            yesFn: fun
        }
    });
}

function showEasyMsgThree(msg) {
    easyDialog.open({
        container: {
            content: msg
        },
        autoClose: 3000
    });
}

function showEasyMsg(msg) {
    easyDialog.open({
        container: {
            content: msg
        },
        autoClose: 1000
    });
}

function showConfirm(msg, func) {
    var fn = func || function () {
    };
    easyDialog.open({
        container: {
            content: msg,
            yesFn: fn,
            yesText: '确定',
            noFn: true,
            noText: "取消"
        }
    });
}

function getLabel(label) {
    label += "";
    var p = parseInt(label.substring(0, 1));
    switch (p) {
        case 1:
            return 'seat_label';
            break;
        case 2:
        case 4:
            return 'coupon_label';
            break;
        case 9:
            return 'plan_label';
            break;
        default:
            return 'seat_label';
            break;
    }
}

//设备信息
var device = {
    headerH: 70,
    footerH: 90
};

function updateLocation() {
    if (navigator.geolocation && $.cookie("komovie_lng") == "undefined") {
        var options = {enableHighAccuracy: true, maximumAge: 180000, timeout: 5000};
        navigator.geolocation.getCurrentPosition(function (position) {
            $.cookie("komovie_lng", position.coords.longitude);
            $.cookie("komovie_lat", position.coords.latitude);
            location.reload();
        }, function (error) {
        }, options);
        navigator.geolocation.watchPosition(function (position) {
            $.cookie("komovie_lng", position.coords.longitude);
            $.cookie("komovie_lat", position.coords.latitude);
        }, function (error) {
        });
    }
}

function initDevice() {
    device.width = $(window).width();
    device.height = $(window).height();
    device.containerH = device.height - device.headerH - device.footerH;
    $(".content").css({"padding-bottom": device.footerH});
}

$(function () {
    initDevice();
    $("input[name=mobile]").attr("maxLength", 11);
})

function float(num) {
    var re = /^\d+$/;
    var re1 = /^\d+\.\d+$/;
    var re2 = /^\d+\.\d$/;
    if (re.test(num + "") || re1.test(num + "")) {
        num1 = (Math.round(num * scale) / scale) + "";
        if (re.test(num1)) {
            return num1 + ".00";
        } else if (re2.test(num1)) {
            return num1 + '0';
        } else {
            return num1;
        }
    } else {
        return false;
    }
}

function testMobile(mobile) {
    var preg = /^1\d{10}$/;
    return preg.test(mobile);
}

function needLogin() {
    if (!isLogin) location.href = '/user/login';
    return false;
}

function orderCallback(jsonStr) {
    var result = $.parseJSON(jsonStr);
    if (result['RespCode'] == "0000") {
        location = '/order/' + result['ExtraCommonParam'];
    } else {
        showEasyMsg(result['RespDesc']);
    }
}

function checkConfirmOrder(data) {
    jQuery(".payment_button").val('确认支付');
    if (data['status'] == 0) {
        if (data['pay_method'] != 28) {
            if (data['payInfo']['payUrl'] != '') {
                jQuery('.page_color').show();
                jQuery("#pay_dialog").show();
                jQuery("#pay_dialog_div").show();
            }
        }
        if (data['payInfo']['payUrl'] == '') {
            location.href = '/order/view/order_id/' + data['order_id'];
        } else {
            if (28 == data['pay_method']) {
                location.href = '/order/view/order_id/' + data['order_id'];
            } else if (4 == data['pay_method']) {
                jQuery("#pay_url").val(data['payInfo']['payUrl']);
            } else {
                location.href = '/order/view/order_id/' + data['order_id'];
            }
        }
    } else {
        showEasyMsg(data['error']);
        return false;
    }
}

$(function () {
    $(".sel_count_minus").click(function () {
        var input = $(this).next('.sel_count_input');
        var min = $(input).attr('min') == undefined ? 1 : parseInt($(input).attr('min'));
        var max = $(input).attr('max') == undefined ? 1 : parseInt($(input).attr('max'));
        var cur = parseInt($(input).html());
        var next = cur - 1 > min ? cur - 1 : min;
        if (next == cur) {
            showEasyMsg("最少选择" + cur + "张！");
            return false;
        }
        $(".sel_count_input").html("0张");
        $(input).html(next + "张");
    })
    $(".sel_count_plus").click(function () {
        var input = $(this).prev('.sel_count_input');
        var min = $(input).attr('min') == undefined ? 1 : parseInt($(input).attr('min'));
        var max = $(input).attr('max') == undefined ? 1 : parseInt($(input).attr('max'));
        var cur = parseInt($(input).html());
        var next = cur + 1 > max ? max : cur + 1;
        if (next == cur) {
            showEasyMsg("最多选择" + cur + "张！");
            return false;
        }
        $(".sel_count_input").html("0张");
        $(input).html(next + "张");
    })
})

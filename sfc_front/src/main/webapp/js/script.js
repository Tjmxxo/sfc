/****************************************************
 *  网站公用JS函数文件                                *
 *  @author wxl                                        *
 ****************************************************/

/**
 * 给ajax绑定loading
 */
jQuery.ajaxSetup({
    beforeSend: function () {
        $(".ko_loading").show();
    },
    complete: function () {
        $(".ko_loading").hide();
    }
});

/**
 * 删除左右两端的空格
 * @param str
 * @returns
 */
function trim(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

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

/**
 * 手机号验证
 * @param mobile
 * @returns
 */
function testMobile(mobile) {
    var preg = /^(13|14|15|17|18|)\d{9}$/;
    return preg.test(mobile);
}

/**
 * 邮箱验证
 * @param email
 * @returns
 */
function testEmail(email) {
    var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    return reg.test(email);
}

/**
 * 身份证号验证
 * @param num
 * @returns {Boolean}
 */
function testCard(num) {
    num = num.toUpperCase();
    //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。
    if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num))) {
        return false;
    }
    //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
    //下面分别分析出生日期和校验位
    var len, re;
    len = num.length;
    if (len == 15) {
        re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/);
        var arrSplit = num.match(re);

        //检查生日日期是否正确
        var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]);
        var bGoodDay;
        bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
        if (!bGoodDay) {
            return false;
        }
        else {
            //将15位身份证转成18位
            //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
            var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
            var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
            var nTemp = 0, i;
            num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6);
            for (i = 0; i < 17; i++) {
                nTemp += num.substr(i, 1) * arrInt[i];
            }
            num += arrCh[nTemp % 11];
            return true;
        }
    }
    if (len == 18) {
        re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/);
        var arrSplit = num.match(re);

        //检查生日日期是否正确
        var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]);
        var bGoodDay;
        bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4]));
        if (!bGoodDay) {
            return false;
        }
        else {
            //检验18位身份证的校验码是否正确。
            //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。
            var valnum;
            var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2);
            var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2');
            var nTemp = 0, i;
            for (i = 0; i < 17; i++) {
                nTemp += num.substr(i, 1) * arrInt[i];
            }
            valnum = arrCh[nTemp % 11];
            if (valnum != num.substr(17, 1)) {
                return false;
            }
            return true;
        }
    }
    return false;
}

/**
 * 底部影院切换
 * @param num
 * @param type
 */
function chang_cinema(num, type) {
    jQuery("#cinema_div_" + num).show();
    if ("min" == type) {
        jQuery("#cinema_div_" + (num + 1)).hide();
    } else {
        jQuery("#cinema_div_" + (num - 1)).hide();
    }
}


//$(window).load(function(){
//    var new_height=$("#login_height").height();
//    $("#login_left").height(new_height);
//});


$(window).load(function () {
    var new_height = $("#login_height").height();
    var num = Math.floor(new_height / 18);
    var number = num * 18;
    $("#login_left").height(number);
});


//登录、注册、忘记密码输入状态和默认显示状态颜色不一样
$(function () {
    $(".content_clear input[type='text'],.content_clear input[type='password']").bind({
        click: function () {
            $(this).css("color", "#222222");
        },
        blur: function () {
            if ($(this).val() != "") {
                $(this).css("color", "#222222");
            } else {
                $(this).css("color", "#cac9c7");
            }

        }
    })
})
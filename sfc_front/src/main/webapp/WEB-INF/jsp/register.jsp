<%--
  Created by IntelliJ IDEA.
  User: viphu
  Date: 2017-11-30
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/commons/path_init.jsp"/>
<head lang="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/css/sheet.css"/>
    <link rel="stylesheet" type="text/css" href="${staticPath}/js/dialog/easydialog.css"/>
    <script type="text/javascript" src="${staticPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${staticPath}/js/post.js"></script>
    <title>用户注册-上影电影网</title>
</head>
<body>
<script type="text/javascript" src="${staticPath}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${staticPath}/js/dialog/easydialog.min.js"></script>
<script type="text/javascript" src="${staticPath}/js/script.js"></script>
<div class="page_content">
    <div class="login_content">
        <div class="login_top clearfix"><a href=""><span></span></a><strong>欢迎注册</strong></div>
        <div class="login_detail clearfix" id="login_height">
            <form action="">
                <div class="login_left fl content_clear" id="login_left">
                    <ul>
                        <li class="register_title">请使用手机号码注册上影电影网！</li>
                        <li class="clearfix">
                            <div class="login_inter fl">
                                <span class="inter_left inter_left_current"></span>
                                <span class="inter_middle inter_middle_current">
                                <font class="register_width"></font>
                                <input style="display:none"><!-- for disable autocomplete on chrome -->
                                <input type="text" name="mobile" id="mobile" placeholder="手机号码" autocomplete="off">
                                </span>
                                <span class="inter_right inter_right_current"></span>
                            </div>
                            <div class="fl ml_17">
                                <div class="login_inter fl">
                                    <span class="inter_left"></span>
                                    <span class="inter_middle" style="width: 149px!important;"><input type="text"
                                                                                                      name="valid_code"
                                                                                                      id="valid_code"
                                                                                                      placeholder="输入右侧验证码"
                                                                                                      class="width_141"></span>
                                    <span class="inter_right"></span>
                                </div>
                                <img alt="点击换图" title="点击换图" class="login_word" style="cursor:pointer" id="valid_img"
                                     testLimit="1" src=""/> <span class="login_change" id="valid_img"
                                                                  style="cursor: pointer;">换一张</span>
                            </div>
                        </li>
                        <li class="register_click"><input type="button" id="reg_valid" class="get_code" typ="1"
                                                          value="点击获取短信验证码">
                            <span>稍后您的手机将收到短信验证码</span>60秒后可重发
                        </li>
                        <li class="clearfix">
                            <div class="login_inter fl">
                                <span class="inter_left"></span>
                                <span class="inter_middle"><font class="register_password"></font><input type="text"
                                                                                                         id="valid"
                                                                                                         name="valid"
                                                                                                         placeholder="短信验证码"></span>
                                <span class="inter_right"></span>
                            </div>
                            <span class="register_hand">您收到的手机短信验证码</span>
                        </li>
                        <li>
                            <div class="login_inter fl">
                                <span class="inter_left"></span>
                                <span class="inter_middle">
                                <input style="display:none"><!-- for disable autocomplete on chrome -->
                                <input type="password" name="pass" id="pass" autocomplete="off">
                                </span>
                                <span class="inter_right"></span>
                            </div>
                            <span class="register_hand">设置登录密码</span>
                        </li>
                        <div class="clear"></div>
                        <li>
                            <div class="login_inter fl">
                                <span class="inter_left"></span>
                                <span class="inter_middle"><input type="password" name="re_pass" id="re_pass"/></span>
                                <span class="inter_right"></span>
                            </div>
                            <span class="register_hand">确认登录密码</span>
                        </li>
                        <div class="clear"></div>
                        <li class="login_select">
                            <input type="checkbox" name="" value="" id="xieyi" checked="checked"><label for="xieyi">我接受服务协议表明您已经阅读并同意接受上影电影网之&nbsp;<a
                                href="javascript:void(0)" class="register_service" id="ystk">服务协议</a></label>
                        </li>
                        <li class="login_button"><input type="button" class="cursor" id="reg_button" value="立即注册"></li>
                    </ul>
                </div>
            </form>
            <div class="login_right fl">
                <ul>
                    <li class="login_none">已经有上影电影网账号？</li>
                    <li class="login_register"><input type="button" class="cursor" id="login" value="立即登录"></li>
                    <!--                    <li class="register_three">使用第三方账号登录</li>-->
                    <!--                    <li class="login_method register_method clearfix">-->
                    <!--                        <a href="#"></a>-->
                    <!--                        <a href="#" class="login_xin"></a>-->
                    <!--                        <a href="#" class="login_pay right_none"></a>-->
                    <!--                    </li>-->
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="bg_black">
    <div class="wrap">
        <div class="f_12 white">
            <div class="login_bottom_copyright left ">
                <a href="site/help/21" target="_blank">隐私保护</a>
                <span>|</span> <a href="site/help/19" target="_blank">联系我们</a>
                <span>|</span> <a href="site/help/18" target="_blank">关于我们</a>
                <span>|</span> <a href="site/help/20" target="_blank">团体购票</a>
            </div>
            <div class="line_46 right">沪ICP备15055478号 Copyright © 2015 sfccinema.com 版权所有</div>
            <div class="clear"></div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        if (!placeholderSupport()) {   // 判断浏览器是否支持 placeholder
            $('[placeholder]').focus(function () {
                var input = $(this);
                if (input.val() == input.attr('placeholder')) {
                    input.val('');
                    input.removeClass('placeholder');
                }
            }).blur(function () {
                var input = $(this);
                if (input.val() == '' || input.val() == input.attr('placeholder')) {
                    input.addClass('placeholder');
                    input.val(input.attr('placeholder'));
                }
            }).blur();
        }
    });

    function placeholderSupport() {
        return 'placeholder' in document.createElement('input');
    }

    // 绑定回车事件
    document.onkeyup = function (e) {
        e = e || window.event;
        if (e.keyCode == 13) {
            var target = e.target || e.srcElment;//srcElment针对IE
            console.log(target.id);
            if (target.id == 're_pass') {
                $('#reg_button').trigger("click");
            }
        }
    };

    $(document).ready(function () {

        jQuery('#mobile').focus(function () {
            $(this).css("color", "#222222");
            jQuery('.inter_middle').attr('class', 'inter_middle');
            jQuery('.inter_left').attr('class', 'inter_left');
            jQuery('.inter_right').attr('class', 'inter_right');
            jQuery('#mobile').parent().attr('class', 'inter_middle inter_middle_current');
            jQuery('#mobile').parent().prev().attr('class', 'inter_left inter_left_current');
            jQuery('#mobile').parent().next().attr('class', 'inter_right inter_right_current');
        });
        jQuery('#valid_code').focus(function () {
            $(this).css("color", "#222222");
            jQuery('.inter_middle').attr('class', 'inter_middle');
            jQuery('.inter_left').attr('class', 'inter_left');
            jQuery('.inter_right').attr('class', 'inter_right');
            jQuery('#valid_code').parent().attr('class', 'inter_middle inter_middle_current');
            jQuery('#valid_code').parent().prev().attr('class', 'inter_left inter_left_current');
            jQuery('#valid_code').parent().next().attr('class', 'inter_right inter_right_current');
        });
        jQuery('#valid').focus(function () {
            $(this).css("color", "#222222");
            jQuery('.inter_middle').attr('class', 'inter_middle');
            jQuery('.inter_left').attr('class', 'inter_left');
            jQuery('.inter_right').attr('class', 'inter_right');
            jQuery('#valid').parent().attr('class', 'inter_middle inter_middle_current');
            jQuery('#valid').parent().prev().attr('class', 'inter_left inter_left_current');
            jQuery('#valid').parent().next().attr('class', 'inter_right inter_right_current');
        });
        jQuery('#pass').focus(function () {
            $(this).css("color", "#222222");
            jQuery('.inter_middle').attr('class', 'inter_middle');
            jQuery('.inter_left').attr('class', 'inter_left');
            jQuery('.inter_right').attr('class', 'inter_right');
            jQuery('#pass').parent().attr('class', 'inter_middle inter_middle_current');
            jQuery('#pass').parent().prev().attr('class', 'inter_left inter_left_current');
            jQuery('#pass').parent().next().attr('class', 'inter_right inter_right_current');
        });
        jQuery('#re_pass').focus(function () {
            $(this).css("color", "#222222");
            jQuery('.inter_middle').attr('class', 'inter_middle');
            jQuery('.inter_left').attr('class', 'inter_left');
            jQuery('.inter_right').attr('class', 'inter_right');
            jQuery('#re_pass').parent().attr('class', 'inter_middle inter_middle_current');
            jQuery('#re_pass').parent().prev().attr('class', 'inter_left inter_left_current');
            jQuery('#re_pass').parent().next().attr('class', 'inter_right inter_right_current');
        });

        jQuery('#login').click(function () {
            location.href = '${localPath}/user/login';
        });

        changeValid();

        // 发送短信
        $(".get_code").click(function () {
            var send = parseInt($(this).attr("send"));
            var mobile = $("input[name=mobile]").val().replace(/[^\d]|-/g, '');
            var valid_type = $(".get_code").attr('typ');
            var valid_code = jQuery("input[name=valid_code]").val();
            if (!testMobile(mobile)) {
                showEasyMsg("请输入正确的手机号！");
                return false;
            }
            if (valid_code == '') {
                showEasyMsg("请输入右侧验证码！");
                return false;
            } else {
                jQuery.ajax({
                    url: '${localPath}/user/check_code',
                    success: function (rs) {
                        if (valid_code.toLowerCase() != rs) {
                            showEasyMsg("右侧验证码错误");
                            changeValid();
                            return false;
                        } else {
                            if (send > 0) return false;
                            $.ajax({
                                url: '${localPath}/user/get_sms',
                                data: {mobile: mobile},
                                success: function (data) {
                                    if (data['success'] != true) {
                                        showEasyMsg(data['message']);
                                        return false;
                                    } else {
                                        console.log(data);
                                        $(".get_code").attr("send", 60);
                                        sendCode = setInterval(function () {
                                            var i = parseInt($(".get_code").attr("send")) - 1;
                                            i = i <= 0 ? 0 : i;
                                            $(".get_code").attr("send", i).val(i + "秒后重新获取");
                                            if (i == 0) {
                                                $(".get_code").attr("send", 0).val("免费获得验证码");
                                                clearInterval(sendCode);
                                            }
                                        }, 1000);
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });
    });

    /*更换验证码*/
    jQuery('#valid_img').click(function () {
        changeValid();
    });

    function changeValid() {
        jQuery.ajax({
            url: "${localPath}/user/get_code",
            success: function (data) {
                jQuery('#valid_img').prop('src', "${localPath}/user/get_code");
            }
        });
    }

    jQuery("#ystk").click(function () {
        showMsg("隐私条款<br/>我们将保证您的数据及涉及隐私的信息安全。您提供的信息仅供用于订单处理及结算付款之目的，我们不会与任何第三方分享您的任何信息。本网站不会将您的个人信息出售或租用给任何机构。我们注重声誉，因此我们会尽最大的努力保障你的信息安全。");
    });

    // 注册
    jQuery("#reg_button").click(function () {
        var mobile = $("input[name=mobile]").val().replace(/[^\d]|-/g, '');
        var password = $("input[name=pass]").val();
        var re_password = $("input[name=re_pass]").val();
        var valid = jQuery("input[name=valid]").val();
        var valid_code = jQuery("input[name=valid_code]").val();
        var xieyi = jQuery("#xieyi").attr('checked');
        if (!testMobile(mobile)) {
            showEasyMsg("手机号码为空或填写错误！");
            return false;
        }
        if (valid_code == '') {
            showEasyMsg("请输入右侧验证码！");
            return false;
        }

        if ("" == password) {
            showEasyMsg("登录密码不允许为空！");
            return false;
        }

        if ("" == re_password) {
            showEasyMsg("确认登录密码不允许为空！");
            return false;
        }

        if (password != re_password) {
            showEasyMsg("两次密码输入不一样！");
            return false;
        }

        if (password.length < 6 || password.length > 16) {
            showEasyMsg("密码设置范围为6-16位！");
            return false;
        }
        if (valid == '') {
            showEasyMsg("请输入短信验证码！");
            return false;
        }
        if ('checked' != xieyi) {
            showEasyMsg("请同意许可协议！");
            return false;
        }
        jQuery.ajax({
            url: '${localPath}/user/check_code',
            success: function (rs) {
                if (valid_code.toLowerCase() != rs) {
                    showEasyMsg("右侧验证码错误");
                    changeValid();
                    return false;
                } else {
                    var params = new Array();
                    params['userPassword'] = password;
                    params['userTel'] = mobile;
                    params['sms'] = valid;
                    post('${localPath}/user/user_register', params);
                }
            }
        });
    });
</script>

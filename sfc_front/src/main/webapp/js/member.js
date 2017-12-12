/**
 *  用户中心 js 文件
 *  @author wxl
 */
var orderStatusList = {
    1: '等待付款',
    3: '付款成功',
    4: '购买成功',
    5: '购买失败',
    6: '支付失败',
    7: '已经过期'
};
var localPath = "/front";

/**
 * 会员卡充值
 */
function recharge() {
    var no = jQuery("#card_no").val();
    var re_money = jQuery("#pay_money").val();
    var pay_method = $("input:checked[name=pay_method]").val();
    if ("" == no) {
        window.parent.showEasyMsg("请选择要充值的会员卡");
        return false;
    }
    if ("" == re_money) {
        window.parent.showEasyMsg("请选择充值金额");
        return false;
    }

    if ("undefined" == typeof(pay_method)) {
        window.parent.showEasyMsg('请选择支付方式');
        return false;
    }
    var payData = {
        membercard_no: no,
        money: re_money,
        pay_method: pay_method
    };
//	var yue_pay = jQuery("input[name=yue_pay_money]").val();
//	if("" != yue_pay && 'undefined' != typeof(yue_pay)){
//		payData['balance'] = yue_pay;
//	}
    if (pay_method == 4) {
        payData['pay_method'] = pay_method;
    } else {
        payData['bank'] = pay_method;
        payData['pay_method'] = 4;
    }
    jQuery.ajax({
        url: '/database/GetCardInfo',
        data: {membercard_no: no},
        success: function (data) {
            console.log(data);
            if (data['status'] != 0) {
                window.parent.showEasyMsg(data['error']);
                return false;
            } else {
                payData['membercard_password'] = data['member'].cardPass;
                jQuery.ajax({
                    url: '/database/OrderMember',
                    async: false,
                    data: payData,
                    success: function (data) {
                        if (data['status'] != 0) {
                            window.parent.showEasyMsg(data['error']);
                            return false;
                        } else {
                            pay_url = data['pay_url'];
                            location.href = pay_url;
                        }
                    }
                });
            }
        }
    });


//	var cinema_id = pay_url = "";
//	if(no != ""){
//		jQuery.ajax({
//			url:'/member/GetCardInfo',
//			async:false,
//			data:{card_no:no},
//			success:function(data){
//				if(data['status'] != 0){
//					window.parent.showEasyMsg(data['error']);
//					return false;
//				} else{
//					cinema_id = data['memeber'].cinemaId;
//					payData['cinema_id'] = cinema_id;
//				}
//			}
//		});
//		
//	}else{
//		return false;
//	}
//	
//	if("" != cinema_id){
//		jQuery.ajax({
//	        url:'/database/OrderMember',
//	        async:false,
//	        data:{membercard_no:no,membercard_password:pass, money:money,pay_method:pay_method,cinema_id:cinema_id},
//	        success:function(data){
//	        	if(data['status']!=0){
//	        		window.parent.showEasyMsg(data['error']);
//	            	return false;
//	            }else{
//	            	pay_url = data['pay_url'];
//	            }
//	        }
//	    });
//	}
//		jQuery("#pay_url").val(pay_url);
//		if(pay_method != 28){
//			if("" == jQuery("#pay_url").val()){
//	        	return false;
//	        } else {
//				right_url='/member/index/121?no='+no;
//				jQuery("#pay_dialog").show();
//				jQuery("#pay_dialog_div").show();
//	        	return true;
//	        }
//		} else {
//			return false;
//		}

}

// 修改用户资料
function updateInfo() {
    var nickname = jQuery("input[name=nickname]").val();
    var gender = jQuery('select[name="gender"]').val();
    var realname = jQuery("input[name=realname]").val();
    var email = jQuery("input[name=email]").val();
    var photo = jQuery("#crop_photo").val();
    var userid = jQuery("#userid").val();
    var birth = jQuery('#birth').val();
    if ("" == nickname) {
        window.parent.showEasyMsg('请输入昵称');
        return false;
    } else {
        if (nickname.length >= 7 || nickname.length < 1) {
            window.parent.showEasyMsg("昵称长度1-8位");
            return false;
        }
    }
    if (realname != "") {
        if (realname.length >= 20 || realname.length < 2) {
            window.parent.showEasyMsg("真实姓名为长度2-20");
            return false;
        }
        var name_reg = /^(-?\d+)|(-?\d+)(\.\d+)$/;
        if (name_reg.test(realname)) {
            window.parent.showEasyMsg("真实姓名不能为数字");
            return false;
        }
    }
    if (email != "") {
        if (!testEmail(email)) {
            window.parent.showEasyMsg('请输入正确的邮箱');
            return false;
        }
    }
    var pos = {nickname: nickname, gender: gender, email: email, name: realname, birth: birth};
    if ("" != photo) {
        pos['file'] = photo;
    }
    jQuery.ajax({
        url: '/member/UpdateUserInfo',
        type: 'post',
        data: pos,
        success: function (data) {
            console.log(data);
            if (data['status'] == 0) {
                window.parent.showEasyMsg('更新成功');
                location.href = "/member/info";
            } else {
                window.parent.showEasyMsg(data['error']);
            }
        }
    });
}

jQuery(function () {

    /**
     * 修改个人资料
     */
    jQuery("#mod_info").click(function () {

        var photo_html = jQuery('#photo_crop').html();
        if ("" != photo_html) {
            if (!parseInt($('#w').val())) {
                window.parent.showEasyMsg('请选择裁剪区域');
                return false;
            } else {
                console.log(1);
                var url = jQuery('#user_bg').attr('src');
                var x = jQuery('#x').val();
                var y = jQuery('#y').val();
                var w = jQuery('#w').val();
                var h = jQuery('#h').val();
                var b = jQuery('#b').val();
                jQuery.ajax({
                    url: '/member/imagecut',
                    type: 'post',
                    data: {url: url, x: x, y: y, w: w, h: h, b: b},
                    success: function (data) {
                        console.log(0);
                        if (0 == data) {
                            window.parent.showEasyMsg('裁剪失败，请重试');
                            return false;
                        } else {
                            jQuery('#crop_photo').val(data);
                            updateInfo();
                        }
                    }
                });
            }
        } else {
            updateInfo();
        }
    });

    /**
     * 修改密码
     */
    jQuery("#mod_pass").click(function () {
        var old_pass = jQuery("input[name=old_pass]").val();
        var new_pass = jQuery("input[name=new_pass]").val();
        var re_pass = jQuery("input[name=re_pass]").val();
        if ("" == old_pass) {
            window.parent.showEasyMsg('旧密码不能为空');
            return false;
        }
        if ("" == new_pass) {
            window.parent.showEasyMsg('新密码不能为空');
            return false;
        }
        if ("" == re_pass) {
            window.parent.showEasyMsg('确认密码不能为空');
            return false;
        }
        if (new_pass.length < 6 || new_pass.length > 16) {
            window.parent.showEasyMsg("密码设置范围为6-16位！");
            return false;
        }
        if (new_pass != re_pass) {
            window.parent.showEasyMsg('两次密码输入不一致');
            return false;
        }
        jQuery.ajax({
            url: localPath + '/member/modify_pass',
            data: {userPassword: old_pass, newPassword: new_pass},
            method: 'POST',
            success: function (data) {
                if (0 == data['status']) {
                    window.parent.showMsg('密码修改成功,请重新登录', function () {
                        window.parent.location.href = '/user/login_out';
                    });
                } else {
                    window.parent.showEasyMsg(data['error']);
                }
            }
        });
    });

    /**
     * 绑定会员卡
     */
    jQuery("#bind_member_button").click(function () {
        var membercard_no = jQuery("input[name=bind_member_no]").val();
        var membercard_password = jQuery("input[name=bind_member_pass]").val();
        var cinema_id = jQuery("#bind_member_cinema").val();
        var mobile = jQuery("input[name=member_mobile]").val();
        var valid_code = jQuery("input[name=bind_member_valid]").val();
        if ("" == cinema_id) {
            showEasyMsg("请选择开卡影院");
            return false;
        }
        if ("" == membercard_no) {
            showEasyMsg('会员卡号不能为空');
            return false;
        }
        if ("" == membercard_password) {
            showEasyMsg("会员卡密码不能为空");
            return false;
        }
        if (!testMobile(mobile)) {
            showEasyMsg('请填写正确的手机号');
            return false;
        }
        if ("" == valid_code) {
            showEasyMsg("验证码不能为空");
            return false;
        }
        jQuery.ajax({
            url: '/database/bindCard',
            data: {cinema_id: cinema_id, membercard_no: trim(membercard_no), membercard_password: trim(membercard_password), mobile: mobile, valid_code: valid_code},
            success: function (data) {
                if (data['status'] == 0) {
                    showEasyMsg('绑定成功');
                    location.href = '/member/vip';
                } else {
                    showEasyMsg(data['error']);
                }
            }
        });
    });

    /**
     * 解绑会员卡
     */
    jQuery("#del_member_button").click(function () {
        var no = jQuery("input[name=del_member_no]").val();
        var pass = jQuery("input[name=del_member_pass]").val();
        var mobile = jQuery("#part_delete").val();
        var valid = jQuery("input[name=del_member_valid]").val();
        if ("" == no) {
            window.parent.showEasyMsg('会员卡号不能为空');
            return false;
        }
        if ("" == pass) {
            window.parent.showEasyMsg("会员卡密码不能为空");
            return false;
        }
        if (!testMobile(mobile)) {
            window.parent.showEasyMsg('请填写正确的手机号');
            return false;
        }
        if ("" == valid) {
            window.parent.showEasyMsg("验证码不能为空");
            return false;
        }
        jQuery.ajax({
            url: '/database/DelCard',
            data: {membercard_no: no, membercard_password: pass, mobile: mobile, valid_code: valid},
            success: function (data) {
                if (data['status'] == 0) {
                    window.parent.showEasyMsg('解绑成功');
                    location.href = '/member/vip';
                } else {
                    window.parent.showEasyMsg(data['error']);
                }
            }
        });
    });

    /**
     * 切换会员卡，查询会员卡影院ID
     */
    jQuery("#imprest_member_no").change(function () {

    });

//	jQuery("#sub_form_button").submit(function(){
//		return recharge();
//	});

    $("input[name=mobile],input[name=pass],input[name=password],input[name=re_pass],input[name=valid]").focus(function () {
        $(".regmsg").html("<span>验证码，有效期30分钟</span>");
    });

    $(".get_code_member").click(function () {
        var send = parseInt($(this).attr("send"));
        var mobile = $("input[name=member_mobile]").val().replace(/[^\d]|-/g, '');
        var valid_type = $(this).attr('type');
        if (!testMobile(mobile)) {
            window.parent.showEasyMsg("请输入正确的手机号！");
            return;
        }
        var datas = "";
        if ('12' == valid_type) {
            var cinema_id = jQuery("#bind_member_cinema").val();
            var membercard_no = jQuery("input[name=bind_member_no]").val();
            var membercard_password = jQuery("input[name=bind_member_pass]").val();
            if ("" == cinema_id) {
                window.parent.showEasyMsg("请选择开卡影院");
                return false;
            }
            if ("" == membercard_no) {
                window.parent.showEasyMsg('请输入会员卡号');
                return false;
            }
            if ("" == membercard_password) {
                window.parent.showEasyMsg("请输入会员卡密码");
                return false;
            }
            datas = {mobile: mobile, valid_type: valid_type, cinema_id: cinema_id, membercard_no: trim(membercard_no), membercard_password: trim(membercard_password)};
        } else {
            datas = {mobile: mobile, valid_type: valid_type};
        }
        if (send > 0) return false;
        $.ajax({
            url: '/database/validcodequery',
            data: datas,
            success: function (data) {
                if (data['status'] != 0) {
                    window.parent.showEasyMsg(data['error']);
                    return false;
                } else {
                    $(".get_code_member").attr("send", 60);
                    sendCode = setInterval(function () {
                        var i = parseInt($(".get_code_member").attr("send")) - 1;
                        i = i <= 0 ? 0 : i;
                        $(".get_code_member").attr("send", i).html(i + "秒后重新获取").css('background', '#CCC');
                        if (i == 0) {
                            $(".get_code_member").attr("send", 0).html("免费获得验证码").css('background', '#ff9036');
                            clearInterval(sendCode);
                        }
                    }, 1000);
                }
            }
        });
    });

});
<%--
  Created by IntelliJ IDEA.
  User: viphu
  Date: 2017-11-30
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<script type="text/javascript" src="${staticPath}/js/post.js"></script>
<div class="page_color none page_number"></div>
<div class=" width_max background_color2">
    <div class="index_top_information">
        <div class="wrap">
            <div class="login">
                <p class="pl_20 left">您好，欢迎光临上影国际影城</p>
                <div class="login_nav right">
                    <shiro:authenticated>
                        <a>${userBean.userTel}，欢迎回来</a>
                        <a href="${localPath}/user/login_out">[退出]</a>
                        <a href="${localPath}/member/order">我的上影</a><span>|</span>
                    </shiro:authenticated>
                    <shiro:notAuthenticated>
                        <a href="${localPath}/user/login">登录</a><span>|</span>
                        <a href="${localPath}/user/register">注册</a><span>|</span>
                    </shiro:notAuthenticated>
                    <a href="#" class="login_down">手机app</a></div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>
<!--------- 登陆 ------------->
<div class="page_title width_max">
    <div class="wrap menu_h position_r nav_color nav_number clearfix">
        <div class="logo left"><a href=""></a></div>
        <div class="city f_12 left" id="city_all"><span id="city_now" cityid="${currentCity==null?'':currentCity.cityId}">${currentCity==null?'':currentCity.cityName}</span></div>
        <div class="menu fl">
            <ul>
                <li><a href="${localPath}" class="nav_current">首页</a></li>
                <li><a >电影</a></li>
                <li><a >影院</a></li>
                <li><a >资讯</a></li>
            </ul>
        </div>
        <div class="once_money hidden"><a href="#">会员充值</a></div>
        <div class="clear"></div>
        <div class="city_icon none"></div>
        <div class="city_list none" id="city_list">
            <div class="city_letter">
                <p class="city_tip">
                    <span class="letter_current">城市列表</span>
                </p>
                <p class="city_search" id="city_search">
                </p>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        jQuery.ajax({
            url: '${localPath}/index/city_list',
            success: function (data) {
                var str = "";
                data.forEach(function (city) {
                    str += "<a href=\"javascript:void(0)\" cn=\"ABCD\" onclick=\"change_city(this)\" class=\"city_current\" cityid=\"" + city.cityId + "\">" + city.cityName + "</a>"
                });
                var city_select = jQuery('#city_now');
                if (city_select.text() == '') {
                    var city = data[0];
                    if (city != undefined && city != '') {
                        city_select.attr('cityid', data.pop()['cityId']);
                        city_select.text(data.pop()['cityName']);
                    }
                }
                jQuery(".city_search").html(str);
            }
        });
    })
    jQuery('#city_all').click(function () {
        jQuery("#city_list").toggle();
    });
    function city_show(obj) {
        var cz = jQuery(obj).text();
        jQuery('.letter_current').attr('class', 'cz_class');
        jQuery(obj).attr('class', 'letter_current');
        jQuery('.city_current').attr('class', 'none');
        jQuery('.city_hot a').attr('class', 'city_current');
        jQuery("a[cn='" + cz + "']").attr('class', 'city_current');
    }

    function change_city(obj) {
        jQuery("#city_now").text(jQuery(obj).text());
        var city_id = jQuery(obj).attr('cityId');
        console.log(city_id);
        var params = new Array();
        params['cityId'] = city_id;
        post('${localPath}/index/change_city', params);
    }
</script>

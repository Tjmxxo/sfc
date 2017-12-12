var localPath="/front";
$(function () {


    $(".member_order_list li").bind("click", function () {
        jQuery('.ko_loading').show();
    });

    //    我的上影—账户安全-修改支付密码tab切换
    $(".account_list li").bind("click", function () {
        $(".account_list li").removeClass("account_current");
        $(this).addClass("account_current");
        $(".account_inter").addClass("hidden");
        $($(".account_inter").get($(this).index())).removeClass("hidden");
        $(".check_content").addClass("hidden");
        $($(".check_content").get($(this).index())).removeClass("hidden");
        $(".movie_list").addClass("hidden");
        $($(".movie_list").get($(this).index())).removeClass("hidden");
    });

//    首页切换城市
    var new_width = $(document).width();
    var new_height = $(document).height();
    $(".page_color").css({"width": new_width, "height": new_height});
    $(".city").bind("click", function () {
        $(".city_icon,.city_list,.page_color").removeClass("none");
    });

    $(".city_hot p").bind("click", function () {
        $(".city_icon,.city_list,.page_color").addClass("none");
        jQuery("#city_list").toggle();
    })


//    电影_购票_支付tab效果
    $(".pay_detail li").bind("click", function () {
        $(".pay_detail li").removeClass("detail_current");
        $(this).addClass("detail_current");
        $(".pay_main").addClass("hidden");
        $($(".pay_main").get($(this).index())).removeClass("hidden");
    })


//    电影_电影list页_热映
    $(".list_tip li").bind("click", function () {
        $(".list_tip li").removeClass("list_current");
        $(this).addClass("list_current");
        $(".list_time").addClass("hidden");
        $($(".list_time").get($(this).index())).removeClass("hidden");
    })

//    电影_电影详情页_热映
    $("#detail_list li").bind("click", function () {
        $("#detail_list li").removeClass("menu_current");
        $(this).addClass("menu_current");
        $(".detail_take").addClass("hidden");
        $($(".detail_take").get($(this).index())).removeClass("hidden");
    })

    $("#detail_bar li").bind("click", function () {
        $("#detail_bar li").removeClass("bar_current");
        $(this).addClass("bar_current");
        $(".detail_scroll").addClass("hidden");
        $($(".detail_scroll").get($(this).index())).removeClass("hidden");
    })
})

/**
 * 电影-热映-场次 鼠标滑过效果
 * @param pid
 */
function planTimePrice(pid) {
    jQuery('.plan_time').show();
    jQuery('.plan_price').hide();
    jQuery('#plan_time_' + pid).hide();
    jQuery('#plan_price_' + pid).show();
}

/**
 * 电影-热映-详情 鼠标滑过效果
 * @param pid
 */
function HotMoviePlanMouse(pid) {
    jQuery('.price_plan').show();
    jQuery('.buy_plan').hide();
    jQuery('#price_plan_' + pid).hide();
    jQuery('#buy_plan_' + pid).show();
}

/**
 * 影院-详情 鼠标滑过效果
 * @param pid
 */
function cinemaPlanMouse(pid) {
    jQuery('.price_plan').show();
    jQuery('.buy_plan').hide();
    jQuery('#price_plan_' + pid).hide();
    jQuery('#buy_plan_' + pid).show();
}

/**
 * 鼠标移除效果
 */
function movieMouseout(pid) {
    jQuery('#price_plan_' + pid).show();
    jQuery('#buy_plan_' + pid).hide();
}

/**
 * 进入选座页面
 * @param pid
 */
function buyMovie(pid) {
    if (!pid) {
        showEasyMsg('请选择观影时间');
    } else {
        var url = localPath+'/index/plan/' + pid+".html";
        location.href = url;
    }
}
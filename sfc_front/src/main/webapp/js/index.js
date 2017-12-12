/********************************************
 *  首页使用 JS 文件                            *
 *  @author wxl                                *
 ********************************************/

var localPath = '/front';

/**
 * 加载当前城市的电影
 * @param func
 */
function getCurrentMovie(func) {
    var cityId = $('#city_now').attr('cityid');
    if (!isNaN(cityId)) {
        jQuery.ajax({
            url: localPath+'/index/movie_list',
            data: {
                cityId: cityId
            },
            success: function (data) {
                if (data == 0) {
                    showEasyMsg("该城市没有电影放映")
                } else {
                    func(data);
                }
            }
        });
    }
}
$(document).ready(function () {
    $('.pic_next').click(function () {

        if ($('.piclist').is(':animated')) {
            $('.piclist').stop(true, true);
        }
        /* 避免点击事件重复 */
        jQuery('#prev_img').attr('src', '/images/circle_left.png');
        ml = parseInt($('.piclist').css('left'));
        r = liw - (1000 - ml);
        /* 1000为外部区块.infopic的宽度，20为li之间的距离，即.piclist li的margin-right的值 */
        if (r < 1000) {
            // 最后一次少减100，保证宽度
            s = r - 20 - 100;
            jQuery('#next_img').attr('src', '/images/circle_right_free.png');
        } else {
            s = 1000;
        }
        $('.piclist').animate({left: ml - s + 'px'}, 'slow');
    })

    $('.pic_prev').click(function () {

        if ($('.piclist').is(':animated')) {
            $('.piclist').stop(true, true);
        }
        /* 避免点击事件重复 */
        jQuery('#next_img').attr('src', '/images/circle_right.png');
        ml = parseInt($('.piclist').css('left'));
        var pic = parseInt($('.piclist').css('right'))
        if (ml > -1000) {
            s = ml;
            jQuery('#prev_img').attr('src', '/images/circle_left_free.png');
        } else {
            // 第一次多减100px，保证半个的图片显示在右侧
            if (-ml == (liw - 1120)) {
                s = -1100;
            } else {
                s = -1000;
            }

        }
        $('.piclist').animate({left: ml - s + 'px'}, 'slow');
    })

    //热映电影和即将上映切换
    $("#film_nav a").bind("click", function () {
        $("#film_nav a").removeClass("show_nav_current");
        var className = jQuery(this).attr('class');
        if ('hot_movie_list_t' == className) {
            jQuery('.beon_movie_list').hide();
            jQuery('#movie_div_1').show();
        } else {
            jQuery('.beon_movie_list').show();
            jQuery('.hot_movie_list').hide();
        }
        if ($(this).find("div").hasClass("line_position")) {
            $(".film_main").addClass("hidden");
            $(".line_position").removeClass("hidden");
            $(this).addClass("show_nav_current");
        } else {
            $(".film_main").removeClass("hidden");
            $(".line_position").addClass("hidden");
            $(this).addClass("show_nav_current");
        }
    })


//    $("#show_click a").bind({
//        mouseover:function(){
//            $(this).animate({"background-position":function(){
//                $(this).addClass("position_like");
//            }},1000);
//        }
//    })
});


//正在热映和即将上映切换
function selectTag(showContent, selfObj) {
    // 操作标签
    var tag = document.getElementById("nav_title").getElementsByTagName("font");
    var taglength = tag.length;
    for (i = 0; i < taglength; i++) {
        tag[i].className = "";
        selfObj.className = "title_hot_current";
    }
//    selfObj.parentNode.className = "selectTag";
    // 操作内容
    for (i = 0; j = document.getElementById("tagContent" + i); i++) {
        j.style.display = "none";
    }
    document.getElementById(showContent).style.display = "block";
}


//info pic
$(window).load(function () {
    liw = 0;
    $('.piclist li').each(function () {
        liw += $(this).width() + 20;
        $(this).css('width', $(this).width() + 'px');
    });
    $('.piclist').width(liw + 'px');

});

/**
 * 给下拉列表绑定事件
 */
jQuery(document).ready(function () {
    jQuery("#movie_id,.movie_down,#movie_id_input").click(function () {
        jQuery("#movie_div").toggle();
        jQuery("#cinema_div").hide();
        jQuery("#plan_div").hide();
        jQuery("#date_div").hide();
    });
    jQuery("#cinema_id,.cinema_down,#cinema_id_input").click(function () {
        jQuery("#cinema_div").toggle();
        jQuery("#movie_div").hide();
        jQuery("#plan_div").hide();
        jQuery("#date_div").hide();
    });
    jQuery("#date_id,.date_down,#date_id_input").click(function () {
        jQuery("#date_div").toggle();
        jQuery("#cinema_div").hide();
        jQuery("#movie_div").hide();
        jQuery("#plan_div").hide();
    });
    jQuery("#plan_id,.plan_down,#plan_id_input").click(function () {
        jQuery("#plan_div").toggle();
        jQuery("#cinema_div").hide();
        jQuery("#movie_div").hide();
        jQuery("#date_div").hide();
    });
});

// 点击空白，关闭下拉列表
$(document).bind("click", function (e) {
    var target = $(e.target);
    var plan_css = target.closest("#plan_id,#plan_id_input").length;
    var cinema_css = target.closest("#cinema_id,#cinema_id_input").length;
    var movie_css = target.closest("#movie_id,#movie_id_input").length;
    var date_css = target.closest("#date_id,#date_id_input").length;
    if (0 == plan_css) {
        $("#plan_div").hide();
    }
    if (0 == cinema_css) {
        $("#cinema_div").hide();
    }
    if (0 == movie_css) {
        $("#movie_div").hide();
    }
    if (0 == date_css) {
        $('#date_div').hide();
    }
});
/**
 * 加载当前地区可购买电影
 */

/*jQuery('#movie_id_input').click(
    function() {
        var cityId=$('#city_now').attr('cityid');
        var movieList=jQuery('#movie_list');
        if(!isNaN(cityId)){
            jQuery.ajax({
                url:'${localPath}/movie_list',
                type:"post",
                data:{
                    'cityId':cityId
                },
                dataType:'json',
                success:function (data) {
                    var str='';
                    if(data==0){
                        showEasyMsg("该城市没有电影放映")
                    }else{
                        data.forEach(function (movie) {
                            str+="<li><a href=\"javascript:void(0)\" onclick=\"movieSelect(this)\" mid=\""+movie['movieId']+"\" title=\""+movie['movieName']+"\">"+movie['movieName']+"</a></li>";
                        });
                        movieList.html(str);
                    }
                }
            });
        }
    }
);*/
/**
 * 影片下拉[加载对应影院]
 * @param obj
 */
function movieSelect(obj) {
    jQuery("#movie_div").hide();
    jQuery('#movie_id').prev().val(jQuery(obj).text());
    jQuery('#cinema_id').prev().attr('text', '请选择影院');
    jQuery('#date_id').prev().attr('text', '请选择日期');
    jQuery('#plan_id').prev().attr('text', '时间');
    var cinema_str = "";
    var movie_id = jQuery(obj).attr('mid');
    var city_id = jQuery('#city_now').attr('cityid');
    jQuery('#hid_movie_id').val(movie_id);
    // 载入影院列表
    jQuery.ajax({
        type: "post",
        url: localPath + '/index/cinema_list',
        data: {
            'movieId': movie_id,
            'cityId': city_id
        },
        success: function (data) {
            console.log(data)
            if (0 == data) {
                jQuery("#cinema_ul").empty();
                jQuery("#cinema_div").hide();
                showEasyMsg('暂无影院');
            } else {
                jQuery.each(data, function (i, n) {
                    cinema_str += '<li><a cid="' + n.cinemaId + '" href="javascript:void(0)" title="' + n.cinemaName + '" onclick="cinemaSelect(this)">' + n.cinemaName + '</a></li>';
                });
                jQuery("#cinema_ul").empty().append(cinema_str);
                jQuery("#cinema_div").show();
            }
        },
        dataType: "json"
    });
}

/**
 * 影院下拉[加载对应日期]
 * @param obj
 */
function cinemaSelect(obj) {
    jQuery("#cinema_div").hide();
    jQuery('#cinema_id').prev().val(jQuery(obj).text());
    jQuery('#date_id').prev().attr('text', '请选择日期');
    jQuery('#plan_id').prev().attr('text', '时间');
    var city_id = jQuery('#city_now').attr('cityid');
    var cinema_id = jQuery(obj).attr('cid');
    var movie_id = jQuery('#hid_movie_id').val();
    var plan_str = "";
    jQuery('#hid_cinema_id').val(cinema_id);
    jQuery('#hid_plan_id').val("");
    jQuery.ajax({
        type: "post",
        url: localPath + '/index/date_list',
        data: {
            'cinemaId': cinema_id,
            'movieId': movie_id,
            'cityId': city_id
        },
        success: function (data) {
            if (0 == data) {
                jQuery("#date_info").empty();
                jQuery("#date_div").hide();
                showEasyMsg('没有上映日期');
            } else {
                jQuery.each(data, function (i, n) {
                    var planData = new Date(n.planData);
                    var date = (planData.getMonth() + 1) + '月' + planData.getDate() + '日';
                    plan_str += '<li><a did="' + n.planData + '" href="javascript:void(0)" onclick="dateSelect(this)">' + date + '</a></li>';
                });
                jQuery("#date_ul").empty().append(plan_str);
                jQuery("#date_div").show();
            }
        },
        dataType: "json"
    });
}

/**
 * 日期下拉[加载对应排期]
 * @param obj
 */
function dateSelect(obj) {
    jQuery("#date_div").hide();
    jQuery('#plan_id').prev().attr('text', '时间');
    jQuery('#date_id').prev().val(jQuery(obj).text());
    var city_id = jQuery('#city_now').attr('cityid');
    var plan_date = jQuery(obj).attr('did');
    var cinema_id = jQuery('#hid_cinema_id').val();
    var movie_id = jQuery('#hid_movie_id').val();
    var plan_str = "";
    jQuery('#hid_date_id').val(plan_date);
    jQuery('#hid_plan_id').val("");

    jQuery.ajax({
        type: "post",
        url: localPath + '/index/date_list',
        data: {
            'cinemaId': cinema_id,
            'movieId': movie_id,
            'cityId': city_id,
            'planDate': plan_date
        },
        success: function (data) {
            if (0 == data) {
                jQuery("#plan_info").empty();
                jQuery("#plan_div").hide();
                showEasyMsg('当天无排期');
            } else {
                jQuery.each(data, function (i, n) {
                    var css = fun = "";
                    if ('grey' == n.css) {
                        css = 'style="color: grey;"';
                    } else {
                        fun = 'onclick="planSelect(this)"';
                    }
                    var planTime = new Date(n.planTime);
                    var date = planTime.getHours() + '点' + planTime.getMinutes() + '分';
                    plan_str += '<li><a ' + css + ' pid="' + n.planId + '" ' + fun + ' href="javascript:void(0)">' + date + '</a></li>';
                });
                jQuery("#plan_ul").empty().append(plan_str);
                jQuery("#plan_div").show();
            }
        },
        dataType: "json"
    });
}

/**
 * 场次下拉
 * @param obj
 */
function planSelect(obj) {
    jQuery("#plan_div").hide();
    jQuery('#plan_id').prev().val(jQuery(obj).text());
    jQuery('#hid_plan_id').val(jQuery(obj).attr('pid'));
}

jQuery(function () {
    // 首页快速购票
    jQuery("#buy_button").click(function () {
        var plan_id = jQuery('#hid_plan_id').val();
        if ("" == plan_id) {
            showEasyMsg('请选择观影时间');
        } else {
            var url = localPath + '/index/plan/' + plan_id + '.html';
            location.href = url;
        }
    });

    jQuery('.group_buy,#group_off').click(function () {
        jQuery('#group_check').toggle();
        jQuery('.page_color').toggle();
    });
    jQuery('#notice_off').click(function () {
        jQuery('#notice_check').hide();
        jQuery('.page_color').hide();
    });
});

function notic(obj) {
    var title = jQuery(obj).text();
    var content = jQuery(obj).attr('content');
    jQuery('#notic_title').text(title);
    jQuery('#notic_content').text(content);
    jQuery('#notice_check').show();
    jQuery('.page_color').show();
}

function over(obj) {
    jQuery("#movie_yinying_" + obj).show();
    jQuery("#movie_end_" + obj).hide();
    jQuery("#movie_img_" + obj).attr('width', '204px');
    jQuery("#movie_img_" + obj).attr('height', '305px');
    jQuery("#movie_img_" + obj).css({"position": "relative", "left": "-12px"});
}

function mout(obj) {
    jQuery("#movie_yinying_" + obj).hide();
    jQuery("#movie_end_" + obj).show();
    jQuery("#movie_img_" + obj).attr('width', '180px');
    jQuery("#movie_img_" + obj).attr('height', '269px');
    jQuery("#movie_img_" + obj).css({"position": "relative", "left": "0px"});
}

/**
 * 首页正在上映切换
 * @param num
 * @param type
 */
function chang_movie(num, type) {
    jQuery("#movie_div_" + num).fadeIn();
    jQuery("#movie_div_page_" + num).fadeIn();
    if ("min" == type) {
        jQuery("#movie_div_" + (num + 1)).hide();
        jQuery("#movie_div_page_" + (num + 1)).hide();
    } else {
        jQuery("#movie_div_" + (num - 1)).hide();
        jQuery("#movie_div_page_" + (num - 1)).hide();
    }
}

/**
 * 首页即将上映切换
 * @param num
 * @param type
 */
function chang_movie_soon(num, type) {
    jQuery("#movie_soon_div_" + num).fadeIn();
    jQuery("#movie_soon_div_page_" + num).fadeIn();
    if ("min" == type) {
        jQuery("#movie_soon_div_" + (num + 1)).hide();
        jQuery("#movie_soon_div_page_" + (num + 1)).hide();
    } else {
        jQuery("#movie_soon_div_" + (num - 1)).hide();
        jQuery("#movie_soon_div_page_" + (num - 1)).hide();
    }
}

// 首页底部活动滚动
$(function () {
    var sWidth = $("#focus").width(); //获取焦点图的宽度（显示面积）
    var len = $("#focus ul li").length; //获取焦点图个数
    var index = 0;
    var picTimer;

    //以下代码添加数字按钮和按钮后的半透明条，还有上一页、下一页两个按钮
    var btn = "<div class='btnBg activity_button clearfix'>";
    for (var i = 0; i < len; i++) {
        btn += "<span>" + (i + 1) + "</span>";
    }
//	btn += "</div><div class='preNext pre'></div><div class='preNext next'></div>";
    btn += "</div>";
    $("#focus").append(btn);
//	$("#focus .btnBg").css("opacity",0.5);

    //为小按钮添加鼠标滑入事件，以显示相应的内容
    $("#focus .btnBg span").removeClass('advertise_current').mouseover(function () {
        index = $("#focus .btnBg span").index(this);
        $("#focus .btnBg span").removeClass('advertise_current');
        jQuery(this).addClass('advertise_current');
        showPics(index);
    }).eq(0).trigger("mouseover");

    //上一页、下一页按钮透明度处理
    $("#focus .preNext").css("opacity", 0.2).hover(function () {
        $(this).stop(true, false).animate({"opacity": "0.5"}, 300);
    }, function () {
        $(this).stop(true, false).animate({"opacity": "0.2"}, 300);
    });

    //上一页按钮
    $("#focus .pre").click(function () {
        index -= 1;
        if (index == -1) {
            index = len - 1;
        }
        showPics(index);
    });

    //下一页按钮
    $("#focus .next").click(function () {
        index += 1;
        if (index == len) {
            index = 0;
        }
        showPics(index);
    });

    //本例为左右滚动，即所有li元素都是在同一排向左浮动，所以这里需要计算出外围ul元素的宽度
    $("#focus ul").css("width", sWidth * (len));

    //鼠标滑上焦点图时停止自动播放，滑出时开始自动播放
    $("#focus").hover(function () {
        clearInterval(picTimer);
    }, function () {
        picTimer = setInterval(function () {
            showPics(index);
            index++;
            if (index == len) {
                index = 0;
            }
        }, 4000); //此4000代表自动播放的间隔，单位：毫秒
    }).trigger("mouseleave");

    //显示图片函数，根据接收的index值显示相应的内容
    function showPics(index) { //普通切换
        var nowLeft = -index * sWidth; //根据index值计算ul元素的left值
        $("#focus ul").stop(true, false).animate({"left": nowLeft}, 300); //通过animate()调整ul元素滚动到计算出的position
        $("#focus .btnBg span").removeClass("advertise_current").eq(index).addClass("advertise_current"); //为当前的按钮切换到选中的效果
//		$("#focus .btnBg span").stop(true,false).animate({"opacity":"0.4"},300).eq(index).stop(true,false).animate({"opacity":"1"},300); //为当前的按钮切换到选中的效果
    }
});

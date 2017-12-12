/**
 * 购票功能 JS 文件
 * @author wangxulong
 */

/*---------------------------- 快速购票 -------------------------------*/
var localPath="/front";
/**
 * 默认选中第一项影片与影院
 * @
 */
function fastDefault(mid) {
    // 没有影片ID，默认选中第一项
    if (!mid) {
        jQuery('#hid_cinema_id').val(jQuery('.cinema_one').attr('cid'));
        jQuery('#hid_movie_id').val(jQuery('.movie_one').attr('mid'));
        jQuery('#cinema_text').text(jQuery('.cinema_one').text());
        jQuery('#movie_text').text(jQuery('.movie_one').text());
        var cinema_id = jQuery('#hid_cinema_id').val();
        var movie_id = jQuery('#hid_movie_id').val();
        fastGetDate(movie_id, cinema_id);
    } else {
        jQuery('#hid_cinema_id').val(jQuery('.cinema_one').attr('cid'));
        jQuery('#cinema_text').text(jQuery('.cinema_one').text());
        var obj = jQuery('span[mid=' + mid + ']');
        fastMovieChoose(obj);
    }
}

/**
 * 根据影片查询影院 - 快速购票页
 */
function fastGetCinemaByMovie(mid, cid) {
    var dates = "";
    jQuery.ajax({
        type: 'get',
        url: '/movie/CinemaByMovie',
        data: "movie_id=" + mid,
        success: function (data) {
            if (0 == data) {
                showEasyMsg('没有放映影院');
            } else {
                jQuery.each(data, function (i, n) {
                    if (cid == n.cinemaId) {
                        dates += '<dd><span cid="' + n.cinemaId + '" onclick="fastCinemaChoose(this)" class="session_current cinema_one cinema_other">' + n.cinemaName + '</span></dd>';
                    } else {
                        dates += '<dd><span cid="' + n.cinemaId + '" onclick="fastCinemaChoose(this)">' + n.cinemaName + '</span></dd>';
                    }
                });
                jQuery("#cinema_list").empty().append(dates);
                jQuery('#hid_cinema_id').val(jQuery('.cinema_one').attr('cid'));
                fastGetDate(mid, jQuery('.cinema_one').attr('cid'));
            }
        },
        dataType: "json"
    });
}

/**
 * 根据影院获取影片 - 快速购票页
 */
function fastGetMovieByCinema(cid, mid) {
    var dates = "";
    jQuery.ajax({
        type: 'get',
        url: '/movie/GetFilmByCinema',
        data: "cinema_id=" + cid,
        success: function (data) {
            if (0 == data) {
                showEasyMsg('没有放映影片22222');
            } else {
                jQuery.each(data, function (i, n) {
                    if (mid == n.movieId) {
                        dates += '<dd><span mid="' + n.movieId + '" onclick="fastMovieChoose(this)" class="session_current movie_one movie_other">' + n.movieName + '</span></dd>';
                    } else {
                        dates += '<dd><span mid="' + n.movieId + '" onclick="fastMovieChoose(this)">' + n.movieName + '</span></dd>';
                    }
                });
                jQuery("#movie_list").empty().append(dates);
                jQuery('#hid_movie_id').val(jQuery('.movie_one').attr('mid'));
                fastGetDate(jQuery('.movie_one').attr('mid'), cid);
            }
        },
        dataType: "json"
    });
}


/**
 * 日期选择 - 快速购票页
 * @param obj
 */
function fastDateChoose(obj) {
    jQuery('.date_other').removeAttr('class');
    jQuery(obj).attr('class', 'session_current date_other');
    jQuery('#date_text').text(jQuery(obj).text());

    var cinema_id = jQuery('#hid_cinema_id').val();
    var movie_id = jQuery('#hid_movie_id').val();
    var date = jQuery(obj).attr('date');
    fastGetPlan(cinema_id, movie_id, date);
}

/**
 * 影片选择 - 快速购票页
 * @param obj
 */
function fastMovieChoose(obj) {
    jQuery('.movie_other').removeAttr('class');
    jQuery(obj).attr('class', 'session_current movie_other');
    jQuery('#movie_text').text(jQuery(obj).text());

    var movie_id = jQuery(obj).attr('mid');
    var cinema_id = jQuery('#hid_cinema_id').val();
    jQuery('#hid_movie_id').val(movie_id);
    fastGetCinemaByMovie(movie_id, cinema_id);
}

/**
 * 影院选择 - 快速购票页
 * @param obj
 */
function fastCinemaChoose(obj) {
    jQuery('.cinema_other').removeAttr('class');
    jQuery(obj).attr('class', 'session_current cinema_other');
    jQuery('#cinema_text').text(jQuery(obj).text());

    var cinema_id = jQuery(obj).attr('cid');
    var movie_id = jQuery('#hid_movie_id').val();
    jQuery('#hid_cinema_id').val(cinema_id);
    fastGetMovieByCinema(cinema_id, movie_id);
}

/**
 * 公用获取排期日期 - 快速购票页
 * @param movie_id
 * @param cinema_id
 */
function fastGetDate(movie_id, cinema_id) {
    var date_str = "";
    jQuery.ajax({
        type: 'get',
        url: '/movie/PlanDate',
        data: "movie_id=" + movie_id + "&cinemaId=" + cinema_id,
        success: function (plan_date) {
            if (0 == plan_date) {
                jQuery("#plan_date").empty();
                jQuery("#plan_ul").empty();
            } else {
                jQuery.each(plan_date, function (j, m) {
                    if (0 == j) {
                        date_str += '<dd><span onclick="fastDateChoose(this)" class="session_current date_one date_other" date="' + m.time + '">' + m.text + '</span></dd>';
                    } else {
                        date_str += '<dd><span onclick="fastDateChoose(this)" class="date_other" date="' + m.time + '">' + m.text + '</span></dd>';
                    }
                });
                jQuery("#plan_date").empty().append(date_str);
                var date = jQuery('.date_one').attr('date');
                jQuery('#hid_date_id').val(date);
                jQuery('#date_text').text(jQuery('.date_one').text());
                fastGetPlan(cinema_id, movie_id, date);
            }
        },
        dataType: "json"
    });
}

/**
 * 公用获取排期详情 - 快速购票页
 * @param cinema_id
 * @param movie_id
 * @param date
 */
function fastGetPlan(cinema_id, movie_id, date) {
    var plan_str = button = "";
    var none = "";
    jQuery.ajax({
        type: "post",
        url: '/movie/PlanByCinema',
        data: "cinema_id=" + cinema_id + "&movie_id=" + movie_id + "&date=" + date,
        success: function (data) {
            if ("" == data) {
                showEasyMsg('当天无排期');
            }
            if (0 == data) {
                jQuery("#plan_ul").empty();
            } else {
                plan_str = '<p class="session_word clearfix"><span>放映时间</span><span>影厅座位</span><span> 语言</span><span>制式</span><span>优惠信息 </span><span>网购价</span><span>购票</span></p>';
                jQuery.each(data, function (i, n) {
                    if ('grey' == n.css) {
                        none = "nonesss";
                        button = '<li><a href="javascript:void(0)" style="background-color:#CCC">暂停售票</a></li>';
                    } else {
                        none = "";
                        button = '<li><a href="/movie/choose/' + n.qid + '">选座购票</a></li>';
                    }
                    plan_str += '<ul class="session_content clearfix ' + none + '">' +
                        '<li class="line_50"><strong class="font_color3">' + n.featureTimeTake + '</strong></li>' +
                        '<li class="line_50"><!--余 <strong class="font_color3">120</strong>/-->' + n.seatAmount + '</li>' +
                        '<li class="line_50">' + n.language + '</li>' +
                        '<li class="line_50">' + n.screenType + '</li>' +
                        '<li class="session_save"><span onmouseout="mout(' + i + ',1)" onmouseover="over(' + i + ',1)">卡</span><div class="session_three" onmouseout="mout(' + i + ',0)" onmouseover="over(' + i + ',0)">券</div></li>' +
                        '<li class="line_50 session_money"><strong class="font_color3">￥' + n.price + '</strong> <font>￥' + n.marketPrice + '</font></li>' +
                        '<li><a href="/movie/choose/' + n.qid + '">选座购票</a></li>' +
                        //					                button+
                        '</ul>' +
                        '<div class="position_page none" id="card_' + i + '">' +
                        '<div class="session_two left_new_width"></div>' +
                        '<div class="session_key left_new_width1">享受上影会员卡购票折扣</div>' +
                        '</div>' +
                        '<div class="position_page none" id="card_new_' + i + '">' +
                        ' <div class="session_two"></div>' +
                        '<div class="session_key">支持上影优惠券、兑换券购票</div>' +
                        '</div>';

                });
                jQuery("#plan_ul").empty().append(plan_str);
            }
        },
        dataType: "json"
    });
}


function over(obj, type) {
    if (1 == type) {
        jQuery('#card_' + obj).show();
    } else {
        jQuery('#card_new_' + obj).show();
    }
}

function mout(obj, type) {
    if (1 == type) {
        jQuery('#card_' + obj).hide();
    } else {
        jQuery('#card_new_' + obj).hide();
    }
}

/*---------------------------- 影院详情 -------------------------------*/

/**
 * 默认加载第一个影院的第一个日期
 */
function cinemaDefault() {
    var obj = jQuery('.movie_one');
    jQuery('#hid_date_id').val(jQuery('.date_one').attr('time'));
    jQuery('#check_date').text(jQuery('.date_one').attr('time'));
    movieChoose(obj);
}

/**
 * 日期选择 - 影院详情页
 * @param obj
 */
function dateChoose(obj) {
    jQuery(obj).parent().children('li').attr('class', "");
    jQuery(obj).attr('class', 'seed_current');

    var cinema_id = jQuery('#hid_cinema_id').val();
    var movie_id = jQuery('#hid_movie_id').val();
    var date = jQuery(obj).attr('time');
    jQuery('#hid_date_id').val(date);
    jQuery('#check_date').text(date);
    var plan_str = "";
    cinemaPlan(cinema_id, movie_id, date);
}

/**
 * 根据影院、影片获取排期日期 - 影片详情页
 */
function chooseDateByCMidCinema(cid, mid, date) {
    var dates = de = "";
    var is = 0;
    jQuery.ajax({
        type: 'get',
        url: '/movie/PlanDate',
        data: "cinemaId=" + cid + '&movie_id=' + mid,
        success: function (data) {
            if (0 == data) {
                showEasyMsg('没有排期日期');
            } else {
                jQuery.each(data, function (i, n) {
                    if (date == n.time) {
                        dates += '<li time="' + n.time + '" id="' + n.time + '" onclick="dateChoose(this)" class="seed_current date_one">' + n.text + '</li>';
                        jQuery('#hid_date_id').val(date);
                        is = 1;
                    } else {
                        dates += '<li time="' + n.time + '" id="' + n.time + '" onclick="dateChoose(this)" >' + n.text + '</li>';
                        de = n.time;
                    }
                });
                jQuery("#plan_date").empty().append(dates);
                // 判断如果当前没有被选中的日期，则选择日期列表最后一项
                if (is == 0) {
                    jQuery('#' + de).addClass('seed_current');
                    jQuery('#hid_date_id').val(de);
                    jQuery('#check_date').text(de);
                    date = de;
                }
                cinemaPlan(cid, mid, date);
            }
        },
        dataType: "json"
    });
}

/**
 * 影片选择 - 影院详情页
 * @param obj
 */
function movieChoose(obj) {
    var cinema_id = jQuery('#hid_cinema_id').val();
    var date = jQuery('#hid_date_id').val();
    var movie_id = jQuery(obj).attr('mid');
    jQuery('#hid_movie_id').val(movie_id);
    jQuery('#check_movie_name').text(jQuery(obj).attr('mname'));
    jQuery('#check_movie_info').text(jQuery(obj).attr('info'));
    chooseDateByCMidCinema(cinema_id, movie_id, date);
}


/**
 * 公用加载排期 - 影院详情页
 */
function cinemaPlan(cinema_id, movie_id, date) {
    var plan_str = "";
    var button = "";
    jQuery.ajax({
        type: "post",
        url: '/movie/PlanByCinema',
        data: "cinema_id=" + cinema_id + "&movie_id=" + movie_id + "&date=" + date,
        success: function (data) {
            if (0 == data) {
                jQuery("#plan_ul").empty();
            } else {
                jQuery.each(data, function (i, n) {
                    var screen = (n.screenType) ? n.screenType : "";
                    if ('grey' == n.css) {
                        plan_str += '<li class="clearfix" style="background-color:#CCC;color:#FFF">' +
                            '<p><strong>' + n.featureTimeTake + '</strong>' + screen + ' ' + n.language + '</p>' +
                            '<span style="background-color:#CCC;color:#FFF" class="minute_current" >' +
                            '暂停<br/>售票' +
                            '</span>' +
                            '</li>';
                    } else {
                        plan_str += '<li class="clearfix" onmouseover="cinemaPlanMouse(\'' + n.qid + '\')" onmouseout="movieMouseout(\'' + n.qid + '\')">' +
                            '<p><strong>' + n.featureTimeTake + '</strong>' + screen + ' ' + n.language + '</p>' +
                            '<span class="price_plan" id="price_plan_' + n.qid + '" >' +
                            '<label>￥' + n.price + '</label>' +
                            '<font>￥' + n.marketPrice + '</font>' +
                            '</span>' +
                            '<span onclick="buyMovie(\'' + n.qid + '\')" class="minute_current buy_plan" id="buy_plan_' + n.qid + '" style="display: none;cursor: pointer;">' +
                            '选座<br/>购票' +
                            '</span>' +
                            '</li>';
                    }

                });
                jQuery("#plan_ul").empty().append(plan_str);
            }
        },
        dataType: "json"
    });
}


/*---------------------------- 影片详情 -------------------------------*/

/**
 * 默认加载第一个影院的第一个日期
 */
function chooseDefault() {
    var cinema = jQuery('.cinema_one').attr('cid');
    jQuery('#hid_cinema_id').val(cinema);
    var obj = jQuery('.plan_one');
    choosePlanDateDetail(obj);
}


function getDate(cityId, cinemaId, movieId) {
    var  plan_str='';
    var planDate=jQuery("#plan_date");
    jQuery.ajax({
        type: "post",
        url: localPath + '/index/date_list',
        data: {
            'cinemaId': cinemaId,
            'movieId': movieId,
            'cityId': cityId,
        },
        success: function (data) {
            if (0 == data) {
                jQuery("#plan_date").empty();
                showEasyMsg('没有上映日期');
            } else {
                jQuery.each(data, function (i, n) {
                    var planData = new Date(n.planData);
                    var date = (planData.getMonth() + 1) + '月' + planData.getDate() + '日';
                    plan_str += "<li time=\""+n.planData+"\" onclick=\"choosePlanDateDetail(this)\" class=\"\">"+date+"</li>"
                });
                planDate.empty().append(plan_str);
            }
            var date = $('#plan_date li:first-child').attr('time');
            $('#hid_date_id').val(date);
        },
        dataType: "json"
    });
}
/**
 * 根据影院、影片获取排期日期 - 影片详情页
 */
function chooseDateByCMid(cinemaid, movieId) {
    var city_id = jQuery('#city_now').attr('cityid');
    getDate(city_id, cinemaid, movieId);
}

function getTime(cinemaid,movieId,city_id,plan_date){
    var dates='';
    var plan_info=$('#plan_info');
    jQuery.ajax({
        type: 'get',
        url: localPath+'/index/date_list',
        data: "cinemaId=" + cinemaid + '&movieId=' + movieId+'&cityId='+city_id+'&planDate='+plan_date,
        success: function (data) {
            console.log(data);
            if (0 == data) {
                showEasyMsg('没有放映影片1');
            } else {
                jQuery.each(data, function (i, n) {
                    var planTime = new Date(n.planTime);
                    var date = planTime.getHours() + ':' + planTime.getMinutes();
                    dates += "<li class=\"clearfix\" onmouseover=\"HotMoviePlanMouse('"+n.planId+"')\" onmouseout=\"movieMouseout('"+n.planId+"')\">\n" +
                        "    <p><strong>"+date+"</strong>"+n.planScreenType+"</p>\n" +
                        "    <span class=\"price_plan\" id=\"price_plan_"+n.planId+"\" style=\"display: block;\">\n" +
                        "        <label>￥"+parseInt(n.planPrice*(100-n.planDiscount)/10000)+"</label>\n" +
                        "        <font>￥"+parseInt(n.planPrice/100)+"</font>\n" +
                        "    </span>\n" +
                        "    <span onclick=\"buyMovie('"+n.planId+"')\" class=\"once_current buy_plan\" id=\"buy_plan_"+n.planId+"\" style=\"cursor: pointer; display: none;\">选座<br>购票</span>\n" +
                        "</li>";
                });
                plan_info.html(dates);
            }
        },
        dataType: "json"
    });
}
/**
 * 获取当前电影院
 */
function getCinema() {
    var cinema_list=$('#cinema_list');
    var cinema_detail=$('#cinema_detail');
    var movie_id=$('#hid_movie_id').val();
    var city_id = $('#city_now').attr('cityid');
    var list='';
    var detail='';
    $.ajax({
        url:localPath+'/index/cinema_list',
        data:{
            'movieId': movie_id,
            'cityId': city_id
        },
        success:function (data) {
            console.log(data)
            if(data==0){
                cinema_list.empty();
                cinema_detail.empty();
            }else{
                data.forEach(function (cinema) {
                    list+="<li cid=\""+cinema['cinemaId']+"\" onclick=\"chooseCinemaDetail(this,'')\" class=''\"\">"+cinema['cinemaName']+"</li>";
                    detail+="<div class=\"detail_nav none\" id=\"cinema_info_"+cinema['cinemaId']+"\">\n" +
                        "   <span class=\"fr\">"+cinema['cinemaAddress']+cinema['cinemaTel']+"</span><strong>"+cinema['cinemaName']+"</strong>\n" +
                        "</div>"
                })
                cinema_list.html(list);
                cinema_detail.html(detail);
            }
        }
    });
}
/**
 * 影院选择 - 影片详情页
 * @param obj
 */
function chooseCinemaDetail(obj) {
    // 移除所有选中并选中当前 obj
    jQuery(obj).parent().children('li').attr('class', "");
    jQuery(obj).attr('class', 'date_current');
    // 改变当前选择影院
    var cinema_id = jQuery(obj).attr('cid');
    var movie_id = jQuery("#hid_movie_id").val();
    jQuery('#hid_cinema_id').val(cinema_id);
    jQuery('.detail_nav').hide();
    jQuery('#cinema_info_' + cinema_id).show();
    chooseDateByCMid(cinema_id, movie_id);
}

/**
 * 根据排期日期、影片获取影院 - 影片详情页
 */
function chooseCinemaByCDid(cid, mid, date) {
    var city_id = $('#city_now').attr('cityid');
    getTime(cid,mid,city_id,date);
}

/**
 * 排期日期选择 - 影片详情页
 * @param obj
 */
function choosePlanDateDetail(obj) {
    jQuery(obj).parent().children('li').attr('class', "");
    jQuery(obj).attr('class', 'date_current');
    var movie_id = jQuery("#hid_movie_id").val();
    var cinema_id = jQuery('#hid_cinema_id').val();
    var date = jQuery(obj).attr('time');
    jQuery('#hid_date_id').val(date);
    chooseCinemaByCDid(cinema_id, movie_id, date);
}

function choosePublicPlan(cinema_id, movie_id, date) {
    var plan_str = "";
    // 载入排期列表
    jQuery.ajax({
        type: "post",
        url: '/movie/PlanByCinema',
        data: "cinema_id=" + cinema_id + "&movie_id=" + movie_id + "&date=" + date,
        success: function (data) {
            if (0 == data) {
                jQuery("#plan_info").empty();
                showEasyMsg('暂无排期');
            } else {
                jQuery.each(data, function (i, n) {
                    var screen = (n.screenType) ? n.screenType : "";
                    if ('grey' == n.css) {
                        plan_str += '<li class="clearfix" style="background-color:#CCC;color:#FFF">' +
                            '<p><strong>' + n.featureTimeTake + '</strong>' + screen + ' ' + n.language + '</p>' +
                            '<span style="background-color:#CCC;color:#FFF" class="once_current" >' +
                            '暂停<br/>售票' +
                            '</span>' +
                            '</li>';
                    } else {
                        plan_str += '<li class="clearfix" onmouseover="HotMoviePlanMouse(\'' + n.qid + '\')" onmouseout="movieMouseout(\'' + n.qid + '\')">' +
                            '<p><strong>' + n.featureTimeTake + '</strong>' + screen + ' ' + n.language + '</p>' +
                            '<span class="price_plan" id="price_plan_' + n.qid + '" >' +
                            '<label>￥' + n.price + '</label>' +
                            '<font>￥' + n.marketPrice + '</font>' +
                            '</span>' +
                            '<span onclick="buyMovie(\'' + n.qid + '\')" class="once_current buy_plan" id="buy_plan_' + n.qid + '" style="display: none;cursor: pointer;">' +
                            '选座<br/>购票' +
                            '</span>' +
                            '</li>';
                    }

                });
                jQuery("#plan_info").empty().append(plan_str);
            }
        },
        dataType: "json"
    });
}

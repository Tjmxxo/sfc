﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="s" %>
<% String basepath = request.getContextPath();%>
<%@ page isELIgnored="false" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="<%=basepath%>/lib/html5shiv.js"></script>
<script type="text/javascript" src="<%=basepath%>/lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<%=basepath%>/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="<%=basepath%>/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="<%=basepath%>/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=basepath%>/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="<%=basepath%>/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="<%=basepath%>/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>影院管理</title>
<style type="text/css">
.Huiform select{
	font-size: 14px;
	width: 100px;
	height: 31px;
	line-height: 1.42857;
	padding-left: 26px;
	border-radius: 4px;
}
</style>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 影区管理 <span class="c-gray en">&gt;</span> 影院管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c">
		<form class="Huiform" method="post" action="" target="_self">
			<select id="city">
				<option style="text-align: center">全国</option>
				<c:forEach var="city" items="${cities}">
					<option value="${city.cityId}">${city.cityName}</option>
				</c:forEach>
			</select>
			<a href="#" id="citySubmit" class="" onclick="SubmitCity()"><i class="Hui-iconfont">&#xe665;</i> 按城市搜影院</a>
		</form>
	</div>
	<script type="text/javascript">
        function SubmitCity() {
            var city = $("#city").val();
            if("全国"==city){
                city = 0;
			}
			$("#citySubmit").attr("href","cinema_list?cityId="+city);
        }
		</script>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" class="btn btn-danger radius" id="deleteButton"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" id="movies_add" href="javascript:;" onclick="cinema_add('添加电影院','cinema_add.jsp')"> <i class="Hui-iconfont">&#xe600;</i> 添加电影院</a></span> <span class="r">共有数据：<strong>54</strong> 条</span> </div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-sort">
			<thead>
				<tr class="text-c">
					<th width="25"><input type="checkbox" name="" value=""></th>
					<th width="50">编号</th>
					<th width="50">城市</th>
					<th width="80">名称</th>
					<th width="200">描述</th>
					<th width="120">地址</th>
					<th width="100">电话</th>
					<th width="105">营业时间</th>
					<th>图片</th>
					<th width="50">操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="cinema" items="${cinemas}"><tr class="text-c">
					<td><input name="theSelected" type="checkbox" value=""></td>
					<td class="cinemaId">${cinema.cinemaId}</td>
					<td class="cinemaCity">${cinema.cityTbDTO.cityName}</td>
					<td>${cinema.cinemaName}</td>
					<td>${cinema.cinemaDescribe}</td>
					<td>${cinema.cinemaAddress}</td>
					<td>${cinema.cinemaTel}</td>
					<td>${cinema.cinemaBusinessHours}</td>
					<td style="width: 110px;height: 110px;">
						<a id="imgurl" onclick="picture_add('添加图片','picture_add.jsp',this)" href="javascript:;" style="width:100%;height:100%;border: 0px">
							<%--<i class="Hui-iconfont">&#xe600;</i>--%>
							<img src="${cinema.cinemaImg}" id="cinemaimg" style="width:100%;height:100%;"/>
						</a>
					</td>
					<td class="f-14 product-brand-manage"><a style="text-decoration:none" class="updateOne" onclick="updateOne(this)" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> <a style="text-decoration:none" class="ml-5" id="delectOne" onclick="deleteOne(this)" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
			</tr></c:forEach>
			</tbody>
		</table>
	</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=basepath%>/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=basepath%>/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=basepath%>/static/h-ui.admin/js/city/city_NUM.js"></script>
<script type="text/javascript" src="<%=basepath%>/static/h-ui.admin/js/city/SelectCity.js"></script>

<script type="text/javascript" src="<%=basepath%>/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="<%=basepath%>/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=basepath%>/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="<%=basepath%>/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=basepath%>/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basepath%>/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
$("#deleteButton").click(function () {
    var data = new Array();
    $("input[name='theSelected']:checked").each(function (){
        var id = $(this).parent().next().text();
        data.push(id);
    });
    location.href = "<%=basepath%>/cinema/cinema_delete?cinemaIds="+data;
});

function updateOne(ob) {
	var id = $(ob).parent().prev().prev().prev().prev().prev().prev().prev().prev().text();
//    window.location.href = "cinema_update?cinemaId="+id;
    cinema_update("更新影院","cinema_update?cinemaId="+id);
}
function deleteOne(ob) {
    var id = $(ob).parent().prev().prev().prev().prev().prev().prev().prev().prev().text();
//    window.location.href = "cinema_delete?cinemaIds="+id;
//    window.parent.location.reload();
}
//启动时锁定所在城市
$(document).ready(function() {
    var city = getUrlParam("cityId");
    if(null==city){
        city=0;
	}
    $("#city").val(city);
});
/*图片-添加*/
function picture_add(title,url,ob){
    $(ob).parent().parent().children().removeClass("theMarkId");
    $(ob).parent().prev().prev().prev().prev().prev().prev().prev().addClass("theMarkId");
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}
/*影院-添加*/
function cinema_add(title,url){
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}
/*影院-更新*/
function cinema_update(title,url){
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}
</script>
</body>
</html>
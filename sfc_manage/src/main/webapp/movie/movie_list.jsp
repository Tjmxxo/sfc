<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<title>电影管理</title>
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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 影区管理 <span class="c-gray en">&gt;</span> 电影管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px"href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c"> 日期范围：
		<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })" id="logmin" class="input-text Wdate" style="width:120px;">
		-
		<input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })" id="logmax" class="input-text Wdate" style="width:120px;">
		<input type="text" name="" id="" placeholder=" 电影名称" style="width:250px" class="input-text">
		<button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜电影</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" class="btn btn-danger radius" id="deleteButton"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" id="movies_add" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加电影</a></span> <span class="r">共有数据：<strong>54</strong> 条</span> </div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-sort">
			<thead>
				<tr class="text-c">
					<th width="25"><input type="checkbox" name="" value=""></th>
					<th width="30">编号</th>
					<th width="80">名称</th>
					<th width="30">时长</th>
					<th width="70">上映时间</th>
					<th width="60">导演</th>
					<th width="50">地区</th>
					<th width="50">语言</th>
					<th width="200">演员</th>
					<th width="80">类型</th>
					<th>海报图片</th>
					<th width="30">最低价格</th>
					<th width="30">会员最低价格</th>
					<th width="25">操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="movie" items="${movies}"><tr class="text-c">
					<td><input name="theSelected" type="checkbox" value=""></td>
					<td class="movieId">${movie.movieId}</td>
					<td>${movie.movieName}</td>
					<td>${movie.moviePlayTime}</td>
					<td>${movie.moviePublishTime}</td>
					<td>${movie.movieDirector}</td>
					<td>${movie.movieCountry}</td>
					<td>${movie.movieLanguage}</td>
					<td>${movie.movieActor}</td>
					<td>${movie.movieType}</td>
					<td style="width: 110px;height: 110px;">
						<a id="imgurl" onclick="movie_add('添加电影','poster_add.jsp',this)" href="javascript:;" style="width:100%;height:100%;border: 0px">
								<%--<i class="Hui-iconfont">&#xe600;</i>--%>
							<img src="${movie.moviePoster}" id="movieimg" style="width:100%;height:100%;"/>
						</a>
					</td>
					<td>${movie.minPrice}</td>
					<td>${movie.minVipPrice}</td>
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
    location.href = "<%=basepath%>/movie/movie_delete?movieIds="+data;
});
$("#movies_add").click(function () {
    location.href = "<%=basepath%>/movie/movie_add.jsp";
});

function updateOne(ob) {
	var id = $(ob).parent().prev().prev().prev().prev().prev().prev().prev().prev().text();
    window.location.href = "movie_update?movieId="+id;
}
function deleteOne(ob) {
    var id = $(ob).parent().prev().prev().prev().prev().prev().prev().prev().prev().text();
    window.location.href = "movie_delete?movieIds="+id;
}
//启动时锁定所在城市
$(document).ready(function() {
    var city = getUrlParam("cityId");
    if(null==city){
        city=0;
	}
    $("#city").val(city);
});
/*电影-添加*/
function movie_add(title,url,ob){
    $(ob).parent().parent().children().removeClass("theMarkId");
    $(ob).parent().prev().prev().prev().prev().prev().prev().prev().prev().prev().addClass("theMarkId");
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
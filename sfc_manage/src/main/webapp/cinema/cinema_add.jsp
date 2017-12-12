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
<link rel="Bookmark" href="<%=basepath%>//favicon.ico" >
<link rel="Shortcut Icon" href="<%=basepath%>//favicon.ico" />
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
<!--/meta 作为公共模版分离出去-->
<link href="<%=basepath%>/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="page-container">
	<form action="" method="" class="form form-horizontal" id="form-article-add">
		<input type="hidden" class="input-text" value="${cinema.cinemaId}" placeholder="" id="" name="cinemaId">
		<input type="hidden" class="input-text" value="${cinema.cityTbDTO.cityId}" placeholder="" id="cityid" name="cinemaId">
		<input type="hidden" class="input-text" value="${cinema.cinemaImg}"  id="cinemaImg" name="cinemaId">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">影院编号：</label>
			<div class="formControls col-xs-8 col-sm-9" style="line-height: 28px;">
				<div id="cinemaId">${cinema.cinemaId}</div>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">城市：</label>
			<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
				<select id="city" class="select" name="cityId"></select>
			</span> </div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${cinema.cinemaName}" placeholder="" id="cinemaName" name="cinemaName">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">地址：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${cinema.cinemaAddress}" placeholder="" id="cinemaAddress" name="cinemaAddress">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">电话：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${cinema.cinemaTel}" placeholder="" id="cinemaTel" name="cinemaTel">
			</div>
		</div>
		<div><input type="hidden" value="${cinema.cinemaBusinessHours}" id="businessHour" name="cinemaBusinessHours"></div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">开门时间：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" value="" onfocus="WdatePicker({ dateFmt:'HH:mm:ss'})" onchange="MergeTime()" id="datemin" class="input-text Wdate" style="width:180px;">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">关门时间：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" value="" onfocus="WdatePicker({ dateFmt:'HH:mm:ss'})" onchange="MergeTime()" id="datemax" class="input-text Wdate" style="width:180px;">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">描述：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<textarea id="cinemaDescribe" value="${cinema.cinemaDescribe}" name="cinemaDescribe" cols="" rows="" class="textarea"  placeholder="说点什么...最少输入10个字符" datatype="*10-100" dragonfly="true" nullmsg="备注不能为空！" onKeyUp="$.Huitextarealength(this,200)">${cinema.cinemaDescribe}</textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/200</p>
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button id="article_save_submit" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
				<button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="<%=basepath%>/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=basepath%>/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="<%=basepath%>/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="<%=basepath%>/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->


<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=basepath%>/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basepath%>/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="<%=basepath%>/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="<%=basepath%>/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="<%=basepath%>/lib/webuploader/0.1.5/webuploader.min.js"></script> 
<script type="text/javascript" src="<%=basepath%>/lib/ueditor/1.4.3/ueditor.config.js"></script>
<script type="text/javascript" src="<%=basepath%>/lib/ueditor/1.4.3/ueditor.all.min.js"> </script>
<script type="text/javascript" src="<%=basepath%>/lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="<%=basepath%>/static/h-ui.admin/js/city/city_NUM.js"></script>
<script type="text/javascript" src="<%=basepath%>/static/h-ui.admin/js/city/SelectCity.js"></script>
<script type="text/javascript" src="<%=basepath%>/static/h-ui.admin/js/post.js"></script>
<script type="text/javascript">
	function article_save() {
		window.parent.location.reload();
	}
	//结合开始时间和结束时间
    function MergeTime() {
        var arr = new Array();
        arr[0] = $("#datemin").val();
        arr[1] = $("#datemax").val();
        var time = arr[0]+"-"+arr[1];
        $("#businessHour").val(time);
    }
    $(document).ready(function(){
        //赋值给开始时间和结束时间
		var time = $("#businessHour").val();
		var arr = time.split("-");
		$("#datemin").val(arr[0]);
        $("#datemax").val(arr[1]);
        //赋值给城市
		var cityid = $("#cityid").val();
        //填充城市框
        $(window.parent.document).find("#city option").each(function () {
            $("#city").append("<option value="+$(this).val()+">"+$(this).text()+"</option>");
            $("#city option[value="+cityid+"]").attr("selected",true);
        });
	});

//  提交表单
//	input中不能转换cityid而且下拉框的值获取不准确
    $("#article_save_submit").click(function(){
		var cinemaid = $("#cinemaId").text();
		var cityid = $("#city option:selected").val();
		var cinamename = $("#cinemaName").val();
		var	cinemaaddress = $("#cinemaAddress").val();
		var cinematel = $("#cinemaTel").val();
        var businesshour =$("#businessHour").val();
		var cinemadescribe = $("#cinemaDescribe").val();
		var cinemaImg = $("#cinemaImg").val();
		var cinema = {
            "cinemaId" : cinemaid,
            "cinemaName": cinamename,
            "cinemaAddress": cinemaaddress,
            "cinemaTel": cinematel,
            "cinemaBusinessHours": businesshour,
            "cinemaDescribe": cinemadescribe,
			"cinemaImg" : cinemaImg,
            "cityId" : cityid
		}
		post("<%=basepath%>/cinema/cinema_add",cinema);
        window.parent.location.reload();
    });
</script>
</body>
</html>
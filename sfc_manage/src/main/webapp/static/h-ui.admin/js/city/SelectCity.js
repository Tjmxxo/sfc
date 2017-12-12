//获取url中的参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = encodeURI(window.location.search).substr(1).match(reg); //匹配目标参数
    if (r != null) return decodeURI(r[2]); //增加UTF-8解码处理。
    return null; //返回参数值
}
//判断是否为数字
function isNumber(value) {
    var patrn = /^[0-9]*$/;
    if (patrn.exec(value) == null || value == "") {
        return false
    } else {
        return true
    }
}

function numberAndCity(value) {
    var city = {
        "0": "全国",
        "1": "上海",
        "2": "北京"
    }
    if (isNumber(value)) {
        return city[value];
    } else {
        for (var key in city) {
            if (value == city[key]) {
                return key;
            }
        }
    }
}
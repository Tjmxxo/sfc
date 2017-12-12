function post(URL, PARAMS) {
    var temp = document.createElement("form");
    temp.action = URL;
    temp.method = 'POST';
    temp.style.display = "none";
    for (var x in PARAMS) {
        var opt = document.createElement("textarea");
        opt.name = x;
        opt.value = PARAMS[x];
        // alert(opt.name)
        temp.appendChild(opt);
    }
    document.body.appendChild(temp);
    temp.submit();
    return temp;
}

function request(URL, PARAMS, TYPE) {
    var temp = document.createElement("form");
    temp.action = URL;
    temp.method = TYPE;
    temp.style.display = "none";
    if (PARAMS != 0) {
        for (var x in PARAMS) {
            var opt = document.createElement("textarea");
            opt.name = x;
            opt.value = PARAMS[x];
            // alert(opt.name)
            temp.appendChild(opt);
        }
    }
    document.body.appendChild(temp);
    temp.submit();
    return temp;
}
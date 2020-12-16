$(function () {
    $.ajaxSetup({
        global: true,
        complete:function (XMLHttpRequest,textStatus) {
            var response = XMLHttpRequest.responseJSON;
            if(response.status == 44455){
                location.href = "/pages/nologin.html";
            }
            if(response.status == 40104){
                alert("页面未知错误");
            }
        }
    });
});

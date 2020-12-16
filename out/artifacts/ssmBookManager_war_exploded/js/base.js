$(function () {
    $.ajaxSetup({
        global: true,
        complete:function (XMLHttpRequest,textStatus) {
            var response = XMLHttpRequest.responseJSON;
            if(response.status == 44455){
                location.href = "/pages/nologin.html";
            }
        }
    });
});

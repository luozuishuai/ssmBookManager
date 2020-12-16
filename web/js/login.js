$(function () {

    //登录按钮点击时
    $(".loginBtn").click(function () {
        $.post("/doLogin",$("#form").serialize(),function (response) {
            if(response.status == 20000){
                location.replace("/pages/book.html");
            }else{
                $("#tips").show();
                $("#tips").text(response.message);
            }
        });
    });






});
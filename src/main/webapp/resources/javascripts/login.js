jQuery(document).ready(function($){

    $("#login_submit").click(function(){
        var username = $("#j_username").val();
        if(username == undefined || username.length == 0){
            $("#errmsg").text("请输入用户名");
            $(".error_message").removeClass("hide");
            return ;
        }

        var password = $("#j_password").val();
        if(password == undefined || password.length == 0){
            $("#errmsg").text("请输入密码");
            $(".error_message").removeClass("hide");
            return ;
        }
        $("#login_form").submit();
    });

});

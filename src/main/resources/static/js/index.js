//一级目录数据

var date;
var data=[];
// 初始化内容
function init(){
    //获取当前用户
    $.ajax({
        //请求方式
        type: "get",
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",
        //请求地址
        url: "/findUserInSystem",
        //请求成功
        success: function (result) {
            console.log("用户信息:",result.data);
            var message = result.data;
            if(message.photo!=null){
                $("#userImg").attr('src', message.photo);
            }
            $("#userName").text(message.nickName);
        },
        //请求失败，包含具体的错误信息
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
        }
    });

    $.ajax({
        //请求方式
        type: "post",
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",
        //请求地址
        url: "/findProductFromCartCount",
        //请求成功
        success: function (result) {
            console.log("购物车数量：", result.data);
            $("#cartCount").text('('+result.data+')');
        },
        //请求失败，包含具体的错误信息
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
        }
    });


    $.ajax({
        //请求方式
        type : "get",
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",
        //请求地址
        url : "findCategory1",
        //请求成功
        success : function(result) {
            console.log(result);
            //商品一级目录
            data = result.data;

            $("#one").text(data[0].category1Name);
            $("#two").text(data[1].category1Name);
            $("#three").text(data[2].category1Name);
            $("#four").text(data[3].category1Name);
            $("#five").text(data[4].category1Name);
            $("#six").text(data[5].category1Name);
            $("#senven").text(data[6].category1Name);
            $("#eight").text(data[7].category1Name);
        },
        //请求失败，包含具体的错误信息
        error : function(e){
            console.log(e.status);
            console.log(e.responseText);
        }
    });

}





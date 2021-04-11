/* JS Document */

/******************************

[Table of Contents]

1. Vars and Inits
2. Set Header
3. Init Search
4. Init Menu
5. Init Isotope


******************************/

$(document).ready(function()
{
	"use strict";


	/* 

	1. Vars and Inits

	*/

	var header = $('.header');
	var hambActive = false;
	var menuActive = false;


	setHeader();

	$(window).on('resize', function()
	{
		setHeader();
	});

	$(document).on('scroll', function()
	{
		setHeader();
	});

	initSearch();
	initMenu();
	initIsotope();

	/* 

	2. Set Header

	*/

	function setHeader()
	{
		if($(window).scrollTop() > 100)
		{
			header.addClass('scrolled');
		}
		else
		{
			header.removeClass('scrolled');
		}
	}

	/* 

	3. Init Search

	*/

	function initSearch()
	{
		if($('.search').length && $('.search_panel').length)
		{
			var search = $('.search');
			var panel = $('.search_panel');

			search.on('click', function()
			{
				panel.toggleClass('active');
			});
		}
	}

	/* 

	4. Init Menu

	*/

	function initMenu()
	{
		if($('.hamburger').length)
		{
			var hamb = $('.hamburger');

			hamb.on('click', function(event)
			{
				event.stopPropagation();

				if(!menuActive)
				{
					openMenu();
					
					$(document).one('click', function cls(e)
					{
						if($(e.target).hasClass('menu_mm'))
						{
							$(document).one('click', cls);
						}
						else
						{
							closeMenu();
						}
					});
				}
				else
				{
					$('.menu').removeClass('active');
					menuActive = false;
				}
			});

			//Handle page menu
			if($('.page_menu_item').length)
			{
				var items = $('.page_menu_item');
				items.each(function()
				{
					var item = $(this);

					item.on('click', function(evt)
					{
						if(item.hasClass('has-children'))
						{
							evt.preventDefault();
							evt.stopPropagation();
							var subItem = item.find('> ul');
						    if(subItem.hasClass('active'))
						    {
						    	subItem.toggleClass('active');
								TweenMax.to(subItem, 0.3, {height:0});
						    }
						    else
						    {
						    	subItem.toggleClass('active');
						    	TweenMax.set(subItem, {height:"auto"});
								TweenMax.from(subItem, 0.3, {height:0});
						    }
						}
						else
						{
							evt.stopPropagation();
						}
					});
				});
			}
		}
	}

	function openMenu()
	{
		var fs = $('.menu');
		fs.addClass('active');
		hambActive = true;
		menuActive = true;
	}

	function closeMenu()
	{
		var fs = $('.menu');
		fs.removeClass('active');
		hambActive = false;
		menuActive = false;
	}

	/* 

	5. Init Isotope

	*/

	function initIsotope()
	{
		var sortingButtons = $('.product_sorting_btn');
		var sortNums = $('.num_sorting_btn');


	}

});

//一级目录数据

var date;
var data=[];
function init(){
    // 初始化内容
    $.ajax({
        //请求方式
        type : "get",
        //请求的媒体类型
        contentType: "application/json;charset=UTF-8",
        //请求地址
        url : "/findCategory1",
        //请求成功
        success : function(result) {
            console.log("一级目录：",result);
            //商品一级目录
            data = result.data;

            $("#theOne").text(data[0].category1Name);
            $("#theTwo").text(data[1].category1Name);
            $("#theThree").text(data[2].category1Name);
            $("#theFour").text(data[3].category1Name);
            $("#theFive").text(data[4].category1Name);
            $("#theSix").text(data[5].category1Name);
            $("#theSenven").text(data[6].category1Name);
            $("#theEight").text(data[7].category1Name);


            console.log("=====================================================和谐线");
            /**
             * 初始化商品请求
             */

            //一级目录id
            var catId = $("#theFirstId").text();
            var array = $("#theList").text();
            console.log("第一个：",array);

            var req = {
                "category2Id": array,
                "category1Id": catId,
                "pageNum":1
            };
            $.ajax({
                type: "post",
                //数据，json字符串
                data: JSON.stringify(req),
                contentType: "application/json;charset=UTF-8",
                url: "/findProduct",
                success: function (result) {
                    console.log("初始化result:", result);
                    pageNum=result.data.pageNum;
                    var arr=result.data.list;
                    console.log("arr",arr);
                    $("#pages").text(result.data.pages);
                    $("#total").text(result.data.total);
                    $("#homeName").text(arr[0].productName);
                    $("#homeDescription").text(arr[0].outline);
                    $("#homeImg").css("background-image","url(" + arr[0].imgSrc + ")");
                    for(var i=0;i<arr.length;i++){
                        console.log("productName:",arr[i].productName);
                        $("#productName").text(arr[i].productName);
                    }

                    $("#product").html("");
                    for(var i=0;i<arr.length;i++){
                        console.log("arr[i].productId:",arr[i].productId);
                        console.log("productName:",arr[i].productName);
                        var str = "  <div style=\"position: relative; height: auto;float:left; padding-right: 50px;\" class=\"product\">\n" +
                            "                            <div class=\"product_image\"><img src= "+arr[i].imgSrc+" alt='/images/shopping.svg'  height=\"212.5\"; width=\"212.5\"; ></div>\n" +
                            "                            <div class=\"product_extra product_new\"><a>"+arr[i].description+"</a></div>\n" +
                            "                            <div class=\"product_content\">\n" +
                            "                                <div class=\"product_title\"><a href='/findProductDetail/"+arr[i].productId+"'>"+arr[i].productName+"</a></div>\n" +
                            "                                <div class=\"product_price\">￥"+arr[i].salePrice+"</div>\n" +
                            "                            </div>\n" +
                            "                        </div>";
                        console.log("str111111111111:",str);
                        $("#product").append(str);
                    }
                },
                error: function (e) {
                    console.log(e.status);
                    console.log(e.responseText);
                }
            });

        },
        //请求失败，包含具体的错误信息
        error : function(e){
            console.log(e.status);
            console.log(e.responseText);
        }
    });





}





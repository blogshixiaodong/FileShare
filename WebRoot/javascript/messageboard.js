/**
 * 
 */
$(document).ready(function(){
	getMessageBoardList();
    $(".dropdown").hover(            
        function() {
            $('.dropdown-menu', this).not('.in .dropdown-menu').stop(true,true).slideDown("400");
            $(this).toggleClass('open');        
        },
        function() {
            $('.dropdown-menu', this).not('.in .dropdown-menu').stop(true,true).slideUp("400");
            $(this).toggleClass('open');       
        }
    );
});

/**
 * 
 */

$(function(){
	//显示删除留言标签
//	$('#message-board .list').on("mouseover","li",function() {
//		var rowno = $(this).find("p").text();
//		$(this).find('.del').css('display','block');
//	});

	$("#message-board .list").on("click", "li .del", function() {
		var delLi = $(this).parents("li");
		var rowno = delLi.find("p").text();
		var id = delLi.find(".userName a").text();
		$.ajax({
			type: "POST",
			url: "servlet/MessageDelete",
			dataType: "json",
			data: {
				"rowno": rowno,
				"id": id
			},
			success: function(json) {
				if(json === 0) {
					delLi.remove();
				} else if(json === 1) {
					alert("不能删除其他用户留言!");
				} else if(json == 2) {
					alert("请登陆后进行操作!");
					location.href = "login.jsp";
				}
			},
			error: function() {
				alert("ERROE");
			}
		});
	});

	//隐藏删除留言标签
//	$('#message-board .list').on("mouseout","li",function() {
//		$(this).find('.del').css("display",'none');
//	});
//	

	
	//点击头像选中
	$('#input-message .form-group img').click(function(){
		//移除所有
		$('#input-message img.active').removeClass("active");
		//单独添加
		$(this).addClass('active');
	});
	
	//提交验证/插入节点
	$('#sendMessage button').click(function(){
		//内容非空/长度判断
		var $textarea = $('#input-message textarea').val();
		if(null == $textarea || "" == $textarea) {
			alert("请输入内容!");
			return false;
		} else if($textarea.length > 140) {
			$(this).siblings('p').css('color','red');
			return false;
		} else {
			$(this).siblings('p').css('color','black');
		}
		
		var oDate = new Date();
		var $name = $('#input-message input').val();
		var str = encodeURI($textarea);
		var strs = encodeURI(str);
		//var headurl = $('#message img.active').attr('src');   用户头像
		$.ajax({
			type: "POST",
			url: "servlet/MessageInsert",
			dateType: "json",
			data: {
				"response": encodeURI($textarea)
			},
			success: function(json) {
				var dataObj = JSON.parse(json);
				var li = '<li class="clearfix list-group-item">';
					li +=	'<p>' + dataObj[0].rowno  +'</p>';
					li += 	'<div class="user">';
					li +=    	'<img src="image/default_head.ico" alt="pic" />';
					li +=    	'<div class="userName">';
					li +=   			'<a href="javascript:;">' + $name + '</a>:';
					li +=        '</div>';
					li +=   '</div>';
					li += 	'<div class="content">';
					li +=        '<div class="msgInfo">' + $textarea + '</div>';
					li +=        '<div class="content-bar">';
					li +=        	'<span>response at: ' + dateFormat(new Date().getTime()) + '</span>';
	                li +=			'<div class="pull-right">';
	                li +=				'<span>对我有用<b>[0]</b></span>' + "|   ";
	                li +=				'<span>丢个板砖<b>[0]</b></span>' + "|   ";
	                li +=				'<span>举报</span>' + "|   ";
	                li +=     			'<a class="del" href="javascript:;">delete</a>'  + "|   ";
	                li +=				'<span>#' + dataObj[0].rowno + '</span>';
	                li +=        	'</div>';
					li +=    	'</div>';
					li +=    '</div>'; 
					li += '</li>';  
				var insli = $(li);   
				var oUl = $('#message-board ul');
				oUl.prepend(insli);	
				$('#input-message textarea').val("");
				$("#sendMessage p strong").text(140);
			}
		});
	});
	
	//文本长度限制
	$("#input-message textarea").keyup(function() {
		var inputLength = $("#input-message textarea").val().length;
		$("#sendMessage p strong").text(140 - inputLength);
	});
});


function dateFormat(longTypeDate) {
	var dateString = "";
	var date = new Date();
	date.setTime(longTypeDate);
	dateString += date.getFullYear() + "-";
	dateString += date.getMonth() + 1 + "-";
	dateString += date.getDate() + " ";
	dateString += date.getHours() + ":";
	dateString += date.getMinutes() + ":";
	dateString += date.getSeconds() + "";
	return dateString;
}


function getMessageBoardList() {
	$.ajax({
		type: "GET",
		dataType: "json",
		url: "servlet/MessageList",
		success: function(json) {
			$("#message-board ul").children().remove();
			var s = '';
			for(var i = 0; i < json.length; i++) {
				s += '<li class="clearfix list-group-item">';
				s +=    '<p>' + json[i].rowno  +'</p>';
				s += 	'<div class="user">';
				s +=		'<img src="image/default_head.ico" alt="pic" />';
				s +=		'<div class="userName">';
                s +=   			'<a href="javascript:;">' + json[i].id + '</a>';
                s +=        '</div>';
				s +=	'</div>';
				s += 	'<div class="content">';
                s +=        '<div class="msgInfo">' + json[i].response + '</div>';
                s +=        '<div class="content-bar">';
                s +=        	'<span>response at: ' + dateFormat(json[i].time) + '</span>';
                s +=            '<div class="pull-right">';
	            s +=				'<span>对我有用<b>[0]</b></span>' + "|   ";
	            s +=				'<span>丢个板砖<b>[0]</b></span>' + "|   ";
	            s +=				'<span>举报</span>' + "|   ";
	            s +=     			'<a class="del" href="javascript:;">delete</a>'  + "|   ";
	            s +=				'<span>#' + json[i].rowno + '</span>';
	            s +=        	'</div>';
                s +=    	'</div>';
                s +=    '</div>'; 
                s += '</li>';   
			}
			//添加所有节点
			$("#message-board ul").append(s);		
		}
	});
};





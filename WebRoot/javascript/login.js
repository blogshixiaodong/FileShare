
//提交前表单校验
function checkForm(formData, jqForm, options) {
	var account = formData[0];
	var password = formData[1];
	var checkCode = formData[2];
	var errorString = null;
	if(account.value == "") {
		errorString = "请输入账号!";
	} else if(password.value ==  "") {
		errorString = "请输入密码!";
	} else if(checkCode.value == "") {
		errorString = "请输入验证码!";
	}
	if(errorString != null) {
		showError($("#output"), errorString);
		return false;
	}
	return true;
};

//显示错误
function showError(output, errorString) {
	output.removeClass(' alert alert-success');
	output.addClass("alert alert-danger animated fadeInUp").html(errorString);
};

function showSuccess(output, username, info) {
	output.addClass("alert alert-success animated fadeInUp").html("Welcome back " + "<span style='text-transform:uppercase'>" + username + "</span>");
	output.removeClass(' alert-danger');
};

function skipIndex() {
	 $("input").css({
		"height" : "0",
		"padding" : "0",
		"margin" : "0",
		"opacity" : "0"
	});
	$(".checkCode").hide();
	$(".password a").hide();

	// change button text
	$('button[type="submit"]').html("回到首页(3秒后自动跳转)").removeClass("btn-info").addClass("btn-default").click(function() {
		location.href = "index.jsp";
	});
	setTimeout(function(){//两秒后跳转   
        location.href = "index.jsp";
    },3000);
};

// server端返回对表单的响应的回调函数
function showResponse(responseText, statusText) {
	var output = $("#output");
	if(statusText != "success") {
		showError(output, "服务端出错,请重试!");
	} else {
		if(responseText == "-1") {
			showError(output, "验证码错误,请重新输入!");
		} else if(responseText == "-2") {
			showError(output, "账号或密码错误,请重新输入!");
		} else {
			showSuccess(output, responseText);
			skipIndex();
		}
	}
};

$(function(){
	var loginForm = $('form[name="login"]');
	var options = {
		beforeSubmit: checkForm,
		success: showResponse
	};
	loginForm.ajaxForm(options);
});
<%
/**
* @author    ShiXiaodong
* @date      2017/12/28
* @describe  登陆界面
* @version   v1.0
*/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>user login</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="css/login.css">
</head>
<body>
	<div class="container">
		<div class="login-container">
			<div id="output">${request.error}</div>
			<div class="avatar"></div>
			<div class="form-box">
				<form name="login" action="servlet/LoginCheck" method="POST">
					<input name="account" type="text" placeholder="邮箱/手机号/账号" />
					<div class="password">
						<input name="password" type="password" placeholder="密码" />
						<a href="www.baidu.com">忘记密码</a>
					</div>
					
					<div class="checkCode">
						<input name="checkCode" type="text" placeholder="输入右侧验证码"/>
						<img src="servlet/CheckCode"/>
						<div class="clear"></div>
					</div>
					<button class="btn btn-info btn-block login" type="submit">Login</button>
				</form>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="jQuery/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="jQuery/jquery.form.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="javascript/login.js"></script>
	
</body>
</html>
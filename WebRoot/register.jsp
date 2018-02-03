<%
/**
* @author    ShiXiaodong
* @date      2018/01/20
* @describe  注册界面
* @version   v1.0
*/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>register login</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="css/register.css">
</head>
<body>
	<div class="container">
		<div class="register-container">
			<div id="output">${request.error}</div>
			<div class="avatar"></div>
			<div class="form-box">
				<form name="register" action="servlet/RegisterCheck" method="POST">
					<input name="account" type="text" placeholder="邮箱/手机号/账号" />
					<input name="password" type="password" placeholder="密码" />	
					<input name="passwords" type="password" placeholder="确认密码" />
					<div class="clear"></div>
					<button class="btn btn-info btn-block register" type="submit">Register</button>
				</form>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="jQuery/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="jQuery/jquery.form.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="javascript/register.js"></script>
	
</body>
</html>
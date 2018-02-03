<%
/**
* @author    ShiXiaodong
* @date      2018/01/16
* @describe  文件上传界面
* @version   v1.0
*/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>File Upload</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="css/login.css">
</head>
<body>
    <div class="container">
		<nav class="navbar navbar-inverse">
			<div class="navbar-header">
				<button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".js-navbar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span> <span class="icon-bar"></span> 
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Share Point</a>
			</div>
	
			<div class="collapse navbar-collapse js-navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="index.jsp">文件下载</a></li>
					<li><a href="file_upload.jsp">文件共享</a></li>
					<li><a href="messageboard.jsp">留言板</a></li>
					<li><a href="#">更新记录</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">		
							${sessionScope.loginUser == null ? "登陆" : sessionScope.loginUser}
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">Action</a></li>
							<li><a href="#">Another action</a></li>
							<li><a href="#">Something else here</a></li>
							<li class="divider"></li>
							<li><a href="#">Separated link</a></li>
						</ul>
					</li>
					<li><a href="#">注册</a></li>
				</ul>
			</div>
		</nav>
		<div class="panel panel-success upload">
			<div class="panel-heading">
				<div class="panel-title">文件上传</div>
			</div>
			<div class="panel-body">
				《请勿上传大于5M的文件》
				<form action="servlet/FileUpload" method="post" enctype="multipart/form-data" class="upload">
					<input type="file" name="file" /> <br />
					<input type="hidden" name="name" value="sxd" />
					<button type="submit" onclick="return uploadFileCheck();">上传</button>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="jQuery/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="javascript/file_upload.js"></script>
</body>
</html>

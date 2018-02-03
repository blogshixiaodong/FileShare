<%
/**
* @author    ShiXiaodong
* @date      2018/01/18
* @describe  留言板
* @version   v1.0
*/
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>留言板</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="css/messageboard.css">
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
		<div id="left" class="col-sm-12">
				<div id="input-message" class="panel panel-info" >
					<div class="panel-heading">
						<div class="panel-title">
							说点什么吧！
						</div>
					</div>
					<div class="panel-body">
						<div id="login-user">
							<div class="col-sm-0">
								<!-- 保存当前登陆用户  -->
								<input type="hidden" value="${sessionScope.loginUser}" />
							</div>
						</div>
						<div class="form-group" id="message">
							<textarea rows="7" class="form-control" style="resize:none" maxlength="140" required="true" ></textarea>
						</div>								
						<div id="sendMessage" class="col-sm-offset-9 col-sm-3">
							<p class="pull-left">还能输入<span><strong>140</strong> </span>个字</p>
							<button class="pull-right btn btn-success" type="button">发送</button>
						</div>
						<div class="clearfix"></div>
					</div>	
				</div>
				<!-- input-message END -->
				
				<div id="message-board" class="panel panel-warning">
					<div class="panel-heading">
						<div class="panel-title">
							大家都在说
						</div>
					</div>
					<div class="panel-body">
						<ul class="list list-group">
							<!-- response list -->
						</ul>
					</div>
				</div>
				<!-- message-board END -->
			</div>
			<!--main left end！-->
	</div>
	<script type="text/javascript" src="jQuery/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="javascript/messageboard.js"></script>
</body>
</html>

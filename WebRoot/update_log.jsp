<%
	/**
	 * @author    ShiXiaodong
	 * @date      2018/01/21
	 * @describe  更新日志
	 * @version   v1.0
	 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>更新日志</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="css/update_log.css">
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
					<li><a href="#">文件下载</a></li>
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
					<li><a href="register.jsp">注册</a></li>
				</ul>
			</div>
		</nav>
		<!-- Timeline -->
		<div class="timeline">
			<div class="line text-muted"></div>

			<div class="separator text-muted">
				<time>2017-11-20</time>
			</div>
			<!-- Panel -->
			<article class="panel panel-primary"> 
				<div class="panel-heading icon">
					<i class="glyphicon glyphicon-plus"></i>
				</div>

				<div class="panel-heading">
					<h2 class="panel-title">1.3.1</h2>
				</div>
				<div class="panel-body">修复session过期情况下删除留言失败的BUG</div>
			</article>
			
			<div class="separator text-muted">
				<time>2017-11-18</time>
			</div>
			
			<!-- Panel -->
			<article class="panel panel-success"> 
				<div class="panel-heading icon">
					<i class="glyphicon glyphicon-plus"></i>
				</div>
				<div class="panel-heading">
					<h2 class="panel-title">1.3.0</h2>
				</div>
				<div class="panel-body">增加留言版功能 </div>
			</article>

			<div class="separator text-muted">
				<time>2017-11-3</time>
			</div>
			<!-- Panel -->
			<article class="panel panel-info"> 
				<div class="panel-heading icon">
					<i class="glyphicon glyphicon-plus"></i>
				</div>
				<div class="panel-heading">
					<h2 class="panel-title">1.2.4</h2>
				</div>
				<div class="panel-body">
					修改文件上传下载逻辑
				</div>
			</article>
			
			<div class="separator text-muted">
				<time>2017-10-29</time>
			</div>
			<!-- Panel -->
			<article class="panel panel-warning"> 
				<div class="panel-heading icon">
					<i class="glyphicon glyphicon-plus"></i>
				</div>
				<div class="panel-heading">
					<h2 class="panel-title">1.2.3</h2>
				</div>
				<div class="panel-body">
					日志时间格式调整（TimeUtil类）
				</div>
			</article>
			
			<div class="separator text-muted">
				<time>2017-10-28</time>
			</div>
			<!-- Panel -->
			<article class="panel panel-danger"> 
				<div class="panel-heading icon">
					<i class="glyphicon glyphicon-plus"></i>
				</div>
				<div class="panel-heading">
					<h2 class="panel-title">1.2.2</h2>
				</div>
				<div class="panel-body">
					<ul class="list-group">
						<li class="list-group-item">添加用户可以删除自己上传的文件</li>
						<li class="list-group-item">添加上传文件检查</li>
						<li class="list-group-item">优化：jsp页面动态生成节点逻辑，使用Jquery框</li>
					</ul>
				</div>
				
			</article>
			
			
			<div class="separator text-muted">
				<time>2017-10-26</time>
			</div>
			<!-- Panel -->
			<article class="panel panel-primary"> 
				<div class="panel-heading icon">
					<i class="glyphicon glyphicon-plus"></i>
				</div>
				<div class="panel-heading">
					<h2 class="panel-title">1.2.1</h2>
				</div>
				<div class="panel-body">
					<ul class="list-group">
						<li class="list-group-item">添加session超时的日志记录</li>
						<li class="list-group-item">添加上传文件人Id的显示</li>
					</ul>
				</div>
				
			</article>
			
			
			<div class="separator text-muted">
				<time>2017-10-23</time>
			</div>
			<!-- Panel -->
			<article class="panel panel-success"> 
				<div class="panel-heading icon">
					<i class="glyphicon glyphicon-plus"></i>
				</div>
				<div class="panel-heading">
					<h2 class="panel-title">1.2.0</h2>
				</div>
				<div class="panel-body">
					添加登录过滤功能，防止在未登录的情况访问特定页面
				</div>
				
			</article>
			
			
			
			<div class="separator text-muted">
				<time>2017-10-22</time>
			</div>
			<!-- Panel -->
			<article class="panel panel-info"> 
				<div class="panel-heading icon">
					<i class="glyphicon glyphicon-plus"></i>
				</div>
				<div class="panel-heading">
					<h2 class="panel-title">1.2.0</h2>
				</div>
				<div class="panel-body">
					<ul class="list-group">
						<li class="list-group-item">修复文件上传下载路径错误（本地环境与服务器环境不同，修改配置文件参数）</li>
						<li class="list-group-item">修复文件名包含中文时，文件名异常，文件内容为空</li>
						<li class="list-group-item">（文件名异常:编码问题，文件为空：不存在解码后的文件（乱码名）</li>
					</ul>
				</div>
			</article>
			
			
			
			<div class="separator text-muted">
				<time>2017-10-18</time>
			</div>
			<!-- Panel -->
			<article class="panel panel-warning"> 
				<div class="panel-heading icon">
					<i class="glyphicon glyphicon-plus"></i>
				</div>
				<div class="panel-heading">
					<h2 class="panel-title">1.1</h2>
				</div>
				<div class="panel-body">
					上线文件上传下载功能   
				</div>
			</article>
			
			<div class="separator text-muted">
				<time>2017-10-16</time>
			</div>
			<!-- Panel -->
			<article class="panel panel-danger"> 
				<div class="panel-heading icon">
					<i class="glyphicon glyphicon-plus"></i>
				</div>
				<div class="panel-heading">
					<h2 class="panel-title">1.0</h2>
				</div>
				<div class="panel-body">
					上线系统登录功能
				</div>
			</article>
			------------
			
			<!-- Panel -->
			<article class="panel panel-info panel-outline">
				<div class="panel-heading icon">
					<i class="glyphicon glyphicon-info-sign"></i>
				</div>
				<div class="panel-body">That is all.</div>
			</article>

		</div>
	</div>

	<script type="text/javascript" src="jQuery/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
</body>
</html>

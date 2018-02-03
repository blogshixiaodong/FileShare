<%
	/**
	 * @author    ShiXiaodong
	 * @date      2017/12/30
	 * @describe  主页面
	 * @version   v1.0
	 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>main page</title>

<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="css/index.css">
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
		<div id="left" class="col-sm-12">
			<div class="panel panel-warning download">
				<div class="panel-heading">
					<div class="panel-title">文件下载</div>
					<div class="pull-right">
						<button class="btn btn-default btn-xs btn-filter">
							<span class="glyphicon glyphicon-filter"></span>刷新
						</button>
					</div>
				</div>
				<div class="panel-body">
					<table class="table table-condensed table-bordered table-striped row text-center">
						<thead class="col-md-12">
							<tr class="filters row">
								<th class="col-md-1"><input style="text-align:center" type="text" class="form-control" placeholder="#" disabled ></th>
								<th class="col-md-4"><input style="text-align:center" type="text" class="form-control" placeholder="文件名" disabled> </th>
								<th class="col-md-2"><input style="text-align:center" type="text" class="form-control" placeholder="上传作者" disabled ></th>
								<th class="col-md-1"><input style="text-align:center" type="text" class="form-control" placeholder="类型" disabled ></th>
								<th class="col-md-2"><input style="text-align:center" type="text" class="form-control" placeholder="上传时间" disabled ></th>
								<th class="col-md-2"><input style="text-align:center" type="text" class="form-control" placeholder="操作" disabled ></th>
							</tr>
						</thead>

						<tbody id="fileList" class="col-md-12">
							<!-- 列表 -->
						</tbody>
					</table>
				</div>
			</div>
		</div>
<!--	
		<div id="right" class="col-sm-3">
		
			<div class="panel panel-info">
					<div class="panel-heading">
						<div class="panel-title">
							文章搜索
						</div>
					</div>
					<div class="panel-body">
						<div class="form-horizontal">
							<div class="form-group">
								<div class="input-group">
									<input type="text" class="form-control input-lg">
									<span class="input-group-addon btn btn-primary">搜索</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="panel panel-info">
					<div class="panel-heading">
						<div class="panel-title">
							阅读排行
						</div>
					</div>
					<div class="panel-body">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>#</th>
									<th>文章</th>
									<th>阅读量</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td>数据结构</td>
									<td>423</td>
								</tr>
								<tr>
									<td>2</td>
									<td>Javascript</td>
									<td>296</td>
								</tr>
								<tr>
									<td>3</td>
									<td>C/C++</td>
									<td>286</td>
								</tr>
								<tr>
									<td>4</td>
									<td>Java</td>
									<td>241</td>
								</tr>
								<tr>
									<td>5</td>
									<td>Bootstrap</td>
									<td>198</td>
								</tr>
								<tr>
									<td>7</td>
									<td>HTML/CSS</td>
									<td>168</td>
								</tr>
								
							</tbody>
						</table>
					</div>
				</div>
		</div>
-->

	</div>

	<script type="text/javascript" src="jQuery/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
	<script id="fileListTrTemp" type="text/html"> 
   		<tr class="row">
			<td class="col-md-1 text-center">#1</td>
			<td class="col-md-4 text-center">#2</td>
			<td class="col-md-2 text-center">#3</td>
			<td class="col-md-1">#4</td>
			<td class="col-md-2">#5</td>
			<td class="col-md-2">
				<a type="button" class="downloadFile btn btn-success">下载</a>
				<a type="button" class="deleteFile btn btn-danger">删除</a>
			</td>
		</tr>
	</script>
	<script type="text/javascript" src="javascript/index.js" charset="UTF-8"></script>
</body>
</html>

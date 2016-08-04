<%@page import="com.farm.wda.util.AppConfig"%>
<%@page import="java.io.File"%>
<%@page import="com.farm.wda.Beanfactory"%>
<%@ page language="java" pageEncoding="utf-8"%>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><%=AppConfig.getString("config.web.title")%></title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body style="background-color: #8a8a8a;">
	<jsp:include page="/commons/head.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-body text-center">
						<img alt="type" style="margin: auto;" class="img-responsive"
							src="img/type.png">
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">查看一个任务</div>
					<div class="panel-body">
						<form action="path.jsp" method="get">
							<div class="form-group">
								<label for="exampleInputPassword1">KEY</label> <input
									type="text" class="form-control" name="key"
									placeholder="如:232212333">
							</div>
							<button type="submit" class="btn btn-primary">KEY查看</button>
						</form>
					</div>
				</div>
			</div>
			<div class="col-md-3"></div>
		</div>
		<%
			if (AppConfig.getString("config.index").equals("true")) {
		%>
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">检索一个任务</div>
					<div class="panel-body">
						<form action="search.jsp" method="post">
							<div class="form-group">
								<label for="exampleInputPassword1">关键字</label> <input
									type="text" class="form-control" name="key" placeholder="如:火车">
							</div>
							<button type="submit" class="btn btn-primary">全文检索</button>
						</form>
					</div>
				</div>
			</div>
			<div class="col-md-3"></div>
		</div>
		<%
			}
		%>
	</div>
</body>
</html>
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
					<div class="panel-heading">提交一个资源任务</div>
					<div class="panel-body">
						<form action="path.jsp" method="post">
							<div class="form-group">
								<label for="exampleInputPassword1">关键字</label> <input
									type="text" class="form-control" name="key"
									placeholder="如:232212333">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">名称</label> <input type="text"
									class="form-control" name="filename" placeholder="如:资源文件">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">源文件地址(D:\doc\123123.doc)</label>
								<input type="text" class="form-control" name="path"
									placeholder="如:D:\doc\123123.doc">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">源文件类型</label> <select
									name="typename" class="form-control">
									<option value="NONE">默认</option>
									<option value="doc">DOC</option>
									<option value="DOCX">DOCX</option>
									<option value="XLS">XLS</option>
									<option value="XLSX">XLSX</option>
									<option value="PPT">PPT</option>
									<option value="PPTX">PPTX</option>
									<option value="PDF">PDF</option>
									<option value="TXT">TXT</option>
									<option value="ZIP">ZIP</option>
									<option value="RAR">RAR</option>
								</select>
							</div>
							<button type="submit" class="btn btn-primary">提交</button>
						</form>
					</div>
				</div>
			</div>
			<div class="col-md-3"></div>
		</div>
	</div>
</body>
</html>
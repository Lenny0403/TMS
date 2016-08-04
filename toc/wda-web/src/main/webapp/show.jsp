<%@page import="com.farm.wda.util.AppConfig"%>
<%@page import="java.io.File"%>
<%@page import="com.farm.wda.Beanfactory"%>
<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><%=AppConfig.getString("config.web.title")%></title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="embed-responsive embed-responsive-4by3" height="100%">
					<iframe class="embed-responsive-item" height="100%"
						src="http://127.0.0.1:8070/wda-web/text/wda1B/wda53/wdaED/wdaC3/wdaDF/wda087E4297E8448E1F7D63FE/html/index.html"></iframe>
				</div>
			</div>
		</div>
	</div>
	<script src="js/jquery11.3.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
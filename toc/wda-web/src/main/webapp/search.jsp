<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="com.farm.wda.lucene.common.IRResult"%>
<%@page import="com.farm.wda.lucene.server.DocQueryInter"%>
<%@page import="com.farm.wda.lucene.FarmLuceneFace"%>
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
<%
	DocQueryInter query = FarmLuceneFace.inctance().getDocQuery(
			Beanfactory.getFileKeyCoderImpl().parseLuceneDir());
	String key = request.getParameter("key");
	List<Map<String, Object>> list = null;
	if (key == null || key.isEmpty()) {
		key = "无检索词";
		list = new ArrayList();
	} else {
		IRResult result = query
				.query("WHERE(text=" + key + ") ", 1, 20);
		list = result.getResultList();
	}
%>
<body style="background-color: #8a8a8a;">
	<jsp:include page="/commons/head.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-body text-center">全文检索</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						检索词:<%=key%></div>
					<div class="panel-body">
						<table class="table">
							<tr>
								<th width="100">文件名</th>
								<th>内容</th>
							</tr>
							<%
								for (Map<String, Object> node : list) {
							%>
							<tr>
								<td><a href="path.jsp?key=<%=node.get("KEY")%>"><%=node.get("NAME")%></a></td>
								<td><%=node.get("TEXT")%></td>
							</tr>
							<%
								}
							%>
						</table>
					</div>
				</div>
			</div>
			<div class="col-md-3"></div>
		</div>
	</div>
	<script src="js/jquery11.3.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
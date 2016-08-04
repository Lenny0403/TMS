<%@page import="com.farm.wda.util.AppConfig"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.jsp"> <img alt="Brand"
				width="52" height="52" style="margin-top: -16px;" src="img/logo.png">
			</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="index.jsp">查看文档</a></li>
				<%
					if (AppConfig.getString("config.web.submit").equals("true")) {
				%>
				<li><a href="submit.jsp">提交文件</a></li>
				<%
					}
				%>
				<li><a href="document.jsp">帮助</a></li>
			</ul>
		</div>
	</div>
</nav>
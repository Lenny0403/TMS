<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/view/conf/farmtag.tld" prefix="PF"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<PF:basePath/>" />
<title>检索-<PF:ParameterValue key="config.sys.title" /></title>
<meta name="description"
	content='<PF:ParameterValue key="config.sys.mate.description"/>'>
<meta name="keywords"
	content='<PF:ParameterValue key="config.sys.mate.keywords"/>'>
<meta name="author"
	content='<PF:ParameterValue key="config.sys.mate.author"/>'>
<meta name="robots" content="index,follow">
<jsp:include page="../atext/include-web.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="../commons/head.jsp"></jsp:include>
	<jsp:include page="../commons/superContent.jsp"></jsp:include>
	<div class="containerbox">
		<div class="container ">
			<div style="margin-top: 20px;"></div>
			<div class="row column_box">
				<div class="col-lg-3"></div>
				<div class="col-lg-6">
					<div class="row">
						<div class="col-sm-12 column_box  hidden-xs">
							<img class=" center-block" src="text/img/logo.png" alt="wcp" />
						</div>
						<div class="col-sm-12 column_box">
							<jsp:include page="commons/includeSearchForm.jsp"></jsp:include>
						</div>
					</div>
					<div style="margin-top: 20px;"></div>
				</div>
				<div class="col-lg-3"></div>
			</div>
			<!-- /.row -->
			<div class="row ">
				<div class="col-sm-9 hidden-xs">
					<jsp:include page="commons/includeSearchTopDoc.jsp"></jsp:include>
					<jsp:include page="../commons/includePubType.jsp"></jsp:include>
				</div>
				<div class="col-sm-3 ">
					<jsp:include page="../commons/includeHotKnowsMin.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../commons/footServer.jsp"></jsp:include>
	<jsp:include page="../commons/foot.jsp"></jsp:include>
</body>
</html>
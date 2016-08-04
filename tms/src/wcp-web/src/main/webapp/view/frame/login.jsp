<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/view/conf/farmtag.tld" prefix="PF"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<PF:basePath/>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><PF:ParameterValue key="config.sys.title" /> -登录页</title>
<jsp:include page="/view/conf/include.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="text/lib/easyui/themes/gray/login.css" />
<script type="text/javascript" src="text/javascript/md5.js"></script>
</head>
<body class="login_bg1">
	<div class="login_box1">
		<img src="text/lib/easyui/themes/gray/images/login_head.png"
			class="login_head" />
		<div class="login_title1">wcp3</div>
		<form name="loginform" id="loginFormId" method="post" target="_top"
			action="login/submit.do" class="form-singin">
			<table class="login_table1">
				<tr>
					<td><input class="login_ipt1" id="name" type="text"
						name="name" /></td>
				</tr>
				<tr>
					<td><input class="login_ipt1" id="passwordId" type="password"
						name="password" /></td>
				</tr>
				<tr>
					<td><input class="login_but1" type="button" id="loginButton"
						value="登 录" /></td>
				</tr>
				<tr>
					<td
						style="height: 20px; margin-top: -24px; text-align: center; color: #ff0000;"><div
							id="errorMessage">${message}</div></td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
	var mes = '${message}';
	$(function() {
		var msg = document.getElementById("errorMessage");
		if (mes != null && mes.length > 0) {
			msg.innerHTML = mes;
			$(msg).show();
		}
		$('#loginButton').bind('click', function() {
			var password = $('#passwordId').val();
			var name = $('#name').val();
			if (name == null || name.length <= 0) {
				msg.innerHTML = "用户名不能为空";
				$(msg).show();
				return false;
			}
			if (password == null || password.length <= 0) {
				msg.innerHTML = "密码不能为空";
				$(msg).show();
				return false;
			}
			if (password != null && password != '') {
				$('#passwordId').val(hex_md5(password + name));
			}
			$('#loginFormId').submit();
			$('#loginButton').hide();
		});
		$('#clearButton').bind('click', function() {
			$('#passwordId').attr('value', '');
			$('#name').attr('value', '');
		});
		$('#passwordId').keydown(function(e) {
			if (e.keyCode == 13) {
				$('#loginButton').click();
			}
		});
	})
</script>
</html>
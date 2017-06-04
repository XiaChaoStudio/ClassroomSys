<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/default.css" />
<style type="text/css">
* {
	background: none repeat scroll 0 0 transparent;
	margin: 0;
	padding: 0;
	vertical-align: baseline;
	font-family: 微软雅黑;
	overflow: hidden;
}

#navi {
	width: 100%;
	position: relative;
	word-wrap: break-word;
	border-bottom: 1px solid #065FB9;
	margin: 0;
	padding: 0;
	height: 40px;
	line-height: 40px;
	vertical-align: middle;
	background-image: -moz-linear-gradient(top, #EBEBEB, #BFBFBF);
	background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #EBEBEB),
		color-stop(1, #BFBFBF));
}

#naviDiv {
	font-size: 14px;
	color: #333;
	padding-left: 10px;
}
</style>
</head>
<body>
	<div id="navi">
		<div id='naviDiv'>
			<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;用户管理&nbsp; 
			<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;
			<a href="<%=path%>/user/user_query.action">个人信息</a>&nbsp;
		</div>
	</div>
	<h3>修改密码</h3>
	<s:actionerror/>
	<form action="<%=path%>/user/user_alterpass.action" target="_parent" method="post">
		<table align="left" width="400">
			<tr>
				<td><br/>原密码:</td>
				<td>
					<br/>
					<input type="password" name="password_old">
				</td>
			</tr>
			<tr>
				<td><br/>新密码:</td>
				<td>
					<br/>
					<input type="password" name="password_new">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<br/>
					<input type="submit" value="修改密码">
				</td>
			</tr>
		</table>
		
		
	</form>
</body>
</html>
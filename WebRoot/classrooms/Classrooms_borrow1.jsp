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
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="calendar.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/classrooms/calendar.css">
<script type="text/javascript" src="<%=path%>/classrooms/calendar.js"></script>
<style type="text/css">
* {
	background: none repeat scroll 0 0 transparent;
	border: 1 none;
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

#tips {
	margin-top: 10px;
	width: 100%;
	height: 40px;
}

#buttonGroup {
	padding-left: 10px;
	float: left;
	height: 35px;
}

.button {
	margin-top: 20px;
	padding-left: 10px;
	padding-right: 10px;
	font-size: 14px;
	width: 70px;
	height: 30px;
	line-height: 30px;
	vertical-align: middle;
	text-align: center;
	cursor: pointer;
	border-color: #77D1F6;
	border-width: 1px;
	border-style: solid;
	border-radius: 6px 6px;
	-moz-box-shadow: 2px 2px 4px #282828;
	-webkit-box-shadow: 2px 2px 4px #282828;
	background-image: -moz-linear-gradient(top, #EBEBEB, #BFBFBF);
	background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #EBEBEB),
		color-stop(1, #BFBFBF));
}

#mainContainer {
	padding-left: 10px;
	padding-right: 10px;
	text-align: left;
	width: 98%;
	font-size: 16px;
}
</style>
</head>

<body>
	<div id="navi">
		<div id='naviDiv'>
			<span>
				<img src="../images/arror.gif" width="7" height="11" border="0" alt="">
			</span>&nbsp;教室管理&nbsp; 
			<span>
				<img src="../images/arror.gif" width="7" height="11" border="0" alt="">
			</span>&nbsp;
			<a href="<%=path%>/classroom/classroom_listAll.action?kind=1">教室列表</a>&nbsp;
			
		</div>
	</div>
	<div id="tips"></div>
	<div id="mainContainer">
		<!-- 从session中获取教室集合 -->
		<strong>借用教室</strong> <br> <br>
		<form action="<%=path%>/borrow/borrow_borrow.action"
			method="post">
			<input type="hidden" name="islonguse" value="0" />
			<table width="400">
				<tr>
					<td width="30%">教室名称:</td>
					<td><input type="text" readonly="readonly" name="cname" value="<s:property value="cname"/>" /><br /><br />
						</td>
				</tr>
			
				<tr>
					<td width="30%">日期:</td>
					<td><input type="text" readonly="readonly" name="date" value="<s:property value="#session.date"/>" /><br /> <br/>
						</td>
				</tr>
				<tr>
					<td>时间:</td>
					<td>
						
						<s:iterator value="timeInfos" var="timeInfo">
							<s:if test="#session.timeid==#timeInfo.timeid">
								<input type="radio" readonly="readonly" name="timeid" checked="checked" value="<s:property value="#timeInfo.timeid"/>">
								<s:property value="#timeInfo.peroid" /> <br/>
							</s:if>

						</s:iterator>
					</td>
				</tr>
				<tr>
					<td width="30%"><br/>用途:</td>
					<td><br/><input type="text" name="application" /><br /> <br /></td>
				</tr>
				<tr>
					<td colspan="2"><input class="button" type="submit" value="短期借用"></td>
				</tr>
			</table>
		</form>


	</div>
</body>
</html>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/default.css" />
<style type="text/css">
* {
	background: none repeat scroll 0 0 transparent;
	border: 0 none;
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


#mainContainer {
	padding-left: 10px;
	padding-right: 10px;
	text-align: center;
	width: 98%;
	font-size: 12px;
}
</style>
<body>
	<div id="navi">
		<div id='naviDiv'>
			<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;用户管理&nbsp; 
			<span><img src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>&nbsp;
			<a href="<%=path%>/user/user_listAll.action">用户信息</a>&nbsp;
			
		</div>
	</div>
	<div id="mainContainer">

		<table class="default" width="100%">
			<col width="20%">
			<col width="20%">
			<col width="20%">
			<col width="20%">
			<col width="20%">
			<tr class="title">
				<td align="center">学号</td>
				<td align="center">邮箱</td>
				<td align="center">学院</td>
				<td align="center">身份</td>
				<td align="center">操作</td>
			</tr>

			<!-- 遍历开始 -->
			<s:iterator value="list" var="user">
				<tr class="list">
					<td align="center"><s:property value="#user.uid" /></td>
					<td align="center"><s:property value="#user.uemail" /></td>
					<td align="center"><s:property value="#user.deptname" /></td>
					<td align="center">
						<s:if test="%{#user.identify==1}">学生</s:if>
						<s:elseif test="%{#user.identify==2}">老师</s:elseif>
						<s:else>管理员</s:else>
					</td>
					<td><a
						href="<%=path%>/user/user_queryInfo.action?uid=<s:property value="#user.uid"/>"
						onclick="javascript: return confirm('真的要修改吗？');">修改</a>&nbsp;&nbsp;
						<a
						href="<%=path%>/user/user_delete.action?uid=<s:property value="#user.uid"/>"
						onclick="javascript: return confirm('真的要删除吗？');">删除</a></td>
				</tr>
			</s:iterator>
			<!-- 遍历结束 -->

		</table>
		
		<table border="0" align="right">
		<tr>
			<td align="right">
				<span>第<s:property value="currPage" />/<s:property value="totalPage" />页</span> 
				<span>总记录数:<s:property value="totalCount" />&nbsp;&nbsp;每页显示:
					<s:property value="pageSize" />
				</span>
				<span> 
					<s:if test="currPage != 1">
						<a href="${ pageContext.request.contextPath}/user/user_listAll.action?currPage=1">[首页]</a>&nbsp;&nbsp;
	       				<a href="${ pageContext.request.contextPath}/user/user_listAll.action?currPage=<s:property value="currPage-1"/>">[上一页]</a>&nbsp;&nbsp;
	       			</s:if> 
			        <s:if test="currPage != totalPage">
						<a href="${ pageContext.request.contextPath}/user/user_listAll.action?currPage=<s:property value="currPage+1"/>">[下一页]</a>&nbsp;&nbsp;
			       		<a href="${ pageContext.request.contextPath}/user/user_listAll.action?currPage=<s:property value="totalPage"/>">[尾页]</a>&nbsp;&nbsp;
			        </s:if>
				</span>
			</td>
		</tr>
	</table>
	</div>
</body>
</html>
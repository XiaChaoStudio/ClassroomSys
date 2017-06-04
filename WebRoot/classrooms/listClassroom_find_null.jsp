<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
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

#tips {
	margin-top: 10px;
	width: 100%;
	height: 40px;
}

#mainContainer {
	padding-left: 10px;
	padding-right: 10px;
	text-align: center;
	width: 98%;
	font-size: 12px;
}
</style>
<script type="text/javascript">
	$("#back").bind("click", function() {
		window.history.back(); //返回  
	});
</script>
<body>
	<div id="navi">
		<!-- 导航空白 -->
		<div id='naviDiv'>
			<span><img src="../images/arror.gif" width="7" height="11"
				border="0" alt=""></span>&nbsp;学生管理<span>&nbsp; <span><img
					src="../images/arror.gif" width="7" height="11" border="0" alt=""></span>
				&nbsp;查询结果&nbsp; 
		</div>
	</div>
	<div id="tips">
		<!-- 导航空白 -->
	</div>
	<div id="mainContainer">
		<strong style="font-size: 20px">无复合条件的记录， <a
			href="javascript:history.go(-1)">返回？</a></strong>
		<!--数据表格空白 -->
	</div>
</body>
</html>
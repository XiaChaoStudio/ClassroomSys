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
	/* border: 0 none; */
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
	float: left;
	margin-right: 10px;
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
	text-align: center;
	width: 98%;
	font-size: 12px;
}
</style>
<body>
	<div id="navi">
		<div id='naviDiv'>
			<span>
				<img src="../images/arror.gif" width="7" height="11" border="0" alt="">
			</span>&nbsp;已借管理&nbsp; 
			<span>
				<img src="../images/arror.gif" width="7" height="11" border="0" alt="">
			</span>&nbsp;
			<s:if test="Use_Record[0].islonguse==0">
				<a href="<%=path%>/borrow/borrow_listAll.action?islonguse=0">短期借用</a>&nbsp;
			</s:if>
			<s:else>
				<a href="<%=path%>/borrow/borrow_listAll.action?islonguse=1">长期借用</a>&nbsp;
			</s:else>
		</div>
	</div>
	
	<s:if test="#session.LoginUserIdentify == 3">
		<s:if test="Use_Record.size()==0||Use_Record[0].islonguse==0">
			<form action="<%=path%>/borrow/borrow_find.action?islonguse=0" method="post" style="float: left">	
		</s:if>
		<s:else>
			<form action="<%=path%>/borrow/borrow_find.action?islonguse=1" method="post">	
		</s:else>
	
			<div>
				<br /> 
					<span>状态:</span> 
					<span>
						<select name="confirm">
							<s:if test="confirm==1">
								<option value="1" selected="selected">已通过审核</option>
								<option value="0">未通过审核</option>
							</s:if>
							<s:else>
								<option value="1">已通过审核</option>
								<option value="0" selected="selected">未通过审核</option>
							</s:else>
								
						</select>
					</span>
				
				<span>&nbsp;&nbsp;方式及内容:</span> 
				<span> 
					<select name="condition">
						<s:if test="condition==1">
							<option value="1" selected="selected">学号</option>
							<option value="2">教室名称</option>
						</s:if>
						<s:else>
							<option value="1">学号</option>
							<option value="2" selected="selected">教室名称</option>
						</s:else>
							
					</select>
				</span> 
				
				<span><input type="text" name="content"  value="<s:property value="content"/>"></span>
			</div>
			
			<div id="tips">
				<div id="buttonGroup">
					<div class="button"
						onmouseout="this.style.backgroundColor='';this.style.fontWeight='normal'"
						onmouseover="this.style.backgroundColor='#77D1F6';this.style.fontWeight='bold'">
						<input type="submit" value="查找">
					</div>
				</div>
			</div>
	
		</form>
	</s:if>
	
	<s:if test="Use_Record[0].islonguse==0">
		<s:if test="#session.LoginUserIdentify == 3">
			<br/>
			<a href="<%=path%>/borrow/borrow_invalid.action?islonguse=0" style="font-size: 18px;float: right" 
				onclick="javascript: return confirm('真的要清空失效的借用吗？');">清空失效的借用</a>
		</s:if>
	</s:if>

	<div id="mainContainer">

		<table class="default" width="100%">
			<s:if test="#session.LoginUserIdentify == 3">	
				<col width="10%">
			</s:if>
			<col width="10%">
			<col width="15%">
			<col width="10%">
			<col width="10%">
			<col width="5%">
			<col width="20%">
			<tr class="title">
				<s:if test="#session.LoginUserIdentify == 3">	
					<td align="center">用户学号</td>
				</s:if>	
				<td align="center">教室名称</td>
				<td align="center">借用日期</td>
				<td align="center">借用时间</td>
				<td align="center">用途</td>
				<td align="center">状态</td>
				
				<td align="center">操作</td>
			</tr>

			<!-- 遍历开始 -->
			<s:iterator value="Use_Record" var="classroom_use">
				<tr class="list">
					<s:if test="#session.LoginUserIdentify == 3">	
						<td align="center"><s:property value="#classroom_use.uid" /></td>
					</s:if>	
					<td align="center"><s:property value="#classroom_use.cname" /></td>
					<td align="center">
						<s:if test="Use_Record[0].islonguse==1">
							<s:property value="#classroom_use.week" />
						</s:if>
						<s:else>
							20<s:property value="#classroom_use.date" />
						</s:else>
					</td>
					<td align="center">
						<s:if test="#classroom_use.timeid==1">8:00-9:35</s:if>
						<s:elseif test="#classroom_use.timeid==2">9:55-11:30</s:elseif>		
						<s:elseif test="#classroom_use.timeid==3">13:30-15:05</s:elseif>	
						<s:elseif test="#classroom_use.timeid==4">15:20-16:55</s:elseif>	
						<s:elseif test="#classroom_use.timeid==5">17:10-18:45</s:elseif>	
						<s:else>19:30-21:05</s:else>	
					</td>
					<td align="center"><s:property value="#classroom_use.application" /></td>
					<td align="center">
						<s:if test="#classroom_use.status==1">已通过审核</s:if>
						<s:else>未通过审核</s:else>	
					</td>

					<td>
						<s:if test="#session.LoginUserIdentify == 3">
							<a href="<%=path%>/borrow/borrow_cancel.action?recordid=<s:property value="#classroom_use.recordid"/>"
							onclick="javascript: return confirm('真的要删除借用吗？');">删除借用</a>
							<s:if test="#classroom_use.status == 0">
								<a href="<%=path%>/borrow/borrow_agree.action?recordid=<s:property value="#classroom_use.recordid"/>"
								onclick="javascript: return confirm('真的要同意借用吗？');">同意借用</a>
							</s:if>
						</s:if>
						<s:else>
							<a href="<%=path%>/borrow/borrow_cancel.action?recordid=<s:property value="#classroom_use.recordid"/>"
							onclick="javascript: return confirm('真的要取消借用吗？');">取消借用</a>
						</s:else>
					</td>
				</tr>
			</s:iterator>
			<!-- 遍历结束 -->
			
			
			
			
		</table>
		
		
	</div>
</body>
</html>
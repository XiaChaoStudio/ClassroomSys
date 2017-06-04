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
<link rel="stylesheet" type="text/css" href="<%=path%>/classrooms/calendar.css">
<script type="text/javascript" src="<%=path%>/classrooms/calendar.js"></script>
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
			</span>&nbsp;租借管理&nbsp; 
			<span>
				<img src="../images/arror.gif" width="7" height="11" border="0" alt="">
			</span>&nbsp;
			<s:if test="list.size()==0||list[0].kind==1">
				<a href="<%=path%>/classroom/classroom_listAll.action?kind=1">教室列表</a>&nbsp;
			</s:if>
			<s:else>
				<a href="<%=path%>/classroom/classroom_listAll.action?kind=2">会议室列表</a>&nbsp;
			</s:else>
		</div>
	</div>
	<s:if test="list.size()==0||list[0].kind==1">
		<form action="<%=path%>/classroom/classroom_find.action?kind=1" method="post">	
	</s:if>
	<s:else>
		<form action="<%=path%>/classroom/classroom_find.action?kind=2" method="post">	
	</s:else>

		<div>
			<br /> 
			<span>日期:</span> <span><input type="text" id="borrow_date" placeholder="短期借用选择时间" name="date" value="<s:property value="date"/>"></span>

			<span>&nbsp;&nbsp;星期:</span> 
			<span>
				<select name="week">
					<s:if test='week=="无"'>
						<option value="无" selected="selected">无</option>
					</s:if>
					<s:else>
						<option value="无">无</option>
					</s:else>
					<s:if test='week=="星期一"'>
						<option value="星期一" selected="selected">星期一</option>
					</s:if>
					<s:else>
						<option value="星期一">星期一</option>
					</s:else>
					<s:if test='week=="星期二"'>
						<option value="星期二" selected="selected">星期二</option>
					</s:if>
					<s:else>
						<option value="星期二">星期二</option>
					</s:else>
					<s:if test='week=="星期三"'>
						<option value="星期三" selected="selected">星期三</option>
					</s:if>
					<s:else>
						<option value="星期三">星期三</option>
					</s:else>
					<s:if test='week=="星期四"'>
						<option value="星期四" selected="selected">星期四</option>
					</s:if>
					<s:else>
						<option value="星期四">星期四</option>
					</s:else>
					<s:if test='week=="星期五"'>
						<option value="星期五" selected="selected">星期五</option>
					</s:if>
					<s:else>
						<option value="星期五">星期五</option>
					</s:else>
					<s:if test='week=="星期六"'>
						<option value="星期六" selected="selected">星期六</option>
					</s:if>
					<s:else>
						<option value="星期六">星期六</option>
					</s:else>
					<s:if test='week=="星期天"'>
						<option value="星期天" selected="selected">星期天</option>
					</s:if>
					<s:else>
						<option value="星期天">星期天</option>
					</s:else>
				</select>
			</span>

			<span>&nbsp;&nbsp;时间:</span> 
			<span>
				<select name="timeid">
					<s:if test="timeid==1">
						<option value="1" selected="selected">8:00-9:35</option>
					</s:if>
					<s:else>
						<option value="1">8:00-9:35</option>
					</s:else>
					<s:if test="timeid==2">
						<option value="2" selected="selected">9:55-11:30</option>
					</s:if>
					<s:else>
						<option value="2">9:55-11:30</option>
					</s:else>
					<s:if test="timeid==3">
						<option value="3" selected="selected">13:30-15:05</option>
					</s:if>
					<s:else>
						<option value="3">13:30-15:05</option>
					</s:else>
					<s:if test="timeid==4">
						<option value="4" selected="selected">15:20-16:55</option>
					</s:if>
					<s:else>
						<option value="4">15:20-16:55</option>
					</s:else>
					<s:if test="timeid==5">
						<option value="5" selected="selected">17:10-18:45</option>
					</s:if>
					<s:else>
						<option value="5">17:10-18:45</option>
					</s:else>
					<s:if test="timeid==6">
						<option value="6" selected="selected">19:30-21:05</option>
					</s:if>
					<s:else>
						<option value="6">19:30-21:05</option>
					</s:else>				
				</select>
			</span>
			
			<span>&nbsp;&nbsp;方式及内容:</span> 
			<span> 
				<select name="condition">
					<s:if test="condition==1">
						<option value="1" selected="selected">名称</option>
						<option value="2">容量</option>
						<option value="3">位置</option>
					</s:if>
					<s:elseif test="condition==2">
						<option value="1">名称</option>
						<option value="2" selected="selected">容量</option>
						<option value="3">位置</option>
					</s:elseif>
					<s:else>
						<option value="1">名称</option>
						<option value="2">容量</option>
						<option value="3" selected="selected">位置</option>
					</s:else>
						
				</select>
			</span> <span><input type="text" name="content"  value="<s:property value="content"/>"></span>
		</div>
		
		<div id="tips">
			<div id="buttonGroup">
				<div class="button"
					onmouseout="this.style.backgroundColor='';this.style.fontWeight='normal'"
					onmouseover="this.style.backgroundColor='#77D1F6';this.style.fontWeight='bold'">
					<input type="submit" value="查找">
				</div>
				
				<s:if test="#session.LoginUserIdentify == 3">
					<s:if test="list.size()==0||list[0].kind==1">
						<div class="button" onmouseout="this.style.backgroundColor='';this.style.fontWeight='normal'"
						onmouseover="this.style.backgroundColor='#77D1F6';this.style.fontWeight='bold'">
							<a href="<%=path%>/classrooms/Classrooms_add1.jsp">添加教室</a>
						</div>					
					</s:if>
					<s:else>
						<div class="button" onmouseout="this.style.backgroundColor='';this.style.fontWeight='normal'"
						onmouseover="this.style.backgroundColor='#77D1F6';this.style.fontWeight='bold'">
							<a href="<%=path%>/classrooms/Classrooms_add2.jsp">添加会议室</a>
						</div>
					</s:else>
					
				</s:if>
			</div>
		</div>

	</form>

	<br />

	<div id="mainContainer">

		<table class="default" width="100%">
			<col width="30%">
			<col width="30%">
			<col width="40%">
			<tr class="title">
				<s:if test="list[0].kind==1">
					<td align="center">教室名称</td>
					<td align="center">教室容量</td>
					<td align="center">操作</td>
				</s:if>
				<s:elseif test="list[0].kind==2">
					<td align="center">会议室名称</td>
					<td align="center">会议室容量</td>
					<td align="center">操作</td>
				</s:elseif>
			</tr>
			<!-- 遍历开始 -->
			<s:iterator value="list" var="classroom">
				<tr class="list">
					<s:if test="#session.LoginUserIdentify == 3">
						<td align="center"><a href="<%=path%>/classroom/classroom_beforeAlter.action?cname=<s:property value="#classroom.cname"/>"><s:property value="#classroom.cname"/></a></td>
					</s:if>
					<s:else>
						<td align="center"><s:property value="#classroom.cname" /></td>
					</s:else>
					<td align="center"><s:property value="#classroom.size" /></td>
					
					
					<td>
						<s:if test="content!=null">
							<s:if test='week!="无"'>
								<a href="<%=path%>/classroom/classroom_query.action?islonguse=1&cname=<s:property value="#classroom.cname"/>"
								onclick="javascript: return confirm('真的要长期借用吗？');">长期借用</a>&nbsp;&nbsp;
							</s:if>
							<s:elseif test='date!=null&&week=="无"'>
								<a href="<%=path%>/classroom/classroom_query.action?islonguse=0&cname=<s:property value="#classroom.cname"/>"
								onclick="javascript: return confirm('真的要短期借用吗？');">短期借用</a>&nbsp;&nbsp;
							</s:elseif>
						</s:if>
						<s:else>
							（查询后才能借用）
						</s:else>
						<s:if test="#session.LoginUserIdentify == 3">
							<a href="<%=path%>/classroom/classroom_delete.action?cname=<s:property value="#classroom.cname"/>"
							onclick="javascript: return confirm('真的要删除吗？');">删除</a>
						</s:if>
					</td>
				</tr>
			</s:iterator>
			<!-- 遍历结束 -->

		</table>

		<s:if test="content==null">
			<table border="0" align="right">
				<s:if test="#classroom.kind == 1">
					<tr>
						<td align="right">
						<span>
							第<s:property value="currPage" />/<s:property value="totalPage" />页
						</span> 
						<span>
							总记录数:<s:property value="totalCount" />&nbsp;&nbsp;
							每页显示:<s:property value="pageSize" />
						</span> 
						<span> 
							<s:if test="currPage != 1">
								<a href="${ pageContext.request.contextPath}/classroom/classroom_listAll.action?kind=1&currPage=1">[首页]</a>&nbsp;&nbsp;
			       				<a href="${ pageContext.request.contextPath}/classroom/classroom_listAll.action?kind=1&currPage=<s:property value="currPage-1"/>">[上一页]</a>&nbsp;&nbsp;
			       			</s:if> 
			       			<s:if test="currPage != totalPage">
								<a href="${ pageContext.request.contextPath}/classroom/classroom_listAll.action?kind=1&currPage=<s:property value="currPage+1"/>">[下一页]</a>&nbsp;&nbsp;
					       		<a href="${ pageContext.request.contextPath}/classroom/classroom_listAll.action?kind=1&currPage=<s:property value="totalPage"/>">[尾页]</a>&nbsp;&nbsp;
					        </s:if>
						</span></td>
					</tr>
				</s:if>
				<s:else>
					<tr>
						<td align="right">
						<span>
							第<s:property value="currPage" />/<s:property value="totalPage" />页
						</span> 
						<span>
							总记录数:<s:property value="totalCount" />&nbsp;&nbsp;
							每页显示:<s:property value="pageSize" />
						</span> 
						<span> 
							<s:if test="currPage != 1">
								<a href="${ pageContext.request.contextPath}/classroom/classroom_listAll.action?kind=2&currPage=1">[首页]</a>&nbsp;&nbsp;
			       				<a href="${ pageContext.request.contextPath}/classroom/classroom_listAll.action?kind=2&currPage=<s:property value="currPage-1"/>">[上一页]</a>&nbsp;&nbsp;
			       			</s:if> 
			       			<s:if test="currPage != totalPage">
								<a href="${ pageContext.request.contextPath}/classroom/classroom_listAll.action?kind=2&currPage=<s:property value="currPage+1"/>">[下一页]</a>&nbsp;&nbsp;
					       		<a href="${ pageContext.request.contextPath}/classroom/classroom_listAll.action?kind=2&currPage=<s:property value="totalPage"/>">[尾页]</a>&nbsp;&nbsp;
					        </s:if>
						</span></td>
					</tr>
				</s:else>
			</table>
		</s:if>
	</div>
</body>
</html>
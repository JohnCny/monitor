<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib prefix="tiles" uri="/WEB-INF/struts-tiles.tld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>状态监控</title>
		<link href="resources/source/tablecloth.css" rel="stylesheet"
			type="text/css" media="screen" />
					<style type="text/css">
* {
	font-size: 12px;
}

body {
	margin: 0;
	padding: 0;
	background-color: #FFFFFF;
	font-size: 12px;
	color: #666666;
	font-family: "宋体", arial, Helvetica, sans-serif;
}

/*主导航菜单*/
#menu ul {
	padding: 0;
	border: 0;
	list-style: none;
	line-height: 100%;
	margin:0;
}

#menu_out {
	width:90px; padding-top:20px; background: url(resources/source/menu/m_top.gif) no-repeat left top; margin-top:39px;
}

#menu_in {
	width:90px; padding-bottom:20px; background: url(resources/source/menu/m_bot.gif) no-repeat left bottom;
}

.zwh{ height:40px; background:#1a92d5; border-left:1px solid #012b67;}
#menu {
}


#nav {
}

#nav li {
	background: url(resources/source/menu/menu_on_left.gif) repeat-x left top; border-left:1px solid #012b67;
}

#nav li a {
	display: block;
	padding-left: 6px;
	height: 35px;

	cursor: pointer;
	text-decoration: none;

}

#nav li a span {
	float:right;
	padding: 11px 10px 10px 10px;
	line-height: 14px;
	font-size: 14px;
	font-weight: bold;
	color: #FFFFFF;
	text-decoration: none;
	vertical-align:middle;
}

 /*鼠标经过时变换背景*/

#nav li .nav_on span {
	background: url(resources/source/menu/menu_on_right_h.gif) no-repeat left top;
	color: #333333;
	text-decoration: none;
	padding: 11px 10px 10px 10px;
}


/*子栏目布局*/
#menu_s ul {
	padding: 0;
	border: 0;
	list-style: none;
	line-height: 150%;
	margin-top: 0;
	margin-right: 0;
	margin-bottom: 0;
	margin-left: 15px;
	float: right;
}

#menu_out_s {
	width: 99%;
	padding-left: 4px;
	margin-left: auto;
	margin-right: auto;
	background: url(resources/source/menu/menu_left.gif) no-repeat left bottom;
}

#menu_in_s {
	background: url(resources/source/menu/menu_right.gif) no-repeat right bottom;
	padding-right: 4px;
}

#menu_s {
	background: url(resources/source/menu/menu_bg.gif) repeat-x left bottom;
	height: 37px;
}



.menu_line2 {
	background: url(resources/source/menu/menu_line2.gif) no-repeat left top;
	width: 15px;
}

/*子栏目*/
#menu_con {
	text-align: left;
	clear: both;
}

#menu_con li {
	float: left;
	height: 22px;
	margin-top: 8px;
}

#menu_con li a {
	display: block;
	float: left;
	background: url(resources/source/menu/menu_on_left2.gif) no-repeat left
		top;
	cursor: pointer;
	padding-left: 3px;
		text-decoration: none;
		color:blue;
}

#menu_con li a span {
	float: left;
	padding: 6px 10px 4px 10px;
	line-height: 12px;
	background: url(resources/source/menu/menu_on_right2.gif) no-repeat
		right top;
}

/*鼠标悬停效果*/
#menu_con li a:hover {
	text-decoration: none;
	background: url(resources/source/menu/menu_on_left2.gif) no-repeat left bottom;
	color:blue;
}

#menu_con li a:hover span {
	background: url(resources/source/menu/menu_on_right2.gif) no-repeat right bottom;
}
/*鼠标悬停效果*/



/*当前栏目效果*/
#menu_con li .menu_on{	text-decoration: none;
	background: url(resources/source/menu/menu_on_left3.gif) no-repeat left bottom;
	color:#666666;
}
#menu_con li .menu_on span {	background: url(resources/source/menu/menu_on_right3.gif) no-repeat right bottom;
}
/*当前栏目效果*/
#box_m{width:1120px;margin:0 auto;}
#box_nav{float:left; width:90px;}
#box_nav_s{float:left; widows:1000px;}
</style>
		<script>
function locking(id1,id2){
document.getElementById(id1).style.display="block";
document.getElementById(id1).style.width=document.body.clientWidth;
document.getElementById(id1).style.height=document.body.clientHeight;
document.getElementById(id2).style.display='block';
}
function Lock_CheckForm(theForm){
document.all.ly.style.display='none';
document.all.Layer2.style.display='none';
location.replace(location.href);    
return false;
}
</script>
	</head>

	<body style="text-align: center; background: #E6EAE9;">
		<div id="box_m">
		<div id="box_nav">
			<tiles:insert page="../../page/view/left_main.jsp" flush="false" />
		</div>
		<div id="box_nav_s">
			<tiles:insert page="../../page/view/top_sub.jsp" flush="false" />
		<div style="clear:both"></div>
		<div>
		<table cellspacing="0" cellpadding="0" style="width: 1000px;">
			<tr>
				<td colspan="2">
					
				</td>
			</tr>
			<tr>
				<td style="text-align: left;">
					<div style="float: left;">
						<a href="javascript:void(0);"
							onclick="locking('ly','Layer2');return false;">选择过滤条件</a>：
						<font color="red"><bean:write name="showtype" /> </font>
						<bean:write name="AtmCount" />
						台
					</div>
					<div id="loading" style="float: right;"></div>
				</td>
			</tr>
			<tr>
				<td>
					<logic:present name="atmstatus1">
						<div id="doc">
							<div class="fixTh" style="text-align: left;">
								<table cellspacing="0" cellpadding="0" style="width: 98.5%">
									<tr>
										<th width="10%">
											编号
										</th>
										<th width="15%">
											设备类型
										</th>
										<th width="10%">
											所属机构
										</th>
										<th width="10%">
											流水打印
										</th>
										<th width="10%">
											凭条打印
										</th>
										<th width="10%">
											出钞模组
										</th>
										<th width="10%">
											存款模组
										</th>
										<th width="5%">
											读卡器
										</th>
										<th width="5%">
											线路
										</th>
										<th width="5%">
											钞箱
										</th>
										<!-- <th width="10%">
							存款箱张数
						</th> -->
										
										<th width="10%">
											操作
										</th>
									</tr>
								</table>

								<div class="tc" style="height: 450px">
									<tiles:insert page="../../page/monitor/atmstatuslist.jsp"
										flush="true" />
								</div>
							</div>
						</div>
					</logic:present>


					<logic:present name="atmstatus2">
						<div id="doc">
							<div class="fixTh">
								<div class="tc" style="height: 500px">
									<tiles:insert page="../../page/monitor/atmstatusicon.jsp"
										flush="true" />
								</div>
							</div>
						</div>
					</logic:present>

				</td>
			</tr>
		</table>
		<input type="hidden" id="hidden" value="1" />


		<div id="ly"
			style="position: absolute; top: 0px; filter: alpha(opacity =                                                         60); background-color: #777; z-index: 2; left: 0px; display: none;">
		</div>
		<div id="Layer2" align="center"
			style="position: absolute; z-index: 3; width: 600; left: expression((                                                             document .                                                             body .                                                             offsetWidth-540)/ 2 ); top: expression((                                                             document .                                                             body .                                                             offsetHeight-170)/ 2 ); background-color: #fff; display: none;">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<tiles:insert page="changetitle.jsp" flush="true" />
					</td>
				</tr>
				<!-- 
				<tr>
					<td align="center">
						<input type="button" value="确定" onclick="Lock_CheckForm(this);">
					</td>
				</tr> -->
			</table>
			</div>
			</div>
		</div>
		</div>
	</body>
</html>

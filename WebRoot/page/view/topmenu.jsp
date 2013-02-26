<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
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
		<title></title>
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
	line-height: 150%;
	margin-top: 0;
	margin-right: 0;
	margin-bottom: 0;
	margin-left: 15px;
	float: right;
}

#menu_out {
	width: 100%;
	padding-left: 4px;
	margin-left: auto;
	margin-right: auto;
	background: url(resources/source/menu/menu_left.gif) no-repeat left top;
}

#menu_in {
	background: url(resources/source/menu/menu_right.gif) no-repeat right
		top;
	padding-right: 4px;
}

#menu {
	background: url(resources/source/menu/menu_bg.gif) repeat-x;
	height: 73px;
}

.menu_line {
	background: url(resources/source/menu/menu_line.gif) no-repeat center
		top;
	width: 8px;
}

.menu_line2 {
	background: url(resources/source/menu/menu_line2.gif) no-repeat center
		top;
	width: 15px;
}

#nav {
	padding-left: 20px;
}

#nav li {
	float: left;
	height: 35px;
}

#nav li a {
	float: left;
	display: block;
	padding-left: 6px;
	height: 35px;
	background: url(resources/source/menu/menu_on_left.gif) no-repeat left
		top;
	cursor: pointer;
	text-decoration: none;
}

#nav li a span {
	float: left;
	padding: 11px 14px 10px 10px;
	line-height: 14px;
	background: url(resources/source/menu/menu_on_right.gif) no-repeat right
		top;
	font-size: 14px;
	font-weight: bold;
	color: #FFFFFF;
	text-decoration: none;
}

#nav li .nav_on { /*鼠标经过时变换背景，方便JS获取样式*/
	background-position: left 100%;
}

#nav li .nav_on span { /*鼠标经过时变换背景，方便JS获取样式*/
	background-position: right 100%;
	color: #333333;
	text-decoration: none;
	padding: 14px 14px 7px 10px;
}

/*子栏目*/
#menu_con {
	text-align: left;
	padding-left: 20px;
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
}

#menu_con li a span {
	float: left;
	padding: 6px 10px 4px 10px;
	line-height: 12px;
	background: url(resources/source/menu/menu_on_right2.gif) no-repeat
		right top;
}

#menu_con li a:hover {
	text-decoration: none;
	background: url(resources/source/menu/menu_on_left2.gif) no-repeat left
		bottom;
}

#menu_con li a:hover span {
	background: url(resources/source/menu/menu_on_right2.gif) no-repeat
		right bottom;
}

.choice {
	position: absolute;
	width: 80px;
	background-color: #FFFFFF;
	border: 1px solid #B8B8B8;
	left: 97px;
	top: -11px;
	padding-top: 4px;
	padding-bottom: 3px;
	
}

.choice li{
	display:inline;
	padding-left: 0px;
	line-height:22px;
	margin-bottom:2px;
}
.choice li a{
	display: block;
	padding-left: 12px;
	margin-bottom:2px;
	
}
 .choice li a:hover{
	color: #0095d9;text-decoration: none; background:#eff8fc;
}
</style>

		<script language="javascript">
	function qiehuan(num){
	if(num==-1)
	{
		var t=document.getElementById('hidden');
		num=t.value;
	}
	for(var id = 0;id<=7;id++)
		{
			if(id==num)
			{
				document.getElementById("qh_con"+id).style.display="block";
				document.getElementById("mynav"+id).className="nav_on";
			}
			else
			{
				document.getElementById("qh_con"+id).style.display="none";
				document.getElementById("mynav"+id).className="";
			}
		}
	}
	
	function fGetStyleList(event) {
	var txtStyle = document.getElementById("txtStyle"); 
	var dvStyleList = document.getElementById("dvStyleList"); 
	/*var aPos = fPosition(lnkStyle);
	var x = aPos[0];
	var y = aPos[1] + 25;
	dvStyleList.style.top = y + "px";
	dvStyleList.style.left = x + "px";*/
	dvStyleList.style.display = "";
	var ulStyle = document.getElementById("ulStyle"); 
	var liList = ulStyle.getElementsByTagName("LI");
	for(var i=0;i<liList.length;i++){
		liList[i].className = "";
	}
	var styleMap = {"-1" : 0, "4" : 1, "0" : 2, "9" : 2};

	var li = liList[styleMap[txtStyle.value]];
	li.className = "choiceOn";
	fStopPropagation(event);
}
</script>
	</head>
	<body onload="qiehuan(-1);">
		<div id="menu_out">
			<div id="menu_in">
				<div id="menu">
					<ul id="nav">
						<li>
							<a class="nav_off" id="mynav0" onclick="javascript:qiehuan(0)"
								href="index.do?method=getIndexInfo"><span>首 页</span> </a>
						</li>
						<li class="menu_line"></li>
						<li>
							<a href="user.do?method=level&type=em"
								onclick="javascript:qiehuan(1)" id="mynav1" class="nav_off"><span>状态监控</span>
							</a>
						</li>
						<li class="menu_line"></li>
						<li>
							<a href="user.do?method=level&type=sa" onclick="javascript:qiehuan(2);return false;" id="mynav2"
								class="nav_off"><span>统计分析</span> </a>
						</li>
						<li class="menu_line"></li>
						<li>
							<a href="user.do?method=level&type=rm" onclick="javascript:qiehuan(3)" id="mynav3" class="nav_off"><span>远程管理</span>
							</a>
						</li>
						<li class="menu_line"></li>
						<li>
							<a href="user.do?method=level&type=ct"
								onclick="javascript:qiehuan(4)" id="mynav4" class="nav_off"><span>对账管理</span>
							</a>
						</li>
						<li class="menu_line"></li>
						<li>
							<a href="user.do?method=level&type=um" onclick="javascript:qiehuan(5)" id="mynav5" class="nav_off"><span>短信管理</span>
							</a>
						</li>
						<li class="menu_line"></li>
						<li>
							<a href="user.do?method=level&type=sc" onclick="javascript:qiehuan(6)" id="mynav6" class="nav_off"><span>系统管理</span>
							</a>
						</li>
					</ul>
					<div id="menu_con">
						<div id="menu_con">
							<span style="float: left; margin: 8px;"><img
									src="resources/source/loguser.gif" border="0" />您好, <bean:write
									name="login" property="username" />,欢迎使用自助设备综合管理系统 | <bean:write property="name" name="login" /> | <a href="user.do?method=logout">退出</a>
							</span>
							<div id="qh_con0" style="display: none;">

							</div>
							<div id="qh_con1" style="display: none">

							</div>
							<div id="qh_con2" style="display: none">
								<ul>
									<li>
										<a href="atmids.do?method=getCashAtmId"><span>现金分析</span>
										</a>
									</li>
									<li class="menu_line2"></li>
									<li>
										<a href="atmids.do?method=getId"><span>交易信息</span> </a>
									</li>
									<li class="menu_line2"></li>
									<li>
										<a href="atmids.do?method=getId3"><span>运行信息</span> </a>
									</li>
								</ul>
							</div>
							<div id="qh_con3" style="display: none">
								<ul>
									<li>
										<a href="atmids.do?method=getDownloadId"><span>文件下载</span> </a>
									</li>
									<li class="menu_line2"></li>
									<li>
										<a href="atmcompany.do?type=u"><span>文件上传</span> </a>
									</li>
									<li class="menu_line2"></li>
									<li>
										<a href="atmids.do?method=getRebootId"><span>远程执行</span> </a>
									</li>
								</ul>
							</div>
							<div id="qh_con4" style="display: none">

							</div>
							<div id="qh_con5" style="display: none">
								<ul>
									<li>
										<a href="smsstatusconfig.do?method=all"><span>发信管理</span> </a>
									</li>
									<li class="menu_line2"></li>
									<li>
										<a href="smsinfo.do?method=getInfoAll"><span>历史查询</span> </a>
									</li>
									<li class="menu_line2"></li>
									<li>
										<a href="smsinfo.do?method=getSmsSendStatus"><span>测试与发送</span> </a>
									</li>
								</ul>
							</div>
							<div id="qh_con6" style="display: none">
								<ul>
									<li>
										<a href="user.do?method=list"><span>用户管理</span> </a>
									</li>
									<li class="menu_line2"></li>
									<li>
										<a href="atmsetconfig.do?method=all"><span>服务管理</span> </a>
									</li>
									<li class="menu_line2"></li>
									<li>
										<a href="atmcinfoconfig.do?method=all"><span>设备管理</span> </a>
									</li>
									<li class="menu_line2"></li>
									<li>
										<a href="infoconfig.do?method=allInfo"><span>信息管理</span> </a>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>



	</body>
</html>

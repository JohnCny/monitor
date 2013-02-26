<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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
		<script type="text/javascript">
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
	
	/*function fGetStyleList(event) {
	var txtStyle = document.getElementById("txtStyle"); 
	var dvStyleList = document.getElementById("dvStyleList"); 
	/*var aPos = fPosition(lnkStyle);
	var x = aPos[0];
	var y = aPos[1] + 25;
	dvStyleList.style.top = y + "px";
	dvStyleList.style.left = x + "px";///////////
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
	}*/
		
		</script>
		</head>
	<body onload="qiehuan(-1);">
	<div id="menu_out_s">
    <div id="menu_in_s">
        <div id="menu_s">
            <div id="menu_con">
                <div id="menu_con"> <span style="float: left; margin: 8px;"><img
									src="resources/source/loguser.gif" width="16" height="16" border="0" />登陆信息：<bean:write
									name="login" property="username" /> 部门：
                    <bean:write property="name" name="login" />         <a href="user.do?method=logout" style="color:blue">退出</a> </span>
                    <div id="qh_con0" style="display: none;"> </div>
                    <div id="qh_con1" style="display: none"> </div>
                    <div id="qh_con2" style="display: none">
                        <ul>
                            <li> <a href="atmids.do?method=getCashAtmId" class="menu_on"><span>现金分析</span> </a> </li>
                            <li class="menu_line2"></li>
                            <li> <a href="atmids.do?method=getId"  class="menu_off"><span>交易信息</span> </a> </li>
                            <li class="menu_line2"></li>
                            <li> <a href="atmids.do?method=getId3"><span>运行信息</span> </a> </li>
                        </ul>
                    </div>
                    <div id="qh_con3" style="display: none">
                        <ul>
                            <li> <a href="atmids.do?method=getDownloadId"><span>文件下载</span> </a> </li>
                            <li class="menu_line2"></li>
                            <li> <a href="atmcompany.do?type=u"><span>文件上传</span> </a> </li>
                            <li class="menu_line2"></li>
                            <li> <a href="atmids.do?method=getRebootId"><span>远程执行</span> </a> </li>
                        </ul>
                    </div>
                    <div id="qh_con4" style="display: none"> </div>
                    <div id="qh_con5" style="display: none">
                        <ul>
                            <li> <a href="smsstatusconfig.do?method=all"><span>发信管理</span> </a> </li>
                            <li class="menu_line2"></li>
                            <li> <a href="smsinfo.do?method=getInfoAll"><span>历史查询</span> </a> </li>
                            <li class="menu_line2"></li>
                            <li> <a href="smsinfo.do?method=getSmsSendStatus"><span>测试与发送</span> </a> </li>
                        </ul>
                    </div>
                    <div id="qh_con6" style="display: none">
                        <ul>
                            <li> <a href="user.do?method=list"><span>用户管理</span> </a> </li>
                            <li class="menu_line2"></li>
                            <li> <a href="atmsetconfig.do?method=all"><span>服务管理</span> </a> </li>
                            <li class="menu_line2"></li>
                            <li> <a href="atmcinfoconfig.do?method=all"><span>设备管理</span> </a> </li>
                            <li class="menu_line2"></li>
                            <li> <a href="infoconfig.do?method=allInfo"><span>信息管理</span> </a> </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div></div>
		
	</body>
</html>
		
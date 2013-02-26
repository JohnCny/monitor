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
		<title>系统管理-服务管理</title>
		<link href="resources/source/tablecloth.css" rel="stylesheet"
			type="text/css" media="screen" />
		<script type="text/javascript" src="resources/scripts/jquery-1.6.js"></script>

		<script type="text/javascript">
$(document).ready(function(){
      $("#atmcset").click(function(){
            $.ajax({
                 url:'atmsetconfig.do?method=setAtmc', 
                 type: 'post',
                 cache:false,
                 data: $('#form1').serialize(),
                 timeout: 2000,
                 error: function(){
                    alert('错误');
                 },
                success: function(json){
                	alert("保存成功!");
                }
         });
     });
     
     $("#atmpset").click(function(){
            $.ajax({
                 url:'atmsetconfig.do?method=setAtmp', 
                 type: 'post',
                 cache:false,
                 data: $('#form2').serialize(),
                 timeout: 2000,
                 error: function(){
                    alert('错误');
                 },
                success: function(json){
                	alert("保存成功!");
                }
         });
     });
    });
		</script>
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
					<!--<tiles:insert page="../../page/view/topmenu.jsp" flush="false" />-->
				</td>
			</tr>
			<tr>
				<td valign="top" width="50%">
					<table cellspacing="0" cellpadding="0">
						<html:form action="atmsetconfig.do?method=setAtmc" method="post"
							styleId="form1">
							<logic:present name="remotecontrolstatus">
								<tr>
									<th colspan="2">
										客户端管理
									</th>
								</tr>
								<tr>
									<td style="width: 50%">
										远程上传：
									</td>
									<td>
										<html:select property="uploadstatus"
											value="${remotecontrolstatus.uploadstatus}"
											style="width:100%;">
											<html:option value="0">关闭</html:option>
											<html:option value="1">开启</html:option>
										</html:select>
									</td>
								</tr>
								<tr>
									<td>
										远程下载：
									</td>
									<td>
										<html:select property="downloadstatus" style="width:100%;"
											value="${remotecontrolstatus.downloadstatus}">
											<html:option value="0">关闭</html:option>
											<html:option value="1">开启</html:option>
										</html:select>
									</td>
								</tr>
								<tr>
									<td>
										远程执行：
									</td>
									<td>
										<html:select property="rebootstatus" style="width:100%;"
											value="${remotecontrolstatus.rebootstatus}">
											<html:option value="0">关闭</html:option>
											<html:option value="1">开启</html:option>
										</html:select>
									</td>
								</tr>
								<tr>
									<td>
										网络监测：
									</td>
									<td>
										<html:select property="netmonitorstatus" style="width:100%;"
											value="${remotecontrolstatus.netmonitorstatus}"
											styleId="change2" onchange="change()">
											<html:option value="0">关闭</html:option>
											<html:option value="1">开启</html:option>
										</html:select>
									</td>
								</tr>
								<tr id="netmonitortime">
									<td>
										监测时间：
									</td>
									<td>
										<html:select property="netmonitortime" style="width:100%;"
											value="${remotecontrolstatus.netmonitortime}">
											<html:option value="5">5分钟</html:option>
											<html:option value="10">10分钟</html:option>
											<html:option value="15">15分钟</html:option>
											<html:option value="20">20分钟</html:option>
											<html:option value="25">25分钟</html:option>
											<html:option value="30">30分钟</html:option>
											<html:option value="60">60分钟</html:option>
										</html:select>
									</td>
								</tr>
								
								<tr>
									<td>
										流水下载：
									</td>
									<td>
										<html:select property="autodownload" style="width:100%;"
											value="${remotecontrolstatus.autodownload}"
											onchange="change();" styleId="change1">
											<html:option value="0">关闭</html:option>
											<html:option value="1">开启</html:option>
										</html:select>
									</td>
								</tr>
								<tr id="downloadtime">
									<td>
										下载时间：
									</td>
									<td>
										<html:select property="downloadtime" style="width:100%;"
											value="${remotecontrolstatus.downloadtime}">
											<html:option value="1">1点</html:option>
											<html:option value="2">2点</html:option>
											<html:option value="3">3点</html:option>
											<html:option value="4">4点</html:option>
											<html:option value="5">5点</html:option>
											<html:option value="6">6点</html:option>
											<html:option value="7">7点</html:option>
											<html:option value="8">8点</html:option>
											<html:option value="9">9点</html:option>
											<html:option value="10">10点</html:option>
											<html:option value="11">11点</html:option>
											<html:option value="12">12点</html:option>
											<html:option value="13">13点</html:option>
											<html:option value="14">14点</html:option>
											<html:option value="15">15点</html:option>
											<html:option value="16">16点</html:option>
											<html:option value="17">17点</html:option>
											<html:option value="18">18点</html:option>
											<html:option value="19">19点</html:option>
											<html:option value="20">20点</html:option>
											<html:option value="21">21点</html:option>
											<html:option value="22">22点</html:option>
											<html:option value="23">23点</html:option>
											<html:option value="24">24点</html:option>
										</html:select>
									</td>
									
								</tr>
								
								<td colspan="2">
									<input type="button" id="atmcset" value="确定">
								</td>

							</logic:present>
						</html:form>
					</table>

				</td>
				<td valign="top">
					<html:form action="atmsetconfig.do?method=setAtmp" method="post"
						styleId="form2">
						<logic:present name="atmpstatus">
							<table cellspacing="0" cellpadding="0">
								<tr>
									<th colspan="2">
										服务端管理
									</th>
								</tr>
								<tr>
									<td style="width: 50%">
										数据同步：
									</td>
									<td>
										<html:select property="sycnstatus"
											value="${atmpstatus.sycnstatus}" styleId="change1"
											style="width:100%">
											<html:option value="0">关闭</html:option>
											<html:option value="1">开启</html:option>
										</html:select>
									</td>
								</tr>
								<!-- <tr id="dbiptr">
						<td align="right">
							数据库地址：
						</td>
						<td>
							<html:text property="dbip" name="atmpstatus" styleId="ip" />
						</td>
					</tr>
					<tr id="dbporttr">
						<td align="right">
							数据库端口：
						</td>
						<td>
							<html:text property="dbport" name="atmpstatus" styleId="port" />
						</td>
					</tr>
					<tr id="dbnametr">
						<td align="right">
							数据库名：
						</td>
						<td>
							<html:text property="dbname" name="atmpstatus" styleId="dbname" />
						</td>
					</tr>
					<tr id="dbusernametr">
						<td>
							数据库用户名：
						</td>
						<td>
							<html:text property="dbusername" name="atmpstatus"
								styleId="username" />
						</td>
					</tr>
					<tr id="dbpasswordtr">
						<td align="right">
							数据库密码：
						</td>
						<td>
							<html:text property="dbpassword" name="atmpstatus"
								styleId="password" />
						</td>
					</tr>
					<tr id="sycntimetr">
						<td>
							同步间隔：
						</td>
						<td>
							<html:select property="sycntime" value="${atmpstatus.sycntime}">
								<html:option value="5">5分钟</html:option>
								<html:option value="10">10分钟</html:option>
								<html:option value="15">15分钟</html:option>
								<html:option value="20">20分钟</html:option>
								<html:option value="25">25分钟</html:option>
								<html:option value="30">30分钟</html:option>
							</html:select>
						</td>
					</tr>-->
								<tr id="dbporttr">
									<td align="right">
										数据端口：
									</td>
									<td>
										<html:text property="dbport" name="atmpstatus" styleId="port"
											style="width:100%;" />

									</td>
								</tr>
								<tr>
									<td align="right">
										数据迁移：
									</td>
									<td>
										<html:select property="mgrtstatus"
											value="${atmpstatus.mgrtstatus}" styleId="change2"
											style="width:100%">
											<html:option value="0">关闭</html:option>
											<html:option value="1">开启</html:option>
										</html:select>
									</td>
								</tr>


								<tr id="mgrtpathtr">
									<td>
										本地文件：
									</td>
									<td>
										<html:text property="mgrtpath" name="atmpstatus"
											style="width:100%;" styleId="mgrtpath" />
									</td>
								</tr>

								<tr id="ftpipid">
									<td align="right">
										FTP地址：
									</td>
									<td>
										<html:text property="ftpip" name="atmpstatus" styleId="ftpip"
											style="width:100%;" />
									</td>
								</tr>
								<tr id="ftpportid">
									<td align="right">
										FTP端口：
									</td>
									<td>
										<html:text property="ftpport" name="atmpstatus"
											style="width:100%;" styleId="ftpport" />
									</td>
								</tr>
								<tr id="ftppathid">
									<td align="right">
										FTP文件：
									</td>
									<td>
										<html:text property="ftppath" name="atmpstatus"
											style="width:100%;" styleId="ftppath" />
									</td>
								</tr>
								<tr id="ftpusernameid">
									<td align="right">
										FTP用户名：
									</td>
									<td>
										<html:text property="ftpusername" name="atmpstatus"
											style="width:100%;" styleId="ftpusername" />
									</td>
								</tr>
								<tr id="ftppasswordid">
									<td align="right">
										FTP密码：
									</td>
									<td>
										<html:text property="ftppassword" name="atmpstatus"
											style="width:100%;" styleId="ftppassword" />
									</td>
								</tr>
								
								<tr id="mgrttimeid">
									<td align="right">
										迁移时间：
									</td>
									<td>
										<html:select property="mgrttime" name="" style="width:100%;"
											value="${atmpstatus.mgrttime}" styleId="mgrttime"
											style="width:100%">
											<html:option value="1">1点</html:option>
											<html:option value="2">2点</html:option>
											<html:option value="3">3点</html:option>
											<html:option value="4">4点</html:option>
											<html:option value="5">5点</html:option>
											<html:option value="6">6点</html:option>
											<html:option value="7">7点</html:option>
											<html:option value="8">8点</html:option>
											<html:option value="9">9点</html:option>
											<html:option value="10">10点</html:option>
											<html:option value="11">11点</html:option>
											<html:option value="12">12点</html:option>
											<html:option value="13">13点</html:option>
											<html:option value="14">14点</html:option>
											<html:option value="15">15点</html:option>
											<html:option value="16">16点</html:option>
											<html:option value="17">17点</html:option>
											<html:option value="18">18点</html:option>
											<html:option value="19">19点</html:option>
											<html:option value="20">20点</html:option>
											<html:option value="21">21点</html:option>
											<html:option value="22">22点</html:option>
											<html:option value="23">23点</html:option>
											<html:option value="24">24点</html:option>
										</html:select>
									</td>
								</tr>
								
								<tr>
									<td colspan="2">
										<input type="button" id="atmpset" value="确定">
									</td>
								</tr>
							</table>
						</logic:present>
					</html:form>

				</td>
			</tr>
		</table>
		<input type="hidden" id="hidden" value="6" />
		</div>
			</div>
		</div>
	</body>
</html>

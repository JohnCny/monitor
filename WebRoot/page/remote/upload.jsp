<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib prefix="tiles" uri="/WEB-INF/struts-tiles.tld"%>
<html>
	<head>
		<title>远程管理-文件上传</title>
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
#box_m{width:112px;margin:0 auto;}
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
				<td valign="top" style="width: 30%;">
					<table cellspacing="0" cellpadding="0">
						<tr>
							<th colspan="3">
								文件上传
							</th>
						</tr>

						<tr>
							<td>
								<html:form action="atmids.do?method=getUploadId" method="post">
									<tr>
										<td style="width: 30%" align="left">
											设备厂商：
										</td>
										<td>
											<html:select property="macprovider" style="width:100%"
												onchange="submit()">
												<html:option value="">请选择...</html:option>
												<logic:present name="atmcompany">
													<logic:iterate id="tlr" name="atmcompany">
														<html:option value="${tlr.atmid}">
																${tlr.company }
															</html:option>
													</logic:iterate>
												</logic:present>
											</html:select>
										</td>
									</tr>
								</html:form>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								请选择需要上传的设备号
							</td>
						</tr>
						<html:form action="upload.do" enctype="multipart/form-data">
							<tr align="center">
								<td colspan="3">
									<html:select property="atmids" size="23" style="width:100%"
										multiple="true" styleId="atmids">
										<logic:notEmpty name="atmid">
											<html:optionsCollection name="atmid" value="atmid"
												label="allstatus" />
										</logic:notEmpty>
									</html:select>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<html:file property="file" styleId="file" />
								</td>
								<td>
									<!--<html:button property="" value="程序更新" onclick="upload('1');" />
										<html:button property="" value="广告更新" onclick="upload('2');" />-->
									<html:button property="" value="更新驻留" onclick="upload('3');" />
								</td>
							</tr>

							<tr>
								<td>
									远程路径:
								</td>
								<td>
									<html:text property="remPath" />
								</td>
								<td>
									<html:button property="" value="文件上传" onclick="upload('4');" />
								</td>
							</tr>

						</html:form>
					</table>
				</td>
				<td valign="top">
					<logic:present name="backList">
						<table border="0">
							<tr style="background-color: #00AA7B;">
								<td>
									机器号
								</td>
								<td>
									上传状态
								</td>
							</tr>
							<logic:iterate id="tlr" name="backList">
								<tr>
									<td>
										<bean:write name="tlr" property="atmid" />
									</td>
									<td>
										<logic:equal value="1" name="tlr" property="backStatus">
											<font color="#00DB00">成功</font>
										</logic:equal>
										<logic:notEqual value="1" name="tlr" property="backStatus">
											<font color="#FF0000">失败</font>
										</logic:notEqual>
									</td>
								</tr>
							</logic:iterate>
						</table>
					</logic:present>
				</td>
			</tr>
		</table>
		<input type="hidden" id="hidden" value="3" />
		</div>
			</div>
		</div>
	</body>
</html>
<script type="text/javascript">
  function upload(action){
    if(document.getElementById("atmids").value==""){
      	alert("请选择ATM编号！");
      	return false;
    }
    else if(document.getElementById("file").value=="")
    {
      	alert("请选择上传文件！");
    	return false;
    }
    document.forms[1].action="upload.do?method="+action;
    document.forms[1].submit();
   }
</script>
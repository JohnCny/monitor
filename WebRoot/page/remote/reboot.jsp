<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib prefix="tiles" uri="/WEB-INF/struts-tiles.tld"%>
<html>
	<head>
		<title>远程管理-远程执行</title>
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
							<th colspan="2">
								远程执行
							</th>
						</tr>
						<html:form action="reboot.do">
							<tr>
								<td>
									请选择需要执行的设备号
								</td>
							</tr>
							<tr align="center">
								<td>
									<html:select property="atmid" size="25" style="width:100%"
										styleId="atmids">
										<logic:notEmpty name="atmid">
											<html:optionsCollection name="atmid" value="atmid"
												label="allstatus" />
										</logic:notEmpty>
									</html:select>

								</td>
							</tr>
							<tr align="center">
								<td>
									<html:button property="" value="重启" onclick="reboot();" />
								</td>
							</tr>
						</html:form>
					</table>
				</td>
				<td></td>
			</tr>
		</table>
		<input type="hidden" id="hidden" value="3" />
		</div>
			</div>
		</div>
		<!-- <td>
					<table border="0" cellpadding="2" cellspacing="1" class="tablebg">
						<tr>
							<td colspan="2">
								服务暂停ATM服务
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<hr>
							</td>
						</tr>
						<html:form action="reboot.do">
							<tr>
								<td>
									请选择需要重启的ATM
								</td>
							</tr>
							<tr align="center">
								<td>
									<html:select property="atmid" size="10" style="width:100%"
										styleId="atmids">
										<logic:notEmpty name="atmid">
											<html:optionsCollection name="atmid" value="atmid"
												label="atmid" />
										</logic:notEmpty>
									</html:select>

								</td>
							</tr>
							<tr align="center">
								<td>
									<html:button property="" value="重启" onclick="reboot();" />
								</td>
							</tr>
						</html:form>

						<tr>
							<td colspan="2">
								<hr>
							</td>
						</tr>
					</table>
				</td> -->
	</body>
</html>
<script type="text/javascript">
  function reboot(){
    if(document.getElementById("atmids").value==""){
      	alert("请选择ATM编号！");
      	return false;
    }
    document.forms[0].submit();
   }
</script>
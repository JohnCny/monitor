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

		<title></title>

		<link href="resources/source/tablecloth.css" rel="stylesheet"
			type="text/css" media="screen" />
		<script type="text/javascript" src="resources/scripts/calendar.js"></script>
		<style type="text/css">
			/*当前栏目效果*/
			#box_m{width:1200px;margin:0 auto;}
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
				<td style="width: 50%;">
					<logic:present name="smssendstatus">
						<html:form action="smsinfo.do?method=startSms" method="post">
							<table cellspacing="0" cellpadding="0" style="width: 300px">
								<tr>
									<logic:equal name="smssendstatus" value="0">
										<td style="background-color: green;">
											<font size="2em" color="#ffffff">短信服务正常运行</font>
										</td>
									</logic:equal>
									<logic:equal name="smssendstatus" value="1">
										<td style="background-color: red;">
											<font size="2em" color="#ffffff">短信服务异常</font>
										</td>
									</logic:equal>
									<td>
										<html:submit value="重启" />
									</td>
								</tr>
							</table>
						</html:form>
					</logic:present>
				</td>
				<td style="width: 50%;">
					<html:form action="smsinfo.do?method=sendTestSms" method="post">
						<table cellspacing="0" cellpadding="0" style="width: 300px">
							<tr>
								<th colspan="2">
									手动发送短信
								</th>
							</tr>
							<tr>
								<td colspan="2">
									<html:textarea property="sendsms" rows="5" style="width:100%"></html:textarea>
								</td>
							</tr>
							<tr>
								<td>
									<input id="moblie" type="text" name="moblie"
										style="width: 250px"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
								</td>
								<td>
									<input type="submit" value="发送">
								</td>
							</tr>
							<logic:present name="testsmssend">
								<tr>
									<td colspan="2">
										<bean:write name="testsmssend" />
									</td>
								</tr>
							</logic:present>
						</table>
					</html:form>
				</td>
			</tr>
		</table>
		<input type="hidden" id="hidden" value="5" />
		</div>
			</div>
		</div>
	</body>
</html>

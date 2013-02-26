<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
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
	</head>

	<body style="margin: 0px;">
		<logic:present name="tradestatecategoryData">
			<table cellspacing="0" cellpadding="0">
				<tr>
					<th>
						设备编号
					</th>
					<th>
						取款笔数
					</th>
					<th>
						存款笔数
					</th>
				</tr>
				<logic:iterate id="tlr" name="tradestatecategoryData">
					<tr>
						<td>
							<bean:write name="tlr" property="id" />
						</td>
						<td>
							<bean:write name="tlr" property="qk" />
						</td>
						<td>
							<bean:write name="tlr" property="ck" />
						</td>
					</tr>
				</logic:iterate>
			</table>
		</logic:present>
	</body>
</html>

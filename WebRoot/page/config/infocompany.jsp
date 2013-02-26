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

	<body>
		<table cellspacing="0" cellpadding="0">
			<caption style="font-size: 2em;">
				设备厂商
			</caption>

			<tr>
				<th>
					编号
				</th>
				<th>
					中文名称
				</th>
				<th>
					英文名称
				</th>
				<th>
					操作
				</th>
			</tr>

			<logic:present name="atmcompanylist">
				<logic:iterate id="tlr" name="atmcompanylist">
					<tr>
						<td>
							<bean:write name="tlr" property="id" />
						</td>
						<td>
							<bean:write name="tlr" property="chname" />
						</td>
						<td>
							<bean:write name="tlr" property="enname" />
						</td>
						<td>
							<a href="infoconfig.do?method=getCompanyById&id=${tlr.id }">编辑</a>
							<a href="infoconfig.do?method=deleteCompany&id=${tlr.id }">删除</a>
						</td>
					</tr>
				</logic:iterate>
			</logic:present>
		</table>
		<div align="right">
			<a href="page/config/setcompany.jsp"> 创建 </a>|
			<a href="infoconfig.do?method=companyAll">刷新</a>
		</div>
	</body>
</html>

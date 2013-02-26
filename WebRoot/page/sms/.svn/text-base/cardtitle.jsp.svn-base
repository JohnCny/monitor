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

<html>
	<head>
		<base href="<%=basePath%>">
		<title></title>
		<link rel="stylesheet" type="text/css"
			href="resources/source/mainDiv.css" />
	</head>
	<body>
		<div id="card">
			<ul id="card_title">
				<li>
					<a href="smsstatusconfig.do?method=all" target="configMainFrame">发送管理</a>
				</li>
				<li>
					<a href="smsconfig.do?method=all" target="configMainFrame">短信用户管理</a>
				</li>
				<li>
					<a href="smsinfo.do?method=getInfoAll" target="configMainFrame">历史查询</a>
				</li>
				<li>
					<a href="smsinfo.do?method=getSmsSendStatus" target="configMainFrame">短信猫测试</a>
				</li>
			</ul>
		</div>
	</body>
</html>



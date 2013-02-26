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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<center>

		<frameset rows="25,*" cols="*" frameborder="0">
			<frame src="page/config/cardtitle.jsp" name="top" scrolling="NO" noresize
				id="top">
			<frame src="page/user/main.jsp" name="configMainFrame" id="main">
		</frameset>
		<noframes>


		</noframes>
		<body>
		</body>
	</center>
</html>



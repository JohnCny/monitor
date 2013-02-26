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
		<title></title>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<center>

		<frameset rows="*" cols="70%,*" frameborder="4">
			<frameset rows="60%,*" cols="*">
				<frame src="index.do?method=getAtmStateStatistics" name="leftTop"
					scrolling="auto" noresize id="leftTop">
				<frame src="index.do?method=getTradeStateStatistics"
					name="leftBottom" id="leftBottom" scrolling="auto">
			</frameset>
			<frame src="page/index/jylpm.jsp" name="rightTop" scrolling="auto"
				noresize id="rightTop">
		</frameset>
		<noframes>
		</noframes>
		<body>
		</body>
	</center>
</html>



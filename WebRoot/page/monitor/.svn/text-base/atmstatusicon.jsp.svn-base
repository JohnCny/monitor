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
<html>
	<head>
		<base href="<%=basePath%>">
		<title></title>
		
	</head>

	<body>

		<table cellspacing="0" cellpadding="0">
			<tr>
				<%
					int co2 = 0;
				%>
				<logic:iterate id="tlr" name="atmstatus2">
					<td>
						<bean:write name="tlr" property="atmid" />
						<br>
						<logic:equal name="tlr" property="allstatus" value="1">
							<!--<a href="atmstatus.do?method=byId&id=${tlr.atmid}"
								target="monitorInfoFrame">  -->
							<img src="resources/images/AtmWhite${tlr.route}_Error.gif"
								border="0" />
							<!--   </a>-->
						</logic:equal>
						<logic:equal name="tlr" property="allstatus" value="0">
							<img src="resources/images/AtmWhite${tlr.route}.jpg" border="0" />
						</logic:equal>
					</td>
					<%
						co2++;
								if (co2 % 10 == 0) {
									out.print("</tr><tr>");
								}
					%>
				</logic:iterate>
			</tr>
		</table>

	</body>
</html>

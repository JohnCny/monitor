<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="cewolf" uri="/WEB-INF/cewolf.tld"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
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
		<link href="resources/source/tablecloth.css" rel="stylesheet"
			type="text/css" media="screen" />
		<base href="<%=basePath%>">
		<title></title>

	</head>

	<body>
		<%
			String title = request.getAttribute("title").toString();
			String title_id = request.getAttribute("title_id").toString();

			int height = Integer.parseInt(request.getAttribute("height")
					.toString());
		%>
		<table cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<table cellspacing="0" cellpadding="0">
						<tr>
							<th>
								<%=title_id%>
							</th>
							<th><%=title%></th>
							<logic:present name="categoryData">
								<logic:iterate id="tlr" name="categoryData">
									<tr>
										<td>
											<bean:write name="tlr" property="atmid" />
										</td>
										<td>
											<bean:write name="tlr" property="sum" />
										</td>
									</tr>
								</logic:iterate>
							</logic:present>
						</tr>
					</table>
				</td>
				<td valign="top">
					<cewolf:chart id="horizontalBarChart" title="" type="horizontalBar"
						xaxislabel="" yaxislabel="">
						<cewolf:data>
							<cewolf:producer id="categoryImg" />
						</cewolf:data>
					</cewolf:chart>

					<cewolf:img chartid="horizontalBarChart" renderer="/cewolf"
						width="500" height="<%=height%>">
					</cewolf:img>

				</td>

			</tr>
		</table>
	</body>
</html>

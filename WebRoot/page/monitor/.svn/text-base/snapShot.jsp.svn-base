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
		<link href="resources/source/tablecloth.css" rel="stylesheet"
			type="text/css" media="screen" />
		<script type="text/javascript" src="resources/scripts/calendar.js"></script>
	</head>
	<body>
		<table cellspacing="0" cellpadding="0">
			<tr>
				<!-- <td valign="top" width="250px">
					<table cellspacing="0" cellpadding="0">
						<tr>
							<th colspan="2">
								请选择截屏的设备号
							</th>
						</tr>
						<html:form action="snapShot.do" styleId="form1">
							<tr align="center">
								<td colspan="2">
									<html:select property="atmids" size="20" style="width:100%"
										styleId="atmids" multiple="true">
										<logic:notEmpty name="atmid">
											<html:optionsCollection name="atmid" value="atmid"
												label="allstatus" />
										</logic:notEmpty>
									</html:select>
								</td>
							</tr>

							<tr>
								<td align="center" colspan="2">
									<html:button property="" value="截屏" onclick="openPng();" />
								</td>
							</tr>

						</html:form>
					</table>
				</td>-->
				<td>

					<logic:present name="atmPngList">
						<table cellspacing="0" cellpadding="0">
							<logic:iterate id="tlr" name="atmPngList">
								<tr>
									<th>
										<bean:write name="tlr" property="atmid" />
										<bean:write name="tlr" property="addr" />
										<bean:write name="tlr" property="tradetime" />
									</th>
								</tr>
								<tr>
									<td>
										<img src="${tlr.route}" width="600" height="450" border="0" />
									</td>
								</tr>
							</logic:iterate>
						</table>
					</logic:present>
				</td>
			</tr>
		</table>
	</body>
</html>
<script type="text/javascript">
  function openPng(){
    if(document.getElementById("atmids").value==""){
      	alert("请选择ATM编号！");
      	return false;
    }
    document.getElementById("form1").submit();
   }
</script>


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
		<script type="text/javascript" src="resources/scripts/calendar.js"></script>
		<link href="resources/source/tablecloth.css" rel="stylesheet"
			type="text/css" media="screen" />
		<script type="text/javascript">
<!--
function check(action){
 if(action==1)
document.forms[0].action="check.do";
else if(action==2)
document.forms[0].action="reportCheck.do";
document.forms[0].submit();
}
//-->
</script>
	</head>

	<body>
		<html:form action="check.do" enctype="multipart/form-data"
			target="checkMainFrame">
			<table cellspacing="0" cellpadding="0">
				<tr>
				<tr>
					<th colspan="2">
						对账参数
					</th>
				</tr>
				<tr>
					<td align="right">
						对账日期：
					</td>
					<td>
						<html:text property="time" onfocus="calendar()" size="7" />
					</td>
				</tr>

				<tr>
					<td align="right">
						设备编号：
					</td>
					<td width="10%">

						<html:select property="type" style="width:100%"
							onchange="selectType(this)" styleId="sId">
							<logic:equal value="111" property="emlevel" name="login">
								<html:option value="0">全部设备</html:option>
							</logic:equal>
							<logic:present name="typelist">
								<logic:iterate id="tlr" name="typelist">
									<html:option value="${tlr.atmid}">
																${tlr.type }
															</html:option>
								</logic:iterate>
							</logic:present>
							<logic:equal value="111" property="emlevel" name="login">
								<html:option value="5">自选设备</html:option>
							</logic:equal>
							<html:option value="6">手动对账</html:option>

						</html:select>
					</td>
				</tr>
				<tr style="display: none;" id="atmids">
					<td colspan="2">
						<html:select property="atmids" style="width:100%"
							onchange="selectType()" styleId="sId" size="15" multiple="true">
							<logic:notEmpty name="atmid">
								<html:optionsCollection name="atmid" value="atmid" label="atmid" />
							</logic:notEmpty>
						</html:select>
					</td>
				</tr>
				<tr style="display: none;" id="file">
					<td align="right">
						对账流水：
					</td>
					<td>
						<html:file property="file" size="7" />
					</td>
				</tr>
				<tr>
					<td width="11%" align="right">
						<html:button property="" onclick="check(1);" value="显示" />
					</td>
					<td width="11%">
						<html:button property="" onclick="check(2);" value="导出" />
					</td>
				</tr>
			</table>
		</html:form>

	</body>
</html>

<script type="text/javascript">
  function selectType(obj){
  var strsel = obj.options[obj.selectedIndex].value;
  if(strsel==5){
  document.getElementById("file").style.display="none";
    document.getElementById("atmids").style.display="block";
    }
   else if(strsel==6){
   document.getElementById("file").style.display="block";
   	 document.getElementById("atmids").style.display="none";
   }
   else{
   document.getElementById("atmids").style.display="none";
   document.getElementById("file").style.display="none";
   }
   }
</script>

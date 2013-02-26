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
		<script type="text/javascript">
  function modifyCompany(){
     if(document.getElementById("companyCh").value==""){
      alert("请输入设备厂商(中文)!");
      return false;
    }else if(document.getElementById("companyEn").value==""){
      alert("请输入设备厂商(英文)!");
      return false;
    }
    document.forms[0].submit();
   }
</script>
		<link href="resources/source/tablecloth.css" rel="stylesheet"
			type="text/css" media="screen" />
	</head>

	<body>
		<table cellspacing="0" cellpadding="0">
			<tr>
				<th colspan="2">
					修改厂商
				</th>
			</tr>
			<logic:present name="atmcompany">
				<html:form action="infoconfig.do?method=modifyCompany" method="post">
					<tr>
						<td>
							编号
						</td>
						<td>
							<bean:write name="atmcompany" property="id" />
						</td>
					</tr>
					<tr>
						<td>
							厂商名称(中文)
						</td>
						<td>
							<html:text name="atmcompany" property="chname" size="15"
								styleId="companyCh" />
						</td>
					</tr>
					<tr>
						<td>
							厂商名称(英文)
						</td>
						<td>
							<html:text name="atmcompany" property="enname" size="15"
								styleId="companyEn" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<html:button property="" value="修改" onclick="modifyCompany();" />
						</td>
					</tr>
				</html:form>
			</logic:present>
		</table>
		<div align="right">
			<a href="infoconfig.do?method=companyAll"> 返回 </a>
		</div>
	</body>
</html>


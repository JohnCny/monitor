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
  function addCompany(){
    if(document.getElementById("companyId").value==""){
      alert("请输入编号！");
      return false;
    }else if(document.getElementById("companyCh").value==""){
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
					新增厂商
				</th>
			</tr>
			<html:form action="infoconfig.do?method=setCompany" method="post">
				<tr>
					<td>
						编号
					</td>
					<td>
						<html:text property="id" size="15" styleId="companyId" />
					</td>
				</tr>
				<tr>
					<td>
						厂商名称(中文)
					</td>
					<td>
						<html:text property="chname" size="15" styleId="companyCh" />
					</td>
				</tr>
				<tr>
					<td>
						厂商名称(英文)
					</td>
					<td>
						<html:text property="enname" size="15" styleId="companyEn" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<html:button property="" value="创建" onclick="addCompany();" />
					</td>
				</tr>
			</html:form>
		</table>
		<div align="right">
			<a href="infoconfig.do?method=companyAll"> 返回 </a>
		</div>
		<logic:present name="setcompany_err">
			<div align="center" style="font-size: 2em;">
				编号：
				<bean:write name="setcompany_err" property="id" />
				已经存在!
			</div>
		</logic:present>
	</body>
</html>


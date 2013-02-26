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
  function addRun(){
    if(document.getElementById("RunId").value==""){
      alert("请输入编号！");
      return false;
    }else if(document.getElementById("RunCh").value==""){
      alert("请输入运营类型(中文)!");
      return false;
    }else if(document.getElementById("RunEn").value==""){
      alert("请输入运营类型(英文)!");
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
					新增运营
				</th>
			</tr>
			<html:form action="infoconfig.do?method=setRun" method="post">
				<tr>
					<td>
						编号
					</td>
					<td>
						<html:text property="id" size="15" styleId="RunId" />
					</td>
				</tr>
				<tr>
					<td>
						运营类型(中文)
					</td>
					<td>
						<html:text property="chname" size="15" styleId="RunCh" />
					</td>
				</tr>
				<tr>
					<td>
						运营类型(英文)
					</td>
					<td>
						<html:text property="enname" size="15" styleId="RunEn" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<html:button property="" value="创建" onclick="addRun();" />
					</td>
				</tr>
			</html:form>
		</table>
		<div align="right">
			<a href="infoconfig.do?method=runAll"> 返回 </a>
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


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
  function modifyType(){
    if(document.getElementById("typeCh").value==""){
      alert("请输入设备类型(中文)!");
      return false;
    }else if(document.getElementById("typeEn").value==""){
      alert("请输入设备类型(英文)!");
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
					修改类型
				</th>
			</tr>
			<logic:present name="atmtype">
				<html:form action="infoconfig.do?method=modifyType" method="post">
					<tr>
						<td>
							编号
						</td>
						<td>
							<bean:write name="atmtype" property="id" />
						</td>
					</tr>
					<tr>
						<td>
							设备类型(中文)
						</td>
						<td>
							<html:text name="atmtype" property="chname" size="15"
								styleId="typeCh" />
						</td>
					</tr>
					<tr>
						<td>
							设备类型(英文)
						</td>
						<td>
							<html:text name="atmtype" property="enname" size="15"
								styleId="typeEn" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<html:button property="" value="创建" onclick="modifyType();" />
						</td>
					</tr>
				</html:form>
			</logic:present>
		</table>
		<div align="right">
			<a href="infoconfig.do?method=typeAll"> 返回 </a>
		</div>
	</body>
</html>


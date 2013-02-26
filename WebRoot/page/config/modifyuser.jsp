<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<link href="resources/source/tablecloth.css" rel="stylesheet"
			type="text/css" media="screen" />
	</head>

	<body>
		<table cellspacing="0" cellpadding="0">
			<tr>
				<th colspan="2">
					用户管理
				</th>
			</tr>
			<logic:present name="userbyusername">
				<html:form action="user.do?method=modify" method="post">
					<tr>
						<td align="right">
							用户名：
						</td>
						<td align="left">
							<input id="username" type="text" name="username" size="15"
								value="${userbyusername.username}"
								onkeyup="value=value.replace(/[\W]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"
								readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td align="right">
							身份：
						</td>
						<td align="left">
							<html:select property="title" value="${userbyusername.title}">
								<html:option value="1">系统管理员</html:option>
								<html:option value="2">部门经理</html:option>
								<html:option value="3">部门办事员</html:option>
								<html:option value="4">维护工程师</html:option>
								<html:option value="5">网点管理员</html:option>
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							密码：
						</td>
						<td align="left">
						<input type="password">
							<html:password styleId="password1" property="password"
								name="userbyusername" size="16" />
						</td>
					</tr>
					<tr>
						<td align="right">
							确认密码：
						</td>
						<td align="left">
							<html:password styleId="password2" property="password"
								name="userbyusername" size="16" />
						</td>
					</tr>
					<tr>
						<td align="right">
							姓名：
						</td>
						<td align="left">
							<html:text property="name" name="userbyusername" size="15"></html:text>
						</td>
					</tr>
					<tr>
						<td align="right">
							电话：
						</td>
						<td>
							<input id="username" type="text" name="phone" size="15"
								value="${userbyusername.phone}"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''" />
						</td>
					</tr>
					<tr>
						<td align="right">
							Email：
						</td>
						<td>
							<html:text property="email" name="userbyusername" size="15"></html:text>
						</td>
					</tr>

					<tr>
						<td align="right">
							监控权限：
						</td>
						<td>
							<html:multibox property="emlevel1" value="1"
								name="userbyusername" />
							在行自有
							<br>
							<html:multibox property="emlevel2" value="1"
								name="userbyusername" />
							离行自有
							<br>
							<html:multibox property="emlevel3" value="1"
								name="userbyusername" />
							离行运营
							<br>
						</td>
					</tr>

					<tr>
						<td align="right">
							管理权限：
						</td>
						<td>
<html:multibox property="salevel" value="1" name="userbyusername" />
							统计分析
							<br>
							<html:multibox property="rmlevel" value="1" name="userbyusername" />
							远程管理
							<br>
							<html:multibox property="ctlevel" value="1" name="userbyusername" />
							对账管理
							<br>
							<html:multibox property="umlevel" value="1" name="userbyusername" />
							短信管理
							<br>
							<html:multibox property="sclevel" value="1" name="userbyusername" />
							系统管理
							<br>
						</td>
					</tr>
					<tr>
						<td align="right">
							状态：
						</td>
						<td>
							<html:select property="status" value="${userbyusername.status}"
								style="width:50%">
								<html:option value="0">注销</html:option>
								<html:option value="1">启用</html:option>
							</html:select>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="right">
							<html:button property="" value="修改" onclick="modifyuser();" />
						</td>
					</tr>
				</html:form>
			</logic:present>
		</table>
	</body>
</html>
<script type="text/javascript">
  function modifyuser(){
   if(document.getElementById("password").value==""){
      alert("请填写密码!");
      return false;
    }else if(document.getElementById("password2").value!=document.getElementById("password1").value){
      alert("两次密码输入不一致!");
      return false;
    }
    document.forms[0].target="userMainFrame";
    document.forms[0].submit();
   }
</script>

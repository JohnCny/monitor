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
		<script type="text/javascript" src="resources/scripts/MyJs.js"></script>
		<script type="text/javascript">
		<!--
		function addsms(action){
		if(document.getElementById('moblie').value==""){
      		alert("请输入手机号！");
      		return false;
   		}
   		var select=document.getElementById('select1');
   		for(var i=0;i<select.length;i++){
			select.options[i].selected=true;
		}
		document.forms[0].submit();
		}
		function getatmid(obj)
		{
		var strsel = obj.options[obj.selectedIndex].value;
		window.location.href="smsconfig.do?method=getAtmIdByType&type="+strsel ;
		}
		
		//-->
		</script>
	</head>

	<body>
		<table cellspacing="0" cellpadding="0" style="width: 800px">
			<tr>
				<th colspan="2">
					维护短信用户注册
				</th>
			</tr>
			<tr>
				<td valign="top">
					<table id="mytable" cellspacing="0">
						<html:form action="smsconfig.do?method=set" method="post">
							<tr align="center">
								<td align="right">
									故障通知类型：
								</td>
								<td align="left">
									<html:select property="bugtype" style="width:100%"
										value="${type}" onchange="getatmid(this);">
										<html:option value="0">请选择...</html:option>
										<html:option value="1">设备故障</html:option>
										<html:option value="2">业务故障</html:option>
										<html:option value="3">设备故障超时</html:option>
										<html:option value="4">业务故障故障</html:option>

									</html:select>
								</td>
							</tr>
							<tr align="center">
								<td align="right">
									负责设备：
								</td>
								<td>
									<html:select property="atmids" style="width:170px"
										multiple="true" size="5" styleId="select1">
									</html:select>
								</td>
								<td>
									<input type="button" value="》》"
										onclick="DoAdd('select1','select2');" />
									<br>
									<input type="button" value="《《"
										onclick="DoAdd('select2','select1');" />
								</td>
							</tr>
							<tr align="center">
								<td align="right">
									手机号：
								</td>
								<td align="left">
									<input id="moblie" type="text" name="moblie" size="20"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
								</td>
							</tr>
							<tr>
								<td align="right">
									姓名：
								</td>
								<td align="left">
									<html:text property="name" size="20" />
								</td>
							</tr>
							<!-- <tr align="center">
					<td align="right">
						故障修复时间：
					</td>
					<td align="left">
						<html:text property="solvetime" size="20" />
					</td>
				</tr> -->
							<tr>
								<td align="right">
									状态：
								</td>
								<td>
									<html:select property="status" style="width:100%">
										<html:option value="0">注销</html:option>
										<html:option value="1">启用</html:option>
									</html:select>
								</td>
							</tr>
							<tr align="center">
								<td colspan="2">
									<html:button property="" value="添加" onclick="addsms();" />
								</td>
							</tr>
						</html:form>
					</table>
				</td>
				<td valign="top">
					<table border="0" cellpadding="2" cellspacing="1" class="tablebg">
						<tr>
							<td>
								设备列表
							</td>
						</tr>
						<tr>
							<td>
								<select style="width: 200px;" multiple size="15" id="select2">
									<logic:present name="atmidbytype">
										<logic:iterate id="tlr" name="atmidbytype">
											<option>
												<bean:write name="tlr" />
											</option>
										</logic:iterate>
									</logic:present>
								</select>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: right">
					<a href="smsconfig.do?method=all">返回</a>
				</td>
			</tr>
		</table>
	</body>
</html>
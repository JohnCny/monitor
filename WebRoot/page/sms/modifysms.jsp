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
					维护短信用户管理
				</th>
			</tr>
			<tr>
				<td valign="top">
					<table cellspacing="0" cellpadding="0">
						<logic:present name="userbymoblie">
							<html:form action="smsconfig.do?method=modify" method="post">

								<tr align="center" class="front_dbt">
									<td align="right">
										故障通知类型：
									</td>
									<td align="left">
										<!--<html:select property="bugtype" style="width:100%"
								value="${userbymoblie.bugtype}" disabled="false">
								<html:option value="1">设备故障</html:option>
								<html:option value="2">业务故障</html:option>
								<html:option value="3">超时故障</html:option>
							</html:select>-->
										<logic:equal value="1" property="bugtype" name="userbymoblie">
								设备故障
								<html:hidden property="bugtype" value="1" />
										</logic:equal>
										<logic:equal value="2" property="bugtype" name="userbymoblie">
								业务故障
								<html:hidden property="bugtype" value="2" />
										</logic:equal>
										<logic:equal value="3" property="bugtype" name="userbymoblie">
								超时故障
								<html:hidden property="bugtype" value="3" />
										</logic:equal>
										<logic:equal value="4" property="bugtype" name="userbymoblie">
								业务故障超时
								<html:hidden property="bugtype" value="4" />
										</logic:equal>

									</td>
								</tr>
								<tr align="center" class="front_dbt">
									<td align="right" class="front_dbt">
										负责设备：
									</td>
									<td>
										<html:select property="atmids" style="width: 170px;"
											multiple="true" size="5" styleId="select1">
											<logic:present name="atmids">
												<logic:iterate id="tlr" name="atmids">
													<option>
														<bean:write name="tlr" />
													</option>
												</logic:iterate>
											</logic:present>
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
								<tr align="center" class="front_dbt">
									<td align="right">
										手机号：
									</td>
									<td align="left">
										<input id="moblie" type="text" name="moblie" size="20"
											value="${userbymoblie.moblie}"
											onkeyup="value=value.replace(/[^\d]/g,'') "
											onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
										<html:hidden name="userbymoblie" property="moblie_old" />
									</td>
								</tr>
								<tr class="front_dbt">
									<td align="right">
										姓名：
									</td>
									<td align="left">
										<html:text name="userbymoblie" property="name" size="20" />
									</td>
								</tr>
								<!-- <tr align="center" class="front_dbt">
						<td align="right">
							故障修复时间：
						</td>
						<td align="left">
							<html:text property="solvetime" name="userbymoblie" size="20" />
						</td>
					</tr> -->
								<tr class="front_dbt">
									<td align="right">
										状态：
									</td>
									<td>
										<html:select name="userbymoblie" property="status"
											style="width:100%" value="${userbymoblie.status}">
											<html:option value="0">注销</html:option>
											<html:option value="1">启用</html:option>
										</html:select>
									</td>
								</tr>
								<tr class="front_dbt" align="center">
									<td colspan="2">
										<html:button property="" value="修改" onclick="modifysms();" />
									</td>
								</tr>


							</html:form>
						</logic:present>
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
<script type="text/javascript">
		<!--
		function modifysms(action){
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
		//-->
		</script>

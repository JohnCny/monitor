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
		
	</head>

	<body>
		<html:form action="atmstatustitle.do?method=all" method="post"
			styleId="form1">
			<table cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<html:multibox property="sbgz" value="1" />
						服务
					</td>

					<td>
						<html:multibox property="lsdyjgz" value="1" />
						流水打印
					</td>

					<td>
						<html:multibox property="ptdyjgz" value="1" />
						凭条打印
					</td>

					<td>
						<html:multibox property="ccqgz" value="1" />
						出钞器
					</td>

					<td>
						<html:multibox property="qkmkgz" value="1" />
						存款模块
					</td>

					<td>
						<html:multibox property="dkqgz" value="1" />
						读卡器
					</td>

					<td>
						<html:multibox property="xlgz" value="1" />
						线路
					</td>

					<td>
						<html:multibox property="cxgz" value="1" />
						钞箱
					</td>
				</tr>
				<tr>

					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						<html:select property="style">
							<html:option value="1">列表</html:option>
							<html:option value="2">图标</html:option>
						</html:select>
					</td>
					<!--<td>
						<html:select property="atm">
							<logic:present name="typelist">
								<logic:iterate id="tlr" name="typelist">
									<html:option value="${tlr.atmid}">
																${tlr.type }
															</html:option>
								</logic:iterate>
							</logic:present>
						</html:select>
					</td>  -->
					<td>
						<html:button property="" value="筛选" onclick="showType(1);" />
					</td>
					<td colspan="4">
						<html:button property="" value="暂停服务设备筛选" onclick="showType(3);" />
					</td>
					<!-- <td>
						<html:button property="" value="导出" onclick="showType(2);" />
					</td> -->
				</tr>
			</table>
		</html:form>
	</body>
</html>

<script type="text/javascript">
  function showType(action){
  var searchButton = document.getElementById("form1");
  if(action==1)
   		searchButton.action="atmstatustitle.do?method=all";
    else if(action==2)
    	searchButton.action="atmstatereportxls.do";
    	else if(action=3)
    	    	searchButton.action="atmstatustitle.do?method=all&showType=error";
    document.forms[0].submit();
   }
   	setInterval("showType(1)",180000);//每3分钟刷新一次 
</script>

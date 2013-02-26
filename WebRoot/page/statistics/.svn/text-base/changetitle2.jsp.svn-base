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
		<link href="resources/source/tablecloth.css" rel="stylesheet"
			type="text/css" media="screen" />
		<script type="text/javascript" src="resources/scripts/calendar.js"></script>
		<script type="text/javascript">
<!--
function submit1(type)
{
var rs=document.getElementById("rs").value;
//var ts=document.getElementById("ts").value;
//var es=document.getElementById("es").value;
//var t=document.getElementById("t").value;
//var cs=document.getElementById("cs").value;
	if(rs!=2){
	 if(document.getElementById('begin').value==""){
      	alert("请选择起始日期！");
      	return false;
    }else if(document.getElementById('end').value==""){
      	alert("请选择结束日期!");
      	return false;
    }
    }
	
    if(type==0)
   		document.forms[0].action="statiform2.do";
    else if(type==1)
    	document.forms[0].action="reportxls2.do";
    document.forms[0].submit();
}

function change(obj){
var strsel = obj.options[obj.selectedIndex].value;
var tr=document.getElementById("tr1");
var select=document.getElementById('selectId');
if(strsel==5)
tr.style.display="block";
else{
tr.style.display="none";
for(var i=0;i<select.length;i++){
			select.options[i].selected=false;
		}
}
}

function changetype(obj){
var strsel = obj.options[obj.selectedIndex].value;
var trtd=document.getElementById("trtb");
var trte=document.getElementById("trte");

if(strsel=='1')
{
trtd.style.display="block";
trte.style.display="block";
}
else if(strsel=='2'){
trtd.style.display="none";
trte.style.display="none";
}

}
//-->
</script>

	</head>

	<body>
		<html:form action="statiform.do" method="post" target="statMainFrame">
			<table cellspacing="0" cellpadding="0">
				<tr>
					<th colspan="2">
						统计参数
					</th>
				</tr>

				<tr>
					<td>
						报表类型：
					</td>

					<td width="7%">
						<html:select property="reportType" style="width:75px" styleId="rs"
							onchange="changetype(this);">
							<html:option value="1">故障分析</html:option>
							<html:option value="2">归属厂商</html:option>
							<!--<html:option value="8">开机率分析</html:option>-->
						</html:select>
					</td>
				</tr>

				<tr>
					<td>
						运营类型：
					</td>

					<td id="ss" width="9%">
						<html:select property="equipType" style="width:100%"
							onclick="change(this);" styleId="es">
							<logic:equal value="111" property="emlevel" name="login">
								<html:option value="9">全部设备</html:option>
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
						</html:select>
					</td>
				</tr>
				<tr id="tr1" style="display: none;">
					<td id="bs" colspan="2">
						<html:select property="atmids" style="width:100%" multiple="true"
							size="10" styleId="selectId">
							<logic:notEmpty name="atmid">
								<html:optionsCollection name="atmid" value="atmid" label="atmid" />
							</logic:notEmpty>
						</html:select>
					</td>
				</tr>
				<tr id="route" style="display: none;">
					<td>
						设备类型：
					</td>
					<td width="6%">
						<html:select property="route" style="width:100%">
							<html:option value="0">全部</html:option>
							<html:option value="1">取款机</html:option>
							<html:option value="2">存款机</html:option>
						</html:select>
					</td>
				</tr>
				<tr id="trtb">
					<td>
						起始日期：
					</td>

					<td>
						<html:text styleId="begin" property="timebegin"
							onfocus="calendar()" size="7" />
					</td>
				</tr>
				<tr id="trte">
					<td>
						结束日期：
					</td>

					<td>
						<html:text styleId="end" property="timeend" size="7"
							onfocus="calendar()" />
					</td>
				</tr>

				<tr>
					<td>
						<html:button property="" onclick="submit1('0');" value="分析" />
					</td>
					<td>
						<html:button property="" onclick="submit1('1');" value="导出" />
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>

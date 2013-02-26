<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib prefix="tiles" uri="/WEB-INF/struts-tiles.tld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>短信管理-发信管理</title>
		<base href="<%=basePath%>">
		<link href="resources/source/tablecloth.css" rel="stylesheet"
			type="text/css" media="screen" />
		<script type="text/javascript" src="resources/scripts/jquery-1.6.js"></script>
		<script type="text/javascript" src="resources/scripts/MyJs.js"></script>
			<style type="text/css">
* {
	font-size: 12px;
}

body {
	margin: 0;
	padding: 0;
	background-color: #FFFFFF;
	font-size: 12px;
	color: #666666;
	font-family: "宋体", arial, Helvetica, sans-serif;
}

/*主导航菜单*/
#menu ul {
	padding: 0;
	border: 0;
	list-style: none;
	line-height: 100%;
	margin:0;
}

#menu_out {
	width:90px; padding-top:20px; background: url(resources/source/menu/m_top.gif) no-repeat left top; margin-top:39px;
}

#menu_in {
	width:90px; padding-bottom:20px; background: url(resources/source/menu/m_bot.gif) no-repeat left bottom;
}

.zwh{ height:40px; background:#1a92d5; border-left:1px solid #012b67;}
#menu {
}


#nav {
}

#nav li {
	background: url(resources/source/menu/menu_on_left.gif) repeat-x left top; border-left:1px solid #012b67;
}

#nav li a {
	display: block;
	padding-left: 6px;
	height: 35px;

	cursor: pointer;
	text-decoration: none;

}

#nav li a span {
	float:right;
	padding: 11px 10px 10px 10px;
	line-height: 14px;
	font-size: 14px;
	font-weight: bold;
	color: #FFFFFF;
	text-decoration: none;
	vertical-align:middle;
}

 /*鼠标经过时变换背景*/

#nav li .nav_on span {
	background: url(resources/source/menu/menu_on_right_h.gif) no-repeat left top;
	color: #333333;
	text-decoration: none;
	padding: 11px 10px 10px 10px;
}


/*子栏目布局*/
#menu_s ul {
	padding: 0;
	border: 0;
	list-style: none;
	line-height: 150%;
	margin-top: 0;
	margin-right: 0;
	margin-bottom: 0;
	margin-left: 15px;
	float: right;
}

#menu_out_s {
	width: 99%;
	padding-left: 4px;
	margin-left: auto;
	margin-right: auto;
	background: url(resources/source/menu/menu_left.gif) no-repeat left bottom;
}

#menu_in_s {
	background: url(resources/source/menu/menu_right.gif) no-repeat right bottom;
	padding-right: 4px;
}

#menu_s {
	background: url(resources/source/menu/menu_bg.gif) repeat-x left bottom;
	height: 37px;
}



.menu_line2 {
	background: url(resources/source/menu/menu_line2.gif) no-repeat left top;
	width: 15px;
}

/*子栏目*/
#menu_con {
	text-align: left;
	clear: both;
}

#menu_con li {
	float: left;
	height: 22px;
	margin-top: 8px;
}

#menu_con li a {
	display: block;
	float: left;
	background: url(resources/source/menu/menu_on_left2.gif) no-repeat left
		top;
	cursor: pointer;
	padding-left: 3px;
		text-decoration: none;
		color:blue;
}

#menu_con li a span {
	float: left;
	padding: 6px 10px 4px 10px;
	line-height: 12px;
	background: url(resources/source/menu/menu_on_right2.gif) no-repeat
		right top;
}

/*鼠标悬停效果*/
#menu_con li a:hover {
	text-decoration: none;
	background: url(resources/source/menu/menu_on_left2.gif) no-repeat left bottom;
	color:blue;
}

#menu_con li a:hover span {
	background: url(resources/source/menu/menu_on_right2.gif) no-repeat right bottom;
}
/*鼠标悬停效果*/



/*当前栏目效果*/
#menu_con li .menu_on{	text-decoration: none;
	background: url(resources/source/menu/menu_on_left3.gif) no-repeat left bottom;
	color:#666666;
}
#menu_con li .menu_on span {	background: url(resources/source/menu/menu_on_right3.gif) no-repeat right bottom;
}
/*当前栏目效果*/
#box_m{width:1200px;margin:0 auto;}
#box_nav{float:left; width:90px;}
#box_nav_s{float:left; widows:1000px;}
</style>
		<script type="text/javascript">
		$(document).ready(function(){
			$("#setsmsuser").click(function(){
			    $("#ly")[0].style.display="block";
				$("#ly")[0].style.width=document.body.clientWidth;
				$("#ly")[0].style.height=document.body.clientHeight;
				$("#Layer2")[0].style.display='block';
			      
			});
			
			$('#selecterror').change(function(){
				 var value=$(this).children('option:selected').val();
        		 $.ajax({
                 url:'smsconfig.do?method=getAtmIdByType&type='+value, 
                 type: 'get',
                 cache:false,
                 timeout: 2000,
                 error: function(){
                    alert('错误');
                 },
                success: function(json){
                	
				var users = eval('(' + json + ')');
                
                var table_="<table cellspacing='0' cellpadding='0'>"+
                			"<tr><td>设备列表</td></tr>"+
                			"<tr><td><select style='width: 200px;' multiple size='15' id='select2'>";
                 $.each(users,function(i, user){
                 	table_+="<option>"+user+"</option>";
					});					
					table_+="</select></td></tr></table>";
				$("#devicelist").html(table_); 
				
                }
         });
    		});
			
			
			/////////////////////////////////////////////
			$('.modifysmsuser').click(function(){
			var value_1=$(this).attr("id")
      		var value_2=$(this).attr("name");
      		var value_3=$(this).attr("solvetime");
        		 $.ajax({
                 url:'smsconfig.do?method=getByMoblie&id='+value_1+'&bugtype='+value_2+'&solvetime='+value_3, 
                 type: 'get',
                 cache:false,
                 timeout: 2000,
                 error: function(){
                    alert('错误');
                 },
                success: function(json){
                	
				var users = eval('(' + json + ')');
				
				var modifysmsuserdiv="<table cellspacing='0' cellpadding='0'  style='width: 800px'>"+
					"<tr><th colspan='2'>编辑短信用户	</th></tr>"+
					"<tr><td valign='top'><table cellspacing='0' cellpadding='0'>"+
					"<form action='smsconfig.do?method=modify' method='post'>"+
					"<tr><td>通知类型：</td><td>"
                var l=0;
                $.each(users,function(i, user){
					if(i==0)
						modifysmsuserdiv+="<input type='text' value='"+(user==1?'设备故障':user==2?'业务故障':user==3?'设备故障超时':user==4?'业务故障超时':'')+"' size='15'  style='width:100%' readonly='readonly' /><input type='hidden' name='bugtype' value='"+user+"' /></td></tr>";
					if(i==1){
						l=user;
						modifysmsuserdiv+="<tr><td>通知级别：</td><td><select name='solvetime' style='width: 100%;'><option "+(user==1?'selected':'')+" value='1'>一级通知</option><option "+(user==2?'selected':'')+" value='2'>二级通知</option><option "+(user==3?'selected':'')+" value='3'>三级通知</option></select>";
						modifysmsuserdiv+="<input type='hidden' value='"+user+"' name='solvetime_old' /></td></tr>"
					}
					if(i==2){
						l=user;
						modifysmsuserdiv+="<tr><td>负责设备：</td><td><select name='atmids' style='width: 170px;' multiple size='15' id='select1'>";
					}
					if(i>2&&i<=Number(l)+2)
						modifysmsuserdiv+="<option>"+user+"</option>";
					if(i==Number(l)+3){
						modifysmsuserdiv+="</select></td><td><input type='button' value='》》' 	onclick='DoAdd();' /><br><input type='button' value='《《' onclick='DoDel();' /></td></tr><tr><td align='right'>手机号：</td><td><input id='moblie' type='text' name='moblie' size='20' value='"+user+"' style='width:100%'	onkeyup='value=value.replace(/[^\d]/g,'') '	onbeforepaste='clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))' />";
						modifysmsuserdiv+="<input type='hidden' value='"+user+"' name='moblie_old' /></td></tr>";
					}if(i==Number(l)+4)
						modifysmsuserdiv+="<tr><td>姓名：</td><td><input type='text' name='name' value='"+user+"' size='20' style='width:100%' /></td></tr>";
					if(i==Number(l)+5)
						modifysmsuserdiv+="<tr><td>状态：</td><td><select name='status' style='width:100%'><option "+(user==0?'selected':'')+" value='0'>注销</option><option "+(user==1?'selected':'')+" value='1'>启用</option></select></td></tr><tr><td colspan='2'><input type='button' value='修改' onclick='addsms();' />	</td></tr></form></table></td><td  valign='top'><table cellpadding='0' cellspacing='0'><tr><td>设备列表</td></tr><tr><td><select style='width: 200px;' multiple size='15' id='select2'>";
					if(i>Number(l)+6)
						modifysmsuserdiv+="<option>"+user+"</option>";
					});	
					modifysmsuserdiv+="</select></td></tr></table></td></tr></table>"; 
					
				$("#modifysmsuserdiv").html(modifysmsuserdiv);
									
               }
         });
         $("#ly")[0].style.display="block";
				$("#ly")[0].style.width=document.body.clientWidth;
				$("#ly")[0].style.height=document.body.clientHeight;
				$("#Layer2")[0].style.display='block';
    		});
		});
		

function Lock_CheckForm(){
document.all.ly.style.display='none';
document.all.Layer2.style.display='none';
location.replace("smsstatusconfig.do?method=all");    
return false;
}

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
		document.forms[1].submit();
		}
		
		
		//-->
</script>
	</head>

	<body style="text-align: center; background: #E6EAE9;">
	<div id="box_m">
		<div id="box_nav">
			<tiles:insert page="../../page/view/left_main.jsp" flush="false" />
		</div>
		<div id="box_nav_s">
			<tiles:insert page="../../page/view/top_sub.jsp" flush="false" />
		<div style="clear:both"></div>
		<div>
		<table cellspacing="0" cellpadding="0" style="width: 1000px;">
			<tr>
				<td colspan="2">
					<!--<tiles:insert page="../../page/view/topmenu.jsp" flush="false" />-->
				</td>
			</tr>
			<tr>
				<td valign="top" style="width: 20%;">
					<table cellspacing="0" cellpadding="0">
						<html:form action="smsstatusconfig.do?method=set" method="post">
							<logic:present name="smscontrolstatus">
								<tr>
									<th colspan="2">
										短信管理
									</th>
								</tr>
								<tr>
									<td>
										短信通知：
									</td>
									<td>
										<html:select property="smsstatus" style="width:100px"
											value="${smscontrolstatus.smsstatus}" styleId="macbug">
											<html:option value="0">关闭</html:option>
											<html:option value="1">通知</html:option>
										</html:select>
									</td>
								</tr>
								<tr id="macbugtr">
									<td>
										一级通知时间：
									</td>
									<td>
										<html:select property="firsttime" style="width:100px"
											value="${smscontrolstatus.firsttime}">
											<html:option value="0">立刻</html:option>
											<html:option value="1">1小时</html:option>
											<html:option value="2">2小时</html:option>
											<html:option value="3">3小时</html:option>
											<html:option value="4">4小时</html:option>
											<html:option value="5">5小时</html:option>
											<html:option value="6">6小时</html:option>
											<html:option value="7">7小时</html:option>
											<html:option value="8">8小时</html:option>
											<html:option value="9">9小时</html:option>
											<html:option value="10">10小时</html:option>
											<html:option value="11">11小时</html:option>
											<html:option value="12">12小时</html:option>
										</html:select>
									</td>
								</tr>
								<tr>
									<td>
										二级通知时间：
									</td>
									<td>
										<html:select property="secondtime" style="width:100px"
											value="${smscontrolstatus.secondtime}">
											<html:option value="0">立刻</html:option>
											<html:option value="1">1小时</html:option>
											<html:option value="2">2小时</html:option>
											<html:option value="3">3小时</html:option>
											<html:option value="4">4小时</html:option>
											<html:option value="5">5小时</html:option>
											<html:option value="6">6小时</html:option>
											<html:option value="7">7小时</html:option>
											<html:option value="8">8小时</html:option>
											<html:option value="9">9小时</html:option>
											<html:option value="10">10小时</html:option>
											<html:option value="11">11小时</html:option>
											<html:option value="12">12小时</html:option>
										</html:select>
									</td>
								</tr>
								<tr id="buibugtr">
									<td>
										三级通知时间：
									</td>
									<td>
										<html:select property="thirdtime" style="width:100px"
											value="${smscontrolstatus.thirdtime}">
											<html:option value="0">立刻</html:option>
											<html:option value="1">1小时</html:option>
											<html:option value="2">2小时</html:option>
											<html:option value="3">3小时</html:option>
											<html:option value="4">4小时</html:option>
											<html:option value="5">5小时</html:option>
											<html:option value="6">6小时</html:option>
											<html:option value="7">7小时</html:option>
											<html:option value="8">8小时</html:option>
											<html:option value="9">9小时</html:option>
											<html:option value="10">10小时</html:option>
											<html:option value="11">11小时</html:option>
											<html:option value="12">12小时</html:option>
										</html:select>
									</td>
								</tr>
								<tr>
									<td>
										<html:submit value="设定"></html:submit>
									</td>
									<td>
										<input type="button" value="创建用户" id="setsmsuser" />
									</td>
								</tr>
							</logic:present>
						</html:form>
						<logic:present name="setsmsuser_err">
							<tr>
								<td colspan="2">
									<div align="center" style="font-size: 2em;">
										同一个手机号不允许设置相同的故障类型和通知级别
									</div>
								</td>
							</tr>
						</logic:present>
					</table>
				</td>
				<td valign="top">
					<div id="doc">
						<div class="fixTh" style="text-align: left;">
							<table cellspacing="0" cellpadding="0" style="width: 98.5%">

								<tr>
									<th width="14%">
										姓名
									</th>
									<th width="14%">
										手机号
									</th>
									<th width="22%">
										负责设备号
									</th>
									<th width="14%">
										通知类型
									</th>
									<th width="14%">
										通知级别
									</th>
									<th width="14%">
										状态
									</th>
									<th width="8%">
										操作
									</th>
								</tr>
							</table>
							<div class="tc" style="height: 470px">
								<logic:present name="smsuser">
									<table cellspacing="0" cellpadding="0">
										<logic:iterate id="tlr" name="smsuser">
											<tr>
												<td width="14%">
													<bean:write name="tlr" property="name" />
												</td>

												<td width="14%">
													<bean:write name="tlr" property="moblie" />
												</td>
												<td width="22%">
													<bean:write name="tlr" property="atmids" filter="false" />
												</td>
												<td width="14%">
													<logic:equal name="tlr" property="bugtype" value="1">
								设备故障
							</logic:equal>
													<logic:equal name="tlr" property="bugtype" value="2">
								业务故障
							</logic:equal>
													<logic:equal name="tlr" property="bugtype" value="3">
								设备故障超时
							</logic:equal>
													<logic:equal name="tlr" property="bugtype" value="4">
								业务故障超时
							</logic:equal>
												</td>
												<td width="14%">
													<bean:write name="tlr" property="solvetime" />
													级
												</td>
												<td width="14%">
													<logic:equal name="tlr" property="status" value="1">
														<font color="#00DB00">启用</font>
													</logic:equal>
													<logic:equal name="tlr" property="status" value="0">
														<font color="#FF0000">注销</font>
													</logic:equal>
												</td>
												<td width="8%">
													<a class="modifysmsuser" href="javascript:void(0);"
														name="${tlr.bugtype}" id="${tlr.moblie}"
														solvetime="${tlr.solvetime }">编辑</a>
													<a
														href="smsconfig.do?method=deleteMoblie&id=${tlr.moblie}&bugtype=${tlr.bugtype}&solvetime=${tlr.solvetime }">删除</a>
												</td>
											</tr>
										</logic:iterate>
									</table>
								</logic:present>
							</div>
						</div>
					</div>
				</td>
			</tr>
		</table>
		<div id="ly"
			style="position: absolute; top: 0px; filter: alpha(opacity =                                                                                                                     60); background-color: #777; z-index: 2; left: 0px; display: none;">
		</div>
		<div id="Layer2" align="center"
			style="position: absolute; z-index: 3; width: 300; left: expression((                                                         document .                                                         body .                                                         offsetWidth-800)/ 2 ); top: 80px; background-color: #fff; display: none;">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td id="modifysmsuserdiv">
						<table cellspacing="0" cellpadding="0" style="width: 800px">
							<tr>
								<th colspan="2">
									短信用户注册
								</th>
							</tr>
							<tr>
								<td valign="top">
									<table id="mytable" cellspacing="0">
										<html:form action="smsconfig.do?method=set" method="post">
											<tr align="center">
												<td align="right">
													通知类型：
												</td>
												<td align="left">
													<html:select property="bugtype" style="width:100%"
														styleId="selecterror">
														<html:option value="0">请选择...</html:option>
														<html:option value="1">设备故障</html:option>
														<html:option value="2">业务故障</html:option>
													</html:select>
												</td>
											</tr>
											<tr align="center">
												<td align="right">
													通知级别：
												</td>
												<td align="left">
													<html:select property="solvetime" style="width:100%">
														<html:option value="0">请选择...</html:option>
														<html:option value="1">一级</html:option>
														<html:option value="2">二级</html:option>
														<html:option value="3">三级</html:option>
													</html:select>
												</td>
											</tr>
											<tr align="center">
												<td align="right">
													负责设备：
												</td>
												<td>
													<html:select property="atmids" style="width:170px"
														multiple="true" size="15" styleId="select1">
													</html:select>
												</td>
												<td>
													<input type="button" value="》》" onclick="DoAdd();" />
													<br>
													<input type="button" value="《《" onclick="DoDel();" />
												</td>
											</tr>
											<tr align="center">
												<td align="right">
													手机号：
												</td>
												<td align="left">
													<input id="moblie" type="text" name="moblie" size="20"
														style="width: 100%"
														onkeyup="value=value.replace(/[^\d]/g,'') "
														onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
												</td>
											</tr>
											<tr>
												<td align="right">
													姓名：
												</td>
												<td align="left">
													<html:text property="name" size="20" style="width: 100%" />
												</td>
											</tr>
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
													<input type="button" value="关闭" onclick="Lock_CheckForm();">

												</td>
											</tr>
										</html:form>
									</table>
								</td>
								<td valign="top" id="devicelist">
									<table cellpadding="0" cellspacing="0">
										<tr>
											<td>
												设备列表
											</td>
										</tr>
										<tr>
											<td>
												<select style="width: 200px;" multiple size="15"
													id="select2">
													<option>
													</option>
												</select>
											</td>
										</tr>
									</table>

								</td>
							</tr>
						</table>

					</td>
				</tr>
			</table>
		</div>
		<input type="hidden" id="hidden" value="5" />
		</div>
			</div>
		</div>
	</body>
</html>

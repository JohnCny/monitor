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
		<base href="<%=basePath%>">
		<title>系统管理-信息管理</title>
		<link href="resources/source/tablecloth.css" rel="stylesheet"
			type="text/css" media="screen" />
		<script type="text/javascript" src="resources/scripts/jquery-1.6.js"></script>
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
#box_m{width:1120px;margin:0 auto;}
#box_nav{float:left; width:90px;}
#box_nav_s{float:left; widows:1000px;}
</style>
		<script type="text/javascript">
		$(document).ready(function(){
			$("#setcompany").click(function(){
			    $("#ly_1")[0].style.display="block";
				$("#ly_1")[0].style.width=document.body.clientWidth;
				$("#ly_1")[0].style.height=document.body.clientHeight;
				$("#Layer2_1")[0].style.display='block';
			      
			});
			$("#closecompany").click(function(){
			   	$("#ly_1")[0].style.display='none';
				$("#Layer2_1")[0].style.display='none';
				location.replace("infoconfig.do?method=allInfo");    
				return false;
			});
			
			
			$("#settype").click(function(){
			    $("#ly_2")[0].style.display="block";
				$("#ly_2")[0].style.width=document.body.clientWidth;
				$("#ly_2")[0].style.height=document.body.clientHeight;
				$("#Layer2_2")[0].style.display='block';
			      
			});
			$("#closetype").click(function(){
			   	$("#ly_2")[0].style.display='none';
				$("#Layer2_2")[0].style.display='none';
				location.replace("infoconfig.do?method=allInfo");    
				return false;
			});
			
			$("#setrun").click(function(){
			    $("#ly_3")[0].style.display="block";
				$("#ly_3")[0].style.width=document.body.clientWidth;
				$("#ly_3")[0].style.height=document.body.clientHeight;
				$("#Layer2_3")[0].style.display='block';
			      
			});
			$("#closerun").click(function(){
			   	$("#ly_3")[0].style.display='none';
				$("#Layer2_3")[0].style.display='none';
				location.replace("infoconfig.do?method=allInfo");    
				return false;
			});
			
			/////////////////////////////////////////////
			$(".modifycompany").click(function(){
      		var value=$(this).attr("name")
            $.ajax({
                 url:'infoconfig.do?method=getCompanyById&id='+value, 
                 type: 'get',
                 cache:false,
                 timeout: 2000,
                 error: function(){
                    alert('错误');
                 },
                success: function(json){
                	
				var users = eval('(' + json + ')');
                
                 $.each(users,function(i, user){       
                  $("#modifymessage_1").html("<table cellspacing='0' cellpadding='0'>"+
					"<tr><th colspan='2'>编辑厂商</th></tr>"+
					"<form action='infoconfig.do?method=modifyCompany' method='post'>"+
					"<tr><td style='width:50%'>编号</td>	<td><input type='text' id='companyId' name='id' value='"+user.id+"' size='15' style='width:100%' readonly='readonly' /></td></tr>"+
					"<tr><td>厂商名称(中文)</td><td><input type='text' id='companyCh' name='chname' value='"+user.chname+"' size='15' style='width:100%' /></td></tr>"+
					"<tr><td>厂商名称(英文)</td><td><input type='text' id='companyEn' name='enname' value='"+user.enname+"' size='15' style='width:100%' /></td></tr>"+
					"<tr><td colspan='2'><input type='button' value='修改' onclick='addCompany();' /></td></tr>"+
					"</form>"+
					"</table>"); 
					});
                }
         });
			
			    $("#ly_1")[0].style.display="block";
				$("#ly_1")[0].style.width=document.body.clientWidth;
				$("#ly_1")[0].style.height=document.body.clientHeight;
				$("#Layer2_1")[0].style.display='block';
			      
			});
			
			
			$(".modifytype").click(function(){
      		var value=$(this).attr("name")
            $.ajax({
                 url:'infoconfig.do?method=getTypeById&id='+value, 
                 type: 'get',
                 cache:false,
                 timeout: 2000,
                 error: function(){
                    alert('错误');
                 },
                success: function(json){
                	
				var users = eval('(' + json + ')');
                
                 $.each(users,function(i, user){       
                  $("#modifymessage_2").html("<table cellspacing='0' cellpadding='0'>"+
					"<tr><th colspan='2'>编辑类型</th></tr>"+
					"<form action='infoconfig.do?method=modifyType' method='post'>"+
					"<tr><td style='width:50%'>编号</td>	<td><input type='text' id='typeId' name='id' value='"+user.id+"' size='15' style='width:100%' readonly='readonly' /></td></tr>"+
					"<tr><td>设备类型(中文)</td><td><input type='text' id='typeCh' name='chname' value='"+user.chname+"' size='15' style='width:100%' /></td></tr>"+
					"<tr><td>设备类型(英文)</td><td><input type='text' id='typeEn' name='enname' value='"+user.enname+"' size='15' style='width:100%' /></td></tr>"+
					"<tr><td colspan='2'><input type='button' value='修改' onclick='addType();' /></td></tr>"+
					"</form>"+
					"</table>"); 
					});
                }
         });
			
			    $("#ly_2")[0].style.display="block";
				$("#ly_2")[0].style.width=document.body.clientWidth;
				$("#ly_2")[0].style.height=document.body.clientHeight;
				$("#Layer2_2")[0].style.display='block';
			      
			});
			
			$(".modifyrun").click(function(){
      		var value=$(this).attr("name")
            $.ajax({
                 url:'infoconfig.do?method=getRunById&id='+value, 
                 type: 'get',
                 cache:false,
                 timeout: 2000,
                 error: function(){
                    alert('错误');
                 },
                success: function(json){
                	
				var users = eval('(' + json + ')');
                
                 $.each(users,function(i, user){       
                  $("#modifymessage_3").html("<table cellspacing='0' cellpadding='0'>"+
					"<tr><th colspan='2'>编辑运营</th></tr>"+
					"<form action='infoconfig.do?method=modifyRun' method='post'>"+
					"<tr><td style='width:50%'>编号</td>	<td><input type='text' id='RunId' name='id' value='"+user.id+"' size='15' style='width:100%' readonly='readonly' /></td></tr>"+
					"<tr><td>运营类型(中文)</td><td><input type='text' id='RunCh' name='chname' value='"+user.chname+"' size='15' style='width:100%' /></td></tr>"+
					"<tr><td>运营类型(英文)</td><td><input type='text' id='RunEn' name='enname' value='"+user.enname+"' size='15' style='width:100%' /></td></tr>"+
					"<tr><td colspan='2'><input type='button' value='修改' onclick='addRun();' /></td></tr>"+
					"</form>"+
					"</table>"); 
					});
                }
         });
			
			    $("#ly_3")[0].style.display="block";
				$("#ly_3")[0].style.width=document.body.clientWidth;
				$("#ly_3")[0].style.height=document.body.clientHeight;
				$("#Layer2_3")[0].style.display='block';
			      
			});
			
		});
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
				<td colspan="3">
					<!--<tiles:insert page="../../page/view/topmenu.jsp" flush="false" />-->
				</td>
			</tr>
			<tr>
				<td valign="top">
					<table cellspacing="0" cellpadding="0">
						<caption style="font-size: 2em;">
							设备厂商
						</caption>
						<tr>
							<td colspan="4">
								<div align="right">
									<a id="setcompany" href="javascript:void(0);"> 创建 </a>
								</div>
							</td>
						</tr>
						<tr>
							<th>
								编号
							</th>
							<th>
								厂商名称
							</th>
							<th>
								厂商简称
							</th>
							<th>
								操作
							</th>
						</tr>

						<logic:present name="atmcompanylist">
							<logic:iterate id="tlr" name="atmcompanylist">
								<tr>
									<td>
										<bean:write name="tlr" property="id" />
									</td>
									<td>
										<bean:write name="tlr" property="chname" />
									</td>
									<td>
										<bean:write name="tlr" property="enname" />
									</td>
									<td>
										<a class=modifycompany href="javascript:void(0);"
											name="${tlr.id}">编辑</a>
										<a href="infoconfig.do?method=deleteCompany&id=${tlr.id }">删除</a>
									</td>
								</tr>
							</logic:iterate>
						</logic:present>
					</table>

				</td>
				<td valign="top">
					<table cellspacing="0" cellpadding="0">
						<caption style="font-size: 2em;">
							设备类型
						</caption>
						<tr>
							<td colspan="4">
								<div align="right">
									<a id="settype" href="javascript:void(0);"> 创建 </a>
								</div>
							</td>
						</tr>
						<tr>
							<th>
								编号
							</th>
							<th>
								类型名称
							</th>
							<th>
								类型简称
							</th>
							<th>
								操作
							</th>
						</tr>
						<logic:present name="atmtypelist">
							<logic:iterate id="tlr" name="atmtypelist">
								<tr>
									<td>
										<bean:write name="tlr" property="id" />
									</td>
									<td>
										<bean:write name="tlr" property="chname" />
									</td>
									<td>
										<bean:write name="tlr" property="enname" />
									</td>
									<td>
										<a class="modifytype" href="javascript:void(0);"
											name="${tlr.id}">编辑</a>
										<a href="infoconfig.do?method=deleteType&id=${tlr.id }">删除</a>
									</td>
								</tr>
							</logic:iterate>
						</logic:present>
					</table>

				</td>
				<td valign="top">
					<table cellspacing="0" cellpadding="0">
						<caption style="font-size: 2em;">
							所属机构
						</caption>
						<tr>
							<td colspan="4">
								<div align="right">
									<a id="setrun" href="javascript:void(0);"> 创建 </a>
								</div>
							</td>
						</tr>
						<tr>
							<th>
								编号
							</th>
							<th>
								机构名称
							</th>
							<th>
								机构简称
							</th>
							<th>
								操作
							</th>
						</tr>

						<logic:present name="runtypelist">
							<logic:iterate id="tlr" name="runtypelist">
								<tr>
									<td>
										<bean:write name="tlr" property="id" />
									</td>
									<td>
										<bean:write name="tlr" property="chname" />
									</td>
									<td>
										<bean:write name="tlr" property="enname" />
									</td>
									<td>
										<a class="modifyrun" href="javascript:void(0);"
											name="${tlr.id}">编辑</a>
										<a href="infoconfig.do?method=deleteRun&id=${tlr.id }">删除</a>
									</td>
								</tr>
							</logic:iterate>
						</logic:present>
					</table>
				</td>
			</tr>
			<logic:present name="setcompany_err">
				<tr>
					<td colspan="3">
						<div align="center" style="font-size: 2em;">
							编号：
							<bean:write name="setcompany_err" property="id" />
							已经存在!
						</div>
					</td>
				</tr>
			</logic:present>
		</table>
		<input type="hidden" id="hidden" value="6" />

		<!-- 隐藏div -->
		<div id="ly_1"
			style="position: absolute; top: 0px; filter: alpha(opacity =                                                                                                 60); background-color: #777; z-index: 2; left: 0px; display: none;">
		</div>
		<div id="Layer2_1" align="center"
			style="position: absolute; z-index: 3; width: 300; left: expression((                                     document .                                     body .                                     offsetWidth-300)/ 2 ); top: 80px; background-color: #fff; display: none;">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td id="modifymessage_1">
						<table cellspacing="0" cellpadding="0">
							<tr>
								<th colspan="2">
									新增厂商
								</th>
							</tr>
							<html:form action="infoconfig.do?method=setCompany" method="post">
								<tr>
									<td style="width: 50%">
										编号
									</td>
									<td>
										<html:text property="id" size="15" styleId="companyId"
											style="width:100%" />
									</td>
								</tr>
								<tr>
									<td>
										厂商名称
									</td>
									<td>
										<html:text property="chname" size="15" styleId="companyCh"
											style="width:100%" />
									</td>
								</tr>
								<tr>
									<td>
										厂商简称
									</td>
									<td>
										<html:text property="enname" size="15" styleId="companyEn"
											style="width:100%" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<html:button property="" value="创建" onclick="addCompany();" />
										<input type="button" value="关闭" id="closecompany" />
									</td>
								</tr>
							</html:form>
						</table>
					</td>
				</tr>
			</table>
		</div>


		<div id="ly_2"
			style="position: absolute; top: 0px; filter: alpha(opacity =                                                                                                 60); background-color: #777; z-index: 2; left: 0px; display: none;">
		</div>
		<div id="Layer2_2" align="center"
			style="position: absolute; z-index: 3; width: 300; left: expression((                                     document .                                     body .                                     offsetWidth-300)/ 2 ); top: 80px; background-color: #fff; display: none;">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td id="modifymessage_2">
						<table cellspacing="0" cellpadding="0">
							<tr>
								<th colspan="2">
									新增类型
								</th>
							</tr>
							<html:form action="infoconfig.do?method=setType" method="post">
								<tr>
									<td style="width: 50%">
										编号
									</td>
									<td>
										<html:text property="id" size="15" styleId="typeId"
											style="width:100%" />
									</td>
								</tr>
								<tr>
									<td>
										类型名称
									</td>
									<td>
										<html:text property="chname" size="15" styleId="typeCh"
											style="width:100%" />
									</td>
								</tr>
								<tr>
									<td>
										类型简称
									</td>
									<td>
										<html:text property="enname" size="15" styleId="typeEn"
											style="width:100%" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<html:button property="" value="创建" onclick="addType();" />
										<input type="button" value="关闭" id="closetype" />
									</td>
								</tr>
							</html:form>
						</table>
					</td>
				</tr>
			</table>
		</div>

		<div id="ly_3"
			style="position: absolute; top: 0px; filter: alpha(opacity =                                                                                                 60); background-color: #777; z-index: 2; left: 0px; display: none;">
		</div>
		<div id="Layer2_3" align="center"
			style="position: absolute; z-index: 3; width: 300; left: expression((                                     document .                                     body .                                     offsetWidth-300)/ 2 ); top: 80px; background-color: #fff; display: none;">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td id="modifymessage_3">
						<table cellspacing="0" cellpadding="0">
							<tr>
								<th colspan="2">
									新增机构
								</th>
							</tr>
							<html:form action="infoconfig.do?method=setRun" method="post">
								<tr>
									<td style="width: 50%">
										编号
									</td>
									<td>
										<html:text property="id" size="15" styleId="RunId"
											style="width:100%" />
									</td>
								</tr>
								<tr>
									<td>
										机构名称
									</td>
									<td>
										<html:text property="chname" size="15" styleId="RunCh"
											style="width:100%" />
									</td>
								</tr>
								<tr>
									<td>
										机构简称
									</td>
									<td>
										<html:text property="enname" size="15" styleId="RunEn"
											style="width:100%" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<html:button property="" value="创建" onclick="addRun();" />
										<input type="button" value="关闭" id="closerun" />
									</td>
								</tr>
							</html:form>
						</table>
					</td>
				</tr>
			</table>
		</div>
		</div>
			</div>
		</div>
	</body>
</html>
<script type="text/javascript">
  function addCompany(){
  
    if(document.getElementById("companyId").value==""){
      alert("请输入编号！");
      return false;
    }else if(document.getElementById("companyCh").value==""){
      alert("请输入厂商名称!");
      return false;
    }
    document.forms[0].submit();
   }
   
   function addType(){
    if(document.getElementById("typeId").value==""){
      alert("请输入编号！");
      return false;
    }else if(document.getElementById("typeCh").value==""){
      alert("请输入类型名称!");
      return false;
    }
    document.forms[1].submit();
   }
   
   function addRun(){
    if(document.getElementById("RunId").value==""){
      alert("请输入编号！");
      return false;
    }else if(document.getElementById("RunCh").value==""){
      alert("请输入机构名称!");
      return false;
    }
    document.forms[2].submit();
   }
</script>

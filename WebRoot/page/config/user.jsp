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
		<title>系统管理-用户管理</title>
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
			
			
			/////////////////////////////////////////////
			$(".modifyuser,#setuser").click(function(){
      		var value=$(this).attr("name");
            $.ajax({
                 url:'user.do?method=getByUserName&id='+value, 
                 type: 'get',
                 cache:false,
                 timeout: 2000,
                 error: function(){
                    alert('错误');
                 },
                success: function(json){
                	
				var users = eval('(' + json + ')');
                var l=0,s=0;
                var t="编辑用户",uf="user.do?method=modify";
                if(value=='set'){
                	t="创建用户";
                	uf="user.do?method=set";
                }
                var modifyuserdiv="<table cellspacing='0' cellpadding='0'><tr><th colspan='2'>"+t+"</th></tr><form action='"+uf+"' method='post'>";
                 $.each(users,function(i, user){                     
					if(i==0){
					modifyuserdiv+="<tr><td align='right'>用户名：</td><td align='left'><input style='width: 100%' id='username' type='text' name='username' size='15' value='"+user+"' ";
					if(value!='set')
						modifyuserdiv+="readonly";
					modifyuserdiv+=" /></td></tr>";
					}if(i==1)
					modifyuserdiv+="<tr><td align='right'>身份：</td><td align='left'><select style='width: 100%' name='title' ><option "+(user==1?'selected':'')+" value='1'>系统管理员</option><option "+(user==2?'selected':'')+" value='2'>部门经理</option><option "+(user==3?'selected':'')+" value='3'>部门办事员</option><option "+(user==4?'selected':'')+" value='4'>维护工程师</option><option "+(user==5?'selected':'')+" value='5'>网点管理员</option></select></td></tr>";
					if(i==2){
					modifyuserdiv+="<tr><td align='right'>密码：</td><td align='left'><input style='width: 100%' id='password1' type='password' name='password' size='15' value='"+user+"'/></td></tr>";
					modifyuserdiv+="<tr><td align='right'>确认密码：</td><td align='left'><input style='width: 100%' id='password2' type='password' name='password' size='15' value='"+user+"'/></td></tr>";
					}//<input style='width: 100%' type='text' name='name' size='15' value='"+user.name+"'/>
					if(i==3)
						s=user;
					if(i==4){
						l=user;
						modifyuserdiv+="<tr><td align='right'>归属：</td><td align='left'><select style='width: 100%' name='name'>";
					}
					
					if(i>4&&i<=Number(l)+4){
						var u= new Array(); 
						u=user.split(",");						
						if(u[0]==s)
						 	modifyuserdiv+="<option value='"+u[0]+"' selected>"+u[1]+"</option>";
						else
							modifyuserdiv+="<option value='"+u[0]+"'>"+u[1]+"</option>";
					}
					if(i==Number(l)+5)
					modifyuserdiv+="</select></td></tr><tr><td align='right'>电话：</td><td><input style='width: 100%' type='text' name='phone' size='15' value='"+user+"' onkeyup='value=value.replace(/[\W]/g,'') ' onbeforepaste='clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))' /></td></tr>";
					if(i==Number(l)+6)					
					modifyuserdiv+="<tr><td align='right'>Email：</td><td><input style='width: 100%' type='text' name='email' value='"+user+"' size='15' /></td></tr>";
					//"<tr><td align='right'>监控权限：</td><td><input type='checkbox' name='emlevel1'  "+(user.emlevel1==1?'checked':'')+"/>在行自有<br><input type='checkbox' name='emlevel2' "+(user.emlevel2==1?'checked':'')+" />离行自有<br><input type='checkbox' name='emlevel3' "+(user.emlevel3==1?'checked':'')+" />离行运营<br></td></tr>"+
					if(i==Number(l)+7)
					modifyuserdiv+="<tr><td align='right'>管理权限：</td><td><input type='checkbox' name='salevel' "+(user==1?'checked':'')+" />统计分析<br>";
					if(i==Number(l)+8)
					modifyuserdiv+="<input type='checkbox' name='rmlevel' "+(user==1?'checked':'')+" />远程管理<br>";					
					if(i==Number(l)+9)
					modifyuserdiv+="<input type='checkbox' name='ctlevel' "+(user==1?'checked':'')+" />对账管理<br>";
					if(i==Number(l)+10)
					modifyuserdiv+="<input type='checkbox' name='umlevel' "+(user==1?'checked':'')+" />短信管理<br>";
					if(i==Number(l)+11)
					modifyuserdiv+="<input type='checkbox' name='sclevel' "+(user==1?'checked':'')+" />系统管理<br></td></tr>"
					if(i==Number(l)+12)
					modifyuserdiv+="<tr><td align='right'>状态：</td><td><select style='width: 100%' name='status'><option "+(user==0?'selected':'')+"  value='0'>注销</option><option "+(user==1?'selected':'')+" value='1'>启用</option></select></td></tr>";
                 });
					modifyuserdiv+="<tr><td colspan='2' align='right'><input type='button' value='确定' onclick='modifyuser();' /><input type='button' value='关闭' onclick='Lock_CheckForm();'/></td></tr></form></table>"
					$("#modifyuser").html(modifyuserdiv);
					
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
location.replace("user.do?method=list");    
return false;
}
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
				<td>
					<div id="doc">
						<div class="fixTh" style="text-align: left;">
							<logic:present name="setuser_err">
								<div align="center" style="font-size: 2em;">
									用户：
									<bean:write name="setuser_err" property="username" />
									已经存在!
								</div>
							</logic:present>
							<div align="right">
								<a id="setuser" href="javascript:void(0);" name="set">创建用户</a>|
								<a href="user.do?method=list">刷新</a>
							</div>
							<table cellspacing="0" cellpadding="0" style="width: 98.5%">
								<tr>
									<th width="7%">
										归属
									</th>
									<th width="7%">
										身份
									</th>
									<th width="7%">
										用户名
									</th>
									<th width="7%">
										电话
									</th>
									<th width="7%">
										Email
									</th>
									<th width="7%">
										统计分析
									</th>
									<th width="7%">
										远程管理
									</th>
									<th width="7%">
										对账管理
									</th>
									<th width="7%">
										短信管理
									</th>
									<th width="7%">
										系统管理
									</th>
									<th width="16%">
										创建时间
									</th>
									<th width="7%">
										状态
									</th>
									<th width="7%">
										操作
									</th>
								</tr>
							</table>

							<div class="tc" style="height: 450px">
								<logic:present name="userlist">
									<table>
										<logic:iterate id="tlr" name="userlist">
											<tr align="center">
												<td width="7%">
													<bean:write name="tlr" property="name" />
												</td>
												<td width="7%">
													<logic:equal name="tlr" property="title" value="1">
								系统管理员
							</logic:equal>
													<logic:equal name="tlr" property="title" value="2">
								部门经理
							</logic:equal>
													<logic:equal name="tlr" property="title" value="3">
								部门办事员
							</logic:equal>
													<logic:equal name="tlr" property="title" value="4">
								维护工程师
							</logic:equal>
													<logic:equal name="tlr" property="title" value="5">
								网点管理员
							</logic:equal>
												</td>
												<td width="7%">
													<bean:write name="tlr" property="username" />
												</td>
												<td width="7%">
													<bean:write name="tlr" property="phone" />
												</td>
												<td width="7%">
													<bean:write name="tlr" property="email" />
												</td>



												<td width="7%">
													<logic:equal name="tlr" property="salevel" value="1">
														<font color="#00DB00">√</font>
													</logic:equal>
													<logic:equal name="tlr" property="salevel" value="0">
														<font color="#FF0000">×</font>
													</logic:equal>
												</td>
												<td width="7%">
													<logic:equal name="tlr" property="rmlevel" value="1">
														<font color="#00DB00">√</font>
													</logic:equal>
													<logic:equal name="tlr" property="rmlevel" value="0">
														<font color="#FF0000">×</font>
													</logic:equal>
												</td>
												<td width="7%">
													<logic:equal name="tlr" property="ctlevel" value="1">
														<font color="#00DB00">√</font>
													</logic:equal>
													<logic:equal name="tlr" property="ctlevel" value="0">
														<font color="#FF0000">×</font>
													</logic:equal>
												</td>

												<td width="7%">
													<logic:equal name="tlr" property="umlevel" value="1">
														<font color="#00DB00">√</font>
													</logic:equal>
													<logic:equal name="tlr" property="umlevel" value="0">
														<font color="#FF0000">×</font>
													</logic:equal>
												</td>
												<td width="7%">
													<logic:equal name="tlr" property="sclevel" value="1">
														<font color="#00DB00">√</font>
													</logic:equal>
													<logic:equal name="tlr" property="sclevel" value="0">
														<font color="#FF0000">×</font>
													</logic:equal>
												</td>
												<td width="16%">
													<bean:write name="tlr" property="createtime" />
												</td>
												<td width="7%">
													<logic:equal name="tlr" property="status" value="1">
														<font color="#00DB00">启用</font>
													</logic:equal>
													<logic:equal name="tlr" property="status" value="0">
														<font color="#FF0000">注销</font>
													</logic:equal>
												</td>
												<td width="7%">
													<a class="modifyuser" href="javascript:void(0);"
														name="${tlr.username}">编辑</a>
													<a href="user.do?method=deleteUser&id=${tlr.username}"
														name="${tlr.username}">删除</a>
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
		<input type="hidden" id="hidden" value="6" />


		<div id="ly"
			style="position: absolute; top: 0px; filter: alpha(opacity =                                                                                 60); background-color: #777; z-index: 2; left: 0px; display: none;">
		</div>
		<div id="Layer2" align="center"
			style="position: absolute; z-index: 3; width: 300; left: expression((                     document .                     body .                     offsetWidth-300)/ 2 ); top: 80px; background-color: #fff; display: none;">
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td id="modifyuser">
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
  function adduser(){
    if(document.getElementById("username").value==""){
      alert("请填写用户名！");
      return false;
    }else if(document.getElementById("password").value==""){
      alert("请填写密码!");
      return false;
    }else if(document.getElementById("password2").value!=document.getElementById("password1").value){
      alert("两次密码输入不一致!");
      return false;
    }
    document.forms[0].submit();
   }
   function modifyuser(){
   if(document.getElementById("password").value==""){
      alert("请填写密码!");
      return false;
    }else if(document.getElementById("password2").value!=document.getElementById("password1").value){
      alert("两次密码输入不一致!");
      return false;
    }
    document.forms[0].submit();
   }
</script>

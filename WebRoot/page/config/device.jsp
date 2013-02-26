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
		<title>系统管理-管理管理</title>
		<link href="resources/source/tablecloth.css" rel="stylesheet"
			type="text/css" media="screen" />
		<script type="text/javascript" src="resources/scripts/jquery-1.6.js"></script>

		<script type="text/javascript">
		function submit1(atmid){
		document.forms[0].action="atmstatus.do?method=deleteAtm&id="+atmid;
		document.forms[0].submit();
		}
		
		$(document).ready(function(){
	      $(".saveatm").click(function(){
				var value=$(this).attr("name")
				
				var obj1 = $("#"+value+'company')[0]; 
				var obj2 = $("#"+value+'type')[0]; 
				var obj3 = $("#"+value+'route')[0]; 
				
				var select1=obj1.options[obj1.selectedIndex].value;
				var select2=obj2.options[obj2.selectedIndex].value;
				var select3=obj3.options[obj3.selectedIndex].value;
				if(select1==0){
						alert('请选择生产厂商');
				      return false;
				}if(select3==0)
				{
				alert('请选择机器类型');
				      return false;
				}
	      
	            $.ajax({
	                 url:'atmstatus.do?method=setAddress', 
	                 type: 'post',
	                 cache:false,
	                 data: $('#'+value+'form').serialize(),
	                 timeout: 2000,
	                 error: function(){
	                    alert('错误');
	                 },
	                success: function(json){
	                	alert("保存成功!");
	                }
	         });
	     });         
	    });
		</script>
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
		<html:form action="atmstatus.do?method=deleteAtm" method="post"></html:form>
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
							<table cellspacing="0" cellpadding="0" style="width: 98.5%">
								<tr align="center">
									<th width="7%">
										ATM号
									</th>
									<th width="9%">
										虚拟柜员号
									</th>
									<th width="7%">
										生产商
									</th>
									<th width="6%">
										IP
									</th>
									<th width="5%">
										端口
									</th>
									<!-- <th width="7%">
					编码
				</th> -->
									<th width="5%">
										超时
									</th>
									
									<th width="6%">
										流水
									</th>
									<th width="6%">
										日志
									</th>
									<th width="6%">
										驻留
									</th>
									<th width="6%">
										图片
									</th>
									<th width="6%">
										所在地
									</th>
									<th width="7%">
										归属机构
									</th>
									<th width="7%">
										机器类型
									</th>
									<th width="7%">
										开通日期
									</th>
									<th width="8%">
										操作
									</th>
								</tr>
							</table>

							<div class="tc" style="height: 470px">
								<logic:present name="atmaddress">
									<table>
										<logic:iterate id="tlr" name="atmaddress">
											<html:form action="atmstatus.do?method=setAddress"
												method="post" styleId="${tlr.atmid}form">
												<tr>
													<td width="7%">
														<html:text property="atmid" value="${tlr.atmid}"
															readonly="true" style="width:70px" />
													</td>
													<td width="8%">
														<html:text property="atmid_new" value="${tlr.atmid_new}"
															style="width:90px" />
													</td>
													<td width="7%">
														<html:select name="atmcompany" property="company"
															value="${tlr.company}" style="width:70px"
															styleId="${tlr.atmid}company">
															<html:optionsCollection name="atmcompany" value="id"
																label="chname" />
														</html:select>
													</td>
													<td width="5%">
														<html:text property="ip" value="${tlr.ip}"
															style="width:50px" />
													</td>
													<td width="5%">
														<html:text property="port" value="${tlr.port}"
															style="width:50px" />
													</td>
													<!-- <td width="7%">
								<html:text property="encoding" value="${tlr.encoding}"  />
							</td> -->
													<td width="5%">
														<html:text property="timeout" value="${tlr.timeout}"
															style="width:50px" />
													</td>
													
													<td width="6%">
														<html:text property="ejr" value="${tlr.ejr}"
															style="width:60px" />
													</td>
													<td width="6%">
														<html:text property="tracepath" value="${tlr.tracepath}"
															style="width:60px" />
													</td>

													<td width="6%">
														<html:text property="aesyspath" value="${tlr.aesyspath}"
															style="width:60px" />
													</td>
													<td width="6%">
														<html:text property="picture" value="${tlr.picture}"
															style="width:60px" />
													</td>
													<td width="6%">
														<html:text property="addr" value="${tlr.addr}"
															style="width:60px" />
													</td>
													<td width="7%">
														<html:select name="runtype" property="type"
															value="${tlr.type}" style="width:70px"
															styleId="${tlr.atmid}type">
															<html:optionsCollection name="runtype" value="id"
																label="chname" />
														</html:select>
													</td>
													<td width="7%">
														<html:select name="atmtype" property="route"
															value="${tlr.route}" style="width:70px"
															styleId="${tlr.atmid}route">
															<html:optionsCollection name="atmtype" value="id"
																label="chname" />
														</html:select>
													</td>
													<td width="7%">
														<html:text property="encoding" value="${tlr.encoding}"
															style="width:70px" />
													</td>
													<td width="10%">
														<input type="button" name="${tlr.atmid}" class="saveatm"
															value="保存">
														<html:button property="" value="删除"
															onclick="submit1(${tlr.atmid})" />
													</td>
												</tr>
											</html:form>
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
		</div>
			</div>
		</div>
	</body>
</html>



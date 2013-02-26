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

		<title>统计分析-运行信息</title>
		<link href="resources/source/tablecloth.css" rel="stylesheet"
			type="text/css" media="screen" />
		<script type="text/javascript" src="resources/scripts/calendar.js"></script>
		<script type="text/javascript" src="resources/scripts/jquery-1.6.js"></script>

		<script type="text/javascript">
		$(document).ready(function() {
    $("#analyse").click(function() {
        var obj = $("#select1")[0];
        var select = obj.options[obj.selectedIndex].value;
        if (select == 1) {             
            if ($('#begin')[0].value == "") {
                alert("请选择起始日期！");
                return false
            } else if ($('#end')[0].value == "") {
                alert("请选择结束日期!");
                return false
            }
        }
        $.ajax({
            url: 'statiform2.do',
            type: 'post',
            cache: false,
            data: $('#form1').serialize(),
            timeout: 2000,
            error: function() {
                alert('错误')
            },
            success: function(json) {
                var data_backs = eval('(' + json + ')');
                var div_out = "<div id='doc'><div class='fixTh' style='text-align: left;'>";
                var div_title = "<table cellspacing='0' cellpadding='0' style='width: 98%'><tr>";
                if (select == 1) div_title += "<th width='13%'>设备编号</th><th width='11%'>故障模组</th><th width='10%'>故障类型</th><th width='13%'>开始时间</th><th width='13%'>结束时间</th><th width='10%'>耗时(分钟)</th><th width='30%'>型号地址</th>";
                else if (select == 2) div_title += "<th width='50%'>设备厂商</th><th width='50%'>设备数量(台)</th>";
                div_title += "</tr></table>";
                var div_top = "<div class='tc' style='height: 470px;'>";
                var table_top = "<table cellspacing='0' cellpadding='0'>";
                var table_body = "";
                $.each(data_backs,
                function(i, data_back) {
                    if (select == 1) table_body += "<tr><td width='13%'>" + data_back.atmId + "</td><td width='11%'>" + data_back.bugId + "</td><td width='13%'>" + data_back.bugType + "</td><td width='13%'>" + data_back.bugBeg + "</td><td width='13%'>" + data_back.bugEnd + "</td><td width='10%'>" + data_back.subTime + "</td><td width='30%'>" + data_back.constant + "</td></tr>";
                    else if (select == 2) table_body += "<tr><td width='50%'>" + data_back.atmid + "</td><td width='50%'>" + data_back.allstatus + "</td></tr>"
                });
                var table_bottom = "</table>";
                var div_bottom = "</div></div></div>";              
                $("#id_run").html(div_out + div_title + div_top + table_top + table_body + table_bottom + div_bottom)
            }
        })
    });
    $("#id_run").ajaxStart(function() {
        $(this).html("<img src='resources/source/8.gif'>&nbsp;正在加载")
    });
    $("#id_run").ajaxSuccess(function() {});
    $("#id_run").ajaxStop(function() {})
});
		</script>

		<script type="text/javascript">
<!--
function submit1(type)
{
var rs=document.getElementById("rs").value;

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
if(strsel==999999)
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
var errtype=document.getElementById('errtype');
var errtime=document.getElementById('errtime');
if(strsel=='1')
{
trtd.style.display="block";
trte.style.display="block";
errtype.style.display="block";
errtime.style.display="block";
}
else if(strsel=='2'){
trtd.style.display="none";
trte.style.display="none";
errtype.style.display="none";
errtime.style.display="none";
}

}

function submit1()
{
var rs=document.getElementById("select1").value;
	if(rs==1){
	 if(document.getElementById('begin').value==""){
      	alert("请选择起始日期！");
      	return false;
    }else if(document.getElementById('end').value==""){
      	alert("请选择结束日期!");
      	return false;
    }
    }
	

    document.forms[0].action="reportxls2.do";
    document.forms[0].submit();
}
//-->
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
#box_m{width:1200px;margin:0 auto;}
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
		<input type="hidden" id="hidden" value="2" />
		<table cellspacing="0" cellpadding="0" style="width: 1000px;">
			<tr>
				<td colspan="2">
					
				</td>
			</tr>
			<tr>
				<td valign="top" style="width: 20%;">
					<html:form action="statiform.do" method="post" styleId="form1">
						<table cellspacing="0" cellpadding="0">
							<tr>
								<th colspan="2">
									统计参数
								</th>
							</tr>

							<tr>
								<td width="50%">
									报表类型：
								</td>

								<td>
									<html:select property="reportType" style="width:100%"
										styleId="select1" onchange="changetype(this);">
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

								<td id="ss">
									<html:select property="equipType" style="width:100%"
										onclick="change(this);" styleId="es">
										<logic:present name="typelist">
											<logic:iterate id="tlr" name="typelist">
												<html:option value="${tlr.atmid}">
																${tlr.type }
															</html:option>
											</logic:iterate>
										</logic:present>
										<html:option value="999999">自选设备</html:option>
									</html:select>
								</td>
							</tr>
							<tr id="tr1" style="display: none;">
								<td id="bs" colspan="2">
									<html:select property="atmids" style="width:100%;"
										multiple="true" size="10" styleId="selectId">
										<logic:notEmpty name="atmid">
											<html:optionsCollection name="atmid" value="atmid"
												label="atmid" />
										</logic:notEmpty>
									</html:select>
								</td>
							</tr>
							<tr id="errtype">
								<td>
									故障类型：
								</td>
								<td id="ss">
									<html:select property="errtype" style="width:100%" styleId="er">
										<html:option value="0">全部故障</html:option>
										<html:option value="1">设备故障</html:option>
										<html:option value="2">业务故障</html:option>
									</html:select>
								</td>
							</tr>
							<tr id="errtime">
								<td>
									故障时间：
								</td>
								<td>
									<html:select property="errtime" style="width:100%;" value="3">
										<html:option value="0">0小时</html:option>
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
										<html:option value="13">13小时</html:option>
										<html:option value="14">14小时</html:option>
										<html:option value="15">15小时</html:option>
										<html:option value="16">16小时</html:option>
										<html:option value="17">17小时</html:option>
										<html:option value="18">18小时</html:option>
										<html:option value="19">19小时</html:option>
										<html:option value="20">20小时</html:option>
										<html:option value="21">21小时</html:option>
										<html:option value="22">22小时</html:option>
										<html:option value="23">23小时</html:option>
										<html:option value="24">24小时</html:option>
										<html:option value="25">24小时以上</html:option>

									</html:select>
								</td>
							</tr>
							<tr id="trtb">
								<td>
									起始日期：
								</td>

								<td>
									<html:text styleId="begin" property="timebegin"
										onfocus="calendar()" size="12" style="width:100%;" />
								</td>
							</tr>
							<tr id="trte">
								<td>
									结束日期：
								</td>

								<td>
									<html:text styleId="end" property="timeend" size="12"
										style="width:100%;" onfocus="calendar()" />
								</td>
							</tr>

							<tr>
								<td>
									<html:button property="" styleId="analyse" value="分析" />
								</td>
								<td>
									<html:button property="" onclick="submit1();" value="导出" />
								</td>
							</tr>
						</table>
					</html:form>
				</td>
				<td valign="top">
					<div id="id_run">

					</div>
				</td>
			</tr>
		</table>
		</div>
			</div>
		</div>
	</body>
</html>

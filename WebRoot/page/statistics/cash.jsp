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

		<title>统计分析-现金分析</title>
		<link href="resources/source/tablecloth.css" rel="stylesheet"
			type="text/css" media="screen" />
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
		<script type="text/javascript" src="resources/scripts/calendar.js"></script>
		<script type="text/javascript" src="resources/scripts/jquery-1.6.js"></script>

		<script type="text/javascript">
		$(document).ready(function() {
    $("#stock").click(function() {
        var obj = $("#select1")[0];
        var select = obj.options[obj.selectedIndex].value;
        if (select == 'historymoney' || select == 'addmoney') {
            if ($("#begin")[0].value == "") {
                alert("请选择起始日期！");
                return false;
            } else if ($("#end").value == "") {
                alert("请选择结束日期!");
                return false;
            }
        }
        if (select == 'onedaycheck') {
            if ($("#begin")[0].value == "") {
                alert("请选择日期！");
                return false;
            }
        }

        $.ajax({
            url: 'cash.do?method=' + select,
            type: 'post',
            cache: false,
            data: $('#form1').serialize(),
            timeout: 2000,
            error: function() {
                alert('错误');
            },
            success: function(json) {

                var data_backs = eval('(' + json + ')');
				if(select=='stock'){
	                var div_out = "<div id='doc'><div class='fixTh' style='text-align: left;'>";
	                var div_title = "<table cellspacing='0' cellpadding='0' style='width: 98.5%'><tr><th width='30%'>示意图</th>" + "<th width='15%'>设备编号</th>" + "<th width='15%'>取款余额(百元)</th>" + "<th width='15%'>存款金额(百元)</th>" + "<th width='25%'>设备所在场所</th></tr>" + "</table>";
	                var div_top = "<div class='tc' style='height: 470px;'>";
	                var table_top = "<table cellspacing='0' cellpadding='0'>";
	                var table_body = "";
	
	                //返回的是List
	                $.each(data_backs,
	                function(i, data_back) {
	                if(i==0)
	                	table_body+="<td width='30%' valign='top' rowspan='"+data_backs.length+"'><img src='page/view/getchart.jsp?"+data_back.atmid+"' usemap='#map1' border='0'></td>";
	                else
	                	table_body += "<tr><td width='15%'>" + data_back.atmid + "</td>" + "<td width='15%'>" + data_back.partrmb + "</td>" + "<td width='15%'>" + data_back.depormb + "</td>" + "<td width='25%'>" + data_back.addr + "</td></tr>";
	                });
	                var table_bottom = "</table>";
	                var div_bottom = "</div></div></div>";
	                $("#id_stock").html(div_out + div_title + div_top + table_top + table_body + table_bottom + div_bottom);
                }else if(select=='onedaycheck'){
                	var div_out = "<div id='doc'><div class='fixTh' style='text-align: left;'>";
	                var div_title = "<table cellspacing='0' cellpadding='0' style='width: 98.5%'><tr><th width='30%'>示意图</th>" + "<th width='10%'>设备编号</th>" + "<th width='15%'>起始天余额</th>" + "<th width='10%'>当前余额</th>" + "<th width='10%'>差额</th><th width='25%'>所在地</th>" + "</table>";
	                var div_top = "<div class='tc' style='height: 450px;'>";
	                var table_top = "<table cellspacing='0' cellpadding='0'>";
	                var table_body = "";
	
	                //返回的是List
	                $.each(data_backs,
	                function(i, data_back) {
	                if(i==0)
	                	table_body+="<td width='30%' valign='top' rowspan='"+data_backs.length+"'><img src='page/view/getchart.jsp?"+data_back.atmid+"' usemap='#map1' border='0'></td>";
	                else
	                	table_body += "<tr><td width='10%'>" + data_back.atmid + "</td>" + "<td width='15%'>" + data_back.depormb + "</td>" + "<td width='10%'>" + data_back.partrmb + "</td>" + "<td width='10%'>" + data_back.allstatus + "</td><td width='25%'>" + data_back.addr + "</td></tr>";
	                });
	                var table_bottom = "</table>";
	                var div_bottom = "</div></div></div>";
	                $("#id_stock").html(div_out + div_title + div_top + table_top + table_body + table_bottom + div_bottom);
                }else if(select=='historymoney'){
                }else if(select=='addmoney'){
                	    $("#id_stock").hide;
                	    $("#id_onedaycheck").hide;
          
                	var div_out = "<div id='doc'><div class='fixTh' style='text-align: left;'>";
	                var div_title = "<table cellspacing='0' cellpadding='0' style='width: 98.5%'><tr><th width='30%'>示意图</th><th width='20%'>设备编号</th><th width='20%'>建议加钞</th><th width='30%'>所在地</th></table>";
	                var div_top = "<div class='tc' style='height: 450px;'>";
	                var table_top = "<table cellspacing='0' cellpadding='0'>";
	                var table_body = "";
	
	                //返回的是List
	                $.each(data_backs,
	                function(i, data_back) {
	                if(i==0)
	                	table_body+="<td width='30%' valign='top' rowspan='"+data_backs.length+"'><img src='page/view/getchart.jsp?"+data_back.atmid+"' usemap='#map1' border='0'></td>";
	                else
	                	table_body += "<tr><td width='20%'>" + data_back.atmid + "</td>" + "<td width='20%'>" + data_back.depormb + "</td>" +"<td width='30%'>" + data_back.addr + "</td></tr>";
	                });
	                var table_bottom = "</table>";
	                var div_bottom = "</div></div></div>";
	                $("#id_stock").html(div_out + div_title + div_top + table_top + table_body + table_bottom + div_bottom);
                }
				
               
            }
        });

    });

    //ajax提交
    $("#id_showload").ajaxStart(function() {
        $(this).html("<img src='resources/source/8.gif'>&nbsp;正在加载");
    });

    //ajax成功状态
    $("#id_showload").ajaxSuccess(function() {
        //   $(this).html("");
    });

    //ajax结束状态
    $("#id_showload").ajaxStop(function() {
        $(this).hide();
    });

});
function report(){
document.forms[0].action="cashReport.do";
document.forms[0].submit();
}
		</script>

		<script type="text/javascript">
<!--
function change(obj){
var strsel = obj.options[obj.selectedIndex].value;
var tr=document.getElementById("tr1");
if(strsel==999999)
tr.style.display="block";
else
tr.style.display="none";
}

function changetype(obj){
var strsel = obj.options[obj.selectedIndex].value;
var trtd=document.getElementById("trtb");
var trte=document.getElementById("trte");
var bttd=document.getElementById("bttd");
var artb=document.getElementById("artb");
var qjetb=document.getElementById("qjetb");
var cjetb=document.getElementById("cjetb");
if(strsel=='stock')
{
qjetb.style.display="block";
cjetb.style.display="block";
trtd.style.display="none";
trte.style.display="none";
bttd.style.display="block";
artb.style.display="none";
}
else if(strsel=='historymoney'){
qjetb.style.display="none";
cjetb.style.display="none";
trtd.style.display="block";
trte.style.display="block";
bttd.style.display="none";
artb.style.display="none";
}
else if(strsel=='addmoney'){
qjetb.style.display="none";
cjetb.style.display="none";
trtd.style.display="block";
trte.style.display="block";
bttd.style.display="block";
artb.style.display="block";
}
else if(strsel=='onedaycheck'){
qjetb.style.display="none";
cjetb.style.display="none";
trtd.style.display="block";
trte.style.display="none";
bttd.style.display="none";
artb.style.display="none";
}
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
		<input type="hidden" id="hidden" value="2" />
		<table cellspacing="0" cellpadding="0" style="width: 1000px;">
			<tr>
				<td colspan="2">
				</td>
			</tr>
			<tr>
				<td valign="top" style="width: 20%;">
					<html:form action="cash.do" method="post" styleId="form1">
						<table cellspacing="0" cellpadding="0">
							<tr>
								<th colspan="2">
									分析参数
								</th>
							</tr>
							<tr>
								<td style="width: 50%">
									分析类型：
								</td>
								<td>
									<html:select property="reportType" onchange="changetype(this);"
										styleId="select1" style="width:100%;">
										<html:option value="stock">库存分析</html:option>
										<html:option value="onedaycheck">每日对账</html:option>
										<!--<html:option value="historymoney">历史分析</html:option>-->
										<html:option value="addmoney">加钞分析</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<td>
									设备类型：
								</td>
								<td>
									<html:select property="type" onclick="change(this);"
										style="width:100%;">
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
							<tr style="display: none;" id="tr1">
								<td colspan="2">
									<html:select property="atmids" style="width:100%;"
										multiple="true" size="10">
										<logic:notEmpty name="atmid">
											<html:optionsCollection name="atmid" value="atmid"
												label="atmid" />
										</logic:notEmpty>
									</html:select>
								</td>
							</tr>
							<tr style="display: block;" id="qjetb">
								<td>
									取款金额：
								</td>
								<td>
									<input type="text" name="qcash" style="width: 100%;" size="10"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
								</td>
							</tr>
							<tr style="display: block;" id="cjetb">
								<td>
									存款金额：
								</td>
								<td>
									<input type="text" name="ccash" size="10" style="width: 100%;"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
								</td>
							</tr>

							<tr style="display: none;" id="trtb">
								<td>
									起始日期：
								</td>
								<td>
									<html:text styleId="begin" property="timebegin" size="10"
										style="width:100%;" onfocus="calendar()" />
								</td>
							</tr>
							<tr style="display: none;" id="trte">
								<td>
									结束日期：
								</td>
								<td>
									<input type="text" id="end" name="timeend" size="10"
										style="width: 100%;" onfocus="calendar()" />
								</td>
							</tr>
							<tr>
								<td>
									<html:button styleId="stock" property="" value="分析" />
								</td>
								<td style="display: block;" id="bttd" align="left">
									<html:button property="" onclick="report();" value="导出" />
								</td>
							</tr>
						</table>
						<html:hidden property="type" value="2" />
					</html:form>
				</td>
				<td valign="top">
					<div id="id_showload">

					</div>
					<!-- 库存分析 -->
					<div id="id_stock">

					</div>
					<!-- 历史分析 -->
					<div id="id_historymoney">
						<logic:present name="historyq">
							<table border="0" width="100%">
								<tr>
									<th>
										设备编号
									</th>
									<th>
										取款总交易笔数
									</th>
									<th>
										取款总金额
									</th>
									<th>
										取款日均交易笔数
									</th>

									<th>
										平均取款金额
									</th>
								</tr>
								<logic:iterate id="tlr" name="historyq">
									<tr>
										<td>
											<bean:write property="atmid" name="tlr" />
										</td>
										<td>
											<bean:write property="count" name="tlr" />
										</td>
										<td>
											<bean:write property="sum" name="tlr" />
										</td>
										<td>
											<bean:write property="avgcount" name="tlr" />
										</td>
										<td>
											<bean:write property="avgmoney" name="tlr" />
										</td>

									</tr>
								</logic:iterate>
							</table>
						</logic:present>
					</div>
					<div>
						<logic:present name="historyc">
							<table border="0" width="100%">
								<tr>
									<th>
										设备编号
									</th>
									<th>
										存款总交易笔数
									</th>
									<th>
										存款总金额
									</th>
									<th>
										存款日均交易笔数
									</th>

									<th>
										存均取款金额
									</th>
								</tr>
								<logic:iterate id="tlr" name="historyc">
									<tr>
										<td>
											<bean:write property="atmid" name="tlr" />
										</td>
										<td>
											<bean:write property="count" name="tlr" />
										</td>
										<td>
											<bean:write property="sum" name="tlr" />
										</td>
										<td>
											<bean:write property="avgcount" name="tlr" />
										</td>
										<td>
											<bean:write property="avgmoney" name="tlr" />
										</td>

									</tr>
								</logic:iterate>
							</table>
						</logic:present>
					</div>


				</td>
			</tr>
		</table>
		</div>
			</div>
		</div>
	</body>
</html>

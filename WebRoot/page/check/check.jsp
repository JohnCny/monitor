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
		<title>对账管理</title>
		<script type="text/javascript" src="resources/scripts/calendar.js"></script>
		<link href="resources/source/tablecloth.css" rel="stylesheet"
			type="text/css" media="screen" />
		<script type="text/javascript" src="resources/scripts/jquery-1.6.js"></script>

		<script type="text/javascript">
  function selectType(obj){
  var strsel = obj.options[obj.selectedIndex].value;
  if(strsel==999999){
  document.getElementById("file").style.display="none";
    document.getElementById("atmids").style.display="block";
    }
   else if(strsel==9999999){
   document.getElementById("file").style.display="block";
   	 document.getElementById("atmids").style.display="none";
   }
   else{
   document.getElementById("atmids").style.display="none";
   document.getElementById("file").style.display="none";
   }
   }
   
   
   
   $(document).ready(function() {
    $("#check").click(function() {
       $.ajax({
            url: 'check.do',
            type: 'post',
            cache: false,
            data: $('#form1').serialize(),
            timeout: 300000,
            error: function() {
                alert('错误');
            },
            success: function(json) {
            var data_backs = eval('(' + json + ')');

                var div_out = "<div id='doc'><div class='fixTh' style='text-align: left;'>";
                var div_title = "<table cellspacing='0' cellpadding='0' style='width: 98.5%'><tr><th width='10%'>设备编号</th><th width='20%'>卡号</th><th width='10%'>输入金额</th><th width='10%'>出钞情况</th><th width='10%'>回收张数</th><th width='10%'>C端金额</th><th width='10%'>C端时间</th><th width='10%'>后台金额</th><th width='10%'>后台时间</th></tr></table>";
                var div_top = "<div class='tc' style='height: 470px;'>";
                var table_top = "<table cellspacing='0' cellpadding='0'>";
                var table_body = "";

				var strs= new Array(); 
                $.each(data_backs,function(key, value) {
                	strs=key.split(",");
                    table_body += "<tr><td width='10%' rowspan='"+(value.length+1)+"'>" + strs[0] + "</td>";
                	$.each(value,function(key, value) {
                    	table_body += "<td width='20%'>" + value.cardNo + "</td>" + "<td width='10%'>" + value.input + "</td>" + "<td width='10%'>" + value.dispense + "</td>"+ "<td width='10%'>" + value.reject + "</td>"+ "<td width='10%'>" + value.tradermb + "</td>"+ "<td width='10%'>" + value.tradetime + "</td>"+ "<td width='10%'>" + value.tradermb_db + "</td>"+ "<td width='10%'>" + value.tradetime_db + "</td></tr>";
                	});
                	if(value.length>0)table_body +="<tr>";
                	 table_body +="<td colspan='2' style='background-color: #66B3FF'>小计：</td><td style='background-color: #66B3FF'>C端总额：</td><td style='background-color: #66B3FF'>"+strs[1]+"</td><td style='background-color: #66B3FF'>P端总额：</td><td style='background-color: #66B3FF'>"+strs[2]+"</td><td style='background-color: #66B3FF'>差额：</td><td style='background-color: #66B3FF'>"+strs[3]+"</td></tr>";
                });
                var table_bottom = "</table>";
                var div_bottom = "</div></div></div>";
                $("#id_check").html(div_out + div_title + div_top + table_top + table_body + table_bottom + div_bottom);

                //返回的是Map类型
                //  var users = myObject.userlist;
                // $.each(users,function(i, user){
                //    $("#result").append("<li>"+ i + " name: " + user.username + "&nbsp; pwd:"+ user.password +"</li>");
                // });
            }
        });

    });

    //ajax提交
    $("#id_check").ajaxStart(function() {
        $(this).html("<img src='resources/source/8.gif'>&nbsp;正在加载");
    });

    //ajax成功状态
    $("#id_stock").ajaxSuccess(function() {
        //   $(this).html("保存成功!");
    });

    //ajax结束状态
    $("#id_stock").ajaxStop(function() {
        // $(this).hide();
    });

});
   
   <!--
function check(action){
 if(action==1)
document.forms[0].action="check.do";
else if(action==2)
document.forms[0].action="reportCheck.do";
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
		<table cellspacing="0" cellpadding="0" style="width: 1000px;">
			<tr>
				<td colspan="2">
					
				</td>
			</tr>
			<tr>
				<td valign="top" style="width: 20%;">
					<html:form action="check.do" enctype="multipart/form-data"
						styleId="form1">
						<table cellspacing="0" cellpadding="0">
							<tr>
							<tr>
								<th colspan="2">
									对账参数
								</th>
							</tr>
							<tr>
								<td align="right">
									对账日期：
								</td>
								<td>
									<html:text property="time" onfocus="calendar()" size="7"
										style="width:100%" />
								</td>
							</tr>

							<tr>
								<td align="right">
									设备编号：
								</td>
								<td width="10%">
									<html:select property="type" style="width:100%"
										onchange="selectType(this)" styleId="sId">
										<logic:present name="typelist">
											<logic:iterate id="tlr" name="typelist">
												<html:option value="${tlr.atmid}">
																${tlr.type }
															</html:option>
											</logic:iterate>
										</logic:present>
										<html:option value="999999">自选设备</html:option>
										<!--<html:option value="9999999">手动对账</html:option>-->
									</html:select>
								</td>
							</tr>
							<tr style="display: none;" id="atmids">
								<td colspan="2">
									<html:select property="atmids" style="width:100%" styleId="sId"
										size="15" multiple="true">
										<logic:notEmpty name="atmid">
											<html:optionsCollection name="atmid" value="atmid"
												label="atmid" />
										</logic:notEmpty>
									</html:select>
								</td>
							</tr>
							<tr style="display: none;" id="file">
								<td align="right">
									对账流水：
								</td>
								<td>
									<html:file property="file" size="7" />
								</td>
							</tr>
							<tr>
								<td width="11%" align="right">
									<html:button property="" styleId="check" value="显示" />
								</td>
								<td width="11%">
									<html:button property="" onclick="check(2);" value="导出" />
								</td>
							</tr>
						</table>
					</html:form>
				</td>
				<td valign="top">
					<div id="id_check"></div>
				</td>

			</tr>
		</table>
		<input type="hidden" id="hidden" value="4" />
		</div>
			</div>
		</div>
	</body>
</html>
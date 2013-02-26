<%@ page language="java" pageEncoding="UTF-8"
	import="ChartDirector.*,java.util.*;"%>
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

<%
	request.setCharacterEncoding("UTF-8");
%>

<%
	// 交易量排名

	double[] data10 = { (Double) request
			.getAttribute("tradestatecategoryImgDataQk") };
	double[] data11 = { (Double) request
			.getAttribute("tradestatecategoryImgDataCk") };
	XYChart c1 = new XYChart(300, 80, 0xddddff, -1, 1);
	c1.setRoundedFrame();
	c1.setPlotArea(10, 10, 280, 50);
	BarLayer layer1 = c1.addBarLayer2(Chart.Side, 3);
	layer1.addDataSet(data10, 0x0000c0);
	layer1.addDataSet(data11, 0x982810);
	c1.swapXY(true);
	layer1.setBarGap(0.5);
	c1.xAxis();
	c1.yAxis();
	String chart1URL = c1.makeSession(request, "chart1");
	String imageMap1 = c1.getHTMLImageMap("", "", "aaa");
%>
<%
	//全天交易走势
	double[] data20 = ((HashMap<String, double[]>) request
			.getAttribute("tradestatecategoryImgData")).get("qk");
	double[] data21 = ((HashMap<String, double[]>) request
			.getAttribute("tradestatecategoryImgData")).get("ck");
	String[] labels2 = { "0", "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "10", "11", "12", "13", "14", "15", "16", "17", "18",
			"19", "20", "21", "22", "23" };

	XYChart c = new XYChart(700, 150, 0xddddff, -1, 1);
	c.setRoundedFrame();

	c.setPlotArea(50, 10, 630, 100, 0xffffff, -1, -1, 0xcccccc,
			0xcccccc);

	c.addLegend(500, 120, false).setBackground(Chart.Transparent);

	c.setDefaultFonts("SIMSUN.TTC", "simhei.ttf");

	c.xAxis().setLabels(labels2);

	c.xAxis().setWidth(2);
	c.yAxis().setWidth(2);

	SplineLayer layer2 = c.addSplineLayer();

	layer2.setLineWidth(2);

	layer2.addDataSet(data20, 0x0000c0, "取款").setDataSymbol(
			Chart.CircleSymbol, 9, 0xffff00);

	layer2.addDataSet(data21, 0x982810, "存款").setDataSymbol(
			Chart.DiamondSymbol, 9, 0xf040f0);

	//output the chart
	String chart2URL = c.makeSession(request, "chart2");
	//include tool tip for the chart
	String imageMap2 = c.getHTMLImageMap("", "",
			"title='{xLabel}点: {value}笔'");
%>


<%
	//设备状态统计 
	PieChart c3 = new PieChart(500, 280, 0xddddff, -1, 1);
	c3.setRoundedFrame();
	c3.setPieSize(250, 140, 100);
	c3.setDefaultFonts("SIMSUN.TTC", "simhei.ttf");
	double[] data = (double[]) request
			.getAttribute("atmstatecategoryImgData");
	String[] labels3 = new String[] { "预警", "正常", "业务故障", "硬件故障" };
	c3.setData(data, labels3); // 放数据    
	c3.set3D();
	c3.setLabelLayout(Chart.SideLayout);
	TextBox t1 = c3.setLabelStyle();
	t1.setRoundedCorners(10);
	c3.setLineColor(Chart.SameAsMainColor, 0x000000);
	String chart3URL = c3.makeSession(request, "chart3");
	String imageMap3 = c3.getHTMLImageMap("", "",
			"title='{label}: {value}台'");

	double value = (Double) request
			.getAttribute("atmstatecategoryInfoPer");
	AngularMeter m = new AngularMeter(200, 115, 0xddddff, -1, 1);
	m.setRoundedFrame();
	m.setDefaultFonts("SIMSUN.TTC", "simhei.ttf");
	m.setMeter(100, 100, 85, -90, 90);
	m.setScale(0, 100, 20, 10, 5);
	m.addZone(0, 60, 0, 85, 0xff6666);
	m.addZone(60, 80, 0, 85, 0xffff33);
	m.addZone(80, 100, 0, 85, 0x66ff66);
	m.addText(100, 60, "开机率" + m.formatValue(value, "2") + "%", "", 10,
			Chart.TextColor, Chart.Center);
	m.addPointer(value, 0x40666699, 0x000000);
	String chart4URL = m.makeSession(request, "chart4");
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>首页</title>

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
#box_m{width:1120px;margin:0 auto;}
#box_nav{float:left; width:90px;}
#box_nav_s{float:left; widows:1000px;}
</style>
		<script type="text/javascript">
		function qiehuan(num){
	if(num==-1)
	{
		var t=document.getElementById('hidden');
		num=t.value;
	}
	for(var id = 0;id<=7;id++)
		{
			if(id==num)
			{
				document.getElementById("qh_con"+id).style.display="block";
				document.getElementById("mynav"+id).className="nav_on";
			}
			else
			{
				document.getElementById("qh_con"+id).style.display="none";
				document.getElementById("mynav"+id).className="";
			}
		}
	}
	
	function fGetStyleList(event) {
	var txtStyle = document.getElementById("txtStyle"); 
	var dvStyleList = document.getElementById("dvStyleList"); 
	/*var aPos = fPosition(lnkStyle);
	var x = aPos[0];
	var y = aPos[1] + 25;
	dvStyleList.style.top = y + "px";
	dvStyleList.style.left = x + "px";*/
	dvStyleList.style.display = "";
	var ulStyle = document.getElementById("ulStyle"); 
	var liList = ulStyle.getElementsByTagName("LI");
	for(var i=0;i<liList.length;i++){
		liList[i].className = "";
	}
	var styleMap = {"-1" : 0, "4" : 1, "0" : 2, "9" : 2};

	var li = liList[styleMap[txtStyle.value]];
	li.className = "choiceOn";
	fStopPropagation(event);
	}
		function submit1()
		{
					document.forms[0].submit();
		}
		
		function document.onkeydown() 
		{ 
			if(event.keyCode == 13) { 
				submit1();
			} 
		} 
			</script>
	</head>

	<div id="box_m">
<div id="box_nav"><div id="menu_out">
			<div id="menu_in">
			
				<div id="menu">
					<ul id="nav">
						<li>
							<a class="nav_off" id="mynav0" onclick="javascript:qiehuan(0)"
								href="index.do?method=getIndexInfo"><span style="padding-left:22px; padding-right:20px;">首 页</span> </a>
						</li>
						<li>
							<a href="user.do?method=level&type=em"
								onclick="javascript:qiehuan(1)" id="mynav1" class="nav_off"><span>状态监控</span>
							</a>
						</li>
						<li>
							<a href="user.do?method=level&type=sa" onclick="javascript:qiehuan(2);return false;" id="mynav2"
								class="nav_off"><span>统计分析</span> </a>
						</li>
						<li>
							<a href="user.do?method=level&type=rm" onclick="javascript:qiehuan(3)" id="mynav3" class="nav_off"><span>远程管理</span>
							</a>
						</li>
						<li>
							<a href="user.do?method=level&type=ct"
								onclick="javascript:qiehuan(4)" id="mynav4" class="nav_off"><span>对账管理</span>
							</a>
						</li>
						<li>
							<a href="user.do?method=level&type=um" onclick="javascript:qiehuan(5)" id="mynav5" class="nav_off"><span>短信管理</span>
							</a>
						</li>
						<li>
							<a href="user.do?method=level&type=sc" onclick="javascript:qiehuan(6)" id="mynav6" class="nav_off"><span>系统管理</span>
							</a>
						</li>

					</ul>
					
				</div>
				<div class="zwh"></div>
			</div>
		</div></div>
<div id="box_nav_s">
	<div id="menu_out_s">
    <div id="menu_in_s">
        <div id="menu_s">
            <div id="menu_con">
                <div id="menu_con"> <span style="float: left; margin: 8px;"><img
									src="resources/source/loguser.gif" width="16" height="16" border="0" />您好, 
                    ,欢迎使用自助设备综合管理系统 | | <a href="#" style="color:blue">退出</a> </span>
                    <div id="qh_con0" style="display: none;"> </div>
                    <div id="qh_con1" style="display: none"> </div>
                    <div id="qh_con2" style="display: block ">
                        <ul>
                            <li> <a href="atmids.do?method=getCashAtmId" class="menu_on"><span>现金分析</span> </a> </li>
                            <li class="menu_line2"></li>
                            <li> <a href="atmids.do?method=getId"  class="menu_off"><span>交易信息</span> </a> </li>
                            <li class="menu_line2"></li>
                            <li> <a href="atmids.do?method=getId3"><span>运行信息</span> </a> </li>
                        </ul>
                    </div>
                    <div id="qh_con3" style="display: none">
                        <ul>
                            <li> <a href="atmids.do?method=getDownloadId"><span>文件下载</span> </a> </li>
                            <li class="menu_line2"></li>
                            <li> <a href="atmcompany.do?type=u"><span>文件上传</span> </a> </li>
                            <li class="menu_line2"></li>
                            <li> <a href="atmids.do?method=getRebootId"><span>远程执行</span> </a> </li>
                        </ul>
                    </div>
                    <div id="qh_con4" style="display: none"> </div>
                    <div id="qh_con5" style="display: none">
                        <ul>
                            <li> <a href="smsstatusconfig.do?method=all"><span>发信管理</span> </a> </li>
                            <li class="menu_line2"></li>
                            <!-- <li>
										<a href="smsconfig.do?method=all"><span>用户管理</span> </a>
									</li>
									<li class="menu_line2"></li> -->
                            <li> <a href="smsinfo.do?method=getInfoAll"><span>历史查询</span> </a> </li>
                            <li class="menu_line2"></li>
                            <li> <a href="smsinfo.do?method=getSmsSendStatus"><span>测试与发送</span> </a> </li>
                        </ul>
                    </div>
                    <div id="qh_con6" style="display: none">
                        <ul>
                            <li> <a href="user.do?method=list"><span>用户管理</span> </a> </li>
                            <li class="menu_line2"></li>
                            <li> <a href="atmsetconfig.do?method=all"><span>服务管理</span> </a> </li>
                            <li class="menu_line2"></li>
                            <li> <a href="atmcinfoconfig.do?method=all"><span>设备管理</span> </a> </li>
                            <li class="menu_line2"></li>
                            <li> <a href="infoconfig.do?method=allInfo"><span>信息管理</span> </a> </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
	<div style=" clear:both"></div>
	<div><table cellspacing="0" cellpadding="0" style="width: 1000px;">
    
    <tr>
        <td width="700" valign="top"><table cellspacing="0" cellpadding="0">
                <tr>
                    <th style="text-align: left;" colspan="2"> 设备状态统计 </th>
                </tr>
                <tr>
                    <td width="40%"><table cellspacing="0" cellpadding="0">
                            <tr>
                                <td colspan="4"><img
													src='<%=response.encodeURL("page/view/getchart.jsp?"
								+ chart4URL)%>'> </td>
                            </tr>
                            <tr>
                                <td colspan="4"> 服务设备 </td>
                            </tr>
                            <tr>
                                <td><font size="3">正常</font> </td>
                                <td><font size="3" color="#28FF28">${
                                    atmstatecategoryInfo.zc}</font><font size="3">台</font> </td>
                                <td><font size="3">预警</font> </td>
                                <td><font size="3" color="#FF0000">${
                                    atmstatecategoryInfo.yj}</font> <font size="3">台</font> </td>
                            </tr>
                            <tr>
                                <td colspan="4"> 故障设备 </td>
                            </tr>
                            <tr>
                                <td><font size="3">硬件</font> </td>
                                <td><font size="3" color="#9D9D9D">${
                                    atmstatecategoryInfo.gz}</font> <font size="3">台</font> </td>
                                <td><font size="3">业务</font> </td>
                                <td><font size="3" color="#FF8000">${
                                    atmstatecategoryInfo.wh}</font> <font size="3">台</font> </td>
                            </tr>
                        </table></td>
                    <td><img
									src='<%=response.encodeURL("page/view/getchart.jsp?"
									+ chart3URL)%>'
									usemap="#map1" border="0"> </td>
                </tr>
            </table></td>
        <td rowspan="2" width="300" valign="top"><table cellspacing="0" cellpadding="0">
                <tr>
                    <th style="text-align: left;" colspan="3"> 交易量排名 </th>
                </tr>
                <tr>
                    <td><div id="doc">
                            <div class="fixTh" style="text-align: left;">
                                <table cellspacing="0" cellpadding="0" border="1"
											style="width: 95%">
                                    <tr>
                                        <td style="width: 40%;"> 设备编号 </td>
                                        <td style="width: 30%;"> 取款笔数 </td>
                                        <td style="width: 30%;"> 存款笔数 </td>
                                    </tr>
                                </table>
                                <logic:present name="tradestatecategoryData">
                                    <div class="tc" style="height: 370px;">
                                        <table cellspacing="0" cellpadding="0">
                                            <logic:iterate id="tlr" name="tradestatecategoryData">
                                                <tr>
                                                    <td style="width: 40%;"><bean:write name="tlr" property="id" />                                                    </td>
                                                    <td style="width: 30%;"><bean:write name="tlr" property="qk" />                                                    </td>
                                                    <td style="width: 30%;"><bean:write name="tlr" property="ck" />                                                    </td>
                                                </tr>
                                            </logic:iterate>
                                        </table>
                                    </div>
                                </logic:present>
                            </div>
                        </div>
                        <div> <img
										src='<%=response.encodeURL("page/view/getchart.jsp?"
									+ chart1URL)%>'
										usemap="#map1" border="0">
                            <map name="map1">
                                <%=imageMap1%>
                            </map>
                        </div></td>
                </tr>
            </table></td>
    </tr>
    <tr>
        <td width="700">
                <table cellspacing="0" cellpadding="0">
                    <tr>
                        <th style="text-align: left;"> 全天交易量走势图 </th>
                        <th style="text-align: right; padding: 0, 5, 0, 0px;"> 设备编号:
                            <input type="text" size="10" name="atmid"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"
										value="${atmId }" />
                            <input type="radio" name="type" value="0"
										<%if(request.getAttribute("type").equals("0")||request.getAttribute("type")==null||request.getAttribute("type").equals("")) out.print("checked");%>
										onclick="submit1();" />
                            交易笔数
                            <input type="radio" name="type" value="1"
										<%if(request.getAttribute("type").equals("1")) out.print("checked");%>
										onclick="submit1();" />
                            交易金额 </th>
                    </tr>
                    <tr>
                        <td colspan="2"><img
										src='<%=response.encodeURL("page/view/getchart.jsp?"
									+ chart2URL)%>'
										usemap="#map2" border="0">
                            <map name="map2">
                                <%=imageMap2%>
                            </map>                        </td>
                    </tr>
                </table>            </td>
    </tr>
</table></div>
</div>
</div>
</body>
</html>

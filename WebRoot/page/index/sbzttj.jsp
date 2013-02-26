<%@ page language="java" pageEncoding="UTF-8" import="ChartDirector.*;"%>
<%@ taglib prefix="cewolf" uri="/WEB-INF/cewolf.tld"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<%
	request.setCharacterEncoding("UTF-8");
	//以两个系列数据为例
	// 设置Chart大小、背景色    
	PieChart pc = new PieChart(500, 280, 0xddddff, -1, 1);
	pc.setRoundedFrame();
	pc.setPieSize(250, 140, 100);

	// 设置图表标题    
	// 只要将文字转成UTF-8编码就可以解决中文乱码问题    
	// 另外如果你将程序部署到Linux下就需要copy相应的字体文件，比如simsun.ttc文件到/usr/share/font下    
	//TextBox t = null;  
	pc.setDefaultFonts("SIMSUN.TTC", "simhei.ttf");
	//t = pc.addTitle("ee谔谔", "", 14); // 中文就从resource.properties中取吧    
	//t.setBackground(0xaaaaff); // 字体也要有背景的    
	//t.setBackground(0xaaaaff, 0xaaffaa); // 需要加边框吗？加上第二个参数就可以了    
	//t.setHeight(30);

	//TextBox total = pc.addText(10, 35, "嗷嗷嗷"); // 加个文字吧，随便显示什么都行了    
	//total.setFontColor(0x000000);
	//total.setFontSize(10);

	double[] data = (double[]) request
			.getAttribute("atmstatecategoryImgData");
	//double[] data = new double[] { 100, 90, 80, 70 };
	String[] labels = new String[] { "预警", "正常", "业务故障", "硬件故障" };

	pc.setData(data, labels); // 放数据    

	//double[] depths1 = { 10, 7, 4, 1 };
	pc.set3D();
	pc.setLabelLayout(Chart.SideLayout);

	// 每一块饼都得给个说明    
	TextBox t1 = pc.setLabelStyle();
	//t1.setBackground(Chart.SameAsMainColor, Chart.Transparent, Chart
	//		.glassEffect());
	t1.setRoundedCorners(10);
	pc.setLineColor(Chart.SameAsMainColor, 0x000000);
	//pc.setStartAngle(225);

	//output the chart
	String chart1URL = pc.makeSession(request, "chart1");
	//include tool tip for the chart
	String imageMap1 = pc.getHTMLImageMap("", "",
			"title='{label}: {value}台'");
%>

<%
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

	//m.addText(156, 8, m.formatValue(value, "2"), "Arial", 8, 0xffffff)
	//		.setBackground(0x000000, 0, -1);

	m.addPointer(value, 0x40666699, 0x000000);

	String chart2URL = m.makeSession(request, "chart1_2");
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title></title>
		<link href="resources/source/tablecloth.css" rel="stylesheet"
			type="text/css" media="screen" />
	</head>

	<body>
		<table cellspacing="0" cellpadding="0">

			<tr>
				<th style="text-align: left;" colspan="2">
					设备状态统计
				</th>
			</tr>
			<tr>
				<td width="40%">
					<logic:present name="atmstatecategoryInfo">
						<table cellspacing="0" cellpadding="0" style="border: 0">
							<tr>
								<td colspan="4">
									<img
										src='<%=response.encodeURL("page/view/getchart.jsp?"
								+ chart2URL)%>'>
									<map name="map2"><%=imageMap1%></map>

								</td>
							</tr>
							<tr>
								<td>
									<font size="5">正常</font>
								</td>
								<td>
									<font size="5" color="#28FF28">${
										atmstatecategoryInfo.zc}</font><font size="5">台</font>
								</td>
								<td>
									<font size="5">预警</font>
								</td>
								<td>
									<font size="5" color="#FF0000">${
										atmstatecategoryInfo.yj}</font>
									<font size="5">台</font>

								</td>
							</tr>
							<tr>
								<td>
									<font size="5">故障</font>
								</td>
								<td>
									<font size="5" color="#9D9D9D">${
										atmstatecategoryInfo.gz}</font>
									<font size="5">台</font>
								</td>
								<td>
									<font size="5">维护</font>
								</td>
								<td>
									<font size="5" color="#FF8000">${
										atmstatecategoryInfo.wh}</font>
									<font size="5">台</font>
								</td>
							</tr>
						</table>
					</logic:present>
				</td>
				<td>
					<img
						src='<%=response.encodeURL("page/view/getchart.jsp?"
									+ chart1URL)%>'
						usemap="#map1" border="0">
					<map name="map1"><%=imageMap1%></map>

				</td>
			</tr>
		</table>
	</body>
</html>

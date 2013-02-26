<%@ page language="java" pageEncoding="utf-8" import="ChartDirector.*;"%>
<%
	// The data for the bar chart
	double[] data = { 3.9 };
	double[] data1 = { 8.1 };

	// The labels for the bar chart
	//String[] labels = { "Bastic Group", "Simpa" };

	XYChart c = new XYChart(350, 80, 0xddddff, -1, 1);
	c.setRoundedFrame();

	c.setPlotArea(10, 10, 330, 60);

	// Add a bar chart layer using the given data. Use a gradient color for the bars,
	// where the gradient is from dark green (0x008000) to white (0xffffff)
	BarLayer layer = c.addBarLayer2(Chart.Side, 3);
	layer.addDataSet(data, 0x0000c0);
	layer.addDataSet(data1, 0x982810);
	//BarLayer layer = c.addBarLayer(data);

	// Swap the axis so that the bars are drawn horizontally
	c.swapXY(true);

	// Set the bar gap to 10%
	layer.setBarGap(0.5);

	// Use the format "US$ xxx millions" as the bar label
	//layer.setAggregateLabelFormat("US$ {value} millions");

	// Set the bar label font to 10 pts Times Bold Italic/dark red (0x663300)
	//layer.setAggregateLabelStyle("Times New Roman Bold Italic", 10,
	//		0x663300);

	// Set the labels on the x axis
	//TextBox textbox = c.xAxis().setLabels(labels);

	// Set the x axis label font to 10pt Arial Bold Italic
	//textbox.setFontStyle("Arial Bold Italic");
	//textbox.setFontSize(10);

	// Set the x axis to Transparent, with labels in dark red (0x663300)
	c.xAxis().setColors(Chart.Transparent, 0x663300);

	// Set the y axis and labels to Transparent
	c.yAxis().setColors(Chart.Transparent, Chart.Transparent);

	// output the chart
	String chart1URL = c.makeSession(request, "chart3");

	// Include tool tip for the chart
	String imageMap1 = c.getHTMLImageMap("", "",
			"title='{xLabel}: US${value} millions'");
%>
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
	</head>

	<body>
		<table cellspacing="0" cellpadding="0" style="width: 100%">
			<tr>
				<th style="text-align: left;">
					交易量排名
				</th>
			</tr>
			<tr>
				<td height="400px">
					<iframe src="index.do?method=getTradeInfo" width="100%"
						height="100%"></iframe>
				</td>
			</tr>
			<tr>
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

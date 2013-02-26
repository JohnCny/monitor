<%@ page language="java" pageEncoding="UTF-8"
	import="ChartDirector.*,java.util.*;"%>
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
	double[] data0 = ((HashMap<String, double[]>) request
			.getAttribute("tradestatecategoryImgData")).get("qk");
	double[] data1 = ((HashMap<String, double[]>) request
			.getAttribute("tradestatecategoryImgData")).get("ck");
	//double[] data0 = { 32, 39, 23, 28, 41, 38, 26, 35, 29 };
	//double[] data1 = { 50, 55, 47, 34, 47, 53, 38, 40, 101 };

	String[] labels = { "0", "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "10", "11", "12", "13", "14", "15", "16", "17", "18",
			"19", "20", "21", "22", "23" };

	XYChart c = new XYChart(900, 150, 0xddddff, -1, 1);
	c.setRoundedFrame();

	c.setPlotArea(50, 10, 840, 100, 0xffffff, -1, -1, 0xcccccc,
			0xcccccc);

	c.addLegend(700, 120, false).setBackground(Chart.Transparent);

	c.setDefaultFonts("SIMSUN.TTC", "simhei.ttf");

	c.xAxis().setLabels(labels);

	c.xAxis().setWidth(2);
	c.yAxis().setWidth(2);

	SplineLayer layer = c.addSplineLayer();

	layer.setLineWidth(2);

	layer.addDataSet(data0, 0x0000c0, "取款").setDataSymbol(
			Chart.CircleSymbol, 9, 0xffff00);

	layer.addDataSet(data1, 0x982810, "存款").setDataSymbol(
			Chart.DiamondSymbol, 9, 0xf040f0);

	//output the chart
	String chart1URL = c.makeSession(request, "chart2");
	//include tool tip for the chart
	String imageMap1 = c.getHTMLImageMap("", "",
			"title='{xLabel}点: {value}笔'");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title></title>
		<link href="resources/source/tablecloth.css" rel="stylesheet"
			type="text/css" media="screen" />
		<script type="text/javascript">
		function submit1()
		{
					document.forms[0].submit();
		}
		
			function document.onkeydown() { 
			if(event.keyCode == 13) { 
				submit1();
			} 
		} 
			</script>
	</head>

	<body>
		<form action="index.do?method=getTradeStateStatistics" method="post">
			<table cellspacing="0" cellpadding="0">
				<tr>
					<th style="text-align: left;">
						全天交易量走势图
					</th>
					<th style="text-align: right; padding: 0, 5, 0, 0px;">
						<input type="text" size="10" name="atmid"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
						<input type="radio" name="type" value="0" onclick="submit1();" />
						交易笔数
						<input type="radio" name="type" value="1" onclick="submit1();"/>
						交易金额
					</th>

				</tr>

				<tr>
					<td colspan="2">
						<img
							src='<%=response.encodeURL("page/view/getchart.jsp?"
									+ chart1URL)%>'
							usemap="#map1" border="0">
						<map name="map1"><%=imageMap1%></map>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>

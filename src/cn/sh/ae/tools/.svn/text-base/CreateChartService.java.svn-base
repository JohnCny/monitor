//package cn.sh.ae.tools;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.io.FileOutputStream;
//
//import org.apache.log4j.Logger;
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartUtilities;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.axis.CategoryAxis;
//import org.jfree.chart.axis.CategoryLabelPositions;
//import org.jfree.chart.axis.NumberAxis;
//import org.jfree.chart.plot.CategoryPlot;
//import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.chart.renderer.category.LineAndShapeRenderer;
//import org.jfree.chart.title.TextTitle;
//import org.jfree.data.category.CategoryDataset;
//import org.jfree.data.general.DatasetUtilities;
//
//import cn.sh.ae.util.MyConstant;
//import cn.sh.ae.util.MyFile;
//import cn.sh.ae.util.MyTime;
//
//public class CreateChartService {
//
//	private static final String CHART_PATH = MyConstant.USERPATH;
//
//	static Logger logger = Logger.getLogger(CreateChartService.class.getName());
//
//	/**
//	 * 生成折线图
//	 */
//	public String makeLineAndShapeChart(String title, double[][] data,
//			String[] rowKeys, String[] columnKeys, String sessionid) {
//		// double[][] data = new double[][] { { 672, 766, 223, 540, 126 },
//		// { 325, 521, 210, 340, 106 }, { 332, 256, 523, 240, 526 } };
//		// String[] rowKeys = { "苹果", "梨子", "葡萄" };
//		// String[] columnKeys = { "北京", "上海", "广州", "成都", "深圳" };
//		CategoryDataset dataset = getBarData(data, rowKeys, columnKeys);
//		return createTimeXYChar(title, "", "", dataset, sessionid);
//	}
//
//	// 折线图 数据集
//	public CategoryDataset getBarData(double[][] data, String[] rowKeys,
//			String[] columnKeys) {
//		return DatasetUtilities
//				.createCategoryDataset(rowKeys, columnKeys, data);
//
//	}
//
//	/**
//	 * 折线图
//	 * 
//	 * @param chartTitle
//	 * @param x
//	 * @param y
//	 * @param xyDataset
//	 * @param charName
//	 * @return
//	 */
//	public String createTimeXYChar(String chartTitle, String x, String y,
//			CategoryDataset xyDataset, String sessionid) {
//
//		JFreeChart chart = ChartFactory.createLineChart(chartTitle, x, y,
//				xyDataset, PlotOrientation.VERTICAL, true, true, false);
//
//		chart.setTextAntiAlias(false);
//		chart.setBackgroundPaint(Color.WHITE);
//		// 设置图标题的字体重新设置title
//		Font font1 = new Font("隶书", Font.BOLD, 25);
//		Font font2 = new Font("隶书", Font.ITALIC, 10);
//		TextTitle title = new TextTitle(chartTitle);
//		title.setFont(font1);
//		chart.setTitle(title);
//		chart.getLegend().setItemFont(font2);
//		// 设置面板字体
//		Font labelFont = new Font("SansSerif", Font.TRUETYPE_FONT, 12);
//
//		chart.setBackgroundPaint(Color.WHITE);
//
//		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
//		// x轴 // 分类轴网格是否可见
//		categoryplot.setDomainGridlinesVisible(true);
//		// y轴 //数据轴网格是否可见
//		categoryplot.setRangeGridlinesVisible(true);
//
//		categoryplot.setRangeGridlinePaint(Color.WHITE);// 虚线色彩
//
//		categoryplot.setDomainGridlinePaint(Color.WHITE);// 虚线色彩
//
//		categoryplot.setBackgroundPaint(Color.lightGray);
//
//		// 设置轴和面板之间的距离
//		// categoryplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
//
//		CategoryAxis domainAxis = categoryplot.getDomainAxis();
//
//		domainAxis.setLabelFont(labelFont);// 轴标题
//		domainAxis.setTickLabelFont(labelFont);// 轴数值
//
//		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90); // 横轴上的
//		// Lable
//		// 45度倾斜
//		// 设置距离图片左端距离
//		domainAxis.setLowerMargin(0.0);
//		// 设置距离图片右端距离
//		domainAxis.setUpperMargin(0.0);
//
//		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
//		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
//		numberaxis.setAutoRangeIncludesZero(true);
//
//		// 获得renderer 注意这里是下嗍造型到lineandshaperenderer！！
//		LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot
//				.getRenderer();
//
//		lineandshaperenderer.setBaseShapesVisible(true); // series 点（即数据点）可见
//		lineandshaperenderer.setBaseLinesVisible(true); // series 点（即数据点）间有连线可见
//
//		// 显示折点数据
//		// lineandshaperenderer.setBaseItemLabelGenerator(new
//		// StandardCategoryItemLabelGenerator());
//		// lineandshaperenderer.setBaseItemLabelsVisible(true);
//
//		FileOutputStream fos_jpg = null;
//		try {
//			String chartName = CHART_PATH + sessionid + MyConstant.SESSIONPNG;
//			MyFile.isExist(chartName);
//			chartName += "lineAndShap"
//					+ MyTime.getTime("yyyy-MM-dd HH:mm:ss").substring(11)
//							.replaceAll(":", "") + ".png";
//			fos_jpg = new FileOutputStream(chartName);
//
//			int width = xyDataset.getColumnCount() * 15;
//			// 将报表保存为png文件
//			ChartUtilities.writeChartAsPNG(fos_jpg, chart, width < 700 ? 700
//					: width, 470);
//			// linux路径
//			logger.info("统计图表路径：" + chartName.split("webapps")[1]);
//			return chartName.split("webapps")[1];
//			// window路径
//			// logger.info("统计图表路径："+chartName);
//			// return chartName;
//		} catch (Exception e) {
//			logger.error(e.getLocalizedMessage(), e);
//			return null;
//		} finally {
//			try {
//				fos_jpg.close();
//			} catch (Exception e) {
//				logger.error(e.getLocalizedMessage(), e);
//			}
//		}
//	}
//}

package cn.sh.ae.tools;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.log4j.Logger;

import cn.sh.ae.util.MyTime;
import cn.sh.ae.vo.Atm;
import cn.sh.ae.vo.Atmhistory;
import cn.sh.ae.vo.Draw;
import cn.sh.ae.vo.Ejr;

public class WriteExcelCommon {
	static Logger logger = Logger.getLogger(WriteExcelCommon.class.getName());

	// private static final String CHART_PATH = MyConstant.USERPATH;

	public static void writeExcel(File file, String title, List<Draw> list) {
		WritableWorkbook book = null;
		try {
			// 打开文件
			book = Workbook.createWorkbook(file);
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("第一页", 0);
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			// 以及单元格内容为test

			/** 设置Execl */
			sheet.setColumnView(0, 15);// 设置第一列的宽度为4
			sheet.setColumnView(1, 15);// 设置第二列的宽度为23
			sheet.setColumnView(2, 15);// 设置第二列的宽度为23
			sheet.setColumnView(3, 15);// 设置第二列的宽度为23
			sheet.setColumnView(4, 15);// 设置第二列的宽度为23
			sheet.setColumnView(5, 20);// 设置第二列的宽度为23
			WritableFont fontNormal = new WritableFont(WritableFont.ARIAL, 11);
			WritableCellFormat cellFormat = new WritableCellFormat(fontNormal);
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
			cellFormat
					.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			cellFormat.setWrap(true);

			sheet.mergeCells(0, 0, 5, 0);
			Label label = new Label(0, 0, title);
			label.setCellFormat(cellFormat);
			// 将定义好的单元格添加到工作表中
			sheet.addCell(label);
			// sheet.mergeCells(3,0,4,0);
			WritableCellFormat cellFormat2 = new WritableCellFormat();
			cellFormat2.setAlignment(jxl.format.Alignment.CENTRE);
			/*
			 * 生成一个保存数字的单元格 必须使用Number的完整包路径，否则有语法歧义 单元格位置是第二列，第一行，值为789.123
			 */
			Label label1 = new Label(0, 1, "设备编号");
			label1.setCellFormat(cellFormat2);
			sheet.addCell(label1);
			Label label2 = new Label(1, 1, "交易总金额(元)");
			label2.setCellFormat(cellFormat2);
			sheet.addCell(label2);
			Label label3 = new Label(2, 1, "交易总笔数");
			label3.setCellFormat(cellFormat2);
			sheet.addCell(label3);
			Label label4 = new Label(3, 1, "交易平均金额(元)");
			label4.setCellFormat(cellFormat2);
			sheet.addCell(label4);
			Label label5 = new Label(4, 1, "交易平均笔数");
			label5.setCellFormat(cellFormat2);
			sheet.addCell(label5);
			Label label6 = new Label(5, 1, "所在地");
			label6.setCellFormat(cellFormat2);
			sheet.addCell(label6);

			// 数据居右显示
			WritableCellFormat cellFormat1 = new WritableCellFormat();
			cellFormat1.setAlignment(jxl.format.Alignment.RIGHT);
			int t = list.size(), a = 0, b = 0, c = 0, d = 0;
			for (int i = 0; i < list.size(); i++) {
				Label l1 = new Label(0, 2 + i, list.get(i).getAtmid());
				float a1 = list.get(i).getSum();
				float b1 = list.get(i).getCount();
				float c1 = list.get(i).getAvgmoney();
				float d1 = list.get(i).getAvgcount();

				Label l2 = new Label(1, 2 + i, String.valueOf(a1));
				Label l3 = new Label(2, 2 + i, String.valueOf(b1));
				Label l4 = new Label(3, 2 + i, String.valueOf(c1));
				Label l5 = new Label(4, 2 + i, String.valueOf(d1));
				Label l6 = new Label(5, 2 + i, list.get(i).getAddr());
				l1.setCellFormat(cellFormat2);
				l2.setCellFormat(cellFormat1);
				l3.setCellFormat(cellFormat2);
				l4.setCellFormat(cellFormat1);
				l5.setCellFormat(cellFormat2);
				l6.setCellFormat(cellFormat2);
				sheet.addCell(l1);
				sheet.addCell(l2);
				sheet.addCell(l3);
				sheet.addCell(l4);
				sheet.addCell(l5);
				sheet.addCell(l6);
				a += a1;
				b += b1;
				c += c1;
				d += d1;

			}
			Label l7 = new Label(0, 2 + t, "总计");
			Label l8 = new Label(1, 2 + t, String.valueOf(a));
			Label l9 = new Label(2, 2 + t, String.valueOf(b));
			Label l10 = new Label(3, 2 + t, String.valueOf(c));
			Label l11 = new Label(4, 2 + t, String.valueOf(d));
			l7.setCellFormat(cellFormat2);
			l8.setCellFormat(cellFormat1);
			l9.setCellFormat(cellFormat2);
			l10.setCellFormat(cellFormat1);
			l11.setCellFormat(cellFormat2);
			sheet.addCell(l7);
			sheet.addCell(l8);
			sheet.addCell(l9);
			sheet.addCell(l10);
			sheet.addCell(l11);

			book.write();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		} finally {
			// 写入数据并关闭文件
			try {
				if (book != null) {
					book.close();
				}
			} catch (IOException e) {
				logger.error(e.getLocalizedMessage(), e);
			} catch (WriteException e) {
				logger.error(e.getLocalizedMessage(), e);
			}

		}
	}

	public static void writeFaultExcel(File file, String title,
			List<Atmhistory> kasList) {
		try {
			// 打开文件
			WritableWorkbook book = Workbook.createWorkbook(file);
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("第一页", 0);
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			// 以及单元格内容为test

			/** 设置Execl */
			sheet.setColumnView(0, 15);// 设置第一列的宽度为4
			sheet.setColumnView(1, 15);// 设置第二列的宽度为23
			sheet.setColumnView(2, 20);// 设置第二列的宽度为23
			sheet.setColumnView(3, 20);// 设置第二列的宽度为23
			sheet.setColumnView(4, 15);// 设置第二列的宽度为23
			sheet.setColumnView(5, 20);// 设置第二列的宽度为23

			WritableFont fontNormal = new WritableFont(WritableFont.ARIAL, 11);
			WritableCellFormat cellFormat = new WritableCellFormat(fontNormal);
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
			cellFormat
					.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			cellFormat.setWrap(true);

			sheet.mergeCells(0, 0, 5, 0);
			Label label = new Label(0, 0, title);
			label.setCellFormat(cellFormat);
			// 将定义好的单元格添加到工作表中
			sheet.addCell(label);
			// sheet.mergeCells(3,0,4,0);
			/*
			 * 生成一个保存数字的单元格 必须使用Number的完整包路径，否则有语法歧义 单元格位置是第二列，第一行，值为789.123
			 */
			WritableCellFormat cellFormat1 = new WritableCellFormat();
			cellFormat1.setAlignment(jxl.format.Alignment.CENTRE);
			Label label1 = new Label(0, 1, "设备编号");
			label1.setCellFormat(cellFormat1);
			sheet.addCell(label1);
			// Label label2 = new Label(4, 1, "机器状态");
			// label2.setCellFormat(cellFormat1);
			// sheet.addCell(label2);
			Label label3 = new Label(1, 1, "故障设备");
			label3.setCellFormat(cellFormat1);
			sheet.addCell(label3);
			Label label4 = new Label(2, 1, "开始时间");
			label4.setCellFormat(cellFormat1);
			sheet.addCell(label4);
			Label label5 = new Label(3, 1, "结束时间");
			label5.setCellFormat(cellFormat1);
			sheet.addCell(label5);
			Label label6 = new Label(4, 1, "持续时间(分钟)");
			label6.setCellFormat(cellFormat1);
			sheet.addCell(label6);
			Label label7 = new Label(5, 1, "型号地址");
			label7.setCellFormat(cellFormat1);
			sheet.addCell(label7);

			String atmIdFlag = "0";
			String addrFlag = "0";
			for (int i = 0; i < kasList.size(); i++) {
				String atmId = kasList.get(i).getAtmId().trim();
				Label l1 = null;
				if (!atmId.equals(atmIdFlag)) {
					l1 = new Label(0, 2 + i, atmId);

				} else {
					l1 = new Label(0, 2 + i, "");
				}
				// Label l2 = new Label(4, 2 + i, String.valueOf(kasList.get(i)
				// .getDemstatus()));
				Label l3 = new Label(1, 2 + i, String.valueOf(kasList.get(i)
						.getBugId()));
				Label l4 = new Label(2, 2 + i, String.valueOf(kasList.get(i)
						.getBugBeg()));
				Label l5 = new Label(3, 2 + i, String.valueOf(kasList.get(i)
						.getBugEnd()));
				Label l6 = new Label(4, 2 + i, String.valueOf(kasList.get(i)
						.getSubTime()));
				String addr = kasList.get(i).getConstant().trim();
				Label l7 = null;
				if (!atmId.equals(atmIdFlag)) {
					l7 = new Label(5, 2 + i, addr);

				} else {
					l7 = new Label(5, 2 + i, "");
				}

				l1.setCellFormat(cellFormat1);
				// l2.setCellFormat(cellFormat1);
				l3.setCellFormat(cellFormat1);
				l4.setCellFormat(cellFormat1);
				l5.setCellFormat(cellFormat1);
				l6.setCellFormat(cellFormat1);
				l7.setCellFormat(cellFormat1);

				sheet.addCell(l1);
				// sheet.addCell(l2);
				sheet.addCell(l3);
				sheet.addCell(l4);
				sheet.addCell(l5);
				sheet.addCell(l6);
				sheet.addCell(l7);
				atmIdFlag = atmId;
				addrFlag = addr;
			}
			book.write();
			book.close();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}
	

	
	public static void writeAtmInfoExcel(File file, String title,
			List<Atm> atmInfoList) {
		try {
			// 打开文件
			WritableWorkbook book = Workbook.createWorkbook(file);
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("第一页", 0);
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			// 以及单元格内容为test

			/** 设置Execl */
			WritableFont fontNormal = new WritableFont(WritableFont.ARIAL, 11);
			WritableCellFormat cellFormat = new WritableCellFormat(fontNormal);
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
			cellFormat
					.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			cellFormat.setWrap(true);

			sheet.mergeCells(3, 0, 5, 0);
			Label label = new Label(3, 0, title);
			label.setCellFormat(cellFormat);
			// 将定义好的单元格添加到工作表中
			sheet.addCell(label);
			// sheet.mergeCells(3,0,4,0);
			/*
			 * 生成一个保存数字的单元格 必须使用Number的完整包路径，否则有语法歧义 单元格位置是第二列，第一行，值为789.123
			 */
			WritableCellFormat cellFormat1 = new WritableCellFormat();
			cellFormat1.setAlignment(jxl.format.Alignment.CENTRE);
			Label label1 = new Label(3, 1, "厂商名称");
			label1.setCellFormat(cellFormat1);
			sheet.addCell(label1);
			// Label label2 = new Label(4, 1, "机器状态");
			// label2.setCellFormat(cellFormat1);
			// sheet.addCell(label2);
			Label label3 = new Label(4, 1, "数量");
			label3.setCellFormat(cellFormat1);
			sheet.addCell(label3);

			for (int i = 0; i < atmInfoList.size(); i++) {
				Label l1 = new Label(3, 2 + i, atmInfoList.get(i).getCompany());
				// Label l2 = new Label(4, 2 + i, String.valueOf(kasList.get(i)
				// .getDemstatus()));
				Label l3 = new Label(4, 2 + i, atmInfoList.get(i)
						.getAllstatus());

				l1.setCellFormat(cellFormat1);
				// l2.setCellFormat(cellFormat1);
				l3.setCellFormat(cellFormat1);

				sheet.addCell(l1);
				// sheet.addCell(l2);
				sheet.addCell(l3);

			}

			// 写入数据并关闭文件
			book.write();
			book.close();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	public static void writeProExcel(File file, String title, List<Draw> list) {
		WritableWorkbook book = null;
		try {
			// 打开文件
			book = Workbook.createWorkbook(file);
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("第一页", 0);
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			// 以及单元格内容为test

			/** 设置Execl */
			WritableFont fontNormal = new WritableFont(WritableFont.ARIAL, 11);
			WritableCellFormat cellFormat = new WritableCellFormat(fontNormal);
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
			cellFormat
					.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			cellFormat.setWrap(true);

			sheet.mergeCells(3, 0, 4, 0);
			Label label = new Label(3, 0, title);
			label.setCellFormat(cellFormat);
			// 将定义好的单元格添加到工作表中
			sheet.addCell(label);
			// sheet.mergeCells(3,0,4,0);
			/*
			 * 生成一个保存数字的单元格 必须使用Number的完整包路径，否则有语法歧义 单元格位置是第二列，第一行，值为789.123
			 */
			WritableCellFormat cellFormat2 = new WritableCellFormat();
			cellFormat2.setAlignment(jxl.format.Alignment.CENTRE);
			Label label1 = new Label(3, 1, "设备编号");
			label1.setCellFormat(cellFormat2);
			sheet.addCell(label1);
			Label label2 = new Label(4, 1, "总收益(元)");
			label2.setCellFormat(cellFormat2);
			sheet.addCell(label2);
			Label label3 = new Label(5, 1, "所在地");
			label3.setCellFormat(cellFormat2);
			sheet.addCell(label3);

			// //数据居右显示
			WritableCellFormat cellFormat1 = new WritableCellFormat();
			cellFormat1.setAlignment(jxl.format.Alignment.RIGHT);
			for (int i = 0; i < list.size(); i++) {
				Label l1 = new Label(3, 2 + i, list.get(i).getAtmid());
				Label l2 = new Label(4, 2 + i, String.valueOf(list.get(i)
						.getCount() * 3));
				Label l3 = new Label(5, 2 + i, list.get(i).getAddr());
				l1.setCellFormat(cellFormat1);
				l2.setCellFormat(cellFormat1);
				l3.setCellFormat(cellFormat2);
				sheet.addCell(l1);
				sheet.addCell(l2);
				sheet.addCell(l3);
			}

			// 写入数据并关闭文件
			book.write();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		} finally {
			// 写入数据并关闭文件
			try {
				if (book != null) {
					book.close();
				}
			} catch (IOException e) {
				logger.error(e.getLocalizedMessage(), e);
			} catch (WriteException e) {
				logger.error(e.getLocalizedMessage(), e);
			}

		}
	}

	public static void writeKJLExcel(File file, String title, List<Draw> list) {
		WritableWorkbook book = null;
		try {
			// 打开文件
			book = Workbook.createWorkbook(file);
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("开机率", 0);
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			// 以及单元格内容为test

			/** 设置Execl */
			WritableFont fontNormal = new WritableFont(WritableFont.ARIAL, 11);
			WritableCellFormat cellFormat = new WritableCellFormat(fontNormal);
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
			cellFormat
					.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			cellFormat.setWrap(true);

			sheet.mergeCells(3, 0, 4, 0);
			Label label = new Label(3, 0, title);
			label.setCellFormat(cellFormat);
			// 将定义好的单元格添加到工作表中
			sheet.addCell(label);
			// sheet.mergeCells(3,0,4,0);
			/*
			 * 生成一个保存数字的单元格 必须使用Number的完整包路径，否则有语法歧义 单元格位置是第二列，第一行，值为789.123
			 */
			WritableCellFormat cellFormat2 = new WritableCellFormat();
			cellFormat2.setAlignment(jxl.format.Alignment.CENTRE);
			Label label1 = new Label(3, 1, "设备编号");
			label1.setCellFormat(cellFormat2);
			sheet.addCell(label1);
			Label label2 = new Label(4, 1, "开机率");
			label2.setCellFormat(cellFormat2);
			sheet.addCell(label2);
			Label label3 = new Label(5, 1, "所在地");
			label3.setCellFormat(cellFormat2);
			sheet.addCell(label3);

			// //数据居右显示
			WritableCellFormat cellFormat1 = new WritableCellFormat();
			cellFormat1.setAlignment(jxl.format.Alignment.RIGHT);
			for (int i = 0; i < list.size(); i++) {
				Label l1 = new Label(3, 2 + i, list.get(i).getAtmid());
				Label l2 = new Label(4, 2 + i, String.valueOf(list.get(i)
						.getCount() * 3));
				Label l3 = new Label(5, 2 + i, list.get(i).getAddr());
				l1.setCellFormat(cellFormat1);
				l2.setCellFormat(cellFormat1);
				l3.setCellFormat(cellFormat2);
				sheet.addCell(l1);
				sheet.addCell(l2);
				sheet.addCell(l3);
			}

			// 写入数据并关闭文件
			book.write();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		} finally {
			// 写入数据并关闭文件
			try {
				if (book != null) {
					book.close();
				}
			} catch (IOException e) {
				logger.error(e.getLocalizedMessage(), e);
			} catch (WriteException e) {
				logger.error(e.getLocalizedMessage(), e);
			}

		}
	}
	
	public static void writeAllTradeExcel(File file, String title,
			List<String[]> list) {
		WritableWorkbook book = null;
		try {
			// 打开文件
			book = Workbook.createWorkbook(file);
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("第一页", 0);
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			// 以及单元格内容为test

			/** 设置Execl */
			WritableFont fontNormal = new WritableFont(WritableFont.ARIAL, 11);
			WritableCellFormat cellFormat = new WritableCellFormat(fontNormal);
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
			cellFormat
					.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			cellFormat.setWrap(true);

			sheet.mergeCells(0, 0, 12, 0);
			Label label = new Label(0, 0, title);
			label.setCellFormat(cellFormat);
			// 将定义好的单元格添加到工作表中
			sheet.addCell(label);
			// sheet.mergeCells(3,0,4,0);
			/*
			 * 生成一个保存数字的单元格 必须使用Number的完整包路径，否则有语法歧义 单元格位置是第二列，第一行，值为789.123
			 */
			WritableCellFormat cellFormat2 = new WritableCellFormat();
			cellFormat2.setAlignment(jxl.format.Alignment.CENTRE);
			cellFormat2.setBackground(Colour.GRAY_50);
			Label label1 = new Label(0, 1, "设备编号");
			label1.setCellFormat(cellFormat2);
			sheet.addCell(label1);
			Label label2 = new Label(1, 1, "所属机构");
			label2.setCellFormat(cellFormat2);
			sheet.addCell(label2);
			Label label3 = new Label(2, 1, "总交易比数");
			label3.setCellFormat(cellFormat2);
			sheet.addCell(label3);

			Label label4 = new Label(3, 1, "总交易额");
			label4.setCellFormat(cellFormat2);
			sheet.addCell(label4);
			Label label5 = new Label(4, 1, "存款比数");
			label5.setCellFormat(cellFormat2);
			sheet.addCell(label5);
			Label label6 = new Label(5, 1, "存款额");
			label6.setCellFormat(cellFormat2);
			sheet.addCell(label6);
			Label label7 = new Label(6, 1, "取款比数");
			label7.setCellFormat(cellFormat2);
			sheet.addCell(label7);
			Label label8 = new Label(7, 1, "取款额");
			label8.setCellFormat(cellFormat2);
			sheet.addCell(label8);
			Label label9 = new Label(8, 1, "转账比数");
			label9.setCellFormat(cellFormat2);
			sheet.addCell(label9);
			Label label10 = new Label(9, 1, "转账额");
			label10.setCellFormat(cellFormat2);
			sheet.addCell(label10);
			Label label11 = new Label(10, 1, "本代他取款比数");
			label11.setCellFormat(cellFormat2);
			sheet.addCell(label11);
			Label label12 = new Label(11, 1, "本代他取款额");
			label12.setCellFormat(cellFormat2);
			sheet.addCell(label12);
			Label label13 = new Label(12, 1, "本代他手续费");
			label13.setCellFormat(cellFormat2);
			sheet.addCell(label13);

			// //数据居右显示
			WritableCellFormat cellFormat1 = new WritableCellFormat();
			cellFormat1.setAlignment(jxl.format.Alignment.RIGHT);
			int t = list.size(), a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0, h = 0, j = 0, k = 0, m = 0;
			for (int i = 0; i < list.size(); i++) {
				String a1Str = list.get(i)[2];
				String b1Str = list.get(i)[3];
				String c1Str = list.get(i)[4];
				String d1Str = list.get(i)[5];
				String e1Str = list.get(i)[6];
				String f1Str = list.get(i)[7];
				String g1Str = list.get(i)[8];
				String h1Str = list.get(i)[9];
				String j1Str = list.get(i)[10];
				String k1Str = list.get(i)[11];
				String m1Str = list.get(i)[12];

				float a1 = Float.parseFloat(a1Str == null ? "0" : a1Str
						.equals("") ? "0" : a1Str);
				float b1 = Float.parseFloat(b1Str == null ? "0" : b1Str
						.equals("") ? "0" : b1Str);
				float c1 = Float.parseFloat(c1Str == null ? "0" : c1Str
						.equals("") ? "0" : a1Str);
				float d1 = Float.parseFloat(d1Str == null ? "0" : d1Str
						.equals("") ? "0" : d1Str);
				float e1 = Float.parseFloat(e1Str == null ? "0" : e1Str
						.equals("") ? "0" : e1Str);
				float f1 = Float.parseFloat(f1Str == null ? "0" : f1Str
						.equals("") ? "0" : f1Str);
				float g1 = Float.parseFloat(g1Str == null ? "0" : g1Str
						.equals("") ? "0" : g1Str);
				float h1 = Float.parseFloat(h1Str == null ? "0" : h1Str
						.equals("") ? "0" : h1Str);
				float j1 = Float.parseFloat(j1Str == null ? "0" : j1Str
						.equals("") ? "0" : j1Str);
				float k1 = Float.parseFloat(k1Str == null ? "0" : k1Str
						.equals("") ? "0" : k1Str);
				float m1 = Float.parseFloat(m1Str == null ? "0" : m1Str
						.equals("") ? "0" : m1Str);

				Label l1 = new Label(0, 2 + i, list.get(i)[0]);
				Label l2 = new Label(1, 2 + i, list.get(i)[1]);
				Label l3 = new Label(2, 2 + i, a1Str);
				Label l4 = new Label(3, 2 + i, b1Str);
				Label l5 = new Label(4, 2 + i, c1Str);
				Label l6 = new Label(5, 2 + i, d1Str);
				Label l7 = new Label(6, 2 + i, e1Str);
				Label l8 = new Label(7, 2 + i, f1Str);
				Label l9 = new Label(8, 2 + i, g1Str);
				Label l10 = new Label(9, 2 + i, h1Str);
				Label l11 = new Label(10, 2 + i, j1Str);
				Label l12 = new Label(11, 2 + i, k1Str);
				Label l13 = new Label(12, 2 + i, m1Str);

				l1.setCellFormat(cellFormat1);
				l2.setCellFormat(cellFormat1);
				l3.setCellFormat(cellFormat1);
				l4.setCellFormat(cellFormat1);
				l5.setCellFormat(cellFormat1);
				l6.setCellFormat(cellFormat1);
				l7.setCellFormat(cellFormat1);
				l8.setCellFormat(cellFormat1);
				l9.setCellFormat(cellFormat1);
				l10.setCellFormat(cellFormat1);
				l11.setCellFormat(cellFormat1);
				l12.setCellFormat(cellFormat1);
				l13.setCellFormat(cellFormat1);

				sheet.addCell(l1);
				sheet.addCell(l2);
				sheet.addCell(l3);
				sheet.addCell(l4);
				sheet.addCell(l5);
				sheet.addCell(l6);
				sheet.addCell(l7);
				sheet.addCell(l8);
				sheet.addCell(l9);
				sheet.addCell(l10);
				sheet.addCell(l11);
				sheet.addCell(l12);
				sheet.addCell(l13);

				a += a1;
				b += b1;
				c += c1;
				d += d1;
				e += e1;
				f += f1;
				g += g1;
				h += h1;
				j += j1;
				k += k1;
				m += m1;
			}

			Label l14 = new Label(1, 2 + t, "总计");
			Label l15 = new Label(2, 2 + t, String.valueOf(a));
			Label l16 = new Label(3, 2 + t, String.valueOf(b));
			Label l17 = new Label(4, 2 + t, String.valueOf(c));
			Label l18 = new Label(5, 2 + t, String.valueOf(d));
			Label l19 = new Label(6, 2 + t, String.valueOf(e));
			Label l20 = new Label(7, 2 + t, String.valueOf(f));
			Label l21 = new Label(8, 2 + t, String.valueOf(g));
			Label l22 = new Label(9, 2 + t, String.valueOf(h));
			Label l23 = new Label(10, 2 + t, String.valueOf(j));
			Label l24 = new Label(11, 2 + t, String.valueOf(k));
			Label l25 = new Label(12, 2 + t, String.valueOf(m));

			l14.setCellFormat(cellFormat2);
			l15.setCellFormat(cellFormat1);
			l16.setCellFormat(cellFormat2);
			l17.setCellFormat(cellFormat1);
			l18.setCellFormat(cellFormat2);
			l19.setCellFormat(cellFormat2);
			l20.setCellFormat(cellFormat1);
			l21.setCellFormat(cellFormat2);
			l22.setCellFormat(cellFormat1);
			l23.setCellFormat(cellFormat2);
			l24.setCellFormat(cellFormat2);
			l25.setCellFormat(cellFormat1);

			sheet.addCell(l14);
			sheet.addCell(l15);
			sheet.addCell(l16);
			sheet.addCell(l17);
			sheet.addCell(l18);
			sheet.addCell(l19);
			sheet.addCell(l20);
			sheet.addCell(l21);
			sheet.addCell(l22);
			sheet.addCell(l23);
			sheet.addCell(l24);
			sheet.addCell(l25);

			// 写入数据并关闭文件
			book.write();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		} finally {
			// 写入数据并关闭文件
			try {
				if (book != null) {
					book.close();
				}
			} catch (IOException e) {
				logger.error(e.getLocalizedMessage(), e);
			} catch (WriteException e) {
				logger.error(e.getLocalizedMessage(), e);
			}

		}
	}

	public static void writeStockExcel(File file, String title, List<Atm> list) {
		WritableWorkbook book = null;
		try {
			// 打开文件
			book = Workbook.createWorkbook(file);
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("第一页", 0);
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			// 以及单元格内容为test

			/** 设置Execl */
			WritableFont fontNormal = new WritableFont(WritableFont.ARIAL, 11);
			WritableCellFormat cellFormat = new WritableCellFormat(fontNormal);
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
			cellFormat
					.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			cellFormat.setWrap(true);

			sheet.mergeCells(3, 0, 5, 0);
			Label label = new Label(3, 0, title);
			label.setCellFormat(cellFormat);
			// 将定义好的单元格添加到工作表中
			sheet.addCell(label);
			// sheet.mergeCells(3,0,4,0);
			WritableCellFormat cellFormat2 = new WritableCellFormat();
			cellFormat2.setAlignment(jxl.format.Alignment.CENTRE);
			/*
			 * 生成一个保存数字的单元格 必须使用Number的完整包路径，否则有语法歧义 单元格位置是第二列，第一行，值为789.123
			 */
			Label label1 = new Label(3, 1, "设备编号");
			label1.setCellFormat(cellFormat2);
			sheet.addCell(label1);
			Label label2 = new Label(4, 1, "取款箱余额");
			label2.setCellFormat(cellFormat2);
			sheet.addCell(label2);
			Label label3 = new Label(5, 1, "存款箱余额");
			label3.setCellFormat(cellFormat2);
			sheet.addCell(label3);

			// 数据居右显示
			WritableCellFormat cellFormat1 = new WritableCellFormat();
			cellFormat1.setAlignment(jxl.format.Alignment.RIGHT);

			for (int i = 0; i < list.size(); i++) {
				Label l1 = new Label(3, 2 + i, list.get(i).getAtmid());
				Label l2 = new Label(4, 2 + i, list.get(i).getPartrmb() + "00");
				Label l3 = new Label(5, 2 + i, list.get(i).getDepormb() + "00");
				l1.setCellFormat(cellFormat2);
				l2.setCellFormat(cellFormat1);
				l3.setCellFormat(cellFormat2);
				sheet.addCell(l1);
				sheet.addCell(l2);
				sheet.addCell(l3);
			}
			book.write();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		} finally {
			// 写入数据并关闭文件
			try {
				if (book != null) {
					book.close();
				}
			} catch (IOException e) {
				logger.error(e.getLocalizedMessage(), e);
			} catch (WriteException e) {
				logger.error(e.getLocalizedMessage(), e);
			}

		}
	}

	public static void writeAddMoneyExcel(File file, String title,
			List<Atm> list) {
		WritableWorkbook book = null;
		try {
			// 打开文件
			book = Workbook.createWorkbook(file);
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("第一页", 0);
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			// 以及单元格内容为test

			/** 设置Execl */
			WritableFont fontNormal = new WritableFont(WritableFont.ARIAL, 11);
			WritableCellFormat cellFormat = new WritableCellFormat(fontNormal);
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
			cellFormat
					.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			cellFormat.setWrap(true);

			sheet.mergeCells(3, 0, 5, 0);
			Label label = new Label(3, 0, title);
			label.setCellFormat(cellFormat);
			// 将定义好的单元格添加到工作表中
			sheet.addCell(label);
			// sheet.mergeCells(3,0,4,0);
			WritableCellFormat cellFormat2 = new WritableCellFormat();
			cellFormat2.setAlignment(jxl.format.Alignment.CENTRE);
			/*
			 * 生成一个保存数字的单元格 必须使用Number的完整包路径，否则有语法歧义 单元格位置是第二列，第一行，值为789.123
			 */
			Label label1 = new Label(3, 1, "设备编号");
			label1.setCellFormat(cellFormat2);
			sheet.addCell(label1);
			Label label2 = new Label(4, 1, "建议加钞(元)");
			label2.setCellFormat(cellFormat2);
			sheet.addCell(label2);
			Label label3 = new Label(5, 1, "所在地");
			label3.setCellFormat(cellFormat2);
			sheet.addCell(label3);

			// 数据居右显示
			WritableCellFormat cellFormat1 = new WritableCellFormat();
			cellFormat1.setAlignment(jxl.format.Alignment.RIGHT);

			for (int i = 0; i < list.size(); i++) {
				Label l1 = new Label(3, 2 + i, list.get(i).getAtmid());
				Label l2 = new Label(4, 2 + i, list.get(i).getTradetime());
				Label l3 = new Label(5, 2 + i, list.get(i).getAddr());
				l1.setCellFormat(cellFormat2);
				l2.setCellFormat(cellFormat1);
				l3.setCellFormat(cellFormat2);
				sheet.addCell(l1);
				sheet.addCell(l2);
				sheet.addCell(l3);
			}
			book.write();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		} finally {
			// 写入数据并关闭文件
			try {
				if (book != null) {
					book.close();
				}
			} catch (IOException e) {
				logger.error(e.getLocalizedMessage(), e);
			} catch (WriteException e) {
				logger.error(e.getLocalizedMessage(), e);
			}

		}
	}

	public static void writeCheckExcel(File file, HashMap<String, List<Ejr>> map) {
		WritableWorkbook book = null;
		try {
			// 打开文件
			book = Workbook.createWorkbook(file);
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("第一页", 0);
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			// 以及单元格内容为test

			/** 设置Execl */
			sheet.setColumnView(0, 30);// 设置第一列的宽度为4
			sheet.setColumnView(1, 20);// 设置第二列的宽度为23
			sheet.setColumnView(2, 20);// 设置第二列的宽度为23
			sheet.setColumnView(3, 20);// 设置第二列的宽度为23
			sheet.setColumnView(4, 20);// 设置第二列的宽度为23
			sheet.setColumnView(5, 20);// 设置第二列的宽度为23
			sheet.setColumnView(6, 20);// 设置第二列的宽度为23
			sheet.setColumnView(7, 20);// 设置第二列的宽度为23
			sheet.setColumnView(8, 20);// 设置第二列的宽度为23
			WritableFont fontNormal = new WritableFont(WritableFont.ARIAL, 11);
			WritableCellFormat cellFormat = new WritableCellFormat(fontNormal);
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
			cellFormat
					.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			cellFormat.setWrap(true);
			// cellFormat.setBorder(jxl.format.Border.ALL,
			// jxl.format.BorderLineStyle.THICK);
			WritableCellFormat cellFormat1 = new WritableCellFormat();
			WritableCellFormat cellFormat2 = new WritableCellFormat();
			WritableCellFormat cellFormat3 = new WritableCellFormat();
			cellFormat1.setAlignment(jxl.format.Alignment.RIGHT);
			// cellFormat1.setBorder(jxl.format.Border.ALL,
			// jxl.format.BorderLineStyle.THIN);
			cellFormat2.setAlignment(jxl.format.Alignment.CENTRE);
			// cellFormat2.setBorder(jxl.format.Border.ALL,
			// jxl.format.BorderLineStyle.THIN);
			cellFormat3.setAlignment(jxl.format.Alignment.CENTRE);
			// cellFormat3.setBorder(jxl.format.Border.ALL,
			// jxl.format.BorderLineStyle.THIN);
			cellFormat3.setBackground(Colour.LIGHT_BLUE);

			sheet.mergeCells(0, 0, 8, 2);
			Label label = new Label(0, 0, "银行ATM对账清单");
			label.setCellFormat(cellFormat);
			// 将定义好的单元格添加到工作表中
			sheet.addCell(label);
			// // sheet.mergeCells(3,0,4,0);

			Label labelTime = new Label(7, 3, "对账日期：");
			Label timeLabel = new Label(8, 3, MyTime
					.getTime("yyyy-MM-dd HH:mm:ss"));
			labelTime.setCellFormat(cellFormat1);
			timeLabel.setCellFormat(cellFormat2);
			sheet.addCell(labelTime);
			sheet.addCell(timeLabel);
			/*
			 * 生成一个保存数字的单元格 必须使用Number的完整包路径，否则有语法歧义 单元格位置是第二列，第一行，值为789.123
			 */
			Label label1 = new Label(0, 4, "设备编号");
			label1.setCellFormat(cellFormat2);
			sheet.addCell(label1);
			Label label2 = new Label(1, 4, "卡号");
			label2.setCellFormat(cellFormat2);
			sheet.addCell(label2);
			Label label3 = new Label(2, 4, "用户输入金额");
			label3.setCellFormat(cellFormat2);
			sheet.addCell(label3);

			Label label4 = new Label(3, 4, "钞箱出钞情况");
			label4.setCellFormat(cellFormat2);
			sheet.addCell(label4);
			Label label5 = new Label(4, 4, "回收张数");
			label5.setCellFormat(cellFormat2);
			sheet.addCell(label5);
			Label label6 = new Label(5, 4, "ATM端交易金额");
			label6.setCellFormat(cellFormat2);
			sheet.addCell(label6);
			Label label7 = new Label(6, 4, "ATM端交易金额");
			label7.setCellFormat(cellFormat2);
			sheet.addCell(label7);
			Label label8 = new Label(7, 4, "后台记账金额");
			label8.setCellFormat(cellFormat2);
			sheet.addCell(label8);
			Label label9 = new Label(8, 4, "后台记账时间");
			label9.setCellFormat(cellFormat2);
			sheet.addCell(label9);

			// 数据居右显示

			int i = 5, j = 0;
			for (Iterator iter = map.entrySet().iterator(); iter.hasNext();) {
				Map.Entry element = (Map.Entry) iter.next();
				String strKey = String.valueOf(element.getKey());
				logger.info(strKey);
				List<Ejr> strObj = (List<Ejr>) element.getValue();
				// sheet.mergeCells(0, i + j, 0, strObj.size());
				if (strObj != null && !strObj.equals("")) {
					Label l1 = new Label(0, i + j, strKey.split(",")[0]);
					for (Ejr ejr : strObj) {
						Label l2 = new Label(1, i + j, ejr.getCardNo());
						Label l3 = new Label(2, i + j, String.valueOf(ejr
								.getInput()));
						Label l4 = new Label(3, i + j, ejr.getDispense());
						Label l5 = new Label(4, i + j, String.valueOf(ejr
								.getReject()));
						Label l6 = new Label(5, i + j, String.valueOf(ejr
								.getTradermb()));
						Label l7 = new Label(6, i + j, String.valueOf(ejr
								.getTradetime()));
						Label l8 = new Label(7, i + j, String.valueOf(ejr
								.getTradermb_db()));
						Label l9 = new Label(8, i + j, String.valueOf(ejr
								.getTradetime_db()));
						l2.setCellFormat(cellFormat2);
						l3.setCellFormat(cellFormat1);
						l4.setCellFormat(cellFormat2);
						l5.setCellFormat(cellFormat2);
						l6.setCellFormat(cellFormat1);
						l7.setCellFormat(cellFormat1);
						l8.setCellFormat(cellFormat1);
						l9.setCellFormat(cellFormat1);
						sheet.addCell(l2);
						sheet.addCell(l3);
						sheet.addCell(l4);
						sheet.addCell(l5);
						sheet.addCell(l6);
						sheet.addCell(l7);
						sheet.addCell(l8);
						sheet.addCell(l9);
						j++;
					}
					l1.setCellFormat(cellFormat2);
					sheet.addCell(l1);

					Label lab_all = new Label(2, i + j, "小计：");
					lab_all.setCellFormat(cellFormat3);

					Label lab_allRmb_c = new Label(3, i + j, "C端出钞总额：");
					Label allRmb_c = new Label(4, i + j, strKey.split(",")[1]);
					lab_allRmb_c.setCellFormat(cellFormat3);
					allRmb_c.setCellFormat(cellFormat3);

					Label lab_allRmb_p = new Label(5, i + j, "P端出钞总额：");
					Label allRmb_p = new Label(6, i + j, strKey.split(",")[2]);
					lab_allRmb_p.setCellFormat(cellFormat3);
					allRmb_p.setCellFormat(cellFormat3);

					Label lab_Rmb_sub = new Label(7, i + j, "差额：");
					Label Rmb_sub = new Label(8, i + j, strKey.split(",")[3]);
					lab_Rmb_sub.setCellFormat(cellFormat3);
					Rmb_sub.setCellFormat(cellFormat3);

					sheet.addCell(lab_allRmb_c);
					sheet.addCell(allRmb_c);
					sheet.addCell(lab_allRmb_p);
					sheet.addCell(allRmb_p);
					sheet.addCell(lab_Rmb_sub);
					sheet.addCell(Rmb_sub);
				} else {
					sheet.mergeCells(0, i + j, 8, 1);
					Label label_error = new Label(0, i + j, strKey);
					label_error.setCellFormat(cellFormat3);
					// 将定义好的单元格添加到工作表中
					sheet.addCell(label_error);
				}
				j++;
				i++;

			}
			book.write();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		} finally {
			// 写入数据并关闭文件
			try {
				if (book != null) {
					book.close();
				}
			} catch (IOException e) {
				logger.error(e.getLocalizedMessage(), e);
			} catch (WriteException e) {
				logger.error(e.getLocalizedMessage(), e);
			}

		}
	}

	public static void writeAtmStateInfoExcel(File file, String title,
			List<Atm> atmInfoList) {
		try {
			// 打开文件
			WritableWorkbook book = Workbook.createWorkbook(file);
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("第一页", 0);
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			// 以及单元格内容为test

			/** 设置Execl */
			WritableFont fontNormal = new WritableFont(WritableFont.ARIAL, 11);
			WritableCellFormat cellFormat = new WritableCellFormat(fontNormal);
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
			cellFormat
					.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			cellFormat.setWrap(true);

			sheet.mergeCells(3, 0, 12, 0);
			Label label = new Label(3, 0, title);
			label.setCellFormat(cellFormat);
			// 将定义好的单元格添加到工作表中
			sheet.addCell(label);
			// sheet.mergeCells(3,0,4,0);
			/*
			 * 生成一个保存数字的单元格 必须使用Number的完整包路径，否则有语法歧义 单元格位置是第二列，第一行，值为789.123
			 */
			WritableCellFormat cellFormat1 = new WritableCellFormat();
			cellFormat1.setAlignment(jxl.format.Alignment.CENTRE);
			Label label1 = new Label(3, 1, "ATM号");
			label1.setCellFormat(cellFormat1);
			sheet.addCell(label1);

			Label label2 = new Label(4, 1, "机器状态");
			label2.setCellFormat(cellFormat1);
			sheet.addCell(label2);

			Label label3 = new Label(5, 1, "流水打印机状态");
			label3.setCellFormat(cellFormat1);
			sheet.addCell(label3);

			Label label4 = new Label(6, 1, "凭条打印机状态");
			label4.setCellFormat(cellFormat1);
			sheet.addCell(label4);

			Label label5 = new Label(7, 1, "出钞器状态");
			label5.setCellFormat(cellFormat1);
			sheet.addCell(label5);

			Label label6 = new Label(8, 1, "存款模块状态");
			label6.setCellFormat(cellFormat1);
			sheet.addCell(label6);

			Label label7 = new Label(9, 1, "读卡器状态");
			label7.setCellFormat(cellFormat1);
			sheet.addCell(label7);

			Label label8 = new Label(10, 1, "线路状态");
			label8.setCellFormat(cellFormat1);
			sheet.addCell(label8);

			Label label9 = new Label(11, 1, "取款箱张数");
			label9.setCellFormat(cellFormat1);
			sheet.addCell(label9);

			Label label10 = new Label(12, 1, "所在场所");
			label10.setCellFormat(cellFormat1);
			sheet.addCell(label10);

			for (int i = 0; i < atmInfoList.size(); i++) {
				Label l1 = new Label(3, 2 + i, atmInfoList.get(i).getAtmid());
				String dem = atmInfoList.get(i).getDemstatus();
				Label l2 = new Label(4, 2 + i, dem.equals("0") ? "正常" : dem
						.equals("1") ? "异常" : dem.equals("2") ? "维护状态" : dem
						.equals("5") ? "柜员操作" : "未知");
				String prj = atmInfoList.get(i).getPrjstatus();
				Label l3 = new Label(5, 2 + i, prj.equals("0") ? "正常" : prj
						.equals("2") ? "纸少" : prj.equals("3") ? "纸尽" : prj
						.equals("4") ? "异常" : "未知");
				String prr = atmInfoList.get(i).getPrrstatus();
				Label l4 = new Label(6, 2 + i, prr.equals("0") ? "正常" : prr
						.equals("2") ? "纸少" : prr.equals("3") ? "纸尽" : prr
						.equals("4") ? "异常" : "未知");
				String cdm = atmInfoList.get(i).getCdmstatus();
				Label l5 = new Label(7, 2 + i, cdm.equals("0") ? "正常" : cdm
						.equals("1") ? "异常" : "未知");
				String dep = atmInfoList.get(i).getDepstatus();
				Label l6 = new Label(8, 2 + i, dep.equals("0") ? "正常" : dep
						.equals("1") ? "异常" : dep.equals("-") ? "-" : "未知");
				String rea = atmInfoList.get(i).getReaderstatus();
				Label l7 = new Label(9, 2 + i, rea.equals("0") ? "正常" : rea
						.equals("1") ? "异常" : "未知");
				String lin = atmInfoList.get(i).getLinestatus();
				Label l8 = new Label(10, 2 + i, lin.equals("0") ? "正常" : lin
						.equals("1") ? "异常" : "未知");

				Label l9 = new Label(11, 2 + i, atmInfoList.get(i).getPartrmb());

				Label l10 = new Label(12, 2 + i, atmInfoList.get(i).getAddr());

				l1.setCellFormat(cellFormat1);
				l2.setCellFormat(cellFormat1);
				l3.setCellFormat(cellFormat1);
				l4.setCellFormat(cellFormat1);
				l5.setCellFormat(cellFormat1);
				l6.setCellFormat(cellFormat1);
				l7.setCellFormat(cellFormat1);
				l8.setCellFormat(cellFormat1);
				l9.setCellFormat(cellFormat1);
				l10.setCellFormat(cellFormat1);

				sheet.addCell(l1);
				sheet.addCell(l2);
				sheet.addCell(l3);
				sheet.addCell(l4);
				sheet.addCell(l5);
				sheet.addCell(l6);
				sheet.addCell(l7);
				sheet.addCell(l8);
				sheet.addCell(l9);
				sheet.addCell(l10);

			}
			book.write();
			book.close();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

}

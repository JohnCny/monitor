//package cn.sh.ae.tools;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.sql.DataSource;
//
//import cn.sh.ae.factory.DaoFactory;
//import cn.sh.ae.inteface.IAtmtradataDao;
//import cn.sh.ae.util.MyFile;
//import cn.sh.ae.util.MyTime;
//import cn.sh.ae.vo.Atmtradata;
//import cn.sh.ae.vo.Ejr;
//
//public class EJRReader {
//
//	/** */
//	/**
//	 * 在指定的文件名中查找对应的字符串，存在就将该文件名绝对路径打印出来
//	 * 
//	 * @param fileName
//	 *            文件名的绝对路径,String型
//	 * @param srcStr
//	 *            要查找的字符串,String型
//	 */
//	public static List<Ejr> searchFile(String fileName, String srcStr1,
//			String srcStr2, String srcStr3) {
//		File file = new File(fileName);
//		List<Ejr> list = null;
//		Ejr ejr = null;
//		try {
//			FileReader fr = new FileReader(file);
//			BufferedReader br = new BufferedReader(fr);
//			String strLine = "";
//			list = new ArrayList<Ejr>();
//			float d = 0, r = 0;
//			while ((strLine = br.readLine()) != null) {
//				if (strLine.indexOf(srcStr1) > 0) {
//					float input = Float.parseFloat(strLine.trim()
//							.split(srcStr1)[1].replace(">", ""));
//					strLine = br.readLine();
//					if (strLine.indexOf(srcStr2) > 0) {
//						ejr = new Ejr();
//						ejr.setInput(input);
//						int size1 = strLine.trim().length();
//						String dispense = strLine.trim().substring(size1 - 12,
//								size1 - 1);
//						ejr.setDispense(dispense);
//						String[] s = dispense.split(",");
//						d = Integer.parseInt(s[0]) + Integer.parseInt(s[1])
//								+ Integer.parseInt(s[2])
//								+ Integer.parseInt(s[3]);
//
//						strLine = br.readLine();
//						if (strLine.indexOf(srcStr3) > 0) {
//							int size2 = strLine.trim().length();
//							int reject = Integer.parseInt(strLine.substring(
//									size2 - 3, size2 - 1));
//							ejr.setReject(reject);
//							r = reject;
//						}
//						float tradermb = (d - r) * 100;
//						d = 0;
//						r = 0;
//						ejr.setTradermb(tradermb);
//					}
//					list.add(ejr);
//				}
//			}
//			br.close();
//			fr.close();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return list;
//	}
//
//	/** */
//	/**
//	 * 在指定的绝对路径中查找指定的字符串
//	 * 
//	 * @param path
//	 *            绝对路径
//	 * @param srcStr
//	 *            要查找的字符串
//	 */
//	public static List<List<Ejr>> searchPath(String path, String srcStr1,
//			String srcStr2, String srcStr3) {
//		File file = new File(path);
//		List<List<Ejr>> list = new ArrayList<List<Ejr>>();
//		if (file.isDirectory()) {
//			File[] fileList = file.listFiles();
//			for (int i = 0; i < fileList.length; i++) {
//				if (fileList[i].isDirectory())// 是目录就调用递归
//				{
//					searchPath(fileList[i].getAbsolutePath(), srcStr1, srcStr2,
//							srcStr3);
//				} else {
//					list.add(searchFile(fileList[i].getAbsolutePath(), srcStr1,
//							srcStr2, srcStr3));
//				}
//			}
//		} else {
//			list.add(searchFile(path, srcStr1, srcStr2, srcStr3));
//		}
//		return list;
//	}
//
//	public static List<List<Ejr>> searchPath(String path) {
//		return searchPath(path + "EJRPACK", "AMOUNT INPUT :",
//				"CASH : DISPENSE", "CASH : REJECT");
//	}
//
//	public static List<Ejr> doCheck(DataSource ds, String atmid,
//			String fileName, byte[] fileData, String time, String sessionid) {
//		// String fileName = myFile.getFileName();
//		String path = MyFile.saveCheckFile(fileName, fileData, sessionid);
//		if (path != null) {
//			RarFile.unRarFile(path, path + fileName);
//			MyFile.deleteFile(path + fileName);
//		}
//		List<Ejr> checkList = new ArrayList<Ejr>();
//		Ejr ejr = null;
//		List<List<Ejr>> list = searchPath(path);
//		IAtmtradataDao atd = DaoFactory.getAtmtradataDao();
//		for (List<Ejr> ejrList : list) {
//			List<Atmtradata> trade = atd.getTradeRmb(ds, atmid, time);
//			int ejrSize = ejrList.size();
//			int tradeSize = trade.size();
//			int size = ejrSize <= tradeSize ? ejrSize : tradeSize;
//			for (int i = 0; i < size; i++) {
//				float a = ejrList.get(i).getTradermb();
//				float b = trade.get(i).getTradermb();
//				if (a != b) {
//					ejr = new Ejr();
//					ejr.setAtmid(trade.get(i).getAtmid());
//					ejr.setYear(trade.get(i).getTradeyear());
//					ejr.setMonth(trade.get(i).getTrademonth());
//					ejr.setDay(trade.get(i).getTradeday());
//					ejr.setHour(trade.get(i).getTradehour());
//					ejr.setInput(ejrList.get(i).getInput());
//					ejr.setDispense(ejrList.get(i).getDispense());
//					ejr.setReject(ejrList.get(i).getReject());
//					ejr.setTradermb(a);
//					ejr.setTradermb_db(b);
//					ejr.setAddr(trade.get(i).getReserve());
//					checkList.add(ejr);
//				}
//			}
//			time = MyTime.getNextDay(time, "yyyy-MM-dd");
//		}
//		MyFile.deleteDirectory(path, false);
//		return checkList;
//	}
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		String path = "C:/EJRPACK";
//		String srcStr1 = "AMOUNT INPUT :";
//		String srcStr2 = "CASH : DISPENSE";
//		String srcStr3 = "CASH : REJECT";
//		// EJRReader s = new EJRReader();
//		EJRReader.searchPath(path, srcStr1, srcStr2, srcStr3);
//		System.out.println("执行完毕！");
//	}
//}

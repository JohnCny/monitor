package cn.sh.ae.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.sh.ae.vo.AtmTradataMoney;
import cn.sh.ae.vo.Atmtradata;

public class LogReader implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7087230622740439818L;
	static Logger logger = Logger.getLogger(LogReader.class.getName());

	public static List<Atmtradata> saveLogToBase(File file) {
		BufferedReader reader = null;
		Atmtradata traData = null;
		List<Atmtradata> list = null;
		try {
			list = new ArrayList<Atmtradata>();
			logger.info("同步文件路径：" + file.getPath());
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			String col = null;
			while ((col = reader.readLine()) != null) {
				String[] cell = col.split(",");
				traData = new Atmtradata();
				traData
						.setTradetime(cell[0]
								.substring(1, cell[0].length() - 1));
				traData
						.setTradehour(cell[1]
								.substring(1, cell[1].length() - 3));
				traData.setCardno(cell[2].substring(1, cell[2].length() - 1));
				traData.setTradermb(Float.parseFloat(cell[3]));
				traData.setCardclass(cell[4].equals("") ? "NULL" : cell[4]
						.substring(1, cell[4].length() - 1));
				traData
						.setTradetype(cell[5]
								.substring(1, cell[5].length() - 1));
				traData.setAtmid(cell[6].substring(1, cell[6].length() - 1));
				list.add(traData);
			}
			reader.close();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		logger.info("数据总数" + list.size());
		return list;

	}

	public static List<AtmTradataMoney> saveLogToBase_1(File file) {
		BufferedReader reader = null;
		AtmTradataMoney traDataMon = null;
		List<AtmTradataMoney> list = null;
		try {
			list = new ArrayList<AtmTradataMoney>();
			logger.info("同步文件路径：" + file.getPath());
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			String col = null;
			while ((col = reader.readLine()) != null) {
				String[] cell = col.split(",");
				traDataMon = new AtmTradataMoney();
				traDataMon.setTradetime(cell[0].substring(1,
						cell[0].length() - 1));
				traDataMon.setAtmid(cell[1].substring(1, cell[1].length() - 1));
				traDataMon.setAtmid_new(cell[2].substring(1,
						cell[2].length() - 1));
				traDataMon.setMoney(Float.parseFloat(cell[3]));
				list.add(traDataMon);
			}
			reader.close();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		logger.info("数据总数" + list.size());
		return list;

	}

	public static void main(String args[]) {
		String a = "312";
		String b = "323";
		System.out.println(a.compareTo(b));
	}
}

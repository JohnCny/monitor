package cn.sh.ae.bank.nc.state;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.sh.ae.manage.DataManager;
import cn.sh.ae.pojo.TradeStatePojo;
import cn.sh.ae.util.MyConstant;
import cn.sh.ae.vo.Atmtradata;

public class TradeState implements Serializable {

	public static void main(String args[]) {

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3241771686557608017L;

	public static double qkzz = 0, ckzz = 0;

	public static List<TradeStatePojo> getTradeInfo() {
		qkzz = 0;
		ckzz = 0;
		List<TradeStatePojo> tspList = new ArrayList<TradeStatePojo>();
		TradeStatePojo tsp = null;
		List<Atmtradata> tradeList = DataManager.getTodayTradeByType("1");
		List<Atmtradata> tradeList2 = DataManager.getTodayTradeByType("2");
		for (Atmtradata trade : tradeList) {
			tsp = new TradeStatePojo();
			String atmId = trade.getAtmid();
			tsp.setId(atmId);
			tsp.setQk(String.valueOf(trade.getTradermb()));
			qkzz += trade.getTradermb();
			for (Atmtradata trade2 : tradeList2) {
				String atmId2 = trade2.getAtmid();
				if (atmId.equals(atmId2)) {
					tsp.setCk(String.valueOf(trade2.getTradermb()));
					ckzz += trade2.getTradermb();
					break;
				} else
					tsp.setCk("0");

			}
			tspList.add(tsp);
		}
		return tspList;
	}

	public static HashMap<String, double[]> getTradeStatelinDatas(String atmId,
			String type) {
		HashMap<String, double[]> map = new HashMap<String, double[]>();

		// 获取当前时间
		// int hour = MyTime.getDate().getHour();
		// double[] qkData = new double[hour + 1];
		// double[] ckData = new double[hour + 1];
		List<Atmtradata> tradeList = DataManager.getTodayTradeById(atmId);
		// 每个小时的取款和存款
		double qk0 = 0, qk1 = 0, qk2 = 0, qk3 = 0, qk4 = 0, qk5 = 0, qk6 = 0, qk7 = 0, qk8 = 0, qk9 = 0, qk10 = 0, qk11 = 0, qk12 = 0, qk13 = 0, qk14 = 0, qk15 = 0, qk16 = 0, qk17 = 0, qk18 = 0, qk19 = 0, qk20 = 0, qk21 = 0, qk22 = 0, qk23 = 0;
		double ck0 = 0, ck1 = 0, ck2 = 0, ck3 = 0, ck4 = 0, ck5 = 0, ck6 = 0, ck7 = 0, ck8 = 0, ck9 = 0, ck10 = 0, ck11 = 0, ck12 = 0, ck13 = 0, ck14 = 0, ck15 = 0, ck16 = 0, ck17 = 0, ck18 = 0, ck19 = 0, ck20 = 0, ck21 = 0, ck22 = 0, ck23 = 0;

		for (Atmtradata trade : tradeList) {
			String tradeType = trade.getTradetype();
			tradeType = tradeType.substring(0, tradeType.length() - 2);
			String tradeHour = trade.getTradehour().substring(0, 2);
			float tradeRmb = trade.getTradermb();
			if (tradeHour.equals("00")) {
				if (tradeType.equals(MyConstant.draw)) {
					if (type.equals("1"))
						qk0 += tradeRmb;
					else
						qk0++;
				} else if (tradeType.equals(MyConstant.deposit)) {
					if (type.equals("1"))
						ck0 += tradeRmb;
					else
						ck0++;
				}
			} else if (tradeHour.equals("01")) {
				if (tradeType.equals(MyConstant.draw)) {
					if (type.equals("1"))
						qk1 += tradeRmb;
					else
						qk1++;
				} else if (tradeType.equals(MyConstant.deposit)) {
					if (type.equals("1"))
						ck1 += tradeRmb;
					else
						ck1++;
				}
			} else if (tradeHour.equals("02")) {
				if (tradeType.equals(MyConstant.draw)) {
					if (type.equals("1"))
						qk2 += tradeRmb;
					else
						qk2++;
				} else if (tradeType.equals(MyConstant.deposit)) {
					if (type.equals("1"))
						ck2 += tradeRmb;
					else
						ck2++;
				}
			} else if (tradeHour.equals("03")) {
				if (tradeType.equals(MyConstant.draw)) {
					if (type.equals("1"))
						qk3 += tradeRmb;
					else
						qk3++;
				} else if (tradeType.equals(MyConstant.deposit)) {
					if (type.equals("1"))
						ck3 += tradeRmb;
					else
						ck3++;
				}
			} else if (tradeHour.equals("04")) {
				if (tradeType.equals(MyConstant.draw)) {
					if (type.equals("1"))
						qk4 += tradeRmb;
					else
						qk4++;
				} else if (tradeType.equals(MyConstant.deposit)) {
					if (type.equals("1"))
						ck4 += tradeRmb;
					else
						ck4++;
				}
			} else if (tradeHour.equals("05")) {
				if (tradeType.equals(MyConstant.draw)) {
					if (type.equals("1"))
						qk5 += tradeRmb;
					else
						qk5++;
				} else if (tradeType.equals(MyConstant.deposit)) {
					if (type.equals("1"))
						ck5 += tradeRmb;
					else
						ck5++;
				}
			} else if (tradeHour.equals("06")) {
				if (tradeType.equals(MyConstant.draw)) {
					if (type.equals("1"))
						qk6 += tradeRmb;
					else
						qk6++;
				} else if (tradeType.equals(MyConstant.deposit)) {
					if (type.equals("1"))
						ck6 += tradeRmb;
					else
						ck6++;
				}
			} else if (tradeHour.equals("07")) {
				if (tradeType.equals(MyConstant.draw)) {
					if (type.equals("1"))
						qk7 += tradeRmb;
					else
						qk7++;
				} else if (tradeType.equals(MyConstant.deposit)) {
					if (type.equals("1"))
						ck7 += tradeRmb;
					else
						ck7++;
				}
			} else if (tradeHour.equals("08")) {
				if (tradeType.equals(MyConstant.draw)) {
					if (type.equals("1"))
						qk8 += tradeRmb;
					else
						qk8++;
				} else if (tradeType.equals(MyConstant.deposit)) {
					if (type.equals("1"))
						ck8 += tradeRmb;
					else
						ck8++;
				}
			} else if (tradeHour.equals("09")) {
				if (tradeType.equals(MyConstant.draw)) {
					if (type.equals("1"))
						qk9 += tradeRmb;
					else
						qk9++;
				} else if (tradeType.equals(MyConstant.deposit)) {
					if (type.equals("1"))
						ck9 += tradeRmb;
					else
						ck9++;
				}
			} else if (tradeHour.equals("10")) {
				if (tradeType.equals(MyConstant.draw)) {
					if (type.equals("1"))
						qk10 += tradeRmb;
					else
						qk10++;
				} else if (tradeType.equals(MyConstant.deposit)) {
					if (type.equals("1"))
						ck10 += tradeRmb;
					else
						ck10++;
				}
			} else if (tradeHour.equals("11")) {
				if (tradeType.equals(MyConstant.draw)) {
					if (type.equals("1"))
						qk11 += tradeRmb;
					else
						qk11++;
				} else if (tradeType.equals(MyConstant.deposit)) {
					if (type.equals("1"))
						ck11 += tradeRmb;
					else
						ck11++;
				}
			} else if (tradeHour.equals("12")) {
				if (tradeType.equals(MyConstant.draw)) {
					if (type.equals("1"))
						qk12 += tradeRmb;
					else
						qk12++;
				} else if (tradeType.equals(MyConstant.deposit)) {
					if (type.equals("1"))
						ck12 += tradeRmb;
					else
						ck12++;
				}
			} else if (tradeHour.equals("13")) {
				if (tradeType.equals(MyConstant.draw)) {
					if (type.equals("1"))
						qk13 += tradeRmb;
					else
						qk13++;
				} else if (tradeType.equals(MyConstant.deposit)) {
					if (type.equals("1"))
						ck13 += tradeRmb;
					else
						ck13++;
				}
			} else if (tradeHour.equals("14")) {
				if (tradeType.equals(MyConstant.draw)) {
					if (type.equals("1"))
						qk14 += tradeRmb;
					else
						qk14++;
				} else if (tradeType.equals(MyConstant.deposit)) {
					if (type.equals("1"))
						ck14 += tradeRmb;
					else
						ck14++;
				}
			} else if (tradeHour.equals("15")) {
				if (tradeType.equals(MyConstant.draw)) {
					if (type.equals("1"))
						qk15 += tradeRmb;
					else
						qk15++;
				} else if (tradeType.equals(MyConstant.deposit)) {
					if (type.equals("1"))
						ck15 += tradeRmb;
					else
						ck15++;
				}
			} else if (tradeHour.equals("16")) {
				if (tradeType.equals(MyConstant.draw)) {
					if (type.equals("1"))
						qk16 += tradeRmb;
					else
						qk16++;
				} else if (tradeType.equals(MyConstant.deposit)) {
					if (type.equals("1"))
						ck16 += tradeRmb;
					else
						ck16++;
				}
			} else if (tradeHour.equals("17")) {
				if (tradeType.equals(MyConstant.draw)) {
					if (type.equals("1"))
						qk17 += tradeRmb;
					else
						qk17++;
				} else if (tradeType.equals(MyConstant.deposit)) {
					if (type.equals("1"))
						ck17 += tradeRmb;
					else
						ck17++;
				}
			} else if (tradeHour.equals("18")) {
				if (tradeType.equals(MyConstant.draw)) {
					if (type.equals("1"))
						qk18 += tradeRmb;
					else
						qk18++;
				} else if (tradeType.equals(MyConstant.deposit)) {
					if (type.equals("1"))
						ck18 += tradeRmb;
					else
						ck18++;
				}
			} else if (tradeHour.equals("19")) {
				if (tradeType.equals(MyConstant.draw)) {
					if (type.equals("1"))
						qk19 += tradeRmb;
					else
						qk19++;
				} else if (tradeType.equals(MyConstant.deposit)) {
					if (type.equals("1"))
						ck19 += tradeRmb;
					else
						ck19++;
				}
			} else if (tradeHour.equals("20")) {
				if (tradeType.equals(MyConstant.draw)) {
					if (type.equals("1"))
						qk20 += tradeRmb;
					else
						qk20++;
				} else if (tradeType.equals(MyConstant.deposit)) {
					if (type.equals("1"))
						ck20 += tradeRmb;
					else
						ck20++;
				}
			} else if (tradeHour.equals("21")) {
				if (tradeType.equals(MyConstant.draw)) {
					if (type.equals("1"))
						qk21 += tradeRmb;
					else
						qk21++;
				} else if (tradeType.equals(MyConstant.deposit)) {
					if (type.equals("1"))
						ck21 += tradeRmb;
					else
						ck21++;
				}
			} else if (tradeHour.equals("22")) {
				if (tradeType.equals(MyConstant.draw)) {
					if (type.equals("1"))
						qk22 += tradeRmb;
					else
						qk22++;
				} else if (tradeType.equals(MyConstant.deposit)) {
					if (type.equals("1"))
						ck22 += tradeRmb;
					else
						ck22++;
				}
			} else if (tradeHour.equals("23")) {
				if (tradeType.equals(MyConstant.draw)) {
					if (type.equals("1"))
						qk23 += tradeRmb;
					else
						qk23++;
				} else if (tradeType.equals(MyConstant.deposit)) {
					if (type.equals("1"))
						ck23 += tradeRmb;
					else
						ck23++;
				}
			}
		}
		double[] qkB = { qk0, qk1, qk2, qk3, qk4, qk5, qk6, qk7, qk8, qk9,
				qk10, qk11, qk12, qk13, qk14, qk15, qk16, qk17, qk18, qk19,
				qk20, qk21, qk22, qk23 };
		double[] ckB = { ck0, ck1, ck2, ck3, ck4, ck5, ck6, ck7, ck8, ck9,
				ck10, ck11, ck12, ck13, ck14, ck15, ck16, ck17, ck18, ck19,
				ck20, ck21, ck22, ck23 };
		// for (int i = 0; i < hour; i++) {
		// qkData[i + 1] = qkB[i + 1];
		// ckData[i + 1] = ckB[i + 1];
		// }
		map.put("qk", qkB);
		map.put("ck", ckB);
		return map;
	}
}

package cn.sh.ae.bank.nc.state;

import java.io.Serializable;
import java.util.List;

import cn.sh.ae.manage.DataManager;
import cn.sh.ae.pojo.AtmStatePojo;
import cn.sh.ae.vo.Atm;

public class AtmState implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1371168599604216410L;

	/***************************************************************************
	 * 设备状态统计（饼图）
	 **************************************************************************/
	public static double[] getAtmStatePieData(int level) {
		int zc = 0, yjgz = 0, ywgz = 0, yj = 0;
		List<Atm> atmList = DataManager.getAtmList(level);
		for (Atm atm : atmList) {
			// 服务状态
			String demstatus = atm.getDemstatus();
			// 平台打印机
			String prjstatus = atm.getPrjstatus();
			// 流水打印机
			String prrstatus = atm.getPrrstatus();
			// 出钞模块
			String cdmstatus = atm.getCdmstatus();
			// 存款模块
			String depstatus = atm.getDepstatus();
			// 读卡器
			String readerstatus = atm.getReaderstatus();
			// 线路
			String linestatus = atm.getLinestatus();
			// 钞箱金额
			String partrmb = atm.getPartrmb();
			if (prjstatus.equals("4") || prrstatus.equals("4")
					|| cdmstatus.equals("4") || depstatus.equals("4")
					|| readerstatus.equals("4") || linestatus.equals("1")) {
				yjgz++;
			} else if (!demstatus.equals("0")
					|| prrstatus.equals("3") || Integer.parseInt(partrmb) == 0) {
				ywgz++;
			} else if (prjstatus.equals("2") && prrstatus.equals("2")
					|| Integer.parseInt(partrmb) < 300) {
				yj++;
			} else
				zc++;

		}
		double[] atmCount = { yj, zc, ywgz, yjgz };
		return atmCount;
	}

	/***************************************************************************
	 * 设备状态统计
	 **************************************************************************/
	public static AtmStatePojo getAllState(int level) {
		List<Atm> atmList = DataManager.getAtmList(level);
		int zc = 0, gz = 0, wh = 0, yj = 0, size = atmList.size();
		AtmStatePojo atmStatePojo = new AtmStatePojo();
		for (Atm atm : atmList) {
			// 服务状态
			String demstatus = atm.getDemstatus();
			// 平台打印机
			String prjstatus = atm.getPrjstatus();
			// 流水打印机
			String prrstatus = atm.getPrrstatus();
			// 出钞模块
			String cdmstatus = atm.getCdmstatus();
			// 存款模块
			String depstatus = atm.getDepstatus();
			// 读卡器
			String readerstatus = atm.getReaderstatus();
			// 线路
			String linestatus = atm.getLinestatus();
			// 钞箱金额
			String partrmb = atm.getPartrmb();
			if (demstatus.equals("0") && prjstatus.equals("0")
					&& prrstatus.equals("0") && cdmstatus.equals("0")
					&& depstatus.equals("0") && readerstatus.equals("0")
					&& linestatus.equals("0")
					&& Integer.parseInt(partrmb) >= 300) {
				zc++;
			} else {
				if (prjstatus.equals("2")
						&& prrstatus.equals("2")
						|| (Integer.parseInt(partrmb) > 0 && Integer
								.parseInt(partrmb) < 300)) {
					yj++;
				} else if (!demstatus.equals("0")) {
					wh++;
				} else {
					gz++;
				}
			}
		}
		atmStatePojo.setGz(String.valueOf(gz));
		atmStatePojo.setWh(String.valueOf(wh));
		atmStatePojo.setYj(String.valueOf(yj));
		atmStatePojo.setZc(String.valueOf(zc));
		// DecimalFormat df1 = new DecimalFormat("##.0%");
		atmStatePojo.setStatePre(((double) (zc + yj) / size) * 100);
		return atmStatePojo;
	}

}

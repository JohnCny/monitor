package cn.sh.ae.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.sh.ae.inteface.IRemoteAtmDao;
import cn.sh.ae.util.MyTime;
import cn.sh.ae.vo.Atm;

/**
 * 
 */
public class RemoteAtmDao implements IRemoteAtmDao {

	private static final long serialVersionUID = 609846680552862009L;

	static Logger logger = Logger.getLogger(RemoteAtmDao.class.getName());

	@Override
	public List<Atm> getAtmpStatus(Connection conn) {
		String remoteSql = "select SBH,CX,JYRQ,BWSJ,DQZT,YBZS,WSZS from djsbjk where SBH like '51%'";
		List<Atm> list = null;
		Atm ksAtm = null;
		// List<List<Object>> table = null;
		try {
			ResultSet remoteRs = null;
			list = new ArrayList<Atm>();
			// if (localAtm != null && !localAtm.isEmpty()) {
			// for (Atm atm : localAtm) {
			// String remoteSqlEnd = " and SBH='" + atm.getAtmid()
			// + "' and (DQZT<>'" + atm.getDemstatus()
			// + atm.getPrjstatus() + atm.getPrrstatus()
			// + atm.getCdmstatus() + atm.getDemstatus()
			// + atm.getReaderstatus() + "' or WSZS<>'"
			// + atm.getDemstatus() + "' or WSZS<>"
			// + atm.getPartrmb() + "')";
			// logger.info(remoteSql + remoteSqlEnd);
			// remoteRs = conn.createStatement().executeQuery(
			// remoteSql + remoteSqlEnd);
			// while (remoteRs.next()) {
			// ksAtm = new Atm();
			// ksAtm.setAtmid(remoteRs.getString("SBH"));
			// ksAtm.setBox(remoteRs.getString("CX"));
			// ksAtm.setTradetime(MyTime.getTime("yy-MM-dd hh:mm:ss"));
			// String dqzt = remoteRs.getString("DQZT");
			// ksAtm.setDemstatus(dqzt.substring(0, 1));
			// ksAtm.setPrjstatus(dqzt.substring(1, 2));
			// ksAtm.setPrrstatus(dqzt.substring(2, 3));
			// ksAtm.setCdmstatus(dqzt.substring(3, 4));
			// ksAtm.setDemstatus(dqzt.substring(4, 5));
			// ksAtm.setReaderstatus(dqzt.substring(5, 6));
			// ksAtm.setDepormb(remoteRs.getString("WSZS"));
			// ksAtm.setPartrmb(remoteRs.getString("YBZS"));
			// list.add(ksAtm);
			// }
			// }
			// } else {
			remoteRs = conn.createStatement().executeQuery(remoteSql);
			while (remoteRs.next()) {
				ksAtm = new Atm();
				ksAtm.setAtmid(remoteRs.getString("SBH").trim());
				ksAtm.setBox(remoteRs.getString("CX").trim());
				ksAtm.setTradetime(MyTime.getTime("yy-MM-dd HH:mm:ss").trim());
				String dqzt = remoteRs.getString("DQZT").trim();
				ksAtm.setDemstatus(dqzt.substring(0, 1));
				ksAtm.setPrjstatus(dqzt.substring(1, 2));
				ksAtm.setPrrstatus(dqzt.substring(2, 3));
				ksAtm.setCdmstatus(dqzt.substring(3, 4));
				ksAtm.setDepstatus(dqzt.substring(4, 5));
				ksAtm.setReaderstatus(dqzt.substring(5, 6));
				ksAtm.setDepormb(remoteRs.getString("WSZS").trim());
				ksAtm.setPartrmb(remoteRs.getString("YBZS").trim());
				list.add(ksAtm);
			}
			// }
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			return null;
		}
		return list;
	}

}

package cn.sh.ae.inteface;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import cn.sh.ae.vo.Atm;
import cn.sh.ae.vo.Atmhistory;

public interface IAtmhistoryDAO extends Serializable {

	public int[] setAtmHistory(Connection conn, List<Atm> list);

	public void setAtmHistory(Connection conn, Atm atm);

	public List<Atmhistory> getAtmBug(Connection conn, String equipType,
			String[] atmids, String timebegin, String timeend, String errType,
			String errTime);

	public String getOutTimeAtm(Connection conn, Atm atm, int macOrBui,
			boolean flag);

	public List<Atm> getSendBugAtm(Connection conn);

	public void setSendType(Connection conn, Atmhistory atmHistory);

	public void saveMoneyValidAtmHistory(Connection conn, String atmid);

	public void updateMoneyValidAtmHistory(Connection conn, String atmid);

	public Atmhistory getMoneyValidAtmHistory(Connection conn, String atmid);

	public List<String> getKJL(Connection conn);

	// public Atm getTimeoutAtmBug(Connection conn, Atm atm);
}

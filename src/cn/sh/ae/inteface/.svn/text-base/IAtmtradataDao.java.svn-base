package cn.sh.ae.inteface;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import cn.sh.ae.vo.Atm;
import cn.sh.ae.vo.AtmTradataMoney;
import cn.sh.ae.vo.Atmtradata;
import cn.sh.ae.vo.Draw;

public interface IAtmtradataDao extends Serializable {

	// 取款交易量(Excel)
	public List<Draw> getDrawInfo(Connection conn, String[] atmids,
			String tradeType, String transType, String type, String timebegin,
			String timeend);

	public List<List<Draw>> getHisInfo(Connection conn, String type,
			String timebegin, String timeend, String[] atmid);

	/***/
	// public String getStatiInfo(DataSource ds, String atmid, String timebegin,
	// String timeend, String cycle, String type);
	// public String getDayLast(Connection conn);
	public void setTradata(Connection conn, List<Atmtradata> list);
	public void setTradataMoney(Connection conn, List<AtmTradataMoney> list);

	// public void setTradata(Connection conn, Atmtradata atmTra);
	// 历史分析
	public List<Atm> getDesMoney(Connection conn, String[] atmids,
			String timebegin, String timeend, String self);

	// public void moveDayData(Connection conn);
	//
	// public void moveWeekData(Connection conn);
	//
	// public void moveMonthData(Connection conn);
	//
	// public void moveYearData(Connection conn);

	// public List<Atmtradata> getTradeRmb(Connection conn, String atmid,
	// String timebegin);

	public List<Atmtradata> getAtmTradataByTime(Connection conn, String atmId,
			String time);

	public List<Atmtradata> getTodayTradeById(Connection conn, String atmId);

	public List<Atmtradata> getTodayTradeByType(Connection conn, String type);
	
	public List<Atmtradata> getAllTrade(Connection conn,int type, String timebegin, String timeend);
	
	public List<AtmTradataMoney> getAllTradeMoney(Connection conn, String timebegin, String timeend);



}

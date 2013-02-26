package cn.sh.ae.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.sh.ae.inteface.IAtmtradataDao;
import cn.sh.ae.util.MyClose;
import cn.sh.ae.util.MyConstant;
import cn.sh.ae.util.MyTime;
import cn.sh.ae.vo.Atm;
import cn.sh.ae.vo.AtmTradataMoney;
import cn.sh.ae.vo.Atmtradata;
import cn.sh.ae.vo.Draw;

/**
 * 
 */
public class AtmtradataDao implements IAtmtradataDao {

	private static final long serialVersionUID = -5642860111801235477L;

	static Logger logger = Logger.getLogger(AtmtradataDao.class.getName());

	private String drawInfoSQL(String[] atmids, String tradeType,
			String transType, String type, String timebegin, String timeend) {

		int flag = (atmids == null || atmids.length == 0) ? 0
				: atmids.length == 1 ? 1 : 2;

		String sql = "select ";

		if (flag != 1)
			sql += "a.atmid,a.addr,";
		else
			sql += "atmtradata.tradetime,";
		sql += "c.company_ch,sum(atmtradata.tradermb) as tradermb,count(*) as tradecount from atm as a , atmtradata , atmcompany as c where a.atmid_new=atmtradata.atmid and a.company=c.id and ";
		if (tradeType.equals("1")) {
			if (transType.equals("0"))
				sql += MyConstant.dra;
			else if (transType.equals("1"))
				sql += MyConstant.b_dra;
			else if (transType.equals("2"))
				sql += MyConstant.t_dra;
		} else if (tradeType.equals("2"))
			sql += MyConstant.b_dep;
		else if (tradeType.equals("3"))
			sql += MyConstant.t_dra;
		if (type.equals("999999")) {
			if (flag != 0) {
				sql += " and (1<>1";
				for (int i = 0; i < atmids.length; i++) {
					sql += " or a.atmid='" + atmids[i] + "'";
				}
				sql += ")";
			}
		} else if (!type.equals("0"))
			sql += " and a.TYPE='" + type + "'";
		sql += " and atmtradata.tradetime>='"
				+ MyTime.getFormatDate(timebegin, "yyyy-MM-dd", "yyyyMMdd")
				+ "' and atmtradata.tradetime<='"
				+ MyTime.getFormatDate(timeend, "yyyy-MM-dd", "yyyyMMdd") + "'";
		sql += "  group by ";
		if (flag != 1)
			sql += " a.atmid,a.addr,c.company_ch order by a.atmid";
		else
			sql += " atmtradata.tradetime,c.company_ch order by atmtradata.tradetime";
		return sql;
	}

	@Override
	public List<Draw> getDrawInfo(Connection conn, String[] atmids,
			String tradeType, String transType, String type, String timebegin,
			String timeend) {
		Draw draw = null;
		List<Draw> list = null;
		int count = 0, sum = 0;
		String sql = drawInfoSQL(atmids, tradeType, transType, type, timebegin,
				timeend);
		logger.info(sql);
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			list = new ArrayList<Draw>();
			int subDay = MyTime.subTime(timebegin, timeend) / 24;
			logger.info("subDay:" + subDay);
			while (rs.next()) {
				draw = new Draw();
				sum = rs.getInt("tradermb");
				count = rs.getInt("tradecount");
				if (atmids != null && atmids.length == 1) {
					draw.setAtmid(atmids[0]);
					draw.setTradetime(rs.getString("tradetime").trim());
				} else {
					draw.setAtmid(rs.getString("atmid").trim());
					draw.setAddr(rs.getString("addr").trim());
				}
				draw.setType(rs.getString("company_ch"));
				if (atmids.length > 1)
					draw.setAddr(rs.getString("addr"));

				draw.setSum(sum);
				draw.setCount(count);
				// draw.setAvgmoney((float) sum);
				// draw.setAvgcount((float) count);
				draw.setAvgmoney((float) sum / (float) count);
				draw.setAvgcount((float) count / (float) subDay);

				list.add(draw);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			list = null;
		}
		return list;
	}

	// @Override
	// public String getDayLast(Connection conn) {
	// String last = "";
	// String sql = "select max(tradetime) as last from atmtradata";
	// try {
	// ResultSet rs = conn.createStatement().executeQuery(sql);
	// while (rs.next()) {
	// last = rs.getTimestamp("last").toString();
	// }
	// } catch (Exception e) {
	// logger.error(e.getLocalizedMessage(), e);
	// }
	// return last;
	// }

	@Override
	public void setTradata(Connection conn, List<Atmtradata> list) {
		String localSqlI = "insert into atmtradata (TRADETIME,TRADEHOUR,CARDNO,TRADERMB,CARDCLASS,TRADETYPE,ATMID,NUMBER) values(?,?,?,?,?,?,?,?)";
		try {
//			conn.setAutoCommit(false);
			PreparedStatement ptI = conn.prepareStatement(localSqlI);
			// 计数器,6W条数据提交可能出错。
			int recordNum = 0;
			for (Atmtradata tradata : list) {
				recordNum++;
				ptI.setString(1, tradata.getTradetime().trim());
				ptI.setString(2, tradata.getTradehour().trim());
				ptI.setString(3, tradata.getCardno().trim());
				ptI.setFloat(4, tradata.getTradermb());
				String s = tradata.getCardclass().trim();
				if (s.equals(""))
					ptI.setNull(5, Types.VARCHAR);
				else
					ptI.setString(5, tradata.getCardclass().trim());
				ptI.setString(6, tradata.getTradetype().trim());
				ptI.setString(7, tradata.getAtmid().trim());
				ptI.setFloat(8, tradata.getNumber());
				ptI.execute();
//				ptI.addBatch();
//
//				// 先将5W条整数倍的记录提交到库中
//				if (recordNum % 50000 == 0) {
//					logger.info("提交5W条数据");
//					ptI.executeBatch();
//					conn.commit();
//					conn.setAutoCommit(false);
//					ptI = conn.prepareStatement(localSqlI);
//				}
			}
//			// 提交剩余记录
//			int i = list.size();
//			logger.info("提交" + (i - (i / 50000) * 50000) + "剩余数据");
//			ptI.executeBatch();
//			conn.commit();

			logger.info("数据入库");
		} catch (SQLException e) {
			logger.error(e.getLocalizedMessage(), e);
		}

	}

	@Override
	public List<Atm> getDesMoney(Connection conn, String[] atmids,
			String timebegin, String timeend, String type) {
		List<Atm> list = null, yList = null, mList = null;
		Atm atm = null;
		int flag = (atmids == null || atmids.length == 0) ? 0
				: atmids.length == 1 ? 1 : 2;
		String sql = "select a.atmid,a.addr,sum(atmtradata.tradermb) as tradermb from atm as a join atmtradata on a.atmid_new=atmtradata.atmid and "
				+ MyConstant.dra;
		if (type.equals("999999")) {
			if (flag != 0) {
				sql += " and (1<>1";
				for (int i = 0; i < atmids.length; i++) {
					sql += " or a.atmid='" + atmids[i] + "'";
				}
				sql += ")";
			}
		} else if (!type.equals("0"))
			sql += " and a.TYPE='" + type + "'";

		String sql_end = " group by a.atmid,a.addr";

		int day = MyTime.subTime(timebegin, timeend);

		String sql1 = sql += " and atmtradata.tradetime>='"
				+ MyTime.getFormatDate(timebegin, "yyyy-MM-dd", "yyyyMMdd")
				+ "' and atmtradata.tradetime<='"
				+ MyTime.getFormatDate(timeend, "yyyy-MM-dd", "yyyyMMdd") + "'";
		sql1 += sql_end;

		String sql2 = sql += " and atmtradata.tradetime>='"
				+ MyTime.getFormatDate(timebegin, "yyyy-MM-dd", "yyyyMMdd")
				+ "' and atmtradata.tradetime<='"
				+ MyTime.getFormatDate(timeend, "yyyy-MM-dd", "yyyyMMdd") + "'";
		sql2 += sql_end;
		// logger.info(sql1);
		// logger.info(sql2);

		try {
			list = new ArrayList<Atm>();
			yList = new ArrayList<Atm>();
			mList = new ArrayList<Atm>();

			ResultSet rs1 = conn.createStatement().executeQuery(sql1);
			while (rs1.next()) {
				atm = new Atm();
				int money = (rs1.getInt("tradermb") * day);
				if (money > 10000)
					money = ((money / 10000) + 1) * 10000;
				atm.setAtmid(rs1.getString("atmid"));
				atm.setPartrmb(String.valueOf(money));
				atm.setAddr(rs1.getString("addr"));
				yList.add(atm);
			}

			ResultSet rs2 = conn.createStatement().executeQuery(sql2);
			while (rs2.next()) {
				atm = new Atm();
				int money = (rs2.getInt("tradermb") * day);
				if (money > 10000)
					money = ((money / 10000) + 1) * 10000;
				atm.setAtmid(rs2.getString("atmid"));
				atm.setPartrmb(String.valueOf(money));
				atm.setAddr(rs2.getString("addr"));
				mList.add(atm);
			}
			for (Atm mAtm : mList) {
				if (yList.size() != 0) {
					atm = new Atm();
					atm.setAtmid(mAtm.getAtmid());
					atm.setAddr(mAtm.getAddr());
					for (Atm yAtm : yList) {
						if (mAtm.getAtmid().equals(yAtm.getAtmid())) {
							int m = Integer.parseInt(mAtm.getPartrmb()) / 2;
							int y = Integer.parseInt(yAtm.getPartrmb()) / 2;
							atm.setPartrmb(String.valueOf(m + y));
						} else
							atm.setPartrmb("0");
					}
					list.add(atm);
				} else
					list = mList;
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			list = null;
		} finally {
			yList = null;
			mList = null;
		}
		return list;
	}

	@Override
	public List<List<Draw>> getHisInfo(Connection conn, String type,
			String timebegin, String timeend, String[] atmids) {
		Draw draw = null;
		List<Draw> list_q = null;
		List<Draw> list_c = null;
		List<List<Draw>> list = null;

		int flag = (atmids == null || atmids.length == 0) ? 0
				: atmids.length == 1 ? 1 : 2;

		String sql = "select a.atmid,atmtradata.tradetype,count(atmtradata.tradermb) as drawcount,sum(atmtradata.tradermb) as drawsum,avg(atmtradata.tradermb) as drawavg from atm as a join atmtradata on a.atmid_new=atmtradata.atmid and atmtradata.tradetime>='"
				+ MyTime.getFormatDate(timebegin, "yyyy-MM-dd", "yyyyMMdd")
				+ "' and atmtradata.tradetime<='"
				+ MyTime.getFormatDate(timeend, "yyyy-MM-dd", "yyyyMMdd") + "'";

		if (type.equals("5")) {
			if (flag != 0) {
				sql += " and (1<>1";
				for (int i = 0; i < atmids.length; i++) {
					sql += " or a.atmid='" + atmids[i] + "'";
				}
				sql += ")";
			}
		} else if (!type.equals("9"))
			sql += " and a.TYPE='" + type + "'";
		sql += " group by a.atmid,atmtradata.tradetype order by a.atmid,atmtradata.tradetype";
		logger.info(sql);
		try {
			list_q = new ArrayList<Draw>();
			list_c = new ArrayList<Draw>();
			list = new ArrayList<List<Draw>>();
			int subDay = MyTime.subTime(timebegin, timeend);
			ResultSet rs = conn.createStatement().executeQuery(sql);
			while (rs.next()) {
				draw = new Draw();
				String cq = rs.getString("tradetype");
				int count = rs.getInt("drawcount");
				draw.setAtmid(rs.getString("atmid"));
				draw.setSum(rs.getInt("drawsum"));
				draw.setCount(count);
				draw.setAvgmoney(rs.getInt("drawavg"));
				if (subDay != 0)
					draw.setAvgcount((float) count / (float) subDay);
				else
					draw.setAvgcount(count);
				if (cq.equals("004") || cq.equals("037")) {
					list_q.add(draw);
				} else if (cq.equals("003")) {
					list_c.add(draw);
				}
			}
			list.add(list_c);
			list.add(list_q);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			list = null;
		} finally {
			MyClose.close(conn);
		}
		return list;
	}

	@Override
	public List<Atmtradata> getAtmTradataByTime(Connection conn, String atmId,
			String time) {
		List<Atmtradata> tradataList = null;
		Atmtradata tradata = null;

		String sql = "select atm.atmid,atmtradata.tradetime,atmtradata.tradehour,atmtradata.cardno,atmtradata.tradermb,atm.addr,company_ch,type_ch from atm,atmcompany,atmtype,atmtradata where atmtradata.atmid=atm.atmid_new and atm.route=atmtype.id and atm.company=atmcompany.id and "
				+ MyConstant.dra
				+ " and atm.atmid='"
				+ atmId
				+ "' and atmtradata.tradetime='"
				+ MyTime.getFormatDate(time, "yyyy-MM-dd", "yyyyMMdd")
				+ "' order by atmtradata.tradehour";
		logger.info(sql);
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			tradataList = new ArrayList<Atmtradata>();
			while (rs.next()) {
				tradata = new Atmtradata();
				tradata.setAtmid(rs.getString("atmid") + ""
						+ rs.getString("company_ch") + "-"
						+ rs.getString("type_ch") + " " + rs.getString("addr"));
				tradata.setTradetime(rs.getString("tradetime"));
				tradata.setTradehour(rs.getString("tradehour"));
				tradata.setCardno(rs.getString("cardno"));
				tradata.setTradermb(rs.getFloat("tradermb"));
				tradataList.add(tradata);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		} finally {
			// MyClose.close(conn);
		}
		return tradataList;
	}

	@Override
	public List<Atmtradata> getTodayTradeById(Connection conn, String atmId) {
		List<Atmtradata> tradataList = null;
		Atmtradata tradata = null;

		String sql = "select * from atmtradata where " + MyConstant.dra
				+ "  and atmtradata.tradetime='"
				+ MyTime.getOneDate("yyyyMMdd", -2) + "'";
		if (atmId != null && !atmId.equals(""))
			sql += " and atmtradata.atmId='" + atmId + "'";
		sql += " order by atmtradata.tradehour";
		logger.info(sql);
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			tradataList = new ArrayList<Atmtradata>();
			while (rs.next()) {
				tradata = new Atmtradata();
				tradata.setTradetime(rs.getString("tradetime"));
				tradata.setTradehour(rs.getString("tradehour"));
				tradata.setTradetype(rs.getString("tradetype"));
				tradata.setTradermb(rs.getFloat("tradermb"));
				tradataList.add(tradata);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		} finally {
			// MyClose.close(conn);
		}
		return tradataList;
	}

	@Override
	public List<Atmtradata> getTodayTradeByType(Connection conn, String type) {
		List<Atmtradata> tradataList = null;
		Atmtradata tradata = null;

		String sql = "select atm.atmid,count(atmtradata.tradetype) as a from atmtradata,atm where atm.atmid_new=atmtradata.atmid and atmtradata.tradetime='"
				+ MyTime.getOneDate("yyyyMMdd", -2) + "'";
		if (type != null) {
			if (type.equals("1"))
				sql += " and " + MyConstant.dra;
			else if (type.equals("2"))
				sql += " and " + MyConstant.b_dep;
		}
		sql += " group by atmtradata.tradetype,atm.atmid order by a desc";
		logger.info(sql);
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			tradataList = new ArrayList<Atmtradata>();
			while (rs.next()) {
				tradata = new Atmtradata();
				tradata.setAtmid(rs.getString("atmId"));
				tradata.setTradermb(rs.getInt("a"));
				tradataList.add(tradata);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		} finally {
			// MyClose.close(conn);
		}
		return tradataList;
	}

	@Override
	public List<Atmtradata> getAllTrade(Connection conn, int type,
			String timebegin, String timeend) {
		List<Atmtradata> tradataList = null;
		Atmtradata tradata = null;

		String sql = null;

		sql = "select atm.atmid,atm.addr,sum(atmtradata.tradermb) as s,count(atmtradata.tradermb) as c from atm,atmtradata where atmtradata.atmid=atm.atmid_new";
		if (type == 1)
			sql += " and " + MyConstant.dra;
		else if (type == 11)
			sql += " and " + MyConstant.t_dra;
		else if (type == 2)
			sql += " and " + MyConstant.b_dep;
		else if (type == 3)
			sql += " and " + MyConstant.tra;

		sql += " and atmtradata.tradetime>='"
				+ MyTime.getFormatDate(timebegin, "yyyy-MM-dd", "yyyyMMdd")
				+ "' and atmtradata.tradetime<='"
				+ MyTime.getFormatDate(timeend, "yyyy-MM-dd", "yyyyMMdd") + "'"
				+ " group by atm.atmid,atm.addr order by atm.atmid";
		logger.info(sql);

		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			tradataList = new ArrayList<Atmtradata>();
			while (rs.next()) {
				tradata = new Atmtradata();
				tradata.setAtmid(rs.getString("atmId"));
				tradata.setReserve(rs.getString("addr"));
				tradata.setTradecount(rs.getInt("c"));
				tradata.setTradermb(rs.getInt("s"));
				tradataList.add(tradata);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		} finally {
			// MyClose.close(conn);
		}
		return tradataList;
	}

	// @Override
	// public void setTradata(Connection conn, Atmtradata atmTra) {
	// String localSqlI = "insert into atmtradata
	// values(?,?,?,?,?,?,?,?,?,?,?)";
	// try {
	// PreparedStatement ptI = conn.prepareStatement(localSqlI);
	// ptI.setString(1, "");
	// ptI.setString(2, atmTra.getAtmid().trim());
	// ptI.setString(3, atmTra.getTradetime().trim());
	// ptI.setString(4, atmTra.getTradetime().trim());
	// ptI.setString(5, atmTra.getTradetype().trim());
	// ptI.setString(6, atmTra.getCardno().trim());
	// ptI.setString(7, atmTra.getCardclass().trim());
	// ptI.setFloat(8, atmTra.getTradermb());
	// ptI.setString(9, atmTra.getSendstatus().trim());
	// ptI.setString(10, atmTra.getDevno().trim());
	// ptI.setString(11, "");
	// ptI.execute();
	// } catch (SQLException e) {
	// logger.error(e.getLocalizedMessage(), e);
	// }
	//
	// }

	@Override
	public void setTradataMoney(Connection conn, List<AtmTradataMoney> list) {
		String localSqlI = "insert into atmmoney (ATMID,MONEY,ATMID_NEW,TRADETIME) values(?,?,?,?)";
		try {
			PreparedStatement ptI = conn.prepareStatement(localSqlI);
			for (AtmTradataMoney tradataMon : list) {
				ptI.setString(1, tradataMon.getAtmid().trim());
				ptI.setFloat(2, tradataMon.getMoney());
				ptI.setString(3, tradataMon.getAtmid_new().trim());
				ptI.setString(4, tradataMon.getTradetime().trim());
				ptI.addBatch();
			}
			ptI.executeBatch();
		} catch (SQLException e) {
			logger.error(e.getLocalizedMessage(), e);
		}

	}

	@Override
	public List<AtmTradataMoney> getAllTradeMoney(Connection conn,
			String timebegin, String timeend) {
		List<AtmTradataMoney> tradataMonList = null;
		AtmTradataMoney tradataMon = null;

		String sql = null;

		sql = "select atmid,sum(money) as money from atmmoney  where  tradetime>='"
				+ MyTime.getFormatDate(timebegin, "yyyy-MM-dd", "yyyyMMdd")
				+ "' and tradetime<='"
				+ MyTime.getFormatDate(timeend, "yyyy-MM-dd", "yyyyMMdd")
				+ "'"
				+ " group by atmid order by atmid";
		logger.info(sql);

		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			tradataMonList = new ArrayList<AtmTradataMoney>();
			while (rs.next()) {
				tradataMon = new AtmTradataMoney();
				tradataMon.setAtmid(rs.getString("atmid"));
				tradataMon.setMoney(rs.getFloat("money"));
				tradataMonList.add(tradataMon);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		} finally {
			// MyClose.close(conn);
		}
		return tradataMonList;
	}
}

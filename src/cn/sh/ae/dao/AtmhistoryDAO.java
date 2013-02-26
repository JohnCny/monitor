package cn.sh.ae.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jxl.write.DateTime;

import org.apache.log4j.Logger;

import cn.sh.ae.inteface.IAtmhistoryDAO;
import cn.sh.ae.util.MyClose;
import cn.sh.ae.util.MyTime;
import cn.sh.ae.vo.Atm;
import cn.sh.ae.vo.Atmhistory;

/**
 * 
 */
public class AtmhistoryDAO implements IAtmhistoryDAO {

	private static final long serialVersionUID = 5484034910767818941L;

	static Logger logger = Logger.getLogger(AtmhistoryDAO.class.getName());

	@Override
	public int[] setAtmHistory(Connection conn, List<Atm> list) {
		int s[] = null;
		String sql = "insert into atmhistory values(?,?,?,?,?,?,?,?,?,?)";
		String time = MyTime.getTime("yyyyMMddHHmmss");
		try {
			PreparedStatement pt = conn.prepareStatement(sql);
			if (list != null && !list.isEmpty()) {
				for (Atm column : list) {
					pt.setString(1, column.getAtmid());
					pt.setString(2, time.substring(0, 8));
					pt.setString(3, time.substring(8));
					pt.setInt(4, Integer.parseInt(column.getDemstatus()));
					pt.setInt(5, Integer.parseInt(column.getPrjstatus()));
					pt.setInt(6, Integer.parseInt(column.getPrrstatus()));
					pt.setInt(7, Integer.parseInt(column.getCdmstatus()));
					pt.setInt(8, Integer.parseInt(column.getDemstatus()));
					pt.setInt(9, Integer.parseInt(column.getReaderstatus()));
					pt.setInt(10, 1);
					pt.addBatch();
				}
				s = pt.executeBatch();
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			s = null;
		} finally {
			MyClose.close(conn);
		}
		return s;
	}

	public static void main(String[] args) {
		System.out.println(MyTime.getFormatDate("20110426110305",
				"yyyyMMddHHmmss", "yyyyMMdd"));
	}

	@Override
	public List<Atmhistory> getAtmBug(Connection conn, String equipType,
			String[] atmids, String timebegin, String timeend, String errType,
			String errTime) {
		List<Atmhistory> list = null;
		Atmhistory atmHistory = null;
		int flag = (atmids == null || atmids.length == 0) ? 0
				: atmids.length == 1 ? 1 : 2;

		String sql = "select atmhistory.atmid,atmhistory.bugbeg,atmhistory.bugend,unittype.unit_ch,unittype.id,atm.addr,atmcompany.company_en,atmtype.type_en from atmcompany,atmtype,atmhistory,unittype,atm where unittype.id=atmhistory.bugid and atmhistory.atmid=atm.atmid and atm.company=atmcompany.id and atm.route=atmtype.id ";
		if (!errType.equals("0"))
			sql += "and atmhistory.bugtype='" + errType + "'";
		if (equipType.equals("999999")) {
			if (flag != 0) {
				sql += " and (1<>1";
				for (int i = 0; i < atmids.length; i++) {
					sql += " or atmhistory.atmid='" + atmids[i] + "'";
				}
				sql += ")";
			}
		} else if (!equipType.equals("0"))
			sql += " and atm.type='" + equipType + "'";
		sql += " and bugend>='"
				+ MyTime.getFormatDate(timebegin, "yyyy-MM-dd", "yyyyMMdd")
				+ "' and bugend<='"
				+ MyTime.getFormatDate(timeend, "yyyy-MM-dd", "yyyyMMdd") + "'";
		sql += " order by atmhistory.atmid,unittype.id";
		logger.info(sql);
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			list = new ArrayList<Atmhistory>();
			while (rs.next()) {
				String begTime = rs.getString("bugBeg");
				String endTime = rs.getString("bugEnd");
				int subTime = MyTime.subTimeM(begTime, endTime,
						"yyyyMMddHHmmss");
				if (subTime >= Integer.parseInt(errTime) * 60) {
					atmHistory = new Atmhistory();
					String bugId = rs.getString("unit_ch");
					atmHistory.setAtmId(rs.getString("atmid"));
					atmHistory.setBugId(bugId);
					atmHistory.setBugEnd(MyTime.getFormatDate(endTime,
							"yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss"));
					atmHistory.setBugBeg(MyTime.getFormatDate(begTime,
							"yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss"));
					atmHistory.setSubTime(String.valueOf(subTime));
					atmHistory.setConstant(rs.getString("company_en") + "-"
							+ rs.getString("type_en") + " "
							+ rs.getString("addr"));
					atmHistory.setBugType("故障");
					if (bugId.equals("2") || bugId.equals("3"))
						atmHistory.setBugType("缺纸");
					list.add(atmHistory);
				}
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			list = null;
		}
		return list;
	}

	@Override
	public void setAtmHistory(Connection conn, Atm atm) {
		String localSqlI = "insert into atmhistory values(?,?,?,?,?,?)";
		String localSqlU = "update atmhistory set bugEnd=?,sendtype=? where atmid=? and bugId=? and bugEnd='0'";
//		String localSqlUB= "update atmhistory set bugBeg=? where atmid=? and bugId=? and bugEnd='0'";
		boolean su = false, si = false/*, sub = false*/;
		PreparedStatement ptI = null;
		PreparedStatement ptU = null;
//		PreparedStatement ptUB = null;
		String time = MyTime.getTime("yyyyMMddHHmmss");
		try {
			ptU = conn.prepareStatement(localSqlU);
			ptI = conn.prepareStatement(localSqlI);
//			ptUB= conn.prepareStatement(localSqlUB);
			/** ***************************************** */
			if (atm.getCdmstatus() != null) {
				if (atm.getCdmstatus().equals("1")) {
					si = true;
					ptI.setString(1, atm.getAtmid());
					ptI.setString(2, "4");
					ptI.setString(3, time);
					ptI.setString(4, "0");
					ptI.setString(5, "1");
					ptI.setString(6, "1");
					ptI.addBatch();
				} else if (atm.getCdmstatus().equals("0")) {
					su = true;
					ptU.setString(1, time);
					ptU.setString(2, "4");
					ptU.setString(3, atm.getAtmid());
					ptU.setString(4, "4");
					ptU.addBatch();
				}
			}
			/** ***************************************** */
			if (atm.getDepstatus() != null) {
				if (atm.getDepstatus().equals("1")) {
					si = true;
					ptI.setString(1, atm.getAtmid());
					ptI.setString(2, "5");
					ptI.setString(3, time);
					ptI.setString(4, "0");
					ptI.setString(5, "1");
					ptI.setString(6, "1");
					ptI.addBatch();
				} else if (atm.getDepstatus().equals("0")) {
					su = true;
					ptU.setString(1, time);
					ptU.setString(2, "4");
					ptU.setString(3, atm.getAtmid());
					ptU.setString(4, "5");
					ptU.addBatch();
				}
			}
			/** ***************************************** */
			if (atm.getReaderstatus() != null) {
				if (atm.getReaderstatus().equals("1")) {
					si = true;
					ptI.setString(1, atm.getAtmid());
					ptI.setString(2, "6");
					ptI.setString(3, time);
					ptI.setString(4, "0");
					ptI.setString(5, "1");
					ptI.setString(6, "1");
					ptI.addBatch();
				} else if (atm.getReaderstatus().equals("0")) {
					su = true;
					ptU.setString(1, time);
					ptU.setString(2, "4");
					ptU.setString(3, atm.getAtmid());
					ptU.setString(4, "6");
					ptU.addBatch();
				}
			}
			/** ***************************************** */
			if (atm.getPrjstatus() != null) {
				if (atm.getPrjstatus().equals("1")
						|| atm.getPrjstatus().equals("2")) {
					si = true;
					ptI.setString(1, atm.getAtmid());
					ptI.setString(2, "2");
					ptI.setString(3, time);
					ptI.setString(4, "0");
					ptI.setString(5, atm.getPrjstatus());
//					ptI.setString(5, "1");
					ptI.setString(6, "1");
					ptI.addBatch();
				} else if (atm.getPrjstatus().equals("0")) {
					su = true;
					ptU.setString(1, time);
					ptU.setString(2, "4");
					ptU.setString(3, atm.getAtmid());
					ptU.setString(4, "2");
					ptU.addBatch();
				}
			}
			/** ***************************************** */
			if (atm.getPrrstatus() != null) {
				if (atm.getPrrstatus().equals("1")
						|| atm.getPrrstatus().equals("2")) {
					si = true;
					ptI.setString(1, atm.getAtmid());
					ptI.setString(2, "3");
					ptI.setString(3, time);
					ptI.setString(4, "0");
					ptI.setString(5, atm.getPrrstatus());
//					ptI.setString(5, "1");
					ptI.setString(6, "1");
					ptI.addBatch();
				} else if (atm.getPrrstatus().equals("0")) {
					su = true;
					ptU.setString(1, time);
					ptU.setString(2, "4");
					ptU.setString(3, atm.getAtmid());
					ptU.setString(4, "3");
					ptU.addBatch();
				}
			}
			/** ***************************************** */
			if (atm.getLinestatus() != null) {
				if (atm.getLinestatus().equals("1")) {
					si = true;
					ptI.setString(1, atm.getAtmid());
					ptI.setString(2, "7");
					ptI.setString(3, time);
					ptI.setString(4, "0");
					ptI.setString(5, "1");
					ptI.setString(6, "1");
					ptI.addBatch();
				} else if (atm.getLinestatus().equals("0")) {
					su = true;
					ptU.setString(1, time);
					ptU.setString(2, "4");
					ptU.setString(3, atm.getAtmid());
					ptU.setString(4, "7");
					ptU.addBatch();
				}

			}
			if (atm.getPartrmb() != null) {
				if (atm.getPartrmb().equals("5")) {
					//小于300张预警
					si = true;
					ptI.setString(1, atm.getAtmid());
					ptI.setString(2, "8");
					ptI.setString(3, time);
					ptI.setString(4, "0");
					ptI.setString(5, "5");
					ptI.setString(6, "1");
					ptI.addBatch();
				} else if (atm.getPartrmb().equals("0")) {
					su = true;
					ptU.setString(1, time);
					ptU.setString(2, "4");
					ptU.setString(3, atm.getAtmid());
					ptU.setString(4, "8");
					ptU.addBatch();
				} else if (atm.getPartrmb().equals("7")){
					if (MyTime.getDate().getHour()<7){
						si=false;
						logger.info("当前时间"+MyTime.getDate().getHour()+"点");
					}
					else if (MyTime.getDate().getHour()>=18){
						si=false;
						logger.info("当前时间"+MyTime.getDate().getHour()+"点");
					}
					else{
						
						si=true;
						ptI.setString(1,atm.getAtmid());
						ptI.setString(2, "8");
						ptI.setString(3, time);
						ptI.setString(4, "0");
						ptI.setString(5, "7");
						ptI.setString(6, "1");
						ptI.addBatch();
	//					sub = true;
	//					ptUB.setString(1, time);
	//					ptUB.setString(2, atm.getAtmid());
	//					ptUB.setString(3, "8");
	//					ptUB.addBatch();
						logger.info("机器缺钞,开始计时"+time+"id:"+atm.getAtmid());
					}
				}
			}
			if (atm.getDepormb() != null) {
				if (atm.getDepormb().equals("6")) {
					si = true;
					ptI.setString(1, atm.getAtmid());
					ptI.setString(2, "8");
					ptI.setString(3, time);
					ptI.setString(4, "0");
					ptI.setString(5, "6");
					ptI.setString(6, "1");
					ptI.addBatch();
				} else if (atm.getDepormb().equals("0")) {
					su = true;
					ptU.setString(1, time);
					ptU.setString(2, "4");
					ptU.setString(3, atm.getAtmid());
					ptU.setString(4, "8");
					ptU.addBatch();
				}
			}
			if (su)
				ptU.executeBatch();
			if (si)
				ptI.executeBatch();
//			if (sub){
//				int update_num[]=ptUB.executeBatch();
//				logger.info("更新缺钞时间"+update_num);
//			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	@Override
	public String getOutTimeAtm(Connection conn, Atm atm, int macOrBui,
			boolean flag) {
		String str = null;
		String sql = null;
		try {
			if (macOrBui == 1)
				sql = "select  bugtime,bughour from atmhistory where atmid='"
						+ atm.getAtmid() + "' and cdmstatus="
						+ atm.getCdmstatus() + " and depstatus="
						+ atm.getDepstatus() + " and readerstatus="
						+ atm.getReaderstatus()
						+ " order by bugtime,bughour desc";
			if (flag)
				sql += " and demstatus='" + atm.getDemstatus() + "'";
			else if (macOrBui == 2)
				sql = "select  bugtime,bughour from atmhistory where atmid='"
						+ atm.getAtmid() + "' and cast(partrmb as int)<" + 300
						+ " order by bugtime,bughour desc";
			// //logger.info(sql);
			ResultSet rs = conn.createStatement().executeQuery(sql);
			if (rs.next()) {
				str = rs.getString("bugtime").trim() + rs.getString("bughour");
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			str = null;
		}
		return str;
	}

	@Override
	public List<Atm> getSendBugAtm(Connection conn) {
		List<Atm> atmList = null;
		Atm atm = null;
		String sql = "select partrmb,atmhistory.atmid,atmhistory.bugid,atmhistory.bugbeg,atmhistory.bugtype,atmhistory.sendtype,unittype.unit_ch,atm.addr,atmcompany.company_ch,atmtype.type_ch from atmhistory,atm,unittype,atmcompany,atmtype where atmhistory.atmid=atm.atmid and atmhistory.bugid=unittype.id and atm.company=atmcompany.id and atm.route=atmtype.id and bugend='0' and sendtype<>'4'";
		logger.info(sql);
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			atmList = new ArrayList<Atm>();
			while (rs.next()) {
				atm = new Atm();
				atm.setAtmid(rs.getString("atmid"));
				atm.setTimeout(rs.getString("bugbeg"));
				atm.setEjr(rs.getString("bugid"));
				atm.setAddr(rs.getString("addr"));
				atm.setRoute(rs.getString("type_ch"));
				atm.setCompany(rs.getString("company_ch"));
				atm.setAllstatus(rs.getString("unit_ch"));
				atm.setType(rs.getString("bugtype"));
				atm.setAesyspath(rs.getString("sendtype"));
				atm.setPartrmb(rs.getString("partrmb"));
				atmList.add(atm);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		return atmList;
	}

	@Override
	public void setSendType(Connection conn, Atmhistory atmHistory) {
		String sql = "update atmhistory set sendtype='"
				+ atmHistory.getConstant() + "' where atmid='"
				+ atmHistory.getAtmId() + "' and bugid='"
				+ atmHistory.getBugId() + "' and sendtype='"
				+ atmHistory.getSendType() + "' and bugBeg='"
				+ atmHistory.getBugBeg() + "'";
		logger.info(sql);
		try {
			conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			logger.error(e.getLocalizedMessage(), e);
		}

	}

	@Override
	public Atmhistory getMoneyValidAtmHistory(Connection conn, String atmid) {
		String sql = "select atmid,sendtype from atmhistory where bugid = '99'and atmid='"
				+ atmid + "'";
		logger.info(sql);
		Atmhistory atmhistory = null;
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);

			while (rs.next()) {
				atmhistory = new Atmhistory();
				atmhistory.setAtmId(rs.getString("atmid"));
				atmhistory.setSendType(rs.getString("sendtype"));
			}
		} catch (SQLException e) {
			logger.error(e.getLocalizedMessage(), e);
		}

		return atmhistory;
	}

	@Override
	public void saveMoneyValidAtmHistory(Connection conn, String atmid) {
		String sql = " insert into atmhistory (atmid,sendtype,bugid) values(?,?,?)";
		logger.info(sql);
		try {
			PreparedStatement pt = conn.prepareStatement(sql);
			if (null != atmid) {
				pt.setString(1, atmid);
				pt.setString(2, "2");
				pt.setString(3, "99");
			}
			pt.execute();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error(e.getLocalizedMessage(), e);
			}
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	@Override
	public void updateMoneyValidAtmHistory(Connection conn, String atmid) {
		String sql = "update atmhistory set sendtype='2' where bugid = '99' and atmid='"
				+ atmid + "'";
		logger.info(sql);
		try {
			conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	@Override
	public List<String> getKJL(Connection conn) {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	// public List<String> getKJL(Connection conn){
	// String sql="select * from ";
	//		
	// }
}

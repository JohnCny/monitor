package cn.sh.ae.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.sh.ae.inteface.ISmsInfoDao;
import cn.sh.ae.vo.SmsInfo;

public class SmsInfoDao implements ISmsInfoDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2061662379737608486L;
	static Logger logger = Logger.getLogger(SmsInfoDao.class.getName());

	@Override
	public List<SmsInfo> getAllSmsInfo(Connection conn) {
		List<SmsInfo> smsInfoList = null;
		SmsInfo smsInfo = null;
		try {
			ResultSet rs = conn.createStatement().executeQuery(
					"select * from smsinfo order by smsdate desc");
			smsInfoList = new ArrayList<SmsInfo>();
			while (rs.next()) {
				smsInfo = new SmsInfo();
				smsInfo.setSmsDate(rs.getString("smsdate"));
				smsInfo.setSmsMsg(rs.getString("smsMsg"));
				smsInfo.setSmsNo(rs.getString("smsNo"));
				smsInfo.setSmsName(rs.getString("smsName"));
				smsInfoList.add(smsInfo);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			smsInfo = null;
		}
		return smsInfoList;
	}

	@Override
	public List<SmsInfo> getSmsInfo(Connection conn, SmsInfo smsInfo) {
		List<SmsInfo> smsInfoList = null;
		SmsInfo smsInfo_1 = null;
		String sql = "select * from smsinfo where 1=1 ";
		String smsdate = smsInfo.getSmsDate();
		String smsNo = smsInfo.getSmsNo();
		String smsName = smsInfo.getSmsName();
		if (smsdate != null && !smsdate.equals(""))
			sql += "and smsdate like '" + smsdate + "%'";
		if (smsNo != null && !smsNo.equals(""))
			sql += "and smsno like '%" + smsNo + "%'";
		if (smsName != null && !smsName.equals(""))
			sql += "and smsname like '%" + smsName + "%'";
		sql += " order by smsdate desc";
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			smsInfoList = new ArrayList<SmsInfo>();
			while (rs.next()) {
				smsInfo_1 = new SmsInfo();
				smsInfo_1.setSmsDate(rs.getString("smsdate"));
				smsInfo_1.setSmsMsg(rs.getString("smsmsg"));
				smsInfo_1.setSmsNo(rs.getString("smsno"));
				smsInfo_1.setSmsName(rs.getString("smsname"));
				smsInfoList.add(smsInfo_1);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			smsInfo_1 = null;
		}
		return smsInfoList;
	}

	@Override
	public void setSmsInfo(Connection conn, SmsInfo smsInfo) {
		String sql = "insert into smsinfo values ('" + smsInfo.getSmsNo()
				+ "','" + smsInfo.getSmsName() + "','" + smsInfo.getSmsMsg()
				+ "','" + smsInfo.getSmsDate() + "')";
		try {
			conn.createStatement().executeUpdate(sql);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

}

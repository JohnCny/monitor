package cn.sh.ae.dao;

import java.sql.Connection;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import cn.sh.ae.inteface.ISmsDao;
import cn.sh.ae.vo.Sms;

public class SmsDao implements ISmsDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2321682318555933067L;
	static Logger logger = Logger.getLogger(SmsDao.class.getName());

	@Override
	public Sms getSmsStatus(Connection conn) {
		Sms sms = null;
		try {
			ResultSet rs = conn.createStatement().executeQuery(
					"select * from smscontrol");
			sms = new Sms();
			while (rs.next()) {
				sms.setSmsstatus(rs.getString("smsstatus").trim());
				sms.setFirsttime(rs.getString("firsttime").trim());
				sms.setSecondtime(rs.getString("secondtime").trim());
				sms.setThirdtime(rs.getString("thirdtime").trim());
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			sms = null;
		}
		return sms;
	}

	@Override
	public int setSmsStatus(Connection conn, Sms sms) {
		int i = 0;
		String sql = "update smscontrol set smsstatus='"
				+ sms.getSmsstatus() + "',firsttime='"
				+ sms.getFirsttime() + "',secondtime='"
				+ sms.getSecondtime() + "',thirdtime='"
				+ sms.getThirdtime() + "'";
		logger.info(sql);
		try {
			i = conn.createStatement().executeUpdate(sql);

		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			i = 0;
		}
		return i;
	}

}

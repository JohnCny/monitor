package cn.sh.ae.dao;

import java.sql.Connection;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import cn.sh.ae.inteface.IAtmpDao;
import cn.sh.ae.util.MyConstant;
import cn.sh.ae.vo.Atmp;

/**
 * 
 */
public class AtmpDao implements IAtmpDao {

	private static final long serialVersionUID = -1815649027638460498L;

	static Logger logger = Logger.getLogger(AtmpDao.class.getName());

	@Override
	public Atmp getAtmpStatus(Connection conn) {
		Atmp atmp = null;
		try {
			ResultSet rs = conn.createStatement().executeQuery(
					"select * from Atmp");
			atmp = new Atmp();
			while (rs.next()) {
				atmp.setDbip(rs.getString(MyConstant.Table.Atmp.DBIP));

				atmp.setDbname(rs.getString(MyConstant.Table.Atmp.DBNAME));
				atmp.setDbpassword(rs
						.getString(MyConstant.Table.Atmp.DBPASSWORD));
				atmp.setDbport(rs.getString(MyConstant.Table.Atmp.DBPORT));

				atmp.setDbusername(rs
						.getString(MyConstant.Table.Atmp.DBUSERNAME));
				atmp.setMgrtstatus(rs
						.getString(MyConstant.Table.Atmp.MGRTSTATUS));
				atmp.setMgrttime(rs.getString(MyConstant.Table.Atmp.MGRTTIME));
				atmp.setMgrtpath(rs.getString(MyConstant.Table.Atmp.MGRTPATH));
				atmp.setSycnstatus(rs
						.getString(MyConstant.Table.Atmp.SYCNSTATUS));
				atmp.setSycntime(rs.getString(MyConstant.Table.Atmp.SYCNTIME));
				atmp.setFtpip(rs.getString(MyConstant.Table.Atmp.FTPIP));
				atmp.setFtppassword(rs
						.getString(MyConstant.Table.Atmp.FTPPASSWORD));
				atmp.setFtppath(rs.getString(MyConstant.Table.Atmp.FTPPATH));
				atmp.setFtpport(rs.getString(MyConstant.Table.Atmp.FTPPORT));
				atmp.setFtpusername(rs
						.getString(MyConstant.Table.Atmp.FTPUSERNAME));
				
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			atmp = null;
		}
		return atmp;
	}

	@Override
	public void setAtmpStatus(Connection conn, Atmp atmp) {
		try {
			String sql = "update Atmp set dbip='" + atmp.getDbip()
					+ "',dbname='" + atmp.getDbname() + "',dbpassword='"
					+ atmp.getDbpassword() + "',dbport='" + atmp.getDbport()
					+ "',dbusername='" + atmp.getDbusername()
					+ "',mgrtstatus='" + atmp.getMgrtstatus() + "',mgrtpath='"
					+ atmp.getMgrtpath() + "',mgrttime='" + atmp.getMgrttime()
					+ "',sycnstatus='" + atmp.getSycnstatus() + "',sycntime='"
					+ atmp.getSycntime() + "',ftpip='" + atmp.getFtpip()
					+ "',ftpport='" + atmp.getFtpport() + "',ftppath='"
					+ atmp.getFtppath() + "',ftpusername='"
					+ atmp.getFtpusername() + "',ftppassword='"
					+ atmp.getFtppassword() + "'";
			conn.createStatement().executeUpdate(sql);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

}

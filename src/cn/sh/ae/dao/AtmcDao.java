package cn.sh.ae.dao;

import java.sql.Connection;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import cn.sh.ae.inteface.IAtmcDao;
import cn.sh.ae.util.MyConstant;
import cn.sh.ae.vo.Atmc;

/**
 * 
 */
public class AtmcDao implements IAtmcDao {

	private static final long serialVersionUID = -1265766209439989555L;
	static Logger logger = Logger.getLogger(AtmcDao.class.getName());

	@Override
	public Atmc getAtmcStatus(Connection conn) {
		Atmc remotecontrol = null;
		try {
			ResultSet rs = conn.createStatement().executeQuery(
					"select * from remotecontrol");
			remotecontrol = new Atmc();
			while (rs.next()) {
				remotecontrol
						.setDownloadstatus(rs
								.getString(MyConstant.Table.Remotecontrol.DOWNLOADSTATUS));
				remotecontrol
						.setRebootstatus(rs
								.getString(MyConstant.Table.Remotecontrol.REBOOTSTATUS));
				remotecontrol
						.setUploadstatus(rs
								.getString(MyConstant.Table.Remotecontrol.UPLOADSTATUS));
				remotecontrol
						.setAutodownload(rs
								.getString(MyConstant.Table.Remotecontrol.AUTODOWNLOAD));
				remotecontrol
						.setDownloadtime(rs
								.getString(MyConstant.Table.Remotecontrol.DOWNLOADTIME));
				remotecontrol
						.setNetmonitorstatus(rs
								.getString(MyConstant.Table.Remotecontrol.NETMONITORSTATUS));
				remotecontrol
						.setNetmonitortime(rs
								.getString(MyConstant.Table.Remotecontrol.NETMONITORTIME));
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			remotecontrol = null;
		}
		return remotecontrol;
	}

	@Override
	public int setAtmcStatus(Connection conn, Atmc atmc) {
		int i = 0;
		String sql = "update remotecontrol set uploadstatus='"
				+ atmc.getUploadstatus() + "',downloadstatus='"
				+ atmc.getDownloadstatus() + "',rebootstatus='"
				+ atmc.getRebootstatus() + "',autodownload='"
				+ atmc.getAutodownload() + "',downloadtime='"
				+ atmc.getDownloadtime() + "',netmonitorstatus='"
				+ atmc.getNetmonitorstatus() + "',netmonitortime='"
				+ atmc.getNetmonitortime() + "'";
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

package cn.sh.ae.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.sh.ae.inteface.IAtmtypeDao;
import cn.sh.ae.vo.Atminfo;

public class AtmtypeDao implements IAtmtypeDao {

	private static final long serialVersionUID = 5652911355944618595L;
	static Logger logger = Logger.getLogger(AtmtypeDao.class.getName());

	@Override
	public void deleteAtmType(Connection con, Atminfo atmType) {
		String sql = "delete from atmtype where id='" + atmType.getId() + "'";
		// logger.info(sql);
		try {
			con.createStatement().executeUpdate(sql);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	@Override
	public List<Atminfo> getAtmTypeList(Connection con) {
		List<Atminfo> atmTypeList = null;
		Atminfo atmType = null;
		String sql = "select * from atmtype order by id";
		try {
			ResultSet rs = con.createStatement().executeQuery(sql);
			atmTypeList = new ArrayList<Atminfo>();
			while (rs.next()) {
				atmType = new Atminfo();
				atmType.setId(rs.getString("id"));
				atmType.setChname(rs.getString("type_Ch"));
				atmType.setEnname(rs.getString("type_En"));
				atmTypeList.add(atmType);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		return atmTypeList;
	}

	@Override
	public void setAtmType(Connection con, Atminfo atmType) {
		String sql = "insert into atmtype values('" + atmType.getId() + "','"
				+ atmType.getChname() + "','" + atmType.getEnname() + "')";
		// logger.info(sql);
		try {
			con.createStatement().executeUpdate(sql);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	@Override
	public Atminfo getAtmType(Connection con, Atminfo atmType) {
		Atminfo atmtype = null;
		String sql = "select * from atmtype where id='" + atmType.getId() + "'";
		try {
			ResultSet rs = con.createStatement().executeQuery(sql);
			while (rs.next()) {
				atmtype = new Atminfo();
				atmtype.setId(rs.getString("id"));
				atmtype.setChname(rs.getString("type_Ch"));
				atmtype.setEnname(rs.getString("type_En"));
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		return atmtype;
	}

	@Override
	public void modifyAtmType(Connection con, Atminfo atmType) {
		String sql = "update atmtype set type_ch='" + atmType.getChname()
				+ "', type_en='" + atmType.getEnname() + "' where id='"
				+ atmType.getId() + "'";
//		 logger.info(sql);
		try {
			con.createStatement().executeUpdate(sql);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}

	}

}

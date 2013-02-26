package cn.sh.ae.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.sh.ae.inteface.IAtmcompanyDao;
import cn.sh.ae.vo.Atminfo;

public class AtmcompanyDao implements IAtmcompanyDao {

	private static final long serialVersionUID = 1491146977155864053L;
	static Logger logger = Logger.getLogger(AtmcompanyDao.class.getName());

	@Override
	public void deleteAtmCompany(Connection con, Atminfo atmcompany) {
		String sql = "delete from atmcompany where id='" + atmcompany.getId()
				+ "'";
		// logger.info(sql);
		try {
			con.createStatement().executeUpdate(sql);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	@Override
	public List<Atminfo> getAtmCompanyList(Connection con) {
		List<Atminfo> atmCompanyList = null;
		Atminfo atmCompany = null;
		String sql = "select * from atmcompany order by id";
		try {
			ResultSet rs = con.createStatement().executeQuery(sql);
			atmCompanyList = new ArrayList<Atminfo>();
			while (rs.next()) {
				atmCompany = new Atminfo();
				atmCompany.setId(rs.getString("id"));
				atmCompany.setChname(rs.getString("company_ch"));
				atmCompany.setEnname(rs.getString("company_en"));
				atmCompanyList.add(atmCompany);
			}
		} catch (SQLException e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		return atmCompanyList;
	}

	@Override
	public Atminfo getAtmCompany(Connection con, Atminfo atmcompany) {
		Atminfo atmCompany = null;
		String sql = "select * from atmcompany where id='" + atmcompany.getId()
				+ "'";
		try {
			ResultSet rs = con.createStatement().executeQuery(sql);
			while (rs.next()) {
				atmCompany = new Atminfo();
				atmCompany.setId(rs.getString("id"));
				atmCompany.setChname(rs.getString("company_ch"));
				atmCompany.setEnname(rs.getString("company_en"));
			}
		} catch (SQLException e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		return atmCompany;
	}

	@Override
	public void setAtmCompany(Connection con, Atminfo atmcompany) {
		String sql = "insert into atmcompany values('" + atmcompany.getId()
				+ "','" + atmcompany.getChname() + "','"
				+ atmcompany.getEnname() + "')";
		// logger.info(sql);
		try {
			con.createStatement().executeUpdate(sql);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	@Override
	public void modifyAtmCompany(Connection con, Atminfo atmcompany) {
		String sql = "update atmcompany set company_ch='"
				+ atmcompany.getChname() + "', company_en='"
				+ atmcompany.getEnname() + "' where id='" + atmcompany.getId()
				+ "'";
		// logger.info(sql);
		try {
			con.createStatement().executeUpdate(sql);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

}

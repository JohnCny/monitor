package cn.sh.ae.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.sh.ae.inteface.IBankTypeDao;
import cn.sh.ae.vo.Atminfo;

public class BankTypeDao implements IBankTypeDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2184225531347204569L;
	static Logger logger = Logger.getLogger(AtmcompanyDao.class.getName());

	@Override
	public void deleteBankType(Connection con, Atminfo banktype) {
		String sql = "delete from banktype where id='" + banktype.getId() + "'";
		// logger.info(sql);
		try {
			con.createStatement().executeUpdate(sql);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	@Override
	public Atminfo getBankType(Connection con, Atminfo banktype) {
		Atminfo bankType = null;
		String sql = "select * from banktype where id='" + banktype.getId()
				+ "'";
		try {
			ResultSet rs = con.createStatement().executeQuery(sql);
			while (rs.next()) {
				bankType = new Atminfo();
				bankType.setId(rs.getString("id"));
				bankType.setChname(rs.getString("name_ch"));
				bankType.setEnname(rs.getString("name_en"));
			}
		} catch (SQLException e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		return bankType;
	}

	@Override
	public List<Atminfo> getBankTypeList(Connection con) {
		List<Atminfo> bankListList = null;
		Atminfo bankList = null;
		String sql = "select * from banktype order by id";
		try {
			ResultSet rs = con.createStatement().executeQuery(sql);
			bankListList = new ArrayList<Atminfo>();
			while (rs.next()) {
				bankList = new Atminfo();
				bankList.setId(rs.getString("id"));
				bankList.setChname(rs.getString("name_ch"));
				bankList.setEnname(rs.getString("name_en"));
				bankListList.add(bankList);
			}
		} catch (SQLException e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		return bankListList;
	}

	@Override
	public void modifyBankType(Connection con, Atminfo banktype) {
		String sql = "update banktype set name_ch='" + banktype.getChname()
				+ "', name_en='" + banktype.getEnname() + "' where id='"
				+ banktype.getId() + "'";
		// logger.info(sql);
		try {
			con.createStatement().executeUpdate(sql);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}

	}

	@Override
	public void setBankType(Connection con, Atminfo banktype) {
		String sql = "insert into banktype values('" + banktype.getId()
				+ "','" + banktype.getChname() + "','"
				+ banktype.getEnname() + "')";
		// logger.info(sql);
		try {
			con.createStatement().executeUpdate(sql);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}

	}

}

//package cn.sh.ae.dao;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.log4j.Logger;
//
//import cn.sh.ae.inteface.IAtmrunDao;
//import cn.sh.ae.vo.Atminfo;
//
//public class AtmrunDao implements IAtmrunDao {
//
//	private static final long serialVersionUID = 1464861819509730830L;
//	static Logger logger = Logger.getLogger(AtmrunDao.class.getName());
//
//	@Override
//	public void deleteAtmRun(Connection con, Atminfo runType) {
//		String sql = "delete from runtype where id='" + runType.getId() + "'";
//		// logger.info(sql);
//		try {
//			con.createStatement().executeUpdate(sql);
//		} catch (Exception e) {
//			logger.error(e.getLocalizedMessage(), e);
//		}
//	}
//
//	@Override
//	public List<Atminfo> getAtmRunList(Connection con) {
//		List<Atminfo> atmRunList = null;
//		Atminfo atmRun = null;
//		String sql = "select * from runtype order by id";
//		try {
//			ResultSet rs = con.createStatement().executeQuery(sql);
//			atmRunList = new ArrayList<Atminfo>();
//			while (rs.next()) {
//				atmRun = new Atminfo();
//				atmRun.setId(rs.getString("id"));
//				atmRun.setChname(rs.getString("run_ch"));
//				atmRun.setEnname(rs.getString("run_en"));
//				atmRunList.add(atmRun);
//			}
//		} catch (SQLException e) {
//			logger.error(e.getLocalizedMessage(), e);
//		}
//		return atmRunList;
//	}
//
//	@Override
//	public void setAtmRun(Connection con, Atminfo runType) {
//		String sql = "insert into runtype values('" + runType.getId() + "','"
//				+ runType.getChname() + "','" + runType.getEnname() + "')";
//		// logger.info(sql);
//		try {
//			con.createStatement().executeUpdate(sql);
//		} catch (Exception e) {
//			logger.error(e.getLocalizedMessage(), e);
//		}
//	}
//
//	@Override
//	public Atminfo getAtmRun(Connection con, Atminfo runType) {
//		Atminfo atmRun = null;
//		String sql = "select * from runtype where id='" + runType.getId()
//				+ "'";
//		try {
//			ResultSet rs = con.createStatement().executeQuery(sql);
//			while (rs.next()) {
//				atmRun = new Atminfo();
//				atmRun.setId(rs.getString("id"));
//				atmRun.setChname(rs.getString("run_ch"));
//				atmRun.setEnname(rs.getString("run_en"));
//			}
//		} catch (SQLException e) {
//			logger.error(e.getLocalizedMessage(), e);
//		}
//		return atmRun;
//	}
//
//	@Override
//	public void modifyRunType(Connection con, Atminfo runType) {
//		String sql = "update runtype set run_ch='" + runType.getChname()
//				+ "', run_en='" + runType.getEnname() + "' where id='"
//				+ runType.getId() + "'";
//		// logger.info(sql);
//		try {
//			con.createStatement().executeUpdate(sql);
//		} catch (Exception e) {
//			logger.error(e.getLocalizedMessage(), e);
//		}
//	}
//
//}

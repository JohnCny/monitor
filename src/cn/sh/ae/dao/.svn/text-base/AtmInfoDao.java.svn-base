//package cn.sh.ae.dao;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.log4j.Logger;
//
//import cn.sh.ae.inteface.IAtmInfoDao;
//import cn.sh.ae.vo.Atmcompany;
//import cn.sh.ae.vo.Atmtype;
//import cn.sh.ae.vo.Runtype;
//import cn.sh.ae.vo.Unittype;
//
//public class AtmInfoDao implements IAtmInfoDao {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -3834962133245248389L;
//
//	static Logger logger = Logger.getLogger(AtmInfoDao.class.getName());
//
//	@Override
//	public List<Atmcompany> getAtmCompanyList(Connection conn) {
//		List<Atmcompany> atmCompanyList = null;
//		Atmcompany atmCompany = null;
//		try {
//			ResultSet rs = conn.createStatement().executeQuery(
//					"select * from atmcompany order by id");
//			atmCompanyList=new ArrayList<Atmcompany>();
//			while (rs.next()) {
//				atmCompany = new Atmcompany();
//				atmCompany.setId(rs.getString("id"));
//				atmCompany.setCompanyCh(rs.getString("company_Ch"));
//				atmCompany.setCompanyEn(rs.getString("company_En"));
//				atmCompanyList.add(atmCompany);
//			}
//		} catch (Exception e) {
//			logger.error(e.getLocalizedMessage(), e);
//		}
//		return atmCompanyList;
//	}
//
//	@Override
//	public List<Atmtype> getAtmTypeList(Connection conn) {
//		List<Atmtype> atmTypeList = null;
//		Atmtype atmType = null;
//		try {
//			ResultSet rs = conn.createStatement().executeQuery(
//					"select * from atmtype order by id");
//			atmTypeList=new ArrayList<Atmtype>();
//			while (rs.next()) {
//				atmType = new Atmtype();
//				atmType.setId(rs.getString("id"));
//				atmType.setTypeCh(rs.getString("type_Ch"));
//				atmType.setTypeEn(rs.getString("type_En"));
//				atmTypeList.add(atmType);
//			}
//		} catch (Exception e) {
//			logger.error(e.getLocalizedMessage(), e);
//		}
//		return atmTypeList;
//	}
//
//	@Override
//	public List<Runtype> getRunTypeList(Connection conn) {
//		List<Runtype> runTypeList = null;
//		Runtype runType = null;
//		try {
//			ResultSet rs = conn.createStatement().executeQuery(
//					"select * from runtype order by id");
//			runTypeList=new ArrayList<Runtype>();
//			while (rs.next()) {
//				runType = new Runtype();
//				runType.setId(rs.getString("id"));
//				runType.setRunCh(rs.getString("run_Ch"));
//				runType.setRunEn(rs.getString("run_En"));
//				runTypeList.add(runType);
//			}
//		} catch (Exception e) {
//			logger.error(e.getLocalizedMessage(), e);
//		}
//		return runTypeList;
//	}
//
//	@Override
//	public List<Unittype> getUnitTypeList(Connection conn) {
//		List<Unittype> unitTypeList = null;
//		Unittype unitType = null;
//		try {
//			ResultSet rs = conn.createStatement().executeQuery(
//					"select * from unittype order by id");
//			unitTypeList=new ArrayList<Unittype>();
//			while (rs.next()) {
//				unitType = new Unittype();
//				unitType.setId(rs.getString("id"));
//				unitType.setUnitCh(rs.getString("unit_Ch"));
//				unitType.setUnitEn(rs.getString("unit_En"));
//				unitTypeList.add(unitType);
//			}
//		} catch (Exception e) {
//			logger.error(e.getLocalizedMessage(), e);
//		}
//		return unitTypeList;
//	}
//
//}

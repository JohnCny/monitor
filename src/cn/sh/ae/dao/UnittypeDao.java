package cn.sh.ae.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.sh.ae.inteface.IUnittypeDao;
import cn.sh.ae.vo.Unittype;

public class UnittypeDao implements IUnittypeDao {

	
	private static final long serialVersionUID = 1156729614382775577L;
	static Logger logger = Logger.getLogger(UnittypeDao.class.getName());

	@Override
	public List<Unittype> getUnitTypeList(Connection conn) {
		List<Unittype> unitTypeList = null;
		Unittype unitType = null;
		try {
			ResultSet rs = conn.createStatement().executeQuery(
					"select * from unittype order by id");
			unitTypeList=new ArrayList<Unittype>();
			while (rs.next()) {
				unitType = new Unittype();
				unitType.setId(rs.getString("id"));
				unitType.setUnitCh(rs.getString("unit_Ch"));
				unitType.setUnitEn(rs.getString("unit_En"));
				unitTypeList.add(unitType);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		return unitTypeList;
	}

}

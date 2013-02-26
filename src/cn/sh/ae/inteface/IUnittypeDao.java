package cn.sh.ae.inteface;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import cn.sh.ae.vo.Unittype;

public interface IUnittypeDao extends Serializable {
	
	public List<Unittype> getUnitTypeList(Connection conn);

}

package cn.sh.ae.inteface;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import cn.sh.ae.vo.Atminfo;

public interface IAtmcompanyDao extends Serializable {

	public List<Atminfo> getAtmCompanyList(Connection con);

	public Atminfo getAtmCompany(Connection con, Atminfo atmcompany);

	public void setAtmCompany(Connection con, Atminfo atmcompany);
	
	public void modifyAtmCompany(Connection con, Atminfo atmcompany);

	public void deleteAtmCompany(Connection con, Atminfo atmcompany);

}

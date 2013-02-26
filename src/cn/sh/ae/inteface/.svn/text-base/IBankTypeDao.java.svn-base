package cn.sh.ae.inteface;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import cn.sh.ae.vo.Atminfo;

public interface IBankTypeDao extends Serializable{


	public List<Atminfo> getBankTypeList(Connection con);

	public Atminfo getBankType(Connection con, Atminfo banktype);

	public void setBankType(Connection con, Atminfo banktype);
	
	public void modifyBankType(Connection con, Atminfo banktype);

	public void deleteBankType(Connection con, Atminfo banktype);

}

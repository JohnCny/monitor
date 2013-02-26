package cn.sh.ae.inteface;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import cn.sh.ae.vo.Atminfo;

public interface IAtmrunDao extends Serializable {

	public List<Atminfo> getAtmRunList(Connection con);

	public Atminfo getAtmRun(Connection con, Atminfo runType);

	public void modifyRunType(Connection con, Atminfo runType);

	public void setAtmRun(Connection con, Atminfo runType);

	public void deleteAtmRun(Connection con, Atminfo runType);
}

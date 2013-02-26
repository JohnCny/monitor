package cn.sh.ae.inteface;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import cn.sh.ae.vo.Atm;
import cn.sh.ae.vo.User;

public interface IAtmDao extends Serializable {
	// 根据ATMID获取ATM状态
	public Atm getAtmByAtmId(Connection conn, String atmid);

	/**
	 * 根据获取ATM状态
	 * 
	 * @param ds
	 *            数据源
	 * @param atm
	 *            机器类型
	 * @param style
	 *            显示方式
	 * @param type
	 *            显示类型
	 */
	public List<Atm> getAtmStatus(Connection conn, int level, String style,
			String type);

	// 根据ATM生产厂商获取ATM编号
	public List<Atm> getAtmByCompany(Connection conn, String company);

	// 根据ATMID获取地址
	public Atm getAtmAddressByAtmid(Connection conn, String atmid);

	// 获取前一次变化状态
	public Atm getChangeAtm(Connection conn, Atm atm);

	// 获取缺钞加钞变化状态
	public Atm getBoxChangeAtm(Connection conn, Atm atm);

	// 获取ATM基本信息
	public List<Atm> getAtmList(Connection con,int level);

	// 设置地址
	public void setAtmAddress(Connection conn, Atm atm);

	// 获取库存信息
	public List<Atm> getMoneyStatus(Connection conn, String self,
			String[] atmid, String qcash, String ccash);

	// // 获取历史交易量
	// public List<Atm> getHisMoney(DataSource ds,String self);
	// // 获取历史交易量(自选)
	// public List<Atm> getHisMoneySelf(DataSource ds, String[] atmid);

	public List<Atm> saveOrUpdateAtm(Connection conn, List<Atm> list);

	public void saveOrUpdateAtm(Connection conn, Atm atm);

	public void updateAtmTradetime(Connection conn, Atm atm);

	// 获取运营类型
	public List<Atm> getType(Connection conn, int level);

	// 获取ATM厂商
	public List<Atm> getAtmCompany(Connection conn);

	public List<Atm> getAtmInfo(Connection conn, String type, String route,
			String[] atmids);

	public void setLineStatus(Connection conn, String subTime);

	public List<Atm> getOuttimeLineStatus(Connection conn, String subTime);

	public List<Atm> getBugAtmList(Connection conn);

	public void setDayCheck(Connection conn, List<Atm> list);

	public List<Atm> getDayCheck(Connection conn, String[] atmids,
			String begintime, String type);

	public Atm getChangeStatusAtm(Connection conn, Atm atm);
	
	public List<Atm> getNoNetBugAtmList(Connection con);
	
	public void deleteAtm(Connection conn, Atm atm);

	public List<Atm> getMoneyValidAtm(Connection conn);

	
	/******************************************************************/
}

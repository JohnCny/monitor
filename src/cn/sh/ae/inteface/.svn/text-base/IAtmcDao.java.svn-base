package cn.sh.ae.inteface;

import java.io.Serializable;
import java.sql.Connection;

import cn.sh.ae.vo.Atmc;

public interface IAtmcDao extends Serializable {
	/**
	 * 获取C端的状态信息
	 */
	public Atmc getAtmcStatus(Connection con);
	/**
	 * 设置C端的状态信息
	 */
	public int setAtmcStatus(Connection con, Atmc atmc);
}

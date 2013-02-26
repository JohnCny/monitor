package cn.sh.ae.inteface;

import java.io.Serializable;
import java.sql.Connection;

import cn.sh.ae.vo.Sms;

public interface ISmsDao extends Serializable{
	
	/**
	 * 获取状态信息
	 */
	public Sms getSmsStatus(Connection con);
	/**
	 * 设置状态信息
	 */
	public int setSmsStatus(Connection con, Sms sms);

}

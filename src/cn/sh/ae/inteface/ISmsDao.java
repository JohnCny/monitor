package cn.sh.ae.inteface;

import java.io.Serializable;
import java.sql.Connection;

import cn.sh.ae.vo.Sms;

public interface ISmsDao extends Serializable{
	
	/**
	 * ��ȡ״̬��Ϣ
	 */
	public Sms getSmsStatus(Connection con);
	/**
	 * ����״̬��Ϣ
	 */
	public int setSmsStatus(Connection con, Sms sms);

}

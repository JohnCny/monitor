package cn.sh.ae.inteface;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import cn.sh.ae.vo.Smsuser;

public interface ISmsuserDao extends Serializable {
	public List<Smsuser> getSmsUser(Connection conn);

	public void createSms(Connection conn, Smsuser smsuser);

	public Smsuser getUserByMoblie(Connection conn, Smsuser smsuser);

	// �޸��û�
	public void modifyUser(Connection conn, Smsuser smsuser);

	public void deleteMoblie(Connection conn, Smsuser smsuser);

	public List<String> getAtmIdByType(Connection conn, int level);

	public List<Smsuser> getSmsListByType(Connection conn, String type);

	public List<Smsuser> lsgetMobileByAtmId(Connection conn, String atmId, String type,
			String level);
	
	public Smsuser getMobileByAtmId(Connection conn, String atmId, String type,
			String level);
	/** ����atmId ������Ӧ��ϵ�ֻ� */
	public Smsuser getMobileByAtmId(Connection conn, String atmId);

}
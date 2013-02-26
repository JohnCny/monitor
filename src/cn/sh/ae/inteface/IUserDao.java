package cn.sh.ae.inteface;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import cn.sh.ae.vo.User;

public interface IUserDao extends Serializable {

	// ��ȡuser�б�
	public List<User> getUser(Connection conns);

	// �����û�
	public void createUser(Connection conn, User user);

	// �޸��û�
	public void modifyUser(Connection conn, User user);
	
	//ɾ���û�
	public void deleteUser(Connection conn, User user);

	// ��ȡָ���û���
	public User getUserByUserName(Connection conn, String userName);

	// ����
	public User isLogin(Connection conn, String userName, String password);
}

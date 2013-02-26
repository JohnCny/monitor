package cn.sh.ae.inteface;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import cn.sh.ae.vo.User;

public interface IUserDao extends Serializable {

	// 获取user列表
	public List<User> getUser(Connection conns);

	// 创建用户
	public void createUser(Connection conn, User user);

	// 修改用户
	public void modifyUser(Connection conn, User user);
	
	//删除用户
	public void deleteUser(Connection conn, User user);

	// 获取指定用户名
	public User getUserByUserName(Connection conn, String userName);

	// 登入
	public User isLogin(Connection conn, String userName, String password);
}

package cn.sh.ae.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.sh.ae.inteface.IUserDao;
import cn.sh.ae.util.MyConstant;
import cn.sh.ae.util.MyTime;
import cn.sh.ae.vo.User;

/**
 * 
 */
public class UserDao implements IUserDao {

	private static final long serialVersionUID = 6336770615477882938L;

	static Logger logger = Logger.getLogger(UserDao.class.getName());

	@Override
	public List<User> getUser(Connection conn) {
		List<User> list = null;
		try {
			ResultSet rs = conn
					.createStatement()
					.executeQuery(
							"select * from sysuser,banktype where sysuser.name=banktype.id");
			list = new ArrayList<User>();
			while (rs.next()) {
				User user = new User();
				user.setCreatetime(rs
						.getString(MyConstant.Table.User.CREATETIME));
				user.setEmail(rs.getString(MyConstant.Table.User.EMAIL));
				user.setName(rs.getString("name_ch"));
				user.setEmlevel1(rs.getInt(MyConstant.Table.User.NAME));
				user.setPassword(rs.getString(MyConstant.Table.User.PASSWORD));
				user.setPhone(rs.getString(MyConstant.Table.User.PHONE));
				user.setStatus(rs.getInt(MyConstant.Table.User.STATUS));
				user.setUsername(rs.getString(MyConstant.Table.User.USERNAME));
				user.setTitle(rs.getInt(MyConstant.Table.User.TITLE));
				user.setUmlevel(rs.getInt(MyConstant.Table.User.UMLEVEL));
				// int emlevel = rs.getInt(MyConstant.Table.User.EMLEVEL);
				// String emlevelStr = String.valueOf(emlevel);
				// user.setEmlevel(emlevel);
				// user.setEmlevel1(Integer.valueOf(emlevelStr.substring(0,1)));
				// user.setEmlevel2(Integer.valueOf(emlevelStr.substring(1,2)));
				// user.setEmlevel3(Integer.valueOf(emlevelStr.substring(2,3)));
				user.setCmlevel(rs.getInt(MyConstant.Table.User.CMLEVEL));
				user.setCtlevel(rs.getInt(MyConstant.Table.User.CTLEVEL));
				user.setSalevel(rs.getInt(MyConstant.Table.User.SALEVEL));
				user.setRmlevel(rs.getInt(MyConstant.Table.User.RMLEVEL));
				user.setSclevel(rs.getInt(MyConstant.Table.User.SCLEVEL));
				list.add(user);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			list = null;
		}
		return list;
	}

	@Override
	public void createUser(Connection conn, User user) {
		String sql = "insert into sysuser values('" + user.getUsername()
				+ "','" + user.getPassword() + "','" + user.getName() + "',"
				+ user.getTitle() + ",'" + user.getPhone() + "','"
				+ user.getEmail() + "','"
				+ MyTime.getTime("yyyy-MM-dd HH:mm:ss") + "',"
				+ user.getStatus() + "," + user.getUmlevel() + ","
				+ user.getEmlevel() + "," + user.getCmlevel() + ","
				+ user.getSalevel() + "," + user.getCtlevel() + ","
				+ user.getRmlevel() + "," + user.getSclevel() + ")";
		try {
			conn.createStatement().executeUpdate(sql);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	@Override
	public void modifyUser(Connection conn, User user) {
		String sql = "update sysuser set password='" + user.getPassword()
				+ "',name='" + user.getName() + "',title=" + user.getTitle()
				+ ",phone='" + user.getPhone() + "',email='" + user.getEmail()
				+ "',createtime='" + MyTime.getTime("yyyy-MM-dd HH:mm:ss")
				+ "',status=" + user.getStatus() + ",umlevel="
				+ user.getUmlevel() + ",emlevel=" + user.getEmlevel()
				+ ",cmlevel=" + user.getCmlevel() + ",salevel="
				+ user.getSalevel() + ",ctlevel=" + user.getCtlevel()
				+ ",rmlevel=" + user.getRmlevel() + ",sclevel="
				+ user.getSclevel() + " where username='" + user.getUsername()
				+ "'";
		// System.out.println(sql);
		try {
			conn.createStatement().executeUpdate(sql);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	@Override
	public User getUserByUserName(Connection conn, String userName) {
		User user = null;
		try {
			ResultSet rs = conn
					.createStatement()
					.executeQuery(
							"select * from sysuser,banktype where sysuser.name=banktype.id and sysuser.username='"
									+ userName + "'");
			while (rs.next()) {
				user = new User();
				user.setCreatetime(rs
						.getString(MyConstant.Table.User.CREATETIME));
				user.setEmail(rs.getString(MyConstant.Table.User.EMAIL));
				user.setUsername(rs.getString(MyConstant.Table.User.USERNAME));
				user.setPassword(rs.getString(MyConstant.Table.User.PASSWORD));
				user.setPhone(rs.getString(MyConstant.Table.User.PHONE));
				user.setStatus(rs.getInt(MyConstant.Table.User.STATUS));
				user.setName(rs.getString("name_ch"));
				user.setEmlevel1(rs.getInt(MyConstant.Table.User.NAME));
				user.setTitle(rs.getInt(MyConstant.Table.User.TITLE));
				user.setUmlevel(rs.getInt(MyConstant.Table.User.UMLEVEL));
				user.setCmlevel(rs.getInt(MyConstant.Table.User.CMLEVEL));
				user.setCtlevel(rs.getInt(MyConstant.Table.User.CTLEVEL));
				// int emlevel = rs.getInt(MyConstant.Table.User.EMLEVEL);
				// String emlevelStr = String.valueOf(emlevel);
				// user.setEmlevel(emlevel);
				// user.setEmlevel1(Integer.valueOf(emlevelStr.substring(0,1)));
				// user.setEmlevel2(Integer.valueOf(emlevelStr.substring(1,2)));
				// user.setEmlevel3(Integer.valueOf(emlevelStr.substring(2,3)));
				user.setRmlevel(rs.getInt(MyConstant.Table.User.RMLEVEL));
				user.setSalevel(rs.getInt(MyConstant.Table.User.SALEVEL));
				user.setSclevel(rs.getInt(MyConstant.Table.User.SCLEVEL));
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			user = null;
		}
		return user;
	}

	@Override
	public User isLogin(Connection conn, String userName, String password) {
		User user = null;
		String sql = "select * from sysuser,banktype where sysuser.name=banktype.id and username='"
				+ userName + "' and password='" + password + "'";
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			logger.info(sql);
			while (rs.next()) {
				user = new User();
				user.setUsername(rs.getString(MyConstant.Table.User.USERNAME));
				user.setPassword(rs.getString(MyConstant.Table.User.PASSWORD));
				user.setStatus(rs.getInt(MyConstant.Table.User.STATUS));
				user.setName(rs.getString("name_ch"));
				user.setTitle(rs.getInt(MyConstant.Table.User.NAME));
				user.setUmlevel(rs.getInt(MyConstant.Table.User.UMLEVEL));
				user.setCmlevel(rs.getInt(MyConstant.Table.User.CMLEVEL));
				user.setCtlevel(rs.getInt(MyConstant.Table.User.CTLEVEL));
				// int emlevel = rs.getInt(MyConstant.Table.User.EMLEVEL);
				// String emlevelStr = String.valueOf(emlevel);
				// user.setEmlevel(emlevel);
				// user.setEmlevel1(Integer.valueOf(emlevelStr.substring(0,1)));
				// user.setEmlevel2(Integer.valueOf(emlevelStr.substring(1,2)));
				// user.setEmlevel3(Integer.valueOf(emlevelStr.substring(2,3)));
				user.setRmlevel(rs.getInt(MyConstant.Table.User.RMLEVEL));
				user.setSalevel(rs.getInt(MyConstant.Table.User.SALEVEL));
				user.setSclevel(rs.getInt(MyConstant.Table.User.SCLEVEL));
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			user = null;
		}
		return user;
	}

	@Override
	public void deleteUser(Connection conn, User user) {
		String sql = "delete from sysuser  where username='"
				+ user.getUsername() + "'";
		try {
			conn.createStatement().executeUpdate(sql);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

}

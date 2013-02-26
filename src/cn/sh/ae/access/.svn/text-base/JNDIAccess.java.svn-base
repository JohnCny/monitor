package cn.sh.ae.access;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import cn.sh.ae.inteface.IDBAccess;
import cn.sh.ae.vo.Atmp;

public class JNDIAccess implements IDBAccess {
	/**
	 * 
	 */
	private static final long serialVersionUID = -114081653890403433L;
	static Logger logger = Logger.getLogger(JNDIAccess.class.getName());
	protected Connection conn = null;

	@Override
	public void closeConnection() {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	@Override
	public Connection getSystemConnection() {
		ConnectionManager cm = ConnectionManager.getInstance();
		conn = cm.getConnection();
		// InitialContext cxt;
		// try {
		// cxt = new InitialContext();
		// DataSource ds = (DataSource) cxt
		// .lookup("java:/comp/env/jdbc/systemDB");
		// conn = ds.getConnection();
		// } catch (Exception e) {
		// logger.error(e.getLocalizedMessage(),e);
		// }
		return conn;
	}

	@Override
	public Connection getAtmpConnection(Atmp p) {
		return null;
	}
	
	public static void main(String[] args){
		Connection conn=new JNDIAccess().getSystemConnection();
		try {
			ResultSet rs=conn.createStatement().executeQuery("select * from atm");
			while(rs.next()){
				System.out.println("qqq");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

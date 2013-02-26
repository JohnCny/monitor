package cn.sh.ae.access;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

public final class ConnectionManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3758734134979579322L;

	static Logger logger = Logger.getLogger(ConnectionManager.class.getName());

	private static ConnectionManager instance;

	private ComboPooledDataSource ds;

	private ConnectionManager() throws Exception {
		ds = new ComboPooledDataSource();
		//测试
//		 ds.setDriverClass("com.mysql.Driver");
//		 ds.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/localatm");
//		 ds.setUser("johnny");
//		 ds.setPassword("112358");
		//
		//兴化
//		 ds.setDriverClass("com.ibm.db2.jcc.DB2Driver");
//		 ds.setJdbcUrl("jdbc:db2://127.0.0.1:50000/XHATM");
//		 ds.setUser("administrator");
//		 ds.setPassword("zt720625");
		//句容
		 ds.setDriverClass("com.ibm.db2.jcc.DB2Driver");
		 ds.setJdbcUrl("jdbc:db2://127.0.0.1:50000/JRATM");
		 ds.setUser("Administrator");
		 ds.setPassword("jsrun");
//		 
		//姜堰
//		 ds.setDriverClass("com.ibm.db2.jcc.DB2Driver");
//		 ds.setJdbcUrl("jdbc:db2://127.0.0.1:50000/JYATM");
//		 ds.setUser("Administrator");
//		 ds.setPassword("kjxxb");

		// 初始化时获取连接，取值应在minPoolSize与maxPoolSize之间。Default: 3 initialPoolSize
		ds.setInitialPoolSize(0);
		// 连接池中保留的最大连接数。Default: 15 maxPoolSize
		ds.setMaxPoolSize(300);
		// // 连接池中保留的最小连接数。
		ds.setMinPoolSize(20);
		// 当连接池中的连接耗尽的时候c3p0一次同时获取的连接数。Default: 3 acquireIncrement
		ds.setAcquireIncrement(10);

		// 每60秒检查所有连接池中的空闲连接。Default: 0 idleConnectionTestPeriod
		ds.setIdleConnectionTestPeriod(60);
		ds.setAutoCommitOnClose(true);

		ds.setTestConnectionOnCheckout(true);
		// 如果设为true那么在取得连接的同时将校验连接的有效性。Default: false testConnectionOnCheckin
		ds.setTestConnectionOnCheckin(true);

		// 定义在从数据库获取新连接失败后重复尝试的次数。Default: 30 acquireRetryAttempts
		ds.setAcquireRetryAttempts(30);
		// 两次连接中间隔时间，单位毫秒。Default: 1000 acquireRetryDelay
		ds.setAcquireRetryDelay(1000);
	}

	public static final ConnectionManager getInstance() {
		if (instance == null) {
			try {
				instance = new ConnectionManager();
			} catch (Exception e) {
				logger.error(e.getLocalizedMessage(), e);
			}
		}
		return instance;
	}

	public synchronized final Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		return null;
	}

	public void finalize() throws Throwable {
		DataSources.destroy(ds); // 关闭datasource
		super.finalize();
	}

}
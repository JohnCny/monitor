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
		//����
//		 ds.setDriverClass("com.mysql.Driver");
//		 ds.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/localatm");
//		 ds.setUser("johnny");
//		 ds.setPassword("112358");
		//
		//�˻�
//		 ds.setDriverClass("com.ibm.db2.jcc.DB2Driver");
//		 ds.setJdbcUrl("jdbc:db2://127.0.0.1:50000/XHATM");
//		 ds.setUser("administrator");
//		 ds.setPassword("zt720625");
		//����
		 ds.setDriverClass("com.ibm.db2.jcc.DB2Driver");
		 ds.setJdbcUrl("jdbc:db2://127.0.0.1:50000/JRATM");
		 ds.setUser("Administrator");
		 ds.setPassword("jsrun");
//		 
		//����
//		 ds.setDriverClass("com.ibm.db2.jcc.DB2Driver");
//		 ds.setJdbcUrl("jdbc:db2://127.0.0.1:50000/JYATM");
//		 ds.setUser("Administrator");
//		 ds.setPassword("kjxxb");

		// ��ʼ��ʱ��ȡ���ӣ�ȡֵӦ��minPoolSize��maxPoolSize֮�䡣Default: 3 initialPoolSize
		ds.setInitialPoolSize(0);
		// ���ӳ��б����������������Default: 15 maxPoolSize
		ds.setMaxPoolSize(300);
		// // ���ӳ��б�������С��������
		ds.setMinPoolSize(20);
		// �����ӳ��е����Ӻľ���ʱ��c3p0һ��ͬʱ��ȡ����������Default: 3 acquireIncrement
		ds.setAcquireIncrement(10);

		// ÿ60�����������ӳ��еĿ������ӡ�Default: 0 idleConnectionTestPeriod
		ds.setIdleConnectionTestPeriod(60);
		ds.setAutoCommitOnClose(true);

		ds.setTestConnectionOnCheckout(true);
		// �����Ϊtrue��ô��ȡ�����ӵ�ͬʱ��У�����ӵ���Ч�ԡ�Default: false testConnectionOnCheckin
		ds.setTestConnectionOnCheckin(true);

		// �����ڴ����ݿ��ȡ������ʧ�ܺ��ظ����ԵĴ�����Default: 30 acquireRetryAttempts
		ds.setAcquireRetryAttempts(30);
		// ���������м��ʱ�䣬��λ���롣Default: 1000 acquireRetryDelay
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
		DataSources.destroy(ds); // �ر�datasource
		super.finalize();
	}

}
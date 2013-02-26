package cn.sh.ae.manage;

import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;

import org.apache.log4j.Logger;

import cn.sh.ae.factory.AccessFactory;
import cn.sh.ae.vo.Atmp;

public class ConnectManager implements Serializable {
	static Logger logger = Logger.getLogger(ConnectManager.class.getName());

	/**
	 * 
	 */
	private static final long serialVersionUID = 3891363300384027146L;

	/**
	 * ͨ�����û�ñ������ݿ�����
	 */
	public static Connection getSystemDB() {
		return AccessFactory.getJNDIAccess().getSystemConnection();
	}

	/**
	 * ͨ�����������е����û��Զ�����ݿ�����
	 */
	// public static Connection getRemoteDB() {
	// Atmp atmp = DataManager.getAtmpStatus();
	// // setAtmp(atmp);
	// return AccessFactory.getKSDBAccess().getAtmpConnection(atmp);
	// }
	public static ServerSocket getAtmpServer() {
		Atmp atmp = DataManager.getAtmpStatus();
		return AccessFactory.getDataSocketAccess().getDataSocket(
				Integer.parseInt(atmp.getDbport()));
	}

	//
	public static Socket getAtmClient(String ip, int port, int timeout) {
//		logger.info("��ʼ���ӣ�" + ip + ":" + port + ":" + timeout);
		return AccessFactory.getSocketAccess().getSocket(ip, port, timeout);
	}
}

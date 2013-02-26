package cn.sh.ae.comm;

import java.io.Serializable;
import java.net.Socket;

import org.apache.log4j.Logger;

import cn.sh.ae.manage.ConnectManager;

public class Ping implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8804663705123954644L;
	static Logger logger = Logger.getLogger(Ping.class.getName());

	public boolean ping(String ip, int port) {
		// int timeout = 3000; // 超时应该在3钞以上
		// try {
		// return InetAddress.getByName(
		// (ip == null && ip.equals("")) ? "0" : ip).isReachable(
		// timeout);
		// } catch (UnknownHostException e) {
		// logger.error(e.getLocalizedMessage(), e);
		// return false;
		// } catch (IOException e) {
		// logger.error(e.getLocalizedMessage(), e);
		// return false;
		// }

		int timeout = 3000; // 超时应该在3钞以上
		Socket server = ConnectManager.getAtmClient(ip, port, timeout);
		if (server != null)
			return true;
		else
			return false;

	}

	public static void main(String[] args) {
		System.out.println(new Ping().ping("132.2.22.13", 1702));
	}
}

package cn.sh.ae.comm;

import java.net.Socket;

import org.apache.log4j.Logger;

import cn.sh.ae.manage.ConnectManager;
import cn.sh.ae.tools.AtmcClient;
import cn.sh.ae.vo.Host;

public class Down implements Runnable {

	static Logger logger = Logger.getLogger(Down.class.getName());

	// public static void main(String[] arg) {
	// Host host = new Host();
	// host.setIp("198.2.4.146");
	// host.setPort(1702);
	// host.setTimeout(20000);
	// // host.setFileName("ATMÖÐÇï»­Ãæ.jpg");
	// host.setRemoteRoute("e:\\" + MyFile.getEjrPathByDate() + ".TXT");
	// String route = "TXT" + MyFile.getEjrPathByDate() + "\\"
	// + MyFile.getEjrPathByDate() + ".TXT";
	// host.setLocalRoute("D:\\EJR\\" + route);
	//
	// System.out.println("e:\\" + MyFile.getEjrPathByDate() + ".TXT");
	// System.out.println("D:\\EJR\\" + route);
	//
	// new Thread(new Down(host)).start();
	// }

	private Socket socket = null;

	private String ip;
	private int port;
	private int timeout;
	private String localRoute;
	private String remoteRoute;

	private String fileName;

	public Down(Host host) {
		this.ip = host.getIp().trim();
		this.port = host.getPort();
		this.timeout = host.getTimeout();
		this.fileName = host.getFileName().trim();
		this.localRoute = host.getRemoteRoute().trim();
		this.remoteRoute = host.getLocalRoute().trim();
	}

	@Override
	public void run() {
		try {
			down();
		} catch (Exception e) {
			down();
		} finally {
			AtmcClient.close(socket);
		}
	}

	private void down() {
		logger.info(ip + ":" + port + ":");
		socket = ConnectManager.getAtmClient(ip, port, timeout);
		AtmcClient.setResponse(socket, 1);
		int i = AtmcClient.getRequest(socket);
		if (i == 1) {
			AtmcClient.sendMessage(socket, this.remoteRoute);
			AtmcClient.receiveFile(socket, this.localRoute, this.fileName);
			AtmcClient.setResponse(socket, 0);
		}
		AtmcClient.close(socket);
	}
}

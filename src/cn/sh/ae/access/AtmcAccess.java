package cn.sh.ae.access;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import cn.sh.ae.inteface.ISocketAccess;

/**
 * 
 */
public class AtmcAccess implements ISocketAccess {

	private static final long serialVersionUID = 7368616180685099496L;
	static Logger logger = Logger.getLogger(AtmcAccess.class.getName());

	private Socket socket = null;
	//自动连接次数
	private int linkSize = 3;

	@Override
	public void closeSocket() {
		// TODO Auto-generated method stub

	}

	@Override
	public Socket getSocket(String ip, int port, int connectTimeout) {
		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress(ip, port), connectTimeout);
		} catch (Exception e) {
			if (socket != null) {
				try {
					socket.close();
					// 等待10秒后重新连接一次
					Thread.sleep(5000);
					if (--linkSize != 0)
						getSocket(ip, port, connectTimeout);
				} catch (Exception e1) {
					logger.error(e1.getLocalizedMessage(), e1);
				}
				socket = null;
			}

//			logger.error(ip + ":" + e.getLocalizedMessage());
		}
		return socket;
	}

	@Override
	public ServerSocket getDataSocket(int port) {
		// TODO Auto-generated method stub
		return null;
	}

}

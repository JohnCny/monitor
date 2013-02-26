package cn.sh.ae.access;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import cn.sh.ae.inteface.ISocketAccess;

public class AtmpDataAccess implements ISocketAccess {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1696142501562757345L;

	static Logger logger = Logger.getLogger(AtmpDataAccess.class.getName());

	private ServerSocket socket = null;

	@Override
	public void closeSocket() {
		// TODO Auto-generated method stub

	}

	@Override
	public ServerSocket getDataSocket(int port) {
		 try {
			socket = new ServerSocket(port);
		} catch (IOException e) {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e1) {
					logger.error(e1.getLocalizedMessage(), e1);
				}
				socket = null;
			}
			logger.error(e.getLocalizedMessage(), e);
		}
		return socket;
	}

	@Override
	public Socket getSocket(String ip, int port, int connectTimeout) {
		// TODO Auto-generated method stub
		return null;
	}

}

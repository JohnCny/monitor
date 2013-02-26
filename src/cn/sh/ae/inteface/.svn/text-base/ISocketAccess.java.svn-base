package cn.sh.ae.inteface;

import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public interface ISocketAccess extends Serializable {
	public Socket getSocket(String ip, int port, int connectTimeout) ;
	
	public ServerSocket getDataSocket(int port);

	public void closeSocket();

}

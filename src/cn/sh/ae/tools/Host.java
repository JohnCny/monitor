package cn.sh.ae.tools;

import java.io.Serializable;

public class Host implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2014424818693835961L;
	//���涓绘�IP
	private String hostIP;
	//���涓绘�绔��
	private int hostPort;
	//���瓒���堕�
	private int timeout;
	//浼��缂��
	private String encoding;

	public String getHostIP() {
		return hostIP;
	}

	public void setHostIP(String hostIP) {
		this.hostIP = hostIP;
	}

	public int getHostPort() {
		return hostPort;
	}

	public void setHostPort(int hostPort) {
		this.hostPort = hostPort;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

}

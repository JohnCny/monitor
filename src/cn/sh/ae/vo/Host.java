package cn.sh.ae.vo;

import java.io.Serializable;

public class Host implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6829245588671324800L;

	private String ip;
	private int port;
	private int timeout;
	private String localRoute;
	private String remoteRoute;
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String getLocalRoute() {
		return localRoute;
	}

	public void setLocalRoute(String localRoute) {
		this.localRoute = localRoute;
	}

	public String getRemoteRoute() {
		return remoteRoute;
	}

	public void setRemoteRoute(String remoteRoute) {
		this.remoteRoute = remoteRoute;
	}

}

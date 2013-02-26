//package cn.sh.ae.util;
//
//import java.io.Serializable;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//public class MyThread implements Serializable {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -4255209163937014742L;
//	private ExecutorService exec = null;
//
//	public void setThreadPool(int size) {
//		this.exec = Executors.newFixedThreadPool(size);
//	}
//
//	public ExecutorService getExec() {
//		return exec;
//	}
//	
//	public void shutdownExec(){
//		exec.shutdown();
//	}
//
//}

package cn.sh.ae.factory;

import java.io.Serializable;

import cn.sh.ae.access.AtmcAccess;
import cn.sh.ae.access.AtmpDataAccess;
import cn.sh.ae.access.JNDIAccess;
import cn.sh.ae.inteface.IDBAccess;
import cn.sh.ae.inteface.ISocketAccess;

/**
 * 
 */
public class AccessFactory implements Serializable {

	private static final long serialVersionUID = 6261331991834779950L;

	public static ISocketAccess getSocketAccess() {
		return new AtmcAccess();
	}
	
	public static ISocketAccess getDataSocketAccess() {
		return new AtmpDataAccess();
	}

//	public static IDBAccess getKSDBAccess() {
//		return new AtmpAccess();
//	}

	public static IDBAccess getJNDIAccess() {
		return new JNDIAccess();
	}

}

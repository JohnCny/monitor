package cn.sh.ae.tools;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

import cn.sh.ae.vo.Atm;

public class SmsQueue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5600424988309807424L;
	private static Queue<Atm> queue = new LinkedList<Atm>();

	public static Queue<Atm> getQueue() {
		if (queue == null)
			queue = new LinkedList<Atm>();
		return queue;
	}

}

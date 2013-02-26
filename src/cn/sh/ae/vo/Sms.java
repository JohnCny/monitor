package cn.sh.ae.vo;

import java.io.Serializable;

public class Sms implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6363150275619267841L;
	private String smsstatus;
	private String firsttime;
	private String secondtime;
	private String thirdtime;
	public String getSmsstatus() {
		return smsstatus;
	}
	public void setSmsstatus(String smsstatus) {
		this.smsstatus = smsstatus;
	}
	public String getFirsttime() {
		return firsttime;
	}
	public void setFirsttime(String firsttime) {
		this.firsttime = firsttime;
	}
	public String getSecondtime() {
		return secondtime;
	}
	public void setSecondtime(String secondtime) {
		this.secondtime = secondtime;
	}
	public String getThirdtime() {
		return thirdtime;
	}
	public void setThirdtime(String thirdtime) {
		this.thirdtime = thirdtime;
	}


}

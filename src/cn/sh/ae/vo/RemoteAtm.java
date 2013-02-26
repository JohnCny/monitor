package cn.sh.ae.vo;

import java.io.Serializable;

/**
 * 
 */
public class RemoteAtm implements Serializable {

	private static final long serialVersionUID = 3419739011716580399L;
	private String SBH;
	private String CX;
	private String JYRQ;
	private String BWSJ;
	private String DQZT;
	private String YBZS;
	private String WSZS;

	public String getSBH() {
		return SBH;
	}

	public void setSBH(String sbh) {
		SBH = sbh;
	}

	public String getCX() {
		return CX;
	}

	public void setCX(String cx) {
		CX = cx;
	}

	public String getJYRQ() {
		return JYRQ;
	}

	public void setJYRQ(String jyrq) {
		JYRQ = jyrq;
	}

	public String getBWSJ() {
		return BWSJ;
	}

	public void setBWSJ(String bwsj) {
		BWSJ = bwsj;
	}

	public String getDQZT() {
		return DQZT;
	}

	public void setDQZT(String dqzt) {
		DQZT = dqzt;
	}

	public String getYBZS() {
		return YBZS;
	}

	public void setYBZS(String ybzs) {
		YBZS = ybzs;
	}

	public String getWSZS() {
		return WSZS;
	}

	public void setWSZS(String wszs) {
		WSZS = wszs;
	}

}

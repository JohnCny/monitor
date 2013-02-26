package cn.sh.ae.pojo;

import java.io.Serializable;

public class TradeStatePojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8508735886285505394L;

	private String id;
	private String ck;
	private String qk;
	private String zz;

	public String getCk() {
		return ck;
	}

	public void setCk(String ck) {
		this.ck = ck;
	}

	public String getQk() {
		return qk;
	}

	public void setQk(String qk) {
		this.qk = qk;
	}

	public String getZz() {
		return zz;
	}

	public void setZz(String zz) {
		this.zz = zz;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}

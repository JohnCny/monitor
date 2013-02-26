package cn.sh.ae.vo;

public class AtmTradataMoney implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1853546868816939585L;
	private String atmid;
	private String atmid_new;
	private float money;
	private String tradetime;

	public String getAtmid() {
		return atmid;
	}

	public void setAtmid(String atmid) {
		this.atmid = atmid;
	}

	public String getAtmid_new() {
		return atmid_new;
	}

	public void setAtmid_new(String atmid_new) {
		this.atmid_new = atmid_new;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public String getTradetime() {
		return tradetime;
	}

	public void setTradetime(String tradetime) {
		this.tradetime = tradetime;
	}

}

package cn.sh.ae.vo;

/**
 * 
 */

public class Smsuser implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = -8880596545558319035L;
	private String moblie;
	private String moblie_old;
	private String name;
	private String atmids;
	private String bugtype;
	private String status;
	private String solvetime;
	private String solvetime_old;
	private String group;

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMoblie() {
		return this.moblie;
	}

	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAtmids() {
		return atmids;
	}

	public void setAtmids(String atmids) {
		this.atmids = atmids;
	}

	public String getBugtype() {
		return bugtype;
	}

	public void setBugtype(String e) {
		this.bugtype = e;
	}

	public String getSolvetime() {
		return solvetime;
	}

	public void setSolvetime(String solvetime) {
		this.solvetime = solvetime;
	}

	public String getMoblie_old() {
		return moblie_old;
	}

	public void setMoblie_old(String moblie_old) {
		this.moblie_old = moblie_old;
	}

	public String getSolvetime_old() {
		return solvetime_old;
	}

	public void setSolvetime_old(String solvetime_old) {
		this.solvetime_old = solvetime_old;
	}

}
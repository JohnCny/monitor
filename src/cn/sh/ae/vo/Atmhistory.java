package cn.sh.ae.vo;


/**
 * 
 */
public class Atmhistory implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 8810524893519783150L;
	private String atmId;
	private String bugId;
	private String bugBeg;
	private String bugEnd;
	private String subTime;
	private String constant;
	private String bugType;
	private String sendType;


	public String getAtmId() {
		return atmId;
	}

	public void setAtmId(String atmId) {
		this.atmId = atmId;
	}

	public String getBugId() {
		return bugId;
	}

	public void setBugId(String bugId) {
		this.bugId = bugId;
	}

	public String getBugBeg() {
		return bugBeg;
	}

	public void setBugBeg(String bugBeg) {
		this.bugBeg = bugBeg;
	}

	public String getBugEnd() {
		return bugEnd;
	}

	public void setBugEnd(String bugEnd) {
		this.bugEnd = bugEnd;
	}

	public String getSubTime() {
		return subTime;
	}

	public void setSubTime(String subTime) {
		this.subTime = subTime;
	}

	public String getConstant() {
		return constant;
	}

	public void setConstant(String constant) {
		this.constant = constant;
	}

	public String getBugType() {
		return bugType;
	}

	public void setBugType(String bugType) {
		this.bugType = bugType;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

}
package cn.sh.ae.vo;

/**
 * 
 */
public class Atmtradata implements java.io.Serializable {

	private static final long serialVersionUID = -6204561146804685056L;
	private String deptcode;
	private String atmid;
	private String tradetime;
	private String tradehour;
	private String tradetype;
	private String cardno;
	private String cardclass;
	private float tradermb;
	private float tradecount;
	private float avgrmb;
	private float number;
	private float avgcount;
	private String sendstatus;
	private String devno;
	private String reserve;

	public String getDeptcode() {
		return deptcode;
	}

	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}

	public String getAtmid() {
		return atmid;
	}

	public void setAtmid(String atmid) {
		this.atmid = atmid;
	}

	public String getTradetype() {
		return tradetype;
	}

	public void setTradetype(String tradetype) {
		this.tradetype = tradetype;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getCardclass() {
		return cardclass;
	}

	public void setCardclass(String cardclass) {
		this.cardclass = cardclass;
	}

	public String getSendstatus() {
		return sendstatus;
	}

	public void setSendstatus(String sendstatus) {
		this.sendstatus = sendstatus;
	}

	public String getReserve() {
		return reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
	}

	public String getDevno() {
		return devno;
	}

	public void setDevno(String devno) {
		this.devno = devno;
	}

	public String getTradetime() {
		return tradetime;
	}

	public void setTradetime(String tradetime) {
		this.tradetime = tradetime;
	}

	public String getTradehour() {
		return tradehour;
	}

	public void setTradehour(String tradehour) {
		this.tradehour = tradehour;
	}

	public float getTradermb() {
		return tradermb;
	}

	public void setTradermb(float tradermb) {
		this.tradermb = tradermb;
	}

	public float getTradecount() {
		return tradecount;
	}

	public void setTradecount(float tradecount) {
		this.tradecount = tradecount;
	}

	public float getAvgrmb() {
		return avgrmb;
	}

	public void setAvgrmb(float avgrmb) {
		this.avgrmb = avgrmb;
	}

	public float getAvgcount() {
		return avgcount;
	}

	public void setAvgcount(float avgcount) {
		this.avgcount = avgcount;
	}

	public float getNumber() {
		return number;
	}

	public void setNumber(float number) {
		this.number = number;
	}

}
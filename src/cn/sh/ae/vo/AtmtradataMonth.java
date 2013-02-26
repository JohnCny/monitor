package cn.sh.ae.vo;

/**
 * 
 */
public class AtmtradataMonth implements java.io.Serializable {

	private static final long serialVersionUID = 2644454495601574224L;
	// Fields

	private String atmid;
	private String tradeyear;
	private String trademonth;
	private String tradetype;
	private String cardclass;
	private Double tradermb;

	public String getAtmid() {
		return this.atmid;
	}

	public void setAtmid(String atmid) {
		this.atmid = atmid;
	}

	public String getTradeyear() {
		return this.tradeyear;
	}

	public void setTradeyear(String tradeyear) {
		this.tradeyear = tradeyear;
	}

	public String getTrademonth() {
		return this.trademonth;
	}

	public void setTrademonth(String trademonth) {
		this.trademonth = trademonth;
	}

	public String getTradetype() {
		return this.tradetype;
	}

	public void setTradetype(String tradetype) {
		this.tradetype = tradetype;
	}

	public String getCardclass() {
		return this.cardclass;
	}

	public void setCardclass(String cardclass) {
		this.cardclass = cardclass;
	}

	public Double getTradermb() {
		return this.tradermb;
	}

	public void setTradermb(Double tradermb) {
		this.tradermb = tradermb;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AtmtradataMonth))
			return false;
		AtmtradataMonth castOther = (AtmtradataMonth) other;

		return ((this.getAtmid() == castOther.getAtmid()) || (this.getAtmid() != null
				&& castOther.getAtmid() != null && this.getAtmid().equals(
				castOther.getAtmid())))
				&& ((this.getTradeyear() == castOther.getTradeyear()) || (this
						.getTradeyear() != null
						&& castOther.getTradeyear() != null && this
						.getTradeyear().equals(castOther.getTradeyear())))
				&& ((this.getTrademonth() == castOther.getTrademonth()) || (this
						.getTrademonth() != null
						&& castOther.getTrademonth() != null && this
						.getTrademonth().equals(castOther.getTrademonth())))
				&& ((this.getTradetype() == castOther.getTradetype()) || (this
						.getTradetype() != null
						&& castOther.getTradetype() != null && this
						.getTradetype().equals(castOther.getTradetype())))
				&& ((this.getCardclass() == castOther.getCardclass()) || (this
						.getCardclass() != null
						&& castOther.getCardclass() != null && this
						.getCardclass().equals(castOther.getCardclass())))
				&& ((this.getTradermb() == castOther.getTradermb()) || (this
						.getTradermb() != null
						&& castOther.getTradermb() != null && this
						.getTradermb().equals(castOther.getTradermb())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAtmid() == null ? 0 : this.getAtmid().hashCode());
		result = 37 * result
				+ (getTradeyear() == null ? 0 : this.getTradeyear().hashCode());
		result = 37
				* result
				+ (getTrademonth() == null ? 0 : this.getTrademonth()
						.hashCode());
		result = 37 * result
				+ (getTradetype() == null ? 0 : this.getTradetype().hashCode());
		result = 37 * result
				+ (getCardclass() == null ? 0 : this.getCardclass().hashCode());
		result = 37 * result
				+ (getTradermb() == null ? 0 : this.getTradermb().hashCode());
		return result;
	}

}
package cn.sh.ae.vo;

/**
 * UnittypeId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Unittype implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -172950113787324424L;
	private String id;
	private String unitCh;
	private String unitEn;

	

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUnitCh() {
		return this.unitCh;
	}

	public void setUnitCh(String unitCh) {
		this.unitCh = unitCh;
	}

	public String getUnitEn() {
		return this.unitEn;
	}

	public void setUnitEn(String unitEn) {
		this.unitEn = unitEn;
	}

	

}
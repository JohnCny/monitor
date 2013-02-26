package cn.sh.ae.vo;

/**
 * 
 */

public class Atmp implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = -1292035354737342248L;
	private String dbip;
	private String dbport;
	private String dbusername;
	private String dbpassword;
	private String dbname;
	private String sycntime;
	private String mgrttime;
	private String sycnstatus;
	private String mgrtstatus;
	private String mgrtpath;
	private String ftpip;
	private String ftpport;
	private String ftppath;
	private String ftpusername;
	private String ftppassword;
	private String maxoutmoney;//最大取款金额
	private String maxinmoney;//最大存款金额
	
	// Property accessors
	public String getMaxoutmoney() {
		return maxoutmoney;
	}

	public void setMaxoutmoney(String maxoutmoney) {
		this.maxoutmoney = maxoutmoney;
	}

	public String getMaxinmoney() {
		return maxinmoney;
	}

	public void setMaxinmoney(String maxinmoney) {
		this.maxinmoney = maxinmoney;
	}
	
	public String getDbip() {
		return this.dbip;
	}

	public void setDbip(String dbip) {
		this.dbip = dbip;
	}

	public String getDbport() {
		return this.dbport;
	}

	public void setDbport(String dbport) {
		this.dbport = dbport;
	}

	public String getDbusername() {
		return this.dbusername;
	}

	public void setDbusername(String dbusername) {
		this.dbusername = dbusername;
	}

	public String getDbpassword() {
		return this.dbpassword;
	}

	public void setDbpassword(String dbpassword) {
		this.dbpassword = dbpassword;
	}

	public String getDbname() {
		return this.dbname;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getFtpip() {
		return ftpip;
	}

	public void setFtpip(String ftpip) {
		this.ftpip = ftpip;
	}

	public String getFtpport() {
		return ftpport;
	}

	public void setFtpport(String ftpport) {
		this.ftpport = ftpport;
	}

	public String getFtppath() {
		return ftppath;
	}

	public void setFtppath(String ftppath) {
		this.ftppath = ftppath;
	}

	public String getFtpusername() {
		return ftpusername;
	}

	public void setFtpusername(String ftpusername) {
		this.ftpusername = ftpusername;
	}

	public String getFtppassword() {
		return ftppassword;
	}

	public void setFtppassword(String ftppassword) {
		this.ftppassword = ftppassword;
	}

	public String getSycntime() {
		return this.sycntime;
	}

	public void setSycntime(String sycntime) {
		this.sycntime = sycntime;
	}

	public String getMgrttime() {
		return this.mgrttime;
	}

	public void setMgrttime(String mgrttime) {
		this.mgrttime = mgrttime;
	}

	public String getSycnstatus() {
		return this.sycnstatus;
	}

	public void setSycnstatus(String sycnstatus) {
		this.sycnstatus = sycnstatus;
	}

	public String getMgrtstatus() {
		return this.mgrtstatus;
	}

	public void setMgrtstatus(String mgrtstatus) {
		this.mgrtstatus = mgrtstatus;
	}

	public String getMgrtpath() {
		return mgrtpath;
	}

	public void setMgrtpath(String mgrtpath) {
		this.mgrtpath = mgrtpath;
	}

	
}
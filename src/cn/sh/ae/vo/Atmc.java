package cn.sh.ae.vo;

/**
 * 
 */

public class Atmc implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 3342297546570585657L;
	private String uploadstatus;
	private String downloadstatus;
	private String rebootstatus;
	private String autodownload;
	private String downloadtime;
	private String netmonitorstatus;
	private String netmonitortime;

	public String getAutodownload() {
		return autodownload;
	}

	public void setAutodownload(String autodownload) {
		this.autodownload = autodownload;
	}

	public String getUploadstatus() {
		return uploadstatus;
	}

	public void setUploadstatus(String uploadstatus) {
		this.uploadstatus = uploadstatus;
	}

	public String getDownloadstatus() {
		return downloadstatus;
	}

	public void setDownloadstatus(String downloadstatus) {
		this.downloadstatus = downloadstatus;
	}

	public String getRebootstatus() {
		return rebootstatus;
	}

	public void setRebootstatus(String rebootstatus) {
		this.rebootstatus = rebootstatus;
	}

	public String getDownloadtime() {
		return downloadtime;
	}

	public void setDownloadtime(String downloadtime) {
		this.downloadtime = downloadtime;
	}

	public String getNetmonitorstatus() {
		return netmonitorstatus;
	}

	public void setNetmonitorstatus(String netmonitorstatus) {
		this.netmonitorstatus = netmonitorstatus;
	}

	public String getNetmonitortime() {
		return netmonitortime;
	}

	public void setNetmonitortime(String netmonitortime) {
		this.netmonitortime = netmonitortime;
	}

}
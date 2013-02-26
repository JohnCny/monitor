package cn.sh.ae.vo;

/**
 * 
 */
public class User implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = -4135734441597972057L;
	private String username="";
	private String password="";
	private String name="";
	private int title=0;
	private String phone="";
	private String email="";
	private String createtime="";
	private int status=0;
	private int umlevel=0;
	private int emlevel=0;
	private int emlevel1=0;
	private int emlevel2=0;
	private int emlevel3=0;
	private int cmlevel=0;
	private int salevel=0;
	private int ctlevel=0;
	private int rmlevel=0;
	private int sclevel=0;

	// Constructors

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public int getTitle() {
		return title;
	}

	public void setTitle(int title) {
		this.title = title;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getUmlevel() {
		return umlevel;
	}

	public void setUmlevel(int umlevel) {
		this.umlevel = umlevel;
	}

	public int getCmlevel() {
		return cmlevel;
	}

	public void setCmlevel(int cmlevel) {
		this.cmlevel = cmlevel;
	}

	public int getSalevel() {
		return salevel;
	}

	public void setSalevel(int salevel) {
		this.salevel = salevel;
	}

	public int getCtlevel() {
		return ctlevel;
	}

	public void setCtlevel(int ctlevel) {
		this.ctlevel = ctlevel;
	}

	public int getRmlevel() {
		return rmlevel;
	}

	public void setRmlevel(int rmlevel) {
		this.rmlevel = rmlevel;
	}

	public int getSclevel() {
		return sclevel;
	}

	public void setSclevel(int sclevel) {
		this.sclevel = sclevel;
	}

	public int getEmlevel() {
		return emlevel;
	}

	public void setEmlevel(int emlevel) {
		this.emlevel = emlevel;
	}

	public int getEmlevel1() {
		return emlevel1;
	}

	public void setEmlevel1(int emlevel1) {
		this.emlevel1 = emlevel1;
	}

	public int getEmlevel2() {
		return emlevel2;
	}

	public void setEmlevel2(int emlevel2) {
		this.emlevel2 = emlevel2;
	}

	public int getEmlevel3() {
		return emlevel3;
	}

	public void setEmlevel3(int emlevel3) {
		this.emlevel3 = emlevel3;
	}


}
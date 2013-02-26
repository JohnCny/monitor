package cn.sh.ae.vo;

import java.util.Comparator;

/**
 * 
 */
public class Atm implements Comparator {

	private static final long serialVersionUID = 943360360977007033L;
	private String atmid; // ATMID
	private String atmid_new; // 虚拟柜员号
	private String tradetime; // 报文时间
	private String box; // 钞箱
	private String partrmb; // 取款箱金额
	private String depormb; // 存款箱金额
	private String readerstatus; // 读卡器状态
	private String prjstatus; // 流水打印状态
	private String prrstatus; // 凭条打印状态
	private String cdmstatus;
	private String depstatus;
	private String demstatus;
	private String linestatus;
	private String allstatus;
	private String ip;
	private String port;
	private String ejr;
	private String picture;
	private String upload;
	private String encoding;
	private String timeout;
	private String route;
	private String type;
	private String addr;
	private String company;
	private String aesyspath;
	private String tracepath;
	private String maxoutmoney;//最大取款金额
	private String maxinmoney;//最大存款金额
	private boolean moneyvalid;//临时使用，检测是否钞箱金额异常使用

	public boolean isMoneyvalid() {
		return moneyvalid;
	}

	public void setMoneyvalid(boolean moneyvalid) {
		this.moneyvalid = moneyvalid;
	}

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

	public String getAesyspath() {
		return aesyspath;
	}

	public void setAesyspath(String aesyspath) {
		this.aesyspath = aesyspath;
	}

	public String getAtmid() {
		return atmid;
	}

	public void setAtmid(String atmid) {
		this.atmid = atmid;
	}

	public String getReaderstatus() {
		return readerstatus;
	}

	public void setReaderstatus(String readerstatus) {
		this.readerstatus = readerstatus;
	}

	public String getPrjstatus() {
		return prjstatus;
	}

	public void setPrjstatus(String prjstatus) {
		this.prjstatus = prjstatus;
	}

	public String getPrrstatus() {
		return prrstatus;
	}

	public void setPrrstatus(String prrstatus) {
		this.prrstatus = prrstatus;
	}

	public String getCdmstatus() {
		return cdmstatus;
	}

	public void setCdmstatus(String cdmstatus) {
		this.cdmstatus = cdmstatus;
	}

	public String getDepstatus() {
		return depstatus;
	}

	public void setDepstatus(String depstatus) {
		this.depstatus = depstatus;
	}

	public String getDemstatus() {
		return demstatus;
	}

	public void setDemstatus(String demstatus) {
		this.demstatus = demstatus;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getEjr() {
		return ejr;
	}

	public void setEjr(String ejr) {
		this.ejr = ejr;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getTradetime() {
		return tradetime;
	}

	public void setTradetime(String tradetime) {
		this.tradetime = tradetime;
	}

	public String getBox() {
		return box;
	}

	public void setBox(String box) {
		this.box = box;
	}

	public String getPartrmb() {
		return partrmb;
	}

	public void setPartrmb(String partrmb) {
		this.partrmb = partrmb;
	}

	public String getDepormb() {
		return depormb;
	}

	public void setDepormb(String depormb) {
		this.depormb = depormb;
	}

	public String getAllstatus() {
		return allstatus;
	}

	public void setAllstatus(String allstatus) {
		this.allstatus = allstatus;
	}

	public String getLinestatus() {
		return linestatus;
	}

	public void setLinestatus(String linestatus) {
		this.linestatus = linestatus;
	}

	public String getTracepath() {
		return tracepath;
	}

	public void setTracepath(String tracepath) {
		this.tracepath = tracepath;
	}

	@Override
	public int compare(Object o1, Object o2) {
		/* 判断是否为student类型 */
		Atm a1 = (Atm) o1;
		Atm a2 = (Atm) o2;
		return Integer.parseInt(a1.getAllstatus()) < Integer.parseInt(a2
				.getAllstatus()) ? 0 : 1;
		// 判断也可详细点
	}

	public String getAtmid_new() {
		return atmid_new;
	}

	public void setAtmid_new(String atmid_new) {
		this.atmid_new = atmid_new;
	}
}
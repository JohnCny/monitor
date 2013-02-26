package cn.sh.ae.tools;

import java.io.Serializable;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2611427085033398352L;
	//���浠ｇ�
	private String msgBody_SNO;
	//�佃��风�
	private String msgBody_PNO;
	//淇℃����
	private String msgBody_TEXT;

	public String getMsgBody_SNO() {
		return msgBody_SNO;
	}

	public void setMsgBody_SNO(String msgBody_SNO) {
		this.msgBody_SNO = msgBody_SNO;
	}

	public String getMsgBody_PNO() {
		return msgBody_PNO;
	}

	public void setMsgBody_PNO(String msgBody_PNO) {
		this.msgBody_PNO = msgBody_PNO;
	}

	public String getMsgBody_TEXT() {
		return msgBody_TEXT;
	}

	public void setMsgBody_TEXT(String msgBody_TEXT) {
		this.msgBody_TEXT = msgBody_TEXT;
	}

}

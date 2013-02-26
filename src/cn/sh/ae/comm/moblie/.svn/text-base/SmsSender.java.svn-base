package cn.sh.ae.comm.moblie;

import org.smslib.IOutboundMessageNotification;
import org.smslib.OutboundMessage;
import org.smslib.Message.MessageEncodings;
import org.smslib.modem.SerialModemGateway;

public class SmsSender {
	private static org.smslib.Service srv = new org.smslib.Service();
	SerialModemGateway gateway = null;
	private static SmsSender smsSender = new SmsSender(); // 本类单例

	public static SmsSender getInstance() {
		return smsSender;
	}

	public static void main(String[] args) {
		SmsSender sms = SmsSender.getInstance();
		try {
			sms.connect("COM1");
			for (int i = 0; i < 2; i++)
				sms.sendSms("13681853376", "你好");
			sms.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void connect(String com) throws Exception {
		gateway = new SerialModemGateway("SMS", com, 9600, "Nokia", "5800");
		// gateway.setInbound(true);
		gateway.setOutbound(true);
		srv.setOutboundMessageNotification(new OutboundNotification());
		srv.addGateway(gateway);
		srv.startService();
	}

	public void close() throws Exception {
		gateway.stopGateway();
		srv.stopService();
	}

	public void sendSms(String number, String info) throws Exception {
		OutboundMessage msg = new OutboundMessage(number, info);
		msg.setEncoding(MessageEncodings.ENCUCS2);
		srv.sendMessage(msg);
	}

}

class OutboundNotification implements IOutboundMessageNotification {
	public void process(String gatewayId, OutboundMessage msg) {
	}
}

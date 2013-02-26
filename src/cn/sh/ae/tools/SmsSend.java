package cn.sh.ae.tools;

import org.apache.log4j.Logger;
import org.smslib.IOutboundMessageNotification;
import org.smslib.Message;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.modem.SerialModemGateway;

public class SmsSend {

	static Logger logger = Logger.getLogger(SmsSend.class.getName());
	private Service srv = new Service();

	public static void main(String[] args) {
		SmsSend sms = new SmsSend();
		try {
			sms.connect(args[0]);
			sms.sendSms(args[1], args[2]);
			sms.close();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	public void connect(String com) {
		try {
			SerialModemGateway gateway = new SerialModemGateway("SMS", com,
					19200, "Asiaeagle", "");
			gateway.setOutbound(true);
			this.srv.setOutboundMessageNotification(new OutboundNotification());
			this.srv.addGateway(gateway);
			System.setProperty("sendsms.serial.polling",new String());
			this.srv.startService();
			logger.info("Modem connected.");
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	public void close() throws Exception {
		this.srv.stopService();
		logger.info("Modem closed.");
	}

	public void sendSms(String number, String info) {
		try {
			OutboundMessage msg = new OutboundMessage(number, info);
			msg.setEncoding(Message.MessageEncodings.ENCUCS2);
			this.srv.sendMessage(msg);
			logger.info("Send message success.");
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	public class OutboundNotification implements IOutboundMessageNotification {
		public void process(String gatewayId, OutboundMessage msg) {
			logger.info("Outbound handler called from Gateway: "
					+ gatewayId);
			logger.info(msg);
		}
	}
}

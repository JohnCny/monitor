package cn.sh.ae.bank.ks.data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import cn.sh.ae.manage.DataManager;
import cn.sh.ae.util.MyTime;
import cn.sh.ae.vo.Atm;

public class DataSource implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5818933174666215846L;
	
	static Logger logger = Logger.getLogger(DataSource.class.getName());

	
	/**
	 * ��ȡԶ�����ݿ�atm�仯��״̬�����Ա������ݿ���£�ͬʱ����״̬��ʷ���ѽ����Ͷ��Ÿ������Ա
	 */
	public static void sycnLocalDB(ServerSocket ssocket) {
		logger.info("��ʼͬ������");
		Socket socket = null;
		try {
			while (true) {
				socket = ssocket.accept();
				// logger.info(socket.getRemoteSocketAddress() + "����");
				new Thread(new MsgToData(socket){}).start();
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	} 

}

/**
 * ���Ľ��ܽ��
 */
class MsgToData implements Runnable {
	static Logger logger = Logger.getLogger(MsgToData.class.getName());

	// private String message = null;
	private Atm atm = null;
	private Socket socket = null;
	private BufferedReader reader = null;

	public MsgToData(Socket socket) {
		this.socket = socket;
		// this.message = message;
	}

	private void setMsgToDb() {
		if (this.socket != null) {
			try {
				while (true) {
					reader = new BufferedReader(new InputStreamReader(socket
							.getInputStream()));
					String message = null;
					if ((message = reader.readLine()) != null) {
						String[] msg = message.split("#");
						atm = new Atm();
						String tradeTime = MyTime.getTime("yyMMddHHmmss");
						// ��״̬��������ı���ֻҪ����ʱ�����·
						atm.setAtmid(msg[0]);
						atm.setTradetime(tradeTime);

						String prjstatus = msg[1].substring(0, 1);
						String prrstatus = msg[1].substring(1, 2);
						String readerstatus = msg[1].substring(2, 3);
						String depstatus = msg[1].substring(3, 4);
						String cdmstatus = msg[1].substring(4, 5);
						String box = msg[1].substring(5);

						atm.setDemstatus("0");
						atm.setPrjstatus(prjstatus.equals("f") ? "5"
								: prjstatus);
						atm.setPrrstatus(prrstatus.equals("f") ? "5"
								: prrstatus);
						atm.setReaderstatus(readerstatus.equals("f") ? "1"
								: readerstatus);
						atm.setDepstatus(depstatus.equals("f") ? "1"
								: depstatus);
						atm.setCdmstatus(cdmstatus.equals("f") ? "1"
								: cdmstatus);
						atm.setBox(box);
						atm.setLinestatus("0");
						int moneySize = 0;
						for (int i = 0; i < box.length(); i += 8) {
							String type_1 = box.substring(0 + i, 1 + i);
							String type = box.substring(1 + i, 2 + i);
							if (type_1.equals("o")
									&& (type.equals("2") || type.equals("3")))
								moneySize += Integer.parseInt(box.substring(
										i + 4, i + 8));
						}
						atm.setPartrmb(String.valueOf(moneySize));
						atm.setIp(socket.getInetAddress().toString().substring(
								1));
						atm.setDepormb("0");
						// logger.info("״̬��Ϣ��" + message);
						// ��״̬��Ϣѹ����Ŷ���
						// WorkManager.setInstantSms(atm);
						// ���жϱ����Ƿ��и���
						Atm changeAtm = DataManager.getChangeStatusAtm(atm);
						DataManager.saveOrUpdateAtm(atm);
						changeAtm.setAtmid(atm.getAtmid());
						changeAtm.setLinestatus("0");
						DataManager.setAtmHistory(changeAtm);
					}
				}
			} catch (Exception e) {
				logger.error(e.getLocalizedMessage(), e);
			}
		}
	}

	@Override
	public void run() {
		setMsgToDb();
	}
}

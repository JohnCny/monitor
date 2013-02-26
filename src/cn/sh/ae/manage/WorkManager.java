package cn.sh.ae.manage;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.log4j.Logger;

import cn.sh.ae.comm.Down;
import cn.sh.ae.comm.moblie.SmsSender;
import cn.sh.ae.tools.DoWork;
import cn.sh.ae.tools.FtpClient;
import cn.sh.ae.tools.LogReader;
import cn.sh.ae.tools.Message;
import cn.sh.ae.util.MyConstant;
import cn.sh.ae.util.MyFile;
import cn.sh.ae.util.MyTime;
import cn.sh.ae.vo.Atm;
import cn.sh.ae.vo.Atmhistory;
import cn.sh.ae.vo.Atmp;
import cn.sh.ae.vo.Atmtradata;
import cn.sh.ae.vo.Ejr;
import cn.sh.ae.vo.Host;
import cn.sh.ae.vo.Sms;
import cn.sh.ae.vo.SmsInfo;
import cn.sh.ae.vo.Smsuser;

public class WorkManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2784076355178029935L;

	static Logger logger = Logger.getLogger(WorkManager.class.getName());

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
				new Thread(new MsgToData(socket)).start();
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	/**
	 * ���ļ��ж�ȡ���������������ݿ�
	 */
	public static void mgrtLocalDB() {
		/** FTPԶ��·�� */
		Atmp atmp = DataManager.getAtmpStatus();

		FtpClient ftp = new FtpClient(atmp);
		ftp.ftpConnect();
		ftp.downloadFile();
		ftp.ftpClose();

		File localFile = MyFile.getFile(atmp.getMgrtpath());
		if (MyFile.isExist(localFile)) {
			try {
				// �־û�����
				DataManager.setAtmTradata(LogReader.saveLogToBase(localFile));
				// ���ݱ����ļ�
				MyFile.backUpFile(localFile);
			} catch (Exception e) {
				logger.error(e.getLocalizedMessage(), e);
			}
		}
		localFile = MyFile.getFile(atmp.getMgrtpath() + "_1");
		if (MyFile.isExist(localFile)) {
			try {
				// �־û�����
				DataManager.setAtmTradata_1(LogReader
						.saveLogToBase_1(localFile));
				// ���ݱ����ļ�
				MyFile.backUpFile(localFile);
			} catch (Exception e) {
				logger.error(e.getLocalizedMessage(), e);
			}
		}
		// logger.info("���±���״̬��");
		// // ��ȡ�����ļ�·��
		// String path = DataManager.getAtmpStatus().getMgrtpath();
		// DataManager.setAtmTradata(LogReader.saveLogToBase(path));
	}

	/**
	 * �ֶ������ļ�
	 */
	public static void DownFile(List<Atm> atmAddressList, String filePath) {
		int size = atmAddressList.size();
		if (size > 0) {
			Host host = null;
			for (int i = 0; i < size; i++) {
				host = new Host();
				host.setIp(atmAddressList.get(i).getIp());
				host.setPort(Integer.parseInt(atmAddressList.get(i).getPort()));
				String timeout = atmAddressList.get(i).getTimeout();

				host.setTimeout((timeout == null || timeout.equals("")) ? 10000
						: Integer.parseInt(timeout));
				String[] remPaths = filePath.split("\\\\");

				host.setLocalRoute(filePath);
				host.setFileName(remPaths[remPaths.length - 1]);
				// logger.info("��־����·����" + filePath);
				// logger.info("��־����·����" + MyConstant.TEMPPATH+"aaa.txt");
				host.setRemoteRoute(MyConstant.TEMPPATH);
				new Down(host).run();
			}
		}

	}

	/**
	 * �Զ������ļ�
	 */
	public static void autoDownEjr(List<Atm> atmAddressList, String date,
			String type, boolean sync) {
		// List<Atm> atmAddressList = DataManager.getAtmList();
		int size = atmAddressList.size();
		if (size > 0) {
			try {
				Host host = null;
				for (int i = 0; i < size; i++) {
					String ip = atmAddressList.get(i).getIp();
					String port = atmAddressList.get(i).getPort();
					String route = atmAddressList.get(i).getEjr();
					if (type != null && type.equals("2"))
						route = atmAddressList.get(i).getTracepath();
					String timeout = atmAddressList.get(i).getTimeout();
					if (ip != null && !ip.equals("") && port != null
							&& !port.equals("") && route != null
							&& !route.equals("")) {
						host = new Host();
						host.setIp(ip);
						host.setPort(Integer.parseInt(port));
						host
								.setTimeout((timeout == null || timeout
										.equals("")) ? 10000 : Integer
										.parseInt(timeout));
						if (date == null)
							date = MyTime.getPreDate("yyyyMMdd");
						route += date + File.separator + "Trans.jrn";

						host.setLocalRoute(route);
						String localRoute = MyConstant.ATMLOGPATH + date
								+ File.separator;
						host.setFileName(atmAddressList.get(i).getAtmid());
						// logger.info("��־����·����" + localRoute);
						// logger.info("��־����·����" + route);
						host.setRemoteRoute(localRoute);
						if (sync)
							new Down(host).run();
						else
							new Thread((new Down(host))).start();
					}
				}
			} catch (Exception e) {
				logger.error(e.getLocalizedMessage(), e);
			}
		}
	}

	/**
	 * ����
	 */
	public static HashMap<String, List<Ejr>> getCheckEjrAndDb(
			List<String> atmIdList, String time) {
		HashMap<String, List<Ejr>> map = new HashMap<String, List<Ejr>>();
		// ��ȡ�ɶ��˵���־�б�
		String date = null;

		if (time == null || time.equals(""))
			date = MyTime.getPreDate("yyyyMMdd");
		else
			date = MyTime.getFormatDate(time, "yyyy-MM-dd", "yyyyMMdd");
		File[] files = null;
		String path = MyConstant.ATMLOGPATH + date + File.separator;
		if (atmIdList == null || atmIdList.isEmpty())
			files = MyFile.getFileList(path);
		else {
			int size = atmIdList.size();
			files = new File[size];
			for (int i = 0; i < size; i++) {
				files[i] = MyFile.getFile(path + atmIdList.get(i));
			}
		}

		List<Ejr> ejrList = null;
		Ejr ejr = null;
		float allRmb_c = 0, allRmb_p = 0;
		List<Ejr> ejrTradataList = null;

		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			// �豸���
			String atmId = file.getName();
			if (file != null && file.length() != 0) {
				logger.info("��Ҫ���˵Ļ�����" + path + atmId);
				// ��ȡĳ��Ľ������
				List<Atmtradata> tradataList = DataManager.getAtmTradataByTime(
						atmId, time);
				if (tradataList != null && !tradataList.isEmpty()
						&& tradataList.size() != 0) {
					// ����
					// String company =
					// DataManager.getAtmById(atmId).getCompany();

					// ����ҵ���̨��¼��������ݲ�ͬ�ĳ��̽�����ˮ
					ejrTradataList = DataManager.getTradataFromEjr(file);

					logger.info("��ʼ����");
					// ��־ƥ����
					if (ejrTradataList != null && !ejrTradataList.isEmpty()
							&& ejrTradataList.size() != 0) {
						logger.info("�ļ���" + ejrTradataList.size());
						logger.info("���ݿ⣺" + tradataList.size());
						for (Atmtradata tradata : tradataList) {
							atmId = tradata.getAtmid();
							String cardNoDb = tradata.getCardno().trim();
							float tradataRmbDb = tradata.getTradermb();
							for (Ejr ejrTradata : ejrTradataList) {
								String cardNoDbEjr = ejrTradata.getCardNo()
										.trim();
								float tradataRmbDbEjr = ejrTradata
										.getTradermb();
								// ��־ƥ�佻��
								if (cardNoDb.equals(cardNoDbEjr)
										&& tradataRmbDb == tradataRmbDbEjr) {
									tradata.setCardno("0");
									ejrTradata.setCardNo("0");
									break;
								}
							}
						}

						// �ֱ��¼��ˮ�ͺ�̨��ƥ����
						ejrList = new ArrayList<Ejr>();
						for (Atmtradata tradata : tradataList) {
							allRmb_p += tradata.getTradermb();
							if (!tradata.getCardno().equals("0")) {
								ejr = new Ejr();
								ejr.setCardNo(tradata.getCardno());
								ejr.setTradetime_db(MyTime.getFormatDate(
										tradata.getTradehour(), "HHmmss",
										"HH:mm:ss"));
								ejr.setTradermb_db(tradata.getTradermb());
								ejrList.add(ejr);
							}
						}
						for (Ejr ejrTradata : ejrTradataList) {
							if (ejrTradata.getCardNo().equals("-1"))
								allRmb_c = ejrTradata.getTradermb();
							else if (!ejrTradata.getCardNo().equals("0")) {
								ejr = new Ejr();
								ejr.setCardNo(ejrTradata.getCardNo());
								ejr.setTradetime(ejrTradata.getTradetime());
								ejr.setTradermb(ejrTradata.getTradermb());
								ejr.setInput(ejrTradata.getInput());
								ejr.setDispense(ejrTradata.getDispense());
								ejr.setReject(ejrTradata.getReject());
								ejr.setTradermb(ejr.getTradermb());
								ejrList.add(ejr);
							}
						}

						map.put(atmId + "," + allRmb_c + "," + allRmb_p + ","
								+ (allRmb_p - allRmb_c), ejrList);
						allRmb_c = 0;
						allRmb_p = 0;
					} else {
						map.put(atmId + ":" + "����ATM��ˮ���ش���", null);
					}
				} else {
					map.put(atmId + ":" + "�����޺�̨���׼�¼", null);
				}
			} else {
				map.put(atmId + ":" + "�޵���ATM��ˮ", null);
			}
		}

		return map;
	}

	public static void setLineStatus() {
		logger.info("��ʼϵͳ������");
		String subTime = MyTime
				.subTimeByTime(MyTime.getTime("yyMMddHHmmss"), Long
						.parseLong(DataManager.getAtmcStatus()
								.getNetmonitortime()) * 1000 * 60,
						"yyMMddHHmmss");
		logger.info("��ֵʱ��:" + subTime);
		List<Atm> atmList = DataManager.getNoNetBugAtmList();
		// List<Atm> atmList = DataManager.getOutimeLinestatus(subTime);
		logger.info("����豸��:" + atmList.size());
		for (Atm atm : atmList) {
			// logger.info("���ܱ���ʱ��:" + atm.getTradetime());
			if (Long.parseLong(atm.getTradetime()) < Long.parseLong(subTime)) {
				logger.info("�ޱ����豸:" + atm.getAtmid());
				atm.setLinestatus("1");
				atm.setPrjstatus(null);
				atm.setPrrstatus(null);
				atm.setCdmstatus(null);
				atm.setDepstatus(null);
				atm.setReaderstatus(null);
				atm.setPartrmb(null);
				// if (!CommFactory.ping(atm.getIp(), Integer.parseInt(atm
				// .getPort()))) {
				// logger.info("�ޱ��ĺ����Ӳ��ɹ��豸:" + atm.getAtmid());
				DataManager.setAtmLinestatus(atm.getAtmid());
				DataManager.setAtmHistory(atm);
				
			}
		}
		// DataManager.setAtmLinestatus(subTime);
		// List<Atm> atmList = DataManager.getAtmList();
		// List<Atm> atmList_1 = new ArrayList<Atm>();
		// Atm atm_1 = null;
		// for (Atm atm : atmList) {
		// String atmId = atm.getAtmid();
		// atm_1 = new Atm();
		// String linestatus = "1";
		// String ip = atm.getIp();
		// String port = atm.getPort();
		// if (ip != null && !ip.equals("") && port != null
		// && !port.equals("")) {
		// if (CommFactory.ping(ip, Integer.parseInt(port))) {
		// linestatus = "0";
		// }
		// }
		// atm_1.setAtmid(atmId);
		// atm_1.setLinestatus(linestatus);
		// atmList_1.add(atm_1);
		// }
		// DataManager.setAtmLinestatus(atmList_1);
	}

	public static boolean sendSmsFlag = false;

	/** ��������è */
	public static boolean startGSMModem() {
		SmsSender smsSender = SmsSender.getInstance(); // ����ʵ��
		if (smsSender != null) {
			try {
				// ����
				 smsSender.connect("COM1");
				// ����
//				smsSender.connect("COM6");
				sendSmsFlag = true;
			} catch (Exception e) {
				logger.error(e.getLocalizedMessage(), e);
			}
		} else {
			logger.error("�˿��ѱ�ռ��");
		}
		return sendSmsFlag;
	}

	/** �رն���è */
	public static void stopGSMModem() {
		SmsSender smsSender = SmsSender.getInstance(); // ����ʵ��
		try {
			smsSender.close();
			sendSmsFlag = false;
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	/** ���Ͷ��� */
	public static void sendSms(String phone, String sendStr) {
		SmsSender smsSender = SmsSender.getInstance(); // ����ʵ��
		try {
			smsSender.sendSms(phone, sendStr);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	public static Queue<SmsInfo> smsQueue = new LinkedList<SmsInfo>();
	// ���Ӷ��Ŷ���
	public static void setTimingSms() {
		// ������è�����ɹ�ʱ���������
		logger.info("���Ӷ��Ŷ�������");
		while (sendSmsFlag) {
			if (MyTime.getDate().getHour() >= 7
					&& MyTime.getDate().getHour() < 18) {
				try {
					logger.info("��ǰʱ��"+MyTime.getDate().getHour()+"��");
					Thread.sleep(300000);
					if (MyTime.getDate().getHour()==0)
						continue;
					Sms ss = DataManager.getSmsStatus();
					if (ss.getSmsstatus() != null
							&& ss.getSmsstatus().equals("1")) {
						String fir = ss.getFirsttime();
						String sec = ss.getSecondtime();
						String thi = ss.getThirdtime();
						List<Atm> atmList = DataManager.getSendBugAtm();
						SmsInfo smsInfo = null;
						for (Atm atm : atmList) {
							String atmId = atm.getAtmid();
							String type = atm.getType();
							String typeStr = "����";
							String sendType = "1";
							if (!type.equals("1")) {
								sendType = "2";
								if (atm.getAllstatus().equals("����") && type.equals("2"))
									typeStr = "ȱ��";
								else if (type.equals("2"))
									typeStr = "ֽ��";
								else if (type.equals("5"))
									typeStr = "ȡ������С��300��";
								else if (type.equals("6"))
									typeStr = "����������1500��"; 
								else if (type.equals("7"))
									typeStr = "������ȱ��";
							}
							String level = atm.getAesyspath();
							// ͨ���豸ID���������ͺͷ��ͼ����ҵ���Ӧ���ֻ��û�
							List<Smsuser> smsList=DataManager.lsgetSmsMobile(atmId, sendType, level);
//							Smsuser smsUser = DataManager.getSmsMobile(atmId,
//									sendType, level);
							// �жϼ���ʱ��ʱ������
							int timeout = MyTime.subTime(atm.getTimeout(),
									MyTime.getTime("yyyyMMddHHmmss"),
									"yyyyMMddHHmmss");
							boolean levelFlag = false;
							if (level.equals("1")
									&& (timeout >= Integer.parseInt(fir)))
								levelFlag = true;
							else if (level.equals("2")
									&& (timeout >= Integer.parseInt(sec)))
								levelFlag = true;
							else if (level.equals("3")
									&& (timeout >= Integer.parseInt(thi)))
								levelFlag = true;
							
							if ((level.equals("2") || level.equals("3")) && atm.getAllstatus().equals("����") && !type.equals("7"))
								continue;
							else
							// �жϸ��û��Ƿ�������Ƿ�ע��
							for (int i=0;i<smsList.size();i++)
							{
								Smsuser smsUser=smsList.get(i);
								if (smsUser != null && smsUser.getStatus() != null
										&& smsUser.getStatus().equals("1")) {
									if (levelFlag) {
										String sendPhone = smsUser.getMoblie();
										if (sendPhone != null
												&& !sendPhone.equals("")) {
											smsInfo = new SmsInfo();
											smsInfo.setSmsNo(sendPhone);
											smsInfo.setSmsName(smsUser.getName());
											if ((level.equals("2") || level.equals("3")) && atm.getAllstatus().equals("����") && !type.equals("7"))
											{
												logger.info("����Ԥ����δȱ��������");
												continue;
											}
											if (level.equals("2") && atm.getAllstatus().equals("����"))
											{
												typeStr = "ȱ���ѹ�һСʱ";
												logger.info("���䣺"+type+level);
												
											}
											else if (level.equals("3") && atm.getAllstatus().equals("����"))
											{
												
												typeStr = "ȱ���ѹ���Сʱ";
												logger.info("���䣺"+type+level);
											}
											else if (atm.getAllstatus().equals("��ˮ��ӡ") && level.equals("2"))
											{
												typeStr = "ȱֽ�ѹ�һСʱ";
											}
											else if (atm.getAllstatus().equals("��ˮ��ӡ") && level.equals("3"))
											{
												typeStr = "ȱֽ�ѹ���Сʱ";
											}
											// ��װ����
											String sendMsg = atmId + "-"
													+ atm.getCompany() + "-"
													+ atm.getRoute() + "-"
													+ atm.getAddr() + ","
													+ atm.getAllstatus() + typeStr;
											smsInfo.setSmsMsg(sendMsg);
	
											smsQueue.offer(smsInfo);
											// ���к�������+1
											Atmhistory atmhistory = new Atmhistory();
											atmhistory.setConstant(String
													.valueOf((Integer
															.parseInt(level) + 1)));
											atmhistory.setAtmId(atmId);
											atmhistory.setBugId(atm.getEjr());
											atmhistory.setBugBeg(atm.getTimeout());
											atmhistory.setSendType(level);
											DataManager.setSendType(atmhistory);
											logger.info("��������" + sendMsg);
										}
									}
								}
							}
						}
					}

					
				} catch (Exception e) {
					logger.error(e.getLocalizedMessage(), e);
				}
			}
		}
	}

	// ���ŷ���-ʡ�������ƽ̨
//	public static void sendSms() {
//		cn.sh.ae.tools.Host myHost=new cn.sh.ae.tools.Host();
//		Message sendMessage;
//		DoWork worker=new DoWork();
//		myHost.setHostIP("32.235.32.226");
//		myHost.setHostPort(10030);
//		myHost.setTimeout(5000);
//		myHost.setEncoding("GBK");
//		while (sendSmsFlag) {
//			try {
//				Thread.sleep(1000);
//				if (smsQueue != null && !smsQueue.isEmpty()) {
//					SmsInfo sms = smsQueue.poll();
//					sendMessage=new Message();
//					String phoneNo = sms.getSmsNo();
//					String phoneMsg = sms.getSmsMsg();
//					sendMessage.setMsgBody_SNO("ATMJ");
//					sendMessage.setMsgBody_PNO(phoneNo);
//					sendMessage.setMsgBody_TEXT(phoneMsg);
//					String message=worker.packMessage(sendMessage);
//					String result=worker.doSend(myHost, message);
//					logger.info("����ƽ̨���ͷ�����:"+result+message);
//					String phoneDate = MyTime.getTime("yyyy-MM-dd HHmmss");
//					sms.setSmsDate(phoneDate);
//					DataManager.setSmsinfo(sms);
//				}
//			} catch (Exception e) {
//				logger.error(e.getLocalizedMessage(), e);
//			}
//		}
//	}
//���ŷ���-����è
	public static void sendSms() {
		while (sendSmsFlag) {
			try {
				Thread.sleep(1000);
				if (smsQueue != null && !smsQueue.isEmpty()) {
					SmsInfo sms = smsQueue.poll();
					String phoneNo = sms.getSmsNo();
					String phoneMsg = sms.getSmsMsg();				
					sendSms(phoneNo, phoneMsg);
					String phoneDate = MyTime.getTime("yyyy-MM-dd HHmmss");
					sms.setSmsDate(phoneDate);
					DataManager.setSmsinfo(sms);
				}
			} catch (Exception e) {
				logger.error(e.getLocalizedMessage(), e);
			}
		}
	}
	public static void setDayCheck() {
		logger.info("��ʼ��¼��������");
		List<Atm> listAtm = DataManager.getAtmList(0);
		DataManager.setDayCheck(listAtm);
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
						// logger.info("���ģ�" + message);
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
						int moneySize_i = 0;
						for (int i = 0; i < box.length(); i += 8) {
							String type_1 = box.substring(0 + i, 1 + i);
							String type = box.substring(1 + i, 2 + i);
							if (type_1.equals("o") && type.equals("2"))
								moneySize += Integer.parseInt(box.substring(
										i + 4, i + 8));
							if (type_1.equals("i") && type.equals("1"))
								moneySize_i += Integer.parseInt(box.substring(
										i + 4, i + 8));
						}
						atm.setPartrmb(String.valueOf(moneySize));
						atm.setIp(socket.getInetAddress().toString().substring(
								1));
						atm.setDepormb(String.valueOf(moneySize_i));
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
package cn.sh.ae.tools;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import cn.sh.ae.access.ConnectionManager;
import cn.sh.ae.manage.ConnectManager;
import cn.sh.ae.manage.DataManager;
import cn.sh.ae.manage.WorkManager;
import cn.sh.ae.util.MyTime;
import cn.sh.ae.vo.Atmc;
import cn.sh.ae.vo.Atmp;

/**
 * @author J.tao $<a href="tjzsq@hotmail.com">��ϵ����</a>$
 * 
 * @version v1.0
 */
public class MsTimerTask implements ServletContextListener {

	static Logger logger = Logger.getLogger(MsTimerTask.class.getName());
	private static Timer timer_moni = null, timer_mgrt = null,
			timer_down = null, timer_check = null;
	private static long time_moni = 0;
	private static Date date_mgrt = null, autoDownTime = null;
	private static ServerSocket socket = null;

	public static Thread tSmsQueue = null, tSmsSend = null, tSycnTask = null;

	public static void startSendSms() {
		tSmsQueue = new Thread(new TimingSmsTask());
		tSmsQueue.start();
		logger.info("������Ӷ��Ŷ���");
		tSmsSend = new Thread(new SendSmsTask());
		tSmsSend.start();
		logger.info("�������Ͷ��Ŷ���");
	}
//�˻�����ƽ̨ʹ��
//	public static void startSendSmsTimer() {
////		logger.info("��ʼ��������è������");
//		startSendSms();
//	}
//����èʹ��	
	public static void startSendSmsTimer() {
		logger.info("��ʼ��������è������");
		if (WorkManager.startGSMModem()) {
			logger.info("��������è�ɹ�");
			startSendSms();
		} else
			logger.info("��������èʧ��");
	}

	public static void stopSendSmsTimer() {
		logger.info("�رն���è");
		WorkManager.stopGSMModem();
	}

	public static void startSyncTimer(String flag) {
		if ("1".equals(flag)) {
			// time_sync = Long.parseLong(DataManager.getAtmpStatus()
			// .getSycntime());
			// timer_sync = new Timer(true);
			logger.info("��ʼͬ��״̬����");
			// timer_sync.scheduleAtFixedRate(new SycnTask(), 1000,
			// time_sync * 60000);
			socket = ConnectManager.getAtmpServer();
			tSycnTask = new Thread(new SycnTask(socket));
			tSycnTask.start();
		}
	}
	

	public static void stopSyncTimer() {
		logger.info("ֹͣͬ��");
		if (socket != null)
			try {
				socket.close();
			} catch (IOException e) {
				logger.error(e.getLocalizedMessage(), e);
			}
	}

	public static void startMoniTimer(String flag) {
		if ("1".equals(flag)) {
			time_moni = Long.parseLong(DataManager.getAtmcStatus()
					.getNetmonitortime());
			timer_moni = new Timer(true);
			logger.info("�����������:" + time_moni + "����");
			timer_moni.scheduleAtFixedRate(new PingTask(), 30000,
					time_moni * 60000);
		}
	}

	public static void stopMoniTimer() {
		logger.info("ֹͣ������");
		if (timer_moni != null)
			timer_moni.cancel();
	}

	public static void startMgrtTimer(String flag) {
		if ("1".equals(flag)) {
			try {
				date_mgrt = MyTime.getHourOfDay(Integer.parseInt(DataManager
						.getAtmpStatus().getMgrttime().trim()));
				timer_mgrt = new Timer(true);
				logger.info("Ǩ��ʱ�䣺" + date_mgrt);
				timer_mgrt.scheduleAtFixedRate(new MgrtTask(), date_mgrt,
						24 * 60 * 60 * 1000);
			} catch (Exception e) {
				logger.error(e.getLocalizedMessage(), e);
			}
		}
	}

	public static void stopMgrtTimer() {
		logger.info("ֹͣǨ��");
		if (timer_mgrt != null)
			timer_mgrt.cancel();
	}

	public static void startDownTimer(String flag) {
		if ("1".equals(flag)) {
			autoDownTime = MyTime.getHourOfDay(Integer.parseInt(DataManager
					.getAtmcStatus().getDownloadtime()));
			timer_down = new Timer(true);
			logger.info("�Զ�������ˮ��" + autoDownTime);
			timer_down.scheduleAtFixedRate(new autoDownTask(), autoDownTime,
					24 * 60 * 60 * 1000);
		}
	}

	public static void stopDownTimer() {
		logger.info("ֹͣ������ˮ");
		if (timer_down != null)
			timer_down.cancel();
	}

	public static void startDayCheckTimer() {
		timer_check = new Timer(true);
		Date date = MyTime.getHourOfDay(6);
		logger.info("ÿ�ն���ʱ��Ϊ��" + date);
		timer_check.scheduleAtFixedRate(new DayCheckTask(), date,
				24 * 60 * 60 * 1000);
	}

	public static void stopDayCheckTimer() {
		logger.info("ֹͣÿ�ն���");
		timer_check.cancel();
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		MsTimerTask.stopMgrtTimer();
		MsTimerTask.stopDownTimer();
		MsTimerTask.stopMoniTimer();
		MsTimerTask.stopSyncTimer();
		MsTimerTask.stopSendSmsTimer();
		MsTimerTask.stopDayCheckTimer();
		// �ر��̳߳�
		try {
			ConnectionManager.getInstance().finalize();
		} catch (Throwable e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	public void contextInitialized(ServletContextEvent arg0) {
		MsTimerTask.startSendSmsTimer();

		Atmc atmc = DataManager.getAtmcStatus();
		MsTimerTask.startDownTimer(atmc.getAutodownload() == null ? "0" : atmc
				.getAutodownload());
		MsTimerTask.startMoniTimer(atmc.getNetmonitorstatus());

		Atmp atmp = DataManager.getAtmpStatus();
		MsTimerTask.startMgrtTimer(atmp.getMgrtstatus());
		MsTimerTask.startSyncTimer(atmp.getSycnstatus());
		MsTimerTask.startDayCheckTimer();
	}
}

// class SycnTask extends TimerTask {
// @Override
// public void run() {
// WorkManager.sycnLocalDB();
// }
// }

class SycnTask implements Runnable {
	private ServerSocket socket = null;

	public SycnTask(ServerSocket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		WorkManager.sycnLocalDB(socket);
	}
}

class MgrtTask extends TimerTask {
	static Logger logger = Logger.getLogger(MgrtTask.class.getName());

	@Override
	public void run() {
		logger.info("��ʼǨ������");
		WorkManager.mgrtLocalDB();
	}
}

class autoDownTask extends TimerTask {
	@Override
	public void run() {
		WorkManager.autoDownEjr(DataManager.getAtmList(0), null, "1", false);
	}
}

class PingTask extends TimerTask {
	@Override
	public void run() {
		WorkManager.setLineStatus();
	}
}

class TimingSmsTask extends TimerTask {
	@Override
	public void run() {
		WorkManager.setTimingSms();
	}
}

class SendSmsTask implements Runnable {
	@Override
	public void run() {
		WorkManager.sendSms();
	}
}

class DayCheckTask extends TimerTask {
	@Override
	public void run() {
		WorkManager.setDayCheck();
	}
}
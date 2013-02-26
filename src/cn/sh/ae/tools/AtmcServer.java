package cn.sh.ae.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

public class AtmcServer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3740536362802873499L;

	private static Logger logger = Logger.getLogger(AtmcServer.class.getName());

	/**
	 * ��������
	 */
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	private boolean isRun = true;
	private ExecutorService exec = null;

	private boolean startServer(int port, int size) {
		logger.info("ִ��������");
		logger.info("********************");
		try {
			serverSocket = new ServerSocket(port);
			logger.info("*�����˿�����=" + port);
			exec = Executors.newFixedThreadPool(size);
			logger.info("*����������=" + size);
			isRun = true;
			logger.info("*********************");
			new Thread() {
				@Override
				public void run() {
					while (isRun) {
						try {
							logger.info("*��ʼ�ȴ�����");
							socket = serverSocket.accept();
							logger.info("*" + socket.getRemoteSocketAddress()
									+ "����");
							exec.execute(new Work(socket));
						} catch (IOException e) {
							logger.error(e.getLocalizedMessage(), e);
						}

					}
				}
			}.start();
			return true;
		} catch (IOException e) {
			logger.error(e.getLocalizedMessage(), e);
			return false;
		}
	}

	/**
	 * ֹͣ����
	 */
	private boolean stopServer() {
		logger.info("ִ�йرգ�");
		logger.info("@@@@@@@@@@@@@@@@@");
		try {
			isRun = false;
			if (exec != null && !exec.isShutdown()) {
				exec.shutdown();
				logger.info("�ر��̳߳أ�");
			}
			if (serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();
				logger.info("�رն˿ڣ�");
			}
			return true;
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			return false;
		} finally {
			exec = null;
			serverSocket = null;
			logger.info("@@@@@@@@@@@@@@@@@");
		}
	}

	class Work implements Runnable {
		private Socket socket = null;

		public Work(Socket socket) {
			this.socket = socket;
		}

		/** �������� */
		private String receiveMessage() {
			BufferedReader reader = null;
			String message = null;
			try {
				while (true) {
					reader = new BufferedReader(new InputStreamReader(socket
							.getInputStream()));
					if ((message = reader.readLine()) != null) {
						System.out.println(message);
					}
				}
			} catch (Exception e) {
				message = null;
				logger.error(e.getLocalizedMessage(), e);
			}
			return message;
		}

		@Override
		public void run() {
			receiveMessage();
		}
	}

	public static void main(String[] args) {
		new AtmcServer().startServer(2704, 10);
	}

}

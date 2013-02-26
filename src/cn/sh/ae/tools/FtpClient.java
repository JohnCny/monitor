package cn.sh.ae.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import cn.sh.ae.vo.Atmp;

public class FtpClient implements Serializable {

	static Logger logger = Logger.getLogger(FtpClient.class.getName());

	private static final long serialVersionUID = -5537015662601715077L;
	private String ip = "127.0.0.1";// SystemConstants.FTP_IP;
	private int port = 21;// SystemConstants.FTP_IP;
	private String userName = "";// SystemConstants.FTP_USERNAME;
	private String password = "";// SystemConstants.FTP_PASSWORD;
	private String workPath = "";// SystemConstants.FTP_SNDDIR;

	// private String cfgPath = SystemConstants.CFGPATH;
	private String localLogPath;

	private FTPClient ftp = new FTPClient();

	public FtpClient(Atmp atmp) {
		this.ip = atmp.getFtpip();
		String port = atmp.getFtpport();
		if (port != null && !port.equals(""))
			this.port = Integer.parseInt(port);
		this.userName = atmp.getFtpusername();
		this.password = atmp.getFtppassword();
		this.workPath = atmp.getFtppath();
		this.localLogPath = atmp.getMgrtpath();
	}

	public boolean ftpConnect() {
		try {
			ftp.connect(ip, port);
			if (FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				if (ftp.login(userName, password)) {
					return true;
				}
			}
			ftpClose();
			return false;
		} catch (SocketException e) {
			logger.error(e.getLocalizedMessage(), e);
			return false;
		} catch (IOException e) {
			logger.error(e.getLocalizedMessage(), e);
			return false;
		}

	}

	/**
	 * 
	 * @param path
	 * @param fileName
	 * @throws IOException
	 * @throws Exception
	 */
	public void downloadFile() {

		FileOutputStream fos = null;

		try {
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.setControlEncoding("GBK");
			// ftp.enterLocalPassiveMode();
			logger.info("下载交易量文件："+workPath);
			FTPFile[] ftpFiles = ftp.listFiles(workPath);
			if (ftpFiles.length != 0) {
				fos = new FileOutputStream(localLogPath);
				ftp.retrieveFile(workPath, fos);
				ftp.deleteFile(workPath);
			}
			workPath+="_1";
			logger.info("下载收益文件："+workPath);
			ftpFiles = ftp.listFiles(workPath);
			if (ftpFiles.length != 0) {
				fos = new FileOutputStream(localLogPath+"_1");
				ftp.retrieveFile(workPath, fos);
				ftp.deleteFile(workPath);
			}
		} catch (IOException e) {
			logger.error(e.getLocalizedMessage(), e);
		}

	}

	public void deleteDownFile() {
		try {
			ftp.deleteFile(workPath);
			int status = ftp.getReplyCode();
			if (status == 250) {
				logger.info("成功删除FTP服务器中文件:" + workPath);
			}
		} catch (IOException e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	/**
	 * 
	 * @throws IOException
	 */
	public void ftpClose() {
		if (ftp.isConnected()) {
			try {
				ftp.logout();
				ftp.disconnect();
			} catch (IOException e) {
				logger.error(e.getLocalizedMessage(), e);
			}
		}
	}

	public static void main(String[] args) {
		// Atmp atmp = new Atmp();
		// atmp.setFtpip("127.0.0.1");
		// atmp.setFtppassword("");
		// atmp.setFtpport("21");
		// atmp.setFtpusername("");
		// FtpClient ftp = new FtpClient(atmp);
		//
		// ftp.ftpConnect();
		// ftp.downloadFile();

		File f = new File("c:\\xyhxif.anr");
		String ss = f.getPath();
		System.out.println(ss.split("\\\\")[1]);

	}
}
package cn.sh.ae.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.Socket;

import org.apache.log4j.Logger;

import cn.sh.ae.vo.MyDate;

/**
 * 
 */
public class MyFile implements Serializable {

	static Logger logger = Logger.getLogger(MyFile.class.getName());

	private static final long serialVersionUID = -804507657047276762L;

	/**
	 * ɾ�������ļ�
	 * 
	 * @param fileName
	 *            ��ɾ���ļ����ļ���
	 * @return �����ļ�ɾ���ɹ�����true,���򷵻�false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.isFile() && file.exists()) {
			file.delete();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ɾ��Ŀ¼���ļ��У��Լ�Ŀ¼�µ��ļ�
	 * 
	 * @param dir
	 *            ��ɾ��Ŀ¼���ļ�·��
	 * @return Ŀ¼ɾ���ɹ�����true,���򷵻�false
	 */
	public static boolean deleteDirectory(String dir, boolean b) {
		// ���dir�����ļ��ָ�����β���Զ�����ļ��ָ���
		if (!dir.endsWith(File.separator)) {
			dir = dir + File.separator;
		}
		File dirFile = new File(dir);
		// ���dir��Ӧ���ļ������ڣ����߲���һ��Ŀ¼�����˳�
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			logger.info("ɾ��Ŀ¼ʧ��" + dir + "Ŀ¼�����ڣ�");
			return false;
		}
		boolean flag = true;
		// ɾ���ļ����µ������ļ�(������Ŀ¼)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// ɾ�����ļ�
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
			// ɾ����Ŀ¼
			else {
				flag = deleteDirectory(files[i].getAbsolutePath(), true);
				if (!flag) {
					break;
				}
			}
		}

		if (!flag) {
			logger.info("ɾ��Ŀ¼ʧ��");
			return false;
		}

		// ɾ����ǰĿ¼
		if (b) {
			if (dirFile.delete()) {
				return true;
			} else {
				logger.info("ɾ��Ŀ¼" + dir + "ʧ�ܣ�");
				return false;
			}
		} else
			return true;
	}

	public static String getXlsFolder() {
		String userFolderPath = MyConstant.ATMXLSPATH;
		File folder = new File(userFolderPath);
		if (!folder.exists() && !folder.isDirectory()) {
			folder.mkdirs();
		}
		return userFolderPath;
	}

	public static void createFloder(String sessionid) {
		File file = new File(sessionid);
		if (!file.exists() && !file.isDirectory())
			file.mkdir();
	}

	public static File getFile(String filePath) {
		File file = new File(filePath);
		if (!file.exists() && !file.isFile()) {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (IOException e) {
				logger.error(e.getLocalizedMessage(), e);
				file = null;
			}
		}
		return file;
	}

	public static boolean saveUploadFile(String fileName, byte[] fileData,
			String sessionid) {
		String localPath = MyConstant.USERPATH + sessionid
				+ MyConstant.SESSIONTEMPUP;
		return saveFile(localPath, fileName, fileData);
	}

	public static String saveCheckFile(String fileName, byte[] fileData,
			String sessionid) {
		String localPath = MyConstant.USERPATH + sessionid
				+ MyConstant.SESSIONTEMPCHECK;
		if (saveFile(localPath, fileName, fileData))
			return localPath;
		else
			return null;
	}

	public static boolean saveFile(String path, String name, byte[] fileData) {
		boolean flag = false;
		OutputStream streamOut = null;
		try {
			new File(path).mkdirs();
			streamOut = new FileOutputStream(path + name);
			streamOut.write(fileData);
			streamOut.flush();
			flag = true;
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		} finally {
			MyClose.close(streamOut);
		}
		return flag;
	}

	public static String sendSocketTempFile(Socket socket, String fileName,
			String remotePath) {
		DataInputStream fileIn = null, socketIn = null;
		DataOutputStream socketOut = null;

		File file = new File(MyConstant.SESSIONTEMPUP + fileName);
		if (isExist(file)) {
			try {
				// String sizeStr = String.valueOf(fileSize);
				// int sSize = sizeStr.length();
				// while (sSize < 10) {
				// sizeStr = "0" + sizeStr;
				// sSize = sizeStr.length();
				// }
				fileIn = new DataInputStream(new BufferedInputStream(
						new FileInputStream(file)));
				socketOut = new DataOutputStream(new BufferedOutputStream(
						socket.getOutputStream()));
				socketIn = new DataInputStream(new BufferedInputStream(socket
						.getInputStream()));
				socketOut.writeLong(file.length());
				socketOut.writeUTF(remotePath);
				int i = 0;
				while ((i = fileIn.read()) != -1) {
					socketOut.write(i);
				}
				socketOut.flush();
				return socketIn.readUTF();
			} catch (FileNotFoundException e) {
				logger.error(e.getLocalizedMessage(), e);
				return e.getMessage();
			} catch (IOException e) {
				logger.error(e.getLocalizedMessage(), e);
				return e.getMessage();
			} finally {
				MyClose.close(fileIn);
				MyClose.close(socketOut);
				MyClose.close(socketIn);
			}

		} else
			return "file exist";

	}

	/**
	 * �ж��ļ����Ƿ���ڣ�������������½�
	 * 
	 * @param chartPath
	 */
	public static boolean isExist(File file) {
		return (file.exists() && file.isFile());
	}

	public static void isExist(String chartPath) {
		File file = new File(chartPath);
		if (!file.exists() || !file.isDirectory()) {
			file.mkdirs();
		}
		// else {
		// deleteDirectory(chartPath, false);
		// }
	}

	public static File getFolderFile(String folderPath) {
		File folder = new File(folderPath);
		if (folder.exists() && folder.isDirectory()) {
			return folder.listFiles()[0];
		} else
			return null;
	}

	public static File[] getFileList(String folderPath) {
		File folder = new File(folderPath);
		if (folder.exists() && folder.isDirectory()) {
			return folder.listFiles();
		} else
			return null;
	}

	public static String getEjrPathByDate(MyDate date) {
		int month = date.getMonth();
		int day = date.getDay();
		String strMonth = String.valueOf(month), strDay = String.valueOf(day);
		if (month < 10)
			strMonth = "0" + strMonth;
		if (day < 10)
			strDay = "0" + strDay;
		return strMonth + strDay;
	}

	public static void main(String[] args) {
		backUpFile(new File("c:\\bdtatmls20101216.txt"));
	}

	public static void backUpFile(File oldfile) {
		try {
			if (oldfile.exists()) { // �ļ�����ʱ
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(new FileInputStream(oldfile)));
				BufferedWriter write = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(
								getFile(MyConstant.ATMTRADATAPATH
										+ oldfile.getName() + "."
										+ MyTime.getTime("yyyyMMdd")))));
				String col = null;
				while ((col = reader.readLine()) != null) {
					write.write(col);
					write.newLine();
				}
				reader.close();
				write.close();
				oldfile.delete();
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}
}

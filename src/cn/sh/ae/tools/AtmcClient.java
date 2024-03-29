package cn.sh.ae.tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

import org.apache.log4j.Logger;

import cn.sh.ae.util.MyFile;

/**
 * 
 */
public class AtmcClient implements Serializable {

	private static final long serialVersionUID = -8187557624488929979L;

	static Logger logger = Logger.getLogger(AtmcClient.class.getName());

	public static void close(Socket socket) {
		try {
			if (socket != null)
				socket.close();
		} catch (IOException e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	/** 获取前提报文 */
	public static int getRequest(Socket socket) {
		int type = 0;
		DataInputStream socketIn = null;
		try {
			socketIn = new DataInputStream(new BufferedInputStream(socket
					.getInputStream()));
			type = socketIn.readInt();
		} catch (Exception e) {
			// logger.error(e.getLocalizedMessage(), e);
			type = 0;
		}
		return type;
	}

	/** 发送返回报文 */
	public static void setResponse(Socket socket, int message) {
		DataOutputStream socketOut = null;
		try {
			socketOut = new DataOutputStream(new BufferedOutputStream(socket
					.getOutputStream()));
			socketOut.writeInt(message);
			socketOut.flush();
		} catch (Exception e) {
			// logger.error(e.getLocalizedMessage(), e);
		}
	}

	/** 发送返回报文 */
	public static void sendMessage(Socket socket, String path) {
		DataOutputStream socketOut = null;
		try {
			socketOut = new DataOutputStream(new BufferedOutputStream(socket
					.getOutputStream()));
			socketOut.writeUTF(path);
			socketOut.flush();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}
	
	// /** 接收文件 */
	// public static void receiveFile(Socket socket, String path) {
	// DataInputStream socketIn = null;
	// DataOutputStream fileOut = null;
	// try {
	// MyFile.isExist(path.substring(0, path.length() - 5));
	// fileOut = new DataOutputStream(new BufferedOutputStream(
	// new FileOutputStream(path)));
	// socketIn = new DataInputStream(new BufferedInputStream(socket
	// .getInputStream()));
	// int i = 0;
	// while ((i = socketIn.read()) != -1) {
	// fileOut.write(i);
	// }
	// fileOut.flush();
	// } catch (Exception e) {
	// logger.error(e.getLocalizedMessage(),e);
	// }finally{
	// logger.info("文件接收完成");
	// close(socket);
	// }
	// }

	/** 接收文件 */
	public static String receiveFile(Socket socket, String path, String fileName) {
		DataInputStream socketIn = null;
		DataOutputStream fileOut = null;
		byte[] buffer = new byte[1024];
		File file = null;
		try {
			// System.out.println(path);
			MyFile.isExist(path);
			socketIn = new DataInputStream(new BufferedInputStream(socket
					.getInputStream()));
			// 获取文件大小
			long length = socketIn.readLong();
			// logger.info("上传文件大小=" + length);
			// 建立文件输出流
			file = new File(path + fileName);
			fileOut = new DataOutputStream(new BufferedOutputStream(
					new FileOutputStream(file)));
			int l = 0, s = 0;
			while ((l = socketIn.read(buffer)) != -1) {
				s += l;
				fileOut.write(buffer, 0, l);
				if (s == (int) length)
					break;
			}
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
//		logger.info("截图路径："+file.getPath());
		return file.getPath();
	}

	/** 发送文件 */
	public static void sendFile(Socket socket, String fileName, byte[] fileData) {
		// DataInputStream fileIn = null;
		// File file = null;
		DataOutputStream socketOut = null;
		// byte[] buffer = new byte[1024];
		try {
			socketOut = new DataOutputStream(new BufferedOutputStream(socket
					.getOutputStream()));
			int size = 1;
			// 通知接收方上传文件数量
			socketOut.writeInt(size);
			for (int i = 0; i < size; i++) {
				// file = new File(files.get(i));
				// fileIn = new DataInputStream(new BufferedInputStream(
				// new FileInputStream(file)));
				long length = fileData.length;
				socketOut.writeUTF(fileName);
				socketOut.writeLong(length);
				socketOut.write(fileData);
				socketOut.flush();
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}
}

package cn.sh.ae.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DecimalFormat;

import org.apache.log4j.Logger;

import cn.sh.ae.manage.WorkManager;

public class DoWork {
	static Logger logger = Logger.getLogger(WorkManager.class.getName());
	/**
	 * @param msg �ユ�瀵硅薄
	 * 
	 * @return String 瀹������ユ�
	 * */
	public String packMessage(Message msg) {
		StringBuffer buff = new StringBuffer();
		buff.append(msg.getMsgBody_SNO());
		buff.append(msg.getMsgBody_PNO());
		for (int i=msg.getMsgBody_PNO().length();i<15;i++)
		{
			buff.append(" ");
		}
		buff.append(msg.getMsgBody_TEXT());
		String body = buff.toString();
		
		int bodyLength = buff.length();
		buff.setLength(0);
		return buff.append(new DecimalFormat("0000").format(bodyLength))
				.append(body).toString();

	}

	/**
	 * @param host 杩��涓绘�淇℃�
	 * @param ����ユ� 
	 * @see packMessage
	 * 
	 * @return 涓绘�杩����00涓烘�甯��朵�涓洪�璇�
	 * */
	public String doSend(Host host, String msg) {
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		String backStr = null;
		try {
			socket = new Socket(host.getHostIP(), host.getHostPort());
//			socket.setSoTimeout(host.getTimeout());	
			in = new BufferedReader(new InputStreamReader(socket
					.getInputStream(), host.getEncoding()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream(), host.getEncoding())), true);
			out.write(msg);
			backStr = "ss";
			out.close();
			in.close();
			socket.close();
			if (in!=null){
				backStr = in.readLine();
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		} 
				
		return backStr;

	}


}

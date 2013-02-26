package cn.sh.ae.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * 
 */
public class MyClose implements Serializable {

	static Logger logger = Logger.getLogger(MyClose.class.getName());

	private static final long serialVersionUID = 855919876588774102L;

	public static void close(Connection conn) {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			logger.error(e.getLocalizedMessage(), e);
			conn = null;
		}
	}

	public static void close(OutputStream out) {
		try {
			if (out != null)
				out.close();
		} catch (IOException e) {
			logger.error(e.getLocalizedMessage(), e);
			out = null;
		}
	}

	public static void close(InputStream in) {
		try {
			if (in != null)
				in.close();
		} catch (IOException e) {
			logger.error(e.getLocalizedMessage(), e);
			in = null;
		}
	}

	public static void shutdownOutput(Socket socket) {
		try {
			if (socket != null)
				socket.shutdownOutput();
		} catch (IOException e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

}

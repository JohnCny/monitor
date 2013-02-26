package cn.sh.ae.util;

import java.io.Serializable;

/**
 * 
 */
public class MyConstant implements Serializable {

	private static final long serialVersionUID = 1634775629534804868L;

	public static final String SYSPATH = System.getProperty("user.dir")
			+ java.io.File.separator;

	public static final String LIBPATH = SYSPATH.replace("bin", "ae-lib");

	public static final String USER = "user" + java.io.File.separator;

	public static final String USERPATH = SYSPATH + USER;

	public static final String ATMLOGPATH = java.io.File.separator + "atm"
			+ java.io.File.separator + "journal" + java.io.File.separator;

	public static final String ATMTRADATAPATH = java.io.File.separator + "atm"
			+ java.io.File.separator + "trade" + java.io.File.separator;

	public static final String ATMXLSPATH = java.io.File.separator + "atm"
			+ java.io.File.separator + "report" + java.io.File.separator;

	public static final String TEMPPATH = java.io.File.separator + "atm"
			+ java.io.File.separator + "temp" + java.io.File.separator;

	public static final String SNAPSHOTPATH_WEB = "atm" + java.io.File.separator + "snapShot"+ java.io.File.separator;

	public static final String SNAPSHOTPATH = SYSPATH + "webapps"
			+ java.io.File.separator + "monitor" + java.io.File.separator
			+ SNAPSHOTPATH_WEB;

	// public static final String TEMPUP = SYSPATH + "tempup"
	// + java.io.File.separator;
	// public static final String TEMPDOWN = SYSPATH + "tempdown"
	// + java.io.File.separator;
	// public static final String REPORTPNG = SYSPATH + "reportPNG"
	// + java.io.File.separator;

	public static final String SESSIONTEMPUP = java.io.File.separator
			+ "Upload" + java.io.File.separator;
	public static final String SESSIONTEMPDOWN = java.io.File.separator + "EJR";
	public static final String SESSIONPNG = java.io.File.separator + "PNG"
			+ java.io.File.separator;
	public static final String SESSIONTEMPCHECK = java.io.File.separator
			+ "check" + java.io.File.separator;

	// public static final String TRADATA_T = TRADATA + "bdtatmls.txt";
	// public static final String TRADATA_B = TRADATA + "bdatmls.txt";

	public static class DBInfo {
		public static final String dbip = "127.0.0.1";
		public static final String dbport = "1433";
		public static final String dbusername = "sa";
		public static final String dbpassword = "sa";
		public static final String dbname = "ksatm";
		public static final String dbsycntable = "atm";
		public static final String dbmgrttable = "atmtradata";
	}

	public static class File {

	}

	public static class Lib {
		public static class jar {
			public static final String COMMON = "common.jar";
		}

		public static class clazz {
			public static final String DOWN = "cn.sh.ae.comm.Down";
		}
	}

	public static class Table {

		public static class User {
			public static final String USERNAME = "username";
			public static final String PASSWORD = "password";
			public static final String NAME = "name";
			public static final String TITLE = "title";
			public static final String PHONE = "phone";
			public static final String EMAIL = "email";
			public static final String CREATETIME = "createtime";
			public static final String ULEVEL = "ulevel";
			public static final String STATUS = "status";
			public static final String UMLEVEL = "umlevel";
			public static final String EMLEVEL = "emlevel";
			public static final String EMLEVEL1 = "emlevel1";
			public static final String EMLEVEL2 = "emlevel2";
			public static final String EMLEVEL3 = "emlevel3";
			public static final String EMLEVEL4 = "emlevel4";
			public static final String CMLEVEL = "cmlevel";
			public static final String SALEVEL = "salevel";
			public static final String CTLEVEL = "ctlevel";
			public static final String RMLEVEL = "rmlevel";
			public static final String SCLEVEL = "sclevel";

		}

		public static class Atmp {
			public static final String DBIP = "dbip";
			public static final String DBPORT = "dbport";
			public static final String DBUSERNAME = "dbusername";
			public static final String DBPASSWORD = "dbpassword";
			public static final String DBNAME = "dbname";
			public static final String SYCNTIME = "sycntime";
			public static final String MGRTTIME = "mgrttime";
			public static final String MGRTPATH = "mgrtpath";
			public static final String SYCNSTATUS = "sycnstatus";
			public static final String MGRTSTATUS = "mgrtstatus";

			public static final String FTPIP = "ftpip";
			public static final String FTPPORT = "ftpport";
			public static final String FTPPATH = "ftppath";
			public static final String FTPUSERNAME = "ftpusername";
			public static final String FTPPASSWORD = "ftppassword";
			
			
		}

		public static class Remotecontrol {
			public static final String UPLOADSTATUS = "uploadstatus";
			public static final String DOWNLOADSTATUS = "downloadstatus";
			public static final String REBOOTSTATUS = "rebootstatus";
			public static final String AUTODOWNLOAD = "autodownload";
			public static final String DOWNLOADTIME = "downloadtime";
			public static final String NETMONITORSTATUS = "netmonitorstatus";
			public static final String NETMONITORTIME = "netmonitortime";
		}

		public static class Atm {
			public static final String DEPTCODE = "deptcode";
			public static final String ATMID = "atmid";
			public static final String DEVTYPE = "devtype";
			public static final String DEVXH = "devxh";
			public static final String MACPROVIDER = "macprovider";
			public static final String MACNO = "macno";
			public static final String XPOS = "xpos";
			public static final String YPOS = "ypos";
			public static final String AREA = "area";
			public static final String CITY = "city";
			public static final String CITYDEP = "citydep";
			public static final String CLINAME = "cliname";
			public static final String ADDR = "addr";
			public static final String PLACETYPE = "placetype";
			public static final String BANKNAME = "bankname";
			public static final String BANKSUBCODE = "banksubcode";
			public static final String BANKPOINTCODE = "bankpointcode";
			public static final String CLIENTCODE = "clientcode";
			public static final String ISUSED = "isused";
			public static final String USEDATE = "usedate";
			public static final String CREATEDATE = "createdate";
			public static final String DUTYMAN = "dutyman";
			public static final String DUTYPHONE = "dutyphone";
			public static final String MOBILE = "mobile";
			public static final String EMAIL = "email";
			public static final String MONDATETIME = "mondatetime";
			public static final String BOX1DENOM = "box1denom";
			public static final String BOX1CURTYPE = "box1curtype";
			public static final String BOX1NOTENUM = "box1notenum";
			public static final String BOX2DENOM = "box2denom";
			public static final String BOX2CURTYPE = "box2curtype";
			public static final String BOX2NOTENUM = "box2notenum";
			public static final String BOX3DENOM = "box3denom";
			public static final String BOX3CURTYPE = "box3curtype";
			public static final String BOX3NOTENUM = "box3notenum";
			public static final String BOX4DENOM = "box4denom";
			public static final String BOX4CURTYPE = "box4curtype";
			public static final String BOX4NOTENUM = "box4notenum";
			public static final String PARTRMB = "partrmb";
			public static final String RETCARDNUM = "retcardnum";
			public static final String READERSTATUS = "readerstatus";
			public static final String PRJSTATUS = "prjstatus";
			public static final String PRRSTATUS = "prrstatus";
			public static final String CDMSTATUS = "cdmstatus";
			public static final String DEPSTATUS = "depstatus";
			public static final String DEMSTATUS = "demstatus";
			public static final String PCBSTATUS = "pcbstatus";
			public static final String SAFEBOXSTATUS = "safeboxstatus";
			public static final String BOX1STATUS = "box1status";
			public static final String BOX2STATUS = "box2status";
			public static final String BOX3STATUS = "box3status";
			public static final String BOX4STATUS = "box4status";
			public static final String LINESTATUS = "linestatus";
			public static final String SERVICESTATUS = "servicestatus";
			public static final String SELF = "self";
			public static final String ATMIP = "atmip";
			public static final String EJRPATH = "ejrpath";
			public static final String PICTUREPATH = "picturepath";
			public static final String UPDATEPATH = "updatepath";
			public static final String ENCODING = "encoding";
			public static final String TIMEOUT = "timeout";
			public static final String PORT = "port";
			public static final String ROUTE = "route";
		}

	}

	public static String draw = "CR05069";
	public static String deposit = "CR05148";
	public static String transfer = "CR05076";

	// 全部取款
	public static String dra = " atmtradata.tradetype like 'CR05069%' ";
	// 本行取款
	public static String b_dra = " atmtradata.cardclass='NULL' and atmtradata.tradetype like 'CR05069%' ";
	// 他行取款
	public static String t_dra = " atmtradata.cardclass<>'NULL' and atmtradata.tradetype like 'CR05069%' ";
	// 存款（存款只有本行）
	public static String b_dep = " atmtradata.tradetype like 'CR05148%' ";
	// 转账
	public static String tra = " atmtradata.tradetype like 'CR05076%' ";

}

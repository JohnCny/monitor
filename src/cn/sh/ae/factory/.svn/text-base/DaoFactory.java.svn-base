package cn.sh.ae.factory;

import java.io.Serializable;

import cn.sh.ae.dao.AtmDao;
import cn.sh.ae.dao.AtmcDao;
import cn.sh.ae.dao.AtmcompanyDao;
import cn.sh.ae.dao.AtmhistoryDAO;
import cn.sh.ae.dao.AtmpDao;
import cn.sh.ae.dao.AtmtradataDao;
import cn.sh.ae.dao.AtmtypeDao;
import cn.sh.ae.dao.BankTypeDao;
import cn.sh.ae.dao.RemoteAtmDao;
import cn.sh.ae.dao.SmsDao;
import cn.sh.ae.dao.SmsInfoDao;
import cn.sh.ae.dao.SmsuserDao;
import cn.sh.ae.dao.UnittypeDao;
import cn.sh.ae.dao.UserDao;
import cn.sh.ae.inteface.IAtmDao;
import cn.sh.ae.inteface.IAtmcDao;
import cn.sh.ae.inteface.IAtmcompanyDao;
import cn.sh.ae.inteface.IAtmhistoryDAO;
import cn.sh.ae.inteface.IAtmpDao;
import cn.sh.ae.inteface.IAtmtradataDao;
import cn.sh.ae.inteface.IAtmtypeDao;
import cn.sh.ae.inteface.IBankTypeDao;
import cn.sh.ae.inteface.IRemoteAtmDao;
import cn.sh.ae.inteface.ISmsDao;
import cn.sh.ae.inteface.ISmsInfoDao;
import cn.sh.ae.inteface.ISmsuserDao;
import cn.sh.ae.inteface.IUnittypeDao;
import cn.sh.ae.inteface.IUserDao;

public class DaoFactory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3240617687657703449L;

	public static IAtmcDao getAtmcDao() {
		return new AtmcDao();
	}

	public static IAtmDao getAtmDao() {
		return new AtmDao();
	}

	public static IAtmhistoryDAO getAtmhistoryDAO() {
		return new AtmhistoryDAO();
	}

	public static IAtmpDao getAtmpDao() {
		return new AtmpDao();
	}

	public static IAtmtradataDao getAtmtradataDao() {
		return new AtmtradataDao();
	}

	public static IRemoteAtmDao getRemoteAtmDao() {
		return new RemoteAtmDao();
	}

	public static ISmsuserDao getSmsuserDao() {
		return new SmsuserDao();
	}

	public static IUserDao getUserDao() {
		return new UserDao();
	}

	public static ISmsDao getSmsDao() {
		return new SmsDao();
	}

	public static ISmsInfoDao getSmsInfoDao() {
		return new SmsInfoDao();
	}

	public static IAtmcompanyDao getAtmcompanyDao() {
		return new AtmcompanyDao();
	}

	public static IAtmtypeDao getAtmtypeDao() {
		return new AtmtypeDao();
	}

//	public static IAtmrunDao getAtmrunDao() {
//		return new AtmrunDao();
//	}

	public static IUnittypeDao getUnittypeDao() {
		return new UnittypeDao();
	}
	
	public static IBankTypeDao getBankTypeDao() {
		return new BankTypeDao();
	}

}

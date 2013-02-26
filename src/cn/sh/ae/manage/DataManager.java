package cn.sh.ae.manage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import cn.sh.ae.factory.DaoFactory;
import cn.sh.ae.util.MyClose;
import cn.sh.ae.vo.Atm;
import cn.sh.ae.vo.AtmTradataMoney;
import cn.sh.ae.vo.Atmc;
import cn.sh.ae.vo.Atmhistory;
import cn.sh.ae.vo.Atminfo;
import cn.sh.ae.vo.Atmp;
import cn.sh.ae.vo.Atmtradata;
import cn.sh.ae.vo.Draw;
import cn.sh.ae.vo.Ejr;
import cn.sh.ae.vo.Sms;
import cn.sh.ae.vo.SmsInfo;
import cn.sh.ae.vo.Smsuser;
import cn.sh.ae.vo.Unittype;
import cn.sh.ae.vo.User;

/**
 * 
 */
public class DataManager implements Serializable {

	private static final long serialVersionUID = 6753443910609517115L;
	static Logger logger = Logger.getLogger(DataManager.class.getName());

	// ////////////////////////////////////////////////////////////////////////
	// remotecontrol表
	// ///////////////////////////////////////////////////////////////////////
	public static Atmc getAtmcStatus() {
		Connection conn = ConnectManager.getSystemDB();
		Atmc atmc = DaoFactory.getAtmcDao().getAtmcStatus(conn);
		MyClose.close(conn);
		return atmc;
	}

	public static void setAtmcStatus(Atmc atmc) throws SQLException {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getAtmcDao().setAtmcStatus(conn, atmc);
		MyClose.close(conn);
	}

	public static Sms getSmsStatus() {
		Connection conn = ConnectManager.getSystemDB();
		Sms sms = DaoFactory.getSmsDao().getSmsStatus(conn);
		MyClose.close(conn);
		return sms;
	}

	public static List<SmsInfo> getAllSmsinfo() {
		Connection conn = ConnectManager.getSystemDB();
		List<SmsInfo> smsInfoList = DaoFactory.getSmsInfoDao().getAllSmsInfo(
				conn);
		MyClose.close(conn);
		return smsInfoList;
	}

	public static List<SmsInfo> getSmsinfo(SmsInfo smsInfo) {
		Connection conn = ConnectManager.getSystemDB();
		List<SmsInfo> smsInfoList = DaoFactory.getSmsInfoDao().getSmsInfo(conn,
				smsInfo);
		MyClose.close(conn);
		return smsInfoList;
	}

	public static void setSmsinfo(SmsInfo smsInfo) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getSmsInfoDao().setSmsInfo(conn, smsInfo);
		MyClose.close(conn);
	}

	public static void setSmsStatus(Sms sms) throws SQLException {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getSmsDao().setSmsStatus(conn, sms);
		MyClose.close(conn);
	}

	// ///////////////////////////////////////////////////////////////////////////////////
	// atm表
	// ///////////////////////////////////////////////////////////////////////////////////

	// Atm列表
	public static List<Atm> getAtmList(int level) {
		Connection conn = ConnectManager.getSystemDB();
		List<Atm> atmList = DaoFactory.getAtmDao().getAtmList(conn, level);
		MyClose.close(conn);
		return atmList;
	}

	public static List<Atm> getNoNetBugAtmList() {
		Connection conn = ConnectManager.getSystemDB();
		List<Atm> atmList = DaoFactory.getAtmDao().getNoNetBugAtmList(conn);
		MyClose.close(conn);
		return atmList;
	}

	public static void setDayCheck(List<Atm> atmList) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getAtmDao().setDayCheck(conn, atmList);
		MyClose.close(conn);
	}

	public static List<Atm> getDayCheck(String[] atmids, String begintime,
			String type) {
		Connection conn = ConnectManager.getSystemDB();
		List<Atm> atmList = DaoFactory.getAtmDao().getDayCheck(conn, atmids,
				begintime, type);
		MyClose.close(conn);
		return atmList;
	}

	public static List<Atm> getAtmInfoList(String type, String route,
			String[] atmids) {
		Connection conn = ConnectManager.getSystemDB();
		List<Atm> atmList = DaoFactory.getAtmDao().getAtmInfo(conn, type,
				route, atmids);
		MyClose.close(conn);
		return atmList;
	}

	// 
	public static void setAtmLinestatus(String atmid) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getAtmDao().setLineStatus(conn, atmid);
		MyClose.close(conn);
	}

	public static List<Atm> getOutimeLinestatus(String subTime) {
		Connection conn = ConnectManager.getSystemDB();
		List<Atm> atmList = DaoFactory.getAtmDao().getOuttimeLineStatus(conn,
				subTime);
		MyClose.close(conn);
		return atmList;
	}

	// 各厂商Atm列表
	public static List<Atm> getAtmListByCompany(String company) {
		Connection conn = ConnectManager.getSystemDB();
		List<Atm> atmList = DaoFactory.getAtmDao().getAtmByCompany(conn,
				company);
		MyClose.close(conn);
		return atmList;
	}

	// ATM运营列表
	public static List<Atm> getAtmServerType(int lvevel) {
		Connection conn = ConnectManager.getSystemDB();
		List<Atm> serverType = DaoFactory.getAtmDao().getType(conn, lvevel);
		MyClose.close(conn);
		return serverType;
	}

	// 不同状态ATM列表
	public static List<Atm> getAtmListByStatus(int emlevel, String style,
			String type) {
		Connection conn = ConnectManager.getSystemDB();
		List<Atm> atmStatusList = DaoFactory.getAtmDao().getAtmStatus(conn,
				emlevel, style, type);
		MyClose.close(conn);
		return atmStatusList;
	}

	public static List<Atm> getBugAtmList() {
		Connection conn = ConnectManager.getSystemDB();
		List<Atm> atmList = DaoFactory.getAtmDao().getBugAtmList(conn);
		MyClose.close(conn);
		return atmList;
	}

	// 单台ATM
	public static Atm getAtmById(String atmId) {
		Connection conn = ConnectManager.getSystemDB();
		Atm atm = DaoFactory.getAtmDao().getAtmByAtmId(conn, atmId);
		MyClose.close(conn);
		return atm;
	}

	// 
	public static Atm getChangeAtm(Atm atm) {
		Connection conn = ConnectManager.getSystemDB();
		Atm retAtm = DaoFactory.getAtmDao().getChangeAtm(conn, atm);
		MyClose.close(conn);
		return retAtm;
	}

	public static Atm getChangeStatusAtm(Atm atm) {
		Connection conn = ConnectManager.getSystemDB();
		Atm retAtm = DaoFactory.getAtmDao().getChangeStatusAtm(conn, atm);
		MyClose.close(conn);
		return retAtm;
	}

	public static Atm getBoxChangeAtm(Atm atm) {
		Connection conn = ConnectManager.getSystemDB();
		Atm retAtm = DaoFactory.getAtmDao().getChangeAtm(conn, atm);
		MyClose.close(conn);
		return retAtm;
	}

	// 设置ATM通讯地址
	public static void setAtmAddress(Atm atm) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getAtmDao().setAtmAddress(conn, atm);
		MyClose.close(conn);
	}

	public static void deleteAtm(Atm atm) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getAtmDao().deleteAtm(conn, atm);
		MyClose.close(conn);
	}

	// ATM生产厂商
	public static List<Atm> getAtmCompany() {
		Connection conn = ConnectManager.getSystemDB();
		List<Atm> atmList = DaoFactory.getAtmDao().getAtmCompany(conn);
		MyClose.close(conn);
		return atmList;
	}

	// 与P端状态有变化的ATM列表
	// public static List<Atm> getRemoteAtm() {
	// Connection r_conn = ConnectManager.getRemoteDB();
	// List<Atm> changeAtmList = DaoFactory.getRemoteAtmDao().getAtmpStatus(
	// r_conn);
	// MyClose.close(r_conn);
	// return changeAtmList;
	// }

	// 保存或更新ATM状态
	public static List<Atm> saveOrUpdateAtm(List<Atm> atmList) {
		Connection conn = ConnectManager.getSystemDB();
		List<Atm> atmChangeList = DaoFactory.getAtmDao().saveOrUpdateAtm(conn,
				atmList);
		MyClose.close(conn);
		return atmChangeList;
	}

	// 保存或更新ATM状态
	public static void saveOrUpdateAtm(Atm atm) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getAtmDao().saveOrUpdateAtm(conn, atm);
		MyClose.close(conn);
	}

	public static void updateAtmTradetime(Atm atm) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getAtmDao().updateAtmTradetime(conn, atm);
		MyClose.close(conn);
	}

	// ATM库存列表
	public static List<Atm> getStockMoney(String[] atmids, String type,
			String qcash, String ccash) {
		Connection conn = ConnectManager.getSystemDB();
		List<Atm> stockMoneyList = DaoFactory.getAtmDao().getMoneyStatus(conn,
				type, atmids, qcash, ccash);
		MyClose.close(conn);
		return stockMoneyList;
	}

	// 根据ID获取ATM的通讯地址
	public static Atm getAtmAdderssById(String atmId) {
		Connection conn = ConnectManager.getSystemDB();
		Atm atmAddress = DaoFactory.getAtmDao().getAtmAddressByAtmid(conn,
				atmId);
		MyClose.close(conn);
		return atmAddress;
	}

	// ///////////////////////////////////////////////////////////////////////////////////
	// atmp表
	// //////////////////////////////////////////////////////////////////////////////////
	public static Atmp getAtmpStatus() {
		Connection conn = ConnectManager.getSystemDB();
		Atmp atmp = DaoFactory.getAtmpDao().getAtmpStatus(conn);
		MyClose.close(conn);
		return atmp;
	}

	public static void setAtmpStatus(Atmp atmp) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getAtmpDao().setAtmpStatus(conn, atmp);
		MyClose.close(conn);
	}

	// //////////////////////////////////////////////////////////////////////
	// atmtradata_day表
	// /////////////////////////////////////////////////////////////////////
	public static List<Atm> getDesMoneyList(String[] atmids, String timebegin,
			String timeend, String type) {
		Connection conn = ConnectManager.getSystemDB();
		List<Atm> desMoneyList = DaoFactory.getAtmtradataDao().getDesMoney(
				conn, atmids, timebegin, timeend, type);
		MyClose.close(conn);
		return desMoneyList;
	}

	//
	public static List<Draw> getDrawInfoList(String[] atmids, String tradeType,
			String transType, String type, String timebegin, String timeend) {
		Connection conn = ConnectManager.getSystemDB();
		List<Draw> drawInfoList = DaoFactory.getAtmtradataDao().getDrawInfo(
				conn, atmids, tradeType, transType, type, timebegin, timeend);
		MyClose.close(conn);
		return drawInfoList;
	}

	public static List<Atmtradata> getAllTrade(int type, String timebegin,
			String timeend) {
		Connection conn = ConnectManager.getSystemDB();
		List<Atmtradata> allTradeList = DaoFactory.getAtmtradataDao()
				.getAllTrade(conn, type, timebegin, timeend);
		MyClose.close(conn);
		return allTradeList;
	}

	public static List<AtmTradataMoney> getAllTradeMoney(String timebegin,
			String timeend) {
		Connection conn = ConnectManager.getSystemDB();
		List<AtmTradataMoney> allTradeList = DaoFactory.getAtmtradataDao()
				.getAllTradeMoney(conn, timebegin, timeend);
		MyClose.close(conn);
		return allTradeList;
	}

	public static List<Atmtradata> getTodayTradeById(String atmId) {
		Connection conn = ConnectManager.getSystemDB();
		List<Atmtradata> tradeList = DaoFactory.getAtmtradataDao()
				.getTodayTradeById(conn, atmId);
		MyClose.close(conn);
		return tradeList;
	}

	public static List<Atmtradata> getTodayTradeByType(String type) {
		Connection conn = ConnectManager.getSystemDB();
		List<Atmtradata> tradeList = DaoFactory.getAtmtradataDao()
				.getTodayTradeByType(conn, type);
		MyClose.close(conn);
		return tradeList;
	}

	//
	public static List<List<Draw>> getHisInfoList(String[] atmids, String type,
			String timebegin, String timeend) {
		Connection conn = ConnectManager.getSystemDB();
		List<List<Draw>> list = DaoFactory.getAtmtradataDao().getHisInfo(conn,
				type, timebegin, timeend, atmids);
		MyClose.close(conn);
		return list;
	}

	// //////////////////////////////////////////////////////////////////////
	// atmtradata表
	// /////////////////////////////////////////////////////////////////////

	// //////////////////////////////////////////////////////////////////////
	// atmhistory表
	// /////////////////////////////////////////////////////////////////////
	public static List<Atmhistory> getAtmBugList(String equipType,
			String[] atmids, String timebegin, String timeend, String errType,
			String errTime) {
		Connection conn = ConnectManager.getSystemDB();
		List<Atmhistory> bugList = DaoFactory.getAtmhistoryDAO().getAtmBug(
				conn, equipType, atmids, timebegin, timeend, errType, errTime);
		MyClose.close(conn);
		return bugList;
	}

	// 
	public static String getOutTimeAtm(Atm atm, int macOrBui, boolean flag) {
		Connection conn = ConnectManager.getSystemDB();
		String str = DaoFactory.getAtmhistoryDAO().getOutTimeAtm(conn, atm,
				macOrBui, flag);
		MyClose.close(conn);
		return str;
	}

	public static List<Atm> getSendBugAtm() {
		Connection conn = ConnectManager.getSystemDB();
		List<Atm> atmList = DaoFactory.getAtmhistoryDAO().getSendBugAtm(conn);
		MyClose.close(conn);
		return atmList;
	}

	public static List<Atm> getMoneyValidAtm() {
		Connection conn = ConnectManager.getSystemDB();
		List<Atm> atmList = DaoFactory.getAtmDao().getMoneyValidAtm(conn);
		MyClose.close(conn);
		return atmList;
	}

	// public static Atm getOutBugList(Atm atm) {
	// Connection conn = ConnectManager.getSystemDB();
	// Atm outBug = DaoFactory.getAtmhistoryDAO().getTimeoutAtmBug(conn, atm);
	// MyClose.close(conn);
	// return outBug;
	// }

	// //////////////////////////////////////////////////////////////////////
	// sysuser表
	// /////////////////////////////////////////////////////////////////////
	public static List<User> getUserList() {
		Connection conn = ConnectManager.getSystemDB();
		List<User> userList = DaoFactory.getUserDao().getUser(conn);
		MyClose.close(conn);
		return userList;
	}

	public static void setUser(User user) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getUserDao().createUser(conn, user);
		MyClose.close(conn);
	}

	public static void modifyUser(User user) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getUserDao().modifyUser(conn, user);
		MyClose.close(conn);
	}

	public static User getUserById(String id) {
		Connection conn = ConnectManager.getSystemDB();
		User user = DaoFactory.getUserDao().getUserByUserName(conn, id);
		MyClose.close(conn);
		return user;
	}

	public static void deleteUser(User user) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getUserDao().deleteUser(conn, user);
		MyClose.close(conn);
	}

	public static User login(String id, String password) {
		Connection conn = ConnectManager.getSystemDB();
		User user = DaoFactory.getUserDao().isLogin(conn, id, password);
		MyClose.close(conn);
		return user;
	}

	// //////////////////////////////////////////////////////////////////////
	// smsuser表
	// /////////////////////////////////////////////////////////////////////
	public static List<Smsuser> getSmsUserList() {
		Connection conn = ConnectManager.getSystemDB();
		List<Smsuser> SmsUserList = DaoFactory.getSmsuserDao().getSmsUser(conn);
		MyClose.close(conn);
		return SmsUserList;
	}

	public static void deleteMoblie(Smsuser smsuser) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getSmsuserDao().deleteMoblie(conn, smsuser);
		MyClose.close(conn);
	}

	public static void modifySmsUser(Smsuser SmsUser) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getSmsuserDao().modifyUser(conn, SmsUser);
		MyClose.close(conn);
	}

	public static void setSmsUser(Smsuser SmsUser) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getSmsuserDao().createSms(conn, SmsUser);
		MyClose.close(conn);
	}

	public static Smsuser getSmsUserByMoblie(Smsuser smsuser) {
		Connection conn = ConnectManager.getSystemDB();
		Smsuser SmsUser = DaoFactory.getSmsuserDao().getUserByMoblie(conn,
				smsuser);
		MyClose.close(conn);
		return SmsUser;
	}

	public static List<String> getAtmByBugType(int level) {
		Connection conn = ConnectManager.getSystemDB();
		List<String> atmId = DaoFactory.getSmsuserDao().getAtmIdByType(conn,
				level);
		MyClose.close(conn);
		return atmId;
	}

	public static List<Smsuser> getSmsListByType(String bugType) {
		Connection conn = ConnectManager.getSystemDB();
		List<Smsuser> smsList = DaoFactory.getSmsuserDao().getSmsListByType(
				conn, bugType);
		MyClose.close(conn);
		return smsList;
	}

	public static Smsuser getSmsMobile(String atmId, String type, String level) {
		Connection conn = ConnectManager.getSystemDB();
		Smsuser sms = DaoFactory.getSmsuserDao().getMobileByAtmId(conn, atmId,
				type, level);
		MyClose.close(conn);
		return sms;
	}

	public static List<Smsuser> lsgetSmsMobile(String atmId, String type, String level) {
		Connection conn = ConnectManager.getSystemDB();
		List<Smsuser> smsList = DaoFactory.getSmsuserDao().lsgetMobileByAtmId(conn, atmId,
				type, level);
		MyClose.close(conn);
		return smsList;
	}
	
	/** 根据 atmId 查找相应联系手机 */
	public static Smsuser getSmsMobile(String atmId) {
		Connection conn = ConnectManager.getSystemDB();
		Smsuser sms = DaoFactory.getSmsuserDao().getMobileByAtmId(conn, atmId);
		MyClose.close(conn);
		return sms;
	}

	/**
	 * 保存ATM历史状态信息
	 */
	public static void setAtmHistory(List<Atm> atmList) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getAtmhistoryDAO().setAtmHistory(conn, atmList);
		MyClose.close(conn);
	}

	public static void setSendType(Atmhistory atmList) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getAtmhistoryDAO().setSendType(conn, atmList);
		MyClose.close(conn);
	}

	public static void setAtmHistory(Atm atm) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getAtmhistoryDAO().setAtmHistory(conn, atm);
		MyClose.close(conn);
	}

	public static void saveMoneyValidAtmHistory(String atmid) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getAtmhistoryDAO().saveMoneyValidAtmHistory(conn, atmid);
		MyClose.close(conn);
	}

	public static void updateMoneyValidAtmHistory(String atmid) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getAtmhistoryDAO().updateMoneyValidAtmHistory(conn, atmid);
		MyClose.close(conn);
	}

	public static Atmhistory getMoneyValidAtmHistory(String atmid) {
		Connection conn = ConnectManager.getSystemDB();
		Atmhistory atmhistory = DaoFactory.getAtmhistoryDAO()
				.getMoneyValidAtmHistory(conn, atmid);
		MyClose.close(conn);
		return atmhistory;
	}

	public static List<Atminfo> getBankTypeList() {
		Connection conn = ConnectManager.getSystemDB();
		List<Atminfo> list = DaoFactory.getBankTypeDao().getBankTypeList(conn);
		MyClose.close(conn);
		return list;
	}

	public static Atminfo getBankType(Atminfo banktype) {
		Connection conn = ConnectManager.getSystemDB();
		Atminfo atmType = DaoFactory.getBankTypeDao().getBankType(conn,
				banktype);
		MyClose.close(conn);
		return atmType;
	}

	public static void modifyBankType(Atminfo banktype) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getBankTypeDao().modifyBankType(conn, banktype);
		MyClose.close(conn);
	}

	public static void deleteBankType(Atminfo banktype) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getBankTypeDao().deleteBankType(conn, banktype);
		MyClose.close(conn);
	}

	public static void setBankType(Atminfo banktype) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getBankTypeDao().setBankType(conn, banktype);
		MyClose.close(conn);
	}

	/**
	 * 保存交易量
	 */
	public static void setAtmTradata(List<Atmtradata> atmTradataList) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getAtmtradataDao().setTradata(conn, atmTradataList);
		MyClose.close(conn);
	}

	/**
	 * 保存交易手续费
	 */
	public static void setAtmTradata_1(List<AtmTradataMoney> atmTradataMonList) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getAtmtradataDao().setTradataMoney(conn, atmTradataMonList);
		MyClose.close(conn);
	}

	// public static void setAtmTradata(Atmtradata atmTradata) {
	// Connection conn = ConnectManager.getSystemDB();
	// DaoFactory.getAtmtradataDao().setTradata(conn, atmTradata);
	// MyClose.close(conn);
	// }
	/**
	 * 获取本地数据库的交易信息表
	 */
	// public String getLocalAtmtradata() {
	// Connection conn = ConnectManager.getSystemDB();
	// String last = DaoFactory.getAtmtradataDao().getDayLast(conn);
	// MyClose.close(conn);
	// return last;
	// }
	/**
	 * 获取当天取款交易量
	 */
	public static List<Atmtradata> getAtmTradataByTime(String atmId, String time) {
		Connection conn = ConnectManager.getSystemDB();
		List<Atmtradata> atmtradata = DaoFactory.getAtmtradataDao()
				.getAtmTradataByTime(conn, atmId, time);
		MyClose.close(conn);
		return atmtradata;
	}

	public static List<Atminfo> getAtmCompanyList() {
		Connection conn = ConnectManager.getSystemDB();
		List<Atminfo> atmCompanyList = DaoFactory.getAtmcompanyDao()
				.getAtmCompanyList(conn);
		MyClose.close(conn);
		return atmCompanyList;
	}

	public static Atminfo getAtmCompany(Atminfo atmcompany) {
		Connection conn = ConnectManager.getSystemDB();
		Atminfo atmCompany = DaoFactory.getAtmcompanyDao().getAtmCompany(conn,
				atmcompany);
		MyClose.close(conn);
		return atmCompany;
	}

	public static void deleteAtmCompany(Atminfo atmcompany) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getAtmcompanyDao().deleteAtmCompany(conn, atmcompany);
		MyClose.close(conn);
	}

	public static void setAtmCompany(Atminfo atmcompany) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getAtmcompanyDao().setAtmCompany(conn, atmcompany);
		MyClose.close(conn);
	}

	public static void modifyAtmCompany(Atminfo atmcompany) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getAtmcompanyDao().modifyAtmCompany(conn, atmcompany);
		MyClose.close(conn);
	}

	public static List<Atminfo> getAtmTypeList() {
		Connection conn = ConnectManager.getSystemDB();
		List<Atminfo> atmTypeList = DaoFactory.getAtmtypeDao().getAtmTypeList(
				conn);
		MyClose.close(conn);
		return atmTypeList;
	}

	public static Atminfo getAtmType(Atminfo atmtype) {
		Connection conn = ConnectManager.getSystemDB();
		Atminfo atmType = DaoFactory.getAtmtypeDao().getAtmType(conn, atmtype);
		MyClose.close(conn);
		return atmType;
	}

	public static void deleteAtmType(Atminfo atmtype) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getAtmtypeDao().deleteAtmType(conn, atmtype);
		MyClose.close(conn);
	}

	public static void setAtmType(Atminfo atmtype) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getAtmtypeDao().setAtmType(conn, atmtype);
		MyClose.close(conn);
	}

	public static void modifyAtmType(Atminfo atmtype) {
		Connection conn = ConnectManager.getSystemDB();
		DaoFactory.getAtmtypeDao().modifyAtmType(conn, atmtype);
		MyClose.close(conn);
	}

	// public static List<Atminfo> getRunTypeList() {
	// Connection conn = ConnectManager.getSystemDB();
	// List<Atminfo> runTypeList = DaoFactory.getAtmrunDao().getAtmRunList(
	// conn);
	// MyClose.close(conn);
	// return runTypeList;
	// }
	//
	// public static Atminfo getRunType(Atminfo atmtype) {
	// Connection conn = ConnectManager.getSystemDB();
	// Atminfo atmType = DaoFactory.getAtmrunDao().getAtmRun(conn, atmtype);
	// MyClose.close(conn);
	// return atmType;
	// }
	//
	// public static void setRunType(Atminfo runtype) {
	// Connection conn = ConnectManager.getSystemDB();
	// DaoFactory.getAtmrunDao().setAtmRun(conn, runtype);
	// MyClose.close(conn);
	// }
	//
	// public static void modifyRunType(Atminfo runtype) {
	// Connection conn = ConnectManager.getSystemDB();
	// DaoFactory.getAtmrunDao().modifyRunType(conn, runtype);
	// MyClose.close(conn);
	// }
	//
	// public static void deleteRunType(Atminfo runtype) {
	// Connection conn = ConnectManager.getSystemDB();
	// DaoFactory.getAtmrunDao().deleteAtmRun(conn, runtype);
	// MyClose.close(conn);
	// }

	public static List<Unittype> getUnitTypeList() {
		Connection conn = ConnectManager.getSystemDB();
		List<Unittype> unitTypeList = DaoFactory.getUnittypeDao()
				.getUnitTypeList(conn);
		MyClose.close(conn);
		return unitTypeList;
	}

	/** *********************************************************************************************** */

	// // 导数据
	// public static void saveDate(File file) {
	// // 句容
	// List<Atmtradata> tradataList = LogReader.saveLogToBase(file);
	// setAtmTradata(tradataList);
	//		
	// // 姜堰
	// // Process ps = null;
	// // String cmd = "db2cmd.exe C:/db2import/import_tradata.bat";
	// // try {
	// // logger.info("开始导数据");
	// // ps = Runtime.getRuntime().exec(cmd);
	// // logger.info("导数据完成");
	// // } catch (IOException e) {
	// // ps.destroy();
	// // logger.error(e.getLocalizedMessage(), e);
	// // }
	//
	// }
	// private static String getEjrPathByDate() {
	// int month = MyTime.getYestoday().getMonth();
	// int day = MyTime.getYestoday().getDay();
	// String strMonth = String.valueOf(month), strDay = String.valueOf(day);
	// if (month < 10)
	// strMonth = "0" + strMonth;
	// if (day < 10)
	// strDay = "0" + strDay;
	// return strMonth + strDay;
	// }
	private static String disLastSign(String str) {
		if (str.indexOf(">") > 0)
			str = str.split(">")[0];
		return str;
	}

	/**
	 * 读取流水
	 */
	public static List<Ejr> getTradataFromEjr(File file) {
		List<Ejr> ejrList = null;
		Ejr ejr = null;
		BufferedReader br = null;

		String acceptCard = "AcceptCard";
		String card = "Pan:";
		String cwdStr = "CWD Amount:";
		String cass = "Cass:";
		String rc = "RC";
		String rcSuccess = "RC:0000";
		String inqRc = "INQ RC:0000";
		String cwcRc = "CWC RC:0000";
		String cashTaken = "Cash Taken";
		String cardTaken = "Card Taken";

		int cwd = 0, dispense = 0, reject = 0;
		// String disStr = null;
		String cardNo = null;
		String tradetime = null;
		float allRmb = 0;
		try {
			br = new BufferedReader(new FileReader(file));
			ejrList = new ArrayList<Ejr>();
			String strLine = "";
			// 除去特殊字符
			Pattern p_1 = Pattern.compile("\\-");
			// 开始读取流水
			while ((strLine = br.readLine()) != null) {
				// System.out.println("%%%%%%%%%%%%%%%%%%%%" + strLine);
				// 有卡插入开始读取用户操作
				if (strLine.indexOf(acceptCard) > 0) {
					// 继续读取
					while ((strLine = br.readLine()) != null) {
						if (strLine.indexOf(card) > 0
								&& !(strLine.indexOf("DestPan:") > 0))
							// 读取卡号
							cardNo = strLine.trim().split(card)[1];
						// System.out.println(strLine);
						boolean breakCondition = false;
						// 如果是取款动作
						if (strLine.indexOf(cwdStr) > 0) {
							// 取款金额
							String cwd_str = strLine.split(cwdStr)[1];
							Matcher m = p_1.matcher(cwd_str);
							cwd_str = m.replaceAll("");
							String cwd_amount = cwd_str.substring(0,
									cwd_str.length() - 8).trim();
							tradetime = cwd_str.substring(cwd_str.length() - 8,
									cwd_str.length()).trim();
							cwd = Integer.parseInt(cwd_amount.equals("") ? "0"
									: cwd_amount);
							// 如果取款金额大于100才是有效交易
							if (cwd >= 100 && cwd % 100 == 0) {
								// 读取回收金额

								// 继续读取
								while ((strLine = br.readLine()) != null) {
									// System.out.println(strLine);
									// 如果后台返回为0000才能正常出钞,剔除查询成功记录
									if (!(strLine.indexOf(cardTaken) > 0)) {
										if (strLine.indexOf(rc) > 0) {
											// 出钞后判断是否出错冲正
											if (strLine.indexOf(rcSuccess) > 0
													&& !(strLine.indexOf(inqRc) > 0)) {
												while ((strLine = br.readLine()) != null) {
													if (!(strLine
															.indexOf(cardTaken) > 0)) {
														// 出现冲正成功丢弃这笔交易
														// System.out.println(strLine);
														if (strLine
																.indexOf(cwcRc) > 0) {
															break;
														}
														// 出钞记录交易
														else if (strLine
																.indexOf(cashTaken) > 0) {
															// System.out
															// .println(cardNo
															// + ":"
															// + cwd_str);
															// 记录交易信息
															float rmb = cwd
																	- reject;
															allRmb += rmb;
															ejr = new Ejr();
															ejr
																	.setCardNo(cardNo);
															ejr
																	.setTradetime(tradetime);
															ejr.setInput(cwd);
															// ejr.setDispense(disStr);
															ejr
																	.setReject(reject);
															ejr
																	.setTradermb(rmb);
															ejrList.add(ejr);
															// System.out.println(cardNo
															// +
															// ":"
															// + tradetime + ":"
															// +
															// cwd
															// + ":" + rmb);
															// 如果只是取钱操作
															breakCondition = true;
															break;
														}
													} else {
														breakCondition = true;
														break;
													}
												}
											} else {
												breakCondition = true;
											}
										}
										if (breakCondition)
											break;
									} else
										break;
								}
							}
						} else if (strLine.indexOf(cardTaken) > 0)
							break;

					}

				}
			}
			// 设置特殊卡号为"-1"的金额为当日交易总金额
			ejr = new Ejr();
			ejr.setCardNo("-1");
			ejr.setTradermb(allRmb);
			ejrList.add(ejr);
			// System.out.println(allRmb);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				logger.error(e.getLocalizedMessage(), e);
			} finally {
				br = null;
			}

		}
		return ejrList;
	}

	public static void main(String[] args) {
		// String s = "3000 09:16:55------";
		// Pattern p_1 = Pattern.compile("\\-");
		// Matcher m = p_1.matcher(s);
		// s = m.replaceAll("");
		// System.out.println(s.substring(0, s.length() - 8));
		// getTradataFromEjr(new File("f:/a/asd/71380002"));
		// System.out
		// .println(MyTime.getFormatDate("020456", "HHmmss", "HH:mm:ss"));
		String box = "1";
		for (int i = 0; i < box.length(); i += 8) {
			// String type_1 = box.substring(0 + i, 1 + i);
			// String type = box.substring(1 + i, 2 + i);

			System.out.println(box.substring(i + 4, i + 8));
		}
	}

}

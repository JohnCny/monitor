package cn.sh.ae.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.sh.ae.inteface.IAtmDao;
import cn.sh.ae.util.MyConstant;
import cn.sh.ae.util.MyTime;
import cn.sh.ae.vo.Atm;

/**
 * 
 */
public class AtmDao implements IAtmDao {

	private static final long serialVersionUID = -822345916168381912L;

	static Logger logger = Logger.getLogger(AtmcDao.class.getName());

	public List<Atm> getAtmStatus(Connection conn, int level, String style,
			String clzss) {
		List<Atm> list = null;
		Atm atm = null;
		String sql = "select atm.atmid,atm.atmid_new,banktype.name_ch,atm.partrmb,atm.box,atm.depormb,atm.demstatus,atm.prjstatus,atm.prrstatus,atm.cdmstatus,atm.depstatus,atm.readerstatus,atm.linestatus,atm.route,atmtype.type_en,atmcompany.company_en from atm,atmcompany,atmtype,banktype where atm.type=banktype.id and atm.company=atmcompany.id and atm.route=atmtype.id ";

		if (clzss != null && !clzss.equals("")) {
			String[] clzsses = clzss.split(",");

			if (!clzss.equals("0,0,0,0,0,0,0,0"))
				sql += "and (";
			if (clzsses[0].equals("1"))
				sql += " demstatus!='0' or ";
			if (clzsses[1].equals("1"))
				sql += "(route='2' and company='3' and prjstatus='4') or ";
			if (clzsses[2].equals("1"))
				sql += "prrstatus='4' or ";
			if (clzsses[3].equals("1"))
				sql += "cdmstatus!='0' or ";
			if (clzsses[4].equals("1"))
				sql += "(route ='2' and depstatus!='0') or ";
			if (clzsses[5].equals("1"))
				sql += "readerstatus!='0' or ";
			if (clzsses[6].equals("1"))
				sql += "linestatus!='0' or ";
			if (clzsses[7].equals("1"))
				sql += "box like '3%' or ";
			if (!clzss.equals("0,0,0,0,0,0,0,0"))
				sql += "1!=1)";
		}

		if (level != 0) {
			sql += " and atm.type='" + level + "'";
		}

		sql += " order by atmid";
		logger.info(sql);
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			list = new ArrayList<Atm>();
			while (rs.next()) {
				atm = new Atm();
				atm.setAtmid(rs.getString("atmid"));
				atm.setAtmid_new(rs.getString("atmid_new"));
				// String boxstatus = rs.getString("box").trim().substring(0,
				// 1);
				String demstatus = rs.getString("demstatus");
				String prjstatus = rs.getString("prjstatus");
				String prrstatus = rs.getString("prrstatus");
				String cdmstatus = rs.getString("cdmstatus");
				String depstatus = rs.getString("depstatus");
				String readerstatus = rs.getString("readerstatus");
				String linestatus = rs.getString("linestatus");
				String route = rs.getString("route");
				linestatus = (linestatus == null) ? "1" : linestatus;
				String allstatus = "1";

				boolean statusFlag = demstatus.equals("0")
						&& cdmstatus.equals("0") && readerstatus.equals("0")
						&& linestatus.equals("0") ? true : false;
				// && !boxstatus.equals("3")
				if (route.equals("2")) {
					if (statusFlag && !prjstatus.equals("4"))
						allstatus = "0";
				} else {
					if (statusFlag)
						allstatus = "0";
				}

				if (style == null || style.equals("") || style.equals("1")) {
					atm.setDemstatus(demstatus);
					atm.setPrjstatus(prjstatus);
					atm.setPrrstatus(prrstatus);
					atm.setCdmstatus(cdmstatus);
					atm.setDepstatus("-");
					if (route.equals("2")) {
						atm.setDepstatus(depstatus);
						if (!depstatus.equals("0"))
							allstatus = "1";
					}
					atm.setReaderstatus(readerstatus);
					atm.setLinestatus(linestatus);
				} else if (style.equals("2")) {
					atm.setAllstatus(allstatus);
				}
				atm.setPartrmb(rs.getString("partrmb"));
				atm.setDepormb(rs.getString("depormb"));
				atm.setCompany(rs.getString("company_en"));
				atm.setRoute(rs.getString("type_en"));
				String type = rs.getString("name_ch");
				atm.setAddr(type.length() > 4 ? type.substring(0, 4) : type);
				list.add(atm);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			list = null;
		}
		return list;
	}

	@Override
	public List<Atm> getAtmByCompany(Connection conn, String company) {
		List<Atm> list = null;
		try {
			ResultSet rs = conn
					.createStatement()
					.executeQuery(
							"select atmid,atmid_new,addr,type_en from atm,atmtype where atm.route=atmtype.id and COMPANY='"
									+ company + "' order by atmid");
			list = new ArrayList<Atm>();
			while (rs.next()) {
				Atm atm = new Atm();
				atm.setAtmid(rs.getString("atmid"));
				atm.setAtmid_new(rs.getString("atmid_new"));
				atm.setAllstatus(rs.getString("atmid") + " "
						+ rs.getString("type_en") + " " + rs.getString("addr"));
				list.add(atm);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			list = null;
		}
		return list;
	}

	@Override
	public Atm getAtmAddressByAtmid(Connection conn, String atmid) {
		Atm atm = null;
		String sql = "select atmid_new,IP,PORT,ENCODING,TIMEOUT,UPLOAD,EJR,picture,company,aesyspath,tracepath,addr from atm where atmid='"
				+ atmid + "' order by atmid";
		// logger.info(sql);
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);

			atm = new Atm();
			while (rs.next()) {
				atm.setAtmid(atmid);
				atm.setAtmid_new(rs.getString("atmid_new"));
				atm.setIp(rs.getString("IP"));
				atm.setPort(rs.getString(MyConstant.Table.Atm.PORT));
				atm.setEncoding(rs.getString(MyConstant.Table.Atm.ENCODING));
				atm.setTimeout(rs.getString(MyConstant.Table.Atm.TIMEOUT));
				atm.setUpload(rs.getString("UPLOAD"));
				atm.setEjr(rs.getString("EJR"));
				atm.setCompany(rs.getString("company"));
				atm.setPicture(rs.getString("picture"));
				atm.setAesyspath(rs.getString("aesyspath"));
				atm.setTracepath(rs.getString("tracepath"));
				atm.setAddr(rs.getString("addr"));

			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			atm = null;
		}
		return atm;
	}

	@Override
	public Atm getAtmByAtmId(Connection conn, String atmid) {
		Atm show = null;
		try {
			ResultSet rs = conn
					.createStatement()
					.executeQuery(
							"select * from atm,atmcompany,atmtype,banktype where atm.company=atmcompany.id and atm.route=atmtype.id and atm.type=banktype.id and atmid='"
									+ atmid + "'");
			while (rs.next()) {
				show = new Atm();
				show.setAtmid(rs.getString("atmid"));
				show.setAtmid_new(rs.getString("atmid_new"));
				show.setDemstatus(rs.getString("demstatus"));
				show.setPrjstatus(rs.getString("prjstatus"));
				show.setPrrstatus(rs.getString("prrstatus"));
				show.setCdmstatus(rs.getString("cdmstatus"));
				String route = rs.getString("route");
				show.setDepstatus("-");
				if (route.equals("2"))
					show.setDepstatus(rs.getString("depstatus"));
				show.setReaderstatus(rs.getString("readerstatus"));
				show.setLinestatus(rs.getString("linestatus"));
				show.setPartrmb(rs.getString("partrmb"));
				show.setDepormb(rs.getString("depormb"));
				show.setAddr(rs.getString("addr"));
				show.setIp(rs.getString("ip"));
				show.setBox(rs.getString("box"));
				show.setCompany(rs.getString("company_ch"));
				show.setType(rs.getString("name_ch"));
				show.setEncoding(rs.getString("encoding"));
				String tradetime = rs.getString("tradetime");
				String tradetime_show = tradetime.substring(0, 2) + "年"
						+ tradetime.substring(2, 4) + "月"
						+ tradetime.substring(4, 6) + "日 "
						+ tradetime.substring(6, 8) + ":"
						+ tradetime.substring(8, 10) + ":"
						+ tradetime.substring(10, 12);
				show.setTradetime(tradetime_show);
				show.setRoute(rs.getString("type_ch"));
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			show = null;
		}
		return show;
	}

	@Override
	public List<Atm> getAtmList(Connection conn, int level) {
		List<Atm> list = null;
		Atm atm = null;
		String sql = "select * from atm,atmtype where atm.route=atmtype.id ";
		if (level != 0)
			sql += " and atm.type='" + level + "' ";
		sql += "order by atm.atmid";
		logger.info(sql);
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			list = new ArrayList<Atm>();
			while (rs.next()) {
				atm = new Atm();
				atm.setAtmid(rs.getString("atmid"));
				atm.setAtmid_new(rs.getString("atmid_new"));
				atm.setCompany(rs.getString("company"));
				atm.setTradetime(rs.getString("tradetime"));

				atm.setCdmstatus(rs.getString("cdmstatus"));
				atm.setDemstatus(rs.getString("demstatus"));
				atm.setDepstatus(rs.getString("depstatus"));
				atm.setReaderstatus(rs.getString("readerstatus"));
				atm.setLinestatus(rs.getString("linestatus"));
				atm.setPrjstatus(rs.getString("prjstatus"));
				atm.setPrrstatus(rs.getString("prrstatus"));
				atm.setDepormb(rs.getString("depormb"));
				atm.setPartrmb(rs.getString("partrmb"));

				atm.setIp(rs.getString("IP"));
				atm.setPort(rs.getString("PORT"));
				atm.setEncoding(rs.getString("ENCODING"));
				atm.setTimeout(rs.getString("TIMEOUT"));
				atm.setUpload(rs.getString("UPLOAD"));
				atm.setEjr(rs.getString("EJR"));
				atm.setPicture(rs.getString("PICTURE"));
				atm.setRoute(rs.getString("ROUTE"));
				atm.setAddr(rs.getString("ADDR"));
				atm.setCompany(rs.getString("company"));
				atm.setType(rs.getString("type"));
				atm.setAllstatus(rs.getString("atmid") + " "
						+ rs.getString("type_en") + " " + rs.getString("ADDR"));
				atm.setAesyspath(rs.getString("aesyspath"));
				atm.setTracepath(rs.getString("tracepath"));
				list.add(atm);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			list = null;
		}
		return list;
	}

	@Override
	public void setAtmAddress(Connection conn, Atm atm) {
		String sql = "update atm set atmid_new='" + atm.getAtmid_new()
				+ "',IP='" + atm.getIp() + "',EJR='" + atm.getEjr()
				+ "',picture='" + atm.getPicture() + "',UPLOAD='"
				+ atm.getUpload() + "',encoding='" + atm.getEncoding()
				+ "',timeout='" + atm.getTimeout() + "',port='" + atm.getPort()
				+ "', route='" + atm.getRoute() + "',TYPE='" + atm.getType()
				+ "', ADDR='" + atm.getAddr() + "',COMPANY='"
				+ atm.getCompany() + "',AESYSPATH='" + atm.getAesyspath()
				+ "',TRACEPATH='" + atm.getTracepath() + "' where atmid='"
				+ atm.getAtmid() + "'";
		try {
			conn.createStatement().executeUpdate(sql);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	@Override
	public List<Atm> getMoneyStatus(Connection conn, String type,
			String[] atmids, String qcash, String ccash) {
		List<Atm> list = null;
		Atm atm = null;
		int flag = (atmids == null || atmids.length == 0) ? 0
				: atmids.length == 1 ? 1 : 2;

		String sql = "select atmid,atmid_new,addr,partrmb,depormb,type from atm where 1=1";

		if (type.equals("999999")) {
			if (flag != 0) {
				sql += " and (1<>1";
				for (int i = 0; i < atmids.length; i++) {
					sql += " or atmid='" + atmids[i] + "'";
				}
				sql += ")";
			}
		} else if (!type.equals("0")) {
			sql += " and TYPE='" + type + "'";
		}
		if (qcash != null && !qcash.equals(""))
			sql += " and cast(partrmb as int)<=" + qcash;
		if (ccash != null && !ccash.equals(""))
			sql += " and cast(depormb as int)<=" + ccash;

		sql += " order by cast(partrmb as int)";
		logger.info(sql);
		try {
			list = new ArrayList<Atm>();
			ResultSet rs = conn.createStatement().executeQuery(sql);
			while (rs.next()) {
				atm = new Atm();
				atm.setAtmid(rs.getString("atmid"));
				atm.setAtmid_new(rs.getString("atmid_new"));
				atm.setPartrmb(rs.getString("partrmb"));
				atm.setDepormb(rs.getString("depormb"));
				atm.setAddr(rs.getString("addr"));
				atm.setType(rs.getString("type").trim());
				list.add(atm);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			list = null;
		}
		return list;
	}

	@Override
	public List<Atm> saveOrUpdateAtm(Connection conn, List<Atm> list) {
		List<Atm> changeAtm = null;
		// 状态已存在更新
		String localSqlU = "update atm set tradetime=?,box=?,partrmb=?,depormb=?,demstatus=?,prjstatus=?,prrstatus=?,cdmstatus=?,depstatus=?,readerstatus=?,ip=? where atmid=?";
		// 状态不存在插入
		String localSqlI = "insert into atm values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		boolean su = false, si = false;
		PreparedStatement ptU = null;
		PreparedStatement ptI = null;
		try {
			ptU = conn.prepareStatement(localSqlU);
			ptI = conn.prepareStatement(localSqlI);
			if (list != null && !list.isEmpty()) {
				changeAtm = new ArrayList<Atm>();
				// logger.info("P端数据记录数：" + list.size());
				for (Atm atm : list) {
					if (isExist(conn, atm.getAtmid())) {
						su = true;
						ptU.setString(1, atm.getTradetime());
						ptU.setString(2, atm.getBox());
						ptU.setString(3, atm.getPartrmb());
						ptU.setString(4, atm.getDepormb());
						ptU.setString(5, atm.getDemstatus());
						ptU.setString(6, atm.getPrjstatus());
						ptU.setString(7, atm.getPrrstatus());
						ptU.setString(8, atm.getCdmstatus());
						ptU.setString(9, atm.getDepstatus());
						ptU.setString(10, atm.getReaderstatus());
						ptU.setString(11, atm.getIp());
						ptU.setString(12, atm.getAtmid());
						ptU.addBatch();
						changeAtm.add(atm);
					} else {
						// logger.info(atm.getAtmid());
						si = true;
						ptI.setString(1, atm.getAtmid());
						ptI.setString(2, atm.getTradetime());
						ptI.setString(3, atm.getBox());
						ptI.setString(4, atm.getPartrmb());
						ptI.setString(5, atm.getDepormb());
						ptI.setString(6, atm.getDemstatus());
						ptI.setString(7, atm.getPrjstatus());
						ptI.setString(8, atm.getPrrstatus());
						ptI.setString(9, atm.getCdmstatus());
						ptI.setString(10, atm.getDepstatus());
						ptI.setString(11, atm.getReaderstatus());
						ptI.setString(12, "0");
						ptI.setString(13, atm.getIp());
						ptI.setString(14, "");
						ptI.setString(15, "");
						ptI.setString(16, "");
						ptI.setString(17, "");
						ptI.setString(18, "");
						ptI.setString(19, "");
						ptI.setString(20, "");
						ptI.setString(21, "0");
						ptI.setString(22, "");
						ptI.setString(23, "0");
						ptI.addBatch();
						changeAtm.add(atm);
					}
				}
				if (su)
					ptU.executeBatch();
				if (si)
					ptI.executeBatch();
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error(e.getLocalizedMessage(), e);
			}
			changeAtm = null;
			logger.error(e.getLocalizedMessage(), e);
		}
		return changeAtm;
	}

	private boolean isExist(Connection conn, String atmId) {
		String sql = "select count(atmid) as c from atm where atmid='" + atmId
				+ "'";
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			if (rs.next()) {
				if (rs.getInt("c") > 0)
					return true;
				else
					return false;
			}
			return false;
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			return false;
		}
	}

	@Override
	public List<Atm> getType(Connection conn, int level) {
		List<Atm> list = null;
		Atm ka = null;
		String sql = "select id,name_ch from atm,banktype where atm.type=banktype.id ";
		if (level != 0)
			sql += "and banktype.id='" + level + "'";
		sql += " group by id,name_ch order by id";
		logger.info(sql);
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			list = new ArrayList<Atm>();
			while (rs.next()) {
				ka = new Atm();
				ka.setAtmid(rs.getString("id"));
				ka.setType(rs.getString("name_ch"));
				list.add(ka);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			list = null;
		}
		return list;
	}

	@Override
	public List<Atm> getAtmCompany(Connection conn) {
		List<Atm> list = null;
		Atm ksAtm = null;
		String sql = "select id,company_ch from atm,atmcompany where atm.company=atmcompany.id group by id,company_ch";
		try {
			list = new ArrayList<Atm>();
			ResultSet rs = conn.createStatement().executeQuery(sql);
			while (rs.next()) {
				ksAtm = new Atm();
				ksAtm.setAtmid(rs.getString("id"));
				ksAtm.setCompany(rs.getString("company_ch"));
				list.add(ksAtm);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			return null;
		}
		return list;
	}

	@Override
	public List<Atm> getOuttimeLineStatus(Connection conn, String subTime) {
		List<Atm> atmList = new ArrayList<Atm>();
		Atm atm = null;
		String sql = "select atmid,ip,port from atm where tradetime<'"
				+ subTime + "'";
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			while (rs.next()) {
				atm = new Atm();
				atm.setIp(rs.getString("ip"));
				atm.setPort(rs.getString("port"));
				atmList.add(atm);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		return atmList;
	}

	@Override
	public void setLineStatus(Connection conn, String atmid) {
		String sql = "update atm set linestatus='1' where atmid='" + atmid
				+ "'";
		// logger.info(sql);
		try {
			conn.createStatement().executeUpdate(sql);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	@Override
	public void saveOrUpdateAtm(Connection conn, Atm atm) {
		// 状态已存在更新
		String localSqlU = "update atm set tradetime=?,box=?,partrmb=?,depormb=?,demstatus=?,prjstatus=?,prrstatus=?,cdmstatus=?,depstatus=?,readerstatus=?,linestatus=?,ip=? where atmid=?";
		// 状态不存在插入
		String localSqlI = "insert into atm (atmid ,atmid_new,tradetime ,box,partrmb,depormb,demstatus,prjstatus,prrstatus,cdmstatus,depstatus,readerstatus,linestatus,ip,port,ejr,picture,upload ,encoding ,timeout ,route ,type ,addr ,company ,aesyspath ,tracepath ) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ptU = null;
		PreparedStatement ptI = null;
		try {
			ptU = conn.prepareStatement(localSqlU);
			ptI = conn.prepareStatement(localSqlI);
			if (isExist(conn, atm.getAtmid())) {
				// logger.info(localSqlU);
				ptU.setString(1, atm.getTradetime());
				ptU.setString(2, atm.getBox());
				ptU.setString(3, atm.getPartrmb());
				ptU.setString(4, atm.getDepormb());
				ptU.setString(5, atm.getDemstatus());
				ptU.setString(6, atm.getPrjstatus());
				ptU.setString(7, atm.getPrrstatus());
				ptU.setString(8, atm.getCdmstatus());
				ptU.setString(9, atm.getDepstatus());
				ptU.setString(10, atm.getReaderstatus());
				ptU.setString(11, atm.getLinestatus());
				ptU.setString(12, atm.getIp());
				ptU.setString(13, atm.getAtmid());
				ptU.execute();
			} else {
				logger.info("新设备加入:" + atm.getAtmid());
				ptI.setString(1, atm.getAtmid());
				ptI.setString(2, "");
				ptI.setString(3, atm.getTradetime());
				ptI.setString(4, atm.getBox());
				ptI.setString(5, atm.getPartrmb());
				ptI.setString(6, atm.getDepormb());
				ptI.setString(7, atm.getDemstatus());
				ptI.setString(8, atm.getPrjstatus());
				ptI.setString(9, atm.getPrrstatus());
				ptI.setString(10, atm.getCdmstatus());
				ptI.setString(11, atm.getDepstatus());
				ptI.setString(12, atm.getReaderstatus());
				ptI.setString(13, atm.getLinestatus());
				ptI.setString(14, atm.getIp());
				ptI.setString(15, "");
				ptI.setString(16, "");
				ptI.setString(17, "");
				ptI.setString(18, "");
				ptI.setString(19, "");
				ptI.setString(20, "0");
				ptI.setString(21, "0");
				ptI.setString(22, "");
				ptI.setString(23, "0");
				ptI.setString(24, "");
				ptI.setString(25, "");
				ptI.setString(26, "");
				ptI.execute();
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error(e.getLocalizedMessage(), e);
			}
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	@Override
	public List<Atm> getBugAtmList(Connection conn) {
		List<Atm> list = null;
		Atm atm = null;
		String sql = "select atmid,atmid_new,tradetime,addr,partrmb,depormb,demstatus,cdmstatus,depstatus,readerstatus,linestatus,type,box,prjstatus,prrstatus from atm where demstatus!='0'  or cdmstatus!='0' or depstatus!='0' or readerstatus!='0' or linestatus!='0' or cast(partrmb as int)< (select maxoutmoney from atmp)";
		logger.info(sql);
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			list = new ArrayList<Atm>();
			while (rs.next()) {
				atm = new Atm();
				atm.setAtmid(rs.getString("atmid"));
				atm.setAtmid_new(rs.getString("atmid_new"));
				atm.setTradetime(rs.getString("tradetime"));
				atm.setCdmstatus(rs.getString("cdmstatus"));
				atm.setDemstatus(rs.getString("demstatus"));
				atm.setDepstatus(rs.getString("depstatus"));
				atm.setLinestatus(rs.getString("linestatus"));
				atm.setReaderstatus(rs.getString("readerstatus"));
				atm.setPartrmb(rs.getString("partrmb"));
				atm.setDepormb(rs.getString("depormb"));
				atm.setAddr(rs.getString("addr"));
				atm.setType(rs.getString("type"));
				atm.setBox(rs.getString("box"));
				atm.setPrjstatus(rs.getString("prjstatus"));
				atm.setPrrstatus(rs.getString("prrstatus"));
				list.add(atm);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			list = null;
		}
		return list;
	}

	@Override
	public List<Atm> getMoneyValidAtm(Connection conn) {
		List<Atm> list = null;
		Atm atm = null;
		String sql = "select atmid,addr,partrmb,depormb,company_ch,maxoutmoney,maxinmoney from (select atmid,addr,cast(partrmb as int) as partrmb,cast(depormb as int) as depormb ,atmcompany.company_ch,cast((select maxoutmoney from atmp) as int) as maxoutmoney,cast((select maxinmoney from atmp) as int) as maxinmoney from atm inner join atmcompany on atm.company=atmcompany.id) as temp  where  partrmb<maxoutmoney or depormb>maxinmoney";
		logger.info(sql);
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			list = new ArrayList<Atm>();
			while (rs.next()) {
				atm = new Atm();
				atm.setAtmid(rs.getString("atmid"));
				atm.setPartrmb(rs.getString("partrmb"));
				atm.setDepormb(rs.getString("depormb"));
				atm.setAddr(rs.getString("addr"));
				atm.setCompany(rs.getString("company_ch"));
				atm.setMaxinmoney(rs.getString("maxinmoney"));
				atm.setMaxoutmoney(rs.getString("maxoutmoney"));
				list.add(atm);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			list = null;
		}
		return list;
	}

	@Override
	public Atm getChangeAtm(Connection conn, Atm atm) {
		// Atm retAtm = null;
		try {
			String sql = "select count(*) as atmcount from atm where atmid='"
					+ atm.getAtmid() + "' and cdmstatus='" + atm.getCdmstatus()
					+ "' and depstatus='" + atm.getDepstatus()
					+ "' and readerstatus='" + atm.getReaderstatus()
					+ "' and prjstatus='" + atm.getPrjstatus()
					+ "' and prrstatus='" + atm.getPrrstatus() + "'";
			// logger.info(sql);
			ResultSet rs = conn.createStatement().executeQuery(sql);
			if (rs.next()) {
				// 如果该条记录不存在，说明报文有变化
				if (rs.getInt("atmcount") == 0) {
					ResultSet rs1 = conn.createStatement().executeQuery(
							"select type from atm where atmid='"
									+ atm.getAtmid() + "'");
					// 查询该条记录前次状态
					if (rs1.next()) {
						atm.setType(rs1.getString("type"));
					} else
						atm.setType(null);
				} else
					atm.setType(null);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		return atm;
	}

	@Override
	public List<Atm> getAtmInfo(Connection conn, String type, String route,
			String[] atmids) {
		List<Atm> list = null;
		Atm atm = null;
		String sql = "select company_en,count(company_en) as companyCount from atm,atmcompany where atm.company=atmcompany.id ";
		if (atmids != null && atmids.length != 0) {
			sql += " and (1<>1 ";
			for (int i = 0; i < atmids.length; i++)
				sql += " or atmid='" + atmids[i] + "'";
			sql += ")";
		} else {
			if (!type.equals("9"))
				sql += " and type='" + type + "'";
		}

		sql += " group by company_en";
		logger.info(sql);
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			list = new ArrayList<Atm>();
			while (rs.next()) {
				atm = new Atm();
				atm.setCompany(rs.getString("company_en"));
				atm.setAllstatus(String.valueOf(rs.getInt("companyCount")));
				list.add(atm);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			list = null;
		}
		return list;
	}

	@Override
	public Atm getBoxChangeAtm(Connection conn, Atm atm) {
		// Atm retAtm = null;
		try {
			String sql = "select partrmb,type from atm where atmid='"
					+ atm.getAtmid() + "'";
			// logger.info(sql);
			ResultSet rs = conn.createStatement().executeQuery(sql);
			if (rs.next()) {
				String partrmb_old = rs.getString("partrmb");
				String partrmb_new = atm.getPartrmb();
				int partrmb_old_int = Integer
						.parseInt(partrmb_old == null ? "0" : partrmb_old);
				int partrmb_new_int = Integer
						.parseInt(partrmb_new == null ? "0" : partrmb_new);
				if (((partrmb_old_int - partrmb_new_int) < 0)
						|| (partrmb_old_int > 300 && partrmb_new_int < 300)) {
					atm.setType(rs.getString("type"));
				} else
					atm.setType(null);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		return atm;
	}

	@Override
	public void updateAtmTradetime(Connection conn, Atm atm) {
		String sql = "update atm set linestatus='0',tradetime='"
				+ atm.getTradetime() + "' where atmid='" + atm.getAtmid() + "'";
		// logger.info(sql);
		try {
			conn.createStatement().executeUpdate(sql);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}

	}

	@Override
	public List<Atm> getDayCheck(Connection conn, String[] atmids,
			String timebegin, String type) {
		List<Atm> list = null;
		int flag = (atmids == null || atmids.length == 0) ? 0
				: atmids.length == 1 ? 1 : 2;
		String sql = "select * from atmdaycheck where tradetime='" + timebegin
				+ "'";
		if (type.equals("999999")) {
			if (flag != 0) {
				sql += " and (1<>1";
				for (int i = 0; i < atmids.length; i++) {
					sql += " or atmid='" + atmids[i] + "'";
				}
				sql += ")";
			}
		} else if (!type.equals("0"))
			sql += " and type='" + type + "'";
		try {
			logger.info(sql);
			ResultSet rs = conn.createStatement().executeQuery(sql);
			list = new ArrayList<Atm>();
			while (rs.next()) {
				Atm atm = new Atm();
				atm.setAtmid(rs.getString("atmid"));
				atm.setAtmid_new(rs.getString("atmid_new"));
				atm.setTradetime(rs.getString("tradetime"));
				atm.setPartrmb(rs.getString("partrmb"));
				atm.setType(rs.getString("partrmb"));
				atm.setAddr(rs.getString("addr"));
				list.add(atm);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		return list;
	}

	@Override
	public void setDayCheck(Connection conn, List<Atm> list) {
		String localSqlI = "insert into atmdaycheck values(?,?,?,?,?)";
		PreparedStatement ptI = null;
		try {
			if (!isExist_(conn, MyTime.getTime("yyyyMMdd"))) {
				ptI = conn.prepareStatement(localSqlI);
				if (list != null && !list.isEmpty()) {
					// logger.info("P端数据记录数：" + list.size());
					for (Atm atm : list) {
						ptI.setString(1, atm.getAtmid());
						ptI.setString(2, MyTime.getTime("yyyyMMdd"));
						ptI.setString(3, atm.getPartrmb());
						ptI.setString(4, atm.getType());
						ptI.setString(5, atm.getAddr());
						ptI.addBatch();
					}
					ptI.executeBatch();
				}
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error(e.getLocalizedMessage(), e);
			}
			logger.error(e.getLocalizedMessage(), e);
		}
	}

	private boolean isExist_(Connection conn, String tradetime) {
		String sql = "select count(atmid) as c from atmdaycheck where tradetime='"
				+ tradetime + "'";
		try {
			ResultSet rs = conn.createStatement().executeQuery(sql);
			if (rs.next()) {
				if (rs.getInt("c") > 0)
					return true;
				else
					return false;
			}
			return false;
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			return false;
		}
	}

	@Override
	public Atm getChangeStatusAtm(Connection conn, Atm atm) {
		Atm retAtm = new Atm();
		try {
			String sql = "select cdmstatus,depstatus,readerstatus,prjstatus,prrstatus,PARTRMB,depormb from atm where atmid='"
					+ atm.getAtmid() + "'";
			// logger.info(sql);
			ResultSet rs = conn.createStatement().executeQuery(sql);
			// 如果有记录，则判断变化的状态
			if (rs.next()) {
				String cdmstatus = rs.getString("cdmstatus");
				String depstatus = rs.getString("depstatus");
				String readerstatus = rs.getString("readerstatus");
				String prjstatus = rs.getString("prjstatus");
				String prrstatus = rs.getString("prrstatus");
				String partrmb = rs.getString("PARTRMB");
				String depormb = rs.getString("depormb");

				// ////////////////////////////////////////
				if (cdmstatus.equals(atm.getCdmstatus()))
					retAtm.setCdmstatus(null);
				else {
					if (atm.getCdmstatus().equals("4"))
						retAtm.setCdmstatus("1");
					else if (atm.getCdmstatus().equals("0"))
						retAtm.setCdmstatus("0");
					else
						retAtm.setCdmstatus(null);
				}
				// /////////////////////////////////////////
				if (depstatus.equals(atm.getDepstatus()))
					retAtm.setDepstatus(null);
				else {
					if (atm.getDepstatus().equals("4"))
						retAtm.setDepstatus("1");
					else if (atm.getDepstatus().equals("0"))
						retAtm.setDepstatus("0");
					else
						retAtm.setDepstatus(null);
				}
				// ////////////////////////////////////////
				if (readerstatus.equals(atm.getReaderstatus()))
					retAtm.setReaderstatus(null);
				else {
					if (atm.getReaderstatus().equals("4"))
						retAtm.setReaderstatus("1");
					else if (atm.getReaderstatus().equals("0"))
						retAtm.setReaderstatus("0");
					else
						retAtm.setReaderstatus(null);
				}
				// ///////////////////////////////////////
				if (prjstatus.equals(atm.getPrjstatus()))
					retAtm.setPrjstatus(null);
				else {
					if (atm.getPrjstatus().equals("4")
							|| atm.getPrrstatus().equals("6"))
						retAtm.setPrjstatus("1");
					else if (atm.getPrjstatus().equals("3"))
						retAtm.setPrjstatus("2");
					else if (atm.getPrjstatus().equals("0"))
						retAtm.setPrjstatus("0");
					else
						retAtm.setPrjstatus(null);
				}
				// //////////////////////////////////////
				if (prrstatus.equals(atm.getPrrstatus()))
					retAtm.setPrrstatus(null);
				else {
					if (atm.getPrrstatus().equals("4")
							|| atm.getPrrstatus().equals("6"))
						retAtm.setPrrstatus("1");
					else if (atm.getPrrstatus().equals("3"))
						retAtm.setPrrstatus("2");
					else if (atm.getPrrstatus().equals("0"))
						retAtm.setPrrstatus("0");
					else
						retAtm.setPrrstatus(null);
				}
				// //////////////////////////////////////
				if (partrmb.equals(atm.getPartrmb()))
					retAtm.setPartrmb(null);
				else {
					int cRmb = Integer.parseInt(atm.getPartrmb());
					int pRmb = Integer.parseInt(partrmb);
					if (cRmb < 300 && pRmb >= 300) {
						logger.info(atm.getAtmid() + "/////金额对比:C端" + cRmb
								+ "...P端" + pRmb);
						retAtm.setPartrmb("5");
					} else if (cRmb >= pRmb || cRmb>=300)
					{
						logger.info("机器金额大于300张，重置atmhistory记录");
						retAtm.setPartrmb("0");
					}
					else if (cRmb<=0)
						retAtm.setPartrmb("7");
					else
						retAtm.setPartrmb(null);
				}
				// //////////////////////////////////////
				if (depormb.equals(atm.getDepormb()))
					retAtm.setDepormb(null);
				else {
					int cRmb = Integer.parseInt(atm.getDepormb());
					int pRmb = Integer.parseInt(depormb);
					if (cRmb > 1500 && pRmb <= 1500) {
						logger.info(atm.getAtmid() + "/////金额对比:C端" + cRmb
								+ "...P端" + pRmb);
						retAtm.setDepormb("6");
					} else if (cRmb < pRmb && pRmb <= 1500)
						retAtm.setDepormb("0");
					else
						retAtm.setDepormb(null);
				}
			} else {
				if (atm.getCdmstatus().equals("4"))
					retAtm.setCdmstatus("1");
				else
					retAtm.setCdmstatus(null);
				if (atm.getDepstatus().equals("4"))
					retAtm.setDepstatus("1");
				else
					retAtm.setDepstatus(null);
				if (atm.getReaderstatus().equals("4"))
					retAtm.setReaderstatus("1");
				else
					retAtm.setReaderstatus(null);
				if (atm.getPrjstatus().equals("4")
						|| atm.getPrjstatus().equals("6"))
					retAtm.setPrjstatus("1");
				else if (atm.getPrjstatus().equals("3"))
					retAtm.setPrjstatus("2");
				else
					retAtm.setPrjstatus(null);
				if (atm.getPrrstatus().equals("4")
						|| atm.getPrrstatus().equals("6"))
					retAtm.setPrrstatus("1");
				else if (atm.getPrrstatus().equals("3"))
					retAtm.setPrjstatus("2");
				else
					retAtm.setPrrstatus(null);
				int cRmb = Integer.parseInt(atm.getPartrmb());
				if (cRmb <= 300)
					retAtm.setPartrmb("5");
				else
					retAtm.setPartrmb(null);
				cRmb = Integer.parseInt(atm.getDepormb());
				if (cRmb >= 1500)
					retAtm.setDepormb("6");
				else
					retAtm.setDepormb(null);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
		return retAtm;
	}

	@Override
	public List<Atm> getNoNetBugAtmList(Connection con) {
		List<Atm> list = null;
		Atm atm = null;
		try {
			ResultSet rs = con
					.createStatement()
					.executeQuery(
							"select atmid,tradetime from atm where LINESTATUS='0' order by atmid");
			list = new ArrayList<Atm>();
			while (rs.next()) {
				atm = new Atm();
				atm.setAtmid(rs.getString("atmid"));
				atm.setTradetime(rs.getString("tradetime"));
				list.add(atm);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
			list = null;
		}
		return list;
	}

	@Override
	public void deleteAtm(Connection conn, Atm atm) {
		String sql = "delete from atm  where atmid='" + atm.getAtmid() + "'";
		try {
			conn.createStatement().executeUpdate(sql);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}
	}
}
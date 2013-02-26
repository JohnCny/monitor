package cn.sh.ae.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

import cn.sh.ae.manage.DataManager;
import cn.sh.ae.vo.Atm;
import cn.sh.ae.vo.Atmp;
import cn.sh.ae.vo.User;

public class AtmStateAction extends DispatchAction {

	public ActionForward all(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm tlrForm = (DynaActionForm) form;
		// 获取监控权限
		int emlevel = ((User) request.getSession().getAttribute("login"))
				.getTitle();
		// 显示方式
		String style = tlrForm.getString("style");
		// 设备
		// String atm = tlrForm.getString("atm");
		// 显示类型
		String sbgz = tlrForm.getString("sbgz").equals("") ? "0" : "1";
		String lsdyjgz = tlrForm.getString("lsdyjgz").equals("") ? "0" : "1";
		String ptdyjgz = tlrForm.getString("ptdyjgz").equals("") ? "0" : "1";
		String ccqgz = tlrForm.getString("ccqgz").equals("") ? "0" : "1";
		String qkmkgz = tlrForm.getString("qkmkgz").equals("") ? "0" : "1";
		String dkqgz = tlrForm.getString("dkqgz").equals("") ? "0" : "1";
		String xlgz = tlrForm.getString("xlgz").equals("") ? "0" : "1";
		String cxgz = tlrForm.getString("cxgz").equals("") ? "0" : "1";
		String type = sbgz + "," + lsdyjgz + "," + ptdyjgz + "," + ccqgz + ","
				+ qkmkgz + "," + dkqgz + "," + xlgz + "," + cxgz;

		String showtype = "";
		String showType = request.getParameter("showType");
		if (showType != null && showType.equals("error")) {
			type = "1,0,0,1,1,1,1,1";
			showtype = "/暂停服务设备";
		} else {
			if (sbgz.equals("1"))
				showtype += "/无服务";
			if (lsdyjgz.equals("1"))
				showtype += "/流水打印机故障";
			if (ptdyjgz.equals("1"))
				showtype += "/凭条打印机故障";
			if (ccqgz.equals("1"))
				showtype += "/出钞模块故障";
			if (qkmkgz.equals("1"))
				showtype += "/取款模块故障";
			if (dkqgz.equals("1"))
				showtype += "/读卡器故障";
			if (xlgz.equals("1"))
				showtype += "/线路故障";
			if (cxgz.equals("1"))
				showtype += "/钞箱故障";
		}
		if (type.equals("0,0,0,0,0,0,0,0"))
			showtype = "/全部设备";

		String attr = "atmstatus";
		if (style == null || style.equals("") || style.equals("1"))
			attr += "1";
		else
			attr += "2";

		// if (emlevel != 111)
		// atm = String.valueOf(emlevel);

		List<Atm> atmList = DataManager
				.getAtmListByStatus(emlevel, style, type);

		// request.setAttribute("typelist",
		// DataManager.getAtmServerType(emlevel));

		request.setAttribute(attr, atmList);

		request.setAttribute("AtmCount", atmList.size());

		request.setAttribute("showtype", showtype);
		
		if (attr.equals("atmstatus1"))
			return mapping.findForward("allsuccesslist");
		else if (attr.equals("atmstatus2"))
			return mapping.findForward("allsuccessicon");
		else
			return mapping.findForward("");
	}

	public ActionForward byId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// request.setAttribute("atmstatusinfo", );
		List<Atm> list = new ArrayList<Atm>();
		Atm atm = DataManager.getAtmById(request.getParameter("id"));
		list.add(atm);

		JSONArray json = JSONArray.fromObject(list);
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(json);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

		// return mapping.findForward("byidsuccess");
	}

	public ActionForward setAddress(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm tlrForm = (DynaActionForm) form;
		Atm atm = new Atm();
		atm.setAtmid(tlrForm.getString("atmid"));
		atm.setAtmid_new(tlrForm.getString("atmid_new"));
		atm.setIp(tlrForm.getString("ip"));
		atm.setPort(tlrForm.getString("port"));
		atm.setEncoding(tlrForm.getString("encoding"));
		atm.setTimeout(tlrForm.getString("timeout"));
		atm.setUpload(tlrForm.getString("upload"));
		atm.setEjr(tlrForm.getString("ejr"));
		atm.setPicture(tlrForm.getString("picture"));
		atm.setRoute(tlrForm.getString("route"));
		atm.setAddr(tlrForm.getString("addr"));
		atm.setCompany(tlrForm.getString("company"));
		atm.setType(tlrForm.getString("type"));
		atm.setTracepath(tlrForm.getString("tracepath"));
		atm.setAesyspath(tlrForm.getString("aesyspath"));
		DataManager.setAtmAddress(atm);
		return null;
		// return mapping.findForward("setaddresssuccess");
	}

	public ActionForward deleteAtm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String atmId = request.getParameter("id");
		Atm atm = new Atm();
		atm.setAtmid(atmId);
		DataManager.deleteAtm(atm);
		return mapping.findForward("setaddresssuccess");
	}
}

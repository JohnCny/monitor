package cn.sh.ae.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

import cn.sh.ae.manage.DataManager;
import cn.sh.ae.vo.Smsuser;
import cn.sh.ae.vo.User;

public class SmsAction extends DispatchAction {

	static Logger logger = Logger.getLogger(SmsAction.class.getName());

	public ActionForward all(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("smsuser", DataManager.getSmsUserList());
		return mapping.findForward("success");
	}

	public ActionForward modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DataManager.modifySmsUser(getSms((DynaActionForm) form));
		return mapping.findForward("setsuccess");
	}

	public ActionForward getByMoblie(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int level = ((User) request.getSession().getAttribute("login"))
				.getTitle();
		String id = request.getParameter("id");
		String bugtype = request.getParameter("bugtype");
		String solvetime = request.getParameter("solvetime");
		Smsuser smsuser = new Smsuser();
		smsuser.setMoblie(id);
		smsuser.setBugtype(bugtype);
		smsuser.setSolvetime(solvetime);

		Smsuser user = DataManager.getSmsUserByMoblie(smsuser);
		List<String> list1 = new ArrayList<String>();
		list1.add(user.getBugtype());
		list1.add(user.getSolvetime());
		String[] atmids = user.getAtmids().split(",");
		int length = atmids.length;
		list1.add(String.valueOf(length));
		for (int i = 0; i < length; i++) {
			list1.add(atmids[i]);
		}
		list1.add(user.getMoblie());
		list1.add(user.getName());
		list1.add(user.getStatus());

		// 用户
		// request.setAttribute("userbymoblie", user);
		// 已选设备号
		// request.setAttribute("atmids", list);
		// 未被使用设备号
		List<String> list = DataManager.getAtmByBugType(level);
		list.addAll(0, list1);
		// request.setAttribute("atmidbytype",
		// DataManager.getAtmByBugType(String
		// .valueOf(user.getBugtype())));

		JSONArray json = JSONArray.fromObject(list);
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(json);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
		// return mapping.findForward("bymobliesuccess");
	}

	public ActionForward set(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		DynaActionForm tlrForm = (DynaActionForm) form;
		Smsuser smsuser = getSms((DynaActionForm) form);
		Smsuser smsuser1 = DataManager.getSmsUserByMoblie(smsuser);
		if (smsuser1 != null)
			request.setAttribute("setsmsuser_err", smsuser1);
		else
			DataManager.setSmsUser(smsuser);
		return mapping.findForward("setsuccess");
	}

	public ActionForward deleteMoblie(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		String bugtype = request.getParameter("bugtype");
		String solvetime = request.getParameter("solvetime");
		Smsuser smsuser = new Smsuser();
		smsuser.setMoblie(id);
		smsuser.setBugtype(bugtype);
		smsuser.setSolvetime(solvetime);
		DataManager.deleteMoblie(smsuser);
		return mapping.findForward("setsuccess");
	}

	public ActionForward getAtmIdByType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int level = ((User) request.getSession().getAttribute("login"))
				.getTitle();
		// String type = request.getParameter("type");
		JSONArray json = JSONArray.fromObject(DataManager
				.getAtmByBugType(level));
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(json);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
		// request.setAttribute("atmidbytype",
		// DataManager.getAtmByBugType(type));
		// request.setAttribute("type", type);
		// return mapping.findForward("typesuccess");
	}

	private Smsuser getSms(DynaActionForm tlrForm) {
		Smsuser smsuser = new Smsuser();
		smsuser.setMoblie(tlrForm.getString("moblie"));
		smsuser.setMoblie_old(tlrForm.getString("moblie_old"));
		smsuser.setName(tlrForm.getString("name"));
		String[] atmids = tlrForm.getStrings("atmids");
		String atmid = "";
		for (int i = 0; i < atmids.length; i++) {
			atmid += atmids[i];
			if (i != atmids.length - 1)
				atmid += ",";
		}
		smsuser.setAtmids(atmid);
		smsuser.setBugtype(tlrForm.getString("bugtype"));
		smsuser.setStatus(tlrForm.getString("status"));
		smsuser.setSolvetime(tlrForm.getString("solvetime"));
		smsuser.setSolvetime_old(tlrForm.getString("solvetime_old"));
		return smsuser;
	}

}

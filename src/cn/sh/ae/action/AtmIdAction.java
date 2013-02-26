package cn.sh.ae.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

import cn.sh.ae.manage.DataManager;
import cn.sh.ae.vo.User;

public class AtmIdAction extends DispatchAction {

	public ActionForward byCompany(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm tlrForm = (DynaActionForm) form;
		request.setAttribute("atmid", DataManager.getAtmListByCompany(tlrForm
				.getString("macprovider").trim()));
		return mapping.findForward("usuccess");
	}

	public ActionForward getId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int level = ((User) request.getSession().getAttribute("login"))
				.getTitle();
		request.setAttribute("typelist", DataManager.getAtmServerType(level));
		request.setAttribute("atmid", DataManager.getAtmList(level));
		return mapping.findForward("tsuccess");
	}

	public ActionForward getId3(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int level = ((User) request.getSession().getAttribute("login"))
				.getTitle();
		request.setAttribute("typelist", DataManager.getAtmServerType(level));
		request.setAttribute("atmid", DataManager.getAtmList(level));
		return mapping.findForward("yxsuccess");
	}

	public ActionForward getCashAtmId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int level = ((User) request.getSession().getAttribute("login"))
				.getTitle();
		request.setAttribute("atmid", DataManager.getAtmList(level));
		System.out.println("level=" + level);
		request.setAttribute("typelist", DataManager.getAtmServerType(level));
		return mapping.findForward("csuccess");
	}

	public ActionForward getId1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int level = ((User) request.getSession().getAttribute("login"))
				.getTitle();
		request.setAttribute("atmid", DataManager.getAtmList(level));
		return mapping.findForward("reportsuccess");
	}

	public ActionForward getId2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int level = ((User) request.getSession().getAttribute("login"))
				.getTitle();
		request.setAttribute("atmid", DataManager.getAtmList(level));
		return mapping.findForward("smssuccess");
	}

	public ActionForward getId4(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int level = ((User) request.getSession().getAttribute("login"))
				.getTitle();
		request.setAttribute("atmid", DataManager.getAtmList(level));
		return mapping.findForward("snapShotsuccess");
	}

	public ActionForward getCheckId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int level = ((User) request.getSession().getAttribute("login"))
				.getTitle();
		request.setAttribute("atmid", DataManager.getAtmList(level));
		request.setAttribute("typelist", DataManager.getAtmServerType(level));
		return mapping.findForward("checksuccess");
	}

	public ActionForward getRebootId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int level = ((User) request.getSession().getAttribute("login"))
				.getTitle();
		if (DataManager.getAtmcStatus().getRebootstatus().equals("0"))
			return mapping.findForward("close");
		else {
			request.setAttribute("atmid", DataManager.getAtmList(level));
			return mapping.findForward("rsuccess");
		}
	}

	public ActionForward getDownloadId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int level = ((User) request.getSession().getAttribute("login"))
				.getTitle();
		if (DataManager.getAtmcStatus().getDownloadstatus().equals("0"))
			return mapping.findForward("close");
		else {
			request.setAttribute("atmid", DataManager.getAtmList(level));
			return mapping.findForward("dsuccess");
		}
	}

	public ActionForward getUploadId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int level = ((User) request.getSession().getAttribute("login"))
				.getTitle();
		DynaActionForm tlrForm = (DynaActionForm) form;
		if (DataManager.getAtmcStatus().getUploadstatus().equals("0"))
			return mapping.findForward("close");
		else {
			request.setAttribute("atmid", DataManager
					.getAtmListByCompany(tlrForm.getString("macprovider")));
			request.setAttribute("atmcompany", DataManager.getAtmCompany());
			return mapping.findForward("usuccess");
		}
	}

	// private void setRequest(HttpServletRequest request) throws SQLException {
	// request.setAttribute("atmid", DataManager.getAtmList());
	// }
}

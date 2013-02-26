package cn.sh.ae.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.sh.ae.manage.DataManager;

public class AtmCompanyAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String type = request.getParameter("type");
		if (type.equals("u")
				&& DataManager.getAtmcStatus().getUploadstatus().equals("0"))
			return mapping.findForward("close");
		else if (type.equals("d")
				&& DataManager.getAtmcStatus().getDownloadstatus().equals("0"))
			return mapping.findForward("close");
		request.setAttribute("atmcompany", DataManager.getAtmCompany());
//		request.setAttribute("atmcompany_name", DataManager.getAtmCompanyList());
		return mapping.findForward("success" + type);
	}

}

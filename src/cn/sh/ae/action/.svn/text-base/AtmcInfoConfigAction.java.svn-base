package cn.sh.ae.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import cn.sh.ae.manage.DataManager;
import cn.sh.ae.vo.User;

public class AtmcInfoConfigAction extends DispatchAction {

	public ActionForward all(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int level = ((User) request.getSession().getAttribute("login"))
				.getTitle();
		request.setAttribute("atmcompany", DataManager.getAtmCompanyList());
		request.setAttribute("atmtype", DataManager.getAtmTypeList());
		request.setAttribute("runtype", DataManager.getBankTypeList());
		request.setAttribute("atmaddress", DataManager.getAtmList(level));
		return mapping.findForward("success");
	}

}

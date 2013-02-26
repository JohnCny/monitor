package cn.sh.ae.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

import cn.sh.ae.manage.DataManager;
import cn.sh.ae.vo.Sms;

public class SmsConfigAction extends DispatchAction {

	public ActionForward all(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("smsuser", DataManager.getSmsUserList());
		request.setAttribute("smscontrolstatus", DataManager.getSmsStatus());
		return mapping.findForward("success");
	}

	public ActionForward set(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm tlrForm = (DynaActionForm) form;
		Sms sms = new Sms();
		sms.setSmsstatus(tlrForm.getString("smsstatus"));
		sms.setFirsttime(tlrForm.getString("firsttime"));
		sms.setSecondtime(tlrForm.getString("secondtime"));
		sms.setThirdtime(tlrForm.getString("thirdtime"));
		
		DataManager.setSmsStatus(sms);

		return mapping.findForward("setsuccess");
	}
}

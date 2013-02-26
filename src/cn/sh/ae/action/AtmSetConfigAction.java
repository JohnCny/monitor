package cn.sh.ae.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

import cn.sh.ae.manage.DataManager;
import cn.sh.ae.tools.MsTimerTask;
import cn.sh.ae.vo.Atmc;
import cn.sh.ae.vo.Atmp;

public class AtmSetConfigAction extends DispatchAction {

	public ActionForward all(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request
				.setAttribute("remotecontrolstatus", DataManager
						.getAtmcStatus());
		request.setAttribute("atmpstatus", DataManager.getAtmpStatus());
		return mapping.findForward("success");
	}

	public ActionForward setAtmc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm tlrForm = (DynaActionForm) form;
		String autodownload = tlrForm.getString("autodownload");
		String netmonitorstatus = tlrForm.getString("netmonitorstatus");
		Atmc atmc = new Atmc();
		atmc.setDownloadstatus(tlrForm.getString("downloadstatus"));
		atmc.setRebootstatus(tlrForm.getString("rebootstatus"));
		atmc.setUploadstatus(tlrForm.getString("uploadstatus"));
		atmc.setAutodownload(autodownload);
		atmc.setDownloadtime(tlrForm.getString("downloadtime"));
		atmc.setNetmonitorstatus(netmonitorstatus);
		atmc.setNetmonitortime(tlrForm.getString("netmonitortime"));
		DataManager.setAtmcStatus(atmc);
		MsTimerTask.stopDownTimer();
		MsTimerTask.stopMoniTimer();
		MsTimerTask.startDownTimer(autodownload);
		MsTimerTask.startMoniTimer(netmonitorstatus);
		return null;
		//return mapping.findForward("setsuccess");
	}

	public ActionForward setAtmp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm tlrForm = (DynaActionForm) form;
		Atmp atmp = new Atmp();
		atmp.setDbip(tlrForm.getString("dbip"));
		atmp.setDbname(tlrForm.getString("dbname"));
		atmp.setDbpassword(tlrForm.getString("dbpassword"));
		atmp.setDbport(tlrForm.getString("dbport"));
		atmp.setDbusername(tlrForm.getString("dbusername"));
		String mgrtstatus = tlrForm.getString("mgrtstatus");
		String sycnstatus = tlrForm.getString("sycnstatus");
		atmp.setMgrtstatus(mgrtstatus);
		atmp.setMgrttime(tlrForm.getString("mgrttime"));
		atmp.setMgrtpath(tlrForm.getString("mgrtpath"));
		atmp.setSycnstatus(sycnstatus);
		atmp.setSycntime(tlrForm.getString("sycntime"));
		atmp.setFtpip(tlrForm.getString("ftpip"));
		atmp.setFtpport(tlrForm.getString("ftpport"));
		atmp.setFtppath(tlrForm.getString("ftppath"));
		atmp.setFtpusername(tlrForm.getString("ftpusername"));
		atmp.setFtppassword(tlrForm.getString("ftppassword"));
		atmp.setMaxinmoney(tlrForm.getString("maxinmoney"));
		atmp.setMaxoutmoney(tlrForm.getString("maxoutmoney"));
		
		DataManager.setAtmpStatus(atmp);
		MsTimerTask.stopMgrtTimer();
		MsTimerTask.stopSyncTimer();
		MsTimerTask.startMgrtTimer(mgrtstatus);
		MsTimerTask.startSyncTimer(sycnstatus);
		return null;
		//return mapping.findForward("setsuccess");
	}
}

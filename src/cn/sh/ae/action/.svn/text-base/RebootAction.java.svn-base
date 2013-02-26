package cn.sh.ae.action;

import java.net.Socket;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import cn.sh.ae.factory.AccessFactory;
import cn.sh.ae.manage.DataManager;
import cn.sh.ae.tools.AtmcClient;
import cn.sh.ae.vo.Atm;

public class RebootAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm fileForm = (DynaActionForm) form;
		String atmid = (String) fileForm.get("atmid");
		reboot(atmid);
		return mapping.findForward("success");
	}

	private void reboot(String atmId) {
		// 获取需要下载文件的主机
		Atm atm = DataManager.getAtmAdderssById(atmId);
		// 建立连接
		Socket socket = AccessFactory.getSocketAccess().getSocket(atm.getIp(),
				Integer.parseInt(atm.getPort()),
				Integer.parseInt(atm.getTimeout()));

		String command = "shutdown.exe -r -f -t 00";

		AtmcClient.setResponse(socket, 3);
		if (AtmcClient.getRequest(socket) == 1)
			AtmcClient.sendMessage(socket, command);
	}

}

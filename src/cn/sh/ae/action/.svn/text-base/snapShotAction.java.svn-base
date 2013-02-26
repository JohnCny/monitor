package cn.sh.ae.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.sh.ae.manage.ConnectManager;
import cn.sh.ae.manage.DataManager;
import cn.sh.ae.tools.AtmcClient;
import cn.sh.ae.util.MyConstant;
import cn.sh.ae.util.MyTime;
import cn.sh.ae.vo.Atm;

public class snapShotAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// DynaActionForm fileForm = (DynaActionForm) form;
		String atmId = request.getParameter("id");
		// String[] atmids = (String[]) fileForm.get("atmids");
		String[] atmids = { atmId };
		System.out.println(atmId);

		List<Atm> atmPngList = new ArrayList<Atm>();
		Atm atmPng = null;
		int size = atmids.length;
		for (int i = 0; i < size; i++) {
			atmPng = new Atm();
			Atm atm = DataManager.getAtmAdderssById(atmids[i]);
			Socket socket = ConnectManager.getAtmClient(atm.getIp(), Integer
					.parseInt(atm.getPort()), Integer
					.parseInt(atm.getTimeout()));
			AtmcClient.setResponse(socket, 4);
			if (AtmcClient.getRequest(socket) == 1) {
				AtmcClient.receiveFile(socket, MyConstant.SNAPSHOTPATH,
						atmids[i] + ".png");
				AtmcClient.setResponse(socket, 0);
			}
			AtmcClient.close(socket);
			atmPng.setAtmid(atmids[i]);
			atmPng.setTradetime(MyTime.getTime("yyyy-MM-dd HH:mm:ss"));
			atmPng.setRoute(MyConstant.SNAPSHOTPATH_WEB + atmids[i] + ".png");
			// atmPng.setRoute("E:\\workspace\\workPro\\monitor\\WebRoot\\resources\\source\\images\\user_bg.jpg");
			atmPng.setAddr(atm.getAddr());
			atmPngList.add(atmPng);

		}

		JSONArray json = JSONArray.fromObject(atmPngList);
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(json);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		// request.setAttribute("atmid", DataManager.getAtmList());
		// request.setAttribute("atmPngList", atmPngList);
		// return mapping.findForward("success");
	}

}

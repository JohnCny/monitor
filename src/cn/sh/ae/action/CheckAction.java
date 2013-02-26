package cn.sh.ae.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;

import cn.sh.ae.manage.DataManager;
import cn.sh.ae.manage.WorkManager;
import cn.sh.ae.util.MyConstant;
import cn.sh.ae.util.MyFile;
import cn.sh.ae.util.MyTime;
import cn.sh.ae.vo.Atm;
import cn.sh.ae.vo.Ejr;
import cn.sh.ae.vo.MyDate;

public class CheckAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm tlrForm = (DynaActionForm) form;
		String type = tlrForm.getString("type");
		String[] atmIds = tlrForm.getStrings("atmids");
		String time = tlrForm.getString("time");
		FormFile myFile = (FormFile) tlrForm.get("file");
		// if (type != null && type.equals("6")) {
		// MyDate date = MyTime.getDate(time, "yyyy-MM-dd");
		// String localRoute = MyConstant.ATMLOGPATH + date.getYear()
		// + File.separator + date.getMonth() + File.separator
		// + (date.getDay() - 1) + File.separator;
		// // ±£´æÎÄ¼þ
		// MyFile.saveFile(localRoute, myFile.getFileName(), myFile
		// .getFileData());
		// }
		List<String> atmIdList = new ArrayList<String>();

		if (!type.equals("999999")) {
			List<Atm> atmList = DataManager.getAtmListByStatus(Integer
					.parseInt(type), "", "");
			for (Atm atmId : atmList) {
				atmIdList.add(atmId.getAtmid());
			}
		} else
			for (int i = 0; i < atmIds.length; i++)
				atmIdList.add(atmIds[i].trim());

		//
		// request.setAttribute("checklist", WorkManager.getCheckEjrAndDb(
		// atmIdList, time));
		// return mapping.findForward("success");
		HashMap<String, List<Ejr>> map = WorkManager.getCheckEjrAndDb(
				atmIdList, time);
		JSONObject json = new JSONObject();
		for (Iterator iter = map.entrySet().iterator(); iter.hasNext();) {
			Map.Entry element = (Map.Entry) iter.next();
			String strKey = String.valueOf(element.getKey());
			List<Ejr> strObj = (List<Ejr>) element.getValue();
			json.put(strKey, strObj);
		}

		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(json);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}

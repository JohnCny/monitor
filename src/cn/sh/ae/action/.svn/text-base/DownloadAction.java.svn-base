package cn.sh.ae.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import cn.sh.ae.manage.DataManager;
import cn.sh.ae.manage.WorkManager;
import cn.sh.ae.util.MyConstant;
import cn.sh.ae.util.MyFile;
import cn.sh.ae.util.MyTime;
import cn.sh.ae.vo.Atm;

public class DownloadAction extends org.apache.struts.actions.DownloadAction {

	@Override
	protected StreamInfo getStreamInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String method = request.getParameter("method");
		DynaActionForm fileForm = (DynaActionForm) form;
		String atmid = (String) fileForm.get("atmid");
		String logtime = fileForm.getString("logtime");
		String remPath = fileForm.getString("remPath");
		// String fileName = "log.rar";// (String) fileForm.get("fileName");
		// String sessionid = request.getSession().getId();
		// 从ATM上下载文件到服务器
		// download(atmid, fileName, sessionid);
		// 从服务器上下载文件到本地
		Atm atm = DataManager.getAtmAdderssById(atmid);
		List<Atm> atmAddressList = new ArrayList<Atm>();
		atmAddressList.add(atm);
		String path = null;
		if (method != null && method.equals("9")) {
			WorkManager.DownFile(atmAddressList, remPath);
			String[] remPaths = remPath.split("\\\\");
			path = MyConstant.TEMPPATH + remPaths[remPaths.length - 1];
		} else {
			String date = MyTime.getPreDate("yyyyMMdd");
			if (logtime != null && !logtime.equals(""))
				date = MyTime.getFormatDate(logtime, "yyyy-MM-dd", "yyyyMMdd");
			path = MyConstant.ATMLOGPATH + date + File.separator + atmid.trim();

			WorkManager.autoDownEjr(atmAddressList, date, method, true);
		}
		File file = MyFile.getFile(path);

		String contentType = "application/x-download"; // 接收类型为下载
		response.setContentType(contentType); // 设置接收类型
		response.setHeader("Content-Disposition", "attachment;filename="
				+ file.getName() + MyTime.getTime("yyyyMMdd"));
		//
		return new FileStreamInfo(contentType, file);
		// return null;
	}
}

package cn.sh.ae.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import cn.sh.ae.manage.DataManager;
import cn.sh.ae.tools.WriteExcelCommon;
import cn.sh.ae.util.MyFile;
import cn.sh.ae.util.MyTime;
import cn.sh.ae.vo.Atm;
import cn.sh.ae.vo.Atmhistory;
import cn.sh.ae.vo.Draw;

public class Excel2Action extends org.apache.struts.actions.DownloadAction {

	@Override
	protected StreamInfo getStreamInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaActionForm tlrForm = (DynaActionForm) form;
		String reportType = tlrForm.getString("reportType");
		String equipType = tlrForm.getString("equipType");
		// String transType = tlrForm.getString("transType");
		String timebegin = tlrForm.getString("timebegin");
		String timeend = tlrForm.getString("timeend");
		String[] atmids = (String[]) tlrForm.get("atmids");
		// String type = tlrForm.getString("type");
		String route = tlrForm.getString("route");
		String errType = tlrForm.getString("errtype");
		String errTime = tlrForm.getString("errtime");

		
		List<Atm> kasList = null;
		List<Atmhistory> kasHisList = null;
		File file = null;
		if (reportType.equals("1")) {
			kasHisList = DataManager.getAtmBugList(equipType, atmids, timebegin,
					timeend,errType,errTime);
			file = MyFile.getFile(MyFile.getXlsFolder() + "gzfx"
					+ MyTime.getTime("yyyyMMddHHmmss") + ".xls");
			WriteExcelCommon.writeFaultExcel(file, timebegin + "~" + timeend
					+ " " + "故障分析", kasHisList);
		}
		if (reportType.equals("2")) {
			kasList = DataManager.getAtmInfoList(equipType, route, atmids);
			file = MyFile.getFile(MyFile.getXlsFolder() + "jbqk"
					+ MyTime.getTime("yyyyMMddHHmmss") + ".xls");
			WriteExcelCommon.writeAtmInfoExcel(file, timebegin + "~" + timeend
					+ " " + "基本情况", kasList);
		}

		String contentType = "application/msexcel"; // 接收类型为下载
		response.setContentType(contentType); // 设置接收类型
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(file.getName().getBytes(), "UTF-8"));
		return new FileStreamInfo(contentType, file);

	}
}

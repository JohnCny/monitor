package cn.sh.ae.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;

import cn.sh.ae.manage.DataManager;
import cn.sh.ae.manage.WorkManager;
import cn.sh.ae.tools.WriteExcelCommon;
import cn.sh.ae.util.MyConstant;
import cn.sh.ae.util.MyFile;
import cn.sh.ae.util.MyTime;
import cn.sh.ae.vo.Atm;
import cn.sh.ae.vo.Ejr;
import cn.sh.ae.vo.MyDate;

public class CheckReportAction extends org.apache.struts.actions.DownloadAction {

	@Override
	protected StreamInfo getStreamInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获取数据源
		// DataSource ds = (DataSource)
		// servlet.getServletContext().getAttribute(
		// "datas");
		DynaActionForm tlrForm = (DynaActionForm) form;
		String type = tlrForm.getString("type");
		String[] atmIds = tlrForm.getStrings("atmids");
		String time = tlrForm.getString("time");
		FormFile myFile = (FormFile) tlrForm.get("file");
		// if (myFile != null) {
		// MyDate date = MyTime.getDate(time, "yyyy-MM-dd");
		// String localRoute = MyConstant.ATMLOGPATH + date.getYear()
		// + File.separator + date.getMonth() + File.separator
		// + (date.getDay() - 1) + File.separator;
		// System.out.println(localRoute);
		// // 保存文件
		// MyFile.saveFile(localRoute, myFile.getFileName(), myFile
		// .getFileData());
		//		}
		List<String> atmIdList = new ArrayList<String>();

			if (!type.equals("999999")) {
				List<Atm> atmList = DataManager
						.getAtmListByStatus(Integer.parseInt(type), "", "");
				for (Atm atmId : atmList) {
					atmIdList.add(atmId.getAtmid());
				}
			} else
				for (int i = 0; i < atmIds.length; i++)
					atmIdList.add(atmIds[i].trim());
		
		// // 保存文件
		// String fileName = myFile.getFileName();
		HashMap<String, List<Ejr>> map = WorkManager.getCheckEjrAndDb(
				atmIdList, time);
		// List<Ejr> list = EJRReader.doCheck(ds, atmid, fileName, myFile
		// .getFileData(), time, sessionid);

		File file = MyFile.getFile(MyFile.getXlsFolder() + "dzb"
				+ MyTime.getTime("yyyyMMdd")+ ".xls");
		WriteExcelCommon.writeCheckExcel(file, map);

		String contentType = "application/msexcel"; // 接收类型为下载
		response.setContentType(contentType); // 设置接收类型
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(file.getName().getBytes(), "UTF-8"));
		return new FileStreamInfo(contentType, file);
	}
}

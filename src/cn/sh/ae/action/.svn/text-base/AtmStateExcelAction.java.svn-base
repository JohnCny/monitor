//package cn.sh.ae.action;
//
//import java.io.File;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.struts.action.ActionForm;
//import org.apache.struts.action.ActionMapping;
//import org.apache.struts.action.DynaActionForm;
//
//import cn.sh.ae.manage.DataManager;
//import cn.sh.ae.tools.WriteExcelCommon;
//import cn.sh.ae.util.MyFile;
//import cn.sh.ae.util.MyTime;
//import cn.sh.ae.vo.Atm;
//import cn.sh.ae.vo.User;
//
//public class AtmStateExcelAction extends
//		org.apache.struts.actions.DownloadAction {
//
//	@Override
//	protected StreamInfo getStreamInfo(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		DynaActionForm tlrForm = (DynaActionForm) form;
//		// 获取监控权限
//		int emlevel = ((User) request.getSession().getAttribute("login"))
//				.getEmlevel();
//		// 显示方式
//		String style = tlrForm.getString("style");
//		// 设备
//		String atm = tlrForm.getString("atm");
//		// 显示类型
//		String sbgz = tlrForm.getString("sbgz").equals("") ? "0" : "1";
//		String lsdyjgz = tlrForm.getString("lsdyjgz").equals("") ? "0" : "1";
//		String ptdyjgz = tlrForm.getString("ptdyjgz").equals("") ? "0" : "1";
//		String ccqgz = tlrForm.getString("ccqgz").equals("") ? "0" : "1";
//		String qkmkgz = tlrForm.getString("qkmkgz").equals("") ? "0" : "1";
//		String dkqgz = tlrForm.getString("dkqgz").equals("") ? "0" : "1";
//		String xlgz = tlrForm.getString("xlgz").equals("") ? "0" : "1";
//		String cxgz = tlrForm.getString("cxgz").equals("") ? "0" : "1";
//		String type = sbgz + "," + lsdyjgz + "," + ptdyjgz + "," + ccqgz + ","
//				+ qkmkgz + "," + dkqgz + "," + xlgz + "," + cxgz;
//
//		String attr = "atmstatus";
//		if (style == null || style.equals("") || style.equals("1"))
//			attr += "1";
//		else
//			attr += "2";
//
//		if (emlevel != 111)
//			atm = String.valueOf(emlevel);
//
//		List<Atm> atmList = DataManager.getAtmListByStatus(atm, style, type);
//
//		File file = MyFile.getFile(MyFile.getXlsFolder() + "ATMztb"
//				+ MyTime.getTime("yyyyMMddHHmmss") + ".xls");
//		WriteExcelCommon.writeAtmStateInfoExcel(file, "ATM状态表", atmList);
//
//		String contentType = "application/msexcel"; // 接收类型为下载
//		response.setContentType(contentType); // 设置接收类型
//		response.setHeader("Content-Disposition", "attachment;filename="
//				+ new String(file.getName().getBytes(), "UTF-8"));
//		return new FileStreamInfo(contentType, file);
//	}
//
//}

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

public class DownAddMoneyAction extends
		org.apache.struts.actions.DownloadAction {

	@Override
	protected StreamInfo getStreamInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm tlrForm = (DynaActionForm) form;
		String reportType = tlrForm.getString("reportType");
		String type = tlrForm.getString("type");
		String timebegin = tlrForm.getString("timebegin");
		String timeend = tlrForm.getString("timeend");
		String[] atmids = tlrForm.getStrings("atmids");
		String qcash = tlrForm.getString("qcash");
		String ccash = tlrForm.getString("ccash");

		List<Atm> list = null;
		File file = null;
		if (reportType.equals("stock")) {
			list = DataManager.getStockMoney(atmids, type, qcash, ccash);
			file = MyFile.getFile(MyFile.getXlsFolder() + "kcfx"
					+ MyTime.getTime("yyyyMMddHHmmss") + ".xls");
			WriteExcelCommon.writeStockExcel(file, "������", list);
		} else if (reportType.equals("historymoney")) {

			// file = we.getAddMoneyFile("history.xls", "��ʷ����", list,
			// sessionid);
		} else if (reportType.equals("addmoney")) {
			list = DataManager
					.getDesMoneyList(atmids, timebegin, timeend, type);
			file = MyFile.getFile(MyFile.getXlsFolder() + "jyjc"
					+ MyTime.getTime("yyyyMMddHHmmss")+ ".xls");
			WriteExcelCommon.writeAddMoneyExcel(file, "����ӳ�", list);
		}

		String contentType = "application/msexcel"; // ��������Ϊ����
		response.setContentType(contentType); // ���ý�������
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(file.getName().getBytes(), "UTF-8"));

		return new FileStreamInfo(contentType, file);
	}
}

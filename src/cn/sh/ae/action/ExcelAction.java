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
import cn.sh.ae.tools.WriteExcelCommon;
import cn.sh.ae.util.MyFile;
import cn.sh.ae.util.MyTime;
import cn.sh.ae.vo.AtmTradataMoney;
import cn.sh.ae.vo.Atmtradata;
import cn.sh.ae.vo.Draw;
import cn.sh.ae.vo.User;

public class ExcelAction extends org.apache.struts.actions.DownloadAction {

	@Override
	protected StreamInfo getStreamInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int level = ((User) request.getSession().getAttribute("login"))
				.getTitle();
		DynaActionForm tlrForm = (DynaActionForm) form;
		String reportType = tlrForm.getString("reportType");
		String equipType = tlrForm.getString("equipType");
		String transType = tlrForm.getString("transType");
		String timebegin = tlrForm.getString("timebegin");
		String timeend = tlrForm.getString("timeend");
		String[] atmids = (String[]) tlrForm.get("atmids");
		// String type = tlrForm.getString("type");
		// String route = tlrForm.getString("route");

		String tradeTypeFlag = reportType;
		List<Draw> drawList = null;

		if (reportType.equals("1") || reportType.equals("2"))
			tradeTypeFlag = "1";
		else if (reportType.equals("3") || reportType.equals("4"))
			tradeTypeFlag = "2";
		else if (reportType.equals("5"))
			tradeTypeFlag = "3";

		if (!reportType.equals("6"))
			drawList = DataManager.getDrawInfoList(atmids, tradeTypeFlag,
					transType, equipType, timebegin, timeend);

		File file = null;
		if (tradeTypeFlag.equals("1")) {
			file = MyFile.getFile(MyFile.getXlsFolder() + "qujy"
					+ MyTime.getTime("yyyyMMddHHmmss") + ".xls");
			WriteExcelCommon.writeExcel(file, timebegin + "~" + timeend + " "
					+ "取款交易", drawList);
		} else if (tradeTypeFlag.equals("2")) {
			file = MyFile.getFile(MyFile.getXlsFolder() + "ckjy"
					+ MyTime.getTime("yyyyMMddHHmmss") + ".xls");
			WriteExcelCommon.writeExcel(file, timebegin + "~" + timeend + " "
					+ "存款交易", drawList);
		} else if (tradeTypeFlag.equals("3")) {
			file = MyFile.getFile(MyFile.getXlsFolder() + "sy"
					+ MyTime.getTime("yyyyMMddHHmmss") + ".xls");
			WriteExcelCommon.writeProExcel(file, timebegin + "~" + timeend
					+ " " + "收益", drawList);
		} else if (reportType.equals("6")) {
			List<String[]> strsList = new ArrayList<String[]>();
			String[] strs = null;
			// 全部交易
			List<Atmtradata> allTradeList1 = DataManager.getAllTrade(-1,
					timebegin, timeend);
			// 全部存款
			List<Atmtradata> allTradeList2 = DataManager.getAllTrade(2,
					timebegin, timeend);
			// 全部取款
			List<Atmtradata> allTradeList3 = DataManager.getAllTrade(1,
					timebegin, timeend);
			// 全部转账
			List<Atmtradata> allTradeList4 = DataManager.getAllTrade(3,
					timebegin, timeend);
			// 本代他
			List<Atmtradata> allTradeList5 = DataManager.getAllTrade(11,
					timebegin, timeend);
			// 收益
			List<AtmTradataMoney> allTradeList6 = DataManager.getAllTradeMoney(timebegin, timeend);
			for (Atmtradata list1 : allTradeList1) {
				strs=new String[13];
				strs[0] = list1.getAtmid();
				strs[1] = list1.getReserve();
				strs[2] = String.valueOf(list1.getTradecount());
				strs[3] = String.valueOf(list1.getTradermb());
				for (Atmtradata list2 : allTradeList2) {
					if (list1.getAtmid().equals(list2.getAtmid())) {
						strs[4] = String.valueOf(list2.getTradecount());
						strs[5] = String.valueOf(list2.getTradermb());
						break;
					}
				}
				for (Atmtradata list3 : allTradeList3) {
					if (list1.getAtmid().equals(list3.getAtmid())) {
						strs[6] = String.valueOf(list3.getTradecount());
						strs[7] = String.valueOf(list3.getTradermb());
						break;
					}
				}
				for (Atmtradata list4 : allTradeList4) {
					if (list1.getAtmid().equals(list4.getAtmid())) {
						strs[8] = String.valueOf(list4.getTradecount());
						strs[9] = String.valueOf(list4.getTradermb());
						break;
					}
				}
				for (Atmtradata list5 : allTradeList5) {
					if (list1.getAtmid().equals(list5.getAtmid())) {
						strs[10] = String.valueOf(list5.getTradecount());
						strs[11] = String.valueOf(list5.getTradermb());
						break;
					}
				}
				for (AtmTradataMoney list6 : allTradeList6) {
					if (list1.getAtmid().equals(list6.getAtmid())) {
						strs[12] = String.valueOf(list6.getMoney());
						break;
					}
				}
				strsList.add(strs);
			}
			for(String[] s:strsList)
				System.out.println(s[0]);
			file = MyFile.getFile(MyFile.getXlsFolder() + "jylzb"
					+ MyTime.getTime("yyyyMMddHHmmss") + ".xls");
			WriteExcelCommon.writeAllTradeExcel(file, timebegin + "~" + timeend
					+ " " + "交易量总表", strsList);
		}

		String contentType = "application/msexcel"; // 接收类型为下载
		response.setContentType(contentType); // 设置接收类型
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(file.getName().getBytes(), "UTF-8"));
		return new FileStreamInfo(contentType, file);

	}
}

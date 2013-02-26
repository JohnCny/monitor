package cn.sh.ae.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import cn.sh.ae.manage.DataManager;
import cn.sh.ae.vo.Atm;
import cn.sh.ae.vo.Atmhistory;

/**
 * 统计分析
 */
public class Stati2Action extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获取表单数据
		DynaActionForm tlrForm = (DynaActionForm) form;
		String reportType = tlrForm.getString("reportType");
		String equipType = tlrForm.getString("equipType");
		String[] atmids = (String[]) tlrForm.get("atmids");
		String timebegin = tlrForm.getString("timebegin");
		String timeend = tlrForm.getString("timeend");
		String route = tlrForm.getString("route");
		String errType = tlrForm.getString("errtype");
		String errTime = tlrForm.getString("errtime");

		// 交易明细列表
		List<Atm> kasList = null;
		List<Atmhistory> kasHisList = null;

		List<Atm> showList = null;
		Atm atm = null;

		JSONArray json = null;

		if (reportType.equals("1")) {
			kasHisList = DataManager.getAtmBugList(equipType, atmids,
					timebegin, timeend, errType, errTime);
			// request.setAttribute("atmhistory_bug", kasHisList);
			json = JSONArray.fromObject(kasHisList);
		} else if (reportType.equals("2")) {
			kasList = DataManager.getAtmInfoList(equipType, route, atmids);
			if (kasList != null && kasList.size() != 0 && !kasList.isEmpty()) {

				int size = kasList.size();

				String category[] = new String[size];
				int bookSales[] = new int[size];

				showList = new ArrayList<Atm>();
				for (int i = 0; i < size; i++) {
					atm = new Atm();
					String allSataus = kasList.get(i).getAllstatus();
					bookSales[i] = Integer.parseInt(allSataus);

					String atmId = "";
					if (reportType.equals("2")) {
						atmId = kasList.get(i).getCompany();

						category[i] = atmId;
						atm.setAllstatus(allSataus);
						atm.setAtmid(atmId);
						showList.add(atm);
						// request.setAttribute("categoryData", showList);
						json = JSONArray.fromObject(showList);
					}
				}
			}
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

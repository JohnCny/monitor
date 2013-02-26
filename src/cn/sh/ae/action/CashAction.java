package cn.sh.ae.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

import ChartDirector.Chart;
import ChartDirector.LineLayer;
import ChartDirector.XYChart;
import cn.sh.ae.manage.DataManager;
import cn.sh.ae.util.MyTime;
import cn.sh.ae.vo.Atm;
import cn.sh.ae.vo.Draw;
import cn.sh.ae.vo.User;

public class CashAction extends DispatchAction {

	public ActionForward stock(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm tlrForm = (DynaActionForm) form;
		String type = tlrForm.getString("type");
		String qcash = tlrForm.getString("qcash");
		String ccash = tlrForm.getString("ccash");
		String[] atmids = tlrForm.getStrings("atmids");
		List<Atm> list=DataManager.getStockMoney(atmids, type,
				qcash, ccash);

		int size=list.size();
		double[] data1=new double[size];
		double[] data2=new double[size];
		String[] labels = new String[size];
		for(int i=0;i<size;i++)
		{
			String p=list.get(i).getPartrmb();
			String d=list.get(i).getDemstatus();
			data1[size-i-1]=Double.valueOf(p==null?"0":p.equals("")?"0":p);
			data2[size-i-1]=Double.valueOf(d==null?"0":d.equals("")?"0":d);
			labels[size-i-1]=list.get(i).getAtmid();
		}
		
		/** ******** */
		request.setCharacterEncoding("UTF-8");
		
		XYChart c = new XYChart(230, size*15);

		c.setPlotArea(60, 20, 150, (size*15)-30).setGridColor(0xc0c0c0, 0xc0c0c0);

		// c.addLegend(270, 75);

		c.swapXY();

		c.setYAxisOnRight();

		// c.yAxis().setTitle("Throughput (MBytes)");

		// c.xAxis().setReverse();
		c.xAxis().setLabels(labels);

		LineLayer layer = c.addLineLayer2();
		layer.addDataSet(data1, 0x0000c0, "È¡¿î").setDataSymbol(
				Chart.CircleSymbol, 9, 0xffff00);
		layer.addDataSet(data2, 0x982810, "´æ¿î").setDataSymbol(
				Chart.DiamondSymbol, 9, 0xf040f0);

		layer.setLineWidth(2);

		String chart1URL = c.makeSession(request, "chart1");
		Atm atm=new Atm();
		atm.setAtmid(chart1URL);
		list.add(0, atm);
		/** ******** */
		
		JSONArray json = JSONArray.fromObject(list);
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
// return mapping.findForward("success");
		return null;
	}

	public ActionForward addmoney(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm tlrForm = (DynaActionForm) form;
		String type = tlrForm.getString("type");
		String timebegin = tlrForm.getString("timebegin");
		String timeend = tlrForm.getString("timeend");
		String[] atmids = tlrForm.getStrings("atmids");
		List<Atm> list = DataManager.getDesMoneyList(atmids, timebegin,
				timeend, type);
		int size=list.size();
		double[] data=new double[size];
		String[] labels = new String[size];
		for(int i=0;i<size;i++)
		{
			data[size-i-1]=Double.valueOf(list.get(i).getPartrmb());
			labels[size-i-1]=list.get(i).getAtmid();
		}
		
		/** ******** */
		request.setCharacterEncoding("UTF-8");
		
		XYChart c = new XYChart(230, size*15);

		c.setPlotArea(60, 20, 150, (size*15)-30).setGridColor(0xc0c0c0, 0xc0c0c0);

		// c.addLegend(270, 75);

		c.swapXY();

		c.setYAxisOnRight();

		// c.yAxis().setTitle("Throughput (MBytes)");

		// c.xAxis().setReverse();
		c.xAxis().setLabels(labels);

		LineLayer layer = c.addLineLayer2();
		layer.addDataSet(data, 0x0000c0, "²î¶î").setDataSymbol(
				Chart.CircleSymbol, 9, 0xffff00);

		layer.setLineWidth(2);

		String chart1URL = c.makeSession(request, "chart1");
		Atm atm=new Atm();
		atm.setAtmid(chart1URL);
		list.add(0, atm);
		/** ******** */
		
		JSONArray json = JSONArray.fromObject(list);
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
// return mapping.findForward("success");
		return null;
		
		
//		request.setAttribute("atmid", DataManager.getAtmList());
//		request.setAttribute("typelist", DataManager.getAtmServerType(String
//				.valueOf(emlevel)));
//		request.setAttribute("lookmoney", list);
//		return mapping.findForward("success");
	}

	public ActionForward historymoney(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int emlevel = ((User) request.getSession().getAttribute("login"))
				.getTitle();
		DynaActionForm tlrForm = (DynaActionForm) form;
		String type = tlrForm.getString("type");
		String timebegin = tlrForm.getString("timebegin");
		String timeend = tlrForm.getString("timeend");
		String[] atmids = tlrForm.getStrings("atmids");
		List<List<Draw>> list = DataManager.getHisInfoList(atmids, type,
				timebegin, timeend);

		request.setAttribute("typelist", DataManager.getAtmServerType(emlevel));

		request.setAttribute("historyc", list.get(0));
		request.setAttribute("historyq", list.get(1));
		return mapping.findForward("success");
	}

	public ActionForward onedaycheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm tlrForm = (DynaActionForm) form;
		String type = tlrForm.getString("type");
		String timebegin = tlrForm.getString("timebegin");
		String[] atmids = tlrForm.getStrings("atmids");

		List<Atm> oldList = DataManager.getDayCheck(atmids, MyTime
				.getFormatDate(timebegin, "yyyy-MM-dd", "yyyyMMdd"), type);
		List<Atm> todayList = DataManager.getStockMoney(atmids, type, null,
				null);

		List<Atm> atmList = null;
		Atm atm = null;

		if (todayList != null) {
			atmList = new ArrayList<Atm>();
			for (Atm atm_today : todayList) {
				String atmid_today = atm_today.getAtmid();
				for (Atm atm_old : oldList) {
					String atmid_old = atm_old.getAtmid();
					if (atmid_today.trim().equals(atmid_old.trim())) {
						atm = new Atm();
						atm.setAtmid(atmid_today);
						atm.setPartrmb(atm_today.getPartrmb());
						atm.setDepormb(atm_old.getPartrmb());
						int rmb = Integer.parseInt(atm_old.getPartrmb())
								- Integer.parseInt(atm_today.getPartrmb());
						atm.setAllstatus(String.valueOf(rmb));
						atm.setType(atm_today.getType());
						atm.setAddr(atm_today.getAddr());
						atmList.add(atm);
					}
				}
			}
		}

		Collections.sort(atmList, new Atm());

		request.setAttribute("moneydaycheck", atmList);

//		request.setAttribute("typelist", DataManager.getAtmServerType(String
//				.valueOf(emlevel)));
		int size=atmList.size();
		double[] data=new double[size];
		String[] labels = new String[size];
		for(int i=0;i<size;i++)
		{
			data[size-i-1]=Double.valueOf(atmList.get(i).getAllstatus());
			labels[size-i-1]=atmList.get(i).getAtmid();
		}
		
		/** ******** */
		request.setCharacterEncoding("UTF-8");
		
		XYChart c = new XYChart(230, size*15);

		c.setPlotArea(60, 20, 150, (size*15)-30).setGridColor(0xc0c0c0, 0xc0c0c0);

		// c.addLegend(270, 75);

		c.swapXY();

		c.setYAxisOnRight();

		// c.yAxis().setTitle("Throughput (MBytes)");

		// c.xAxis().setReverse();
		c.xAxis().setLabels(labels);

		LineLayer layer = c.addLineLayer2();
		layer.addDataSet(data, 0x0000c0, "²î¶î").setDataSymbol(
				Chart.CircleSymbol, 9, 0xffff00);

		layer.setLineWidth(2);

		String chart1URL = c.makeSession(request, "chart1");
		atm=new Atm();
		atm.setAtmid(chart1URL);
		atmList.add(0, atm);
		/** ******** */
		
		JSONArray json = JSONArray.fromObject(atmList);
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
//		return mapping.findForward("success");
	}
}

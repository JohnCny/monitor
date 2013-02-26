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

import ChartDirector.Chart;
import ChartDirector.LineLayer;
import ChartDirector.XYChart;
import cn.sh.ae.manage.DataManager;
import cn.sh.ae.vo.Draw;

/**
 * ͳ�Ʒ���
 */
public class StatiAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ��ȡ������
		DynaActionForm tlrForm = (DynaActionForm) form;
		String reportType = tlrForm.getString("reportType");
		// ��Ӫ����
		String equipType = tlrForm.getString("equipType");
		// ��������
		String transType = tlrForm.getString("transType");
		String[] atmids = (String[]) tlrForm.get("atmids");
		String timebegin = tlrForm.getString("timebegin");
		String timeend = tlrForm.getString("timeend");

		// ������ϸ�б�
		List<Draw> drawList = null;
		List<Draw> showList = null;
		Draw draw = null;
		// ͼ��·��

		String tradeTypeFlag = null;

		if (reportType.equals("1") || reportType.equals("2"))
			tradeTypeFlag = "1";
		else if (reportType.equals("3") || reportType.equals("4"))
			tradeTypeFlag = "2";
		else if (reportType.equals("5"))
			tradeTypeFlag = "3";

		drawList = DataManager.getDrawInfoList(atmids, tradeTypeFlag,
				transType, equipType, timebegin, timeend);

		if (drawList != null && drawList.size() != 0 && !drawList.isEmpty()) {
			int size = drawList.size();

			boolean flag = atmids.length == 1 ? true : false;

			double[] data=new double[size];
			String[] labels = new String[size];
			showList = new ArrayList<Draw>();
			int sum = 0;
			for (int i = 0; i < size; i++) {
				if (reportType.equals("1") || reportType.equals("3")) {
					draw = new Draw();
//					bookTitle[0] = "������(Ԫ)";
					sum = drawList.get(i).getSum();
				} else if (reportType.equals("2") || reportType.equals("4")) {
					draw = new Draw();
//					bookTitle[0] = "���ױ���(��)";
					sum = drawList.get(i).getCount();
				} else if (reportType.equals("5")) {
					draw = new Draw();
//					bookTitle[0] = "����(Ԫ)";
					sum = drawList.get(i).getCount() * 3;
				}
				data[size-i-1] = sum;
				draw.setSum(sum);

				// else if (reportType.equals("5")) {
				// bookTitle[0]="����";
				// bookSales[i] = drawList.get(i).getCount() * 3;
				// }
				String atmId = null;
				if (flag) {
					atmId = drawList.get(i).getTradetime();
				} else {
					atmId = drawList.get(i).getAtmid();
				}

				labels[size-i-1] = atmId;
				draw.setAtmid(atmId);
				draw.setType(drawList.get(i).getType());
				draw.setAddr(drawList.get(i).getAddr());
				showList.add(draw);
			}

			/***********/
			request.setCharacterEncoding("UTF-8");
			
			XYChart c = new XYChart(230, size*15);

			c.setPlotArea(60, 20, 150, (size*15)-30).setGridColor(0xc0c0c0, 0xc0c0c0);

			//c.addLegend(270, 75);

			c.swapXY();

			c.setYAxisOnRight();

			//c.yAxis().setTitle("Throughput (MBytes)");

			//c.xAxis().setReverse();
			c.xAxis().setLabels(labels);

			LineLayer layer = c.addLineLayer2();
			layer.addDataSet(data, 0x0000c0, "ȡ��").setDataSymbol(
					Chart.CircleSymbol, 9, 0xffff00);
			

			layer.setLineWidth(2);

			String chart1URL = c.makeSession(request, "chart1");
			draw=new Draw();
			draw.setAtmid(chart1URL);
			showList.add(0, draw);
			/***********/
			
//			request.setAttribute("categoryImg", getDatasetProducer(bookTitle,
//					category, bookSales));
			JSONArray json = JSONArray.fromObject(showList);
			try {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.print(json);
			} catch (IOException e) {
				e.printStackTrace();
			}

//			request.setAttribute("categoryData", showList);
//			return mapping.findForward("success");
			return null;
		} else
			return mapping.findForward("fault");
	}
}

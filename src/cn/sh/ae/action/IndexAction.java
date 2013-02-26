package cn.sh.ae.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import cn.sh.ae.bank.nc.state.AtmState;
import cn.sh.ae.bank.nc.state.TradeState;
import cn.sh.ae.pojo.AtmStatePojo;
import cn.sh.ae.pojo.TradeStatePojo;
import cn.sh.ae.vo.User;

public class IndexAction extends DispatchAction {

	public ActionForward getAtmStateStatistics(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int level = ((User) request.getSession().getAttribute("login"))
				.getTitle();
		request.setAttribute("atmstatecategoryImgData", AtmState
				.getAtmStatePieData(level));
		AtmStatePojo asp = AtmState.getAllState(level);
		request.setAttribute("atmstatecategoryInfoPer", asp.getStatePre());
		request.setAttribute("atmstatecategoryInfo", asp);
		return mapping.findForward("atmstatecategoryImgsuccess");
	}

	public ActionForward getTradeStateStatistics(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaActionForm tlrForm = (DynaActionForm) form;
		String atmId = tlrForm.getString("atmid");
		String type = tlrForm.getString("type");
		request.setAttribute("tradestatecategoryImgData", TradeState
				.getTradeStatelinDatas(atmId, type));
		return mapping.findForward("tradestatecategoryImgsuccess");
	}

	public ActionForward getTradeInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("tradestatecategoryData", TradeState
				.getTradeInfo());
		return mapping.findForward("tradestatecategorysuccess");
	}

	public ActionForward getIndexInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int level = ((User) request.getSession().getAttribute("login"))
				.getTitle();
		double[] atmState = AtmState.getAtmStatePieData(level);
		request.setAttribute("atmstatecategoryImgData", atmState);
		AtmStatePojo atmStatePojo = new AtmStatePojo();
		atmStatePojo.setYj(String.valueOf(atmState[0]));
		atmStatePojo.setZc(String.valueOf(atmState[1]));
		atmStatePojo.setWh(String.valueOf(atmState[2]));
		atmStatePojo.setGz(String.valueOf(atmState[3]));
		double per = ((double) (atmState[1] + atmState[0]) / (atmState[0]
				+ atmState[1] + atmState[2] + atmState[3])) * 100;

		// AtmStatePojo asp = AtmState.getAllState();
		request.setAttribute("atmstatecategoryInfoPer", per);
		request.setAttribute("atmstatecategoryInfo", atmStatePojo);

		DynaActionForm tlrForm = (DynaActionForm) form;
		String atmId = tlrForm.getString("atmid");
		String type = tlrForm.getString("type");
		request.setAttribute("atmId", atmId);
		request.setAttribute("type", type);
		request.setAttribute("tradestatecategoryImgData", TradeState
				.getTradeStatelinDatas(atmId, type));

		List<TradeStatePojo> tsp = TradeState.getTradeInfo();
		request.setAttribute("tradestatecategoryImgDataQk", TradeState.qkzz);
		request.setAttribute("tradestatecategoryImgDataCk", TradeState.ckzz);
		request.setAttribute("tradestatecategoryData", tsp);
		
		return mapping.findForward("indexinfosuccess");
	}

}

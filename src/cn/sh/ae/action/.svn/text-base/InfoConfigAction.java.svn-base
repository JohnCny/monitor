package cn.sh.ae.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

import cn.sh.ae.manage.DataManager;
import cn.sh.ae.vo.Atminfo;

public class InfoConfigAction extends DispatchAction {

	public ActionForward allInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("atmcompanylist", DataManager.getAtmCompanyList());
		request.setAttribute("atmtypelist", DataManager.getAtmTypeList());
		request.setAttribute("runtypelist", DataManager.getBankTypeList());
		return mapping.findForward("success");
	}

	public ActionForward getCompanyById(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Atminfo atmcompany = new Atminfo();
		atmcompany.setId(request.getParameter("id"));
		List<Atminfo> list = new ArrayList<Atminfo>();
		list.add(DataManager.getAtmCompany(atmcompany));

		JSONArray json = JSONArray.fromObject(list);
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		// return mapping.findForward("cmsuccess");
	}

	public ActionForward setCompany(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm tlrForm = (DynaActionForm) form;
		Atminfo atmcompany = new Atminfo();
		atmcompany.setId(tlrForm.getString("id"));
		atmcompany.setChname(tlrForm.getString("chname"));
		atmcompany.setEnname(tlrForm.getString("enname"));
		Atminfo atmInfo = DataManager.getAtmCompany(atmcompany);
		if (atmInfo != null)
			request.setAttribute("setcompany_err", atmInfo);
		else {
			DataManager.setAtmCompany(atmcompany);
			request.setAttribute("atmcompanylist", DataManager
					.getAtmCompanyList());
		}
		return mapping.findForward("csuccess");
	}

	public ActionForward modifyCompany(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm tlrForm = (DynaActionForm) form;
		Atminfo atmcompany = new Atminfo();
		atmcompany.setId(tlrForm.getString("id"));
		atmcompany.setChname(tlrForm.getString("chname"));
		atmcompany.setEnname(tlrForm.getString("enname"));
		DataManager.modifyAtmCompany(atmcompany);
		request.setAttribute("atmcompanylist", DataManager.getAtmCompanyList());
		return mapping.findForward("csuccess");
	}

	public ActionForward deleteCompany(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Atminfo atmcompany = new Atminfo();
		atmcompany.setId(request.getParameter("id"));
		DataManager.deleteAtmCompany(atmcompany);
		request.setAttribute("atmcompanylist", DataManager.getAtmCompanyList());
		return mapping.findForward("csuccess");
	}

	/** ********************************************************************* */

	public ActionForward typeAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("atmtypelist", DataManager.getAtmTypeList());
		return mapping.findForward("tsuccess");
	}

	public ActionForward getTypeById(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Atminfo atmtype = new Atminfo();
		atmtype.setId(request.getParameter("id"));
		// request.setAttribute("atmtype", DataManager.getAtmType(atmtype));
		List<Atminfo> list = new ArrayList<Atminfo>();
		list.add(DataManager.getAtmType(atmtype));

		JSONArray json = JSONArray.fromObject(list);
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		// return mapping.findForward("tmsuccess");
	}

	public ActionForward setType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm tlrForm = (DynaActionForm) form;
		Atminfo atmtype = new Atminfo();
		atmtype.setId(tlrForm.getString("id"));
		atmtype.setChname(tlrForm.getString("chname"));
		atmtype.setEnname(tlrForm.getString("enname"));
		Atminfo atmInfo = DataManager.getAtmType(atmtype);
		if (atmInfo != null)
			request.setAttribute("setcompany_err", atmInfo);
		else {
			DataManager.setAtmType(atmtype);
			request.setAttribute("atmtypelist", DataManager.getAtmTypeList());
		}
		return mapping.findForward("csuccess");
	}

	public ActionForward modifyType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm tlrForm = (DynaActionForm) form;
		Atminfo atmtype = new Atminfo();
		atmtype.setId(tlrForm.getString("id"));
		atmtype.setChname(tlrForm.getString("chname"));
		atmtype.setEnname(tlrForm.getString("enname"));
		DataManager.modifyAtmType(atmtype);
		request.setAttribute("atmtypelist", DataManager.getAtmTypeList());
		return mapping.findForward("csuccess");
	}

	public ActionForward deleteType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Atminfo atmtype = new Atminfo();
		atmtype.setId(request.getParameter("id"));
		DataManager.deleteAtmType(atmtype);
		request.setAttribute("atmtypelist", DataManager.getAtmTypeList());
		return mapping.findForward("csuccess");
	}

	/** ********************************************************************* */

	public ActionForward runAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("runtypelist", DataManager.getBankTypeList());
		return mapping.findForward("csuccess");
	}

	public ActionForward getRunById(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Atminfo atmrun = new Atminfo();
		atmrun.setId(request.getParameter("id"));
		List<Atminfo> list = new ArrayList<Atminfo>();
		list.add(DataManager.getBankType(atmrun));

		JSONArray json = JSONArray.fromObject(list);
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		// request.setAttribute("atmrun", DataManager.getRunType(atmrun));
		// return mapping.findForward("rmsuccess");
	}

	public ActionForward setRun(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm tlrForm = (DynaActionForm) form;
		Atminfo runtype = new Atminfo();
		runtype.setId(tlrForm.getString("id"));
		runtype.setChname(tlrForm.getString("chname"));
		runtype.setEnname(tlrForm.getString("enname"));
		Atminfo atmInfo = DataManager.getBankType(runtype);
		if (atmInfo != null)
			request.setAttribute("setcompany_err", atmInfo);
		else {
			DataManager.setBankType(runtype);
			request.setAttribute("runtypelist", DataManager.getBankTypeList());
		}
		return mapping.findForward("csuccess");
	}

	public ActionForward modifyRun(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm tlrForm = (DynaActionForm) form;
		Atminfo atmrun = new Atminfo();
		atmrun.setId(tlrForm.getString("id"));
		atmrun.setChname(tlrForm.getString("chname"));
		atmrun.setEnname(tlrForm.getString("enname"));
		DataManager.modifyBankType(atmrun);
		request.setAttribute("runtypelist", DataManager.getBankTypeList());
		return mapping.findForward("csuccess");
	}

	public ActionForward deleteRun(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Atminfo runtype = new Atminfo();
		runtype.setId(request.getParameter("id"));
		DataManager.deleteBankType(runtype);
		request.setAttribute("runtypelist", DataManager.getBankTypeList());
		return mapping.findForward("csuccess");
	}

}

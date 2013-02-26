package cn.sh.ae.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

import cn.sh.ae.manage.DataManager;
import cn.sh.ae.util.MyConstant;
import cn.sh.ae.vo.Atminfo;
import cn.sh.ae.vo.User;

public class UserAction extends DispatchAction {

	static Logger logger = Logger.getLogger(UserAction.class.getName());

	/** 用户权限控制 */
	public ActionForward level(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String type = request.getParameter("type");
		User user = (User) request.getSession().getAttribute("login");
		if (user != null) {
			if (type.equals("um") && user.getUmlevel() == 1)
				return mapping.findForward("umsuccess");
			else if (type.equals("em") && user.getEmlevel() != 222)
				return mapping.findForward("emsuccess");
			else if (type.equals("cm") && user.getCmlevel() == 1)
				return mapping.findForward("cmsuccess");
			else if (type.equals("sa") && user.getSalevel() == 1)
				return mapping.findForward("sasuccess");
			else if (type.equals("ct") && user.getCtlevel() == 1)
				return mapping.findForward("ctsuccess");
			else if (type.equals("rm") && user.getRmlevel() == 1)
				return mapping.findForward("rmsuccess");
			else if (type.equals("sc") && user.getSclevel() == 1)
				return mapping.findForward("scsuccess");
			else
				return mapping.findForward("nopower");
		} else
			return mapping.findForward("nopower");
	}

	/** 用户列表 */
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("userlist", DataManager.getUserList());
		return mapping.findForward("success");

	}

	/** 修改用户 */
	public ActionForward modify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DataManager.modifyUser(getUser((DynaActionForm) form));
		return mapping.findForward("setsuccess");
	}

	/** 注册用户 */
	public ActionForward set(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm tlrForm = (DynaActionForm) form;
		String id = tlrForm.getString(MyConstant.Table.User.USERNAME);
		User user = DataManager.getUserById(id);
		if (user != null) {
			request.setAttribute("setuser_err", user);
		} else
			DataManager.setUser(getUser((DynaActionForm) form));
		return mapping.findForward("setsuccess");
	}

	public ActionForward getByUserName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		List<String> list = new ArrayList<String>();
		List<Atminfo> info = DataManager.getBankTypeList();
		User user = new User();
		if (!id.equals("set")) {
			user = DataManager.getUserById(request.getParameter("id"));
		}
		list.add(user.getUsername());
		list.add(String.valueOf(user.getTitle()));
		list.add(user.getPassword());
		list.add(String.valueOf(user.getEmlevel1()));
		list.add(String.valueOf(info.size()));
		for (Atminfo i : info)
			list.add(i.getId() + "," + i.getChname());
		list.add(user.getPhone());
		list.add(user.getEmail());
		list.add(String.valueOf(user.getSalevel()));
		list.add(String.valueOf(user.getRmlevel()));
		list.add(String.valueOf(user.getCtlevel()));
		list.add(String.valueOf(user.getUmlevel()));
		list.add(String.valueOf(user.getSclevel()));
		list.add(String.valueOf(user.getStatus()));

		JSONArray json = JSONArray.fromObject(list);
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		// return mapping.findForward("byusernamesuccess");
	}

	/** 用户登入 */
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm fileForm = (DynaActionForm) form;
		String userName = fileForm.getString("username");
		String password = fileForm.getString("password");
		User user = DataManager.login(userName, password);
		if (user != null && user.getStatus() == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("login", user);
			logger.info("login success");
			return mapping.findForward("loginsuccess");
		} else {
			logger.info("login error");
			request.setAttribute("loginerrorinfo", "您输入的用户名、密码有误");
			return mapping.findForward("loginerror");
		}
	}

	public ActionForward deleteUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userName = request.getParameter("id");
		User user = new User();
		user.setUsername(userName);
		DataManager.deleteUser(user);
		return mapping.findForward("setsuccess");
	}

	/** 安全退出 */
	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.getSession().setAttribute("login", null);
		return mapping.findForward("logoutsuccess");
	}

	private User getUser(DynaActionForm tlrForm) {
		User user = new User();
		user.setCreatetime(tlrForm.getString(MyConstant.Table.User.CREATETIME));
		user.setEmail(tlrForm.getString(MyConstant.Table.User.EMAIL));
		user.setName(tlrForm.getString(MyConstant.Table.User.NAME));
		user.setTitle(Integer.parseInt(tlrForm
				.getString(MyConstant.Table.User.TITLE)));
		user.setPassword(tlrForm.getString(MyConstant.Table.User.PASSWORD));
		user.setPhone(tlrForm.getString(MyConstant.Table.User.PHONE));
		user.setStatus(Integer.parseInt(tlrForm
				.getString(MyConstant.Table.User.STATUS)));
		user.setUsername(tlrForm.getString(MyConstant.Table.User.USERNAME));
		String umlevel = tlrForm.getString(MyConstant.Table.User.UMLEVEL);

		String emlevel1 = tlrForm.getString(MyConstant.Table.User.EMLEVEL1)
				.equals("") ? "2" : "1";
		String emlevel2 = tlrForm.getString(MyConstant.Table.User.EMLEVEL2)
				.equals("") ? "2" : "1";
		String emlevel3 = tlrForm.getString(MyConstant.Table.User.EMLEVEL3)
				.equals("") ? "2" : "1";
		// String emlevel4 = tlrForm.getString(MyConstant.Table.User.EMLEVEL4)
		// .equals("") ? "2" : "1";
		String emlevel = emlevel1 + emlevel2 + emlevel3;

		String cmlevel = tlrForm.getString(MyConstant.Table.User.CMLEVEL);
		String salevel = tlrForm.getString(MyConstant.Table.User.SALEVEL);
		String ctlevel = tlrForm.getString(MyConstant.Table.User.CTLEVEL);
		String rmlevel = tlrForm.getString(MyConstant.Table.User.RMLEVEL);
		String sclevel = tlrForm.getString(MyConstant.Table.User.SCLEVEL);
		user.setUmlevel(umlevel.equals("") ? 0 : 1);
		user.setEmlevel(Integer.parseInt(emlevel));
		user.setCmlevel(cmlevel.equals("") ? 0 : 1);
		user.setSalevel(salevel.equals("") ? 0 : 1);
		user.setCtlevel(ctlevel.equals("") ? 0 : 1);
		user.setRmlevel(rmlevel.equals("") ? 0 : 1);
		user.setSclevel(sclevel.equals("") ? 0 : 1);
		return user;
	}
}

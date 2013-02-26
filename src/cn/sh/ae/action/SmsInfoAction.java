package cn.sh.ae.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;
import org.apache.log4j.Logger;

import cn.sh.ae.manage.DataManager;
import cn.sh.ae.manage.WorkManager;
import cn.sh.ae.tools.DoWork;
import cn.sh.ae.tools.Message;
import cn.sh.ae.tools.MsTimerTask;
import cn.sh.ae.vo.SmsInfo;


public class SmsInfoAction extends DispatchAction {
	static Logger logger = Logger.getLogger(WorkManager.class.getName());
	
	public ActionForward getInfoAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("allsmsinfo", DataManager.getAllSmsinfo());
		return mapping.findForward("success");
	}

	public ActionForward getInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm tlrForm = (DynaActionForm) form;
		String moblie = tlrForm.getString("moblie");
		String solvetime = tlrForm.getString("solvetime");
		String name = tlrForm.getString("name");
		SmsInfo smsInfo = new SmsInfo();
		smsInfo.setSmsDate(solvetime);
		smsInfo.setSmsNo(moblie);
		smsInfo.setSmsName(name);
		request.setAttribute("allsmsinfo", DataManager.getSmsinfo(smsInfo));
		return mapping.findForward("success");
	}

	public ActionForward sendTestSms(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm tlrForm = (DynaActionForm) form;
		String testStr = "�Ѽ�����Ŷ���";
		if (!WorkManager.sendSmsFlag)
			testStr = "���Ŷ���û������";
		else {
			cn.sh.ae.tools.Host myHost=new cn.sh.ae.tools.Host();
			Message sendMessage;
			DoWork worker=new DoWork();
			myHost.setHostIP("32.235.32.226");
			myHost.setHostPort(10030);
			myHost.setTimeout(40000);
			myHost.setEncoding("GBK");
			String moblie = tlrForm.getString("moblie");
			String sendsms = tlrForm.getString("sendsms");
			sendMessage=new Message();
			String phoneNo = moblie;
			String phoneMsg = sendsms;
			sendMessage.setMsgBody_SNO("ATMJ");
			sendMessage.setMsgBody_PNO(phoneNo);
			sendMessage.setMsgBody_TEXT(phoneMsg);
			String message=worker.packMessage(sendMessage);
			String result=worker.doSend(myHost, message);
			logger.info("����ƽ̨���ͷ�����:"+result+message);
		}
		request.setAttribute("testsmssend", testStr);
		return mapping.findForward("testsmssend");
	}
//	public ActionForward sendTestSms(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		DynaActionForm tlrForm = (DynaActionForm) form;
//		String testStr = "�ڲ�����";
//		if (!WorkManager.sendSmsFlag)
//			testStr = "���Ŷ���û������";
//		else {
//			String moblie = tlrForm.getString("moblie");
//			String sendsms = tlrForm.getString("sendsms");
//			SmsInfo smsE = new SmsInfo();
//			smsE.setSmsMsg(sendsms);
//			smsE.setSmsNo(moblie);
//			smsE.setSmsName("��������ƽ̨����ƽ̨");
//			WorkManager.smsQueue.add(smsE);
//			testStr = "�Ѿ���ӵ����Ŷ���";
//		}
//		request.setAttribute("testsmssend", testStr);
//		return mapping.findForward("testsmssend");
//	}

	public ActionForward getSmsSendStatus(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		boolean b_1 = MsTimerTask.tSmsQueue.isAlive();
		boolean b_2 = MsTimerTask.tSmsSend.isAlive();
		boolean b_3 = WorkManager.sendSmsFlag;
		if (b_1 && b_2 && b_3)
			request.setAttribute("smssendstatus", "0");
		else
			request.setAttribute("smssendstatus", "1");
		return mapping.findForward("smsstatus");
	}

	public ActionForward startSms(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WorkManager.sendSmsFlag = false;
		Thread.sleep(10000);
		WorkManager.sendSmsFlag = true;
		MsTimerTask.startSendSms();
		return mapping.findForward("startsmssend");
	}

}

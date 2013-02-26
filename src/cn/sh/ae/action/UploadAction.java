package cn.sh.ae.action;

import java.io.File;
import java.net.Socket;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;

import cn.sh.ae.manage.ConnectManager;
import cn.sh.ae.manage.DataManager;
import cn.sh.ae.tools.AtmcClient;
import cn.sh.ae.vo.Atm;
import cn.sh.ae.vo.Back;

public class UploadAction extends Action {
	static Logger logger = Logger.getLogger(UploadAction.class.getName());

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String method = request.getParameter("method");
		Vector<Back> backList = new Vector<Back>();
		DynaActionForm fileForm = (DynaActionForm) form;
		FormFile myFile = (FormFile) fileForm.get("file");
		String[] atmids = (String[]) fileForm.get("atmids");
		String remPath = fileForm.getString("remPath");

		// String seeesionid = request.getSession().getId();
		int size = atmids.length;
		// boolean flag = MyFile.saveUploadFile(myFile.getFileName(), myFile
		// .getFileData());

		if (size > 0) {
			// MyThread myThread = new MyThread();
			// myThread.setThreadPool(size);
			for (int i = 0; i < size; i++) {
				new Thread(new Upload(atmids[i], myFile.getFileName(), myFile
						.getFileData(), method, remPath, backList)).start();
			}

			logger.info("等待所有回复");
			while (size > backList.size()) {
			}
			logger.info("等待回复结束");
			// myThread.shutdownExec();
		}
		request.setAttribute("backList", backList);
		return mapping.findForward("success");

	}

	class Upload implements Runnable {
		private Socket socket = null;
		private String atmid = null;
		// private String fileName = null;
		private String remotePath = null;
		private Vector<Back> backList = null;
		// private List<String> files = null;
		private byte[] fileData = null;

		public Upload(String atmId, String fileName, byte[] fileData,
				String sOrF, String remPath, Vector<Back> backList) {
			this.atmid = atmId;
			Atm atm = DataManager.getAtmAdderssById(atmId);
			this.socket = ConnectManager.getAtmClient(atm.getIp(), Integer
					.parseInt(atm.getPort()), Integer
					.parseInt(atm.getTimeout()));
			// this.fileName = fileName;
			if (sOrF != null && sOrF.equals("1"))
				this.remotePath = atm.getUpload() + fileName;
			else if (sOrF != null && sOrF.equals("2"))
				this.remotePath = atm.getPicture() + fileName;
			else if (sOrF != null && sOrF.equals("3"))
				this.remotePath = atm.getAesyspath() + fileName;
			else if (sOrF != null && sOrF.equals("4"))
				this.remotePath = remPath + File.separator + fileName;
			this.backList = backList;
			this.fileData = fileData;
			// this.files = new ArrayList<String>();
		}

		@Override
		public void run() {

			// 提取上传文件
			// String lPath = MyConstant.USERPATH + this.sessionid
			// + MyConstant.SESSIONTEMPUP + fileName;
			// files.add(lPath);
			// files.add(remotePath);
			// if (this.sOrF.equals("2") && company.equals("2")) {
			// files.add(MyConstant.SYSPATH + "RCCU_KS_ADV.reg");
			// files.add("D://RCCU_KS_ADV.reg");
			// }
			// 告知对方将有文件上传，准备
			AtmcClient.setResponse(socket, 2);
			// 接受对方响应
			int i = AtmcClient.getRequest(socket);
			// 如果响应1，即正确响应则发送文件
			logger.info("得到响应值：" + i);
			if (i == 1) {
				logger.info("发送文件");
				// 发送文件
				AtmcClient.sendFile(socket, remotePath, fileData);
				// 等待对方告知接受文件情况，并判断是否要关闭通话连接
				i = AtmcClient.getRequest(socket);
				// if (i == 1) {
				// if (this.sOrF.equals("2") && company.equals("2")) {
				// AtmcClient.setResponse(socket, 3);
				// if (AtmcClient.getRequest(socket) == 1) {
				// AtmcClient
				// .sendMessage(socket,
				// "cmd /c \"c://windows//regedit /s D://RCCU_KS_ADV.reg\"");
				// i = AtmcClient.getRequest(socket);
				// }
				// }
				// AtmcClient.setResponse(socket, 0);
				// }
			}
			AtmcClient.close(socket);
			logger.info("结束通信");
			Back back = new Back();
			back.setAtmid(atmid);
			back.setBackStatus(i);
			this.backList.add(back);
		}
	}
}

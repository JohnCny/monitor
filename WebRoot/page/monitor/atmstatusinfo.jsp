<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title></title>
		<link href="resources/source/tablecloth.css" rel="stylesheet"
			type="text/css" media="screen" />
	</head>

	<body>
		<logic:notEmpty name="atmstatusinfo">
			<table cellspacing="0" cellpadding="0">
				<caption>
					<font size="5"> 设备代码： <bean:write name="atmstatusinfo"
							property="atmid" /> </font>
				</caption>
				<tr>
					<th colspan="5">
						设备状态
					</th>
				</tr>


				<tr id="tr_info">
					<td colspan="2">
						流水打印：
					</td>
					<td>
						<logic:equal name="atmstatusinfo" property="prjstatus" value="4">
							<font color="#FF0000">异常</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="prjstatus" value="0">
							<font color="#00DB00">正常</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="prjstatus" value="1">
							<font color="#FF0000">故障</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="prjstatus" value="2">
							<font color="#FF8000">纸少</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="prjstatus" value="3">
							<font color="#FF8000">纸尽</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="prjstatus" value="5">
							<font color="#FF0000">未知</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="prjstatus" value="6">
							<font color="#FF8000">卡纸</font>
						</logic:equal>
					</td>

					<td>
						凭条打印：
					</td>
					<td>
						<logic:equal name="atmstatusinfo" property="prrstatus" value="4">
							<font color="#FF0000">异常</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="prrstatus" value="0">
							<font color="#00DB00">正常</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="prrstatus" value="1">
							<font color="#FF0000">故障</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="prrstatus" value="2">
							<font color="#FF8000">纸少</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="prrstatus" value="3">
							<font color="#FF8000">纸尽</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="prrstatus" value="5">
							<font color="#FF0000">未知</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="prrstatus" value="6">
							<font color="#FF8000">卡纸</font>
						</logic:equal>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						出钞模组：
					</td>
					<td>
						<logic:equal name="atmstatusinfo" property="cdmstatus" value="1">
							<font color="#FF0000">离线</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="cdmstatus" value="0">
							<font color="#00DB00">正常</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="cdmstatus" value="2">
							<font color="#FF0000">电源关闭</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="cdmstatus" value="3">
							<font color="#FF0000">不存在</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="cdmstatus" value="4">
							<font color="#FF0000">故障</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="cdmstatus" value="5">
							<font color="#FF0000">柜员状态</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="cdmstatus" value="6">
							<font color="#00DB00">设备忙</font>
						</logic:equal>
					</td>

					<td>
						存款模组：
					</td>
					<td>
						<logic:equal name="atmstatusinfo" property="depstatus" value="1">
							<font color="#FF0000">离线</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="depstatus" value="0">
							<font color="#00DB00">正常</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="depstatus" value="2">
							<font color="#FF0000">电源关闭</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="depstatus" value="3">
							<font color="#FF0000">不存在</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="depstatus" value="4">
							<font color="#FF0000">故障</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="depstatus" value="5">
							<font color="#FF0000">柜员状态</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="depstatus" value="6">
							<font color="#00DB00">设备忙</font>
						</logic:equal>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						读卡器：
					</td>
					<td>
						<logic:equal name="atmstatusinfo" property="readerstatus"
							value="1">
							<font color="#FF0000">离线</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="readerstatus"
							value="0">
							<font color="#00DB00">正常</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="readerstatus"
							value="2">
							<font color="#FF0000">电源关闭</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="readerstatus"
							value="3">
							<font color="#FF0000">不存在</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="readerstatus"
							value="4">
							<font color="#FF0000">故障</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="readerstatus"
							value="5">
							<font color="#FF0000">柜员状态</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="readerstatus"
							value="6">
							<font color="#00DB00">设备忙</font>
						</logic:equal>

					</td>

					<td>
						线路：
					</td>
					<td>
						<logic:equal name="atmstatusinfo" property="linestatus" value="1">
							<font color="#FF0000">异常</font>
						</logic:equal>
						<logic:equal name="atmstatusinfo" property="linestatus" value="0">
							<font color="#00DB00">正常</font>
						</logic:equal>
						<br>
					</td>
				</tr>
				<tr>
					<th colspan="5">
						钞箱状态
					</th>
				</tr>



				<%
					String box = ((cn.sh.ae.vo.Atm) request
								.getAttribute("atmstatusinfo")).getBox();
						for (int i = 0, j = 1; i < box.length(); i += 8, j++) {
							String boxType_1 = box.substring(i, i + 1);
							String boxType = box.substring(i + 1, i + 2);
							String moneyType = box.substring(i + 2, i + 3);
							String boxStatus = box.substring(i + 3, i + 4);
							String moneySize = box.substring(i + 4, i + 8);
							String boxTypeStr = boxType.equals("0") ? "未定义" : boxType
									.equals("1") ? "废钞箱" : boxType.equals("2") ? "纸币箱"
									: boxType.equals("3") ? "硬币箱"
											: boxType.equals("4") ? "硬币自动售货机" : boxType
													.equals("5") ? "回收箱" : boxType
													.equals("6") ? "存有优惠券的箱" : boxType
													.equals("7") ? "存有文件的箱" : boxType
													.equals("8") ? "可通过补充容器再充满的箱"
													: boxType.equals("9") ? "循环箱"
															: "未定义";
							if (boxType_1.equals("i"))
								boxTypeStr = boxType.equals("0") ? "循环箱" : boxType
										.equals("1") ? "存款箱"
										: boxType.equals("2") ? "补充箱" : boxType
												.equals("3") ? "回收箱" : "未定义";
							String moneyTypeStr = moneyType.equals("0") ? "未知"
									: moneyType.equals("1") ? "100元" : "未知";
							String boxStatusStr = boxStatus.equals("0") ? "正常"
									: boxStatus.equals("1") ? "钞满"
											: boxStatus.equals("2") ? "钞箱接近满"
													: boxStatus.equals("3") ? "钞少"
															: boxStatus.equals("4") ? "空"
																	: boxStatus
																			.equals("5") ? "钞箱处于柜员模式"
																			: boxStatus
																					.equals("6") ? "无钞箱"
																					: boxStatus
																							.equals("7") ? "钞箱中数额未知"
																							: boxStatus
																									.equals("8") ? "钞箱中未标准化"
																									: boxStatus
																											.equals("9") ? "钞箱在交易过程中被改变，不能由出钞模块操作"
																											: "未定义钞箱";
				%>
				<tr>
					<td><%=j%>钞箱
					</td>

					<td><%=boxTypeStr%>
					</td>

					<td><%=moneyTypeStr%>
					</td>


					<td>
						<%=moneySize%>
						<br>
					</td>

					<td><%=boxStatusStr%>
					</td>
				</tr>
				<%
					}
				%>




				<tr>
					<td colspan="2">
						钞箱总张数:
					</td>
					<td colspan="3">
						<logic:notEqual name="atmstatusinfo" property="partrmb" value="0">
							<bean:write name="atmstatusinfo" property="partrmb" />
						</logic:notEqual>
						<logic:equal name="atmstatusinfo" property="partrmb" value="0">
							<font color="#FF0000">缺钞</font>
						</logic:equal>
					</td>
				</tr>

				<tr>
					<th colspan="5">
						设备情况
					</th>
				</tr>
				<tr>
					<td colspan="2">
						更新时间:
					</td>
					<td colspan="3">
						<bean:write name="atmstatusinfo" property="tradetime" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						所在场所:
					</td>
					<td colspan="3">
						<bean:write name="atmstatusinfo" property="addr" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						IP地址:
					</td>
					<td colspan="3">
						<bean:write name="atmstatusinfo" property="ip" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						所属机构:
					</td>
					<td colspan="3">
						<bean:write name="atmstatusinfo" property="type" />
					</td>
				</tr>

				<tr>
					<td colspan="2">
						机器类型:
					</td>
					<td colspan="3">
						<bean:write name="atmstatusinfo" property="route" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						生产厂商:
					</td>
					<td colspan="3">
						<bean:write name="atmstatusinfo" property="company" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						摆放日期:
					</td>
					<td colspan="3">
						<bean:write name="atmstatusinfo" property="encoding" />
					</td>
				</tr>
			</table>
		</logic:notEmpty>
		<br>
		<a href="atmstatus.do?method=byId&id=${atmstatusinfo.atmid}">刷新</a>
	</body>
</html>

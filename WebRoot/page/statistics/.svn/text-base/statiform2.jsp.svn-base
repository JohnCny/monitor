<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.sun.org.apache.xalan.internal.xsltc.compiler.sym"%>
<%@ taglib prefix="cewolf" uri="/WEB-INF/cewolf.tld"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
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
		<link href="resources/source/tablecloth.css" rel="stylesheet"
			type="text/css" media="screen" />
		<base href="<%=basePath%>">
		<title></title>
		<script type="text/javascript">

		function uniteTdCells(tableId){   
        var table = document.getElementById(tableId);   
        for(i=0;i<table.rows.length;i++){   
        for(c=0;c<table.rows[i].cells.length;c++){   
            //if(c==0)            //选择要合并的列序数，去掉默认全部合并   
            for(j=i+1;j<table.rows.length;j++){   
            var cell1=table.rows[i].cells[c].innerHTML;   
            var cell2=table.rows[j].cells[c].innerHTML;   
            	if(cell1==cell2){   
                    	table.rows[j].cells[c].style.display='none';   
                    	table.rows[i].cells[c].rowSpan++;   
            			} else break;                        
            		}   
        		}       
        	}    
        }   


		</script>
	</head>

	<body onLoad="uniteTdCells('table1')">
		<logic:present name="categoryData">
			<logic:present name="categoryImg">
				<table cellspacing="0" cellpadding="0">
					<tr>
						<td valign="top">
							<table cellspacing="0" cellpadding="0">
								<tr>
									<th>
										设备厂商
									</th>
									<th>
										设备数量(台)
									</th>

									<logic:iterate id="tlr" name="categoryData">
										<tr>
											<td>
												<bean:write name="tlr" property="atmid" />
											</td>
											<td>
												<bean:write name="tlr" property="allstatus" />
											</td>
										</tr>
									</logic:iterate>
								</tr>
							</table>
						</td>
						<td valign="top">
							<cewolf:chart id="pieT" type="pie">
								<cewolf:data>
									<cewolf:producer id="categoryImg" />
								</cewolf:data>
							</cewolf:chart>

							<cewolf:img chartid="pieT" renderer="/cewolf" width="500"
								height="375">
							</cewolf:img>
						</td>
					</tr>
				</table>
			</logic:present>
		</logic:present>

		<logic:present name="atmhistory_bug">
			<table cellspacing="0" cellpadding="0" id="table1">
				<tr>
					<th>
						设备编号
					</th>
					<th>
						故障设备
					</th>
					<th>
						开始时间
					</th>
					<th>
						结束时间
					</th>
					<th>
						持续时间(分钟)
					</th>
					<th>
						型号地址
					</th>
				</tr>
				<logic:iterate id="tlr" name="atmhistory_bug">
					<tr>
						<td>
							<bean:write name="tlr" property="atmId" />
						</td>
						<td>
							<bean:write name="tlr" property="bugId" />
						</td>
						<td>
							<bean:write name="tlr" property="bugBeg" />
						</td>
						<td>
							<bean:write name="tlr" property="bugEnd" />
						</td>
						<td>
							<bean:write name="tlr" property="subTime" />
						</td>
						<td>
							<bean:write name="tlr" property="constant" />
						</td>
					</tr>
				</logic:iterate>
			</table>
		</logic:present>
	</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib prefix="tiles" uri="/WEB-INF/struts-tiles.tld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>">
		<title></title>
		<script type="text/javascript" src="resources/scripts/jquery-1.6.js"></script>
		<style>
			#changeBgLess{
				
			}
		</style>
		<script type="text/javascript">
         $(document).ready(function(){
    
      $(".xq_load").click(function(){
      		var value=$(this).attr("name")
            $.ajax({
                 url:'atmstatus.do?method=byId&id='+value, 
                 type: 'get',
                 cache:false,
                 timeout: 2000,
                 error: function(){
                    alert('错误');
                 },
                success: function(json){
                
                var users = eval('(' + json + ')');
               //返回的是List
               $.each(users,function(i, user){       
               		var atmInfo="<td colspan='4' valign='top'><table cellspacing='0' cellpadding='0'>"+
                  							"<tr><th colspan='2'>设备信息</th></tr>"+
                  							"<tr><td style='text-align: right;'>更新时间:</td><td style='text-align: left;'>" + user.tradetime + "</td></tr>"+
                  							"<tr><td style='text-align: right;'>所在场所:</td><td style='text-align: left;'>"+ user.addr +"</td></tr>"+
                  							"<tr><td style='text-align: right;'>IP地址:</td><td style='text-align: left;'>"+user.ip+"</td></tr>"+
                  							"<tr><td style='text-align: right;'>所属机构:</td><td style='text-align: left;'>"+user.type+" ["+user.atmid_new+"]</td></tr>"+
                  							"<tr><td style='text-align: right;'>设备类型:</td><td style='text-align: left;'>"+user.route+"</td></tr>"+
                  							"<tr><td style='text-align: right;'>生产厂商:</td><td style='text-align: left;'>"+user.company+"</td></tr>"+
                  							"<tr><td style='text-align: right;'>开通日期:</td><td style='text-align: left;'>"+user.encoding+"</td></tr></table></td>";
              	  	var boxInfo="<td colspan='8' valign='top'><table cellspacing='0' cellpadding='0'>"+
              	   							"<tr><th colspan='6'>钞箱信息</th></tr>"
										for (var i = 0, j = 1; i < user.box.length; i += 8, j++){
              	   							boxInfo+="<tr><td  style='text-align: right;'>"+j+"钞箱:</td>"
              	   							var box1=user.box.substring(i, i + 1);
              	   							var box2=user.box.substring(i + 1, i + 2);
              	   							var box3=user.box.substring(i + 2, i + 3);
              	   							var box4=user.box.substring(i + 3, i + 4);
              	   							if(box1=='o'){box1='取款箱';if(box2=='1')box2='废钞箱';else if(box2=='2')box2='纸币箱';else if(box2=='3')box2='硬币箱';else if(box2=='5')box2='回收箱';else if(box2=='9')box2='循环箱';else box2='未定义';};
              	   							if(box1=='i'){box1='存款箱';if(box2=='0')box2='循环箱';else if(box2=='1')box2='存款箱';else if(box2=='2')box2='补充箱';else if(box2=='3')box2='回收箱';else box2='未定义';}				
              	   							if(box3=='0')
              	   								box3='未定义';
              	   							else if(box3=='1')
              	   								box3='100元';
              	   							if(box4=='0')box4='正常';else if(box4=='1')box4='钞满';else if(box4=='2')box4='接近满';else if(box4=='3')box4='钞少';else if(box4=='4')box4='空';else if(box4=='5')box4='柜员模式';else if(box4=='6')box4='无钞箱';else if(box4=='7')box4='数额未知';else if(box4=='8')box4='未标准化';else box4='未定义';
              	   							boxInfo+="<td style='text-align: left;'>" + box1 + "</td>"
              								boxInfo+="<td style='text-align: left;'>" + box2 + "</td>"
											boxInfo+="<td style='text-align: left;'>" + box3 + "</td>"
											boxInfo+="<td style='text-align: left;'>" + box4 + "</td>"
											boxInfo+="<td style='text-align: left;'>" + user.box.substring(i + 4, i + 8) + "</td></tr>"   								
              							}  	
              							boxInfo+="</table></td>"			
              	   $("#id"+value).html(atmInfo+boxInfo);
              								
               });
               
                 }
         });
        
     });      
        
   
   $(".jt_load").click(function(){
      		var value=$(this).attr("name")
            $.ajax({
                 url:'snapShot.do?id='+value, 
                 type: 'get',
                 cache:false,
                 timeout: 120000,
                 error: function(){
                    alert('超时错误');
                 },
                success: function(json){
                
                var users = eval('(' + json + ')');
               //返回的是List
               $.each(users,function(i, user){       
                  $("#id"+value).html("<td colspan='11'><img src='"+user.route+"' width='600' height='337' border='0' /></td>");				
               });
               
                 }
         });
        
     }); 
     
     //ajax提交
    $("#loading").ajaxStart(function() {
        $(this).html("<img src='resources/source/8.gif'>&nbsp;正在加载...");
    });

    //ajax成功状态
    $("#loading").ajaxSuccess(function() {
        //   $(this).html("");
    });

    //ajax结束状态
    $("#loading").ajaxStop(function() {
        $(this).html("");
    });
   
    });

         </script>

	</head>

	<body>
		<table cellspacing="0" cellpadding="0">
			<logic:iterate id="tlr" name="atmstatus1">
			
				<tr id="change${tlr.atmid}">
					<td width="10%" id="${tlr.atmid}">
						<bean:write name="tlr" property="atmid" />
					</td>
					<td width="15%">
						<bean:write name="tlr" property="company" />
						-
						<bean:write name="tlr" property="route" />
					</td>
					<td width="10%">
						<bean:write name="tlr" property="addr" />
					</td>
					<td width="10%">
						<logic:equal name="tlr" property="prjstatus" value="4">
							<font color="#FF0000">异常</font>
						</logic:equal>
						<logic:equal name="tlr" property="prjstatus" value="0">
							<font color="#00DB00">正常</font>
						</logic:equal>
						<logic:equal name="tlr" property="prjstatus" value="1">
							<font color="#FF0000">故障</font>
						</logic:equal>
						<logic:equal name="tlr" property="prjstatus" value="2">
							<font color="#FF8000">纸少</font>
						</logic:equal>
						<logic:equal name="tlr" property="prjstatus" value="3">
							<font color="#FF8000">纸尽</font>
						</logic:equal>
						<logic:equal name="tlr" property="prjstatus" value="5">
							<font color="#FF0000">未定义</font>
						</logic:equal>
						<logic:equal name="tlr" property="prjstatus" value="6">
							<font color="#FF8000">卡纸</font>
						</logic:equal>
					</td>
					<td width="10%">
						<logic:equal name="tlr" property="prrstatus" value="4">
							<font color="#FF0000">异常</font>
						</logic:equal>
						<logic:equal name="tlr" property="prrstatus" value="0">
							<font color="#00DB00">正常</font>
						</logic:equal>
						<logic:equal name="tlr" property="prrstatus" value="1">
							<font color="#FF0000">故障</font>
						</logic:equal>
						<logic:equal name="tlr" property="prrstatus" value="2">
							<font color="#FF8000">纸少</font>
						</logic:equal>
						<logic:equal name="tlr" property="prrstatus" value="3">
							<font color="#FF8000">纸尽</font>
						</logic:equal>
						<logic:equal name="tlr" property="prrstatus" value="5">
							<font color="#FF0000">未定义</font>
						</logic:equal>
						<logic:equal name="tlr" property="prrstatus" value="6">
							<font color="#FF8000">卡纸</font>
						</logic:equal>
					</td>
					<td width="10%">
						<logic:equal name="tlr" property="cdmstatus" value="1">
							<font color="#FF0000">离线</font>
						</logic:equal>
						<logic:equal name="tlr" property="cdmstatus" value="0">
							<font color="#00DB00">正常</font>
						</logic:equal>
						<logic:equal name="tlr" property="cdmstatus" value="2">
							<font color="#FF0000">电源关闭</font>
						</logic:equal>
						<logic:equal name="tlr" property="cdmstatus" value="3">
							<font color="#FF0000">不存在</font>
						</logic:equal>
						<logic:equal name="tlr" property="cdmstatus" value="4">
							<font color="#FF0000">故障</font>
						</logic:equal>
						<logic:equal name="tlr" property="cdmstatus" value="5">
							<font color="#FF0000">柜员状态</font>
						</logic:equal>
						<logic:equal name="tlr" property="cdmstatus" value="6">
							<font color="#00DB00">设备忙</font>
						</logic:equal>
					</td>
					<td width="10%">
						<logic:equal name="tlr" property="depstatus" value="1">
							<font color="#FF0000">离线</font>
						</logic:equal>
						<logic:equal name="tlr" property="depstatus" value="0">
							<font color="#00DB00">正常</font>
						</logic:equal>
						<logic:equal name="tlr" property="depstatus" value="2">
							<font color="#FF0000">电源关闭</font>
						</logic:equal>
						<logic:equal name="tlr" property="depstatus" value="3">
							<font color="#FF0000">不存在</font>
						</logic:equal>
						<logic:equal name="tlr" property="depstatus" value="4">
							<font color="#FF0000">故障</font>
						</logic:equal>
						<logic:equal name="tlr" property="depstatus" value="5">
							<font color="#FF0000">柜员状态</font>
						</logic:equal>
						<logic:equal name="tlr" property="depstatus" value="6">
							<font color="#00DB00">设备忙</font>
						</logic:equal>
					</td>
					<td width="5%">
						<logic:equal name="tlr" property="readerstatus" value="1">
							<font color="#FF0000">离线</font>
						</logic:equal>
						<logic:equal name="tlr" property="readerstatus" value="0">
							<font color="#00DB00">正常</font>
						</logic:equal>
						<logic:equal name="tlr" property="readerstatus" value="2">
							<font color="#FF0000">电源关闭</font>
						</logic:equal>
						<logic:equal name="tlr" property="readerstatus" value="3">
							<font color="#FF0000">不存在</font>
						</logic:equal>
						<logic:equal name="tlr" property="readerstatus" value="4">
							<font color="#FF0000">故障</font>
						</logic:equal>
						<logic:equal name="tlr" property="readerstatus" value="5">
							<font color="#FF0000">柜员状态</font>
						</logic:equal>
						<logic:equal name="tlr" property="readerstatus" value="6">
							<font color="#00DB00">设备忙</font>
						</logic:equal>

					</td>
					<td width="5%">
						<logic:equal name="tlr" property="linestatus" value="1">
							<font color="#FF0000">异常</font>
						</logic:equal>
						<logic:equal name="tlr" property="linestatus" value="0">
							<font color="#00DB00">正常</font>
						</logic:equal>
					</td>
					<td width="5%">
						<logic:notEqual name="tlr" property="partrmb" value="0">
							<bean:write name="tlr" property="partrmb" />
						</logic:notEqual>
						<logic:equal name="tlr" property="partrmb" value="0">
							<font color="#FF0000">缺钞</font>
						</logic:equal>
					</td>
					<!-- <td width="10%">
								<bean:write name="tlr" property="depormb" />
							</td> -->
					
					<td width="10%">
						<input type="button" name="${tlr.atmid}" class="xq_load"
							value="详情">
						<input type="button" name="${tlr.atmid}" class="jt_load"
							value="截图">

						<!-- <a href="snapShot.do?id=${tlr.atmid}">截屏</a> -->
					</td>
				</tr>
				<tr id="id${tlr.atmid}">

				</tr>

			</logic:iterate>
		</table>

	</body>
</html>

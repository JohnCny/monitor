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
<html>
	<head>
		<base href="<%=basePath%>">
		<title>欢迎使用乾康自助终端管理平台</title>
		<link href="resources/scripts/global.css" rel="stylesheet"
			type="text/css" />
		<link href="resources/scripts/user.css" rel="stylesheet"
			type="text/css" />
		<style type="text/css">
#menu_out {
	width: 100%;
	padding-left: 4px;
	margin-left: auto;
	margin-right: auto;
	background: url(resources/source/menu/menu_left.gif) no-repeat left top;
}

#menu_in {
	background: url(resources/source/menu/menu_right.gif) no-repeat right
		top;
	padding-right: 4px;
}

#menu {
	background: url(resources/source/menu/menu_bg.gif) repeat-x;
	height: 73px;
}
</style>
		<script type="text/javascript" language="javascript">
    function AddFavorite(sURL, sTitle)
    {
        try
        {
            window.external.addFavorite(sURL, sTitle);
        }
        catch (e)
        {
            try
            {
                window.sidebar.addPanel(sTitle, sURL, "");
            }
            catch (e)
            {
                alert("加入收藏失败，请使用Ctrl+D进行添加");
            }
        }
    }
    function SetHome(obj,vrl)
    {
        try
        {
                obj.style.behavior='url(#default#homepage)';obj.setHomePage(vrl);
        }
        catch(e){
                if(window.netscape) {
                        try {
                                netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect"); 
                        } 
                        catch (e) { 
                                alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入“about:config”并回车\n然后将[signed.applets.codebase_principal_support]设置为'true'"); 
                        }
                        var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
                        prefs.setCharPref('browser.startup.homepage',vrl);
                 }
        }
    }
    </script>
	</head>

	<body style="text-align: center; background: #E6EAE9;">
		<div class="page">
			<!-- <h1 class="logo">
				<img src="resources/source/images/logo.gif" id="logo" />
			</h1> -->
			<div id="menu_out">
				<div id="menu_in">
					<div id="menu">
						<div class="header">

							<div class="toplinks">
								<a onclick="SetHome(this,window.location)" style="cursor: hand"><span>设为首页</span>
								</a> |
								<a onclick="AddFavorite(window.location,document.title)"
									style="cursor: hand"><span>加入收藏</span>
								</a> |
								<a onclick="javascript:return alert('暂无帮助');"
									style="cursor: hand"><span>帮助中心
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="body">
				<div class="part main-part">
					<div class="mod login-mod">
						<form action="user.do?method=login" method="post" name="tlrForm">
							<h2>
								企业用户登录
							</h2>
							<div class="fi">
								<label class="tit">
									用户名
								</label>
								<input type="text" name="username" id="username"
									class="ipt-t ipt-sample" value="" />
							</div>
							<div class="fi">
								<label class="tit">
									密 码
								</label>
								<input type="password" class="ipt-t" name="password"
									id="password" />
							</div>

							<div class="fi fi-notit">
								<label for="remUsername">
									<input name="remUser" id="remUsername" value="1"
										type="checkbox" class="ipt-c" />
									记住用户名
								</label>
								<input type="button" class="ipt-b" value="登　录"
									onclick="sysLogin()" />
							</div>
							<div class="goto">
								<logic:present name="loginerrorinfo">
									<bean:write name="loginerrorinfo" />
								</logic:present>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
<script type="text/javascript">		
		function sysLogin(){
			var username=document.getElementById("username").value;
			var password=document.getElementById("password").value;
			var expiration = new Date((new Date()).getTime()+60000*60*24*30 );   
			document.cookie = "UserName=" + username+ "; expires ="  
			+ expiration.toGMTString() ;
			document.cookie = "Password=" + password+ "; expires ="  
			+ expiration.toGMTString() ;
			document.forms[0].submit();
		}
		
		function document.onkeydown() { 
			if(event.keyCode == 13) { 
				document.getElementById("cu").click(); 
				return false; 
			} 
		} 
		
</script>

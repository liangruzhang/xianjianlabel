<%@ page
    contentType="text/html; charset=UTF-8" 
    language="java" 
%>
<!DOCTYPE HTML>
<HEAD>
<TITLE>Barcode Management System</TITLE>
	<script language="JavaScript" src="../js/md5.js"></script>
	<script language="JavaScript" src="../js/md5PasswordEncoder.js"></script>
	<script language="javascript">
		function onWebload(){
			var language = navigator.browserLanguage;
			//if("zh-cn" == language) {
			//	document.forms[0].languageRadio[0].checked = true;
			//} else {
				document.forms[0].languageRadio[1].checked = true;
			//}
		}
	</script> 
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<STYLE type=text/css media=screen>
@import url( ./login.css);
</STYLE>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</HEAD>
<% 
    String errorMessage =(String) request.getAttribute("errorMessage");
	if (errorMessage==null || "".equals(errorMessage)) {
		errorMessage=(String)request.getParameter("errorMessage");
	}
	String companyCode=(String)request.getAttribute("companyCode");
	companyCode=(companyCode==null) ? "" : companyCode;
	String userId = (String)request.getAttribute("userId");
	userId=(userId==null)? "" : userId;
	request.removeAttribute("errorMessage");
	request.removeAttribute("companyCode");
	request.removeAttribute("userId");
%>

<BODY onload="onWebload()">
<DIV id=HeaderPub>
<DIV class=logo><IMG alt="WEB Barcode" src="./logo_login.jpg" ></DIV>
</DIV>
<DIV id=PageContainer>
	<DIV id=PageHome>
		<DIV class=desc>
			<P>Barcode system modifications.</P>
		</DIV>
		<DIV class="container_rnd loginBox">
			<DIV class=left>
				<DIV class=right>
					<DIV class=top>
						<DIV class="c_tl crn"></DIV>
						<DIV class="c_tr crn"></DIV>
					</DIV>
					<DIV class=content>
						<DIV><IMG height=30 alt="" src="h_existingusers.gif"></DIV>
						<FORM class=signin id=signin action="/login/login" method=post>
							<INPUT type=hidden value=login name=action> 
								<% 
									if (errorMessage!=null && !"".equals(errorMessage.trim())) {
								%>
								<div class="row error"><%= errorMessage %>${requestScope.errorMessage} </div>							
								<%
									}
								%>						
							
							<DIV class=row>
								<DIV class="cell label">Company:</DIV>
								<DIV class=cell><INPUT class=entry maxLength=30 name="companyCode" value='xianjian' readonly="readonly"></DIV>
							</DIV>						
							<DIV class=row>
								<DIV class="cell label">Username:</DIV>
								<DIV class=cell><INPUT class=entry maxLength=30 name="userId" value=<%= userId %>></DIV>
							</DIV>
							<DIV class=row>
								<DIV class="cell label">Password:</DIV>
								<DIV class=cell><INPUT class=entry type=password maxLength=30 name="plain_password" value=''></DIV>
								<input name="password" type="hidden">
							</DIV>
							<DIV class=row style="display:none">
								<DIV class="cell label">Language:</DIV>
								<DIV class=cell><INPUT type=radio name=languageRadio value='ch' disabled="true">chinese &nbsp;&nbsp;
										<INPUT  type=radio name=languageRadio value='en'>english
								</DIV>								
							</DIV>
							<DIV class=row>
								<DIV class="cell label">&nbsp;</DIV>
								<DIV class=cell><INPUT class=button type=image alt="" src="signin.gif" onClick="encodePassword(userId, plain_password, password)"></DIV>
							</DIV>
						</FORM>
					</DIV>
					<DIV class=bottom>
						<DIV class="c_bl crn"></DIV>
						<DIV class="c_br crn"></DIV>
					</DIV>
				</DIV>
			</DIV>
		</DIV>
	</DIV>
</DIV>
<DIV  id=FooterPub>
	<div>

	</div>
	<DIV class=content>
		<DIV>2016 XIAN JIAN Limited, All Rights Reserved</DIV>
	</DIV>
</DIV>
</BODY>
</HTML>

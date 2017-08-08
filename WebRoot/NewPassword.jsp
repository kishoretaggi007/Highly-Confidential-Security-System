

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<head id="ctl00_header">
		<head>

			<script language="JavaScript" src="scripts/gen_validatorv31.js"
				type="text/javascript">
</script>
			<script language="JavaScript" type="text/javascript"
				src="scripts/ts_picker.js">
</script>
			<script language="JavaScript1.1" src="scripts/pass.js">
</script>
			<script type="text/javascript" src="scripts/image.js">
</script>
			<script type="text/javascript" src="scripts/general.js">
</script>
			<script type="text/javascript" src="scripts/adi.js">
</script>
			<script type="text/javascript" src="scripts/form_validation.js">
</script>

			<script language="JavaScript" src="images/javascripts.js">
</script>
			<script language="JavaScript" src="images/pop-closeup.js">
</script>




			<style type="text/css">
.Title {
	font-family: Verdana;
	font-weight: bold;
	font-size: 8pt
}

.Title1 {
	font-family: Verdana;
	font-weight: bold;
	font-size: 8pt
}
</style>
		</head>
		<body>

			<jsp:include page="Header.jsp"></jsp:include>
			<br />
			<center>
			<h3>

				<span class=subHead><br />Enter New Password </span>
			</h3>
			</center>

			<form method='post' action="./newPasswordAction.do" name="register"
				onsubmit="return validate()">
				<!--<table border="1"><tr><td></td></tr></table>-->
				<input type="hidden" name="port" value="<%=request.getLocalPort()%>" />
				<input type="hidden" name="host"
					value="<%=request.getServerName()%>" />
				<br />

				<table align="center">

					<tr>
						<td align='right'>
							User Name :
						</td>
						<td>
							<input type="text" name="loginid"
								value="<%=request.getParameter("loginid")%>" size="20" readonly />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;


						
					</tr>
					<tr>
						<td align='right'>
							New PassWord :
						</td>
						<td>
							<input type="password" name="password"
								onkeyup="testPassword(document.forms.register.password.value);"
								onchange="Encrypt(document.forms.register.password.value);" />
						</td>
					</tr>
					<tr>
						<td></td>
						<td align='right'>
							<a id="Words"> Strength :</a>
						</td>
						<td>
							<table border='0' cellpadding=0 cellspacing=0 colspan='2'>
								<tr>
									<td height=15 bgcolor=red></td>
								</tr>
							</table>
						</td>


					</tr>
					<tr>
						<td align='right'>
							Confirm :
						</td>
						<td>
							<input type="password" name="conformpassword" value="" size="20"
								onBlur="checkconformpassword()" />
						</td>

					</tr>
					<tr>
						<td align='right'>
							<input type="submit" name="Submit" value="submit" />
						</td>
						<td align='left'>
							<input type="reset" name="reset" value="reset" />
						</td>
					</tr>
				</table>
			</form>

			<script language="JavaScript" type="text/javascript">
//You should create the validator only after the definition of the HTML form
var frmvalidator = new Validator("register");

frmvalidator.addValidation("loginid", "req", "Please enter your Username");
frmvalidator.addValidation("password", "req", "Please enter your Password");
frmvalidator.addValidation("conformpassword", "req",
		"Please enter your Confirm Password");
</script>

			<br />
			<br />

			<jsp:include page="Footer.jsp"></jsp:include>


		</body>
</html>
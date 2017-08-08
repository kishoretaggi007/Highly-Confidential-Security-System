<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<head>
		<script language="JavaScript" src="scripts/gen_validatorv31.js"
			type="text/javascript">
</script>
		<script language="JavaScript1.1" src="scripts/pass.js">
</script>
<script language="JavaScript">
function checkconformpassword() {
	var password = mails.password.value;
	var conformpassword = mails.conformpassword.value

	if (password != conformpassword) {
		alert("ConformPassword does not matche with Password");
		return false;
	}
	return true;
}</script>
	</head>
	<body>
		<jsp:include page="Header.jsp"></jsp:include>
		<center>
		<h3>
			<span class=subHead><br /><font color='white'>Add Mails Details</font></span>
		</h3>
		</center>

		<form action="./addMailDetailsAction.do" name="mails" method='post'
			onsubmit="return validate()">
			<!--<table border="1"><tr><td></td></tr></table>-->
			<table border='0' align="center" width=40% >
				<th colspan="2" bgcolor="white">
					Mails Details
				</th>
				<tr></tr>
				<tr></tr>
				<tr>
					<td align='right'>
						Site Name:
					</td>
					<td>
						<input type="text" name="sitename" value="" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td align='right'>
						Site Url :
					</td>
					<td>
						<input type="text" name="siteurl" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td align='right'>
						Mail Id:
					</td>
					<td>
						<input type="text" name="mailidstring" value="" size="20" />
					</td>
				</tr>
				<tr>
					<td align='right'>
						Password :
					</td>
					<td>
						<input type="password" name="password" value=""
							size="20"
							onkeyup="testPassword(document.forms.mails.password.value);"
							onchange="Encrypt(document.forms.mails.password.value);" />
					</td>

				</tr>
				<tr>
					<td>
						<a id="Words"> Strength :</a>
					</td>

					<td>
						<center>
						<table border='0' cellpadding=0 cellspacing=0 colspan='2'>
							<tr>
								<td height=15 bgcolor=red></td>
							</tr>
						</table>
						</center>
					</td>

				</tr>
				<tr>
					<td align='right'>
						Confirm :
					</td>
					<td>
						<input type="password" name="conformpassword"
							value="" size="20"
							onBlur="checkconformpassword()" />
					</td>
				<tr>
					<td align="right">
						<input type="submit" name="Add" value="Add" />
					</td>
					<td align="left">
						<input type="reset" name="cancel" value="Cancel" />
					</td>
					<td>
						<input type='hidden' name='useridref'
							value='<%=session.getAttribute("userid")%>' />
						<input type='hidden' name='status' value='Active' />
					</td>
				</tr>
			</table>
			<p>
				<br />
				<br />
				<br />
			</p>
			<p>
				&nbsp;
			</p>
			<p>
				&nbsp;
			</p>
			<p>
				&nbsp;
			</p>
			<p>
				<br />
			</p>
		</form>
		<jsp:include page="Footer.jsp"></jsp:include>
		<script language="JavaScript" type="text/javascript">
//You should create the validator only after the definition of the HTML form
var frmvalidator = new Validator("mails");

frmvalidator.addValidation("sitename", "req", "Please enter your Site Name");

frmvalidator.addValidation("siteurl", "req", "Please enter your URL");

frmvalidator.addValidation("mailidstring", "maxlen=50");
frmvalidator.addValidation("mailidstring", "req");
frmvalidator.addValidation("mailidstring", "email");

frmvalidator.addValidation("password", "req", "Please enter your Password");
frmvalidator.addValidation("conformpassword", "req", "Please enter your conform Password");
</script>


	</body>
</html>
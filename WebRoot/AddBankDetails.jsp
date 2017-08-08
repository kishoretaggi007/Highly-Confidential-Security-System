<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<script language="JavaScript">
function checkconformpassword() {
	var password = bank.password.value;
	var confirmpassword = bank.confirmpassword.value

	if (password != confirmpassword) {
		alert("Password does not match");
		return false;
	}
	return true;
}</script>
		<script>
function checkconformatmpin() {
	var atmpin = bank.atmpin.value;
	var conatmpin = bank.conatmpin.value

	if (atmpin != conatmpin) {
		alert("ConfirmPin does not matche with Pin");
		return false;
	}
	return true;
}</script>
	</head>
	<body>
		<jsp:include page="Header.jsp"></jsp:include>
		<center>
		<h3>
			<span class=subHead><br />
				<font color='white'>Add Bank Details</font>
			</span>
		</h3>
		</center>

		<form action="./addBankDetailsAction.do" name="bank" method='post'
			onsubmit="return validate()">
			<!--<table border="1"><tr><td></td></tr></table>-->
			<table border='0' align="center" width=40% >
				<th colspan="2" bgcolor="white">
					Bank Details
				</th>
				<tr></tr>
				<tr></tr>
				<tr>
					<td align='right'>
						<b>Name of Bank:</b>
					</td>
					<td>
						<input type="text" name="bankName" value="" size="20" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td align='right'>
						<b>Branch Name :</b>
					</td>
					<td>
						<input type="text" name="branchName" />
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td align='right'>
						<b>Account No:</b>
					</td>
					<td>
						<input type="text" name="accnumber" value="" size="20" />
					</td>
				</tr>
				<tr>
					<td align='right'>
						<b>UserName :</b>
					</td>
					<td>
						<input type="text" name="username" value="" size="20" />
					</td>
				</tr>
				<tr>
					<td colspan="2" align='center'>

					</td>
				</tr>
				<tr>
					<td align='right'>
						<b>Password :</b>
					</td>
					<td>
						<input type="password" name="password"
							onkeyup="testPassword(document.forms.bank.password.value);"
							onchange="Encrypt(document.forms.bank.password.value);" />
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
						<b>Confirm :</b>
					</td>
					<td>
						<input type="password" name="conformpassword" value="" size="20"
							onBlur="checkconformpassword()" />
					</td>

				</tr>
				<tr>
					<td align='right'>
						<b>Atm CardNo :</b>
					</td>
					<td>
						<input type="text" name="atmcardno" value="" size="20" />
					</td>
				</tr>
				<tr>
					<td align='right'>
						<b>Atm Pin :</b>
					</td>
					<td>
						<input type="password" name="atmpin" value="" size="20"
							onkeyup="testPassword(document.forms.bank.atmpin.value);"
							onchange="Encrypt(document.forms.bank.atmpin.value);" />
					</td>
				</tr>
				<tr>
					<td align='right'>
						<b>Confirm :</b>
					</td>
					<td>
						<input type="password" name="conatmpin" value="" size="20"
							onBlur="checkconformatmpin()" />
					</td>

				</tr>
				<tr>
					<td align='right'>
						<b>SiteURL:</b>
					</td>
					<td>
						<input type="text" name="siteurl" value="" size="20" />
					</td>
				</tr>
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


		<script language="JavaScript" type="text/javascript">
//You should create the validator only after the definition of the HTML form
var frmvalidator = new Validator("bank");

frmvalidator.addValidation("bankName", "req", "Please enter your Bank Name");
frmvalidator.addValidation("bankName", "alpha");

frmvalidator.addValidation("branchName", "req", "Please enter your Branch Name");
frmvalidator.addValidation("branchName", "alpha");
frmvalidator.addValidation("accnumber", "req", "Please enter your Acc number");
frmvalidator.addValidation("accnumber", "numeric");
frmvalidator.addValidation("accnumber", "maxlen=14");
frmvalidator.addValidation("username", "req", "Please enter your UserName");
frmvalidator.addValidation("username", "alpha");
frmvalidator.addValidation("password", "req", "Please enter your UserName");
frmvalidator.addValidation("password", "alpha");
frmvalidator.addValidation("conformpassword", "req", "Please conform your password");
frmvalidator.addValidation("conformpassword", "alpha");


frmvalidator.addValidation("atmcardno", "req", "Please enter your Atm card no");
frmvalidator.addValidation("atmcardno", "numeric");
frmvalidator.addValidation("atmcardno", "maxlen=14");
frmvalidator.addValidation("atmpin", "req", "Please enter your Atm pin no");
frmvalidator.addValidation("atmpin", "maxlen=5");
frmvalidator.addValidation("atmpin", "numeric");
frmvalidator.addValidation("conatmpin", "req", "Please enter your Atm conformpin no");
frmvalidator.addValidation("conatmpin", "maxlen=5");
frmvalidator.addValidation("conatmpin", "numeric");
frmvalidator.addValidation("siteurl", "req", "Please enter your Site URL");
</script>
		<jsp:include page="Footer.jsp"></jsp:include>


	</body>
</html>
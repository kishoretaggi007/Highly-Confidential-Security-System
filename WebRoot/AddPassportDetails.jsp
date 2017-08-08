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
	</head>
	<body>
		<jsp:include page="Header.jsp"></jsp:include>
		<center>
		<h3>
			<span class=subHead><br /><font color='white'>Add PassPort Details</font></span>
		</h3>
		</center>

		<form action="./addPassportDetailsAction.do" name="passport"
			method='post' onsubmit="return validate()">
			<!--<table border="1"><tr><td></td></tr></table>-->
			<table border='0' align="center" width=60% >
				<th colspan="3" bgcolor="white">
					PassPort Details
				</th>
				<tr></tr>
				<tr></tr>
				<tr>
					<td align='right'>
						Passport Number :
					</td>
					<td>
						<input type="text" name="passportno" value="" size="20" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td align='right'>
						IssudDate :
					</td>
					<td>
						<input type="text" name="issueddate" value="" />
						<a
							href="javascript:show_calendar('document.passport.issueddate', document.passport.issueddate.value);">
							<img src="images/cal.gif" alt="a" width="18" height="18"
								border="0" /> </a>
					</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td align='right'>
						ValidatyDate :
					</td>
					<td>
						<input type="text" name="validitydate" value="" size="20" />
						<a
							href="javascript:show_calendar('document.passport.validitydate', document.passport.issueddate.value);">
							<img src="images/cal.gif" alt="a" width="18" height="18"
								border="0" /> </a>
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
var frmvalidator = new Validator("passport");

frmvalidator.addValidation("passportno", "req",
		"Please enter your Passport Number");
frmvalidator.addValidation("issueddate", "req", "Please enter your IssudDate");
frmvalidator.addValidation("validitydate", "req",
		"Please enter your Validity Date");
</script>
		<jsp:include page="Footer.jsp"></jsp:include>


	</body>
</html>